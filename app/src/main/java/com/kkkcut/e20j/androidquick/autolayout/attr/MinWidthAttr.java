package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.os.Build;
import android.view.View;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public class MinWidthAttr extends AutoAttr {
    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected int attrVal() {
        return 8192;
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected boolean defaultBaseWidth() {
        return true;
    }

    public MinWidthAttr(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    @Override // com.kkkcut.e20j.androidquick.autolayout.attr.AutoAttr
    protected void execute(View view, int i) {
        view.setMinimumWidth(i);
    }

    public static int getMinWidth(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            return view.getMinimumWidth();
        }
        try {
            Field field = view.getClass().getField("mMinWidth");
            field.setAccessible(true);
            return ((Integer) field.get(view)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static MinWidthAttr generate(int i, int i2) {
        MinWidthAttr minWidthAttr;
        if (i2 == 1) {
            minWidthAttr = new MinWidthAttr(i, 8192, 0);
        } else if (i2 == 2) {
            minWidthAttr = new MinWidthAttr(i, 0, 8192);
        } else {
            if (i2 != 3) {
                return null;
            }
            minWidthAttr = new MinWidthAttr(i, 0, 0);
        }
        return minWidthAttr;
    }
}
