package com.kkkcut.e20j.ui.fragment.duplicatekey

import android.app.Activity
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import com.cutting.machine.ToolSizeManager
import com.cutting.machine.bean.DestPoint
import com.cutting.machine.bean.KeyAlign
import com.cutting.machine.bean.KeyType
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.duplicate.keyview.CopyDoubleOutSideKey
import com.cutting.machine.duplicate.keyview.CopyDoubleSideKey
import com.cutting.machine.duplicate.keyview.CopySideHoleKey
import com.cutting.machine.duplicate.keyview.CopySingleInsideKey
import com.cutting.machine.duplicate.keyview.CopySingleOutSideKey
import com.cutting.machine.duplicate.keyview.CopySingleSideKey
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.eventbus.DuplicateBean
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.DialogDuplicateCutBinding
import com.kkkcut.e20j.utils.ThemeUtils
import org.greenrobot.eventbus.EventBus
import java.util.Objects

class DuplicateNewCutDialog(activity: Activity?, private val decodeParams: DuplicateDecodeParams?) :
    BottomInDialog(activity) {
    private var cutDepthSingleKey: Int = 0
    private var cutSpeed: Int = 0
    private var cutterSize: Int

    private var layerCut: Int = 3

    var binding: DialogDuplicateCutBinding

    override fun getContentLayoutID(): Int {
        return R.layout.dialog_duplicate_cut
    }

    init {
        this.cutterSize = ToolSizeManager.getCutterSize()
        setCancelable(false)
        this.binding = DialogDuplicateCutBinding.inflate(
            activity!!.layoutInflater
        )
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_1_0mm -> {
                this.cutterSize = 100
                binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_1_5mm -> {
                this.cutterSize = 150
                binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_0mm -> {
                this.cutterSize = 200
                binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_2_5mm -> {
                this.cutterSize = 250
                binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                return
            }

            R.id.bt_cancle -> {
                if (!SPUtils.getBoolean(SpKeys.DUPLICATE_CANCEL_REMIND_NERVER_ASK, false)) {
                    val remindDialog: RemindDialog = RemindDialog(context)
                    remindDialog.show()
                    remindDialog.setCheckbox(true, SpKeys.DUPLICATE_CANCEL_REMIND_NERVER_ASK)
                    remindDialog.setRemindMsg(getActivity().getString(R.string.decode_data_will_be_lost_after_cancel))
                    remindDialog.setCancelStr(getActivity().getString(R.string.no))
                    remindDialog.setConfirmStr(getActivity().getString(R.string.yes))

                    remindDialog.setDialogBtnCallback { result: Boolean ->
                        if (result) {
                            this@DuplicateNewCutDialog.dismiss()
                        }
                    }
                    return
                }
                dismiss()
                return
            }

            R.id.bt_cut -> {
                EventBus.getDefault().post(
                    EventCenter(
                        43, DuplicateBean(
                            this.layerCut, this.cutterSize, this.cutDepthSingleKey
                        )
                    )
                )
                return
            }

            R.id.iv_depth_add_single_key -> {
                val i: Int = this.cutDepthSingleKey
                if (i < 100) {
                    this.cutDepthSingleKey = i + 50
                    binding.tvDepthValueSingleKey.text = (this.cutDepthSingleKey / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_depth_reduce_single_key -> {
                val i2: Int = this.cutDepthSingleKey
                if (i2 > -100) {
                    this.cutDepthSingleKey = i2 - 50
                    binding.tvDepthValueSingleKey.text = (this.cutDepthSingleKey / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_size_add -> {
                val i3: Int = this.cutterSize
                if (i3 < 250) {
                    this.cutterSize = i3 + 10
                    binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_size_reduce -> {
                val i4: Int = this.cutterSize
                if (i4 > 50) {
                    this.cutterSize = i4 - 10
                    binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
                    return
                }
                return
            }

            R.id.iv_speed_add -> {
                if (this.cutSpeed < (if (keyType == KeyType.DIMPLE_KEY) 5 else 25)) {
                    val i5: Int = this.cutSpeed + 1
                    this.cutSpeed = i5
                    binding.tvSpeedValue.text = i5.toString()
                    SPUtils.put(SpKeys.SPEED + keyType.value, this.cutSpeed)
                    return
                }
                return
            }

            R.id.iv_speed_reduce -> {
                val i6: Int = this.cutSpeed
                if (i6 > 1) {
                    val i7: Int = i6 - 1
                    this.cutSpeed = i7
                    binding.tvSpeedValue.text = i7.toString()
                    SPUtils.put(SpKeys.SPEED + keyType.value, this.cutSpeed)
                    return
                }
                return
            }

            else -> {}
        }
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        val id: Int = compoundButton.id
        if (id == R.id.rb_100) {
            if (z) {
                ToolSizeManager.setDecoderSize(100)
                return
            }
            return
        }
        if (id == R.id.rb_50) {
            if (z) {
                ToolSizeManager.setDecoderSize(50)
                return
            }
            return
        }
        when (id) {
            R.id.rb_layer_1 -> {
                if (z) {
                    this.layerCut = 1
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 1)
                    return
                }
                return
            }

            R.id.rb_layer_2 -> {
                if (z) {
                    this.layerCut = 2
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 2)
                    return
                }
                return
            }

            R.id.rb_layer_3 -> {
                if (z) {
                    this.layerCut = 3
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 3)
                    return
                }
                return
            }

            else -> {}
        }
    }

    // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    override fun initView() {
        initKeyView()
        initCutter()
        initLayerCut()
        initClamp()
        initCutSpeed()
        initSingleKeyCutDepth()
    }

    private fun initSingleKeyCutDepth() {
        if (Objects.requireNonNull(keyInfo)?.keyType == KeyType.SINGLE_SIDE_KEY) {
            binding.llCutDepthSingleKey.visibility = 0
            binding.tvCutDepthSingleKey.visibility = 0
            binding.tvDepthValueSingleKey.text = (this.cutDepthSingleKey / 100.0f).toString() + "mm"
            return
        }
        binding.llCutDepthSingleKey.visibility = 8
        binding.tvCutDepthSingleKey.visibility = 8
    }

    private val keyInfo: DuplicateKeyData?
        get() {
            val duplicateDecodeParams: DuplicateDecodeParams = this.decodeParams ?: return null
            return duplicateDecodeParams.keyInfo
        }

    private fun initLayerCut() {
        if (keyType == KeyType.DOUBLE_INSIDE_GROOVE_KEY) {
            binding.tvTitleLayer.visibility = 0
            binding.rgLayerCut.visibility = 0
            val i: Int = SPUtils.getInt(SpKeys.DUPLICATE_LAYERCUT, 3)
            if (i == 1) {
                binding.rbLayer1.setChecked(true)
                return
            } else if (i == 2) {
                binding.rbLayer2.setChecked(true)
                return
            } else {
                if (i != 3) {
                    return
                }
                binding.rbLayer3.setChecked(true)
                return
            }
        }
        binding.tvTitleLayer.visibility = 8
        binding.rgLayerCut.visibility = 8
        if ((keyType == KeyType.LEFT_GROOVE) || (keyType == KeyType.RIGHT_GROOVE) || (keyType == KeyType.TWO_GROOVE) || (keyType == KeyType.THREE_GROOVE)) {
            this.layerCut = 1
        }
    }

    private fun initCutter() {
        if (keyInfo?.keyType == KeyType.DOUBLE_SIDE_KEY) {
            this.cutterSize = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200)
        }
        binding.tvCutterSize.text = (this.cutterSize / 100.0f).toString() + "mm"
        if (keyType == KeyType.DOUBLE_SIDE_KEY) {
            binding.tvRemind.visibility = 0
            binding.tvRemind.text = getContext().getString(R.string.recommended_150_milling_cutter)
        } else {
            binding.tvRemind.visibility = 8
        }
    }

    private fun initCutSpeed() {
        val i: Int = SPUtils.getInt(
            SpKeys.SPEED + keyType.getValue(),
            if (keyType == KeyType.DIMPLE_KEY) 3 else 15
        )
        this.cutSpeed = i
        binding.tvSpeedValue.text = i.toString()
    }

    private fun initKeyView() {
        val view: View?
        when (ClampEnum.keyType[keyType.ordinal]) {
            1 -> view = CopySingleSideKey(context, keyInfo)
            2 -> view = CopyDoubleSideKey(context, keyInfo)
            3 -> view = CopySingleOutSideKey(context, keyInfo)
            4 -> view = CopyDoubleOutSideKey(context, keyInfo)
            5, 6, 7, 8, 9 -> view = CopySingleInsideKey(context, keyInfo)
            10 -> {
                binding.flKey.visibility = 8
                binding.flTubular.visibility = 0
                binding.llDepth.removeAllViews()
                val destPointList: List<DestPoint> = keyInfo!!.decodeData.pathDataList[0].destPointList
                var i: Int = 0
                while (i < destPointList.size) {
                    val textView = TextView(context)
                    textView.text = Math.round(
                        destPointList[i].depth * MachineData.dZScale
                    ).toString()
                    textView.setTextColor(
                        ThemeUtils.getColor(
                            context,
                            R.attr.textColor_ffffff_333333
                        )
                    )
                    textView.setGravity(17)
                    textView.textSize = AutoUtils.getPercentHeightSize(20).toFloat()
                    binding.llDepth.addView(
                        textView,
                        LinearLayout.LayoutParams(-1, AutoUtils.getPercentHeightSize(35))
                    )
                    val view2 = View(context)
                    view2.setBackgroundColor(
                        context.resources.getColor(R.color.color_1b1a28)
                    )
                    binding.llDepth.addView(
                        view2,
                        LinearLayout.LayoutParams(-1, AutoUtils.getPercentHeightSize(1))
                    )
                    i++
                }
                view = null
            }

            11 -> view = CopySideHoleKey(context, keyInfo)
            else -> view = null
        }
        if (view != null) {
            binding.flKey.removeAllViews()
            binding.flKey.addView(view)
        }
    }

    private val keyType: KeyType
        get() {
            return keyInfo!!.keyType
        }

    object ClampEnum {
        val keyType: IntArray
        val clamp: IntArray = IntArray(Clamp.entries.size)

        init {

            clamp[Clamp.S1_A.ordinal] = 1

            clamp[Clamp.S1_B.ordinal] = 2

            clamp[Clamp.S1_C.ordinal] = 3

            clamp[Clamp.S1_D.ordinal] = 4

            clamp[Clamp.S2_A.ordinal] = 5

            clamp[Clamp.S2_B.ordinal] = 6

            clamp[Clamp.S3.ordinal] = 7

            clamp[Clamp.E9S1A.ordinal] = 8

            clamp[Clamp.E9S1C.ordinal] = 9

            clamp[Clamp.E9S2A.ordinal] = 10

            clamp[Clamp.E9S2B.ordinal] = 11

            clamp[Clamp.E9S3.ordinal] = 12

            keyType = IntArray(KeyType.entries.size)

            keyType[KeyType.SINGLE_SIDE_KEY.ordinal] = 1

            keyType[KeyType.DOUBLE_SIDE_KEY.ordinal] = 2

            keyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal] =
                3

            keyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal] =
                4

            keyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal] =
                5

            keyType[KeyType.LEFT_GROOVE.ordinal] = 6

            keyType[KeyType.RIGHT_GROOVE.ordinal] = 7

            keyType[KeyType.TWO_GROOVE.ordinal] = 8

            keyType[KeyType.THREE_GROOVE.ordinal] = 9

            keyType[KeyType.TUBULAR_KEY.ordinal] = 10

            keyType[KeyType.SIDE_HOLE.ordinal] = 11
        }
    }

    private fun initClamp() {
        val keyAlign: KeyAlign = keyInfo!!.keyAlign
        when (ClampEnum.clamp[decodeParams!!.clamp.ordinal]) {
            1 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default)
                }
            }

            2 -> if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default)
            } else if (decodeParams.clampMode == 0) {
                binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
            } else {
                binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default)
            }

            3 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default)
                }
            }

            4 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default)
                }
                return
            }

            5 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default)
                }
                return
            }

            6 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default)
                }
                return
            }

            7 -> {
                binding.ivClamp.setImageResource(R.drawable.keycopy_clamp_s3_select)
                return
            }

            8 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9)
                }
                return
            }

            9 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.a9_standard_stop_1_duplicate_e9)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.a9_standard_stop_4_duplicate_e9)
                }
                return
            }

            10 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.single_key_5mm_shoulder_duplicate_e9)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.single_key_5mm_tip_duplicate_e9)
                }
                return
            }

            11 -> {
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    binding.ivClamp.setImageResource(R.drawable.single_key_35mm_shoulder_duplicate_e9)
                } else {
                    binding.ivClamp.setImageResource(R.drawable.single_key_35mm_tip_duplicate_e9)
                }
                return
            }

            12 -> {
                binding.ivClamp.setImageResource(R.drawable.a9_tubular_stop_duplicate_e9)
                return
            }

            else -> {}
        }
    }
}
