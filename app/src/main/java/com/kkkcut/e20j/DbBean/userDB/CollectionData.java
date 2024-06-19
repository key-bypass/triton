package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.GoOperatBean;
import java.util.Date;

/* loaded from: classes.dex */
public class CollectionData {
    String KeyChinaNum;
    int basicDataID;
    String codeSeries;
    String cuts;
    Long id;
    String remark;
    int seriesID;
    long time;
    String title;
    String toothCode;

    public CollectionData(GoOperatBean goOperatBean) {
        this.title = goOperatBean.getTitle();
        this.codeSeries = goOperatBean.getCodeSeries();
        this.basicDataID = goOperatBean.getKeyID();
        this.KeyChinaNum = goOperatBean.getKeyChinaNum();
        this.seriesID = goOperatBean.getSeriesID();
        this.remark = goOperatBean.getRemark();
        this.toothCode = goOperatBean.getToothCode();
        this.cuts = goOperatBean.getCuts();
        this.time = new Date().getTime();
    }

    public CollectionData(Long l, String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, long j) {
        this.id = l;
        this.title = str;
        this.codeSeries = str2;
        this.KeyChinaNum = str3;
        this.cuts = str4;
        this.basicDataID = i;
        this.seriesID = i2;
        this.toothCode = str5;
        this.remark = str6;
        this.time = j;
    }

    public CollectionData() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public void setKeyChinaNum(String str) {
        this.KeyChinaNum = str;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public int getBasicDataID() {
        return this.basicDataID;
    }

    public void setBasicDataID(int i) {
        this.basicDataID = i;
    }

    public int getSeriesID() {
        return this.seriesID;
    }

    public void setSeriesID(int i) {
        this.seriesID = i;
    }

    public String getToothCode() {
        return this.toothCode;
    }

    public void setToothCode(String str) {
        this.toothCode = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
