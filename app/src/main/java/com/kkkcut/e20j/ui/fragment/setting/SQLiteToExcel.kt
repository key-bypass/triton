package com.kkkcut.e20j.ui.fragment.setting;

import static org.apache.poi.ss.usermodel.ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.kkkcut.e20j.utils.SecurityUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/* loaded from: classes.dex */
public class SQLiteToExcel {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private SQLiteDatabase database;
    private String encryptKey;
    private String fileName;
    private String filePath;
    private String protectKey;
    private String sheetName;
    private String sql;
    private List<String> tables;
    private Workbook workbook;

    /* loaded from: classes.dex */
    public interface ExportListener {
        void onCompleted(String str);

        void onError(Exception exc);

        void onStart();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private String dataBaseName;
        private String encryptKey;
        private String fileName;
        private String filePath;
        private String protectKey;
        private String sheetName;
        private String sql;
        private List<String> tables;

        public Builder(Context context) {
            this.filePath = context.getExternalFilesDir(null).getPath();
        }

        public SQLiteToExcel build() {
            if (TextUtils.isEmpty(this.dataBaseName)) {
                throw new IllegalArgumentException("Database name must not be null.");
            }
            if (TextUtils.isEmpty(this.fileName)) {
                throw new IllegalArgumentException("Output file name must not be null.");
            }
            return new SQLiteToExcel(this.tables, this.protectKey, this.encryptKey, this.fileName, this.dataBaseName, this.filePath, this.sql, this.sheetName);
        }

        public Builder setDataBase(String str) {
            this.dataBaseName = str;
            this.fileName = new File(str).getName() + ".xls";
            return this;
        }

        @Deprecated
        public Builder setFileName(String str) {
            return setOutputFileName(str);
        }

        public Builder setOutputFileName(String str) {
            this.fileName = str;
            return this;
        }

        public Builder setProtectKey(String str) {
            this.protectKey = str;
            return this;
        }

        public Builder setEncryptKey(String str) {
            this.encryptKey = str;
            return this;
        }

        public Builder setTables(String... strArr) {
            this.tables = Arrays.asList(strArr);
            return this;
        }

        @Deprecated
        public Builder setPath(String str) {
            return setOutputPath(str);
        }

        public Builder setOutputPath(String str) {
            this.filePath = str;
            return this;
        }

        public Builder setSQL(String str, String str2) {
            this.sql = str2;
            this.sheetName = str;
            return this;
        }

        public Builder setSQL(String str) {
            return setSQL("Sheet1", str);
        }

        public String start() {
            return build().start();
        }

        public void start(ExportListener exportListener) {
            build().start(exportListener);
        }
    }

    public String start() {
        try {
            List<String> list = this.tables;
            if (list == null || list.size() == 0) {
                this.tables = getTablesName(this.database);
            }
            return exportTables(this.tables, this.fileName);
        } catch (Exception unused) {
            SQLiteDatabase sQLiteDatabase = this.database;
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return null;
            }
            this.database.close();
            return null;
        }
    }

    public void start(final ExportListener exportListener) {
        if (exportListener != null) {
            exportListener.onStart();
        }
        new Thread(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (SQLiteToExcel.this.tables == null || SQLiteToExcel.this.tables.size() == 0) {
                        SQLiteToExcel sQLiteToExcel = SQLiteToExcel.this;
                        sQLiteToExcel.tables = sQLiteToExcel.getTablesName(sQLiteToExcel.database);
                    }
                    SQLiteToExcel sQLiteToExcel2 = SQLiteToExcel.this;
                    final String exportTables = sQLiteToExcel2.exportTables(sQLiteToExcel2.tables, SQLiteToExcel.this.fileName);
                    if (exportListener != null) {
                        SQLiteToExcel.handler.post(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                exportListener.onCompleted(exportTables);
                            }
                        });
                    }
                } catch (Exception e) {
                    if (SQLiteToExcel.this.database != null && SQLiteToExcel.this.database.isOpen()) {
                        SQLiteToExcel.this.database.close();
                    }
                    if (exportListener != null) {
                        SQLiteToExcel.handler.post(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.SQLiteToExcel.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                exportListener.onError(e);
                            }
                        });
                    }
                }
            }
        }).start();
    }

    private SQLiteToExcel(List<String> list, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.protectKey = str;
        this.encryptKey = str2;
        this.fileName = str3;
        this.tables = list;
        this.filePath = str5;
        this.sql = str6;
        this.sheetName = str7;
        try {
            this.database = SQLiteDatabase.openOrCreateDatabase(str4, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String exportTables(List<String> list, String str) throws Exception {
        if (str.toLowerCase().endsWith(".xls")) {
            this.workbook = new HSSFWorkbook();
            if (TextUtils.isEmpty(this.sql)) {
                for (int i = 0; i < list.size(); i++) {
                    Sheet createSheet = this.workbook.createSheet(list.get(i));
                    fillSheet("select * from " + list.get(i), createSheet);
                    if (!TextUtils.isEmpty(this.protectKey)) {
                        createSheet.protectSheet(this.protectKey);
                    }
                }
            } else {
                Sheet createSheet2 = this.workbook.createSheet(this.sheetName);
                fillSheet(this.sql, createSheet2);
                if (!TextUtils.isEmpty(this.protectKey)) {
                    createSheet2.protectSheet(this.protectKey);
                }
            }
            File file = new File(this.filePath, str);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            this.workbook.close();
            this.database.close();
            if (!TextUtils.isEmpty(this.encryptKey)) {
                SecurityUtil.EncryptFile(file, this.encryptKey);
            }
            return file.getPath();
        }
        throw new IllegalArgumentException("File name is null or unsupported file format!");
    }

    private void fillSheet(String str, Sheet sheet) {
        int i;
        Row row;
        Drawing<?> createDrawingPatriarch = sheet.createDrawingPatriarch();
        Cursor rawQuery = this.database.rawQuery(str, null);
        rawQuery.moveToFirst();
        int columnCount = rawQuery.getColumnCount();
        Row createRow = sheet.createRow(0);
        for (int i2 = 0; i2 < columnCount; i2++) {
            createRow.createCell(i2).setCellValue(new HSSFRichTextString("" + rawQuery.getColumnNames()[i2]));
        }
        int i3 = 1;
        while (!rawQuery.isAfterLast()) {
            Row createRow2 = sheet.createRow(i3);
            int i4 = 0;
            while (i4 < columnCount) {
                Cell createCell = createRow2.createCell(i4);
                if (rawQuery.getType(i4) == Cursor.FIELD_TYPE_BLOB) {
                    row = createRow2;
                    HSSFClientAnchor hSSFClientAnchor = new HSSFClientAnchor(0, 0, 0, 0, (short) i4, i3, (short) (i4 + 1), i3 + 1);
                    hSSFClientAnchor.setAnchorType(DONT_MOVE_AND_RESIZE);
                    i = i4;
                    createDrawingPatriarch.createPicture(hSSFClientAnchor, this.workbook.addPicture(rawQuery.getBlob(i), 5));
                } else {
                    i = i4;
                    row = createRow2;
                    String string = rawQuery.getString(i);
                    if (!TextUtils.isEmpty(string) && string.length() >= 32767) {
                        string = string.substring(0, 32766);
                    }
                    createCell.setCellValue(new HSSFRichTextString(string));
                }
                i4 = i + 1;
                createRow2 = row;
            }
            i3++;
            rawQuery.moveToNext();
        }
        rawQuery.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getTablesName(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(0));
        }
        rawQuery.close();
        return arrayList;
    }
}
