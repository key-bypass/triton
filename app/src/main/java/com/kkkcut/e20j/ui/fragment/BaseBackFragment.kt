package com.kkkcut.e20j.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.base.BaseFFragment;

/* loaded from: classes.dex */
public abstract class BaseBackFragment extends BaseFFragment {
    public abstract String setTitleStr();

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setTittle();
        fullScreen();
    }

    public void onBack() {
        this._mActivity.onBackPressed();
    }

    public void setTittle() {
        var frameActivity = this._mActivity;
        if (frameActivity != null) {
            String titleStr = setTitleStr();
            if (titleStr == null) {
                titleStr = "";
            }
            frameActivity.setTitle(titleStr);
        }
    }

    @Override // com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        if (z || !isAdded()) {
            return;
        }
        setTittle();
    }

}
