package com.kkkcut.e20j.dao;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.kkkcut.e20j.DbBean.DaoMaster;
import com.kkkcut.e20j.DbBean.DaoSession;
import com.kkkcut.e20j.DbBean.DbVersion;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.DbBean.KeyBasicDataDao;
import com.kkkcut.e20j.DbBean.KeyBasicDataItem;
import com.kkkcut.e20j.DbBean.KeyBasicDataItemDao;
import com.kkkcut.e20j.DbBean.Manufacturer;
import com.kkkcut.e20j.DbBean.ManufacturerDao;
import com.kkkcut.e20j.DbBean.Model;
import com.kkkcut.e20j.DbBean.ModelDao;
import com.kkkcut.e20j.DbBean.ModelSeries;
import com.kkkcut.e20j.DbBean.ModelSeriesDao;
import com.kkkcut.e20j.DbBean.ModelYear;
import com.kkkcut.e20j.DbBean.ModelYearDao;
import com.kkkcut.e20j.DbBean.china.ModelChina;
import com.kkkcut.e20j.DbBean.china.ModelChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelYearChina;
import com.kkkcut.e20j.DbBean.china.ModelYearChinaDao;
import com.kkkcut.e20j.DbBean.search.BarCodeSearch;
import com.kkkcut.e20j.DbBean.search.CardsSystem;
import com.kkkcut.e20j.DbBean.search.CardsSystemDao;
import com.kkkcut.e20j.DbBean.search.ChinaNumSearch;
import com.kkkcut.e20j.DbBean.search.KeyBlankItemSearch;
import com.kkkcut.e20j.DbBean.search.MenuSummary;
import com.kkkcut.e20j.DbBean.search.UsaSearchExtItemBasicData;
import com.kkkcut.e20j.DbBean.technical.DataList;
import com.kkkcut.e20j.DbBean.technical.DataListDao;
import com.kkkcut.e20j.DbBean.technical.DataManufacturer;
import com.kkkcut.e20j.DbBean.technical.DataModel;
import com.kkkcut.e20j.DbBean.technical.DataModelDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeries;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYear;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYearDao;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.ui.fragment.search.SearchCondition;
import com.kkkcut.e20j.utils.DesUtil;
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
import com.cutting.machine.MachineInfo;

/* loaded from: classes.dex */
public class KeyInfoDaoManager {
    private static DaoSession daoSession;
    private static KeyInfoDaoManager sInstance;
    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mOpenHelper;

    public KeyInfoDaoManager(Context context) {
        this.mContext = context;
    }

    public static KeyInfoDaoManager getInstance() {
        if (sInstance == null) {
            synchronized (KeyInfoDaoManager.class) {
                sInstance = new KeyInfoDaoManager(MyApplication.getInstance());
            }
        }
        return sInstance;
    }

    public DaoMaster getDaoMaster() {
        if (this.mDaoMaster == null) {
            this.mOpenHelper = new DaoMaster.DevOpenHelper(this.mContext, ResUpdateUtils.getLocalMainDbName());
            this.mDaoMaster = new DaoMaster(this.mOpenHelper.getReadableDatabase());
        }
        return this.mDaoMaster;
    }

    public DaoSession getDaoSession() {
        if (!ResUpdateUtils.localMainDbExist()) {
            return null;
        }
        if (daoSession == null) {
            if (this.mDaoMaster == null) {
                this.mDaoMaster = getDaoMaster();
            }
            daoSession = this.mDaoMaster.newSession();
        }
        return daoSession;
    }

    private void closeDaoSession() {
        DaoSession daoSession2 = daoSession;
        if (daoSession2 != null) {
            daoSession2.clear();
            daoSession = null;
        }
    }

    private void closeHelper() {
        DaoMaster.DevOpenHelper devOpenHelper = this.mOpenHelper;
        if (devOpenHelper != null) {
            devOpenHelper.close();
            this.mOpenHelper = null;
        }
    }

    public void closeDBConncetion() {
        closeDaoSession();
        closeHelper();
    }

    public List<Manufacturer> getManufacturers(int i, List<Integer> list) {
        var queryBuilder = getDaoSession().queryBuilder(Manufacturer.class);
        switch (i) {
            case 1:
                return MachineInfo.isChineseMachine() ? queryBuilder.where(ManufacturerDao.Properties.Is_automobile.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list() : queryBuilder.where(ManufacturerDao.Properties.Is_automobile.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            case 2:
                return queryBuilder.where(ManufacturerDao.Properties.Is_motorcycle.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            case 3:
                return queryBuilder.where(ManufacturerDao.Properties.Is_dimple.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            case 4:
                return queryBuilder.where(ManufacturerDao.Properties.Is_standard.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            case 5:
                return queryBuilder.where(ManufacturerDao.Properties.Is_tubular.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            case 6:
                return queryBuilder.where(ManufacturerDao.Properties.Is_automobile_chs.eq(1), ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
            default:
                return queryBuilder.where(ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
        }
    }

    public List<Model> getModels(int i) {
        return getDaoSession().queryBuilder(Model.class).where(ModelDao.Properties.FK_ManufacturerID.eq(Integer.valueOf(i)), ModelDao.Properties.Is_visible.eq(1)).list();
    }

    public List<ModelChina> getChinaModels(int i) {
        return getDaoSession().queryBuilder(ModelChina.class).where(ModelChinaDao.Properties.FK_ManufacturerID.eq(Integer.valueOf(i)), ModelChinaDao.Properties.Is_visible.eq(1)).list();
    }

    public List<ModelYear> getModelYears(int i) {
        return getDaoSession().queryBuilder(ModelYear.class).where(ModelYearDao.Properties.FK_ModelID.eq(Integer.valueOf(i)), new WhereCondition[0]).orderAsc(ModelYearDao.Properties.Sort).list();
    }

    public List<ModelYearChina> getChinaModelYears(int i) {
        return getDaoSession().queryBuilder(ModelYearChina.class).where(ModelYearChinaDao.Properties.FK_ModelChinaID.eq(Integer.valueOf(i)), new WhereCondition[0]).orderAsc(ModelYearChinaDao.Properties.Sort).list();
    }

    public List<ModelSeries> getModelSeries(int i) {
        return getDaoSession().queryBuilder(ModelSeries.class).where(ModelSeriesDao.Properties.FK_ModelYearID.eq(Integer.valueOf(i)), new WhereCondition[0]).orderAsc(ModelSeriesDao.Properties.Sort).list();
    }

    public List<ModelSeriesChina> getChinaModelSeries(int i) {
        return getDaoSession().queryBuilder(ModelSeriesChina.class).where(ModelSeriesChinaDao.Properties.FK_ModelYearChinaID.eq(Integer.valueOf(i)), new WhereCondition[0]).orderAsc(ModelSeriesChinaDao.Properties.Sort).list();
    }

    public KeyBasicData getBasicData(int i) {
        List list = getDaoSession().queryBuilder(KeyBasicData.class).where(KeyBasicDataDao.Properties.IcCard.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
        if (list.size() == 0) {
            return null;
        }
        return (KeyBasicData) list.get(0);
    }

    public KeyBasicDataItem getBasicDataSide(int i) {
        List list = getDaoSession().queryBuilder(KeyBasicDataItem.class).where(KeyBasicDataItemDao.Properties.Id.eq(Long.valueOf(i)), new WhereCondition[0]).list();
        if (list.size() == 0) {
            return null;
        }
        return (KeyBasicDataItem) list.get(0);
    }

    public List<KeyBlankItemSearch> searchKey(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT T.FK_KeyID ,J1.KeyblankItemName , K.KeyBlankName,D.space,D.parameter_info FROM KeyBlankItem_BasicData T , KeyblankItem J1 , KeyBlank K,KeyBasicData D  WHERE T.FK_KeyBlankItemID=J1.ID and J1.FK_KeyblankID=K.ID and T.FK_KeyID=D.ID and J1.KeyblankItemName LIKE '" + (str + "%") + "'", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(new KeyBlankItemSearch(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_KeyID")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("KeyblankItemName")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("KeyBlankName")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("space")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("parameter_info"))));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<CardsSystem> searchID(String str) {
        QueryBuilder<CardsSystem> queryBuilder = getDaoSession().getCardsSystemDao().queryBuilder();
        queryBuilder.where(CardsSystemDao.Properties.KeyID.eq(str), new WhereCondition[0]);
        return queryBuilder.orderAsc(CardsSystemDao.Properties.KeyID).list();
    }

    public List<ChinaNumSearch> searchChinaKeyNumber(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT T.KeyChinaNum,T.FK_KeyID,T.Cuts,T.CodeSeries,T.ID,J.ModelName,J.ModelName_CN,K.Name,K.Name_CN,Q.FromYear,Q.ToYear FROM ModelSeriesChina T , ModelChina J,Manufacturer K,ModelYearChina Q WHERE K.ID=J.FK_ManufacturerID and J.ID=Q.FK_ModelChinaID  and T.FK_ModelYearChinaID=Q.ID and T.KeyChinaNum like '" + str + "%' order by T.KeyChinaNum asc", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(new ChinaNumSearch(rawQuery.getString(rawQuery.getColumnIndexOrThrow("KeyChinaNum")), rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_KeyID")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("CodeSeries")), rawQuery.getInt(rawQuery.getColumnIndexOrThrow(BarCodeRemindActivity.ID)), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Cuts")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Name")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Name_CN")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("ModelName")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("ModelName_CN")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("FromYear")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("ToYear"))));
        }
        rawQuery.close();
        return arrayList;
    }

    public String getDbVersion() {
        if (!ResUpdateUtils.localMainDbExist()) {
            return "0";
        }
        var unique = getDaoSession().getLocalDbVersionDao().queryBuilder().unique();
        if (unique == null) {
            return "";
        }
        try {
            return DesUtil.decrypt(unique.getLocResVer(), DesUtil.DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<DataManufacturer> getTechnicalInfoManufacturers() {
        return getDaoSession().queryBuilder(DataManufacturer.class).list();
    }

    public List<DataModel> getTechnicalInfoModels(int i) {
        return getDaoSession().queryBuilder(DataModel.class).where(DataModelDao.Properties.FK_ManufacturerID.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
    }

    public List<DataModelSeries> getTechnicalInfoModelSeries(int i) {
        return getDaoSession().queryBuilder(DataModelSeries.class).where(DataModelSeriesDao.Properties.FK_ModelID.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
    }

    public List<DataModelSeriesYear> getTechnicalInfoModelSeriesYear(int i) {
        return getDaoSession().queryBuilder(DataModelSeriesYear.class).where(DataModelSeriesYearDao.Properties.FK_ModelSeries.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
    }

    public DataList getTechnicalDataList(int i) {
        List list = getDaoSession().queryBuilder(DataList.class).where(DataListDao.Properties.FK_ModelSeriesYearID.eq(Integer.valueOf(i)), new WhereCondition[0]).list();
        if (list.size() == 0) {
            return null;
        }
        return (DataList) list.get(0);
    }

    public boolean isChineseCar(int i) {
        String str;
        if (MachineInfo.isChineseMachine()) {
            str = "SELECT K.is_automobile_chs FROM ModelSeriesChina T , ModelChina J,Manufacturer K,ModelYearChina Q WHERE K.ID=J.FK_ManufacturerID and J.ID=Q.FK_ModelChinaID  and T.FK_ModelYearChinaID=Q.ID and T.ID=" + i;
        } else {
            str = "SELECT K.is_automobile_chs FROM ModelSeries T , Model J,Manufacturer K,ModelYear Q WHERE K.ID=J.FK_ManufacturerID and J.ID=Q.FK_ModelID  and T.FK_ModelYearID=Q.ID and T.ID=" + i;
        }
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery(str, null);
        if (rawQuery.moveToNext()) {
            int i2 = rawQuery.getInt(rawQuery.getColumnIndexOrThrow("is_automobile_chs"));
            rawQuery.close();
            return i2 != 0;
        }
        rawQuery.close();
        return false;
    }

    public List<Manufacturer> getManufacturersExceptKeys(List<Integer> list) {
        return getDaoSession().queryBuilder(Manufacturer.class).where(ManufacturerDao.Properties.Is_visible.eq(1), ManufacturerDao.Properties.Is_automobile.eq(1), ManufacturerDao.Properties.ManufacturerId.notIn(list)).list();
    }

    public String getUpdateInfo() {
        var unique = getDaoSession().getLocalDbVersionDao().queryBuilder().unique();
        if (unique == null) {
            return "";
        }
        try {
            return DesUtil.decrypt(unique.toString(), DesUtil.DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<UsaSearchExtItemBasicData> searchBlitzOrDsd(int i, String str) {
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT T.FK_KeyID,J1.FK_SearchExtID,J1.Name,K.space,K.parameter_info FROM USA_SearchExtItem_BasicData T , USA_SearchExtItem J1,KeyBasicData K WHERE T.FK_SearchExtItemID=J1.ID and T.FK_KeyID=K.ID and J1.FK_SearchExtID=" + i + "  and J1.Name LIKE '%" + str + "%' order by J1.Name asc", null);
        ArrayList arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            arrayList.add(new UsaSearchExtItemBasicData(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_KeyID")), rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_SearchExtID")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Name")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("space")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("parameter_info"))));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<BarCodeSearch> searchBarCode(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT J.FK_KeyID ,T.SKU,T.ofCuts FROM USA_AbridgedDB T, USA_AbridgedDB_BasicData J WHERE (T.SKU LIKE '" + str + "%' OR T.UPC1Durakey LIKE '" + str + "' OR T.UPC2Keystart LIKE '" + str + "' OR T.HillmanDurakey LIKE '" + str + "' OR T.HillmanKeystart LIKE '" + str + "') AND T.ID=J.FK_AbridgedDBID ", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(new BarCodeSearch(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_KeyID")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("SKU")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("ofCuts"))));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchKeyBlank(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT DISTINCT J1.KeyblankItemName FROM  KeyblankItem J1 WHERE J1.KeyblankItemName LIKE '" + (str + "%") + "'", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("KeyblankItemName")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchKeyManu(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("SELECT K.KeyBlankName FROM KeyblankItem J1 , KeyBlank K WHERE J1.FK_KeyblankID=K.ID and J1.KeyblankItemName LIKE '" + str + "'", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("KeyBlankName")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchLockSystem(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("select DISTINCT * from WL_LockSystem where LockSystemName like '" + str + "%'", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("LockSystemName")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchLockManu(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("select DISTINCT LockManufacturerName from WL_LockManufacturer where LockManufacturerName like '" + str + "%'", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("LockManufacturerName")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchSn(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("select DISTINCT SN from WL_MenuSummary where SN like '" + str + "%' order by SN", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("SN")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchCard(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("select DISTINCT Card from WL_MenuSummary where Card like '" + str + "%' order by Card", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("Card")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<String> searchKid(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getDaoSession().getDatabase().rawQuery("select DISTINCT FK_KeyID from WL_MenuSummary where FK_KeyID like '" + str + "%' order by FK_KeyID", null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndexOrThrow("FK_KeyID")));
        }
        rawQuery.close();
        return arrayList;
    }

    public List<MenuSummary> advSearch(SearchCondition searchCondition) {
        Database database;
        String str;
        String kid = searchCondition.getKid();
        TextUtils.isEmpty(kid);
        String silcaCard = searchCondition.getSilcaCard();
        TextUtils.isEmpty(silcaCard);
        String silcaSN = searchCondition.getSilcaSN();
        TextUtils.isEmpty(silcaSN);
        String keyBlank = searchCondition.getKeyBlank();
        if (TextUtils.isEmpty(keyBlank)) {
            keyBlank = "";
        }
        String keyBlankManu = searchCondition.getKeyBlankManu();
        if (TextUtils.isEmpty(keyBlankManu)) {
            keyBlankManu = "";
        }
        String lockSys = searchCondition.getLockSys();
        if (TextUtils.isEmpty(lockSys)) {
            lockSys = "";
        }
        String replace = lockSys.replace(" ", "");
        String lockManu = searchCondition.getLockManu();
        String str2 = TextUtils.isEmpty(lockManu) ? "" : lockManu;
        Database database2 = getDaoSession().getDatabase();
        if (TextUtils.isEmpty(silcaCard) && TextUtils.isEmpty(silcaSN) && TextUtils.isEmpty(kid)) {
            if (TextUtils.isEmpty(keyBlank)) {
                str = "select DISTINCT W.FK_KeyID,W.SN,W.LockSystem,W.Series,W.Card,W.Type,W.Nofcuts from WL_MenuSummary W where W.LockManufacturerName like '" + str2 + "%' and W.LockSystem like '" + replace + "%' order by W.FK_KeyID asc";
            } else {
                str = "select DISTINCT W.FK_KeyID,W.SN,W.LockSystem,W.Series,W.Card,W.Type,W.Nofcuts from WL_MenuSummary W,KeyblankItem K,KeyBlankItem_BasicData B,KeyBlank M where W.FK_KeyID=B.FK_KeyID and K.ID=B.FK_KeyBlankItemID and M.ID=K.FK_KeyblankID and K.KeyblankItemName like '" + keyBlank + "%'  and M.KeyBlankName like '" + keyBlankManu + "%' and W.LockManufacturerName like '" + str2 + "%' and W.LockSystem like '" + replace + "%' order by W.FK_KeyID asc";
            }
            database = database2;
        } else {
            database = database2;
            if (TextUtils.isEmpty(keyBlank)) {
                str = "select DISTINCT W.FK_KeyID,W.SN,W.LockSystem,W.Series,W.Card,W.Type,W.Nofcuts from WL_MenuSummary W where W.LockManufacturerName like '" + str2 + "%' and W.LockSystem like '" + replace + "%' and (W.Card=" + silcaCard + " or W.FK_KeyID=" + kid + " or W.SN=" + silcaSN + ") order by W.FK_KeyID asc";
            } else {
                str = "select DISTINCT W.FK_KeyID,W.SN,W.LockSystem,W.Series,W.Card,W.Type,W.Nofcuts from WL_MenuSummary W,KeyblankItem K,KeyBlankItem_BasicData B,KeyBlank M where W.FK_KeyID=B.FK_KeyID and K.ID=B.FK_KeyBlankItemID and M.ID=K.FK_KeyblankID and K.KeyblankItemName like '" + keyBlank + "%'  and M.KeyBlankName like '" + keyBlankManu + "%' and W.LockManufacturerName like '" + str2 + "%' and W.LockSystem like '" + replace + "%' and (W.Card=" + silcaCard + " or W.FK_KeyID=" + kid + " or W.SN=" + silcaSN + ") order by W.FK_KeyID asc";
            }
        }
        Cursor rawQuery = database.rawQuery(str, null);
        ArrayList arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            try {
                arrayList.add(new MenuSummary(rawQuery.getInt(rawQuery.getColumnIndexOrThrow("FK_KeyID")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("SN")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("LockSystem")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Series")), rawQuery.getInt(rawQuery.getColumnIndexOrThrow("Card")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Type")), rawQuery.getString(rawQuery.getColumnIndexOrThrow("Nofcuts"))));
            } catch (Exception unused) {
                rawQuery.close();
            }
        }
        return arrayList;
    }
}