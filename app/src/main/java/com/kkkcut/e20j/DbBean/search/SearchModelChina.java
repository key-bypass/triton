package com.kkkcut.e20j.DbBean.search;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchModelChina {
    long fK_ManufacturerID;
    int is_visible;
    long modelID;
    String modelName;
    String modelName_CN;

    @Generated(hash = 1030975218)
    public SearchModelChina(long fK_ManufacturerID, int is_visible, long modelID,
                            String modelName, String modelName_CN) {
        this.fK_ManufacturerID = fK_ManufacturerID;
        this.is_visible = is_visible;
        this.modelID = modelID;
        this.modelName = modelName;
        this.modelName_CN = modelName_CN;
    }

    @Generated(hash = 570948065)
    public SearchModelChina() {
    }
    public long getFK_ManufacturerID() {
        return this.fK_ManufacturerID;
    }

    public void setFK_ManufacturerID(long fK_ManufacturerID) {
        this.fK_ManufacturerID = fK_ManufacturerID;
    }

    public int getIs_visible() {
        return this.is_visible;
    }

    public void setIs_visible(int is_visible) {
        this.is_visible = is_visible;
    }

    public long getModelID() {
        return this.modelID;
    }

    public void setModelID(long modelID) {
        this.modelID = modelID;
    }
    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public String getModelName_CN() {
        return this.modelName_CN;
    }

    public void setModelName_CN(String modelName_CN) {
        this.modelName_CN = modelName_CN;
    }

}
