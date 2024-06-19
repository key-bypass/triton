package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DuplicateDimpleAlignFragment_ViewBinding implements Unbinder {
    private DuplicateDimpleAlignFragment target;
    private View view7f0a00aa;
    private View view7f0a00f0;
    private View view7f0a0102;
    private View view7f0a0353;
    private View view7f0a0364;
    private View view7f0a049f;
    private View view7f0a04a0;
    private View view7f0a04a9;
    private View view7f0a04ab;

    public DuplicateDimpleAlignFragment_ViewBinding(final DuplicateDimpleAlignFragment duplicateDimpleAlignFragment, View view) {
        this.target = duplicateDimpleAlignFragment;
        duplicateDimpleAlignFragment.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        duplicateDimpleAlignFragment.tvNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_number, "field 'tvNumber'", TextView.class);
        duplicateDimpleAlignFragment.tvRowNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_row_number, "field 'tvRowNumber'", TextView.class);
        duplicateDimpleAlignFragment.rvData = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_data, "field 'rvData'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_shoulder, "field 'rbShoulder' and method 'onCheckedChanged'");
        duplicateDimpleAlignFragment.rbShoulder = (RadioButton) Utils.castView(findRequiredView, R.id.rb_shoulder, "field 'rbShoulder'", RadioButton.class);
        this.view7f0a0353 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleAlignFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_tip, "field 'rbTip' and method 'onCheckedChanged'");
        duplicateDimpleAlignFragment.rbTip = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_tip, "field 'rbTip'", RadioButton.class);
        this.view7f0a0364 = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleAlignFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.cb_side, "field 'cbSide' and method 'onCheckedChanged'");
        duplicateDimpleAlignFragment.cbSide = (CheckBox) Utils.castView(findRequiredView3, R.id.cb_side, "field 'cbSide'", CheckBox.class);
        this.view7f0a00f0 = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleAlignFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.cb_zimuzhu, "field 'cbZiMuZhu' and method 'onCheckedChanged'");
        duplicateDimpleAlignFragment.cbZiMuZhu = (CheckBox) Utils.castView(findRequiredView4, R.id.cb_zimuzhu, "field 'cbZiMuZhu'", CheckBox.class);
        this.view7f0a0102 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleAlignFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_number_reduce, "method 'onclick'");
        this.view7f0a04a0 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleAlignFragment.onclick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_number_add, "method 'onclick'");
        this.view7f0a049f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleAlignFragment.onclick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.tv_row_reduce, "method 'onclick'");
        this.view7f0a04ab = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleAlignFragment.onclick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.tv_row_add, "method 'onclick'");
        this.view7f0a04a9 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleAlignFragment.onclick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_ok, "method 'onclick'");
        this.view7f0a00aa = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleAlignFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleAlignFragment.onclick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DuplicateDimpleAlignFragment duplicateDimpleAlignFragment = this.target;
        if (duplicateDimpleAlignFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        duplicateDimpleAlignFragment.ivClamp = null;
        duplicateDimpleAlignFragment.tvNumber = null;
        duplicateDimpleAlignFragment.tvRowNumber = null;
        duplicateDimpleAlignFragment.rvData = null;
        duplicateDimpleAlignFragment.rbShoulder = null;
        duplicateDimpleAlignFragment.rbTip = null;
        duplicateDimpleAlignFragment.cbSide = null;
        duplicateDimpleAlignFragment.cbZiMuZhu = null;
        ((CompoundButton) this.view7f0a0353).setOnCheckedChangeListener(null);
        this.view7f0a0353 = null;
        ((CompoundButton) this.view7f0a0364).setOnCheckedChangeListener(null);
        this.view7f0a0364 = null;
        ((CompoundButton) this.view7f0a00f0).setOnCheckedChangeListener(null);
        this.view7f0a00f0 = null;
        ((CompoundButton) this.view7f0a0102).setOnCheckedChangeListener(null);
        this.view7f0a0102 = null;
        this.view7f0a04a0.setOnClickListener(null);
        this.view7f0a04a0 = null;
        this.view7f0a049f.setOnClickListener(null);
        this.view7f0a049f = null;
        this.view7f0a04ab.setOnClickListener(null);
        this.view7f0a04ab = null;
        this.view7f0a04a9.setOnClickListener(null);
        this.view7f0a04a9 = null;
        this.view7f0a00aa.setOnClickListener(null);
        this.view7f0a00aa = null;
    }
}
