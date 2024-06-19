package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.liulishuo.filedownloader.model.ConnectionModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ClampKeyBasicDataDao extends AbstractDao<ClampKeyBasicData, Long> {
    public static final String TABLENAME = "ClampKeyBasicData";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Integer.TYPE, ConnectionModel.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_KeyID = new Property(1, Long.class, "FK_KeyID", true, "FK_KeyID");
        public static final Property ClampNum = new Property(2, String.class, "ClampNum", false, "ClampNum");
        public static final Property ClampSide = new Property(3, String.class, "ClampSide", false, "ClampSide");
        public static final Property ClampSlot = new Property(4, String.class, "ClampSlot", false, "ClampSlot");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public ClampKeyBasicDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ClampKeyBasicDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ClampKeyBasicData clampKeyBasicData) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, clampKeyBasicData.getId());
        Long fK_KeyID = clampKeyBasicData.getFK_KeyID();
        if (fK_KeyID != null) {
            databaseStatement.bindLong(2, fK_KeyID.longValue());
        }
        String clampNum = clampKeyBasicData.getClampNum();
        if (clampNum != null) {
            databaseStatement.bindString(3, clampNum);
        }
        String clampSide = clampKeyBasicData.getClampSide();
        if (clampSide != null) {
            databaseStatement.bindString(4, clampSide);
        }
        String clampSlot = clampKeyBasicData.getClampSlot();
        if (clampSlot != null) {
            databaseStatement.bindString(5, clampSlot);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ClampKeyBasicData clampKeyBasicData) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, clampKeyBasicData.getId());
        Long fK_KeyID = clampKeyBasicData.getFK_KeyID();
        if (fK_KeyID != null) {
            sQLiteStatement.bindLong(2, fK_KeyID.longValue());
        }
        String clampNum = clampKeyBasicData.getClampNum();
        if (clampNum != null) {
            sQLiteStatement.bindString(3, clampNum);
        }
        String clampSide = clampKeyBasicData.getClampSide();
        if (clampSide != null) {
            sQLiteStatement.bindString(4, clampSide);
        }
        String clampSlot = clampKeyBasicData.getClampSlot();
        if (clampSlot != null) {
            sQLiteStatement.bindString(5, clampSlot);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 1;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ClampKeyBasicData readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = i + 1;
        Long valueOf = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        int i6 = i + 4;
        return new ClampKeyBasicData(i2, valueOf, string, cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ClampKeyBasicData clampKeyBasicData, int i) {
        clampKeyBasicData.setId(cursor.getInt(i + 0));
        int i2 = i + 1;
        clampKeyBasicData.setFK_KeyID(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 2;
        clampKeyBasicData.setClampNum(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        clampKeyBasicData.setClampSide(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        clampKeyBasicData.setClampSlot(cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(ClampKeyBasicData clampKeyBasicData, long j) {
        clampKeyBasicData.setFK_KeyID(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(ClampKeyBasicData clampKeyBasicData) {
        if (clampKeyBasicData != null) {
            return clampKeyBasicData.getFK_KeyID();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ClampKeyBasicData clampKeyBasicData) {
        return clampKeyBasicData.getFK_KeyID() != null;
    }
}
