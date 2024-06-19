package com.kkkcut.e20j.ui.fragment.engraving;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.KeyMarkingFramelayout;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyMarkingFragment_ViewBinding implements Unbinder {
    private KeyMarkingFragment target;
    private View view7f0a0081;
    private View view7f0a0082;
    private View view7f0a01b0;
    private View view7f0a01b1;
    private View view7f0a01f5;
    private View view7f0a01f7;
    private View view7f0a01f9;
    private View view7f0a0208;
    private View view7f0a0219;
    private View view7f0a0235;
    private View view7f0a0338;
    private View view7f0a0344;
    private View view7f0a0347;
    private View view7f0a0441;
    private View view7f0a0442;
    private View view7f0a046c;
    private View view7f0a0475;
    private View view7f0a04ad;
    private View view7f0a04b0;

    public KeyMarkingFragment_ViewBinding(final KeyMarkingFragment keyMarkingFragment, View view) {
        this.target = keyMarkingFragment;
        keyMarkingFragment.flContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.fl_container, "field 'flContainer'", FrameLayout.class);
        keyMarkingFragment.kmfl = (KeyMarkingFramelayout) Utils.findRequiredViewAsType(view, R.id.kmfl, "field 'kmfl'", KeyMarkingFramelayout.class);
        keyMarkingFragment.tvDepthValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_depth_value, "field 'tvDepthValue'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_low_speed, "field 'rbLowSpeed' and method 'onViewClicked'");
        keyMarkingFragment.rbLowSpeed = (RadioButton) Utils.castView(findRequiredView, R.id.rb_low_speed, "field 'rbLowSpeed'", RadioButton.class);
        this.view7f0a0344 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_middle_speed, "field 'rbMiddleSpeed' and method 'onViewClicked'");
        keyMarkingFragment.rbMiddleSpeed = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_middle_speed, "field 'rbMiddleSpeed'", RadioButton.class);
        this.view7f0a0347 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_high_speed, "field 'rbHighSpeed' and method 'onViewClicked'");
        keyMarkingFragment.rbHighSpeed = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_high_speed, "field 'rbHighSpeed'", RadioButton.class);
        this.view7f0a0338 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        keyMarkingFragment.cbAutoCenter = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_auto_center, "field 'cbAutoCenter'", CheckBox.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_up, "method 'onViewClicked'");
        this.view7f0a0235 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_down, "method 'onViewClicked'");
        this.view7f0a01f9 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_left, "method 'onViewClicked'");
        this.view7f0a0208 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_right, "method 'onViewClicked'");
        this.view7f0a0219 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_cut, "method 'onViewClicked'");
        this.view7f0a0081 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.font_size_add, "method 'onViewClicked'");
        this.view7f0a01b0 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.font_size_reduce, "method 'onViewClicked'");
        this.view7f0a01b1 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tv_select_template, "method 'onViewClicked'");
        this.view7f0a04b0 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.iv_depth_reduce, "method 'onViewClicked'");
        this.view7f0a01f7 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.iv_depth_add, "method 'onViewClicked'");
        this.view7f0a01f5 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.tv_add_text, "method 'onViewClicked'");
        this.view7f0a0442 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.tv_edit_text, "method 'onViewClicked'");
        this.view7f0a0475 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.tv_delete, "method 'onViewClicked'");
        this.view7f0a046c = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.tv_add_pic, "method 'onViewClicked'");
        this.view7f0a0441 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.tv_save, "method 'onViewClicked'");
        this.view7f0a04ad = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.bt_decode, "method 'onViewClicked'");
        this.view7f0a0082 = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.KeyMarkingFragment_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyMarkingFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyMarkingFragment keyMarkingFragment = this.target;
        if (keyMarkingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyMarkingFragment.flContainer = null;
        keyMarkingFragment.kmfl = null;
        keyMarkingFragment.tvDepthValue = null;
        keyMarkingFragment.rbLowSpeed = null;
        keyMarkingFragment.rbMiddleSpeed = null;
        keyMarkingFragment.rbHighSpeed = null;
        keyMarkingFragment.cbAutoCenter = null;
        this.view7f0a0344.setOnClickListener(null);
        this.view7f0a0344 = null;
        this.view7f0a0347.setOnClickListener(null);
        this.view7f0a0347 = null;
        this.view7f0a0338.setOnClickListener(null);
        this.view7f0a0338 = null;
        this.view7f0a0235.setOnClickListener(null);
        this.view7f0a0235 = null;
        this.view7f0a01f9.setOnClickListener(null);
        this.view7f0a01f9 = null;
        this.view7f0a0208.setOnClickListener(null);
        this.view7f0a0208 = null;
        this.view7f0a0219.setOnClickListener(null);
        this.view7f0a0219 = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
        this.view7f0a01b0.setOnClickListener(null);
        this.view7f0a01b0 = null;
        this.view7f0a01b1.setOnClickListener(null);
        this.view7f0a01b1 = null;
        this.view7f0a04b0.setOnClickListener(null);
        this.view7f0a04b0 = null;
        this.view7f0a01f7.setOnClickListener(null);
        this.view7f0a01f7 = null;
        this.view7f0a01f5.setOnClickListener(null);
        this.view7f0a01f5 = null;
        this.view7f0a0442.setOnClickListener(null);
        this.view7f0a0442 = null;
        this.view7f0a0475.setOnClickListener(null);
        this.view7f0a0475 = null;
        this.view7f0a046c.setOnClickListener(null);
        this.view7f0a046c = null;
        this.view7f0a0441.setOnClickListener(null);
        this.view7f0a0441 = null;
        this.view7f0a04ad.setOnClickListener(null);
        this.view7f0a04ad = null;
        this.view7f0a0082.setOnClickListener(null);
        this.view7f0a0082 = null;
    }
}
