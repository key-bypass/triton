package com.kkkcut.e20j.dao;

import android.database.Cursor;
import android.text.TextUtils;
import com.kkkcut.e20j.DbBean.BittingCode;
import com.kkkcut.e20j.DbBean.BittingCodeDao;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* loaded from: classes.dex */
public class ToothCodeDaoManager {
    private DaoSession daoSession;

    public ToothCodeDaoManager(int i) {
        this.daoSession = new DaoMaster(new DaoMaster.DevOpenHelper(new GreenDaoContext(), String.valueOf(i)).getWritableDb()).newSession();
    }

    public List<BittingCode> codeFindTooth(String str, String str2) {
        QueryBuilder queryBuilder = this.daoSession.queryBuilder(BittingCode.class);
        return "0".equals(str2) ? queryBuilder.where(BittingCodeDao.Properties.Code.eq(str.toUpperCase()), new WhereCondition[0]).list() : queryBuilder.where(BittingCodeDao.Properties.Code.eq(str.toUpperCase()), BittingCodeDao.Properties.Isn.eq(str2)).list();
    }

    public List<BittingCode> lackTooth(String str) {
        return this.daoSession.queryBuilder(BittingCode.class).where(BittingCodeDao.Properties.Bitting.like(str), new WhereCondition[0]).distinct().orderAsc(BittingCodeDao.Properties.Bitting).limit(100).list();
    }

    public List<BittingCode> lackToothMulti(String str, Map<Integer, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("select code,bitting,ISN from BittingCode where bitting like ");
        sb.append("'");
        sb.append(str);
        sb.append("'");
        String[] split = str.split("-");
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            for (int i3 = 0; i3 < split[i2].length(); i3++) {
                if (split[i2].charAt(i3) == '#') {
                    String str2 = map.get(Integer.valueOf(i));
                    if (!TextUtils.isEmpty(str2)) {
                        String[] split2 = str2.split(",");
                        sb.append(" and (");
                        for (int i4 = 0; i4 < split2.length; i4++) {
                            sb.append("B" + ((char) (i2 + 65)) + (i3 + 1) + "=" + split2[i4]);
                            if (i4 != split2.length - 1) {
                                sb.append(" or ");
                            }
                        }
                        sb.append(")");
                    }
                }
                i++;
            }
            i++;
        }
        String replace = sb.toString().replace("#", "_");
        Database database = this.daoSession.getBittingCodeDao().getDatabase();
        ArrayList arrayList = new ArrayList();
        try {
            Cursor rawQuery = database.rawQuery(replace, null);
            while (rawQuery.moveToNext()) {
                try {
                    arrayList.add(new BittingCode(rawQuery.getString(rawQuery.getColumnIndexOrThrow("code")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("bitting")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("ISN"))));
                } finally {
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
