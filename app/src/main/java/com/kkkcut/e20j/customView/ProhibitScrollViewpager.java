package com.kkkcut.e20j.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes.dex */
public class ProhibitScrollViewpager extends ViewPager {
    @Override // androidx.viewpager.widget.ViewPager
    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        return false;
    }

    public ProhibitScrollViewpager(Context context) {
        super(context);
    }

    public ProhibitScrollViewpager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
