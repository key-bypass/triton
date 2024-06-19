package com.kkkcut.e20j.androidquick.ui.viewstatus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class VaryViewHelperX implements IVaryViewHelper {
    private IVaryViewHelper helper;
    private View view;

    public VaryViewHelperX(View view) {
        this.view = view;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout frameLayout = new FrameLayout(view.getContext());
        viewGroup.removeView(view);
        viewGroup.addView(frameLayout, layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -1);
        View view2 = new View(view.getContext());
        frameLayout.addView(view, layoutParams2);
        frameLayout.addView(view2, layoutParams2);
        this.helper = new VaryViewHelper(view2);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View getCurrentLayout() {
        return this.helper.getCurrentLayout();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public void restoreView() {
        this.helper.restoreView();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public void showLayout(View view) {
        this.helper.showLayout(view);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View inflate(int i) {
        return this.helper.inflate(i);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public Context getContext() {
        return this.helper.getContext();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View getView() {
        return this.view;
    }
}
