package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ManufacturerDao extends AbstractDao<Manufacturer, Void> {
    public static final String TABLENAME = "Manufacturer";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ManufacturerId = new Property(0, Integer.TYPE, "manufacturerId", false, BarCodeRemindActivity.ID);
        public static final Property ManufacturerName = new Property(1, String.class, "manufacturerName", false, "Name");
        public static final Property ManufacturerNameCN = new Property(2, String.class, "manufacturerNameCN", false, "Name_CN");
        public static final Property Is_automobile = new Property(3, Integer.TYPE, "is_automobile", false, "is_automobile");
        public static final Property Is_motorcycle = new Property(4, Integer.TYPE, "is_motorcycle", false, "is_motorcycle");
        public static final Property Is_dimple = new Property(5, Integer.TYPE, "is_dimple", false, "is_dimple");
        public static final Property Is_tubular = new Property(6, Integer.TYPE, "is_tubular", false, "is_tubular");
        public static final Property Is_standard = new Property(7, Integer.TYPE, "is_standard", false, "is_standard");
        public static final Property Is_automobile_chs = new Property(8, Integer.TYPE, "is_automobile_chs", false, "is_automobile_chs");
        public static final Property Is_visible = new Property(9, Integer.TYPE, "is_visible", false, "is_visible");
        public static final Property Description = new Property(10, String.class, "description", false, "Description");
        public static final Property ManufacturerLogoImage = new Property(11, byte[].class, "manufacturerLogoImage", false, "LogoImage");
        public static final Property DistributorNum = new Property(12, String.class, "distributorNum", false, "DistributorNum");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(Manufacturer manufacturer) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(Manufacturer manufacturer) {
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
    public final Void updateKeyAfterInsert(Manufacturer manufacturer, long j) {
        return null;
    }

    public ManufacturerDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ManufacturerDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, Manufacturer manufacturer) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, manufacturer.getManufacturerId());
        String manufacturerName = manufacturer.getManufacturerName();
        if (manufacturerName != null) {
            databaseStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = manufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            databaseStatement.bindString(3, manufacturerNameCN);
        }
        databaseStatement.bindLong(4, manufacturer.getIs_automobile());
        databaseStatement.bindLong(5, manufacturer.getIs_motorcycle());
        databaseStatement.bindLong(6, manufacturer.getIs_dimple());
        databaseStatement.bindLong(7, manufacturer.getIs_tubular());
        databaseStatement.bindLong(8, manufacturer.getIs_standard());
        databaseStatement.bindLong(9, manufacturer.getIs_automobile_chs());
        databaseStatement.bindLong(10, manufacturer.getIs_visible());
        String description = manufacturer.getDescription();
        if (description != null) {
            databaseStatement.bindString(11, description);
        }
        byte[] manufacturerLogoImage = manufacturer.getManufacturerLogoImage();
        if (manufacturerLogoImage != null) {
            databaseStatement.bindBlob(12, manufacturerLogoImage);
        }
        String distributorNum = manufacturer.getDistributorNum();
        if (distributorNum != null) {
            databaseStatement.bindString(13, distributorNum);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, Manufacturer manufacturer) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, manufacturer.getManufacturerId());
        String manufacturerName = manufacturer.getManufacturerName();
        if (manufacturerName != null) {
            sQLiteStatement.bindString(2, manufacturerName);
        }
        String manufacturerNameCN = manufacturer.getManufacturerNameCN();
        if (manufacturerNameCN != null) {
            sQLiteStatement.bindString(3, manufacturerNameCN);
        }
        sQLiteStatement.bindLong(4, manufacturer.getIs_automobile());
        sQLiteStatement.bindLong(5, manufacturer.getIs_motorcycle());
        sQLiteStatement.bindLong(6, manufacturer.getIs_dimple());
        sQLiteStatement.bindLong(7, manufacturer.getIs_tubular());
        sQLiteStatement.bindLong(8, manufacturer.getIs_standard());
        sQLiteStatement.bindLong(9, manufacturer.getIs_automobile_chs());
        sQLiteStatement.bindLong(10, manufacturer.getIs_visible());
        String description = manufacturer.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(11, description);
        }
        byte[] manufacturerLogoImage = manufacturer.getManufacturerLogoImage();
        if (manufacturerLogoImage != null) {
            sQLiteStatement.bindBlob(12, manufacturerLogoImage);
        }
        String distributorNum = manufacturer.getDistributorNum();
        if (distributorNum != null) {
            sQLiteStatement.bindString(13, distributorNum);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Manufacturer readEntity(Cursor cursor, int i) {
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 10;
        int i5 = i + 11;
        int i6 = i + 12;
        return new Manufacturer(cursor.getInt(i + 0), cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getBlob(i5), cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, Manufacturer manufacturer, int i) {
        manufacturer.setManufacturerId(cursor.getInt(i + 0));
        int i2 = i + 1;
        manufacturer.setManufacturerName(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        manufacturer.setManufacturerNameCN(cursor.isNull(i3) ? null : cursor.getString(i3));
        manufacturer.setIs_automobile(cursor.getInt(i + 3));
        manufacturer.setIs_motorcycle(cursor.getInt(i + 4));
        manufacturer.setIs_dimple(cursor.getInt(i + 5));
        manufacturer.setIs_tubular(cursor.getInt(i + 6));
        manufacturer.setIs_standard(cursor.getInt(i + 7));
        manufacturer.setIs_automobile_chs(cursor.getInt(i + 8));
        manufacturer.setIs_visible(cursor.getInt(i + 9));
        int i4 = i + 10;
        manufacturer.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 11;
        manufacturer.setManufacturerLogoImage(cursor.isNull(i5) ? null : cursor.getBlob(i5));
        int i6 = i + 12;
        manufacturer.setDistributorNum(cursor.isNull(i6) ? null : cursor.getString(i6));
    }
}
