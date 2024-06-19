package com.kkkcut.e20j.dao;

import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.MyApplication;
import java.io.File;

/* loaded from: classes.dex */
public class GreenDaoContext extends ContextWrapper {
    public GreenDaoContext() {
        super(MyApplication.getInstance());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDatabasePath(String str) {
        File file = new File(Constant.CODE_DATABASE_PATH, str);
        return !file.exists() ? super.getDatabasePath(str) : file;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), cursorFactory);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String str, int i, SQLiteDatabase.CursorFactory cursorFactory, DatabaseErrorHandler databaseErrorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(str), cursorFactory);
    }
}
