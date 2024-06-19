package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ModelYearDao extends AbstractDao<ModelYear, Integer> {
    public static final String TABLENAME = "ModelYear";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelYearID = new Property(0, Integer.TYPE, "modelYearID", true, BarCodeRemindActivity.ID);
        public static final Property FK_ModelID = new Property(1, Integer.TYPE, "fK_ModelID", false, "FK_ModelID");
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

    public ModelYearDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ModelYearDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ModelYear modelYear) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, modelYear.getModelYearID());
        databaseStatement.bindLong(2, modelYear.getFK_ModelID());
        String fromYear = modelYear.getFromYear();
        if (fromYear != null) {
            databaseStatement.bindString(3, fromYear);
        }
        String toYear = modelYear.getToYear();
        if (toYear != null) {
            databaseStatement.bindString(4, toYear);
        }
        databaseStatement.bindLong(5, modelYear.getSort());
        String description = modelYear.getDescription();
        if (description != null) {
            databaseStatement.bindString(6, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ModelYear modelYear) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, modelYear.getModelYearID());
        sQLiteStatement.bindLong(2, modelYear.getFK_ModelID());
        String fromYear = modelYear.getFromYear();
        if (fromYear != null) {
            sQLiteStatement.bindString(3, fromYear);
        }
        String toYear = modelYear.getToYear();
        if (toYear != null) {
            sQLiteStatement.bindString(4, toYear);
        }
        sQLiteStatement.bindLong(5, modelYear.getSort());
        String description = modelYear.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(6, description);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Integer readKey(Cursor cursor, int i) {
        return Integer.valueOf(cursor.getInt(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ModelYear readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = cursor.getInt(i + 4);
        int i7 = i + 5;
        return new ModelYear(i2, i3, string, string2, i6, cursor.isNull(i7) ? null : cursor.getString(i7));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ModelYear modelYear, int i) {
        modelYear.setModelYearID(cursor.getInt(i + 0));
        modelYear.setFK_ModelID(cursor.getInt(i + 1));
        int i2 = i + 2;
        modelYear.setFromYear(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        modelYear.setToYear(cursor.isNull(i3) ? null : cursor.getString(i3));
        modelYear.setSort(cursor.getInt(i + 4));
        int i4 = i + 5;
        modelYear.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Integer updateKeyAfterInsert(ModelYear modelYear, long j) {
        return Integer.valueOf(modelYear.getModelYearID());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Integer getKey(ModelYear modelYear) {
        if (modelYear != null) {
            return Integer.valueOf(modelYear.getModelYearID());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ModelYear modelYear) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }
}
