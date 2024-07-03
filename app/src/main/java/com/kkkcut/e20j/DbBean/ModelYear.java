package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ModelYear extends KeyInfoBase {
    String description;
    int fK_ModelID;
    String fromYear;
    int modelYearID;
    int sort;
    String toYear;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.fromYear;
    }

    public ModelYear(int i, int i2, String str, String str2, int i3, String str3) {
        this.modelYearID = i;
        this.fK_ModelID = i2;
        this.fromYear = str;
        this.toYear = str2;
        this.sort = i3;
        this.description = str3;
    }

    public ModelYear() {
    }

    @Generated(hash = 295269605)
    public ModelYear(String description, int fK_ModelID, String fromYear, int modelYearID,
            int sort, String toYear) {
        this.description = description;
        this.fK_ModelID = fK_ModelID;
        this.fromYear = fromYear;
        this.modelYearID = modelYearID;
        this.sort = sort;
        this.toYear = toYear;
    }

    public int getModelYearID() {
        return this.modelYearID;
    }

    public void setModelYearID(int i) {
        this.modelYearID = i;
    }

    public int getFK_ModelID() {
        return this.fK_ModelID;
    }

    public void setFK_ModelID(int i) {
        this.fK_ModelID = i;
    }

    public String getFromYear() {
        return this.fromYear;
    }

    public void setFromYear(String str) {
        this.fromYear = str;
    }

    public String getToYear() {
        return this.toYear;
    }

    public void setToYear(String str) {
        this.toYear = str;
    }

    public int getSort() {
        return this.sort;
    }

    public void setSort(int i) {
        this.sort = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
