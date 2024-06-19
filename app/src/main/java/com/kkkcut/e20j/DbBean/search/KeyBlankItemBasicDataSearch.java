package com.kkkcut.e20j.DbBean.search;

/* loaded from: classes.dex */
public class KeyBlankItemBasicDataSearch {
    Long FK_KeyBlankItemID;
    long FK_KeyID;
    int ID;

    public KeyBlankItemBasicDataSearch(int i, Long l, long j) {
        this.ID = i;
        this.FK_KeyBlankItemID = l;
        this.FK_KeyID = j;
    }

    public KeyBlankItemBasicDataSearch() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }

    public Long getFK_KeyBlankItemID() {
        return this.FK_KeyBlankItemID;
    }

    public void setFK_KeyBlankItemID(Long l) {
        this.FK_KeyBlankItemID = l;
    }

    public long getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(long j) {
        this.FK_KeyID = j;
    }
}
