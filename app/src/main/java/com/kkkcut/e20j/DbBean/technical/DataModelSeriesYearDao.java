package com.kkkcut.e20j.DbBean.technical;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DataModelSeriesYearDao extends AbstractDao<DataModelSeriesYear, Void> {
    public static final String TABLENAME = "Data_ModelSeriesYear";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelSeriesYearID = new Property(0, Integer.TYPE, "modelSeriesYearID", false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelSeries = new Property(1, Integer.TYPE, "fK_ModelSeries", false, "FK_ModelSeries");
        public static final Property SeriesYearName = new Property(2, String.class, "seriesYearName", false, "Name");
        public static final Property SeriesYearName_CN = new Property(3, String.class, "seriesYearName_CN", false, "Name_CN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DataModelSeriesYear dataModelSeriesYear) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataModelSeriesYear dataModelSeriesYear) {
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
    public final Void updateKeyAfterInsert(DataModelSeriesYear dataModelSeriesYear, long j) {
        return null;
    }

    public DataModelSeriesYearDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DataModelSeriesYearDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataModelSeriesYear dataModelSeriesYear) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, dataModelSeriesYear.getModelSeriesYearID());
        databaseStatement.bindLong(2, dataModelSeriesYear.getFK_ModelSeries());
        String seriesYearName = dataModelSeriesYear.getSeriesYearName();
        if (seriesYearName != null) {
            databaseStatement.bindString(3, seriesYearName);
        }
        String seriesYearName_CN = dataModelSeriesYear.getSeriesYearName_CN();
        if (seriesYearName_CN != null) {
            databaseStatement.bindString(4, seriesYearName_CN);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataModelSeriesYear dataModelSeriesYear) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, dataModelSeriesYear.getModelSeriesYearID());
        sQLiteStatement.bindLong(2, dataModelSeriesYear.getFK_ModelSeries());
        String seriesYearName = dataModelSeriesYear.getSeriesYearName();
        if (seriesYearName != null) {
            sQLiteStatement.bindString(3, seriesYearName);
        }
        String seriesYearName_CN = dataModelSeriesYear.getSeriesYearName_CN();
        if (seriesYearName_CN != null) {
            sQLiteStatement.bindString(4, seriesYearName_CN);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataModelSeriesYear readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        int i5 = i + 3;
        return new DataModelSeriesYear(i2, i3, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataModelSeriesYear dataModelSeriesYear, int i) {
        dataModelSeriesYear.setModelSeriesYearID(cursor.getInt(i + 0));
        dataModelSeriesYear.setFK_ModelSeries(cursor.getInt(i + 1));
        int i2 = i + 2;
        dataModelSeriesYear.setSeriesYearName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        dataModelSeriesYear.setSeriesYearName_CN(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
