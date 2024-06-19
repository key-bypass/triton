package com.kkkcut.e20j.bean.gsonBean;

/* loaded from: classes.dex */
public class RegistrationRes {
    private String Code;
    private String ConfigurationFile;
    private String CurrentDate;
    private String ExpirationDate;
    private String Msg;
    private String NewLicense;
    private String OldLicense;

    public String getCode() {
        return this.Code;
    }

    public void setCode(String str) {
        this.Code = str;
    }

    public String getMsg() {
        return this.Msg;
    }

    public void setMsg(String str) {
        this.Msg = str;
    }

    public String getExpirationDate() {
        return this.ExpirationDate;
    }

    public void setExpirationDate(String str) {
        this.ExpirationDate = str;
    }

    public String getCurrentDate() {
        return this.CurrentDate;
    }

    public void setCurrentDate(String str) {
        this.CurrentDate = str;
    }

    public String getOldLicense() {
        return this.OldLicense;
    }

    public void setOldLicense(String str) {
        this.OldLicense = str;
    }

    public String getNewLicense() {
        return this.NewLicense;
    }

    public void setNewLicense(String str) {
        this.NewLicense = str;
    }

    public String getConfigurationFile() {
        return this.ConfigurationFile;
    }

    public void setConfigurationFile(String str) {
        this.ConfigurationFile = str;
    }
}
