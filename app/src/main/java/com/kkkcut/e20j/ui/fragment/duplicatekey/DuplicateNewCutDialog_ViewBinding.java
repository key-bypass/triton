package com.kkkcut.e20j.ui.fragment.duplicatekey;

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
import com.kkkcut.e20j.androidquick.autolayout.widget.AutoRadioGroup;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DuplicateNewCutDialog_ViewBinding implements Unbinder {
    private DuplicateNewCutDialog target;
    private View view7f0a006c;
    private View view7f0a006d;
    private View view7f0a006e;
    private View view7f0a006f;
    private View view7f0a0075;
    private View view7f0a0081;
    private View view7f0a01f6;
    private View view7f0a01f8;
    private View view7f0a021f;
    private View view7f0a0220;
    private View view7f0a0229;
    private View view7f0a022a;
    private View view7f0a0323;
    private View view7f0a0326;
    private View view7f0a0341;
    private View view7f0a0342;
    private View view7f0a0343;

    public DuplicateNewCutDialog_ViewBinding(final DuplicateNewCutDialog duplicateNewCutDialog, View view) {
        this.target = duplicateNewCutDialog;
        duplicateNewCutDialog.flKey = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_key, "field 'flKey'", FrameLayout.class);
        duplicateNewCutDialog.llDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_depth, "field 'llDepth'", LinearLayout.class);
        duplicateNewCutDialog.flTubular = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_tubular, "field 'flTubular'", FrameLayout.class);
        duplicateNewCutDialog.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        duplicateNewCutDialog.tvRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_remind, "field 'tvRemind'", TextView.class);
        duplicateNewCutDialog.tvTitleLayer = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_layer, "field 'tvTitleLayer'", TextView.class);
        duplicateNewCutDialog.rgLayerCut = (AutoRadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_layer_cut, "field 'rgLayerCut'", AutoRadioGroup.class);
        duplicateNewCutDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_layer_1, "field 'rbLayer1' and method 'onCheckedChanged'");
        duplicateNewCutDialog.rbLayer1 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_layer_1, "field 'rbLayer1'", RadioButton.class);
        this.view7f0a0341 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateNewCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_layer_2, "field 'rbLayer2' and method 'onCheckedChanged'");
        duplicateNewCutDialog.rbLayer2 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_layer_2, "field 'rbLayer2'", RadioButton.class);
        this.view7f0a0342 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateNewCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_layer_3, "field 'rbLayer3' and method 'onCheckedChanged'");
        duplicateNewCutDialog.rbLayer3 = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_layer_3, "field 'rbLayer3'", RadioButton.class);
        this.view7f0a0343 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateNewCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        duplicateNewCutDialog.tvSpeedValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_speed_value, "field 'tvSpeedValue'", TextView.class);
        duplicateNewCutDialog.tvCutDepthSingleKey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_depth_single_key, "field 'tvCutDepthSingleKey'", TextView.class);
        duplicateNewCutDialog.tvDepthValueSingleKey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_value_single_key, "field 'tvDepthValueSingleKey'", TextView.class);
        duplicateNewCutDialog.llCutDepthSingleKey = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_depth_single_key, "field 'llCutDepthSingleKey'", LinearLayout.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_50, "method 'onCheckedChanged'");
        this.view7f0a0326 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateNewCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_100, "method 'onCheckedChanged'");
        this.view7f0a0323 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateNewCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_size_reduce, "method 'onViewClicked'");
        this.view7f0a0220 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_size_add, "method 'onViewClicked'");
        this.view7f0a021f = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_cut, "method 'onViewClicked'");
        this.view7f0a0081 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.iv_speed_add, "method 'onViewClicked'");
        this.view7f0a0229 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.iv_speed_reduce, "method 'onViewClicked'");
        this.view7f0a022a = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.bt_1_0mm, "method 'onViewClicked'");
        this.view7f0a006c = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_1_5mm, "method 'onViewClicked'");
        this.view7f0a006d = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.bt_2_0mm, "method 'onViewClicked'");
        this.view7f0a006e = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.bt_2_5mm, "method 'onViewClicked'");
        this.view7f0a006f = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.iv_depth_add_single_key, "method 'onViewClicked'");
        this.view7f0a01f6 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.iv_depth_reduce_single_key, "method 'onViewClicked'");
        this.view7f0a01f8 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateNewCutDialog_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateNewCutDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DuplicateNewCutDialog duplicateNewCutDialog = this.target;
        if (duplicateNewCutDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        duplicateNewCutDialog.flKey = null;
        duplicateNewCutDialog.llDepth = null;
        duplicateNewCutDialog.flTubular = null;
        duplicateNewCutDialog.tvCutterSize = null;
        duplicateNewCutDialog.tvRemind = null;
        duplicateNewCutDialog.tvTitleLayer = null;
        duplicateNewCutDialog.rgLayerCut = null;
        duplicateNewCutDialog.ivClamp = null;
        duplicateNewCutDialog.rbLayer1 = null;
        duplicateNewCutDialog.rbLayer2 = null;
        duplicateNewCutDialog.rbLayer3 = null;
        duplicateNewCutDialog.tvSpeedValue = null;
        duplicateNewCutDialog.tvCutDepthSingleKey = null;
        duplicateNewCutDialog.tvDepthValueSingleKey = null;
        duplicateNewCutDialog.llCutDepthSingleKey = null;
        ((CompoundButton) this.view7f0a0341).setOnCheckedChangeListener(null);
        this.view7f0a0341 = null;
        ((CompoundButton) this.view7f0a0342).setOnCheckedChangeListener(null);
        this.view7f0a0342 = null;
        ((CompoundButton) this.view7f0a0343).setOnCheckedChangeListener(null);
        this.view7f0a0343 = null;
        ((CompoundButton) this.view7f0a0326).setOnCheckedChangeListener(null);
        this.view7f0a0326 = null;
        ((CompoundButton) this.view7f0a0323).setOnCheckedChangeListener(null);
        this.view7f0a0323 = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a0220.setOnClickListener(null);
        this.view7f0a0220 = null;
        this.view7f0a021f.setOnClickListener(null);
        this.view7f0a021f = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
        this.view7f0a0229.setOnClickListener(null);
        this.view7f0a0229 = null;
        this.view7f0a022a.setOnClickListener(null);
        this.view7f0a022a = null;
        this.view7f0a006c.setOnClickListener(null);
        this.view7f0a006c = null;
        this.view7f0a006d.setOnClickListener(null);
        this.view7f0a006d = null;
        this.view7f0a006e.setOnClickListener(null);
        this.view7f0a006e = null;
        this.view7f0a006f.setOnClickListener(null);
        this.view7f0a006f = null;
        this.view7f0a01f6.setOnClickListener(null);
        this.view7f0a01f6 = null;
        this.view7f0a01f8.setOnClickListener(null);
        this.view7f0a01f8 = null;
    }
}
