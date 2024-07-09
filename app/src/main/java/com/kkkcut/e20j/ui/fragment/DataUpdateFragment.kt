package com.kkkcut.e20j.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding4.view.clicks
import com.kkkcut.e20j.DbBean.LocalDbVersion
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.dao.ResUpdateDaoManager
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentUpdateBinding
import com.kkkcut.e20j.utils.AppUpdateUtil
import com.kkkcut.e20j.utils.ResUpdateUtils
import com.kkkcut.e20j.utils.DataBaseUpdateListener
import com.kkkcut.e20j.utils.ThemeUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/* loaded from: classes.dex */
class DataUpdateFragment() : BaseBackFragment() {
    private var binding: FragmentUpdateBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view: View = binding!!.getRoot()
        return view
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_update
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        addDisposable(
            Observable.create { observableEmitter ->
                val it: Iterator<LocalDbVersion> =
                    ResUpdateDaoManager.getInstance().getLocalDbList().iterator()
                while (it.hasNext()) {
                    observableEmitter.onNext(it.next())
                }
                observableEmitter.onComplete()
            }.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe(
                { localDbVersion: LocalDbVersion ->
                    val localMainDbName: String = ResUpdateUtils.getLocalMainDbName()
                    if (TextUtils.equals(localDbVersion.getSvResName(), localMainDbName)) {
                        binding!!.tvNameMainDb.text = this@DataUpdateFragment.getString(
                            R.string.resup_data_res,
                            localMainDbName
                        )
                        var mainDbVersion: String =
                            ResUpdateDaoManager.getInstance().getMainDbVersion(localMainDbName)
                        var svResVer: String = localDbVersion.getSvResVer()
                        if (TextUtils.isEmpty(mainDbVersion)) {
                            mainDbVersion = "0"
                        }
                        if (TextUtils.isEmpty(svResVer)) {
                            svResVer = "0"
                        }
                        binding!!.tvValueCurrentMainDb.setText(mainDbVersion)
                        binding!!.tvValueNewMainDb.setText(svResVer)
                        if (svResVer.toFloat() > mainDbVersion.toFloat()) {
                            binding!!.tvValueNewMainDb.setTextColor(getResources().getColor(R.color.color_ff205f))
                            binding!!.btMainDbUpdate.setVisibility(0)
                        } else {
                            binding!!.btMainDbUpdate.setVisibility(8)
                        }
                    }
                    if (TextUtils.equals(localDbVersion.getSvResName(), ResUpdateUtils.RES)) {
                        var resDbVersion: String =
                            ResUpdateDaoManager.getInstance().getResDbVersion()
                        var svResVer2: String = localDbVersion.getSvResVer()
                        if (TextUtils.isEmpty(resDbVersion)) {
                            resDbVersion = "0"
                        }
                        if (TextUtils.isEmpty(svResVer2)) {
                            svResVer2 = "0"
                        }
                        binding!!.tvValueCurrentRes.setText(resDbVersion)
                        binding!!.tvValueNewRes.setText(svResVer2)
                        if (svResVer2.toFloat() > resDbVersion.toFloat()) {
                            binding!!.tvValueNewRes.setTextColor(getResources().getColor(R.color.color_ff205f))
                            binding!!.btResUpdate.setVisibility(0)
                        } else {
                            binding!!.btResUpdate.setVisibility(8)
                        }
                    }
                    if (TextUtils.equals(
                            localDbVersion.getSvResName(),
                            ResUpdateUtils.ERROR_CODE
                        )
                    ) {
                        var locResVer: String = localDbVersion.getLocResVer()
                        var svResVer3: String = localDbVersion.getSvResVer()
                        if (TextUtils.isEmpty(locResVer)) {
                            locResVer = "0"
                        }
                        if (TextUtils.isEmpty(svResVer3)) {
                            svResVer3 = "0"
                        }
                        binding!!.tvValueCurrentError.setText(locResVer)
                        binding!!.tvValueNewError.setText(svResVer3)
                        if (!ResUpdateUtils.localErrorDbExist() || svResVer3.toFloat() > locResVer.toFloat()) {
                            binding!!.tvValueNewError.setTextColor(getResources().getColor(R.color.color_ff205f))
                            binding!!.btErrorUpdate.setVisibility(0)
                        } else {
                            binding!!.btErrorUpdate.setVisibility(8)
                        }
                    }
                    if (TextUtils.equals(localDbVersion.getLocResName(), ResUpdateUtils.IMAGE_DB)) {
                        var locResVer2: String = localDbVersion.getLocResVer()
                        var svResVer4: String = localDbVersion.getSvResVer()
                        if (TextUtils.isEmpty(locResVer2)) {
                            locResVer2 = "0"
                        }
                        if (TextUtils.isEmpty(svResVer4)) {
                            svResVer4 = "0"
                        }
                        binding!!.tvValueCurrentImgs.setText(locResVer2)
                        binding!!.tvValueNewImgs.setText(svResVer4)
                        if (svResVer4.toFloat() > locResVer2.toFloat()) {
                            binding!!.tvValueNewImgs.setTextColor(getResources().getColor(R.color.color_ff205f))
                            binding!!.btImgsUpdate.setVisibility(0)
                        } else {
                            binding!!.btImgsUpdate.setVisibility(8)
                        }
                    }
                    if (TextUtils.equals(localDbVersion.getLocResName(), ResUpdateUtils.APP)) {
                        binding!!.tvNameApp.setText(ResUpdateUtils.APP)
                        var versionName: String =
                            AppUtil.getVersionName(this@DataUpdateFragment.getContext())
                        val svResVer5: String = localDbVersion.getSvResVer()
                        if (TextUtils.isEmpty(versionName)) {
                            versionName = "0"
                        }
                        val str: String = if (TextUtils.isEmpty(svResVer5)) "0" else svResVer5
                        binding!!.tvValueCurrentSoftware.setText(versionName)
                        binding!!.tvValueNewSoftware.setText(str)
                        if (str.toFloat() > versionName.toFloat()) {
                            binding!!.tvValueNewSoftware.setTextColor(getResources().getColor(R.color.color_ff205f))
                            binding!!.btSoftwareUpdate.setVisibility(0)
                        } else {
                            binding!!.btSoftwareUpdate.setVisibility(8)
                        }
                    }
                },
                { th: Throwable -> ToastUtil.showToast(th.message) }, { dismissLoadingDialog() }))

        addDisposable(binding!!.btSoftwareUpdate.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({ obj: Unit ->
            AppUpdateUtil.checkUpdate(this@DataUpdateFragment.activity)
        }, { dismissLoadingDialog() }))
        update(
            binding!!.btMainDbUpdate,
            binding!!.tvValueCurrentMainDb,
            binding!!.tvValueNewMainDb,
            ResUpdateDaoManager.getInstance().getMainDb(ResUpdateUtils.getLocalMainDbName())
        )
        update(
            binding!!.btResUpdate,
            binding!!.tvValueCurrentRes,
            binding!!.tvValueNewRes, ResUpdateDaoManager.getInstance().getResDb()
        )
        update(
            binding!!.btErrorUpdate,
            binding!!.tvValueCurrentError,
            binding!!.tvValueNewError, ResUpdateDaoManager.getInstance().getErrorDb()
        )
        update(
            binding!!.btImgsUpdate,
            binding!!.tvValueCurrentImgs,
            binding!!.tvValueNewImgs, ResUpdateDaoManager.getInstance().getImgListDb(), true
        )
    }

    private fun update(
        view: View,
        textView: TextView,
        textView2: TextView,
        localDbVersion: LocalDbVersion,
        z: Boolean = false
    ) {
        addDisposable(
            view.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({ obj: Unit ->
                    ResUpdateUtils.download(
                        this@DataUpdateFragment.getContext(),
                        localDbVersion,
                        object : DataBaseUpdateListener {
                            // from class: com.kkkcut.e20j.ui.fragment.DataUpdateFragment.5.1
                            private var progressDialog: ProgressDialog? = null

                            // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                            override fun start() {
                                this.progressDialog =
                                    this@DataUpdateFragment.getProgressDialog(localDbVersion)
                            }

                            // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                            override fun progress(i: Int) {
                                progressDialog!!.setProgress(i)
                            }

                            // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                            override fun finish() {
                                view.setVisibility(8)
                                progressDialog!!.dismiss()
                                textView.setTextColor(
                                    ThemeUtils.getColor(
                                        this@DataUpdateFragment.getContext(),
                                        R.attr.textColor_ffffff_333333
                                    )
                                )
                                textView.setText(localDbVersion.getSvResVer())
                                textView2.setTextColor(
                                    ThemeUtils.getColor(
                                        this@DataUpdateFragment.getContext(),
                                        R.attr.textColor_ffffff_333333
                                    )
                                )
                                ToastUtil.showToast(R.string.update_finish)
                            }

                            // com.kkkcut.e20j.utils.ResUpdateUtils.DataBaseUpdateListener
                            override fun error(th: Throwable?) {
                                progressDialog!!.dismiss()
                                ToastUtil.showToast(th?.message)
                            }
                        },
                        z
                    )
                }, { dismissLoadingDialog() }))
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun getProgressDialog(localDbVersion: LocalDbVersion): ProgressDialog {
        val progressDialog: ProgressDialog = ProgressDialog(context)
        var svResName: String? = localDbVersion.svResName
        val localMainDbName: String = ResUpdateUtils.getLocalMainDbName()
        if (TextUtils.equals(localDbVersion.svResName, localMainDbName)) {
            svResName = getString(R.string.resup_data_res, localMainDbName)
        } else if (TextUtils.equals(localDbVersion.svResName, ResUpdateUtils.RES)) {
            svResName = getString(R.string.resup_resdb)
        } else if (TextUtils.equals(localDbVersion.svResName, ResUpdateUtils.ERROR_CODE)) {
            svResName = getString(R.string.resup_error_code)
        } else if (TextUtils.equals(localDbVersion.svResName, ResUpdateUtils.IMAGE_DB)) {
            svResName = getString(R.string.resup_imglist)
        }
        progressDialog.setProgressStyle(1)
        progressDialog.setTitle(svResName)
        progressDialog.setMessage(getString(R.string.downloading))
        progressDialog.setIndeterminate(false)
        progressDialog.setCancelable(false)
        progressDialog.show()
        return progressDialog
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.ResUpdate)
    }

    companion object {
        fun newInstance(): DataUpdateFragment {
            return DataUpdateFragment()
        }
    }
}
