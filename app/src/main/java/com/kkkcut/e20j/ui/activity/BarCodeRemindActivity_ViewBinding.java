package com.kkkcut.e20j.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BarCodeRemindActivity_ViewBinding implements Unbinder {
    private BarCodeRemindActivity target;
    private View view7f0a01e9;
    private View view7f0a0252;
    private View view7f0a0253;
    private View view7f0a0254;

    public BarCodeRemindActivity_ViewBinding(BarCodeRemindActivity barCodeRemindActivity) {
        this(barCodeRemindActivity, barCodeRemindActivity.getWindow().getDecorView());
    }

    public BarCodeRemindActivity_ViewBinding(final BarCodeRemindActivity barCodeRemindActivity, View view) {
        this.target = barCodeRemindActivity;
        barCodeRemindActivity.iv1 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_1, "field 'iv1'", ImageView.class);
        barCodeRemindActivity.iv2 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_2, "field 'iv2'", ImageView.class);
        barCodeRemindActivity.iv3 = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_3, "field 'iv3'", ImageView.class);
        barCodeRemindActivity.tv1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_1, "field 'tv1'", TextView.class);
        barCodeRemindActivity.tv2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_2, "field 'tv2'", TextView.class);
        barCodeRemindActivity.tv3 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_3, "field 'tv3'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.ll_1, "field 'll1' and method 'onclick'");
        barCodeRemindActivity.ll1 = (LinearLayout) Utils.castView(findRequiredView, R.id.ll_1, "field 'll1'", LinearLayout.class);
        this.view7f0a0252 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.BarCodeRemindActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                barCodeRemindActivity.onclick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ll_2, "field 'll2' and method 'onclick'");
        barCodeRemindActivity.ll2 = (LinearLayout) Utils.castView(findRequiredView2, R.id.ll_2, "field 'll2'", LinearLayout.class);
        this.view7f0a0253 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.BarCodeRemindActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                barCodeRemindActivity.onclick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.ll_3, "field 'll3' and method 'onclick'");
        barCodeRemindActivity.ll3 = (LinearLayout) Utils.castView(findRequiredView3, R.id.ll_3, "field 'll3'", LinearLayout.class);
        this.view7f0a0254 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.BarCodeRemindActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                barCodeRemindActivity.onclick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_back, "method 'onclick'");
        this.view7f0a01e9 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.BarCodeRemindActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                barCodeRemindActivity.onclick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BarCodeRemindActivity barCodeRemindActivity = this.target;
        if (barCodeRemindActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        barCodeRemindActivity.iv1 = null;
        barCodeRemindActivity.iv2 = null;
        barCodeRemindActivity.iv3 = null;
        barCodeRemindActivity.tv1 = null;
        barCodeRemindActivity.tv2 = null;
        barCodeRemindActivity.tv3 = null;
        barCodeRemindActivity.ll1 = null;
        barCodeRemindActivity.ll2 = null;
        barCodeRemindActivity.ll3 = null;
        this.view7f0a0252.setOnClickListener(null);
        this.view7f0a0252 = null;
        this.view7f0a0253.setOnClickListener(null);
        this.view7f0a0253 = null;
        this.view7f0a0254.setOnClickListener(null);
        this.view7f0a0254 = null;
        this.view7f0a01e9.setOnClickListener(null);
        this.view7f0a01e9 = null;
    }
}
