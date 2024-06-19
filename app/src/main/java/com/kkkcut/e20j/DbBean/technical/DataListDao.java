package com.kkkcut.e20j.DbBean.technical;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.liulishuo.filedownloader.model.ConnectionModel;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DataListDao extends AbstractDao<DataList, Void> {
    public static final String TABLENAME = "Data_List";

    /* loaded from: classes.dex */
    public static class Properties {
        public static final Property Id = new Property(0, Integer.TYPE, ConnectionModel.ID, false, BarCodeRemindActivity.ID);
        public static final Property FK_ModelSeriesYearID = new Property(1, Integer.TYPE, "fK_ModelSeriesYearID", false, "FK_ModelSeriesYearID");
        public static final Property Column1 = new Property(2, String.class, "Column1", false, "Column1");
        public static final Property Column2 = new Property(3, String.class, "Column2", false, "Column2");
        public static final Property Column3 = new Property(4, String.class, "Column3", false, "Column3");
        public static final Property Column4 = new Property(5, String.class, "Column4", false, "Column4");
        public static final Property Column5 = new Property(6, String.class, "Column5", false, "Column5");
        public static final Property Column6 = new Property(7, String.class, "Column6", false, "Column6");
        public static final Property Column7 = new Property(8, String.class, "Column7", false, "Column7");
        public static final Property Column8 = new Property(9, String.class, "Column8", false, "Column8");
        public static final Property Column9 = new Property(10, String.class, "Column9", false, "Column9");
        public static final Property Column10 = new Property(11, String.class, "Column10", false, "Column10");
        public static final Property Column11 = new Property(12, String.class, "Column11", false, "Column11");
        public static final Property Column12 = new Property(13, String.class, "Column12", false, "Column12");
        public static final Property Column13 = new Property(14, String.class, "Column13", false, "Column13");
        public static final Property Column14 = new Property(15, String.class, "Column14", false, "Column14");
        public static final Property Column15 = new Property(16, String.class, "Column15", false, "Column15");
        public static final Property Column16 = new Property(17, String.class, "Column16", false, "Column16");
        public static final Property Column17 = new Property(18, String.class, "Column17", false, "Column17");
        public static final Property Column18 = new Property(19, String.class, "Column18", false, "Column18");
        public static final Property Column19 = new Property(20, String.class, "Column19", false, "Column19");
        public static final Property Column20 = new Property(21, String.class, "Column20", false, "Column20");
        public static final Property Column21 = new Property(22, String.class, "Column21", false, "Column21");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Void getKey(DataList dataList) {
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataList dataList) {
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
    public final Void updateKeyAfterInsert(DataList dataList, long j) {
        return null;
    }

    public DataListDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DataListDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataList dataList) {
        databaseStatement.clearBindings();
        databaseStatement.bindLong(1, dataList.getId());
        databaseStatement.bindLong(2, dataList.getFK_ModelSeriesYearID());
        String column1 = dataList.getColumn1();
        if (column1 != null) {
            databaseStatement.bindString(3, column1);
        }
        String column2 = dataList.getColumn2();
        if (column2 != null) {
            databaseStatement.bindString(4, column2);
        }
        String column3 = dataList.getColumn3();
        if (column3 != null) {
            databaseStatement.bindString(5, column3);
        }
        String column4 = dataList.getColumn4();
        if (column4 != null) {
            databaseStatement.bindString(6, column4);
        }
        String column5 = dataList.getColumn5();
        if (column5 != null) {
            databaseStatement.bindString(7, column5);
        }
        String column6 = dataList.getColumn6();
        if (column6 != null) {
            databaseStatement.bindString(8, column6);
        }
        String column7 = dataList.getColumn7();
        if (column7 != null) {
            databaseStatement.bindString(9, column7);
        }
        String column8 = dataList.getColumn8();
        if (column8 != null) {
            databaseStatement.bindString(10, column8);
        }
        String column9 = dataList.getColumn9();
        if (column9 != null) {
            databaseStatement.bindString(11, column9);
        }
        String column10 = dataList.getColumn10();
        if (column10 != null) {
            databaseStatement.bindString(12, column10);
        }
        String column11 = dataList.getColumn11();
        if (column11 != null) {
            databaseStatement.bindString(13, column11);
        }
        String column12 = dataList.getColumn12();
        if (column12 != null) {
            databaseStatement.bindString(14, column12);
        }
        String column13 = dataList.getColumn13();
        if (column13 != null) {
            databaseStatement.bindString(15, column13);
        }
        String column14 = dataList.getColumn14();
        if (column14 != null) {
            databaseStatement.bindString(16, column14);
        }
        String column15 = dataList.getColumn15();
        if (column15 != null) {
            databaseStatement.bindString(17, column15);
        }
        String column16 = dataList.getColumn16();
        if (column16 != null) {
            databaseStatement.bindString(18, column16);
        }
        String column17 = dataList.getColumn17();
        if (column17 != null) {
            databaseStatement.bindString(19, column17);
        }
        String column18 = dataList.getColumn18();
        if (column18 != null) {
            databaseStatement.bindString(20, column18);
        }
        String column19 = dataList.getColumn19();
        if (column19 != null) {
            databaseStatement.bindString(21, column19);
        }
        String column20 = dataList.getColumn20();
        if (column20 != null) {
            databaseStatement.bindString(22, column20);
        }
        String column21 = dataList.getColumn21();
        if (column21 != null) {
            databaseStatement.bindString(23, column21);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataList dataList) {
        sQLiteStatement.clearBindings();
        sQLiteStatement.bindLong(1, dataList.getId());
        sQLiteStatement.bindLong(2, dataList.getFK_ModelSeriesYearID());
        String column1 = dataList.getColumn1();
        if (column1 != null) {
            sQLiteStatement.bindString(3, column1);
        }
        String column2 = dataList.getColumn2();
        if (column2 != null) {
            sQLiteStatement.bindString(4, column2);
        }
        String column3 = dataList.getColumn3();
        if (column3 != null) {
            sQLiteStatement.bindString(5, column3);
        }
        String column4 = dataList.getColumn4();
        if (column4 != null) {
            sQLiteStatement.bindString(6, column4);
        }
        String column5 = dataList.getColumn5();
        if (column5 != null) {
            sQLiteStatement.bindString(7, column5);
        }
        String column6 = dataList.getColumn6();
        if (column6 != null) {
            sQLiteStatement.bindString(8, column6);
        }
        String column7 = dataList.getColumn7();
        if (column7 != null) {
            sQLiteStatement.bindString(9, column7);
        }
        String column8 = dataList.getColumn8();
        if (column8 != null) {
            sQLiteStatement.bindString(10, column8);
        }
        String column9 = dataList.getColumn9();
        if (column9 != null) {
            sQLiteStatement.bindString(11, column9);
        }
        String column10 = dataList.getColumn10();
        if (column10 != null) {
            sQLiteStatement.bindString(12, column10);
        }
        String column11 = dataList.getColumn11();
        if (column11 != null) {
            sQLiteStatement.bindString(13, column11);
        }
        String column12 = dataList.getColumn12();
        if (column12 != null) {
            sQLiteStatement.bindString(14, column12);
        }
        String column13 = dataList.getColumn13();
        if (column13 != null) {
            sQLiteStatement.bindString(15, column13);
        }
        String column14 = dataList.getColumn14();
        if (column14 != null) {
            sQLiteStatement.bindString(16, column14);
        }
        String column15 = dataList.getColumn15();
        if (column15 != null) {
            sQLiteStatement.bindString(17, column15);
        }
        String column16 = dataList.getColumn16();
        if (column16 != null) {
            sQLiteStatement.bindString(18, column16);
        }
        String column17 = dataList.getColumn17();
        if (column17 != null) {
            sQLiteStatement.bindString(19, column17);
        }
        String column18 = dataList.getColumn18();
        if (column18 != null) {
            sQLiteStatement.bindString(20, column18);
        }
        String column19 = dataList.getColumn19();
        if (column19 != null) {
            sQLiteStatement.bindString(21, column19);
        }
        String column20 = dataList.getColumn20();
        if (column20 != null) {
            sQLiteStatement.bindString(22, column20);
        }
        String column21 = dataList.getColumn21();
        if (column21 != null) {
            sQLiteStatement.bindString(23, column21);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataList readEntity(Cursor cursor, int i) {
        int i2 = cursor.getInt(i + 0);
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string4 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        String string5 = cursor.isNull(i8) ? null : cursor.getString(i8);
        int i9 = i + 7;
        String string6 = cursor.isNull(i9) ? null : cursor.getString(i9);
        int i10 = i + 8;
        String string7 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        String string8 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = i + 10;
        String string9 = cursor.isNull(i12) ? null : cursor.getString(i12);
        int i13 = i + 11;
        String string10 = cursor.isNull(i13) ? null : cursor.getString(i13);
        int i14 = i + 12;
        String string11 = cursor.isNull(i14) ? null : cursor.getString(i14);
        int i15 = i + 13;
        String string12 = cursor.isNull(i15) ? null : cursor.getString(i15);
        int i16 = i + 14;
        String string13 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 15;
        String string14 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 16;
        String string15 = cursor.isNull(i18) ? null : cursor.getString(i18);
        int i19 = i + 17;
        String string16 = cursor.isNull(i19) ? null : cursor.getString(i19);
        int i20 = i + 18;
        String string17 = cursor.isNull(i20) ? null : cursor.getString(i20);
        int i21 = i + 19;
        String string18 = cursor.isNull(i21) ? null : cursor.getString(i21);
        int i22 = i + 20;
        String string19 = cursor.isNull(i22) ? null : cursor.getString(i22);
        int i23 = i + 21;
        String string20 = cursor.isNull(i23) ? null : cursor.getString(i23);
        int i24 = i + 22;
        return new DataList(i2, i3, string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, string16, string17, string18, string19, string20, cursor.isNull(i24) ? null : cursor.getString(i24));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataList dataList, int i) {
        dataList.setId(cursor.getInt(i + 0));
        dataList.setFK_ModelSeriesYearID(cursor.getInt(i + 1));
        int i2 = i + 2;
        dataList.setColumn1(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 3;
        dataList.setColumn2(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        dataList.setColumn3(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 5;
        dataList.setColumn4(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 6;
        dataList.setColumn5(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 7;
        dataList.setColumn6(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 8;
        dataList.setColumn7(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 9;
        dataList.setColumn8(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 10;
        dataList.setColumn9(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 11;
        dataList.setColumn10(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 12;
        dataList.setColumn11(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 13;
        dataList.setColumn12(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 14;
        dataList.setColumn13(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 15;
        dataList.setColumn14(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 16;
        dataList.setColumn15(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 17;
        dataList.setColumn16(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 18;
        dataList.setColumn17(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 19;
        dataList.setColumn18(cursor.isNull(i19) ? null : cursor.getString(i19));
        int i20 = i + 20;
        dataList.setColumn19(cursor.isNull(i20) ? null : cursor.getString(i20));
        int i21 = i + 21;
        dataList.setColumn20(cursor.isNull(i21) ? null : cursor.getString(i21));
        int i22 = i + 22;
        dataList.setColumn21(cursor.isNull(i22) ? null : cursor.getString(i22));
    }
}
