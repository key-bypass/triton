package com.cutting.machine.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/* loaded from: classes2.dex */
public class CalibrationBean {

    @SerializedName("stepBeanList")
    private List<StepBean> stepBeanList;

    public List<StepBean> getStepBeanList() {
        return this.stepBeanList;
    }

    public void setStepBeanList(List<StepBean> list) {
        this.stepBeanList = list;
    }
}
