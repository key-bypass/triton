package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyThumbnailDao extends AbstractDao<KeyThumbnail, Void> {
    public static final String TABLENAME = "KeyThumbnail";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_KeyID = new Property(1, Integer.TYPE, "FK_KeyID", false, "FK_KeyID");
        public static final Property ImageFormat1 = new Property(2, byte[].class, "ImageFormat1", false, "ImageFormat1");
        public static final Property ImageFormat2 = new Property(3, byte[].class, "ImageFormat2", false, "ImageFormat2");
        public static final Property ImageFormat3 = new Property(4, byte[].class, "ImageFormat3", false, "ImageFormat3");
        public static final Property ImageFormat4 = new Property(5, byte[].class, "ImageFormat4", false, "ImageFormat4");
        public static final Property ImageFormat5 = new Property(6, byte[].class, "ImageFormat5", false, "ImageFormat5");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyThumbnail keyThumbnail) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyThumbnail keyThumbnail) {
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
    public final Void updateKeyAfterInsert(KeyThumbnail keyThumbnail, long j) {
        return null;
    }

    public KeyThumbnailDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyThumbnailDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyThumbnail keyThumbnail) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyThumbnail.getID());
        databaseStatement.bindLong(2, keyThumbnail.getFK_KeyID());
        byte[] imageFormat1 = keyThumbnail.getImageFormat1();
        if (imageFormat1 != null) {
            databaseStatement.bindBlob(3, imageFormat1);
        }
        byte[] imageFormat2 = keyThumbnail.getImageFormat2();
        if (imageFormat2 != null) {
            databaseStatement.bindBlob(4, imageFormat2);
        }
        byte[] imageFormat3 = keyThumbnail.getImageFormat3();
        if (imageFormat3 != null) {
            databaseStatement.bindBlob(5, imageFormat3);
        }
        byte[] imageFormat4 = keyThumbnail.getImageFormat4();
        if (imageFormat4 != null) {
            databaseStatement.bindBlob(6, imageFormat4);
        }
        byte[] imageFormat5 = keyThumbnail.getImageFormat5();
        if (imageFormat5 != null) {
            databaseStatement.bindBlob(7, imageFormat5);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyThumbnail keyThumbnail) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyThumbnail.getID());
        sQLiteStatement.bindLong(2, keyThumbnail.getFK_KeyID());
        byte[] imageFormat1 = keyThumbnail.getImageFormat1();
        if (imageFormat1 != null) {
            sQLiteStatement.bindBlob(3, imageFormat1);
        }
        byte[] imageFormat2 = keyThumbnail.getImageFormat2();
        if (imageFormat2 != null) {
            sQLiteStatement.bindBlob(4, imageFormat2);
        }
        byte[] imageFormat3 = keyThumbnail.getImageFormat3();
        if (imageFormat3 != null) {
            sQLiteStatement.bindBlob(5, imageFormat3);
        }
        byte[] imageFormat4 = keyThumbnail.getImageFormat4();
        if (imageFormat4 != null) {
            sQLiteStatement.bindBlob(6, imageFormat4);
        }
        byte[] imageFormat5 = keyThumbnail.getImageFormat5();
        if (imageFormat5 != null) {
            sQLiteStatement.bindBlob(7, imageFormat5);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyThumbnail readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        byte[] blob = cursor.isNull(i4) ? null : cursor.getBlob(i4);
        int i5 = i + 3;
        byte[] blob2 = cursor.isNull(i5) ? null : cursor.getBlob(i5);
        int i6 = i + 4;
        byte[] blob3 = cursor.isNull(i6) ? null : cursor.getBlob(i6);
        int i7 = i + 5;
        int i8 = i + 6;
        return new KeyThumbnail(i2, i3, blob, blob2, blob3, cursor.isNull(i7) ? null : cursor.getBlob(i7), cursor.isNull(i8) ? null : cursor.getBlob(i8));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyThumbnail keyThumbnail, int i) {
        keyThumbnail.setID(cursor.getInt(i + 0));
        keyThumbnail.setFK_KeyID(cursor.getInt(i + 1));
        int i2 = i + 2;
        keyThumbnail.setImageFormat1(cursor.isNull(i2) ? null : cursor.getBlob(i2));
        int i3 = i + 3;
        keyThumbnail.setImageFormat2(cursor.isNull(i3) ? null : cursor.getBlob(i3));
        int i4 = i + 4;
        keyThumbnail.setImageFormat3(cursor.isNull(i4) ? null : cursor.getBlob(i4));
        int i5 = i + 5;
        keyThumbnail.setImageFormat4(cursor.isNull(i5) ? null : cursor.getBlob(i5));
        int i6 = i + 6;
        keyThumbnail.setImageFormat5(cursor.isNull(i6) ? null : cursor.getBlob(i6));
    }
}
