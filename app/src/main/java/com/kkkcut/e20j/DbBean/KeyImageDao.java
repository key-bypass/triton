package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyImageDao extends AbstractDao<KeyImage, Void> {
    public static final String TABLENAME = "KEY_IMAGE";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property KeyId = new Property(0, Integer.TYPE, "keyId", false, "KEY_ID");
        public static final Property HashLocal = new Property(1, String.class, "hashLocal", false, "HASH_LOCAL");
        public static final Property Url = new Property(2, String.class, FileDownloadModel.URL, false, "URL");
        public static final Property HashServer = new Property(3, String.class, "hashServer", false, "HASH_SERVER");
        public static final Property LastUpdateTime = new Property(4, Long.TYPE, "LastUpdateTime", false, "LAST_UPDATE_TIME");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyImage keyImage) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyImage keyImage) {
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
    public final Void updateKeyAfterInsert(KeyImage keyImage, long j) {
        return null;
    }

    public KeyImageDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyImageDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"KEY_IMAGE\" (\"KEY_ID\" INTEGER NOT NULL UNIQUE ,\"HASH_LOCAL\" TEXT,\"URL\" TEXT,\"HASH_SERVER\" TEXT,\"LAST_UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"KEY_IMAGE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyImage keyImage) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyImage.getKeyId());
        String hashLocal = keyImage.getHashLocal();
        if (hashLocal != null) {
            databaseStatement.bindString(2, hashLocal);
        }
        String url = keyImage.getUrl();
        if (url != null) {
            databaseStatement.bindString(3, url);
        }
        String hashServer = keyImage.getHashServer();
        if (hashServer != null) {
            databaseStatement.bindString(4, hashServer);
        }
        databaseStatement.bindLong(5, keyImage.getLastUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyImage keyImage) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyImage.getKeyId());
        String hashLocal = keyImage.getHashLocal();
        if (hashLocal != null) {
            sQLiteStatement.bindString(2, hashLocal);
        }
        String url = keyImage.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(3, url);
        }
        String hashServer = keyImage.getHashServer();
        if (hashServer != null) {
            sQLiteStatement.bindString(4, hashServer);
        }
        sQLiteStatement.bindLong(5, keyImage.getLastUpdateTime());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyImage readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        return new KeyImage(i2, string, string2, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getLong(i + 4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyImage keyImage, int i) {
        keyImage.setKeyId(cursor.getInt(i + 0));
        int i2 = i + 1;
        keyImage.setHashLocal(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        keyImage.setUrl(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        keyImage.setHashServer(cursor.isNull(i4) ? null : cursor.getString(i4));
        keyImage.setLastUpdateTime(cursor.getLong(i + 4));
    }
}
