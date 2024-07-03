package com.kkkcut.e20j.DbBean.china;

import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyInfoBase;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class ModelYearChina extends KeyInfoBase {
    int FK_ModelChinaID;
    private transient DaoSession daoSession;
    String description;
    String fromYear;
    int id;
    List<ModelSeriesChina> modelSeriesList;
    private transient ModelYearChinaDao myDao;
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

    public List<ModelSeriesChina> getModelSeriesList() {
        if (this.modelSeriesList == null) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            List<ModelSeriesChina> _queryModelYearChina_ModelSeriesList = daoSession.getModelSeriesChinaDao()._queryModelYearChina_ModelSeriesList(this.id);
            synchronized (this) {
                if (this.modelSeriesList == null) {
                    this.modelSeriesList = _queryModelYearChina_ModelSeriesList;
                }
            }
        }
        return this.modelSeriesList;
    }

    public synchronized void resetModelSeriesList() {
        this.modelSeriesList = null;
    }

    public void delete() {
        ModelYearChinaDao modelYearChinaDao = this.myDao;
        if (modelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelYearChinaDao.delete(this);
    }

    public void refresh() {
        ModelYearChinaDao modelYearChinaDao = this.myDao;
        if (modelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelYearChinaDao.refresh(this);
    }

    public void update() {
        ModelYearChinaDao modelYearChinaDao = this.myDao;
        if (modelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelYearChinaDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getModelYearChinaDao() : null;
    }
}
