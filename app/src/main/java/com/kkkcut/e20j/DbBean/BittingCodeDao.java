package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.fragment.CodeFindToothFragment;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class BittingCodeDao extends AbstractDao<BittingCode, Void> {
    public static final String TABLENAME = "BittingCode";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Code = new Property(0, String.class, "code", false, "code");
        public static final Property Bitting = new Property(1, String.class, "bitting", false, "bitting");
        public static final Property Isn = new Property(2, String.class, CodeFindToothFragment.ISN, false, "ISN");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(BittingCode bittingCode) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(BittingCode bittingCode) {
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
    public final Void updateKeyAfterInsert(BittingCode bittingCode, long j) {
        return null;
    }

    public BittingCodeDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BittingCodeDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, BittingCode bittingCode) {
        databaseStatement.clearBindings();
        String code = bittingCode.getCode();
        if (code != null) {
            databaseStatement.bindString(1, code);
        }
        String bitting = bittingCode.getBitting();
        if (bitting != null) {
            databaseStatement.bindString(2, bitting);
        }
        String isn = bittingCode.getIsn();
        if (isn != null) {
            databaseStatement.bindString(3, isn);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, BittingCode bittingCode) {
        sQLiteStatement.clearBindings();
        String code = bittingCode.getCode();
        if (code != null) {
            sQLiteStatement.bindString(1, code);
        }
        String bitting = bittingCode.getBitting();
        if (bitting != null) {
            sQLiteStatement.bindString(2, bitting);
        }
        String isn = bittingCode.getIsn();
        if (isn != null) {
            sQLiteStatement.bindString(3, isn);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public BittingCode readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        return new BittingCode(cursor.isNull(i2) ? null : cursor.getString(i2), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, BittingCode bittingCode, int i) {
        int i2 = i + 0;
        bittingCode.setCode(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 1;
        bittingCode.setBitting(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        bittingCode.setIsn(cursor.isNull(i4) ? null : cursor.getString(i4));
    }
}
