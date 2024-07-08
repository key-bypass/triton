package com.kkkcut.e20j.ui.fragment.duplicatekey

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.cutting.machine.CuttingMachine
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.bean.DestPoint
import com.cutting.machine.bean.KeyAlign
import com.cutting.machine.bean.KeyType
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.duplicate.Benchmark
import com.cutting.machine.duplicate.DecodeData
import com.cutting.machine.duplicate.SinglePathData
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData
import com.cutting.machine.speed.Speed
import com.cutting.machine.utils.DuplicateUtil
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.eventbus.DuplicateBean
import com.kkkcut.e20j.customView.MyRadioGroup
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment
import com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.CutCountHelper
import com.kkkcut.e20j.utils.ThemeUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

/* loaded from: classes.dex */
class DuplicateKeyNewFragment() : BaseBackFragment() {
    var btDimple: Button? = null

    var btHondaKey: Button? = null
    private var cutParams: DuplicateCutParams? = null
    private var decodeParams: DuplicateDecodeParams? = null
    private var isE9Machine = false

    var ivClamp0: ImageView? = null

    var ivClamp1: ImageView? = null

    var ivClamp2: ImageView? = null
    private val keyInfo = DuplicateKeyData()

    var llClamp0: LinearLayout? = null

    var llClamp1: LinearLayout? = null

    var llClamp2: LinearLayout? = null

    var myRadioGroup: MyRadioGroup? = null

    var rbDoubleGrooveKey: RadioButton? = null

    var rbDoubleKey: RadioButton? = null

    var rbSideHole: RadioButton? = null

    var rbSingleInsideLeftKey: RadioButton? = null

    var rbSingleInsideRightKey: RadioButton? = null

    var rbSingleKey: RadioButton? = null

    var rbThreeGrooveKey: RadioButton? = null

    var rbTubularKey: RadioButton? = null

    var tvShoulder1: RadioButton? = null

    var tvShoulder2: RadioButton? = null

    var tvTip1: RadioButton? = null

    var tvTip2: RadioButton? = null

    var tvTitleClamp: TextView? = null

    /* JADX INFO: Access modifiers changed from: package-private */ /* loaded from: classes.dex */
    enum class OperationType {
        decode,
        cut
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_duplicate_key
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onUserVisible() {
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val isE9 = CuttingMachine.getInstance().isE9
        this.isE9Machine = isE9
        if (isE9) {
            btHondaKey!!.visibility = 8
            rbSideHole!!.visibility = 8
            rbDoubleGrooveKey!!.visibility = 8
            rbThreeGrooveKey!!.visibility = 8
            rbSingleInsideLeftKey!!.visibility = 8
            rbSingleInsideRightKey!!.visibility = 8
            btDimple!!.visibility = 8
        } else {
            if (MachineInfo.isChineseMachine()) {
                rbTubularKey!!.visibility = 8
                rbSingleKey!!.visibility = 8
            }
            rbSingleInsideLeftKey!!.visibility = 0
            rbSingleInsideRightKey!!.visibility = 0
            btHondaKey!!.visibility = 0
            btDimple!!.visibility = 0
            rbSideHole!!.visibility = 0
        }
        if (MachineInfo.isChineseMachine()) {
            rbDoubleKey!!.isChecked = true
        } else {
            rbSingleKey!!.isChecked = true
            keyInfo.setType(KeyType.SINGLE_SIDE_KEY)
        }
        showDuplicationRemind()
    }

    private fun showDuplicationRemind() {
        val warningDialog = WarningDialog(context)
        warningDialog.show()
        warningDialog.setRemind(getString(R.string.duplication_remind))
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String {
        return getString(R.string.duplicating_key)
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_dimple -> {
                start(DuplicateDimpleAlignFragment.newInstance())
                return
            }

            R.id.bt_honda_key -> {
                start(HondaSideCutFragment.newInstance())
                return
            }

            R.id.ll_clamp_0 -> {
                readyDecode(0, view)
                return
            }

            R.id.ll_clamp_1 -> {
                readyDecode(1, view)
                return
            }

            R.id.ll_clamp_2 -> {
                readyDecode(2, view)
                return
            }

            R.id.rb_shoulder_1 -> {
                if (keyInfo.keyType == KeyType.SINGLE_OUTSIDE_GROOVE_KEY) {
                    keyInfo.side = 1
                } else {
                    keyInfo.side = 0
                }
                keyInfo.setAlign(KeyAlign.SHOULDERS_ALIGN)
                showClamp()
                return
            }

            R.id.rb_shoulder_2 -> {
                if (keyInfo.keyType == KeyType.DOUBLE_SIDE_KEY) {
                    keyInfo.side = 3
                } else {
                    keyInfo.side = 0
                }
                keyInfo.setAlign(KeyAlign.SHOULDERS_ALIGN)
                showClamp()
                return
            }

            R.id.rb_tip_1 -> {
                if (keyInfo.keyType == KeyType.SINGLE_OUTSIDE_GROOVE_KEY) {
                    keyInfo.side = 1
                } else {
                    keyInfo.side = 0
                }
                keyInfo.setAlign(KeyAlign.FRONTEND_ALIGN)
                showClamp()
                return
            }

            R.id.rb_tip_2 -> {
                if (keyInfo.keyType == KeyType.DOUBLE_SIDE_KEY) {
                    keyInfo.side = 3
                } else {
                    keyInfo.side = 0
                }
                keyInfo.setAlign(KeyAlign.FRONTEND_ALIGN)
                showClamp()
                return
            }

            else -> return
        }
    }

    private fun readyDecode(i: Int, view: View) {
        val clampTag = view.tag as ClampTag
        val clamp = clampTag.clamp
        val clampMode = clampTag.clampMode
        if (ClampManager.getInstance().checkHasCalibrated(clamp)) {
            initDecodeData(keyInfo.keyType)
            changeClampStatus(i)
            this.decodeParams = DuplicateDecodeParams.DecodeParamsBuilder.aDecodeParams()
                .withDecodeSize(ToolSizeManager.getDecoderSize()).withKeyInfo(
                this.keyInfo
            ).withClamp(clamp).withClampMode(clampMode).withDensity(30).build()
            showConductiveTestRemind(OperationType.decode)
        }
    }

    object KeyTypeEnum {
       val keyType: IntArray = IntArray(KeyType.entries.size)

        init {

            keyType[KeyType.SINGLE_SIDE_KEY.ordinal] = 1
            keyType[KeyType.DOUBLE_SIDE_KEY.ordinal] = 2
            keyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal] = 3
            keyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal] = 4
            keyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal] = 5
            keyType[KeyType.LEFT_GROOVE.ordinal] = 6
            keyType[KeyType.RIGHT_GROOVE.ordinal] = 7
            keyType[KeyType.TWO_GROOVE.ordinal] = 8
            keyType[KeyType.THREE_GROOVE.ordinal] = 9
            keyType[KeyType.TUBULAR_KEY.ordinal] = 10
            keyType[KeyType.SIDE_HOLE.ordinal] = 11
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private fun initDecodeData(keyType: KeyType) {
        val decodeData = DecodeData()
        var i = 0
        when (KeyTypeEnum.keyType[keyType.ordinal]) {
            1 -> decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
            2, 3 -> {
                decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
                decodeData.addSinglePathData(SinglePathData(Benchmark.LEFT))
            }

            4 -> if (keyInfo.side == 0) {
                decodeData.addSinglePathData(SinglePathData(Benchmark.LEFT))
            } else {
                decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
            }

            5, 6, 7 -> {
                decodeData.addSinglePathData(SinglePathData(Benchmark.LEFT))
                decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
            }

            8 -> while (i < 2) {
                decodeData.addSinglePathData(SinglePathData(Benchmark.LEFT))
                decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
                i++
            }

            9 -> while (i < 3) {
                decodeData.addSinglePathData(SinglePathData(Benchmark.LEFT))
                decodeData.addSinglePathData(SinglePathData(Benchmark.RIGHT))
                i++
            }

            10 -> decodeData.addSinglePathData(SinglePathData())
            11 -> decodeData.addSinglePathData(SinglePathData())
        }
        keyInfo.decodeData = decodeData
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCut() {
        CuttingMachine.getInstance().duplicateCut(this.cutParams)
        showLoadingDialog(getString(R.string.waitting), true)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startDecode() {
        CuttingMachine.getInstance().duplicateDecode(this.decodeParams)
        showLoadingDialog(getString(R.string.decoding), true)
    }

    private fun changeClampStatus(i: Int) {
        if (CuttingMachine.getInstance().isE9) {
            return
        }
        when (KeyTypeEnum.keyType[keyInfo.keyType.ordinal]) {
            1 -> {
                if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    if (i == 0) {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_select)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default)
                        return
                    } else {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_select)
                        return
                    }
                }
                if (i == 0) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_select)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default)
                    return
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_select)
                    return
                }
            }

            2 -> {
                if (i == 0) {
                    if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_select)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default)
                        return
                    } else {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_select)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default)
                        return
                    }
                }
                if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_select)
                    return
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_select)
                    return
                }
            }

            3, 5, 6, 7, 8, 9 -> {
                if (i == 1) {
                    if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_select)
                        return
                    } else {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_select)
                        return
                    }
                }
                if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_select)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
                    return
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_select)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
                    return
                }
            }

            4 -> {
                if (keyInfo.keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    if (i == 1) {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_select)
                        ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default)
                        return
                    } else if (i == 2) {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
                        ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_select)
                        return
                    } else {
                        ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_select)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
                        ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default)
                        return
                    }
                }
                if (i == 1) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_select)
                    ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default)
                    return
                } else if (i == 2) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
                    ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_select)
                    return
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_select)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
                    ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default)
                    return
                }
            }

            10 -> {
                ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s3_select)
                return
            }

            else -> {}
        }
    }

    private fun showClamp() {
        llClamp0!!.visibility = View.GONE
        llClamp1!!.visibility = View.GONE
        llClamp2!!.visibility = View.GONE
        val keyAlign = keyInfo.keyAlign
        if (this.isE9Machine) {
            showE9Clamp(keyAlign)
        } else {
            showAlphaBetaClamp(keyAlign)
        }
    }

    private fun showE9Clamp(keyAlign: KeyAlign) {
        when (KeyTypeEnum.keyType[keyInfo.keyType.ordinal]) {
            1 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.single_key_5mm_shoulder_duplicate_e9)
                    ivClamp1!!.setImageResource(R.drawable.single_key_35mm_shoulder_duplicate_e9)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.single_key_5mm_tip_duplicate_e9)
                    ivClamp1!!.setImageResource(R.drawable.single_key_35mm_tip_duplicate_e9)
                }
                llClamp0!!.tag = ClampTag(Clamp.E9S2A)
                llClamp1!!.tag = ClampTag(Clamp.E9S2B)
                return
            }

            2 -> {
                llClamp0!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.a9_standard_stop_1_duplicate_e9)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.a9_standard_stop_4_duplicate_e9)
                }
                llClamp0!!.tag = ClampTag(Clamp.E9S1C)
                return
            }

            3, 5, 6, 7, 8, 9 -> {
                llClamp0!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9)
                }
                llClamp0!!.tag = ClampTag(Clamp.E9S1A)
                return
            }

            4 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9)
                    ivClamp1!!.setImageResource(R.drawable.a9_laser_stop_1_side_duplicate_e9)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9)
                    ivClamp1!!.setImageResource(R.drawable.a9_laser_stop_4_side_duplicate_e9)
                }
                llClamp0!!.tag = ClampTag(Clamp.E9S1A)
                llClamp1!!.tag = ClampTag(Clamp.E9S1A, 1)
                return
            }

            10 -> return
            11 -> {
                llClamp0!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_1_side_duplicate_e9)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.a9_laser_stop_4_side_duplicate_e9)
                }
                llClamp0!!.tag = ClampTag(Clamp.E9S1A, 1)
            }

            else -> return
        }
    }

    private fun showAlphaBetaClamp(keyAlign: KeyAlign) {
        when (KeyTypeEnum.keyType[keyInfo.keyType.ordinal]) {
            1 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default)
                }
                llClamp0!!.tag = ClampTag(Clamp.S2_A)
                llClamp1!!.tag = ClampTag(Clamp.S2_B)
                return
            }

            2 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default)
                }
                llClamp0!!.tag = ClampTag(Clamp.S1_C)
                llClamp1!!.tag = ClampTag(Clamp.S1_D)
                return
            }

            3, 5, 6, 7, 8, 9 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
                }
                llClamp0!!.tag = ClampTag(Clamp.S1_A)
                llClamp1!!.tag = ClampTag(Clamp.S1_B)
                return
            }

            4 -> {
                llClamp0!!.visibility = 0
                llClamp1!!.visibility = 0
                llClamp2!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
                    ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                    ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
                    ivClamp2!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default)
                }
                llClamp0!!.tag = ClampTag(Clamp.S1_A)
                llClamp1!!.tag = ClampTag(Clamp.S1_B)
                llClamp2!!.tag = ClampTag(Clamp.S1_B, 1)
                return
            }

            10 -> return
            11 -> {
                llClamp0!!.visibility = 0
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default)
                } else {
                    ivClamp0!!.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default)
                }
                llClamp0!!.tag = ClampTag(Clamp.S1_B, 1)
            }

            else -> return
        }
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        if (z) {
            myRadioGroup!!.clearCheck()
            tvShoulder1!!.isChecked = false
            tvShoulder2!!.isChecked = false
            tvTip1!!.isChecked = false
            tvTip2!!.isChecked = false
            llClamp0!!.visibility = 8
            llClamp1!!.visibility = 8
            llClamp2!!.visibility = 8
            ToolSizeManager.setDecoderSize(100)
            ToolSizeManager.setCutterSize(200)
            when (compoundButton.id) {
                R.id.rb_double_groove_key -> {
                    ToolSizeManager.setDecoderSize(50)
                    ToolSizeManager.setCutterSize(100)
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_double_groove_key_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_double_groove_key_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.TWO_GROOVE)
                    return
                }

                R.id.rb_double_groove_key_wudihu -> {
                    ToolSizeManager.setDecoderSize(50)
                    ToolSizeManager.setCutterSize(100)
                    tvShoulder1!!.setBackgroundResource(R.drawable.keycopy_double_groove_key_shoulder_wudihu_default)
                    tvTip1!!.setBackgroundResource(R.drawable.keycopy_double_groove_key_tip_wudihu_default)
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.TWO_GROOVE)
                    return
                }

                R.id.rb_double_key -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_doublekey_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_doublekey_tip
                        )
                    )
                    tvShoulder1!!.text =
                        getString(R.string.ab_same) + " " + getString(R.string.shoulder)
                    tvTip1!!.text =
                        getString(R.string.ab_same) + " " + getString(R.string.tip)
                    tvShoulder2!!.visibility = 0
                    tvTip2!!.visibility = 0
                    tvShoulder2!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            context, R.attr.keycopy_doublekey_shoulder_abdiff
                        )
                    )
                    tvTip2!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            context, R.attr.keycopy_doublekey_tip_abdiff
                        )
                    )
                    tvShoulder2!!.text =
                        getString(R.string.ab_diff) + " " + getString(R.string.shoulder)
                    tvTip2!!.text =
                        getString(R.string.ab_diff) + " " + getString(R.string.tip)
                    keyInfo.setType(KeyType.DOUBLE_SIDE_KEY)
                    return
                }

                R.id.rb_double_outside_key -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_double_outside_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_double_outside_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.DOUBLE_OUTSIDE_GROOVE_KEY)
                    return
                }

                R.id.rb_inside_key -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.DOUBLE_INSIDE_GROOVE_KEY)
                    return
                }

                R.id.rb_side_hole -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_side_hole_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_side_hole_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    ToolSizeManager.setCutterSize(100)
                    ToolSizeManager.setDecoderSize(50)
                    keyInfo.setType(KeyType.SIDE_HOLE)
                    return
                }

                R.id.rb_single_inside_left_key -> {
                    ToolSizeManager.setDecoderSize(50)
                    ToolSizeManager.setCutterSize(100)
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_left_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_left_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.LEFT_GROOVE)
                    return
                }

                R.id.rb_single_inside_right_key -> {
                    ToolSizeManager.setCutterSize(100)
                    ToolSizeManager.setDecoderSize(50)
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_right_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_inside_right_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.RIGHT_GROOVE)
                    return
                }

                R.id.rb_single_key -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_singlekey_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_singlekey_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.SINGLE_SIDE_KEY)
                    return
                }

                R.id.rb_single_outside_key -> {
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_outside_up_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_outside_up_tip
                        )
                    )
                    tvShoulder2!!.visibility = 0
                    tvTip2!!.visibility = 0
                    tvShoulder2!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_outside_down_shoulder
                        )
                    )
                    tvTip2!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_single_outside_down_tip
                        )
                    )
                    tvShoulder1!!.text =
                        getString(R.string.up_single_track) + " " + getString(R.string.shoulder)
                    tvTip1!!.text =
                        getString(R.string.up_single_track) + " " + getString(R.string.tip)
                    tvShoulder2!!.text =
                        getString(R.string.down_single_track) + " " + getString(R.string.shoulder)
                    tvTip2!!.text =
                        getString(R.string.down_single_track) + " " + getString(R.string.tip)
                    keyInfo.setType(KeyType.SINGLE_OUTSIDE_GROOVE_KEY)
                    return
                }

                R.id.rb_three_groove_key -> {
                    ToolSizeManager.setDecoderSize(50)
                    ToolSizeManager.setCutterSize(100)
                    tvShoulder1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_three_groove_key_shoulder
                        )
                    )
                    tvTip1!!.setBackgroundResource(
                        ThemeUtils.getResId(
                            requireContext(),
                            R.attr.keycopy_three_groove_key_tip
                        )
                    )
                    tvShoulder1!!.setText(R.string.shoulder)
                    tvTip1!!.setText(R.string.tip)
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    keyInfo.setType(KeyType.THREE_GROOVE)
                    return
                }

                R.id.rb_tubular_key -> {
                    tvShoulder1!!.setBackgroundResource(0)
                    tvTip1!!.setBackgroundResource(0)
                    tvShoulder1!!.text = ""
                    tvTip1!!.text = ""
                    tvShoulder2!!.visibility = 8
                    tvTip2!!.visibility = 8
                    llClamp0!!.visibility = 8
                    llClamp1!!.visibility = 0
                    llClamp2!!.visibility = 8
                    keyInfo.setType(KeyType.TUBULAR_KEY)
                    llClamp1!!.visibility = 0
                    if (CuttingMachine.getInstance().isE9) {
                        ivClamp1!!.setImageResource(R.drawable.a9_tubular_stop_duplicate_e9)
                        llClamp1!!.tag = ClampTag(Clamp.E9S3)
                        return
                    } else {
                        llClamp1!!.tag = ClampTag(Clamp.S3)
                        ivClamp1!!.setImageResource(R.drawable.keycopy_clamp_s3_select)
                        return
                    }
                }

                else -> return
            }
        }
    }

    private fun saveCutNumber() {
        val i = SPUtils.getInt(SpKeys.CUT_NUMBER, 200) - 1
        if (i <= 0) {
            EventBus.getDefault().post(EventCenter<Any?>(36))
            SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1)
        }
        SPUtils.put(SpKeys.CUT_NUMBER, i)
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible) {
            val eventCode = eventCenter.eventCode
            if (eventCode == 0) {
                calculatePoint(eventCenter)
                return
            }
            if (eventCode == 43) {
                val duplicateBean = eventCenter.data as DuplicateBean
                ToolSizeManager.setCutterSize(duplicateBean.cutterSize)
                val i = SPUtils.getInt(
                    SpKeys.SPEED + keyInfo.keyType.value,
                    if (keyInfo.keyType == KeyType.DIMPLE_KEY) 3 else 15
                )
                this.cutParams = DuplicateCutParams.DuplicateCutParamsBuilder.aDuplicateCutParams()
                    .withCutDepth(0).withCutSpeed(i).withKeyInfo(
                    this.keyInfo
                ).withDecoderSize(ToolSizeManager.getDecoderSize())
                    .withCutterSize(ToolSizeManager.getCutterSize()).withClamp(
                    decodeParams!!.clamp
                ).withClampMode(decodeParams!!.clampMode)
                    .singleSideKeyCutDepthFix(duplicateBean.cutDepthSingleKey)
                    .withLayer(duplicateBean.layerCut).build()
                Speed.setSpeed(i)
                showClearClampRemind(OperationType.cut)
                return
            }
            if (eventCode == 47) {
                showLoadingDialog((eventCenter.data as Int).toString() + "%", true)
                return
            }
            if (eventCode == 49) {
                putUpDecoderRemindDialog()
                return
            }
            if (eventCode != 50) {
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
            putDownDecoderRemindDialog((eventCenter.data as Int))
        }
    }

    fun putDownDecoderRemindDialog(i: Int) {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog = RemindDialog(context)
            remindDialog.setRemindImg(R.drawable.pull_decoder_down)
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down))
            remindDialog.setDialogBtnCallback { z ->
                if (!z) {
                    this.dismissLoadingDialog()
                } else if (i == 0) {
                    this.startDecode()
                } else {
                    this.startCut()
                }
            }
            remindDialog.show()
        }
    }

    fun putUpDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog = RemindDialog(context)
            remindDialog.setRemindImg(R.drawable.push_decoder_up)
            remindDialog.setRemindMsg(getString(R.string.push_the_decoder_up))
            remindDialog.setDialogBtnCallback(object : RemindDialog.DialogBtnCallBack {
                // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.2
                // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                override fun onDialogButClick(z: Boolean) {
                    if (z) {
                        CuttingMachine.getInstance()
                            .duplicateCutFromCutterLocation(this@DuplicateKeyNewFragment.cutParams)
                        val duplicateKeyNewFragment = this@DuplicateKeyNewFragment
                        duplicateKeyNewFragment.showLoadingDialog(
                            duplicateKeyNewFragment.getString(
                                R.string.waitting
                            ), true
                        )
                        return
                    }
                    this@DuplicateKeyNewFragment.dismissLoadingDialog()
                }
            })
            remindDialog.show()
        }
    }

    private fun showError(eventCenter: EventCenter<*>) {
        dismissLoadingDialog()
        showErrorDialog(context, eventCenter.data as ErrorBean)
    }

    private fun calculatePoint(eventCenter: EventCenter<*>) {
        val bundle = eventCenter.data as Bundle
        val i = bundle.getInt("row")
        val i2 = bundle.getInt("depth")
        val i3 = bundle.getInt("space")
        val i4 = bundle.getInt("keyIndex")
        Log.i(TAG, "row: $i  space:$i3  depth:$i2  keyIndex:$i4")
        if (i2 < 0) {
            return
        }
        val destPoint = DestPoint(i3, i2)
        destPoint.keyIndex = i4
        val pathDataList = keyInfo.decodeData.pathDataList
        if (i == 1) {
            val i5 = i4 * 2
            pathDataList[i5].addDestPoint(destPoint)
            if (keyInfo.keyType == KeyType.DOUBLE_SIDE_KEY && keyInfo.side == 0) {
                pathDataList[i5 + 1].addDestPoint(destPoint.m600clone())
                return
            }
            return
        }
        pathDataList[(i4 * 2) + 1].addDestPoint(destPoint)
    }

    private fun handleOperationFinish(eventCenter: EventCenter<*>) {
        val operateType = eventCenter.data as OperateType
        if (operateType == OperateType.DUPLICATE_DECODE_EXECUTE) {
            decodeFinish()
        } else if (operateType == OperateType.DUPLICATE_CUT_EXECUTE) {
            addDisposable(
                Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread())
                    .observeOn(
                        AndroidSchedulers.mainThread()
                    ).subscribe({
                        this.dismissLoadingDialog()
                    }, { dismissLoadingDialog() })
            )
            showLoadingDialog("100%", true)
            saveCutNumber()
            CutCountHelper.getInstance().addCutCount(keyInfo.keyType)
        }
    }

    private fun decodeFinish() {
        val pathDataList = keyInfo.decodeData.pathDataList
        val keyAlignInfo = OperationManager.getInstance().keyAlignInfo
        val it: Iterator<SinglePathData> = pathDataList.iterator()
        while (it.hasNext()) {
            DuplicateUtil.reducePoint(
                keyInfo.keyType,
                keyInfo.keyAlign, keyAlignInfo, it.next().destPointList
            )
        }
        DuplicateNewCutDialog(activity, this.decodeParams).show()
        dismissLoadingDialog()
    }

    private fun showConductiveTestRemind(operationType: OperationType) {
        if ((keyInfo.keyType == KeyType.LEFT_GROOVE) || (keyInfo.keyType == KeyType.RIGHT_GROOVE) || (keyInfo.keyType == KeyType.TWO_GROOVE) || (keyInfo.keyType == KeyType.THREE_GROOVE)) {
            val warningDialog = WarningDialog(context)
            warningDialog.setCancelBtVisible(true)
            warningDialog.setRemind(getString(R.string.duplicate_conductive_test))
            warningDialog.setDialogBtnCallback { z ->
                if (z) {
                    this.showClearClampRemind(operationType)
                }
            }
            warningDialog.show()
            return
        }
        showClearClampRemind(operationType)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun showClearClampRemind(operationType: OperationType) {
        val duplicateDecodeParams = this.decodeParams
        val clamp = duplicateDecodeParams?.clamp
        if (clamp == null) {
            ToastUtil.showToast("NO Jaw selected")
            return
        }
        val remindDialog = RemindDialog(context)
        remindDialog.setCancleBtnVisibility(false)
        if (clamp == Clamp.S1_A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_a)
        } else if (clamp == Clamp.S1_B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_b)
        } else if (clamp == Clamp.S1_C) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_c)
        } else if (clamp == Clamp.S1_D) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_d)
        } else if (clamp == Clamp.S2_A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s2_a)
        } else if (clamp == Clamp.S2_B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s2_b)
        } else if (clamp == Clamp.S3) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s3)
        } else if (clamp == Clamp.E9S1A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_laser_key)
        } else if (clamp == Clamp.E9S1C) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_standard_key)
        } else if (clamp == Clamp.E9S2A || clamp == Clamp.E9S2B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_single_key_e9)
        } else if (clamp == Clamp.E9S3) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_tubular_e9)
        }
        if ((keyInfo.keyType == KeyType.TWO_GROOVE) || (keyInfo.keyType == KeyType.THREE_GROOVE) || (keyInfo.keyType == KeyType.LEFT_GROOVE) || (keyInfo.keyType == KeyType.RIGHT_GROOVE) || (keyInfo.keyType == KeyType.SIDE_HOLE)) {
            remindDialog.setRemindMsg(
                getString(
                    R.string.please_use_specify_decoder_cutter,
                    "0.5mm",
                    "1.0mm"
                ) + "\n" + getString(
                    R.string.clean_the_groove_from_chips
                )
            )
        } else if (operationType == OperationType.cut) {
            val sb = StringBuilder()
            sb.append(
                getString(
                    R.string.please_use_specify_cutter,
                    (ToolSizeManager.getCutterSize() / 100.0f).toString() + "mm"
                )
            )
            sb.append("\n")
            sb.append(getString(R.string.clean_the_groove_from_chips))
            remindDialog.setRemindMsg(sb.toString())
        } else {
            remindDialog.setRemindMsg(getString(R.string.clean_the_groove_from_chips))
        }
        remindDialog.setDialogBtnCallback(object : RemindDialog.DialogBtnCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment$$ExternalSyntheticLambda0
            // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            override fun onDialogButClick(z: Boolean) {
                this@DuplicateKeyNewFragment.m50xebbc060e(operationType, remindDialog, z)
            }
        })
        remindDialog.show()
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showClearClampRemind$0$com-kkkcut-e20j-ui-fragment-duplicatekey-DuplicateKeyNewFragment, reason: not valid java name */
    /* synthetic */ fun m50xebbc060e(
        operationType: OperationType,
        remindDialog: RemindDialog,
        z: Boolean
    ) {
        if (z) {
            if (operationType == OperationType.cut) {
                startCut()
            } else {
                startDecode()
            }
            remindDialog.dismiss()
        }
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    class ClampTag {
        var clamp: Clamp
        var clampMode: Int = 0

        constructor(clamp: Clamp) {
            this.clamp = clamp
        }

        constructor(clamp: Clamp, i: Int) {
            this.clamp = clamp
            this.clampMode = i
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): DuplicateKeyNewFragment {
            return DuplicateKeyNewFragment()
        }
    }
}
