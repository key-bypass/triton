package com.kkkcut.e20j.ui.fragment;

import android.view.View;

import com.kkkcut.e20j.us.R;

import me.yokeyword.fragmentation.ISupportFragment;

/* loaded from: classes.dex */
public class DeviceCenterFragment extends BaseBackFragment {
    public static final String TAG = "DeviceCenterFragment";

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_device_center;
    }

    @Override // com.kkkcut.e20j.android quick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static ISupportFragment newInstance() {
        return new DeviceCenterFragment();
    }

    public void onViewClicked(View view) {
        view.getId();
    }
}
