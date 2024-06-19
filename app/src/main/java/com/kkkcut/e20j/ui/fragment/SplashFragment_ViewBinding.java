package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SplashFragment_ViewBinding implements Unbinder {
    private SplashFragment target;
    private View view7f0a022b;

    public SplashFragment_ViewBinding(final SplashFragment splashFragment, View view) {
        this.target = splashFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_splash, "field 'ivSplash' and method 'onViewClicked'");
        splashFragment.ivSplash = (ImageView) Utils.castView(findRequiredView, R.id.iv_splash, "field 'ivSplash'", ImageView.class);
        this.view7f0a022b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SplashFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                splashFragment.onViewClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SplashFragment splashFragment = this.target;
        if (splashFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        splashFragment.ivSplash = null;
        this.view7f0a022b.setOnClickListener(null);
        this.view7f0a022b = null;
    }
}
