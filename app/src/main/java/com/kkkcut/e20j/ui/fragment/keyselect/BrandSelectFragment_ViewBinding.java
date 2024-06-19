package com.kkkcut.e20j.ui.fragment.keyselect;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BrandSelectFragment_ViewBinding implements Unbinder {
    private BrandSelectFragment target;
    private View view7f0a017d;
    private TextWatcher view7f0a017dTextWatcher;

    public BrandSelectFragment_ViewBinding(final BrandSelectFragment brandSelectFragment, View view) {
        this.target = brandSelectFragment;
        brandSelectFragment.rvCategoryList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_category_list, "field 'rvCategoryList'", RecyclerView.class);
        brandSelectFragment.indexBar = (IndexBar) Utils.findRequiredViewAsType(view, R.id.indexBar, "field 'indexBar'", IndexBar.class);
        brandSelectFragment.tvSideBarHint = (TextView) Utils.findRequiredViewAsType(view, R.id.tvSideBarHint, "field 'tvSideBarHint'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.et_search, "field 'etSearch' and method 'afterTextChanged'");
        brandSelectFragment.etSearch = (EditText) Utils.castView(findRequiredView, R.id.et_search, "field 'etSearch'", EditText.class);
        this.view7f0a017d = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment_ViewBinding.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                brandSelectFragment.afterTextChanged(editable);
            }
        };
        this.view7f0a017dTextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BrandSelectFragment brandSelectFragment = this.target;
        if (brandSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        brandSelectFragment.rvCategoryList = null;
        brandSelectFragment.indexBar = null;
        brandSelectFragment.tvSideBarHint = null;
        brandSelectFragment.etSearch = null;
        ((TextView) this.view7f0a017d).removeTextChangedListener(this.view7f0a017dTextWatcher);
        this.view7f0a017dTextWatcher = null;
        this.view7f0a017d = null;
    }
}
