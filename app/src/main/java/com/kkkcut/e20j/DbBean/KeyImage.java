package com.kkkcut.e20j.DbBean;

import android.text.TextUtils;

/* loaded from: classes.dex */
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
