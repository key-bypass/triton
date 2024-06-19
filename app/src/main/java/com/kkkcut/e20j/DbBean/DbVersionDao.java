package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DbVersionDao extends AbstractDao<DbVersion, Void> {
    public static final String TABLENAME = "Key_blank_Sec";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Version = new Property(0, String.class, "version", false, BarCodeRemindActivity.ID);
        public static final Property UpdateInfo = new Property(1, String.class, "updateInfo", false, "ModelName8");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DbVersion dbVersion) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DbVersion dbVersion) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void readKey(Cursor cursor, int i) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Void updateKeyAfterInsert(DbVersion dbVersion, long j) {
        return null;
    }

    public DbVersionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DbVersionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DbVersion dbVersion) {
        databaseStatement.clearBindings();
        String version = dbVersion.getVersion();
        if (version != null) {
            databaseStatement.bindString(1, version);
        }
        String updateInfo = dbVersion.getUpdateInfo();
        if (updateInfo != null) {
            databaseStatement.bindString(2, updateInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DbVersion dbVersion) {
        sQLiteStatement.clearBindings();
        String version = dbVersion.getVersion();
        if (version != null) {
            sQLiteStatement.bindString(1, version);
        }
        String updateInfo = dbVersion.getUpdateInfo();
        if (updateInfo != null) {
            sQLiteStatement.bindString(2, updateInfo);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DbVersion readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        return new DbVersion(cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DbVersion dbVersion, int i) {
        int i2 = i + 0;
        dbVersion.setVersion(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 1;
        dbVersion.setUpdateInfo(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
