package com.kkkcut.e20j.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.Constant
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.adapter.HomeCenterRvAdapter
import com.kkkcut.e20j.adapter.HomepageExtraFuntionAdapter
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.GsonHelper
import com.kkkcut.e20j.androidquick.tool.NetUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.gsonBean.Configuration
import com.kkkcut.e20j.bean.gsonBean.Configuration.CenterLayoutBean.KeytypeBean
import com.kkkcut.e20j.dao.ErrorCodeDaoManager
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.activity.FrameActivity
import com.kkkcut.e20j.ui.activity.RegisterActivity
import com.kkkcut.e20j.ui.fragment.SearchFragment.SearchType
import com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment
import com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment
import com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment
import com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment
import com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment
import com.kkkcut.e20j.ui.fragment.setting.SettingFragment
import com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.AppUpdateUtil
import com.kkkcut.e20j.utils.AssetVersionUtil
import com.kkkcut.e20j.utils.DesUtil
import com.kkkcut.e20j.utils.ResUpdateUtils
import com.kkkcut.e20j.utils.lan.LocalManageUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.io.InputStream

/* loaded from: classes.dex */
class MainFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    var aboutUs: TextView? = null
    private var companyStr: String? = null
    var configuration1: Configuration? = null

    var devideLanguage: View? = null

    var drawerLayout: DrawerLayout? = null

    var flAdvSearch: FrameLayout? = null

    var helpCenter: TextView? = null

    var languageChoice: TextView? = null
    private var languageStr: String? = null

    var llBarCode: LinearLayout? = null
    private var machineName: String? = null

    var rvCarkay: RecyclerView? = null

    var rvExtraFuntion: RecyclerView? = null

    var rvHousekey: RecyclerView? = null

    var setting: TextView? = null

    var tvDbVersion: TextView? = null

    var tvSearch: TextView? = null

    var tvSeries: TextView? = null

    var tvSoftVersion: TextView? = null

    var tvTitleCarkey: TextView? = null

    var tvTitleHousekey: TextView? = null

    var versionUpdate: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_main
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var str: String?
        setUserVisibleHint(true)
        initView()
        checkConfigUpdate()
        try {
            str = DesUtil.decrypt(DesUtil.DATABASE, "8357C3A71BB080D8")
        } catch (e: Exception) {
            e.printStackTrace()
            str = ""
        }
        Log.d("MainFragment", (str)!!)
    }

    private fun checkConfigUpdate() {
        addDisposable(Observable.fromCallable<Boolean> {
            if (AppUtil.isApkInDebug(this@MainFragment.context) || !NetUtil.isNetworkConnected(
                    this@MainFragment.context
                )
            ) {
                return@fromCallable false
            }
            if (!File(Constant.CONFIG_PATH).exists()) {
                Log.i("MainFragment", "配置文件不存在: ")
                return@fromCallable true
            }
            AssetVersionUtil.getAssetsDbVersion(
                context!!.assets,
                Constant.CONFIG_UPDATA
            ) > SPUtils.getInt(SpKeys.CONFIG_UPDATE, 0)
        }.subscribeOn(Schedulers.io()).subscribe(
        { bool ->
            if (bool) {
                this@MainFragment.updateConfig()
            } else {
                this@MainFragment.configuration
            }
        }, { dismissLoadingDialog() }))
    }

    private fun initView() {
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(1)
        rvExtraFuntion!!.setLayoutManager(linearLayoutManager)
        val linearLayoutManager2: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager2.setOrientation(0)
        rvCarkay!!.setLayoutManager(linearLayoutManager2)
        val linearLayoutManager3: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager3.setOrientation(0)
        rvHousekey!!.setLayoutManager(linearLayoutManager3)
        (getActivity() as FrameActivity?)!!.showLogo()
        setMachineInfo()
        if (MachineInfo.isE20Neutral(getContext())) {
            helpCenter!!.setVisibility(8)
        }
        if (MachineInfo.isE20Us(getContext())) {
            llBarCode!!.setVisibility(if (SPUtils.getBoolean("bar_code", true)) 0 else 8)
        }
    }

    fun onViewClicked(view: View) {
        when (view.getId()) {
            R.id.about_us -> {
                closeDrawer()
                start(
                    AboutFragment.Companion.newInstance(
                        this.machineName,
                        SPUtils.getString("series"),
                        this.companyStr
                    )
                )
                return
            }

            R.id.data_update -> {
                closeDrawer()
                goDataUpgradeFragment()
                return
            }

            R.id.fl_adv_search -> if (!ResUpdateUtils.resDownloadFinished()) {
                showDownloadDbDialog()
                return
            } else {
                start(AdvanceSearchFragment.newInstance())
                return
            }

            R.id.fl_search -> if (!ResUpdateUtils.resDownloadFinished()) {
                showDownloadDbDialog()
                return
            } else {
                start(SearchFragment.Companion.newInstance())
                return
            }

            R.id.help_center -> {
                closeDrawer()
                start(HelpCenterFragment.Companion.newInstance())
                return
            }

            R.id.language_choice -> {
                closeDrawer()
                start(LanguageSwitchFragment.Companion.newInstance(this.languageStr))
                return
            }

            R.id.ll_bar_code -> if (!ResUpdateUtils.resDownloadFinished()) {
                showDownloadDbDialog()
                return
            } else {
                start(SearchFragment.Companion.newInstance(SearchType.BAR_CODE))
                return
            }

            R.id.setting -> {
                closeDrawer()
                start(SettingFragment.newInstance())
                return
            }

            R.id.version_update -> {
                closeDrawer()
                AppUpdateUtil.checkUpdate(getActivity())
                return
            }

            else -> return
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun goDataUpgradeFragment() {
        start(DataUpdateFragment.Companion.newInstance())
    }

    private fun closeDrawer() {
        if (drawerLayout!!.isDrawerOpen(5)) {
            drawerLayout!!.closeDrawer(5)
        }
    }

    val configuration: Unit
        get() {
            addDisposable(
                Observable.fromCallable<Configuration> {
                    this@MainFragment.readConfigurationFromLocal()
                }.doOnNext { this@MainFragment.initDb() }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ obj ->
                        this@MainFragment.handleConfig(obj)
                    },
                    { obj ->
                        this@MainFragment.m43xd5936c62(obj)
                    }, { dismissLoadingDialog() })
            )
        }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$getConfiguration$2$com-kkkcut-e20j-ui-fragment-MainFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun m43xd5936c62(th: Throwable?) {
        Log.i("MainFragment", "getConfiguration: " + th!!.message)
        goRegister()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun initDb() {
        KeyInfoDaoManager.getInstance()
        ErrorCodeDaoManager.getInstance()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: handleConfig, reason: merged with bridge method [inline-methods] */
    fun handleConfig(configuration: Configuration?) {
        var resources: Resources? = null
        var identifier = 0
        MachineInfo.setMachineTypeAndRegion(MyApplication.getInstance(), configuration!!.id)
        this.configuration1 = configuration
        this.languageStr = configuration.language
        this.companyStr = configuration.company
        this.machineName = configuration.title_layout.model.name
        (activity as FrameActivity?)!!.setMachineName(this.machineName)
        SPUtils.put(SpKeys.MACHINE_ID, configuration.id)
        if (MachineInfo.isChineseMachine()) {
            devideLanguage!!.visibility = 8
            languageChoice!!.visibility = 8
            LocalManageUtil.saveSelectLanguage(context, "zh")
        }
        val frameActivity: FrameActivity? = activity as FrameActivity?
        frameActivity!!.checkHaveNewMessage()
        val img: String = configuration.title_layout.logo.img
        if (!TextUtils.isEmpty(img) && !MachineInfo.isE20Neutral(context) && ((getResources().also {
                resources = it
            }) != null) && ((resources!!.getIdentifier(
                img,
                "drawable",
                context!!.packageName
            ).also { identifier = it }) != 0)
        ) {
            frameActivity.setLogo(identifier)
        }
        val welcomeLogo: String = configuration.welcome_logo
        if (!TextUtils.isEmpty(welcomeLogo)) {
            SPUtils.put("welcome", welcomeLogo)
        } else {
            SPUtils.put("welcome", "welcome_kukai")
        }
        val homepageExtraFuntionAdapter: HomepageExtraFuntionAdapter = HomepageExtraFuntionAdapter(
            configuration.getBottom_layout()
        )
        homepageExtraFuntionAdapter.setOnItemClickListener(this)
        rvExtraFuntion!!.setAdapter(homepageExtraFuntionAdapter)
        val center_layout: List<Configuration.CenterLayoutBean> = configuration.getCenter_layout()
        val keytype: MutableList<KeytypeBean> = center_layout.get(0).getKeytype()
        val homeCenterRvAdapter: HomeCenterRvAdapter = HomeCenterRvAdapter(keytype)
        Log.i("MainFragment", "KeyInfo.size: " + keytype.size)
        if (!MachineInfo.isChineseMachine()) {
            if (!SPUtils.getBoolean(
                    SpKeys.SHOW_CHINESE_CAR,
                    MachineInfo.isE20Standard(getContext())
                )
            ) {
                var i: Int = 0
                while (true) {
                    if (i >= keytype.size) {
                        break
                    }
                    if (("Domestic car" == keytype.get(i).getName())) {
                        homeCenterRvAdapter.remove(i)
                        break
                    }
                    i++
                }
            } else if (keytype.size == 2) {
                val keytypeBean: KeytypeBean = KeytypeBean()
                keytypeBean.setName("Domestic car")
                keytypeBean.setImg("homepage_domestic_car")
                keytype.add(keytypeBean)
            }
        }
        homeCenterRvAdapter.setOnItemClickListener(this)
        rvCarkay!!.setAdapter(homeCenterRvAdapter)
        if (center_layout.size > 1) {
            tvTitleHousekey!!.setVisibility(0)
            rvHousekey!!.setVisibility(0)
            val homeCenterRvAdapter2: HomeCenterRvAdapter =
                HomeCenterRvAdapter(center_layout.get(1).getKeytype())
            homeCenterRvAdapter2.setOnItemClickListener(this)
            rvHousekey!!.setAdapter(homeCenterRvAdapter2)
        }
        if (MachineInfo.isChineseMachine()) {
            flAdvSearch!!.setVisibility(8)
        } else {
            flAdvSearch!!.setVisibility(0)
        }
    }


    /* JADX INFO: Access modifiers changed from: private */
    fun checkResUpdate(context: Context?, str: String?) {
        addDisposable(
            ResUpdateUtils.checkResUpdate(getContext(), str,
                // io.reactivex.functions.Consumer
                { updateBean ->

                    // from class: com.kkkcut.e20j.ui.fragment.MainFragment.4
                    if (updateBean.isUpdate) {
                        AlertDialog.Builder(context).setIcon(R.drawable.upgrade)
                            .setTitle(R.string.ResUpdate).setMessage(updateBean.updateLog)
                            .setCancelable(false).setPositiveButton(
                                R.string.f1269ok
                            ) { dialogInterface, i ->
                                // from class: com.kkkcut.e20j.ui.fragment.MainFragment.4.1
                                // android.content.DialogInterface.OnClickListener
                                this@MainFragment.goDataUpgradeFragment()
                            }.setNegativeButton(
                                R.string.cancel,
                                null as DialogInterface.OnClickListener?
                            ).show()
                    }
                }, // io.reactivex.functions.Consumer
                { th ->
                    // from class: com.kkkcut.e20j.ui.fragment.MainFragment.5
                    ToastUtil.showToast(th.message)
                })
        )
    }

    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: readConfigurationFromLocal, reason: merged with bridge method [inline-methods] */
    fun readConfigurationFromLocal(): Configuration {
        if (AppUtil.isApkInDebug(getContext())) {
            return GsonHelper.fromJson(readConfigurationFromAssets(), Configuration::class.java)
        }
        return GsonHelper.fromJson(
            FileUtil.readIoToString(Constant.CONFIG_PATH),
            Configuration::class.java
        )
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun updateConfig() {
        val string: String = SPUtils.getString(SpKeys.CONFIGURATION_FILE)
        Log.i("MainFragment", "updateConfig: " + string)
        if (TextUtils.isEmpty(string)) {
            goRegister()
        } else {
            addDisposable(
                RetrofitManager.getInstance().createApi(Apis::class.java)
                    .getConfig(TUitls.getconfig(string)).subscribeOn(
                        Schedulers.io()
                    ).observeOn(Schedulers.newThread()).doOnNext { configuration: Configuration? ->
                        FileUtil.readIoStringToFile(
                            GsonHelper.toJsonString(configuration),
                            Constant.CONFIG_PATH
                        )
                        this@MainFragment.initDb()
                        SPUtils.put(
                            SpKeys.CONFIG_UPDATE, AssetVersionUtil.getAssetsDbVersion(
                                getContext()!!
                                    .getAssets(), Constant.CONFIG_UPDATA
                            )
                        )
                    }.observeOn(AndroidSchedulers.mainThread()).subscribe(
                        { configuration: Configuration? ->
                            ToastUtil.showToast(R.string.update_finish)
                            this@MainFragment.handleConfig(configuration)
                        },
                        { th: Throwable? -> this@MainFragment.configuration },
                        { dismissLoadingDialog() })
            )

        }
    }

    private fun goRegister() {
        addDisposable(
            Observable.create{ observableEmitter ->
                do {
                } while (!MyApplication.getInstance().isSerialInit)
                observableEmitter.onNext(true)
                observableEmitter.onComplete()
            }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this@MainFragment.startActivityForResult(
                        Intent(
                            this@MainFragment.context, RegisterActivity::class.java
                        ), 1
                    )
                }, { dismissLoadingDialog() })
        )
    }

    private fun readConfigurationFromAssets(): String? {
        val str: String
        try {
            str = if (MachineInfo.isE20Neutral(context)) {
                "config_neutral.json"
            } else {
                if (MachineInfo.isE20Us(context)) "config_us.json" else "config.json"
            }
            val open: InputStream = context!!.assets.open(str)
            val bArr = ByteArray(open.available())
            open.read(bArr)
            return String(bArr)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>?, view: View, i: Int) {
        if (!ResUpdateUtils.resDownloadFinished()) {
            showDownloadDbDialog()
            return
        }
        if (view.getTag() != null) {
            val intValue: Int = (view.getTag() as Int)
            when (intValue) {
                R.string.automobile -> {
                    goKeySelect(1, intValue)
                    return
                }

                R.string.blank_cut -> {
                    start(KeyBlankCutTypeSelectFragment.newInstance())
                    return
                }

                R.string.calibration -> {
                    start(CalibrationFragment.Companion.newInstance())
                    return
                }

                R.string.chinese_car -> {
                    goKeySelect(6, intValue)
                    return
                }

                R.string.cut_history -> {
                    start(UserDataFragment.Companion.newInstance(0))
                    return
                }

                R.string.dimple -> {
                    goKeySelect(3, intValue)
                    return
                }

                R.string.duplicating_key -> {
                    start(DuplicateKeyNewFragment.newInstance())
                    return
                }

                R.string.favorites -> if (MachineInfo.isE20Us(getContext())) {
                    start(FavoriteFragment.Companion.newInstance())
                    return
                } else {
                    start(UserDataFragment.Companion.newInstance(1))
                    return
                }

                R.string.key_marking -> {
                    start(KeyMarkingFragment.newInstance())
                    return
                }

                R.string.motorcycle -> {
                    goKeySelect(2, intValue)
                    return
                }

                R.string.my_key_info -> {
                    start(CustomKeyListFragment.newInstance())
                    return
                }

                R.string.service -> {
                    start(SupportFragment.Companion.newInstance())
                    return
                }

                R.string.single_standard -> {
                    goKeySelect(4, intValue)
                    return
                }

                R.string.technical_information -> {
                    start(TechnicalInfoBrandSelectFragment.newInstance())
                    return
                }

                R.string.tubular -> {
                    goKeySelect(5, intValue)
                    return
                }

                else -> return
            }
        }
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.drawerLayout = layoutInflater.inflate(R.layout.fragment_main, null) as DrawerLayout?
        this.rvExtraFuntion = drawerLayout!!.findViewById(R.id.rv_extra_funtion)
        this.rvHousekey = drawerLayout!!.findViewById(R.id.rv_housekey)
        this.rvCarkay = drawerLayout!!.findViewById(R.id.rv_carkay)
        this.tvTitleCarkey = drawerLayout!!.findViewById(R.id.about_us)
        this.tvTitleHousekey = drawerLayout!!.findViewById(R.id.tv_title_house_key)
        this.flAdvSearch = drawerLayout!!.findViewById(R.id.fl_adv_search)
        this.tvSearch = drawerLayout!!.findViewById(R.id.tv_search)
        this.versionUpdate = drawerLayout!!.findViewById(R.id.version_update)
        this.llBarCode = drawerLayout!!.findViewById(R.id.ll_bar_code)
        this.aboutUs = drawerLayout!!.findViewById(R.id.about_us)
        this.tvSoftVersion = drawerLayout!!.findViewById(R.id.tv_soft_version)
        this.tvDbVersion = drawerLayout!!.findViewById(R.id.tv_db_version)
        return this.drawerLayout
    }

    private fun showDownloadDbDialog() {
        AlertDialog.Builder(getContext()).setTitle(R.string.resup_need_update)
            .setPositiveButton(R.string.ok, object : DialogInterface.OnClickListener {
                // from class: com.kkkcut.e20j.ui.fragment.MainFragment.11
                // android.content.DialogInterface.OnClickListener
                override fun onClick(dialogInterface: DialogInterface, i: Int) {
                    this@MainFragment.goDataUpgradeFragment()
                }
            }).show()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment, com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    override fun onHiddenChanged(z: Boolean) {
        super.onHiddenChanged(z)
        if (z) {
            (getActivity() as FrameActivity?)!!.hideLogo()
        } else {
            (getActivity() as FrameActivity?)!!.showLogo()
        }
    }

    private fun goKeySelect(i: Int, i2: Int) {
        start(BrandSelectFragment.newInstance(i, i2))
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        var configuration: Configuration?
        val eventCode: Int = eventCenter.getEventCode()
        if (eventCode == 3) {
            val configuration2: Configuration? = this.configuration1
            if (configuration2 != null) {
                handleConfig(configuration2)
                return
            }
            return
        }
        if (eventCode == 10) {
            if (drawerLayout!!.isDrawerOpen(5)) {
                drawerLayout!!.closeDrawer(5)
                return
            } else {
                drawerLayout!!.openDrawer(5)
                return
            }
        }
        if (eventCode == 37) {
            addDisposable(
                Observable.create{ observableEmitter ->
                    while (true) {
                        val string: String = SPUtils.getString(SpKeys.MACHINE_ID)
                        if (!TextUtils.isEmpty(string)) {
                            observableEmitter.onNext(string)
                            observableEmitter.onComplete()
                            return@create
                        }
                        Thread.sleep(500L)
                    }
                }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ str: Any? ->
                        val mainFragment: MainFragment = this@MainFragment
                        mainFragment.checkResUpdate(mainFragment.context, str as String?)
                    }, { dismissLoadingDialog() })
            )
            return
        }
        if (eventCode == 40) {
            startActivityForResult(Intent(context, RegisterActivity::class.java), 1)
            return
        }
        if (eventCode == 54) {
            if ((eventCenter.data as Boolean)) {
                llBarCode!!.visibility = 0
                return
            } else {
                llBarCode!!.visibility = 8
                return
            }
        }
        if (eventCode == 44) {
            updateConfig()
        } else if (eventCode == 45 && (configuration1.also { handleConfig(it) }) != null) {
        }
    }

    // androidx.fragment.app.Fragment
    override fun onActivityResult(i: Int, i2: Int, intent: Intent?) {
        Log.d(
            "MainFragment",
            "onActivityResult() called with: requestCode = [" + i + "], resultCode = [" + i2 + "], data = [" + intent + "]"
        )
        if (i == 1) {
            configuration
            setMachineInfo()
        }
    }

    private fun setMachineInfo() {
        val string: String = SPUtils.getString("series")
        if (!TextUtils.isEmpty(string)) {
            tvSeries!!.setText(getString(R.string.sn_xx) + string)
        }
        tvSoftVersion!!.setText(getString(R.string.version) + AppUtil.getVersionName(getContext()))
        val dbVersion: String = KeyInfoDaoManager.getInstance().getDbVersion()
        if (TextUtils.isEmpty(dbVersion)) {
            return
        }
        tvDbVersion!!.setText(getString(R.string.db_version) + dbVersion)
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroy() {
        super.onDestroy()
        this.configuration1 = null
    }

    companion object {
        private val REGISTER: Int = 1
        val TAG: String = "MainFragment"

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
