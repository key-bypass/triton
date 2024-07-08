package com.kkkcut.e20j.ui.fragment.search;

/* loaded from: classes.dex */
class AdvSearchResultChild {
    String LockSystem;
    String SN;
    String Series;
    String Type;

    public AdvSearchResultChild(String str, String str2, String str3, String str4) {
        this.SN = str;
        this.LockSystem = str2;
        this.Series = str3;
        this.Type = str4;
    }

    public String getSN() {
        return this.SN;
    }

    public void setSN(String str) {
        this.SN = str;
    }

    public String getLockSystem() {
        return this.LockSystem;
    }

    public void setLockSystem(String str) {
        this.LockSystem = str;
    }

    public String getSeries() {
        return this.Series;
    }

    public void setSeries(String str) {
        this.Series = str;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String str) {
        this.Type = str;
    }
}
