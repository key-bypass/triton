package com.kkkcut.e20j.DbBean;

import android.text.TextUtils;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHidden;
import com.cutting.machine.MachineInfo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Manufacturer extends KeyInfoBase {
    String description;
    String distributorNum;
    private boolean isChecked;
    int is_automobile;
    int is_automobile_chs;
    int is_dimple;
    int is_motorcycle;
    int is_standard;
    int is_tubular;
    int is_visible;
    int manufacturerId;
    public byte[] manufacturerLogoImage;
    String manufacturerName;
    String manufacturerNameCN;

    public int getItemType() {
        return 0;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public Manufacturer(ManufacturerHidden manufacturerHidden) {
        this.manufacturerId = manufacturerHidden.getManufacturerId().intValue();
        this.manufacturerName = manufacturerHidden.getManufacturerName();
        this.manufacturerNameCN = manufacturerHidden.getManufacturerNameCN();
        this.manufacturerLogoImage = manufacturerHidden.getManufacturerLogoImage();
    }

    public Manufacturer(int i, String str, String str2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str3, byte[] bArr, String str4) {
        this.manufacturerId = i;
        this.manufacturerName = str;
        this.manufacturerNameCN = str2;
        this.is_automobile = i2;
        this.is_motorcycle = i3;
        this.is_dimple = i4;
        this.is_tubular = i5;
        this.is_standard = i6;
        this.is_automobile_chs = i7;
        this.is_visible = i8;
        this.description = str3;
        this.manufacturerLogoImage = bArr;
        this.distributorNum = str4;
    }

    public Manufacturer() {
    }

    @Generated(hash = 1228751705)
    public Manufacturer(String description, String distributorNum, boolean isChecked, int is_automobile, int is_automobile_chs, int is_dimple,
            int is_motorcycle, int is_standard, int is_tubular, int is_visible, int manufacturerId, byte[] manufacturerLogoImage, String manufacturerName,
            String manufacturerNameCN) {
        this.description = description;
        this.distributorNum = distributorNum;
        this.isChecked = isChecked;
        this.is_automobile = is_automobile;
        this.is_automobile_chs = is_automobile_chs;
        this.is_dimple = is_dimple;
        this.is_motorcycle = is_motorcycle;
        this.is_standard = is_standard;
        this.is_tubular = is_tubular;
        this.is_visible = is_visible;
        this.manufacturerId = manufacturerId;
        this.manufacturerLogoImage = manufacturerLogoImage;
        this.manufacturerName = manufacturerName;
        this.manufacturerNameCN = manufacturerNameCN;
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

    public int getIs_automobile() {
        return this.is_automobile;
    }

    public void setIs_automobile(int i) {
        this.is_automobile = i;
    }

    public int getIs_motorcycle() {
        return this.is_motorcycle;
    }

    public void setIs_motorcycle(int i) {
        this.is_motorcycle = i;
    }

    public int getIs_dimple() {
        return this.is_dimple;
    }

    public void setIs_dimple(int i) {
        this.is_dimple = i;
    }

    public int getIs_tubular() {
        return this.is_tubular;
    }

    public void setIs_tubular(int i) {
        this.is_tubular = i;
    }

    public int getIs_standard() {
        return this.is_standard;
    }

    public void setIs_standard(int i) {
        this.is_standard = i;
    }

    public int getIs_automobile_chs() {
        return this.is_automobile_chs;
    }

    public void setIs_automobile_chs(int i) {
        this.is_automobile_chs = i;
    }

    public int getIs_visible() {
        return this.is_visible;
    }

    public void setIs_visible(int i) {
        this.is_visible = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public byte[] getManufacturerLogoImage() {
        return this.manufacturerLogoImage;
    }

    public void setManufacturerLogoImage(byte[] bArr) {
        this.manufacturerLogoImage = bArr;
    }

    public String getDistributorNum() {
        return this.distributorNum;
    }

    public void setDistributorNum(String str) {
        this.distributorNum = str;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(getManufacturerNameCN())) {
            return getManufacturerNameCN();
        }
        return getManufacturerName();
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
