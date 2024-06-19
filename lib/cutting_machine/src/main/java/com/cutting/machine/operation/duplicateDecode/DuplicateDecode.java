package com.liying.core.operation.duplicateDecode;

import com.liying.core.Command;
import com.liying.core.CuttingMachine;
import com.liying.core.KeyAlignInfo;
import com.liying.core.OperateType;
import com.liying.core.StepsGenerateUtil;
import com.liying.core.bean.EventBean;
import com.liying.core.bean.KeyType;
import com.liying.core.bean.PointXyz;
import com.liying.core.bean.StepBean;
import com.liying.core.communication.OperationManager;
import com.liying.core.error.ErrorHelper;
import com.liying.core.operation.Operation;
import com.liying.core.utils.AssetsJsonUtils;
import com.liying.core.utils.DecoderPositionUtils;
import java.util.List;
import java.util.Observable;

/* loaded from: classes2.dex */
public class DuplicateDecode implements Operation {
    private DuplicateDecodeParams decodeParams;

    @Override // com.liying.core.operation.Operation
    public void operationFinish() {
    }

    @Override // com.liying.core.operation.Operation
    public void handleOperate(Observable observable, Object obj) {
        List<StepBean> startDuplicateDecode;
        EventBean eventBean = (EventBean) obj;
        OperateType operateType = eventBean.getOperateType();
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION) {
            if (DecoderPositionUtils.isDownPosition(((Integer) eventBean.getData()).intValue())) {
                decoderLocation(this.decodeParams);
                return;
            } else {
                OperationManager.getInstance().sendEventMessage(50, 0);
                return;
            }
        }
        if (operateType == OperateType.DUPLICATE_DECODE_LOCATION) {
            DuplicateKeyData keyInfo = this.decodeParams.getKeyInfo();
            KeyAlignInfo keyAlignInfo = OperationManager.getInstance().getKeyAlignInfo();
            keyInfo.setDecodeLength(keyAlignInfo.getDecodeLength2());
            keyInfo.setDecodeWidth(keyAlignInfo.getDecodeWidth2());
            keyInfo.setCutDepth(keyAlignInfo.getCutDepth());
            if (keyInfo.getKeyType() == KeyType.TUBULAR_KEY) {
                startDuplicateDecode = AssetsJsonUtils.getKeyDecodePathSteps(TubularKeyinfoUtil.getTubularKey());
            } else {
                startDuplicateDecode = startDuplicateDecode();
            }
            OperationManager.getInstance().start(this.decodeParams, startDuplicateDecode, OperateType.DUPLICATE_DECODE_EXECUTE);
            return;
        }
        if (operateType == OperateType.DUPLICATE_DECODE_EXECUTE) {
            operationFinish();
        }
    }

    public List<StepBean> startDuplicateDecode() {
        List<PointXyz> groovePosition = OperationManager.getInstance().getGroovePosition();
        DuplicateKeyData keyInfo = this.decodeParams.getKeyInfo();
        if (keyInfo.getKeyType() == KeyType.DOUBLE_INSIDE_GROOVE_KEY || keyInfo.getKeyType() == KeyType.LEFT_GROOVE || keyInfo.getKeyType() == KeyType.RIGHT_GROOVE || keyInfo.getKeyType() == KeyType.TWO_GROOVE || keyInfo.getKeyType() == KeyType.THREE_GROOVE) {
            if (groovePosition.size() == 0) {
                ErrorHelper.handleError(150);
                return null;
            }
            if (keyInfo.getKeyType() == KeyType.DOUBLE_INSIDE_GROOVE_KEY && groovePosition.size() != 1) {
                ErrorHelper.handleError(150);
                return null;
            }
            if (keyInfo.getKeyType() == KeyType.TWO_GROOVE && groovePosition.size() != 2) {
                ErrorHelper.handleError(150);
                return null;
            }
            if (keyInfo.getKeyType() == KeyType.THREE_GROOVE && groovePosition.size() != 3) {
                ErrorHelper.handleError(150);
                return null;
            }
            if (keyInfo.getKeyType() == KeyType.LEFT_GROOVE) {
                PointXyz pointXyz = groovePosition.get(0);
                groovePosition.clear();
                groovePosition.add(pointXyz);
            }
            if (keyInfo.getKeyType() == KeyType.RIGHT_GROOVE) {
                groovePosition.size();
                PointXyz pointXyz2 = groovePosition.get(groovePosition.size() - 1);
                groovePosition.clear();
                groovePosition.add(pointXyz2);
            }
        } else if (groovePosition != null) {
            groovePosition.clear();
        }
        return StepsGenerateUtil.generateDuplicateDecodeSteps(this.decodeParams.getKeyInfo(), OperationManager.getInstance().getKeyAlignInfo(), this.decodeParams.getDensity(), groovePosition);
    }

    public void duplicateDecode(DuplicateDecodeParams duplicateDecodeParams) {
        this.decodeParams = duplicateDecodeParams;
        if (isDetectDecoder()) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION);
        } else {
            decoderLocation(duplicateDecodeParams);
        }
    }

    private boolean isDetectDecoder() {
        return CuttingMachine.getInstance().isE9() && !CuttingMachine.getInstance().isNotDetectDecoder();
    }

    private void checkDecoderState(OperateType operateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType);
    }

    private void decoderLocation(DuplicateDecodeParams duplicateDecodeParams) {
        OperationManager.getInstance().clearAllState();
        OperationManager.getInstance().start(duplicateDecodeParams, AssetsJsonUtils.getDuplicateDecodeLocationSteps(CuttingMachine.getInstance().getContext(), duplicateDecodeParams), OperateType.DUPLICATE_DECODE_LOCATION);
    }
}
