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
public class KeyMarkingTemplateDao extends AbstractDao<KeyMarkingTemplate, Long> {
    public static final String TABLENAME = "KEY_MARKING_TEMPLATE";
    private DaoSession daoSession;

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property Width = new Property(1, Integer.TYPE, "width", false, "WIDTH");
        public static final Property Height = new Property(2, Integer.TYPE, "height", false, "HEIGHT");
        public static final Property MarginLeft = new Property(3, Integer.TYPE, "marginLeft", false, "MARGIN_LEFT");
        public static final Property MarginTop = new Property(4, Integer.TYPE, "marginTop", false, "MARGIN_TOP");
        public static final Property Description = new Property(5, String.class, "description", false, "DESCRIPTION");
        public static final Property Time = new Property(6, Long.TYPE, "time", false, "TIME");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public KeyMarkingTemplateDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public KeyMarkingTemplateDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"KEY_MARKING_TEMPLATE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"WIDTH\" INTEGER NOT NULL ,\"HEIGHT\" INTEGER NOT NULL ,\"MARGIN_LEFT\" INTEGER NOT NULL ,\"MARGIN_TOP\" INTEGER NOT NULL ,\"DESCRIPTION\" TEXT,\"TIME\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"KEY_MARKING_TEMPLATE\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, KeyMarkingTemplate keyMarkingTemplate) {
        databaseStatement.clearBindings();
        Long id = keyMarkingTemplate.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, keyMarkingTemplate.getWidth());
        databaseStatement.bindLong(3, keyMarkingTemplate.getHeight());
        databaseStatement.bindLong(4, keyMarkingTemplate.getMarginLeft());
        databaseStatement.bindLong(5, keyMarkingTemplate.getMarginTop());
        String description = keyMarkingTemplate.getDescription();
        if (description != null) {
            databaseStatement.bindString(6, description);
        }
        databaseStatement.bindLong(7, keyMarkingTemplate.getTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, KeyMarkingTemplate keyMarkingTemplate) {
        sQLiteStatement.clearBindings();
        Long id = keyMarkingTemplate.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, keyMarkingTemplate.getWidth());
        sQLiteStatement.bindLong(3, keyMarkingTemplate.getHeight());
        sQLiteStatement.bindLong(4, keyMarkingTemplate.getMarginLeft());
        sQLiteStatement.bindLong(5, keyMarkingTemplate.getMarginTop());
        String description = keyMarkingTemplate.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(6, description);
        }
        sQLiteStatement.bindLong(7, keyMarkingTemplate.getTime());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(KeyMarkingTemplate keyMarkingTemplate) {
        this.attachEntity(keyMarkingTemplate.getId(), keyMarkingTemplate, false);
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
    public KeyMarkingTemplate readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 5;
        return new KeyMarkingTemplate(valueOf, cursor.getInt(i + 1), cursor.getInt(i + 2), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.getLong(i + 6));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, KeyMarkingTemplate keyMarkingTemplate, int i) {
        int i2 = i + 0;
        keyMarkingTemplate.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        keyMarkingTemplate.setWidth(cursor.getInt(i + 1));
        keyMarkingTemplate.setHeight(cursor.getInt(i + 2));
        keyMarkingTemplate.setMarginLeft(cursor.getInt(i + 3));
        keyMarkingTemplate.setMarginTop(cursor.getInt(i + 4));
        int i3 = i + 5;
        keyMarkingTemplate.setDescription(cursor.isNull(i3) ? null : cursor.getString(i3));
        keyMarkingTemplate.setTime(cursor.getLong(i + 6));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(KeyMarkingTemplate keyMarkingTemplate, long j) {
        keyMarkingTemplate.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(KeyMarkingTemplate keyMarkingTemplate) {
        if (keyMarkingTemplate != null) {
            return keyMarkingTemplate.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(KeyMarkingTemplate keyMarkingTemplate) {
        return keyMarkingTemplate.getId() != null;
    }
}
