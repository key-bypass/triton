package com.liying.core.duplicate.keyview;

import android.graphics.PointF;

/* loaded from: classes2.dex */
public class LineUtils {
    public static boolean between(float f, float f2, float f3) {
        double d = f3;
        double d2 = f;
        if (d < d2 - 0.01d || d > f2 + 0.01d) {
            return d <= d2 + 0.01d && d >= ((double) f2) - 0.01d;
        }
        return true;
    }

    public static PointF getCrossPoint(Line line, Line line2) {
        float f = line.getA().x;
        float f2 = line.getA().y;
        float f3 = line.getB().x;
        float f4 = line.getB().y;
        float f5 = line2.getA().x;
        float f6 = line2.getA().y;
        float f7 = line2.getB().x;
        float f8 = line2.getB().y;
        float f9 = f - f3;
        boolean z = f9 == 0.0f;
        float f10 = f5 - f7;
        boolean z2 = f10 == 0.0f;
        float f11 = !z ? (f2 - f4) / f9 : Float.MAX_VALUE;
        float f12 = z2 ? Float.MAX_VALUE : (f6 - f8) / f10;
        if (f11 == f12) {
            return null;
        }
        if (z) {
            if (z2) {
                return null;
            }
            if (f12 != 0.0f) {
                f2 = (f12 * (f - f7)) + f8;
                return new PointF(f, f2);
            }
            f2 = f6;
            return new PointF(f, f2);
        }
        if (z2) {
            if (f11 != 0.0f) {
                f2 = (f11 * (f5 - f3)) + f4;
            }
            f = f5;
        } else if (f11 == 0.0f) {
            f = ((f2 - f8) / f12) + f7;
        } else if (f12 == 0.0f) {
            f = ((f6 - f4) / f11) + f3;
            f2 = f6;
        } else {
            f = ((((f11 * f3) - (f7 * f12)) + f8) - f4) / (f11 - f12);
            f2 = (f11 * (f - f3)) + f4;
        }
        return new PointF(f, f2);
    }

    public static float getK(Line line) {
        if (line.getB().x - line.getA().x == 0.0f) {
            return Float.MAX_VALUE;
        }
        return (line.getB().y - line.getA().y) / (line.getB().x - line.getA().x);
    }

    public static Line getVerticalLine(Line line, PointF pointF) {
        float k = (-1.0f) / getK(line);
        return new Line(pointF, new PointF(pointF.x + 10.0f, ((pointF.x + 10.0f) * k) + (pointF.y - (pointF.x * k))));
    }
}
