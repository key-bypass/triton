package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class MainFragment_ViewBinding implements Unbinder {
    private MainFragment target;
    private View view7f0a0014;
    private View view7f0a0127;
    private View view7f0a0194;
    private View view7f0a01a0;
    private View view7f0a01c3;
    private View view7f0a0245;
    private View view7f0a025d;
    private View view7f0a03cc;
    private View view7f0a04fb;

    public MainFragment_ViewBinding(final MainFragment mainFragment, View view) {
        this.target = mainFragment;
        mainFragment.rvExtraFuntion = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_extra_funtion, "field 'rvExtraFuntion'", RecyclerView.class);
        mainFragment.tvSeries = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_series, "field 'tvSeries'", TextView.class);
        mainFragment.tvSoftVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_soft_version, "field 'tvSoftVersion'", TextView.class);
        mainFragment.tvDbVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_db_version, "field 'tvDbVersion'", TextView.class);
        mainFragment.tvSearch = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_search, "field 'tvSearch'", TextView.class);
        mainFragment.rvCarkay = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_carkay, "field 'rvCarkay'", RecyclerView.class);
        mainFragment.rvHousekey = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_housekey, "field 'rvHousekey'", RecyclerView.class);
        mainFragment.tvTitleHousekey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_house_key, "field 'tvTitleHousekey'", TextView.class);
        mainFragment.tvTitleCarkey = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_carkey, "field 'tvTitleCarkey'", TextView.class);
        mainFragment.drawerLayout = (DrawerLayout) Utils.findRequiredViewAsType(view, R.id.drawerlayout, "field 'drawerLayout'", DrawerLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.language_choice, "field 'languageChoice' and method 'onViewClicked'");
        mainFragment.languageChoice = (TextView) Utils.castView(findRequiredView, R.id.language_choice, "field 'languageChoice'", TextView.class);
        this.view7f0a0245 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.setting, "field 'setting' and method 'onViewClicked'");
        mainFragment.setting = (TextView) Utils.castView(findRequiredView2, R.id.setting, "field 'setting'", TextView.class);
        this.view7f0a03cc = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.version_update, "field 'versionUpdate' and method 'onViewClicked'");
        mainFragment.versionUpdate = (TextView) Utils.castView(findRequiredView3, R.id.version_update, "field 'versionUpdate'", TextView.class);
        this.view7f0a04fb = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.about_us, "field 'aboutUs' and method 'onViewClicked'");
        mainFragment.aboutUs = (TextView) Utils.castView(findRequiredView4, R.id.about_us, "field 'aboutUs'", TextView.class);
        this.view7f0a0014 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.help_center, "field 'helpCenter' and method 'onViewClicked'");
        mainFragment.helpCenter = (TextView) Utils.castView(findRequiredView5, R.id.help_center, "field 'helpCenter'", TextView.class);
        this.view7f0a01c3 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        mainFragment.devideLanguage = Utils.findRequiredView(view, R.id.view_devide_language, "field 'devideLanguage'");
        View findRequiredView6 = Utils.findRequiredView(view, R.id.ll_bar_code, "field 'llBarCode' and method 'onViewClicked'");
        mainFragment.llBarCode = (LinearLayout) Utils.castView(findRequiredView6, R.id.ll_bar_code, "field 'llBarCode'", LinearLayout.class);
        this.view7f0a025d = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.fl_adv_search, "field 'flAdvSearch' and method 'onViewClicked'");
        mainFragment.flAdvSearch = (FrameLayout) Utils.castView(findRequiredView7, R.id.fl_adv_search, "field 'flAdvSearch'", FrameLayout.class);
        this.view7f0a0194 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.fl_search, "method 'onViewClicked'");
        this.view7f0a01a0 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.data_update, "method 'onViewClicked'");
        this.view7f0a0127 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MainFragment mainFragment = this.target;
        if (mainFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainFragment.rvExtraFuntion = null;
        mainFragment.tvSeries = null;
        mainFragment.tvSoftVersion = null;
        mainFragment.tvDbVersion = null;
        mainFragment.tvSearch = null;
        mainFragment.rvCarkay = null;
        mainFragment.rvHousekey = null;
        mainFragment.tvTitleHousekey = null;
        mainFragment.tvTitleCarkey = null;
        mainFragment.drawerLayout = null;
        mainFragment.languageChoice = null;
        mainFragment.setting = null;
        mainFragment.versionUpdate = null;
        mainFragment.aboutUs = null;
        mainFragment.helpCenter = null;
        mainFragment.devideLanguage = null;
        mainFragment.llBarCode = null;
        mainFragment.flAdvSearch = null;
        this.view7f0a0245.setOnClickListener(null);
        this.view7f0a0245 = null;
        this.view7f0a03cc.setOnClickListener(null);
        this.view7f0a03cc = null;
        this.view7f0a04fb.setOnClickListener(null);
        this.view7f0a04fb = null;
        this.view7f0a0014.setOnClickListener(null);
        this.view7f0a0014 = null;
        this.view7f0a01c3.setOnClickListener(null);
        this.view7f0a01c3 = null;
        this.view7f0a025d.setOnClickListener(null);
        this.view7f0a025d = null;
        this.view7f0a0194.setOnClickListener(null);
        this.view7f0a0194 = null;
        this.view7f0a01a0.setOnClickListener(null);
        this.view7f0a01a0 = null;
        this.view7f0a0127.setOnClickListener(null);
        this.view7f0a0127 = null;
    }
}
