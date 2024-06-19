package com.kkkcut.e20j.bean;

/* loaded from: classes.dex */
public class AngleKeyStep {
    String space;
    String tooth;

    public AngleKeyStep(String str, String str2) {
        this.tooth = str;
        this.space = str2;
    }

    public String getTooth() {
        return this.tooth;
    }

    public void setTooth(String str) {
        this.tooth = str;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String str) {
        this.space = str;
    }

    public String toString() {
        return "AngleKeyStep{tooth=" + this.tooth + ", space='" + this.space + "'}";
    }
}
