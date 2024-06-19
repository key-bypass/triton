package com.kkkcut.e20j.ui.fragment.keyselect;

import android.content.Context;
import androidx.recyclerview.widget.LinearSmoothScroller;

/* loaded from: classes.dex */
public class TopLinearSmoothScroller extends LinearSmoothScroller {
    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public int getVerticalSnapPreference() {
        return -1;
    }

    public TopLinearSmoothScroller(Context context) {
        super(context);
    }
}
