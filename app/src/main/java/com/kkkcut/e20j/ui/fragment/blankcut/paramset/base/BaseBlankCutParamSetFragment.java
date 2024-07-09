package com.kkkcut.e20j.ui.fragment.blankcut.paramset.base;

import android.os.Bundle;
import android.view.View;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.MachineInfo;
import com.cutting.machine.OperateType;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.S8;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampF;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.S1B;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.error.ErrorBean;
import com.cutting.machine.utils.UnitConvertUtil;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;

import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutSpeedUtils;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutType;
import com.kkkcut.e20j.us.R;
import com.spl.key.JawClass;
import com.spl.key.Key;
import com.spl.key.mdKeyBlankClass;
import com.spl.key.mdKeyLocationPointClass;
import com.spl.key.KeyBlankCutPathClass;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class BaseBlankCutParamSetFragment extends BaseBackFragment {
    public static final String BLANK_CUT = "BLANK CUT";
    public static final String PARAM = "PARAM";
    BlankCutBean blankCutBean;
    public boolean isSecondSide;

    public abstract boolean checkHaveRiskCutClamp(ClampF clampF);

    public abstract void onCutFinish();

    public abstract boolean readyStartCut();

    public abstract String setKeyLocationPath();

    public abstract void setMdKeyBlankClass(mdKeyBlankClass mdkeyblankclass);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    public void initViewsAndEvents() {
        this.blankCutBean = (BlankCutBean) getArguments().getParcelable(BLANK_CUT);
    }

    public BlankCutType getBlankCutType() {
        BlankCutBean blankCutBean = this.blankCutBean;
        if (blankCutBean != null) {
            return blankCutBean.getBlankCutType();
        }
        return null;
    }

    @Override // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    public String setTitleStr() {
        BlankCutBean blankCutBean = this.blankCutBean;
        if (blankCutBean != null) {
            return blankCutBean.name;
        }
        return null;
    }

    public void onClick(View view) {
        if (view.getId() != R.id.bt_cut) {
            return;
        }
        startCut();
    }

    public void startCut() {
        if (!AppUtil.isApkInDebug(getContext())) {
            if (MachineInfo.isChineseMachine()) {
                if (!ClampManager.getInstance().checkHasCalibrated(Clamp.S1_B)) {
                    return;
                }
            } else if (!ClampManager.getInstance().checkHasCalibrated(Clamp.S8)) {
                return;
            }
        }
        if (readyStartCut()) {
            if (MachineInfo.isChineseMachine()) {
                if (checkHaveRiskCutClamp(ClampManager.getInstance().getS1B())) {
                    return;
                }
            } else if (checkHaveRiskCutClamp(ClampManager.getInstance().getS8())) {
                return;
            }
            new BlankCutDialog(getActivity(), getBlankCutType()).show();
            return;
        }
        ToastUtil.showToast(R.string.please_complete_the_data);
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        KeyInfo keyInfo = new KeyInfo();
        char c = 3;
        keyInfo.setType(3);
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 1) {
            Bundle bundle = (Bundle) eventCenter.getData();
            ToolSizeManager.setCutterSize(bundle.getInt("cutterSize", 200));
            this.isSecondSide = bundle.getBoolean("isSecondSide");
            showLoadingDialog(getString(R.string.cutting), true);
            OperationManager.getInstance().start(setKeyLocationPath(), keyInfo, OperateType.MODIFY_KEY_BLANK_LOCATION);
            return;
        }
        if (eventCode == 47) {
            showLoadingDialog(((Integer) eventCenter.getData()).intValue() + "%", true);
            return;
        }
        if (eventCode != 32) {
            if (eventCode != 33) {
                return;
            }
            dismissLoadingDialog();
            this.isSecondSide = false;
            showErrorDialog(getContext(), (ErrorBean) eventCenter.getData());
            return;
        }
        OperateType operateType = (OperateType) eventCenter.getData();
        if (operateType == OperateType.MODIFY_KEY_BLANK_LOCATION) {
            OperationManager.getInstance().start(setCutterLocationPath(), keyInfo, OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH);
            return;
        }
        if (operateType == OperateType.MODIFY_KEY_BLANK_CUTTER_HIGH) {
            S8 s8 = ClampManager.getInstance().getS8();
            mdKeyLocationPointClass mdKeyLocationPointClass = getMdKeyLocationPointClass(s8);
            mdKeyBlankClass mdkeyblankclass = new mdKeyBlankClass();
            setMdKeyBlankClass(mdkeyblankclass);
            JawClass.JAW_S8 jaw_s8 = getJaw_s8(s8);
            String[][] strArr = null;
            switch (C14862.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[getBlankCutType().ordinal()]) {
                case 1:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_TopPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 2:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_WidthThickPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 3:
                case 4:
                case 5:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_WidthThickPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 6:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_KeyHeadDrillingPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize());
                    break;
                case 7:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_LeftGrovePath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 8:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_RightGrovePath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 9:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_TipCutPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 10:
                case 11:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_40KTo80KPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 12:
                case 13:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_HY18Path(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 14:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_InternalGroveCutPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
                case 15:
                case 16:
                    strArr = KeyBlankCutPathClass.INSTANCE.GetKeyBlankCut_KW16ToKW15Path(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(getBlankCutType()), mdKeyLocationPointClass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
                    break;
            }
            ArrayList arrayList = new ArrayList();
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String[] strArr2 = strArr[i];
                arrayList.add(new StepBean(strArr2[0], Integer.parseInt(strArr2[1]), Integer.parseInt(strArr2[2]), Integer.parseInt(strArr2[c]), Integer.parseInt(strArr2[4]), strArr2[5], strArr2[6]));
                i++;
                c = 3;
            }
            if (ClampManager.getInstance().checkHaveRiskCutClamp(arrayList, ToolSizeManager.getCutterSize())) {
                return;
            }
            OperationManager.getInstance().startExecution(arrayList, OperateType.MODIFY_KEY_BLANK_EXECUTE);
            return;
        }
        // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment.1
// io.reactivex.functions.Consumer
        addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(l -> BaseBlankCutParamSetFragment.this.dismissLoadingDialog(), th -> this.dismissLoadingDialog()));
        showLoadingDialog("100%", true);
        this.isSecondSide = !this.isSecondSide;
        onCutFinish();
    }

    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment$2 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C14862 {
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

    private mdKeyLocationPointClass getMdKeyLocationPointClass(S8 s8) {
        int length;
        mdKeyLocationPointClass mdkeylocationpointclass = new mdKeyLocationPointClass();
        KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
        int i = ClampManager.getInstance().getDC().getxDistance();
        int i2 = ClampManager.getInstance().getDC().getyDistance();
        mdkeylocationpointclass.setSideKeyShoulder(keyAlignInfo.getShoulder() + i2);
        mdkeylocationpointclass.setSideLeft((keyAlignInfo.getLeft() + i) - ToolSizeManager.getDecoderRadius2());
        mdkeylocationpointclass.setSideRight(keyAlignInfo.getRight() + i + ToolSizeManager.getDecoderRadius2());
        mdkeylocationpointclass.setSideZ(keyAlignInfo.getClampFace());
        mdkeylocationpointclass.setSideKeyTopSurface_Cut(keyAlignInfo.getKeyFace());
        mdkeylocationpointclass.setSideKeyCenter(keyAlignInfo.getKeyCenterCutter());
        if (getBlankCutType() == BlankCutType.KEY_HEAD || getBlankCutType() == BlankCutType.DRILLING) {
            if (MachineInfo.isChineseMachine()) {
                length = UnitConvertUtil.cmm2StepY(3750);
            } else {
                length = s8.getLength();
            }
            mdkeylocationpointclass.setSideTip((keyAlignInfo.getShoulder() - length) + i2);
        } else {
            mdkeylocationpointclass.setSideTip(keyAlignInfo.getTip() + i2);
        }
        return mdkeylocationpointclass;
    }

    private JawClass.JAW_S8 getJaw_s8(S8 s8) {
        JawClass.JAW_S8 jaw_s8 = new JawClass.JAW_S8();
        if (MachineInfo.isChineseMachine()) {
            S1B s1b = ClampManager.getInstance().getS1B();
            jaw_s8.setJawS8_ClampLength(UnitConvertUtil.cmm2StepY(3750));
            jaw_s8.setJawS8_FirstStepDepth(s1b.getHigh1());
            jaw_s8.setJawS8_SecondStepDepth(s1b.getHigh1() + s1b.getHigh2());
        } else {
            jaw_s8.setJawS8_ClampLength(s8.getLength());
            jaw_s8.setJawS8_FirstStepDepth(s8.getHeight1());
            jaw_s8.setJawS8_SecondStepDepth(s8.getHeight2());
        }
        return jaw_s8;
    }

    public String setCutterLocationPath() {
        return MachineInfo.isChineseMachine() ? "keyblank/cutter/S1-B(Top).json" : "keyblank/cutter/S8.json";
    }
}