package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.example.spl_key_sdklibrary.JawClass;
import com.example.spl_key_sdklibrary.Key;
import com.example.spl_key_sdklibrary.KeyBlankCutPathClass;
import com.example.spl_key_sdklibrary.mdKeyBlankClass;
import com.example.spl_key_sdklibrary.mdKeyLocationPointClass;
import com.kkkcut.e20j.androidquick.tool.AppUtil;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.tool.ToastUtil;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.dialog.RemindDialog;
import com.kkkcut.e20j.ui.dialog.WarningDialog;
import com.kkkcut.e20j.ui.fragment.BaseBackFragment;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutSpeedUtils;
import com.kkkcut.e20j.ui.fragment.blankcut.BlankCutType;
import com.kkkcut.e20j.us.R;
import com.liying.core.KeyAlignInfo;
import com.liying.core.MachineInfo;
import com.liying.core.OperateType;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.clamp.S1B;
import com.liying.core.clamp.S8;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorBean;
import com.liying.core.error.ErrorBeanFactory;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.utils.UnitConvertUtil;
import io.reactivex.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class BlankCutHeadFragment extends BaseBackFragment {
    public static final String MODIFY_TYPE = "modifyType";

    @BindView(R.id.blank_cut_head)
    ViewGroup blankCutHead;

    @BindView(R.id.blank_cut_thickness)
    ViewStub blankCutThickness;
    private BlankCutType blankCutType;

    @BindView(R.id.blank_cut_width)
    ViewStub blankCutWidth;

    @BindView(R.id.et_head_groove_location)
    EditText etHeadGrooveLocation;

    @BindView(R.id.et_head_groove_thickness)
    EditText etHeadGrooveThickness;

    @BindView(R.id.et_head_length)
    EditText etHeadLength;

    @BindView(R.id.et_head_thickness)
    EditText etHeadThickness;

    @BindView(R.id.et_head_width)
    EditText etHeadWidth;
    private boolean isSecondSide;
    private int keyBlankThick;
    private int keyHeadCardSlotPosition;
    private int keyHeadGrooveThick;
    private int keyHeadLength;
    private int keyHeadThick;
    private int keyHeadWidth;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_modify_key_blank;
    }

    public static BlankCutHeadFragment newInstance(BlankCutBean blankCutBean) {
        Bundle bundle = new Bundle();
        BlankCutHeadFragment blankCutHeadFragment = new BlankCutHeadFragment();
        bundle.putParcelable(MODIFY_TYPE, blankCutBean);
        blankCutHeadFragment.setArguments(bundle);
        return blankCutHeadFragment;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        this.blankCutType = ((BlankCutBean) getArguments().getParcelable(MODIFY_TYPE)).getBlankCutType();
        int i = AnonymousClass5.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[this.blankCutType.ordinal()];
        if (i == 1) {
            this.blankCutHead.setVisibility(0);
            this.blankCutThickness.setVisibility(8);
            this.blankCutWidth.setVisibility(8);
            return;
        }
        if (i == 2) {
            this.blankCutHead.setVisibility(8);
            this.blankCutThickness.setVisibility(0);
            this.blankCutWidth.setVisibility(8);
            final CheckBox checkBox = (CheckBox) getView().findViewById(R.id.cb_cut_more_thick);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        WarningDialog warningDialog = new WarningDialog(BlankCutHeadFragment.this.getContext());
                        warningDialog.setRemind(BlankCutHeadFragment.this.getString(R.string.blankcut_thick_width_extra_cut));
                        warningDialog.setCancelBtVisible(true);
                        warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.1.1
                            @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                            public void onDialogButClick(boolean z2) {
                                if (z2) {
                                    return;
                                }
                                checkBox.setChecked(false);
                            }
                        });
                        warningDialog.show();
                    }
                }
            });
            return;
        }
        if (i == 3 || i == 4 || i == 5) {
            this.blankCutHead.setVisibility(8);
            this.blankCutThickness.setVisibility(8);
            this.blankCutWidth.setVisibility(0);
            final CheckBox checkBox2 = (CheckBox) getView().findViewById(R.id.cb_cut_more_width);
            checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.2
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        WarningDialog warningDialog = new WarningDialog(BlankCutHeadFragment.this.getContext());
                        warningDialog.setRemind(BlankCutHeadFragment.this.getString(R.string.blankcut_thick_width_extra_cut));
                        warningDialog.setCancelBtVisible(true);
                        warningDialog.setDialogBtnCallback(new WarningDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.2.1
                            @Override // com.kkkcut.e20j.ui.dialog.WarningDialog.DialogBtnCallBack
                            public void onDialogButClick(boolean z2) {
                                if (z2) {
                                    return;
                                }
                                checkBox2.setChecked(false);
                            }
                        });
                        warningDialog.show();
                    }
                }
            });
            if (this.blankCutType == BlankCutType.k9ToLxp90) {
                ((EditText) getView().findViewById(R.id.et_key_width)).setText("690");
            } else if (this.blankCutType == BlankCutType.Toyota80K) {
                ((EditText) getView().findViewById(R.id.et_key_width)).setText("780");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment$5, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass5 {
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
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter eventCenter) {
        float f;
        int i;
        final KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType(3);
        int eventCode = eventCenter.getEventCode();
        if (eventCode == 1) {
            Bundle bundle = (Bundle) eventCenter.getData();
            ToolSizeManager.setCutterSize(bundle.getInt("cutterSize", 200));
            this.isSecondSide = bundle.getBoolean("isSecondSide");
            showLoadingDialog(getString(R.string.cutting), true);
            OperationManager.getInstance().start(getDecoderLocationPath(this.blankCutType), keyInfo, OperateType.MODIFY_KEY_BLANK_LOCATION);
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
            mdKeyLocationPointClass mdkeylocationpointclass = new mdKeyLocationPointClass();
            KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
            int i2 = ClampManager.getInstance().getDC().getxDistance();
            LogUtil.i(TAG, "xDistance: " + i2);
            int i3 = ClampManager.getInstance().getDC().getyDistance();
            if (this.blankCutType == BlankCutType.THICKNESS) {
                if (((CheckBox) getView().findViewById(R.id.cb_cut_more_thick)).isChecked()) {
                    f = MachineData.dYScale;
                    i = (int) (500.0f / f);
                }
                i = 0;
            } else {
                if ((((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) && ((CheckBox) getView().findViewById(R.id.cb_cut_more_width)).isChecked()) {
                    f = MachineData.dYScale;
                    i = (int) (500.0f / f);
                }
                i = 0;
            }
            mdkeylocationpointclass.setSideKeyShoulder(keyAlignInfo.getShoulder() + i3 + i);
            mdkeylocationpointclass.setSideLeft((keyAlignInfo.getLeft() + i2) - ToolSizeManager.getDecoderRadius2());
            mdkeylocationpointclass.setSideRight(keyAlignInfo.getRight() + i2 + ToolSizeManager.getDecoderRadius2());
            mdkeylocationpointclass.setSideZ(keyAlignInfo.getClampFace());
            mdkeylocationpointclass.setSideKeyTopSurface_Cut(keyAlignInfo.getKeyFace());
            mdkeylocationpointclass.setSideKeyCenter(keyAlignInfo.getKeyCenterCutter());
            mdKeyBlankClass mdkeyblankclass = new mdKeyBlankClass();
            mdkeyblankclass.setKeyWidth(this.keyHeadWidth);
            mdkeyblankclass.setKeylength(this.keyHeadLength);
            mdkeyblankclass.setKeyCardSlotPosition(this.keyHeadCardSlotPosition);
            mdkeyblankclass.setKeyThick(this.keyHeadThick);
            mdkeyblankclass.setKeyCardSlotThick(this.keyHeadGrooveThick);
            mdkeyblankclass.setKeyBlankThick(this.keyBlankThick);
            S8 s8 = ClampManager.getInstance().getS8();
            JawClass.JAW_S8 jaw_s8 = getJaw_s8(s8);
            if (dataInvalid(keyAlignInfo)) {
                dismissLoadingDialog();
                showErrorDialog(getContext(), ErrorBeanFactory.getErrorBean(0, getString(R.string.the_key_is_at_or_below_the_desired_dimension_already)));
                return;
            }
            String[][] strArr = null;
            if (this.blankCutType == BlankCutType.KEY_HEAD) {
                mdkeyblankclass.setCutFaceSettingType(0);
                mdkeyblankclass.setRepairKeyBlakType(0);
                mdkeylocationpointclass.setSideTip((keyAlignInfo.getShoulder() - s8.getLength()) + i3);
                strArr = KeyBlankCutPathClass.GetKeyBlankCut_TopPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(this.blankCutType), mdkeylocationpointclass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
            } else if (this.blankCutType == BlankCutType.THICKNESS) {
                mdkeyblankclass.setRepairKeyBlakType(1);
                if (this.isSecondSide) {
                    mdkeyblankclass.setCutFaceSettingType(0);
                } else {
                    mdkeyblankclass.setCutFaceSettingType(1);
                }
                mdkeylocationpointclass.setSideTip(keyAlignInfo.getTip() + i3);
                strArr = KeyBlankCutPathClass.GetKeyBlankCut_WidthThickPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(this.blankCutType), mdkeylocationpointclass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
            } else if (((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) {
                mdkeyblankclass.setRepairKeyBlakType(2);
                if (this.isSecondSide) {
                    mdkeyblankclass.setCutFaceSettingType(0);
                } else {
                    mdkeyblankclass.setCutFaceSettingType(1);
                }
                mdkeylocationpointclass.setSideTip(keyAlignInfo.getTip() + i3);
                strArr = KeyBlankCutPathClass.GetKeyBlankCut_WidthThickPath(Key.enumMachineType.alpha, BlankCutSpeedUtils.getSpeed(this.blankCutType), mdkeylocationpointclass, mdkeyblankclass, ToolSizeManager.getCutterSize(), jaw_s8);
            }
            ArrayList arrayList = new ArrayList();
            for (String[] strArr2 : strArr) {
                Log.i(TAG, "onEventComing: " + Arrays.toString(strArr2));
                arrayList.add(new StepBean(strArr2[0], Integer.parseInt(strArr2[1]), Integer.parseInt(strArr2[2]), Integer.parseInt(strArr2[3]), Integer.parseInt(strArr2[4]), strArr2[5], strArr2[6]));
            }
            if (ClampManager.getInstance().checkHaveRiskCutClamp(arrayList, ToolSizeManager.getCutterSize())) {
                return;
            }
            OperationManager.getInstance().startExecution(arrayList, OperateType.MODIFY_KEY_BLANK_EXECUTE);
            return;
        }
        addDisposable(Observable.timer(500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) throws Exception {
                BlankCutHeadFragment.this.dismissLoadingDialog();
            }
        }));
        showLoadingDialog("100%", true);
        if (!this.isSecondSide && this.blankCutType != BlankCutType.KEY_HEAD) {
            RemindDialog remindDialog = new RemindDialog(getContext());
            remindDialog.show();
            if (((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) {
                remindDialog.setRemindImg(R.drawable.clamp_remind_blank_cut_width);
            } else {
                remindDialog.setRemindImg(R.drawable.clamp_remind_blank_cut_thickness);
            }
            remindDialog.setRemindMsg(getString(R.string.turn_over_second_side));
            remindDialog.setDialogBtnCallback(new RemindDialog.DialogBtnCallBack() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment.4
                @Override // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
                public void onDialogButClick(boolean z) {
                    if (z) {
                        BlankCutHeadFragment.this.isSecondSide = true;
                        BlankCutHeadFragment blankCutHeadFragment = BlankCutHeadFragment.this;
                        blankCutHeadFragment.showLoadingDialog(blankCutHeadFragment.getString(R.string.cutting), true);
                        OperationManager operationManager = OperationManager.getInstance();
                        BlankCutHeadFragment blankCutHeadFragment2 = BlankCutHeadFragment.this;
                        operationManager.start(blankCutHeadFragment2.getDecoderLocationPath(blankCutHeadFragment2.blankCutType), keyInfo, OperateType.MODIFY_KEY_BLANK_LOCATION);
                    }
                }
            });
            return;
        }
        this.isSecondSide = false;
    }

    private String setCutterLocationPath() {
        return MachineInfo.isChineseMachine() ? "keyblank/cutter/S1-B(Top).json" : "keyblank/cutter/S8.json";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDecoderLocationPath(BlankCutType blankCutType) {
        int i = AnonymousClass5.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[blankCutType.ordinal()];
        return i != 1 ? i != 2 ? (i == 3 || i == 4 || i == 5) ? MachineInfo.isChineseMachine() ? "duplicate/decoder/S1-B-D(ThreeendsTop).json" : "keyblank/decoder/S8-3.json" : "" : MachineInfo.isChineseMachine() ? "duplicate/decoder/S1-B(ThreeendsTop).json" : "keyblank/decoder/S8-2.json" : "keyblank/decoder/S8-1.json";
    }

    private boolean checkHaveRiskCutClamp(S8 s8) {
        if (this.blankCutType != BlankCutType.THICKNESS) {
            if ((((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) && this.keyBlankThick / MachineData.dZScale < s8.getHeight2() + 10) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
                return true;
            }
        } else if (this.keyBlankThick / MachineData.dZScale < s8.getHeight1() + 10) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS8);
            return true;
        }
        return false;
    }

    private boolean checkHaveRiskCutClamp(S1B s1b) {
        if (this.blankCutType != BlankCutType.THICKNESS) {
            if ((((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) && this.keyBlankThick / MachineData.dZScale < s1b.getHigh2() + s1b.getHigh1() + 10) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
                return true;
            }
        } else if (this.keyBlankThick / MachineData.dZScale < s1b.getHigh1() + 10) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
            return true;
        }
        return false;
    }

    private boolean dataInvalid(KeyAlignInfo keyAlignInfo) {
        int height2;
        int height1;
        if (this.blankCutType == BlankCutType.THICKNESS) {
            if (MachineInfo.isChineseMachine()) {
                height1 = ClampManager.getInstance().getS1B().getHigh1();
            } else {
                height1 = ClampManager.getInstance().getS8().getHeight1();
            }
            if (((int) (((keyAlignInfo.getClampFace() + height1) - keyAlignInfo.getKeyFace()) * MachineData.dZScale)) < this.keyBlankThick) {
                return true;
            }
        } else {
            if (((this.blankCutType == BlankCutType.WIDTH) | (this.blankCutType == BlankCutType.k9ToLxp90)) || this.blankCutType == BlankCutType.Toyota80K) {
                if (MachineInfo.isChineseMachine()) {
                    height2 = ClampManager.getInstance().getS1B().getHigh1() + ClampManager.getInstance().getS1B().getHigh2();
                } else {
                    height2 = ClampManager.getInstance().getS8().getHeight2();
                }
                if (((int) (((keyAlignInfo.getClampFace() + height2) - keyAlignInfo.getKeyFace()) * MachineData.dZScale)) < this.keyBlankThick) {
                    return true;
                }
            }
        }
        return false;
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

    @OnClick({R.id.bt_cut})
    public void onClick(View view) {
        if (view.getId() != R.id.bt_cut) {
            return;
        }
        if (!AppUtil.isApkInDebug(getContext())) {
            if (MachineInfo.isChineseMachine()) {
                if (!ClampManager.getInstance().checkHasCalibrated(Clamp.S1_B)) {
                    return;
                }
            } else if (!ClampManager.getInstance().checkHasCalibrated(Clamp.S8)) {
                return;
            }
        }
        ToolSizeManager.setDecoderSize(100);
        int i = AnonymousClass5.$SwitchMap$com$kkkcut$e20j$ui$fragment$blankcut$BlankCutType[this.blankCutType.ordinal()];
        if (i == 1) {
            String trim = this.etHeadWidth.getText().toString().trim();
            String trim2 = this.etHeadLength.getText().toString().trim();
            String trim3 = this.etHeadThickness.getText().toString().trim();
            String trim4 = this.etHeadGrooveLocation.getText().toString().trim();
            String trim5 = this.etHeadGrooveThickness.getText().toString().trim();
            if (TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2) || TextUtils.isEmpty(trim3) || TextUtils.isEmpty(trim4) || TextUtils.isEmpty(trim5)) {
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
            this.keyHeadWidth = Integer.parseInt(trim);
            this.keyHeadLength = Integer.parseInt(trim2);
            this.keyHeadThick = Integer.parseInt(trim3);
            this.keyHeadCardSlotPosition = Integer.parseInt(trim4);
            this.keyHeadGrooveThick = Integer.parseInt(trim5);
        } else if (i == 2) {
            String trim6 = ((EditText) getView().findViewById(R.id.et_key_thickness)).getText().toString().trim();
            if (TextUtils.isEmpty(trim6)) {
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
            this.keyBlankThick = Integer.parseInt(trim6);
        } else if (i == 3 || i == 4 || i == 5) {
            String trim7 = ((EditText) getView().findViewById(R.id.et_key_width)).getText().toString().trim();
            if (TextUtils.isEmpty(trim7)) {
                ToastUtil.showToast(R.string.please_complete_the_data);
                return;
            }
            this.keyBlankThick = Integer.parseInt(trim7);
        }
        if (MachineInfo.isChineseMachine()) {
            if (checkHaveRiskCutClamp(ClampManager.getInstance().getS1B())) {
                return;
            }
        } else if (checkHaveRiskCutClamp(ClampManager.getInstance().getS8())) {
            return;
        }
        new BlankCutDialog(getActivity(), this.blankCutType).show();
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return getString(R.string.blank_cut);
    }
}
