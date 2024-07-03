package com.kkkcut.e20j.dao;

import android.content.Context;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.ErrorCodeBean;
import com.kkkcut.e20j.DbBean.ErrorCodeBeanDao;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.utils.DesUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class ErrorCodeDaoManager {
    private static final String TAG = "ResourceDaoManager";
    private static DaoSession daoSession;
    private static ErrorCodeDaoManager sInstance;
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mOpenHelper;

    public ErrorCodeDaoManager(Context context) {
        this.mContext = context;
    }

    public static ErrorCodeDaoManager getInstance() {
        if (sInstance == null) {
            synchronized (ErrorCodeDaoManager.class) {
                sInstance = new ErrorCodeDaoManager(MyApplication.getInstance());
            }
        }
        return sInstance;
    }

    public DaoMaster getDaoMaster() {
        if (this.mDaoMaster == null) {
            this.mOpenHelper = new DaoMaster.DevOpenHelper(this.mContext, ResUpdateUtils.getLocalErrorDbName());
            this.mDaoMaster = new DaoMaster(this.mOpenHelper.getWritableDatabase());
        }
        return this.mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (!ResUpdateUtils.localErrorDbExist()) {
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

    public ErrorCodeBean getErrorCode(int i) {
        List list = getDaoSession().queryBuilder(ErrorCodeBean.class).where(ErrorCodeBeanDao.Properties.Code.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return (ErrorCodeBean) list.get(0);
    }

    public String getDbVersion() {
        if (!ResUpdateUtils.localErrorDbExist()) {
            return "";
        }
        try {
            return DesUtil.decrypt(getDaoSession().getLocalDbVersionDao().queryBuilder().unique().getSvResVer(), DesUtil.DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }
}