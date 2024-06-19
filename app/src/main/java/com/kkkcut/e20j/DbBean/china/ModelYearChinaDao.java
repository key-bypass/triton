package com.kkkcut.e20j.DbBean.china;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.liulishuo.filedownloader.model.ConnectionModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ModelYearChinaDao extends AbstractDao<ModelYearChina, Integer> {
    public static final String TABLENAME = "ModelYearChina";
    private DaoSession daoSession;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Integer.TYPE, ConnectionModel.ID, true, BarCodeRemindActivity.ID);
        public static final Property FK_ModelChinaID = new Property(1, Integer.TYPE, "FK_ModelChinaID", false, "FK_ModelChinaID");
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

    public ModelYearChinaDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ModelYearChinaDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ModelYearChina modelYearChina) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, modelYearChina.getId());
        databaseStatement.bindLong(2, modelYearChina.getFK_ModelChinaID());
        String fromYear = modelYearChina.getFromYear();
        if (fromYear != null) {
            databaseStatement.bindString(3, fromYear);
        }
        String toYear = modelYearChina.getToYear();
        if (toYear != null) {
            databaseStatement.bindString(4, toYear);
        }
        databaseStatement.bindLong(5, modelYearChina.getSort());
        String description = modelYearChina.getDescription();
        if (description != null) {
            databaseStatement.bindString(6, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ModelYearChina modelYearChina) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, modelYearChina.getId());
        sQLiteStatement.bindLong(2, modelYearChina.getFK_ModelChinaID());
        String fromYear = modelYearChina.getFromYear();
        if (fromYear != null) {
            sQLiteStatement.bindString(3, fromYear);
        }
        String toYear = modelYearChina.getToYear();
        if (toYear != null) {
            sQLiteStatement.bindString(4, toYear);
        }
        sQLiteStatement.bindLong(5, modelYearChina.getSort());
        String description = modelYearChina.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(6, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(ModelYearChina modelYearChina) {
        this.attachEntity(modelYearChina.id, modelYearChina, false);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Integer readKey(Cursor cursor, int i) {
        return Integer.valueOf(cursor.getInt(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ModelYearChina readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = cursor.getInt(i + 4);
        int i7 = i + 5;
        return new ModelYearChina(i2, i3, string, string2, i6, cursor.isNull(i7) ? null : cursor.getString(i7));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ModelYearChina modelYearChina, int i) {
        modelYearChina.setId(cursor.getInt(i + 0));
        modelYearChina.setFK_ModelChinaID(cursor.getInt(i + 1));
        int i2 = i + 2;
        modelYearChina.setFromYear(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        modelYearChina.setToYear(cursor.isNull(i3) ? null : cursor.getString(i3));
        modelYearChina.setSort(cursor.getInt(i + 4));
        int i4 = i + 5;
        modelYearChina.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Integer updateKeyAfterInsert(ModelYearChina modelYearChina, long j) {
        return Integer.valueOf(modelYearChina.getId());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Integer getKey(ModelYearChina modelYearChina) {
        if (modelYearChina != null) {
            return Integer.valueOf(modelYearChina.getId());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ModelYearChina modelYearChina) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }
}
