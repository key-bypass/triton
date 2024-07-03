package com.kkkcut.e20j.dao;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyImage;
import com.kkkcut.e20j.DbBean.KeyImageDao;
import com.kkkcut.e20j.DbBean.LocalDbVersion;
import com.kkkcut.e20j.DbBean.LocalDbVersionDao;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.bean.gsonBean.ResUpdate;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class ResUpdateDaoManager {
    private static final String TAG = "ResUpdateDaoManager";
    private static DaoSession daoSession;
    private static ResUpdateDaoManager sInstance;
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mOpenHelper;

    public ResUpdateDaoManager(Context context) {
        this.mContext = context;
    }

    public static ResUpdateDaoManager getInstance() {
        if (sInstance == null) {
            synchronized (ResUpdateDaoManager.class) {
                sInstance = new ResUpdateDaoManager(MyApplication.getInstance());
            }
        }
        return sInstance;
    }

    public DaoMaster getDaoMaster() {
        if (this.mDaoMaster == null) {
            this.mOpenHelper = new DaoMaster.DevOpenHelper(this.mContext, "ResUpdate.db");
            this.mDaoMaster = new DaoMaster(this.mOpenHelper.getWritableDatabase());
        }
        return this.mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (this.mDaoMaster == null) {
                this.mDaoMaster = getDaoMaster();
            }
            daoSession = this.mDaoMaster.newSession();
        }
        return daoSession;
    }

    private void closeDaoSession() {
        DaoSession daoSession2 = daoSession;
        if (daoSession2 != null) {
            daoSession2.clear();
            daoSession = null;
        }
    }

    private void closeHelper() {
        DaoMaster.DevOpenHelper devOpenHelper = this.mOpenHelper;
        if (devOpenHelper != null) {
            devOpenHelper.close();
            this.mOpenHelper = null;
        }
    }

    public void closeDBConncetion() {
        closeDaoSession();
        closeHelper();
    }

    public List<LocalDbVersion> getLocalDbList() {
        return getDaoSession().getLocalDbVersionDao().queryBuilder().list();
    }

    public String getMainDbVersion(String str) {
        String dbVersion = KeyInfoDaoManager.getInstance().getDbVersion();
        if (!TextUtils.isEmpty(dbVersion)) {
            return dbVersion;
        }
        String locResVer = getMainDb(str).getLocResVer();
        return TextUtils.isEmpty(locResVer) ? "0" : locResVer;
    }

    public LocalDbVersion getMainDb(String str) {
        List<LocalDbVersion> list = getDaoSession().getLocalDbVersionDao().queryBuilder().where(LocalDbVersionDao.Properties.SvResName.eq(str), new WhereCondition[0]).list();
        LocalDbVersion localDbVersion = list.size() > 0 ? list.get(0) : null;
        if (localDbVersion != null) {
            return localDbVersion;
        }
        LocalDbVersion localDbVersion2 = new LocalDbVersion();
        localDbVersion2.setLocResName(str);
        return localDbVersion2;
    }

    public String getResDbVersion() {
        String dbVersion = ResourceDaoManager.getInstance().getDbVersion();
        if (!TextUtils.isEmpty(dbVersion)) {
            return dbVersion;
        }
        String locResVer = getResDb().getLocResVer();
        return TextUtils.isEmpty(locResVer) ? "0" : locResVer;
    }

    public LocalDbVersion getResDb() {
        List<LocalDbVersion> list = getDaoSession().getLocalDbVersionDao().queryBuilder().where(LocalDbVersionDao.Properties.SvResName.eq(ResUpdateUtils.RES), new WhereCondition[0]).list();
        LocalDbVersion localDbVersion = list.size() > 0 ? list.get(0) : null;
        if (localDbVersion != null) {
            return localDbVersion;
        }
        LocalDbVersion localDbVersion2 = new LocalDbVersion();
        localDbVersion2.setLocResName(ResUpdateUtils.RES);
        return localDbVersion2;
    }

    public String getErrorDbVersion() {
        String dbVersion = ErrorCodeDaoManager.getInstance().getDbVersion();
        if (!TextUtils.isEmpty(dbVersion)) {
            return dbVersion;
        }
        String locResVer = getErrorDb().getLocResVer();
        return TextUtils.isEmpty(locResVer) ? "0" : locResVer;
    }

    public LocalDbVersion getErrorDb() {
        QueryBuilder<LocalDbVersion> queryBuilder = getDaoSession().getLocalDbVersionDao().queryBuilder();
        String localErrorDbName = ResUpdateUtils.getLocalErrorDbName();
        List<LocalDbVersion> list = queryBuilder.where(LocalDbVersionDao.Properties.LocResName.eq(localErrorDbName), new WhereCondition[0]).list();
        LocalDbVersion localDbVersion = list.size() > 0 ? list.get(0) : null;
        if (localDbVersion != null) {
            return localDbVersion;
        }
        LocalDbVersion localDbVersion2 = new LocalDbVersion();
        localDbVersion2.setLocResName(localErrorDbName);
        return localDbVersion2;
    }

    public String getImgListDbVersion() {
        String locResVer = getImgListDb().getLocResVer();
        return TextUtils.isEmpty(locResVer) ? "0" : locResVer;
    }

    public int getImgListDbUpdateStatus() {
        List<LocalDbVersion> list = getDaoSession().getLocalDbVersionDao().queryBuilder().where(LocalDbVersionDao.Properties.SvResName.eq(ResUpdateUtils.IMAGE_DB), new WhereCondition[0]).list();
        LocalDbVersion localDbVersion = list.size() > 0 ? list.get(0) : null;
        if (localDbVersion == null) {
            return 0;
        }
        return localDbVersion.getUpdateStatus();
    }

    public LocalDbVersion getImgListDb() {
        LocalDbVersion unique = getDaoSession().getLocalDbVersionDao().queryBuilder().where(LocalDbVersionDao.Properties.LocResName.eq(ResUpdateUtils.IMAGE_DB), new WhereCondition[0]).unique();
        if (unique != null) {
            return unique;
        }
        LocalDbVersion localDbVersion = new LocalDbVersion();
        localDbVersion.setLocResName(ResUpdateUtils.IMAGE_DB);
        return localDbVersion;
    }

    public KeyImage getImgDb(int i) {
        KeyImage unique = getDaoSession().getKeyImageDao().queryBuilder().where(KeyImageDao.Properties.KeyId.eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
        if (unique != null) {
            return unique;
        }
        KeyImage keyImage = new KeyImage();
        keyImage.setKeyId(i);
        return keyImage;
    }

    public List<KeyImage> getImgList() {
        return getDaoSession().getKeyImageDao().queryBuilder().list();
    }

    public boolean isImageUpdateFinish() {
        return getDaoSession().getKeyImageDao().queryBuilder().where(KeyImageDao.Properties.HashServer.isNull(), new WhereCondition[0]).list().size() == 0;
    }

    private LocalDbVersion getAppDb() {
        LocalDbVersion unique = getDaoSession().getLocalDbVersionDao().queryBuilder().where(LocalDbVersionDao.Properties.LocResName.eq(ResUpdateUtils.APP), new WhereCondition[0]).unique();
        if (unique != null) {
            return unique;
        }
        LocalDbVersion localDbVersion = new LocalDbVersion();
        localDbVersion.setLocResName(ResUpdateUtils.APP);
        return localDbVersion;
    }

    public void updateLocalResDb(ResUpdate resUpdate, String str) {
        String androidAPPVer = resUpdate.getAndroidAPPVer();
        String versionName = AppUtil.getVersionName(MyApplication.getInstance());
        LocalDbVersion appDb = getAppDb();
        appDb.setSvResVer(androidAPPVer);
        appDb.setLocResVer(versionName);
        getDaoSession().getLocalDbVersionDao().insertOrReplace(appDb);
        List<ResUpdate.DbUpdatelistBean> dbUpdatelist = resUpdate.getDbUpdatelist();
        if (dbUpdatelist == null) {
            return;
        }
        for (ResUpdate.DbUpdatelistBean dbUpdatelistBean : dbUpdatelist) {
            LocalDbVersion localDbVersion = null;
            String dbName = dbUpdatelistBean.getDbName();
            String dbVer = dbUpdatelistBean.getDbVer();
            if (TextUtils.equals(dbName, str)) {
                localDbVersion = getMainDb(str);
                if (TextUtils.equals(localDbVersion.getLocResVer(), dbVer)) {
                }
            }
            if (TextUtils.equals(dbName, ResUpdateUtils.RES)) {
                localDbVersion = getResDb();
                if (TextUtils.equals(localDbVersion.getLocResVer(), dbVer)) {
                }
            }
            if (TextUtils.equals(dbName, ResUpdateUtils.ERROR_CODE)) {
                localDbVersion = getErrorDb();
                if (TextUtils.equals(localDbVersion.getLocResVer(), dbVer)) {
                }
            }
            if (localDbVersion != null) {
                localDbVersion.setSvResVer(dbVer);
                localDbVersion.setUrl(dbUpdatelistBean.getUrl());
                localDbVersion.setSvHash(dbUpdatelistBean.getDBHash());
                localDbVersion.setSvResName(dbUpdatelistBean.getDbName());
                getDaoSession().getLocalDbVersionDao().insertOrReplace(localDbVersion);
            }
        }
        ResUpdate.KeyImgUpdateListBean keyImgUpdateList = resUpdate.getKeyImgUpdateList();
        LocalDbVersion imgListDb = getImgListDb();
        String ver = keyImgUpdateList.getVer();
        if (TextUtils.equals(imgListDb.getLocResVer(), ver)) {
            return;
        }
        imgListDb.setSvResName(ResUpdateUtils.IMAGE_DB);
        imgListDb.setSvResVer(ver);
        imgListDb.setUrl(keyImgUpdateList.getUrl());
        getDaoSession().getLocalDbVersionDao().insertOrReplace(imgListDb);
        Log.i(TAG, "更新图片列表:开始");
        for (ResUpdate.KeyImgUpdateListBean.ImgHashListBean imgHashListBean : keyImgUpdateList.getImgHashList()) {
            Log.i(TAG, "updateLocalResDb: " + imgHashListBean);
            KeyImage keyImage = new KeyImage();
            try {
                keyImage.setKeyId(Integer.parseInt(imgHashListBean.getKeyID()));
                String keyImg = imgHashListBean.getKeyImg();
                String url = keyImgUpdateList.getUrl();
                if (!TextUtils.isEmpty(keyImg) && !TextUtils.isEmpty(url)) {
                    String[] split = keyImg.split(";");
                    StringBuilder sb = new StringBuilder();
                    for (String str2 : split) {
                        sb.append(url + str2 + ";");
                    }
                    keyImage.setUrl(sb.toString());
                    keyImage.setHashServer(imgHashListBean.getKeyHash());
                    getDaoSession().getKeyImageDao().insertOrReplace(keyImage);
                }
            } catch (Exception unused) {
            }
        }
        Log.i(TAG, "更新图片列表:完成");
    }

    public void updateSingleLocalDb(LocalDbVersion localDbVersion) {
        getDaoSession().getLocalDbVersionDao().update(localDbVersion);
    }

    public void updateSingleImg(KeyImage keyImage) {
        getDaoSession().getKeyImageDao().insertOrReplace(keyImage);
    }

    public void updateImgDbLocal() {
        LocalDbVersion imgListDb = getImgListDb();
        imgListDb.setLocResVer(imgListDb.getSvResVer());
        getDaoSession().getLocalDbVersionDao().update(imgListDb);
    }
}