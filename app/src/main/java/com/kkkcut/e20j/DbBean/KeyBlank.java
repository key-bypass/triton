package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
/* loaded from: classes.dex */
public class KeyBlank {
    String Description;

    @Id
    long ID;

    String KeyBlankName;

    public KeyBlank(long j, String str, String str2) {
        this.ID = j;
        this.KeyBlankName = str;
        this.Description = str2;
    }

    public KeyBlank() {
    }

    @Generated(hash = 687553763)
    public KeyBlank(String Description, long ID, String KeyBlankName) {
        this.Description = Description;
        this.ID = ID;
        this.KeyBlankName = KeyBlankName;
    }

    public long getID() {
        return this.ID;
    }

    public void setID(long j) {
        this.ID = j;
    }

    public String getKeyBlankName() {
        return this.KeyBlankName;
    }

    public void setKeyBlankName(String str) {
        this.KeyBlankName = str;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String str) {
        this.Description = str;
    }
}
