package com.liying.core.operation.duplicateCut;

import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.OperateType;
import com.liying.core.bean.DestPoint;
import com.liying.core.bean.EventBean;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.KeyType;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.ClampManager;
import com.liying.core.communication.OperationManager;
import com.liying.core.operation.Operation;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.blankCutPath.TubularKeyCutPath;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleOutsideKeyCut;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleSideKeyCut;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateInsideGrooveKeyCut;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateSideHoleKeyCut;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleOutsideKeyCut;
import com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleSideKeyCut;
import com.liying.core.operation.duplicateDecode.TubularKeyinfoUtil;
import com.liying.core.speed.Speed;
import com.liying.core.utils.AssetsJsonUtils;
import com.liying.core.utils.DecoderPositionUtils;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/* loaded from: classes2.dex */
public class DuplicateCut implements Operation {
    DuplicateCutParams cutParams;

    @Override // com.liying.core.operation.Operation
    public void operationFinish() {
    }

    public DuplicateCut(DuplicateCutParams duplicateCutParams) {
        this.cutParams = duplicateCutParams;
        int cutSpeed = duplicateCutParams.getCutSpeed();
        if (cutSpeed > 0) {
            Speed.setSpeed(cutSpeed);
        }
    }

    @Override // com.liying.core.operation.Operation
    public void handleOperate(Observable observable, Object obj) {
        EventBean eventBean = (EventBean) obj;
        OperateType operateType = eventBean.getOperateType();
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION) {
            if (DecoderPositionUtils.isDownPosition(((Integer) eventBean.getData()).intValue())) {
                decoderLocation();
                return;
            } else {
                OperationManager.getInstance().sendEventMessage(50, 1);
                return;
            }
        }
        if (operateType == OperateType.DUPLICATE_CUT_LOCATION) {
            if (this.cutParams.isHighHandleMode()) {
                checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_CUTTER_LOCATION);
                return;
            } else {
                detectCutterHigh();
                return;
            }
        }
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_CUTTER_LOCATION) {
            if (!DecoderPositionUtils.isUpPosition(((Integer) eventBean.getData()).intValue())) {
                OperationManager.getInstance().sendEventMessage(49, 1);
                return;
            } else {
                detectCutterHigh();
                return;
            }
        }
        if (operateType == OperateType.DUPLICATE_CUT_CUTTER_HIGH) {
            List<StepBean> cutPoints = getCutPoints();
            if (ClampManager.getInstance().checkHaveRiskCutClamp(cutPoints, this.cutParams.getCutterSize())) {
                return;
            }
            OperationManager.getInstance().start(this.cutParams, cutPoints, OperateType.DUPLICATE_CUT_EXECUTE);
            return;
        }
        if (operateType == OperateType.DUPLICATE_CUT_EXECUTE) {
            operationFinish();
        }
    }

    private void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    /* renamed from: com.liying.core.operation.duplicateCut.DuplicateCut$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19951 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_INSIDE_GROOVE_KEY.ordinal()] = 7;
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
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_SIDE_KEY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private List<StepBean> getCutPoints() {
        ArrayList arrayList = new ArrayList();
        switch (C19951.$SwitchMap$com$liying$core$bean$KeyType[this.cutParams.getKeyInfo().getKeyType().ordinal()]) {
            case 1:
                return new DuplicateDoubleSideKeyCut(this.cutParams).getCutPathSteps();
            case 2:
                return new DuplicateDoubleOutsideKeyCut(this.cutParams).getCutPathSteps();
            case 3:
                return new DuplicateSingleOutsideKeyCut(this.cutParams).getCutPathSteps();
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return new DuplicateInsideGrooveKeyCut(this.cutParams).getCutPathSteps();
            case 10:
                KeyInfo tubularKey = TubularKeyinfoUtil.getTubularKey();
                List<DestPoint> destPointList = this.cutParams.getKeyInfo().getDecodeData().getPathDataList().get(0).getDestPointList();
                String str = "";
                for (int i = 0; i < destPointList.size(); i++) {
                    String codeByDepth = tubularKey.getCodeByDepth(tubularKey.getDepthList().get(0), tubularKey.getDepthNameList().get(0), UnitConvertUtil.step2CmmZ(destPointList.get(i).getDepth()), false);
                    str = i == destPointList.size() - 1 ? str + codeByDepth + ";" : str + codeByDepth + ",";
                }
                tubularKey.setKeyToothCode(str);
                return new TubularKeyCutPath(OperationManager.getInstance().getKeyAlignInfo(), new DataParam.Builder().keyInfo(tubularKey).clamp(this.cutParams.getClamp()).decoderSize(this.cutParams.getDecoderSize()).cutterSize(this.cutParams.getCutterSize()).cutSpeed(this.cutParams.getCutSpeed()).build()).getCutPathSteps();
            case 11:
                return new DuplicateSingleSideKeyCut(this.cutParams).getCutPathSteps();
            case 12:
                return new DuplicateSideHoleKeyCut(this.cutParams).getCutPathSteps();
            default:
                return arrayList;
        }
    }

    private void detectCutterHigh() {
        OperationManager.getInstance().start(this.cutParams, AssetsJsonUtils.getDuplicateCutCutterHeightLocationSteps(CuttingMachine.getInstance().getContext(), this.cutParams), OperateType.DUPLICATE_CUT_CUTTER_HIGH);
    }

    public void duplicateCut() {
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            decoderLocation();
        }
    }

    private boolean isDetectDecoder() {
        return CuttingMachine.getInstance().isE9() && !CuttingMachine.getInstance().isNotDetectDecoder();
    }

    public void duplicateCutFromCutterLocation() {
        if (this.cutParams.isHighHandleMode()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_CUTTER_LOCATION);
        } else {
            detectCutterHigh();
        }
    }

    private void decoderLocation() {
        OperationManager.getInstance().clearAllState();
        OperationManager.getInstance().start(this.cutParams, AssetsJsonUtils.getDuplicateCutLocationSteps(CuttingMachine.getInstance().getContext(), this.cutParams), OperateType.DUPLICATE_CUT_LOCATION);
    }
}
