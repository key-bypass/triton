package com.kkkcut.e20j.bean.gsonBean;

import java.util.List;

/* loaded from: classes.dex */
public class GetTestData {
    private String Code;
    private List<DataListBean> Data_list;
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

    public List<DataListBean> getData_list() {
        return this.Data_list;
    }

    public void setData_list(List<DataListBean> list) {
        this.Data_list = list;
    }

    /* loaded from: classes.dex */
    public static class DataListBean {
        private String Basic_data_ID;
        private String Code_series;
        private String Cuts;
        private String Key_china_num;
        private String Remark;
        private String Series_ID;
        private String Title;
        private String Tooth_Code;

        public String getTitle() {
            return this.Title;
        }

        public void setTitle(String str) {
            this.Title = str;
        }

        public String getCode_series() {
            return this.Code_series;
        }

        public void setCode_series(String str) {
            this.Code_series = str;
        }

        public String getKey_china_num() {
            return this.Key_china_num;
        }

        public void setKey_china_num(String str) {
            this.Key_china_num = str;
        }

        public String getCuts() {
            return this.Cuts;
        }

        public void setCuts(String str) {
            this.Cuts = str;
        }

        public String getBasic_data_ID() {
            return this.Basic_data_ID;
        }

        public void setBasic_data_ID(String str) {
            this.Basic_data_ID = str;
        }

        public String getSeries_ID() {
            return this.Series_ID;
        }

        public void setSeries_ID(String str) {
            this.Series_ID = str;
        }

        public String getTooth_Code() {
            return this.Tooth_Code;
        }

        public void setTooth_Code(String str) {
            this.Tooth_Code = str;
        }

        public String getRemark() {
            return this.Remark;
        }

        public void setRemark(String str) {
            this.Remark = str;
        }
    }
}
