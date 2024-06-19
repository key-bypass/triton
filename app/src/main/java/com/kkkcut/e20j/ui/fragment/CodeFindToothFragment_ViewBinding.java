package com.kkkcut.e20j.ui.fragment;

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
public class CodeFindToothFragment_ViewBinding implements Unbinder {
    private CodeFindToothFragment target;
    private View view7f0a00b5;
    private View view7f0a00b6;

    public CodeFindToothFragment_ViewBinding(final CodeFindToothFragment codeFindToothFragment, View view) {
        this.target = codeFindToothFragment;
        codeFindToothFragment.etSearch = (EditText) Utils.findRequiredViewAsType(view, R.id.et_search, "field 'etSearch'", EditText.class);
        codeFindToothFragment.tvSeries = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_series, "field 'tvSeries'", TextView.class);
        codeFindToothFragment.rvToothList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_tooth_list, "field 'rvToothList'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_search_online, "field 'btSearchOnline' and method 'onViewClicked'");
        codeFindToothFragment.btSearchOnline = (Button) Utils.castView(findRequiredView, R.id.bt_search_online, "field 'btSearchOnline'", Button.class);
        this.view7f0a00b6 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                codeFindToothFragment.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_search_offline, "method 'onViewClicked'");
        this.view7f0a00b5 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.CodeFindToothFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                codeFindToothFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CodeFindToothFragment codeFindToothFragment = this.target;
        if (codeFindToothFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        codeFindToothFragment.etSearch = null;
        codeFindToothFragment.tvSeries = null;
        codeFindToothFragment.rvToothList = null;
        codeFindToothFragment.btSearchOnline = null;
        this.view7f0a00b6.setOnClickListener(null);
        this.view7f0a00b6 = null;
        this.view7f0a00b5.setOnClickListener(null);
        this.view7f0a00b5 = null;
    }
}
