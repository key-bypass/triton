package com.liying.core.operation.calibrate;

import com.liying.core.clamp.Clamp;
import com.liying.core.communication.KeyInfoBase;
import com.liying.core.operation.BaseParam;

/* loaded from: classes2.dex */
public class CalibrationParams extends BaseParam {
    private Clamp clamp;
    private boolean distanceCalibration;

    @Override // com.liying.core.communication.OperationParamBase
    public int getClampMode() {
        return 0;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public KeyInfoBase getKeyInfo() {
        return null;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public int getPauseTime() {
        return 0;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public boolean isPlasticKey() {
        return false;
    }

    public CalibrationParams(Clamp clamp) {
        this(clamp, false);
    }

    public CalibrationParams(Clamp clamp, boolean z) {
        this.clamp = clamp;
        this.distanceCalibration = z;
    }

    @Override // com.liying.core.communication.OperationParamBase
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
