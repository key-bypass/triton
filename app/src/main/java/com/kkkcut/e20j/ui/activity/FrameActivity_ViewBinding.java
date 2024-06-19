package com.kkkcut.e20j.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class FrameActivity_ViewBinding implements Unbinder {
    private FrameActivity target;
    private View view7f0a0200;
    private View view7f0a020f;
    private View view7f0a0210;
    private View view7f0a0447;
    private View view7f0a04c2;

    public FrameActivity_ViewBinding(FrameActivity frameActivity) {
        this(frameActivity, frameActivity.getWindow().getDecorView());
    }

    public FrameActivity_ViewBinding(final FrameActivity frameActivity, View view) {
        this.target = frameActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_back, "field 'tvBack' and method 'onViewClicked'");
        frameActivity.tvBack = (TextView) Utils.castView(findRequiredView, R.id.tv_back, "field 'tvBack'", TextView.class);
        this.view7f0a0447 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                frameActivity.onViewClicked(view2);
            }
        });
        frameActivity.tvLogo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_logo, "field 'tvLogo'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.iv_menu, "field 'ivMenu' and method 'onViewClicked'");
        frameActivity.ivMenu = (ImageView) Utils.castView(findRequiredView2, R.id.iv_menu, "field 'ivMenu'", ImageView.class);
        this.view7f0a020f = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                frameActivity.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.iv_message, "field 'ivMessage' and method 'onViewClicked'");
        frameActivity.ivMessage = (ImageView) Utils.castView(findRequiredView3, R.id.iv_message, "field 'ivMessage'", ImageView.class);
        this.view7f0a0210 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                frameActivity.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_home, "field 'ivHome' and method 'onViewClicked'");
        frameActivity.ivHome = (ImageView) Utils.castView(findRequiredView4, R.id.iv_home, "field 'ivHome'", ImageView.class);
        this.view7f0a0200 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                frameActivity.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.tv_title, "field 'tvTitle' and method 'onViewClicked'");
        frameActivity.tvTitle = (TextView) Utils.castView(findRequiredView5, R.id.tv_title, "field 'tvTitle'", TextView.class);
        this.view7f0a04c2 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.FrameActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                frameActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        FrameActivity frameActivity = this.target;
        if (frameActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        frameActivity.tvBack = null;
        frameActivity.tvLogo = null;
        frameActivity.ivMenu = null;
        frameActivity.ivMessage = null;
        frameActivity.ivHome = null;
        frameActivity.tvTitle = null;
        this.view7f0a0447.setOnClickListener(null);
        this.view7f0a0447 = null;
        this.view7f0a020f.setOnClickListener(null);
        this.view7f0a020f = null;
        this.view7f0a0210.setOnClickListener(null);
        this.view7f0a0210 = null;
        this.view7f0a0200.setOnClickListener(null);
        this.view7f0a0200 = null;
        this.view7f0a04c2.setOnClickListener(null);
        this.view7f0a04c2 = null;
    }
}
