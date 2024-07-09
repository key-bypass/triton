package com.uuzuche.lib_zxing;

import android.content.Context;

/* loaded from: classes2.dex */
public class DisplayUtil {
    public static float density;
    public static int densityDPI;
    public static float screenHightDip;
    public static float screenWidthDip;
    public static int screenWidthPx;
    public static int screenhightPx;

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
