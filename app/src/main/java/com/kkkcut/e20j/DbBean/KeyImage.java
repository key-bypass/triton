package com.kkkcut.e20j.DbBean;

import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class KeyImage {
    long LastUpdateTime;
    String hashLocal;
    String hashServer;
    int keyId;
    String url;

    public KeyImage(int i, String str, String str2, String str3, long j) {
        this.keyId = i;
        this.hashLocal = str;
        this.url = str2;
        this.hashServer = str3;
        this.LastUpdateTime = j;
    }

    public KeyImage() {
    }

    @Generated(hash = 1057741164)
    public KeyImage(long LastUpdateTime, String hashLocal, String hashServer,
            int keyId, String url) {
        this.LastUpdateTime = LastUpdateTime;
        this.hashLocal = hashLocal;
        this.hashServer = hashServer;
        this.keyId = keyId;
        this.url = url;
    }

    public int getKeyId() {
        return this.keyId;
    }

    public void setKeyId(int i) {
        this.keyId = i;
    }

    public String getHashLocal() {
        return TextUtils.isEmpty(this.hashLocal) ? "" : this.hashLocal;
    }

    public void setHashLocal(String str) {
        this.hashLocal = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getHashServer() {
        return this.hashServer;
    }

    public void setHashServer(String str) {
        this.hashServer = str;
    }

    public long getLastUpdateTime() {
        return this.LastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.LastUpdateTime = j;
    }
}
