package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataModelSeries extends KeyInfoBase {
    int fK_ModelID;
    int modelSeriesID;
    String seriesName;
    String seriesName_CN;

    @Generated(hash = 1358380681)
    public DataModelSeries(int fK_ModelID, int modelSeriesID, String seriesName,
            String seriesName_CN) {
        this.fK_ModelID = fK_ModelID;
        this.modelSeriesID = modelSeriesID;
        this.seriesName = seriesName;
        this.seriesName_CN = seriesName_CN;
    }


    @Generated(hash = 1717617936)
    public DataModelSeries() {
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
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
