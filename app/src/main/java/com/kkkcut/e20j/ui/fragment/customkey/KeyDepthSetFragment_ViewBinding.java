package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyDepthSetFragment_ViewBinding implements Unbinder {
    private KeyDepthSetFragment target;
    private View view7f0a0083;
    private View view7f0a0095;
    private View view7f0a009b;
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
    private View view7f0a04a9;
    private View view7f0a04ab;

    public KeyDepthSetFragment_ViewBinding(final KeyDepthSetFragment keyDepthSetFragment, View view) {
        this.target = keyDepthSetFragment;
        keyDepthSetFragment.llDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_space, "field 'llDepth'", LinearLayout.class);
        keyDepthSetFragment.llDepthTool = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_space_tool, "field 'llDepthTool'", LinearLayout.class);
        keyDepthSetFragment.llIndex = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_index, "field 'llIndex'", LinearLayout.class);
        keyDepthSetFragment.tvSideRow = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_side_row, "field 'tvSideRow'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_row_add, "field 'tvRowAdd' and method 'onViewClicked'");
        keyDepthSetFragment.tvRowAdd = (ImageView) Utils.castView(findRequiredView, R.id.tv_row_add, "field 'tvRowAdd'", ImageView.class);
        this.view7f0a04a9 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_row_reduce, "field 'tvRowReduce' and method 'onViewClicked'");
        keyDepthSetFragment.tvRowReduce = (ImageView) Utils.castView(findRequiredView2, R.id.tv_row_reduce, "field 'tvRowReduce'", ImageView.class);
        this.view7f0a04ab = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        keyDepthSetFragment.llSide = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_side, "field 'llSide'", LinearLayout.class);
        keyDepthSetFragment.llDepthName = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_depth_name, "field 'llDepthName'", LinearLayout.class);
        keyDepthSetFragment.ivSpace = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_space, "field 'ivSpace'", ImageView.class);
        keyDepthSetFragment.tvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'tvUnit'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_number_1, "method 'onViewClicked'");
        this.view7f0a009e = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_number_2, "method 'onViewClicked'");
        this.view7f0a009f = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_number_3, "method 'onViewClicked'");
        this.view7f0a00a0 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_delete, "method 'onViewClicked'");
        this.view7f0a0083 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_number_4, "method 'onViewClicked'");
        this.view7f0a00a1 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_number_5, "method 'onViewClicked'");
        this.view7f0a00a2 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.bt_number_6, "method 'onViewClicked'");
        this.view7f0a00a3 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_number_next, "method 'onViewClicked'");
        this.view7f0a00a8 = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.bt_number_7, "method 'onViewClicked'");
        this.view7f0a00a4 = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.bt_number_8, "method 'onViewClicked'");
        this.view7f0a00a5 = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.bt_number_9, "method 'onViewClicked'");
        this.view7f0a00a6 = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView14 = Utils.findRequiredView(view, R.id.bt_number_last, "method 'onViewClicked'");
        this.view7f0a00a7 = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.bt_number_0, "method 'onViewClicked'");
        this.view7f0a009d = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.bt_next, "method 'onViewClicked'");
        this.view7f0a009b = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyDepthSetFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyDepthSetFragment keyDepthSetFragment = this.target;
        if (keyDepthSetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyDepthSetFragment.llDepth = null;
        keyDepthSetFragment.llDepthTool = null;
        keyDepthSetFragment.llIndex = null;
        keyDepthSetFragment.tvSideRow = null;
        keyDepthSetFragment.tvRowAdd = null;
        keyDepthSetFragment.tvRowReduce = null;
        keyDepthSetFragment.llSide = null;
        keyDepthSetFragment.llDepthName = null;
        keyDepthSetFragment.ivSpace = null;
        keyDepthSetFragment.tvUnit = null;
        this.view7f0a04a9.setOnClickListener(null);
        this.view7f0a04a9 = null;
        this.view7f0a04ab.setOnClickListener(null);
        this.view7f0a04ab = null;
        this.view7f0a009e.setOnClickListener(null);
        this.view7f0a009e = null;
        this.view7f0a009f.setOnClickListener(null);
        this.view7f0a009f = null;
        this.view7f0a00a0.setOnClickListener(null);
        this.view7f0a00a0 = null;
        this.view7f0a0083.setOnClickListener(null);
        this.view7f0a0083 = null;
        this.view7f0a00a1.setOnClickListener(null);
        this.view7f0a00a1 = null;
        this.view7f0a00a2.setOnClickListener(null);
        this.view7f0a00a2 = null;
        this.view7f0a00a3.setOnClickListener(null);
        this.view7f0a00a3 = null;
        this.view7f0a00a8.setOnClickListener(null);
        this.view7f0a00a8 = null;
        this.view7f0a00a4.setOnClickListener(null);
        this.view7f0a00a4 = null;
        this.view7f0a00a5.setOnClickListener(null);
        this.view7f0a00a5 = null;
        this.view7f0a00a6.setOnClickListener(null);
        this.view7f0a00a6 = null;
        this.view7f0a00a7.setOnClickListener(null);
        this.view7f0a00a7 = null;
        this.view7f0a009d.setOnClickListener(null);
        this.view7f0a009d = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
    }
}
