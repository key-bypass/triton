package com.kkkcut.e20j.DbBean;

/* loaded from: classes.dex */
public class DbVersion {
    String updateInfo;
    String version;

    public DbVersion(String str, String str2) {
        this.version = str;
        this.updateInfo = str2;
    }

    public DbVersion() {
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getUpdateInfo() {
        return this.updateInfo;
    }

    public void setUpdateInfo(String str) {
        this.updateInfo = str;
    }
}
