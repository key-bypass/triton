package com.cutting.machine.operation.calibrate;

import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.communication.KeyInfoBase;
import com.cutting.machine.operation.BaseParam;

/* loaded from: classes2.dex */
public class CalibrationParams extends BaseParam {
    private Clamp clamp;
    private boolean distanceCalibration;

    public CalibrationParams(Clamp clamp) {
        this(clamp, false);
    }

    public CalibrationParams(Clamp clamp, boolean z) {
        this.clamp = clamp;
        this.distanceCalibration = z;
    }

    @Override // com.cutting.machine.communication.OperationParamBase
    public int getClampMode() {
        return 0;
    }

    @Override // com.cutting.machine.communication.OperationParamBase
    public KeyInfoBase getKeyInfo() {
        return null;
    }

    @Override // com.cutting.machine.communication.OperationParamBase
    public int getPauseTime() {
        return 0;
    }

    @Override // com.cutting.machine.communication.OperationParamBase
    public boolean isPlasticKey() {
        return false;
    }

    @Override // com.cutting.machine.communication.OperationParamBase
    public Clamp getClamp() {
        return this.clamp;
    }

    public void setClamp(Clamp clamp) {
        this.clamp = clamp;
    }

    public boolean isDistanceCalibration() {
        return this.distanceCalibration;
    }

    public void setDistanceCalibration(boolean z) {
        this.distanceCalibration = z;
    }
}
