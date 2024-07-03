package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

@Entity
/* loaded from: classes.dex */
public class KeyblankItem {
    String Description;

    long FK_KeyblankID;

    @Id
    long ID;

    String KeyblankItemName;

    @ToOne(joinProperty = "FK_KeyblankID")
    KeyBlank keyBlank;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 376584636)
    private transient KeyblankItemDao myDao;

    @Generated(hash = 244294253)
    public KeyblankItem(String Description, long FK_KeyblankID, long ID,
            String KeyblankItemName) {
        this.Description = Description;
        this.FK_KeyblankID = FK_KeyblankID;
        this.ID = ID;
        this.KeyblankItemName = KeyblankItemName;
    }

    @Generated(hash = 1508404796)
    public KeyblankItem() {
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public long getFK_KeyblankID() {
        return this.FK_KeyblankID;
    }

    public void setFK_KeyblankID(long FK_KeyblankID) {
        this.FK_KeyblankID = FK_KeyblankID;
    }

    public long getID() {
        return this.ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getKeyblankItemName() {
        return this.KeyblankItemName;
    }

    public void setKeyblankItemName(String KeyblankItemName) {
        this.KeyblankItemName = KeyblankItemName;
    }

    @Generated(hash = 763468264)
    private transient Long keyBlank__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 475520919)
    public KeyBlank getKeyBlank() {
        long __key = this.FK_KeyblankID;
        if (keyBlank__resolvedKey == null || !keyBlank__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBlankDao targetDao = daoSession.getKeyBlankDao();
            KeyBlank keyBlankNew = targetDao.load(__key);
            synchronized (this) {
                keyBlank = keyBlankNew;
                keyBlank__resolvedKey = __key;
            }
        }
        return keyBlank;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1466182844)
    public void setKeyBlank(@NotNull KeyBlank keyBlank) {
        if (keyBlank == null) {
            throw new DaoException(
                    "To-one property 'FK_KeyblankID' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.keyBlank = keyBlank;
            FK_KeyblankID = keyBlank.getID();
            keyBlank__resolvedKey = FK_KeyblankID;
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
    @Generated(hash = 1240916949)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKeyblankItemDao() : null;
    }
}
