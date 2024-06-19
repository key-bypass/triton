package com.kkkcut.e20j.androidquick.tool;

import android.util.Log;

/* loaded from: classes.dex */
public class LogUtil {
    public static final int LOG_LEVEL = 2;
    public static final String TAG = "LogUtil";

    public static boolean isLoggable(int i) {
        return i >= 2;
    }

    public static void dumpException(Throwable th) {
        if (isLoggable(5)) {
            StringBuilder sb = new StringBuilder(256);
            sb.append("Got exception: ");
            sb.append(th.toString());
            sb.append("\n");
            System.out.println(sb.toString());
            th.printStackTrace(System.out);
        }
    }

    public static void v(String str, String str2) {
        log(2, str, str2);
    }

    public static void v(String str, String str2, Throwable th) {
        log(2, str, str2, th);
    }

    public static void d(String str, String str2) {
        log(3, str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        log(3, str, str2, th);
    }

    public static void i(String str, String str2) {
        log(4, str, str2);
    }

    public static void i(String str, String str2, Throwable th) {
        log(4, str, str2, th);
    }

    public static void w(String str, String str2) {
        log(5, str, str2);
    }

    public static void w(String str, String str2, Throwable th) {
        log(5, str, str2, th);
    }

    public static void e(String str, String str2) {
        log(6, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        log(6, str, str2, th);
    }

    public static void log(int i, String str, String str2) {
        log(i, str, str2, null);
    }

    public static void log(int i, String str, String str2, Throwable th) {
        if (isLoggable(i)) {
            if (i == 2) {
                Log.v(TAG, str + ": " + str2, th);
                return;
            }
            if (i == 3) {
                Log.d(TAG, str + ": " + str2, th);
                return;
            }
            if (i == 4) {
                Log.i(TAG, str + ": " + str2, th);
                return;
            }
            if (i == 5) {
                Log.w(TAG, str + ": " + str2, th);
                return;
            }
            if (i == 6) {
                Log.e(TAG, str + ": " + str2, th);
                return;
            }
            Log.e(TAG, str + ": " + str2, th);
        }
    }

    public static void method() {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace == null || 2 > stackTrace.length || (stackTraceElement = stackTrace[1]) == null) {
            return;
        }
        d(stackTraceElement.getClassName(), "+++++" + stackTraceElement.getMethodName());
    }

    public static void enter() {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace == null || 2 > stackTrace.length || (stackTraceElement = stackTrace[1]) == null) {
            return;
        }
        d(stackTraceElement.getClassName(), "====>" + stackTraceElement.getMethodName());
    }

    public static void leave() {
        StackTraceElement stackTraceElement;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace == null || 2 > stackTrace.length || (stackTraceElement = stackTrace[1]) == null) {
            return;
        }
        d(stackTraceElement.getClassName(), "<====" + stackTraceElement.getMethodName());
    }
}
