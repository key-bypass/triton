package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class MainE9Fragment_ViewBinding implements Unbinder {
    private MainE9Fragment target;
    private View view7f0a0014;
    private View view7f0a0127;
    private View view7f0a01c3;
    private View view7f0a021b;
    private View view7f0a0245;
    private View view7f0a03cc;
    private View view7f0a04fb;

    public MainE9Fragment_ViewBinding(final MainE9Fragment mainE9Fragment, View view) {
        this.target = mainE9Fragment;
        mainE9Fragment.rvExtraFuntion = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_bottom, "field 'rvExtraFuntion'", RecyclerView.class);
        mainE9Fragment.rvCarkay = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_center, "field 'rvCarkay'", RecyclerView.class);
        mainE9Fragment.tvSeries = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_series, "field 'tvSeries'", TextView.class);
        mainE9Fragment.tvSoftVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_soft_version, "field 'tvSoftVersion'", TextView.class);
        mainE9Fragment.tvDbVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_db_version, "field 'tvDbVersion'", TextView.class);
        mainE9Fragment.drawerLayout = (DrawerLayout) Utils.findRequiredViewAsType(view, R.id.drawerlayout, "field 'drawerLayout'", DrawerLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.language_choice, "field 'languageChoice' and method 'onViewClicked'");
        mainE9Fragment.languageChoice = (TextView) Utils.castView(findRequiredView, R.id.language_choice, "field 'languageChoice'", TextView.class);
        this.view7f0a0245 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.help_center, "field 'helpCenter' and method 'onViewClicked'");
        mainE9Fragment.helpCenter = (TextView) Utils.castView(findRequiredView2, R.id.help_center, "field 'helpCenter'", TextView.class);
        this.view7f0a01c3 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        mainE9Fragment.devideLanguage = Utils.findRequiredView(view, R.id.view_devide_language, "field 'devideLanguage'");
        View findRequiredView3 = Utils.findRequiredView(view, R.id.iv_service, "field 'ivService' and method 'onViewClicked'");
        mainE9Fragment.ivService = (ImageView) Utils.castView(findRequiredView3, R.id.iv_service, "field 'ivService'", ImageView.class);
        this.view7f0a021b = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.version_update, "method 'onViewClicked'");
        this.view7f0a04fb = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.data_update, "method 'onViewClicked'");
        this.view7f0a0127 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.about_us, "method 'onViewClicked'");
        this.view7f0a0014 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.setting, "method 'onViewClicked'");
        this.view7f0a03cc = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.MainE9Fragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainE9Fragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MainE9Fragment mainE9Fragment = this.target;
        if (mainE9Fragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainE9Fragment.rvExtraFuntion = null;
        mainE9Fragment.rvCarkay = null;
        mainE9Fragment.tvSeries = null;
        mainE9Fragment.tvSoftVersion = null;
        mainE9Fragment.tvDbVersion = null;
        mainE9Fragment.drawerLayout = null;
        mainE9Fragment.languageChoice = null;
        mainE9Fragment.helpCenter = null;
        mainE9Fragment.devideLanguage = null;
        mainE9Fragment.ivService = null;
        this.view7f0a0245.setOnClickListener(null);
        this.view7f0a0245 = null;
        this.view7f0a01c3.setOnClickListener(null);
        this.view7f0a01c3 = null;
        this.view7f0a021b.setOnClickListener(null);
        this.view7f0a021b = null;
        this.view7f0a04fb.setOnClickListener(null);
        this.view7f0a04fb = null;
        this.view7f0a0127.setOnClickListener(null);
        this.view7f0a0127 = null;
        this.view7f0a0014.setOnClickListener(null);
        this.view7f0a0014 = null;
        this.view7f0a03cc.setOnClickListener(null);
        this.view7f0a03cc = null;
    }
}
