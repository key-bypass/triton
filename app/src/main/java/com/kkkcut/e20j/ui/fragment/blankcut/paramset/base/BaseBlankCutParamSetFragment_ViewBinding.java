package com.kkkcut.e20j.ui.fragment.blankcut.paramset.base;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BaseBlankCutParamSetFragment_ViewBinding implements Unbinder {
    private BaseBlankCutParamSetFragment target;
    private View view7f0a0081;

    public BaseBlankCutParamSetFragment_ViewBinding(final BaseBlankCutParamSetFragment baseBlankCutParamSetFragment, View view) {
        this.target = baseBlankCutParamSetFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_cut, "method 'onClick'");
        this.view7f0a0081 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                baseBlankCutParamSetFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
    }
}
