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
public class DataModelSeriesDao extends AbstractDao<DataModelSeries, Void> {
    public static final String TABLENAME = "Data_ModelSeries";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelSeriesID = new Property(0, Integer.TYPE, "modelSeriesID", false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelID = new Property(1, Integer.TYPE, "fK_ModelID", false, "FK_ModelID");
        public static final Property SeriesName = new Property(2, String.class, "seriesName", false, "Name");
        public static final Property SeriesName_CN = new Property(3, String.class, "seriesName_CN", false, "Name_CN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DataModelSeries dataModelSeries) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataModelSeries dataModelSeries) {
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
    public final Void updateKeyAfterInsert(DataModelSeries dataModelSeries, long j) {
        return null;
    }

    public DataModelSeriesDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DataModelSeriesDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataModelSeries dataModelSeries) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, dataModelSeries.getModelSeriesID());
        databaseStatement.bindLong(2, dataModelSeries.getFK_ModelID());
        String seriesName = dataModelSeries.getSeriesName();
        if (seriesName != null) {
            databaseStatement.bindString(3, seriesName);
        }
        String seriesName_CN = dataModelSeries.getSeriesName_CN();
        if (seriesName_CN != null) {
            databaseStatement.bindString(4, seriesName_CN);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataModelSeries dataModelSeries) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, dataModelSeries.getModelSeriesID());
        sQLiteStatement.bindLong(2, dataModelSeries.getFK_ModelID());
        String seriesName = dataModelSeries.getSeriesName();
        if (seriesName != null) {
            sQLiteStatement.bindString(3, seriesName);
        }
        String seriesName_CN = dataModelSeries.getSeriesName_CN();
        if (seriesName_CN != null) {
            sQLiteStatement.bindString(4, seriesName_CN);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataModelSeries readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        int i5 = i + 3;
        return new DataModelSeries(i2, i3, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataModelSeries dataModelSeries, int i) {
        dataModelSeries.setModelSeriesID(cursor.getInt(i + 0));
        dataModelSeries.setFK_ModelID(cursor.getInt(i + 1));
        int i2 = i + 2;
        dataModelSeries.setSeriesName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        dataModelSeries.setSeriesName_CN(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
