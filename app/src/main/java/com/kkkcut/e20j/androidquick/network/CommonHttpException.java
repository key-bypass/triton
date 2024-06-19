package com.kkkcut.e20j.androidquick.network;

/* loaded from: classes.dex */
public class CommonHttpException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public CommonHttpException(int i, String str) {
        super(str);
        this.errorCode = i;
        this.errorMsg = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }
}
