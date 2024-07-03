package com.kkkcut.e20j.DbBean.search;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SearchManufacturer {
    long manufacturerId;
    String manufacturerName;
    String manufacturerNameCN;
    @Generated(hash = 1456345221)
    public SearchManufacturer(long manufacturerId, String manufacturerName,
            String manufacturerNameCN) {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.manufacturerNameCN = manufacturerNameCN;
    }
    @Generated(hash = 1460686600)
    public SearchManufacturer() {
    }
    public long getManufacturerId() {
        return this.manufacturerId;
    }
    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
    public String getManufacturerName() {
        return this.manufacturerName;
    }
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
    public String getManufacturerNameCN() {
        return this.manufacturerNameCN;
    }
    public void setManufacturerNameCN(String manufacturerNameCN) {
        this.manufacturerNameCN = manufacturerNameCN;
    }
}
