package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.view.View;

/* loaded from: classes.dex */
public class PaddingTopAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 1024;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return false;
    }

    public PaddingTopAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
    }

    public static PaddingTopAttr generate(int i, int i2) {
        PaddingTopAttr paddingTopAttr;
        if (i2 == 1) {
            paddingTopAttr = new PaddingTopAttr(i, 1024, 0);
        } else if (i2 == 2) {
            paddingTopAttr = new PaddingTopAttr(i, 0, 1024);
        } else {
            if (i2 != 3) {
                return null;
            }
            paddingTopAttr = new PaddingTopAttr(i, 0, 0);
        }
        return paddingTopAttr;
    }
}
