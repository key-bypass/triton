package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyResourceDao extends AbstractDao<KeyResource, Void> {
    public static final String TABLENAME = "KeyRes";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelSeriesID = new Property(1, Integer.TYPE, "FK_ModelSeriesID", false, "FK_ModelSeriesID");
        public static final Property FK_LanguageID = new Property(2, Integer.TYPE, "FK_LanguageID", false, "FK_LanguageID");
        public static final Property ProductTypeID = new Property(3, Integer.TYPE, "ProductTypeID", false, "ProductTypeID");
        public static final Property ExplainHtml = new Property(4, String.class, "ExplainHtml", false, "ExplainHtml");
        public static final Property VideoPath = new Property(5, String.class, "VideoPath", false, "VideoPath");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyResource keyResource) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyResource keyResource) {
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
    public final Void updateKeyAfterInsert(KeyResource keyResource, long j) {
        return null;
    }

    public KeyResourceDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyResourceDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyResource keyResource) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyResource.getID());
        databaseStatement.bindLong(2, keyResource.getFK_ModelSeriesID());
        databaseStatement.bindLong(3, keyResource.getFK_LanguageID());
        databaseStatement.bindLong(4, keyResource.getProductTypeID());
        String explainHtml = keyResource.getExplainHtml();
        if (explainHtml != null) {
            databaseStatement.bindString(5, explainHtml);
        }
        String videoPath = keyResource.getVideoPath();
        if (videoPath != null) {
            databaseStatement.bindString(6, videoPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyResource keyResource) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyResource.getID());
        sQLiteStatement.bindLong(2, keyResource.getFK_ModelSeriesID());
        sQLiteStatement.bindLong(3, keyResource.getFK_LanguageID());
        sQLiteStatement.bindLong(4, keyResource.getProductTypeID());
        String explainHtml = keyResource.getExplainHtml();
        if (explainHtml != null) {
            sQLiteStatement.bindString(5, explainHtml);
        }
        String videoPath = keyResource.getVideoPath();
        if (videoPath != null) {
            sQLiteStatement.bindString(6, videoPath);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyResource readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = i + 4;
        int i7 = i + 5;
        return new KeyResource(i2, i3, i4, i5, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : cursor.getString(i7));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyResource keyResource, int i) {
        keyResource.setID(cursor.getInt(i + 0));
        keyResource.setFK_ModelSeriesID(cursor.getInt(i + 1));
        keyResource.setFK_LanguageID(cursor.getInt(i + 2));
        keyResource.setProductTypeID(cursor.getInt(i + 3));
        int i2 = i + 4;
        keyResource.setExplainHtml(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 5;
        keyResource.setVideoPath(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
