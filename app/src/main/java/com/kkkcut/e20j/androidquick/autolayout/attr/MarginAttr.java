package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class MarginAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 16;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return false;
    }

    public MarginAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    public void apply(View view) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            if (useDefault()) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int percentWidthSize = getPercentWidthSize();
                marginLayoutParams.rightMargin = percentWidthSize;
                marginLayoutParams.leftMargin = percentWidthSize;
                int percentHeightSize = getPercentHeightSize();
                marginLayoutParams.bottomMargin = percentHeightSize;
                marginLayoutParams.topMargin = percentHeightSize;
                return;
            }
            super.apply(view);
        }
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.bottomMargin = i;
        marginLayoutParams.topMargin = i;
        marginLayoutParams.rightMargin = i;
        marginLayoutParams.leftMargin = i;
    }
}
