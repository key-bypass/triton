package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;

/* loaded from: classes.dex */
public class Utils {
    public static int dipToPx(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int pxToDip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
