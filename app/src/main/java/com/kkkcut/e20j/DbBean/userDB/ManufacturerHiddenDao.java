package com.kkkcut.e20j.DbBean.userDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ManufacturerHiddenDao extends AbstractDao<ManufacturerHidden, Long> {
    public static final String TABLENAME = "ManufacturerHidden";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ManufacturerId = new Property(0, Long.class, "manufacturerId", true, BarCodeRemindActivity.ID);
        public static final Property ManufacturerName = new Property(1, String.class, "manufacturerName", false, "Name");
        public static final Property ManufacturerNameCN = new Property(2, String.class, "manufacturerNameCN", false, "Name_CN");
        public static final Property ManufacturerLogoImage = new Property(3, byte[].class, "manufacturerLogoImage", false, "LogoImage");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public ManufacturerHiddenDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ManufacturerHiddenDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"ManufacturerHidden\" (\"ID\" INTEGER PRIMARY KEY UNIQUE ,\"Name\" TEXT,\"Name_CN\" TEXT,\"LogoImage\" BLOB);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"ManufacturerHidden\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ManufacturerHidden manufacturerHidden) {
        databaseStatement.clearBindings();
        Long manufacturerId = manufacturerHidden.getManufacturerId();
        if (manufacturerId != null) {
            databaseStatement.bindLong(1, manufacturerId.longValue());
        }
        String manufacturerName = manufacturerHidden.getManufacturerName();
        if (manufacturerName != null) {
            databaseStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = manufacturerHidden.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            databaseStatement.bindString(3, manufacturerNameCN);
        }
        byte[] manufacturerLogoImage = manufacturerHidden.getManufacturerLogoImage();
        if (manufacturerLogoImage != null) {
            databaseStatement.bindBlob(4, manufacturerLogoImage);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ManufacturerHidden manufacturerHidden) {
        sQLiteStatement.clearBindings();
        Long manufacturerId = manufacturerHidden.getManufacturerId();
        if (manufacturerId != null) {
            sQLiteStatement.bindLong(1, manufacturerId.longValue());
        }
        String manufacturerName = manufacturerHidden.getManufacturerName();
        if (manufacturerName != null) {
            sQLiteStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = manufacturerHidden.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            sQLiteStatement.bindString(3, manufacturerNameCN);
        }
        byte[] manufacturerLogoImage = manufacturerHidden.getManufacturerLogoImage();
        if (manufacturerLogoImage != null) {
            sQLiteStatement.bindBlob(4, manufacturerLogoImage);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ManufacturerHidden readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        return new ManufacturerHidden(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getBlob(i5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ManufacturerHidden manufacturerHidden, int i) {
        int i2 = i + 0;
        manufacturerHidden.setManufacturerId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        manufacturerHidden.setManufacturerName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        manufacturerHidden.setManufacturerNameCN(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        manufacturerHidden.setManufacturerLogoImage(cursor.isNull(i5) ? null : cursor.getBlob(i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ManufacturerHidden manufacturerHidden, long j) {
        manufacturerHidden.setManufacturerId(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ManufacturerHidden manufacturerHidden) {
        if (manufacturerHidden != null) {
            return manufacturerHidden.getManufacturerId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ManufacturerHidden manufacturerHidden) {
        return manufacturerHidden.getManufacturerId() != null;
    }
}
