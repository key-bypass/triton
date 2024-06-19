package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DeviceCenterFragment_ViewBinding implements Unbinder {
    private DeviceCenterFragment target;
    private View view7f0a0210;
    private View view7f0a0257;
    private View view7f0a025f;
    private View view7f0a0261;
    private View view7f0a0280;
    private View view7f0a0283;

    public DeviceCenterFragment_ViewBinding(final DeviceCenterFragment deviceCenterFragment, View view) {
        this.target = deviceCenterFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_message, "method 'onViewClicked'");
        this.view7f0a0210 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ll_language_selection, "method 'onViewClicked'");
        this.view7f0a0283 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.ll_check_update, "method 'onViewClicked'");
        this.view7f0a0261 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.ll_about_us, "method 'onViewClicked'");
        this.view7f0a0257 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.ll_instructions, "method 'onViewClicked'");
        this.view7f0a0280 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.ll_bluetooth, "method 'onViewClicked'");
        this.view7f0a025f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.DeviceCenterFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceCenterFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a0210.setOnClickListener(null);
        this.view7f0a0210 = null;
        this.view7f0a0283.setOnClickListener(null);
        this.view7f0a0283 = null;
        this.view7f0a0261.setOnClickListener(null);
        this.view7f0a0261 = null;
        this.view7f0a0257.setOnClickListener(null);
        this.view7f0a0257 = null;
        this.view7f0a0280.setOnClickListener(null);
        this.view7f0a0280 = null;
        this.view7f0a025f.setOnClickListener(null);
        this.view7f0a025f = null;
    }
}
