package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.KeyBasicData;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyBasicDataDao;

import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class CollectionData {
    String KeyChinaNum;
    int basicDataID;
    String codeSeries;
    String cuts;
    @Id
    Long id;

    @ToOne(joinProperty = "basicDataID")
    KeyBasicData keyBasicData;

    String remark;
    int seriesID;
    long time;
    String title;
    String toothCode;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1456748428)
    private transient CollectionDataDao myDao;
    @Generated(hash = 1488740074)
    public CollectionData(String KeyChinaNum, int basicDataID, String codeSeries,
            String cuts, Long id, String remark, int seriesID, long time,
            String title, String toothCode) {
        this.KeyChinaNum = KeyChinaNum;
        this.basicDataID = basicDataID;
        this.codeSeries = codeSeries;
        this.cuts = cuts;
        this.id = id;
        this.remark = remark;
        this.seriesID = seriesID;
        this.time = time;
        this.title = title;
        this.toothCode = toothCode;
    }
    @Generated(hash = 1584283732)
    public CollectionData() {
    }
    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }
    public void setKeyChinaNum(String KeyChinaNum) {
        this.KeyChinaNum = KeyChinaNum;
    }
    public int getBasicDataID() {
        return this.basicDataID;
    }
    public void setBasicDataID(int basicDataID) {
        this.basicDataID = basicDataID;
    }
    public String getCodeSeries() {
        return this.codeSeries;
    }
    public void setCodeSeries(String codeSeries) {
        this.codeSeries = codeSeries;
    }
    public String getCuts() {
        return this.cuts;
    }
    public void setCuts(String cuts) {
        this.cuts = cuts;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getSeriesID() {
        return this.seriesID;
    }
    public void setSeriesID(int seriesID) {
        this.seriesID = seriesID;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getToothCode() {
        return this.toothCode;
    }
    public void setToothCode(String toothCode) {
        this.toothCode = toothCode;
    }
    @Generated(hash = 963364568)
    private transient Integer keyBasicData__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1514792297)
    public KeyBasicData getKeyBasicData() {
        int __key = this.basicDataID;
        if (keyBasicData__resolvedKey == null
                || !keyBasicData__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBasicDataDao targetDao = daoSession.getKeyBasicDataDao();
            KeyBasicData keyBasicDataNew = targetDao.load(__key);
            synchronized (this) {
                keyBasicData = keyBasicDataNew;
                keyBasicData__resolvedKey = __key;
            }
        }
        return keyBasicData;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1027691891)
    public void setKeyBasicData(@NotNull KeyBasicData keyBasicData) {
        if (keyBasicData == null) {
            throw new DaoException(
                    "To-one property 'basicDataID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyBasicData = keyBasicData;
            basicDataID = keyBasicData.getId();
            keyBasicData__resolvedKey = basicDataID;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 426104141)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCollectionDataDao() : null;
    }

}
