package com.liying.core.operation.cut;

import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.OperateType;
import com.liying.core.bean.EventBean;
import com.liying.core.communication.OperationManager;
import com.liying.core.operation.Operation;
import com.liying.core.operation.cut.cutpath.KeyPathFactory;
import com.liying.core.speed.Speed;
import com.liying.core.utils.AssetsJsonUtils;
import com.liying.core.utils.DecoderPositionUtils;
import java.util.Observable;

/* loaded from: classes2.dex */
public class DataCut implements Operation {
    private CutType cutType = CutType.Cut;
    private DataParam dataParams;

    @Override // com.liying.core.operation.Operation
    public void operationFinish() {
    }

    public DataCut(DataParam dataParam) {
        this.dataParams = dataParam;
        int cutSpeed = dataParam.getCutSpeed();
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
        if (operateType == OperateType.KEY_BLANK_CUT_LOCATION) {
            if (this.dataParams.isHighHandleMode()) {
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
        if (operateType == OperateType.KEY_BLANK_CUT_CUTTER_HIGH) {
            if (this.cutType == CutType.Location || this.dataParams.getKeyInfo().getType() == 7) {
                return;
            }
            OperationManager.getInstance().start(this.dataParams, KeyPathFactory.getCutPath(OperationManager.getInstance().getKeyAlignInfo(), this.dataParams), OperateType.KEY_BLANK_CUT_EXECUTE);
            return;
        }
        if (operateType == OperateType.KEY_BLANK_CUT_EXECUTE) {
            operationFinish();
        }
    }

    private void detectCutterHigh() {
        OperationManager.getInstance().start(this.dataParams, AssetsJsonUtils.getKeyCutCutterLocationSteps(CuttingMachine.getInstance().getContext(), this.dataParams), OperateType.KEY_BLANK_CUT_CUTTER_HIGH);
    }

    public void cut() {
        this.cutType = CutType.Cut;
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            decoderLocation();
        }
    }

    private void decoderLocation() {
        OperationManager.getInstance().clearAllState();
        OperationManager.getInstance().start(this.dataParams, AssetsJsonUtils.getKeyCutLocationSteps(CuttingMachine.getInstance().getContext(), this.dataParams), OperateType.KEY_BLANK_CUT_LOCATION);
    }

    private void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    public void locationOnly() {
        this.cutType = CutType.Location;
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            decoderLocation();
        }
    }

    private boolean isDetectDecoder() {
        return CuttingMachine.getInstance().isE9() && !CuttingMachine.getInstance().isNotDetectDecoder();
    }

    public void locationCutterHeightOnly() {
        this.cutType = CutType.Location;
        if (this.dataParams.isHighHandleMode()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_CUTTER_LOCATION);
        } else {
            detectCutterHigh();
        }
    }

    public void startCutFromCutterLocation() {
        this.cutType = CutType.Cut;
        checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_CUTTER_LOCATION);
    }

    public void cutNoLocation() {
        OperationManager.getInstance().start(this.dataParams, KeyPathFactory.getCutPath(OperationManager.getInstance().getKeyAlignInfo(), this.dataParams), OperateType.KEY_BLANK_CUT_EXECUTE);
    }
}