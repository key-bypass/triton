package com.kkkcut.e20j.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class FavoriteFragment_ViewBinding implements Unbinder {
    private FavoriteFragment target;
    private View view7f0a0084;
    private View view7f0a008c;
    private View view7f0a017d;
    private TextWatcher view7f0a017dTextWatcher;

    public FavoriteFragment_ViewBinding(final FavoriteFragment favoriteFragment, View view) {
        this.target = favoriteFragment;
        favoriteFragment.rvUserData = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_user_data, "field 'rvUserData'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.et_search, "field 'etSearch' and method 'afterTextChanged'");
        favoriteFragment.etSearch = (EditText) Utils.castView(findRequiredView, R.id.et_search, "field 'etSearch'", EditText.class);
        this.view7f0a017d = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment_ViewBinding.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                favoriteFragment.afterTextChanged(editable);
            }
        };
        this.view7f0a017dTextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_get_test_data, "field 'btGetTestData' and method 'onViewClicked'");
        favoriteFragment.btGetTestData = (Button) Utils.castView(findRequiredView2, R.id.bt_get_test_data, "field 'btGetTestData'", Button.class);
        this.view7f0a008c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                favoriteFragment.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_delete_all, "method 'onViewClicked'");
        this.view7f0a0084 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.FavoriteFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                favoriteFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        FavoriteFragment favoriteFragment = this.target;
        if (favoriteFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        favoriteFragment.rvUserData = null;
        favoriteFragment.etSearch = null;
        favoriteFragment.btGetTestData = null;
        ((TextView) this.view7f0a017d).removeTextChangedListener(this.view7f0a017dTextWatcher);
        this.view7f0a017dTextWatcher = null;
        this.view7f0a017d = null;
        this.view7f0a008c.setOnClickListener(null);
        this.view7f0a008c = null;
        this.view7f0a0084.setOnClickListener(null);
        this.view7f0a0084 = null;
    }
}
