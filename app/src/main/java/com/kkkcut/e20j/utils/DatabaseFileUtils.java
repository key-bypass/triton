package com.kkkcut.e20j.utils;

import android.util.Log;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.MyApplication;

import net.zetetic.database.sqlcipher.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class DatabaseFileUtils {
    public static final String KEY = "sece9eandroid9espl9e9e";
    private static final String TAG = "DatabaseFileUtils";

    public static void addDatabases() {
        try {
            for (String str : MyApplication.getInstance().getAssets().list("database")) {
                addDatabaseFile(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearDatabases() {
        File[] listFiles;
        File file = new File("/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/");
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                Log.i(TAG, "clearDatabases: " + file2.getAbsolutePath());
                file2.delete();
            }
        }
    }

    public static boolean dataExist(String str) {
        File file = new File(Constant.CODE_DATABASE_PATH, str);
        if (!file.exists()) {
            return false;
        }
        if (file.length() != 0) {
            return true;
        }
        file.delete();
        return false;
    }

    public static void addDatabaseFile(String str) {
        try {
            String str2 = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/" + str;
            String str3 = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/";
            File file = new File(str2);
            Log.d(TAG, "file: " + file.getAbsolutePath());
            File file2 = new File(str3);
            if (!file2.exists()) {
                file2.mkdir();
            }
            if (file.exists() && file.length() > 0) {
                return;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(MyApplication.getInstance().getAssets().open("database/" + str));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SQLiteDatabase getDataBase(String str) {
        String str2 = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/" + str;
        if (new File(str2).exists()) {
            return SQLiteDatabase.openDatabase(str2, null,   SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        return null;
    }

    public static SQLiteDatabase getDatabaseByPassword(String str) {
        String str2 = "/data/data/" + MyApplication.getInstance().getPackageName() + "/databases/" + str;
        if (new File(str2).exists()) {
            return SQLiteDatabase.openDatabase(str2, null,   SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        return null;
    }
}
