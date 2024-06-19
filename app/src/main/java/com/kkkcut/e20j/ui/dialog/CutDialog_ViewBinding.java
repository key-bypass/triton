package com.kkkcut.e20j.ui.dialog;

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
public class CutDialog_ViewBinding implements Unbinder {
    private CutDialog target;
    private View view7f0a006d;
    private View view7f0a006e;
    private View view7f0a006f;
    private View view7f0a0075;
    private View view7f0a0081;
    private View view7f0a00e8;
    private View view7f0a00ed;
    private View view7f0a01ef;
    private View view7f0a01f5;
    private View view7f0a01f6;
    private View view7f0a01f7;
    private View view7f0a01f8;
    private View view7f0a0209;
    private View view7f0a020a;
    private View view7f0a021f;
    private View view7f0a0220;
    private View view7f0a0229;
    private View view7f0a022a;
    private View view7f0a0323;
    private View view7f0a0326;
    private View view7f0a0341;
    private View view7f0a0342;
    private View view7f0a0343;
    private View view7f0a034b;
    private View view7f0a0351;
    private View view7f0a0352;
    private View view7f0a0369;

    public CutDialog_ViewBinding(final CutDialog cutDialog, View view) {
        this.target = cutDialog;
        cutDialog.tvLayerCut = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_layer_cut, "field 'tvLayerCut'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_layer_1, "field 'rbLayer1' and method 'onCheckedChanged'");
        cutDialog.rbLayer1 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_layer_1, "field 'rbLayer1'", RadioButton.class);
        this.view7f0a0341 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_layer_2, "field 'rbLayer2' and method 'onCheckedChanged'");
        cutDialog.rbLayer2 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_layer_2, "field 'rbLayer2'", RadioButton.class);
        this.view7f0a0342 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_layer_3, "field 'rbLayer3' and method 'onCheckedChanged'");
        cutDialog.rbLayer3 = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_layer_3, "field 'rbLayer3'", RadioButton.class);
        this.view7f0a0343 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        cutDialog.rgLayerCut = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_layer_cut, "field 'rgLayerCut'", RadioGroup.class);
        cutDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        cutDialog.ivCutter = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_cutter, "field 'ivCutter'", ImageView.class);
        cutDialog.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        cutDialog.tvSpeedValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_speed_value, "field 'tvSpeedValue'", TextView.class);
        cutDialog.llCutSpeed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_speed, "field 'llCutSpeed'", LinearLayout.class);
        cutDialog.tvCutSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_speed, "field 'tvCutSpeed'", TextView.class);
        cutDialog.ivDepth = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_depth, "field 'ivDepth'", ImageView.class);
        cutDialog.tvDepthValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_value, "field 'tvDepthValue'", TextView.class);
        cutDialog.llCutterSize = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cutter_size, "field 'llCutterSize'", LinearLayout.class);
        cutDialog.llCutDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_depth, "field 'llCutDepth'", LinearLayout.class);
        cutDialog.tvCutDepth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_depth, "field 'tvCutDepth'", TextView.class);
        cutDialog.tvCutterSizeRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size_remind, "field 'tvCutterSizeRemind'", TextView.class);
        cutDialog.tvCutShape = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_shape, "field 'tvCutShape'", TextView.class);
        cutDialog.rgCutShape = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cut_shape, "field 'rgCutShape'", RadioGroup.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_shape_gentle, "field 'rbShapeGentle' and method 'onCheckedChanged'");
        cutDialog.rbShapeGentle = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_shape_gentle, "field 'rbShapeGentle'", RadioButton.class);
        this.view7f0a0351 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_shape_jagged, "field 'rbShapeJagged' and method 'onCheckedChanged'");
        cutDialog.rbShapeJagged = (RadioButton) Utils.castView(findRequiredView5, R.id.rb_shape_jagged, "field 'rbShapeJagged'", RadioButton.class);
        this.view7f0a0352 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_1_5mm, "field 'bt15mm' and method 'onViewClicked'");
        cutDialog.bt15mm = (Button) Utils.castView(findRequiredView6, R.id.bt_1_5mm, "field 'bt15mm'", Button.class);
        this.view7f0a006d = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_2_0mm, "field 'bt20mm' and method 'onViewClicked'");
        cutDialog.bt20mm = (Button) Utils.castView(findRequiredView7, R.id.bt_2_0mm, "field 'bt20mm'", Button.class);
        this.view7f0a006e = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_2_5mm, "field 'bt25mm' and method 'onViewClicked'");
        cutDialog.bt25mm = (Button) Utils.castView(findRequiredView8, R.id.bt_2_5mm, "field 'bt25mm'", Button.class);
        this.view7f0a006f = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        cutDialog.llPlasticKey = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_plastic_key, "field 'llPlasticKey'", LinearLayout.class);
        View findRequiredView9 = Utils.findRequiredView(view, R.id.cb_plastic_key, "field 'cbPlasticKey' and method 'onCheckedChanged'");
        cutDialog.cbPlasticKey = (CheckBox) Utils.castView(findRequiredView9, R.id.cb_plastic_key, "field 'cbPlasticKey'", CheckBox.class);
        this.view7f0a00ed = findRequiredView9;
        ((CompoundButton) findRequiredView9).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        cutDialog.llHighHandleMode = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_high_handle_mode, "field 'llHighHandleMode'", LinearLayout.class);
        cutDialog.cbHighHandleMode = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_high_handle_mode, "field 'cbHighHandleMode'", CheckBox.class);
        View findRequiredView10 = Utils.findRequiredView(view, R.id.cb_fast, "field 'cbFast' and method 'onViewClicked'");
        cutDialog.cbFast = (CheckBox) Utils.castView(findRequiredView10, R.id.cb_fast, "field 'cbFast'", CheckBox.class);
        this.view7f0a00e8 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        cutDialog.tvCutMode = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_mode, "field 'tvCutMode'", TextView.class);
        cutDialog.rgCutMode = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cut_mode, "field 'rgCutMode'", RadioGroup.class);
        View findRequiredView11 = Utils.findRequiredView(view, R.id.rb_up_down_cutting, "field 'rbUpDownCutting' and method 'onCheckedChanged'");
        cutDialog.rbUpDownCutting = (RadioButton) Utils.castView(findRequiredView11, R.id.rb_up_down_cutting, "field 'rbUpDownCutting'", RadioButton.class);
        this.view7f0a0369 = findRequiredView11;
        ((CompoundButton) findRequiredView11).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.11
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        cutDialog.tvCutDepthSingleKey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_depth_single_key, "field 'tvCutDepthSingleKey'", TextView.class);
        cutDialog.tvDepthValueSingleKey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_value_single_key, "field 'tvDepthValueSingleKey'", TextView.class);
        cutDialog.llCutDepthSingleKey = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_depth_single_key, "field 'llCutDepthSingleKey'", LinearLayout.class);
        cutDialog.tvTitleDecoderDize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_decoder_size, "field 'tvTitleDecoderDize'", TextView.class);
        View findRequiredView12 = Utils.findRequiredView(view, R.id.rb_50, "field 'rb50' and method 'onCheckedChanged'");
        cutDialog.rb50 = (RadioButton) Utils.castView(findRequiredView12, R.id.rb_50, "field 'rb50'", RadioButton.class);
        this.view7f0a0326 = findRequiredView12;
        ((CompoundButton) findRequiredView12).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        cutDialog.rgDecodeSize = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_decode_size, "field 'rgDecodeSize'", RadioGroup.class);
        cutDialog.tvSlantCorrection = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_slant_correction, "field 'tvSlantCorrection'", TextView.class);
        cutDialog.cbSlantCorrection = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_slant_correction, "field 'cbSlantCorrection'", CheckBox.class);
        cutDialog.tvTitleDepth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_depth, "field 'tvTitleDepth'", TextView.class);
        cutDialog.llDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_depth, "field 'llDepth'", LinearLayout.class);
        cutDialog.llDepthContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_depth_container, "field 'llDepthContainer'", LinearLayout.class);
        cutDialog.llIndex = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_index, "field 'llIndex'", LinearLayout.class);
        cutDialog.llHu101Length = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_hu101_length, "field 'llHu101Length'", LinearLayout.class);
        cutDialog.tvLengthValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_length_value, "field 'tvLengthValue'", TextView.class);
        cutDialog.tvTitleHu101 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_hu101, "field 'tvTitleHu101'", TextView.class);
        View findRequiredView13 = Utils.findRequiredView(view, R.id.rb_rotate_cutting, "method 'onCheckedChanged'");
        this.view7f0a034b = findRequiredView13;
        ((CompoundButton) findRequiredView13).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.13
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.rb_100, "method 'onCheckedChanged'");
        this.view7f0a0323 = findRequiredView14;
        ((CompoundButton) findRequiredView14).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutDialog.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.iv_size_add, "method 'onViewClicked'");
        this.view7f0a021f = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.iv_size_reduce, "method 'onViewClicked'");
        this.view7f0a0220 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.iv_depth_add, "method 'onViewClicked'");
        this.view7f0a01f5 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.iv_depth_reduce, "method 'onViewClicked'");
        this.view7f0a01f7 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.iv_speed_add, "method 'onViewClicked'");
        this.view7f0a0229 = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.iv_speed_reduce, "method 'onViewClicked'");
        this.view7f0a022a = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.bt_cut, "method 'onViewClicked'");
        this.view7f0a0081 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.iv_close, "method 'onViewClicked'");
        this.view7f0a01ef = findRequiredView23;
        findRequiredView23.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.23
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView24 = Utils.findRequiredView(view, R.id.iv_depth_add_single_key, "method 'onViewClicked'");
        this.view7f0a01f6 = findRequiredView24;
        findRequiredView24.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.24
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView25 = Utils.findRequiredView(view, R.id.iv_depth_reduce_single_key, "method 'onViewClicked'");
        this.view7f0a01f8 = findRequiredView25;
        findRequiredView25.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.25
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView26 = Utils.findRequiredView(view, R.id.iv_length_reduce, "method 'onViewClicked'");
        this.view7f0a020a = findRequiredView26;
        findRequiredView26.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.26
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView27 = Utils.findRequiredView(view, R.id.iv_length_add, "method 'onViewClicked'");
        this.view7f0a0209 = findRequiredView27;
        findRequiredView27.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.CutDialog_ViewBinding.27
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cutDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CutDialog cutDialog = this.target;
        if (cutDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cutDialog.tvLayerCut = null;
        cutDialog.rbLayer1 = null;
        cutDialog.rbLayer2 = null;
        cutDialog.rbLayer3 = null;
        cutDialog.rgLayerCut = null;
        cutDialog.ivClamp = null;
        cutDialog.ivCutter = null;
        cutDialog.tvCutterSize = null;
        cutDialog.tvSpeedValue = null;
        cutDialog.llCutSpeed = null;
        cutDialog.tvCutSpeed = null;
        cutDialog.ivDepth = null;
        cutDialog.tvDepthValue = null;
        cutDialog.llCutterSize = null;
        cutDialog.llCutDepth = null;
        cutDialog.tvCutDepth = null;
        cutDialog.tvCutterSizeRemind = null;
        cutDialog.tvCutShape = null;
        cutDialog.rgCutShape = null;
        cutDialog.rbShapeGentle = null;
        cutDialog.rbShapeJagged = null;
        cutDialog.bt15mm = null;
        cutDialog.bt20mm = null;
        cutDialog.bt25mm = null;
        cutDialog.llPlasticKey = null;
        cutDialog.cbPlasticKey = null;
        cutDialog.llHighHandleMode = null;
        cutDialog.cbHighHandleMode = null;
        cutDialog.cbFast = null;
        cutDialog.tvCutMode = null;
        cutDialog.rgCutMode = null;
        cutDialog.rbUpDownCutting = null;
        cutDialog.tvCutDepthSingleKey = null;
        cutDialog.tvDepthValueSingleKey = null;
        cutDialog.llCutDepthSingleKey = null;
        cutDialog.tvTitleDecoderDize = null;
        cutDialog.rb50 = null;
        cutDialog.rgDecodeSize = null;
        cutDialog.tvSlantCorrection = null;
        cutDialog.cbSlantCorrection = null;
        cutDialog.tvTitleDepth = null;
        cutDialog.llDepth = null;
        cutDialog.llDepthContainer = null;
        cutDialog.llIndex = null;
        cutDialog.llHu101Length = null;
        cutDialog.tvLengthValue = null;
        cutDialog.tvTitleHu101 = null;
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
        ((CompoundButton) this.view7f0a0369).setOnCheckedChangeListener(null);
        this.view7f0a0369 = null;
        ((CompoundButton) this.view7f0a0326).setOnCheckedChangeListener(null);
        this.view7f0a0326 = null;
        ((CompoundButton) this.view7f0a034b).setOnCheckedChangeListener(null);
        this.view7f0a034b = null;
        ((CompoundButton) this.view7f0a0323).setOnCheckedChangeListener(null);
        this.view7f0a0323 = null;
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
        this.view7f0a01f6.setOnClickListener(null);
        this.view7f0a01f6 = null;
        this.view7f0a01f8.setOnClickListener(null);
        this.view7f0a01f8 = null;
        this.view7f0a020a.setOnClickListener(null);
        this.view7f0a020a = null;
        this.view7f0a0209.setOnClickListener(null);
        this.view7f0a0209 = null;
    }
}
