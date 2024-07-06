package com.kkkcut.e20j.customView.bubbleseekbar;

import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* loaded from: classes.dex */
class BubbleUtils {
    private static final String KEY_MIUI_MANE = "ro.miui.ui.version.name";
    private static Boolean miui;
    private static Properties sProperties = new Properties();

    BubbleUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMIUI() {
        Boolean bool = miui;
        Throwable th = null;
        Exception e = null;
        if (bool != null) {
            return bool.booleanValue();
        }
        FileInputStream fileInputStream = null;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                    try {
                        sProperties.load(fileInputStream2);
                        fileInputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                        return miui.booleanValue();
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
                miui = Boolean.valueOf(sProperties.containsKey(KEY_MIUI_MANE));
                return miui.booleanValue();
            }
            try {
                boolean z = true;
                if (TextUtils.isEmpty((String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, KEY_MIUI_MANE))) {
                    z = false;
                }
                miui = Boolean.valueOf(z);
            } catch (Exception unused) {
                miui = false;
            }
            return miui.booleanValue();
        } catch (Throwable th2) {
            th = th2;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, Resources.getSystem().getDisplayMetrics());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, i, Resources.getSystem().getDisplayMetrics());
    }
}
