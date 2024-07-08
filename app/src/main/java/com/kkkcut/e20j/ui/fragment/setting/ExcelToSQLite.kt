package com.kkkcut.e20j.ui.fragment.setting

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity
import com.liulishuo.filedownloader.model.FileDownloadModel
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import java.io.FileInputStream
import java.math.BigInteger
import java.util.Locale
import java.util.regex.Pattern

/* loaded from: classes.dex */
class ExcelToSQLite private constructor(
    private val mContext: Context?,
    private val databasePath: String?,
    private val excelPath: String?,
    private val decryptKey: String?
) {
    private var database: SQLiteDatabase? = null

    /* loaded from: classes.dex */
    interface ImportListener {
        fun onCompleted(str: String?)

        fun onError(exc: Exception)

        fun onStart()
    }

    /* loaded from: classes.dex */
    class Builder(private val context: Context?) {
        private var dataBasePath: String? = null
        private var decryptKey: String? = null
        private var excelPath: String? = null

        fun build(): ExcelToSQLite {
            if (TextUtils.isEmpty(this.dataBasePath)) {
                throw IllegalArgumentException("Database name must not be null.")
            }
            return ExcelToSQLite(this.context, this.dataBasePath, this.excelPath, this.decryptKey)
        }

        fun setDataBasePath(str: String?): Builder {
            this.dataBasePath = str
            return this
        }

        fun setExcelPath(str: String?): Builder {
            this.excelPath = str
            return this
        }

        fun setDecryptKey(str: String?): Builder {
            this.decryptKey = str
            return this
        }

        fun start() {
            build().start()
        }

        fun start(importListener: ImportListener?) {
            build().start(importListener)
        }
    }

    init {
        try {
            this.database = SQLiteDatabase.openOrCreateDatabase(
                (databasePath)!!, null as SQLiteDatabase.CursorFactory?
            )
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun start(): Boolean {
        if (TextUtils.isEmpty(this.excelPath)) {
            throw IllegalArgumentException("Asset file or external file name must not be null.")
        }
        try {
            return importTables(this.excelPath)
        } catch (unused: Exception) {
            val sQLiteDatabase: SQLiteDatabase? = this.database
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return false
            }
            database!!.close()
            return false
        }
    }

    fun start(importListener: ImportListener?) {
        if (TextUtils.isEmpty(this.excelPath)) {
            throw IllegalArgumentException("Asset file or external file name must not be null.")
        }
        importListener?.onStart()
        Thread {
            try {
                val excelToSQLite: ExcelToSQLite = this@ExcelToSQLite
                excelToSQLite.importTables(excelToSQLite.excelPath)
                if (importListener != null) {
                    handler.post { importListener.onCompleted(this@ExcelToSQLite.databasePath) }
                }
            } catch (e: Exception) {
                if (this@ExcelToSQLite.database != null && database!!.isOpen) {
                    database!!.close()
                }
                if (importListener != null) {
                    handler.post { importListener.onError(e) }
                }
            }
        }.start()
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Throws(Exception::class)
    fun importTables(str: String?): Boolean {
        val fileInputStream: FileInputStream = FileInputStream(str)
        if (str!!.lowercase(Locale.getDefault()).endsWith(".xls")) {
            if (!TextUtils.isEmpty(this.decryptKey)) {
                Biff8EncryptionKey.setCurrentUserPassword("1234567")
            }
            val hSSFWorkbook: HSSFWorkbook = HSSFWorkbook(fileInputStream)
            fileInputStream.close()
            val numberOfSheets: Int = hSSFWorkbook.getNumberOfSheets()
            for (i in 0 until numberOfSheets) {
                createTable(hSSFWorkbook.getSheetAt(i))
            }
            database!!.close()
            return true
        }
        fileInputStream.close()
        throw UnsupportedOperationException("Unsupported file format!")
    }

    private fun createTable(sheet: Sheet) {
        var asLong: Long
        var rawQuery: Cursor
        val sheetName: String = sheet.sheetName
        val rowIterator: Iterator<Row> = sheet.rowIterator()
        val next: Row = rowIterator.next()
        val arrayList = ArrayList<String>()
        for (i in 0 until next.physicalNumberOfCells) {
            next.getCell(i).stringCellValue
            next.physicalNumberOfCells
            arrayList.add(next.getCell(i).getStringCellValue())
        }
        while (rowIterator.hasNext()) {
            val next2: Row = rowIterator.next()
            val contentValues: ContentValues = ContentValues()
            for (i2 in 0 until next2.getPhysicalNumberOfCells()) {
                if ((next2.getCell(i2) != null) && !(arrayList.get(i2) as String).equals(
                        FileDownloadModel.ID,
                        ignoreCase = true
                    ) && !(arrayList.get(i2) as String).equals(
                        BarCodeRemindActivity.ID,
                        ignoreCase = true
                    )
                ) {
                    if (next2.getCell(i2).getCellTypeEnum() == CellType.NUMERIC) {
                        contentValues.put(
                            arrayList.get(i2) as String?,
                            getRealStringValueOfDouble(next2.getCell(i2).getNumericCellValue())
                        )
                    } else if (next2.getCell(i2).getCellTypeEnum() == CellType.STRING) {
                        contentValues.put(
                            arrayList.get(i2) as String?,
                            next2.getCell(i2).getStringCellValue()
                        )
                    }
                }
            }
            if (contentValues.size() != 0) {
                if (!contentValues.containsKey("TIME")) {
                    asLong = 0L
                    contentValues.put("TIME", 0L as Long?)
                } else {
                    asLong = contentValues.getAsLong("TIME")
                }
                val asString: String = contentValues.getAsString("TITLE")
                val asString2: String = contentValues.getAsString("keyname")
                if (!TextUtils.isEmpty(asString)) {
                    rawQuery = database!!.rawQuery(
                        "select * from " + sheetName + " where  TIME=? and TITLE=?",
                        arrayOf(asLong.toString(), asString)
                    )
                } else if (!TextUtils.isEmpty(asString2)) {
                    rawQuery = database!!.rawQuery(
                        "select * from " + sheetName + " where  TIME=? and keyname=?",
                        arrayOf(asLong.toString(), asString2)
                    )
                } else {
                    database!!.insert(sheetName, null, contentValues)
                    return
                }
                if (!rawQuery.moveToNext()) {
                    database!!.insert(sheetName, null, contentValues)
                }
                rawQuery.close()
            }
        }
    }

    companion object {
        private val handler: Handler = Handler(Looper.getMainLooper())
        private fun getRealStringValueOfDouble(d: Double): String {
            val d2: String = d.toString()
            val contains: Boolean = d2.contains("E")
            val indexOf: Int = d2.indexOf(46.toChar())
            if (!contains) {
                return if (Pattern.compile(".0$").matcher(d2).find()) d2.replace(".0", "") else d2
            }
            val indexOf2: Int = d2.indexOf(69.toChar())
            var length: Int = BigInteger(
                d2.substring(
                    indexOf + BigInteger.ONE.toInt(),
                    indexOf2
                )
            ).toByteArray().size - d2.substring(indexOf2 + BigInteger.ONE.toInt()).toInt()
            if (length <= 0) {
                length = 0
            }
            return String.format("%." + length + "f", d)
        }
    }
}
