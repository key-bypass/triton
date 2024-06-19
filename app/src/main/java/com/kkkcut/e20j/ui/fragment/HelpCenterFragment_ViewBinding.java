package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class HelpCenterFragment_ViewBinding implements Unbinder {
    private HelpCenterFragment target;

    public HelpCenterFragment_ViewBinding(HelpCenterFragment helpCenterFragment, View view) {
        this.target = helpCenterFragment;
        helpCenterFragment.vp = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'vp'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HelpCenterFragment helpCenterFragment = this.target;
        if (helpCenterFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        helpCenterFragment.vp = null;
    }
}
