package com.pgyersdk.update.javabean;

/* loaded from: classes2.dex */
public class AppBean {

    /* renamed from: a */
    private String f729a;

    /* renamed from: b */
    private String f730b;

    /* renamed from: c */
    private String f731c;

    /* renamed from: d */
    private String f732d;

    /* renamed from: e */
    private boolean f733e;

    public String getDownloadURL() {
        return this.f731c;
    }

    public String getReleaseNote() {
        return this.f730b;
    }

    public String getVersionCode() {
        return this.f732d;
    }

    public String getVersionName() {
        return this.f729a;
    }

    public boolean isShouldForceToUpdate() {
        return this.f733e;
    }

    public void setDownloadURL(String str) {
        this.f731c = str;
    }

    public void setReleaseNote(String str) {
        this.f730b = str;
    }

    public void setShouldForceToUpdate(boolean z) {
        this.f733e = z;
    }

    public void setVersionCode(String str) {
        this.f732d = str;
    }

    public void setVersionName(String str) {
        this.f729a = str;
    }
}
