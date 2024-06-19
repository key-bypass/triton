package com.kkkcut.e20j.ui.fragment.hondakey;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class HondaSideCutFragment_ViewBinding implements Unbinder {
    private HondaSideCutFragment target;
    private View view7f0a021f;
    private View view7f0a0220;
    private View view7f0a0339;
    private View view7f0a033a;
    private View view7f0a033b;
    private View view7f0a033c;

    public HondaSideCutFragment_ViewBinding(final HondaSideCutFragment hondaSideCutFragment, View view) {
        this.target = hondaSideCutFragment;
        hondaSideCutFragment.btCut = (TextView) Utils.findRequiredViewAsType(view, R.id.bt_cut, "field 'btCut'", TextView.class);
        hondaSideCutFragment.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_honda_2020, "field 'rbHonda2020' and method 'onCheckChanged'");
        hondaSideCutFragment.rbHonda2020 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_honda_2020, "field 'rbHonda2020'", RadioButton.class);
        this.view7f0a0339 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                hondaSideCutFragment.onCheckChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_honda_2021, "field 'rbHonda2021' and method 'onCheckChanged'");
        hondaSideCutFragment.rbHonda2021 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_honda_2021, "field 'rbHonda2021'", RadioButton.class);
        this.view7f0a033a = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                hondaSideCutFragment.onCheckChanged(compoundButton, z);
            }
        });
        hondaSideCutFragment.rgYear = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_year, "field 'rgYear'", RadioGroup.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_honda_a, "method 'onCheckChanged'");
        this.view7f0a033b = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                hondaSideCutFragment.onCheckChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_honda_b, "method 'onCheckChanged'");
        this.view7f0a033c = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                hondaSideCutFragment.onCheckChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_size_reduce, "method 'onViewClicked'");
        this.view7f0a0220 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                hondaSideCutFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_size_add, "method 'onViewClicked'");
        this.view7f0a021f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.hondakey.HondaSideCutFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                hondaSideCutFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HondaSideCutFragment hondaSideCutFragment = this.target;
        if (hondaSideCutFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hondaSideCutFragment.btCut = null;
        hondaSideCutFragment.tvCutterSize = null;
        hondaSideCutFragment.rbHonda2020 = null;
        hondaSideCutFragment.rbHonda2021 = null;
        hondaSideCutFragment.rgYear = null;
        ((CompoundButton) this.view7f0a0339).setOnCheckedChangeListener(null);
        this.view7f0a0339 = null;
        ((CompoundButton) this.view7f0a033a).setOnCheckedChangeListener(null);
        this.view7f0a033a = null;
        ((CompoundButton) this.view7f0a033b).setOnCheckedChangeListener(null);
        this.view7f0a033b = null;
        ((CompoundButton) this.view7f0a033c).setOnCheckedChangeListener(null);
        this.view7f0a033c = null;
        this.view7f0a0220.setOnClickListener(null);
        this.view7f0a0220 = null;
        this.view7f0a021f.setOnClickListener(null);
        this.view7f0a021f = null;
    }
}
