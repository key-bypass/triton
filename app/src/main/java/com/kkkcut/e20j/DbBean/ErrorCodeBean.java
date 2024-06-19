package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class ErrorCodeBean {
    int Code;
    String Desc_cs;
    String Desc_de;
    String Desc_en;
    String Desc_es;
    String Desc_fr;
    String Desc_hb;
    String Desc_it;
    String Desc_ko;
    String Desc_pl;
    String Desc_pt;
    String Desc_ru;
    String Desc_tr;
    String Desc_vi;
    String Desc_zh;
    int ID;
    int MessageType;

    public ErrorCodeBean(int i, int i2, int i3, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.ID = i;
        this.Code = i2;
        this.MessageType = i3;
        this.Desc_en = str;
        this.Desc_zh = str2;
        this.Desc_cs = str3;
        this.Desc_fr = str4;
        this.Desc_de = str5;
        this.Desc_it = str6;
        this.Desc_es = str7;
        this.Desc_ko = str8;
        this.Desc_pt = str9;
        this.Desc_ru = str10;
        this.Desc_tr = str11;
        this.Desc_hb = str12;
        this.Desc_pl = str13;
        this.Desc_vi = str14;
    }

    public ErrorCodeBean() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public int getCode() {
        return this.Code;
    }

    public void setCode(int i) {
        this.Code = i;
    }

    public int getMessageType() {
        return this.MessageType;
    }

    public void setMessageType(int i) {
        this.MessageType = i;
    }

    public String getDesc_en() {
        return this.Desc_en;
    }

    public void setDesc_en(String str) {
        this.Desc_en = str;
    }

    public String getDesc_zh() {
        return this.Desc_zh;
    }

    public void setDesc_zh(String str) {
        this.Desc_zh = str;
    }

    public String getDesc_cs() {
        return this.Desc_cs;
    }

    public void setDesc_cs(String str) {
        this.Desc_cs = str;
    }

    public String getDesc_fr() {
        return this.Desc_fr;
    }

    public void setDesc_fr(String str) {
        this.Desc_fr = str;
    }

    public String getDesc_de() {
        return this.Desc_de;
    }

    public void setDesc_de(String str) {
        this.Desc_de = str;
    }

    public String getDesc_it() {
        return this.Desc_it;
    }

    public void setDesc_it(String str) {
        this.Desc_it = str;
    }

    public String getDesc_es() {
        return this.Desc_es;
    }

    public void setDesc_es(String str) {
        this.Desc_es = str;
    }

    public String getDesc_ko() {
        return this.Desc_ko;
    }

    public void setDesc_ko(String str) {
        this.Desc_ko = str;
    }

    public String getDesc_pt() {
        return this.Desc_pt;
    }

    public void setDesc_pt(String str) {
        this.Desc_pt = str;
    }

    public String getDesc_ru() {
        return this.Desc_ru;
    }

    public void setDesc_ru(String str) {
        this.Desc_ru = str;
    }

    public String getDesc_tr() {
        return this.Desc_tr;
    }

    public void setDesc_tr(String str) {
        this.Desc_tr = str;
    }

    public String getDesc_hb() {
        return this.Desc_hb;
    }

    public void setDesc_hb(String str) {
        this.Desc_hb = str;
    }

    public String getDesc_pl() {
        return this.Desc_pl;
    }

    public void setDesc_pl(String str) {
        this.Desc_pl = str;
    }

    public String getDesc_vi() {
        return this.Desc_vi;
    }

    public void setDesc_vi(String str) {
        this.Desc_vi = str;
    }
}
