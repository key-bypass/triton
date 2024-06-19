package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment;
import com.liulishuo.filedownloader.model.ConnectionModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class KeyBasicDataItemDao extends AbstractDao<KeyBasicDataItem, Void> {
    public static final String TABLENAME = "KeyBasicDataItem";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Integer.TYPE, ConnectionModel.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_KeyID = new Property(1, Integer.TYPE, "FK_KeyID", false, "FK_KeyID");
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

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(KeyBasicDataItem keyBasicDataItem) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyBasicDataItem keyBasicDataItem) {
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
    public final Void updateKeyAfterInsert(KeyBasicDataItem keyBasicDataItem, long j) {
        return null;
    }

    public KeyBasicDataItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyBasicDataItemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyBasicDataItem keyBasicDataItem) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, keyBasicDataItem.getId());
        databaseStatement.bindLong(2, keyBasicDataItem.getFK_KeyID());
        databaseStatement.bindLong(3, keyBasicDataItem.getType());
        databaseStatement.bindLong(4, keyBasicDataItem.getAlign());
        databaseStatement.bindLong(5, keyBasicDataItem.getWidth());
        databaseStatement.bindLong(6, keyBasicDataItem.getThick());
        databaseStatement.bindLong(7, keyBasicDataItem.getLength());
        databaseStatement.bindLong(8, keyBasicDataItem.getRow_count());
        databaseStatement.bindLong(9, keyBasicDataItem.getFace());
        String row_pos = keyBasicDataItem.getRow_pos();
        if (row_pos != null) {
            databaseStatement.bindString(10, row_pos);
        }
        String space = keyBasicDataItem.getSpace();
        if (space != null) {
            databaseStatement.bindString(11, space);
        }
        String space_width = keyBasicDataItem.getSpace_width();
        if (space_width != null) {
            databaseStatement.bindString(12, space_width);
        }
        String depth = keyBasicDataItem.getDepth();
        if (depth != null) {
            databaseStatement.bindString(13, depth);
        }
        String depth_name = keyBasicDataItem.getDepth_name();
        if (depth_name != null) {
            databaseStatement.bindString(14, depth_name);
        }
        String parameter_info = keyBasicDataItem.getParameter_info();
        if (parameter_info != null) {
            databaseStatement.bindString(15, parameter_info);
        }
        String description = keyBasicDataItem.getDescription();
        if (description != null) {
            databaseStatement.bindString(16, description);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyBasicDataItem keyBasicDataItem) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, keyBasicDataItem.getId());
        sQLiteStatement.bindLong(2, keyBasicDataItem.getFK_KeyID());
        sQLiteStatement.bindLong(3, keyBasicDataItem.getType());
        sQLiteStatement.bindLong(4, keyBasicDataItem.getAlign());
        sQLiteStatement.bindLong(5, keyBasicDataItem.getWidth());
        sQLiteStatement.bindLong(6, keyBasicDataItem.getThick());
        sQLiteStatement.bindLong(7, keyBasicDataItem.getLength());
        sQLiteStatement.bindLong(8, keyBasicDataItem.getRow_count());
        sQLiteStatement.bindLong(9, keyBasicDataItem.getFace());
        String row_pos = keyBasicDataItem.getRow_pos();
        if (row_pos != null) {
            sQLiteStatement.bindString(10, row_pos);
        }
        String space = keyBasicDataItem.getSpace();
        if (space != null) {
            sQLiteStatement.bindString(11, space);
        }
        String space_width = keyBasicDataItem.getSpace_width();
        if (space_width != null) {
            sQLiteStatement.bindString(12, space_width);
        }
        String depth = keyBasicDataItem.getDepth();
        if (depth != null) {
            sQLiteStatement.bindString(13, depth);
        }
        String depth_name = keyBasicDataItem.getDepth_name();
        if (depth_name != null) {
            sQLiteStatement.bindString(14, depth_name);
        }
        String parameter_info = keyBasicDataItem.getParameter_info();
        if (parameter_info != null) {
            sQLiteStatement.bindString(15, parameter_info);
        }
        String description = keyBasicDataItem.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(16, description);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public KeyBasicDataItem readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
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
        return new KeyBasicDataItem(i2, i3, i4, i5, i6, i7, i8, i9, i10, string, string2, string3, string4, string5, string6, cursor.isNull(i17) ? null : cursor.getString(i17));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyBasicDataItem keyBasicDataItem, int i) {
        keyBasicDataItem.setId(cursor.getInt(i + 0));
        keyBasicDataItem.setFK_KeyID(cursor.getInt(i + 1));
        keyBasicDataItem.setType(cursor.getInt(i + 2));
        keyBasicDataItem.setAlign(cursor.getInt(i + 3));
        keyBasicDataItem.setWidth(cursor.getInt(i + 4));
        keyBasicDataItem.setThick(cursor.getInt(i + 5));
        keyBasicDataItem.setLength(cursor.getInt(i + 6));
        keyBasicDataItem.setRow_count(cursor.getInt(i + 7));
        keyBasicDataItem.setFace(cursor.getInt(i + 8));
        int i2 = i + 9;
        keyBasicDataItem.setRow_pos(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 10;
        keyBasicDataItem.setSpace(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 11;
        keyBasicDataItem.setSpace_width(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 12;
        keyBasicDataItem.setDepth(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 13;
        keyBasicDataItem.setDepth_name(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 14;
        keyBasicDataItem.setParameter_info(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 15;
        keyBasicDataItem.setDescription(cursor.isNull(i8) ? null : cursor.getString(i8));
    }
}
