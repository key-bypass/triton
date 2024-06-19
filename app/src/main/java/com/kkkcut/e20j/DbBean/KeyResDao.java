package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyResDao extends AbstractDao<KeyRes, Void> {
    public static final String TABLENAME = "KeyRes";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelSeriesID = new Property(1, Integer.TYPE, "FK_ModelSeriesID", false, "FK_ModelSeriesID");
        public static final Property FK_LanguageID = new Property(2, Integer.TYPE, "FK_LanguageID", false, "FK_LanguageID");
        public static final Property ExplainHtml = new Property(3, byte[].class, "ExplainHtml", false, "ExplainHtml");
        public static final Property VideoPath = new Property(4, String.class, "VideoPath", false, "VideoPath");
        public static final Property Description = new Property(5, String.class, "Description", false, "Description");
        public static final Property ProductTypeID = new Property(6, Integer.TYPE, "ProductTypeID", false, "ProductTypeID");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyRes keyRes) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyRes keyRes) {
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
    public final Void updateKeyAfterInsert(KeyRes keyRes, long j) {
        return null;
    }

    public KeyResDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyResDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyRes keyRes) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyRes.getID());
        databaseStatement.bindLong(2, keyRes.getFK_ModelSeriesID());
        databaseStatement.bindLong(3, keyRes.getFK_LanguageID());
        byte[] explainHtml = keyRes.getExplainHtml();
        if (explainHtml != null) {
            databaseStatement.bindBlob(4, explainHtml);
        }
        String videoPath = keyRes.getVideoPath();
        if (videoPath != null) {
            databaseStatement.bindString(5, videoPath);
        }
        String description = keyRes.getDescription();
        if (description != null) {
            databaseStatement.bindString(6, description);
        }
        databaseStatement.bindLong(7, keyRes.getProductTypeID());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyRes keyRes) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyRes.getID());
        sQLiteStatement.bindLong(2, keyRes.getFK_ModelSeriesID());
        sQLiteStatement.bindLong(3, keyRes.getFK_LanguageID());
        byte[] explainHtml = keyRes.getExplainHtml();
        if (explainHtml != null) {
            sQLiteStatement.bindBlob(4, explainHtml);
        }
        String videoPath = keyRes.getVideoPath();
        if (videoPath != null) {
            sQLiteStatement.bindString(5, videoPath);
        }
        String description = keyRes.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(6, description);
        }
        sQLiteStatement.bindLong(7, keyRes.getProductTypeID());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyRes readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = i + 3;
        byte[] blob = cursor.isNull(i5) ? null : cursor.getBlob(i5);
        int i6 = i + 4;
        String string = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        return new KeyRes(i2, i3, i4, blob, string, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getInt(i + 6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyRes keyRes, int i) {
        keyRes.setID(cursor.getInt(i + 0));
        keyRes.setFK_ModelSeriesID(cursor.getInt(i + 1));
        keyRes.setFK_LanguageID(cursor.getInt(i + 2));
        int i2 = i + 3;
        keyRes.setExplainHtml(cursor.isNull(i2) ? null : cursor.getBlob(i2));
        int i3 = i + 4;
        keyRes.setVideoPath(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        keyRes.setDescription(cursor.isNull(i4) ? null : cursor.getString(i4));
        keyRes.setProductTypeID(cursor.getInt(i + 6));
    }
}
