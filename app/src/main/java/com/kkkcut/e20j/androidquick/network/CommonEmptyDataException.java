package com.kkkcut.e20j.androidquick.network;

/* loaded from: classes.dex */
public class CommonEmptyDataException extends RuntimeException {
    private int errorCode;
    private String errorMsg;

    public CommonEmptyDataException(String str) {
        super(str);
        this.errorCode = 10000;
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
