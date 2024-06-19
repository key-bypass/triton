package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DimpleDuplicateCutDialog_ViewBinding implements Unbinder {
    private DimpleDuplicateCutDialog target;
    private View view7f0a006d;
    private View view7f0a006e;
    private View view7f0a006f;
    private View view7f0a0075;
    private View view7f0a0081;
    private View view7f0a00e8;
    private View view7f0a00ed;
    private View view7f0a01ef;
    private View view7f0a01f5;
    private View view7f0a01f7;
    private View view7f0a021f;
    private View view7f0a0220;
    private View view7f0a0229;
    private View view7f0a022a;
    private View view7f0a0341;
    private View view7f0a0342;
    private View view7f0a0343;
    private View view7f0a0351;
    private View view7f0a0352;

    public DimpleDuplicateCutDialog_ViewBinding(final DimpleDuplicateCutDialog dimpleDuplicateCutDialog, View view) {
        this.target = dimpleDuplicateCutDialog;
        dimpleDuplicateCutDialog.tvLayerCut = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_layer_cut, "field 'tvLayerCut'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_layer_1, "field 'rbLayer1' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.rbLayer1 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_layer_1, "field 'rbLayer1'", RadioButton.class);
        this.view7f0a0341 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_layer_2, "field 'rbLayer2' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.rbLayer2 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_layer_2, "field 'rbLayer2'", RadioButton.class);
        this.view7f0a0342 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_layer_3, "field 'rbLayer3' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.rbLayer3 = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_layer_3, "field 'rbLayer3'", RadioButton.class);
        this.view7f0a0343 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        dimpleDuplicateCutDialog.rgLayerCut = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_layer_cut, "field 'rgLayerCut'", RadioGroup.class);
        dimpleDuplicateCutDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        dimpleDuplicateCutDialog.ivCutter = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_cutter, "field 'ivCutter'", ImageView.class);
        dimpleDuplicateCutDialog.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        dimpleDuplicateCutDialog.tvSpeedValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_speed_value, "field 'tvSpeedValue'", TextView.class);
        dimpleDuplicateCutDialog.llCutSpeed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_speed, "field 'llCutSpeed'", LinearLayout.class);
        dimpleDuplicateCutDialog.tvCutSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_speed, "field 'tvCutSpeed'", TextView.class);
        dimpleDuplicateCutDialog.ivDepth = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_depth, "field 'ivDepth'", ImageView.class);
        dimpleDuplicateCutDialog.tvDepthValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_value, "field 'tvDepthValue'", TextView.class);
        dimpleDuplicateCutDialog.llCutterSize = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cutter_size, "field 'llCutterSize'", LinearLayout.class);
        dimpleDuplicateCutDialog.llCutDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_depth, "field 'llCutDepth'", LinearLayout.class);
        dimpleDuplicateCutDialog.tvCutDepth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_depth, "field 'tvCutDepth'", TextView.class);
        dimpleDuplicateCutDialog.tvCutterSizeRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size_remind, "field 'tvCutterSizeRemind'", TextView.class);
        dimpleDuplicateCutDialog.tvCutShape = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_shape, "field 'tvCutShape'", TextView.class);
        dimpleDuplicateCutDialog.rgCutShape = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cut_shape, "field 'rgCutShape'", RadioGroup.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_shape_gentle, "field 'rbShapeGentle' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.rbShapeGentle = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_shape_gentle, "field 'rbShapeGentle'", RadioButton.class);
        this.view7f0a0351 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_shape_jagged, "field 'rbShapeJagged' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.rbShapeJagged = (RadioButton) Utils.castView(findRequiredView5, R.id.rb_shape_jagged, "field 'rbShapeJagged'", RadioButton.class);
        this.view7f0a0352 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_1_5mm, "field 'bt15mm' and method 'onViewClicked'");
        dimpleDuplicateCutDialog.bt15mm = (Button) Utils.castView(findRequiredView6, R.id.bt_1_5mm, "field 'bt15mm'", Button.class);
        this.view7f0a006d = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_2_0mm, "field 'bt20mm' and method 'onViewClicked'");
        dimpleDuplicateCutDialog.bt20mm = (Button) Utils.castView(findRequiredView7, R.id.bt_2_0mm, "field 'bt20mm'", Button.class);
        this.view7f0a006e = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_2_5mm, "field 'bt25mm' and method 'onViewClicked'");
        dimpleDuplicateCutDialog.bt25mm = (Button) Utils.castView(findRequiredView8, R.id.bt_2_5mm, "field 'bt25mm'", Button.class);
        this.view7f0a006f = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        dimpleDuplicateCutDialog.llPlasticKey = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_plastic_key, "field 'llPlasticKey'", LinearLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, R.id.cb_plastic_key, "field 'cbPlasticKey' and method 'onCheckedChanged'");
        dimpleDuplicateCutDialog.cbPlasticKey = (CheckBox) Utils.castView(findRequiredView9, R.id.cb_plastic_key, "field 'cbPlasticKey'", CheckBox.class);
        this.view7f0a00ed = findRequiredView9;
        ((CompoundButton) findRequiredView9).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateCutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.cb_fast, "field 'cbFast' and method 'onViewClicked'");
        dimpleDuplicateCutDialog.cbFast = (CheckBox) Utils.castView(findRequiredView10, R.id.cb_fast, "field 'cbFast'", CheckBox.class);
        this.view7f0a00e8 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.iv_size_add, "method 'onViewClicked'");
        this.view7f0a021f = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.iv_size_reduce, "method 'onViewClicked'");
        this.view7f0a0220 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.iv_depth_add, "method 'onViewClicked'");
        this.view7f0a01f5 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.iv_depth_reduce, "method 'onViewClicked'");
        this.view7f0a01f7 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.iv_speed_add, "method 'onViewClicked'");
        this.view7f0a0229 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.iv_speed_reduce, "method 'onViewClicked'");
        this.view7f0a022a = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.bt_cut, "method 'onViewClicked'");
        this.view7f0a0081 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.iv_close, "method 'onViewClicked'");
        this.view7f0a01ef = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateCutDialog_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateCutDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DimpleDuplicateCutDialog dimpleDuplicateCutDialog = this.target;
        if (dimpleDuplicateCutDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dimpleDuplicateCutDialog.tvLayerCut = null;
        dimpleDuplicateCutDialog.rbLayer1 = null;
        dimpleDuplicateCutDialog.rbLayer2 = null;
        dimpleDuplicateCutDialog.rbLayer3 = null;
        dimpleDuplicateCutDialog.rgLayerCut = null;
        dimpleDuplicateCutDialog.ivClamp = null;
        dimpleDuplicateCutDialog.ivCutter = null;
        dimpleDuplicateCutDialog.tvCutterSize = null;
        dimpleDuplicateCutDialog.tvSpeedValue = null;
        dimpleDuplicateCutDialog.llCutSpeed = null;
        dimpleDuplicateCutDialog.tvCutSpeed = null;
        dimpleDuplicateCutDialog.ivDepth = null;
        dimpleDuplicateCutDialog.tvDepthValue = null;
        dimpleDuplicateCutDialog.llCutterSize = null;
        dimpleDuplicateCutDialog.llCutDepth = null;
        dimpleDuplicateCutDialog.tvCutDepth = null;
        dimpleDuplicateCutDialog.tvCutterSizeRemind = null;
        dimpleDuplicateCutDialog.tvCutShape = null;
        dimpleDuplicateCutDialog.rgCutShape = null;
        dimpleDuplicateCutDialog.rbShapeGentle = null;
        dimpleDuplicateCutDialog.rbShapeJagged = null;
        dimpleDuplicateCutDialog.bt15mm = null;
        dimpleDuplicateCutDialog.bt20mm = null;
        dimpleDuplicateCutDialog.bt25mm = null;
        dimpleDuplicateCutDialog.llPlasticKey = null;
        dimpleDuplicateCutDialog.cbPlasticKey = null;
        dimpleDuplicateCutDialog.cbFast = null;
        ((CompoundButton) this.view7f0a0341).setOnCheckedChangeListener(null);
        this.view7f0a0341 = null;
        ((CompoundButton) this.view7f0a0342).setOnCheckedChangeListener(null);
        this.view7f0a0342 = null;
        ((CompoundButton) this.view7f0a0343).setOnCheckedChangeListener(null);
        this.view7f0a0343 = null;
        ((CompoundButton) this.view7f0a0351).setOnCheckedChangeListener(null);
        this.view7f0a0351 = null;
        ((CompoundButton) this.view7f0a0352).setOnCheckedChangeListener(null);
        this.view7f0a0352 = null;
        this.view7f0a006d.setOnClickListener(null);
        this.view7f0a006d = null;
        this.view7f0a006e.setOnClickListener(null);
        this.view7f0a006e = null;
        this.view7f0a006f.setOnClickListener(null);
        this.view7f0a006f = null;
        ((CompoundButton) this.view7f0a00ed).setOnCheckedChangeListener(null);
        this.view7f0a00ed = null;
        this.view7f0a00e8.setOnClickListener(null);
        this.view7f0a00e8 = null;
        this.view7f0a021f.setOnClickListener(null);
        this.view7f0a021f = null;
        this.view7f0a0220.setOnClickListener(null);
        this.view7f0a0220 = null;
        this.view7f0a01f5.setOnClickListener(null);
        this.view7f0a01f5 = null;
        this.view7f0a01f7.setOnClickListener(null);
        this.view7f0a01f7 = null;
        this.view7f0a0229.setOnClickListener(null);
        this.view7f0a0229 = null;
        this.view7f0a022a.setOnClickListener(null);
        this.view7f0a022a = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
        this.view7f0a01ef.setOnClickListener(null);
        this.view7f0a01ef = null;
    }
}
