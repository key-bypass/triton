package com.cutting.machine;

/* loaded from: classes2.dex */
public class CutPoint {
    private boolean breakPoint;
    private boolean gap;
    private boolean isTooth;
    private boolean sharpenPoint;

    /* renamed from: x */
    private int f361x;

    /* renamed from: y */
    private int f362y;

    /* renamed from: z */
    private int f363z;

    public CutPoint() {
    }

    public CutPoint(int i, int i2) {
        this.f361x = i;
        this.f362y = i2;
    }

    public CutPoint(int i, int i2, int i3) {
        this.f361x = i;
        this.f362y = i2;
        this.f363z = i3;
    }

    public CutPoint(int i, int i2, boolean z) {
        this.f361x = i;
        this.f362y = i2;
        this.isTooth = z;
    }

    public boolean isBreakPoint() {
        return this.breakPoint;
    }

    public void setBreakPoint(boolean z) {
        this.breakPoint = z;
    }

    public int getX() {
        return this.f361x;
    }

    public void setX(int i) {
        this.f361x = i;
    }

    public int getY() {
        return this.f362y;
    }

    public void setY(int i) {
        this.f362y = i;
    }

    public int getZ() {
        return this.f363z;
    }

    public void setZ(int i) {
        this.f363z = i;
    }

    public boolean isTooth() {
        return this.isTooth;
    }

    public boolean isSharpenPoint() {
        return this.sharpenPoint;
    }

    public void setSharpenPoint(boolean z) {
        this.sharpenPoint = z;
    }

    public boolean isGap() {
        return this.gap;
    }

    public void setGap(boolean z) {
        this.gap = z;
    }

    public String toString() {
        return "CutPoint{x=" + this.f361x + ", y=" + this.f362y + ", z=" + this.f363z + '}';
    }
}
