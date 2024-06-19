package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.os.Build;
import android.view.View;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class MinHeightAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 32768;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return false;
    }

    public MinHeightAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        try {
            view.setMinimumHeight(i);
        } catch (Exception unused) {
        }
    }

    public static MinHeightAttr generate(int i, int i2) {
        MinHeightAttr minHeightAttr;
        if (i2 == 1) {
            minHeightAttr = new MinHeightAttr(i, 32768, 0);
        } else if (i2 == 2) {
            minHeightAttr = new MinHeightAttr(i, 0, 32768);
        } else {
            if (i2 != 3) {
                return null;
            }
            minHeightAttr = new MinHeightAttr(i, 0, 0);
        }
        return minHeightAttr;
    }

    public static int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumHeight();
        }
        try {
            Field field = view.getClass().getField("mMinHeight");
            field.setAccessible(true);
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }
}
