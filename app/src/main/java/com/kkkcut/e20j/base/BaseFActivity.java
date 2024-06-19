package com.kkkcut.e20j.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import com.kkkcut.e20j.utils.lan.MultiLanguage;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/* loaded from: classes.dex */
public abstract class BaseFActivity extends HideStatusActivity implements ISupportActivity {
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguage.setLocal(context));
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public SupportActivityDelegate getSupportDelegate() {
        return this.mDelegate;
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public ExtraTransaction extraTransaction() {
        return this.mDelegate.extraTransaction();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.HideStatusActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mDelegate.onCreate(bundle);
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity
    public void onPostCreate(Bundle bundle) {
        this.mDelegate.onPostCreate(bundle);
        super.onPostCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mDelegate.onDestroy();
        super.onDestroy();
    }

    @Override // com.kkkcut.e20j.base.HideStatusActivity, android.app.Activity, android.view.Window.Callback, me.yokeyword.fragmentation.ISupportActivity
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.dispatchTouchEvent(motionEvent) || super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        this.mDelegate.onBackPressed();
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public void onBackPressedSupport() {
        this.mDelegate.onBackPressedSupport();
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public FragmentAnimator getFragmentAnimator() {
        return this.mDelegate.getFragmentAnimator();
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        this.mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public FragmentAnimator onCreateFragmentAnimator() {
        return this.mDelegate.onCreateFragmentAnimator();
    }

    @Override // me.yokeyword.fragmentation.ISupportActivity
    public void post(Runnable runnable) {
        this.mDelegate.post(runnable);
    }

    public void loadRootFragment(int i, ISupportFragment iSupportFragment) {
        this.mDelegate.loadRootFragment(i, iSupportFragment);
    }

    public void start(ISupportFragment iSupportFragment) {
        this.mDelegate.start(iSupportFragment);
    }

    public void start(ISupportFragment iSupportFragment, int i) {
        this.mDelegate.start(iSupportFragment, i);
    }

    public void startWithPopTo(ISupportFragment iSupportFragment, Class<?> cls, boolean z) {
        this.mDelegate.startWithPopTo(iSupportFragment, cls, z);
    }

    public void pop() {
        this.mDelegate.pop();
    }

    public void popTo(Class<?> cls, boolean z) {
        this.mDelegate.popTo(cls, z);
    }

    public void popTo(Class<?> cls, boolean z, Runnable runnable) {
        this.mDelegate.popTo(cls, z, runnable);
    }

    public void popTo(Class<?> cls, boolean z, Runnable runnable, int i) {
        this.mDelegate.popTo(cls, z, runnable, i);
    }

    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    public <T extends ISupportFragment> T findFragment(Class<T> cls) {
        return (T) SupportHelper.findFragment(getSupportFragmentManager(), cls);
    }
}
