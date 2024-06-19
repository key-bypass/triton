package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import butterknife.OnClick;
import com.kkkcut.e20j.us.R;
import me.yokeyword.fragmentation.ISupportFragment;

/* loaded from: classes.dex */
public class DeviceCenterFragment extends BaseBackFragment {
    public static final String TAG = "DeviceCenterFragment";

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_device_center;
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
    }

    @Override // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    public String setTitleStr() {
        return null;
    }

    public static ISupportFragment newInstance() {
        return new DeviceCenterFragment();
    }

    @OnClick({R.id.iv_message, R.id.ll_language_selection, R.id.ll_check_update, R.id.ll_about_us, R.id.ll_instructions, R.id.ll_bluetooth})
    public void onViewClicked(View view) {
        view.getId();
    }
}
