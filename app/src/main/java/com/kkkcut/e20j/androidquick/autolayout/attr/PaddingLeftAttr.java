package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.view.View;

/* loaded from: classes.dex */
public class PaddingLeftAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 512;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return true;
    }

    public PaddingLeftAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        view.setPadding(i, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static PaddingLeftAttr generate(int i, int i2) {
        PaddingLeftAttr paddingLeftAttr;
        if (i2 == 1) {
            paddingLeftAttr = new PaddingLeftAttr(i, 512, 0);
        } else if (i2 == 2) {
            paddingLeftAttr = new PaddingLeftAttr(i, 0, 512);
        } else {
            if (i2 != 3) {
                return null;
            }
            paddingLeftAttr = new PaddingLeftAttr(i, 0, 0);
        }
        return paddingLeftAttr;
    }
}
