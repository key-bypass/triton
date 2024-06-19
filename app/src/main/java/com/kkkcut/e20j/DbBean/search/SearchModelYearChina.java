package com.kkkcut.e20j.DbBean.search;

import com.kkkcut.e20j.DbBean.DaoSession;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class SearchModelYearChina {
    long FK_ModelChinaID;
    private transient DaoSession daoSession;
    String description;
    String fromYear;
    long id;
    private transient SearchModelYearChinaDao myDao;
    SearchModelChina searchModelChina;
    private transient Long searchModelChina__resolvedKey;
    int sort;
    String toYear;

    public SearchModelYearChina(long j, long j2, String str, String str2, int i, String str3) {
        this.id = j;
        this.FK_ModelChinaID = j2;
        this.fromYear = str;
        this.toYear = str2;
        this.sort = i;
        this.description = str3;
    }

    public SearchModelYearChina() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getFK_ModelChinaID() {
        return this.FK_ModelChinaID;
    }

    public void setFK_ModelChinaID(long j) {
        this.FK_ModelChinaID = j;
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

    public SearchModelChina getSearchModelChina() {
        long j = this.FK_ModelChinaID;
        Long l = this.searchModelChina__resolvedKey;
        if (l == null || !l.equals(Long.valueOf(j))) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SearchModelChina load = daoSession.getSearchModelChinaDao().load(Long.valueOf(j));
            synchronized (this) {
                this.searchModelChina = load;
                this.searchModelChina__resolvedKey = Long.valueOf(j);
            }
        }
        return this.searchModelChina;
    }

    public void setSearchModelChina(SearchModelChina searchModelChina) {
        if (searchModelChina == null) {
            throw new DaoException("To-one property 'FK_ModelChinaID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.searchModelChina = searchModelChina;
            long modelID = searchModelChina.getModelID();
            this.FK_ModelChinaID = modelID;
            this.searchModelChina__resolvedKey = Long.valueOf(modelID);
        }
    }

    public void delete() {
        SearchModelYearChinaDao searchModelYearChinaDao = this.myDao;
        if (searchModelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelYearChinaDao.delete(this);
    }

    public void refresh() {
        SearchModelYearChinaDao searchModelYearChinaDao = this.myDao;
        if (searchModelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelYearChinaDao.refresh(this);
    }

    public void update() {
        SearchModelYearChinaDao searchModelYearChinaDao = this.myDao;
        if (searchModelYearChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        searchModelYearChinaDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSearchModelYearChinaDao() : null;
    }
}
