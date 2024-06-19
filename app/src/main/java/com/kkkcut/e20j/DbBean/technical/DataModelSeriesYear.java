package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

/* loaded from: classes.dex */
public class DataModelSeriesYear extends KeyInfoBase {
    int fK_ModelSeries;
    int modelSeriesYearID;
    String seriesYearName;
    String seriesYearName_CN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    public DataModelSeriesYear(int i, int i2, String str, String str2) {
        this.modelSeriesYearID = i;
        this.fK_ModelSeries = i2;
        this.seriesYearName = str;
        this.seriesYearName_CN = str2;
    }

    public DataModelSeriesYear() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.seriesYearName;
    }

    public int getModelSeriesYearID() {
        return this.modelSeriesYearID;
    }

    public void setModelSeriesYearID(int i) {
        this.modelSeriesYearID = i;
    }

    public int getFK_ModelSeries() {
        return this.fK_ModelSeries;
    }

    public void setFK_ModelSeries(int i) {
        this.fK_ModelSeries = i;
    }

    public String getSeriesYearName() {
        return this.seriesYearName;
    }

    public void setSeriesYearName(String str) {
        this.seriesYearName = str;
    }

    public String getSeriesYearName_CN() {
        return this.seriesYearName_CN;
    }

    public void setSeriesYearName_CN(String str) {
        this.seriesYearName_CN = str;
    }
}
