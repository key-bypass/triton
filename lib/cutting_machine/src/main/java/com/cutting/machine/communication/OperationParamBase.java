package com.liying.core.communication;

import com.liying.core.clamp.Clamp;

/* loaded from: classes2.dex */
public interface OperationParamBase {
    Clamp getClamp();

    int getClampMode();

    KeyInfoBase getKeyInfo();

    int getPauseTime();

    boolean isPlasticKey();
}
