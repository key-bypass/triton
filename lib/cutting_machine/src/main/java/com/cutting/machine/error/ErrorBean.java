package com.liying.core.error;

import com.liying.core.DialogBtnCallBack;

/* loaded from: classes2.dex */
public class ErrorBean {
    private int code;
    private DialogBtnCallBack dialogBtnCallBack;
    private int imgRes;
    private String msg;

    public ErrorBean(int i, String str) {
        this.code = i;
        this.msg = str;
    }

    public ErrorBean(int i) {
        this.code = i;
    }

    public ErrorBean() {
    }

    public int getImgRes() {
        return this.imgRes;
    }

    public void setImgRes(int i) {
        this.imgRes = i;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DialogBtnCallBack getDialogBtnCallBack() {
        return this.dialogBtnCallBack;
    }

    public void setDialogBtnCallBack(DialogBtnCallBack dialogBtnCallBack) {
        this.dialogBtnCallBack = dialogBtnCallBack;
    }
}
