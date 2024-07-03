package com.kkkcut.e20j.androidquick.autolayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

/* loaded from: classes.dex */
public class AutoLayoutActivity extends FragmentActivity {
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View autoFrameLayout = str.equals(LAYOUT_FRAMELAYOUT) ? new AutoFrameLayout(context, attributeSet) : null;
        if (str.equals(LAYOUT_LINEARLAYOUT)) {
            autoFrameLayout = new AutoLinearLayout(context, attributeSet);
        }
        if (str.equals(LAYOUT_RELATIVELAYOUT)) {
            autoFrameLayout = new AutoRelativeLayout(context, attributeSet);
        }
        return autoFrameLayout != null ? autoFrameLayout : super.onCreateView(str, context, attributeSet);
    }
}
