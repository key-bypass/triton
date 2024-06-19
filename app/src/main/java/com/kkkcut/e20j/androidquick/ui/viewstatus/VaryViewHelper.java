package com.kkkcut.e20j.androidquick.ui.viewstatus;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class VaryViewHelper implements IVaryViewHelper {
    private View currentView;
    private ViewGroup.LayoutParams params;
    private ViewGroup parentView;
    private View view;
    private int viewIndex;

    public VaryViewHelper(View view) {
        this.view = view;
    }

    private void init() {
        this.params = this.view.getLayoutParams();
        if (this.view.getParent() != null) {
            this.parentView = (ViewGroup) this.view.getParent();
        } else {
            this.parentView = (ViewGroup) this.view.getRootView().findViewById(R.id.content);
        }
        int childCount = this.parentView.getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            if (this.view == this.parentView.getChildAt(i)) {
                this.viewIndex = i;
                break;
            }
            i++;
        }
        this.currentView = this.view;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View getCurrentLayout() {
        return this.currentView;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public void restoreView() {
        showLayout(this.view);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public void showLayout(View view) {
        if (this.parentView == null) {
            init();
        }
        this.currentView = view;
        if (this.parentView.getChildAt(this.viewIndex) != view) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
            this.parentView.removeViewAt(this.viewIndex);
            this.parentView.addView(view, this.viewIndex, this.params);
        }
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View inflate(int i) {
        return LayoutInflater.from(this.view.getContext()).inflate(i, (ViewGroup) null);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public Context getContext() {
        return this.view.getContext();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.viewstatus.IVaryViewHelper
    public View getView() {
        return this.view;
    }
}
