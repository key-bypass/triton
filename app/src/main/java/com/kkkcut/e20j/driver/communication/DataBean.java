package com.kkkcut.e20j.driver.communication;

import java.util.Arrays;

/* loaded from: classes.dex */
public class DataBean {
    private byte[] addr;
    private byte[] data;

    public DataBean(byte[] bArr, byte[] bArr2) {
        this.addr = bArr;
        this.data = bArr2;
    }

    public byte[] getAddr() {
        return this.addr;
    }

    public byte[] getData() {
        return this.data;
    }

    public String toString() {
        return "DataBean{addr=" + Arrays.toString(this.addr) + ", data=" + Arrays.toString(this.data) + '}';
    }
}
