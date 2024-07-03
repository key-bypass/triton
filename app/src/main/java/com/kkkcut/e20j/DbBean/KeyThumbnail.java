package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class KeyThumbnail {
    int FK_KeyID;
    int ID;
    byte[] ImageFormat1;
    byte[] ImageFormat2;
    byte[] ImageFormat3;
    byte[] ImageFormat4;
    byte[] ImageFormat5;

    @Generated(hash = 1427644859)
    public KeyThumbnail(int FK_KeyID, int ID, byte[] ImageFormat1,
            byte[] ImageFormat2, byte[] ImageFormat3, byte[] ImageFormat4,
            byte[] ImageFormat5) {
        this.FK_KeyID = FK_KeyID;
        this.ID = ID;
        this.ImageFormat1 = ImageFormat1;
        this.ImageFormat2 = ImageFormat2;
        this.ImageFormat3 = ImageFormat3;
        this.ImageFormat4 = ImageFormat4;
        this.ImageFormat5 = ImageFormat5;
    }

    @Generated(hash = 637526414)
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
