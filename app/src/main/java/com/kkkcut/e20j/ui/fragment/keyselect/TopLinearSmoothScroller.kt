package com.kkkcut.e20j.ui.fragment.keyselect

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller

/* loaded from: classes.dex */
class TopLinearSmoothScroller(context: Context?) : LinearSmoothScroller(context) {
    // androidx.recyclerview.widget.LinearSmoothScroller
    public override fun getVerticalSnapPreference(): Int {
        return -1
    }
}
