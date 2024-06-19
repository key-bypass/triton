package com.kkkcut.e20j.DbBean.search;

/* loaded from: classes.dex */
public class MenuSummary {
    int Card;
    int FK_KeyID;
    String LockSystem;
    String Nofcuts;
    String SN;
    String Series;
    String Type;

    public MenuSummary(int i, String str, String str2, String str3, int i2, String str4, String str5) {
        this.FK_KeyID = i;
        this.SN = str;
        this.LockSystem = str2;
        this.Series = str3;
        this.Card = i2;
        this.Type = str4;
        this.Nofcuts = str5;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
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

    public int getCard() {
        return this.Card;
    }

    public void setCard(int i) {
        this.Card = i;
    }

    public String getType() {
        return this.Type;
    }

    public void setType(String str) {
        this.Type = str;
    }

    public String getNofcuts() {
        return this.Nofcuts;
    }

    public void setNofcuts(String str) {
        this.Nofcuts = str;
    }
}
