package com.cutting.machine.bean;

import com.cutting.machine.clamp.MachineData;

/* loaded from: classes2.dex */
public class StepBean {
    private String Des;
    private String Rule;

    /* renamed from: S */
    private String f388S;

    /* renamed from: T */
    private int f389T;

    /* renamed from: X */
    private int f390X;

    /* renamed from: Y */
    private int f391Y;

    /* renamed from: Z */
    private int f392Z;
    private SerialResBean serialResBean;
    private String vStr;

    public StepBean(int i, int i2, int i3, int i4, String str, String str2) {
        this.f389T = i;
        this.f390X = (int) (i2 / MachineData.dXScale);
        this.f391Y = (int) (i3 / MachineData.dYScale);
        this.f392Z = (int) (i4 / MachineData.dZScale);
        this.vStr = str;
        this.Rule = str2;
    }

    public StepBean(int i, int i2, int i3, int i4, String str, String str2, boolean z) {
        this.f389T = i;
        if (z) {
            this.f390X = (int) (i2 / MachineData.dXScale);
            this.f391Y = (int) (i3 / MachineData.dYScale);
            this.f392Z = (int) (i4 / MachineData.dZScale);
        } else {
            this.f390X = i2;
            this.f391Y = i3;
            this.f392Z = i4;
        }
        this.vStr = str;
        this.Rule = str2;
    }

    public StepBean(String str, int i, int i2, int i3, int i4, String str2, String str3) {
        this.f388S = str;
        this.f389T = i;
        this.f390X = (int) (i2 / MachineData.dXScale);
        this.f391Y = (int) (i3 / MachineData.dYScale);
        this.f392Z = (int) (i4 / MachineData.dZScale);
        this.vStr = str2;
        this.Rule = str3;
    }

    public StepBean(String str, int i, int i2, int i3, int i4, String str2, String str3, boolean z) {
        this.f388S = str;
        this.f389T = i;
        if (z) {
            this.f390X = (int) (i2 / MachineData.dXScale);
            this.f391Y = (int) (i3 / MachineData.dYScale);
            this.f392Z = (int) (i4 / MachineData.dZScale);
        } else {
            this.f390X = i2;
            this.f391Y = i3;
            this.f392Z = i4;
        }
        this.vStr = str2;
        this.Rule = str3;
    }

    public StepBean() {
    }

    public String getS() {
        return this.f388S;
    }

    public void setS(String str) {
        this.f388S = str;
    }

    public int getT() {
        return this.f389T;
    }

    public void setT(int i) {
        this.f389T = i;
    }

    public int getX() {
        return this.f390X;
    }

    public void setX(int i) {
        this.f390X = i;
    }

    public int getY() {
        return this.f391Y;
    }

    public void setY(int i) {
        this.f391Y = i;
    }

    public int getZ() {
        return this.f392Z;
    }

    public void setZ(int i) {
        this.f392Z = i;
    }

    public String getvStr() {
        return this.vStr;
    }

    public void setvStr(String str) {
        this.vStr = str;
    }

    public String getRule() {
        return this.Rule;
    }

    public void setRule(String str) {
        this.Rule = str;
    }

    public String getDes() {
        return this.Des;
    }

    public void setDes(String str) {
        this.Des = str;
    }

    public SerialResBean getSerialResBean() {
        return this.serialResBean;
    }

    public void setSerialResBean(SerialResBean serialResBean) {
        this.serialResBean = serialResBean;
    }

    public String toString() {
        return "ProcessBean{S='" + this.f388S + "', T=" + this.f389T + ", X=" + this.f390X + ", Y=" + this.f391Y + ", Z=" + this.f392Z + ", V=" + this.vStr + ", Rule='" + this.Rule + "', Des='" + this.Des + "'}";
    }
}
