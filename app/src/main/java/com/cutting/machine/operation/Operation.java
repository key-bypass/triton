package com.cutting.machine.operation;

import java.util.Observable;

/* loaded from: classes2.dex */
public interface Operation {
    void handleOperate(Observable observable, Object obj);

    void operationFinish();
}
