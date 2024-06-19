package com.kkkcut.e20j.DbBean;

import com.kkkcut.e20j.DbBean.china.ModelChina;
import com.kkkcut.e20j.DbBean.china.ModelChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChinaDao;
import com.kkkcut.e20j.DbBean.china.ModelYearChina;
import com.kkkcut.e20j.DbBean.china.ModelYearChinaDao;
import com.kkkcut.e20j.DbBean.search.CardsSystem;
import com.kkkcut.e20j.DbBean.search.CardsSystemDao;
import com.kkkcut.e20j.DbBean.search.KeyBlankItemBasicDataSearch;
import com.kkkcut.e20j.DbBean.search.KeyBlankItemBasicDataSearchDao;
import com.kkkcut.e20j.DbBean.search.SearchManufacturer;
import com.kkkcut.e20j.DbBean.search.SearchManufacturerDao;
import com.kkkcut.e20j.DbBean.search.SearchModelChina;
import com.kkkcut.e20j.DbBean.search.SearchModelChinaDao;
import com.kkkcut.e20j.DbBean.search.SearchModelYearChina;
import com.kkkcut.e20j.DbBean.search.SearchModelYearChinaDao;
import com.kkkcut.e20j.DbBean.technical.DataList;
import com.kkkcut.e20j.DbBean.technical.DataListDao;
import com.kkkcut.e20j.DbBean.technical.DataManufacturer;
import com.kkkcut.e20j.DbBean.technical.DataManufacturerDao;
import com.kkkcut.e20j.DbBean.technical.DataModel;
import com.kkkcut.e20j.DbBean.technical.DataModelDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeries;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesDao;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYear;
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYearDao;
import com.kkkcut.e20j.DbBean.userDB.CollectionData;
import com.kkkcut.e20j.DbBean.userDB.CollectionDataDao;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.DbBean.userDB.CustomKeyDao;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryDataDao;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple;
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimpleDao;
import com.kkkcut.e20j.DbBean.userDB.JpushMsg;
import com.kkkcut.e20j.DbBean.userDB.JpushMsgDao;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChild;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingChildDao;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate;
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplateDao;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHidden;
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHiddenDao;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* loaded from: classes.dex */
public class DaoSession extends AbstractDaoSession {
    private final BittingCodeDao bittingCodeDao;
    private final DaoConfig bittingCodeDaoConfig;
    private final CardsSystemDao cardsSystemDao;
    private final DaoConfig cardsSystemDaoConfig;
    private final ClampKeyBasicDataDao clampKeyBasicDataDao;
    private final DaoConfig clampKeyBasicDataDaoConfig;
    private final CollectionDataDao collectionDataDao;
    private final DaoConfig collectionDataDaoConfig;
    private final CustomKeyDao customKeyDao;
    private final DaoConfig customKeyDaoConfig;
    private final CutHistoryDataDao cutHistoryDataDao;
    private final DaoConfig cutHistoryDataDaoConfig;
    private final DataListDao dataListDao;
    private final DaoConfig dataListDaoConfig;
    private final DataManufacturerDao dataManufacturerDao;
    private final DaoConfig dataManufacturerDaoConfig;
    private final DataModelDao dataModelDao;
    private final DaoConfig dataModelDaoConfig;
    private final DataModelSeriesDao dataModelSeriesDao;
    private final DaoConfig dataModelSeriesDaoConfig;
    private final DataModelSeriesYearDao dataModelSeriesYearDao;
    private final DaoConfig dataModelSeriesYearDaoConfig;
    private final DbVersionDao dbVersionDao;
    private final DaoConfig dbVersionDaoConfig;
    private final DuplicateDimpleDao duplicateDimpleDao;
    private final DaoConfig duplicateDimpleDaoConfig;
    private final ErrorCodeBeanDao errorCodeBeanDao;
    private final DaoConfig errorCodeBeanDaoConfig;
    private final JpushMsgDao jpushMsgDao;
    private final DaoConfig jpushMsgDaoConfig;
    private final KeyBasicDataDao keyBasicDataDao;
    private final DaoConfig keyBasicDataDaoConfig;
    private final KeyBasicDataItemDao keyBasicDataItemDao;
    private final DaoConfig keyBasicDataItemDaoConfig;
    private final KeyBlankDao keyBlankDao;
    private final DaoConfig keyBlankDaoConfig;
    private final KeyBlankItemBasicDataDao keyBlankItemBasicDataDao;
    private final DaoConfig keyBlankItemBasicDataDaoConfig;
    private final KeyBlankItemBasicDataSearchDao keyBlankItemBasicDataSearchDao;
    private final DaoConfig keyBlankItemBasicDataSearchDaoConfig;
    private final KeyImageDao keyImageDao;
    private final DaoConfig keyImageDaoConfig;
    private final KeyMarkingChildDao keyMarkingChildDao;
    private final DaoConfig keyMarkingChildDaoConfig;
    private final KeyMarkingTemplateDao keyMarkingTemplateDao;
    private final DaoConfig keyMarkingTemplateDaoConfig;
    private final KeyResDao keyResDao;
    private final DaoConfig keyResDaoConfig;
    private final KeyResourceDao keyResourceDao;
    private final DaoConfig keyResourceDaoConfig;
    private final KeyThumbnailDao keyThumbnailDao;
    private final DaoConfig keyThumbnailDaoConfig;
    private final KeyblankItemDao keyblankItemDao;
    private final DaoConfig keyblankItemDaoConfig;
    private final LocalDbVersionDao localDbVersionDao;
    private final DaoConfig localDbVersionDaoConfig;
    private final ManufacturerDao manufacturerDao;
    private final DaoConfig manufacturerDaoConfig;
    private final ManufacturerHiddenDao manufacturerHiddenDao;
    private final DaoConfig manufacturerHiddenDaoConfig;
    private final ModelChinaDao modelChinaDao;
    private final DaoConfig modelChinaDaoConfig;
    private final ModelDao modelDao;
    private final DaoConfig modelDaoConfig;
    private final ModelSeriesChinaDao modelSeriesChinaDao;
    private final DaoConfig modelSeriesChinaDaoConfig;
    private final ModelSeriesDao modelSeriesDao;
    private final DaoConfig modelSeriesDaoConfig;
    private final ModelYearChinaDao modelYearChinaDao;
    private final DaoConfig modelYearChinaDaoConfig;
    private final ModelYearDao modelYearDao;
    private final DaoConfig modelYearDaoConfig;
    private final SearchManufacturerDao searchManufacturerDao;
    private final DaoConfig searchManufacturerDaoConfig;
    private final SearchModelChinaDao searchModelChinaDao;
    private final DaoConfig searchModelChinaDaoConfig;
    private final SearchModelYearChinaDao searchModelYearChinaDao;
    private final DaoConfig searchModelYearChinaDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        DaoConfig clone = map.get(BittingCodeDao.class).clone();
        this.bittingCodeDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        DaoConfig clone2 = map.get(ClampKeyBasicDataDao.class).clone();
        this.clampKeyBasicDataDaoConfig = clone2;
        clone2.initIdentityScope(identityScopeType);
        DaoConfig clone3 = map.get(DbVersionDao.class).clone();
        this.dbVersionDaoConfig = clone3;
        clone3.initIdentityScope(identityScopeType);
        DaoConfig clone4 = map.get(ErrorCodeBeanDao.class).clone();
        this.errorCodeBeanDaoConfig = clone4;
        clone4.initIdentityScope(identityScopeType);
        DaoConfig clone5 = map.get(KeyBasicDataDao.class).clone();
        this.keyBasicDataDaoConfig = clone5;
        clone5.initIdentityScope(identityScopeType);
        DaoConfig clone6 = map.get(KeyBasicDataItemDao.class).clone();
        this.keyBasicDataItemDaoConfig = clone6;
        clone6.initIdentityScope(identityScopeType);
        DaoConfig clone7 = map.get(KeyBlankDao.class).clone();
        this.keyBlankDaoConfig = clone7;
        clone7.initIdentityScope(identityScopeType);
        DaoConfig clone8 = map.get(KeyBlankItemBasicDataDao.class).clone();
        this.keyBlankItemBasicDataDaoConfig = clone8;
        clone8.initIdentityScope(identityScopeType);
        DaoConfig clone9 = map.get(KeyImageDao.class).clone();
        this.keyImageDaoConfig = clone9;
        clone9.initIdentityScope(identityScopeType);
        DaoConfig clone10 = map.get(KeyResDao.class).clone();
        this.keyResDaoConfig = clone10;
        clone10.initIdentityScope(identityScopeType);
        DaoConfig clone11 = map.get(KeyResourceDao.class).clone();
        this.keyResourceDaoConfig = clone11;
        clone11.initIdentityScope(identityScopeType);
        DaoConfig clone12 = map.get(KeyThumbnailDao.class).clone();
        this.keyThumbnailDaoConfig = clone12;
        clone12.initIdentityScope(identityScopeType);
        DaoConfig clone13 = map.get(KeyblankItemDao.class).clone();
        this.keyblankItemDaoConfig = clone13;
        clone13.initIdentityScope(identityScopeType);
        DaoConfig clone14 = map.get(LocalDbVersionDao.class).clone();
        this.localDbVersionDaoConfig = clone14;
        clone14.initIdentityScope(identityScopeType);
        DaoConfig clone15 = map.get(ManufacturerDao.class).clone();
        this.manufacturerDaoConfig = clone15;
        clone15.initIdentityScope(identityScopeType);
        DaoConfig clone16 = map.get(ModelDao.class).clone();
        this.modelDaoConfig = clone16;
        clone16.initIdentityScope(identityScopeType);
        DaoConfig clone17 = map.get(ModelSeriesDao.class).clone();
        this.modelSeriesDaoConfig = clone17;
        clone17.initIdentityScope(identityScopeType);
        DaoConfig clone18 = map.get(ModelYearDao.class).clone();
        this.modelYearDaoConfig = clone18;
        clone18.initIdentityScope(identityScopeType);
        DaoConfig clone19 = map.get(ModelChinaDao.class).clone();
        this.modelChinaDaoConfig = clone19;
        clone19.initIdentityScope(identityScopeType);
        DaoConfig clone20 = map.get(ModelSeriesChinaDao.class).clone();
        this.modelSeriesChinaDaoConfig = clone20;
        clone20.initIdentityScope(identityScopeType);
        DaoConfig clone21 = map.get(ModelYearChinaDao.class).clone();
        this.modelYearChinaDaoConfig = clone21;
        clone21.initIdentityScope(identityScopeType);
        DaoConfig clone22 = map.get(CardsSystemDao.class).clone();
        this.cardsSystemDaoConfig = clone22;
        clone22.initIdentityScope(identityScopeType);
        DaoConfig clone23 = map.get(KeyBlankItemBasicDataSearchDao.class).clone();
        this.keyBlankItemBasicDataSearchDaoConfig = clone23;
        clone23.initIdentityScope(identityScopeType);
        DaoConfig clone24 = map.get(SearchManufacturerDao.class).clone();
        this.searchManufacturerDaoConfig = clone24;
        clone24.initIdentityScope(identityScopeType);
        DaoConfig clone25 = map.get(SearchModelChinaDao.class).clone();
        this.searchModelChinaDaoConfig = clone25;
        clone25.initIdentityScope(identityScopeType);
        DaoConfig clone26 = map.get(SearchModelYearChinaDao.class).clone();
        this.searchModelYearChinaDaoConfig = clone26;
        clone26.initIdentityScope(identityScopeType);
        DaoConfig clone27 = map.get(DataListDao.class).clone();
        this.dataListDaoConfig = clone27;
        clone27.initIdentityScope(identityScopeType);
        DaoConfig clone28 = map.get(DataManufacturerDao.class).clone();
        this.dataManufacturerDaoConfig = clone28;
        clone28.initIdentityScope(identityScopeType);
        DaoConfig clone29 = map.get(DataModelDao.class).clone();
        this.dataModelDaoConfig = clone29;
        clone29.initIdentityScope(identityScopeType);
        DaoConfig clone30 = map.get(DataModelSeriesDao.class).clone();
        this.dataModelSeriesDaoConfig = clone30;
        clone30.initIdentityScope(identityScopeType);
        DaoConfig clone31 = map.get(DataModelSeriesYearDao.class).clone();
        this.dataModelSeriesYearDaoConfig = clone31;
        clone31.initIdentityScope(identityScopeType);
        DaoConfig clone32 = map.get(CollectionDataDao.class).clone();
        this.collectionDataDaoConfig = clone32;
        clone32.initIdentityScope(identityScopeType);
        DaoConfig clone33 = map.get(CustomKeyDao.class).clone();
        this.customKeyDaoConfig = clone33;
        clone33.initIdentityScope(identityScopeType);
        DaoConfig clone34 = map.get(CutHistoryDataDao.class).clone();
        this.cutHistoryDataDaoConfig = clone34;
        clone34.initIdentityScope(identityScopeType);
        DaoConfig clone35 = map.get(DuplicateDimpleDao.class).clone();
        this.duplicateDimpleDaoConfig = clone35;
        clone35.initIdentityScope(identityScopeType);
        DaoConfig clone36 = map.get(JpushMsgDao.class).clone();
        this.jpushMsgDaoConfig = clone36;
        clone36.initIdentityScope(identityScopeType);
        DaoConfig clone37 = map.get(KeyMarkingChildDao.class).clone();
        this.keyMarkingChildDaoConfig = clone37;
        clone37.initIdentityScope(identityScopeType);
        DaoConfig clone38 = map.get(KeyMarkingTemplateDao.class).clone();
        this.keyMarkingTemplateDaoConfig = clone38;
        clone38.initIdentityScope(identityScopeType);
        DaoConfig clone39 = map.get(ManufacturerHiddenDao.class).clone();
        this.manufacturerHiddenDaoConfig = clone39;
        clone39.initIdentityScope(identityScopeType);
        BittingCodeDao bittingCodeDao = new BittingCodeDao(clone, this);
        this.bittingCodeDao = bittingCodeDao;
        ClampKeyBasicDataDao clampKeyBasicDataDao = new ClampKeyBasicDataDao(clone2, this);
        this.clampKeyBasicDataDao = clampKeyBasicDataDao;
        DbVersionDao dbVersionDao = new DbVersionDao(clone3, this);
        this.dbVersionDao = dbVersionDao;
        ErrorCodeBeanDao errorCodeBeanDao = new ErrorCodeBeanDao(clone4, this);
        this.errorCodeBeanDao = errorCodeBeanDao;
        KeyBasicDataDao keyBasicDataDao = new KeyBasicDataDao(clone5, this);
        this.keyBasicDataDao = keyBasicDataDao;
        KeyBasicDataItemDao keyBasicDataItemDao = new KeyBasicDataItemDao(clone6, this);
        this.keyBasicDataItemDao = keyBasicDataItemDao;
        KeyBlankDao keyBlankDao = new KeyBlankDao(clone7, this);
        this.keyBlankDao = keyBlankDao;
        KeyBlankItemBasicDataDao keyBlankItemBasicDataDao = new KeyBlankItemBasicDataDao(clone8, this);
        this.keyBlankItemBasicDataDao = keyBlankItemBasicDataDao;
        KeyImageDao keyImageDao = new KeyImageDao(clone9, this);
        this.keyImageDao = keyImageDao;
        KeyResDao keyResDao = new KeyResDao(clone10, this);
        this.keyResDao = keyResDao;
        KeyResourceDao keyResourceDao = new KeyResourceDao(clone11, this);
        this.keyResourceDao = keyResourceDao;
        KeyThumbnailDao keyThumbnailDao = new KeyThumbnailDao(clone12, this);
        this.keyThumbnailDao = keyThumbnailDao;
        KeyblankItemDao keyblankItemDao = new KeyblankItemDao(clone13, this);
        this.keyblankItemDao = keyblankItemDao;
        LocalDbVersionDao localDbVersionDao = new LocalDbVersionDao(clone14, this);
        this.localDbVersionDao = localDbVersionDao;
        ManufacturerDao manufacturerDao = new ManufacturerDao(clone15, this);
        this.manufacturerDao = manufacturerDao;
        ModelDao modelDao = new ModelDao(clone16, this);
        this.modelDao = modelDao;
        ModelSeriesDao modelSeriesDao = new ModelSeriesDao(clone17, this);
        this.modelSeriesDao = modelSeriesDao;
        ModelYearDao modelYearDao = new ModelYearDao(clone18, this);
        this.modelYearDao = modelYearDao;
        ModelChinaDao modelChinaDao = new ModelChinaDao(clone19, this);
        this.modelChinaDao = modelChinaDao;
        ModelSeriesChinaDao modelSeriesChinaDao = new ModelSeriesChinaDao(clone20, this);
        this.modelSeriesChinaDao = modelSeriesChinaDao;
        ModelYearChinaDao modelYearChinaDao = new ModelYearChinaDao(clone21, this);
        this.modelYearChinaDao = modelYearChinaDao;
        CardsSystemDao cardsSystemDao = new CardsSystemDao(clone22, this);
        this.cardsSystemDao = cardsSystemDao;
        KeyBlankItemBasicDataSearchDao keyBlankItemBasicDataSearchDao = new KeyBlankItemBasicDataSearchDao(clone23, this);
        this.keyBlankItemBasicDataSearchDao = keyBlankItemBasicDataSearchDao;
        SearchManufacturerDao searchManufacturerDao = new SearchManufacturerDao(clone24, this);
        this.searchManufacturerDao = searchManufacturerDao;
        SearchModelChinaDao searchModelChinaDao = new SearchModelChinaDao(clone25, this);
        this.searchModelChinaDao = searchModelChinaDao;
        SearchModelYearChinaDao searchModelYearChinaDao = new SearchModelYearChinaDao(clone26, this);
        this.searchModelYearChinaDao = searchModelYearChinaDao;
        DataListDao dataListDao = new DataListDao(clone27, this);
        this.dataListDao = dataListDao;
        DataManufacturerDao dataManufacturerDao = new DataManufacturerDao(clone28, this);
        this.dataManufacturerDao = dataManufacturerDao;
        DataModelDao dataModelDao = new DataModelDao(clone29, this);
        this.dataModelDao = dataModelDao;
        DataModelSeriesDao dataModelSeriesDao = new DataModelSeriesDao(clone30, this);
        this.dataModelSeriesDao = dataModelSeriesDao;
        DataModelSeriesYearDao dataModelSeriesYearDao = new DataModelSeriesYearDao(clone31, this);
        this.dataModelSeriesYearDao = dataModelSeriesYearDao;
        CollectionDataDao collectionDataDao = new CollectionDataDao(clone32, this);
        this.collectionDataDao = collectionDataDao;
        CustomKeyDao customKeyDao = new CustomKeyDao(clone33, this);
        this.customKeyDao = customKeyDao;
        CutHistoryDataDao cutHistoryDataDao = new CutHistoryDataDao(clone34, this);
        this.cutHistoryDataDao = cutHistoryDataDao;
        DuplicateDimpleDao duplicateDimpleDao = new DuplicateDimpleDao(clone35, this);
        this.duplicateDimpleDao = duplicateDimpleDao;
        JpushMsgDao jpushMsgDao = new JpushMsgDao(clone36, this);
        this.jpushMsgDao = jpushMsgDao;
        KeyMarkingChildDao keyMarkingChildDao = new KeyMarkingChildDao(clone37, this);
        this.keyMarkingChildDao = keyMarkingChildDao;
        KeyMarkingTemplateDao keyMarkingTemplateDao = new KeyMarkingTemplateDao(clone38, this);
        this.keyMarkingTemplateDao = keyMarkingTemplateDao;
        ManufacturerHiddenDao manufacturerHiddenDao = new ManufacturerHiddenDao(clone39, this);
        this.manufacturerHiddenDao = manufacturerHiddenDao;
        registerDao(BittingCode.class, bittingCodeDao);
        registerDao(ClampKeyBasicData.class, clampKeyBasicDataDao);
        registerDao(DbVersion.class, dbVersionDao);
        registerDao(ErrorCodeBean.class, errorCodeBeanDao);
        registerDao(KeyBasicData.class, keyBasicDataDao);
        registerDao(KeyBasicDataItem.class, keyBasicDataItemDao);
        registerDao(KeyBlank.class, keyBlankDao);
        registerDao(KeyBlankItemBasicData.class, keyBlankItemBasicDataDao);
        registerDao(KeyImage.class, keyImageDao);
        registerDao(KeyRes.class, keyResDao);
        registerDao(KeyResource.class, keyResourceDao);
        registerDao(KeyThumbnail.class, keyThumbnailDao);
        registerDao(KeyblankItem.class, keyblankItemDao);
        registerDao(LocalDbVersion.class, localDbVersionDao);
        registerDao(Manufacturer.class, manufacturerDao);
        registerDao(Model.class, modelDao);
        registerDao(ModelSeries.class, modelSeriesDao);
        registerDao(ModelYear.class, modelYearDao);
        registerDao(ModelChina.class, modelChinaDao);
        registerDao(ModelSeriesChina.class, modelSeriesChinaDao);
        registerDao(ModelYearChina.class, modelYearChinaDao);
        registerDao(CardsSystem.class, cardsSystemDao);
        registerDao(KeyBlankItemBasicDataSearch.class, keyBlankItemBasicDataSearchDao);
        registerDao(SearchManufacturer.class, searchManufacturerDao);
        registerDao(SearchModelChina.class, searchModelChinaDao);
        registerDao(SearchModelYearChina.class, searchModelYearChinaDao);
        registerDao(DataList.class, dataListDao);
        registerDao(DataManufacturer.class, dataManufacturerDao);
        registerDao(DataModel.class, dataModelDao);
        registerDao(DataModelSeries.class, dataModelSeriesDao);
        registerDao(DataModelSeriesYear.class, dataModelSeriesYearDao);
        registerDao(CollectionData.class, collectionDataDao);
        registerDao(CustomKey.class, customKeyDao);
        registerDao(CutHistoryData.class, cutHistoryDataDao);
        registerDao(DuplicateDimple.class, duplicateDimpleDao);
        registerDao(JpushMsg.class, jpushMsgDao);
        registerDao(KeyMarkingChild.class, keyMarkingChildDao);
        registerDao(KeyMarkingTemplate.class, keyMarkingTemplateDao);
        registerDao(ManufacturerHidden.class, manufacturerHiddenDao);
    }

    public void clear() {
        this.bittingCodeDaoConfig.clearIdentityScope();
        this.clampKeyBasicDataDaoConfig.clearIdentityScope();
        this.dbVersionDaoConfig.clearIdentityScope();
        this.errorCodeBeanDaoConfig.clearIdentityScope();
        this.keyBasicDataDaoConfig.clearIdentityScope();
        this.keyBasicDataItemDaoConfig.clearIdentityScope();
        this.keyBlankDaoConfig.clearIdentityScope();
        this.keyBlankItemBasicDataDaoConfig.clearIdentityScope();
        this.keyImageDaoConfig.clearIdentityScope();
        this.keyResDaoConfig.clearIdentityScope();
        this.keyResourceDaoConfig.clearIdentityScope();
        this.keyThumbnailDaoConfig.clearIdentityScope();
        this.keyblankItemDaoConfig.clearIdentityScope();
        this.localDbVersionDaoConfig.clearIdentityScope();
        this.manufacturerDaoConfig.clearIdentityScope();
        this.modelDaoConfig.clearIdentityScope();
        this.modelSeriesDaoConfig.clearIdentityScope();
        this.modelYearDaoConfig.clearIdentityScope();
        this.modelChinaDaoConfig.clearIdentityScope();
        this.modelSeriesChinaDaoConfig.clearIdentityScope();
        this.modelYearChinaDaoConfig.clearIdentityScope();
        this.cardsSystemDaoConfig.clearIdentityScope();
        this.keyBlankItemBasicDataSearchDaoConfig.clearIdentityScope();
        this.searchManufacturerDaoConfig.clearIdentityScope();
        this.searchModelChinaDaoConfig.clearIdentityScope();
        this.searchModelYearChinaDaoConfig.clearIdentityScope();
        this.dataListDaoConfig.clearIdentityScope();
        this.dataManufacturerDaoConfig.clearIdentityScope();
        this.dataModelDaoConfig.clearIdentityScope();
        this.dataModelSeriesDaoConfig.clearIdentityScope();
        this.dataModelSeriesYearDaoConfig.clearIdentityScope();
        this.collectionDataDaoConfig.clearIdentityScope();
        this.customKeyDaoConfig.clearIdentityScope();
        this.cutHistoryDataDaoConfig.clearIdentityScope();
        this.duplicateDimpleDaoConfig.clearIdentityScope();
        this.jpushMsgDaoConfig.clearIdentityScope();
        this.keyMarkingChildDaoConfig.clearIdentityScope();
        this.keyMarkingTemplateDaoConfig.clearIdentityScope();
        this.manufacturerHiddenDaoConfig.clearIdentityScope();
    }

    public BittingCodeDao getBittingCodeDao() {
        return this.bittingCodeDao;
    }

    public ClampKeyBasicDataDao getClampKeyBasicDataDao() {
        return this.clampKeyBasicDataDao;
    }

    public DbVersionDao getDbVersionDao() {
        return this.dbVersionDao;
    }

    public ErrorCodeBeanDao getErrorCodeBeanDao() {
        return this.errorCodeBeanDao;
    }

    public KeyBasicDataDao getKeyBasicDataDao() {
        return this.keyBasicDataDao;
    }

    public KeyBasicDataItemDao getKeyBasicDataItemDao() {
        return this.keyBasicDataItemDao;
    }

    public KeyBlankDao getKeyBlankDao() {
        return this.keyBlankDao;
    }

    public KeyBlankItemBasicDataDao getKeyBlankItemBasicDataDao() {
        return this.keyBlankItemBasicDataDao;
    }

    public KeyImageDao getKeyImageDao() {
        return this.keyImageDao;
    }

    public KeyResDao getKeyResDao() {
        return this.keyResDao;
    }

    public KeyResourceDao getKeyResourceDao() {
        return this.keyResourceDao;
    }

    public KeyThumbnailDao getKeyThumbnailDao() {
        return this.keyThumbnailDao;
    }

    public KeyblankItemDao getKeyblankItemDao() {
        return this.keyblankItemDao;
    }

    public LocalDbVersionDao getLocalDbVersionDao() {
        return this.localDbVersionDao;
    }

    public ManufacturerDao getManufacturerDao() {
        return this.manufacturerDao;
    }

    public ModelDao getModelDao() {
        return this.modelDao;
    }

    public ModelSeriesDao getModelSeriesDao() {
        return this.modelSeriesDao;
    }

    public ModelYearDao getModelYearDao() {
        return this.modelYearDao;
    }

    public ModelChinaDao getModelChinaDao() {
        return this.modelChinaDao;
    }

    public ModelSeriesChinaDao getModelSeriesChinaDao() {
        return this.modelSeriesChinaDao;
    }

    public ModelYearChinaDao getModelYearChinaDao() {
        return this.modelYearChinaDao;
    }

    public CardsSystemDao getCardsSystemDao() {
        return this.cardsSystemDao;
    }

    public KeyBlankItemBasicDataSearchDao getKeyBlankItemBasicDataSearchDao() {
        return this.keyBlankItemBasicDataSearchDao;
    }

    public SearchManufacturerDao getSearchManufacturerDao() {
        return this.searchManufacturerDao;
    }

    public SearchModelChinaDao getSearchModelChinaDao() {
        return this.searchModelChinaDao;
    }

    public SearchModelYearChinaDao getSearchModelYearChinaDao() {
        return this.searchModelYearChinaDao;
    }

    public DataListDao getDataListDao() {
        return this.dataListDao;
    }

    public DataManufacturerDao getDataManufacturerDao() {
        return this.dataManufacturerDao;
    }

    public DataModelDao getDataModelDao() {
        return this.dataModelDao;
    }

    public DataModelSeriesDao getDataModelSeriesDao() {
        return this.dataModelSeriesDao;
    }

    public DataModelSeriesYearDao getDataModelSeriesYearDao() {
        return this.dataModelSeriesYearDao;
    }

    public CollectionDataDao getCollectionDataDao() {
        return this.collectionDataDao;
    }

    public CustomKeyDao getCustomKeyDao() {
        return this.customKeyDao;
    }

    public CutHistoryDataDao getCutHistoryDataDao() {
        return this.cutHistoryDataDao;
    }

    public DuplicateDimpleDao getDuplicateDimpleDao() {
        return this.duplicateDimpleDao;
    }

    public JpushMsgDao getJpushMsgDao() {
        return this.jpushMsgDao;
    }

    public KeyMarkingChildDao getKeyMarkingChildDao() {
        return this.keyMarkingChildDao;
    }

    public KeyMarkingTemplateDao getKeyMarkingTemplateDao() {
        return this.keyMarkingTemplateDao;
    }

    public ManufacturerHiddenDao getManufacturerHiddenDao() {
        return this.manufacturerHiddenDao;
    }
}
