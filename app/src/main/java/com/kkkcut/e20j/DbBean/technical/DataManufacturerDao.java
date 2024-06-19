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
public class DataManufacturerDao extends AbstractDao<DataManufacturer, Void> {
    public static final String TABLENAME = "Data_Manufacturer";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ManufacturerId = new Property(0, Integer.TYPE, "manufacturerId", false, BarCodeRemindActivity.ID);
        public static final Property ManufacturerName = new Property(1, String.class, "manufacturerName", false, "Name");
        public static final Property ManufacturerNameCN = new Property(2, String.class, "manufacturerNameCN", false, "Name_CN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DataManufacturer dataManufacturer) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataManufacturer dataManufacturer) {
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
    public final Void updateKeyAfterInsert(DataManufacturer dataManufacturer, long j) {
        return null;
    }

    public DataManufacturerDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DataManufacturerDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataManufacturer dataManufacturer) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, dataManufacturer.getManufacturerId());
        String manufacturerName = dataManufacturer.getManufacturerName();
        if (manufacturerName != null) {
            databaseStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = dataManufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            databaseStatement.bindString(3, manufacturerNameCN);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataManufacturer dataManufacturer) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, dataManufacturer.getManufacturerId());
        String manufacturerName = dataManufacturer.getManufacturerName();
        if (manufacturerName != null) {
            sQLiteStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = dataManufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            sQLiteStatement.bindString(3, manufacturerNameCN);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataManufacturer readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = i + 1;
        int i4 = i + 2;
        return new DataManufacturer(i2, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataManufacturer dataManufacturer, int i) {
        dataManufacturer.setManufacturerId(cursor.getInt(i + 0));
        int i2 = i + 1;
        dataManufacturer.setManufacturerName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        dataManufacturer.setManufacturerNameCN(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
