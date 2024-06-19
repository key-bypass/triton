package com.kkkcut.e20j.ui.fragment.duplicatekey;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.MyRadioGroup;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DuplicateKeyNewFragment_ViewBinding implements Unbinder {
    private DuplicateKeyNewFragment target;
    private View view7f0a0085;
    private View view7f0a008e;
    private View view7f0a0263;
    private View view7f0a0264;
    private View view7f0a0265;
    private View view7f0a0333;
    private View view7f0a0334;
    private View view7f0a0336;
    private View view7f0a0337;
    private View view7f0a0340;
    private View view7f0a0354;
    private View view7f0a0355;
    private View view7f0a0358;
    private View view7f0a0359;
    private View view7f0a035a;
    private View view7f0a035b;
    private View view7f0a035c;
    private View view7f0a0363;
    private View view7f0a0365;
    private View view7f0a0366;
    private View view7f0a0368;

    public DuplicateKeyNewFragment_ViewBinding(final DuplicateKeyNewFragment duplicateKeyNewFragment, View view) {
        this.target = duplicateKeyNewFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_tip_1, "field 'tvTip1' and method 'onViewClicked'");
        duplicateKeyNewFragment.tvTip1 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_tip_1, "field 'tvTip1'", RadioButton.class);
        this.view7f0a0365 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_shoulder_1, "field 'tvShoulder1' and method 'onViewClicked'");
        duplicateKeyNewFragment.tvShoulder1 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_shoulder_1, "field 'tvShoulder1'", RadioButton.class);
        this.view7f0a0354 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_shoulder_2, "field 'tvShoulder2' and method 'onViewClicked'");
        duplicateKeyNewFragment.tvShoulder2 = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_shoulder_2, "field 'tvShoulder2'", RadioButton.class);
        this.view7f0a0355 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_tip_2, "field 'tvTip2' and method 'onViewClicked'");
        duplicateKeyNewFragment.tvTip2 = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_tip_2, "field 'tvTip2'", RadioButton.class);
        this.view7f0a0366 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        duplicateKeyNewFragment.tvTitleClamp = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_clamp, "field 'tvTitleClamp'", TextView.class);
        duplicateKeyNewFragment.ivClamp0 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp_0, "field 'ivClamp0'", ImageView.class);
        duplicateKeyNewFragment.ivClamp1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp_1, "field 'ivClamp1'", ImageView.class);
        duplicateKeyNewFragment.ivClamp2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp_2, "field 'ivClamp2'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.ll_clamp_0, "field 'llClamp0' and method 'onViewClicked'");
        duplicateKeyNewFragment.llClamp0 = (LinearLayout) Utils.castView(findRequiredView5, R.id.ll_clamp_0, "field 'llClamp0'", LinearLayout.class);
        this.view7f0a0263 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.ll_clamp_1, "field 'llClamp1' and method 'onViewClicked'");
        duplicateKeyNewFragment.llClamp1 = (LinearLayout) Utils.castView(findRequiredView6, R.id.ll_clamp_1, "field 'llClamp1'", LinearLayout.class);
        this.view7f0a0264 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.ll_clamp_2, "field 'llClamp2' and method 'onViewClicked'");
        duplicateKeyNewFragment.llClamp2 = (LinearLayout) Utils.castView(findRequiredView7, R.id.ll_clamp_2, "field 'llClamp2'", LinearLayout.class);
        this.view7f0a0265 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.rb_tubular_key, "field 'rbTubularKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbTubularKey = (RadioButton) Utils.castView(findRequiredView8, R.id.rb_tubular_key, "field 'rbTubularKey'", RadioButton.class);
        this.view7f0a0368 = findRequiredView8;
        ((CompoundButton) findRequiredView8).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.rb_double_key, "field 'rbDoubleKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbDoubleKey = (RadioButton) Utils.castView(findRequiredView9, R.id.rb_double_key, "field 'rbDoubleKey'", RadioButton.class);
        this.view7f0a0336 = findRequiredView9;
        ((CompoundButton) findRequiredView9).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.rb_single_key, "field 'rbSingleKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbSingleKey = (RadioButton) Utils.castView(findRequiredView10, R.id.rb_single_key, "field 'rbSingleKey'", RadioButton.class);
        this.view7f0a035b = findRequiredView10;
        ((CompoundButton) findRequiredView10).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_honda_key, "field 'btHondaKey' and method 'onViewClicked'");
        duplicateKeyNewFragment.btHondaKey = (Button) Utils.castView(findRequiredView11, R.id.bt_honda_key, "field 'btHondaKey'", Button.class);
        this.view7f0a008e = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.bt_dimple, "field 'btDimple' and method 'onViewClicked'");
        duplicateKeyNewFragment.btDimple = (Button) Utils.castView(findRequiredView12, R.id.bt_dimple, "field 'btDimple'", Button.class);
        this.view7f0a0085 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateKeyNewFragment.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.rb_single_inside_left_key, "field 'rbSingleInsideLeftKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbSingleInsideLeftKey = (RadioButton) Utils.castView(findRequiredView13, R.id.rb_single_inside_left_key, "field 'rbSingleInsideLeftKey'", RadioButton.class);
        this.view7f0a0359 = findRequiredView13;
        ((CompoundButton) findRequiredView13).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.13
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.rb_single_inside_right_key, "field 'rbSingleInsideRightKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbSingleInsideRightKey = (RadioButton) Utils.castView(findRequiredView14, R.id.rb_single_inside_right_key, "field 'rbSingleInsideRightKey'", RadioButton.class);
        this.view7f0a035a = findRequiredView14;
        ((CompoundButton) findRequiredView14).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.rb_side_hole, "field 'rbSideHole' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbSideHole = (RadioButton) Utils.castView(findRequiredView15, R.id.rb_side_hole, "field 'rbSideHole'", RadioButton.class);
        this.view7f0a0358 = findRequiredView15;
        ((CompoundButton) findRequiredView15).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.15
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.rb_double_groove_key, "field 'rbDoubleGrooveKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbDoubleGrooveKey = (RadioButton) Utils.castView(findRequiredView16, R.id.rb_double_groove_key, "field 'rbDoubleGrooveKey'", RadioButton.class);
        this.view7f0a0333 = findRequiredView16;
        ((CompoundButton) findRequiredView16).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.16
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.rb_three_groove_key, "field 'rbThreeGrooveKey' and method 'onCheckedChanged'");
        duplicateKeyNewFragment.rbThreeGrooveKey = (RadioButton) Utils.castView(findRequiredView17, R.id.rb_three_groove_key, "field 'rbThreeGrooveKey'", RadioButton.class);
        this.view7f0a0363 = findRequiredView17;
        ((CompoundButton) findRequiredView17).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        duplicateKeyNewFragment.myRadioGroup = (MyRadioGroup) Utils.findRequiredViewAsType(view, R.id.myRg, "field 'myRadioGroup'", MyRadioGroup.class);
        View findRequiredView18 = Utils.findRequiredView(view, R.id.rb_single_outside_key, "method 'onCheckedChanged'");
        this.view7f0a035c = findRequiredView18;
        ((CompoundButton) findRequiredView18).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.18
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.rb_double_outside_key, "method 'onCheckedChanged'");
        this.view7f0a0337 = findRequiredView19;
        ((CompoundButton) findRequiredView19).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.19
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.rb_inside_key, "method 'onCheckedChanged'");
        this.view7f0a0340 = findRequiredView20;
        ((CompoundButton) findRequiredView20).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.20
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.rb_double_groove_key_wudihu, "method 'onCheckedChanged'");
        this.view7f0a0334 = findRequiredView21;
        ((CompoundButton) findRequiredView21).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment_ViewBinding.21
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateKeyNewFragment.onCheckedChanged(compoundButton, z);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DuplicateKeyNewFragment duplicateKeyNewFragment = this.target;
        if (duplicateKeyNewFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        duplicateKeyNewFragment.tvTip1 = null;
        duplicateKeyNewFragment.tvShoulder1 = null;
        duplicateKeyNewFragment.tvShoulder2 = null;
        duplicateKeyNewFragment.tvTip2 = null;
        duplicateKeyNewFragment.tvTitleClamp = null;
        duplicateKeyNewFragment.ivClamp0 = null;
        duplicateKeyNewFragment.ivClamp1 = null;
        duplicateKeyNewFragment.ivClamp2 = null;
        duplicateKeyNewFragment.llClamp0 = null;
        duplicateKeyNewFragment.llClamp1 = null;
        duplicateKeyNewFragment.llClamp2 = null;
        duplicateKeyNewFragment.rbTubularKey = null;
        duplicateKeyNewFragment.rbDoubleKey = null;
        duplicateKeyNewFragment.rbSingleKey = null;
        duplicateKeyNewFragment.btHondaKey = null;
        duplicateKeyNewFragment.btDimple = null;
        duplicateKeyNewFragment.rbSingleInsideLeftKey = null;
        duplicateKeyNewFragment.rbSingleInsideRightKey = null;
        duplicateKeyNewFragment.rbSideHole = null;
        duplicateKeyNewFragment.rbDoubleGrooveKey = null;
        duplicateKeyNewFragment.rbThreeGrooveKey = null;
        duplicateKeyNewFragment.myRadioGroup = null;
        this.view7f0a0365.setOnClickListener(null);
        this.view7f0a0365 = null;
        this.view7f0a0354.setOnClickListener(null);
        this.view7f0a0354 = null;
        this.view7f0a0355.setOnClickListener(null);
        this.view7f0a0355 = null;
        this.view7f0a0366.setOnClickListener(null);
        this.view7f0a0366 = null;
        this.view7f0a0263.setOnClickListener(null);
        this.view7f0a0263 = null;
        this.view7f0a0264.setOnClickListener(null);
        this.view7f0a0264 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        ((CompoundButton) this.view7f0a0368).setOnCheckedChangeListener(null);
        this.view7f0a0368 = null;
        ((CompoundButton) this.view7f0a0336).setOnCheckedChangeListener(null);
        this.view7f0a0336 = null;
        ((CompoundButton) this.view7f0a035b).setOnCheckedChangeListener(null);
        this.view7f0a035b = null;
        this.view7f0a008e.setOnClickListener(null);
        this.view7f0a008e = null;
        this.view7f0a0085.setOnClickListener(null);
        this.view7f0a0085 = null;
        ((CompoundButton) this.view7f0a0359).setOnCheckedChangeListener(null);
        this.view7f0a0359 = null;
        ((CompoundButton) this.view7f0a035a).setOnCheckedChangeListener(null);
        this.view7f0a035a = null;
        ((CompoundButton) this.view7f0a0358).setOnCheckedChangeListener(null);
        this.view7f0a0358 = null;
        ((CompoundButton) this.view7f0a0333).setOnCheckedChangeListener(null);
        this.view7f0a0333 = null;
        ((CompoundButton) this.view7f0a0363).setOnCheckedChangeListener(null);
        this.view7f0a0363 = null;
        ((CompoundButton) this.view7f0a035c).setOnCheckedChangeListener(null);
        this.view7f0a035c = null;
        ((CompoundButton) this.view7f0a0337).setOnCheckedChangeListener(null);
        this.view7f0a0337 = null;
        ((CompoundButton) this.view7f0a0340).setOnCheckedChangeListener(null);
        this.view7f0a0340 = null;
        ((CompoundButton) this.view7f0a0334).setOnCheckedChangeListener(null);
        this.view7f0a0334 = null;
    }
}
