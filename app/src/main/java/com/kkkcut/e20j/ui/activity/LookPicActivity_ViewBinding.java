package com.kkkcut.e20j.ui.activity;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class LookPicActivity_ViewBinding implements Unbinder {
    private LookPicActivity target;
    private View view7f0a019d;

    public LookPicActivity_ViewBinding(LookPicActivity lookPicActivity) {
        this(lookPicActivity, lookPicActivity.getWindow().getDecorView());
    }

    public LookPicActivity_ViewBinding(final LookPicActivity lookPicActivity, View view) {
        this.target = lookPicActivity;
        lookPicActivity.ivPhoto = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_show, "field 'ivPhoto'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.fl_lool_pic, "method 'onViewClicked'");
        this.view7f0a019d = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.LookPicActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                lookPicActivity.onViewClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LookPicActivity lookPicActivity = this.target;
        if (lookPicActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        lookPicActivity.ivPhoto = null;
        this.view7f0a019d.setOnClickListener(null);
        this.view7f0a019d = null;
    }
}
