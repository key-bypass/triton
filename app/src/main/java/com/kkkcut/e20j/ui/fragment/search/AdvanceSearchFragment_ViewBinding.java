package com.kkkcut.e20j.ui.fragment.search;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.customView.searchSpinner.SearchableSpinner;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class AdvanceSearchFragment_ViewBinding implements Unbinder {
    private AdvanceSearchFragment target;
    private View view7f0a007b;
    private View view7f0a00b4;
    private View view7f0a01ea;
    private View view7f0a0204;
    private View view7f0a020b;
    private View view7f0a020c;

    public AdvanceSearchFragment_ViewBinding(final AdvanceSearchFragment advanceSearchFragment, View view) {
        this.target = advanceSearchFragment;
        advanceSearchFragment.spinnerCard = (SearchableSpinner) Utils.findRequiredViewAsType(view, R.id.spinner_card, "field 'spinnerCard'", SearchableSpinner.class);
        advanceSearchFragment.etCard = (EditText) Utils.findRequiredViewAsType(view, R.id.et_card, "field 'etCard'", EditText.class);
        advanceSearchFragment.etKeyBlank = (EditText) Utils.findRequiredViewAsType(view, R.id.et_key_blank, "field 'etKeyBlank'", EditText.class);
        advanceSearchFragment.spinnerKeyManufacturer = (SearchableSpinner) Utils.findRequiredViewAsType(view, R.id.spinner_key_manufacturer, "field 'spinnerKeyManufacturer'", SearchableSpinner.class);
        advanceSearchFragment.etLockManufacturer = (EditText) Utils.findRequiredViewAsType(view, R.id.et_lock_manufacturer, "field 'etLockManufacturer'", EditText.class);
        advanceSearchFragment.etLockSystem = (EditText) Utils.findRequiredViewAsType(view, R.id.et_lock_system, "field 'etLockSystem'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_card, "method 'onClick'");
        this.view7f0a01ea = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.iv_key_blank, "method 'onClick'");
        this.view7f0a0204 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.iv_lock_manu, "method 'onClick'");
        this.view7f0a020b = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_lock_system, "method 'onClick'");
        this.view7f0a020c = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_clear, "method 'onClick'");
        this.view7f0a007b = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.bt_search, "method 'onClick'");
        this.view7f0a00b4 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                advanceSearchFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AdvanceSearchFragment advanceSearchFragment = this.target;
        if (advanceSearchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        advanceSearchFragment.spinnerCard = null;
        advanceSearchFragment.etCard = null;
        advanceSearchFragment.etKeyBlank = null;
        advanceSearchFragment.spinnerKeyManufacturer = null;
        advanceSearchFragment.etLockManufacturer = null;
        advanceSearchFragment.etLockSystem = null;
        this.view7f0a01ea.setOnClickListener(null);
        this.view7f0a01ea = null;
        this.view7f0a0204.setOnClickListener(null);
        this.view7f0a0204 = null;
        this.view7f0a020b.setOnClickListener(null);
        this.view7f0a020b = null;
        this.view7f0a020c.setOnClickListener(null);
        this.view7f0a020c = null;
        this.view7f0a007b.setOnClickListener(null);
        this.view7f0a007b = null;
        this.view7f0a00b4.setOnClickListener(null);
        this.view7f0a00b4 = null;
    }
}
