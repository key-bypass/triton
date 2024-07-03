package com.kkkcut.e20j.dao;

import android.content.Context;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyResource;
import com.kkkcut.e20j.DbBean.KeyResourceDao;
import com.kkkcut.e20j.DbBean.KeyThumbnail;
import com.kkkcut.e20j.DbBean.KeyThumbnailDao;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.utils.DesUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class ResourceDaoManager {
    private static final String TAG = "ResourceDaoManager";
    private static DaoSession daoSession;
    private static ResourceDaoManager sInstance;
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mOpenHelper;

    public ResourceDaoManager(Context context) {
        this.mContext = context;
    }

    public static ResourceDaoManager getInstance() {
        if (sInstance == null) {
            synchronized (ResourceDaoManager.class) {
                sInstance = new ResourceDaoManager(MyApplication.getInstance());
            }
        }
        return sInstance;
    }

    public DaoMaster getDaoMaster() {
        if (this.mDaoMaster == null) {
            this.mOpenHelper = new DaoMaster.DevOpenHelper(this.mContext, ResUpdateUtils.getLocalResDbName());
            this.mDaoMaster = new DaoMaster(this.mOpenHelper.getWritableDatabase());
        }
        return this.mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (!ResUpdateUtils.localResDbExist()) {
            return null;
        }
        if (daoSession == null) {
            if (this.mDaoMaster == null) {
                this.mDaoMaster = getDaoMaster();
            }
            daoSession = this.mDaoMaster.newSession();
        }
        return daoSession;
    }

    public KeyThumbnail getKeyselectThumb(int i) {
        return (KeyThumbnail) getDaoSession().queryBuilder(KeyThumbnail.class).where(KeyThumbnailDao.Properties.FK_KeyID.eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public KeyResource getKeyResource(int i) {
        KeyResource keyResource = (KeyResource) getDaoSession().queryBuilder(KeyResource.class).where(KeyResourceDao.Properties.FK_ModelSeriesID.eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
        try {
            keyResource.setExplainHtml(DesUtil.decrypt(keyResource.getExplainHtml(), DesUtil.DATABASE));
            keyResource.setVideoPath(DesUtil.decrypt(keyResource.getVideoPath(), DesUtil.DATABASE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyResource;
    }

    public String getDbVersion() {
        if (!ResUpdateUtils.localResDbExist()) {
            return "0";
        }
        try {
            return DesUtil.decrypt(getDaoSession().getDbVersionDao().queryBuilder().unique().getVersion(), DesUtil.DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}