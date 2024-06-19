package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class LookRealDepthFragment_ViewBinding implements Unbinder {
    private LookRealDepthFragment target;
    private View view7f0a00b3;

    public LookRealDepthFragment_ViewBinding(final LookRealDepthFragment lookRealDepthFragment, View view) {
        this.target = lookRealDepthFragment;
        lookRealDepthFragment.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        lookRealDepthFragment.tvDepth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth, "field 'tvDepth'", TextView.class);
        lookRealDepthFragment.tvDepthName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_name, "field 'tvDepthName'", TextView.class);
        lookRealDepthFragment.row = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.row, "field 'row'", LinearLayout.class);
        lookRealDepthFragment.colum = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.colum, "field 'colum'", LinearLayout.class);
        lookRealDepthFragment.llDepthName = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.depth_name, "field 'llDepthName'", LinearLayout.class);
        lookRealDepthFragment.llRealDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.real_depth, "field 'llRealDepth'", LinearLayout.class);
        lookRealDepthFragment.standardDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.standard_depth, "field 'standardDepth'", LinearLayout.class);
        lookRealDepthFragment.difference = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.difference, "field 'difference'", LinearLayout.class);
        lookRealDepthFragment.tvMax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_max, "field 'tvMax'", TextView.class);
        lookRealDepthFragment.tvMin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_min, "field 'tvMin'", TextView.class);
        lookRealDepthFragment.tvDiff = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_diff, "field 'tvDiff'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_screenshot, "method 'onclick'");
        this.view7f0a00b3 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.LookRealDepthFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                lookRealDepthFragment.onclick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LookRealDepthFragment lookRealDepthFragment = this.target;
        if (lookRealDepthFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        lookRealDepthFragment.tvTitle = null;
        lookRealDepthFragment.tvDepth = null;
        lookRealDepthFragment.tvDepthName = null;
        lookRealDepthFragment.row = null;
        lookRealDepthFragment.colum = null;
        lookRealDepthFragment.llDepthName = null;
        lookRealDepthFragment.llRealDepth = null;
        lookRealDepthFragment.standardDepth = null;
        lookRealDepthFragment.difference = null;
        lookRealDepthFragment.tvMax = null;
        lookRealDepthFragment.tvMin = null;
        lookRealDepthFragment.tvDiff = null;
        this.view7f0a00b3.setOnClickListener(null);
        this.view7f0a00b3 = null;
    }
}
