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
public class CutHistoryDataDao extends AbstractDao<CutHistoryData, Long> {
    public static final String TABLENAME = "CUT_HISTORY_DATA";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property Title = new Property(1, String.class, "title", false, "TITLE");
        public static final Property CodeSeries = new Property(2, String.class, "codeSeries", false, "CODE_SERIES");
        public static final Property Cuts = new Property(3, String.class, "cuts", false, "CUTS");
        public static final Property BasicDataID = new Property(4, Integer.TYPE, "basicDataID", false, "BASIC_DATA_ID");
        public static final Property KeyChinaNum = new Property(5, String.class, "KeyChinaNum", false, "KEY_CHINA_NUM");
        public static final Property SeriesID = new Property(6, Integer.TYPE, "seriesID", false, "SERIES_ID");
        public static final Property ToothCode = new Property(7, String.class, "toothCode", false, "TOOTH_CODE");
        public static final Property ToothCodeSide = new Property(8, String.class, "toothCodeSide", false, "TOOTH_CODE_SIDE");
        public static final Property IsCustomKey = new Property(9, Integer.TYPE, "isCustomKey", false, "IS_CUSTOM_KEY");
        public static final Property Time = new Property(10, Long.TYPE, "time", false, "TIME");
        public static final Property DoorIgnition = new Property(11, Integer.TYPE, "doorIgnition", false, "DOOR_IGNITION");
        public static final Property DoorToIgnition = new Property(12, Integer.TYPE, "doorToIgnition", false, "DOOR_TO_IGNITION");
        public static final Property Remark = new Property(13, String.class, "remark", false, "REMARK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public CutHistoryDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CutHistoryDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"CUT_HISTORY_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TITLE\" TEXT,\"CODE_SERIES\" TEXT,\"CUTS\" TEXT,\"BASIC_DATA_ID\" INTEGER NOT NULL ,\"KEY_CHINA_NUM\" TEXT,\"SERIES_ID\" INTEGER NOT NULL ,\"TOOTH_CODE\" TEXT,\"TOOTH_CODE_SIDE\" TEXT,\"IS_CUSTOM_KEY\" INTEGER NOT NULL ,\"TIME\" INTEGER NOT NULL ,\"DOOR_IGNITION\" INTEGER NOT NULL ,\"DOOR_TO_IGNITION\" INTEGER NOT NULL ,\"REMARK\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"CUT_HISTORY_DATA\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, CutHistoryData cutHistoryData) {
        databaseStatement.clearBindings();
        Long id = cutHistoryData.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String title = cutHistoryData.getTitle();
        if (title != null) {
            databaseStatement.bindString(2, title);
        }
        String codeSeries = cutHistoryData.getCodeSeries();
        if (codeSeries != null) {
            databaseStatement.bindString(3, codeSeries);
        }
        String cuts = cutHistoryData.getCuts();
        if (cuts != null) {
            databaseStatement.bindString(4, cuts);
        }
        databaseStatement.bindLong(5, cutHistoryData.getBasicDataID());
        String keyChinaNum = cutHistoryData.getKeyChinaNum();
        if (keyChinaNum != null) {
            databaseStatement.bindString(6, keyChinaNum);
        }
        databaseStatement.bindLong(7, cutHistoryData.getSeriesID());
        String toothCode = cutHistoryData.getToothCode();
        if (toothCode != null) {
            databaseStatement.bindString(8, toothCode);
        }
        String toothCodeSide = cutHistoryData.getToothCodeSide();
        if (toothCodeSide != null) {
            databaseStatement.bindString(9, toothCodeSide);
        }
        databaseStatement.bindLong(10, cutHistoryData.getIsCustomKey());
        databaseStatement.bindLong(11, cutHistoryData.getTime());
        databaseStatement.bindLong(12, cutHistoryData.getDoorIgnition());
        databaseStatement.bindLong(13, cutHistoryData.getDoorToIgnition());
        String remark = cutHistoryData.getRemark();
        if (remark != null) {
            databaseStatement.bindString(14, remark);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, CutHistoryData cutHistoryData) {
        sQLiteStatement.clearBindings();
        Long id = cutHistoryData.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String title = cutHistoryData.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(2, title);
        }
        String codeSeries = cutHistoryData.getCodeSeries();
        if (codeSeries != null) {
            sQLiteStatement.bindString(3, codeSeries);
        }
        String cuts = cutHistoryData.getCuts();
        if (cuts != null) {
            sQLiteStatement.bindString(4, cuts);
        }
        sQLiteStatement.bindLong(5, cutHistoryData.getBasicDataID());
        String keyChinaNum = cutHistoryData.getKeyChinaNum();
        if (keyChinaNum != null) {
            sQLiteStatement.bindString(6, keyChinaNum);
        }
        sQLiteStatement.bindLong(7, cutHistoryData.getSeriesID());
        String toothCode = cutHistoryData.getToothCode();
        if (toothCode != null) {
            sQLiteStatement.bindString(8, toothCode);
        }
        String toothCodeSide = cutHistoryData.getToothCodeSide();
        if (toothCodeSide != null) {
            sQLiteStatement.bindString(9, toothCodeSide);
        }
        sQLiteStatement.bindLong(10, cutHistoryData.getIsCustomKey());
        sQLiteStatement.bindLong(11, cutHistoryData.getTime());
        sQLiteStatement.bindLong(12, cutHistoryData.getDoorIgnition());
        sQLiteStatement.bindLong(13, cutHistoryData.getDoorToIgnition());
        String remark = cutHistoryData.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(14, remark);
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
    public CutHistoryData readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        int i6 = i + 5;
        int i7 = i + 7;
        int i8 = i + 8;
        int i9 = i + 13;
        return new CutHistoryData(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.getInt(i + 4), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getInt(i + 6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.getInt(i + 9), cursor.getLong(i + 10), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, CutHistoryData cutHistoryData, int i) {
        int i2 = i + 0;
        cutHistoryData.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        cutHistoryData.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        cutHistoryData.setCodeSeries(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        cutHistoryData.setCuts(cursor.isNull(i5) ? null : cursor.getString(i5));
        cutHistoryData.setBasicDataID(cursor.getInt(i + 4));
        int i6 = i + 5;
        cutHistoryData.setKeyChinaNum(cursor.isNull(i6) ? null : cursor.getString(i6));
        cutHistoryData.setSeriesID(cursor.getInt(i + 6));
        int i7 = i + 7;
        cutHistoryData.setToothCode(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 8;
        cutHistoryData.setToothCodeSide(cursor.isNull(i8) ? null : cursor.getString(i8));
        cutHistoryData.setIsCustomKey(cursor.getInt(i + 9));
        cutHistoryData.setTime(cursor.getLong(i + 10));
        cutHistoryData.setDoorIgnition(cursor.getInt(i + 11));
        cutHistoryData.setDoorToIgnition(cursor.getInt(i + 12));
        int i9 = i + 13;
        cutHistoryData.setRemark(cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(CutHistoryData cutHistoryData, long j) {
        cutHistoryData.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(CutHistoryData cutHistoryData) {
        if (cutHistoryData != null) {
            return cutHistoryData.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(CutHistoryData cutHistoryData) {
        return cutHistoryData.getId() != null;
    }
}
