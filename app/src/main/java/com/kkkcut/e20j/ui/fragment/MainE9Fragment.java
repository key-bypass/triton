package com.kkkcut.e20j.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.DbBean.GoOperatBean;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.adapter.HomeCenterRvE9Adapter;
import com.kkkcut.e20j.adapter.HomepageEExtraFunctionAdapter;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.GsonHelper;
import com.kkkcut.e20j.androidquick.tool.NetUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.dao.UserDataDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.activity.E9RegisterActivity;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment;
import com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment;
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment;
import com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment;
import com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment;
import com.kkkcut.e20j.ui.fragment.setting.SettingFragment;
import com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AppUpdateUtil;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;
import com.liying.core.MachineInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public class MainE9Fragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    private static final int REGISTER = 1;
    public static final String TAG = "MainFragment";
    private String companyStr;
    ConfigurationE9 configuration1;

    @BindView(R.id.view_devide_language)
    View devideLanguage;

    @BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.help_center)
    TextView helpCenter;

    @BindView(R.id.iv_service)
    ImageView ivService;

    @BindView(R.id.language_choice)
    TextView languageChoice;
    private String languageStr;
    private String machineName;

    @BindView(R.id.rv_center)
    RecyclerView rvCarkay;

    @BindView(R.id.rv_bottom)
    RecyclerView rvExtraFuntion;

    @BindView(R.id.tv_db_version)
    TextView tvDbVersion;

    @BindView(R.id.tv_series)
    TextView tvSeries;

    @BindView(R.id.tv_soft_version)
    TextView tvSoftVersion;

    /* JADX INFO: Access modifiers changed from: private */
    public void initDb() {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_e9;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static MainE9Fragment newInstance() {
        return new MainE9Fragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        setUserVisibleHint(true);
        initView();
        checkConfigUpdate();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.rvExtraFuntion.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(0);
        this.rvCarkay.addItemDecoration(new SpaceItemDecoration(AutoUtils.getPercentWidthSize(20), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentWidthSize(38), AutoUtils.getPercentHeightSize(15)));
        this.rvCarkay.setLayoutManager(gridLayoutManager);
        ((FrameActivity) getActivity()).showLogo();
        setSeries();
        this.helpCenter.setVisibility(8);
    }

    /* loaded from: classes.dex */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int bottom;
        private int left;
        private int right;
        private int top;

        public SpaceItemDecoration(int i, int i2, int i3, int i4) {
            this.top = i2;
            this.left = i;
            this.right = i3;
            this.bottom = i4;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = this.left;
            rect.top = this.top;
            rect.bottom = this.bottom;
            rect.right = this.right;
        }
    }

    private void setSeries() {
        String string = SPUtils.getString("series");
        if (!TextUtils.isEmpty(string)) {
            this.tvSeries.setText(getString(R.string.sn_xx) + string);
        }
        this.tvSoftVersion.setText(getString(R.string.version) + AppUtil.getVersionName(getContext()));
        String dbVersion = KeyInfoDaoManager.getInstance().getDbVersion();
        if (TextUtils.isEmpty(dbVersion)) {
            return;
        }
        this.tvDbVersion.setText(getString(R.string.db_version) + dbVersion);
    }

    private void checkConfigUpdate() {
        addDisposable(Observable.fromCallable(new Callable<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                if (AppUtil.isApkInDebug(MainE9Fragment.this.getContext()) || !NetUtil.isNetworkConnected(MainE9Fragment.this.getContext())) {
                    return false;
                }
                if (!new File(Constant.CONFIG_PATH).exists()) {
                    Log.i("MainFragment", "配置文件不存在: ");
                    return true;
                }
                return Boolean.valueOf(AssetVersionUtil.getAssetsDbVersion(MainE9Fragment.this.getContext().getAssets(), Constant.CONFIG_UPDATA) > SPUtils.getInt(SpKeys.CONFIG_UPDATE, 0));
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                Log.i("MainFragment", "accept: " + bool);
                if (bool.booleanValue()) {
                    MainE9Fragment.this.updateConfig();
                } else {
                    MainE9Fragment.this.getConfigurationE9();
                }
            }
        }));
    }

    public void getConfigurationE9() {
        if (AppUtil.isApkInDebug(getContext())) {
            addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda6
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return MainE9Fragment.this.m35x32abd4e4();
                }
            }).map(new Function() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda5
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MainE9Fragment.lambda$getConfigurationE9$1((String) obj);
                }
            }).doOnNext(new Consumer<ConfigurationE9>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.3
                @Override // io.reactivex.functions.Consumer
                public void accept(ConfigurationE9 configurationE9) throws Exception {
                    MainE9Fragment.this.initDb();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MainE9Fragment.this.m36xb884a7a2((ConfigurationE9) obj);
                }
            }, new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ToastUtil.showToast(R.string.network_unavailable);
                }
            }));
        } else {
            addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda7
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return MainE9Fragment.this.m37x3e5d7a60();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MainE9Fragment.this.m38x149e3bf((ConfigurationE9) obj);
                }
            }, new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MainE9Fragment.this.m39xc4364d1e((Throwable) obj);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ConfigurationE9 lambda$getConfigurationE9$1(String str) throws Exception {
        return (ConfigurationE9) GsonHelper.fromJson(str, ConfigurationE9.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getConfigurationE9$6$com-kkkcut-e20j-ui-fragment-MainE9Fragment, reason: not valid java name */
    public /* synthetic */ void m39xc4364d1e(Throwable th) throws Exception {
        Log.i("MainFragment", "getConfigurationE9: " + th.getMessage());
        goRegister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleConfig, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void m38x149e3bf(ConfigurationE9 configurationE9) {
        int identifier;
        this.configuration1 = configurationE9;
        this.languageStr = configurationE9.getLanguage();
        this.companyStr = configurationE9.getCompany();
        this.machineName = configurationE9.getTitle_layout().getModel().getName();
        ((FrameActivity) getActivity()).setMachineName(this.machineName);
        String id = configurationE9.getId();
        SPUtils.put(SpKeys.MACHINE_ID, id);
        MachineInfo.setMachineTypeAndRegion(MyApplication.getInstance(), id);
        if (MachineInfo.isChineseMachine()) {
            this.devideLanguage.setVisibility(8);
            this.languageChoice.setVisibility(8);
            LocalManageUtil.saveSelectLanguage(getContext(), "zh");
            this.ivService.setVisibility(0);
        }
        FrameActivity frameActivity = (FrameActivity) getActivity();
        frameActivity.checkHaveNewMessage();
        String img = configurationE9.getTitle_layout().getLogo().getImg();
        if (!TextUtils.isEmpty(img) && !MachineInfo.isE20Neutral(getContext()) && (identifier = getResources().getIdentifier(img, "drawable", getContext().getPackageName())) != 0) {
            frameActivity.setLogo(identifier);
        }
        String welcome_logo = configurationE9.getWelcome_logo();
        if (!TextUtils.isEmpty(welcome_logo)) {
            SPUtils.put("welcome", welcome_logo);
        } else {
            SPUtils.put("welcome", "welcome_kukai");
        }
        HomepageEExtraFunctionAdapter homepageEExtraFunctionAdapter = new HomepageEExtraFunctionAdapter(configurationE9.getBottom_layout());
        homepageEExtraFunctionAdapter.setOnItemClickListener(this);
        this.rvExtraFuntion.setAdapter(homepageEExtraFunctionAdapter);
        HomeCenterRvE9Adapter homeCenterRvE9Adapter = new HomeCenterRvE9Adapter(configurationE9.getCenter_layout());
        homeCenterRvE9Adapter.setOnItemClickListener(this);
        this.rvCarkay.setAdapter(homeCenterRvE9Adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkResUpdate(final Context context, String str) {
        addDisposable(ResUpdateUtils.checkResUpdate(getContext(), str, new Consumer<ResUpdateUtils.UpdateBean>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.4
            @Override // io.reactivex.functions.Consumer
            public void accept(ResUpdateUtils.UpdateBean updateBean) throws Exception {
                if (updateBean.isUpdate()) {
                    new AlertDialog.Builder(context).setIcon(R.drawable.upgrade).setTitle(R.string.ResUpdate).setMessage(updateBean.getUpdateLog()).setCancelable(false).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.4.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainE9Fragment.this.goDataUpgradeFragment();
                        }
                    }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).show();
                }
            }
        }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ToastUtil.showToast(th.getMessage());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goDataUpgradeFragment() {
        start(DataUpdateFragment.newInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readConfigrationFromLocal, reason: merged with bridge method [inline-methods] */
    public ConfigurationE9 m37x3e5d7a60() {
        return (ConfigurationE9) GsonHelper.fromJson(FileUtil.readIoToString(Constant.CONFIG_PATH), ConfigurationE9.class);
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        ConfigurationE9 configurationE9;
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 10) {
            if (this.drawerLayout.isDrawerOpen(5)) {
                this.drawerLayout.closeDrawer(5);
                return;
            } else {
                this.drawerLayout.openDrawer(5);
                return;
            }
        }
        if (eventCode == 37) {
            addDisposable(Observable.create(new ObservableOnSubscribe<String>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.7
                @Override // io.reactivex.ObservableOnSubscribe
                public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                    while (true) {
                        String string = SPUtils.getString(SpKeys.MACHINE_ID);
                        if (!TextUtils.isEmpty(string)) {
                            observableEmitter.onNext(string);
                            observableEmitter.onComplete();
                            return;
                        }
                        Thread.sleep(500L);
                    }
                }
            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.6
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                    MainE9Fragment mainE9Fragment = MainE9Fragment.this;
                    mainE9Fragment.checkResUpdate(mainE9Fragment.getContext(), str);
                }
            }));
            return;
        }
        if (eventCode == 40) {
            startActivityForResult(new Intent(getContext(), (Class<?>) E9RegisterActivity.class), 1);
            return;
        }
        if (eventCode == 44) {
            updateConfig();
        } else if (eventCode == 45 && (configurationE9 = this.configuration1) != null) {
            m38x149e3bf(configurationE9);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConfig() {
        String string = SPUtils.getString(SpKeys.CONFIGURATION_FILE);
        Log.i("MainFragment", "updateConfig: " + string);
        if (TextUtils.isEmpty(string)) {
            goRegister();
        } else {
            addDisposable(((Apis) RetrofitManager.getInstance().createApi(Apis.class)).getConfigE9(TUitls.getconfig(string)).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).doOnNext(new Consumer<ConfigurationE9>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.10
                @Override // io.reactivex.functions.Consumer
                public void accept(ConfigurationE9 configurationE9) throws Exception {
                    Log.i("MainFragment", "config--" + configurationE9.getDatabase());
                    FileUtil.readIoStringToFile(GsonHelper.toJsonString(configurationE9), Constant.CONFIG_PATH);
                    MainE9Fragment.this.initDb();
                    SPUtils.put(SpKeys.CONFIG_UPDATE, AssetVersionUtil.getAssetsDbVersion(MainE9Fragment.this.getContext().getAssets(), Constant.CONFIG_UPDATA));
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConfigurationE9>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.8
                @Override // io.reactivex.functions.Consumer
                public void accept(ConfigurationE9 configurationE9) throws Exception {
                    ToastUtil.showToast(R.string.update_finish);
                    MainE9Fragment.this.m38x149e3bf(configurationE9);
                }
            }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.9
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MainE9Fragment.this.getConfigurationE9();
                }
            }));
        }
    }

    private void goRegister() {
        addDisposable(Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.12
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                do {
                } while (!MyApplication.getInstance().isSerialInit());
                observableEmitter.onNext(true);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.11
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                MainE9Fragment.this.startActivityForResult(new Intent(MainE9Fragment.this.getContext(), (Class<?>) E9RegisterActivity.class), 1);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readConfigurationE9FromAssets, reason: merged with bridge method [inline-methods] */
    public String m35x32abd4e4() {
        try {
            InputStream open = getContext().getAssets().open("config_e9.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (!ResUpdateUtils.resDownloadFinished()) {
            showDownloadDbDialog();
            return;
        }
        if (view.getTag() != null) {
            int intValue = ((Integer) view.getTag()).intValue();
            switch (intValue) {
                case R.string.automobile /* 2131886161 */:
                    goKeySelect(1, intValue);
                    return;
                case R.string.blank_cut /* 2131886172 */:
                    start(KeyBlankCutTypeSelectFragment.newInstance());
                    break;
                case R.string.calibration /* 2131886197 */:
                case R.string.setup /* 2131886784 */:
                    start(CalibrationFragment.newInstance());
                    return;
                case R.string.chinese_car /* 2131886217 */:
                    goKeySelect(6, intValue);
                    return;
                case R.string.cut_history /* 2131886268 */:
                    start(UserDataFragment.newInstance(0));
                    return;
                case R.string.dimple /* 2131886318 */:
                    goKeySelect(3, intValue);
                    return;
                case R.string.duplicating_key /* 2131886361 */:
                    start(DuplicateKeyNewFragment.newInstance());
                    return;
                case R.string.favorites /* 2131886386 */:
                    start(UserDataFragment.newInstance(1));
                    return;
                case R.string.key_marking /* 2131886469 */:
                    start(KeyMarkingE9Fragment.newInstance());
                    return;
                case R.string.last_key /* 2131886482 */:
                    break;
                case R.string.motorcycle /* 2131886524 */:
                    goKeySelect(2, intValue);
                    return;
                case R.string.my_key_info /* 2131886563 */:
                    start(CustomKeyListFragment.newInstance());
                    return;
                case R.string.search /* 2131886757 */:
                    start(SearchFragment.newInstance());
                    return;
                case R.string.service /* 2131886782 */:
                    start(SupportFragment.newInstance());
                    return;
                case R.string.single_standard /* 2131886803 */:
                    goKeySelect(4, intValue);
                    return;
                case R.string.technical_information /* 2131886832 */:
                    start(TechnicalInfoBrandSelectFragment.newInstance());
                    return;
                case R.string.tubular /* 2131886851 */:
                    goKeySelect(5, intValue);
                    return;
                default:
                    return;
            }
            addDisposable(Observable.fromCallable(new Callable<CutHistoryData>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.14
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public CutHistoryData call() throws Exception {
                    CutHistoryData lastCutHistory = UserDataDaoManager.getInstance(MainE9Fragment.this.getContext()).getLastCutHistory();
                    if (lastCutHistory != null) {
                        return lastCutHistory;
                    }
                    throw new Exception(MainE9Fragment.this.getString(R.string.no_data_was_found));
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MainE9Fragment.this.m40lambda$onItemClick$7$comkkkcute20juifragmentMainE9Fragment((CutHistoryData) obj);
                }
            }, new Consumer<Throwable>() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.13
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    ToastUtil.showToast(th.getMessage());
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onItemClick$7$com-kkkcut-e20j-ui-fragment-MainE9Fragment, reason: not valid java name */
    public /* synthetic */ void m40lambda$onItemClick$7$comkkkcute20juifragmentMainE9Fragment(CutHistoryData cutHistoryData) throws Exception {
        start(KeyOperateFragment.newInstance(new GoOperatBean(cutHistoryData)));
    }

    private void showDownloadDbDialog() {
        new AlertDialog.Builder(getContext()).setTitle(R.string.resup_need_update).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainE9Fragment.this.goDataUpgradeFragment();
            }
        }).show();
    }

    @OnClick({R.id.version_update, R.id.data_update, R.id.language_choice, R.id.about_us, R.id.setting, R.id.help_center, R.id.iv_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.about_us /* 2131361812 */:
                closeDrawer();
                start(AboutFragment.newInstance(this.machineName, SPUtils.getString("series"), this.companyStr));
                return;
            case R.id.data_update /* 2131362087 */:
                closeDrawer();
                goDataUpgradeFragment();
                return;
            case R.id.help_center /* 2131362243 */:
                closeDrawer();
                start(HelpCenterFragment.newInstance());
                return;
            case R.id.iv_service /* 2131362331 */:
                start(SupportFragment.newInstance());
                return;
            case R.id.language_choice /* 2131362373 */:
                closeDrawer();
                start(LanguageSwitchFragment.newInstance(this.languageStr));
                return;
            case R.id.setting /* 2131362764 */:
                closeDrawer();
                start(SettingFragment.newInstance());
                return;
            case R.id.version_update /* 2131363067 */:
                closeDrawer();
                AppUpdateUtil.checkUpdate(getActivity());
                return;
            default:
                return;
        }
    }

    private void closeDrawer() {
        if (this.drawerLayout.isDrawerOpen(5)) {
            this.drawerLayout.closeDrawer(5);
        }
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment, com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            ((FrameActivity) getActivity()).hideLogo();
        } else {
            ((FrameActivity) getActivity()).showLogo();
        }
    }

    private void goKeySelect(int i, int i2) {
        start(BrandSelectFragment.newInstance(i, i2));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d("MainFragment", "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        if (i == 1) {
            getConfigurationE9();
            setSeries();
        }
    }
}
