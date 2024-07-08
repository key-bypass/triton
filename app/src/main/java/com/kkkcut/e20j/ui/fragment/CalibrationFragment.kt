package com.kkkcut.e20j.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import com.cutting.machine.Command
import com.cutting.machine.CuttingMachine
import com.cutting.machine.MachineInfo
import com.cutting.machine.OperateType
import com.cutting.machine.clamp.Clamp
import com.cutting.machine.clamp.ClampManager
import com.cutting.machine.clamp.MachineData
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorBean
import com.cutting.machine.operation.calibrate.CalibrationParams
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentCalibrationBinding
import com.kkkcut.e20j.utils.ThemeUtils

/* loaded from: classes.dex */
class CalibrationFragment : BaseBackFragment() {
    var calibrationParams: CalibrationParams? = null
    private var isE9: Boolean = false

    var binding: FragmentCalibrationBinding? = null

    private var remindDialog: RemindDialog? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentCalibrationBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_calibration
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        if (MachineInfo.isChineseMachine()) {
            binding!!.rbTubularClamp.visibility = 8
            binding!!.rbMarkingClamp.visibility = 8
            binding!!.btDistanceCalibrateS1a.visibility = 8
            binding!!.rbBlankCutClamp.visibility = 8
            binding!!.rbCrossKeyClamp.visibility = 8
        }
        val isE9Standard: Boolean = MachineInfo.isE9Standard(requireContext())
        this.isE9 = isE9Standard
        if (isE9Standard) {
            binding!!.btDistanceCalibrateS1a.visibility = View.GONE
            binding!!.btCarClampB.visibility = 8
            binding!!.btCarClampD.visibility = 8
            binding!!.rbBlankCutClamp.visibility = 8
            binding!!.rbAngleClamp.visibility = 8
            binding!!.rbCrossKeyClamp.visibility = 8
            binding!!.btDistanceCalibrateS1a.visibility = 8
        }
    }

    fun onViewClicked(view: View) {
        val i: Int
        var string: String = getString(R.string.clean_the_groove_from_chips)
        when (view.id) {
            R.id.bt_angle_clamp, R.id.bt_cross_key_clamp, R.id.bt_tubular_clamp -> {
                showMessage(getString(R.string.does_not_need_to_be_calibrated))
                return
            }

            R.id.bt_blank_cut_calibrate -> if (!haveCalibratedDc()) {
                i = R.drawable.remind_calibration_blank_cut
                this.calibrationParams = CalibrationParams(Clamp.S8)
            } else {
                return
            }

            R.id.bt_car_clamp_a -> if (!haveCalibratedDc()) {
                i = ThemeUtils.getResId(requireContext(), R.attr.remind_calibration_clamp_s1_a)
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S1_A)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S1A)
                }
            } else {
                return
            }

            R.id.bt_car_clamp_b -> if (!haveCalibratedDc()) {
                i = R.drawable.remind_calibration_clamp_s1_b
                this.calibrationParams = CalibrationParams(Clamp.S1_B)
            } else {
                return
            }

            R.id.bt_car_clamp_c -> if (!haveCalibratedDc()) {
                i = ThemeUtils.getResId(requireContext(), R.attr.remind_calibration_clamp_s1_c)
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S1_C)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S1C)
                }
            } else {
                return
            }

            R.id.bt_car_clamp_d -> if (!haveCalibratedDc()) {
                i = R.drawable.remind_calibration_clamp_s1_d
                this.calibrationParams = CalibrationParams(Clamp.S1_D)
            } else {
                return
            }

            R.id.bt_distance_calibrate -> {
                i = ThemeUtils.getResId(requireContext(), R.attr.remind_calibration_carkey_clamp_d)
                string = getString(R.string.dc_calibrate_remind)
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S1_D, true)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S1C, true)
                }
            }

            R.id.bt_distance_calibrate_s1a -> {
                i = R.drawable.remind_calibration_singlekey_clamp_a
                string = getString(R.string.dc_calibrate_remind)
                this.calibrationParams = CalibrationParams(Clamp.S2_A, true)
            }

            R.id.bt_house_clamp_a -> if (!haveCalibratedDc()) {
                i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_singlekey_clamp_a)
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S2_A)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S2A)
                }
            } else {
                return
            }

            R.id.bt_house_clamp_b -> if (!haveCalibratedDc()) {
                i = R.drawable.remind_calibration_singlekey_clamp_b
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S2_B)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S2B)
                }
            } else {
                return
            }

            R.id.bt_left -> {
                showLoadingDialog(getString(R.string.waitting), true)
                OperationManager.getInstance()
                    .sendOrder(Command.DecoderOperation(1, 0, 1, 1, 1, ""), OperateType.MOVE_XYZ)
                return
            }

            R.id.bt_marking_clamp -> if (!haveCalibratedDc()) {
                i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_key_marking)
                if (CuttingMachine.getInstance().isAlpha) {
                    this.calibrationParams = CalibrationParams(Clamp.S5)
                } else {
                    this.calibrationParams = CalibrationParams(Clamp.E9S5)
                }
            } else {
                return
            }

            R.id.bt_off -> {
                CuttingMachine.getInstance().turnOffSpindle()
                return
            }

            R.id.bt_on -> {
                CuttingMachine.getInstance().turnOnSpindle()
                return
            }

            R.id.bt_right -> {
                showLoadingDialog(getString(R.string.waitting), true)
                OperationManager.getInstance().sendOrder(
                    Command.DecoderOperation(
                        1,
                        0,
                        (6300.0f / MachineData.dXScale).toInt(),
                        0,
                        0,
                        ""
                    ), OperateType.MOVE_XYZ
                )
                return
            }

            else -> i = 0
        }
        showRemindDialog(requireContext(), string, i)
    }

    private fun haveCalibratedDc(): Boolean {
        if (AppUtil.isApkInDebug(requireContext()) || ClampManager.getInstance().getDC() != null) {
            return false
        }
        Toast.makeText(requireContext(), R.string.please_complete_the_first_step, 0).show()
        return true
    }

    private fun showRemindDialog(context: Context?, str: String, i: Int) {
        if (this.remindDialog == null) {
            this.remindDialog = RemindDialog(context)
        }

        remindDialog!!.setDialogBtnCallback { z: Boolean ->
            if (z) {
                this.startCalibrate()
            }
        }
        remindDialog!!.setRemindMsg(str)
        remindDialog!!.setRemindImg(i)
        if (remindDialog!!.isShowing) {
            return
        }
        remindDialog!!.show()
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun startCalibrate() {
        showLoadingDialog(getString(R.string.calibrating), true)
        CuttingMachine.getInstance().calibrateClamp(this.calibrationParams)
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.calibration)
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.id) {
            R.id.rb_angle_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = 8
                    binding!!.llCarClamp.visibility = 8
                    binding!!.llAngleKeyClamp.visibility = 0
                    binding!!.llTubularClamp.visibility = 8
                    binding!!.llSingleSideClamp.visibility = 8
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 8
                    return
                }
                return
            }

            R.id.rb_autonmobile_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = 8
                    binding!!.llCarClamp.visibility = 0
                    binding!!.llAngleKeyClamp.visibility = 8
                    binding!!.llTubularClamp.visibility = 8
                    binding!!.llSingleSideClamp.visibility = 8
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 8
                    return
                }
                return
            }

            R.id.rb_blank_cut_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = 8
                    binding!!.llCarClamp.visibility = 8
                    binding!!.llAngleKeyClamp.visibility = 8
                    binding!!.llTubularClamp.visibility = 8
                    binding!!.llSingleSideClamp.visibility = 8
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 0
                    return
                }
                return
            }

            R.id.rb_cross_key_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = View.GONE
                    binding!!.llCarClamp.visibility = View.GONE
                    binding!!.llAngleKeyClamp.visibility = View.GONE
                    binding!!.llTubularClamp.visibility = View.GONE
                    binding!!.llSingleSideClamp.visibility = View.GONE
                    binding!!.llMarkingClamp.visibility = View.GONE
                    binding!!.llCrossKeyClamp.visibility = View.VISIBLE
                    binding!!.llBlankCutCalibrate.visibility = View.GONE
                    return
                }
                return
            }

            R.id.rb_distance_calibrate -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = View.VISIBLE
                    binding!!.llCarClamp.visibility = View.GONE
                    binding!!.llAngleKeyClamp.visibility = View.GONE
                    binding!!.llTubularClamp.visibility = 8
                    binding!!.llSingleSideClamp.visibility = 8
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 8
                    return
                }
                return
            }

            R.id.rb_marking_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.setVisibility(8)
                    binding!!.llCarClamp.setVisibility(8)
                    binding!!.llAngleKeyClamp.setVisibility(8)
                    binding!!.llTubularClamp.setVisibility(8)
                    binding!!.llSingleSideClamp.setVisibility(8)
                    binding!!.llMarkingClamp.setVisibility(0)
                    binding!!.llCrossKeyClamp.setVisibility(8)
                    binding!!.llBlankCutCalibrate.setVisibility(8)
                    return
                }
                return
            }

            R.id.rb_single_side_clmap -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = 8
                    binding!!.llCarClamp.visibility = 8
                    binding!!.llAngleKeyClamp.visibility = 8
                    binding!!.llTubularClamp.visibility = 8
                    binding!!.llSingleSideClamp.visibility = 0
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 8
                    return
                }
                return
            }

            R.id.rb_tubular_clamp -> {
                if (z) {
                    binding!!.llDistanceCalibrate.visibility = 8
                    binding!!.llCarClamp.visibility = 8
                    binding!!.llAngleKeyClamp.visibility = 8
                    binding!!.llTubularClamp.visibility = 0
                    binding!!.llSingleSideClamp.visibility = 8
                    binding!!.llMarkingClamp.visibility = 8
                    binding!!.llCrossKeyClamp.visibility = 8
                    binding!!.llBlankCutCalibrate.visibility = 8
                    return
                }
                return
            }

            else -> return
        }
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (isVisible) {
            val eventCode: Int = eventCenter.eventCode
            if (eventCode == 7) {
                dismissLoadingDialog()
                return
            }
            if (eventCode == 48) {
                dismissLoadingDialog()
                showMessage(getString(R.string.calibration_successful))
                return
            }
            if (eventCode != 50) {
                when (eventCode) {
                    32 -> {
                        if ((eventCenter.getData() as OperateType) == OperateType.MOVE_XYZ) {
                            dismissLoadingDialog()
                            return
                        }
                        return
                    }

                    33 -> {
                        dismissLoadingDialog()
                        showErrorDialog(getContext(), eventCenter.data as ErrorBean?)
                        return
                    }

                    34 -> {
                        showLoadingDialog(getString(R.string.waitting))
                        return
                    }

                    else -> return
                }
            }
            putDownDecoderRemindDialog()
        }
    }

    fun putDownDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9) {
            val remindDialog = RemindDialog(getContext())
            remindDialog.setRemindImg(R.drawable.pull_decoder_down)
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down))
            remindDialog.setDialogBtnCallback { z ->
                if (z) {
                    this.startCalibrate()
                } else {
                    this.dismissLoadingDialog()
                }
            }
            remindDialog.show()
        }
    }

    private fun showMessage(str: String) {
        val warningDialog = WarningDialog(getContext())
        warningDialog.setRemind(str)
        warningDialog.show()
    }

    companion object {
        val TAG: String = "CalibrationFragment"

        fun newInstance(): CalibrationFragment {
            return CalibrationFragment()
        }
    }
}
