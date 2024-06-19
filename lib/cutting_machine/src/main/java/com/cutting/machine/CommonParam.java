package com.liying.core;

import com.liying.core.clamp.Clamp;
import com.liying.core.communication.KeyInfoBase;
import com.liying.core.operation.BaseParam;

/* loaded from: classes2.dex */
public class CommonParam extends BaseParam {
    protected Clamp clamp;
    protected int clampMode;
    protected int cutDepth;
    protected int cutterSize;
    protected int decoderSize;

    @Override // com.liying.core.communication.OperationParamBase
    public Clamp getClamp() {
        return null;
    }

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
}
