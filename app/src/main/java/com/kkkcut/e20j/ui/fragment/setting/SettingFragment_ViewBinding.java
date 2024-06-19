package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SettingFragment_ViewBinding implements Unbinder {
    private SettingFragment target;
    private View view7f0a0214;
    private View view7f0a032e;
    private View view7f0a034a;
    private View view7f0a035e;
    private View view7f0a035f;

    public SettingFragment_ViewBinding(final SettingFragment settingFragment, View view) {
        this.target = settingFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_cut_setting, "field 'rbCutSetting' and method 'onCheckedChanged'");
        settingFragment.rbCutSetting = (RadioButton) Utils.castView(findRequiredView, R.id.rb_cut_setting, "field 'rbCutSetting'", RadioButton.class);
        this.view7f0a032e = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                settingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_space_Width, "field 'rb_space_Width' and method 'onCheckedChanged'");
        settingFragment.rb_space_Width = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_space_Width, "field 'rb_space_Width'", RadioButton.class);
        this.view7f0a035e = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                settingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        settingFragment.tvCutCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_count, "field 'tvCutCount'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_speed, "method 'onCheckedChanged'");
        this.view7f0a035f = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                settingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_other, "method 'onCheckedChanged'");
        this.view7f0a034a = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                settingFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_quit, "method 'onClick'");
        this.view7f0a0214 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                settingFragment.onClick();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SettingFragment settingFragment = this.target;
        if (settingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        settingFragment.rbCutSetting = null;
        settingFragment.rb_space_Width = null;
        settingFragment.tvCutCount = null;
        ((CompoundButton) this.view7f0a032e).setOnCheckedChangeListener(null);
        this.view7f0a032e = null;
        ((CompoundButton) this.view7f0a035e).setOnCheckedChangeListener(null);
        this.view7f0a035e = null;
        ((CompoundButton) this.view7f0a035f).setOnCheckedChangeListener(null);
        this.view7f0a035f = null;
        ((CompoundButton) this.view7f0a034a).setOnCheckedChangeListener(null);
        this.view7f0a034a = null;
        this.view7f0a0214.setOnClickListener(null);
        this.view7f0a0214 = null;
    }
}
