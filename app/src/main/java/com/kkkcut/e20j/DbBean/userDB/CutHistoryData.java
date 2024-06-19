package com.kkkcut.e20j.DbBean.userDB;

import com.kkkcut.e20j.DbBean.GoOperatBean;
import java.util.Date;

/* loaded from: classes.dex */
public class CutHistoryData {
    String KeyChinaNum;
    int basicDataID;
    String codeSeries;
    String cuts;
    int doorIgnition;
    int doorToIgnition;
    Long id;
    int isCustomKey;
    String remark;
    int seriesID;
    long time;
    String title;
    String toothCode;
    String toothCodeSide;

    public CutHistoryData(GoOperatBean goOperatBean) {
        this.title = goOperatBean.getTitle();
        this.codeSeries = goOperatBean.getCodeSeries();
        this.basicDataID = goOperatBean.getKeyID();
        this.KeyChinaNum = goOperatBean.getKeyChinaNum();
        this.seriesID = goOperatBean.getSeriesID();
        this.toothCode = goOperatBean.getToothCode();
        this.cuts = goOperatBean.getCuts();
        if (goOperatBean.isCustomkey()) {
            this.isCustomKey = 1;
        }
        this.time = new Date().getTime();
    }

    public CutHistoryData(Long l, String str, String str2, String str3, int i, String str4, int i2, String str5, String str6, int i3, long j, int i4, int i5, String str7) {
        this.id = l;
        this.title = str;
        this.codeSeries = str2;
        this.cuts = str3;
        this.basicDataID = i;
        this.KeyChinaNum = str4;
        this.seriesID = i2;
        this.toothCode = str5;
        this.toothCodeSide = str6;
        this.isCustomKey = i3;
        this.time = j;
        this.doorIgnition = i4;
        this.doorToIgnition = i5;
        this.remark = str7;
    }

    public CutHistoryData() {
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

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public void setKeyChinaNum(String str) {
        this.KeyChinaNum = str;
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

    public int getIsCustomKey() {
        return this.isCustomKey;
    }

    public void setIsCustomKey(int i) {
        this.isCustomKey = i;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public int getDoorIgnition() {
        return this.doorIgnition;
    }

    public void setDoorIgnition(int i) {
        this.doorIgnition = i;
    }

    public int getDoorToIgnition() {
        return this.doorToIgnition;
    }

    public void setDoorToIgnition(int i) {
        this.doorToIgnition = i;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getToothCodeSide() {
        return this.toothCodeSide;
    }

    public void setToothCodeSide(String str) {
        this.toothCodeSide = str;
    }
}
