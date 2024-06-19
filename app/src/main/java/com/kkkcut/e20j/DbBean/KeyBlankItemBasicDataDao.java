package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class KeyBlankItemBasicDataDao extends AbstractDao<KeyBlankItemBasicData, Void> {
    public static final String TABLENAME = "KeyBlankItem_BasicData";
    private DaoSession daoSession;
    private Query<KeyBlankItemBasicData> keyBasicData_BlankListQuery;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {

        /* renamed from: ID */
        public static final Property f182ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.f311ID, false, BarCodeRemindActivity.f311ID);
        public static final Property FK_KeyBlankItemID = new Property(1, Long.class, "FK_KeyBlankItemID", false, "FK_KeyBlankItemID");
        public static final Property FK_KeyID = new Property(2, Long.TYPE, "FK_KeyID", false, "FK_KeyID");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyBlankItemBasicData keyBlankItemBasicData) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyBlankItemBasicData keyBlankItemBasicData) {
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
    public final Void updateKeyAfterInsert(KeyBlankItemBasicData keyBlankItemBasicData, long j) {
        return null;
    }

    public KeyBlankItemBasicDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyBlankItemBasicDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyBlankItemBasicData keyBlankItemBasicData) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyBlankItemBasicData.getID());
        Long fK_KeyBlankItemID = keyBlankItemBasicData.getFK_KeyBlankItemID();
        if (fK_KeyBlankItemID != null) {
            databaseStatement.bindLong(2, fK_KeyBlankItemID.longValue());
        }
        databaseStatement.bindLong(3, keyBlankItemBasicData.getFK_KeyID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyBlankItemBasicData keyBlankItemBasicData) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyBlankItemBasicData.getID());
        Long fK_KeyBlankItemID = keyBlankItemBasicData.getFK_KeyBlankItemID();
        if (fK_KeyBlankItemID != null) {
            sQLiteStatement.bindLong(2, fK_KeyBlankItemID.longValue());
        }
        sQLiteStatement.bindLong(3, keyBlankItemBasicData.getFK_KeyID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(KeyBlankItemBasicData keyBlankItemBasicData) {
        super.attachEntity((KeyBlankItemBasicDataDao) keyBlankItemBasicData);
        keyBlankItemBasicData.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyBlankItemBasicData readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        return new KeyBlankItemBasicData(cursor.getInt(i + 0), cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 2));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyBlankItemBasicData keyBlankItemBasicData, int i) {
        keyBlankItemBasicData.setID(cursor.getInt(i + 0));
        int i2 = i + 1;
        keyBlankItemBasicData.setFK_KeyBlankItemID(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        keyBlankItemBasicData.setFK_KeyID(cursor.getLong(i + 2));
    }

    public List<KeyBlankItemBasicData> _queryKeyBasicData_BlankList(long j) {
        synchronized (this) {
            if (this.keyBasicData_BlankListQuery == null) {
                QueryBuilder<KeyBlankItemBasicData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FK_KeyID.m466eq(null), new WhereCondition[0]);
                this.keyBasicData_BlankListQuery = queryBuilder.build();
            }
        }
        Query<KeyBlankItemBasicData> forCurrentThread = this.keyBasicData_BlankListQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) Long.valueOf(j));
        return forCurrentThread.list();
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getKeyblankItemDao().getAllColumns());
            sb.append(" FROM KeyBlankItem_BasicData T");
            sb.append(" LEFT JOIN KeyblankItem T0 ON T.\"FK_KeyBlankItemID\"=T0.\"ID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected KeyBlankItemBasicData loadCurrentDeep(Cursor cursor, boolean z) {
        KeyBlankItemBasicData loadCurrent = loadCurrent(cursor, 0, z);
        loadCurrent.setKeyblankItem((KeyblankItem) loadCurrentOther(this.daoSession.getKeyblankItemDao(), cursor, getAllColumns().length));
        return loadCurrent;
    }

    public KeyBlankItemBasicData loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.f1009db.rawQuery(sb.toString(), new String[]{l.toString()});
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

    public List<KeyBlankItemBasicData> loadAllDeepFromCursor(Cursor cursor) {
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

    protected List<KeyBlankItemBasicData> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<KeyBlankItemBasicData> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.f1009db.rawQuery(getSelectDeep() + str, strArr));
    }
}