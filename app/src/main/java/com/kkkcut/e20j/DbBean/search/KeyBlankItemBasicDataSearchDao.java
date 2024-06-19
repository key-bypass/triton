package com.kkkcut.e20j.DbBean.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyBlankItemBasicDataSearchDao extends AbstractDao<KeyBlankItemBasicDataSearch, Void> {
    public static final String TABLENAME = "KeyBlankItem_BasicData";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_KeyBlankItemID = new Property(1, Long.class, "FK_KeyBlankItemID", false, "FK_KeyBlankItemID");
        public static final Property FK_KeyID = new Property(2, Long.TYPE, "FK_KeyID", false, "FK_KeyID");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch) {
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
    public final Void updateKeyAfterInsert(KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch, long j) {
        return null;
    }

    public KeyBlankItemBasicDataSearchDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyBlankItemBasicDataSearchDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyBlankItemBasicDataSearch.getID());
        Long fK_KeyBlankItemID = keyBlankItemBasicDataSearch.getFK_KeyBlankItemID();
        if (fK_KeyBlankItemID != null) {
            databaseStatement.bindLong(2, fK_KeyBlankItemID.longValue());
        }
        databaseStatement.bindLong(3, keyBlankItemBasicDataSearch.getFK_KeyID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyBlankItemBasicDataSearch.getID());
        Long fK_KeyBlankItemID = keyBlankItemBasicDataSearch.getFK_KeyBlankItemID();
        if (fK_KeyBlankItemID != null) {
            sQLiteStatement.bindLong(2, fK_KeyBlankItemID.longValue());
        }
        sQLiteStatement.bindLong(3, keyBlankItemBasicDataSearch.getFK_KeyID());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyBlankItemBasicDataSearch readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        return new KeyBlankItemBasicDataSearch(cursor.getInt(i + 0), cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 2));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyBlankItemBasicDataSearch keyBlankItemBasicDataSearch, int i) {
        keyBlankItemBasicDataSearch.setID(cursor.getInt(i + 0));
        int i2 = i + 1;
        keyBlankItemBasicDataSearch.setFK_KeyBlankItemID(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        keyBlankItemBasicDataSearch.setFK_KeyID(cursor.getLong(i + 2));
    }
}
