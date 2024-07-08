package com.kkkcut.e20j.ui.fragment.blankcut

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import com.cutting.machine.MachineInfo
import com.cutting.machine.ToolSizeManager
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog
import com.kkkcut.e20j.us.R
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class BlankCutDialog(activity: Activity?, private val blankCutType: BlankCutType) :
    BottomInDialog(activity) {
    var bt15mm: Button? = null

    var bt20mm: Button? = null

    var bt25mm: Button? = null

    var btCut: TextView? = null
    private var cutSpeed = 0
    private var cutterSizeChangeable = false
    private var cutter_size = 0

    var ivClamp: ImageView? = null

    var ivCutter: ImageView? = null

    var llCutSpeed: LinearLayout? = null

    var llCutterSize: LinearLayout? = null
    private var onceCut = false

    var rgCutTimes: RadioGroup? = null

    var tvCutSpeed: TextView? = null

    var tvCutterSize: TextView? = null

    var tvCutterSizeRemind: TextView? = null

    var tvSpeedValue: TextView? = null

    // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    override fun getContentLayoutID(): Int {
        return R.layout.fragment_modify_key_blank_cut
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog$1, reason: invalid class name */ /* loaded from: classes.dex */
    object AnonymousClass1 {
        /* synthetic */val `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`: IntArray

        init {
            val iArr = IntArray(BlankCutType.entries.size)
            `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType` = iArr
            try {
                iArr[BlankCutType.KEY_HEAD.ordinal] = 1
            } catch (unused: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.THICKNESS.ordinal] =
                    2
            } catch (unused2: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.WIDTH.ordinal] =
                    3
            } catch (unused3: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.k9ToLxp90.ordinal] =
                    4
            } catch (unused4: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.Toyota80K.ordinal] =
                    5
            } catch (unused5: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.DRILLING.ordinal] =
                    6
            } catch (unused6: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.LEFT_GROOVE.ordinal] =
                    7
            } catch (unused7: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.RIGHT_GROOVE.ordinal] =
                    8
            } catch (unused8: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.KEY_TIP.ordinal] =
                    9
            } catch (unused9: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.CREATE_GROOVE.ordinal] =
                    10
            } catch (unused10: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.K4080K.ordinal] =
                    11
            } catch (unused11: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.HY18.ordinal] =
                    12
            } catch (unused12: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.HY18R.ordinal] =
                    13
            } catch (unused13: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.SIDE_GROOVE.ordinal] =
                    14
            } catch (unused14: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.KW16ToKW15.ordinal] =
                    15
            } catch (unused15: NoSuchFieldError) {
            }
            try {
                `$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[BlankCutType.KW14ToKW15.ordinal] =
                    16
            } catch (unused16: NoSuchFieldError) {
            }
        }
    }

    // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    override fun initView() {
        when (AnonymousClass1.`$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[blankCutType.ordinal]) {
            1 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_head)
                rgCutTimes!!.visibility = 8
            }

            2 -> {
                if (MachineInfo.isChineseMachine()) {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_thickness_s1)
                } else {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_thickness)
                }
                rgCutTimes!!.visibility = 0
            }

            3, 4, 5 -> {
                if (MachineInfo.isChineseMachine()) {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_width_s1)
                } else {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_width)
                }
                rgCutTimes!!.visibility = 0
            }

            6 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_drilling)
                rgCutTimes!!.visibility = 8
            }

            7 -> {
                if (MachineInfo.isChineseMachine()) {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_left_groove_s1)
                } else {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_left_groove_s8)
                }
                rgCutTimes!!.visibility = 8
            }

            8 -> {
                if (MachineInfo.isChineseMachine()) {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_right_groove_s1)
                } else {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_right_groove_s8)
                }
                rgCutTimes!!.visibility = 8
            }

            9 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_key_tip)
                rgCutTimes!!.visibility = 8
            }

            10, 11 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_40k80k)
                rgCutTimes!!.visibility = 8
            }

            12, 13 -> {
                if (MachineInfo.isChineseMachine()) {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_hy18r_s1)
                } else {
                    ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_hy18_s8)
                }
                rgCutTimes!!.visibility = 8
            }

            14 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_cut_side_groove_s8)
                rgCutTimes!!.visibility = 8
            }

            15, 16 -> {
                ivClamp!!.setImageResource(R.drawable.clamp_remind_blank_kw16_kw15_groove_s8)
                rgCutTimes!!.visibility = 8
            }
        }
        initCutter()
        initCutSpeed()
    }

    private fun initCutSpeed() {
        val speed = BlankCutSpeedUtils.getSpeed(this.blankCutType)
        this.cutSpeed = speed
        tvSpeedValue!!.text = speed.toString()
    }

    private fun initCutter() {
        val i =
            AnonymousClass1.`$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType`[blankCutType.ordinal]
        if (i == 6) {
            this.cutter_size = 250
            this.cutterSizeChangeable = false
        } else if (i == 14) {
            this.cutter_size = 100
            this.cutterSizeChangeable = false
        } else {
            this.cutter_size = ToolSizeManager.getCutterSize()
            this.cutterSizeChangeable = true
        }
        tvCutterSize!!.text = String.format("%.1fmm", this.cutter_size / 100.0f)
        if (this.cutterSizeChangeable) {
            return
        }
        bt15mm!!.visibility = 8
        bt20mm!!.visibility = 8
        bt25mm!!.visibility = 8
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_1_5mm -> {
                if (checkCutterSize()) {
                    return
                }
                this.cutter_size = 150
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_0mm -> {
                if (checkCutterSize()) {
                    return
                }
                this.cutter_size = 200
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_5mm -> {
                if (checkCutterSize()) {
                    return
                }
                this.cutter_size = 250
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_cancle -> {
                dismiss()
                return
            }

            R.id.iv_close -> {
                dismiss()
                return
            }

            R.id.iv_size_add -> {
                if (checkCutterSize()) {
                    return
                }
                val i = this.cutter_size
                if (i < 250) {
                    this.cutter_size = i + 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                if (i < 500) {
                    this.cutter_size = i + 50
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_size_reduce -> {
                if (checkCutterSize()) {
                    return
                }
                val i2 = this.cutter_size
                if (i2 > 250) {
                    this.cutter_size = i2 - 50
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                if (i2 > 100) {
                    this.cutter_size = i2 - 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_speed_add -> {
                val i3 = this.cutSpeed
                if (i3 < 25) {
                    val i4 = i3 + 1
                    this.cutSpeed = i4
                    tvSpeedValue!!.text = i4.toString()
                    BlankCutSpeedUtils.saveSpeed(this.blankCutType, this.cutSpeed)
                    return
                }
                return
            }

            R.id.iv_speed_reduce -> {
                val i5 = this.cutSpeed
                if (i5 > 1) {
                    val i6 = i5 - 1
                    this.cutSpeed = i6
                    tvSpeedValue!!.text = i6.toString()
                    BlankCutSpeedUtils.saveSpeed(this.blankCutType, this.cutSpeed)
                    return
                }
                return
            }

            R.id.tv_cut -> {
                val warningDialog = WarningDialog(context)
                warningDialog.show()
                warningDialog.setRemind(
                    context.getString(
                        R.string.please_use_specify_cutter, *arrayOf<Any>(
                            (this.cutter_size / 100.0f).toString() + "mm"
                        )
                    )
                )
                warningDialog.setDialogBtnCallback { z ->
                    // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog$$ExternalSyntheticLambda0
                    // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    this@BlankCutDialog.m49x9deff7e3(z)
                }
                return
            }

            else -> return
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$onViewClicked$0$com-kkkcut-e20j-ui-fragment-blankcut-BlankCutDialog, reason: not valid java name */
    /* synthetic */ fun m49x9deff7e3(z: Boolean) {
        if (z) {
            val bundle = Bundle()
            bundle.putInt("cutterSize", this.cutter_size)
            bundle.putBoolean("isSecondSide", this.onceCut)
            EventBus.getDefault().post(EventCenter<Any?>(1, bundle))
            dismiss()
        }
    }

    private fun checkCutterSize(): Boolean {
        if (this.cutterSizeChangeable) {
            return false
        }
        ToastUtil.showToast(
            context.getString(
                R.string.please_use_specify_cutter, *arrayOf<Any>(
                    (this.cutter_size / 100.0f).toString() + "mm"
                )
            )
        )
        return true
    }

    fun onCheckedChange(compoundButton: CompoundButton, z: Boolean) {
        val id = compoundButton.id
        if (id == R.id.rb_double_cut) {
            if (z) {
                this.onceCut = false
            }
        } else if (id == R.id.rb_once_cut && z) {
            this.onceCut = true
        }
    }

    companion object {
        private const val TAG = "CutDialog"
    }
}
