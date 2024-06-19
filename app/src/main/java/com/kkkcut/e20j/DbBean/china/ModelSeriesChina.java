package com.kkkcut.e20j.DbBean.china;

import com.kkkcut.e20j.DbBean.ClampKeyBasicData;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.DbBean.KeyInfoBase;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class ModelSeriesChina extends KeyInfoBase {
    String DistributorNum;
    int FK_ModelYearChinaID;
    int ID;
    String KeyChinaNum;
    String Name;
    String Notes;
    String Remark;
    int Sort;
    ClampKeyBasicData clampKeyBasicData;
    private transient Long clampKeyBasicData__resolvedKey;
    String codeSeries;
    String cuts;
    private transient DaoSession daoSession;
    long fK_KeyID;
    KeyBasicData keyBasicData;
    private transient Long keyBasicData__resolvedKey;
    String modelNo;
    private transient ModelSeriesChinaDao myDao;

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

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
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

    public ClampKeyBasicData getClampKeyBasicData() {
        long j = this.fK_KeyID;
        Long l = this.clampKeyBasicData__resolvedKey;
        if (l == null || !l.equals(Long.valueOf(j))) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ClampKeyBasicData load = daoSession.getClampKeyBasicDataDao().load(Long.valueOf(j));
            synchronized (this) {
                this.clampKeyBasicData = load;
                this.clampKeyBasicData__resolvedKey = Long.valueOf(j);
            }
        }
        return this.clampKeyBasicData;
    }

    public void setClampKeyBasicData(ClampKeyBasicData clampKeyBasicData) {
        if (clampKeyBasicData == null) {
            throw new DaoException("To-one property 'fK_KeyID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.clampKeyBasicData = clampKeyBasicData;
            long longValue = clampKeyBasicData.getFK_KeyID().longValue();
            this.fK_KeyID = longValue;
            this.clampKeyBasicData__resolvedKey = Long.valueOf(longValue);
        }
    }

    public KeyBasicData getKeyBasicData() {
        long j = this.fK_KeyID;
        Long l = this.keyBasicData__resolvedKey;
        if (l == null || !l.equals(Long.valueOf(j))) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBasicData load = daoSession.getKeyBasicDataDao().load(Long.valueOf(j));
            synchronized (this) {
                this.keyBasicData = load;
                this.keyBasicData__resolvedKey = Long.valueOf(j);
            }
        }
        return this.keyBasicData;
    }

    public void setKeyBasicData(KeyBasicData keyBasicData) {
        if (keyBasicData == null) {
            throw new DaoException("To-one property 'fK_KeyID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyBasicData = keyBasicData;
            long icCard = keyBasicData.getIcCard();
            this.fK_KeyID = icCard;
            this.keyBasicData__resolvedKey = Long.valueOf(icCard);
        }
    }

    public void delete() {
        ModelSeriesChinaDao modelSeriesChinaDao = this.myDao;
        if (modelSeriesChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelSeriesChinaDao.delete(this);
    }

    public void refresh() {
        ModelSeriesChinaDao modelSeriesChinaDao = this.myDao;
        if (modelSeriesChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelSeriesChinaDao.refresh(this);
    }

    public void update() {
        ModelSeriesChinaDao modelSeriesChinaDao = this.myDao;
        if (modelSeriesChinaDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        modelSeriesChinaDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getModelSeriesChinaDao() : null;
    }
}
