package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.view.View;

/* loaded from: classes.dex */
public class PaddingRightAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 2048;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return true;
    }

    public PaddingRightAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), i, view.getPaddingBottom());
    }

    public static PaddingRightAttr generate(int i, int i2) {
        PaddingRightAttr paddingRightAttr;
        if (i2 == 1) {
            paddingRightAttr = new PaddingRightAttr(i, 2048, 0);
        } else if (i2 == 2) {
            paddingRightAttr = new PaddingRightAttr(i, 0, 2048);
        } else {
            if (i2 != 3) {
                return null;
            }
            paddingRightAttr = new PaddingRightAttr(i, 0, 0);
        }
        return paddingRightAttr;
    }
}
