package com.kkkcut.e20j.androidquick.autolayout.attr;

import android.view.View;
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils;
import com.kkkcut.e20j.androidquick.autolayout.utils.L;

/* loaded from: classes.dex */
public abstract class AutoAttr {
    public static final int BASE_DEFAULT = 3;
    public static final int BASE_HEIGHT = 2;
    public static final int BASE_WIDTH = 1;
    protected int baseHeight;
    protected int baseWidth;
    protected int pxVal;

    protected abstract int attrVal();

    protected boolean contains(int i, int i2) {
        return (i & i2) != 0;
    }

    protected abstract boolean defaultBaseWidth();

    protected abstract void execute(View view, int i);

    public AutoAttr(int i, int i2, int i3) {
        this.pxVal = i;
        this.baseWidth = i2;
        this.baseHeight = i3;
    }

    public void apply(View view) {
        int percentHeightSize;
        boolean z = view.getTag() != null && view.getTag().toString().equals("auto");
        if (z) {
            L.e(" pxVal = " + this.pxVal + " ," + getClass().getSimpleName());
        }
        if (useDefault()) {
            percentHeightSize = defaultBaseWidth() ? getPercentWidthSize() : getPercentHeightSize();
            if (z) {
                L.e(" useDefault val= " + percentHeightSize);
            }
        } else if (baseWidth()) {
            percentHeightSize = getPercentWidthSize();
            if (z) {
                L.e(" baseWidth val= " + percentHeightSize);
            }
        } else {
            percentHeightSize = getPercentHeightSize();
            if (z) {
                L.e(" baseHeight val= " + percentHeightSize);
            }
        }
        if (percentHeightSize > 0) {
            percentHeightSize = Math.max(percentHeightSize, 1);
        }
        execute(view, percentHeightSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPercentWidthSize() {
        return AutoUtils.getPercentWidthSizeBigger(this.pxVal);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getPercentHeightSize() {
        return AutoUtils.getPercentHeightSizeBigger(this.pxVal);
    }

    protected boolean baseWidth() {
        return contains(this.baseWidth, attrVal());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean useDefault() {
        return (contains(this.baseHeight, attrVal()) || contains(this.baseWidth, attrVal())) ? false : true;
    }

    public String toString() {
        return "AutoAttr{pxVal=" + this.pxVal + ", baseWidth=" + baseWidth() + ", defaultBaseWidth=" + defaultBaseWidth() + '}';
    }
}
