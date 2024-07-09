package com.kkkcut.e20j.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.ui.fragment.clampswitch.ClampCreator;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.us.databinding.DialogCutBinding;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.clamp.ClampUtil;
import com.cutting.machine.operation.cut.DataParam;
import com.spl.key.SpecificParamUtils;

import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class CutDialog extends BottomInDialog {
    private static final String TAG = "CutDialog";

    DialogCutBinding binding;
    private int cutDepth;
    private int cutDepthSingleKey;
    private int cutSpeed;
    private int cutter_size;
    private DataParam dataParam;
    private int decoderSize;
    private boolean dimpleDuplicate;



    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.dialog_cut;
    }

    public CutDialog(Activity activity, DataParam dataParam) {
        super(activity);
        this.binding = DialogCutBinding.inflate(activity.getLayoutInflater());
        this.dataParam = dataParam;
    }

    public CutDialog(Activity activity, DataParam dataParam, boolean z) {
        super(activity);
        this.dataParam = dataParam;
        this.dimpleDuplicate = z;
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
        initDimpleCutMode();
        initHighHandleMode();
        initSingleKeyCutDepth();
        initDecoder();
        initHu64SlantCorrection();
        initDimpleDepth();
        initHu101Length();
    }

    private void initHu101Length() {
        if (getKeyInfo().getType() == 3) {
            this.binding.llHu101Length.setVisibility(0);
            this.binding.tvTitleHu101.setVisibility(0);
            this.binding.tvLengthValue.setText(String.valueOf(this.dataParam.getHu101ExtCutFix()));
        } else {
            this.binding.llHu101Length.setVisibility(8);
            this.binding.tvTitleHu101.setVisibility(8);
        }
    }

    private void initHu64SlantCorrection() {
        if (getKeyInfo().getKeyType() == KeyType.SINGLE_OUTSIDE_GROOVE_KEY && getKeyInfo().getSide() == 1 && TextUtils.equals(getKeyInfo().getClampBean().getClampNum(), "S1") && TextUtils.equals(getKeyInfo().getClampBean().getClampSide(), "C")) {
            this.binding.tvSlantCorrection.setVisibility(0);
            this.binding.cbSlantCorrection.setVisibility(0);
            if (this.dataParam.isSlantCorrection()) {
                this.binding.cbSlantCorrection.setChecked(true);
            } else {
                this.binding.cbSlantCorrection.setChecked(false);
            }
        }
    }

    private void initDecoder() {
        this.decoderSize = this.dataParam.getDecoderSize();
        if (getKeyInfo().getType() == 1) {
            this.binding.rgDecodeSize.setVisibility(0);
            this.binding.tvDecoderSize.setVisibility(0);
            if (this.decoderSize == 50) {
                this.binding.rb50.setChecked(true);
            }
        }
    }

    private void initSingleKeyCutDepth() {
        this.cutDepthSingleKey = this.dataParam.getSingleSideKeyCutDepthFix();
        if (getKeyInfo().getType() == 1 && getKeyInfo().getCutDepth() == 0) {
            this.binding.llCutDepthSingleKey.setVisibility(0);
            this.binding.tvCutDepthSingleKey.setVisibility(0);
            this.binding.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
            return;
        }
        this.binding.llCutDepthSingleKey.setVisibility(8);
        this.binding.tvCutDepthSingleKey.setVisibility(8);
    }

    private void initHighHandleMode() {
        if (MachineInfo.isE9Standard(getContext()) && !SPUtils.getBoolean(SpKeys.Not_detect_decoder_position)) {
            this.binding.llHighHandleMode.setVisibility(0);
            this.binding.cbHighHandleMode.setChecked(this.dataParam.isHighHandleMode());
        } else {
            this.binding.llHighHandleMode.setVisibility(8);
        }
    }

    private void initDimpleCutMode() {
        if (this.dimpleDuplicate) {
            this.binding.rgCutMode.setVisibility(8);
            this.binding.tvCutMode.setVisibility(8);
            return;
        }
        if (getKeyInfo().getSpaceWidthStr().contains("-")) {
            this.binding.rgCutMode.setVisibility(8);
            this.binding.tvCutMode.setVisibility(8);
            return;
        }
        if (getKeyInfo().getType() == 6) {
            this.binding.rgCutMode.setVisibility(0);
            this.binding.tvCutMode.setVisibility(0);
        } else {
            this.binding.rgCutMode.setVisibility(8);
            this.binding.tvCutMode.setVisibility(8);
        }
        if (SPUtils.getBoolean(SpKeys.DIMPLE_UP_DOWN_CUTTING)) {
            this.binding.rbUpDownCutting.setChecked(true);
        }
    }

    private void initFastMode() {
        if (this.dataParam.isQuickCut()) {
            this.binding.cbFast.setChecked(true);
        }
    }

    private void initCutSpeed() {
        int i = SPUtils.getInt(SpKeys.SPEED + getKeyInfo().getType(), getKeyInfo().getType() == 6 ? 3 : 15);
        this.cutSpeed = i;
        this.binding.tvSpeedValue.setText(String.valueOf(i));
    }

    private void initPlasticKey() {
        if (getKeyInfo().getAlign() == 0 && getKeyInfo().getLength() == 0) {
            this.binding.llPlasticKey.setVisibility(8);
            return;
        }
        if (getKeyInfo().getType() == 5 || getKeyInfo().getType() == 3 || getKeyInfo().getType() == 2 || getKeyInfo().getType() == 4) {
            this.binding.llPlasticKey.setVisibility(0);
            if (this.dataParam.isPlasticKey()) {
                this.binding.cbPlasticKey.performClick();
                return;
            }
            return;
        }
        this.binding.llPlasticKey.setVisibility(8);
    }

    private void initLayerCut() {
        if (getKeyInfo().getType() == 5 || getKeyInfo().getType() == 2) {
            this.binding.tvLayerCut.setVisibility(0);
            this.binding.rgLayerCut.setVisibility(0);
            int i = SPUtils.getInt(SpKeys.LAYERCUT, 3);
            if (i == 1) {
                this.binding.rbLayer1.setChecked(true);
                return;
            } else if (i == 2) {
                this.binding.rbLayer2.setChecked(true);
                return;
            } else {
                if (i != 3) {
                    return;
                }
                this.binding.rbLayer3.setChecked(true);
                return;
            }
        }
        this.binding.tvLayerCut.setVisibility(8);
        this.binding.rgLayerCut.setVisibility(8);
    }

    private void initCutDepth() {
        this.cutDepth = this.dataParam.getCutDepth();
        if (getKeyInfo().getType() == 5 || getKeyInfo().getType() == 3 || getKeyInfo().getType() == 4 || getKeyInfo().getType() == 2) {
            this.binding.llCutDepth.setVisibility(0);
            this.binding.tvCutDepth.setVisibility(0);
            if (this.dataParam.getCutDepth() == 0) {
                this.cutDepth = 110;
            } else {
                this.cutDepth = this.dataParam.getCutDepth();
            }
            this.binding.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
            return;
        }
        this.binding.llCutDepth.setVisibility(8);
        this.binding.tvCutDepth.setVisibility(8);
    }

    private void initCutShape() {
        if (getKeyInfo().getType() == 1 && getKeyInfo().getCutDepth() == 0) {
            this.binding.tvCutShape.setVisibility(0);
            this.binding.rgCutShape.setVisibility(0);
            int i = SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1);
            if (i == 1) {
                this.binding.rbShapeGentle.setChecked(true);
                return;
            } else {
                if (i != 2) {
                    return;
                }
                this.binding.rbShapeJagged.setChecked(true);
                return;
            }
        }
        this.binding.tvCutShape.setVisibility(8);
        this.binding.rgCutShape.setVisibility(8);
    }

    private void initCutter() {
        if (getKeyInfo().getType() == 6 || getKeyInfo().getType() == 92) {
            if (getKeyInfo().getSpaceWidthStr().contains("-")) {
                this.binding.ivCutter.setImageResource(R.drawable.cutter_dimple_2);
            } else {
                this.binding.ivCutter.setImageResource(R.drawable.cutter_dimple_1);
            }
            this.binding.llCutterSize.setVisibility(8);
            this.binding.bt15mm.setVisibility(8);
            this.binding.bt20mm.setVisibility(8);
            this.binding.bt25mm.setVisibility(8);
        } else {
            this.binding.ivCutter.setImageResource(R.drawable.cutter_normal);
        }
        int cutterSize = ToolSizeManager.getCutterSize();
        Log.i(TAG, "initCutter: " + cutterSize);
        String param = SpecificParamUtils.INSTANCE.getParam(getKeyInfo().getType_specific_info(), "cutter");
        if (!TextUtils.isEmpty(param)) {
            if (param.contains(",")) {
                String[] split = param.split(",");
                if (split.length > 0) {
                    param = split[1];
                }
            }
            this.binding.tvCutterSizeRemind.setVisibility(0);
            this.binding.tvCutterSizeRemind.setText(String.format(getActivity().getResources().getString(R.string.please_use_smm_milling_cutter), param));
        }
        if (cutterSize > 0) {
            this.cutter_size = cutterSize;
        } else if (getKeyInfo().getType() == 5 || getKeyInfo().getType() == 2) {
            if (getKeyInfo().getGroove() != 0 && getKeyInfo().getGroove() < 200) {
                this.cutter_size = (getKeyInfo().getGroove() / 10) * 10;
            } else {
                this.cutter_size = 200;
            }
        } else if (getKeyInfo().getType() == 6) {
            this.cutter_size = ToolSizeManager.DimpleCutterSize;
        } else if (getKeyInfo().getType() == 0) {
            this.cutter_size = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200);
        } else {
            this.cutter_size = 200;
        }
        this.binding.tvCutterSize.setText(String.format(Locale.US, "%.1fmm", Float.valueOf(this.cutter_size / 100.0f)));
    }

    public void onViewClicked(View view) {
        String string;
        switch (view.getId()) {
            case R.id.bt_1_5mm /* 2131361901 */:
                this.cutter_size = 150;
                this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_0mm /* 2131361902 */:
                this.cutter_size = 200;
                this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_5mm /* 2131361903 */:
                this.cutter_size = 250;
                this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_cancle /* 2131361909 */:
                dismiss();
                return;
            case R.id.bt_cut /* 2131361921 */:
                if (getKeyInfo().getType() == 6 && getKeyInfo().getSpaceWidthStr().contains("-")) {
                    if (getKeyInfo().getSpaceWidthStr().contains("-")) {
                        goBack();
                        return;
                    }
                    RemindDialog remindDialog = new RemindDialog(getContext());
                    remindDialog.setRemindImg(R.drawable.remind_cutter_dimple_1);
                    remindDialog.setCancleBtnVisibility(false);
                    remindDialog.setRemindMsg(getContext().getString(R.string.please_install_specified_milling_cutter));
                    remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog.1
                        @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                        public void onDialogButClick(boolean z) {
                            if (z) {
                                CutDialog.this.goBack();
                            }
                        }
                    });
                    remindDialog.show();
                    return;
                }
                WarningDialog warningDialog = new WarningDialog(getContext());
                warningDialog.show();
                int i = SPUtils.getInt(SpKeys.RecentlyUsedCutter, 0);
                if (getKeyInfo().getType() == 6) {
                    string = getContext().getString(R.string.please_install_specified_milling_cutter);
                } else if (i == 0 || i == this.cutter_size) {
                    string = getContext().getString(R.string.please_use_specify_cutter, new Object[]{(this.cutter_size / 100.0f) + "mm"});
                } else {
                    string = getContext().getString(R.string.cutter_different_remind, new Object[]{(i / 100.0f) + "mm", (this.cutter_size / 100.0f) + "mm"});
                }
                warningDialog.setRemind(string);
                warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog.2
                    @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    public void onDialogButClick(boolean z) {
                        if (z) {
                            CutDialog.this.goBack();
                        }
                    }
                });
                return;
            case R.id.cb_fast /* 2131362024 */:
                if (this.binding.cbFast.isChecked()) {
                    WarningDialog warningDialog2 = new WarningDialog(getContext());
                    warningDialog2.setRemind(getContext().getString(R.string.quick_cut_remind));
                    warningDialog2.setCancelText(getContext().getString(R.string.cancel));
                    warningDialog2.setConfirmText(getContext().getString(R.string.continue1));
                    warningDialog2.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog.3
                        @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                        public void onDialogButClick(boolean z) {
                            if (z) {
                                return;
                            }
                            CutDialog.this.binding.cbFast.setChecked(false);
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
                this.binding.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                return;
            case R.id.iv_depth_add_single_key /* 2131362294 */:
                int i2 = this.cutDepthSingleKey;
                if (i2 < 100) {
                    this.cutDepthSingleKey = i2 + 50;
                    this.binding.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_depth_reduce /* 2131362295 */:
                int i3 = this.cutDepth;
                if (i3 > 5) {
                    this.cutDepth = i3 - 5;
                }
                this.binding.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                return;
            case R.id.iv_depth_reduce_single_key /* 2131362296 */:
                int i4 = this.cutDepthSingleKey;
                if (i4 > -100) {
                    this.cutDepthSingleKey = i4 - 50;
                    this.binding.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_length_add /* 2131362313 */:
                int hu101ExtCutFix = this.dataParam.getHu101ExtCutFix() + 10;
                if (hu101ExtCutFix <= 200) {
                    this.dataParam.setHu101ExtCutFix(hu101ExtCutFix);
                    this.binding.tvLengthValue.setText(String.valueOf(hu101ExtCutFix));
                    return;
                }
                return;
            case R.id.iv_length_reduce /* 2131362314 */:
                int hu101ExtCutFix2 = this.dataParam.getHu101ExtCutFix() - 10;
                if (hu101ExtCutFix2 >= -200) {
                    this.dataParam.setHu101ExtCutFix(hu101ExtCutFix2);
                    this.binding.tvLengthValue.setText(String.valueOf(hu101ExtCutFix2));
                    return;
                }
                return;
            case R.id.iv_size_add /* 2131362335 */:
                int i5 = this.cutter_size;
                if (i5 < 250) {
                    this.cutter_size = i5 + 10;
                    this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i5 < 500) {
                    this.cutter_size = i5 + 50;
                    this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_reduce /* 2131362336 */:
                int i6 = this.cutter_size;
                if (i6 > 250) {
                    this.cutter_size = i6 - 50;
                    this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i6 > 100) {
                    this.cutter_size = i6 - 10;
                    this.binding.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_speed_add /* 2131362345 */:
                if (this.cutSpeed < (getKeyInfo().getType() != 6 ? 25 : 5)) {
                    int i7 = this.cutSpeed + 1;
                    this.cutSpeed = i7;
                    this.binding.tvSpeedValue.setText(String.valueOf(i7));
                    SPUtils.put(SpKeys.SPEED + getKeyInfo().getType(), this.cutSpeed);
                    return;
                }
                return;
            case R.id.iv_speed_reduce /* 2131362346 */:
                int i8 = this.cutSpeed;
                if (i8 > 1) {
                    int i9 = i8 - 1;
                    this.cutSpeed = i9;
                    this.binding.tvSpeedValue.setText(String.valueOf(i9));
                    SPUtils.put(SpKeys.SPEED + getKeyInfo().getType(), this.cutSpeed);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goBack() {
        if (getKeyInfo().getType() != 6) {
            SPUtils.put(SpKeys.RecentlyUsedCutter, this.cutter_size);
        }
        Bundle bundle = new Bundle();
        this.dataParam.setClamp(ClampUtil.getClamp(getKeyInfo()));
        this.dataParam.setClampMode(ClampUtil.getClampMode(getKeyInfo()));
        this.dataParam.setDecoderSize(this.decoderSize);
        this.dataParam.setCutterSize(this.cutter_size);
        this.dataParam.setCutSpeed(this.cutSpeed);
        this.dataParam.setCutDepth(this.cutDepth);
        this.dataParam.setLayer(SPUtils.getInt(SpKeys.LAYERCUT, 2));
        this.dataParam.setPlastic(this.binding.cbPlasticKey.isChecked());
        this.dataParam.setQuickCut(this.binding.cbFast.isChecked());
        this.dataParam.setSingleSideKeyCutDepthFix(this.cutDepthSingleKey);
        this.dataParam.setSingleSideKeyCutShape(SPUtils.getInt(SpKeys.SINGLEKEY_CUT_SHAPE, 1));
        this.dataParam.setDimpleUpDownCut(SPUtils.getBoolean(SpKeys.DIMPLE_UP_DOWN_CUTTING));
        this.dataParam.setHighHandleMode(this.binding.cbHighHandleMode.isChecked());
        this.dataParam.setSlantCorrection(this.binding.cbSlantCorrection.isChecked());
        bundle.putParcelable("param", this.dataParam);
        EventBus.getDefault().post(new EventCenter(1, bundle));
        if (this.dimpleDuplicate) {
            return;
        }
        dismiss();
    }

    private void initClamp() {
        int clampBigImg = ClampCreator.getClampBigImg(getKeyInfo());
        if (clampBigImg != 0) {
            this.binding.ivClamp.setImageResource(clampBigImg);
        }
    }

    private KeyInfo getKeyInfo() {
        return this.dataParam.getKeyInfo();
    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.cb_plastic_key /* 2131362029 */:
                if (z && !this.dataParam.isPlasticKey()) {
                    WarningDialog warningDialog = new WarningDialog(getContext());
                    warningDialog.show();
                    warningDialog.setRemind(getContext().getString(R.string.risk_sticking_breaking_milling_cutter));
                }
                if (getKeyInfo().getAlign() == 1) {
                    if (z) {
                        if (MachineInfo.isE9()) {
                            this.binding.ivClamp.setImageResource(R.drawable.car_clamp_remind_e9s1a_tip_plastic);
                            return;
                        } else {
                            this.binding.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip_plastic);
                            return;
                        }
                    }
                    if (MachineInfo.isE9()) {
                        this.binding.ivClamp.setImageResource(R.drawable.a9_laser_stop_4_side_big_e9);
                        return;
                    } else {
                        this.binding.ivClamp.setImageResource(R.drawable.car_clamp_remind_b_tip);
                        return;
                    }
                }
                if (getKeyInfo().getIcCard() == 909) {
                    if (z) {
                        this.cutDepth = 100;
                    } else {
                        this.cutDepth = 115;
                    }
                    this.binding.tvDepthValue.setText((this.cutDepth / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.rb_100 /* 2131362595 */:
                if (z) {
                    this.decoderSize = 100;
                    return;
                }
                return;
            case R.id.rb_50 /* 2131362598 */:
                if (z) {
                    this.decoderSize = 50;
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
            case R.id.rb_rotate_cutting /* 2131362635 */:
                if (z) {
                    SPUtils.put(SpKeys.DIMPLE_UP_DOWN_CUTTING, false);
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
            case R.id.rb_up_down_cutting /* 2131362665 */:
                if (z) {
                    SPUtils.put(SpKeys.DIMPLE_UP_DOWN_CUTTING, true);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void initDimpleDepth() {
        if (this.dimpleDuplicate) {
            this.binding.tvTitleDepth.setVisibility(0);
            this.binding.llDepthContainer.setVisibility(0);
            String[] split = getKeyInfo().getKeyToothCode().split(";");
            int i = 0;
            for (String str : split) {
                i = Math.max(i, str.split(",").length);
            }
            int i2 = 0;
            while (i2 < split.length + 1) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 20);
                layoutParams.setMargins(0, 1, 0, 0);
                this.binding.llIndex.addView(getText(i2 == 0 ? "" : String.valueOf(i2), true), layoutParams);
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(0);
                for (int i3 = 0; i3 < i; i3++) {
                    TextView textView = null;
                    if (i2 == 0) {
                        textView = getText(String.valueOf(i3 + 1), true);
                    } else {
                        int i4 = i2 - 1;
                        String[] split2 = split[i4].split(",");
                        if (i3 < split2.length) {
                            textView = getText(String.valueOf(getKeyInfo().getDepthByCode(getKeyInfo().getDepthList().get(i4), getKeyInfo().getDepthNameList().get(i4), split2[i3])));
                        }
                    }
                    if (textView == null) {
                        break;
                    }
                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(45, 20);
                    layoutParams2.setMargins(1, 1, 0, 0);
                    linearLayout.addView(textView, layoutParams2);
                }
                this.binding.llDepth.addView(linearLayout);
                i2++;
            }
        }
    }

    private TextView getText(String str, boolean z) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input));
        if (z) {
            textView.setTextColor(getContext().getResources().getColor(R.color.color_ff205f));
        } else {
            textView.setTextColor(-1);
        }
        textView.setText(str);
        textView.setTextSize(0, 15.0f);
        return textView;
    }

    private TextView getText(String str) {
        return getText(str, false);
    }
}
