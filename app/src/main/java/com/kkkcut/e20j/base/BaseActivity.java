package com.kkkcut.e20j.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.androidquick.tool.LogUtil;
import com.kkkcut.e20j.androidquick.ui.base.QuickActivity;
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter;
import com.kkkcut.e20j.ui.activity.FrameActivity;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public abstract class BaseActivity extends QuickActivity {
    protected static String TAG = "BaseActivity";

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void getBundleExtras(Bundle bundle) {
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected View getLoadingTargetView() {
        return null;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected boolean isApplySystemBarTint() {
        return true;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected boolean isLoadDefaultTitleBar() {
        return false;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        if (MachineInfo.isE9Standard(this)) {
            setTheme(R.style.E9Theme);
        } else {
            setTheme(R.style.E20Theme);
        }
        super.onCreate(bundle, persistableBundle);
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected void onEventComing(EventCenter<?> eventCenter) {
        LogUtil.i(TAG, eventCenter.getEventCode() + "");
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    protected QuickActivity.TransitionMode getOverridePendingTransitionMode() {
        return QuickActivity.TransitionMode.RIGHT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity
    public Intent getGoIntent(Class<?> cls) {
        if (BaseFragment.class.isAssignableFrom(cls)) {
            Intent intent = new Intent(this, (Class<?>) FrameActivity.class);
            intent.putExtra("fragmentName", cls.getName());
            return intent;
        }
        return super.getGoIntent(cls);
    }
}
