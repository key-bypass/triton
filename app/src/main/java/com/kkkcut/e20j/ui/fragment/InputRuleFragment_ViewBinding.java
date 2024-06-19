package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class InputRuleFragment_ViewBinding implements Unbinder {
    private InputRuleFragment target;
    private View view7f0a00bb;
    private View view7f0a00bf;
    private View view7f0a0434;
    private View view7f0a0435;
    private View view7f0a0436;
    private View view7f0a0437;
    private View view7f0a0438;
    private View view7f0a043d;

    public InputRuleFragment_ViewBinding(final InputRuleFragment inputRuleFragment, View view) {
        this.target = inputRuleFragment;
        inputRuleFragment.llA = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_A, "field 'llA'", LinearLayout.class);
        inputRuleFragment.llB = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_B, "field 'llB'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv1, "method 'onViewClicked'");
        this.view7f0a0434 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv2, "method 'onViewClicked'");
        this.view7f0a0435 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv3, "method 'onViewClicked'");
        this.view7f0a0436 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv4, "method 'onViewClicked'");
        this.view7f0a0437 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv5, "method 'onViewClicked'");
        this.view7f0a0438 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tvX, "method 'onViewClicked'");
        this.view7f0a043d = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.btn_ok, "method 'onViewClicked'");
        this.view7f0a00bf = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.btn_cancel, "method 'onViewClicked'");
        this.view7f0a00bb = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                inputRuleFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        InputRuleFragment inputRuleFragment = this.target;
        if (inputRuleFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inputRuleFragment.llA = null;
        inputRuleFragment.llB = null;
        this.view7f0a0434.setOnClickListener(null);
        this.view7f0a0434 = null;
        this.view7f0a0435.setOnClickListener(null);
        this.view7f0a0435 = null;
        this.view7f0a0436.setOnClickListener(null);
        this.view7f0a0436 = null;
        this.view7f0a0437.setOnClickListener(null);
        this.view7f0a0437 = null;
        this.view7f0a0438.setOnClickListener(null);
        this.view7f0a0438 = null;
        this.view7f0a043d.setOnClickListener(null);
        this.view7f0a043d = null;
        this.view7f0a00bf.setOnClickListener(null);
        this.view7f0a00bf = null;
        this.view7f0a00bb.setOnClickListener(null);
        this.view7f0a00bb = null;
    }
}
