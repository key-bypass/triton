package com.pgyersdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.pgyersdk.PgyerProvider;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/* compiled from: Utils.java */
/* renamed from: com.pgyersdk.f.m */
/* loaded from: classes2.dex */
public class Utils {

    /* renamed from: a */
    private static String f541a = "Utils";

    /* renamed from: b */
    private static Utils f542b;

    /* renamed from: a */
    public static Utils m245a() {
        if (f542b == null) {
            f542b = new Utils();
        }
        return f542b;
    }

    /* renamed from: b */
    public static boolean m249b() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) PgyerProvider.f436a.getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        LogUtils.m220d("PgyerSDK", "Please check your device net can work.");
        return false;
    }

    /* renamed from: a */
    public String m250a(long j) {
        float f;
        String str;
        if (j >= 1000) {
            f = (float) (j / 1000);
            if (f >= 1000.0f) {
                f /= 1000.0f;
                str = "MB";
            } else {
                str = "KB";
            }
            if (f >= 1000.0f) {
                f /= 1000.0f;
                str = "GB";
            }
        } else {
            f = (float) j;
            str = "B";
        }
        return new DecimalFormat("#0.0").format(f) + str;
    }

    /* renamed from: a */
    public boolean m252a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Exception e) {
            m245a().m251a(f541a, e);
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m248a(String str) {
        return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$").matcher(str).matches();
    }

    /* renamed from: a */
    public void m251a(String str, Exception exc) {
        LogUtils.m218b(str, Log.getStackTraceString(exc));
    }

    /* renamed from: a */
    public static void m247a(StringBuffer stringBuffer, String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
            stringBuffer.append(readLine);
            stringBuffer.append("\n");
        }
        bufferedReader.close();
        fileInputStream.close();
    }

    /* renamed from: a */
    public static StringBuffer m246a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer();
    }
}
