package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.DaoSession;
import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class KeyMarkingTemplate implements Serializable {
    private static final long serialVersionUID = -1087553993081659305L;
    private List<KeyMarkingChild> childrenBeanList;
    private transient DaoSession daoSession;
    private String description;
    private int height;
    private Long id;
    private int marginLeft;
    private int marginTop;
    private transient KeyMarkingTemplateDao myDao;
    private long time;
    private int width;

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

    public List<KeyMarkingChild> getChildrenBeanList() {
        if (this.childrenBeanList == null) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            List<KeyMarkingChild> _queryKeyMarkingTemplate_ChildrenBeanList = daoSession.getKeyMarkingChildDao()._queryKeyMarkingTemplate_ChildrenBeanList(this.id);
            synchronized (this) {
                if (this.childrenBeanList == null) {
                    this.childrenBeanList = _queryKeyMarkingTemplate_ChildrenBeanList;
                }
            }
        }
        return this.childrenBeanList;
    }

    public synchronized void resetChildrenBeanList() {
        this.childrenBeanList = null;
    }

    public void delete() {
        KeyMarkingTemplateDao keyMarkingTemplateDao = this.myDao;
        if (keyMarkingTemplateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyMarkingTemplateDao.delete(this);
    }

    public void refresh() {
        KeyMarkingTemplateDao keyMarkingTemplateDao = this.myDao;
        if (keyMarkingTemplateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyMarkingTemplateDao.refresh(this);
    }

    public void update() {
        KeyMarkingTemplateDao keyMarkingTemplateDao = this.myDao;
        if (keyMarkingTemplateDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyMarkingTemplateDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getKeyMarkingTemplateDao() : null;
    }
}
