package com.kkkcut.e20j.ui.fragment.blankcut;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutDialog_ViewBinding implements Unbinder {
    private BlankCutDialog target;
    private View view7f0a006d;
    private View view7f0a006e;
    private View view7f0a006f;
    private View view7f0a0075;
    private View view7f0a01ef;
    private View view7f0a021f;
    private View view7f0a0220;
    private View view7f0a0229;
    private View view7f0a022a;
    private View view7f0a0332;
    private View view7f0a0349;
    private View view7f0a0458;

    public BlankCutDialog_ViewBinding(final BlankCutDialog blankCutDialog, View view) {
        this.target = blankCutDialog;
        blankCutDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_cut, "field 'btCut' and method 'onViewClicked'");
        blankCutDialog.btCut = (TextView) Utils.castView(findRequiredView, R.id.tv_cut, "field 'btCut'", TextView.class);
        this.view7f0a0458 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        blankCutDialog.ivCutter = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_cutter, "field 'ivCutter'", ImageView.class);
        blankCutDialog.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        blankCutDialog.tvSpeedValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_speed_value, "field 'tvSpeedValue'", TextView.class);
        blankCutDialog.llCutSpeed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_speed, "field 'llCutSpeed'", LinearLayout.class);
        blankCutDialog.tvCutSpeed = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cut_speed, "field 'tvCutSpeed'", TextView.class);
        blankCutDialog.llCutterSize = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cutter_size, "field 'llCutterSize'", LinearLayout.class);
        blankCutDialog.tvCutterSizeRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size_remind, "field 'tvCutterSizeRemind'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_1_5mm, "field 'bt15mm' and method 'onViewClicked'");
        blankCutDialog.bt15mm = (Button) Utils.castView(findRequiredView2, R.id.bt_1_5mm, "field 'bt15mm'", Button.class);
        this.view7f0a006d = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_2_0mm, "field 'bt20mm' and method 'onViewClicked'");
        blankCutDialog.bt20mm = (Button) Utils.castView(findRequiredView3, R.id.bt_2_0mm, "field 'bt20mm'", Button.class);
        this.view7f0a006e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_2_5mm, "field 'bt25mm' and method 'onViewClicked'");
        blankCutDialog.bt25mm = (Button) Utils.castView(findRequiredView4, R.id.bt_2_5mm, "field 'bt25mm'", Button.class);
        this.view7f0a006f = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        blankCutDialog.rgCutTimes = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_cut_times, "field 'rgCutTimes'", RadioGroup.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_double_cut, "method 'onCheckedChange'");
        this.view7f0a0332 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                blankCutDialog.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.rb_once_cut, "method 'onCheckedChange'");
        this.view7f0a0349 = findRequiredView6;
        ((CompoundButton) findRequiredView6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                blankCutDialog.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_size_add, "method 'onViewClicked'");
        this.view7f0a021f = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_size_reduce, "method 'onViewClicked'");
        this.view7f0a0220 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.iv_speed_add, "method 'onViewClicked'");
        this.view7f0a0229 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.iv_speed_reduce, "method 'onViewClicked'");
        this.view7f0a022a = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_cancle, "method 'onViewClicked'");
        this.view7f0a0075 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.iv_close, "method 'onViewClicked'");
        this.view7f0a01ef = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutDialog_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BlankCutDialog blankCutDialog = this.target;
        if (blankCutDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutDialog.ivClamp = null;
        blankCutDialog.btCut = null;
        blankCutDialog.ivCutter = null;
        blankCutDialog.tvCutterSize = null;
        blankCutDialog.tvSpeedValue = null;
        blankCutDialog.llCutSpeed = null;
        blankCutDialog.tvCutSpeed = null;
        blankCutDialog.llCutterSize = null;
        blankCutDialog.tvCutterSizeRemind = null;
        blankCutDialog.bt15mm = null;
        blankCutDialog.bt20mm = null;
        blankCutDialog.bt25mm = null;
        blankCutDialog.rgCutTimes = null;
        this.view7f0a0458.setOnClickListener(null);
        this.view7f0a0458 = null;
        this.view7f0a006d.setOnClickListener(null);
        this.view7f0a006d = null;
        this.view7f0a006e.setOnClickListener(null);
        this.view7f0a006e = null;
        this.view7f0a006f.setOnClickListener(null);
        this.view7f0a006f = null;
        ((CompoundButton) this.view7f0a0332).setOnCheckedChangeListener(null);
        this.view7f0a0332 = null;
        ((CompoundButton) this.view7f0a0349).setOnCheckedChangeListener(null);
        this.view7f0a0349 = null;
        this.view7f0a021f.setOnClickListener(null);
        this.view7f0a021f = null;
        this.view7f0a0220.setOnClickListener(null);
        this.view7f0a0220 = null;
        this.view7f0a0229.setOnClickListener(null);
        this.view7f0a0229 = null;
        this.view7f0a022a.setOnClickListener(null);
        this.view7f0a022a = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a01ef.setOnClickListener(null);
        this.view7f0a01ef = null;
    }
}
