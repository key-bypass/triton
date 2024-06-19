package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CalibrationFragment_ViewBinding implements Unbinder {
    private CalibrationFragment target;
    private View view7f0a0071;
    private View view7f0a0073;
    private View view7f0a0076;
    private View view7f0a0077;
    private View view7f0a0078;
    private View view7f0a0079;
    private View view7f0a0080;
    private View view7f0a0087;
    private View view7f0a0088;
    private View view7f0a008f;
    private View view7f0a0090;
    private View view7f0a0096;
    private View view7f0a009a;
    private View view7f0a00a9;
    private View view7f0a00ab;
    private View view7f0a00ae;
    private View view7f0a00ba;
    private View view7f0a0327;
    private View view7f0a0328;
    private View view7f0a0329;
    private View view7f0a032d;
    private View view7f0a0330;
    private View view7f0a0345;
    private View view7f0a035d;
    private View view7f0a0367;

    public CalibrationFragment_ViewBinding(final CalibrationFragment calibrationFragment, View view) {
        this.target = calibrationFragment;
        calibrationFragment.llDistanceCalibrate = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_distance_calibrate, "field 'llDistanceCalibrate'", LinearLayout.class);
        calibrationFragment.llCarClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_car_clamp, "field 'llCarClamp'", LinearLayout.class);
        calibrationFragment.llSingleSideClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_single_side_clamp, "field 'llSingleSideClamp'", LinearLayout.class);
        calibrationFragment.llTubularClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_tubular_clamp, "field 'llTubularClamp'", LinearLayout.class);
        calibrationFragment.llAngleKeyClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_angle_key_clamp, "field 'llAngleKeyClamp'", LinearLayout.class);
        calibrationFragment.llMarkingClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_marking_clamp, "field 'llMarkingClamp'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_single_side_clmap, "field 'rbSingleSideClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbSingleSideClamp = (RadioButton) Utils.castView(findRequiredView, R.id.rb_single_side_clmap, "field 'rbSingleSideClamp'", RadioButton.class);
        this.view7f0a035d = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_tubular_clamp, "field 'rbTubularClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbTubularClamp = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_tubular_clamp, "field 'rbTubularClamp'", RadioButton.class);
        this.view7f0a0367 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_angle_clamp, "field 'rbAngleClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbAngleClamp = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_angle_clamp, "field 'rbAngleClamp'", RadioButton.class);
        this.view7f0a0327 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_marking_clamp, "field 'rbMarkingClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbMarkingClamp = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_marking_clamp, "field 'rbMarkingClamp'", RadioButton.class);
        this.view7f0a0345 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_cross_key_clamp, "field 'rbCrossKeyClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbCrossKeyClamp = (RadioButton) Utils.castView(findRequiredView5, R.id.rb_cross_key_clamp, "field 'rbCrossKeyClamp'", RadioButton.class);
        this.view7f0a032d = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.rb_blank_cut_clamp, "field 'rbBlankCutClamp' and method 'onCheckedChanged'");
        calibrationFragment.rbBlankCutClamp = (RadioButton) Utils.castView(findRequiredView6, R.id.rb_blank_cut_clamp, "field 'rbBlankCutClamp'", RadioButton.class);
        this.view7f0a0329 = findRequiredView6;
        ((CompoundButton) findRequiredView6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_car_clamp_d, "field 'btCarClampD' and method 'onViewClicked'");
        calibrationFragment.btCarClampD = (Button) Utils.castView(findRequiredView7, R.id.bt_car_clamp_d, "field 'btCarClampD'", Button.class);
        this.view7f0a0079 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        calibrationFragment.llCroossKeyClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cross_key_clamp, "field 'llCroossKeyClamp'", LinearLayout.class);
        calibrationFragment.llBlankCutClamp = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_blank_cut_calibrate, "field 'llBlankCutClamp'", LinearLayout.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_distance_calibrate_s1a, "field 'btDistanceCalibrateS1a' and method 'onViewClicked'");
        calibrationFragment.btDistanceCalibrateS1a = (Button) Utils.castView(findRequiredView8, R.id.bt_distance_calibrate_s1a, "field 'btDistanceCalibrateS1a'", Button.class);
        this.view7f0a0088 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_car_clamp_b, "field 'btCarClampB' and method 'onViewClicked'");
        calibrationFragment.btCarClampB = (Button) Utils.castView(findRequiredView9, R.id.bt_car_clamp_b, "field 'btCarClampB'", Button.class);
        this.view7f0a0077 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_house_clamp_b, "field 'btSingleKeyClampB' and method 'onViewClicked'");
        calibrationFragment.btSingleKeyClampB = (Button) Utils.castView(findRequiredView10, R.id.bt_house_clamp_b, "field 'btSingleKeyClampB'", Button.class);
        this.view7f0a0090 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.rb_distance_calibrate, "method 'onCheckedChanged'");
        this.view7f0a0330 = findRequiredView11;
        ((CompoundButton) findRequiredView11).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.11
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.rb_autonmobile_clamp, "method 'onCheckedChanged'");
        this.view7f0a0328 = findRequiredView12;
        ((CompoundButton) findRequiredView12).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                calibrationFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_car_clamp_a, "method 'onViewClicked'");
        this.view7f0a0076 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.bt_car_clamp_c, "method 'onViewClicked'");
        this.view7f0a0078 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.bt_tubular_clamp, "method 'onViewClicked'");
        this.view7f0a00ba = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.bt_house_clamp_a, "method 'onViewClicked'");
        this.view7f0a008f = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_angle_clamp, "method 'onViewClicked'");
        this.view7f0a0071 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.bt_distance_calibrate, "method 'onViewClicked'");
        this.view7f0a0087 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.bt_marking_clamp, "method 'onViewClicked'");
        this.view7f0a009a = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.bt_left, "method 'onViewClicked'");
        this.view7f0a0096 = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.bt_right, "method 'onViewClicked'");
        this.view7f0a00ae = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.bt_cross_key_clamp, "method 'onViewClicked'");
        this.view7f0a0080 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.bt_blank_cut_calibrate, "method 'onViewClicked'");
        this.view7f0a0073 = findRequiredView23;
        findRequiredView23.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.23
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView24 = Utils.findRequiredView(view, R.id.bt_on, "method 'onViewClicked'");
        this.view7f0a00ab = findRequiredView24;
        findRequiredView24.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.24
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
        View findRequiredView25 = Utils.findRequiredView(view, R.id.bt_off, "method 'onViewClicked'");
        this.view7f0a00a9 = findRequiredView25;
        findRequiredView25.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CalibrationFragment_ViewBinding.25
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                calibrationFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CalibrationFragment calibrationFragment = this.target;
        if (calibrationFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calibrationFragment.llDistanceCalibrate = null;
        calibrationFragment.llCarClamp = null;
        calibrationFragment.llSingleSideClamp = null;
        calibrationFragment.llTubularClamp = null;
        calibrationFragment.llAngleKeyClamp = null;
        calibrationFragment.llMarkingClamp = null;
        calibrationFragment.rbSingleSideClamp = null;
        calibrationFragment.rbTubularClamp = null;
        calibrationFragment.rbAngleClamp = null;
        calibrationFragment.rbMarkingClamp = null;
        calibrationFragment.rbCrossKeyClamp = null;
        calibrationFragment.rbBlankCutClamp = null;
        calibrationFragment.btCarClampD = null;
        calibrationFragment.llCroossKeyClamp = null;
        calibrationFragment.llBlankCutClamp = null;
        calibrationFragment.btDistanceCalibrateS1a = null;
        calibrationFragment.btCarClampB = null;
        calibrationFragment.btSingleKeyClampB = null;
        ((CompoundButton) this.view7f0a035d).setOnCheckedChangeListener(null);
        this.view7f0a035d = null;
        ((CompoundButton) this.view7f0a0367).setOnCheckedChangeListener(null);
        this.view7f0a0367 = null;
        ((CompoundButton) this.view7f0a0327).setOnCheckedChangeListener(null);
        this.view7f0a0327 = null;
        ((CompoundButton) this.view7f0a0345).setOnCheckedChangeListener(null);
        this.view7f0a0345 = null;
        ((CompoundButton) this.view7f0a032d).setOnCheckedChangeListener(null);
        this.view7f0a032d = null;
        ((CompoundButton) this.view7f0a0329).setOnCheckedChangeListener(null);
        this.view7f0a0329 = null;
        this.view7f0a0079.setOnClickListener(null);
        this.view7f0a0079 = null;
        this.view7f0a0088.setOnClickListener(null);
        this.view7f0a0088 = null;
        this.view7f0a0077.setOnClickListener(null);
        this.view7f0a0077 = null;
        this.view7f0a0090.setOnClickListener(null);
        this.view7f0a0090 = null;
        ((CompoundButton) this.view7f0a0330).setOnCheckedChangeListener(null);
        this.view7f0a0330 = null;
        ((CompoundButton) this.view7f0a0328).setOnCheckedChangeListener(null);
        this.view7f0a0328 = null;
        this.view7f0a0076.setOnClickListener(null);
        this.view7f0a0076 = null;
        this.view7f0a0078.setOnClickListener(null);
        this.view7f0a0078 = null;
        this.view7f0a00ba.setOnClickListener(null);
        this.view7f0a00ba = null;
        this.view7f0a008f.setOnClickListener(null);
        this.view7f0a008f = null;
        this.view7f0a0071.setOnClickListener(null);
        this.view7f0a0071 = null;
        this.view7f0a0087.setOnClickListener(null);
        this.view7f0a0087 = null;
        this.view7f0a009a.setOnClickListener(null);
        this.view7f0a009a = null;
        this.view7f0a0096.setOnClickListener(null);
        this.view7f0a0096 = null;
        this.view7f0a00ae.setOnClickListener(null);
        this.view7f0a00ae = null;
        this.view7f0a0080.setOnClickListener(null);
        this.view7f0a0080 = null;
        this.view7f0a0073.setOnClickListener(null);
        this.view7f0a0073 = null;
        this.view7f0a00ab.setOnClickListener(null);
        this.view7f0a00ab = null;
        this.view7f0a00a9.setOnClickListener(null);
        this.view7f0a00a9 = null;
    }
}
