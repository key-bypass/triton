package com.cutting.machine.operation.cut.cutpath;

/* loaded from: classes2.dex */
public class KeyPointFactory {
    public static KeyPoint getKeyPoint(int i, int i2, int i3) {
        return new KeyPoint(i, i2, i3);
    }

    public static KeyPoint getKeyPoint(int i, int i2) {
        return new KeyPoint(i, i2, 0);
    }

    public static KeyPoint getAngleKeyPoint(int i, float f) {
        KeyPoint keyPoint = new KeyPoint();
        keyPoint.setX(i);
        keyPoint.setAngle(f);
        return keyPoint;
    }

    public static KeyPoint getKeyPoint(int i, int i2, boolean z) {
        return new KeyPoint(i, i2, 0, z);
    }

    public static KeyPoint getDoNotSplitKeyPoint(int i, int i2) {
        return KeyPoint.KeyPointBuilder.aKeyPoint().withX(i).withY(i2).withDoNotSplitDepth(true).build();
    }

    public static KeyPoint getDoNotFixSpaceWidthPoint(int i, int i2) {
        return KeyPoint.KeyPointBuilder.aKeyPoint().withX(i).withY(i2).withDoNotFixSpaceWidth(true).build();
    }

    public static KeyPoint getDimpleKeyPoint(int i, int i2, int i3, int i4) {
        KeyPoint keyPoint = new KeyPoint(i, i2, i3);
        keyPoint.setDimplePoreRadius(i4);
        return keyPoint;
    }
}
