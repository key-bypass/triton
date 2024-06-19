package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class LackToothFragment_ViewBinding implements Unbinder {
    private LackToothFragment target;
    private View view7f0a00b4;
    private View view7f0a00b5;

    public LackToothFragment_ViewBinding(final LackToothFragment lackToothFragment, View view) {
        this.target = lackToothFragment;
        lackToothFragment.rvToothList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_tooth_list, "field 'rvToothList'", RecyclerView.class);
        lackToothFragment.llToothcodeContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_toothcode_container, "field 'llToothcodeContainer'", LinearLayout.class);
        lackToothFragment.rvKeyboard = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_keyboard, "field 'rvKeyboard'", RecyclerView.class);
        lackToothFragment.tvMulti = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_multi, "field 'tvMulti'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_search, "method 'onViewClicked'");
        this.view7f0a00b4 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.LackToothFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                lackToothFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_search_offline, "method 'onViewClicked'");
        this.view7f0a00b5 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.LackToothFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                lackToothFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LackToothFragment lackToothFragment = this.target;
        if (lackToothFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        lackToothFragment.rvToothList = null;
        lackToothFragment.llToothcodeContainer = null;
        lackToothFragment.rvKeyboard = null;
        lackToothFragment.tvMulti = null;
        this.view7f0a00b4.setOnClickListener(null);
        this.view7f0a00b4 = null;
        this.view7f0a00b5.setOnClickListener(null);
        this.view7f0a00b5 = null;
    }
}
