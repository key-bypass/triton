package com.kkkcut.e20j.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.MachineInfo;
import com.liying.core.OperateType;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorBean;
import com.liying.core.operation.calibrate.CalibrationParams;

/* loaded from: classes.dex */
public class CalibrationFragment extends BaseBackFragment {
    public static final String TAG = "CalibrationFragment";

    @BindView(R.id.bt_car_clamp_b)
    Button btCarClampB;

    @BindView(R.id.bt_car_clamp_d)
    Button btCarClampD;

    @BindView(R.id.bt_distance_calibrate_s1a)
    Button btDistanceCalibrateS1a;

    @BindView(R.id.bt_house_clamp_b)
    Button btSingleKeyClampB;
    CalibrationParams calibrationParams;
    private boolean isE9;

    @BindView(R.id.ll_angle_key_clamp)
    LinearLayout llAngleKeyClamp;

    @BindView(R.id.ll_blank_cut_calibrate)
    LinearLayout llBlankCutClamp;

    @BindView(R.id.ll_car_clamp)
    LinearLayout llCarClamp;

    @BindView(R.id.ll_cross_key_clamp)
    LinearLayout llCroossKeyClamp;

    @BindView(R.id.ll_distance_calibrate)
    LinearLayout llDistanceCalibrate;

    @BindView(R.id.ll_marking_clamp)
    LinearLayout llMarkingClamp;

    @BindView(R.id.ll_single_side_clamp)
    LinearLayout llSingleSideClamp;

    @BindView(R.id.ll_tubular_clamp)
    LinearLayout llTubularClamp;

    @BindView(R.id.rb_angle_clamp)
    RadioButton rbAngleClamp;

    @BindView(R.id.rb_blank_cut_clamp)
    RadioButton rbBlankCutClamp;

    @BindView(R.id.rb_cross_key_clamp)
    RadioButton rbCrossKeyClamp;

    @BindView(R.id.rb_marking_clamp)
    RadioButton rbMarkingClamp;

    @BindView(R.id.rb_single_side_clmap)
    RadioButton rbSingleSideClamp;

    @BindView(R.id.rb_tubular_clamp)
    RadioButton rbTubularClamp;
    private RemindDialog remindDialog;

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
            this.rbTubularClamp.setVisibility(8);
            this.rbMarkingClamp.setVisibility(8);
            this.btDistanceCalibrateS1a.setVisibility(8);
            this.rbBlankCutClamp.setVisibility(8);
            this.rbCrossKeyClamp.setVisibility(8);
        }
        boolean isE9Standard = MachineInfo.isE9Standard(getContext());
        this.isE9 = isE9Standard;
        if (isE9Standard) {
            this.btDistanceCalibrateS1a.setVisibility(8);
            this.btCarClampB.setVisibility(8);
            this.btCarClampD.setVisibility(8);
            this.rbBlankCutClamp.setVisibility(8);
            this.rbAngleClamp.setVisibility(8);
            this.rbCrossKeyClamp.setVisibility(8);
            this.btSingleKeyClampB.setVisibility(8);
        }
    }

    @OnClick({R.id.bt_car_clamp_a, R.id.bt_car_clamp_b, R.id.bt_car_clamp_c, R.id.bt_car_clamp_d, R.id.bt_distance_calibrate_s1a, R.id.bt_tubular_clamp, R.id.bt_house_clamp_a, R.id.bt_house_clamp_b, R.id.bt_angle_clamp, R.id.bt_distance_calibrate, R.id.bt_marking_clamp, R.id.bt_left, R.id.bt_right, R.id.bt_cross_key_clamp, R.id.bt_blank_cut_calibrate, R.id.bt_on, R.id.bt_off})
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

    @OnCheckedChanged({R.id.rb_distance_calibrate, R.id.rb_autonmobile_clamp, R.id.rb_single_side_clmap, R.id.rb_tubular_clamp, R.id.rb_angle_clamp, R.id.rb_marking_clamp, R.id.rb_cross_key_clamp, R.id.rb_blank_cut_clamp})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.rb_angle_clamp /* 2131362599 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(0);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_autonmobile_clamp /* 2131362600 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(0);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_blank_cut_clamp /* 2131362601 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(0);
                    return;
                }
                return;
            case R.id.rb_cross_key_clamp /* 2131362605 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(0);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_distance_calibrate /* 2131362608 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(0);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_marking_clamp /* 2131362629 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(0);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_single_side_clmap /* 2131362653 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(8);
                    this.llSingleSideClamp.setVisibility(0);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
                    return;
                }
                return;
            case R.id.rb_tubular_clamp /* 2131362663 */:
                if (z) {
                    this.llDistanceCalibrate.setVisibility(8);
                    this.llCarClamp.setVisibility(8);
                    this.llAngleKeyClamp.setVisibility(8);
                    this.llTubularClamp.setVisibility(0);
                    this.llSingleSideClamp.setVisibility(8);
                    this.llMarkingClamp.setVisibility(8);
                    this.llCroossKeyClamp.setVisibility(8);
                    this.llBlankCutClamp.setVisibility(8);
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
