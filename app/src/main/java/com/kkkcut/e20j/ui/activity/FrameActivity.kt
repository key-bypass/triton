package com.kkkcut.e20j.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.cutting.machine.Command
import com.cutting.machine.DialogBtnCallBack
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.communication.OperationManager
import com.kkkcut.e20j.Constant
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.LogUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.StringUtil
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.androidquick.ui.receiver.NetStateReceiver
import com.kkkcut.e20j.bean.gsonBean.CertificationRes
import com.kkkcut.e20j.driver.communication.OnSerialMessageComingListener
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.CertificationDialog
import com.kkkcut.e20j.ui.dialog.ErrorDialog
import com.kkkcut.e20j.ui.dialog.ForbidDialog
import com.kkkcut.e20j.ui.dialog.LoadingDialog
import com.kkkcut.e20j.ui.dialog.OperatorRemindDialog
import com.kkkcut.e20j.ui.fragment.CalibrationFragment
import com.kkkcut.e20j.ui.fragment.MainE9Fragment
import com.kkkcut.e20j.ui.fragment.MainFragment
import com.kkkcut.e20j.ui.fragment.MessageFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityFrameBinding
import com.kkkcut.e20j.utils.AssetVersionUtil
import com.kkkcut.e20j.utils.GetUUID
import com.kkkcut.e20j.utils.ThemeUtils
import com.kkkcut.e20j.utils.ZipUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import me.yokeyword.fragmentation.ISupportFragment
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class FrameActivity : BaseCustomKeyBoardActivity(), OnSerialMessageComingListener {
    private var certificationDialog: CertificationDialog? = null
    private var errorDialog: ErrorDialog? = null

    private var loadingDialog: LoadingDialog? = null

    private var binding: ActivityFrameBinding? = null

    private var DOUBLE_CLICK_TIME: Long = 0
    var mHits: LongArray = LongArray(10)

    // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_frame
    }

    // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun isLoadDefaultTitleBar(): Boolean {
        return false
    }

    // com.kkkcut.e20j.driver.communication.OnSerialMessageComingListener
    override fun onSerialMessageComing(str: String, operateType: OperateType) {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameBinding.inflate(getLayoutInflater())
        val view: View = binding!!.getRoot()
        setContentView(view)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun initViewsAndEvents() {
        if (MachineInfo.isE9Standard(this)) {
            loadRootFragment(R.id.content_frame, MainE9Fragment.newInstance())
        } else {
            loadRootFragment(R.id.content_frame, MainFragment.newInstance())
        }
        NetStateReceiver.registerNetworkStateReceiver(this.mContext)
        checkCodeDb()
        if (!AppUtil.isApkInDebug(this)) {
            showLoadingDialog(getString(R.string.waitting))
        }
        UsbSerialManager.getInstance().init(this)
        if (SPUtils.getInt(SpKeys.CERTIFICATION_STATUS, 0) != 0) {
            showCertificationDialog(
                getString(R.string.please_connect_network_for_authentication_device_has_been_locked),
                false,
                object : CertificationDialog.DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.activity.FrameActivity.1
                    // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        if (z) {
                            this@FrameActivity.startCertificationFromNet()
                        }
                    }
                })
        } else {
            val i: Int = SPUtils.getInt(SpKeys.TOTAL_CUT_NUMBER, 200)
            val i2: Int = SPUtils.getInt(SpKeys.CUT_NUMBER)
            if (i2 < i * 0.3 && i2 > 0) {
                showCertificationDialog(
                    getString(R.string.please_connect_to_the_network_for_authentication_is_about_to_be_locked),
                    true,
                    object : CertificationDialog.DialogBtnCallBack {
                        // from class: com.kkkcut.e20j.ui.activity.FrameActivity.2
                        // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                        override fun onDialogButClick(z: Boolean) {
                            if (z) {
                                this@FrameActivity.startCertificationFromNet()
                            }
                        }
                    })
            }
        }
        if (SPUtils.getBoolean(SpKeys.TABETS_OPERATION_REMIND, false) || MachineInfo.isE9Standard(
                this
            )
        ) {
            return
        }
        showOperatorRemind(0)
    }

    private fun showOperatorRemind(i: Int) {
        val operatorRemindDialog = OperatorRemindDialog(this)
        operatorRemindDialog.show()
        operatorRemindDialog.setRemindText(getString(R.string.caution))
        operatorRemindDialog.setType(i)
        operatorRemindDialog.window!!
            .setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun checkHaveNewMessage() {
        binding!!.titleLayout.ivMessage.setImageResource(
            ThemeUtils.getResId(
                this, R.attr.icon_message
            )
        )
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCertificationFromNet() {
        val string: String = SPUtils.getString("series")
        if (TextUtils.isEmpty(string)) {
            return
        }
        addDisposable((RetrofitManager.getInstance()
            .createApi(Apis::class.java) as Apis).certification(
            TUitls.certification(
                GetUUID.getUUID(),
                string,
                "XX",
                "0.0.0.0"
            )
        ).subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Consumer<CertificationRes> {
            // from class: com.kkkcut.e20j.ui.activity.FrameActivity.3
            @Throws(Exception::class)  // io.reactivex.functions.Consumer
            override fun accept(certificationRes: CertificationRes) {
                val str: String
                Log.i(TAG, "accept: " + certificationRes.getMsg())
                val cutNuber: String = certificationRes.getCutNuber()
                if (("0" == certificationRes.getCode())) {
                    SPUtils.put(SpKeys.CUT_NUMBER, cutNuber.toInt())
                    SPUtils.put(SpKeys.TOTAL_CUT_NUMBER, cutNuber.toInt())
                    SPUtils.put(SpKeys.CERTIFICATION_STATUS, 0)
                    this@FrameActivity.dissmissCertificationDislog()
                    return
                }
                SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1)
                this@FrameActivity.dissmissCertificationDislog()
                val msg: String = certificationRes.getMsg()
                if (!TextUtils.isEmpty(msg) && msg.contains("Parameter")) {
                    EventBus.getDefault().post(EventCenter<Any?>(40))
                    return
                }
                val split: Array<String> =
                    msg.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (MachineInfo.isChineseMachine()) {
                    str = split[0]
                } else if (split.size == 2) {
                    str = split[1]
                } else {
                    str = split[0]
                }
                this@FrameActivity.showCertificationFailedDialog(str)
            }
        }
        ) // io.reactivex.functions.Consumer
        { th ->
            // from class: com.kkkcut.e20j.ui.activity.FrameActivity.4
            Log.i(TAG, "error: " + th.message)
        }
        )
    }

    private fun checkCodeDb() {
        addDisposable(Observable.zip(
            assetsVersionObservable,
            localVersionObservable
        )
        // io.reactivex.functions.BiFunction
        { num, num2 ->
            // from class: com.kkkcut.e20j.ui.activity.FrameActivity.7
            num > num2
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                // io.reactivex.functions.Consumer
                { bool ->

                    // from class: com.kkkcut.e20j.ui.activity.FrameActivity.5
                    if (bool) {
                        this@FrameActivity.updateCodeDatabase()
                        LogUtil.d(TAG, "升级")
                    } else {
                        LogUtil.d(TAG, "齿号数据库无需升级")
                    }
                }
            )
            { }
        )
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun updateCodeDatabase() {
        addDisposable(Observable.fromCallable
        {
            FileUtil.copyAssetToSDCard(
                this@FrameActivity.assets,
                Constant.ZIP_NAME_CODE_DB,
                Constant.CODE_DB_ZIP_PATH
            )
        }.subscribeOn(Schedulers.io()).map {
            val file = File(Constant.CODE_DATABASE_PATH)
            if (!file.exists()) {
                file.mkdir()
            }
            val unzipFile: List<File>? =
                ZipUtils.unzipFile(Constant.CODE_DB_ZIP_PATH, Constant.CODE_DATABASE_PATH)
            return@map !unzipFile.isNullOrEmpty()

        }.map
        {
            FileUtil.copyAssetToSDCard(
                this@FrameActivity.assets, Constant.JSON_CODE_DB_UPDATE, File(
                    this@FrameActivity.filesDir, Constant.JSON_CODE_DB_UPDATE
                ).path
            )
        }.subscribe
        { bool ->
            if (bool) {
                LogUtil.d(TAG, "齿号数据库升级完成")
            }
        })
    }

    private val assetsVersionObservable: Observable<Int>
        get() {
            return Observable.fromCallable(
            {
                this@FrameActivity.assetsDbVersion
            })
        }

    private val localVersionObservable: Observable<Int>
        get() {
            return Observable.fromCallable(object : Callable<Int> {
                // from class: com.kkkcut.e20j.ui.activity.FrameActivity.13
                /* JADX WARN: Can't rename method to resolve collision */
                @Throws(Exception::class)  // java.util.concurrent.Callable
                override fun call(): Int {
                    return this@FrameActivity.localDbVersion
                }
            })
        }

    val localDbVersion: Int
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            val file = File(filesDir, Constant.JSON_CODE_DB_UPDATE)
            if (!file.exists()) {
                return 0
            }
            try {
                val fileInputStream = FileInputStream(file)
                val bArr = ByteArray(fileInputStream.available())
                fileInputStream.read(bArr)
                return JSONObject(String(bArr)).getInt("version")
            } catch (e: Exception) {
                e.printStackTrace()
                return 0
            }
        }

    val assetsDbVersion: Int
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            try {
                val open: InputStream = assets.open(Constant.JSON_CODE_DB_UPDATE)
                val bArr = ByteArray(open.available())
                open.read(bArr)
                return JSONObject(String(bArr)).getInt("version")
            } catch (e: Exception) {
                e.printStackTrace()
                return 0
            }
        }

    fun showLogo() {
        binding!!.titleLayout.tvLogo.setVisibility(0)
        binding!!.titleLayout.ivMenu.setVisibility(0)
        binding!!.titleLayout.ivMessage.setVisibility(0)
        binding!!.titleLayout.tvBack.setVisibility(8)
        binding!!.titleLayout.ivHome.setVisibility(8)
        setTittle("")
    }

    fun hideLogo() {
        binding!!.titleLayout.tvLogo.setVisibility(8)
        binding!!.titleLayout.ivMenu.setVisibility(8)
        binding!!.titleLayout.ivMessage.setVisibility(8)
        binding!!.titleLayout.tvBack.setVisibility(0)
        binding!!.titleLayout.ivHome.setVisibility(0)
    }

    fun setMachineName(str: String?) {
        if (TextUtils.isEmpty(str)) {
            return
        }
        binding!!.titleLayout.tvLogo.text = str
    }

    fun setLogo(i: Int) {
        val drawable: Drawable = resources.getDrawable(i)
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight())
        binding!!.titleLayout.tvLogo.setCompoundDrawablePadding(20)
        binding!!.titleLayout.tvLogo.setCompoundDrawables(drawable, null, null, null)
    }

    // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    override fun onKeyDown(i: Int, keyEvent: KeyEvent): Boolean {
        if (keyEvent.getKeyCode() == 82) {
            return true
        }
        if (keyEvent.getKeyCode() == 4) {
            val topFragment: ISupportFragment = getTopFragment()
            if ((topFragment is MainFragment) || (topFragment is MainE9Fragment)) {
                val currentTimeMillis: Long = System.currentTimeMillis()
                if (currentTimeMillis - this.DOUBLE_CLICK_TIME > 1500) {
                    ToastUtil.showToast(R.string.press_again_to_exit)
                    this.DOUBLE_CLICK_TIME = currentTimeMillis
                } else {
                    UsbSerialManager.getInstance().stop()
                    System.exit(0)
                }
                return true
            }
        }
        return super.onKeyDown(i, keyEvent)
    }

    // com.kkkcut.e20j.base.BaseActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    override fun onEventComing(eventCenter: EventCenter<*>) {
        val eventCode: Int = eventCenter.getEventCode()
        if (eventCode == 12) {
            if (AppUtil.isApkInDebug(this)) {
                return
            }
            showErrorDialog(
                R.drawable.error_1,
                getString(R.string.no_device_found),
                getString(R.string.retry),
                object : DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.activity.FrameActivity.14
                    // com.cutting.machine.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        if (z) {
                            UsbSerialManager.getInstance().init(this@FrameActivity)
                        }
                    }
                })
            return
        }
        if (eventCode == 14) {
            if (AppUtil.isApkInDebug(this)) {
                return
            }
            showErrorDialog(
                R.drawable.error_1, getString(R.string.device_not_connected), getString(
                    R.string.retry
                ), object : DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.activity.FrameActivity.15
                    // com.cutting.machine.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        if (z) {
                            UsbSerialManager.getInstance().init(this@FrameActivity)
                        }
                    }
                })
            return
        }
        if (eventCode == 19) {
            val forbidDialog: ForbidDialog = ForbidDialog(this)
            forbidDialog.setRemindImg(R.drawable.error_1)
            forbidDialog.setTip(getString(R.string.init_failed_restart_machine))
            forbidDialog.show()
            return
        }
        if (eventCode == 20) {
            MyApplication.getInstance().setSerialInit(true)
            dismissLoadingDialog()
            return
        }
        if (eventCode == 41) {
            AppUtil.isApkInDebug(this)
            return
        }
        if (eventCode == 42) {
            checkHaveNewMessage()
            return
        }
        when (eventCode) {
            35 -> {
                val assetsFrimVerstion: String =
                    AssetVersionUtil.getAssetsFrimVerstion(MyApplication.getInstance())
                val str: String = eventCenter.getData() as String
                if (TextUtils.isEmpty(assetsFrimVerstion)) {
                    OperationManager.getInstance().sendOrder(Command.OpenDoorCuttingSettings(1))
                    return
                } else if (!TextUtils.equals(assetsFrimVerstion, str)) {
                    FirmwareUpdateActivity.start(this, str, assetsFrimVerstion)
                    return
                } else {
                    OperationManager.getInstance().sendOrder(
                        Command.OpenDoorCuttingSettings(
                            if (SPUtils.getBoolean(
                                    SpKeys.COVER,
                                    true
                                )
                            ) 1 else 0
                        )
                    )
                    return
                }
            }

            36 -> {
                showCertificationDialog(
                    getString(R.string.please_connect_network_for_authentication_device_has_been_locked),
                    false,
                    object : CertificationDialog.DialogBtnCallBack {
                        // from class: com.kkkcut.e20j.ui.activity.FrameActivity.16
                        // com.kkkcut.e20j.ui.dialog.CertificationDialog.DialogBtnCallBack
                        override fun onDialogButClick(z: Boolean) {
                            if (z) {
                                this@FrameActivity.startCertificationFromNet()
                            }
                        }
                    })
                return
            }

            37 -> {
                startCertificationFromNet()
                return
            }

            else -> return
        }
    }

    private fun showCertificationDialog(
        str: String,
        z: Boolean,
        dialogBtnCallBack: CertificationDialog.DialogBtnCallBack
    ) {
        if (AppUtil.isApkInDebug(this)) {
            return
        }
        if (this.certificationDialog == null) {
            this.certificationDialog = CertificationDialog(this)
        }
        certificationDialog!!.setTip(str)
        certificationDialog!!.setClickDismiss(z)
        certificationDialog!!.setConfirmStr(getString(R.string.authenticate))
        certificationDialog!!.setDialogBtnCallback(dialogBtnCallBack)
        if (certificationDialog!!.isShowing()) {
            return
        }
        certificationDialog!!.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun dissmissCertificationDislog() {
        val certificationDialog: CertificationDialog? = this.certificationDialog
        if (certificationDialog == null || !certificationDialog.isShowing()) {
            return
        }
        this.certificationDialog!!.dismiss()
    }

    fun onViewClicked(view: View) {
        when (view.getId()) {
            R.id.iv_home -> {
                goHome()
                return
            }

            R.id.iv_menu -> {
                EventBus.getDefault().post(EventCenter<Any?>(10))
                return
            }

            R.id.iv_message -> {
                goMessage()
                return
            }

            R.id.tv_back -> {
                onBack()
                return
            }

            R.id.tv_title -> {
                val jArr: LongArray = this.mHits
                System.arraycopy(jArr, 1, jArr, 0, jArr.size - 1)
                val jArr2: LongArray = this.mHits
                jArr2[jArr2.size - 1] = SystemClock.uptimeMillis()
                if (mHits.get(0) >= SystemClock.uptimeMillis() - 2000) {
                    val file: File = File(Constant.CONFIG_PATH)
                    if (file.exists() && file.delete()) {
                        ToastUtil.showToast(R.string.configuration_file_delete_success)
                        return
                    }
                    return
                }
                return
            }

            else -> return
        }
    }

    // androidx.fragment.app.FragmentActivity, android.app.Activity
    public override fun onResume() {
        super.onResume()
    }

    private fun goMessage() {
        start(MessageFragment.newInstance())
    }

    private fun goCalibrate() {
        start(CalibrationFragment.newInstance())
    }

    fun goHome() {
        val findFragment: ISupportFragment?
        if (MachineInfo.isE9Standard(this)) {
            findFragment = findFragment(MainE9Fragment::class.java)
        } else {
            findFragment = findFragment(MainFragment::class.java)
        }
        if (findFragment != null) {
            start(findFragment, 2)
        } else {
            start(MainFragment.newInstance())
        }
    }

    fun onBack() {
        onBackPressed()
    }

    fun setTittle(str: String?) {
        tvTitle?.setText(str)
    }

    fun setTittle(i: Int) {
        val textView: TextView? = this.tvTitle
        textView?.setText(i)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showCertificationFailedDialog(str: String?) {
        val forbidDialog: ForbidDialog = ForbidDialog(this)
        forbidDialog.setRemindImg(R.drawable.error_1)
        forbidDialog.setTip(str)
        forbidDialog.show()
    }

    private fun showErrorDialog(
        i: Int,
        str: String,
        str2: String,
        dialogBtnCallBack: DialogBtnCallBack
    ) {
        if (this.errorDialog == null) {
            this.errorDialog = ErrorDialog(this)
        }
        errorDialog!!.setRemindImg(i)
        errorDialog!!.setTip(str)
        errorDialog!!.setConfirmStr(str2)
        errorDialog!!.setDialogBtnCallback(dialogBtnCallBack)
        if (errorDialog!!.isShowing()) {
            return
        }
        errorDialog!!.show()
    }

    // com.kkkcut.e20j.base.BaseFActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
        UsbSerialManager.getInstance().stop()
        NetStateReceiver.unRegisterNetworkStateReceiver(this.mContext)
    }

    val tvBack: TextView
        get() {
            return binding!!.titleLayout.tvBack
        }

    // com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity
    override fun showLoadingDialog(str: String?) {
        if (this.loadingDialog == null) {
            this.loadingDialog = LoadingDialog(this)
        }
        if (!StringUtil.isEmpty(str)) {
            this.loadingDialog!!.setTip(str)
        }
        this.loadingDialog!!.setCancelable(false)
        if (!this.loadingDialog!!.isShowing) {
            this.loadingDialog!!.show()
        }
        Handler().postDelayed(object : Runnable {
            // from class: com.kkkcut.e20j.ui.activity.FrameActivity.17
            // java.lang.Runnable
            override fun run() {
                this@FrameActivity.dismissLoadingDialog()
            }
        }, 15000L)
    }

    // com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity
    override fun dismissLoadingDialog() {
        val loadingDialog: LoadingDialog? = this.loadingDialog
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return
        }
        this.loadingDialog!!.dismiss()
    }

    companion object {
        protected var TAG: String = "FrameActivity"
    }
}
