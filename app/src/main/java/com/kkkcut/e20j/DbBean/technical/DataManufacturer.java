package com.kkkcut.e20j.DbBean.technical;

import com.kkkcut.e20j.DbBean.KeyInfoBase;

/* loaded from: classes.dex */
public class DataManufacturer extends KeyInfoBase {
    int manufacturerId;
    String manufacturerName;
    String manufacturerNameCN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    public DataManufacturer(int i, String str, String str2) {
        this.manufacturerId = i;
        this.manufacturerName = str;
        this.manufacturerNameCN = str2;
    }

    public DataManufacturer() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.manufacturerName;
    }

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(int i) {
        this.manufacturerId = i;
    }

    public String getManufacturerName() {
        return this.manufacturerName;
    }

    public void setManufacturerName(String str) {
        this.manufacturerName = str;
    }

    public String getManufacturerNameCN() {
        return this.manufacturerNameCN;
    }

    public void setManufacturerNameCN(String str) {
        this.manufacturerNameCN = str;
    }
}
