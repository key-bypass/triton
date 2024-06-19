package com.kkkcut.e20j.ui.fragment.search;

import com.kkkcut.e20j.DbBean.search.MenuSummary;
import java.util.List;

/* loaded from: classes.dex */
public class AdvSearchResult {
    private int FK_KeyID;
    List<MenuSummary> childList;
    private String cuts;

    public List<MenuSummary> getChildList() {
        return this.childList;
    }

    public void setChildList(List<MenuSummary> list) {
        this.childList = list;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }
}
