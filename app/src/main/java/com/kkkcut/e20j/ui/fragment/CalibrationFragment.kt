package com.kkkcut.e20j.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.us.databinding.FragmentCalibrationBinding;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.cutting.machine.Command;
import com.cutting.machine.CuttingMachine;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.error.ErrorBean;
import com.cutting.machine.operation.calibrate.CalibrationParams;

/* loaded from: classes.dex */
public class CalibrationFragment extends BaseBackFragment {
    public static final String TAG = "CalibrationFragment";


    CalibrationParams calibrationParams;
    private boolean isE9;

    FragmentCalibrationBinding binding;

    private RemindDialog remindDialog;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        this.binding = FragmentCalibrationBinding.inflate(layoutInflater, viewGroup, false);
        return this.binding.getRoot();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_calibration;
    }

    public static CalibrationFragment newInstance() {
        return new CalibrationFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        if (MachineInfo.isChineseMachine()) {
            this.binding.rbTubularClamp.setVisibility(8);
            this.binding.rbMarkingClamp.setVisibility(8);
            this.binding.btDistanceCalibrateS1a.setVisibility(8);
            this.binding.rbBlankCutClamp.setVisibility(8);
            this.binding.rbCrossKeyClamp.setVisibility(8);
        }
        boolean isE9Standard = MachineInfo.isE9Standard(getContext());
        this.isE9 = isE9Standard;
        if (isE9Standard) {
            this.binding.btDistanceCalibrateS1a.setVisibility(8);
            this.binding.btCarClampB.setVisibility(8);
            this.binding.btCarClampD.setVisibility(8);
            this.binding.rbBlankCutClamp.setVisibility(8);
            this.binding.rbAngleClamp.setVisibility(8);
            this.binding.rbCrossKeyClamp.setVisibility(8);
            this.binding.btDistanceCalibrateS1a.setVisibility(8);
        }
    }

    public void onViewClicked(View view) {
        int i;
        String string = getString(R.string.clean_the_groove_from_chips);
        switch (view.getId()) {
            case R.id.bt_angle_clamp /* 2131361905 */:
            case R.id.bt_cross_key_clamp /* 2131361920 */:
            case R.id.bt_tubular_clamp /* 2131361978 */:
                showMessage(getString(R.string.does_not_need_to_be_calibrated));
                return;
            case R.id.bt_blank_cut_calibrate /* 2131361907 */:
                if (!haveCalibratedDc()) {
                    i = R.drawable.remind_calibration_blank_cut;
                    this.calibrationParams = new CalibrationParams(Clamp.S8);
                    break;
                } else {
                    return;
                }
            case R.id.bt_car_clamp_a /* 2131361910 */:
                if (!haveCalibratedDc()) {
                    i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_clamp_s1_a);
                    if (CuttingMachine.getInstance().isAlpha()) {
                        this.calibrationParams = new CalibrationParams(Clamp.S1_A);
                        break;
                    } else {
                        this.calibrationParams = new CalibrationParams(Clamp.E9S1A);
                        break;
                    }
                } else {
                    return;
                }
            case R.id.bt_car_clamp_b /* 2131361911 */:
                if (!haveCalibratedDc()) {
                    i = R.drawable.remind_calibration_clamp_s1_b;
                    this.calibrationParams = new CalibrationParams(Clamp.S1_B);
                    break;
                } else {
                    return;
                }
            case R.id.bt_car_clamp_c /* 2131361912 */:
                if (!haveCalibratedDc()) {
                    i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_clamp_s1_c);
                    if (CuttingMachine.getInstance().isAlpha()) {
                        this.calibrationParams = new CalibrationParams(Clamp.S1_C);
                        break;
                    } else {
                        this.calibrationParams = new CalibrationParams(Clamp.E9S1C);
                        break;
                    }
                } else {
                    return;
                }
            case R.id.bt_car_clamp_d /* 2131361913 */:
                if (!haveCalibratedDc()) {
                    i = R.drawable.remind_calibration_clamp_s1_d;
                    this.calibrationParams = new CalibrationParams(Clamp.S1_D);
                    break;
                } else {
                    return;
                }
            case R.id.bt_distance_calibrate /* 2131361927 */:
                i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_carkey_clamp_d);
                string = getString(R.string.dc_calibrate_remind);
                if (CuttingMachine.getInstance().isAlpha()) {
                    this.calibrationParams = new CalibrationParams(Clamp.S1_D, true);
                    break;
                } else {
                    this.calibrationParams = new CalibrationParams(Clamp.E9S1C, true);
                    break;
                }
            case R.id.bt_distance_calibrate_s1a /* 2131361928 */:
                i = R.drawable.remind_calibration_singlekey_clamp_a;
                string = getString(R.string.dc_calibrate_remind);
                this.calibrationParams = new CalibrationParams(Clamp.S2_A, true);
                break;
            case R.id.bt_house_clamp_a /* 2131361935 */:
                if (!haveCalibratedDc()) {
                    i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_singlekey_clamp_a);
                    if (CuttingMachine.getInstance().isAlpha()) {
                        this.calibrationParams = new CalibrationParams(Clamp.S2_A);
                        break;
                    } else {
                        this.calibrationParams = new CalibrationParams(Clamp.E9S2A);
                        break;
                    }
                } else {
                    return;
                }
            case R.id.bt_house_clamp_b /* 2131361936 */:
                if (!haveCalibratedDc()) {
                    i = R.drawable.remind_calibration_singlekey_clamp_b;
                    if (CuttingMachine.getInstance().isAlpha()) {
                        this.calibrationParams = new CalibrationParams(Clamp.S2_B);
                        break;
                    } else {
                        this.calibrationParams = new CalibrationParams(Clamp.E9S2B);
                        break;
                    }
                } else {
                    return;
                }
            case R.id.bt_left /* 2131361942 */:
                showLoadingDialog(getString(R.string.waitting), true);
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, 1, 1, 1, ""), OperateType.MOVE_XYZ);
                return;
            case R.id.bt_marking_clamp /* 2131361946 */:
                if (!haveCalibratedDc()) {
                    i = ThemeUtils.getResId(getContext(), R.attr.remind_calibration_key_marking);
                    if (CuttingMachine.getInstance().isAlpha()) {
                        this.calibrationParams = new CalibrationParams(Clamp.S5);
                        break;
                    } else {
                        this.calibrationParams = new CalibrationParams(Clamp.E9S5);
                        break;
                    }
                } else {
                    return;
                }
            case R.id.bt_off /* 2131361961 */:
                CuttingMachine.getInstance().turnOffSpindle();
                return;
            case R.id.bt_on /* 2131361963 */:
                CuttingMachine.getInstance().turnOnSpindle();
                return;
            case R.id.bt_right /* 2131361966 */:
                showLoadingDialog(getString(R.string.waitting), true);
                OperationManager.getInstance().sendOrder(Command.DecoderOperation(1, 0, (int) (6300.0f / MachineData.dXScale), 0, 0, ""), OperateType.MOVE_XYZ);
                return;
            default:
                i = 0;
                break;
        }
        showRemindDialog(getContext(), string, i);
    }

    private boolean haveCalibratedDc() {
        if (AppUtil.isApkInDebug(getContext()) || ClampManager.getInstance().getDC() != null) {
            return false;
        }
        Toast.makeText(getContext(), R.string.please_complete_the_first_step, 0).show();
        return true;
    }

    private void showRemindDialog(Context context, String str, int i) {
        if (this.remindDialog == null) {
            this.remindDialog = new RemindDialog(context);
        }
        this.remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment.1
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public void onDialogButClick(boolean z) {
                if (z) {
                    CalibrationFragment.this.startCalibrate();
                }
            }
        });
        this.remindDialog.setRemindMsg(str);
        this.remindDialog.setRemindImg(i);
        if (this.remindDialog.isShowing()) {
            return;
        }
        this.remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCalibrate() {
        showLoadingDialog(getString(R.string.calibrating), true);
        CuttingMachine.getInstance().calibrateClamp(this.calibrationParams);
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.calibration);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_angle_clamp /* 2131362599 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(0);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_autonmobile_clamp /* 2131362600 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(0);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_blank_cut_clamp /* 2131362601 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(0);
                    return;
                }
                return;
            case R.id.rb_cross_key_clamp /* 2131362605 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(0);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_distance_calibrate /* 2131362608 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(0);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_marking_clamp /* 2131362629 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(0);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_single_side_clmap /* 2131362653 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(8);
                    this.binding.llSingleSideClamp.setVisibility(0);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_tubular_clamp /* 2131362663 */:
                if (z) {
                    this.binding.llDistanceCalibrate.setVisibility(8);
                    this.binding.llCarClamp.setVisibility(8);
                    this.binding.llAngleKeyClamp.setVisibility(8);
                    this.binding.llTubularClamp.setVisibility(0);
                    this.binding.llSingleSideClamp.setVisibility(8);
                    this.binding.llMarkingClamp.setVisibility(8);
                    this.binding.llCrossKeyClamp.setVisibility(8);
                    this.binding.llBlankCutCalibrate.setVisibility(8);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (isVisible()) {
            int eventCode = eventCenter.getEventCode();
            if (eventCode == 7) {
                dismissLoadingDialog();
                return;
            }
            if (eventCode == 48) {
                dismissLoadingDialog();
                showMessage(getString(R.string.calibration_successful));
                return;
            }
            if (eventCode != 50) {
                switch (eventCode) {
                    case 32:
                        if (((OperateType) eventCenter.getData()) == OperateType.MOVE_XYZ) {
                            dismissLoadingDialog();
                            return;
                        }
                        return;
                    case 33:
                        dismissLoadingDialog();
                        showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
                        return;
                    case 34:
                        showLoadingDialog(getString(R.string.waitting));
                        return;
                    default:
                        return;
                }
            }
            putDownDecoderRemindDialog();
        }
    }

    public void putDownDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.pull_decoder_down);
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment.2
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        CalibrationFragment.this.startCalibrate();
                    } else {
                        CalibrationFragment.this.dismissLoadingDialog();
                    }
                }
            });
            remindDialog.show();
        }
    }

    private void showMessage(String str) {
        WarningDialog warningDialog = new WarningDialog(getContext());
        warningDialog.setRemind(str);
        warningDialog.show();
    }
}
