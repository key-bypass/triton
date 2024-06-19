package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

/* loaded from: classes.dex */
public class DataModelSeries extends KeyInfoBase {
    int fK_ModelID;
    int modelSeriesID;
    String seriesName;
    String seriesName_CN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    public DataModelSeries(int i, int i2, String str, String str2) {
        this.modelSeriesID = i;
        this.fK_ModelID = i2;
        this.seriesName = str;
        this.seriesName_CN = str2;
    }

    public DataModelSeries() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.seriesName;
    }

    public int getModelSeriesID() {
        return this.modelSeriesID;
    }

    public void setModelSeriesID(int i) {
        this.modelSeriesID = i;
    }

    public int getFK_ModelID() {
        return this.fK_ModelID;
    }

    public void setFK_ModelID(int i) {
        this.fK_ModelID = i;
    }

    public String getSeriesName() {
        return this.seriesName;
    }

    public void setSeriesName(String str) {
        this.seriesName = str;
    }

    public String getSeriesName_CN() {
        return this.seriesName_CN;
    }

    public void setSeriesName_CN(String str) {
        this.seriesName_CN = str;
    }
}
