package com.kkkcut.e20j.DbBean.search;

/* loaded from: classes.dex */
public class BarCodeSearch implements SearchResult {
    private int FK_KeyID;
    private String barCode;
    private String ofCuts;

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getKeyBlankName() {
        return "";
    }

    public BarCodeSearch(int i, String str, String str2) {
        this.FK_KeyID = i;
        this.barCode = str;
        this.ofCuts = str2;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String str) {
        this.barCode = str;
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayKeyID() {
        return this.FK_KeyID + "{" + this.ofCuts + "}";
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayName() {
        return this.barCode;
    }
}
