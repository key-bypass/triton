package com.kkkcut.e20j.bean.eventbus;

import com.kkkcut.e20j.bean.CodeAndTooth;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class InputFinishBean {
    private ArrayList<CodeAndTooth> bittingCodes;
    boolean doorAndIgnition;
    boolean doorToIgnition;
    String toothCode;
    private String toothCodeLack;

    public InputFinishBean(String str, String str2, ArrayList<CodeAndTooth> arrayList) {
        this.toothCode = str;
        this.toothCodeLack = str2;
        this.bittingCodes = arrayList;
    }

    public InputFinishBean(boolean z, String str) {
        this.doorAndIgnition = z;
        this.toothCode = str;
    }

    public InputFinishBean(boolean z, String str, boolean z2) {
        this.doorAndIgnition = z;
        this.toothCode = str;
        this.doorToIgnition = z2;
    }

    public boolean isDoorAndIgnition() {
        return this.doorAndIgnition;
    }

    public String getToothCode() {
        return this.toothCode;
    }

    public boolean isDoorToIgnition() {
        return this.doorToIgnition;
    }

    public String getToothCodeLack() {
        return this.toothCodeLack;
    }

    public ArrayList<CodeAndTooth> getBittingCodes() {
        return this.bittingCodes;
    }
}
