package com.kkkcut.e20j.dao;

import com.kkkcut.e20j.utils.DatabaseFileUtils;
import java.util.HashMap;


/* loaded from: classes.dex */
public class LanguageDaoManager {
    public static String DB_NAME = "LanguageFile.db";
    private static LanguageDaoManager mInstanceHolder;

    private LanguageDaoManager() {
    }

    public static LanguageDaoManager getInstance() {
        if (mInstanceHolder == null) {
            synchronized (LanguageDaoManager.class) {
                if (mInstanceHolder == null) {
                    mInstanceHolder = new LanguageDaoManager();
                }
            }
        }
        return mInstanceHolder;
    }

    public HashMap<String, String> queryLanguageTable(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (!str.equals("0") && !str.isEmpty()) {
            String str2 = "select * from " + str;
            SQLiteDatabase dataBase = DatabaseFileUtils.getDataBase(DB_NAME);
            if (dataBase != null) {
                Cursor rawQuery = dataBase.rawQuery(str2, (String[]) null);
                while (rawQuery.moveToNext()) {
                    hashMap.put(rawQuery.getString(rawQuery.getColumnIndexOrThrow("TableKey")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("TableValue")));
                }
                rawQuery.close();
                dataBase.close();
            }
        }
        return hashMap;
    }
}
