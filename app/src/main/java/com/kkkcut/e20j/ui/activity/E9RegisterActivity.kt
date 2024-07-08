package com.kkkcut.e20j.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import androidx.core.widget.addTextChangedListener
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.Constant
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.GsonHelper
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.LoadingDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityRegisterBinding
import com.kkkcut.e20j.utils.AssetVersionUtil
import com.kkkcut.e20j.utils.GetUUID
import com.kkkcut.e20j.utils.UnifiedErrorUtil
import com.kkkcut.e20j.utils.lan.LocalManageUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/* loaded from: classes.dex */
class E9RegisterActivity : BaseCustomKeyBoardActivity() {
    private var binding: ActivityRegisterBinding? = null

    private val listCompositeDisposable = ListCompositeDisposable()
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        this.binding = ActivityRegisterBinding.inflate(
            layoutInflater
        )
        this.binding!!.etRegCode.addTextChangedListener {
            if (it != null && it.length == 8) {
                hideSoftKeyboard()
            }
        }
        this.binding!!.etSn.addTextChangedListener {
            if (it != null && it.length == 10) {
                binding!!.etRegCode.requestFocus()
            }
        }
        setContentView(binding!!.root)
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_register
    }

    override fun initViewsAndEvents() {
        bindToEditor(binding!!.etSn, 0)
        bindToEditor(binding!!.etRegCode, 0)
        binding!!.etSn.postDelayed(
            { binding!!.etSn.requestFocus() }, 300L
        )
        val uuid = GetUUID.getUUID()
        if (TextUtils.isEmpty(uuid)) {
            return
        }
        binding!!.tvCpu.text = uuid
    }

    fun onViewClicked() {
        registration()
    }

    private fun registration() {
        val trim = binding!!.etSn.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToast(R.string.not_null)
            return
        }
        if (trim.length != 10) {
            ToastUtil.showToast(R.string.serial_number_is_not_correct)
            return
        }
        val trim2 = binding!!.etRegCode.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim2)) {
            ToastUtil.showToast(R.string.not_null)
            return
        }
        if (trim2.length != 8) {
            ToastUtil.showToast(R.string.registration_code_is_not_correct)
            return
        }
        val uuid = GetUUID.getUUID()
        if (TextUtils.isEmpty(uuid)) {
            ToastUtil.showToast(R.string.cpu_id_not_found)
        } else {
            showLoading()
            addDisposable((RetrofitManager.getInstance()
                .createApi(Apis::class.java) as Apis).register(
                TUitls.register(
                    uuid,
                    trim,
                    trim2
                )
            ).subscribeOn(
                Schedulers.io()
            ).flatMap { registrationRes ->
                Log.i(TAG, "first---" + registrationRes.msg + ":" + registrationRes.code)
                if (("0" == registrationRes.code)) {
                    val configurationFile = registrationRes.configurationFile
                    SPUtils.put(SpKeys.CONFIGURATION_FILE, configurationFile)
                    return@flatMap (RetrofitManager.getInstance()
                        .createApi(Apis::class.java) as Apis).getConfigE9(
                        TUitls.getconfig(
                            configurationFile
                        )
                    ) as ObservableSource<ConfigurationE9>
                }
                throw Exception(registrationRes.msg + ":" + registrationRes.code)
            }.doOnNext { configurationE9 ->
                val id = configurationE9.id
                SPUtils.put(SpKeys.MACHINE_ID, id)
                if (MachineInfo.isE20Standard(this)) {
                    RetrofitManager.getInstance().initBaseUrl(MachineInfo.isChineseMachine(id))
                } else {
                    RetrofitManager.getInstance().initBaseUrl(false)
                }
                if (MachineInfo.isChineseMachine(id)) {
                    LocalManageUtil.saveSelectLanguage(this, "zh")
                } else {
                    LocalManageUtil.saveSelectLanguage(this, "en")
                }
                Log.i(TAG, "database:" + configurationE9.database)
                FileUtil.readIoStringToFile(
                    GsonHelper.toJsonString(configurationE9),
                    Constant.CONFIG_PATH
                )
                SPUtils.put("series",
                    binding!!.etSn.text.toString().trim { it <= ' ' })
                val assetsDbVersion = AssetVersionUtil.getAssetsDbVersion(
                    this.assets,
                    Constant.CONFIG_UPDATA
                )
                Log.i(TAG, "asset配置文件版本: $assetsDbVersion")
                SPUtils.put(SpKeys.CONFIG_UPDATE, assetsDbVersion)
                SPUtils.sync()
                Thread.sleep(3000L)
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.dismiss()
                    this.finish()
                },  { th ->
                    this.dismiss()
                    ToastUtil.showToast(UnifiedErrorUtil.unifiedError(th).message)
                })
            )
        }
    }

    private fun showLoading() {
        if (this.loadingDialog == null) {
            this.loadingDialog = LoadingDialog(this)
        }
        if (this.loadingDialog!!.isShowing) {
            return
        }
        this.loadingDialog!!.show()
    }

    fun dismiss() {
        val loadingDialog = this.loadingDialog
        if (loadingDialog == null || !loadingDialog.isShowing) {
            return
        }
        this.loadingDialog!!.dismiss()
    }

    override fun addDisposable(disposable: Disposable) {
        if (disposable.isDisposed) {
            return
        }
        this.listCompositeDisposable.add(disposable)
    }

    override fun clear() {
        if (this.listCompositeDisposable.isDisposed) {
            return
        }
        this.listCompositeDisposable.clear()
    }

    override fun dispatchKeyEvent(keyEvent: KeyEvent): Boolean {
        if (keyEvent.keyCode == 66) {
            if (binding!!.etSn.hasFocus()) {
                binding!!.etRegCode.requestFocus()
                return true
            }
            registration()
            return true
        }
        return super.dispatchKeyEvent(keyEvent)
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}
