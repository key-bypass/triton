package com.cutting.machine.communication;

/* loaded from: classes2.dex */
public abstract class WriteCallback {
    public abstract void onWriteFailure(Exception exc);

    public abstract void onWriteSuccess(int i, int i2, byte[] bArr);
}
