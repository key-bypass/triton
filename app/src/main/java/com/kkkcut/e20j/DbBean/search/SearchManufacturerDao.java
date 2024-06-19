package com.kkkcut.e20j.DbBean.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class SearchManufacturerDao extends AbstractDao<SearchManufacturer, Long> {
    public static final String TABLENAME = "Manufacturer";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ManufacturerId = new Property(0, Long.TYPE, "manufacturerId", true, BarCodeRemindActivity.ID);
        public static final Property ManufacturerName = new Property(1, String.class, "manufacturerName", false, "Name");
        public static final Property ManufacturerNameCN = new Property(2, String.class, "manufacturerNameCN", false, "Name_CN");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public SearchManufacturerDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SearchManufacturerDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SearchManufacturer searchManufacturer) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, searchManufacturer.getManufacturerId());
        String manufacturerName = searchManufacturer.getManufacturerName();
        if (manufacturerName != null) {
            databaseStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = searchManufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            databaseStatement.bindString(3, manufacturerNameCN);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SearchManufacturer searchManufacturer) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, searchManufacturer.getManufacturerId());
        String manufacturerName = searchManufacturer.getManufacturerName();
        if (manufacturerName != null) {
            sQLiteStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = searchManufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            sQLiteStatement.bindString(3, manufacturerNameCN);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public SearchManufacturer readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        int i2 = i + 1;
        int i3 = i + 2;
        return new SearchManufacturer(j, cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SearchManufacturer searchManufacturer, int i) {
        searchManufacturer.setManufacturerId(cursor.getLong(i + 0));
        int i2 = i + 1;
        searchManufacturer.setManufacturerName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        searchManufacturer.setManufacturerNameCN(cursor.isNull(i3) ? null : cursor.getString(i3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SearchManufacturer searchManufacturer, long j) {
        searchManufacturer.setManufacturerId(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SearchManufacturer searchManufacturer) {
        if (searchManufacturer != null) {
            return Long.valueOf(searchManufacturer.getManufacturerId());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SearchManufacturer searchManufacturer) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }
}
