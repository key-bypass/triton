package com.kkkcut.e20j.androidquick.autolayout.utils;

import android.util.TypedValue;

/* loaded from: classes.dex */
public class DimenUtils {
    private static int getComplexUnit(int i) {
        return (i >> 0) & 15;
    }

    public static boolean isPxVal(TypedValue typedValue) {
        return typedValue != null && typedValue.type == 5 && getComplexUnit(typedValue.data) == 0;
    }
}
