package com.cutting.machine.operation.duplicateDecode

import com.cutting.machine.Command
import com.cutting.machine.CuttingMachine
import com.cutting.machine.OperateType
import com.cutting.machine.StepsGenerateUtil
import com.cutting.machine.bean.EventBean
import com.cutting.machine.bean.KeyType
import com.cutting.machine.bean.StepBean
import com.cutting.machine.communication.OperationManager
import com.cutting.machine.error.ErrorHelper
import com.cutting.machine.operation.Operation
import com.cutting.machine.utils.AssetsJsonUtils
import com.cutting.machine.utils.DecoderPositionUtils
import java.util.Observable

/* loaded from: classes2.dex */
class DuplicateDecode : Operation {
    private var decodeParams: DuplicateDecodeParams? = null

    // com.cutting.machine.operation.Operation
    override fun operationFinish() {
    }

    // com.cutting.machine.operation.Operation
    override fun handleOperate(observable: Observable, obj: Any) {
        val startDuplicateDecode: List<StepBean>?
        val eventBean = obj as EventBean
        val operateType = eventBean.operateType
        if (operateType == OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION) {
            if (DecoderPositionUtils.isDownPosition(((eventBean.data as Int)))) {
                decoderLocation(this.decodeParams)
                return
            } else {
                OperationManager.getInstance().sendEventMessage(50, 0)
                return
            }
        }
        if (operateType == OperateType.DUPLICATE_DECODE_LOCATION) {
            val keyInfo = decodeParams!!.keyInfo
            val keyAlignInfo = OperationManager.getInstance().keyAlignInfo
            keyInfo.decodeLength = (keyAlignInfo.decodeLength2)
            keyInfo.decodeWidth = (keyAlignInfo.decodeWidth2)
            keyInfo.cutDepth = (keyAlignInfo.cutDepth)
            startDuplicateDecode = if (keyInfo.keyType == KeyType.TUBULAR_KEY) {
                AssetsJsonUtils.getKeyDecodePathSteps(TubularKeyinfoUtil.getTubularKey())
            } else {
                startDuplicateDecode()
            }
            OperationManager.getInstance().start(
                this.decodeParams,
                startDuplicateDecode,
                OperateType.DUPLICATE_DECODE_EXECUTE
            )
            return
        }
        if (operateType == OperateType.DUPLICATE_DECODE_EXECUTE) {
            operationFinish()
        }
    }

    fun startDuplicateDecode(): List<StepBean>? {
        val groovePosition = OperationManager.getInstance().groovePosition
        val keyInfo = decodeParams!!.keyInfo
        if (keyInfo.keyType == KeyType.DOUBLE_INSIDE_GROOVE_KEY || keyInfo.keyType == KeyType.LEFT_GROOVE || keyInfo.keyType == KeyType.RIGHT_GROOVE || keyInfo.keyType == KeyType.TWO_GROOVE || keyInfo.keyType == KeyType.THREE_GROOVE) {
            if (groovePosition!!.size == 0) {
                ErrorHelper.handleError(150)
                return null
            }
            if (keyInfo.keyType == KeyType.DOUBLE_INSIDE_GROOVE_KEY && groovePosition.size != 1) {
                ErrorHelper.handleError(150)
                return null
            }
            if (keyInfo.keyType == KeyType.TWO_GROOVE && groovePosition.size != 2) {
                ErrorHelper.handleError(150)
                return null
            }
            if (keyInfo.keyType == KeyType.THREE_GROOVE && groovePosition.size != 3) {
                ErrorHelper.handleError(150)
                return null
            }
            if (keyInfo.keyType == KeyType.LEFT_GROOVE) {
                val pointXyz = groovePosition[0]
                groovePosition.clear()
                groovePosition.add(pointXyz)
            }
            if (keyInfo.keyType == KeyType.RIGHT_GROOVE) {
                groovePosition.size
                val pointXyz2 = groovePosition[groovePosition.size - 1]
                groovePosition.clear()
                groovePosition.add(pointXyz2)
            }
        } else groovePosition?.clear()
        return StepsGenerateUtil.generateDuplicateDecodeSteps(
            decodeParams!!.keyInfo, OperationManager.getInstance().keyAlignInfo,
            decodeParams!!.density, groovePosition
        )
    }

    fun duplicateDecode(duplicateDecodeParams: DuplicateDecodeParams?) {
        this.decodeParams = duplicateDecodeParams
        if (isDetectDecoder) {
            checkDecoderState(OperateType.CHECK_DECODER_STATE_BEFORE_DECODER_LOCATION)
        } else {
            decoderLocation(duplicateDecodeParams)
        }
    }

    private val isDetectDecoder: Boolean
        get() = CuttingMachine.getInstance().isE9 && !CuttingMachine.getInstance().isNotDetectDecoder

    private fun checkDecoderState(operateType: OperateType) {
        OperationManager.getInstance().sendOrder(Command.queryDecoderPosition(), operateType)
    }

    private fun decoderLocation(duplicateDecodeParams: DuplicateDecodeParams?) {
        OperationManager.getInstance().clearAllState()
        OperationManager.getInstance().start(
            duplicateDecodeParams,
            AssetsJsonUtils.getDuplicateDecodeLocationSteps(
                CuttingMachine.getInstance().context,
                duplicateDecodeParams
            ),
            OperateType.DUPLICATE_DECODE_LOCATION
        )
    }
}
