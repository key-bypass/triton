package com.kkkcut.e20j.ui.fragment.blankcut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.ToolSizeManager;
import com.kkkcut.e20j.us.R;

import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class BlankCutDialog extends BottomInDialog {
    private static final String TAG = "CutDialog";
    private BlankCutType blankCutType;
    Button bt15mm;

    Button bt20mm;

    Button bt25mm;

    TextView btCut;
    private int cutSpeed;
    private boolean cutterSizeChangeable;
    private int cutter_size;

    ImageView ivClamp;

    ImageView ivCutter;

    LinearLayout llCutSpeed;

    LinearLayout llCutterSize;
    private boolean onceCut;

    RadioGroup rgCutTimes;

    TextView tvCutSpeed;

    TextView tvCutterSize;

    TextView tvCutterSizeRemind;

    TextView tvSpeedValue;

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.fragment_modify_key_blank_cut;
    }

    public BlankCutDialog(Activity activity, BlankCutType blankCutType) {
        super(activity);
        this.blankCutType = blankCutType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType;

        static {
            int[] iArr = new int[BlankCutType.values().length];
            $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType = iArr;
            try {
                iArr[BlankCutType.KEY_HEAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.THICKNESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.WIDTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.k9ToLxp90.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.Toyota80K.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.DRILLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.LEFT_GROOVE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.RIGHT_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KEY_TIP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.CREATE_GROOVE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.K4080K.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.HY18R.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.SIDE_GROOVE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KW16ToKW15.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[BlankCutType.KW14ToKW15.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public void initView() {
        switch (AnonymousClass1.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[this.blankCutType.ordinal()]) {
            case 1:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_head);
                this.rgCutTimes.setVisibility(8);
                break;
            case 2:
                if (MachineInfo.isChineseMachine()) {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_thickness_s1);
                } else {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_thickness);
                }
                this.rgCutTimes.setVisibility(0);
                break;
            case 3:
            case 4:
            case 5:
                if (MachineInfo.isChineseMachine()) {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_width_s1);
                } else {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_width);
                }
                this.rgCutTimes.setVisibility(0);
                break;
            case 6:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_drilling);
                this.rgCutTimes.setVisibility(8);
                break;
            case 7:
                if (MachineInfo.isChineseMachine()) {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_left_groove_s1);
                } else {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_left_groove_s8);
                }
                this.rgCutTimes.setVisibility(8);
                break;
            case 8:
                if (MachineInfo.isChineseMachine()) {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_right_groove_s1);
                } else {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_right_groove_s8);
                }
                this.rgCutTimes.setVisibility(8);
                break;
            case 9:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_key_tip);
                this.rgCutTimes.setVisibility(8);
                break;
            case 10:
            case 11:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_40k80k);
                this.rgCutTimes.setVisibility(8);
                break;
            case 12:
            case 13:
                if (MachineInfo.isChineseMachine()) {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_hy18r_s1);
                } else {
                    this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_hy18_s8);
                }
                this.rgCutTimes.setVisibility(8);
                break;
            case 14:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_cut_side_groove_s8);
                this.rgCutTimes.setVisibility(8);
                break;
            case 15:
            case 16:
                this.ivClamp.setImageResource(R.drawable.clamp_remind_blank_kw16_kw15_groove_s8);
                this.rgCutTimes.setVisibility(8);
                break;
        }
        initCutter();
        initCutSpeed();
    }

    private void initCutSpeed() {
        int speed = BlankCutSpeedUtils.getSpeed(this.blankCutType);
        this.cutSpeed = speed;
        this.tvSpeedValue.setText(String.valueOf(speed));
    }

    private void initCutter() {
        int i = AnonymousClass1.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[this.blankCutType.ordinal()];
        if (i == 6) {
            this.cutter_size = 250;
            this.cutterSizeChangeable = false;
        } else if (i == 14) {
            this.cutter_size = 100;
            this.cutterSizeChangeable = false;
        } else {
            this.cutter_size = ToolSizeManager.getCutterSize();
            this.cutterSizeChangeable = true;
        }
        this.tvCutterSize.setText(String.format("%.1fmm", Float.valueOf(this.cutter_size / 100.0f)));
        if (this.cutterSizeChangeable) {
            return;
        }
        this.bt15mm.setVisibility(8);
        this.bt20mm.setVisibility(8);
        this.bt25mm.setVisibility(8);
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1_5mm /* 2131361901 */:
                if (checkCutterSize()) {
                    return;
                }
                this.cutter_size = 150;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_0mm /* 2131361902 */:
                if (checkCutterSize()) {
                    return;
                }
                this.cutter_size = 200;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_2_5mm /* 2131361903 */:
                if (checkCutterSize()) {
                    return;
                }
                this.cutter_size = 250;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
            case R.id.bt_cancle /* 2131361909 */:
                dismiss();
                return;
            case R.id.iv_close /* 2131362287 */:
                dismiss();
                return;
            case R.id.iv_size_add /* 2131362335 */:
                if (checkCutterSize()) {
                    return;
                }
                int i = this.cutter_size;
                if (i < 250) {
                    this.cutter_size = i + 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i < 500) {
                    this.cutter_size = i + 50;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_reduce /* 2131362336 */:
                if (checkCutterSize()) {
                    return;
                }
                int i2 = this.cutter_size;
                if (i2 > 250) {
                    this.cutter_size = i2 - 50;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                if (i2 > 100) {
                    this.cutter_size = i2 - 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_speed_add /* 2131362345 */:
                int i3 = this.cutSpeed;
                if (i3 < 25) {
                    int i4 = i3 + 1;
                    this.cutSpeed = i4;
                    this.tvSpeedValue.setText(String.valueOf(i4));
                    BlankCutSpeedUtils.saveSpeed(this.blankCutType, this.cutSpeed);
                    return;
                }
                return;
            case R.id.iv_speed_reduce /* 2131362346 */:
                int i5 = this.cutSpeed;
                if (i5 > 1) {
                    int i6 = i5 - 1;
                    this.cutSpeed = i6;
                    this.tvSpeedValue.setText(String.valueOf(i6));
                    BlankCutSpeedUtils.saveSpeed(this.blankCutType, this.cutSpeed);
                    return;
                }
                return;
            case R.id.tv_cut /* 2131362904 */:
                WarningDialog warningDialog = new WarningDialog(getContext());
                warningDialog.show();
                warningDialog.setRemind(getContext().getString(R.string.please_use_specify_cutter, new Object[]{(this.cutter_size / 100.0f) + "mm"}));
                warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog$$ExternalSyntheticLambda0
                    @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                    public final void onDialogButClick(boolean z) {
                        BlankCutDialog.this.m49x9deff7e3(z);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onViewClicked$0$com-kkkcut-e20j-ui-fragment-blankcut-BlankCutDialog, reason: not valid java name */
    public /* synthetic */ void m49x9deff7e3(boolean z) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt("cutterSize", this.cutter_size);
            bundle.putBoolean("isSecondSide", this.onceCut);
            EventBus.getDefault().post(new EventCenter(1, bundle));
            dismiss();
        }
    }

    private boolean checkCutterSize() {
        if (this.cutterSizeChangeable) {
            return false;
        }
        ToastUtil.showToast(getContext().getString(R.string.please_use_specify_cutter, new Object[]{(this.cutter_size / 100.0f) + "mm"}));
        return true;
    }

    public void onCheckedChange(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.rb_double_cut) {
            if (z) {
                this.onceCut = false;
            }
        } else if (id == R.id.rb_once_cut && z) {
            this.onceCut = true;
        }
    }
}
