package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.bean.KeyInfo
import com.cutting.machine.error.ErrorCode
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.SpecificParamUtils
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class DimpleDuplicateCutDialog(activity: Activity?, private val ki: KeyInfo) :
    BottomInDialog(activity) {
    var bt15mm: Button? = null

    var bt20mm: Button? = null

    var bt25mm: Button? = null

    var cbFast: CheckBox? = null

    var cbPlasticKey: CheckBox? = null
    private var cutDepth = 0
    private var cutSpeed = 0
    private var cutter_size = 0

    var ivClamp: ImageView? = null

    var ivCutter: ImageView? = null

    var ivDepth: ImageView? = null

    var llCutDepth: LinearLayout? = null

    var llCutSpeed: LinearLayout? = null

    var llCutterSize: LinearLayout? = null

    var llPlasticKey: LinearLayout? = null

    var rbLayer1: RadioButton? = null

    var rbLayer2: RadioButton? = null

    var rbLayer3: RadioButton? = null

    var rbShapeGentle: RadioButton? = null

    var rbShapeJagged: RadioButton? = null

    var rgCutShape: RadioGroup? = null

    var rgLayerCut: RadioGroup? = null

    var tvCutDepth: TextView? = null

    var tvCutShape: TextView? = null

    var tvCutSpeed: TextView? = null

    var tvCutterSize: TextView? = null

    var tvCutterSizeRemind: TextView? = null

    var tvDepthValue: TextView? = null

    var tvLayerCut: TextView? = null

    var tvSpeedValue: TextView? = null

    // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    override fun getContentLayoutID(): Int {
        return R.layout.dialog_cut
    }

    // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    override fun initView() {
        initClamp()
        initCutter()
        initCutDepth()
        initLayerCut()
        initCutShape()
        initCutSpeed()
        initPlasticKey()
        initFastMode()
    }

    private fun initFastMode() {
        if (ki.isQuickCut) {
            cbFast!!.isChecked = true
        }
    }

    private fun initCutSpeed() {
        val i = SPUtils.getInt(SpKeys.SPEED + ki.type, if (ki.type == 6) 3 else 15)
        this.cutSpeed = i
        tvSpeedValue!!.text = i.toString()
    }

    private fun initPlasticKey() {
        if (ki.align == 0 && ki.length == 0) {
            llPlasticKey!!.visibility = 8
            return
        }
        if ((ki.type == 5) || (ki.type == 3) || (ki.type == 2) || (ki.type == 4)) {
            llPlasticKey!!.visibility = 0
            if (ki.isPlasticKey) {
                cbPlasticKey!!.performClick()
                return
            }
            return
        }
        llPlasticKey!!.visibility = 8
    }

    private fun initLayerCut() {
        if (ki.type == 5 || ki.type == 2) {
            tvLayerCut!!.visibility = 0
            rgLayerCut!!.visibility = 0
            val i = SPUtils.getInt(SpKeys.LAYERCUT, 3)
            if (i == 1) {
                rbLayer1!!.isChecked = true
                return
            } else if (i == 2) {
                rbLayer2!!.isChecked = true
                return
            } else {
                if (i != 3) {
                    return
                }
                rbLayer3!!.isChecked = true
                return
            }
        }
        tvLayerCut!!.visibility = 8
        rgLayerCut!!.visibility = 8
    }

    private fun initCutDepth() {
        this.cutDepth = ki.cutDepth
        if ((ki.type == 5) || (ki.type == 3) || (ki.type == 4) || (ki.type == 2)) {
            llCutDepth!!.visibility = 0
            tvCutDepth!!.visibility = 0
            if (ki.cutDepth == 0) {
                this.cutDepth = 110
            } else {
                this.cutDepth = ki.cutDepth
            }
            tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
            return
        }
        llCutDepth!!.visibility = 8
        tvCutDepth!!.visibility = 8
    }

    private fun initCutShape() {
        if (ki.type == 1 && ki.cutDepth == 0) {
            tvCutShape!!.visibility = 0
            rgCutShape!!.visibility = 0
            val i = SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1)
            if (i == 1) {
                rbShapeGentle!!.isChecked = true
                return
            } else {
                if (i != 2) {
                    return
                }
                rbShapeJagged!!.isChecked = true
                return
            }
        }
        tvCutShape!!.visibility = 8
        rgCutShape!!.visibility = 8
    }

    private fun initCutter() {
        if (ki.type == 6) {
            if (ki.spaceWidthStr.contains("-")) {
                ivCutter!!.setImageResource(R.drawable.cutter_dimple_2)
            } else {
                ivCutter!!.setImageResource(R.drawable.cutter_dimple_1)
            }
            llCutterSize!!.visibility = 8
            bt15mm!!.visibility = 8
            bt20mm!!.visibility = 8
            bt25mm!!.visibility = 8
            ToolSizeManager.setCutterSize(ToolSizeManager.DimpleCutterSize)
        } else {
            ivCutter!!.setImageResource(R.drawable.cutter_normal)
        }
        val cutterSize = ToolSizeManager.getCutterSize()
        Log.i(TAG, "initCutter: $cutterSize")
        var param = SpecificParamUtils.getParam(ki.type_specific_info, "cutter")
        if (!TextUtils.isEmpty(param)) {
            if (param.contains(",")) {
                val split = param.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (split.size > 0) {
                    param = split[1]
                }
            }
            tvCutterSizeRemind!!.visibility = 0
            tvCutterSizeRemind!!.text = String.format(
                activity.resources.getString(R.string.please_use_smm_milling_cutter),
                param
            )
        }
        if (cutterSize > 0) {
            this.cutter_size = cutterSize
        } else if (ki.type == 5 || ki.type == 2) {
            if (ki.groove != 0 && ki.groove < 200) {
                this.cutter_size = (ki.groove / 10) * 10
            } else {
                this.cutter_size = 200
            }
        } else if (ki.type == 6) {
            this.cutter_size = 100
        } else if (ki.type == 0) {
            this.cutter_size = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200)
        } else {
            this.cutter_size = 200
        }
        tvCutterSize!!.text =
            String.format("%.1fmm", this.cutter_size / 100.0f)
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_1_5mm -> {
                this.cutter_size = 150
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_0mm -> {
                this.cutter_size = 200
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_5mm -> {
                this.cutter_size = 250
                tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_cancle -> {
                dismiss()
                return
            }

            R.id.bt_cut -> {
                if (ki.type == 6) {
                    if (ki.spaceWidthStr.contains("-")) {
                        val bundle = Bundle()
                        bundle.putInt("cutDepth", this.cutDepth)
                        bundle.putInt("cutterSize", this.cutter_size)
                        bundle.putBoolean("plastic_key", cbPlasticKey!!.isChecked)
                        bundle.putBoolean("quick_cut", cbFast!!.isChecked)
                        EventBus.getDefault().post(EventCenter<Any?>(1, bundle))
                        return
                    }
                    val remindDialog = RemindDialog(context)
                    remindDialog.setRemindImg(R.drawable.remind_cutter_dimple_1)
                    remindDialog.setCancleBtnVisibility(false)
                    remindDialog.setRemindMsg(context.getString(R.string.please_install_specified_milling_cutter))
                    remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack { z ->

                        // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.1
                        // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                        if (z) {
                            val bundle2 = Bundle()
                            bundle2.putInt("cutDepth", this@DimpleDuplicateCutDialog.cutDepth)
                            bundle2.putInt("cutterSize", this@DimpleDuplicateCutDialog.cutter_size)
                            bundle2.putBoolean(
                                "plastic_key",
                                cbPlasticKey!!.isChecked
                            )
                            bundle2.putBoolean(
                                "quick_cut",
                                cbFast!!.isChecked
                            )
                            EventBus.getDefault().post(EventCenter<Any?>(1, bundle2))
                        }
                    })
                    remindDialog.show()
                    return
                }
                val warningDialog = WarningDialog(context)
                warningDialog.show()
                warningDialog.setRemind(
                    context.getString(
                        R.string.please_use_specify_cutter, *arrayOf<Any>(
                            (this.cutter_size / 100.0f).toString() + "mm"
                        )
                    )
                )
                warningDialog.setDialogBtnCallback(object : WarningDialog.DialogBtnCallBack {
                    // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.2
                    // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    override fun onDialogButClick(z: Boolean) {
                        if (z) {
                            val bundle2 = Bundle()
                            bundle2.putInt("cutDepth", this@DimpleDuplicateCutDialog.cutDepth)
                            bundle2.putInt("cutterSize", this@DimpleDuplicateCutDialog.cutter_size)
                            bundle2.putBoolean(
                                "plastic_key",
                                cbPlasticKey!!.isChecked
                            )
                            bundle2.putBoolean(
                                "quick_cut",
                                cbFast!!.isChecked
                            )
                            EventBus.getDefault().post(EventCenter<Any?>(1, bundle2))
                            this@DimpleDuplicateCutDialog.dismiss()
                        }
                    }
                })
                return
            }

            R.id.cb_fast -> {
                if (cbFast!!.isChecked) {
                    val warningDialog2 = WarningDialog(context)
                    warningDialog2.setRemind(context.getString(R.string.quick_cut_remind))
                    warningDialog2.setCancelText(context.getString(R.string.cancel))
                    warningDialog2.setConfirmText(context.getString(R.string.continue1))
                    warningDialog2.setDialogBtnCallback(object : WarningDialog.DialogBtnCallBack {
                        // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.3
                        // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                        override fun onDialogButClick(z: Boolean) {
                            if (z) {
                                return
                            }
                            cbFast!!.isChecked = false
                        }
                    })
                    warningDialog2.show()
                    return
                }
                return
            }

            R.id.iv_close -> {
                dismiss()
                return
            }

            R.id.iv_depth_add -> {
                this.cutDepth += 5
                tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
                return
            }

            R.id.iv_depth_reduce -> {
                val i = this.cutDepth
                if (i > 5) {
                    this.cutDepth = i - 5
                }
                tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
                return
            }

            R.id.iv_size_add -> {
                val i2 = this.cutter_size
                if (i2 < 250) {
                    this.cutter_size = i2 + 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                if (i2 < 500) {
                    this.cutter_size = i2 + 50
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_size_reduce -> {
                val i3 = this.cutter_size
                if (i3 > 250) {
                    this.cutter_size = i3 - 50
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                if (i3 > 100) {
                    this.cutter_size = i3 - 10
                    tvCutterSize!!.text = (this.cutter_size / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_speed_add -> {
                if (this.cutSpeed < (if (ki.type != 6) 25 else 5)) {
                    val i4 = this.cutSpeed + 1
                    this.cutSpeed = i4
                    tvSpeedValue!!.text = i4.toString()
                    SPUtils.put(SpKeys.SPEED + ki.type, this.cutSpeed)
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
                    SPUtils.put(SpKeys.SPEED + ki.type, this.cutSpeed)
                    return
                }
                return
            }

            else -> return
        }
    }

    private fun initClamp() {
        val clampImg = getClampImg(this.ki)
        if (clampImg != 0) {
            ivClamp!!.setImageResource(clampImg)
        }
    }

    private fun getClampImg(keyInfo: KeyInfo): Int {
        if (keyInfo.icCard == 20131 || keyInfo.icCard == 1915) {
            return R.drawable.car_clamp_remind_d_tip_20131
        }
        if (keyInfo.shoulderBlock == 1) {
            return R.drawable.car_clamp_remind_d_shoulder_6620131
        }
        val clampBean = keyInfo.clampBean
        if (("S1" == clampBean.clampNum)) {
            if (("A" == clampBean.clampSide)) {
                if (("0" == clampBean.clampSlot)) {
                    return if (keyInfo.align == 0) R.drawable.car_clamp_remind_a_shoulder else R.drawable.car_clamp_remind_a_tip
                }
            } else {
                if (("B" == clampBean.clampSide)) {
                    return if (("0" == clampBean.clampSlot)) if (keyInfo.align == 0) R.drawable.car_clamp_remind_b_shoulder else R.drawable.car_clamp_remind_b_tip else if (keyInfo.align == 0) R.drawable.car_clamp_remind_b_shoulder_side else R.drawable.car_clamp_remind_b_tip_side
                }
                if (("C" == clampBean.clampSide)) {
                    if (("0" == clampBean.clampSlot)) {
                        return if (keyInfo.align == 0) R.drawable.car_clamp_remind_c_shoulder else if (keyInfo.spaceStr.split(
                                ";".toRegex()
                            ).dropLastWhile { it.isEmpty() }
                                .toTypedArray()[0].split(",".toRegex())
                                .dropLastWhile { it.isEmpty() }
                                .toTypedArray()[0].toInt() + ErrorCode.keyCuttingError > 3000
                        ) R.drawable.car_clamp_remind_c_long_tip else R.drawable.car_clamp_remind_c_tip
                    }
                } else if (("D" == clampBean.clampSide) && ("0" == clampBean.clampSlot)) {
                    return if (keyInfo.align == 0) R.drawable.car_clamp_remind_d_shoulder else if (keyInfo.isNewHonda) R.drawable.car_clamp_remind_d_tip_honda else R.drawable.car_clamp_remind_d_tip
                }
            }
        } else {
            if (("S2" == clampBean.clampNum)) {
                return if (("A" == clampBean.clampSide)) if (keyInfo.align == 0) R.drawable.singlekey_clamp_remind_a_shoulder else R.drawable.singlekey_clamp_remind_a_tip else if (keyInfo.align == 0) R.drawable.singlekey_clamp_remind_b_shoulder else if (keyInfo.isNewHonda) R.drawable.singlekey_clamp_remind_b_tip_honda else R.drawable.singlekey_clamp_remind_b_tip
            }
            if (("S3" == clampBean.clampNum)) {
                if (("A" == clampBean.clampSide)) {
                    return R.drawable.tubular_clamp_remind_s3_s7
                }
            } else if (("S4" == clampBean.clampNum)) {
                if (("A" == clampBean.clampSide)) {
                    return R.drawable.angel_key_clamp_remind
                }
            } else if (("S6" == clampBean.clampNum)) {
                return if (("A" == clampBean.clampSide)) R.drawable.sx9_clamp_remind_a else R.drawable.sx9_clamp_remind_b
            }
        }
        return 0
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.id) {
            R.id.cb_plastic_key -> {
                if (z && !ki.isPlasticKey) {
                    val warningDialog = WarningDialog(context)
                    warningDialog.show()
                    warningDialog.setRemind(context.getString(R.string.risk_sticking_breaking_milling_cutter))
                }
                if (ki.align == 1) {
                    if (z) {
                        if (("B" == ki.clampBean.clampSide)) {
                            ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip_plastic)
                            return
                        } else {
                            ivClamp!!.setImageResource(R.drawable.car_clamp_remind_a_tip_plastic)
                            return
                        }
                    }
                    ivClamp!!.setImageResource(R.drawable.car_clamp_remind_b_tip)
                    return
                }
                if (ki.icCard == 909) {
                    if (z) {
                        this.cutDepth = 100
                    } else {
                        this.cutDepth = 115
                    }
                    tvDepthValue!!.text = (this.cutDepth / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.rb_layer_1 -> {
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 1)
                    return
                }
                return
            }

            R.id.rb_layer_2 -> {
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 2)
                    return
                }
                return
            }

            R.id.rb_layer_3 -> {
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 3)
                    return
                }
                return
            }

            R.id.rb_shape_gentle -> {
                if (z) {
                    SPUtils.put(SpKeys.SINGLEKEY_CUT_SHAPE, 1)
                    return
                }
                return
            }

            R.id.rb_shape_jagged -> {
                if (z) {
                    SPUtils.put(SpKeys.SINGLEKEY_CUT_SHAPE, 2)
                    return
                }
                return
            }

            else -> return
        }
    }

    companion object {
        private val TAG = "CutDialog"
    }
}
