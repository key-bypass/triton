package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import retrofit2.http.Field;

@Entity
public class KeyBlankItemBasicData {

    long FK_KeyBlankItemID;

    long FK_KeyID;

    @Id
    int ID;

    @ToOne(joinProperty = "FK_KeyBlankItemID")
    KeyblankItem keyblankItem;

    @ToOne(joinProperty = "FK_KeyID")
    KeyBasicDataItem key;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 527326114)
    private transient KeyBlankItemBasicDataDao myDao;

    @Generated(hash = 1747299506)
    public KeyBlankItemBasicData(long FK_KeyBlankItemID, long FK_KeyID, int ID) {
        this.FK_KeyBlankItemID = FK_KeyBlankItemID;
        this.FK_KeyID = FK_KeyID;
        this.ID = ID;
    }

    @Generated(hash = 1986566679)
    public KeyBlankItemBasicData() {
    }

    @Generated(hash = 643277687)
    private transient Long keyblankItem__resolvedKey;

    @Generated(hash = 1458909973)
    private transient Long key__resolvedKey;

    public long getFK_KeyBlankItemID() {
        return this.FK_KeyBlankItemID;
    }

    public void setFK_KeyBlankItemID(long FK_KeyBlankItemID) {
        this.FK_KeyBlankItemID = FK_KeyBlankItemID;
    }

    public long getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(long FK_KeyID) {
        this.FK_KeyID = FK_KeyID;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1802019480)
    public KeyblankItem getKeyblankItem() {
        long __key = this.FK_KeyBlankItemID;
        if (keyblankItem__resolvedKey == null
                || !keyblankItem__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyblankItemDao targetDao = daoSession.getKeyblankItemDao();
            KeyblankItem keyblankItemNew = targetDao.load(__key);
            synchronized (this) {
                keyblankItem = keyblankItemNew;
                keyblankItem__resolvedKey = __key;
            }
        }
        return keyblankItem;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 44175678)
    public void setKeyblankItem(@NotNull KeyblankItem keyblankItem) {
        if (keyblankItem == null) {
            throw new DaoException(
                    "To-one property 'FK_KeyBlankItemID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyblankItem = keyblankItem;
            FK_KeyBlankItemID = keyblankItem.getID();
            keyblankItem__resolvedKey = FK_KeyBlankItemID;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1574579871)
    public KeyBasicDataItem getKey() {
        long __key = this.FK_KeyID;
        if (key__resolvedKey == null || !key__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBasicDataItemDao targetDao = daoSession.getKeyBasicDataItemDao();
            KeyBasicDataItem keyNew = targetDao.load(__key);
            synchronized (this) {
                key = keyNew;
                key__resolvedKey = __key;
            }
        }
        return key;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1150400466)
    public void setKey(@NotNull KeyBasicDataItem key) {
        if (key == null) {
            throw new DaoException(
                    "To-one property 'FK_KeyID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.key = key;
            FK_KeyID = key.getId();
            key__resolvedKey = FK_KeyID;
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
    @Generated(hash = 1359374103)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKeyBlankItemBasicDataDao() : null;
    }


}
