package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BrandVisiBilitySettingFragment_ViewBinding implements Unbinder {
    private BrandVisiBilitySettingFragment target;
    private View view7f0a01ff;
    private View view7f0a021e;

    public BrandVisiBilitySettingFragment_ViewBinding(final BrandVisiBilitySettingFragment brandVisiBilitySettingFragment, View view) {
        this.target = brandVisiBilitySettingFragment;
        brandVisiBilitySettingFragment.rvBrandShow = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_brand_show, "field 'rvBrandShow'", RecyclerView.class);
        brandVisiBilitySettingFragment.indexBarBrandShow = (IndexBar) Utils.findRequiredViewAsType(view, R.id.indexBar_brand_show, "field 'indexBarBrandShow'", IndexBar.class);
        brandVisiBilitySettingFragment.tvSideBarHintBradShow = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSideBarHint_brad_show, "field 'tvSideBarHintBradShow'", TextView.class);
        brandVisiBilitySettingFragment.rvBrandHidden = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_brand_hidden, "field 'rvBrandHidden'", RecyclerView.class);
        brandVisiBilitySettingFragment.indexBarBrandHide = (IndexBar) Utils.findRequiredViewAsType(view, R.id.indexBar_brand_hide, "field 'indexBarBrandHide'", IndexBar.class);
        brandVisiBilitySettingFragment.tvSideBarHintBradHide = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSideBarHint_brad_hide, "field 'tvSideBarHintBradHide'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_hide, "method 'onViewClicked'");
        this.view7f0a01ff = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                brandVisiBilitySettingFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.iv_show, "method 'onViewClicked'");
        this.view7f0a021e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                brandVisiBilitySettingFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BrandVisiBilitySettingFragment brandVisiBilitySettingFragment = this.target;
        if (brandVisiBilitySettingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        brandVisiBilitySettingFragment.rvBrandShow = null;
        brandVisiBilitySettingFragment.indexBarBrandShow = null;
        brandVisiBilitySettingFragment.tvSideBarHintBradShow = null;
        brandVisiBilitySettingFragment.rvBrandHidden = null;
        brandVisiBilitySettingFragment.indexBarBrandHide = null;
        brandVisiBilitySettingFragment.tvSideBarHintBradHide = null;
        this.view7f0a01ff.setOnClickListener(null);
        this.view7f0a01ff = null;
        this.view7f0a021e.setOnClickListener(null);
        this.view7f0a021e = null;
    }
}
