package com.kkkcut.e20j.ui;

import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SizeAdjustFragment_ViewBinding implements Unbinder {
    private SizeAdjustFragment target;
    private View view7f0a00b1;
    private View view7f0a01f9;
    private View view7f0a0208;
    private View view7f0a0219;
    private View view7f0a0224;
    private View view7f0a0225;
    private View view7f0a0226;
    private View view7f0a0227;
    private View view7f0a0235;

    public SizeAdjustFragment_ViewBinding(final SizeAdjustFragment sizeAdjustFragment, View view) {
        this.target = sizeAdjustFragment;
        sizeAdjustFragment.llSizeContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.size_container, "field 'llSizeContainer'", LinearLayout.class);
        sizeAdjustFragment.tvSpaceValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_space_value, "field 'tvSpaceValue'", TextView.class);
        sizeAdjustFragment.tvSpaceWidthValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_spaceWidth_value, "field 'tvSpaceWidthValue'", TextView.class);
        sizeAdjustFragment.cbAllSpace = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_all_space, "field 'cbAllSpace'", CheckBox.class);
        sizeAdjustFragment.cbAllSpaceWidth = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_all_spaceWidth, "field 'cbAllSpaceWidth'", CheckBox.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_space_reduce, "method 'onClick'");
        this.view7f0a0227 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.iv_space_add, "method 'onClick'");
        this.view7f0a0226 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.iv_spaceWidth_add, "method 'onClick'");
        this.view7f0a0224 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_spaceWidth_reduce, "method 'onClick'");
        this.view7f0a0225 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_left, "method 'onClick'");
        this.view7f0a0208 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_right, "method 'onClick'");
        this.view7f0a0219 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_down, "method 'onClick'");
        this.view7f0a01f9 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_up, "method 'onClick'");
        this.view7f0a0235 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_save, "method 'onClick'");
        this.view7f0a00b1 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.SizeAdjustFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sizeAdjustFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SizeAdjustFragment sizeAdjustFragment = this.target;
        if (sizeAdjustFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sizeAdjustFragment.llSizeContainer = null;
        sizeAdjustFragment.tvSpaceValue = null;
        sizeAdjustFragment.tvSpaceWidthValue = null;
        sizeAdjustFragment.cbAllSpace = null;
        sizeAdjustFragment.cbAllSpaceWidth = null;
        this.view7f0a0227.setOnClickListener(null);
        this.view7f0a0227 = null;
        this.view7f0a0226.setOnClickListener(null);
        this.view7f0a0226 = null;
        this.view7f0a0224.setOnClickListener(null);
        this.view7f0a0224 = null;
        this.view7f0a0225.setOnClickListener(null);
        this.view7f0a0225 = null;
        this.view7f0a0208.setOnClickListener(null);
        this.view7f0a0208 = null;
        this.view7f0a0219.setOnClickListener(null);
        this.view7f0a0219 = null;
        this.view7f0a01f9.setOnClickListener(null);
        this.view7f0a01f9 = null;
        this.view7f0a0235.setOnClickListener(null);
        this.view7f0a0235 = null;
        this.view7f0a00b1.setOnClickListener(null);
        this.view7f0a00b1 = null;
    }
}
