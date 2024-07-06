package com.kkkcut.e20j.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.Constant;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.adapter.HomeCenterRvAdapter;
import com.kkkcut.e20j.adapter.HomepageExtraFuntionAdapter;
import com.kkkcut.e20j.androidquick.network.RetrofitManager;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.androidquick.tool.GsonHelper;
import com.kkkcut.e20j.androidquick.tool.NetUtil;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.gsonBean.Configuration;
import com.kkkcut.e20j.dao.ErrorCodeDaoManager;
import com.kkkcut.e20j.dao.KeyInfoDaoManager;
import com.kkkcut.e20j.net.Apis;
import com.kkkcut.e20j.net.TUitls;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.ui.activity.RegisterActivity;
import com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment;
import com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment;
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment;
import com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment;
import com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment;
import com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment;
import com.kkkcut.e20j.ui.fragment.setting.SettingFragment;
import com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.AppUpdateUtil;
import com.kkkcut.e20j.utils.AssetVersionUtil;
import com.kkkcut.e20j.utils.DesUtil;
import com.kkkcut.e20j.utils.ResUpdateUtils;
import com.kkkcut.e20j.utils.lan.LocalManageUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class MainFragment extends BaseBackFragment implements BaseQuickAdapter.OnItemClickListener {
    private static final int REGISTER = 1;
    public static final String TAG = "MainFragment";

    TextView aboutUs;
    private String companyStr;
    Configuration configuration1;

    View devideLanguage;

    DrawerLayout drawerLayout;

    FrameLayout flAdvSearch;

    TextView helpCenter;

    TextView languageChoice;
    private String languageStr;

    LinearLayout llBarCode;
    private String machineName;

    RecyclerView rvCarkay;

    RecyclerView rvExtraFuntion;

    RecyclerView rvHousekey;

    TextView setting;

    TextView tvDbVersion;

    TextView tvSearch;

    TextView tvSeries;

    TextView tvSoftVersion;

    TextView tvTitleCarkey;

    TextView tvTitleHousekey;

    TextView versionUpdate;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main;
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        String str;
        setUserVisibleHint(true);
        initView();
        checkConfigUpdate();
        try {
            str = DesUtil.decrypt(DesUtil.DATABASE, "8357C3A71BB080D8");
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        Log.d("MainFragment", str);
    }

    private void checkConfigUpdate() {
        addDisposable((Disposable) Observable.fromCallable(() -> {
                if (AppUtil.isApkInDebug(MainFragment.this.getContext()) || !NetUtil.isNetworkConnected(MainFragment.this.getContext())) {
                    return false;
                }
                if (!new File(Constant.CONFIG_PATH).exists()) {
                    Log.i("MainFragment", "配置文件不存在: ");
                    return true;
                }
                return Boolean.valueOf(AssetVersionUtil.getAssetsDbVersion(MainFragment.this.getContext().getAssets(), Constant.CONFIG_UPDATA) > SPUtils.getInt(SpKeys.CONFIG_UPDATE, 0));

        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Boolean>() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    MainFragment.this.updateConfig();
                } else {
                    MainFragment.this.getConfiguration();
                }
            }
        }));
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.rvExtraFuntion.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(0);
        this.rvCarkay.setLayoutManager(linearLayoutManager2);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext());
        linearLayoutManager3.setOrientation(0);
        this.rvHousekey.setLayoutManager(linearLayoutManager3);
        ((FrameActivity) getActivity()).showLogo();
        setMachineInfo();
        if (MachineInfo.isE20Neutral(getContext())) {
            this.helpCenter.setVisibility(8);
        }
        if (MachineInfo.isE20Us(getContext())) {
            this.llBarCode.setVisibility(SPUtils.getBoolean("bar_code", true) ? 0 : 8);
        }
    }

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
            case R.id.fl_adv_search /* 2131362196 */:
                if (!ResUpdateUtils.resDownloadFinished()) {
                    showDownloadDbDialog();
                    return;
                } else {
                    start(AdvanceSearchFragment.newInstance());
                    return;
                }
            case R.id.fl_search /* 2131362208 */:
                if (!ResUpdateUtils.resDownloadFinished()) {
                    showDownloadDbDialog();
                    return;
                } else {
                    start(SearchFragment.newInstance());
                    return;
                }
            case R.id.help_center /* 2131362243 */:
                closeDrawer();
                start(HelpCenterFragment.newInstance());
                return;
            case R.id.language_choice /* 2131362373 */:
                closeDrawer();
                start(LanguageSwitchFragment.newInstance(this.languageStr));
                return;
            case R.id.ll_bar_code /* 2131362397 */:
                if (!ResUpdateUtils.resDownloadFinished()) {
                    showDownloadDbDialog();
                    return;
                } else {
                    start(SearchFragment.newInstance(SearchFragment.SearchType.BAR_CODE));
                    return;
                }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void goDataUpgradeFragment() {
        start(DataUpdateFragment.newInstance());
    }

    private void closeDrawer() {
        if (this.drawerLayout.isDrawerOpen(5)) {
            this.drawerLayout.closeDrawer(5);
        }
    }

    public void getConfiguration() {
        addDisposable(Observable.fromCallable(new Callable() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MainFragment.this.m41xc6c90224();
            }
        }).doOnNext(new Consumer<Configuration>() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Configuration configuration) throws Exception {
                MainFragment.this.initDb();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MainFragment.this.m42xce2e3743((Configuration) obj);
            }
        }, new Consumer() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                MainFragment.this.m43xd5936c62((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getConfiguration$2$com-kkkcut-e20j-ui-fragment-MainFragment, reason: not valid java name */
    public /* synthetic */ void m43xd5936c62(Throwable th) throws Exception {
        Log.i("MainFragment", "getConfiguration: " + th.getMessage());
        goRegister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDb() {
        KeyInfoDaoManager.getInstance();
        ErrorCodeDaoManager.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleConfig, reason: merged with bridge method [inline-methods] */
    public void m42xce2e3743(Configuration configuration) {
        Resources resources;
        int identifier;
        MachineInfo.setMachineTypeAndRegion(MyApplication.getInstance(), configuration.getId());
        this.configuration1 = configuration;
        this.languageStr = configuration.getLanguage();
        this.companyStr = configuration.getCompany();
        this.machineName = configuration.getTitle_layout().getModel().getName();
        ((FrameActivity) getActivity()).setMachineName(this.machineName);
        SPUtils.put(SpKeys.MACHINE_ID, configuration.getId());
        if (MachineInfo.isChineseMachine()) {
            this.devideLanguage.setVisibility(8);
            this.languageChoice.setVisibility(8);
            LocalManageUtil.saveSelectLanguage(getContext(), "zh");
        }
        FrameActivity frameActivity = (FrameActivity) getActivity();
        frameActivity.checkHaveNewMessage();
        String img = configuration.getTitle_layout().getLogo().getImg();
        if (!TextUtils.isEmpty(img) && !MachineInfo.isE20Neutral(getContext()) && (resources = getResources()) != null && (identifier = resources.getIdentifier(img, "drawable", getContext().getPackageName())) != 0) {
            frameActivity.setLogo(identifier);
        }
        String welcome_logo = configuration.getWelcome_logo();
        if (!TextUtils.isEmpty(welcome_logo)) {
            SPUtils.put("welcome", welcome_logo);
        } else {
            SPUtils.put("welcome", "welcome_kukai");
        }
        HomepageExtraFuntionAdapter homepageExtraFuntionAdapter = new HomepageExtraFuntionAdapter(configuration.getBottom_layout());
        homepageExtraFuntionAdapter.setOnItemClickListener(this);
        this.rvExtraFuntion.setAdapter(homepageExtraFuntionAdapter);
        List<Configuration.CenterLayoutBean> center_layout = configuration.getCenter_layout();
        List<Configuration.CenterLayoutBean.KeytypeBean> keytype = center_layout.get(0).getKeytype();
        HomeCenterRvAdapter homeCenterRvAdapter = new HomeCenterRvAdapter(keytype);
        Log.i("MainFragment", "KeyInfo.size: " + keytype.size());
        if (!MachineInfo.isChineseMachine()) {
            if (!SPUtils.getBoolean(SpKeys.SHOW_CHINESE_CAR, MachineInfo.isE20Standard(getContext()))) {
                int i = 0;
                while (true) {
                    if (i >= keytype.size()) {
                        break;
                    }
                    if ("Domestic car".equals(keytype.get(i).getName())) {
                        homeCenterRvAdapter.remove(i);
                        break;
                    }
                    i++;
                }
            } else if (keytype.size() == 2) {
                Configuration.CenterLayoutBean.KeytypeBean keytypeBean = new Configuration.CenterLayoutBean.KeytypeBean();
                keytypeBean.setName("Domestic car");
                keytypeBean.setImg("homepage_domestic_car");
                keytype.add(keytypeBean);
            }
        }
        homeCenterRvAdapter.setOnItemClickListener(this);
        this.rvCarkay.setAdapter(homeCenterRvAdapter);
        if (center_layout.size() > 1) {
            this.tvTitleHousekey.setVisibility(0);
            this.rvHousekey.setVisibility(0);
            HomeCenterRvAdapter homeCenterRvAdapter2 = new HomeCenterRvAdapter(center_layout.get(1).getKeytype());
            homeCenterRvAdapter2.setOnItemClickListener(this);
            this.rvHousekey.setAdapter(homeCenterRvAdapter2);
        }
        if (MachineInfo.isChineseMachine()) {
            this.flAdvSearch.setVisibility(8);
        } else {
            this.flAdvSearch.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkResUpdate(final Context context, String str) {
        addDisposable(ResUpdateUtils.checkResUpdate(getContext(), str,  updateBean -> {
            if (updateBean.isUpdate()) {
                new AlertDialog.Builder(context).setIcon(R.drawable.upgrade).setTitle(R.string.ResUpdate).setMessage(updateBean.getUpdateLog()).setCancelable(false).setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                        MainFragment.this.goDataUpgradeFragment();

                }).setNegativeButton(R.string.cancel, null).show();
            }
        }, th -> ToastUtil.showToast(th.getMessage())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readConfigurationFromLocal, reason: merged with bridge method [inline-methods] */
    public Configuration m41xc6c90224() {
        if (AppUtil.isApkInDebug(getContext())) {
            return GsonHelper.fromJson(readConfigurationFromAssets(), Configuration.class);
        }
        return GsonHelper.fromJson(FileUtil.readIoToString(Constant.CONFIG_PATH), Configuration.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConfig() {
        String string = SPUtils.getString(SpKeys.CONFIGURATION_FILE);
        Log.i("MainFragment", "updateConfig: " + string);
        if (TextUtils.isEmpty(string)) {
            goRegister();
        } else {
            addDisposable(RetrofitManager.getInstance().createApi(Apis.class).getConfig(TUitls.getconfig(string)).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).doOnNext(configuration -> {
                FileUtil.readIoStringToFile(GsonHelper.toJsonString(configuration), Constant.CONFIG_PATH);
                MainFragment.this.initDb();
                SPUtils.put(SpKeys.CONFIG_UPDATE, AssetVersionUtil.getAssetsDbVersion(MainFragment.this.getContext().getAssets(), Constant.CONFIG_UPDATA));
            }).observeOn(AndroidSchedulers.mainThread()).subscribe( configuration -> {
                ToastUtil.showToast(R.string.update_finish);
                MainFragment.this.m42xce2e3743(configuration);
            }, th -> MainFragment.this.getConfiguration()));
        }
    }

    private void goRegister() {
        addDisposable(Observable.create( observableEmitter -> {
            do {
            } while (!MyApplication.getInstance().isSerialInit());
            observableEmitter.onNext(true);
            observableEmitter.onComplete();
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe( bool -> {
            MainFragment.this.startActivityForResult(new Intent(MainFragment.this.getContext(), RegisterActivity.class), 1);
        }));
    }

    private String readConfigurationFromAssets() {
        String str;
        try {
            if (MachineInfo.isE20Neutral(getContext())) {
                str = "config_neutral.json";
            } else {
                str = MachineInfo.isE20Us(getContext()) ? "config_us.json" : "config.json";
            }
            InputStream open = getContext().getAssets().open(str);
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
                    return;
                case R.string.calibration /* 2131886197 */:
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
                    if (MachineInfo.isE20Us(getContext())) {
                        start(FavoriteFragment.newInstance());
                        return;
                    } else {
                        start(UserDataFragment.newInstance(1));
                        return;
                    }
                case R.string.key_marking /* 2131886469 */:
                    start(KeyMarkingFragment.newInstance());
                    return;
                case R.string.motorcycle /* 2131886524 */:
                    goKeySelect(2, intValue);
                    return;
                case R.string.my_key_info /* 2131886563 */:
                    start(CustomKeyListFragment.newInstance());
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
        }
    }

    private void showDownloadDbDialog() {
        new AlertDialog.Builder(getContext()).setTitle(R.string.resup_need_update).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainFragment.this.goDataUpgradeFragment();
            }
        }).show();
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

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        Configuration configuration;
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 3) {
            Configuration configuration2 = this.configuration1;
            if (configuration2 != null) {
                m42xce2e3743(configuration2);
                return;
            }
            return;
        }
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
            addDisposable(Observable.create( observableEmitter -> {
                    while (true) {
                        String string = SPUtils.getString(SpKeys.MACHINE_ID);
                        if (!TextUtils.isEmpty(string)) {
                            observableEmitter.onNext(string);
                            observableEmitter.onComplete();
                            return;
                        }
                        Thread.sleep(500L);
                    }

            }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe( str ->{
                    MainFragment mainFragment = MainFragment.this;
                    mainFragment.checkResUpdate(mainFragment.getContext(), (String) str);

            }));
            return;
        }
        if (eventCode == 40) {
            startActivityForResult(new Intent(getContext(), RegisterActivity.class), 1);
            return;
        }
        if (eventCode == 54) {
            if (((Boolean) eventCenter.getData()).booleanValue()) {
                this.llBarCode.setVisibility(0);
                return;
            } else {
                this.llBarCode.setVisibility(8);
                return;
            }
        }
        if (eventCode == 44) {
            updateConfig();
        } else if (eventCode == 45 && (configuration = this.configuration1) != null) {
            m42xce2e3743(configuration);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.d("MainFragment", "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]");
        if (i == 1) {
            getConfiguration();
            setMachineInfo();
        }
    }

    private void setMachineInfo() {
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

    @Override // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.configuration1 = null;
    }
}
