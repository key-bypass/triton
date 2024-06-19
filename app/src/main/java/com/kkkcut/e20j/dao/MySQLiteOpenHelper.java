package com.kkkcut.e20j.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao;
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao;
import com.kkkcut.e20j.DbBean.userDB.JpushMsgDao;
import org.greenrobot.greendao.database.Database;

/* loaded from: classes.dex */
public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
    public void onUpgrade(Database database, int i, int i2) {
        if (i < i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by migrating all tables data");
            MigrationHelper.getInstance().migrate(database, CutHistoryDataDao.class, CollectionDataDao.class, JpushMsgDao.class, CustomKeyDao.class);
        }
    }
}
