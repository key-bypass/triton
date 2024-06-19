package com.kkkcut.e20j.utils;

/* loaded from: classes.dex */
public class UnitUtils {
    public static int mm2Inch(int i) {
        return Math.round(i / 2.54f);
    }

    public static int inch2Mm(int i) {
        return Math.round(i * 2.54f);
    }
}
