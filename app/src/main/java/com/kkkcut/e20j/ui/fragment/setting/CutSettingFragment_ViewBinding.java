package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CutSettingFragment_ViewBinding implements Unbinder {
    private CutSettingFragment target;
    private View view7f0a00cb;
    private View view7f0a00cc;
    private View view7f0a00cd;
    private View view7f0a00ce;
    private View view7f0a00d6;
    private View view7f0a00d7;
    private View view7f0a00d8;
    private View view7f0a00d9;
    private View view7f0a00db;
    private View view7f0a00dc;
    private View view7f0a00dd;
    private View view7f0a00de;
    private View view7f0a00df;
    private View view7f0a00e0;
    private View view7f0a00e1;
    private View view7f0a00e2;
    private View view7f0a00e3;
    private View view7f0a00e4;
    private View view7f0a00e5;
    private View view7f0a00e6;
    private View view7f0a00f1;
    private View view7f0a00f2;
    private View view7f0a00f3;
    private View view7f0a00f4;
    private View view7f0a00f5;
    private View view7f0a00f6;
    private View view7f0a00f7;
    private View view7f0a00f8;
    private View view7f0a00f9;
    private View view7f0a00fa;
    private View view7f0a00fb;
    private View view7f0a00fc;
    private View view7f0a00fe;
    private View view7f0a00ff;
    private View view7f0a0100;
    private View view7f0a0101;

    public CutSettingFragment_ViewBinding(final CutSettingFragment cutSettingFragment, View view) {
        this.target = cutSettingFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.cb_singlekey_1, "field 'cbSinglekey1' and method 'onCheckedChanged'");
        cutSettingFragment.cbSinglekey1 = (CheckBox) Utils.castView(findRequiredView, R.id.cb_singlekey_1, "field 'cbSinglekey1'", CheckBox.class);
        this.view7f0a00f9 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.cb_doublekey_1, "field 'cbDoublekey1' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoublekey1 = (CheckBox) Utils.castView(findRequiredView2, R.id.cb_doublekey_1, "field 'cbDoublekey1'", CheckBox.class);
        this.view7f0a00e3 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.cb_single_internal_1, "field 'cbSingleInternal1' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleInternal1 = (CheckBox) Utils.castView(findRequiredView3, R.id.cb_single_internal_1, "field 'cbSingleInternal1'", CheckBox.class);
        this.view7f0a00f5 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.cb_single_external_1, "field 'cbSingleExternal1' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleExternal1 = (CheckBox) Utils.castView(findRequiredView4, R.id.cb_single_external_1, "field 'cbSingleExternal1'", CheckBox.class);
        this.view7f0a00f1 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.cb_double_internal_1, "field 'cbDoubleInternal1' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleInternal1 = (CheckBox) Utils.castView(findRequiredView5, R.id.cb_double_internal_1, "field 'cbDoubleInternal1'", CheckBox.class);
        this.view7f0a00df = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.cb_double_external_1, "field 'cbDoubleExternal1' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleExternal1 = (CheckBox) Utils.castView(findRequiredView6, R.id.cb_double_external_1, "field 'cbDoubleExternal1'", CheckBox.class);
        this.view7f0a00db = findRequiredView6;
        ((CompoundButton) findRequiredView6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.cb_dimple_1, "field 'cbDimple1' and method 'onCheckedChanged'");
        cutSettingFragment.cbDimple1 = (CheckBox) Utils.castView(findRequiredView7, R.id.cb_dimple_1, "field 'cbDimple1'", CheckBox.class);
        this.view7f0a00d6 = findRequiredView7;
        ((CompoundButton) findRequiredView7).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.cb_tubular_1, "field 'cbTubular1' and method 'onCheckedChanged'");
        cutSettingFragment.cbTubular1 = (CheckBox) Utils.castView(findRequiredView8, R.id.cb_tubular_1, "field 'cbTubular1'", CheckBox.class);
        this.view7f0a00fe = findRequiredView8;
        ((CompoundButton) findRequiredView8).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.cb_angle_1, "field 'cbAngle1' and method 'onCheckedChanged'");
        cutSettingFragment.cbAngle1 = (CheckBox) Utils.castView(findRequiredView9, R.id.cb_angle_1, "field 'cbAngle1'", CheckBox.class);
        this.view7f0a00cb = findRequiredView9;
        ((CompoundButton) findRequiredView9).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.cb_singlekey_2, "field 'cbSinglekey2' and method 'onCheckedChanged'");
        cutSettingFragment.cbSinglekey2 = (CheckBox) Utils.castView(findRequiredView10, R.id.cb_singlekey_2, "field 'cbSinglekey2'", CheckBox.class);
        this.view7f0a00fa = findRequiredView10;
        ((CompoundButton) findRequiredView10).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.cb_doublekey_2, "field 'cbDoublekey2' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoublekey2 = (CheckBox) Utils.castView(findRequiredView11, R.id.cb_doublekey_2, "field 'cbDoublekey2'", CheckBox.class);
        this.view7f0a00e4 = findRequiredView11;
        ((CompoundButton) findRequiredView11).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.11
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.cb_single_internal_2, "field 'cbSingleInternal2' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleInternal2 = (CheckBox) Utils.castView(findRequiredView12, R.id.cb_single_internal_2, "field 'cbSingleInternal2'", CheckBox.class);
        this.view7f0a00f6 = findRequiredView12;
        ((CompoundButton) findRequiredView12).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.cb_single_external_2, "field 'cbSingleExternal2' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleExternal2 = (CheckBox) Utils.castView(findRequiredView13, R.id.cb_single_external_2, "field 'cbSingleExternal2'", CheckBox.class);
        this.view7f0a00f2 = findRequiredView13;
        ((CompoundButton) findRequiredView13).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.13
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.cb_double_internal_2, "field 'cbDoubleInternal2' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleInternal2 = (CheckBox) Utils.castView(findRequiredView14, R.id.cb_double_internal_2, "field 'cbDoubleInternal2'", CheckBox.class);
        this.view7f0a00e0 = findRequiredView14;
        ((CompoundButton) findRequiredView14).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.cb_double_external_2, "field 'cbDoubleExternal2' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleExternal2 = (CheckBox) Utils.castView(findRequiredView15, R.id.cb_double_external_2, "field 'cbDoubleExternal2'", CheckBox.class);
        this.view7f0a00dc = findRequiredView15;
        ((CompoundButton) findRequiredView15).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.15
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.cb_dimple_2, "field 'cbDimple2' and method 'onCheckedChanged'");
        cutSettingFragment.cbDimple2 = (CheckBox) Utils.castView(findRequiredView16, R.id.cb_dimple_2, "field 'cbDimple2'", CheckBox.class);
        this.view7f0a00d7 = findRequiredView16;
        ((CompoundButton) findRequiredView16).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.16
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.cb_tubular_2, "field 'cbTubular2' and method 'onCheckedChanged'");
        cutSettingFragment.cbTubular2 = (CheckBox) Utils.castView(findRequiredView17, R.id.cb_tubular_2, "field 'cbTubular2'", CheckBox.class);
        this.view7f0a00ff = findRequiredView17;
        ((CompoundButton) findRequiredView17).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.cb_angle_2, "field 'cbAngle2' and method 'onCheckedChanged'");
        cutSettingFragment.cbAngle2 = (CheckBox) Utils.castView(findRequiredView18, R.id.cb_angle_2, "field 'cbAngle2'", CheckBox.class);
        this.view7f0a00cc = findRequiredView18;
        ((CompoundButton) findRequiredView18).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.18
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.cb_singlekey_3, "field 'cbSinglekey3' and method 'onCheckedChanged'");
        cutSettingFragment.cbSinglekey3 = (CheckBox) Utils.castView(findRequiredView19, R.id.cb_singlekey_3, "field 'cbSinglekey3'", CheckBox.class);
        this.view7f0a00fb = findRequiredView19;
        ((CompoundButton) findRequiredView19).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.19
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.cb_doublekey_3, "field 'cbDoublekey3' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoublekey3 = (CheckBox) Utils.castView(findRequiredView20, R.id.cb_doublekey_3, "field 'cbDoublekey3'", CheckBox.class);
        this.view7f0a00e5 = findRequiredView20;
        ((CompoundButton) findRequiredView20).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.20
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.cb_single_internal_3, "field 'cbSingleInternal3' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleInternal3 = (CheckBox) Utils.castView(findRequiredView21, R.id.cb_single_internal_3, "field 'cbSingleInternal3'", CheckBox.class);
        this.view7f0a00f7 = findRequiredView21;
        ((CompoundButton) findRequiredView21).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.21
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.cb_single_external_3, "field 'cbSingleExternal3' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleExternal3 = (CheckBox) Utils.castView(findRequiredView22, R.id.cb_single_external_3, "field 'cbSingleExternal3'", CheckBox.class);
        this.view7f0a00f3 = findRequiredView22;
        ((CompoundButton) findRequiredView22).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.22
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.cb_double_internal_3, "field 'cbDoubleInternal3' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleInternal3 = (CheckBox) Utils.castView(findRequiredView23, R.id.cb_double_internal_3, "field 'cbDoubleInternal3'", CheckBox.class);
        this.view7f0a00e1 = findRequiredView23;
        ((CompoundButton) findRequiredView23).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.23
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView24 = Utils.findRequiredView(view, R.id.cb_double_external_3, "field 'cbDoubleExternal3' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleExternal3 = (CheckBox) Utils.castView(findRequiredView24, R.id.cb_double_external_3, "field 'cbDoubleExternal3'", CheckBox.class);
        this.view7f0a00dd = findRequiredView24;
        ((CompoundButton) findRequiredView24).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.24
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView25 = Utils.findRequiredView(view, R.id.cb_dimple_3, "field 'cbDimple3' and method 'onCheckedChanged'");
        cutSettingFragment.cbDimple3 = (CheckBox) Utils.castView(findRequiredView25, R.id.cb_dimple_3, "field 'cbDimple3'", CheckBox.class);
        this.view7f0a00d8 = findRequiredView25;
        ((CompoundButton) findRequiredView25).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.25
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView26 = Utils.findRequiredView(view, R.id.cb_tubular_3, "field 'cbTubular3' and method 'onCheckedChanged'");
        cutSettingFragment.cbTubular3 = (CheckBox) Utils.castView(findRequiredView26, R.id.cb_tubular_3, "field 'cbTubular3'", CheckBox.class);
        this.view7f0a0100 = findRequiredView26;
        ((CompoundButton) findRequiredView26).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.26
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView27 = Utils.findRequiredView(view, R.id.cb_angle_3, "field 'cbAngle3' and method 'onCheckedChanged'");
        cutSettingFragment.cbAngle3 = (CheckBox) Utils.castView(findRequiredView27, R.id.cb_angle_3, "field 'cbAngle3'", CheckBox.class);
        this.view7f0a00cd = findRequiredView27;
        ((CompoundButton) findRequiredView27).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.27
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView28 = Utils.findRequiredView(view, R.id.cb_singlekey_4, "field 'cbSinglekey4' and method 'onCheckedChanged'");
        cutSettingFragment.cbSinglekey4 = (CheckBox) Utils.castView(findRequiredView28, R.id.cb_singlekey_4, "field 'cbSinglekey4'", CheckBox.class);
        this.view7f0a00fc = findRequiredView28;
        ((CompoundButton) findRequiredView28).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.28
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView29 = Utils.findRequiredView(view, R.id.cb_doublekey_4, "field 'cbDoublekey4' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoublekey4 = (CheckBox) Utils.castView(findRequiredView29, R.id.cb_doublekey_4, "field 'cbDoublekey4'", CheckBox.class);
        this.view7f0a00e6 = findRequiredView29;
        ((CompoundButton) findRequiredView29).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.29
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView30 = Utils.findRequiredView(view, R.id.cb_single_internal_4, "field 'cbSingleInternal4' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleInternal4 = (CheckBox) Utils.castView(findRequiredView30, R.id.cb_single_internal_4, "field 'cbSingleInternal4'", CheckBox.class);
        this.view7f0a00f8 = findRequiredView30;
        ((CompoundButton) findRequiredView30).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.30
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView31 = Utils.findRequiredView(view, R.id.cb_single_external_4, "field 'cbSingleExternal4' and method 'onCheckedChanged'");
        cutSettingFragment.cbSingleExternal4 = (CheckBox) Utils.castView(findRequiredView31, R.id.cb_single_external_4, "field 'cbSingleExternal4'", CheckBox.class);
        this.view7f0a00f4 = findRequiredView31;
        ((CompoundButton) findRequiredView31).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.31
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView32 = Utils.findRequiredView(view, R.id.cb_double_internal_4, "field 'cbDoubleInternal4' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleInternal4 = (CheckBox) Utils.castView(findRequiredView32, R.id.cb_double_internal_4, "field 'cbDoubleInternal4'", CheckBox.class);
        this.view7f0a00e2 = findRequiredView32;
        ((CompoundButton) findRequiredView32).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.32
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView33 = Utils.findRequiredView(view, R.id.cb_double_external_4, "field 'cbDoubleExternal4' and method 'onCheckedChanged'");
        cutSettingFragment.cbDoubleExternal4 = (CheckBox) Utils.castView(findRequiredView33, R.id.cb_double_external_4, "field 'cbDoubleExternal4'", CheckBox.class);
        this.view7f0a00de = findRequiredView33;
        ((CompoundButton) findRequiredView33).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.33
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView34 = Utils.findRequiredView(view, R.id.cb_dimple_4, "field 'cbDimple4' and method 'onCheckedChanged'");
        cutSettingFragment.cbDimple4 = (CheckBox) Utils.castView(findRequiredView34, R.id.cb_dimple_4, "field 'cbDimple4'", CheckBox.class);
        this.view7f0a00d9 = findRequiredView34;
        ((CompoundButton) findRequiredView34).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.34
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView35 = Utils.findRequiredView(view, R.id.cb_tubular_4, "field 'cbTubular4' and method 'onCheckedChanged'");
        cutSettingFragment.cbTubular4 = (CheckBox) Utils.castView(findRequiredView35, R.id.cb_tubular_4, "field 'cbTubular4'", CheckBox.class);
        this.view7f0a0101 = findRequiredView35;
        ((CompoundButton) findRequiredView35).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.35
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView36 = Utils.findRequiredView(view, R.id.cb_angle_4, "field 'cbAngle4' and method 'onCheckedChanged'");
        cutSettingFragment.cbAngle4 = (CheckBox) Utils.castView(findRequiredView36, R.id.cb_angle_4, "field 'cbAngle4'", CheckBox.class);
        this.view7f0a00ce = findRequiredView36;
        ((CompoundButton) findRequiredView36).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.CutSettingFragment_ViewBinding.36
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                cutSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CutSettingFragment cutSettingFragment = this.target;
        if (cutSettingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cutSettingFragment.cbSinglekey1 = null;
        cutSettingFragment.cbDoublekey1 = null;
        cutSettingFragment.cbSingleInternal1 = null;
        cutSettingFragment.cbSingleExternal1 = null;
        cutSettingFragment.cbDoubleInternal1 = null;
        cutSettingFragment.cbDoubleExternal1 = null;
        cutSettingFragment.cbDimple1 = null;
        cutSettingFragment.cbTubular1 = null;
        cutSettingFragment.cbAngle1 = null;
        cutSettingFragment.cbSinglekey2 = null;
        cutSettingFragment.cbDoublekey2 = null;
        cutSettingFragment.cbSingleInternal2 = null;
        cutSettingFragment.cbSingleExternal2 = null;
        cutSettingFragment.cbDoubleInternal2 = null;
        cutSettingFragment.cbDoubleExternal2 = null;
        cutSettingFragment.cbDimple2 = null;
        cutSettingFragment.cbTubular2 = null;
        cutSettingFragment.cbAngle2 = null;
        cutSettingFragment.cbSinglekey3 = null;
        cutSettingFragment.cbDoublekey3 = null;
        cutSettingFragment.cbSingleInternal3 = null;
        cutSettingFragment.cbSingleExternal3 = null;
        cutSettingFragment.cbDoubleInternal3 = null;
        cutSettingFragment.cbDoubleExternal3 = null;
        cutSettingFragment.cbDimple3 = null;
        cutSettingFragment.cbTubular3 = null;
        cutSettingFragment.cbAngle3 = null;
        cutSettingFragment.cbSinglekey4 = null;
        cutSettingFragment.cbDoublekey4 = null;
        cutSettingFragment.cbSingleInternal4 = null;
        cutSettingFragment.cbSingleExternal4 = null;
        cutSettingFragment.cbDoubleInternal4 = null;
        cutSettingFragment.cbDoubleExternal4 = null;
        cutSettingFragment.cbDimple4 = null;
        cutSettingFragment.cbTubular4 = null;
        cutSettingFragment.cbAngle4 = null;
        ((CompoundButton) this.view7f0a00f9).setOnCheckedChangeListener(null);
        this.view7f0a00f9 = null;
        ((CompoundButton) this.view7f0a00e3).setOnCheckedChangeListener(null);
        this.view7f0a00e3 = null;
        ((CompoundButton) this.view7f0a00f5).setOnCheckedChangeListener(null);
        this.view7f0a00f5 = null;
        ((CompoundButton) this.view7f0a00f1).setOnCheckedChangeListener(null);
        this.view7f0a00f1 = null;
        ((CompoundButton) this.view7f0a00df).setOnCheckedChangeListener(null);
        this.view7f0a00df = null;
        ((CompoundButton) this.view7f0a00db).setOnCheckedChangeListener(null);
        this.view7f0a00db = null;
        ((CompoundButton) this.view7f0a00d6).setOnCheckedChangeListener(null);
        this.view7f0a00d6 = null;
        ((CompoundButton) this.view7f0a00fe).setOnCheckedChangeListener(null);
        this.view7f0a00fe = null;
        ((CompoundButton) this.view7f0a00cb).setOnCheckedChangeListener(null);
        this.view7f0a00cb = null;
        ((CompoundButton) this.view7f0a00fa).setOnCheckedChangeListener(null);
        this.view7f0a00fa = null;
        ((CompoundButton) this.view7f0a00e4).setOnCheckedChangeListener(null);
        this.view7f0a00e4 = null;
        ((CompoundButton) this.view7f0a00f6).setOnCheckedChangeListener(null);
        this.view7f0a00f6 = null;
        ((CompoundButton) this.view7f0a00f2).setOnCheckedChangeListener(null);
        this.view7f0a00f2 = null;
        ((CompoundButton) this.view7f0a00e0).setOnCheckedChangeListener(null);
        this.view7f0a00e0 = null;
        ((CompoundButton) this.view7f0a00dc).setOnCheckedChangeListener(null);
        this.view7f0a00dc = null;
        ((CompoundButton) this.view7f0a00d7).setOnCheckedChangeListener(null);
        this.view7f0a00d7 = null;
        ((CompoundButton) this.view7f0a00ff).setOnCheckedChangeListener(null);
        this.view7f0a00ff = null;
        ((CompoundButton) this.view7f0a00cc).setOnCheckedChangeListener(null);
        this.view7f0a00cc = null;
        ((CompoundButton) this.view7f0a00fb).setOnCheckedChangeListener(null);
        this.view7f0a00fb = null;
        ((CompoundButton) this.view7f0a00e5).setOnCheckedChangeListener(null);
        this.view7f0a00e5 = null;
        ((CompoundButton) this.view7f0a00f7).setOnCheckedChangeListener(null);
        this.view7f0a00f7 = null;
        ((CompoundButton) this.view7f0a00f3).setOnCheckedChangeListener(null);
        this.view7f0a00f3 = null;
        ((CompoundButton) this.view7f0a00e1).setOnCheckedChangeListener(null);
        this.view7f0a00e1 = null;
        ((CompoundButton) this.view7f0a00dd).setOnCheckedChangeListener(null);
        this.view7f0a00dd = null;
        ((CompoundButton) this.view7f0a00d8).setOnCheckedChangeListener(null);
        this.view7f0a00d8 = null;
        ((CompoundButton) this.view7f0a0100).setOnCheckedChangeListener(null);
        this.view7f0a0100 = null;
        ((CompoundButton) this.view7f0a00cd).setOnCheckedChangeListener(null);
        this.view7f0a00cd = null;
        ((CompoundButton) this.view7f0a00fc).setOnCheckedChangeListener(null);
        this.view7f0a00fc = null;
        ((CompoundButton) this.view7f0a00e6).setOnCheckedChangeListener(null);
        this.view7f0a00e6 = null;
        ((CompoundButton) this.view7f0a00f8).setOnCheckedChangeListener(null);
        this.view7f0a00f8 = null;
        ((CompoundButton) this.view7f0a00f4).setOnCheckedChangeListener(null);
        this.view7f0a00f4 = null;
        ((CompoundButton) this.view7f0a00e2).setOnCheckedChangeListener(null);
        this.view7f0a00e2 = null;
        ((CompoundButton) this.view7f0a00de).setOnCheckedChangeListener(null);
        this.view7f0a00de = null;
        ((CompoundButton) this.view7f0a00d9).setOnCheckedChangeListener(null);
        this.view7f0a00d9 = null;
        ((CompoundButton) this.view7f0a0101).setOnCheckedChangeListener(null);
        this.view7f0a0101 = null;
        ((CompoundButton) this.view7f0a00ce).setOnCheckedChangeListener(null);
        this.view7f0a00ce = null;
    }
}
