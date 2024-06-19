package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.MarqueeTextView;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyOperateFragment_ViewBinding implements Unbinder {
    private KeyOperateFragment target;
    private View view7f0a007a;
    private View view7f0a0081;
    private View view7f0a0082;
    private View view7f0a019c;
    private View view7f0a0205;
    private View view7f0a021a;
    private View view7f0a022c;
    private View view7f0a022d;
    private View view7f0a0443;
    private View view7f0a0451;
    private View view7f0a0452;
    private View view7f0a047b;
    private View view7f0a047c;
    private View view7f0a0486;
    private View view7f0a0493;

    public KeyOperateFragment_ViewBinding(final KeyOperateFragment keyOperateFragment, View view) {
        this.target = keyOperateFragment;
        keyOperateFragment.tvHint = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hint, "field 'tvHint'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fl_key_view, "field 'flKeyview' and method 'onViewClicked'");
        keyOperateFragment.flKeyview = (FrameLayout) Utils.castView(findRequiredView, R.id.fl_key_view, "field 'flKeyview'", FrameLayout.class);
        this.view7f0a019c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.tv_collect, "field 'tvCollect' and method 'onViewClicked'");
        keyOperateFragment.tvCollect = (TextView) Utils.castView(findRequiredView2, R.id.tv_collect, "field 'tvCollect'", TextView.class);
        this.view7f0a0452 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        keyOperateFragment.tvBlank = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_blank, "field 'tvBlank'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tv_input, "field 'llInput' and method 'onViewClicked'");
        keyOperateFragment.llInput = (TextView) Utils.castView(findRequiredView3, R.id.tv_input, "field 'llInput'", TextView.class);
        this.view7f0a047c = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tv_code_find_tooth, "field 'llCodeFindTooth' and method 'onViewClicked'");
        keyOperateFragment.llCodeFindTooth = (TextView) Utils.castView(findRequiredView4, R.id.tv_code_find_tooth, "field 'llCodeFindTooth'", TextView.class);
        this.view7f0a0451 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        keyOperateFragment.tvSide = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_side, "field 'tvSide'", TextView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_lack_tooth, "field 'llLackTooth' and method 'onViewClicked'");
        keyOperateFragment.llLackTooth = (TextView) Utils.castView(findRequiredView5, R.id.tv_lack_tooth, "field 'llLackTooth'", TextView.class);
        this.view7f0a0486 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.tv_move, "field 'tvMove' and method 'onViewClicked'");
        keyOperateFragment.tvMove = (TextView) Utils.castView(findRequiredView6, R.id.tv_move, "field 'tvMove'", TextView.class);
        this.view7f0a0493 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.bt_cut, "field 'btCut' and method 'onViewClicked'");
        keyOperateFragment.btCut = (TextView) Utils.castView(findRequiredView7, R.id.bt_cut, "field 'btCut'", TextView.class);
        this.view7f0a0081 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.bt_decode, "field 'btDecode' and method 'onViewClicked'");
        keyOperateFragment.btDecode = (TextView) Utils.castView(findRequiredView8, R.id.bt_decode, "field 'btDecode'", TextView.class);
        this.view7f0a0082 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.tv_adjust, "field 'tvAdjust' and method 'onViewClicked'");
        keyOperateFragment.tvAdjust = (TextView) Utils.castView(findRequiredView9, R.id.tv_adjust, "field 'tvAdjust'", TextView.class);
        this.view7f0a0443 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        keyOperateFragment.tvSeries = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_series, "field 'tvSeries'", TextView.class);
        keyOperateFragment.vpClamp = (ViewPager) Utils.findRequiredViewAsType(view, R.id.vp_clamp, "field 'vpClamp'", ViewPager.class);
        keyOperateFragment.tvDecoderSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_decoder_size, "field 'tvDecoderSize'", TextView.class);
        keyOperateFragment.tvCutterSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvCutterSize'", TextView.class);
        View findRequiredView10 = Utils.findRequiredView(view, R.id.bt_change_sibling, "field 'btChangedSibling' and method 'onViewClicked'");
        keyOperateFragment.btChangedSibling = (Button) Utils.castView(findRequiredView10, R.id.bt_change_sibling, "field 'btChangedSibling'", Button.class);
        this.view7f0a007a = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        keyOperateFragment.ivRealKey = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_real_key, "field 'ivRealKey'", ImageView.class);
        View findRequiredView11 = Utils.findRequiredView(view, R.id.tv_info, "field 'tvInfo' and method 'onViewClicked'");
        keyOperateFragment.tvInfo = (TextView) Utils.castView(findRequiredView11, R.id.tv_info, "field 'tvInfo'", TextView.class);
        this.view7f0a047b = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView12 = Utils.findRequiredView(view, R.id.iv_switch_last, "field 'ivSwitchLast' and method 'onViewClicked'");
        keyOperateFragment.ivSwitchLast = (ImageView) Utils.castView(findRequiredView12, R.id.iv_switch_last, "field 'ivSwitchLast'", ImageView.class);
        this.view7f0a022c = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView13 = Utils.findRequiredView(view, R.id.iv_switch_next, "field 'ivSwitchNext' and method 'onViewClicked'");
        keyOperateFragment.ivSwitchNext = (ImageView) Utils.castView(findRequiredView13, R.id.iv_switch_next, "field 'ivSwitchNext'", ImageView.class);
        this.view7f0a022d = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        keyOperateFragment.mtv = (MarqueeTextView) Utils.findRequiredViewAsType(view, R.id.mtv, "field 'mtv'", MarqueeTextView.class);
        keyOperateFragment.tvCode = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_code, "field 'tvCode'", TextView.class);
        View findRequiredView14 = Utils.findRequiredView(view, R.id.iv_scale, "method 'onViewClicked'");
        this.view7f0a021a = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.iv_key_scale, "method 'onViewClicked'");
        this.view7f0a0205 = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyOperateFragment_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyOperateFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyOperateFragment keyOperateFragment = this.target;
        if (keyOperateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyOperateFragment.tvHint = null;
        keyOperateFragment.flKeyview = null;
        keyOperateFragment.tvCollect = null;
        keyOperateFragment.tvBlank = null;
        keyOperateFragment.llInput = null;
        keyOperateFragment.llCodeFindTooth = null;
        keyOperateFragment.tvSide = null;
        keyOperateFragment.llLackTooth = null;
        keyOperateFragment.tvMove = null;
        keyOperateFragment.btCut = null;
        keyOperateFragment.btDecode = null;
        keyOperateFragment.tvAdjust = null;
        keyOperateFragment.tvSeries = null;
        keyOperateFragment.vpClamp = null;
        keyOperateFragment.tvDecoderSize = null;
        keyOperateFragment.tvCutterSize = null;
        keyOperateFragment.btChangedSibling = null;
        keyOperateFragment.ivRealKey = null;
        keyOperateFragment.tvInfo = null;
        keyOperateFragment.ivSwitchLast = null;
        keyOperateFragment.ivSwitchNext = null;
        keyOperateFragment.mtv = null;
        keyOperateFragment.tvCode = null;
        this.view7f0a019c.setOnClickListener(null);
        this.view7f0a019c = null;
        this.view7f0a0452.setOnClickListener(null);
        this.view7f0a0452 = null;
        this.view7f0a047c.setOnClickListener(null);
        this.view7f0a047c = null;
        this.view7f0a0451.setOnClickListener(null);
        this.view7f0a0451 = null;
        this.view7f0a0486.setOnClickListener(null);
        this.view7f0a0486 = null;
        this.view7f0a0493.setOnClickListener(null);
        this.view7f0a0493 = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
        this.view7f0a0082.setOnClickListener(null);
        this.view7f0a0082 = null;
        this.view7f0a0443.setOnClickListener(null);
        this.view7f0a0443 = null;
        this.view7f0a007a.setOnClickListener(null);
        this.view7f0a007a = null;
        this.view7f0a047b.setOnClickListener(null);
        this.view7f0a047b = null;
        this.view7f0a022c.setOnClickListener(null);
        this.view7f0a022c = null;
        this.view7f0a022d.setOnClickListener(null);
        this.view7f0a022d = null;
        this.view7f0a021a.setOnClickListener(null);
        this.view7f0a021a = null;
        this.view7f0a0205.setOnClickListener(null);
        this.view7f0a0205 = null;
    }
}
