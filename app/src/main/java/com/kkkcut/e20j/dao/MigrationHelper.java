package com.kkkcut.e20j.dao;

import android.text.TextUtils;
import com.kkkcut.e20j.DbBean.DaoMaster;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class MigrationHelper {
    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";
    private static MigrationHelper instance;

    public static MigrationHelper getInstance() {
        if (instance == null) {
            instance = new MigrationHelper();
        }
        return instance;
    }

    public void migrate(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        generateTempTables(database, clsArr);
        DaoMaster.dropAllTables(database, true);
        DaoMaster.createAllTables(database, false);
        restoreData(database, clsArr);
    }

    private void generateTempTables(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String concat = daoConfig.tablename.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE ");
            sb.append(concat);
            sb.append(" (");
            String str2 = "";
            for (int i = 0; i < daoConfig.properties.length; i++) {
                String str3 = daoConfig.properties[i].columnName;
                if (getColumns(database, str).contains(str3)) {
                    arrayList.add(str3);
                    String str4 = null;
                    try {
                        try {
                            str4 = getTypeByClass(daoConfig.properties[i].type);
                        } catch (Exception unused) {
                        }
                    } catch (Exception unused2) {
                    }
                    sb.append(str2);
                    sb.append(str3);
                    sb.append(" ");
                    sb.append(str4);
                    if (daoConfig.properties[i].primaryKey) {
                        sb.append(" PRIMARY KEY");
                    }
                    str2 = ",";
                }
            }
            sb.append(");");
            database.execSQL(sb.toString());
            database.execSQL("INSERT INTO " + concat + " (" + TextUtils.join(",", arrayList) + ") SELECT " + TextUtils.join(",", arrayList) + " FROM " + str + ";");
        }
    }

    private void restoreData(Database database, Class<? extends AbstractDao<?, ?>>... clsArr) {
        for (Class<? extends AbstractDao<?, ?>> cls : clsArr) {
            DaoConfig daoConfig = new DaoConfig(database, cls);
            String str = daoConfig.tablename;
            String concat = daoConfig.tablename.concat("_TEMP");
            ArrayList arrayList = new ArrayList();
            List<String> columns = getColumns(database, concat);
            for (int i = 0; i < daoConfig.properties.length; i++) {
                String str2 = daoConfig.properties[i].columnName;
                if (!columns.contains(str2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ALTER TABLE " + concat + " ADD COLUMN " + str2 + getTableType(daoConfig.properties[i].type));
                    database.execSQL(sb.toString());
                }
                arrayList.add(str2);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("DROP TABLE ");
            sb2.append(concat);
            database.execSQL("INSERT INTO " + str + " (" + TextUtils.join(",", arrayList) + ") SELECT " + TextUtils.join(",", arrayList) + " FROM " + concat + ";");
            database.execSQL(sb2.toString());
        }
    }

    private String getTypeByClass(Class<?> cls) throws Exception {
        if (cls.equals(String.class)) {
            return "TEXT";
        }
        if (cls.equals(Long.class) || cls.equals(Integer.class) || cls.equals(Long.TYPE)) {
            return "INTEGER";
        }
        if (cls.equals(Boolean.class)) {
            return "BOOLEAN";
        }
        throw new Exception(CONVERSION_CLASS_NOT_FOUND_EXCEPTION.concat(" - Class: ").concat(cls.toString()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0046, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        if (r1 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List<java.lang.String> getColumns(org.greenrobot.greendao.database.Database r4, java.lang.String r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r2.<init>()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r2.append(r5)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r3 = " limit 1"
            r2.append(r3)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            android.database.Cursor r1 = r4.rawQuery(r2, r1)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            if (r1 == 0) goto L30
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.lang.String[] r2 = r1.getColumnNames()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r0 = r4
        L30:
            if (r1 == 0) goto L46
        L32:
            r1.close()
            goto L46
        L36:
            r4 = move-exception
            goto L47
        L38:
            r4 = move-exception
            java.lang.String r2 = r4.getMessage()     // Catch: java.lang.Throwable -> L36
            android.util.Log.v(r5, r2, r4)     // Catch: java.lang.Throwable -> L36
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L46
            goto L32
        L46:
            return r0
        L47:
            if (r1 == 0) goto L4c
            r1.close()
        L4c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kkkcut.e20j.dao.MigrationHelper.getColumns(org.greenrobot.greendao.database.Database, java.lang.String):java.util.List");
    }

    private static Object getTableType(Class<?> cls) {
        return cls.equals(Integer.TYPE) ? " INTEGER DEFAULT 0" : cls.equals(Long.TYPE) ? " Long DEFAULT 0" : cls.equals(String.class) ? " TEXT " : cls.equals(Boolean.TYPE) ? " NUMERIC DEFAULT 0" : " TEXT";
    }
}
