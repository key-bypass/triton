package com.kkkcut.e20j.utils.lan;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import java.util.Locale;

/* loaded from: classes.dex */
public class MultiLanguage {
    private static LanguageLocalListener languageLocalListener;

    public static void init(LanguageLocalListener languageLocalListener2) {
        languageLocalListener = languageLocalListener2;
    }

    public static Context setLocal(Context context) {
        return updateResources(context, getSetLanguageLocale(context));
    }

    public static void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale setLanguageLocale = getSetLanguageLocale(context);
        configuration.locale = setLanguageLocale;
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList localeList = new LocaleList(setLanguageLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(configuration);
            Locale.setDefault(setLanguageLocale);
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void onConfigurationChanged(Context context) {
        setLocal(context);
        setApplicationLanguage(context);
    }

    private static Locale getSetLanguageLocale(Context context) {
        LanguageLocalListener languageLocalListener2 = languageLocalListener;
        if (languageLocalListener2 != null) {
            return languageLocalListener2.getSetLanguageLocale(context);
        }
        return getSystemLocal();
    }

    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = new Configuration(resources.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            configuration.setLocale(locale);
            return context.createConfigurationContext(configuration);
        }
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    public static Locale getSystemLocal(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }

    public static Locale getSystemLocal() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleList.getDefault().get(0);
        }
        return Locale.getDefault();
    }
}
