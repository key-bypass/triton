package com.cutting.machine;

import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.communication.KeyInfoBase;
import com.cutting.machine.operation.BaseParam;

/* loaded from: classes2.dex */
public class CommonParam extends BaseParam {
    protected Clamp clamp;
    protected int clampMode;
    protected int cutDepth;
    protected int cutterSize;
    protected int decoderSize;

    @Override // com.cutting.machine.communication.OperationParamBase
    public Clamp getClamp() {
        return null;
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
}
