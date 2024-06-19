package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class BittingCode {
    String bitting;
    String code;
    String isn;

    public BittingCode(String str, String str2, String str3) {
        this.code = str;
        this.bitting = str2;
        this.isn = str3;
    }

    public BittingCode() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getBitting() {
        return this.bitting;
    }

    public void setBitting(String str) {
        this.bitting = str;
    }

    public String getIsn() {
        return this.isn;
    }

    public void setIsn(String str) {
        this.isn = str;
    }
}
