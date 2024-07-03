package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.DaoSession;
import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class KeyMarkingTemplate implements Serializable {
    private static final long serialVersionUID = -1087553993081659305L;

    @ToMany(referencedJoinProperty = "parentId")
    private List<KeyMarkingChild> childrenBeanList;

    private String description;

    private int height;

    /* renamed from: id */
    @Id
    private Long id;
    private int marginLeft;
    private int marginTop;
    private long time;
    private int width;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1731901055)
    private transient KeyMarkingTemplateDao myDao;

    public KeyMarkingTemplate(Long l, int i, int i2, int i3, int i4, String str, long j) {
        this.id = l;
        this.width = i;
        this.height = i2;
        this.marginLeft = i3;
        this.marginTop = i4;
        this.description = str;
        this.time = j;
    }

    public KeyMarkingTemplate() {
    }

    @Generated(hash = 546699504)
    public KeyMarkingTemplate(String description, int height, Long id, int marginLeft,
            int marginTop, long time, int width) {
        this.description = description;
        this.height = height;
        this.id = id;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.time = time;
        this.width = width;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public void setMarginLeft(int i) {
        this.marginLeft = i;
    }

    public int getMarginTop() {
        return this.marginTop;
    }

    public void setMarginTop(int i) {
        this.marginTop = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1429138806)
    public List<KeyMarkingChild> getChildrenBeanList() {
        if (childrenBeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyMarkingChildDao targetDao = daoSession.getKeyMarkingChildDao();
            List<KeyMarkingChild> childrenBeanListNew = targetDao
                    ._queryKeyMarkingTemplate_ChildrenBeanList(id);
            synchronized (this) {
                if (childrenBeanList == null) {
                    childrenBeanList = childrenBeanListNew;
                }
            }
        }
        return childrenBeanList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2107861997)
    public synchronized void resetChildrenBeanList() {
        childrenBeanList = null;
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
    @Generated(hash = 1659949934)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKeyMarkingTemplateDao() : null;
    }
}