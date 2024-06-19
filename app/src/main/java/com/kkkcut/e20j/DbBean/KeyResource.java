package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class KeyResource {
    String ExplainHtml;
    int FK_LanguageID;
    int FK_ModelSeriesID;
    int ID;
    int ProductTypeID;
    String VideoPath;

    public KeyResource(int i, int i2, int i3, int i4, String str, String str2) {
        this.ID = i;
        this.FK_ModelSeriesID = i2;
        this.FK_LanguageID = i3;
        this.ProductTypeID = i4;
        this.ExplainHtml = str;
        this.VideoPath = str2;
    }

    public KeyResource() {
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

    public int getProductTypeID() {
        return this.ProductTypeID;
    }

    public void setProductTypeID(int i) {
        this.ProductTypeID = i;
    }

    public String getExplainHtml() {
        return this.ExplainHtml;
    }

    public void setExplainHtml(String str) {
        this.ExplainHtml = str;
    }

    public String getVideoPath() {
        return this.VideoPath;
    }

    public void setVideoPath(String str) {
        this.VideoPath = str;
    }
}
