package com.kkkcut.e20j.DbBean.userDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class KeyMarkingChildDao extends AbstractDao<KeyMarkingChild, Long> {
    public static final String TABLENAME = "KEY_MARKING_CHILD";
    private Query<KeyMarkingChild> keyMarkingTemplate_ChildrenBeanListQuery;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property ParentId = new Property(1, Long.class, "parentId", false, "PARENT_ID");
        public static final Property Type = new Property(2, Integer.TYPE, "type", false, "WIFI_CONNECT_TYPE");
        public static final Property Width = new Property(3, Integer.TYPE, "width", false, "WIDTH");
        public static final Property Height = new Property(4, Integer.TYPE, "height", false, "HEIGHT");
        public static final Property MarginLeft = new Property(5, Integer.TYPE, "marginLeft", false, "MARGIN_LEFT");
        public static final Property MarginTop = new Property(6, Integer.TYPE, "marginTop", false, "MARGIN_TOP");
        public static final Property FontSize = new Property(7, Float.TYPE, "fontSize", false, "FONT_SIZE");
        public static final Property Text = new Property(8, String.class, "text", false, "TEXT");
        public static final Property ImageByte = new Property(9, byte[].class, "imageByte", false, "IMAGE_BYTE");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KeyMarkingChildDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyMarkingChildDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"KEY_MARKING_CHILD\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"PARENT_ID\" INTEGER,\"TYPE\" INTEGER NOT NULL ,\"WIDTH\" INTEGER NOT NULL ,\"HEIGHT\" INTEGER NOT NULL ,\"MARGIN_LEFT\" INTEGER NOT NULL ,\"MARGIN_TOP\" INTEGER NOT NULL ,\"FONT_SIZE\" REAL NOT NULL ,\"TEXT\" TEXT,\"IMAGE_BYTE\" BLOB);");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"KEY_MARKING_CHILD\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyMarkingChild keyMarkingChild) {
        databaseStatement.clearBindings();
        Long id = keyMarkingChild.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        Long parentId = keyMarkingChild.getParentId();
        if (parentId != null) {
            databaseStatement.bindLong(2, parentId.longValue());
        }
        databaseStatement.bindLong(3, keyMarkingChild.getType());
        databaseStatement.bindLong(4, keyMarkingChild.getWidth());
        databaseStatement.bindLong(5, keyMarkingChild.getHeight());
        databaseStatement.bindLong(6, keyMarkingChild.getMarginLeft());
        databaseStatement.bindLong(7, keyMarkingChild.getMarginTop());
        databaseStatement.bindDouble(8, keyMarkingChild.getFontSize());
        String text = keyMarkingChild.getText();
        if (text != null) {
            databaseStatement.bindString(9, text);
        }
        byte[] imageByte = keyMarkingChild.getImageByte();
        if (imageByte != null) {
            databaseStatement.bindBlob(10, imageByte);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyMarkingChild keyMarkingChild) {
        sQLiteStatement.clearBindings();
        Long id = keyMarkingChild.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        Long parentId = keyMarkingChild.getParentId();
        if (parentId != null) {
            sQLiteStatement.bindLong(2, parentId.longValue());
        }
        sQLiteStatement.bindLong(3, keyMarkingChild.getType());
        sQLiteStatement.bindLong(4, keyMarkingChild.getWidth());
        sQLiteStatement.bindLong(5, keyMarkingChild.getHeight());
        sQLiteStatement.bindLong(6, keyMarkingChild.getMarginLeft());
        sQLiteStatement.bindLong(7, keyMarkingChild.getMarginTop());
        sQLiteStatement.bindDouble(8, keyMarkingChild.getFontSize());
        String text = keyMarkingChild.getText();
        if (text != null) {
            sQLiteStatement.bindString(9, text);
        }
        byte[] imageByte = keyMarkingChild.getImageByte();
        if (imageByte != null) {
            sQLiteStatement.bindBlob(10, imageByte);
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
    public KeyMarkingChild readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        Long valueOf2 = cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3));
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = cursor.getInt(i + 4);
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        float f = cursor.getFloat(i + 7);
        int i9 = i + 8;
        int i10 = i + 9;
        return new KeyMarkingChild(valueOf, valueOf2, i4, i5, i6, i7, i8, f, cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : cursor.getBlob(i10));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyMarkingChild keyMarkingChild, int i) {
        int i2 = i + 0;
        keyMarkingChild.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        keyMarkingChild.setParentId(cursor.isNull(i3) ? null : Long.valueOf(cursor.getLong(i3)));
        keyMarkingChild.setType(cursor.getInt(i + 2));
        keyMarkingChild.setWidth(cursor.getInt(i + 3));
        keyMarkingChild.setHeight(cursor.getInt(i + 4));
        keyMarkingChild.setMarginLeft(cursor.getInt(i + 5));
        keyMarkingChild.setMarginTop(cursor.getInt(i + 6));
        keyMarkingChild.setFontSize(cursor.getFloat(i + 7));
        int i4 = i + 8;
        keyMarkingChild.setText(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 9;
        keyMarkingChild.setImageByte(cursor.isNull(i5) ? null : cursor.getBlob(i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(KeyMarkingChild keyMarkingChild, long j) {
        keyMarkingChild.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(KeyMarkingChild keyMarkingChild) {
        if (keyMarkingChild != null) {
            return keyMarkingChild.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyMarkingChild keyMarkingChild) {
        return keyMarkingChild.getId() != null;
    }

    public List<KeyMarkingChild> _queryKeyMarkingTemplate_ChildrenBeanList(Long l) {
        synchronized (this) {
            if (this.keyMarkingTemplate_ChildrenBeanListQuery == null) {
                QueryBuilder<KeyMarkingChild> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ParentId.eq(null), new WhereCondition[0]);
                this.keyMarkingTemplate_ChildrenBeanListQuery = queryBuilder.build();
            }
        }
        Query<KeyMarkingChild> forCurrentThread = this.keyMarkingTemplate_ChildrenBeanListQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
