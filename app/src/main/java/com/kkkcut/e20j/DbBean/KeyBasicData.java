package com.kkkcut.e20j.DbBean;

import com.kkkcut.e20j.utils.DesUtil;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.utils.KeyDataUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* loaded from: classes.dex */
public class KeyBasicData extends KeyInfoBase {
    String Description;
    int KeyTypeItemID;
    int align;
    List<KeyBlankItemBasicData> blankList;
    ClampKeyBasicData clampKeyBasicData;
    private transient Long clampKeyBasicData__resolvedKey;
    private transient DaoSession daoSession;
    String depth;
    String depth_name;
    int face;
    long icCard;
    int length;
    private transient KeyBasicDataDao myDao;
    String parameter_info;
    int row_count;
    String row_pos;
    String space;
    String space_width;
    int thick;
    int type;
    int width;

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
            str = ((str + str2 + ":") + hashMap.get(str2)).substring(0, r8.length() - 1) + "\n";
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

    public ClampKeyBasicData getClampKeyBasicData() {
        long j = this.icCard;
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
            throw new DaoException("To-one property 'icCard' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.clampKeyBasicData = clampKeyBasicData;
            long longValue = clampKeyBasicData.getFK_KeyID().longValue();
            this.icCard = longValue;
            this.clampKeyBasicData__resolvedKey = Long.valueOf(longValue);
        }
    }

    public List<KeyBlankItemBasicData> getBlankList() {
        if (this.blankList == null) {
            DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            List<KeyBlankItemBasicData> _queryKeyBasicData_BlankList = daoSession.getKeyBlankItemBasicDataDao()._queryKeyBasicData_BlankList(this.icCard);
            synchronized (this) {
                if (this.blankList == null) {
                    this.blankList = _queryKeyBasicData_BlankList;
                }
            }
        }
        return this.blankList;
    }

    public synchronized void resetBlankList() {
        this.blankList = null;
    }

    public void delete() {
        KeyBasicDataDao keyBasicDataDao = this.myDao;
        if (keyBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBasicDataDao.delete(this);
    }

    public void refresh() {
        KeyBasicDataDao keyBasicDataDao = this.myDao;
        if (keyBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBasicDataDao.refresh(this);
    }

    public void update() {
        KeyBasicDataDao keyBasicDataDao = this.myDao;
        if (keyBasicDataDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        keyBasicDataDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getKeyBasicDataDao() : null;
    }
}
