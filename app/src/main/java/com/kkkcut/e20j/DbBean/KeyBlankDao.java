package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyBlankDao extends AbstractDao<KeyBlank, Long> {
    public static final String TABLENAME = "KeyBlank";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Long.TYPE, BarCodeRemindActivity.ID, true, BarCodeRemindActivity.ID);
        public static final Property KeyBlankName = new Property(1, String.class, "KeyBlankName", false, "KeyBlankName");
        public static final Property Description = new Property(2, String.class, "Description", false, "Description");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KeyBlankDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyBlankDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyBlank keyBlank) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyBlank.getID());
        String keyBlankName = keyBlank.getKeyBlankName();
        if (keyBlankName != null) {
            databaseStatement.bindString(2, keyBlankName);
        }
        String description = keyBlank.getDescription();
        if (description != null) {
            databaseStatement.bindString(3, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyBlank keyBlank) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyBlank.getID());
        String keyBlankName = keyBlank.getKeyBlankName();
        if (keyBlankName != null) {
            sQLiteStatement.bindString(2, keyBlankName);
        }
        String description = keyBlank.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(3, description);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyBlank readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        int i2 = i + 1;
        int i3 = i + 2;
        return new KeyBlank(j, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyBlank keyBlank, int i) {
        keyBlank.setID(cursor.getLong(i + 0));
        int i2 = i + 1;
        keyBlank.setKeyBlankName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        keyBlank.setDescription(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(KeyBlank keyBlank, long j) {
        keyBlank.setID(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(KeyBlank keyBlank) {
        if (keyBlank != null) {
            return Long.valueOf(keyBlank.getID());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyBlank keyBlank) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }
}
