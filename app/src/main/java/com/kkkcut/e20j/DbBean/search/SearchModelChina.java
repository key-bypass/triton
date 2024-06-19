package com.kkkcut.e20j.DbBean.search;

import com.kkkcut.e20j.DbBean.DaoSession;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class SearchModelChina {
    private transient DaoSession daoSession;
    long fK_ManufacturerID;
    int is_visible;
    long modelID;
    String modelName;
    String modelName_CN;
    private transient SearchModelChinaDao myDao;
    SearchManufacturer searchManufacturer;
    private transient Long searchManufacturer__resolvedKey;

    public SearchModelChina(long j, long j2, String str, String str2, int i) {
        this.modelID = j;
        this.fK_ManufacturerID = j2;
        this.modelName = str;
        this.modelName_CN = str2;
        this.is_visible = i;
    }

    public SearchModelChina() {
    }

    public long getModelID() {
        return this.modelID;
    }

    public void setModelID(long j) {
        this.modelID = j;
    }

    public long getFK_ManufacturerID() {
        return this.fK_ManufacturerID;
    }

    public void setFK_ManufacturerID(long j) {
        this.fK_ManufacturerID = j;
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

    public int getIs_visible() {
        return this.is_visible;
    }

    public void setIs_visible(int i) {
        this.is_visible = i;
    }

    public SearchManufacturer getSearchManufacturer() {
        long j = this.fK_ManufacturerID;
        Long l = this.searchManufacturer__resolvedKey;
        if (l == null || !l.equals(Long.valueOf(j))) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SearchManufacturer load = daoSession.getSearchManufacturerDao().load(Long.valueOf(j));
            synchronized (this) {
                this.searchManufacturer = load;
                this.searchManufacturer__resolvedKey = Long.valueOf(j);
            }
        }
        return this.searchManufacturer;
    }

    public void setSearchManufacturer(SearchManufacturer searchManufacturer) {
        if (searchManufacturer == null) {
            throw new DaoException("To-one property 'fK_ManufacturerID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.searchManufacturer = searchManufacturer;
            long manufacturerId = searchManufacturer.getManufacturerId();
            this.fK_ManufacturerID = manufacturerId;
            this.searchManufacturer__resolvedKey = Long.valueOf(manufacturerId);
        }
    }

    public void delete() {
        SearchModelChinaDao searchModelChinaDao = this.myDao;
        if (searchModelChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelChinaDao.delete(this);
    }

    public void refresh() {
        SearchModelChinaDao searchModelChinaDao = this.myDao;
        if (searchModelChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelChinaDao.refresh(this);
    }

    public void update() {
        SearchModelChinaDao searchModelChinaDao = this.myDao;
        if (searchModelChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelChinaDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSearchModelChinaDao() : null;
    }
}
