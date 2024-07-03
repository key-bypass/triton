package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
/* loaded from: classes.dex */
public class LocalDbVersion {
    long LastUpdateTime;
    String LocResName;
    String LocResVer;
    int ResType;
    String SvHash;
    String SvResName;
    String SvResVer;
    @Id
    Long id;
    int updateStatus;
    String url;

    public LocalDbVersion(Long l, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, long j) {
        this.id = l;
        this.LocResName = str;
        this.LocResVer = str2;
        this.SvResName = str3;
        this.SvResVer = str4;
        this.url = str5;
        this.SvHash = str6;
        this.ResType = i;
        this.updateStatus = i2;
        this.LastUpdateTime = j;
    }

    public LocalDbVersion() {
    }

    @Generated(hash = 1859759485)
    public LocalDbVersion(long LastUpdateTime, String LocResName, String LocResVer, int ResType, String SvHash, String SvResName,
                          String SvResVer, Long id, int updateStatus, String url) {
        this.LastUpdateTime = LastUpdateTime;
        this.LocResName = LocResName;
        this.LocResVer = LocResVer;
        this.ResType = ResType;
        this.SvHash = SvHash;
        this.SvResName = SvResName;
        this.SvResVer = SvResVer;
        this.id = id;
        this.updateStatus = updateStatus;
        this.url = url;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getLocResName() {
        return this.LocResName;
    }

    public void setLocResName(String str) {
        this.LocResName = str;
    }

    public String getLocResVer() {
        return this.LocResVer;
    }

    public void setLocResVer(String str) {
        this.LocResVer = str;
    }

    public String getSvResName() {
        return this.SvResName;
    }

    public void setSvResName(String str) {
        this.SvResName = str;
    }

    public String getSvResVer() {
        return this.SvResVer;
    }

    public void setSvResVer(String str) {
        this.SvResVer = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getSvHash() {
        return this.SvHash;
    }

    public void setSvHash(String str) {
        this.SvHash = str;
    }

    public int getResType() {
        return this.ResType;
    }

    public void setResType(int i) {
        this.ResType = i;
    }

    public int getUpdateStatus() {
        return this.updateStatus;
    }

    public void setUpdateStatus(int i) {
        this.updateStatus = i;
    }

    public long getLastUpdateTime() {
        return this.LastUpdateTime;
    }

    public void setLastUpdateTime(long j) {
        this.LastUpdateTime = j;
    }
}
