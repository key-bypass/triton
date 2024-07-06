package com.kkkcut.e20j.ui.fragment.duplicatekey;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.duplicate.Benchmark;
import com.cutting.machine.duplicate.DecodeData;
import com.cutting.machine.duplicate.SinglePathData;
import com.cutting.machine.error.ErrorBean;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateDecodeParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.DuplicateUtil;
import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.bean.eventbus.DuplicateBean;
import com.kkkcut.e20j.customView.MyRadioGroup;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment;
import com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.utils.CutCountHelper;
import com.kkkcut.e20j.utils.ThemeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/* loaded from: classes.dex */
public class DuplicateKeyNewFragment extends BaseBackFragment {

    Button btDimple;

    Button btHondaKey;
    private DuplicateCutParams cutParams;
    private DuplicateDecodeParams decodeParams;
    private boolean isE9Machine;

    ImageView ivClamp0;

    ImageView ivClamp1;

    ImageView ivClamp2;
    private DuplicateKeyData keyInfo = new DuplicateKeyData();

    LinearLayout llClamp0;

    LinearLayout llClamp1;

    LinearLayout llClamp2;

    MyRadioGroup myRadioGroup;

    RadioButton rbDoubleGrooveKey;

    RadioButton rbDoubleKey;

    RadioButton rbSideHole;

    RadioButton rbSingleInsideLeftKey;

    RadioButton rbSingleInsideRightKey;

    RadioButton rbSingleKey;

    RadioButton rbThreeGrooveKey;

    RadioButton rbTubularKey;

    RadioButton tvShoulder1;

    RadioButton tvShoulder2;

    RadioButton tvTip1;

    RadioButton tvTip2;

    TextView tvTitleClamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum OperationType {
        decode,
        cut
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_duplicate_key;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void onUserVisible() {
    }

    public static DuplicateKeyNewFragment newInstance() {
        return new DuplicateKeyNewFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        boolean isE9 = CuttingMachine.getInstance().isE9();
        this.isE9Machine = isE9;
        if (isE9) {
            this.btHondaKey.setVisibility(8);
            this.rbSideHole.setVisibility(8);
            this.rbDoubleGrooveKey.setVisibility(8);
            this.rbThreeGrooveKey.setVisibility(8);
            this.rbSingleInsideLeftKey.setVisibility(8);
            this.rbSingleInsideRightKey.setVisibility(8);
            this.btDimple.setVisibility(8);
        } else {
            if (MachineInfo.isChineseMachine()) {
                this.rbTubularKey.setVisibility(8);
                this.rbSingleKey.setVisibility(8);
            }
            this.rbSingleInsideLeftKey.setVisibility(0);
            this.rbSingleInsideRightKey.setVisibility(0);
            this.btHondaKey.setVisibility(0);
            this.btDimple.setVisibility(0);
            this.rbSideHole.setVisibility(0);
        }
        if (MachineInfo.isChineseMachine()) {
            this.rbDoubleKey.setChecked(true);
        } else {
            this.rbSingleKey.setChecked(true);
            this.keyInfo.setType(KeyType.SINGLE_SIDE_KEY);
        }
        showDuplicationRemind();
    }

    private void showDuplicationRemind() {
        WarningDialog warningDialog = new WarningDialog(getContext());
        warningDialog.show();
        warningDialog.setRemind(getString(R.string.duplication_remind));
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.duplicating_key);
    }

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_dimple /* 2131361925 */:
                start(DuplicateDimpleAlignFragment.newInstance());
                return;
            case R.id.bt_honda_key /* 2131361934 */:
                start(HondaSideCutFragment.newInstance());
                return;
            case R.id.ll_clamp_0 /* 2131362403 */:
                readyDecode(0, view);
                return;
            case R.id.ll_clamp_1 /* 2131362404 */:
                readyDecode(1, view);
                return;
            case R.id.ll_clamp_2 /* 2131362405 */:
                readyDecode(2, view);
                return;
            case R.id.rb_shoulder_1 /* 2131362644 */:
                if (this.keyInfo.getKeyType() == KeyType.SINGLE_OUTSIDE_GROOVE_KEY) {
                    this.keyInfo.setSide(1);
                } else {
                    this.keyInfo.setSide(0);
                }
                this.keyInfo.setAlign(KeyAlign.SHOULDERS_ALIGN);
                showClamp();
                return;
            case R.id.rb_shoulder_2 /* 2131362645 */:
                if (this.keyInfo.getKeyType() == KeyType.DOUBLE_SIDE_KEY) {
                    this.keyInfo.setSide(3);
                } else {
                    this.keyInfo.setSide(0);
                }
                this.keyInfo.setAlign(KeyAlign.SHOULDERS_ALIGN);
                showClamp();
                return;
            case R.id.rb_tip_1 /* 2131362661 */:
                if (this.keyInfo.getKeyType() == KeyType.SINGLE_OUTSIDE_GROOVE_KEY) {
                    this.keyInfo.setSide(1);
                } else {
                    this.keyInfo.setSide(0);
                }
                this.keyInfo.setAlign(KeyAlign.FRONTEND_ALIGN);
                showClamp();
                return;
            case R.id.rb_tip_2 /* 2131362662 */:
                if (this.keyInfo.getKeyType() == KeyType.DOUBLE_SIDE_KEY) {
                    this.keyInfo.setSide(3);
                } else {
                    this.keyInfo.setSide(0);
                }
                this.keyInfo.setAlign(KeyAlign.FRONTEND_ALIGN);
                showClamp();
                return;
            default:
                return;
        }
    }

    private void readyDecode(int i, View view) {
        ClampTag clampTag = (ClampTag) view.getTag();
        Clamp clamp = clampTag.getClamp();
        int clampMode = clampTag.getClampMode();
        if (ClampManager.getInstance().checkHasCalibrated(clamp)) {
            initDecodeData(this.keyInfo.getKeyType());
            changeClampStatus(i);
            this.decodeParams = DuplicateDecodeParams.DecodeParamsBuilder.aDecodeParams().withDecodeSize(ToolSizeManager.getDecoderSize()).withKeyInfo(this.keyInfo).withClamp(clamp).withClampMode(clampMode).withDensity(30).build();
            showConductiveTestRemind(OperationType.decode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment$5, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.SINGLE_SIDE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TWO_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.THREE_GROOVE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TUBULAR_KEY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void initDecodeData(KeyType keyType) {
        DecodeData decodeData = new DecodeData();
        int i = 0;
        switch (AnonymousClass5.$SwitchMap$com$liying$core$bean$KeyType[keyType.ordinal()]) {
            case 1:
                decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                break;
            case 2:
            case 3:
                decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                decodeData.addSinglePathData(new SinglePathData(Benchmark.LEFT));
                break;
            case 4:
                if (this.keyInfo.getSide() == 0) {
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.LEFT));
                    break;
                } else {
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                    break;
                }
            case 5:
            case 6:
            case 7:
                decodeData.addSinglePathData(new SinglePathData(Benchmark.LEFT));
                decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                break;
            case 8:
                while (i < 2) {
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.LEFT));
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                    i++;
                }
                break;
            case 9:
                while (i < 3) {
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.LEFT));
                    decodeData.addSinglePathData(new SinglePathData(Benchmark.RIGHT));
                    i++;
                }
                break;
            case 10:
                decodeData.addSinglePathData(new SinglePathData());
                break;
            case 11:
                decodeData.addSinglePathData(new SinglePathData());
                break;
        }
        this.keyInfo.setDecodeData(decodeData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCut() {
        CuttingMachine.getInstance().duplicateCut(this.cutParams);
        showLoadingDialog(getString(R.string.waitting), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDecode() {
        CuttingMachine.getInstance().duplicateDecode(this.decodeParams);
        showLoadingDialog(getString(R.string.decoding), true);
    }

    private void changeClampStatus(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return;
        }
        switch (AnonymousClass5.$SwitchMap$com$liying$core$bean$KeyType[this.keyInfo.getKeyType().ordinal()]) {
            case 1:
                if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    if (i == 0) {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_select);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default);
                        return;
                    } else {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_select);
                        return;
                    }
                }
                if (i == 0) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_select);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default);
                    return;
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_select);
                    return;
                }
            case 2:
                if (i == 0) {
                    if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_select);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default);
                        return;
                    } else {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_select);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default);
                        return;
                    }
                }
                if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_select);
                    return;
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_select);
                    return;
                }
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (i == 1) {
                    if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_select);
                        return;
                    } else {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_select);
                        return;
                    }
                }
                if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_select);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                    return;
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_select);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    return;
                }
            case 4:
                if (this.keyInfo.getKeyAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    if (i == 1) {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_select);
                        this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default);
                        return;
                    } else if (i == 2) {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                        this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_select);
                        return;
                    } else {
                        this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_select);
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                        this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default);
                        return;
                    }
                }
                if (i == 1) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_select);
                    this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default);
                    return;
                } else if (i == 2) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_select);
                    return;
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_select);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default);
                    return;
                }
            case 10:
                this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s3_select);
                return;
            default:
                return;
        }
    }

    private void showClamp() {
        this.llClamp0.setVisibility(View.GONE);
        this.llClamp1.setVisibility(View.GONE);
        this.llClamp2.setVisibility(View.GONE);
        KeyAlign keyAlign = this.keyInfo.getKeyAlign();
        if (this.isE9Machine) {
            showE9Clamp(keyAlign);
        } else {
            showAlphaBetaClamp(keyAlign);
        }
    }

    private void showE9Clamp(KeyAlign keyAlign) {
        switch (AnonymousClass5.$SwitchMap$com$liying$core$bean$KeyType[this.keyInfo.getKeyType().ordinal()]) {
            case 1:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.single_key_5mm_shoulder_duplicate_e9);
                    this.ivClamp1.setImageResource(R.drawable.single_key_35mm_shoulder_duplicate_e9);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.single_key_5mm_tip_duplicate_e9);
                    this.ivClamp1.setImageResource(R.drawable.single_key_35mm_tip_duplicate_e9);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.E9S2A));
                this.llClamp1.setTag(new ClampTag(Clamp.E9S2B));
                return;
            case 2:
                this.llClamp0.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.a9_standard_stop_1_duplicate_e9);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.a9_standard_stop_4_duplicate_e9);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.E9S1C));
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                this.llClamp0.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.E9S1A));
                return;
            case 4:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_1_duplicate_e9);
                    this.ivClamp1.setImageResource(R.drawable.a9_laser_stop_1_side_duplicate_e9);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_4_duplicate_e9);
                    this.ivClamp1.setImageResource(R.drawable.a9_laser_stop_4_side_duplicate_e9);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.E9S1A));
                this.llClamp1.setTag(new ClampTag(Clamp.E9S1A, 1));
                return;
            case 10:
            default:
                return;
            case 11:
                this.llClamp0.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_1_side_duplicate_e9);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.a9_laser_stop_4_side_duplicate_e9);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.E9S1A, 1));
                return;
        }
    }

    private void showAlphaBetaClamp(KeyAlign keyAlign) {
        switch (AnonymousClass5.$SwitchMap$com$liying$core$bean$KeyType[this.keyInfo.getKeyType().ordinal()]) {
            case 1:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_shoulder_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_shoulder_default);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s2_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s2_b_tip_default);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.S2_A));
                this.llClamp1.setTag(new ClampTag(Clamp.S2_B));
                return;
            case 2:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_shoulder_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_shoulder_default);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_c_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_d_tip_default);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.S1_C));
                this.llClamp1.setTag(new ClampTag(Clamp.S1_D));
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.S1_A));
                this.llClamp1.setTag(new ClampTag(Clamp.S1_B));
                return;
            case 4:
                this.llClamp0.setVisibility(0);
                this.llClamp1.setVisibility(0);
                this.llClamp2.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_shoulder_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_default);
                    this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_a_tip_default);
                    this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_default);
                    this.ivClamp2.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.S1_A));
                this.llClamp1.setTag(new ClampTag(Clamp.S1_B));
                this.llClamp2.setTag(new ClampTag(Clamp.S1_B, 1));
                return;
            case 10:
            default:
                return;
            case 11:
                this.llClamp0.setVisibility(0);
                if (keyAlign == KeyAlign.SHOULDERS_ALIGN) {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_b_shoulder_side_default);
                } else {
                    this.ivClamp0.setImageResource(R.drawable.keycopy_clamp_s1_b_tip_side_default);
                }
                this.llClamp0.setTag(new ClampTag(Clamp.S1_B, 1));
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.myRadioGroup.clearCheck();
            this.tvShoulder1.setChecked(false);
            this.tvShoulder2.setChecked(false);
            this.tvTip1.setChecked(false);
            this.tvTip2.setChecked(false);
            this.llClamp0.setVisibility(8);
            this.llClamp1.setVisibility(8);
            this.llClamp2.setVisibility(8);
            ToolSizeManager.setDecoderSize(100);
            ToolSizeManager.setCutterSize(200);
            switch (compoundButton.getId()) {
                case R.id.rb_double_groove_key /* 2131362611 */:
                    ToolSizeManager.setDecoderSize(50);
                    ToolSizeManager.setCutterSize(100);
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_double_groove_key_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_double_groove_key_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.TWO_GROOVE);
                    return;
                case R.id.rb_double_groove_key_wudihu /* 2131362612 */:
                    ToolSizeManager.setDecoderSize(50);
                    ToolSizeManager.setCutterSize(100);
                    this.tvShoulder1.setBackgroundResource(R.drawable.keycopy_double_groove_key_shoulder_wudihu_default);
                    this.tvTip1.setBackgroundResource(R.drawable.keycopy_double_groove_key_tip_wudihu_default);
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.TWO_GROOVE);
                    return;
                case R.id.rb_double_key /* 2131362614 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_doublekey_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_doublekey_tip));
                    this.tvShoulder1.setText(getString(R.string.ab_same) + " " + getString(R.string.shoulder));
                    this.tvTip1.setText(getString(R.string.ab_same) + " " + getString(R.string.tip));
                    this.tvShoulder2.setVisibility(0);
                    this.tvTip2.setVisibility(0);
                    this.tvShoulder2.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_doublekey_shoulder_abdiff));
                    this.tvTip2.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_doublekey_tip_abdiff));
                    this.tvShoulder2.setText(getString(R.string.ab_diff) + " " + getString(R.string.shoulder));
                    this.tvTip2.setText(getString(R.string.ab_diff) + " " + getString(R.string.tip));
                    this.keyInfo.setType(KeyType.DOUBLE_SIDE_KEY);
                    return;
                case R.id.rb_double_outside_key /* 2131362615 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_double_outside_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_double_outside_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.DOUBLE_OUTSIDE_GROOVE_KEY);
                    return;
                case R.id.rb_inside_key /* 2131362624 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.DOUBLE_INSIDE_GROOVE_KEY);
                    return;
                case R.id.rb_side_hole /* 2131362648 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_side_hole_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_side_hole_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    ToolSizeManager.setCutterSize(100);
                    ToolSizeManager.setDecoderSize(50);
                    this.keyInfo.setType(KeyType.SIDE_HOLE);
                    return;
                case R.id.rb_single_inside_left_key /* 2131362649 */:
                    ToolSizeManager.setDecoderSize(50);
                    ToolSizeManager.setCutterSize(100);
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_left_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_left_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.LEFT_GROOVE);
                    return;
                case R.id.rb_single_inside_right_key /* 2131362650 */:
                    ToolSizeManager.setCutterSize(100);
                    ToolSizeManager.setDecoderSize(50);
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_right_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_inside_right_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.RIGHT_GROOVE);
                    return;
                case R.id.rb_single_key /* 2131362651 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_singlekey_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_singlekey_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.SINGLE_SIDE_KEY);
                    return;
                case R.id.rb_single_outside_key /* 2131362652 */:
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_outside_up_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_outside_up_tip));
                    this.tvShoulder2.setVisibility(0);
                    this.tvTip2.setVisibility(0);
                    this.tvShoulder2.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_outside_down_shoulder));
                    this.tvTip2.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_single_outside_down_tip));
                    this.tvShoulder1.setText(getString(R.string.up_single_track) + " " + getString(R.string.shoulder));
                    this.tvTip1.setText(getString(R.string.up_single_track) + " " + getString(R.string.tip));
                    this.tvShoulder2.setText(getString(R.string.down_single_track) + " " + getString(R.string.shoulder));
                    this.tvTip2.setText(getString(R.string.down_single_track) + " " + getString(R.string.tip));
                    this.keyInfo.setType(KeyType.SINGLE_OUTSIDE_GROOVE_KEY);
                    return;
                case R.id.rb_three_groove_key /* 2131362659 */:
                    ToolSizeManager.setDecoderSize(50);
                    ToolSizeManager.setCutterSize(100);
                    this.tvShoulder1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_three_groove_key_shoulder));
                    this.tvTip1.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.keycopy_three_groove_key_tip));
                    this.tvShoulder1.setText(R.string.shoulder);
                    this.tvTip1.setText(R.string.tip);
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.keyInfo.setType(KeyType.THREE_GROOVE);
                    return;
                case R.id.rb_tubular_key /* 2131362664 */:
                    this.tvShoulder1.setBackgroundResource(0);
                    this.tvTip1.setBackgroundResource(0);
                    this.tvShoulder1.setText("");
                    this.tvTip1.setText("");
                    this.tvShoulder2.setVisibility(8);
                    this.tvTip2.setVisibility(8);
                    this.llClamp0.setVisibility(8);
                    this.llClamp1.setVisibility(0);
                    this.llClamp2.setVisibility(8);
                    this.keyInfo.setType(KeyType.TUBULAR_KEY);
                    this.llClamp1.setVisibility(0);
                    if (CuttingMachine.getInstance().isE9()) {
                        this.ivClamp1.setImageResource(R.drawable.a9_tubular_stop_duplicate_e9);
                        this.llClamp1.setTag(new ClampTag(Clamp.E9S3));
                        return;
                    } else {
                        this.llClamp1.setTag(new ClampTag(Clamp.S3));
                        this.ivClamp1.setImageResource(R.drawable.keycopy_clamp_s3_select);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private void saveCutNumber() {
        int i = SPUtils.getInt(SpKeys.CUT_NUMBER, 200) - 1;
        if (i <= 0) {
            EventBus.getDefault().post(new EventCenter(36));
            SPUtils.put(SpKeys.CERTIFICATION_STATUS, 1);
        }
        SPUtils.put(SpKeys.CUT_NUMBER, i);
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        if (isVisible()) {
            int eventCode = eventCenter.getEventCode();
            if (eventCode == 0) {
                calculatePoint(eventCenter);
                return;
            }
            if (eventCode == 43) {
                DuplicateBean duplicateBean = (DuplicateBean) eventCenter.getData();
                ToolSizeManager.setCutterSize(duplicateBean.getCutterSize());
                int i = SPUtils.getInt(SpKeys.SPEED + this.keyInfo.getKeyType().getValue(), this.keyInfo.getKeyType() == KeyType.DIMPLE_KEY ? 3 : 15);
                this.cutParams = DuplicateCutParams.DuplicateCutParamsBuilder.aDuplicateCutParams().withCutDepth(0).withCutSpeed(i).withKeyInfo(this.keyInfo).withDecoderSize(ToolSizeManager.getDecoderSize()).withCutterSize(ToolSizeManager.getCutterSize()).withClamp(this.decodeParams.getClamp()).withClampMode(this.decodeParams.getClampMode()).singleSideKeyCutDepthFix(duplicateBean.getCutDepthSingleKey()).withLayer(duplicateBean.getLayerCut()).build();
                Speed.setSpeed(i);
                showClearClampRemind(OperationType.cut);
                return;
            }
            if (eventCode == 47) {
                showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
                return;
            }
            if (eventCode == 49) {
                putUpDecoderRemindDialog();
                return;
            }
            if (eventCode != 50) {
                switch (eventCode) {
                    case 32:
                        handleOperationFinish(eventCenter);
                        return;
                    case 33:
                        showError(eventCenter);
                        return;
                    case 34:
                        showLoadingDialog(getString(R.string.waitting));
                        return;
                    default:
                        return;
                }
            }
            putDownDecoderRemindDialog(((Integer) eventCenter.getData()).intValue());
        }
    }

    public void putDownDecoderRemindDialog(final int i) {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.pull_decoder_down);
            remindDialog.setRemindMsg(getString(R.string.pull_the_decoder_down));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.1
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (!z) {
                        DuplicateKeyNewFragment.this.dismissLoadingDialog();
                    } else if (i == 0) {
                        DuplicateKeyNewFragment.this.startDecode();
                    } else {
                        DuplicateKeyNewFragment.this.startCut();
                    }
                }
            });
            remindDialog.show();
        }
    }

    public void putUpDecoderRemindDialog() {
        if (CuttingMachine.getInstance().isE9()) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.setRemindImg(R.drawable.push_decoder_up);
            remindDialog.setRemindMsg(getString(R.string.push_the_decoder_up));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.2
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        CuttingMachine.getInstance().duplicateCutFromCutterLocation(DuplicateKeyNewFragment.this.cutParams);
                        DuplicateKeyNewFragment duplicateKeyNewFragment = DuplicateKeyNewFragment.this;
                        duplicateKeyNewFragment.showLoadingDialog(duplicateKeyNewFragment.getString(R.string.waitting), true);
                        return;
                    }
                    DuplicateKeyNewFragment.this.dismissLoadingDialog();
                }
            });
            remindDialog.show();
        }
    }

    private void showError(EventCenter eventCenter) {
        dismissLoadingDialog();
        showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
    }

    private void calculatePoint(EventCenter eventCenter) {
        Bundle bundle = (Bundle) eventCenter.getData();
        int i = bundle.getInt("row");
        int i2 = bundle.getInt("depth");
        int i3 = bundle.getInt("space");
        int i4 = bundle.getInt("keyIndex");
        Log.i(TAG, "row: " + i + "  space:" + i3 + "  depth:" + i2 + "  keyIndex:" + i4);
        if (i2 < 0) {
            return;
        }
        DestPoint destPoint = new DestPoint(i3, i2);
        destPoint.setKeyIndex(i4);
        List<SinglePathData> pathDataList = this.keyInfo.getDecodeData().getPathDataList();
        if (i == 1) {
            int i5 = i4 * 2;
            pathDataList.get(i5).addDestPoint(destPoint);
            if (this.keyInfo.getKeyType() == KeyType.DOUBLE_SIDE_KEY && this.keyInfo.getSide() == 0) {
                pathDataList.get(i5 + 1).addDestPoint(destPoint.m600clone());
                return;
            }
            return;
        }
        pathDataList.get((i4 * 2) + 1).addDestPoint(destPoint);
    }

    private void handleOperationFinish(EventCenter eventCenter) {
        OperateType operateType = (OperateType) eventCenter.getData();
        if (operateType == OperateType.DUPLICATE_DECODE_EXECUTE) {
            decodeFinish();
        } else if (operateType == OperateType.DUPLICATE_CUT_EXECUTE) {
            addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    DuplicateKeyNewFragment.this.dismissLoadingDialog();
                }
            }));
            showLoadingDialog("100%", true);
            saveCutNumber();
            CutCountHelper.getInstance().addCutCount(this.keyInfo.getKeyType());
        }
    }

    private void decodeFinish() {
        List<SinglePathData> pathDataList = this.keyInfo.getDecodeData().getPathDataList();
        KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
        Iterator<SinglePathData> it = pathDataList.iterator();
        while (it.hasNext()) {
            DuplicateUtil.reducePoint(this.keyInfo.getKeyType(), this.keyInfo.getKeyAlign(), keyAlignInfo, it.next().getDestPointList());
        }
        new DuplicateNewCutDialog(getActivity(), this.decodeParams).show();
        dismissLoadingDialog();
    }

    private void showConductiveTestRemind(final OperationType operationType) {
        if (this.keyInfo.getKeyType() == KeyType.LEFT_GROOVE || this.keyInfo.getKeyType() == KeyType.RIGHT_GROOVE || this.keyInfo.getKeyType() == KeyType.TWO_GROOVE || this.keyInfo.getKeyType() == KeyType.THREE_GROOVE) {
            WarningDialog warningDialog = new WarningDialog(getContext());
            warningDialog.setCancelBtVisible(true);
            warningDialog.setRemind(getString(R.string.duplicate_conductive_test));
            warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.4
                @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        DuplicateKeyNewFragment.this.showClearClampRemind(operationType);
                    }
                }
            });
            warningDialog.show();
            return;
        }
        showClearClampRemind(operationType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showClearClampRemind(final OperationType operationType) {
        DuplicateDecodeParams duplicateDecodeParams = this.decodeParams;
        Clamp clamp = duplicateDecodeParams != null ? duplicateDecodeParams.getClamp() : null;
        if (clamp == null) {
            ToastUtil.showToast("NO Jaw selected");
            return;
        }
        final RemindDialog remindDialog = new RemindDialog(getContext());
        remindDialog.setCancleBtnVisibility(false);
        if (clamp == Clamp.S1_A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_a);
        } else if (clamp == Clamp.S1_B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_b);
        } else if (clamp == Clamp.S1_C) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_c);
        } else if (clamp == Clamp.S1_D) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s1_d);
        } else if (clamp == Clamp.S2_A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s2_a);
        } else if (clamp == Clamp.S2_B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s2_b);
        } else if (clamp == Clamp.S3) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_s3);
        } else if (clamp == Clamp.E9S1A) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_laser_key);
        } else if (clamp == Clamp.E9S1C) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_standard_key);
        } else if (clamp == Clamp.E9S2A || clamp == Clamp.E9S2B) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_single_key_e9);
        } else if (clamp == Clamp.E9S3) {
            remindDialog.setRemindImg(R.drawable.clear_clamp_tubular_e9);
        }
        if (this.keyInfo.getKeyType() == KeyType.TWO_GROOVE || this.keyInfo.getKeyType() == KeyType.THREE_GROOVE || this.keyInfo.getKeyType() == KeyType.LEFT_GROOVE || this.keyInfo.getKeyType() == KeyType.RIGHT_GROOVE || this.keyInfo.getKeyType() == KeyType.SIDE_HOLE) {
            remindDialog.setRemindMsg(getString(R.string.please_use_specify_decoder_cutter, "0.5mm", "1.0mm") + "\n" + getString(R.string.clean_the_groove_from_chips));
        } else if (operationType == OperationType.cut) {
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.please_use_specify_cutter, (ToolSizeManager.getCutterSize() / 100.0f) + "mm"));
            sb.append("\n");
            sb.append(getString(R.string.clean_the_groove_from_chips));
            remindDialog.setRemindMsg(sb.toString());
        } else {
            remindDialog.setRemindMsg(getString(R.string.clean_the_groove_from_chips));
        }
        remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment$$ExternalSyntheticLambda0
            @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            public final void onDialogButClick(boolean z) {
                DuplicateKeyNewFragment.this.m50xebbc060e(operationType, remindDialog, z);
            }
        });
        remindDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$showClearClampRemind$0$com-kkkcut-e20j-ui-fragment-duplicatekey-DuplicateKeyNewFragment, reason: not valid java name */
    public /* synthetic */ void m50xebbc060e(OperationType operationType, RemindDialog remindDialog, boolean z) {
        if (z) {
            if (operationType == OperationType.cut) {
                startCut();
            } else {
                startDecode();
            }
            remindDialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ClampTag {
        Clamp clamp;
        int clampMode;

        public ClampTag(Clamp clamp) {
            this.clamp = clamp;
        }

        public ClampTag(Clamp clamp, int i) {
            this.clamp = clamp;
            this.clampMode = i;
        }

        public Clamp getClamp() {
            return this.clamp;
        }

        public int getClampMode() {
            return this.clampMode;
        }
    }
}
