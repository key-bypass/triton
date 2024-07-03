package com.cutting.machine.utils;

import android.graphics.PointF;

/* loaded from: classes2.dex */
public class CircleUtils {
    public static PointF getCenterPointOfCircle(int i, int i2, int i3, int i4, int i5, int i6) {
        float f = (i3 - i) * 2;
        float f2 = (i4 - i2) * 2;
        int i7 = i3 * i3;
        int i8 = i4 * i4;
        float f3 = ((i7 + i8) - (i * i)) - (i2 * i2);
        float f4 = (i5 - i3) * 2;
        float f5 = (i6 - i4) * 2;
        float f6 = (((i5 * i5) + (i6 * i6)) - i7) - i8;
        float f7 = (f2 * f6) - (f5 * f3);
        float f8 = (f2 * f4) - (f5 * f);
        return new PointF(f7 / f8, ((f4 * f3) - (f * f6)) / f8);
    }

    public static double getRadiusOfCircle(int i, int i2, int i3, int i4, int i5, int i6) {
        PointF centerPointOfCircle = getCenterPointOfCircle(i, i2, i3, i4, i5, i6);
        float f = centerPointOfCircle.x - i;
        float f2 = centerPointOfCircle.y - i2;
        return Math.sqrt((f * f) + (f2 * f2));
    }
}
