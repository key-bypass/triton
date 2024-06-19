package com.kkkcut.e20j.DbBean.search;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class CardsSystemDao extends AbstractDao<CardsSystem, Void> {
    public static final String TABLENAME = "KeyBasicData";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property KeyID = new Property(0, Integer.TYPE, "KeyID", false, BarCodeRemindActivity.ID);
        public static final Property Space = new Property(1, String.class, "space", false, "space");
        public static final Property Parameter_info = new Property(2, String.class, "parameter_info", false, "parameter_info");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(CardsSystem cardsSystem) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(CardsSystem cardsSystem) {
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
    public final Void updateKeyAfterInsert(CardsSystem cardsSystem, long j) {
        return null;
    }

    public CardsSystemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public CardsSystemDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, CardsSystem cardsSystem) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, cardsSystem.getKeyID());
        String space = cardsSystem.getSpace();
        if (space != null) {
            databaseStatement.bindString(2, space);
        }
        String parameter_info = cardsSystem.getParameter_info();
        if (parameter_info != null) {
            databaseStatement.bindString(3, parameter_info);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, CardsSystem cardsSystem) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, cardsSystem.getKeyID());
        String space = cardsSystem.getSpace();
        if (space != null) {
            sQLiteStatement.bindString(2, space);
        }
        String parameter_info = cardsSystem.getParameter_info();
        if (parameter_info != null) {
            sQLiteStatement.bindString(3, parameter_info);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public CardsSystem readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = i + 1;
        int i4 = i + 2;
        return new CardsSystem(i2, cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, CardsSystem cardsSystem, int i) {
        cardsSystem.setKeyID(cursor.getInt(i + 0));
        int i2 = i + 1;
        cardsSystem.setSpace(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 2;
        cardsSystem.setParameter_info(cursor.isNull(i3) ? null : cursor.getString(i3));
    }
}
