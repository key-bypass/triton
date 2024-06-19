package com.kkkcut.e20j.ui.fragment.customkey;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class CustomKeyListFragment_ViewBinding implements Unbinder {
    private CustomKeyListFragment target;
    private View view7f0a007f;
    private View view7f0a0097;
    private View view7f0a017d;
    private TextWatcher view7f0a017dTextWatcher;

    public CustomKeyListFragment_ViewBinding(final CustomKeyListFragment customKeyListFragment, View view) {
        this.target = customKeyListFragment;
        customKeyListFragment.rvCustomKey = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_custom_key, "field 'rvCustomKey'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_create_key, "field 'btCreateKey' and method 'onViewClicked'");
        customKeyListFragment.btCreateKey = (Button) Utils.castView(findRequiredView, R.id.bt_create_key, "field 'btCreateKey'", Button.class);
        this.view7f0a007f = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                customKeyListFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_load_from_id, "method 'onViewClicked'");
        this.view7f0a0097 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                customKeyListFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.et_search, "method 'afterTextChanged'");
        this.view7f0a017d = findRequiredView3;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment_ViewBinding.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                customKeyListFragment.afterTextChanged(editable);
            }
        };
        this.view7f0a017dTextWatcher = textWatcher;
        ((TextView) findRequiredView3).addTextChangedListener(textWatcher);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CustomKeyListFragment customKeyListFragment = this.target;
        if (customKeyListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        customKeyListFragment.rvCustomKey = null;
        customKeyListFragment.btCreateKey = null;
        this.view7f0a007f.setOnClickListener(null);
        this.view7f0a007f = null;
        this.view7f0a0097.setOnClickListener(null);
        this.view7f0a0097 = null;
        ((TextView) this.view7f0a017d).removeTextChangedListener(this.view7f0a017dTextWatcher);
        this.view7f0a017dTextWatcher = null;
        this.view7f0a017d = null;
    }
}
