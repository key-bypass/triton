package com.kkkcut.e20j.ui.fragment.setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/* loaded from: classes.dex */
public class ExcelToSQLite {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private SQLiteDatabase database;
    private String databasePath;
    private String decryptKey;
    private String excelPath;
    private Context mContext;

    /* loaded from: classes.dex */
    public interface ImportListener {
        void onCompleted(String str);

        void onError(Exception exc);

        void onStart();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Context context;
        private String dataBasePath;
        private String decryptKey;
        private String excelPath;

        public Builder(Context context) {
            this.context = context;
        }

        public ExcelToSQLite build() {
            if (TextUtils.isEmpty(this.dataBasePath)) {
                throw new IllegalArgumentException("Database name must not be null.");
            }
            return new ExcelToSQLite(this.context, this.dataBasePath, this.excelPath, this.decryptKey);
        }

        public Builder setDataBasePath(String str) {
            this.dataBasePath = str;
            return this;
        }

        public Builder setExcelPath(String str) {
            this.excelPath = str;
            return this;
        }

        public Builder setDecryptKey(String str) {
            this.decryptKey = str;
            return this;
        }

        public void start() {
            build().start();
        }

        public void start(ImportListener importListener) {
            build().start(importListener);
        }
    }

    private ExcelToSQLite(Context context, String str, String str2, String str3) {
        this.mContext = context;
        this.excelPath = str2;
        this.decryptKey = str3;
        this.databasePath = str;
        try {
            this.database = SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean start() {
        if (TextUtils.isEmpty(this.excelPath)) {
            throw new IllegalArgumentException("Asset file or external file name must not be null.");
        }
        try {
            return importTables(this.excelPath);
        } catch (Exception unused) {
            SQLiteDatabase sQLiteDatabase = this.database;
            if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
                return false;
            }
            this.database.close();
            return false;
        }
    }

    public void start(final ImportListener importListener) {
        if (TextUtils.isEmpty(this.excelPath)) {
            throw new IllegalArgumentException("Asset file or external file name must not be null.");
        }
        if (importListener != null) {
            importListener.onStart();
        }
        new Thread(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ExcelToSQLite excelToSQLite = ExcelToSQLite.this;
                    excelToSQLite.importTables(excelToSQLite.excelPath);
                    if (importListener != null) {
                        ExcelToSQLite.handler.post(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                importListener.onCompleted(ExcelToSQLite.this.databasePath);
                            }
                        });
                    }
                } catch (Exception e) {
                    if (ExcelToSQLite.this.database != null && ExcelToSQLite.this.database.isOpen()) {
                        ExcelToSQLite.this.database.close();
                    }
                    if (importListener != null) {
                        ExcelToSQLite.handler.post(new Runnable() { // from class: com.kkkcut.e20j.ui.fragment.setting.ExcelToSQLite.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                importListener.onError(e);
                            }
                        });
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean importTables(String str) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(str);
        if (str.toLowerCase().endsWith(".xls")) {
            if (!TextUtils.isEmpty(this.decryptKey)) {
                Biff8EncryptionKey.setCurrentUserPassword("1234567");
            }
            HSSFWorkbook hSSFWorkbook = new HSSFWorkbook(fileInputStream);
            fileInputStream.close();
            int numberOfSheets = hSSFWorkbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                createTable(hSSFWorkbook.getSheetAt(i));
            }
            this.database.close();
            return true;
        }
        fileInputStream.close();
        throw new UnsupportedOperationException("Unsupported file format!");
    }

    private void createTable(Sheet sheet) {
        long asLong;
        Cursor rawQuery;
        String sheetName = sheet.getSheetName();
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row next = rowIterator.next();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < next.getPhysicalNumberOfCells(); i++) {
            next.getCell(i).getStringCellValue();
            next.getPhysicalNumberOfCells();
            arrayList.add(next.getCell(i).getStringCellValue());
        }
        while (rowIterator.hasNext()) {
            Row next2 = rowIterator.next();
            ContentValues contentValues = new ContentValues();
            for (int i2 = 0; i2 < next2.getPhysicalNumberOfCells(); i2++) {
                if (next2.getCell(i2) != null && !((String) arrayList.get(i2)).equalsIgnoreCase(FileDownloadModel.ID) && !((String) arrayList.get(i2)).equalsIgnoreCase(BarCodeRemindActivity.ID)) {
                    if (next2.getCell(i2).getCellTypeEnum() == CellType.NUMERIC) {
                        contentValues.put((String) arrayList.get(i2), getRealStringValueOfDouble(Double.valueOf(next2.getCell(i2).getNumericCellValue())));
                    } else if (next2.getCell(i2).getCellTypeEnum() == CellType.STRING) {
                        contentValues.put((String) arrayList.get(i2), next2.getCell(i2).getStringCellValue());
                    }
                }
            }
            if (contentValues.size() != 0) {
                if (!contentValues.containsKey("TIME")) {
                    asLong = 0L;
                    contentValues.put("TIME", (Long) 0L);
                } else {
                    asLong = contentValues.getAsLong("TIME");
                }
                String asString = contentValues.getAsString("TITLE");
                String asString2 = contentValues.getAsString("keyname");
                if (!TextUtils.isEmpty(asString)) {
                    rawQuery = this.database.rawQuery("select * from " + sheetName + " where  TIME=? and TITLE=?", new String[]{String.valueOf(asLong), asString});
                } else if (!TextUtils.isEmpty(asString2)) {
                    rawQuery = this.database.rawQuery("select * from " + sheetName + " where  TIME=? and keyname=?", new String[]{String.valueOf(asLong), asString2});
                } else {
                    this.database.insert(sheetName, null, contentValues);
                    return;
                }
                if (!rawQuery.moveToNext()) {
                    this.database.insert(sheetName, null, contentValues);
                }
                rawQuery.close();
            }
        }
    }

    private static String getRealStringValueOfDouble(Double d) {
        String d2 = d.toString();
        boolean contains = d2.contains("E");
        int indexOf = d2.indexOf(46);
        if (!contains) {
            return Pattern.compile(".0$").matcher(d2).find() ? d2.replace(".0", "") : d2;
        }
        int indexOf2 = d2.indexOf(69);
        int length = new BigInteger(d2.substring(indexOf + BigInteger.ONE.intValue(), indexOf2)).toByteArray().length - Integer.valueOf(d2.substring(indexOf2 + BigInteger.ONE.intValue())).intValue();
        if (length <= 0) {
            length = 0;
        }
        return String.format("%." + length + "f", d);
    }
}
