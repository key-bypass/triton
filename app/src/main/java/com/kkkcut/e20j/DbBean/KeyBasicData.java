package com.kkkcut.e20j.DbBean;

import com.kkkcut.e20j.utils.DesUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.cutting.machine.bean.ClampBean;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.utils.KeyDataUtils;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class KeyBasicData extends KeyInfoBase {
    String Description;

    int KeyTypeItemID;
    int align;

    @Transient
    List<KeyBlankItemBasicData> blankList;

    @ToOne
    ClampKeyBasicData clampKeyBasicData;

    String depth;
    String depth_name;
    int face;
    @Id
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

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1704098059)
    private transient KeyBasicDataDao myDao;

    @Generated(hash = 818317824)
    private transient boolean clampKeyBasicData__refreshed;

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    public KeyInfo toKeyInfo() {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setIcCard((int) getIcCard());
        long j = this.icCard;
        if (j == 1311 || j == 1372) {
            keyInfo.setType(3);
        } else {
            keyInfo.setType(getType());
        }
        keyInfo.setAlign(getAlign());
        keyInfo.setWidth(getWidth());
        keyInfo.setThick(getThick());
        keyInfo.setLength(getLength());
        keyInfo.setRowCount(getRow_count());
        try {
            String trim = DesUtil.decrypt(getRow_pos(), DesUtil.DATABASE).trim();
            if (trim.contains("-")) {
                trim = trim.replace("-", "");
            }
            keyInfo.setRow_pos(trim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            keyInfo.setSpaceStr(DesUtil.decrypt(getSpace(), DesUtil.DATABASE).trim());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            keyInfo.setSpaceWidthStr(KeyDataUtils.fillSpaceWidth(this.type, keyInfo.getSpaceStr(), DesUtil.decrypt(getSpace_width(), DesUtil.DATABASE)).trim());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            keyInfo.setDepthStr(DesUtil.decrypt(getDepth(), DesUtil.DATABASE).trim());
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            keyInfo.setDepthName(KeyDataUtils.fillNoneDepthNameData(keyInfo.getDepthStr(), DesUtil.decrypt(getDepth_name(), DesUtil.DATABASE)).trim());
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            String decrypt = DesUtil.decrypt(getParameter_info(), DesUtil.DATABASE);
            keyInfo.setType_specific_info(decrypt.trim());
            KeyDataUtils.parseKeySpecificInfo(keyInfo, decrypt);
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        keyInfo.setFace(getFace());
        keyInfo.setKeyBlanks(blankListToStr(keyInfo, getBlankList()));
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum(getClampKeyBasicData().getClampNum());
        clampBean.setClampSide(getClampKeyBasicData().getClampSide());
        clampBean.setClampSlot(getClampKeyBasicData().getClampSlot());
        keyInfo.setClampKeyBasicData(clampBean);
        keyInfo.setDesc(getDescription());
        return keyInfo;
    }

    public String blankListToStr(KeyInfo keyInfo, List<KeyBlankItemBasicData> list) {
        KeyBlank keyBlank;
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<KeyBlankItemBasicData> it = list.iterator();
        while (it.hasNext()) {
            KeyblankItem keyblankItem = it.next().getKeyblankItem();
            if (keyblankItem != null && (keyBlank = keyblankItem.getKeyBlank()) != null) {
                String keyBlankName = keyBlank.getKeyBlankName();
                if (hashMap.containsKey(keyBlankName)) {
                    hashMap.put(keyBlankName, hashMap.get(keyBlankName) + keyblankItem.getKeyblankItemName() + ",");
                } else {
                    hashMap.put(keyBlankName, keyblankItem.getKeyblankItemName() + ",");
                }
            }
        }
        keyInfo.setKeyBlankMap(hashMap);
        String str = "";
        for (String str2 : hashMap.keySet()) {
            var r8 = hashMap.get(str2);
            str = ((str + str2 + ":") + r8).substring(0, r8.length() - 1) + "\n";
        }
        return str;
    }

    public KeyBasicData(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.icCard = j;
        this.KeyTypeItemID = i;
        this.type = i2;
        this.align = i3;
        this.width = i4;
        this.thick = i5;
        this.length = i6;
        this.row_count = i7;
        this.face = i8;
        this.row_pos = str;
        this.space = str2;
        this.space_width = str3;
        this.depth = str4;
        this.depth_name = str5;
        this.parameter_info = str6;
        this.Description = str7;
    }

    public KeyBasicData() {
    }

    @Generated(hash = 265575828)
    public KeyBasicData(String Description, int KeyTypeItemID, int align, String depth, String depth_name, int face, long icCard, int length, String parameter_info, int row_count,
            String row_pos, String space, String space_width, int thick, int type, int width) {
        this.Description = Description;
        this.KeyTypeItemID = KeyTypeItemID;
        this.align = align;
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

    public long getIcCard() {
        return this.icCard;
    }

    public void setIcCard(long j) {
        this.icCard = j;
    }

    public int getKeyTypeItemID() {
        return this.KeyTypeItemID;
    }

    public void setKeyTypeItemID(int i) {
        this.KeyTypeItemID = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getAlign() {
        return this.align;
    }

    public void setAlign(int i) {
        this.align = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getThick() {
        return this.thick;
    }

    public void setThick(int i) {
        this.thick = i;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public int getRow_count() {
        return this.row_count;
    }

    public void setRow_count(int i) {
        this.row_count = i;
    }

    public int getFace() {
        return this.face;
    }

    public void setFace(int i) {
        this.face = i;
    }

    public String getRow_pos() {
        return this.row_pos;
    }

    public void setRow_pos(String str) {
        this.row_pos = str;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String str) {
        this.space = str;
    }

    public String getSpace_width() {
        return this.space_width;
    }

    public void setSpace_width(String str) {
        this.space_width = str;
    }

    public String getDepth() {
        return this.depth;
    }

    public void setDepth(String str) {
        this.depth = str;
    }

    public String getDepth_name() {
        return this.depth_name;
    }

    public void setDepth_name(String str) {
        this.depth_name = str;
    }

    public String getParameter_info() {
        return this.parameter_info;
    }

    public void setParameter_info(String str) {
        this.parameter_info = str;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String str) {
        this.Description = str;
    }





    public List<KeyBlankItemBasicData> getBlankList() {
//        if (this.blankList == null) {
//            DaoSession daoSession = this.daoSession;
//            if (daoSession == null) {
//                throw new DaoException("Entity is detached from DAO context");
//            }
//            List<KeyBlankItemBasicData> _queryKeyBasicData_BlankList = daoSession.getKeyBlankItemBasicDataDao()._queryKeyBasicData_BlankList(this.icCard);
//            synchronized (this) {
//                if (this.blankList == null) {
//                    this.blankList = _queryKeyBasicData_BlankList;
//                }
//            }
//        }
        return this.blankList;

    }

    public synchronized void resetBlankList() {
        this.blankList = null;
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