package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyClampSetFragment_ViewBinding implements Unbinder {
    private KeyClampSetFragment target;
    private View view7f0a0095;
    private View view7f0a009b;
    private View view7f0a032a;
    private View view7f0a032b;
    private View view7f0a032c;

    public KeyClampSetFragment_ViewBinding(final KeyClampSetFragment keyClampSetFragment, View view) {
        this.target = keyClampSetFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.rb_clamp_1, "field 'rbClamp1' and method 'onCheckedChange'");
        keyClampSetFragment.rbClamp1 = (RadioButton) Utils.castView(findRequiredView, R.id.rb_clamp_1, "field 'rbClamp1'", RadioButton.class);
        this.view7f0a032a = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keyClampSetFragment.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rb_clamp_2, "field 'rbClamp2' and method 'onCheckedChange'");
        keyClampSetFragment.rbClamp2 = (RadioButton) Utils.castView(findRequiredView2, R.id.rb_clamp_2, "field 'rbClamp2'", RadioButton.class);
        this.view7f0a032b = findRequiredView2;
        ((CompoundButton) findRequiredView2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment_ViewBinding.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keyClampSetFragment.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rb_clamp_3, "field 'rbClamp3' and method 'onCheckedChange'");
        keyClampSetFragment.rbClamp3 = (RadioButton) Utils.castView(findRequiredView3, R.id.rb_clamp_3, "field 'rbClamp3'", RadioButton.class);
        this.view7f0a032c = findRequiredView3;
        ((CompoundButton) findRequiredView3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment_ViewBinding.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keyClampSetFragment.onCheckedChange(compoundButton, z);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_next, "field 'btNext' and method 'onViewClicked'");
        keyClampSetFragment.btNext = (Button) Utils.castView(findRequiredView4, R.id.bt_next, "field 'btNext'", Button.class);
        this.view7f0a009b = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyClampSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyClampSetFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyClampSetFragment keyClampSetFragment = this.target;
        if (keyClampSetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyClampSetFragment.rbClamp1 = null;
        keyClampSetFragment.rbClamp2 = null;
        keyClampSetFragment.rbClamp3 = null;
        keyClampSetFragment.btNext = null;
        ((CompoundButton) this.view7f0a032a).setOnCheckedChangeListener(null);
        this.view7f0a032a = null;
        ((CompoundButton) this.view7f0a032b).setOnCheckedChangeListener(null);
        this.view7f0a032b = null;
        ((CompoundButton) this.view7f0a032c).setOnCheckedChangeListener(null);
        this.view7f0a032c = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
    }
}
