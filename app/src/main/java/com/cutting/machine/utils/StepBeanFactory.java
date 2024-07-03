package com.cutting.machine.utils;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.bean.StepBean;

/* loaded from: classes2.dex */
public class StepBeanFactory {
    public static StepBean getCutStepBeanNoChangeDirect(String str, int i, int i2, int i3, String str2, String str3) {
        return new StepBean(str, 0, i, i2, i3, str2, str3, false);
    }

    public static StepBean getCutStepBean(String str, int i, int i2, int i3, String str2, String str3) {
        return getStepBean(str, 0, i, i2, i3, str2, str3);
    }

    public static StepBean getCutStepBean(int i, int i2, int i3, String str, String str2) {
        return getStepBean("", 0, i, i2, i3, str, str2);
    }

    public static StepBean getStepBean(String str, int i, int i2, int i3, int i4, String str2, String str3) {
        if (CuttingMachine.getInstance().isE9()) {
            return new StepBean(str, i, i2, i3, i4, str2, str3, false);
        }
        return new StepBean(str, i, i3, i2, i4, str2, str3, false);
    }

    public static StepBean getStepBean(int i, int i2, int i3, int i4, String str, String str2) {
        if (CuttingMachine.getInstance().isE9()) {
            return new StepBean("", i, i2, i3, i4, str, str2, false);
        }
        return new StepBean("", i, i3, i2, i4, str, str2, false);
    }
}
