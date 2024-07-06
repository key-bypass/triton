package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataModelSeriesYear extends KeyInfoBase {
    int fK_ModelSeries;
    int modelSeriesYearID;
    String seriesYearName;
    String seriesYearName_CN;

    @Generated(hash = 1119503197)
    public DataModelSeriesYear(int fK_ModelSeries, int modelSeriesYearID,
            String seriesYearName, String seriesYearName_CN) {
        this.fK_ModelSeries = fK_ModelSeries;
        this.modelSeriesYearID = modelSeriesYearID;
        this.seriesYearName = seriesYearName;
        this.seriesYearName_CN = seriesYearName_CN;
    }



    @Generated(hash = 711987271)
    public DataModelSeriesYear() {
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
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
