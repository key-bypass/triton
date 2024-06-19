package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DuplicateDimpleDataFragment_ViewBinding implements Unbinder {
    private DuplicateDimpleDataFragment target;
    private View view7f0a0072;
    private View view7f0a0083;
    private View view7f0a008d;
    private View view7f0a009d;
    private View view7f0a009e;
    private View view7f0a009f;
    private View view7f0a00a0;
    private View view7f0a00a1;
    private View view7f0a00a2;
    private View view7f0a00a3;
    private View view7f0a00a4;
    private View view7f0a00a5;
    private View view7f0a00a6;
    private View view7f0a00a7;
    private View view7f0a00a8;
    private View view7f0a0221;
    private View view7f0a0222;
    private View view7f0a0226;
    private View view7f0a0227;
    private View view7f0a034d;
    private View view7f0a034e;
    private View view7f0a034f;
    private View view7f0a0350;

    public DuplicateDimpleDataFragment_ViewBinding(final DuplicateDimpleDataFragment duplicateDimpleDataFragment, View view) {
        this.target = duplicateDimpleDataFragment;
        duplicateDimpleDataFragment.gridLayout = (GridLayout) Utils.findRequiredViewAsType(view, R.id.gl_space, "field 'gridLayout'", GridLayout.class);
        duplicateDimpleDataFragment.rbStepX = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_step_x, "field 'rbStepX'", RadioButton.class);
        duplicateDimpleDataFragment.rbStepY = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_step_y, "field 'rbStepY'", RadioButton.class);
        duplicateDimpleDataFragment.rbStepZ = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_step_z, "field 'rbStepZ'", RadioButton.class);
        duplicateDimpleDataFragment.rbRow = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_row, "field 'rbRow'", RadioButton.class);
        duplicateDimpleDataFragment.ivXAdd = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_x_add, "field 'ivXAdd'", ImageView.class);
        duplicateDimpleDataFragment.ivYAdd = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_y_add, "field 'ivYAdd'", ImageView.class);
        duplicateDimpleDataFragment.ivZAdd = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_z_add, "field 'ivZAdd'", ImageView.class);
        duplicateDimpleDataFragment.ivXReduce = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_x_reduce, "field 'ivXReduce'", ImageView.class);
        duplicateDimpleDataFragment.ivYReduce = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_y_reduce, "field 'ivYReduce'", ImageView.class);
        duplicateDimpleDataFragment.ivZReduce = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_z_reduce, "field 'ivZReduce'", ImageView.class);
        duplicateDimpleDataFragment.btLocation = (Button) Utils.findRequiredViewAsType(view, R.id.bt_location, "field 'btLocation'", Button.class);
        duplicateDimpleDataFragment.btFindSpace = (Button) Utils.findRequiredViewAsType(view, R.id.bt_find_space, "field 'btFindSpace'", Button.class);
        duplicateDimpleDataFragment.btDecode = (Button) Utils.findRequiredViewAsType(view, R.id.bt_decode, "field 'btDecode'", Button.class);
        duplicateDimpleDataFragment.btSave = (Button) Utils.findRequiredViewAsType(view, R.id.bt_save, "field 'btSave'", Button.class);
        duplicateDimpleDataFragment.tvSleep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_decode_sleep, "field 'tvSleep'", TextView.class);
        duplicateDimpleDataFragment.rgRowIndex = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_row_index, "field 'rgRowIndex'", RadioGroup.class);
        duplicateDimpleDataFragment.tvSpace = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_space, "field 'tvSpace'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_row_1, "method 'onCheckedChanged'");
        this.view7f0a034d = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleDataFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_row_2, "method 'onCheckedChanged'");
        this.view7f0a034e = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleDataFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_row_3, "method 'onCheckedChanged'");
        this.view7f0a034f = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleDataFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_row_4, "method 'onCheckedChanged'");
        this.view7f0a0350 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                duplicateDimpleDataFragment.onCheckedChanged(compoundButton, z);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_number_1, "method 'onClick'");
        this.view7f0a009e = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_number_2, "method 'onClick'");
        this.view7f0a009f = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_number_3, "method 'onClick'");
        this.view7f0a00a0 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_number_4, "method 'onClick'");
        this.view7f0a00a1 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_number_5, "method 'onClick'");
        this.view7f0a00a2 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_number_6, "method 'onClick'");
        this.view7f0a00a3 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_number_7, "method 'onClick'");
        this.view7f0a00a4 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.bt_number_8, "method 'onClick'");
        this.view7f0a00a5 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_number_9, "method 'onClick'");
        this.view7f0a00a6 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.bt_number_0, "method 'onClick'");
        this.view7f0a009d = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.bt_delete, "method 'onClick'");
        this.view7f0a0083 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.bt_number_next, "method 'onClick'");
        this.view7f0a00a8 = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_number_last, "method 'onClick'");
        this.view7f0a00a7 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.bt_auto, "method 'onClick'");
        this.view7f0a0072 = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.18
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.iv_sleep_reduce, "method 'onClick'");
        this.view7f0a0222 = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.19
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.iv_sleep_add, "method 'onClick'");
        this.view7f0a0221 = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.20
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView21 = Utils.findRequiredView(view, R.id.iv_space_reduce, "method 'onClick'");
        this.view7f0a0227 = findRequiredView21;
        findRequiredView21.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.21
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView22 = Utils.findRequiredView(view, R.id.iv_space_add, "method 'onClick'");
        this.view7f0a0226 = findRequiredView22;
        findRequiredView22.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.22
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
        View findRequiredView23 = Utils.findRequiredView(view, R.id.bt_go_to_raw, "method 'onClick'");
        this.view7f0a008d = findRequiredView23;
        findRequiredView23.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DuplicateDimpleDataFragment_ViewBinding.23
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                duplicateDimpleDataFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DuplicateDimpleDataFragment duplicateDimpleDataFragment = this.target;
        if (duplicateDimpleDataFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        duplicateDimpleDataFragment.gridLayout = null;
        duplicateDimpleDataFragment.rbStepX = null;
        duplicateDimpleDataFragment.rbStepY = null;
        duplicateDimpleDataFragment.rbStepZ = null;
        duplicateDimpleDataFragment.rbRow = null;
        duplicateDimpleDataFragment.ivXAdd = null;
        duplicateDimpleDataFragment.ivYAdd = null;
        duplicateDimpleDataFragment.ivZAdd = null;
        duplicateDimpleDataFragment.ivXReduce = null;
        duplicateDimpleDataFragment.ivYReduce = null;
        duplicateDimpleDataFragment.ivZReduce = null;
        duplicateDimpleDataFragment.btLocation = null;
        duplicateDimpleDataFragment.btFindSpace = null;
        duplicateDimpleDataFragment.btDecode = null;
        duplicateDimpleDataFragment.btSave = null;
        duplicateDimpleDataFragment.tvSleep = null;
        duplicateDimpleDataFragment.rgRowIndex = null;
        duplicateDimpleDataFragment.tvSpace = null;
        ((CompoundButton) this.view7f0a034d).setOnCheckedChangeListener(null);
        this.view7f0a034d = null;
        ((CompoundButton) this.view7f0a034e).setOnCheckedChangeListener(null);
        this.view7f0a034e = null;
        ((CompoundButton) this.view7f0a034f).setOnCheckedChangeListener(null);
        this.view7f0a034f = null;
        ((CompoundButton) this.view7f0a0350).setOnCheckedChangeListener(null);
        this.view7f0a0350 = null;
        this.view7f0a009e.setOnClickListener(null);
        this.view7f0a009e = null;
        this.view7f0a009f.setOnClickListener(null);
        this.view7f0a009f = null;
        this.view7f0a00a0.setOnClickListener(null);
        this.view7f0a00a0 = null;
        this.view7f0a00a1.setOnClickListener(null);
        this.view7f0a00a1 = null;
        this.view7f0a00a2.setOnClickListener(null);
        this.view7f0a00a2 = null;
        this.view7f0a00a3.setOnClickListener(null);
        this.view7f0a00a3 = null;
        this.view7f0a00a4.setOnClickListener(null);
        this.view7f0a00a4 = null;
        this.view7f0a00a5.setOnClickListener(null);
        this.view7f0a00a5 = null;
        this.view7f0a00a6.setOnClickListener(null);
        this.view7f0a00a6 = null;
        this.view7f0a009d.setOnClickListener(null);
        this.view7f0a009d = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a00a8.setOnClickListener(null);
        this.view7f0a00a8 = null;
        this.view7f0a00a7.setOnClickListener(null);
        this.view7f0a00a7 = null;
        this.view7f0a0072.setOnClickListener(null);
        this.view7f0a0072 = null;
        this.view7f0a0222.setOnClickListener(null);
        this.view7f0a0222 = null;
        this.view7f0a0221.setOnClickListener(null);
        this.view7f0a0221 = null;
        this.view7f0a0227.setOnClickListener(null);
        this.view7f0a0227 = null;
        this.view7f0a0226.setOnClickListener(null);
        this.view7f0a0226 = null;
        this.view7f0a008d.setOnClickListener(null);
        this.view7f0a008d = null;
    }
}
