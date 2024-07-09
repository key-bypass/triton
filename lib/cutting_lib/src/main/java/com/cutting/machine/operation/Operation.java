package com.cutting.machine.operation;

import java.util.Observable;
import java.util.concurrent.Flow.Publisher;

import kotlin.Unit;

/* loaded from: classes2.dex */
public interface Operation {
    void handleOperate(Observable observable, Object obj);

    void operationFinish();
}
