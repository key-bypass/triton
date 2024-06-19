package com.kkkcut.e20j.utils.lan;

import android.os.Build;
import android.os.LocaleList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class SupportLanguageUtil {
    private static Map<String, Locale> mSupportLanguages = new HashMap<String, Locale>(7) { // from class: com.kkkcut.e20j.utils.lan.SupportLanguageUtil.1
        {
            put("en", Locale.ENGLISH);
            put("zh", Locale.SIMPLIFIED_CHINESE);
            put("cs", new Locale("cs"));
            put("fr", Locale.FRANCE);
            put("de", Locale.GERMANY);
            put("it", Locale.ITALY);
            put("es", new Locale("es"));
            put("ko", Locale.KOREAN);
            put("pt", new Locale("pt"));
            put("ru", new Locale("ru"));
            put("tr", new Locale("tr"));
            put("pl", new Locale("pl"));
            put("hb", new Locale("ar", "IL"));
            put("vi", new Locale("vi"));
            put("fa", new Locale("fa"));
            put(LanguageConstants.THAI, new Locale(LanguageConstants.THAI));
        }
    };

    public static boolean isSupportLanguage(String str) {
        return mSupportLanguages.containsKey(str);
    }

    public static Locale getSupportLanguage(String str) {
        if (isSupportLanguage(str)) {
            return mSupportLanguages.get(str);
        }
        return getSystemLanguage();
    }

    public static Locale getSystemLanguage() {
        if (Build.VERSION.SDK_INT >= 24) {
            return LocaleList.getDefault().get(0);
        }
        return Locale.getDefault();
    }
}
