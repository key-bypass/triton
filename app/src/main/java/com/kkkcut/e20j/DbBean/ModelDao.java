package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ModelDao extends AbstractDao<Model, Void> {
    public static final String TABLENAME = "Model";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ModelID = new Property(0, Integer.TYPE, "modelID", false, BarCodeRemindActivity.ID);
        public static final Property FK_ManufacturerID = new Property(1, Integer.TYPE, "fK_ManufacturerID", false, "FK_ManufacturerID");
        public static final Property ModelName = new Property(2, String.class, "modelName", false, "ModelName");
        public static final Property ModelName_CN = new Property(3, String.class, "modelName_CN", false, "ModelName_CN");
        public static final Property Description = new Property(4, String.class, "description", false, "Description");
        public static final Property Is_visible = new Property(5, Integer.TYPE, "is_visible", false, "is_visible");
        public static final Property DistributorNum = new Property(6, String.class, "distributorNum", false, "DistributorNum");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(Model model) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(Model model) {
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
    public final Void updateKeyAfterInsert(Model model, long j) {
        return null;
    }

    public ModelDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ModelDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, Model model) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, model.getModelID());
        databaseStatement.bindLong(2, model.getFK_ManufacturerID());
        String modelName = model.getModelName();
        if (modelName != null) {
            databaseStatement.bindString(3, modelName);
        }
        String modelName_CN = model.getModelName_CN();
        if (modelName_CN != null) {
            databaseStatement.bindString(4, modelName_CN);
        }
        String description = model.getDescription();
        if (description != null) {
            databaseStatement.bindString(5, description);
        }
        databaseStatement.bindLong(6, model.getIs_visible());
        String distributorNum = model.getDistributorNum();
        if (distributorNum != null) {
            databaseStatement.bindString(7, distributorNum);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, Model model) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, model.getModelID());
        sQLiteStatement.bindLong(2, model.getFK_ManufacturerID());
        String modelName = model.getModelName();
        if (modelName != null) {
            sQLiteStatement.bindString(3, modelName);
        }
        String modelName_CN = model.getModelName_CN();
        if (modelName_CN != null) {
            sQLiteStatement.bindString(4, modelName_CN);
        }
        String description = model.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(5, description);
        }
        sQLiteStatement.bindLong(6, model.getIs_visible());
        String distributorNum = model.getDistributorNum();
        if (distributorNum != null) {
            sQLiteStatement.bindString(7, distributorNum);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Model readEntity(Cursor cursor, int i) {
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
        return new Model(i2, i3, string, string2, string3, i7, cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, Model model, int i) {
        model.setModelID(cursor.getInt(i + 0));
        model.setFK_ManufacturerID(cursor.getInt(i + 1));
        int i2 = i + 2;
        model.setModelName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        model.setModelName_CN(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        model.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
        model.setIs_visible(cursor.getInt(i + 5));
        int i5 = i + 6;
        model.setDistributorNum(cursor.isNull(i5) ? null : cursor.getString(i5));
    }
}
