package com.liying.core.bean;

/* loaded from: classes2.dex */
public class PointXyz {
    private int index;

    /* renamed from: x */
    private int f381x;

    /* renamed from: y */
    private int f382y;

    /* renamed from: z */
    private int f383z;

    public PointXyz() {
    }

    public PointXyz(int i, SerialResBean serialResBean) {
        this.index = i;
        this.f381x = serialResBean.getX();
        this.f382y = serialResBean.getY();
        this.f383z = serialResBean.getZ();
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getX() {
        return this.f381x;
    }

    public void setX(int i) {
        this.f381x = i;
    }

    public int getY() {
        return this.f382y;
    }

    public void setY(int i) {
        this.f382y = i;
    }

    public int getZ() {
        return this.f383z;
    }

    public void setZ(int i) {
        this.f383z = i;
    }

    public String toString() {
        return "PointXyz{index=" + this.index + ", x=" + this.f381x + ", y=" + this.f382y + ", z=" + this.f383z + '}';
    }
}
