package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class IgnitionDoorSearchFragment_ViewBinding implements Unbinder {
    private IgnitionDoorSearchFragment target;
    private View view7f0a0083;
    private View view7f0a009e;
    private View view7f0a009f;
    private View view7f0a00a0;
    private View view7f0a00a1;
    private View view7f0a00a2;
    private View view7f0a00a7;
    private View view7f0a00a8;
    private View view7f0a00bb;
    private View view7f0a00bf;
    private View view7f0a0331;
    private View view7f0a033d;

    public IgnitionDoorSearchFragment_ViewBinding(final IgnitionDoorSearchFragment ignitionDoorSearchFragment, View view) {
        this.target = ignitionDoorSearchFragment;
        ignitionDoorSearchFragment.llTitle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_title, "field 'llTitle'", LinearLayout.class);
        ignitionDoorSearchFragment.llCode = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_code, "field 'llCode'", LinearLayout.class);
        ignitionDoorSearchFragment.llIndex = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_index, "field 'llIndex'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_ignition_to_door, "field 'rbIgnitionToDoor' and method 'onCheckedChanged'");
        ignitionDoorSearchFragment.rbIgnitionToDoor = (RadioButton) Utils.castView(findRequiredView, R.id.rb_ignition_to_door, "field 'rbIgnitionToDoor'", RadioButton.class);
        this.view7f0a033d = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ignitionDoorSearchFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_door_to_ignition, "method 'onCheckedChanged'");
        this.view7f0a0331 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ignitionDoorSearchFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_number_1, "method 'onViewClicked'");
        this.view7f0a009e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_number_2, "method 'onViewClicked'");
        this.view7f0a009f = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_number_3, "method 'onViewClicked'");
        this.view7f0a00a0 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_number_4, "method 'onViewClicked'");
        this.view7f0a00a1 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_number_5, "method 'onViewClicked'");
        this.view7f0a00a2 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_number_last, "method 'onViewClicked'");
        this.view7f0a00a7 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_number_next, "method 'onViewClicked'");
        this.view7f0a00a8 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_delete, "method 'onViewClicked'");
        this.view7f0a0083 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.btn_cancel, "method 'onViewClicked'");
        this.view7f0a00bb = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.btn_ok, "method 'onViewClicked'");
        this.view7f0a00bf = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                ignitionDoorSearchFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        IgnitionDoorSearchFragment ignitionDoorSearchFragment = this.target;
        if (ignitionDoorSearchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        ignitionDoorSearchFragment.llTitle = null;
        ignitionDoorSearchFragment.llCode = null;
        ignitionDoorSearchFragment.llIndex = null;
        ignitionDoorSearchFragment.rbIgnitionToDoor = null;
        ((CompoundButton) this.view7f0a033d).setOnCheckedChangeListener(null);
        this.view7f0a033d = null;
        ((CompoundButton) this.view7f0a0331).setOnCheckedChangeListener(null);
        this.view7f0a0331 = null;
        this.view7f0a009e.setOnClickListener(null);
        this.view7f0a009e = null;
        this.view7f0a009f.setOnClickListener(null);
        this.view7f0a009f = null;
        this.view7f0a00a0.setOnClickListener(null);
        this.view7f0a00a0 = null;
        this.view7f0a00a1.setOnClickListener(null);
        this.view7f0a00a1 = null;
        this.view7f0a00a2.setOnClickListener(null);
        this.view7f0a00a2 = null;
        this.view7f0a00a7.setOnClickListener(null);
        this.view7f0a00a7 = null;
        this.view7f0a00a8.setOnClickListener(null);
        this.view7f0a00a8 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a00bb.setOnClickListener(null);
        this.view7f0a00bb = null;
        this.view7f0a00bf.setOnClickListener(null);
        this.view7f0a00bf = null;
    }
}
