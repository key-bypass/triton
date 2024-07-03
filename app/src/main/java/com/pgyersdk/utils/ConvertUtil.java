package com.pgyersdk.utils;

import android.content.Context;

/* compiled from: ConvertUtil.java */
/* renamed from: com.pgyersdk.f.b */
/* loaded from: classes2.dex */
public class ConvertUtil {
    /* renamed from: a */
    public static int m195a(Context context, float f) {
        return (int) ((f * (context.getResources().getDisplayMetrics().densityDpi / 160.0f)) + 0.5f);
    }
}
