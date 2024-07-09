package com.cutting.machine.communication;

/* loaded from: classes2.dex */
public interface Communication {
    void sendData(byte[] bArr, WriteCallback writeCallback);

    <T> void sendEventBusMessage(int i, T t);
}
