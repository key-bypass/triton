package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class KeyBlankItemBasicData {
    Long FK_KeyBlankItemID;
    long FK_KeyID;
    int ID;
    private transient DaoSession daoSession;
    KeyblankItem keyblankItem;
    private transient Long keyblankItem__resolvedKey;
    private transient KeyBlankItemBasicDataDao myDao;

    public KeyBlankItemBasicData(int i, Long l, long j) {
        this.ID = i;
        this.FK_KeyBlankItemID = l;
        this.FK_KeyID = j;
    }

    public KeyBlankItemBasicData() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public Long getFK_KeyBlankItemID() {
        return this.FK_KeyBlankItemID;
    }

    public void setFK_KeyBlankItemID(Long l) {
        this.FK_KeyBlankItemID = l;
    }

    public long getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(long j) {
        this.FK_KeyID = j;
    }

    public KeyblankItem getKeyblankItem() {
        Long l = this.FK_KeyBlankItemID;
        Long l2 = this.keyblankItem__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyblankItem load = daoSession.getKeyblankItemDao().load(l);
            synchronized (this) {
                this.keyblankItem = load;
                this.keyblankItem__resolvedKey = l;
            }
        }
        return this.keyblankItem;
    }

    public void setKeyblankItem(KeyblankItem keyblankItem) {
        synchronized (this) {
            this.keyblankItem = keyblankItem;
            Long valueOf = keyblankItem == null ? null : Long.valueOf(keyblankItem.getID());
            this.FK_KeyBlankItemID = valueOf;
            this.keyblankItem__resolvedKey = valueOf;
        }
    }

    public void delete() {
        KeyBlankItemBasicDataDao keyBlankItemBasicDataDao = this.myDao;
        if (keyBlankItemBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBlankItemBasicDataDao.delete(this);
    }

    public void refresh() {
        KeyBlankItemBasicDataDao keyBlankItemBasicDataDao = this.myDao;
        if (keyBlankItemBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBlankItemBasicDataDao.refresh(this);
    }

    public void update() {
        KeyBlankItemBasicDataDao keyBlankItemBasicDataDao = this.myDao;
        if (keyBlankItemBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBlankItemBasicDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getKeyBlankItemBasicDataDao() : null;
    }
}
