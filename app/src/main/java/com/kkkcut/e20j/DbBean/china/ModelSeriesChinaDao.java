package com.kkkcut.e20j.DbBean.china;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.ClampKeyBasicData;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
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
public class ModelSeriesChinaDao extends AbstractDao<ModelSeriesChina, Void> {
    public static final String TABLENAME = "ModelSeriesChina";
    private DaoSession daoSession;
    private Query<ModelSeriesChina> modelYearChina_ModelSeriesListQuery;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelYearChinaID = new Property(1, Integer.TYPE, "FK_ModelYearChinaID", false, "FK_ModelYearChinaID");
        public static final Property Name = new Property(2, String.class, "Name", false, "Name");
        public static final Property CodeSeries = new Property(3, String.class, "codeSeries", false, "CodeSeries");
        public static final Property ModelNo = new Property(4, String.class, "modelNo", false, "ModelNo");
        public static final Property FK_KeyID = new Property(5, Long.TYPE, "fK_KeyID", false, "FK_KeyID");
        public static final Property Cuts = new Property(6, String.class, "cuts", false, "Cuts");
        public static final Property Notes = new Property(7, String.class, "Notes", false, "Notes");
        public static final Property Sort = new Property(8, Integer.TYPE, "Sort", false, "Sort");
        public static final Property Remark = new Property(9, String.class, "Remark", false, "Remark");
        public static final Property KeyChinaNum = new Property(10, String.class, "KeyChinaNum", false, "KeyChinaNum");
        public static final Property DistributorNum = new Property(11, String.class, "DistributorNum", false, "DistributorNum");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(ModelSeriesChina modelSeriesChina) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ModelSeriesChina modelSeriesChina) {
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
    public final Void updateKeyAfterInsert(ModelSeriesChina modelSeriesChina, long j) {
        return null;
    }

    public ModelSeriesChinaDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ModelSeriesChinaDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ModelSeriesChina modelSeriesChina) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, modelSeriesChina.getID());
        databaseStatement.bindLong(2, modelSeriesChina.getFK_ModelYearChinaID());
        String name = modelSeriesChina.getName();
        if (name != null) {
            databaseStatement.bindString(3, name);
        }
        String codeSeries = modelSeriesChina.getCodeSeries();
        if (codeSeries != null) {
            databaseStatement.bindString(4, codeSeries);
        }
        String modelNo = modelSeriesChina.getModelNo();
        if (modelNo != null) {
            databaseStatement.bindString(5, modelNo);
        }
        databaseStatement.bindLong(6, modelSeriesChina.getFK_KeyID());
        String cuts = modelSeriesChina.getCuts();
        if (cuts != null) {
            databaseStatement.bindString(7, cuts);
        }
        String notes = modelSeriesChina.getNotes();
        if (notes != null) {
            databaseStatement.bindString(8, notes);
        }
        databaseStatement.bindLong(9, modelSeriesChina.getSort());
        String remark = modelSeriesChina.getRemark();
        if (remark != null) {
            databaseStatement.bindString(10, remark);
        }
        String keyChinaNum = modelSeriesChina.getKeyChinaNum();
        if (keyChinaNum != null) {
            databaseStatement.bindString(11, keyChinaNum);
        }
        String distributorNum = modelSeriesChina.getDistributorNum();
        if (distributorNum != null) {
            databaseStatement.bindString(12, distributorNum);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ModelSeriesChina modelSeriesChina) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, modelSeriesChina.getID());
        sQLiteStatement.bindLong(2, modelSeriesChina.getFK_ModelYearChinaID());
        String name = modelSeriesChina.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String codeSeries = modelSeriesChina.getCodeSeries();
        if (codeSeries != null) {
            sQLiteStatement.bindString(4, codeSeries);
        }
        String modelNo = modelSeriesChina.getModelNo();
        if (modelNo != null) {
            sQLiteStatement.bindString(5, modelNo);
        }
        sQLiteStatement.bindLong(6, modelSeriesChina.getFK_KeyID());
        String cuts = modelSeriesChina.getCuts();
        if (cuts != null) {
            sQLiteStatement.bindString(7, cuts);
        }
        String notes = modelSeriesChina.getNotes();
        if (notes != null) {
            sQLiteStatement.bindString(8, notes);
        }
        sQLiteStatement.bindLong(9, modelSeriesChina.getSort());
        String remark = modelSeriesChina.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(10, remark);
        }
        String keyChinaNum = modelSeriesChina.getKeyChinaNum();
        if (keyChinaNum != null) {
            sQLiteStatement.bindString(11, keyChinaNum);
        }
        String distributorNum = modelSeriesChina.getDistributorNum();
        if (distributorNum != null) {
            sQLiteStatement.bindString(12, distributorNum);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ModelSeriesChina modelSeriesChina) {
        super.attachEntity((ModelSeriesChinaDao) modelSeriesChina);
        modelSeriesChina.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ModelSeriesChina readEntity(Cursor cursor, int i) {
        int i2 = i + 2;
        int i3 = i + 3;
        int i4 = i + 4;
        int i5 = i + 6;
        int i6 = i + 7;
        int i7 = i + 9;
        int i8 = i + 10;
        int i9 = i + 11;
        return new ModelSeriesChina(cursor.getInt(i + 0), cursor.getInt(i + 1), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getLong(i + 5), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getInt(i + 8), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ModelSeriesChina modelSeriesChina, int i) {
        modelSeriesChina.setID(cursor.getInt(i + 0));
        modelSeriesChina.setFK_ModelYearChinaID(cursor.getInt(i + 1));
        int i2 = i + 2;
        modelSeriesChina.setName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        modelSeriesChina.setCodeSeries(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        modelSeriesChina.setModelNo(cursor.isNull(i4) ? null : cursor.getString(i4));
        modelSeriesChina.setFK_KeyID(cursor.getLong(i + 5));
        int i5 = i + 6;
        modelSeriesChina.setCuts(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        modelSeriesChina.setNotes(cursor.isNull(i6) ? null : cursor.getString(i6));
        modelSeriesChina.setSort(cursor.getInt(i + 8));
        int i7 = i + 9;
        modelSeriesChina.setRemark(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 10;
        modelSeriesChina.setKeyChinaNum(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 11;
        modelSeriesChina.setDistributorNum(cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    public List<ModelSeriesChina> _queryModelYearChina_ModelSeriesList(int i) {
        synchronized (this) {
            if (this.modelYearChina_ModelSeriesListQuery == null) {
                QueryBuilder<ModelSeriesChina> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.FK_ModelYearChinaID.eq(null), new WhereCondition[0]);
                this.modelYearChina_ModelSeriesListQuery = queryBuilder.build();
            }
        }
        Query<ModelSeriesChina> forCurrentThread = this.modelYearChina_ModelSeriesListQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) Integer.valueOf(i));
        return forCurrentThread.list();
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getClampKeyBasicDataDao().getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T1", this.daoSession.getKeyBasicDataDao().getAllColumns());
            sb.append(" FROM ModelSeriesChina T");
            sb.append(" LEFT JOIN ClampKeyBasicData T0 ON T.\"FK_KeyID\"=T0.\"FK_KeyID\"");
            sb.append(" LEFT JOIN KeyBasicData T1 ON T.\"FK_KeyID\"=T1.\"ID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected ModelSeriesChina loadCurrentDeep(Cursor cursor, boolean z) {
        ModelSeriesChina loadCurrent = loadCurrent(cursor, 0, z);
        int length = getAllColumns().length;
        ClampKeyBasicData clampKeyBasicData = (ClampKeyBasicData) loadCurrentOther(this.daoSession.getClampKeyBasicDataDao(), cursor, length);
        if (clampKeyBasicData != null) {
            loadCurrent.setClampKeyBasicData(clampKeyBasicData);
        }
        KeyBasicData keyBasicData = (KeyBasicData) loadCurrentOther(this.daoSession.getKeyBasicDataDao(), cursor, length + this.daoSession.getClampKeyBasicDataDao().getAllColumns().length);
        if (keyBasicData != null) {
            loadCurrent.setKeyBasicData(keyBasicData);
        }
        return loadCurrent;
    }

    public ModelSeriesChina loadDeep(Long l) {
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

    public List<ModelSeriesChina> loadAllDeepFromCursor(Cursor cursor) {
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

    protected List<ModelSeriesChina> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<ModelSeriesChina> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + str, strArr));
    }
}
