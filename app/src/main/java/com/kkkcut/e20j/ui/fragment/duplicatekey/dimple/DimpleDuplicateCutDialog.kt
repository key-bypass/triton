package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.error.ErrorCode;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class DimpleDuplicateCutDialog extends BottomInDialog {
    private static final String TAG = "CutDialog";

    Button bt15mm;

    Button bt20mm;

    Button bt25mm;

    CheckBox cbFast;

    CheckBox cbPlasticKey;
    private int cutDepth;
    private int cutSpeed;
    private int cutter_size;

    ImageView ivClamp;

    ImageView ivCutter;

    ImageView ivDepth;
    private KeyInfo ki;

    LinearLayout llCutDepth;

    LinearLayout llCutSpeed;

    LinearLayout llCutterSize;

    LinearLayout llPlasticKey;

    RadioButton rbLayer1;

    RadioButton rbLayer2;

    RadioButton rbLayer3;

    RadioButton rbShapeGentle;

    RadioButton rbShapeJagged;

    RadioGroup rgCutShape;

    RadioGroup rgLayerCut;

    TextView tvCutDepth;

    TextView tvCutShape;

    TextView tvCutSpeed;

    TextView tvCutterSize;

    TextView tvCutterSizeRemind;

    TextView tvDepthValue;

    TextView tvLayerCut;

    TextView tvSpeedValue;

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.dialog_cut;
    }

    public DimpleDuplicateCutDialog(Activity activity, KeyInfo keyInfo) {
        super(activity);
        this.ki = keyInfo;
    }

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public void initView() {
        initClamp();
        initCutter();
        initCutDepth();
        initLayerCut();
        initCutShape();
        initCutSpeed();
        initPlasticKey();
        initFastMode();
    }

    private void initFastMode() {
        if (this.ki.isQuickCut()) {
            this.cbFast.setChecked(true);
        }
    }

    private void initCutSpeed() {
        int i = SPUtils.getInt(SpKeys.SPEED + this.ki.getType(), this.ki.getType() == 6 ? 3 : 15);
        this.cutSpeed = i;
        this.tvSpeedValue.setText(String.valueOf(i));
    }

    private void initPlasticKey() {
        if (this.ki.getAlign() == 0 && this.ki.getLength() == 0) {
            this.llPlasticKey.setVisibility(8);
            return;
        }
        if (this.ki.getType() == 5 || this.ki.getType() == 3 || this.ki.getType() == 2 || this.ki.getType() == 4) {
            this.llPlasticKey.setVisibility(0);
            if (this.ki.isPlasticKey()) {
                this.cbPlasticKey.performClick();
                return;
            }
            return;
        }
        this.llPlasticKey.setVisibility(8);
    }

    private void initLayerCut() {
        if (this.ki.getType() == 5 || this.ki.getType() == 2) {
            this.tvLayerCut.setVisibility(0);
            this.rgLayerCut.setVisibility(0);
            int i = SPUtils.getInt(SpKeys.LAYERCUT, 3);
            if (i == 1) {
                this.rbLayer1.setChecked(true);
                return;
            } else if (i == 2) {
                this.rbLayer2.setChecked(true);
                return;
            } else {
                if (i != 3) {
                    return;
                }
                this.rbLayer3.setChecked(true);
                return;
            }
        }
        this.tvLayerCut.setVisibility(8);
        this.rgLayerCut.setVisibility(8);
    }

    private void initCutDepth() {
        this.cutDepth = this.ki.getCutDepth();
        if (this.ki.getType() == 5 || this.ki.getType() == 3 || this.ki.getType() == 4 || this.ki.getType() == 2) {
            this.llCutDepth.setVisibility(0);
            this.tvCutDepth.setVisibility(0);
            if (this.ki.getCutDepth() == 0) {
                this.cutDepth = 110;
            } else {
                this.cutDepth = this.ki.getCutDepth();
            }
            this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
            return;
        }
        this.llCutDepth.setVisibility(8);
        this.tvCutDepth.setVisibility(8);
    }

    private void initCutShape() {
        if (this.ki.getType() == 1 && this.ki.getCutDepth() == 0) {
            this.tvCutShape.setVisibility(0);
            this.rgCutShape.setVisibility(0);
            int i = SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1);
            if (i == 1) {
                this.rbShapeGentle.setChecked(true);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                this.rbShapeJagged.setChecked(true);
                return;
            }
        }
        this.tvCutShape.setVisibility(8);
        this.rgCutShape.setVisibility(8);
    }

    private void initCutter() {
        if (this.ki.getType() == 6) {
            if (this.ki.getSpaceWidthStr().contains("-")) {
                this.ivCutter.setImageResource(R.drawable.cutter_dimple_2);
            } else {
                this.ivCutter.setImageResource(R.drawable.cutter_dimple_1);
            }
            this.llCutterSize.setVisibility(8);
            this.bt15mm.setVisibility(8);
            this.bt20mm.setVisibility(8);
            this.bt25mm.setVisibility(8);
            ToolSizeManager.setCutterSize(ToolSizeManager.DimpleCutterSize);
        } else {
            this.ivCutter.setImageResource(R.drawable.cutter_normal);
        }
        int cutterSize = ToolSizeManager.getCutterSize();
        Log.i(TAG, "initCutter: " + cutterSize);
        String param = SpecificParamUtils.getParam(this.ki.getType_specific_info(), "cutter");
        if (!TextUtils.isEmpty(param)) {
            if (param.contains(",")) {
                String[] split = param.split(",");
                if (split.length > 0) {
                    param = split[1];
                }
            }
            this.tvCutterSizeRemind.setVisibility(0);
            this.tvCutterSizeRemind.setText(String.format(getActivity().getResources().getString(R.string.please_use_smm_milling_cutter), param));
        }
        if (cutterSize > 0) {
            this.cutter_size = cutterSize;
        } else if (this.ki.getType() == 5 || this.ki.getType() == 2) {
            if (this.ki.getGroove() != 0 && this.ki.getGroove() < 200) {
                this.cutter_size = (this.ki.getGroove() / 10) * 10;
            } else {
                this.cutter_size = 200;
            }
        } else if (this.ki.getType() == 6) {
            this.cutter_size = 100;
        } else if (this.ki.getType() == 0) {
            this.cutter_size = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200);
        } else {
            this.cutter_size = 200;
        }
        this.tvCutterSize.setText(String.format("%.1fmm", Float.valueOf(this.cutter_size / 100.0f)));
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1_5mm /* 2131361901 */:
                this.cutter_size = 150;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_0mm /* 2131361902 */:
                this.cutter_size = 200;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_5mm /* 2131361903 */:
                this.cutter_size = 250;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_cancle /* 2131361909 */:
                dismiss();
                return;
            case R.id.bt_cut /* 2131361921 */:
                if (this.ki.getType() == 6) {
                    if (this.ki.getSpaceWidthStr().contains("-")) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("cutDepth", this.cutDepth);
                        bundle.putInt("cutterSize", this.cutter_size);
                        bundle.putBoolean("plastic_key", this.cbPlasticKey.isChecked());
                        bundle.putBoolean("quick_cut", this.cbFast.isChecked());
                        EventBus.getDefault().post(new EventCenter(1, bundle));
                        return;
                    }
                    RemindDialog remindDialog = new RemindDialog(getContext());
                    remindDialog.setRemindImg(R.drawable.remind_cutter_dimple_1);
                    remindDialog.setCancleBtnVisibility(false);
                    remindDialog.setRemindMsg(getContext().getString(R.string.please_install_specified_milling_cutter));
                    remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.1
                        @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                        public void onDialogButClick(boolean z) {
                            if (z) {
                                Bundle bundle2 = new Bundle();
                                bundle2.putInt("cutDepth", DimpleDuplicateCutDialog.this.cutDepth);
                                bundle2.putInt("cutterSize", DimpleDuplicateCutDialog.this.cutter_size);
                                bundle2.putBoolean("plastic_key", DimpleDuplicateCutDialog.this.cbPlasticKey.isChecked());
                                bundle2.putBoolean("quick_cut", DimpleDuplicateCutDialog.this.cbFast.isChecked());
                                EventBus.getDefault().post(new EventCenter(1, bundle2));
                            }
                        }
                    });
                    remindDialog.show();
                    return;
                }
                WarningDialog warningDialog = new WarningDialog(getContext());
                warningDialog.show();
                warningDialog.setRemind(getContext().getString(R.string.please_use_specify_cutter, new Object[]{(this.cutter_size / 100.0f) + "mm"}));
                warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.2
                    @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("cutDepth", DimpleDuplicateCutDialog.this.cutDepth);
                            bundle2.putInt("cutterSize", DimpleDuplicateCutDialog.this.cutter_size);
                            bundle2.putBoolean("plastic_key", DimpleDuplicateCutDialog.this.cbPlasticKey.isChecked());
                            bundle2.putBoolean("quick_cut", DimpleDuplicateCutDialog.this.cbFast.isChecked());
                            EventBus.getDefault().post(new EventCenter(1, bundle2));
                            DimpleDuplicateCutDialog.this.dismiss();
                        }
                    }
                });
                return;
            case R.id.cb_fast /* 2131362024 */:
                if (this.cbFast.isChecked()) {
                    WarningDialog warningDialog2 = new WarningDialog(getContext());
                    warningDialog2.setRemind(getContext().getString(R.string.quick_cut_remind));
                    warningDialog2.setCancelText(getContext().getString(R.string.cancel));
                    warningDialog2.setConfirmText(getContext().getString(R.string.continue1));
                    warningDialog2.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog.3
                        @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                        public void onDialogButClick(boolean z) {
                            if (z) {
                                return;
                            }
                            DimpleDuplicateCutDialog.this.cbFast.setChecked(false);
                        }
                    });
                    warningDialog2.show();
                    return;
                }
                return;
            case R.id.iv_close /* 2131362287 */:
                dismiss();
                return;
            case R.id.iv_depth_add /* 2131362293 */:
                this.cutDepth += 5;
                this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                return;
            case R.id.iv_depth_reduce /* 2131362295 */:
                int i = this.cutDepth;
                if (i > 5) {
                    this.cutDepth = i - 5;
                }
                this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                return;
            case R.id.iv_size_add /* 2131362335 */:
                int i2 = this.cutter_size;
                if (i2 < 250) {
                    this.cutter_size = i2 + 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i2 < 500) {
                    this.cutter_size = i2 + 50;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_reduce /* 2131362336 */:
                int i3 = this.cutter_size;
                if (i3 > 250) {
                    this.cutter_size = i3 - 50;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i3 > 100) {
                    this.cutter_size = i3 - 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_speed_add /* 2131362345 */:
                if (this.cutSpeed < (this.ki.getType() != 6 ? 25 : 5)) {
                    int i4 = this.cutSpeed + 1;
                    this.cutSpeed = i4;
                    this.tvSpeedValue.setText(String.valueOf(i4));
                    SPUtils.put(SpKeys.SPEED + this.ki.getType(), this.cutSpeed);
                    return;
                }
                return;
            case R.id.iv_speed_reduce /* 2131362346 */:
                int i5 = this.cutSpeed;
                if (i5 > 1) {
                    int i6 = i5 - 1;
                    this.cutSpeed = i6;
                    this.tvSpeedValue.setText(String.valueOf(i6));
                    SPUtils.put(SpKeys.SPEED + this.ki.getType(), this.cutSpeed);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initClamp() {
        int clampImg = getClampImg(this.ki);
        if (clampImg != 0) {
            this.ivClamp.setImageResource(clampImg);
        }
    }

    private int getClampImg(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915) {
            return R.drawable.car_clamp_remind_d_tip_20131;
        }
        if (keyInfo.getShoulderBlock() == 1) {
            return R.drawable.car_clamp_remind_d_shoulder_6620131;
        }
        ClampBean clampBean = keyInfo.getClampBean();
        if ("S1".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                if ("0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_a_shoulder : R.drawable.car_clamp_remind_a_tip;
                }
            } else {
                if ("B".equals(clampBean.getClampSide())) {
                    return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_b_shoulder : R.drawable.car_clamp_remind_b_tip : keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_b_shoulder_side : R.drawable.car_clamp_remind_b_tip_side;
                }
                if ("C".equals(clampBean.getClampSide())) {
                    if ("0".equals(clampBean.getClampSlot())) {
                        return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_c_shoulder : Integer.parseInt(keyInfo.getSpaceStr().split(";")[0].split(",")[0]) + ErrorCode.keyCuttingError > 3000 ? R.drawable.car_clamp_remind_c_long_tip : R.drawable.car_clamp_remind_c_tip;
                    }
                } else if ("D".equals(clampBean.getClampSide()) && "0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_d_shoulder : keyInfo.isNewHonda() ? R.drawable.car_clamp_remind_d_tip_honda : R.drawable.car_clamp_remind_d_tip;
                }
            }
        } else {
            if ("S2".equals(clampBean.getClampNum())) {
                return "A".equals(clampBean.getClampSide()) ? keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_remind_a_shoulder : R.drawable.singlekey_clamp_remind_a_tip : keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_remind_b_shoulder : keyInfo.isNewHonda() ? R.drawable.singlekey_clamp_remind_b_tip_honda : R.drawable.singlekey_clamp_remind_b_tip;
            }
            if ("S3".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return R.drawable.tubular_clamp_remind_s3_s7;
                }
            } else if ("S4".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return R.drawable.angel_key_clamp_remind;
                }
            } else if ("S6".equals(clampBean.getClampNum())) {
                return "A".equals(clampBean.getClampSide()) ? R.drawable.sx9_clamp_remind_a : R.drawable.sx9_clamp_remind_b;
            }
        }
        return 0;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.cb_plastic_key /* 2131362029 */:
                if (z && !this.ki.isPlasticKey()) {
                    WarningDialog warningDialog = new WarningDialog(getContext());
                    warningDialog.show();
                    warningDialog.setRemind(getContext().getString(R.string.risk_sticking_breaking_milling_cutter));
                }
                if (this.ki.getAlign() == 1) {
                    if (z) {
                        if ("B".equals(this.ki.getClampBean().getClampSide())) {
                            this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip_plastic);
                            return;
                        } else {
                            this.ivClamp.setImageResource(R.drawable.car_clamp_remind_a_tip_plastic);
                            return;
                        }
                    }
                    this.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip);
                    return;
                }
                if (this.ki.getIcCard() == 909) {
                    if (z) {
                        this.cutDepth = 100;
                    } else {
                        this.cutDepth = 115;
                    }
                    this.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.rb_layer_1 /* 2131362625 */:
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 1);
                    return;
                }
                return;
            case R.id.rb_layer_2 /* 2131362626 */:
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 2);
                    return;
                }
                return;
            case R.id.rb_layer_3 /* 2131362627 */:
                if (z) {
                    SPUtils.put(SpKeys.LAYERCUT, 3);
                    return;
                }
                return;
            case R.id.rb_shape_gentle /* 2131362641 */:
                if (z) {
                    SPUtils.put(SpKeys.SINGLEKEY_CUT_SHAPE, 1);
                    return;
                }
                return;
            case R.id.rb_shape_jagged /* 2131362642 */:
                if (z) {
                    SPUtils.put(SpKeys.SINGLEKEY_CUT_SHAPE, 2);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
