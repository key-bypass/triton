package com.kkkcut.e20j.dao;

import android.content.Context;
import android.text.TextUtils;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.Manufacturer;
import com.kkkcut.e20j.DbBean.userDB.CollectionData;
import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimpleDao;
import com.kkkcut.e20j.DbBean.userDB.JpushMsg;
import com.kkkcut.e20j.DbBean.userDB.JpushMsgDao;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChild;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplateDao;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHidden;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class UserDataDaoManager {
    private static final String TAG = "UserDataDaoManager";
    private static UserDataDaoManager sInstance;
    private DaoSession daoSession;

    private UserDataDaoManager(Context context) {
        this.daoSession = new DaoMaster(new MySQLiteOpenHelper(context, "userData.db", null).getWritableDb()).newSession();
    }

    public static UserDataDaoManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserDataDaoManager(context);
        }
        return sInstance;
    }

    public boolean saveCutHistory(CutHistoryData cutHistoryData) {
        String toothCode = cutHistoryData.getToothCode();
        if (TextUtils.isEmpty(toothCode)) {
            return true;
        }
        CutHistoryData lastCutHistory = getLastCutHistory();
        if (lastCutHistory != null && lastCutHistory.getTitle().equals(cutHistoryData.getTitle()) && toothCode.equals(lastCutHistory.getToothCode())) {
            String toothCodeSide = lastCutHistory.getToothCodeSide();
            String toothCodeSide2 = cutHistoryData.getToothCodeSide();
            if (!TextUtils.isEmpty(toothCodeSide2) && !toothCodeSide2.equals(toothCodeSide)) {
                lastCutHistory.setToothCodeSide(toothCodeSide2);
                this.daoSession.getCutHistoryDataDao().update(lastCutHistory);
            }
            return true;
        }
        try {
            this.daoSession.getCutHistoryDataDao().save(cutHistoryData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CutHistoryData getLastCutHistory() {
        List list = this.daoSession.queryBuilder(CutHistoryData.class).orderDesc(CutHistoryDataDao.Properties.Time).limit(1).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (CutHistoryData) list.get(0);
    }

    public List<CutHistoryData> getCutHistory() {
        return this.daoSession.queryBuilder(CutHistoryData.class).orderDesc(CutHistoryDataDao.Properties.Time).limit(300).list();
    }

    public List<CutHistoryData> getCutHistory(int i, int i2, String str) {
        QueryBuilder queryBuilder = this.daoSession.queryBuilder(CutHistoryData.class);
        queryBuilder.whereOr(CutHistoryDataDao.Properties.Title.like("%" + str + "%"), CutHistoryDataDao.Properties.CodeSeries.like("%" + str + "%"), CutHistoryDataDao.Properties.Remark.like("%" + str + "%"));
        return queryBuilder.orderDesc(CutHistoryDataDao.Properties.Time).offset(i * i2).limit(i2).list();
    }

    public void deleteCutHistory(CutHistoryData cutHistoryData) {
        this.daoSession.getCutHistoryDataDao().delete(cutHistoryData);
    }

    public boolean deleteAllCutHistories() {
        try {
            this.daoSession.getCutHistoryDataDao().deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CutHistoryData> fuzzyQueryCutHistory(String str) {
        return this.daoSession.getCutHistoryDataDao().queryBuilder().where(CutHistoryDataDao.Properties.Title.like("%" + str + "%"), new WhereCondition[0]).list();
    }

    public boolean collectKey(CollectionData collectionData) {
        try {
            this.daoSession.getCollectionDataDao().save(collectionData);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CollectionData> getCollection() {
        return this.daoSession.queryBuilder(CollectionData.class).orderDesc(CollectionDataDao.Properties.Title).list();
    }

    public List<CollectionData> getCollection(int i, int i2, String str) {
        QueryBuilder queryBuilder = this.daoSession.queryBuilder(CollectionData.class);
        queryBuilder.whereOr(CollectionDataDao.Properties.Title.like("%" + str + "%"), CollectionDataDao.Properties.CodeSeries.like("%" + str + "%"), CollectionDataDao.Properties.Remark.like("%" + str + "%"));
        return queryBuilder.orderAsc(CollectionDataDao.Properties.Remark).offset(i * i2).limit(i2).list();
    }

    public void deleteCollection(CollectionData collectionData) {
        this.daoSession.getCollectionDataDao().delete(collectionData);
    }

    public List<CollectionData> fuzzyQueryCollection(String str) {
        return this.daoSession.getCollectionDataDao().queryBuilder().whereOr(CollectionDataDao.Properties.Title.like("%" + str + "%"), CollectionDataDao.Properties.Remark.like("%" + str + "%"), new WhereCondition[0]).list();
    }

    public boolean deleteAllCollections() {
        try {
            this.daoSession.getCollectionDataDao().deleteAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasCollected(int i) {
        return this.daoSession.getCollectionDataDao().queryBuilder().where(CollectionDataDao.Properties.SeriesID.eq(Integer.valueOf(i)), new WhereCondition[0]).count() > 0;
    }

    public boolean cancleCollect(int i) {
        try {
            this.daoSession.getCollectionDataDao().queryBuilder().where(CollectionDataDao.Properties.SeriesID.eq(Integer.valueOf(i)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CustomKey> getCustomKeys() {
        return this.daoSession.getCustomKeyDao().queryBuilder().list();
    }

    public List<CustomKey> fuzzyQueryCustomKeys(String str) {
        return this.daoSession.getCustomKeyDao().queryBuilder().where(CustomKeyDao.Properties.Keyname.like("%" + str + "%"), new WhereCondition[0]).list();
    }

    public CustomKey getCustomKeyByID(int i) {
        List<CustomKey> list = this.daoSession.getCustomKeyDao().queryBuilder().where(CustomKeyDao.Properties.IcCard.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void saveCustomKey(CustomKey customKey) {
        this.daoSession.getCustomKeyDao().save(customKey);
    }

    public void deleteCustomKey(CustomKey customKey) {
        this.daoSession.getCustomKeyDao().delete(customKey);
    }

    public List<DuplicateDimple> getDuplicateDimpleKeys() {
        return this.daoSession.getDuplicateDimpleDao().queryBuilder().list();
    }

    public List<DuplicateDimple> fuzzyQueryDuplicateDimpleKeys(String str) {
        return this.daoSession.getDuplicateDimpleDao().queryBuilder().where(DuplicateDimpleDao.Properties.Keyname.like("%" + str + "%"), new WhereCondition[0]).list();
    }

    public DuplicateDimple getDuplicateDimpleKeyByID(int i) {
        List<DuplicateDimple> list = this.daoSession.getDuplicateDimpleDao().queryBuilder().where(DuplicateDimpleDao.Properties.IcCard.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public void saveDuplicateDimpleKey(DuplicateDimple duplicateDimple) {
        this.daoSession.getDuplicateDimpleDao().save(duplicateDimple);
    }

    public void deleteDuplicateDimpleKey(DuplicateDimple duplicateDimple) {
        this.daoSession.getDuplicateDimpleDao().delete(duplicateDimple);
    }

    public void saveJpushMsg(JpushMsg jpushMsg) {
        this.daoSession.getJpushMsgDao().save(jpushMsg);
    }

    public List<JpushMsg> getJpushMsgs() {
        return this.daoSession.getJpushMsgDao().queryBuilder().list();
    }

    public boolean haveNewMessage() {
        return !this.daoSession.getJpushMsgDao().queryBuilder().where(JpushMsgDao.Properties.HaveRead.eq(1), new WhereCondition[0]).list().isEmpty();
    }

    public void updateMessageDate(JpushMsg jpushMsg) {
        this.daoSession.getJpushMsgDao().update(jpushMsg);
    }

    public void saveKeyMarkingTemplate(KeyMarkingTemplate keyMarkingTemplate) {
        this.daoSession.getKeyMarkingTemplateDao().insert(keyMarkingTemplate);
    }

    public void saveKeyMarkingTemplateChild(KeyMarkingChild keyMarkingChild) {
        this.daoSession.getKeyMarkingChildDao().insert(keyMarkingChild);
    }

    public List<KeyMarkingTemplate> getKeyMarkingTemplates() {
        return this.daoSession.getKeyMarkingTemplateDao().loadAll();
    }

    public List<KeyMarkingTemplate> fuzzyQueryTemplates(String str) {
        return this.daoSession.getKeyMarkingTemplateDao().queryBuilder().where(KeyMarkingTemplateDao.Properties.Description.like("%" + str + "%"), new WhereCondition[0]).list();
    }

    public void deleteSingleTemplate(KeyMarkingTemplate keyMarkingTemplate) {
        this.daoSession.getKeyMarkingTemplateDao().delete(keyMarkingTemplate);
    }

    public void deleteAllTemplate() {
        this.daoSession.getKeyMarkingTemplateDao().deleteAll();
    }

    public List<Manufacturer> getManufacturerHidden() {
        var arrayList = new ArrayList();
        Iterator<ManufacturerHidden> it = this.daoSession.getManufacturerHiddenDao().queryBuilder().list().iterator();
        while (it.hasNext()) {
            arrayList.add(new Manufacturer(it.next()));
        }
        return arrayList;
    }

    public void hideManufacturer(ManufacturerHidden manufacturerHidden) {
        this.daoSession.getManufacturerHiddenDao().insertOrReplace(manufacturerHidden);
    }

    public void showManufacturer(ManufacturerHidden manufacturerHidden) {
        this.daoSession.getManufacturerHiddenDao().delete(manufacturerHidden);
    }

    public void saveCustomKeyFromDimpleDup(DuplicateDimple duplicateDimple) {
        CustomKey customKey = new CustomKey();
        customKey.setKeyTypeItemID(duplicateDimple.getKeyTypeItemID());
        customKey.setType(duplicateDimple.getType());
        customKey.setAlign(duplicateDimple.getAlign());
        customKey.setWidth(duplicateDimple.getWidth());
        customKey.setThick(duplicateDimple.getThick());
        customKey.setLength(duplicateDimple.getLength());
        customKey.setRow_count(duplicateDimple.getRow_count());
        customKey.setFace(duplicateDimple.getFace());
        customKey.setRow_pos(duplicateDimple.getRow_pos());
        customKey.setSpace(duplicateDimple.getSpace());
        customKey.setSpace_width(duplicateDimple.getSpace_width());
        customKey.setDepth(duplicateDimple.getDepth());
        customKey.setDepth_name(duplicateDimple.getDepth_name());
        customKey.setParameter_info(duplicateDimple.getParameter_info());
        customKey.setKeyname(duplicateDimple.getKeyname());
        customKey.setClampNum(duplicateDimple.getClampNum());
        customKey.setClampSide(duplicateDimple.getClampSide());
        customKey.setClampSlot(duplicateDimple.getClampSlot());
        this.daoSession.getCustomKeyDao().save(customKey);
    }
}