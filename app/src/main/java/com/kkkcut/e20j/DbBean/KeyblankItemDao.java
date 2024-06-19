package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

/* loaded from: classes.dex */
public class KeyblankItemDao extends AbstractDao<KeyblankItem, Long> {
    public static final String TABLENAME = "KeyblankItem";
    private DaoSession daoSession;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Long.TYPE, BarCodeRemindActivity.ID, true, BarCodeRemindActivity.ID);
        public static final Property KeyblankItemName = new Property(1, String.class, "KeyblankItemName", false, "KeyblankItemName");
        public static final Property Description = new Property(2, String.class, "Description", false, "Description");
        public static final Property FK_KeyblankID = new Property(3, Long.TYPE, "FK_KeyblankID", false, "FK_KeyblankID");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KeyblankItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyblankItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyblankItem keyblankItem) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyblankItem.getID());
        String keyblankItemName = keyblankItem.getKeyblankItemName();
        if (keyblankItemName != null) {
            databaseStatement.bindString(2, keyblankItemName);
        }
        String description = keyblankItem.getDescription();
        if (description != null) {
            databaseStatement.bindString(3, description);
        }
        databaseStatement.bindLong(4, keyblankItem.getFK_KeyblankID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyblankItem keyblankItem) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyblankItem.getID());
        String keyblankItemName = keyblankItem.getKeyblankItemName();
        if (keyblankItemName != null) {
            sQLiteStatement.bindString(2, keyblankItemName);
        }
        String description = keyblankItem.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(3, description);
        }
        sQLiteStatement.bindLong(4, keyblankItem.getFK_KeyblankID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(KeyblankItem keyblankItem) {
        super.attachEntity((KeyblankItemDao) keyblankItem);
        keyblankItem.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyblankItem readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        int i2 = i + 1;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 2;
        return new KeyblankItem(j, string, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getLong(i + 3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyblankItem keyblankItem, int i) {
        keyblankItem.setID(cursor.getLong(i + 0));
        int i2 = i + 1;
        keyblankItem.setKeyblankItemName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        keyblankItem.setDescription(cursor.isNull(i3) ? null : cursor.getString(i3));
        keyblankItem.setFK_KeyblankID(cursor.getLong(i + 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(KeyblankItem keyblankItem, long j) {
        keyblankItem.setID(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(KeyblankItem keyblankItem) {
        if (keyblankItem != null) {
            return Long.valueOf(keyblankItem.getID());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyblankItem keyblankItem) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getKeyBlankDao().getAllColumns());
            sb.append(" FROM KeyblankItem T");
            sb.append(" LEFT JOIN KeyBlank T0 ON T.\"FK_KeyblankID\"=T0.\"ID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected KeyblankItem loadCurrentDeep(Cursor cursor, boolean z) {
        KeyblankItem loadCurrent = loadCurrent(cursor, 0, z);
        KeyBlank keyBlank = (KeyBlank) loadCurrentOther(this.daoSession.getKeyBlankDao(), cursor, getAllColumns().length);
        if (keyBlank != null) {
            loadCurrent.setKeyBlank(keyBlank);
        }
        return loadCurrent;
    }

    public KeyblankItem loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{l.toString()});
        try {
            if (!rawQuery.moveToFirst()) {
                return null;
            }
            if (!rawQuery.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + rawQuery.getCount());
            }
            return loadCurrentDeep(rawQuery, true);
        } finally {
            rawQuery.close();
        }
    }

    public List<KeyblankItem> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (cursor.moveToFirst()) {
            if (this.identityScope != null) {
                this.identityScope.lock();
                this.identityScope.reserveRoom(count);
            }
            do {
                try {
                    arrayList.add(loadCurrentDeep(cursor, false));
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.unlock();
                    }
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    protected List<KeyblankItem> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<KeyblankItem> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + str, strArr));
    }
}
