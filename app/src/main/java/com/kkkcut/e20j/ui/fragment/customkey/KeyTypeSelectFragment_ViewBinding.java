package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyTypeSelectFragment_ViewBinding implements Unbinder {
    private KeyTypeSelectFragment target;
    private View view7f0a0095;
    private View view7f0a009b;

    public KeyTypeSelectFragment_ViewBinding(final KeyTypeSelectFragment keyTypeSelectFragment, View view) {
        this.target = keyTypeSelectFragment;
        keyTypeSelectFragment.rg1 = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg1, "field 'rg1'", RadioGroup.class);
        keyTypeSelectFragment.rg2 = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg2, "field 'rg2'", RadioGroup.class);
        keyTypeSelectFragment.rbSingleKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_single_key, "field 'rbSingleKey'", RadioButton.class);
        keyTypeSelectFragment.rbSingleInsideKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_inside_key, "field 'rbSingleInsideKey'", RadioButton.class);
        keyTypeSelectFragment.rbDoubleInsideKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_double_inside_key, "field 'rbDoubleInsideKey'", RadioButton.class);
        keyTypeSelectFragment.rbDoubleKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_double_key, "field 'rbDoubleKey'", RadioButton.class);
        keyTypeSelectFragment.rbSingleOutsideKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_single_outside_key, "field 'rbSingleOutsideKey'", RadioButton.class);
        keyTypeSelectFragment.rbDoubleOutsideKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_double_outside_key, "field 'rbDoubleOutsideKey'", RadioButton.class);
        keyTypeSelectFragment.rbDimpleKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_dimple_key, "field 'rbDimpleKey'", RadioButton.class);
        keyTypeSelectFragment.rbTubularKey = (RadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tubular_key, "field 'rbTubularKey'", RadioButton.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyTypeSelectFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_next, "method 'onViewClicked'");
        this.view7f0a009b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyTypeSelectFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyTypeSelectFragment keyTypeSelectFragment = this.target;
        if (keyTypeSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyTypeSelectFragment.rg1 = null;
        keyTypeSelectFragment.rg2 = null;
        keyTypeSelectFragment.rbSingleKey = null;
        keyTypeSelectFragment.rbSingleInsideKey = null;
        keyTypeSelectFragment.rbDoubleInsideKey = null;
        keyTypeSelectFragment.rbDoubleKey = null;
        keyTypeSelectFragment.rbSingleOutsideKey = null;
        keyTypeSelectFragment.rbDoubleOutsideKey = null;
        keyTypeSelectFragment.rbDimpleKey = null;
        keyTypeSelectFragment.rbTubularKey = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
    }
}
