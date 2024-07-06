package com.kkkcut.e20j.DbBean.china;

import com.kkkcut.e20j.DbBean.ClampKeyBasicData;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.DbBean.KeyInfoBase;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import com.kkkcut.e20j.DbBean.KeyBasicDataDao;
import com.kkkcut.e20j.DbBean.ClampKeyBasicDataDao;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class ModelSeriesChina extends KeyInfoBase {
    String DistributorNum;
    int FK_ModelYearChinaID;

    @Id
    public int ID;
    public String KeyChinaNum;
    String Name;
    String Notes;
    String Remark;
    int Sort;

    @ToOne
    ClampKeyBasicData clampKeyBasicData;

    String codeSeries;
    String cuts;

    long fK_KeyID;

    @ToOne(joinProperty = "fK_KeyID")
    KeyBasicData keyBasicData;

    String modelNo;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1219043610)
    private transient ModelSeriesChinaDao myDao;
    @Generated(hash = 818317824)
    private transient boolean clampKeyBasicData__refreshed;
    @Generated(hash = 1236923286)
    private transient Long keyBasicData__resolvedKey;


    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    public ModelSeriesChina(int i, int i2, String str, String str2, String str3, long j, String str4, String str5, int i3, String str6, String str7, String str8) {
        this.ID = i;
        this.FK_ModelYearChinaID = i2;
        this.Name = str;
        this.codeSeries = str2;
        this.modelNo = str3;
        this.fK_KeyID = j;
        this.cuts = str4;
        this.Notes = str5;
        this.Sort = i3;
        this.Remark = str6;
        this.KeyChinaNum = str7;
        this.DistributorNum = str8;
    }

    public ModelSeriesChina() {
    }

    @Generated(hash = 1937844480)
    public ModelSeriesChina(String DistributorNum, int FK_ModelYearChinaID, int ID, String KeyChinaNum, String Name, String Notes, String Remark, int Sort,
            String codeSeries, String cuts, long fK_KeyID, String modelNo) {
        this.DistributorNum = DistributorNum;
        this.FK_ModelYearChinaID = FK_ModelYearChinaID;
        this.ID = ID;
        this.KeyChinaNum = KeyChinaNum;
        this.Name = Name;
        this.Notes = Notes;
        this.Remark = Remark;
        this.Sort = Sort;
        this.codeSeries = codeSeries;
        this.cuts = cuts;
        this.fK_KeyID = fK_KeyID;
        this.modelNo = modelNo;
    }



    public int getFK_ModelYearChinaID() {
        return this.FK_ModelYearChinaID;
    }

    public void setFK_ModelYearChinaID(int i) {
        this.FK_ModelYearChinaID = i;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getModelNo() {
        return this.modelNo;
    }

    public void setModelNo(String str) {
        this.modelNo = str;
    }

    public long getFK_KeyID() {
        return this.fK_KeyID;
    }

    public void setFK_KeyID(long j) {
        this.fK_KeyID = j;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setNotes(String str) {
        this.Notes = str;
    }

    public int getSort() {
        return this.Sort;
    }

    public void setSort(int i) {
        this.Sort = i;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setRemark(String str) {
        this.Remark = str;
    }

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public void setKeyChinaNum(String str) {
        this.KeyChinaNum = str;
    }

    public String getDistributorNum() {
        return this.DistributorNum;
    }

    public void setDistributorNum(String str) {
        this.DistributorNum = str;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1198323384)
    public ClampKeyBasicData getClampKeyBasicData() {
        if (clampKeyBasicData != null || !clampKeyBasicData__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClampKeyBasicDataDao targetDao = daoSession.getClampKeyBasicDataDao();
            targetDao.refresh(clampKeyBasicData);
            clampKeyBasicData__refreshed = true;
        }
        return clampKeyBasicData;
    }

    /** To-one relationship, returned entity is not refreshed and may carry only the PK property. */
    @Generated(hash = 1129108540)
    public ClampKeyBasicData peakClampKeyBasicData() {
        return clampKeyBasicData;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1557364244)
    public void setClampKeyBasicData(ClampKeyBasicData clampKeyBasicData) {
        synchronized (this) {
            this.clampKeyBasicData = clampKeyBasicData;
            clampKeyBasicData__refreshed = true;
        }
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 860865353)
    public void setKeyBasicData(@NotNull KeyBasicData keyBasicData) {
        if (keyBasicData == null) {
            throw new DaoException("To-one property 'fK_KeyID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyBasicData = keyBasicData;
            fK_KeyID = keyBasicData.getIcCard();
            keyBasicData__resolvedKey = fK_KeyID;
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
    @Generated(hash = 1390176530)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getModelSeriesChinaDao() : null;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 809307682)
    public KeyBasicData getKeyBasicData() {
        long __key = this.fK_KeyID;
        if (keyBasicData__resolvedKey == null || !keyBasicData__resolvedKey.equals(__key)) {
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


}