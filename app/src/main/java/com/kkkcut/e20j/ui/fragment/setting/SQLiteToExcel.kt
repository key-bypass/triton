package com.kkkcut.e20j.ui.fragment.setting

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.kkkcut.e20j.utils.SecurityUtil
import org.apache.poi.hssf.usermodel.HSSFClientAnchor
import org.apache.poi.hssf.usermodel.HSSFRichTextString
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.ClientAnchor
import org.apache.poi.ss.usermodel.Drawing
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.File
import java.io.FileOutputStream
import java.util.Arrays
import java.util.Locale

/* loaded from: classes.dex */
class SQLiteToExcel private constructor(
    private var tables: List<String>?,
    private val protectKey: String?,
    private val encryptKey: String?,
    private val fileName: String?,
    str4: String?,
    private val filePath: String,
    private val sql: String?,
    private val sheetName: String?
) {
    private var database: SQLiteDatabase? = null
    private var workbook: Workbook? = null

    /* loaded from: classes.dex */
    interface ExportListener {
        fun onCompleted(str: String)

        fun onError(exc: Exception)

        fun onStart()
    }

    /* loaded from: classes.dex */
    class Builder(context: Context?) {
        private var dataBaseName: String? = null
        private var encryptKey: String? = null
        private var fileName: String? = null
        private var filePath: String
        private var protectKey: String? = null
        private var sheetName: String? = null
        private var sql: String? = null
        private var tables: List<String>? = null

        init {
            this.filePath = context!!.getExternalFilesDir(null)!!.getPath()
        }

        fun build(): SQLiteToExcel {
            if (TextUtils.isEmpty(this.dataBaseName)) {
                throw IllegalArgumentException("Database name must not be null.")
            }
            if (TextUtils.isEmpty(this.fileName)) {
                throw IllegalArgumentException("Output file name must not be null.")
            }
            return SQLiteToExcel(
                this.tables,
                this.protectKey,
                this.encryptKey,
                this.fileName,
                this.dataBaseName,
                this.filePath,
                this.sql,
                this.sheetName
            )
        }

        fun setDataBase(str: String?): Builder {
            this.dataBaseName = str
            this.fileName = File(str).getName() + ".xls"
            return this
        }

        @Deprecated("")
        fun setFileName(str: String?): Builder {
            return setOutputFileName(str)
        }

        fun setOutputFileName(str: String?): Builder {
            this.fileName = str
            return this
        }

        fun setProtectKey(str: String?): Builder {
            this.protectKey = str
            return this
        }

        fun setEncryptKey(str: String?): Builder {
            this.encryptKey = str
            return this
        }

        fun setTables(vararg strArr: String?): Builder {
            this.tables = Arrays.asList(*strArr) as List<String>?
            return this
        }

        @Deprecated("")
        fun setPath(str: String): Builder {
            return setOutputPath(str)
        }

        fun setOutputPath(str: String): Builder {
            this.filePath = str
            return this
        }

        fun setSQL(str: String?, str2: String?): Builder {
            this.sql = str2
            this.sheetName = str
            return this
        }

        fun setSQL(str: String?): Builder {
            return setSQL("Sheet1", str)
        }

        fun start(): String? {
            return build().start()
        }

        fun start(exportListener: ExportListener?) {
            build().start(exportListener)
        }
    }

    fun start(): String? {
        try {
            val list: List<String>? = this.tables
            if (list == null || list.size == 0) {
                this.tables = getTablesName(this.database)
            }
            return exportTables(this.tables, this.fileName)
        } catch (unused: Exception) {
            val sQLiteDatabase: SQLiteDatabase? = this.database
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return null
            }
            database!!.close()
            return null
        }
    }

    fun start(exportListener: ExportListener?) {
        if (exportListener != null) {
            exportListener.onStart()
        }
        Thread(object : Runnable {
            // from class: com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.1
            // java.lang.Runnable
            override fun run() {
                try {
                    if (this@SQLiteToExcel.tables == null || tables!!.size == 0) {
                        val sQLiteToExcel: SQLiteToExcel = this@SQLiteToExcel
                        sQLiteToExcel.tables = sQLiteToExcel.getTablesName(sQLiteToExcel.database)
                    }
                    val sQLiteToExcel2: SQLiteToExcel = this@SQLiteToExcel
                    val exportTables: String = sQLiteToExcel2.exportTables(
                        sQLiteToExcel2.tables,
                        this@SQLiteToExcel.fileName
                    )
                    if (exportListener != null) {
                        handler.post(object : Runnable {
                            // from class: com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.1.1
                            // java.lang.Runnable
                            override fun run() {
                                exportListener.onCompleted(exportTables)
                            }
                        })
                    }
                } catch (e: Exception) {
                    if (this@SQLiteToExcel.database != null && database!!.isOpen) {
                        database!!.close()
                    }
                    if (exportListener != null) {
                        handler.post { exportListener.onError(e) }
                    }
                }
            }
        }).start()
    }

    init {
        try {
            this.database = SQLiteDatabase.openOrCreateDatabase(
                (str4)!!, null as SQLiteDatabase.CursorFactory?
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Throws(Exception::class)
    fun exportTables(list: List<String>?, str: String?): String {
        if (str!!.lowercase(Locale.getDefault()).endsWith(".xls")) {
            this.workbook = HSSFWorkbook()
            if (TextUtils.isEmpty(this.sql)) {
                for (i in list!!.indices) {
                    val createSheet: Sheet = workbook!!.createSheet(
                        list[i]
                    )
                    fillSheet("select * from " + list.get(i), createSheet)
                    if (!TextUtils.isEmpty(this.protectKey)) {
                        createSheet.protectSheet(this.protectKey)
                    }
                }
            } else {
                val createSheet2: Sheet = workbook!!.createSheet(this.sheetName)
                fillSheet(this.sql, createSheet2)
                if (!TextUtils.isEmpty(this.protectKey)) {
                    createSheet2.protectSheet(this.protectKey)
                }
            }
            val file: File = File(this.filePath, str)
            val fileOutputStream: FileOutputStream = FileOutputStream(file)
            workbook!!.write(fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            workbook!!.close()
            database!!.close()
            if (!TextUtils.isEmpty(this.encryptKey)) {
                SecurityUtil.EncryptFile(file, this.encryptKey)
            }
            return file.path
        }
        throw IllegalArgumentException("File name is null or unsupported file format!")
    }

    private fun fillSheet(str: String?, sheet: Sheet) {
        var i: Int
        var row: Row
        val createDrawingPatriarch: Drawing<*> = sheet.createDrawingPatriarch()
        val rawQuery: Cursor = database!!.rawQuery(str, null)
        rawQuery.moveToFirst()
        val columnCount: Int = rawQuery.getColumnCount()
        val createRow: Row = sheet.createRow(0)
        for (i2 in 0 until columnCount) {
            createRow.createCell(i2)
                .setCellValue(HSSFRichTextString("" + rawQuery.getColumnNames().get(i2)))
        }
        var i3: Int = 1
        while (!rawQuery.isAfterLast()) {
            var createRow2: Row = sheet.createRow(i3)
            var i4: Int = 0
            while (i4 < columnCount) {
                val createCell: Cell = createRow2.createCell(i4)
                if (rawQuery.getType(i4) == Cursor.FIELD_TYPE_BLOB) {
                    row = createRow2
                    val hSSFClientAnchor: HSSFClientAnchor =
                        HSSFClientAnchor(0, 0, 0, 0, i4.toShort(), i3, (i4 + 1).toShort(), i3 + 1)
                    hSSFClientAnchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE)
                    i = i4
                    createDrawingPatriarch.createPicture(
                        hSSFClientAnchor,
                        workbook!!.addPicture(rawQuery.getBlob(i), 5)
                    )
                } else {
                    i = i4
                    row = createRow2
                    var string: String = rawQuery.getString(i)
                    if (!TextUtils.isEmpty(string) && string.length >= 32767) {
                        string = string.substring(0, 32766)
                    }
                    createCell.setCellValue(HSSFRichTextString(string))
                }
                i4 = i + 1
                createRow2 = row
            }
            i3++
            rawQuery.moveToNext()
        }
        rawQuery.close()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun getTablesName(sQLiteDatabase: SQLiteDatabase?): List<String> {
        val arrayList= ArrayList<String>()
        val rawQuery: Cursor = sQLiteDatabase!!.rawQuery(
            "select name from sqlite_master where type='table' order by name",
            null
        )
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery!!.getString(0))
        }
        rawQuery.close()
        return arrayList!!
    }

    companion object {
        private val handler: Handler = Handler(Looper.getMainLooper())
    }
}
