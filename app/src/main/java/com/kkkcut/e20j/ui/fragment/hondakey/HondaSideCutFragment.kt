package com.kkkcut.e20j.ui.fragment.hondakey

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.cutting.machine.CuttingMachine
import com.cutting.machine.OperateType
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.operation.cut.DataParam
import com.jakewharton.rxbinding4.view.clicks
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/* loaded from: classes.dex */
class HondaSideCutFragment : BaseBackFragment() {
    var btCut: TextView? = null
    private var cutter_size = 200
    private val dataParam = DataParam()


    var rbHonda2020: RadioButton? = null

    var rbHonda2021: RadioButton? = null

    var rgYear: RadioGroup? = null
    private var side = 0

    var tvCutterSize: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_honda_cut
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
        btCut!!.visibility = 0
        addDisposable(btCut!!.clicks().throttleFirst(1L, TimeUnit.SECONDS).subscribe({ obj: Unit ->
            this@HondaSideCutFragment.startCut()
        }, {this.dismissLoadingDialog()}))
        if (arguments == null) {
            return
        }
        rbHonda2020!!.text = getString(R.string.honda) + " 2020"
        rbHonda2021!!.text = getString(R.string.honda) + " 2021"
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCut() {
        val createHondaSideKey = HondaKeyFactory.createHondaSideKey(
            if (rgYear!!.checkedRadioButtonId == R.id.rb_honda_2020) 2020 else 2021,
            this.side
        )
        ToolSizeManager.setCutterSize(this.cutter_size)
        dataParam.keyInfo = createHondaSideKey
        dataParam.cutSpeed = SPUtils.getInt(SpKeys.SPEED + createHondaSideKey!!.type, 15)
        dataParam.decoderSize = 100
        dataParam.cutterSize = this.cutter_size
        dataParam.isQuickCut = true
        CuttingMachine.getInstance().cut(this.dataParam)
        showLoadingDialog(getString(R.string.waitting), true)
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible) {
            val eventCode = eventCenter.eventCode
            if (eventCode != 47) {
                when (eventCode) {
                    32 -> {
                        handleOperationFinish(eventCenter)
                        return
                    }

                    33 -> {
                        showError(eventCenter)
                        return
                    }

                    34 -> {
                        showLoadingDialog(getString(R.string.waitting))
                        return
                    }

                    else -> return
                }
            }
            showLoadingDialog((eventCenter.data as Int).toString() + "%", true)
        }
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.iv_size_add -> {
                val i = this.cutter_size
                if (i < 250) {
                    this.cutter_size = i + 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_size_reduce -> {
                val i2 = this.cutter_size
                if (i2 > 100) {
                    this.cutter_size = i2 - 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            else -> return
        }
    }

    fun onCheckChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.id) {
            R.id.rb_honda_a -> {
                if (z) {
                    this.side = 0
                    return
                }
                return
            }

            R.id.rb_honda_b -> {
                if (z) {
                    this.side = 1
                    return
                }
                return
            }

            else -> return
        }
    }

    private fun handleOperationFinish(eventCenter: EventCenter<*>) {
        if ((eventCenter.data as OperateType) == OperateType.KEY_BLANK_CUT_EXECUTE) {
            addDisposable(
                Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                    .observeOn(
                        AndroidSchedulers.mainThread()
                    ).subscribe(
                { this@HondaSideCutFragment.dismissLoadingDialog() },
                { this.dismissLoadingDialog() })
            )
            showLoadingDialog("100%", true)
        }
    }

    private fun showError(eventCenter: EventCenter<*>) {
        dismissLoadingDialog()
        showErrorDialog(context, eventCenter.data as ErrorBean)
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.honda)
    }

    companion object {
        const val SIDE_A: Int = 0
        const val SIDE_B: Int = 1

        fun newInstance(): HondaSideCutFragment {
            val bundle = Bundle()
            val hondaSideCutFragment = HondaSideCutFragment()
            hondaSideCutFragment.arguments = bundle
            return hondaSideCutFragment
        }
    }
}
