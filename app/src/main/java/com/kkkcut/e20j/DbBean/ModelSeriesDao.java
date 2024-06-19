package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ModelSeriesDao extends AbstractDao<ModelSeries, Void> {
    public static final String TABLENAME = "ModelSeries";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelSeriesID = new Property(0, Integer.TYPE, "modelSeriesID", false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelYearID = new Property(1, Integer.TYPE, "FK_ModelYearID", false, "FK_ModelYearID");
        public static final Property CodeSeries = new Property(2, String.class, "codeSeries", false, "CodeSeries");
        public static final Property Name = new Property(3, String.class, "name", false, "Name");
        public static final Property ModelNo = new Property(4, String.class, "modelNo", false, "ModelNo");
        public static final Property FK_KeyID = new Property(5, Integer.TYPE, "fK_KeyID", false, "FK_KeyID");
        public static final Property Cuts = new Property(6, String.class, "cuts", false, "Cuts");
        public static final Property ISN = new Property(7, String.class, "ISN", false, "ISN");
        public static final Property Notes = new Property(8, String.class, "Notes", false, "notes");
        public static final Property Sort = new Property(9, Integer.TYPE, "Sort", false, "Sort");
        public static final Property Remark = new Property(10, String.class, "Remark", false, "remark");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(ModelSeries modelSeries) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ModelSeries modelSeries) {
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
    public final Void updateKeyAfterInsert(ModelSeries modelSeries, long j) {
        return null;
    }

    public ModelSeriesDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ModelSeriesDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ModelSeries modelSeries) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, modelSeries.getModelSeriesID());
        databaseStatement.bindLong(2, modelSeries.getFK_ModelYearID());
        String codeSeries = modelSeries.getCodeSeries();
        if (codeSeries != null) {
            databaseStatement.bindString(3, codeSeries);
        }
        String name = modelSeries.getName();
        if (name != null) {
            databaseStatement.bindString(4, name);
        }
        String modelNo = modelSeries.getModelNo();
        if (modelNo != null) {
            databaseStatement.bindString(5, modelNo);
        }
        databaseStatement.bindLong(6, modelSeries.getFK_KeyID());
        String cuts = modelSeries.getCuts();
        if (cuts != null) {
            databaseStatement.bindString(7, cuts);
        }
        String isn = modelSeries.getISN();
        if (isn != null) {
            databaseStatement.bindString(8, isn);
        }
        String notes = modelSeries.getNotes();
        if (notes != null) {
            databaseStatement.bindString(9, notes);
        }
        databaseStatement.bindLong(10, modelSeries.getSort());
        String remark = modelSeries.getRemark();
        if (remark != null) {
            databaseStatement.bindString(11, remark);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ModelSeries modelSeries) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, modelSeries.getModelSeriesID());
        sQLiteStatement.bindLong(2, modelSeries.getFK_ModelYearID());
        String codeSeries = modelSeries.getCodeSeries();
        if (codeSeries != null) {
            sQLiteStatement.bindString(3, codeSeries);
        }
        String name = modelSeries.getName();
        if (name != null) {
            sQLiteStatement.bindString(4, name);
        }
        String modelNo = modelSeries.getModelNo();
        if (modelNo != null) {
            sQLiteStatement.bindString(5, modelNo);
        }
        sQLiteStatement.bindLong(6, modelSeries.getFK_KeyID());
        String cuts = modelSeries.getCuts();
        if (cuts != null) {
            sQLiteStatement.bindString(7, cuts);
        }
        String isn = modelSeries.getISN();
        if (isn != null) {
            sQLiteStatement.bindString(8, isn);
        }
        String notes = modelSeries.getNotes();
        if (notes != null) {
            sQLiteStatement.bindString(9, notes);
        }
        sQLiteStatement.bindLong(10, modelSeries.getSort());
        String remark = modelSeries.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(11, remark);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ModelSeries readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = cursor.getInt(i + 5);
        int i8 = i + 6;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = cursor.getInt(i + 9);
        int i12 = i + 10;
        return new ModelSeries(i2, i3, string, string2, string3, i7, string4, string5, string6, i11, cursor.isNull(i12) ? null : cursor.getString(i12));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ModelSeries modelSeries, int i) {
        modelSeries.setModelSeriesID(cursor.getInt(i + 0));
        modelSeries.setFK_ModelYearID(cursor.getInt(i + 1));
        int i2 = i + 2;
        modelSeries.setCodeSeries(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        modelSeries.setName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        modelSeries.setModelNo(cursor.isNull(i4) ? null : cursor.getString(i4));
        modelSeries.setFK_KeyID(cursor.getInt(i + 5));
        int i5 = i + 6;
        modelSeries.setCuts(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        modelSeries.setISN(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 8;
        modelSeries.setNotes(cursor.isNull(i7) ? null : cursor.getString(i7));
        modelSeries.setSort(cursor.getInt(i + 9));
        int i8 = i + 10;
        modelSeries.setRemark(cursor.isNull(i8) ? null : cursor.getString(i8));
    }
}
