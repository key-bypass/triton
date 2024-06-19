package com.kkkcut.e20j.ui.fragment.engraving;

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
public class TemplateSelectFragment_ViewBinding implements Unbinder {
    private TemplateSelectFragment target;
    private View view7f0a0084;
    private View view7f0a017d;
    private TextWatcher view7f0a017dTextWatcher;

    public TemplateSelectFragment_ViewBinding(final TemplateSelectFragment templateSelectFragment, View view) {
        this.target = templateSelectFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.et_search, "field 'etSearch' and method 'afterTextChanged'");
        templateSelectFragment.etSearch = (EditText) Utils.castView(findRequiredView, R.id.et_search, "field 'etSearch'", EditText.class);
        this.view7f0a017d = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment_ViewBinding.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                templateSelectFragment.afterTextChanged(editable);
            }
        };
        this.view7f0a017dTextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_delete_all, "field 'btDeleteAll' and method 'onViewClicked'");
        templateSelectFragment.btDeleteAll = (Button) Utils.castView(findRequiredView2, R.id.bt_delete_all, "field 'btDeleteAll'", Button.class);
        this.view7f0a0084 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.engraving.TemplateSelectFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                templateSelectFragment.onViewClicked();
            }
        });
        templateSelectFragment.rvTemplateList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_template_list, "field 'rvTemplateList'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TemplateSelectFragment templateSelectFragment = this.target;
        if (templateSelectFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        templateSelectFragment.etSearch = null;
        templateSelectFragment.btDeleteAll = null;
        templateSelectFragment.rvTemplateList = null;
        ((TextView) this.view7f0a017d).removeTextChangedListener(this.view7f0a017dTextWatcher);
        this.view7f0a017dTextWatcher = null;
        this.view7f0a017d = null;
        this.view7f0a0084.setOnClickListener(null);
        this.view7f0a0084 = null;
    }
}
