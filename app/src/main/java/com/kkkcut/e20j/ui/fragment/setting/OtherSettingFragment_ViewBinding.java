package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class OtherSettingFragment_ViewBinding implements Unbinder {
    private OtherSettingFragment target;
    private View view7f0a007c;
    private View view7f0a007e;
    private View view7f0a0086;
    private View view7f0a008a;
    private View view7f0a0093;
    private View view7f0a00aa;
    private View view7f0a00b8;
    private View view7f0a00d0;
    private View view7f0a00d2;
    private View view7f0a00d5;
    private View view7f0a00eb;
    private View view7f0a00ef;
    private View view7f0a0324;
    private View view7f0a0325;
    private View view7f0a033f;
    private View view7f0a0348;
    private View view7f0a04a7;
    private View view7f0a04e6;
    private View view7f0a04ff;

    public OtherSettingFragment_ViewBinding(final OtherSettingFragment otherSettingFragment, View view) {
        this.target = otherSettingFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.cb_safety_door, "field 'cbSafetyDoor' and method 'onCheckedChanged'");
        otherSettingFragment.cbSafetyDoor = (CheckBox) Utils.castView(findRequiredView, R.id.cb_safety_door, "field 'cbSafetyDoor'", CheckBox.class);
        this.view7f0a00ef = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        otherSettingFragment.etX = (EditText) Utils.findRequiredViewAsType(view, R.id.et_x, "field 'etX'", EditText.class);
        otherSettingFragment.etY = (EditText) Utils.findRequiredViewAsType(view, R.id.et_y, "field 'etY'", EditText.class);
        otherSettingFragment.etZ = (EditText) Utils.findRequiredViewAsType(view, R.id.et_z, "field 'etZ'", EditText.class);
        otherSettingFragment.etMoveTime = (EditText) Utils.findRequiredViewAsType(view, R.id.et_move_time, "field 'etMoveTime'", EditText.class);
        otherSettingFragment.llMove = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_move, "field 'llMove'", LinearLayout.class);
        otherSettingFragment.btStartMove = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_move_order, "field 'btStartMove'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.cb_chinese_car, "field 'cbChineseCar' and method 'onCheckedChanged'");
        otherSettingFragment.cbChineseCar = (CheckBox) Utils.castView(findRequiredView2, R.id.cb_chinese_car, "field 'cbChineseCar'", CheckBox.class);
        this.view7f0a00d2 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_continue_move, "field 'btContinueMove' and method 'onViewClicked'");
        otherSettingFragment.btContinueMove = (Button) Utils.castView(findRequiredView3, R.id.bt_continue_move, "field 'btContinueMove'", Button.class);
        this.view7f0a007e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_dispaley_setting, "field 'btDispaleySetting' and method 'onViewClicked'");
        otherSettingFragment.btDispaleySetting = (TextView) Utils.castView(findRequiredView4, R.id.bt_dispaley_setting, "field 'btDispaleySetting'", TextView.class);
        this.view7f0a0086 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        otherSettingFragment.llDatabaseExport = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_database_export, "field 'llDatabaseExport'", LinearLayout.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_150, "field 'rb150' and method 'onCheckedChanged'");
        otherSettingFragment.rb150 = (RadioButton) Utils.castView(findRequiredView5, R.id.rb_150, "field 'rb150'", RadioButton.class);
        this.view7f0a0324 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.cb_decoder_position_detect, "field 'cbDecoderPositionDetect' and method 'onCheckedChanged'");
        otherSettingFragment.cbDecoderPositionDetect = (CheckBox) Utils.castView(findRequiredView6, R.id.cb_decoder_position_detect, "field 'cbDecoderPositionDetect'", CheckBox.class);
        this.view7f0a00d5 = findRequiredView6;
        ((CompoundButton) findRequiredView6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_ok, "field 'btOk' and method 'onViewClicked'");
        otherSettingFragment.btOk = (Button) Utils.castView(findRequiredView7, R.id.bt_ok, "field 'btOk'", Button.class);
        this.view7f0a00aa = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        otherSettingFragment.etRatio = (EditText) Utils.findRequiredViewAsType(view, R.id.et_ratio, "field 'etRatio'", EditText.class);
        otherSettingFragment.llRatioSetup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_ratio_setup, "field 'llRatioSetup'", LinearLayout.class);
        View findRequiredView8 = Utils.findRequiredView(view, R.id.cb_bar_code, "field 'cbBarCode' and method 'onCheckedChanged'");
        otherSettingFragment.cbBarCode = (CheckBox) Utils.castView(findRequiredView8, R.id.cb_bar_code, "field 'cbBarCode'", CheckBox.class);
        this.view7f0a00d0 = findRequiredView8;
        ((CompoundButton) findRequiredView8).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.rb_inch, "field 'rbInch' and method 'onCheckedChanged'");
        otherSettingFragment.rbInch = (RadioButton) Utils.castView(findRequiredView9, R.id.rb_inch, "field 'rbInch'", RadioButton.class);
        this.view7f0a033f = findRequiredView9;
        ((CompoundButton) findRequiredView9).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.cb_move, "method 'onCheckedChanged'");
        this.view7f0a00eb = findRequiredView10;
        ((CompoundButton) findRequiredView10).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.rb_200, "method 'onCheckedChanged'");
        this.view7f0a0325 = findRequiredView11;
        ((CompoundButton) findRequiredView11).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.11
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.rb_mm, "method 'onCheckedChanged'");
        this.view7f0a0348 = findRequiredView12;
        ((CompoundButton) findRequiredView12).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                otherSettingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_start_move, "method 'onViewClicked'");
        this.view7f0a00b8 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.tv_upload_log, "method 'onViewClicked'");
        this.view7f0a04e6 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.tv_reset, "method 'onViewClicked'");
        this.view7f0a04a7 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.bt_import, "method 'onViewClicked'");
        this.view7f0a0093 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_export, "method 'onViewClicked'");
        this.view7f0a008a = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.bt_conductivity_test, "method 'onViewClicked'");
        this.view7f0a007c = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                otherSettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.view_hide, "method 'onLongClick'");
        this.view7f0a04ff = findRequiredView19;
        findRequiredView19.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.OtherSettingFragment_ViewBinding.19
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                return otherSettingFragment.onLongClick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        OtherSettingFragment otherSettingFragment = this.target;
        if (otherSettingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        otherSettingFragment.cbSafetyDoor = null;
        otherSettingFragment.etX = null;
        otherSettingFragment.etY = null;
        otherSettingFragment.etZ = null;
        otherSettingFragment.etMoveTime = null;
        otherSettingFragment.llMove = null;
        otherSettingFragment.btStartMove = null;
        otherSettingFragment.cbChineseCar = null;
        otherSettingFragment.btContinueMove = null;
        otherSettingFragment.btDispaleySetting = null;
        otherSettingFragment.llDatabaseExport = null;
        otherSettingFragment.rb150 = null;
        otherSettingFragment.cbDecoderPositionDetect = null;
        otherSettingFragment.btOk = null;
        otherSettingFragment.etRatio = null;
        otherSettingFragment.llRatioSetup = null;
        otherSettingFragment.cbBarCode = null;
        otherSettingFragment.rbInch = null;
        ((CompoundButton) this.view7f0a00ef).setOnCheckedChangeListener(null);
        this.view7f0a00ef = null;
        ((CompoundButton) this.view7f0a00d2).setOnCheckedChangeListener(null);
        this.view7f0a00d2 = null;
        this.view7f0a007e.setOnClickListener(null);
        this.view7f0a007e = null;
        this.view7f0a0086.setOnClickListener(null);
        this.view7f0a0086 = null;
        ((CompoundButton) this.view7f0a0324).setOnCheckedChangeListener(null);
        this.view7f0a0324 = null;
        ((CompoundButton) this.view7f0a00d5).setOnCheckedChangeListener(null);
        this.view7f0a00d5 = null;
        this.view7f0a00aa.setOnClickListener(null);
        this.view7f0a00aa = null;
        ((CompoundButton) this.view7f0a00d0).setOnCheckedChangeListener(null);
        this.view7f0a00d0 = null;
        ((CompoundButton) this.view7f0a033f).setOnCheckedChangeListener(null);
        this.view7f0a033f = null;
        ((CompoundButton) this.view7f0a00eb).setOnCheckedChangeListener(null);
        this.view7f0a00eb = null;
        ((CompoundButton) this.view7f0a0325).setOnCheckedChangeListener(null);
        this.view7f0a0325 = null;
        ((CompoundButton) this.view7f0a0348).setOnCheckedChangeListener(null);
        this.view7f0a0348 = null;
        this.view7f0a00b8.setOnClickListener(null);
        this.view7f0a00b8 = null;
        this.view7f0a04e6.setOnClickListener(null);
        this.view7f0a04e6 = null;
        this.view7f0a04a7.setOnClickListener(null);
        this.view7f0a04a7 = null;
        this.view7f0a0093.setOnClickListener(null);
        this.view7f0a0093 = null;
        this.view7f0a008a.setOnClickListener(null);
        this.view7f0a008a = null;
        this.view7f0a007c.setOnClickListener(null);
        this.view7f0a007c = null;
        this.view7f0a04ff.setOnLongClickListener(null);
        this.view7f0a04ff = null;
    }
}
