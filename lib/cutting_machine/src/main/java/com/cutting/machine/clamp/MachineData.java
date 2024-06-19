package com.liying.core.clamp;

/* loaded from: classes2.dex */
public class MachineData {
    public static final int SafetyValue = 10;
    public static final int commonDiff = 20;
    public static float dXScale = 0.46875f;
    public static float dYScale = 0.46875f;
    public static float dZScale = 0.25f;
    public static final int dcDiff = 50;
    public static int dcX = 4000;
    public static final int dcY = 0;
    public static final int keyWidthDiff = 15;
    public static final int s1aHeight = 65;
    public static final int s1aShoulderDis = 3000;
    public static final int s1bHeightUp = 165;
    public static final int s1bShoulderUpDis = 2690;
    public static final int s1cLeftDis = 1150;
    public static final int s1cRightDis = 1220;
    public static final int s1cShoulderDis = 2200;
    public static final int s1dLeftDis = 1150;
    public static final int s1dRightDis = 1220;
    public static final int s1dShoulderDis = 2000;
    public static int xAxisMax = 6800;
    public static int xDirection = 1;
    public static int yAxisMax = 5800;
    public static int yDirection = -1;
    public static int zAxisMax = 2400;
    public static int zDirection = 1;

    public static void beta() {
        dXScale = 0.46875f;
        dYScale = 0.46875f;
        dZScale = 0.25f;
        xDirection = 1;
        yDirection = -1;
        zDirection = 1;
        xAxisMax = 6800;
        yAxisMax = 5800;
        zAxisMax = 2400;
    }

    public static void alpha() {
        dXScale = 0.25f;
        dYScale = 0.25f;
        dZScale = 0.25f;
        xDirection = 1;
        yDirection = -1;
        zDirection = 1;
        xAxisMax = 6800;
        yAxisMax = 5800;
        zAxisMax = 2400;
    }

    /* renamed from: e9 */
    public static void m93e9() {
        dXScale = 0.5f;
        dYScale = 0.5f;
        dZScale = 0.25f;
        xDirection = -1;
        yDirection = -1;
        zDirection = 1;
        xAxisMax = 10600;
        yAxisMax = 2080;
        zAxisMax = 4500;
    }

    public static float getXRatio() {
        return dXScale;
    }

    public static float getYRatio() {
        return dYScale;
    }

    public static float getZRatio() {
        return dZScale;
    }

    public static int getXDirection() {
        return xDirection;
    }

    public static int getYDirection() {
        return yDirection;
    }

    public static int getZDirection() {
        return zDirection;
    }

    public static int getxAxisMax() {
        return xAxisMax;
    }

    public static int getyAxisMax() {
        return yAxisMax;
    }

    public static int getzAxisMax() {
        return zAxisMax;
    }
}
