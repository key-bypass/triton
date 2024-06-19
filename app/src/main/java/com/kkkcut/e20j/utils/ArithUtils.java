package com.kkkcut.e20j.utils;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public class ArithUtils {
    private ArithUtils() {
    }

    public static double add(double d, double d2) {
        return new BigDecimal(Double.toString(d)).add(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static Double sub(double d, double d2) {
        return Double.valueOf(new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).doubleValue());
    }

    public static double mul(double d, double d2) {
        return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
    }

    public static float div(float f, float f2, int i) throws IllegalAccessException {
        if (i < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        return new BigDecimal(Float.toString(f)).divide(new BigDecimal(Float.toString(f2)), i).floatValue();
    }
}
