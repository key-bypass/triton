package com.kkkcut.e20j.bean.gsonBean;

import java.util.List;

/* loaded from: classes.dex */
public class CodeFindToothRes {
    private String Code;
    private List<DataBean> Data;
    private String Msg;

    public String getCode() {
        return this.Code;
    }

    public void setCode(String str) {
        this.Code = str;
    }

    public String getMsg() {
        return this.Msg;
    }

    public void setMsg(String str) {
        this.Msg = str;
    }

    public List<DataBean> getData() {
        return this.Data;
    }

    public void setData(List<DataBean> list) {
        this.Data = list;
    }

    /* loaded from: classes.dex */
    public static class DataBean {
        private String Bitting;
        private String ISN;

        public String getBitting() {
            return this.Bitting;
        }

        public void setBitting(String str) {
            this.Bitting = str;
        }

        public String getIsn() {
            return this.ISN;
        }

        public void setIsn(String str) {
            this.ISN = str;
        }
    }
}
