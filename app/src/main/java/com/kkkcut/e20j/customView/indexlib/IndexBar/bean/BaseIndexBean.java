package com.kkkcut.e20j.customView.indexlib.IndexBar.bean;

import com.kkkcut.e20j.customView.indexlib.suspension.ISuspensionInterface;

/* loaded from: classes.dex */
public abstract class BaseIndexBean implements ISuspensionInterface {
    private String baseIndexTag;

    @Override // com.kkkcut.e20j.customView.indexlib.suspension.ISuspensionInterface
    public boolean isShowSuspension() {
        return true;
    }

    public String getBaseIndexTag() {
        return this.baseIndexTag;
    }

    public BaseIndexBean setBaseIndexTag(String str) {
        this.baseIndexTag = str;
        return this;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.suspension.ISuspensionInterface
    public String getSuspensionTag() {
        return this.baseIndexTag;
    }
}
