package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.androidquick.autolayout.widget.AutoRadioGroup;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyAlignSelectFragment_ViewBinding implements Unbinder {
    private KeyAlignSelectFragment target;
    private View view7f0a0095;
    private View view7f0a009b;

    public KeyAlignSelectFragment_ViewBinding(final KeyAlignSelectFragment keyAlignSelectFragment, View view) {
        this.target = keyAlignSelectFragment;
        keyAlignSelectFragment.rg1 = (AutoRadioGroup) Utils.findRequiredViewAsType(view, R.id.rg1, "field 'rg1'", AutoRadioGroup.class);
        keyAlignSelectFragment.rbTip = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tip, "field 'rbTip'", RadioButton.class);
        keyAlignSelectFragment.rbShoulder = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_shoulder, "field 'rbShoulder'", RadioButton.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyAlignSelectFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyAlignSelectFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_next, "method 'onViewClicked'");
        this.view7f0a009b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyAlignSelectFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyAlignSelectFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyAlignSelectFragment keyAlignSelectFragment = this.target;
        if (keyAlignSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyAlignSelectFragment.rg1 = null;
        keyAlignSelectFragment.rbTip = null;
        keyAlignSelectFragment.rbShoulder = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
    }
}
