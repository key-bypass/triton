package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class KeyBasicData extends KeyInfoBase {
    String Description;
    int KeyTypeItemID;
    int align;

    @ToOne
    ClampKeyBasicData clampKeyBasicData;

    @JoinEntity(entity = KeyBlankItemBasicData.class, sourceProperty = "FK_KeyBlankItemID", targetProperty = "FK_KeyID")
    @ToMany
    List<KeyBasicDataItem> blankList;

    @Id
    int id;

    String depth;
    String depth_name;
    int face;
    long icCard;
    int length;

    String parameter_info;
    int row_count;
    String row_pos;
    String space;
    String space_width;
    int thick;
    int type;
    int width;

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

        /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1704098059)
    private transient KeyBasicDataDao myDao;
    @Generated(hash = 1082332757)
    public KeyBasicData(String Description, int KeyTypeItemID, int align, int id, String depth, String depth_name, int face,
            long icCard, int length, String parameter_info, int row_count, String row_pos, String space, String space_width,
            int thick, int type, int width) {
        this.Description = Description;
        this.KeyTypeItemID = KeyTypeItemID;
        this.align = align;
        this.id = id;
        this.depth = depth;
        this.depth_name = depth_name;
        this.face = face;
        this.icCard = icCard;
        this.length = length;
        this.parameter_info = parameter_info;
        this.row_count = row_count;
        this.row_pos = row_pos;
        this.space = space;
        this.space_width = space_width;
        this.thick = thick;
        this.type = type;
        this.width = width;
    }
    @Generated(hash = 1538307687)
    public KeyBasicData() {
    }
    public String getDescription() {
        return this.Description;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
    public int getKeyTypeItemID() {
        return this.KeyTypeItemID;
    }
    public void setKeyTypeItemID(int KeyTypeItemID) {
        this.KeyTypeItemID = KeyTypeItemID;
    }
    public int getAlign() {
        return this.align;
    }
    public void setAlign(int align) {
        this.align = align;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDepth() {
        return this.depth;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public String getDepth_name() {
        return this.depth_name;
    }
    public void setDepth_name(String depth_name) {
        this.depth_name = depth_name;
    }
    public int getFace() {
        return this.face;
    }
    public void setFace(int face) {
        this.face = face;
    }
    public long getIcCard() {
        return this.icCard;
    }
    public void setIcCard(long icCard) {
        this.icCard = icCard;
    }
    public int getLength() {
        return this.length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getParameter_info() {
        return this.parameter_info;
    }
    public void setParameter_info(String parameter_info) {
        this.parameter_info = parameter_info;
    }
    public int getRow_count() {
        return this.row_count;
    }
    public void setRow_count(int row_count) {
        this.row_count = row_count;
    }
    public String getRow_pos() {
        return this.row_pos;
    }
    public void setRow_pos(String row_pos) {
        this.row_pos = row_pos;
    }
    public String getSpace() {
        return this.space;
    }
    public void setSpace(String space) {
        this.space = space;
    }
    public String getSpace_width() {
        return this.space_width;
    }
    public void setSpace_width(String space_width) {
        this.space_width = space_width;
    }
    public int getThick() {
        return this.thick;
    }
    public void setThick(int thick) {
        this.thick = thick;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getWidth() {
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    @Generated(hash = 818317824)
    private transient boolean clampKeyBasicData__refreshed;
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
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1561021379)
    public List<KeyBasicDataItem> getBlankList() {
        if (blankList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            KeyBasicDataItemDao targetDao = daoSession.getKeyBasicDataItemDao();
            List<KeyBasicDataItem> blankListNew = targetDao._queryKeyBasicData_BlankList(id);
            synchronized (this) {
                if (blankList == null) {
                    blankList = blankListNew;
                }
            }
        }
        return blankList;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 250161134)
    public synchronized void resetBlankList() {
        blankList = null;
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
    @Generated(hash = 1174473666)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getKeyBasicDataDao() : null;
    }

}