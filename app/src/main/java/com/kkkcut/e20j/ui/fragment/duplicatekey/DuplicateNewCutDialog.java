package com.kkkcut.e20j.ui.fragment.duplicatekey;

import android.app.Activity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.autolayout.widget.AutoRadioGroup;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.eventbus.DuplicateBean;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.ThemeUtils;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.duplicate.keyview.CopyDoubleOutSideKey;
import com.cutting.machine.duplicate.keyview.CopyDoubleSideKey;
import com.cutting.machine.duplicate.keyview.CopySideHoleKey;
import com.cutting.machine.duplicate.keyview.CopySingleInsideKey;
import com.cutting.machine.duplicate.keyview.CopySingleOutSideKey;
import com.cutting.machine.duplicate.keyview.CopySingleSideKey;
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes.dex */
public class DuplicateNewCutDialog extends BottomInDialog {
    private int cutDepthSingleKey;
    private int cutSpeed;
    private int cutter_size;
    private DuplicateDecodeParams decodeParams;

    FrameLayout flKey;

    FrameLayout flTubular;

    ImageView ivClamp;
    private int layerCut;

    LinearLayout llCutDepthSingleKey;

    LinearLayout llDepth;

    RadioButton rbLayer1;

    RadioButton rbLayer2;

    RadioButton rbLayer3;

    AutoRadioGroup rgLayerCut;

    TextView tvCutDepthSingleKey;

    TextView tvCutterSize;

    TextView tvDepthValueSingleKey;

    TextView tvRemind;

    TextView tvSpeedValue;


    TextView tvTitleLayer;

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public int getContentLayoutID() {
        return R.layout.dialog_duplicate_cut;
    }

    public DuplicateNewCutDialog(Activity activity, DuplicateDecodeParams duplicateDecodeParams) {
        super(activity);
        this.cutter_size = ToolSizeManager.getCutterSize();
        this.layerCut = 3;
        this.decodeParams = duplicateDecodeParams;
        setCancelable(false);
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1_0mm /* 2131361900 */:
                this.cutter_size = 100;
                this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                return;
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
                if (!SPUtils.getBoolean(SpKeys.DUPLICATE_CANCEL_REMIND_NERVER_ASK, false)) {
                    RemindDialog remindDialog = new RemindDialog(getContext());
                    remindDialog.show();
                    remindDialog.setCheckbox(true, SpKeys.DUPLICATE_CANCEL_REMIND_NERVER_ASK);
                    remindDialog.setRemindMsg(getActivity().getString(R.string.decode_data_will_be_lost_after_cancel));
                    remindDialog.setCancelStr(getActivity().getString(R.string.no));
                    remindDialog.setConfirmStr(getActivity().getString(R.string.yes));
                    remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog.1
                        @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                        public void onDialogButClick(boolean z) {
                            if (z) {
                                DuplicateNewCutDialog.this.dismiss();
                            }
                        }
                    });
                    return;
                }
                dismiss();
                return;
            case R.id.bt_cut /* 2131361921 */:
                EventBus.getDefault().post(new EventCenter(43, new DuplicateBean(this.layerCut, this.cutter_size, this.cutDepthSingleKey)));
                return;
            case R.id.iv_depth_add_single_key /* 2131362294 */:
                int i = this.cutDepthSingleKey;
                if (i < 100) {
                    this.cutDepthSingleKey = i + 50;
                    this.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_depth_reduce_single_key /* 2131362296 */:
                int i2 = this.cutDepthSingleKey;
                if (i2 > -100) {
                    this.cutDepthSingleKey = i2 - 50;
                    this.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_add /* 2131362335 */:
                int i3 = this.cutter_size;
                if (i3 < 250) {
                    this.cutter_size = i3 + 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_size_reduce /* 2131362336 */:
                int i4 = this.cutter_size;
                if (i4 > 50) {
                    this.cutter_size = i4 - 10;
                    this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
                    return;
                }
                return;
            case R.id.iv_speed_add /* 2131362345 */:
                if (this.cutSpeed < (getKeyType() == KeyType.DIMPLE_KEY ? 5 : 25)) {
                    int i5 = this.cutSpeed + 1;
                    this.cutSpeed = i5;
                    this.tvSpeedValue.setText(String.valueOf(i5));
                    SPUtils.put(SpKeys.SPEED + getKeyType().getValue(), this.cutSpeed);
                    return;
                }
                return;
            case R.id.iv_speed_reduce /* 2131362346 */:
                int i6 = this.cutSpeed;
                if (i6 > 1) {
                    int i7 = i6 - 1;
                    this.cutSpeed = i7;
                    this.tvSpeedValue.setText(String.valueOf(i7));
                    SPUtils.put(SpKeys.SPEED + getKeyType().getValue(), this.cutSpeed);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R.id.rb_100) {
            if (z) {
                ToolSizeManager.setDecoderSize(100);
                return;
            }
            return;
        }
        if (id == R.id.rb_50) {
            if (z) {
                ToolSizeManager.setDecoderSize(50);
                return;
            }
            return;
        }
        switch (id) {
            case R.id.rb_layer_1 /* 2131362625 */:
                if (z) {
                    this.layerCut = 1;
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 1);
                    return;
                }
                return;
            case R.id.rb_layer_2 /* 2131362626 */:
                if (z) {
                    this.layerCut = 2;
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 2);
                    return;
                }
                return;
            case R.id.rb_layer_3 /* 2131362627 */:
                if (z) {
                    this.layerCut = 3;
                    SPUtils.put(SpKeys.DUPLICATE_LAYERCUT, 3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.kkkcut.e20j.ui.dialog.base.BottomInDialog
    public void initView() {
        initKeyView();
        initCutter();
        initLayerCut();
        initClamp();
        initCutSpeed();
        initSingleKeyCutDepth();
    }

    private void initSingleKeyCutDepth() {
        if (getKeyInfo().keyType == KeyType.SINGLE_SIDE_KEY) {
            this.llCutDepthSingleKey.setVisibility(0);
            this.tvCutDepthSingleKey.setVisibility(0);
            this.tvDepthValueSingleKey.setText((this.cutDepthSingleKey / 100.0f) + "mm");
            return;
        }
        this.llCutDepthSingleKey.setVisibility(8);
        this.tvCutDepthSingleKey.setVisibility(8);
    }

    private DuplicateKeyData getKeyInfo() {
        DuplicateDecodeParams duplicateDecodeParams = this.decodeParams;
        if (duplicateDecodeParams == null) {
            return null;
        }
        return duplicateDecodeParams.getKeyInfo();
    }

    private void initLayerCut() {
        if (getKeyType() == KeyType.DOUBLE_INSIDE_GROOVE_KEY) {
            this.tvTitleLayer.setVisibility(0);
            this.rgLayerCut.setVisibility(0);
            int i = SPUtils.getInt(SpKeys.DUPLICATE_LAYERCUT, 3);
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
        this.tvTitleLayer.setVisibility(8);
        this.rgLayerCut.setVisibility(8);
        if (getKeyType() == KeyType.LEFT_GROOVE || getKeyType() == KeyType.RIGHT_GROOVE || getKeyType() == KeyType.TWO_GROOVE || getKeyType() == KeyType.THREE_GROOVE) {
            this.layerCut = 1;
        }
    }

    private void initCutter() {
        if (getKeyInfo().keyType == KeyType.DOUBLE_SIDE_KEY) {
            this.cutter_size = SPUtils.getInt(SpKeys.DOUBLE_KEY_CUTTER, 200);
        }
        this.tvCutterSize.setText((this.cutter_size / 100.0f) + "mm");
        if (getKeyType() == KeyType.DOUBLE_SIDE_KEY) {
            this.tvRemind.setVisibility(0);
            this.tvRemind.setText(getContext().getString(R.string.recommended_150_milling_cutter));
        } else {
            this.tvRemind.setVisibility(8);
        }
    }

    private void initCutSpeed() {
        int i = SPUtils.getInt(SpKeys.SPEED + getKeyType().getValue(), getKeyType() == KeyType.DIMPLE_KEY ? 3 : 15);
        this.cutSpeed = i;
        this.tvSpeedValue.setText(String.valueOf(i));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initKeyView() {
        View view;
        switch (AnonymousClass2.$SwitchMap$com$liying$core$bean$KeyType[getKeyType().ordinal()]) {
            case 1:
                view = new CopySingleSideKey(getContext(), getKeyInfo());
                break;
            case 2:
                view = new CopyDoubleSideKey(getContext(), getKeyInfo());
                break;
            case 3:
                view = new CopySingleOutSideKey(getContext(), getKeyInfo());
                break;
            case 4:
                view = new CopyDoubleOutSideKey(getContext(), getKeyInfo());
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                view = new CopySingleInsideKey(getContext(), getKeyInfo());
                break;
            case 10:
                this.flKey.setVisibility(8);
                this.flTubular.setVisibility(0);
                this.llDepth.removeAllViews();
                List<DestPoint> destPointList = getKeyInfo().getDecodeData().getPathDataList().get(0).getDestPointList();
                for (int i = 0; i < destPointList.size(); i++) {
                    TextView textView = new TextView(getContext());
                    textView.setText(String.valueOf(Math.round(destPointList.get(i).getDepth() * MachineData.dZScale)));
                    textView.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333));
                    textView.setGravity(17);
                    textView.setTextSize(AutoUtils.getPercentHeightSize(20));
                    this.llDepth.addView(textView, new LinearLayout.LayoutParams(-1, AutoUtils.getPercentHeightSize(35)));
                    View view2 = new View(getContext());
                    view2.setBackgroundColor(getContext().getResources().getColor(R.color.color_1b1a28));
                    this.llDepth.addView(view2, new LinearLayout.LayoutParams(-1, AutoUtils.getPercentHeightSize(1)));
                }
                view = null;
                break;
            case 11:
                view = new CopySideHoleKey(getContext(), getKeyInfo());
                break;
            default:
                view = null;
                break;
        }
        if (view != null) {
            this.flKey.removeAllViews();
            this.flKey.addView(view);
        }
    }

    public KeyType getKeyType() {
        return getKeyInfo().keyType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$clamp$Clamp;

        static {
            int[] iArr = new int[Clamp.values().length];
            $SwitchMap$com$liying$core$clamp$Clamp = iArr;
            try {
                iArr[Clamp.S1_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_B.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_C.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S1_D.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_A.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S2_B.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.S3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1A.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S1C.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2A.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S2B.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$clamp$Clamp[Clamp.E9S3.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr2;
            try {
                iArr2[KeyType.SINGLE_SIDE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 7;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TWO_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.THREE_GROOVE.ordinal()] = 9;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TUBULAR_KEY.ordinal()] = 10;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused23) {
            }
        }
    }

    private void initClamp() {
        KeyAlign keyAlign = getKeyInfo().getKeyAlign();
        switch (AnonymousClass2.$SwitchMap$com$liying$core$clamp$Clamp[this.decodeParams.getClamp().ordinal()]) {
            case 1:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                    return;
                }
            case 2:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                    return;
                } else if (this.decodeParams.getClampMode() == 0) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    return;
                }
            case 3:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default);
                    return;
                }
            case 4:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default);
                    return;
                }
            case 5:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default);
                    return;
                }
            case 6:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default);
                    return;
                }
            case 7:
                this.ivClamp.setImageResource(R.drawable.keycopy_clamp_s3_select);
                return;
            case 8:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9);
                    return;
                }
            case 9:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.a9_standard_stop_1_duplicate_e9);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.a9_standard_stop_4_duplicate_e9);
                    return;
                }
            case 10:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.single_key_5mm_shoulder_duplicate_e9);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.single_key_5mm_tip_duplicate_e9);
                    return;
                }
            case 11:
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp.setImageResource(R.drawable.single_key_35mm_shoulder_duplicate_e9);
                    return;
                } else {
                    this.ivClamp.setImageResource(R.drawable.single_key_35mm_tip_duplicate_e9);
                    return;
                }
            case 12:
                this.ivClamp.setImageResource(R.drawable.a9_tubular_stop_duplicate_e9);
                return;
            default:
                return;
        }
    }
}
