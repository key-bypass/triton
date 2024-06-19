package com.kkkcut.e20j.utils.lan;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class MultiLanguageSpUtil {
    private static volatile MultiLanguageSpUtil instance;
    private final String SP_NAME = "language_setting";
    private final String TAG_LANGUAGE = "language_select";
    private final SharedPreferences mSharedPreferences;

    public MultiLanguageSpUtil(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("language_setting", 0);
    }

    public void saveLanguage(String str) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putString("language_select", str);
        edit.commit();
    }

    public String getSelectLanguage() {
        return this.mSharedPreferences.getString("language_select", "");
    }

    public static MultiLanguageSpUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (MultiLanguageSpUtil.class) {
                if (instance == null) {
                    instance = new MultiLanguageSpUtil(context);
                }
            }
        }
        return instance;
    }
}
