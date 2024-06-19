package com.kkkcut.e20j;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDexApplication;

import com.kkkcut.e20j.utils.lan.LanguageLocalListener;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;
import com.kkkcut.e20j.utils.lan.MultiLanguage;
import java.util.Locale;

/* loaded from: classes.dex */
public class MultiLanguagesApp extends MultiDexApplication {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguage.setLocal(context));
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MultiLanguage.onConfigurationChanged(getApplicationContext());
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        MultiLanguage.init(new LanguageLocalListener() { // from class: com.kkkcut.e20j.MultiLanguagesApp.1
            @Override // com.kkkcut.e20j.utils.lan.LanguageLocalListener
            public Locale getSetLanguageLocale(Context context) {
                return LocalManageUtil.getSetLanguageLocale(context);
            }
        });
        MultiLanguage.setApplicationLanguage(this);
    }
}
