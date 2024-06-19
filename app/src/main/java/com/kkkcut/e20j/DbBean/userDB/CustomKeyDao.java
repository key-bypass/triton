package com.kkkcut.e20j.DbBean.userDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class CustomKeyDao extends AbstractDao<CustomKey, Long> {
    public static final String TABLENAME = "CUSTOM_KEY";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property IcCard = new Property(0, Long.class, "icCard", true, BarCodeRemindActivity.ID);
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
        public static final Property Keyname = new Property(15, String.class, "keyname", false, "keyname");
        public static final Property ClampNum = new Property(16, String.class, "ClampNum", false, "ClampNum");
        public static final Property ClampSide = new Property(17, String.class, "ClampSide", false, "ClampSide");
        public static final Property ClampSlot = new Property(18, String.class, "ClampSlot", false, "ClampSlot");
        public static final Property Time = new Property(19, Long.TYPE, "time", false, "TIME");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public CustomKeyDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CustomKeyDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"CUSTOM_KEY\" (\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"KeyTypeItemID\" INTEGER NOT NULL ,\"type\" INTEGER NOT NULL ,\"align\" INTEGER NOT NULL ,\"width\" INTEGER NOT NULL ,\"thick\" INTEGER NOT NULL ,\"length\" INTEGER NOT NULL ,\"row_count\" INTEGER NOT NULL ,\"face\" INTEGER NOT NULL ,\"row_pos\" TEXT,\"space\" TEXT,\"space_width\" TEXT,\"depth\" TEXT,\"depth_name\" TEXT,\"parameter_info\" TEXT,\"keyname\" TEXT,\"ClampNum\" TEXT,\"ClampSide\" TEXT,\"ClampSlot\" TEXT,\"TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"CUSTOM_KEY\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, CustomKey customKey) {
        databaseStatement.clearBindings();
        Long icCard = customKey.getIcCard();
        if (icCard != null) {
            databaseStatement.bindLong(1, icCard.longValue());
        }
        databaseStatement.bindLong(2, customKey.getKeyTypeItemID());
        databaseStatement.bindLong(3, customKey.getType());
        databaseStatement.bindLong(4, customKey.getAlign());
        databaseStatement.bindLong(5, customKey.getWidth());
        databaseStatement.bindLong(6, customKey.getThick());
        databaseStatement.bindLong(7, customKey.getLength());
        databaseStatement.bindLong(8, customKey.getRow_count());
        databaseStatement.bindLong(9, customKey.getFace());
        String row_pos = customKey.getRow_pos();
        if (row_pos != null) {
            databaseStatement.bindString(10, row_pos);
        }
        String space = customKey.getSpace();
        if (space != null) {
            databaseStatement.bindString(11, space);
        }
        String space_width = customKey.getSpace_width();
        if (space_width != null) {
            databaseStatement.bindString(12, space_width);
        }
        String depth = customKey.getDepth();
        if (depth != null) {
            databaseStatement.bindString(13, depth);
        }
        String depth_name = customKey.getDepth_name();
        if (depth_name != null) {
            databaseStatement.bindString(14, depth_name);
        }
        String parameter_info = customKey.getParameter_info();
        if (parameter_info != null) {
            databaseStatement.bindString(15, parameter_info);
        }
        String keyname = customKey.getKeyname();
        if (keyname != null) {
            databaseStatement.bindString(16, keyname);
        }
        String clampNum = customKey.getClampNum();
        if (clampNum != null) {
            databaseStatement.bindString(17, clampNum);
        }
        String clampSide = customKey.getClampSide();
        if (clampSide != null) {
            databaseStatement.bindString(18, clampSide);
        }
        String clampSlot = customKey.getClampSlot();
        if (clampSlot != null) {
            databaseStatement.bindString(19, clampSlot);
        }
        databaseStatement.bindLong(20, customKey.getTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, CustomKey customKey) {
        sQLiteStatement.clearBindings();
        Long icCard = customKey.getIcCard();
        if (icCard != null) {
            sQLiteStatement.bindLong(1, icCard.longValue());
        }
        sQLiteStatement.bindLong(2, customKey.getKeyTypeItemID());
        sQLiteStatement.bindLong(3, customKey.getType());
        sQLiteStatement.bindLong(4, customKey.getAlign());
        sQLiteStatement.bindLong(5, customKey.getWidth());
        sQLiteStatement.bindLong(6, customKey.getThick());
        sQLiteStatement.bindLong(7, customKey.getLength());
        sQLiteStatement.bindLong(8, customKey.getRow_count());
        sQLiteStatement.bindLong(9, customKey.getFace());
        String row_pos = customKey.getRow_pos();
        if (row_pos != null) {
            sQLiteStatement.bindString(10, row_pos);
        }
        String space = customKey.getSpace();
        if (space != null) {
            sQLiteStatement.bindString(11, space);
        }
        String space_width = customKey.getSpace_width();
        if (space_width != null) {
            sQLiteStatement.bindString(12, space_width);
        }
        String depth = customKey.getDepth();
        if (depth != null) {
            sQLiteStatement.bindString(13, depth);
        }
        String depth_name = customKey.getDepth_name();
        if (depth_name != null) {
            sQLiteStatement.bindString(14, depth_name);
        }
        String parameter_info = customKey.getParameter_info();
        if (parameter_info != null) {
            sQLiteStatement.bindString(15, parameter_info);
        }
        String keyname = customKey.getKeyname();
        if (keyname != null) {
            sQLiteStatement.bindString(16, keyname);
        }
        String clampNum = customKey.getClampNum();
        if (clampNum != null) {
            sQLiteStatement.bindString(17, clampNum);
        }
        String clampSide = customKey.getClampSide();
        if (clampSide != null) {
            sQLiteStatement.bindString(18, clampSide);
        }
        String clampSlot = customKey.getClampSlot();
        if (clampSlot != null) {
            sQLiteStatement.bindString(19, clampSlot);
        }
        sQLiteStatement.bindLong(20, customKey.getTime());
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
    public CustomKey readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = cursor.getInt(i + 4);
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = cursor.getInt(i + 8);
        int i11 = i + 9;
        String string = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 10;
        String string2 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 11;
        String string3 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 12;
        String string4 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 13;
        String string5 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 14;
        String string6 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 15;
        String string7 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 16;
        String string8 = cursor.isNull(i18) ? null : cursor.getString(i18);
        int i19 = i + 17;
        String string9 = cursor.isNull(i19) ? null : cursor.getString(i19);
        int i20 = i + 18;
        return new CustomKey(valueOf, i3, i4, i5, i6, i7, i8, i9, i10, string, string2, string3, string4, string5, string6, string7, string8, string9, cursor.isNull(i20) ? null : cursor.getString(i20), cursor.getLong(i + 19));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, CustomKey customKey, int i) {
        int i2 = i + 0;
        customKey.setIcCard(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        customKey.setKeyTypeItemID(cursor.getInt(i + 1));
        customKey.setType(cursor.getInt(i + 2));
        customKey.setAlign(cursor.getInt(i + 3));
        customKey.setWidth(cursor.getInt(i + 4));
        customKey.setThick(cursor.getInt(i + 5));
        customKey.setLength(cursor.getInt(i + 6));
        customKey.setRow_count(cursor.getInt(i + 7));
        customKey.setFace(cursor.getInt(i + 8));
        int i3 = i + 9;
        customKey.setRow_pos(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 10;
        customKey.setSpace(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 11;
        customKey.setSpace_width(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 12;
        customKey.setDepth(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 13;
        customKey.setDepth_name(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 14;
        customKey.setParameter_info(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 15;
        customKey.setKeyname(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 16;
        customKey.setClampNum(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 17;
        customKey.setClampSide(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 18;
        customKey.setClampSlot(cursor.isNull(i12) ? null : cursor.getString(i12));
        customKey.setTime(cursor.getLong(i + 19));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(CustomKey customKey, long j) {
        customKey.setIcCard(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(CustomKey customKey) {
        if (customKey != null) {
            return customKey.getIcCard();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(CustomKey customKey) {
        return customKey.getIcCard() != null;
    }
}