package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebView
import android.widget.TextView
import com.cutting.machine.Command
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.communication.OperationManager
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.fromCallable
import io.reactivex.rxjava3.schedulers.Schedulers

/* loaded from: classes.dex */
class AboutFragment() : BaseBackFragment() {
    var tvCompany: TextView? = null

    var tvDbVersion: TextView? = null

    var tvFirmware: TextView? = null

    var tvModelName: TextView? = null

    var tvSerial: TextView? = null

    var tvSoftVersion: TextView? = null

    var tvUpdateLog: TextView? = null

    var webView: WebView? = null

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_about_us
    }

    // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.machine_info)
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var string = arguments!!.getString("companyStr")
        if (TextUtils.isEmpty(string)) {
            string = "Copyright(c) 2020 Hunan Kukai Electromechanical co.,ltd.  All rights reserved"
        }
        if (MachineInfo.isE20Neutral(context)) {
            string = ""
        }
        tvCompany!!.text = string
        tvSoftVersion!!.text = AppUtil.getVersionName(context)
        dbVersion
        val string2 = arguments!!.getString("machineName")
        if (!TextUtils.isEmpty(string2)) {
            tvModelName!!.text = string2
        }
        val string3 = arguments!!.getString("series")
        if (!TextUtils.isEmpty(string3)) {
            tvSerial!!.text = string3
        }
        initUpdateLog()
        OperationManager.getInstance()
            .sendOrder(Command.QueryFirmwareVersion(), OperateType.READ_FIRMWARE)
    }

    private fun initUpdateLog() {
        val observable = fromCallable{
            KeyInfoDaoManager.getInstance().updateInfo
        }.subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        );

        val subscription = observable.subscribe({
            var str = it
            if (TextUtils.isEmpty(str)) {
                str = ""
            }
            if (!MachineInfo.isE9Standard(this.context)) {
                str =
                    "<body style=\"margin:0;padding:0;background-color:#4b4d62;color:white;\">\n$str</body>"
            }
            val settings = webView!!.settings
            settings.javaScriptEnabled = true
            settings.defaultTextEncodingName = "UTF-8"
            webView!!.loadData(str, "text/html; charset=UTF-8", null)
        }, { dismissLoadingDialog() })

        addDisposable(subscription)
    }

    private val dbVersion: Unit
        get() {
            addDisposable(
                fromCallable({
                    val dbVersion: String = KeyInfoDaoManager.getInstance().getDbVersion()
                    if (TextUtils.isEmpty(dbVersion)) "16.44" else dbVersion
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    tvDbVersion!!.text = it
                }, { th -> dismissLoadingDialog() }))
        }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible && eventCenter.eventCode == 35) {
            val str = eventCenter.data as String
            if (TextUtils.isEmpty(str)) {
                return
            }
            tvFirmware!!.text = str
        }
    }

    companion object {
        fun newInstance(str: String?, str2: String?, str3: String?): AboutFragment {
            val bundle = Bundle()
            bundle.putString("machineName", str)
            bundle.putString("series", str2)
            bundle.putString("companyStr", str3)
            val aboutFragment = AboutFragment()
            aboutFragment.arguments = bundle
            return aboutFragment
        }
    }
}