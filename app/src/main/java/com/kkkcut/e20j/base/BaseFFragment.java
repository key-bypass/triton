package com.kkkcut.e20j.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import androidx.fragment.app.FragmentActivity;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/* loaded from: classes.dex */
public abstract class BaseFFragment extends BaseFragment implements ISupportFragment {
    protected FragmentActivity _mActivity;
    final SupportFragmentDelegate mDelegate = new SupportFragmentDelegate(this);

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public SupportFragmentDelegate getSupportDelegate() {
        return this.mDelegate;
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public ExtraTransaction extraTransaction() {
        return this.mDelegate.extraTransaction();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        this.mDelegate.onAttach(activity);
        this._mActivity = this.mDelegate.getActivity();
        super.onAttach(activity);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mDelegate.onCreate(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return this.mDelegate.onCreateAnimation(i, z, i2);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        this.mDelegate.onActivityCreated(bundle);
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.mDelegate.onSaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.mDelegate.onResume();
        super.onResume();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.mDelegate.onPause();
        super.onPause();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mDelegate.onDestroyView();
        super.onDestroyView();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mDelegate.onDestroy();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        if (isAdded()) {
            this.mDelegate.onHiddenChanged(z);
        }
        super.onHiddenChanged(z);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        this.mDelegate.setUserVisibleHint(z);
        super.setUserVisibleHint(z);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    @Deprecated
    public void enqueueAction(Runnable runnable) {
        this.mDelegate.post(runnable);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void post(Runnable runnable) {
        this.mDelegate.post(runnable);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onEnterAnimationEnd(Bundle bundle) {
        this.mDelegate.onEnterAnimationEnd(bundle);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onLazyInitView(Bundle bundle) {
        this.mDelegate.onLazyInitView(bundle);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onSupportVisible() {
        this.mDelegate.onSupportVisible();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onSupportInvisible() {
        this.mDelegate.onSupportInvisible();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public final boolean isSupportVisible() {
        return this.mDelegate.isSupportVisible();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public FragmentAnimator getFragmentAnimator() {
        return this.mDelegate.getFragmentAnimator();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public boolean onBackPressedSupport() {
        return this.mDelegate.onBackPressedSupport();
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void setFragmentResult(int i, Bundle bundle) {
        this.mDelegate.setFragmentResult(i, bundle);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onFragmentResult(int i, int i2, Bundle bundle) {
        this.mDelegate.onFragmentResult(i, i2, bundle);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void onNewBundle(Bundle bundle) {
        this.mDelegate.onNewBundle(bundle);
    }

    @Override // me.yokeyword.fragmentation.ISupportFragment
    public void putNewBundle(Bundle bundle) {
        this.mDelegate.putNewBundle(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideSoftInput() {
        this.mDelegate.hideSoftInput();
        fullScreen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fullScreen() {
        View decorView = getActivity().getWindow().getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(5894);
        }
    }

    protected void showSoftInput(View view) {
        this.mDelegate.showSoftInput(view);
    }

    public void loadRootFragment(int i, ISupportFragment iSupportFragment) {
        this.mDelegate.loadRootFragment(i, iSupportFragment);
    }

    public void loadRootFragment(int i, ISupportFragment iSupportFragment, boolean z, boolean z2) {
        this.mDelegate.loadRootFragment(i, iSupportFragment, z, z2);
    }

    public void start(ISupportFragment iSupportFragment) {
        this.mDelegate.start(iSupportFragment);
    }

    public void start(ISupportFragment iSupportFragment, int i) {
        this.mDelegate.start(iSupportFragment, i);
    }

    public void startForResult(ISupportFragment iSupportFragment, int i) {
        this.mDelegate.startForResult(iSupportFragment, i);
    }

    public void startWithPop(ISupportFragment iSupportFragment) {
        this.mDelegate.startWithPop(iSupportFragment);
    }

    public void startWithPopTo(ISupportFragment iSupportFragment, Class<?> cls, boolean z) {
        this.mDelegate.startWithPopTo(iSupportFragment, cls, z);
    }

    public void replaceFragment(ISupportFragment iSupportFragment, boolean z) {
        this.mDelegate.replaceFragment(iSupportFragment, z);
    }

    public void pop() {
        this.mDelegate.pop();
    }

    public void popTo(Class<?> cls, boolean z) {
        this.mDelegate.popTo(cls, z);
    }

    public <T extends ISupportFragment> T findFragment(Class<T> cls) {
        return (T) SupportHelper.findFragment(getFragmentManager(), cls);
    }
}
