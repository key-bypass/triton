package com.kkkcut.e20j.DbBean.search;

/* loaded from: classes.dex */
public class SearchManufacturer {
    long manufacturerId;
    String manufacturerName;
    String manufacturerNameCN;

    public SearchManufacturer(long j, String str, String str2) {
        this.manufacturerId = j;
        this.manufacturerName = str;
        this.manufacturerNameCN = str2;
    }

    public SearchManufacturer() {
    }

    public long getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(long j) {
        this.manufacturerId = j;
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
