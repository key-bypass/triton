package com.kkkcut.e20j.DbBean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.kkkcut.e20j.DbBean.china.ModelChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelYearChinaDao;
import com.kkkcut.e20j.DbBean.search.CardsSystemDao;
import com.kkkcut.e20j.DbBean.search.KeyBlankItemBasicDataSearchDao;
import com.kkkcut.e20j.DbBean.search.SearchManufacturerDao;
import com.kkkcut.e20j.DbBean.search.SearchModelChinaDao;
import com.kkkcut.e20j.DbBean.search.SearchModelYearChinaDao;
import com.kkkcut.e20j.DbBean.technical.DataListDao;
import com.kkkcut.e20j.DbBean.technical.DataManufacturerDao;
import com.kkkcut.e20j.DbBean.technical.DataModelDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYearDao;
import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao;
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimpleDao;
import com.kkkcut.e20j.DbBean.userDB.JpushMsgDao;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChildDao;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplateDao;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHiddenDao;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/* loaded from: classes.dex */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 13;

    public static void createAllTables(Database database, boolean z) {
        KeyImageDao.createTable(database, z);
        LocalDbVersionDao.createTable(database, z);
        CollectionDataDao.createTable(database, z);
        CustomKeyDao.createTable(database, z);
        CutHistoryDataDao.createTable(database, z);
        DuplicateDimpleDao.createTable(database, z);
        JpushMsgDao.createTable(database, z);
        KeyMarkingChildDao.createTable(database, z);
        KeyMarkingTemplateDao.createTable(database, z);
        ManufacturerHiddenDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        KeyImageDao.dropTable(database, z);
        LocalDbVersionDao.dropTable(database, z);
        CollectionDataDao.dropTable(database, z);
        CustomKeyDao.dropTable(database, z);
        CutHistoryDataDao.dropTable(database, z);
        DuplicateDimpleDao.dropTable(database, z);
        JpushMsgDao.dropTable(database, z);
        KeyMarkingChildDao.dropTable(database, z);
        KeyMarkingTemplateDao.dropTable(database, z);
        ManufacturerHiddenDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 13);
        registerDaoClass(BittingCodeDao.class);
        registerDaoClass(ClampKeyBasicDataDao.class);
        registerDaoClass(DbVersionDao.class);
        registerDaoClass(ErrorCodeBeanDao.class);
        registerDaoClass(KeyBasicDataDao.class);
        registerDaoClass(KeyBasicDataItemDao.class);
        registerDaoClass(KeyBlankDao.class);
        registerDaoClass(KeyBlankItemBasicDataDao.class);
        registerDaoClass(KeyImageDao.class);
        registerDaoClass(KeyResDao.class);
        registerDaoClass(KeyResourceDao.class);
        registerDaoClass(KeyThumbnailDao.class);
        registerDaoClass(KeyblankItemDao.class);
        registerDaoClass(LocalDbVersionDao.class);
        registerDaoClass(ManufacturerDao.class);
        registerDaoClass(ModelDao.class);
        registerDaoClass(ModelSeriesDao.class);
        registerDaoClass(ModelYearDao.class);
        registerDaoClass(ModelChinaDao.class);
        registerDaoClass(ModelSeriesChinaDao.class);
        registerDaoClass(ModelYearChinaDao.class);
        registerDaoClass(CardsSystemDao.class);
        registerDaoClass(KeyBlankItemBasicDataSearchDao.class);
        registerDaoClass(SearchManufacturerDao.class);
        registerDaoClass(SearchModelChinaDao.class);
        registerDaoClass(SearchModelYearChinaDao.class);
        registerDaoClass(DataListDao.class);
        registerDaoClass(DataManufacturerDao.class);
        registerDaoClass(DataModelDao.class);
        registerDaoClass(DataModelSeriesDao.class);
        registerDaoClass(DataModelSeriesYearDao.class);
        registerDaoClass(CollectionDataDao.class);
        registerDaoClass(CustomKeyDao.class);
        registerDaoClass(CutHistoryDataDao.class);
        registerDaoClass(DuplicateDimpleDao.class);
        registerDaoClass(JpushMsgDao.class);
        registerDaoClass(KeyMarkingChildDao.class);
        registerDaoClass(KeyMarkingTemplateDao.class);
        registerDaoClass(ManufacturerHiddenDao.class);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }

    /* loaded from: classes.dex */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 13);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 13);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 13");
            DaoMaster.createAllTables(database, false);
        }
    }

    /* loaded from: classes.dex */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }
}
