package com.kkkcut.e20j.DbBean;

import android.text.TextUtils;
import com.liying.core.MachineInfo;

/* loaded from: classes.dex */
public class Model extends KeyInfoBase {
    String description;
    String distributorNum;
    int fK_ManufacturerID;
    int is_visible;
    int modelID;
    String modelName;
    String modelName_CN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 1;
    }

    public Model(int i, int i2, String str, String str2, String str3, int i3, String str4) {
        this.modelID = i;
        this.fK_ManufacturerID = i2;
        this.modelName = str;
        this.modelName_CN = str2;
        this.description = str3;
        this.is_visible = i3;
        this.distributorNum = str4;
    }

    public Model() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(this.modelName_CN)) {
            return this.modelName_CN;
        }
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getIs_visible() {
        return this.is_visible;
    }

    public void setIs_visible(int i) {
        this.is_visible = i;
    }

    public String getDistributorNum() {
        return this.distributorNum;
    }

    public void setDistributorNum(String str) {
        this.distributorNum = str;
    }
}
