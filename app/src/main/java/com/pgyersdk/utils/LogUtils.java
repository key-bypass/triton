package com.pgyersdk.utils;

import android.util.Log;

/* compiled from: LogUtils.java */
/* renamed from: com.pgyersdk.f.f */
/* loaded from: classes2.dex */
public class LogUtils {

    /* renamed from: a */
    public static boolean f532a = true;

    /* renamed from: a */
    public static void m216a(String str, String str2) {
        if (!f532a || str == null || str2 == null) {
            return;
        }
        Log.d(str, str2);
    }

    /* renamed from: b */
    public static void m218b(String str, String str2) {
        if (!f532a || str == null || str2 == null) {
            return;
        }
        Log.e(str, str2);
    }

    /* renamed from: c */
    public static void m219c(String str, String str2) {
        if (!f532a || str == null || str2 == null) {
            return;
        }
        Log.i(str, str2);
    }

    /* renamed from: d */
    public static void m220d(String str, String str2) {
        if (!f532a || str == null || str2 == null) {
            return;
        }
        Log.w(str, str2);
    }

    /* renamed from: a */
    public static void m217a(String str, String str2, Throwable th) {
        if (!f532a || str == null || str2 == null) {
            return;
        }
        Log.e(str, str2, th);
    }
}
