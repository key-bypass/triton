package com.kkkcut.e20j.DbBean.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

/* loaded from: classes.dex */
public class SearchModelYearChinaDao extends AbstractDao<SearchModelYearChina, Long> {
    public static final String TABLENAME = "ModelYearChina";
    private DaoSession daoSession;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.TYPE, ConnectionModel.ID, true, BarCodeRemindActivity.ID);
        public static final Property FK_ModelChinaID = new Property(1, Long.TYPE, "FK_ModelChinaID", false, "FK_ModelChinaID");
        public static final Property FromYear = new Property(2, String.class, "fromYear", false, "FromYear");
        public static final Property ToYear = new Property(3, String.class, "toYear", false, "ToYear");
        public static final Property Sort = new Property(4, Integer.TYPE, "sort", false, "Sort");
        public static final Property Description = new Property(5, String.class, "description", false, "Description");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public SearchModelYearChinaDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SearchModelYearChinaDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SearchModelYearChina searchModelYearChina) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, searchModelYearChina.getId());
        databaseStatement.bindLong(2, searchModelYearChina.getFK_ModelChinaID());
        String fromYear = searchModelYearChina.getFromYear();
        if (fromYear != null) {
            databaseStatement.bindString(3, fromYear);
        }
        String toYear = searchModelYearChina.getToYear();
        if (toYear != null) {
            databaseStatement.bindString(4, toYear);
        }
        databaseStatement.bindLong(5, searchModelYearChina.getSort());
        String description = searchModelYearChina.getDescription();
        if (description != null) {
            databaseStatement.bindString(6, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SearchModelYearChina searchModelYearChina) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, searchModelYearChina.getId());
        sQLiteStatement.bindLong(2, searchModelYearChina.getFK_ModelChinaID());
        String fromYear = searchModelYearChina.getFromYear();
        if (fromYear != null) {
            sQLiteStatement.bindString(3, fromYear);
        }
        String toYear = searchModelYearChina.getToYear();
        if (toYear != null) {
            sQLiteStatement.bindString(4, toYear);
        }
        sQLiteStatement.bindLong(5, searchModelYearChina.getSort());
        String description = searchModelYearChina.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(6, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SearchModelYearChina searchModelYearChina) {
        super.attachEntity((SearchModelYearChinaDao) searchModelYearChina);
        searchModelYearChina.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public SearchModelYearChina readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        long j2 = cursor.getLong(i + 1);
        int i2 = i + 2;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 3;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 4);
        int i5 = i + 5;
        return new SearchModelYearChina(j, j2, string, string2, i4, cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SearchModelYearChina searchModelYearChina, int i) {
        searchModelYearChina.setId(cursor.getLong(i + 0));
        searchModelYearChina.setFK_ModelChinaID(cursor.getLong(i + 1));
        int i2 = i + 2;
        searchModelYearChina.setFromYear(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        searchModelYearChina.setToYear(cursor.isNull(i3) ? null : cursor.getString(i3));
        searchModelYearChina.setSort(cursor.getInt(i + 4));
        int i4 = i + 5;
        searchModelYearChina.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SearchModelYearChina searchModelYearChina, long j) {
        searchModelYearChina.setId(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SearchModelYearChina searchModelYearChina) {
        if (searchModelYearChina != null) {
            return Long.valueOf(searchModelYearChina.getId());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SearchModelYearChina searchModelYearChina) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getSearchModelChinaDao().getAllColumns());
            sb.append(" FROM ModelYearChina T");
            sb.append(" LEFT JOIN ModelChina T0 ON T.\"FK_ModelChinaID\"=T0.\"ID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected SearchModelYearChina loadCurrentDeep(Cursor cursor, boolean z) {
        SearchModelYearChina loadCurrent = loadCurrent(cursor, 0, z);
        SearchModelChina searchModelChina = (SearchModelChina) loadCurrentOther(this.daoSession.getSearchModelChinaDao(), cursor, getAllColumns().length);
        if (searchModelChina != null) {
            loadCurrent.setSearchModelChina(searchModelChina);
        }
        return loadCurrent;
    }

    public SearchModelYearChina loadDeep(Long l) {
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

    public List<SearchModelYearChina> loadAllDeepFromCursor(Cursor cursor) {
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

    protected List<SearchModelYearChina> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<SearchModelYearChina> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + str, strArr));
    }
}
