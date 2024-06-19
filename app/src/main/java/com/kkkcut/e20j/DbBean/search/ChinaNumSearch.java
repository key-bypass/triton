package com.kkkcut.e20j.DbBean.search;

import android.text.TextUtils;
import com.liying.core.MachineInfo;

/* loaded from: classes.dex */
public class ChinaNumSearch implements SearchResult {
    String FromYear;
    String KeyChinaNum;
    String ModelName;
    String ModelName_CN;
    String Name;
    String Name_CN;
    String ToYear;
    String codeSeries;
    String cuts;
    int fK_KeyID;
    int seriesID;

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getKeyBlankName() {
        return "";
    }

    public ChinaNumSearch(String str, int i, String str2, int i2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.KeyChinaNum = str;
        this.fK_KeyID = i;
        this.codeSeries = str2;
        this.seriesID = i2;
        this.cuts = str3;
        this.Name = str4;
        this.Name_CN = str5;
        this.ModelName = str6;
        this.ModelName_CN = str7;
        this.FromYear = str8;
        this.ToYear = str9;
    }

    public int getSeriesID() {
        return this.seriesID;
    }

    public void setSeriesID(int i) {
        this.seriesID = i;
    }

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public void setKeyChinaNum(String str) {
        this.KeyChinaNum = str;
    }

    public int getfK_KeyID() {
        return this.fK_KeyID;
    }

    public void setfK_KeyID(int i) {
        this.fK_KeyID = i;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getName_CN() {
        return this.Name_CN;
    }

    public void setName_CN(String str) {
        this.Name_CN = str;
    }

    public String getModelName() {
        return this.ModelName;
    }

    public void setModelName(String str) {
        this.ModelName = str;
    }

    public String getModelName_CN() {
        return this.ModelName_CN;
    }

    public void setModelName_CN(String str) {
        this.ModelName_CN = str;
    }

    public String getFromYear() {
        return this.FromYear;
    }

    public void setFromYear(String str) {
        this.FromYear = str;
    }

    public String getToYear() {
        return this.ToYear;
    }

    public void setToYear(String str) {
        this.ToYear = str;
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayKeyID() {
        return getKeyChinaNum();
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayName() {
        String name_CN = getName_CN();
        if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(name_CN)) {
            name_CN = getName();
        }
        String modelName_CN = getModelName_CN();
        if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(modelName_CN)) {
            modelName_CN = getModelName();
        }
        String fromYear = getFromYear();
        if (TextUtils.isEmpty(fromYear)) {
            fromYear = "0";
        }
        String toYear = getToYear();
        return name_CN + ">" + modelName_CN + ">" + fromYear + "~" + (TextUtils.isEmpty(toYear) ? "0" : toYear) + "--ID:" + getfK_KeyID() + "{" + this.cuts + "}";
    }
}
