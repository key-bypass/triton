package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class LocalDbVersionDao extends AbstractDao<LocalDbVersion, Long> {
    public static final String TABLENAME = "LOCAL_DB_VERSION";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property LocResName = new Property(1, String.class, "LocResName", false, "LOC_RES_NAME");
        public static final Property LocResVer = new Property(2, String.class, "LocResVer", false, "LOC_RES_VER");
        public static final Property SvResName = new Property(3, String.class, "SvResName", false, "SV_RES_NAME");
        public static final Property SvResVer = new Property(4, String.class, "SvResVer", false, "SV_RES_VER");
        public static final Property Url = new Property(5, String.class, FileDownloadModel.URL, false, "URL");
        public static final Property SvHash = new Property(6, String.class, "SvHash", false, "SV_HASH");
        public static final Property ResType = new Property(7, Integer.TYPE, "ResType", false, "RES_TYPE");
        public static final Property UpdateStatus = new Property(8, Integer.TYPE, "updateStatus", false, "UPDATE_STATUS");
        public static final Property LastUpdateTime = new Property(9, Long.TYPE, "LastUpdateTime", false, "LAST_UPDATE_TIME");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public LocalDbVersionDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public LocalDbVersionDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"LOCAL_DB_VERSION\" (\"_id\" INTEGER PRIMARY KEY ,\"LOC_RES_NAME\" TEXT,\"LOC_RES_VER\" TEXT,\"SV_RES_NAME\" TEXT,\"SV_RES_VER\" TEXT,\"URL\" TEXT,\"SV_HASH\" TEXT,\"RES_TYPE\" INTEGER NOT NULL ,\"UPDATE_STATUS\" INTEGER NOT NULL ,\"LAST_UPDATE_TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"LOCAL_DB_VERSION\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, LocalDbVersion localDbVersion) {
        databaseStatement.clearBindings();
        Long id = localDbVersion.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String locResName = localDbVersion.getLocResName();
        if (locResName != null) {
            databaseStatement.bindString(2, locResName);
        }
        String locResVer = localDbVersion.getLocResVer();
        if (locResVer != null) {
            databaseStatement.bindString(3, locResVer);
        }
        String svResName = localDbVersion.getSvResName();
        if (svResName != null) {
            databaseStatement.bindString(4, svResName);
        }
        String svResVer = localDbVersion.getSvResVer();
        if (svResVer != null) {
            databaseStatement.bindString(5, svResVer);
        }
        String url = localDbVersion.getUrl();
        if (url != null) {
            databaseStatement.bindString(6, url);
        }
        String svHash = localDbVersion.getSvHash();
        if (svHash != null) {
            databaseStatement.bindString(7, svHash);
        }
        databaseStatement.bindLong(8, localDbVersion.getResType());
        databaseStatement.bindLong(9, localDbVersion.getUpdateStatus());
        databaseStatement.bindLong(10, localDbVersion.getLastUpdateTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, LocalDbVersion localDbVersion) {
        sQLiteStatement.clearBindings();
        Long id = localDbVersion.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String locResName = localDbVersion.getLocResName();
        if (locResName != null) {
            sQLiteStatement.bindString(2, locResName);
        }
        String locResVer = localDbVersion.getLocResVer();
        if (locResVer != null) {
            sQLiteStatement.bindString(3, locResVer);
        }
        String svResName = localDbVersion.getSvResName();
        if (svResName != null) {
            sQLiteStatement.bindString(4, svResName);
        }
        String svResVer = localDbVersion.getSvResVer();
        if (svResVer != null) {
            sQLiteStatement.bindString(5, svResVer);
        }
        String url = localDbVersion.getUrl();
        if (url != null) {
            sQLiteStatement.bindString(6, url);
        }
        String svHash = localDbVersion.getSvHash();
        if (svHash != null) {
            sQLiteStatement.bindString(7, svHash);
        }
        sQLiteStatement.bindLong(8, localDbVersion.getResType());
        sQLiteStatement.bindLong(9, localDbVersion.getUpdateStatus());
        sQLiteStatement.bindLong(10, localDbVersion.getLastUpdateTime());
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
    public LocalDbVersion readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string5 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        return new LocalDbVersion(valueOf, string, string2, string3, string4, string5, cursor.isNull(i8) ? null : cursor.getString(i8), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getLong(i + 9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, LocalDbVersion localDbVersion, int i) {
        int i2 = i + 0;
        localDbVersion.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        localDbVersion.setLocResName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        localDbVersion.setLocResVer(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        localDbVersion.setSvResName(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        localDbVersion.setSvResVer(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        localDbVersion.setUrl(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        localDbVersion.setSvHash(cursor.isNull(i8) ? null : cursor.getString(i8));
        localDbVersion.setResType(cursor.getInt(i + 7));
        localDbVersion.setUpdateStatus(cursor.getInt(i + 8));
        localDbVersion.setLastUpdateTime(cursor.getLong(i + 9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(LocalDbVersion localDbVersion, long j) {
        localDbVersion.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(LocalDbVersion localDbVersion) {
        if (localDbVersion != null) {
            return localDbVersion.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(LocalDbVersion localDbVersion) {
        return localDbVersion.getId() != null;
    }
}
