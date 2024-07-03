package com.pgyersdk.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: SharedPreferenesManager.java */
/* renamed from: com.pgyersdk.f.h */
/* loaded from: classes2.dex */
public class SharedPreferenesManager {

    /* renamed from: a */
    private static SharedPreferences f533a;

    /* renamed from: a */
    public static String m222a(Context context, String str) {
        if (f533a == null) {
            f533a = context.getSharedPreferences("pgyersdk", 0);
        }
        return f533a.getString(str, "");
    }

    /* renamed from: a */
    public static void m223a(Context context, String str, String str2) {
        if (f533a == null) {
            f533a = context.getSharedPreferences("pgyersdk", 0);
        }
        f533a.edit().putString(str, str2).commit();
    }

    /* renamed from: a */
    public static void m224a(String str, String str2) {
        SharedPreferences sharedPreferences = f533a;
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putString(str, str2).commit();
    }
}
