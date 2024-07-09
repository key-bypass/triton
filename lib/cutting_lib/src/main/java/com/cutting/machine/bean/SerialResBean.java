package com.cutting.machine.bean;

/* loaded from: classes2.dex */
public class SerialResBean {

    /* renamed from: r */
    private int f384r;

    /* renamed from: x */
    private int f385x;

    /* renamed from: y */
    private int f386y;

    /* renamed from: z */
    private int f387z;

    public SerialResBean() {
    }

    public SerialResBean(int i, int i2, int i3, int i4) {
        this.f385x = i;
        this.f386y = i2;
        this.f387z = i3;
        this.f384r = i4;
    }

    public int getX() {
        return this.f385x;
    }

    public void setX(int i) {
        this.f385x = i;
    }

    public int getY() {
        return this.f386y;
    }

    public void setY(int i) {
        this.f386y = i;
    }

    public int getZ() {
        return this.f387z;
    }

    public void setZ(int i) {
        this.f387z = i;
    }

    public int getR() {
        return this.f384r;
    }

    public void setR(int i) {
        this.f384r = i;
    }

    public String toString() {
        return "SerialResBean{x=" + this.f385x + ", y=" + this.f386y + ", z=" + this.f387z + ", r=" + this.f384r + '}';
    }
}
