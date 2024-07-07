package com.pgyersdk.p012c;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.pgyersdk.utils.BinTools;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.Util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: Constants.java */
/* renamed from: com.pgyersdk.c.a */
/* loaded from: classes2.dex */
public class Constants {

    /* renamed from: a */
    public static String f463a = "";

    /* renamed from: b */
    public static String f464b = "";

    /* renamed from: c */
    public static String f465c = "";

    /* renamed from: d */
    public static String f466d = "";

    /* renamed from: e */
    public static String f467e = "";

    /* renamed from: f */
    public static String f468f = "";

    /* renamed from: g */
    public static String f469g = "";

    /* renamed from: h */
    public static String f470h = "";

    /* renamed from: i */
    public static String f471i = "";

    /* renamed from: j */
    public static String f472j = "";

    /* renamed from: k */
    public static String f473k = "3.0.10";

    /* renamed from: l */
    public static String f474l = "";

    /* renamed from: m */
    public static String f475m = "PGYER_APPID";

    /* renamed from: n */
    public static String f476n = "getting failed";

    /* renamed from: o */
    public static String f477o = "iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAMAAAANIilAAAAAkFBMVEUAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////+WABnwAAAAL3RSTlMAMtw091D6Sy7fTTbjpioQBe2vJ8G5tY16ZlLy6MeCDc+bkoZ7c1NAOyGeg1w6Gpvp7nkAAAFwSURBVEjH7dbHcoMwFIXhA0ggMLiAe4+70877v10wDtHCmHCXmfCv2HwjXUaDQFvbX+kjdR/abppZL2ZFPeRtsl9sZ8Cp85DpAwgWTOrxmFNUF5ArjmpxyC4qi+gbvHBcjw2qmlN5QK5H/efYpYOKNOP7jl6ZSHA5L+6teJXhgIOEw2K/ZqB6IqypupgU2lMMIMGn4l3dNDI/txKsSa942HG4YAQJDkoLJKSGBEf0S2tCzgH0e5uGeE5VWs+/79ksts2wpt8pLRl8Pywb4cjO6/3M69BtggPadRVPkGBN5VmrIcHWonM7GxJsuDR23ggiPGJi19UQ4atPvpXzziHDO66X3NuzIcF9FSNTXKNLBhDiGY9AFjNR1JDisPjMr8ncSvGZe3weQ/qpAzEe8X1CqkkGiLHDPHd227kcz8j0DECA7Y3Rmxk8zTCU3lW2LkPpLWmbclxzPx+cug4cdOr+DOqLPTzvkrp1pRe0tf2LvgDo0C+Y+R4NZwAAAABJRU5ErkJggg==";

    /* renamed from: p */
    private static int f478p = -1;

    /* renamed from: a */
    public static boolean m143a() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        for (int i = 0; i < 5; i++) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(strArr[i]);
                sb.append("su");
                if (new File(sb.toString()).exists()) {
                    f478p = 1;
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        f478p = 0;
        return false;
    }

    /* renamed from: b */
    public static void m144b(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            sb.append("x");
            sb.append(displayMetrics.heightPixels);
            f472j = sb.toString();
        } catch (Exception unused) {
            f472j = "resolution getting faild";
        }
    }

    /* renamed from: c */
    public static void m145c(Context context) {
        f467e = Build.VERSION.RELEASE;
        f468f = Build.MODEL;
        f469g = Build.MANUFACTURER;
        m149g(context);
        m150h(context);
        m147e(context);
        m148f(context);
    }

    /* renamed from: d */
    private static String m146d(Context context) {
        String str;
        String str2 = "HA" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.PRODUCT.length() % 10);
        if (Build.VERSION.SDK_INT >= 9) {

            try {
                str = Build.class.getField("SERIAL").get(null).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            return str2 + ":" + str;
        }
        str = "";
        return str2 + ":" + str;
    }

    /* renamed from: e */
    private static void m147e(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (f465c == null || android_id == null) {
            return;
        }
        String str = f465c + ":" + android_id + ":" + m146d(context);

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_1);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        messageDigest.update(bytes, 0, bytes.length);
        f470h = m141a(messageDigest.digest());

    }

    /* renamed from: f */
    private static void m148f(Context context) {
        if (context != null) {
            try {
                m142a(context);
                m144b(context);
            } catch (Exception e) {
                LogUtils.m218b("PgyerSDK", "Exception thrown then accessing the device info:");
                e.printStackTrace();
            }
        }
    }

    /* renamed from: g */
    private static void m149g(Context context) {
        if (context != null) {
            try {
                File filesDir = context.getFilesDir();
                if (filesDir != null) {
                    f463a = filesDir.getAbsolutePath();
                }
            } catch (Exception e) {
                LogUtils.m218b("PgyerSDK", "Exception thrown when accessing the files dir:");
                e.printStackTrace();
            }
        }
    }

    /* renamed from: h */
    private static void m150h(Context context) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                f465c = packageInfo.packageName;
                f464b = "" + packageInfo.versionCode;
                f466d = packageInfo.versionName;
                int m140a = m140a(context, packageManager);
                if (m140a == 0 || m140a <= packageInfo.versionCode) {
                    return;
                }
                f464b = "" + m140a;
            } catch (Exception e) {
                LogUtils.m218b("PgyerSDK", "Exception thrown when accessing the package info:");
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0088  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m142a(Context context) {
        String str;
        String str2;
        String str3 = "";
        Exception e = null;
        if (!Util.m241c()) {
            f471i = f476n;
            return;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            str2 = "" + telephonyManager.getDeviceId();
            try {
                str = "" + telephonyManager.getSimSerialNumber();
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(Settings.Secure.getString(context.getContentResolver(), "android_id"));
                    str3 = sb.toString();
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    f471i = f476n;
                    str3 = "";
                    if (!str2.equals("")) {
                    }
                }
            } catch (Exception e2) {
                e = e2;
                str = "";
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
            str2 = str;
        }
        if (!str2.equals("")) {
            f471i = new UUID(0L, str.hashCode() | (str2.hashCode() << 32)).toString();
        } else {
            f471i = new UUID(str3.hashCode(), (str2.hashCode() << 32) | str.hashCode()).toString();
        }
    }

    private static int m140a(Context context, PackageManager packageManager) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getInt("buildNumber", 0);
            }
        } catch (Exception e) {
            LogUtils.m218b("PgyerSDK", "Exception thrown when accessing the application info:");
            e.printStackTrace();
        }
        return 0;
    }

    /* renamed from: a */
    private static String m141a(byte[] bArr) {
        char[] charArray = BinTools.hex.toCharArray();
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        return new String(cArr).replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
    }
}
