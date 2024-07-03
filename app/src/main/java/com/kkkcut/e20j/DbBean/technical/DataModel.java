package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DataModel extends KeyInfoBase {
    int fK_ManufacturerID;
    int modelID;
    String modelName;
    String modelName_CN;

    @Generated(hash = 1125617716)
    public DataModel(int fK_ManufacturerID, int modelID, String modelName,
            String modelName_CN) {
        this.fK_ManufacturerID = fK_ManufacturerID;
        this.modelID = modelID;
        this.modelName = modelName;
        this.modelName_CN = modelName_CN;
    }


    @Generated(hash = 1794172823)
    public DataModel() {
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 1;
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
