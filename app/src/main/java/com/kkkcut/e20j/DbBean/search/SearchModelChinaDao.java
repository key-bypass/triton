package com.kkkcut.e20j.DbBean.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

/* loaded from: classes.dex */
public class SearchModelChinaDao extends AbstractDao<SearchModelChina, Long> {
    public static final String TABLENAME = "ModelChina";
    private DaoSession daoSession;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelID = new Property(0, Long.TYPE, "modelID", true, BarCodeRemindActivity.ID);
        public static final Property FK_ManufacturerID = new Property(1, Long.TYPE, "fK_ManufacturerID", false, "FK_ManufacturerID");
        public static final Property ModelName = new Property(2, String.class, "modelName", false, "ModelName");
        public static final Property ModelName_CN = new Property(3, String.class, "modelName_CN", false, "ModelName_CN");
        public static final Property Is_visible = new Property(4, Integer.TYPE, "is_visible", false, "is_visible");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public SearchModelChinaDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SearchModelChinaDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SearchModelChina searchModelChina) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, searchModelChina.getModelID());
        databaseStatement.bindLong(2, searchModelChina.getFK_ManufacturerID());
        String modelName = searchModelChina.getModelName();
        if (modelName != null) {
            databaseStatement.bindString(3, modelName);
        }
        String modelName_CN = searchModelChina.getModelName_CN();
        if (modelName_CN != null) {
            databaseStatement.bindString(4, modelName_CN);
        }
        databaseStatement.bindLong(5, searchModelChina.getIs_visible());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SearchModelChina searchModelChina) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, searchModelChina.getModelID());
        sQLiteStatement.bindLong(2, searchModelChina.getFK_ManufacturerID());
        String modelName = searchModelChina.getModelName();
        if (modelName != null) {
            sQLiteStatement.bindString(3, modelName);
        }
        String modelName_CN = searchModelChina.getModelName_CN();
        if (modelName_CN != null) {
            sQLiteStatement.bindString(4, modelName_CN);
        }
        sQLiteStatement.bindLong(5, searchModelChina.getIs_visible());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SearchModelChina searchModelChina) {
        super.attachEntity((SearchModelChinaDao) searchModelChina);
        searchModelChina.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public SearchModelChina readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        long j2 = cursor.getLong(i + 1);
        int i2 = i + 2;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 3;
        return new SearchModelChina(j, j2, string, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SearchModelChina searchModelChina, int i) {
        searchModelChina.setModelID(cursor.getLong(i + 0));
        searchModelChina.setFK_ManufacturerID(cursor.getLong(i + 1));
        int i2 = i + 2;
        searchModelChina.setModelName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        searchModelChina.setModelName_CN(cursor.isNull(i3) ? null : cursor.getString(i3));
        searchModelChina.setIs_visible(cursor.getInt(i + 4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SearchModelChina searchModelChina, long j) {
        searchModelChina.setModelID(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SearchModelChina searchModelChina) {
        if (searchModelChina != null) {
            return Long.valueOf(searchModelChina.getModelID());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SearchModelChina searchModelChina) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getSearchManufacturerDao().getAllColumns());
            sb.append(" FROM ModelChina T");
            sb.append(" LEFT JOIN Manufacturer T0 ON T.\"FK_ManufacturerID\"=T0.\"ID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected SearchModelChina loadCurrentDeep(Cursor cursor, boolean z) {
        SearchModelChina loadCurrent = loadCurrent(cursor, 0, z);
        SearchManufacturer searchManufacturer = (SearchManufacturer) loadCurrentOther(this.daoSession.getSearchManufacturerDao(), cursor, getAllColumns().length);
        if (searchManufacturer != null) {
            loadCurrent.setSearchManufacturer(searchManufacturer);
        }
        return loadCurrent;
    }

    public SearchModelChina loadDeep(Long l) {
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

    public List<SearchModelChina> loadAllDeepFromCursor(Cursor cursor) {
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

    protected List<SearchModelChina> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<SearchModelChina> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + str, strArr));
    }
}
