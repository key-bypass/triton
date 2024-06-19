package com.kkkcut.e20j.utils.lan;

import android.content.Context;
import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public class LocalManageUtil {
    private static final String TAG = "LocalManageUtil";

    public static Locale getSetLanguageLocale(Context context) {
        String selectLanguage = getSelectLanguage(context);
        if (TextUtils.isEmpty(selectLanguage)) {
            return MultiLanguage.getSystemLocal();
        }
        return SupportLanguageUtil.getSupportLanguage(selectLanguage);
    }

    public static String getSelectLanguage(Context context) {
        return MultiLanguageSpUtil.getInstance(context).getSelectLanguage();
    }

    public static void saveSelectLanguage(Context context, String str) {
        MultiLanguageSpUtil.getInstance(context).saveLanguage(str);
        MultiLanguage.setApplicationLanguage(context);
    }
}
