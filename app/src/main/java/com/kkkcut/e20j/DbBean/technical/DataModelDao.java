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
public class DataModelDao extends AbstractDao<DataModel, Void> {
    public static final String TABLENAME = "Data_Model";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelID = new Property(0, Integer.TYPE, "modelID", false, BarCodeRemindActivity.ID);
        public static final Property FK_ManufacturerID = new Property(1, Integer.TYPE, "fK_ManufacturerID", false, "FK_ManufacturerID");
        public static final Property ModelName = new Property(2, String.class, "modelName", false, "Name");
        public static final Property ModelName_CN = new Property(3, String.class, "modelName_CN", false, "Name_CN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DataModel dataModel) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataModel dataModel) {
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
    public final Void updateKeyAfterInsert(DataModel dataModel, long j) {
        return null;
    }

    public DataModelDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DataModelDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataModel dataModel) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, dataModel.getModelID());
        databaseStatement.bindLong(2, dataModel.getFK_ManufacturerID());
        String modelName = dataModel.getModelName();
        if (modelName != null) {
            databaseStatement.bindString(3, modelName);
        }
        String modelName_CN = dataModel.getModelName_CN();
        if (modelName_CN != null) {
            databaseStatement.bindString(4, modelName_CN);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataModel dataModel) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, dataModel.getModelID());
        sQLiteStatement.bindLong(2, dataModel.getFK_ManufacturerID());
        String modelName = dataModel.getModelName();
        if (modelName != null) {
            sQLiteStatement.bindString(3, modelName);
        }
        String modelName_CN = dataModel.getModelName_CN();
        if (modelName_CN != null) {
            sQLiteStatement.bindString(4, modelName_CN);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataModel readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        int i5 = i + 3;
        return new DataModel(i2, i3, cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataModel dataModel, int i) {
        dataModel.setModelID(cursor.getInt(i + 0));
        dataModel.setFK_ManufacturerID(cursor.getInt(i + 1));
        int i2 = i + 2;
        dataModel.setModelName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        dataModel.setModelName_CN(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
