package com.kkkcut.e20j.DbBean.userDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class CollectionDataDao extends AbstractDao<CollectionData, Long> {
    public static final String TABLENAME = "COLLECTION_DATA";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property Title = new Property(1, String.class, "title", false, "TITLE");
        public static final Property CodeSeries = new Property(2, String.class, "codeSeries", false, "CODE_SERIES");
        public static final Property KeyChinaNum = new Property(3, String.class, "KeyChinaNum", false, "KEY_CHINA_NUM");
        public static final Property Cuts = new Property(4, String.class, "cuts", false, "CUTS");
        public static final Property BasicDataID = new Property(5, Integer.TYPE, "basicDataID", false, "BASIC_DATA_ID");
        public static final Property SeriesID = new Property(6, Integer.TYPE, "seriesID", false, "SERIES_ID");
        public static final Property ToothCode = new Property(7, String.class, "toothCode", false, "TOOTH_CODE");
        public static final Property Remark = new Property(8, String.class, "remark", false, "REMARK");
        public static final Property Time = new Property(9, Long.TYPE, "time", false, "TIME");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public CollectionDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CollectionDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"COLLECTION_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TITLE\" TEXT,\"CODE_SERIES\" TEXT,\"KEY_CHINA_NUM\" TEXT,\"CUTS\" TEXT,\"BASIC_DATA_ID\" INTEGER NOT NULL ,\"SERIES_ID\" INTEGER NOT NULL ,\"TOOTH_CODE\" TEXT,\"REMARK\" TEXT,\"TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"COLLECTION_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, CollectionData collectionData) {
        databaseStatement.clearBindings();
        Long id = collectionData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String title = collectionData.getTitle();
        if (title != null) {
            databaseStatement.bindString(2, title);
        }
        String codeSeries = collectionData.getCodeSeries();
        if (codeSeries != null) {
            databaseStatement.bindString(3, codeSeries);
        }
        String keyChinaNum = collectionData.getKeyChinaNum();
        if (keyChinaNum != null) {
            databaseStatement.bindString(4, keyChinaNum);
        }
        String cuts = collectionData.getCuts();
        if (cuts != null) {
            databaseStatement.bindString(5, cuts);
        }
        databaseStatement.bindLong(6, collectionData.getBasicDataID());
        databaseStatement.bindLong(7, collectionData.getSeriesID());
        String toothCode = collectionData.getToothCode();
        if (toothCode != null) {
            databaseStatement.bindString(8, toothCode);
        }
        String remark = collectionData.getRemark();
        if (remark != null) {
            databaseStatement.bindString(9, remark);
        }
        databaseStatement.bindLong(10, collectionData.getTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, CollectionData collectionData) {
        sQLiteStatement.clearBindings();
        Long id = collectionData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String title = collectionData.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(2, title);
        }
        String codeSeries = collectionData.getCodeSeries();
        if (codeSeries != null) {
            sQLiteStatement.bindString(3, codeSeries);
        }
        String keyChinaNum = collectionData.getKeyChinaNum();
        if (keyChinaNum != null) {
            sQLiteStatement.bindString(4, keyChinaNum);
        }
        String cuts = collectionData.getCuts();
        if (cuts != null) {
            sQLiteStatement.bindString(5, cuts);
        }
        sQLiteStatement.bindLong(6, collectionData.getBasicDataID());
        sQLiteStatement.bindLong(7, collectionData.getSeriesID());
        String toothCode = collectionData.getToothCode();
        if (toothCode != null) {
            sQLiteStatement.bindString(8, toothCode);
        }
        String remark = collectionData.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(9, remark);
        }
        sQLiteStatement.bindLong(10, collectionData.getTime());
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
    public CollectionData readEntity(Cursor cursor, int i) {
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
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        return new CollectionData(valueOf, string, string2, string3, string4, i7, i8, string5, cursor.isNull(i10) ? null : cursor.getString(i10), cursor.getLong(i + 9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, CollectionData collectionData, int i) {
        int i2 = i + 0;
        collectionData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        collectionData.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        collectionData.setCodeSeries(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        collectionData.setKeyChinaNum(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        collectionData.setCuts(cursor.isNull(i6) ? null : cursor.getString(i6));
        collectionData.setBasicDataID(cursor.getInt(i + 5));
        collectionData.setSeriesID(cursor.getInt(i + 6));
        int i7 = i + 7;
        collectionData.setToothCode(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 8;
        collectionData.setRemark(cursor.isNull(i8) ? null : cursor.getString(i8));
        collectionData.setTime(cursor.getLong(i + 9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(CollectionData collectionData, long j) {
        collectionData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(CollectionData collectionData) {
        if (collectionData != null) {
            return collectionData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(CollectionData collectionData) {
        return collectionData.getId() != null;
    }
}
