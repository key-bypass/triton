package com.liying.core.operation.decode;

import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.OperateType;
import com.liying.core.bean.EventBean;
import com.liying.core.communication.OperationManager;
import com.liying.core.operation.Operation;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.utils.AssetsJsonUtils;
import com.liying.core.utils.DecoderPositionUtils;
import java.util.Observable;

/* loaded from: classes2.dex */
public class DataDecode implements Operation {
    private DataParam decodeParams;
    private boolean locationOnly;

    @Override // com.liying.core.operation.Operation
    public void operationFinish() {
    }

    @Override // com.liying.core.operation.Operation
    public void handleOperate(Observable observable, Object obj) {
        EventBean eventBean = (EventBean) obj;
        OperateType operateType = eventBean.getOperateType();
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION) {
            if (DecoderPositionUtils.isDownPosition(((Integer) eventBean.getData()).intValue())) {
                location(this.decodeParams);
                return;
            } else {
                OperationManager.getInstance().sendEventMessage(50, 0);
                return;
            }
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_LOCATION) {
            if (this.locationOnly) {
                operationFinish();
                return;
            } else {
                OperationManager.getInstance().start(this.decodeParams, AssetsJsonUtils.getKeyDecodePathSteps(this.decodeParams.getKeyInfo()), OperateType.KEY_BLANK_DECODE_EXECUTE);
                return;
            }
        }
        if (operateType == OperateType.KEY_BLANK_DECODE_EXECUTE) {
            operationFinish();
        }
    }

    public void decode(DataParam dataParam) {
        this.decodeParams = dataParam;
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            location(dataParam);
        }
    }

    public void decodeLocationOnly(DataParam dataParam) {
        this.locationOnly = true;
        this.decodeParams = dataParam;
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            location(dataParam);
        }
    }

    private boolean isDetectDecoder() {
        return CuttingMachine.getInstance().isE9() && !CuttingMachine.getInstance().isNotDetectDecoder();
    }

    private void location(DataParam dataParam) {
        OperationManager.getInstance().clearAllState();
        OperationManager.getInstance().start(dataParam, AssetsJsonUtils.getKeyDecodeLocationSteps(CuttingMachine.getInstance().getContext(), dataParam.getKeyInfo()), OperateType.KEY_BLANK_DECODE_LOCATION);
    }

    private void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    public void decodeNoLocation(DataParam dataParam) {
        this.decodeParams = dataParam;
        OperationManager.getInstance().start(this.decodeParams, AssetsJsonUtils.getKeyDecodePathSteps(dataParam.getKeyInfo(), this.decodeParams.getAngleDimpleIndex()), OperateType.KEY_BLANK_DECODE_EXECUTE);
    }
}
