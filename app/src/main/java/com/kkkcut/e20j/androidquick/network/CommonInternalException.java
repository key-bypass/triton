package com.kkkcut.e20j.androidquick.network;

/* loaded from: classes.dex */
public class CommonInternalException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public CommonInternalException(String str) {
        super(str);
        this.errorCode = 10001;
        this.errorMsg = str;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
