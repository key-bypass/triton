package com.kkkcut.e20j.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.Constant
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.adapter.HomeCenterRvE9Adapter
import com.kkkcut.e20j.adapter.HomepageEExtraFunctionAdapter
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.GsonHelper
import com.kkkcut.e20j.androidquick.tool.NetUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.activity.E9RegisterActivity
import com.kkkcut.e20j.ui.activity.FrameActivity
import com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment
import com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingE9Fragment
import com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment
import com.kkkcut.e20j.ui.fragment.setting.SettingFragment
import com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentMainE9Binding
import com.kkkcut.e20j.utils.AppUpdateUtil
import com.kkkcut.e20j.utils.AssetVersionUtil
import com.kkkcut.e20j.utils.ResUpdateUtils
import com.kkkcut.e20j.utils.lan.LocalManageUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.io.InputStream

/* loaded from: classes.dex */
class MainE9Fragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var companyStr: String? = null
    var configuration1: ConfigurationE9? = null

    var binding: FragmentMainE9Binding? = null


    private var languageStr: String? = null
    private var machineName: String? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentMainE9Binding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun initDb() {
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_main_e9
    }

    // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun initViewsAndEvents() {
        setUserVisibleHint(true)
        initView()
        checkConfigUpdate()
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL)
        binding!!.rvBottom.setLayoutManager(linearLayoutManager)
        val gridLayoutManager = GridLayoutManager(getContext(), 2)
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL)
        binding!!.rvCenter.addItemDecoration(
            SpaceItemDecoration(
                AutoUtils.getPercentWidthSize(20),
                AutoUtils.getPercentHeightSize(20),
                AutoUtils.getPercentWidthSize(38),
                AutoUtils.getPercentHeightSize(15)
            )
        )
        binding!!.rvCenter.setLayoutManager(gridLayoutManager)
        (getActivity() as FrameActivity?)!!.showLogo()
        setSeries()
        binding!!.helpCenter.visibility = View.GONE
    }

    /* loaded from: classes.dex */
    inner class SpaceItemDecoration(
        private val left: Int,
        private val top: Int,
        private val right: Int,
        private val bottom: Int
    ) : RecyclerView.ItemDecoration() {
        // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        override fun getItemOffsets(
            rect: Rect,
            view: View,
            recyclerView: RecyclerView,
            state: RecyclerView.State
        ) {
            rect.left = this.left
            rect.top = this.top
            rect.bottom = this.bottom
            rect.right = this.right
        }
    }

    private fun setSeries() {
        val string: String = SPUtils.getString("series")
        if (!TextUtils.isEmpty(string)) {
            binding!!.tvSeries.setText(getString(R.string.sn_xx) + string)
        }
        binding!!.tvSoftVersion.setText(
            getString(R.string.version) + AppUtil.getVersionName(
                getContext()
            )
        )
        val dbVersion: String = KeyInfoDaoManager.getInstance().getDbVersion()
        if (TextUtils.isEmpty(dbVersion)) {
            return
        }
        binding!!.tvDbVersion.setText(getString(R.string.db_version) + dbVersion)
    }

    private fun checkConfigUpdate() {
        addDisposable(Observable.fromCallable<Boolean> {
            if (AppUtil.isApkInDebug(this@MainE9Fragment.context) || !NetUtil.isNetworkConnected(
                    this@MainE9Fragment.context
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
        }.subscribeOn(Schedulers.io()).subscribe({ bool: Boolean ->
            Log.i("MainFragment", "accept: $bool")
            if (bool) {
                this@MainE9Fragment.updateConfig()
            } else {
                this@MainE9Fragment.configurationE9
            }
        }, { dismissLoadingDialog() }))
    }

    val configurationE9: Unit
        get() {
            if (AppUtil.isApkInDebug(context)) {
                addDisposable(
                    Observable.fromCallable<String> {
                        try {
                            val open: InputStream = context!!.assets.open("config_e9.json")
                            val bArr = ByteArray(open.available())
                            open.read(bArr)
                            return@fromCallable String(bArr)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }.toString()
                    }.map { it: String ->
                        GsonHelper.fromJson(it, ConfigurationE9::class.java)
                    }.doOnNext {
                        this@MainE9Fragment.initDb()
                    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { obj: ConfigurationE9 ->
                                this@MainE9Fragment.handleConfig(obj)
                            },
                            { ToastUtil.showToast(R.string.network_unavailable) }, { dismissLoadingDialog() }
                        )
                )
            } else {
                addDisposable(
                    Observable.fromCallable { this@MainE9Fragment.readConfigurationFromLocal() }
                        .subscribeOn(
                            Schedulers.io()
                        ).observeOn(AndroidSchedulers.mainThread()).subscribe(
                         {
                            this@MainE9Fragment.handleConfig(
                                it
                            )
                        },
                        { th -> Log.i("MainFragment", "getConfigurationE9: " + th.message)
                            goRegister() },
                            { dismissLoadingDialog() }
                    )
                )
            }
        }


    /* JADX INFO: Access modifiers changed from: private */ /* renamed from: handleConfig, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    fun handleConfig(configurationE9: ConfigurationE9) {
        var identifier = 0
        this.configuration1 = configurationE9
        this.languageStr = configurationE9.getLanguage()
        this.companyStr = configurationE9.getCompany()
        this.machineName = configurationE9.getTitle_layout().getModel().getName()
        (getActivity() as FrameActivity?)!!.setMachineName(this.machineName)
        val id: String = configurationE9.getId()
        SPUtils.put(SpKeys.MACHINE_ID, id)
        MachineInfo.setMachineTypeAndRegion(MyApplication.getInstance(), id)
        if (MachineInfo.isChineseMachine()) {
            binding!!.viewDevideLanguage.setVisibility(View.GONE)
            binding!!.languageChoice.setVisibility(View.GONE)
            LocalManageUtil.saveSelectLanguage(getContext(), "zh")
            binding!!.ivService.setVisibility(View.VISIBLE)
        }
        val frameActivity: FrameActivity? = getActivity() as FrameActivity?
        frameActivity!!.checkHaveNewMessage()
        val img: String = configurationE9.getTitle_layout().getLogo().getImg()
        if (!TextUtils.isEmpty(img) && !MachineInfo.isE20Neutral(getContext()) && ((getResources().getIdentifier(
                img,
                "drawable",
                getContext()!!.getPackageName()
            ).also({ identifier = it })) != 0)
        ) {
            frameActivity.setLogo(identifier)
        }
        val welcome_logo: String = configurationE9.getWelcome_logo()
        if (!TextUtils.isEmpty(welcome_logo)) {
            SPUtils.put("welcome", welcome_logo)
        } else {
            SPUtils.put("welcome", "welcome_kukai")
        }
        val homepageEExtraFunctionAdapter =
            HomepageEExtraFunctionAdapter(configurationE9.bottom_layout)
        homepageEExtraFunctionAdapter.onItemClickListener = this
        binding!!.rvBottom.setAdapter(homepageEExtraFunctionAdapter)
        val homeCenterRvE9Adapter =
            HomeCenterRvE9Adapter(configurationE9.center_layout)
        homeCenterRvE9Adapter.onItemClickListener = this
        binding!!.rvCenter.setAdapter(homeCenterRvE9Adapter)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun checkResUpdate(context: Context?, str: String?) {
        addDisposable(
            ResUpdateUtils.checkResUpdate(getContext(), str,
                // io.reactivex.functions.Consumer
                { updateBean ->

                    // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.4
                    if (updateBean.isUpdate) {
                        AlertDialog.Builder(context).setIcon(R.drawable.upgrade)
                            .setTitle(R.string.ResUpdate).setMessage(updateBean.updateLog)
                            .setCancelable(false).setPositiveButton(
                                R.string.f1269ok
                            ) { dialogInterface, i ->
                                // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.4.1
                                // android.content.DialogInterface.OnClickListener
                                this@MainE9Fragment.goDataUpgradeFragment()
                            }.setNegativeButton(
                                R.string.cancel,
                                null as DialogInterface.OnClickListener?
                            ).show()
                    }
                }, // io.reactivex.functions.Consumer
                { th ->
                    // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment.5
                    ToastUtil.showToast(th.message)
                })
        )
    }

    fun goDataUpgradeFragment() {
        start(DataUpdateFragment.newInstance())
    }

    fun readConfigurationFromLocal(): ConfigurationE9 {
        return GsonHelper.fromJson(
            FileUtil.readIoToString(Constant.CONFIG_PATH),
            ConfigurationE9::class.java
        )
    }

    override fun onEventComing(eventCenter: EventCenter<*>) {
        val eventCode: Int = eventCenter.eventCode
        if (eventCode == 10) {
            if (binding!!.drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
                binding!!.drawerlayout.closeDrawer(Gravity.RIGHT)
                return
            } else {
                binding!!.drawerlayout.openDrawer(Gravity.RIGHT)
                return
            }
        }
        if (eventCode == 37) {
            addDisposable(
                Observable.create<String> { observableEmitter ->
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
                    .subscribe( { str: Any? ->
                        val mainE9Fragment: MainE9Fragment = this@MainE9Fragment
                        mainE9Fragment.checkResUpdate(
                            mainE9Fragment.context,
                            str as String?
                        )
                    }, { dismissLoadingDialog() })
            )
            return
        }
        if (eventCode == 40) {
            startActivityForResult(Intent(context, E9RegisterActivity::class.java), 1)
            return
        }
        if (eventCode == 44) {
            updateConfig()
        } else if (eventCode == 45 && (configuration1.also { handleConfig(it!!) }) != null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun updateConfig() {
        val string: String = SPUtils.getString(SpKeys.CONFIGURATION_FILE)
        Log.i("MainFragment", "updateConfig: $string")
        if (TextUtils.isEmpty(string)) {
            goRegister()
        } else {
            addDisposable(
                (RetrofitManager.getInstance().createApi(Apis::class.java) as Apis).getConfigE9(
                    TUitls.getconfig(string)
                ).subscribeOn(
                    Schedulers.io()
                ).observeOn(Schedulers.newThread()).doOnNext { configurationE9: ConfigurationE9 ->
                    Log.i("MainFragment", "config--" + configurationE9.database)
                    FileUtil.readIoStringToFile(
                        GsonHelper.toJsonString(configurationE9),
                        Constant.CONFIG_PATH
                    )
                    this@MainE9Fragment.initDb()
                    SPUtils.put(
                        SpKeys.CONFIG_UPDATE, AssetVersionUtil.getAssetsDbVersion(
                            context!!.assets, Constant.CONFIG_UPDATA
                        )
                    )
                }.observeOn(AndroidSchedulers.mainThread()).subscribe(
                    { configurationE9: ConfigurationE9 ->
                        ToastUtil.showToast(R.string.update_finish)
                        this@MainE9Fragment.handleConfig(configurationE9)
                    }, { this@MainE9Fragment.configurationE9 }, { dismissLoadingDialog() }
                )
            )
        }
    }

    private fun goRegister() {
        addDisposable(
            Observable.create<Boolean> {
                do {
                    } while (!MyApplication.getInstance().isSerialInit)
                    it.onNext(true)
                    it.onComplete()

            }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                {
                    this@MainE9Fragment.startActivityForResult(
                        Intent(
                            this@MainE9Fragment.context,
                            E9RegisterActivity::class.java as Class<*>?
                        ), 1
                    )
                }, { dismissLoadingDialog() })
        )
    }

    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>?, view: View, i: Int) {
        if (!ResUpdateUtils.resDownloadFinished()) {
            showDownloadDbDialog()
            return
        }
        if (view.tag != null) {
            when (val intValue: Int = (view.tag as Int)) {
                R.string.automobile -> {
                    goKeySelect(1, intValue)
                    return
                }

                R.string.blank_cut -> start(com.kkkcut.e20j.ui.fragment.blankcut.KeyBlankCutTypeSelectFragment.newInstance())
                R.string.calibration, R.string.setup -> {
                    start(CalibrationFragment.newInstance())
                    return
                }

                R.string.chinese_car -> {
                    goKeySelect(6, intValue)
                    return
                }

                R.string.cut_history -> {
                    start(UserDataFragment.newInstance(0))
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

                R.string.favorites -> {
                    start(UserDataFragment.newInstance(1))
                    return
                }

                R.string.key_marking -> {
                    start(KeyMarkingE9Fragment.newInstance())
                    return
                }

                R.string.last_key -> {}
                R.string.motorcycle -> {
                    goKeySelect(2, intValue)
                    return
                }

                R.string.my_key_info -> {
                    start(CustomKeyListFragment.newInstance())
                    return
                }

                R.string.search -> {
                    start(SearchFragment.newInstance())
                    return
                }

                R.string.service -> {
                    start(SupportFragment.newInstance())
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
            addDisposable(
                Observable.fromCallable{
                        val lastCutHistory: CutHistoryData? =
                            UserDataDaoManager.getInstance(this@MainE9Fragment.context)
                                .getLastCutHistory()
                        if (lastCutHistory != null) {
                            return@fromCallable lastCutHistory
                        }
                        throw Exception(this@MainE9Fragment.getString(R.string.no_data_was_found))
                    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        start(KeyOperateFragment.newInstance(GoOperatBean(((it as CollectionData?)!!))))
                    }, { th ->
                        ToastUtil.showToast(th.message)
                    }, { dismissLoadingDialog() })
            )
        }
    }

    private fun showDownloadDbDialog() {
        AlertDialog.Builder(getContext()).setTitle(R.string.resup_need_update).setPositiveButton(
            R.string.f1269ok
        ) { _: DialogInterface?, i: Int ->
            this@MainE9Fragment.goDataUpgradeFragment()
        }.show()
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.about_us -> {
                closeDrawer()
                start(
                    AboutFragment.newInstance(
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

            R.id.help_center -> {
                closeDrawer()
                start(HelpCenterFragment.newInstance())
                return
            }

            R.id.iv_service -> {
                start(SupportFragment.newInstance())
                return
            }

            R.id.language_choice -> {
                closeDrawer()
                start(LanguageSwitchFragment.newInstance(this.languageStr))
                return
            }

            R.id.setting -> {
                closeDrawer()
                start(SettingFragment.newInstance())
                return
            }

            R.id.version_update -> {
                closeDrawer()
                AppUpdateUtil.checkUpdate(activity)
                return
            }

            else -> return
        }
    }

    private fun closeDrawer() {
        if (binding!!.drawerlayout.isDrawerOpen(Gravity.RIGHT)) {
            binding!!.drawerlayout.closeDrawer(Gravity.RIGHT)
        }
    }

    // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment, com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    override fun onHiddenChanged(z: Boolean) {
        super.onHiddenChanged(z)
        if (z) {
            (activity as FrameActivity?)!!.hideLogo()
        } else {
            (activity as FrameActivity?)!!.showLogo()
        }
    }

    private fun goKeySelect(i: Int, i2: Int) {
        start(BrandSelectFragment.newInstance(i, i2))
    }

    // androidx.fragment.app.Fragment
    override fun onActivityResult(i: Int, i2: Int, intent: Intent?) {
        Log.d(
            "MainFragment",
            "onActivityResult() called with: requestCode = [$i], resultCode = [$i2], data = [$intent]"
        )
        if (i == 1) {
            configurationE9
            setSeries()
        }
    }

    companion object {
        private val REGISTER: Int = 1
        val TAG: String = "MainFragment"
        fun newInstance(): MainE9Fragment {
            return MainE9Fragment()
        }
    }
}