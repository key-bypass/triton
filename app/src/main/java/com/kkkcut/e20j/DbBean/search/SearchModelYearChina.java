package com.kkkcut.e20j.DbBean.search;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
/* loaded from: classes.dex */
public class SearchModelYearChina {
    long FK_ModelChinaID;
    String description;
    String fromYear;
    @Id
    long id;

    int sort;
    String toYear;

    @Generated(hash = 1730455243)
    public SearchModelYearChina(long FK_ModelChinaID, String description,
                                String fromYear, long id, int sort, String toYear) {
        this.FK_ModelChinaID = FK_ModelChinaID;
        this.description = description;
        this.fromYear = fromYear;
        this.id = id;
        this.sort = sort;
        this.toYear = toYear;
    }

    @Generated(hash = 866993883)
    public SearchModelYearChina() {
    }
    public long getFK_ModelChinaID() {
        return this.FK_ModelChinaID;
    }

    public void setFK_ModelChinaID(long FK_ModelChinaID) {
        this.FK_ModelChinaID = FK_ModelChinaID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getFromYear() {
        return this.fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public int getSort() {
        return this.sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getToYear() {
        return this.toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

}
