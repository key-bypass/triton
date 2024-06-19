package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class KeyblankItem {
    String Description;
    long FK_KeyblankID;
    long ID;
    String KeyblankItemName;
    private transient DaoSession daoSession;
    KeyBlank keyBlank;
    private transient Long keyBlank__resolvedKey;
    private transient KeyblankItemDao myDao;

    public KeyblankItem(long j, String str, String str2, long j2) {
        this.ID = j;
        this.KeyblankItemName = str;
        this.Description = str2;
        this.FK_KeyblankID = j2;
    }

    public KeyblankItem() {
    }

    public long getID() {
        return this.ID;
    }

    public void setID(long j) {
        this.ID = j;
    }

    public String getKeyblankItemName() {
        return this.KeyblankItemName;
    }

    public void setKeyblankItemName(String str) {
        this.KeyblankItemName = str;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String str) {
        this.Description = str;
    }

    public long getFK_KeyblankID() {
        return this.FK_KeyblankID;
    }

    public void setFK_KeyblankID(long j) {
        this.FK_KeyblankID = j;
    }

    public KeyBlank getKeyBlank() {
        long j = this.FK_KeyblankID;
        Long l = this.keyBlank__resolvedKey;
        if (l == null || !l.equals(Long.valueOf(j))) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBlank load = daoSession.getKeyBlankDao().load(Long.valueOf(j));
            synchronized (this) {
                this.keyBlank = load;
                this.keyBlank__resolvedKey = Long.valueOf(j);
            }
        }
        return this.keyBlank;
    }

    public void setKeyBlank(KeyBlank keyBlank) {
        if (keyBlank == null) {
            throw new DaoException("To-one property 'FK_KeyblankID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyBlank = keyBlank;
            long id = keyBlank.getID();
            this.FK_KeyblankID = id;
            this.keyBlank__resolvedKey = Long.valueOf(id);
        }
    }

    public void delete() {
        KeyblankItemDao keyblankItemDao = this.myDao;
        if (keyblankItemDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyblankItemDao.delete(this);
    }

    public void refresh() {
        KeyblankItemDao keyblankItemDao = this.myDao;
        if (keyblankItemDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyblankItemDao.refresh(this);
    }

    public void update() {
        KeyblankItemDao keyblankItemDao = this.myDao;
        if (keyblankItemDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyblankItemDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getKeyblankItemDao() : null;
    }
}
