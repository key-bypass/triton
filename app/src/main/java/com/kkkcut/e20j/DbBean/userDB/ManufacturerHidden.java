package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.KeyInfoBase;
import com.kkkcut.e20j.DbBean.Manufacturer;

/* loaded from: classes.dex */
public class ManufacturerHidden extends KeyInfoBase {
    Long manufacturerId;
    byte[] manufacturerLogoImage;
    String manufacturerName;
    String manufacturerNameCN;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    public ManufacturerHidden(Manufacturer manufacturer) {
        this.manufacturerId = Long.valueOf(manufacturer.getManufacturerId());
        this.manufacturerName = manufacturer.getManufacturerName();
        this.manufacturerNameCN = manufacturer.getManufacturerNameCN();
        this.manufacturerLogoImage = manufacturer.manufacturerLogoImage;
    }

    public ManufacturerHidden(Long l, String str, String str2, byte[] bArr) {
        this.manufacturerId = l;
        this.manufacturerName = str;
        this.manufacturerNameCN = str2;
        this.manufacturerLogoImage = bArr;
    }

    public ManufacturerHidden() {
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.manufacturerName;
    }

    public Long getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(long j) {
        this.manufacturerId = Long.valueOf(j);
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

    public byte[] getManufacturerLogoImage() {
        return this.manufacturerLogoImage;
    }

    public void setManufacturerLogoImage(byte[] bArr) {
        this.manufacturerLogoImage = bArr;
    }

    public void setManufacturerId(Long l) {
        this.manufacturerId = l;
    }
}
