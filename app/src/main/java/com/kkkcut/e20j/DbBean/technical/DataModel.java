package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

/* loaded from: classes.dex */
public class DataModel extends KeyInfoBase {
    int fK_ManufacturerID;
    int modelID;
    String modelName;
    String modelName_CN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 1;
    }

    public DataModel(int i, int i2, String str, String str2) {
        this.modelID = i;
        this.fK_ManufacturerID = i2;
        this.modelName = str;
        this.modelName_CN = str2;
    }

    public DataModel() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.modelName;
    }

    public int getModelID() {
        return this.modelID;
    }

    public void setModelID(int i) {
        this.modelID = i;
    }

    public int getFK_ManufacturerID() {
        return this.fK_ManufacturerID;
    }

    public void setFK_ManufacturerID(int i) {
        this.fK_ManufacturerID = i;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String str) {
        this.modelName = str;
    }

    public String getModelName_CN() {
        return this.modelName_CN;
    }

    public void setModelName_CN(String str) {
        this.modelName_CN = str;
    }
}
