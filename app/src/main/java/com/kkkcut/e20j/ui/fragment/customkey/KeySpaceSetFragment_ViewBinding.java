package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeySpaceSetFragment_ViewBinding implements Unbinder {
    private KeySpaceSetFragment target;
    private View view7f0a0083;
    private View view7f0a0095;
    private View view7f0a009b;
    private View view7f0a009d;
    private View view7f0a009e;
    private View view7f0a009f;
    private View view7f0a00a0;
    private View view7f0a00a1;
    private View view7f0a00a2;
    private View view7f0a00a3;
    private View view7f0a00a4;
    private View view7f0a00a5;
    private View view7f0a00a6;
    private View view7f0a00a7;
    private View view7f0a00a8;
    private View view7f0a01e5;
    private View view7f0a0216;
    private View view7f0a033e;
    private View view7f0a0346;
    private View view7f0a0356;
    private View view7f0a0357;
    private View view7f0a0446;
    private View view7f0a04a9;
    private View view7f0a04ab;

    public KeySpaceSetFragment_ViewBinding(final KeySpaceSetFragment keySpaceSetFragment, View view) {
        this.target = keySpaceSetFragment;
        keySpaceSetFragment.llSpace = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_space, "field 'llSpace'", LinearLayout.class);
        keySpaceSetFragment.llSpaceTool = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_space_tool, "field 'llSpaceTool'", LinearLayout.class);
        keySpaceSetFragment.llIndex = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_index, "field 'llIndex'", LinearLayout.class);
        keySpaceSetFragment.flRowTool = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_row_tool, "field 'flRowTool'", FrameLayout.class);
        keySpaceSetFragment.tvSideRow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_side_row, "field 'tvSideRow'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_row_add, "field 'tvRowAdd' and method 'onViewClicked'");
        keySpaceSetFragment.tvRowAdd = (ImageView) Utils.castView(findRequiredView, R.id.tv_row_add, "field 'tvRowAdd'", ImageView.class);
        this.view7f0a04a9 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_row_reduce, "field 'tvRowReduce' and method 'onViewClicked'");
        keySpaceSetFragment.tvRowReduce = (ImageView) Utils.castView(findRequiredView2, R.id.tv_row_reduce, "field 'tvRowReduce'", ImageView.class);
        this.view7f0a04ab = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        keySpaceSetFragment.tvRows = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rows, "field 'tvRows'", TextView.class);
        keySpaceSetFragment.llSide = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_side, "field 'llSide'", LinearLayout.class);
        keySpaceSetFragment.ivSpace = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_space, "field 'ivSpace'", ImageView.class);
        keySpaceSetFragment.tvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'tvUnit'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_sideB, "field 'rbSideB' and method 'oncheckChanged'");
        keySpaceSetFragment.rbSideB = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_sideB, "field 'rbSideB'", RadioButton.class);
        this.view7f0a0357 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keySpaceSetFragment.oncheckChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_sideA, "field 'rbSideA' and method 'oncheckChanged'");
        keySpaceSetFragment.rbSideA = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_sideA, "field 'rbSideA'", RadioButton.class);
        this.view7f0a0356 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keySpaceSetFragment.oncheckChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_metric, "field 'rbMetric' and method 'oncheckChanged'");
        keySpaceSetFragment.rbMetric = (RadioButton) Utils.castView(findRequiredView5, R.id.rb_metric, "field 'rbMetric'", RadioButton.class);
        this.view7f0a0346 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keySpaceSetFragment.oncheckChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.rb_imperial, "field 'rbInch' and method 'oncheckChanged'");
        keySpaceSetFragment.rbInch = (RadioButton) Utils.castView(findRequiredView6, R.id.rb_imperial, "field 'rbInch'", RadioButton.class);
        this.view7f0a033e = findRequiredView6;
        ((CompoundButton) findRequiredView6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keySpaceSetFragment.oncheckChanged(compoundButton, z);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_number_1, "method 'onViewClicked'");
        this.view7f0a009e = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_number_2, "method 'onViewClicked'");
        this.view7f0a009f = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_number_3, "method 'onViewClicked'");
        this.view7f0a00a0 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_delete, "method 'onViewClicked'");
        this.view7f0a0083 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_number_4, "method 'onViewClicked'");
        this.view7f0a00a1 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.bt_number_5, "method 'onViewClicked'");
        this.view7f0a00a2 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_number_6, "method 'onViewClicked'");
        this.view7f0a00a3 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.bt_number_next, "method 'onViewClicked'");
        this.view7f0a00a8 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.bt_number_7, "method 'onViewClicked'");
        this.view7f0a00a4 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.bt_number_8, "method 'onViewClicked'");
        this.view7f0a00a5 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_number_9, "method 'onViewClicked'");
        this.view7f0a00a6 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.bt_number_last, "method 'onViewClicked'");
        this.view7f0a00a7 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.bt_number_0, "method 'onViewClicked'");
        this.view7f0a009d = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.bt_next, "method 'onViewClicked'");
        this.view7f0a009b = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.iv_add, "method 'onViewClicked'");
        this.view7f0a01e5 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.iv_reduce, "method 'onViewClicked'");
        this.view7f0a0216 = findRequiredView23;
        findRequiredView23.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.23
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView24 = Utils.findRequiredView(view, R.id.tv_auto, "method 'onViewClicked'");
        this.view7f0a0446 = findRequiredView24;
        findRequiredView24.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceSetFragment_ViewBinding.24
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keySpaceSetFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeySpaceSetFragment keySpaceSetFragment = this.target;
        if (keySpaceSetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keySpaceSetFragment.llSpace = null;
        keySpaceSetFragment.llSpaceTool = null;
        keySpaceSetFragment.llIndex = null;
        keySpaceSetFragment.flRowTool = null;
        keySpaceSetFragment.tvSideRow = null;
        keySpaceSetFragment.tvRowAdd = null;
        keySpaceSetFragment.tvRowReduce = null;
        keySpaceSetFragment.tvRows = null;
        keySpaceSetFragment.llSide = null;
        keySpaceSetFragment.ivSpace = null;
        keySpaceSetFragment.tvUnit = null;
        keySpaceSetFragment.rbSideB = null;
        keySpaceSetFragment.rbSideA = null;
        keySpaceSetFragment.rbMetric = null;
        keySpaceSetFragment.rbInch = null;
        this.view7f0a04a9.setOnClickListener(null);
        this.view7f0a04a9 = null;
        this.view7f0a04ab.setOnClickListener(null);
        this.view7f0a04ab = null;
        ((CompoundButton) this.view7f0a0357).setOnCheckedChangeListener(null);
        this.view7f0a0357 = null;
        ((CompoundButton) this.view7f0a0356).setOnCheckedChangeListener(null);
        this.view7f0a0356 = null;
        ((CompoundButton) this.view7f0a0346).setOnCheckedChangeListener(null);
        this.view7f0a0346 = null;
        ((CompoundButton) this.view7f0a033e).setOnCheckedChangeListener(null);
        this.view7f0a033e = null;
        this.view7f0a009e.setOnClickListener(null);
        this.view7f0a009e = null;
        this.view7f0a009f.setOnClickListener(null);
        this.view7f0a009f = null;
        this.view7f0a00a0.setOnClickListener(null);
        this.view7f0a00a0 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a00a1.setOnClickListener(null);
        this.view7f0a00a1 = null;
        this.view7f0a00a2.setOnClickListener(null);
        this.view7f0a00a2 = null;
        this.view7f0a00a3.setOnClickListener(null);
        this.view7f0a00a3 = null;
        this.view7f0a00a8.setOnClickListener(null);
        this.view7f0a00a8 = null;
        this.view7f0a00a4.setOnClickListener(null);
        this.view7f0a00a4 = null;
        this.view7f0a00a5.setOnClickListener(null);
        this.view7f0a00a5 = null;
        this.view7f0a00a6.setOnClickListener(null);
        this.view7f0a00a6 = null;
        this.view7f0a00a7.setOnClickListener(null);
        this.view7f0a00a7 = null;
        this.view7f0a009d.setOnClickListener(null);
        this.view7f0a009d = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
        this.view7f0a01e5.setOnClickListener(null);
        this.view7f0a01e5 = null;
        this.view7f0a0216.setOnClickListener(null);
        this.view7f0a0216 = null;
        this.view7f0a0446.setOnClickListener(null);
        this.view7f0a0446 = null;
    }
}
