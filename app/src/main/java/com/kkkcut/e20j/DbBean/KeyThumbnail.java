package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class KeyThumbnail {
    int FK_KeyID;
    int ID;
    byte[] ImageFormat1;
    byte[] ImageFormat2;
    byte[] ImageFormat3;
    byte[] ImageFormat4;
    byte[] ImageFormat5;

    public KeyThumbnail(int i, int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this.ID = i;
        this.FK_KeyID = i2;
        this.ImageFormat1 = bArr;
        this.ImageFormat2 = bArr2;
        this.ImageFormat3 = bArr3;
        this.ImageFormat4 = bArr4;
        this.ImageFormat5 = bArr5;
    }

    public KeyThumbnail() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
    }

    public byte[] getImageFormat1() {
        return this.ImageFormat1;
    }

    public void setImageFormat1(byte[] bArr) {
        this.ImageFormat1 = bArr;
    }

    public byte[] getImageFormat2() {
        return this.ImageFormat2;
    }

    public void setImageFormat2(byte[] bArr) {
        this.ImageFormat2 = bArr;
    }

    public byte[] getImageFormat3() {
        return this.ImageFormat3;
    }

    public void setImageFormat3(byte[] bArr) {
        this.ImageFormat3 = bArr;
    }

    public byte[] getImageFormat4() {
        return this.ImageFormat4;
    }

    public void setImageFormat4(byte[] bArr) {
        this.ImageFormat4 = bArr;
    }

    public byte[] getImageFormat5() {
        return this.ImageFormat5;
    }

    public void setImageFormat5(byte[] bArr) {
        this.ImageFormat5 = bArr;
    }
}
