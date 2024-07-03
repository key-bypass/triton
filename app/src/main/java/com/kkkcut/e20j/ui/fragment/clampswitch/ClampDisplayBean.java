package com.kkkcut.e20j.ui.fragment.clampswitch;

import com.cutting.machine.bean.ClampBean;

/* loaded from: classes.dex */
public class ClampDisplayBean {
    private ClampBean clampBean;
    private int drawable;

    public ClampDisplayBean(ClampBean clampBean, int i) {
        this.clampBean = clampBean;
        this.drawable = i;
    }

    public ClampBean getClampBean() {
        return this.clampBean;
    }

    public void setClampBean(ClampBean clampBean) {
        this.clampBean = clampBean;
    }

    public int getDrawable() {
        return this.drawable;
    }

    public void setDrawable(int i) {
        this.drawable = i;
    }
}
