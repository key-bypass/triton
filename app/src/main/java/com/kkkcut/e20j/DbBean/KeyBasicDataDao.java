package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

/* loaded from: classes.dex */
public class KeyBasicDataDao extends AbstractDao<KeyBasicData, Long> {
    public static final String TABLENAME = "KeyBasicData";
    private DaoSession daoSession;
    private String selectDeep;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property IcCard = new Property(0, Long.TYPE, "icCard", true, BarCodeRemindActivity.ID);
        public static final Property KeyTypeItemID = new Property(1, Integer.TYPE, "KeyTypeItemID", false, "KeyTypeItemID");
        public static final Property Type = new Property(2, Integer.TYPE, "type", false, "type");
        public static final Property Align = new Property(3, Integer.TYPE, "align", false, "align");
        public static final Property Width = new Property(4, Integer.TYPE, "width", false, "width");
        public static final Property Thick = new Property(5, Integer.TYPE, "thick", false, "thick");
        public static final Property Length = new Property(6, Integer.TYPE, IgnitionDoorSearchFragment.LENGTH, false, IgnitionDoorSearchFragment.LENGTH);
        public static final Property Row_count = new Property(7, Integer.TYPE, "row_count", false, "row_count");
        public static final Property Face = new Property(8, Integer.TYPE, "face", false, "face");
        public static final Property Row_pos = new Property(9, String.class, "row_pos", false, "row_pos");
        public static final Property Space = new Property(10, String.class, "space", false, "space");
        public static final Property Space_width = new Property(11, String.class, "space_width", false, "space_width");
        public static final Property Depth = new Property(12, String.class, "depth", false, "depth");
        public static final Property Depth_name = new Property(13, String.class, "depth_name", false, "depth_name");
        public static final Property Parameter_info = new Property(14, String.class, "parameter_info", false, "parameter_info");
        public static final Property Description = new Property(15, String.class, "Description", false, "Description");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KeyBasicDataDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyBasicDataDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyBasicData keyBasicData) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyBasicData.getIcCard());
        databaseStatement.bindLong(2, keyBasicData.getKeyTypeItemID());
        databaseStatement.bindLong(3, keyBasicData.getType());
        databaseStatement.bindLong(4, keyBasicData.getAlign());
        databaseStatement.bindLong(5, keyBasicData.getWidth());
        databaseStatement.bindLong(6, keyBasicData.getThick());
        databaseStatement.bindLong(7, keyBasicData.getLength());
        databaseStatement.bindLong(8, keyBasicData.getRow_count());
        databaseStatement.bindLong(9, keyBasicData.getFace());
        String row_pos = keyBasicData.getRow_pos();
        if (row_pos != null) {
            databaseStatement.bindString(10, row_pos);
        }
        String space = keyBasicData.getSpace();
        if (space != null) {
            databaseStatement.bindString(11, space);
        }
        String space_width = keyBasicData.getSpace_width();
        if (space_width != null) {
            databaseStatement.bindString(12, space_width);
        }
        String depth = keyBasicData.getDepth();
        if (depth != null) {
            databaseStatement.bindString(13, depth);
        }
        String depth_name = keyBasicData.getDepth_name();
        if (depth_name != null) {
            databaseStatement.bindString(14, depth_name);
        }
        String parameter_info = keyBasicData.getParameter_info();
        if (parameter_info != null) {
            databaseStatement.bindString(15, parameter_info);
        }
        String description = keyBasicData.getDescription();
        if (description != null) {
            databaseStatement.bindString(16, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyBasicData keyBasicData) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyBasicData.getIcCard());
        sQLiteStatement.bindLong(2, keyBasicData.getKeyTypeItemID());
        sQLiteStatement.bindLong(3, keyBasicData.getType());
        sQLiteStatement.bindLong(4, keyBasicData.getAlign());
        sQLiteStatement.bindLong(5, keyBasicData.getWidth());
        sQLiteStatement.bindLong(6, keyBasicData.getThick());
        sQLiteStatement.bindLong(7, keyBasicData.getLength());
        sQLiteStatement.bindLong(8, keyBasicData.getRow_count());
        sQLiteStatement.bindLong(9, keyBasicData.getFace());
        String row_pos = keyBasicData.getRow_pos();
        if (row_pos != null) {
            sQLiteStatement.bindString(10, row_pos);
        }
        String space = keyBasicData.getSpace();
        if (space != null) {
            sQLiteStatement.bindString(11, space);
        }
        String space_width = keyBasicData.getSpace_width();
        if (space_width != null) {
            sQLiteStatement.bindString(12, space_width);
        }
        String depth = keyBasicData.getDepth();
        if (depth != null) {
            sQLiteStatement.bindString(13, depth);
        }
        String depth_name = keyBasicData.getDepth_name();
        if (depth_name != null) {
            sQLiteStatement.bindString(14, depth_name);
        }
        String parameter_info = keyBasicData.getParameter_info();
        if (parameter_info != null) {
            sQLiteStatement.bindString(15, parameter_info);
        }
        String description = keyBasicData.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(16, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(KeyBasicData keyBasicData) {
        super.attachEntity((KeyBasicDataDao) keyBasicData);
        keyBasicData.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        return Long.valueOf(cursor.getLong(i + 0));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyBasicData readEntity(Cursor cursor, int i) {
        long j = cursor.getLong(i + 0);
        int i2 = cursor.getInt(i + 1);
        int i3 = cursor.getInt(i + 2);
        int i4 = cursor.getInt(i + 3);
        int i5 = cursor.getInt(i + 4);
        int i6 = cursor.getInt(i + 5);
        int i7 = cursor.getInt(i + 6);
        int i8 = cursor.getInt(i + 7);
        int i9 = cursor.getInt(i + 8);
        int i10 = i + 9;
        String string = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 10;
        String string2 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 11;
        String string3 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 12;
        String string4 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 13;
        String string5 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 14;
        String string6 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 15;
        return new KeyBasicData(j, i2, i3, i4, i5, i6, i7, i8, i9, string, string2, string3, string4, string5, string6, cursor.isNull(i16) ? null : cursor.getString(i16));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyBasicData keyBasicData, int i) {
        keyBasicData.setIcCard(cursor.getLong(i + 0));
        keyBasicData.setKeyTypeItemID(cursor.getInt(i + 1));
        keyBasicData.setType(cursor.getInt(i + 2));
        keyBasicData.setAlign(cursor.getInt(i + 3));
        keyBasicData.setWidth(cursor.getInt(i + 4));
        keyBasicData.setThick(cursor.getInt(i + 5));
        keyBasicData.setLength(cursor.getInt(i + 6));
        keyBasicData.setRow_count(cursor.getInt(i + 7));
        keyBasicData.setFace(cursor.getInt(i + 8));
        int i2 = i + 9;
        keyBasicData.setRow_pos(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 10;
        keyBasicData.setSpace(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 11;
        keyBasicData.setSpace_width(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 12;
        keyBasicData.setDepth(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 13;
        keyBasicData.setDepth_name(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 14;
        keyBasicData.setParameter_info(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 15;
        keyBasicData.setDescription(cursor.isNull(i8) ? null : cursor.getString(i8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(KeyBasicData keyBasicData, long j) {
        keyBasicData.setIcCard(j);
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(KeyBasicData keyBasicData) {
        if (keyBasicData != null) {
            return Long.valueOf(keyBasicData.getIcCard());
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyBasicData keyBasicData) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    protected String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(',');
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getClampKeyBasicDataDao().getAllColumns());
            sb.append(" FROM KeyBasicData T");
            sb.append(" LEFT JOIN ClampKeyBasicData T0 ON T.\"ID\"=T0.\"FK_KeyID\"");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    protected KeyBasicData loadCurrentDeep(Cursor cursor, boolean z) {
        KeyBasicData loadCurrent = loadCurrent(cursor, 0, z);
        ClampKeyBasicData clampKeyBasicData = (ClampKeyBasicData) loadCurrentOther(this.daoSession.getClampKeyBasicDataDao(), cursor, getAllColumns().length);
        if (clampKeyBasicData != null) {
            loadCurrent.setClampKeyBasicData(clampKeyBasicData);
        }
        return loadCurrent;
    }

    public KeyBasicData loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{l.toString()});
        try {
            if (!rawQuery.moveToFirst()) {
                return null;
            }
            if (!rawQuery.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + rawQuery.getCount());
            }
            return loadCurrentDeep(rawQuery, true);
        } finally {
            rawQuery.close();
        }
    }

    public List<KeyBasicData> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (cursor.moveToFirst()) {
            if (this.identityScope != null) {
                this.identityScope.lock();
                this.identityScope.reserveRoom(count);
            }
            do {
                try {
                    arrayList.add(loadCurrentDeep(cursor, false));
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.unlock();
                    }
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    protected List<KeyBasicData> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<KeyBasicData> queryDeep(String str, String... strArr) {
        return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + str, strArr));
    }
}
