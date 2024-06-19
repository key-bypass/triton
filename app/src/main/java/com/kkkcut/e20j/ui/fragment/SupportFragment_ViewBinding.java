package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SupportFragment_ViewBinding implements Unbinder {
    private SupportFragment target;

    public SupportFragment_ViewBinding(SupportFragment supportFragment, View view) {
        this.target = supportFragment;
        supportFragment.llChinese = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_chinese, "field 'llChinese'", LinearLayout.class);
        supportFragment.llForeign = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_foreign, "field 'llForeign'", LinearLayout.class);
        supportFragment.llForeignUS = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_foreign_us, "field 'llForeignUS'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SupportFragment supportFragment = this.target;
        if (supportFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        supportFragment.llChinese = null;
        supportFragment.llForeign = null;
        supportFragment.llForeignUS = null;
    }
}
