package com.cutting.machine.communication;

import com.cutting.machine.clamp.Clamp;

/* loaded from: classes2.dex */
public interface OperationParamBase {
    Clamp getClamp();

    int getClampMode();

    KeyInfoBase getKeyInfo();

    int getPauseTime();

    boolean isPlasticKey();
}
