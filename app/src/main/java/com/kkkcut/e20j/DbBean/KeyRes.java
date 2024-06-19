package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class KeyRes {
    String Description;
    byte[] ExplainHtml;
    int FK_LanguageID;
    int FK_ModelSeriesID;
    int ID;
    int ProductTypeID;
    String VideoPath;

    public KeyRes(int i, int i2, int i3, byte[] bArr, String str, String str2, int i4) {
        this.ID = i;
        this.FK_ModelSeriesID = i2;
        this.FK_LanguageID = i3;
        this.ExplainHtml = bArr;
        this.VideoPath = str;
        this.Description = str2;
        this.ProductTypeID = i4;
    }

    public KeyRes() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public int getFK_ModelSeriesID() {
        return this.FK_ModelSeriesID;
    }

    public void setFK_ModelSeriesID(int i) {
        this.FK_ModelSeriesID = i;
    }

    public int getFK_LanguageID() {
        return this.FK_LanguageID;
    }

    public void setFK_LanguageID(int i) {
        this.FK_LanguageID = i;
    }

    public byte[] getExplainHtml() {
        return this.ExplainHtml;
    }

    public void setExplainHtml(byte[] bArr) {
        this.ExplainHtml = bArr;
    }

    public String getVideoPath() {
        return this.VideoPath;
    }

    public void setVideoPath(String str) {
        this.VideoPath = str;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String str) {
        this.Description = str;
    }

    public int getProductTypeID() {
        return this.ProductTypeID;
    }

    public void setProductTypeID(int i) {
        this.ProductTypeID = i;
    }
}
