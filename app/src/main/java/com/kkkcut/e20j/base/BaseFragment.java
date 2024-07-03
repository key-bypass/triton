package com.kkkcut.e20j.base;

import android.content.Context;
import android.view.View;
import com.kkkcut.e20j.androidquick.ui.base.QuickFragment;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;

/* loaded from: classes.dex */
public abstract class BaseFragment extends QuickFragment {
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected View getLoadingTargetView() {
        return null;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onEventComing(EventCenter<?> eventCenter) {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onFirstUserVisible() {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void onUserInvisible() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    public void onUserVisible() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
