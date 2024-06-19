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
public class JpushMsgDao extends AbstractDao<JpushMsg, Long> {
    public static final String TABLENAME = "JPUSH_MSG";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Long.class, ConnectionModel.ID, true, FileDownloadModel.ID);
        public static final Property Title = new Property(1, String.class, "title", false, "TITLE");
        public static final Property Content = new Property(2, String.class, "content", false, "CONTENT");
        public static final Property Extra = new Property(3, String.class, "extra", false, "EXTRA");
        public static final Property Data = new Property(4, String.class, "data", false, "DATA");
        public static final Property HaveRead = new Property(5, Integer.TYPE, "haveRead", false, "HAVE_READ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    public JpushMsgDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public JpushMsgDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"JPUSH_MSG\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TITLE\" TEXT,\"CONTENT\" TEXT,\"EXTRA\" TEXT,\"DATA\" TEXT,\"HAVE_READ\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"JPUSH_MSG\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, JpushMsg jpushMsg) {
        databaseStatement.clearBindings();
        Long id = jpushMsg.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        String title = jpushMsg.getTitle();
        if (title != null) {
            databaseStatement.bindString(2, title);
        }
        String content = jpushMsg.getContent();
        if (content != null) {
            databaseStatement.bindString(3, content);
        }
        String extra = jpushMsg.getExtra();
        if (extra != null) {
            databaseStatement.bindString(4, extra);
        }
        String data = jpushMsg.getData();
        if (data != null) {
            databaseStatement.bindString(5, data);
        }
        databaseStatement.bindLong(6, jpushMsg.getHaveRead());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, JpushMsg jpushMsg) {
        sQLiteStatement.clearBindings();
        Long id = jpushMsg.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String title = jpushMsg.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(2, title);
        }
        String content = jpushMsg.getContent();
        if (content != null) {
            sQLiteStatement.bindString(3, content);
        }
        String extra = jpushMsg.getExtra();
        if (extra != null) {
            sQLiteStatement.bindString(4, extra);
        }
        String data = jpushMsg.getData();
        if (data != null) {
            sQLiteStatement.bindString(5, data);
        }
        sQLiteStatement.bindLong(6, jpushMsg.getHaveRead());
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
    public JpushMsg readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long valueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        return new JpushMsg(valueOf, string, string2, string3, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getInt(i + 5));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, JpushMsg jpushMsg, int i) {
        int i2 = i + 0;
        jpushMsg.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        jpushMsg.setTitle(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        jpushMsg.setContent(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        jpushMsg.setExtra(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        jpushMsg.setData(cursor.isNull(i6) ? null : cursor.getString(i6));
        jpushMsg.setHaveRead(cursor.getInt(i + 5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(JpushMsg jpushMsg, long j) {
        jpushMsg.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(JpushMsg jpushMsg) {
        if (jpushMsg != null) {
            return jpushMsg.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(JpushMsg jpushMsg) {
        return jpushMsg.getId() != null;
    }
}
