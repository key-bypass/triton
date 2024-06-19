package com.kkkcut.e20j.DbBean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class ErrorCodeBeanDao extends AbstractDao<ErrorCodeBean, Void> {
    public static final String TABLENAME = "ErrorCodeMessage";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property ID = new Property(0, Integer.TYPE, BarCodeRemindActivity.ID, false, BarCodeRemindActivity.ID);
        public static final Property Code = new Property(1, Integer.TYPE, "Code", false, "Code");
        public static final Property MessageType = new Property(2, Integer.TYPE, "MessageType", false, "MessageType");
        public static final Property Desc_en = new Property(3, String.class, "Desc_en", false, "Desc_en");
        public static final Property Desc_zh = new Property(4, String.class, "Desc_zh", false, "Desc_zh");
        public static final Property Desc_cs = new Property(5, String.class, "Desc_cs", false, "Desc_cs");
        public static final Property Desc_fr = new Property(6, String.class, "Desc_fr", false, "Desc_fr");
        public static final Property Desc_de = new Property(7, String.class, "Desc_de", false, "Desc_de");
        public static final Property Desc_it = new Property(8, String.class, "Desc_it", false, "Desc_it");
        public static final Property Desc_es = new Property(9, String.class, "Desc_es", false, "Desc_es");
        public static final Property Desc_ko = new Property(10, String.class, "Desc_ko", false, "Desc_ko");
        public static final Property Desc_pt = new Property(11, String.class, "Desc_pt", false, "Desc_pt");
        public static final Property Desc_ru = new Property(12, String.class, "Desc_ru", false, "Desc_ru");
        public static final Property Desc_tr = new Property(13, String.class, "Desc_tr", false, "Desc_tr");
        public static final Property Desc_hb = new Property(14, String.class, "Desc_hb", false, "Desc_hb");
        public static final Property Desc_pl = new Property(15, String.class, "Desc_pl", false, "Desc_pl");
        public static final Property Desc_vi = new Property(16, String.class, "Desc_vi", false, "Desc_vi");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(ErrorCodeBean errorCodeBean) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(ErrorCodeBean errorCodeBean) {
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
    public final Void updateKeyAfterInsert(ErrorCodeBean errorCodeBean, long j) {
        return null;
    }

    public ErrorCodeBeanDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public ErrorCodeBeanDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, ErrorCodeBean errorCodeBean) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, errorCodeBean.getID());
        databaseStatement.bindLong(2, errorCodeBean.getCode());
        databaseStatement.bindLong(3, errorCodeBean.getMessageType());
        String desc_en = errorCodeBean.getDesc_en();
        if (desc_en != null) {
            databaseStatement.bindString(4, desc_en);
        }
        String desc_zh = errorCodeBean.getDesc_zh();
        if (desc_zh != null) {
            databaseStatement.bindString(5, desc_zh);
        }
        String desc_cs = errorCodeBean.getDesc_cs();
        if (desc_cs != null) {
            databaseStatement.bindString(6, desc_cs);
        }
        String desc_fr = errorCodeBean.getDesc_fr();
        if (desc_fr != null) {
            databaseStatement.bindString(7, desc_fr);
        }
        String desc_de = errorCodeBean.getDesc_de();
        if (desc_de != null) {
            databaseStatement.bindString(8, desc_de);
        }
        String desc_it = errorCodeBean.getDesc_it();
        if (desc_it != null) {
            databaseStatement.bindString(9, desc_it);
        }
        String desc_es = errorCodeBean.getDesc_es();
        if (desc_es != null) {
            databaseStatement.bindString(10, desc_es);
        }
        String desc_ko = errorCodeBean.getDesc_ko();
        if (desc_ko != null) {
            databaseStatement.bindString(11, desc_ko);
        }
        String desc_pt = errorCodeBean.getDesc_pt();
        if (desc_pt != null) {
            databaseStatement.bindString(12, desc_pt);
        }
        String desc_ru = errorCodeBean.getDesc_ru();
        if (desc_ru != null) {
            databaseStatement.bindString(13, desc_ru);
        }
        String desc_tr = errorCodeBean.getDesc_tr();
        if (desc_tr != null) {
            databaseStatement.bindString(14, desc_tr);
        }
        String desc_hb = errorCodeBean.getDesc_hb();
        if (desc_hb != null) {
            databaseStatement.bindString(15, desc_hb);
        }
        String desc_pl = errorCodeBean.getDesc_pl();
        if (desc_pl != null) {
            databaseStatement.bindString(16, desc_pl);
        }
        String desc_vi = errorCodeBean.getDesc_vi();
        if (desc_vi != null) {
            databaseStatement.bindString(17, desc_vi);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, ErrorCodeBean errorCodeBean) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, errorCodeBean.getID());
        sQLiteStatement.bindLong(2, errorCodeBean.getCode());
        sQLiteStatement.bindLong(3, errorCodeBean.getMessageType());
        String desc_en = errorCodeBean.getDesc_en();
        if (desc_en != null) {
            sQLiteStatement.bindString(4, desc_en);
        }
        String desc_zh = errorCodeBean.getDesc_zh();
        if (desc_zh != null) {
            sQLiteStatement.bindString(5, desc_zh);
        }
        String desc_cs = errorCodeBean.getDesc_cs();
        if (desc_cs != null) {
            sQLiteStatement.bindString(6, desc_cs);
        }
        String desc_fr = errorCodeBean.getDesc_fr();
        if (desc_fr != null) {
            sQLiteStatement.bindString(7, desc_fr);
        }
        String desc_de = errorCodeBean.getDesc_de();
        if (desc_de != null) {
            sQLiteStatement.bindString(8, desc_de);
        }
        String desc_it = errorCodeBean.getDesc_it();
        if (desc_it != null) {
            sQLiteStatement.bindString(9, desc_it);
        }
        String desc_es = errorCodeBean.getDesc_es();
        if (desc_es != null) {
            sQLiteStatement.bindString(10, desc_es);
        }
        String desc_ko = errorCodeBean.getDesc_ko();
        if (desc_ko != null) {
            sQLiteStatement.bindString(11, desc_ko);
        }
        String desc_pt = errorCodeBean.getDesc_pt();
        if (desc_pt != null) {
            sQLiteStatement.bindString(12, desc_pt);
        }
        String desc_ru = errorCodeBean.getDesc_ru();
        if (desc_ru != null) {
            sQLiteStatement.bindString(13, desc_ru);
        }
        String desc_tr = errorCodeBean.getDesc_tr();
        if (desc_tr != null) {
            sQLiteStatement.bindString(14, desc_tr);
        }
        String desc_hb = errorCodeBean.getDesc_hb();
        if (desc_hb != null) {
            sQLiteStatement.bindString(15, desc_hb);
        }
        String desc_pl = errorCodeBean.getDesc_pl();
        if (desc_pl != null) {
            sQLiteStatement.bindString(16, desc_pl);
        }
        String desc_vi = errorCodeBean.getDesc_vi();
        if (desc_vi != null) {
            sQLiteStatement.bindString(17, desc_vi);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public ErrorCodeBean readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = i + 3;
        String string = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        String string4 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        String string5 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        String string6 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        String string7 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 10;
        String string8 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 11;
        String string9 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 12;
        String string10 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 13;
        String string11 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 14;
        String string12 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 15;
        String string13 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 16;
        return new ErrorCodeBean(i2, i3, i4, string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, cursor.isNull(i18) ? null : cursor.getString(i18));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, ErrorCodeBean errorCodeBean, int i) {
        errorCodeBean.setID(cursor.getInt(i + 0));
        errorCodeBean.setCode(cursor.getInt(i + 1));
        errorCodeBean.setMessageType(cursor.getInt(i + 2));
        int i2 = i + 3;
        errorCodeBean.setDesc_en(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 4;
        errorCodeBean.setDesc_zh(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        errorCodeBean.setDesc_cs(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        errorCodeBean.setDesc_fr(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 7;
        errorCodeBean.setDesc_de(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 8;
        errorCodeBean.setDesc_it(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 9;
        errorCodeBean.setDesc_es(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 10;
        errorCodeBean.setDesc_ko(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 11;
        errorCodeBean.setDesc_pt(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 12;
        errorCodeBean.setDesc_ru(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 13;
        errorCodeBean.setDesc_tr(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 14;
        errorCodeBean.setDesc_hb(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 15;
        errorCodeBean.setDesc_pl(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 16;
        errorCodeBean.setDesc_vi(cursor.isNull(i15) ? null : cursor.getString(i15));
    }
}
