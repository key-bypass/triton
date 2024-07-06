package com.kkkcut.e20j.DbBean.china;

import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyInfoBase;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class ModelYearChina extends KeyInfoBase {
    private transient DaoSession daoSession;

    int FK_ModelChinaID;
    String description;
    String fromYear;

    @Id
    int id;

@Transient
    List<ModelSeriesChina> modelSeriesList;

    int sort;
    String toYear;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    public ModelYearChina(int i, int i2, String str, String str2, int i3, String str3) {
        this.id = i;
        this.FK_ModelChinaID = i2;
        this.fromYear = str;
        this.toYear = str2;
        this.sort = i3;
        this.description = str3;
    }

    public ModelYearChina() {
    }

    @Generated(hash = 239207740)
    public ModelYearChina(int FK_ModelChinaID, String description, String fromYear, int id,
            int sort, String toYear) {
        this.FK_ModelChinaID = FK_ModelChinaID;
        this.description = description;
        this.fromYear = fromYear;
        this.id = id;
        this.sort = sort;
        this.toYear = toYear;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getFK_ModelChinaID() {
        return this.FK_ModelChinaID;
    }

    public void setFK_ModelChinaID(int i) {
        this.FK_ModelChinaID = i;
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



    public synchronized void resetModelSeriesList() {
        this.modelSeriesList = null;
    }

}