package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class ToothCodeInputFragment_ViewBinding implements Unbinder {
    private ToothCodeInputFragment target;
    private View view7f0a0075;
    private View view7f0a0091;
    private View view7f0a0094;
    private View view7f0a00b0;
    private View view7f0a00ea;
    private View view7f0a01f9;
    private View view7f0a0208;
    private View view7f0a0219;
    private View view7f0a0235;
    private View view7f0a0267;
    private View view7f0a03f7;

    public ToothCodeInputFragment_ViewBinding(final ToothCodeInputFragment toothCodeInputFragment, View view) {
        this.target = toothCodeInputFragment;
        toothCodeInputFragment.flKey = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_key, "field 'flKey'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.switch_decimal, "field 'cbDecimal' and method 'onCheckedChange'");
        toothCodeInputFragment.cbDecimal = (Switch) Utils.castView(findRequiredView, R.id.switch_decimal, "field 'cbDecimal'", Switch.class);
        this.view7f0a03f7 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                toothCodeInputFragment.onCheckedChange(compoundButton, z);
            }
        });
        toothCodeInputFragment.rvKeyboard = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_keyboard, "field 'rvKeyboard'", RecyclerView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_rounding, "field 'btRounding' and method 'onViewClicked'");
        toothCodeInputFragment.btRounding = (Button) Utils.castView(findRequiredView2, R.id.bt_rounding, "field 'btRounding'", Button.class);
        this.view7f0a00b0 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        toothCodeInputFragment.llInputRule = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_input_rule, "field 'llInputRule'", LinearLayout.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_ignition_door, "field 'btIgnitionDoor' and method 'onViewClicked'");
        toothCodeInputFragment.btIgnitionDoor = (Button) Utils.castView(findRequiredView3, R.id.bt_ignition_door, "field 'btIgnitionDoor'", Button.class);
        this.view7f0a0091 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.cb_invert, "method 'onCheckedChange'");
        this.view7f0a00ea = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                toothCodeInputFragment.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_left, "method 'onViewClicked'");
        this.view7f0a0208 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_right, "method 'onViewClicked'");
        this.view7f0a0219 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_down, "method 'onViewClicked'");
        this.view7f0a01f9 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_up, "method 'onViewClicked'");
        this.view7f0a0235 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.ll_confirm, "method 'onViewClicked'");
        this.view7f0a0267 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_input_rule, "method 'onViewClicked'");
        this.view7f0a0094 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.ToothCodeInputFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                toothCodeInputFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ToothCodeInputFragment toothCodeInputFragment = this.target;
        if (toothCodeInputFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        toothCodeInputFragment.flKey = null;
        toothCodeInputFragment.cbDecimal = null;
        toothCodeInputFragment.rvKeyboard = null;
        toothCodeInputFragment.btRounding = null;
        toothCodeInputFragment.llInputRule = null;
        toothCodeInputFragment.btIgnitionDoor = null;
        ((CompoundButton) this.view7f0a03f7).setOnCheckedChangeListener(null);
        this.view7f0a03f7 = null;
        this.view7f0a00b0.setOnClickListener(null);
        this.view7f0a00b0 = null;
        this.view7f0a0091.setOnClickListener(null);
        this.view7f0a0091 = null;
        ((CompoundButton) this.view7f0a00ea).setOnCheckedChangeListener(null);
        this.view7f0a00ea = null;
        this.view7f0a0208.setOnClickListener(null);
        this.view7f0a0208 = null;
        this.view7f0a0219.setOnClickListener(null);
        this.view7f0a0219 = null;
        this.view7f0a01f9.setOnClickListener(null);
        this.view7f0a01f9 = null;
        this.view7f0a0235.setOnClickListener(null);
        this.view7f0a0235 = null;
        this.view7f0a0267.setOnClickListener(null);
        this.view7f0a0267 = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a0094.setOnClickListener(null);
        this.view7f0a0094 = null;
    }
}
