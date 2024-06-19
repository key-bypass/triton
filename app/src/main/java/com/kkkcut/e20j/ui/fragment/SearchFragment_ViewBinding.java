package com.kkkcut.e20j.ui.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SearchFragment_ViewBinding implements Unbinder {
    private SearchFragment target;
    private View view7f0a017d;
    private TextWatcher view7f0a017dTextWatcher;
    private View view7f0a0380;
    private View view7f0a0381;
    private View view7f0a0382;
    private View view7f0a0385;
    private View view7f0a0386;
    private View view7f0a0387;
    private View view7f0a0388;
    private View view7f0a038b;

    public SearchFragment_ViewBinding(final SearchFragment searchFragment, View view) {
        this.target = searchFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.et_search, "field 'etSearch' and method 'afterTextChanged'");
        searchFragment.etSearch = (EditText) Utils.castView(findRequiredView, R.id.et_search, "field 'etSearch'", EditText.class);
        this.view7f0a017d = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                searchFragment.afterTextChanged(editable);
            }
        };
        this.view7f0a017dTextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
        searchFragment.tvKeyBlank = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_blank, "field 'tvKeyBlank'", TextView.class);
        searchFragment.flagKeyBlank = Utils.findRequiredView(view, R.id.flag_key_blank, "field 'flagKeyBlank'");
        View findRequiredView2 = Utils.findRequiredView(view, R.id.rl_key_blank, "field 'rlKeyBlank' and method 'onViewClicked'");
        searchFragment.rlKeyBlank = (RelativeLayout) Utils.castView(findRequiredView2, R.id.rl_key_blank, "field 'rlKeyBlank'", RelativeLayout.class);
        this.view7f0a0386 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvKeyId = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_id, "field 'tvKeyId'", TextView.class);
        searchFragment.flagKeyId = Utils.findRequiredView(view, R.id.flag_key_id, "field 'flagKeyId'");
        View findRequiredView3 = Utils.findRequiredView(view, R.id.rl_key_id, "field 'rlKeyId' and method 'onViewClicked'");
        searchFragment.rlKeyId = (RelativeLayout) Utils.castView(findRequiredView3, R.id.rl_key_id, "field 'rlKeyId'", RelativeLayout.class);
        this.view7f0a0387 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvChinaKeyNum = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_china_key_num, "field 'tvChinaKeyNum'", TextView.class);
        searchFragment.flagChinaKey = Utils.findRequiredView(view, R.id.flag_china_key, "field 'flagChinaKey'");
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rl_china_key_num, "field 'rlChinaKeyNum' and method 'onViewClicked'");
        searchFragment.rlChinaKeyNum = (RelativeLayout) Utils.castView(findRequiredView4, R.id.rl_china_key_num, "field 'rlChinaKeyNum'", RelativeLayout.class);
        this.view7f0a0382 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvBlitzCard = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_blitz_card, "field 'tvBlitzCard'", TextView.class);
        searchFragment.flagBlitzCard = Utils.findRequiredView(view, R.id.flag_blitz_card, "field 'flagBlitzCard'");
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rl_blitz_card, "field 'rlBlitzCard' and method 'onViewClicked'");
        searchFragment.rlBlitzCard = (RelativeLayout) Utils.castView(findRequiredView5, R.id.rl_blitz_card, "field 'rlBlitzCard'", RelativeLayout.class);
        this.view7f0a0381 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvDsd = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dsd, "field 'tvDsd'", TextView.class);
        searchFragment.flagDsd = Utils.findRequiredView(view, R.id.flag_dsd, "field 'flagDsd'");
        View findRequiredView6 = Utils.findRequiredView(view, R.id.rl_dsd, "field 'rlDsd' and method 'onViewClicked'");
        searchFragment.rlDsd = (RelativeLayout) Utils.castView(findRequiredView6, R.id.rl_dsd, "field 'rlDsd'", RelativeLayout.class);
        this.view7f0a0385 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvLkp = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_lkp, "field 'tvLkp'", TextView.class);
        searchFragment.flagLkp = Utils.findRequiredView(view, R.id.flag_lkp, "field 'flagLkp'");
        View findRequiredView7 = Utils.findRequiredView(view, R.id.rl_lkp, "field 'rlLkp' and method 'onViewClicked'");
        searchFragment.rlLkp = (RelativeLayout) Utils.castView(findRequiredView7, R.id.rl_lkp, "field 'rlLkp'", RelativeLayout.class);
        this.view7f0a0388 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvSilca = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_silca, "field 'tvSilca'", TextView.class);
        searchFragment.flagSilca = Utils.findRequiredView(view, R.id.flag_silca, "field 'flagSilca'");
        View findRequiredView8 = Utils.findRequiredView(view, R.id.rl_silca, "field 'rlSilca' and method 'onViewClicked'");
        searchFragment.rlSilca = (RelativeLayout) Utils.castView(findRequiredView8, R.id.rl_silca, "field 'rlSilca'", RelativeLayout.class);
        this.view7f0a038b = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.tvBarCode = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_bar_code, "field 'tvBarCode'", TextView.class);
        searchFragment.flagBarCode = Utils.findRequiredView(view, R.id.flag_bar_code, "field 'flagBarCode'");
        View findRequiredView9 = Utils.findRequiredView(view, R.id.rl_bar_code, "field 'rlBarCode' and method 'onViewClicked'");
        searchFragment.rlBarCode = (RelativeLayout) Utils.castView(findRequiredView9, R.id.rl_bar_code, "field 'rlBarCode'", RelativeLayout.class);
        this.view7f0a0380 = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.SearchFragment_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                searchFragment.onViewClicked(view2);
            }
        });
        searchFragment.rvResult = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_result, "field 'rvResult'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SearchFragment searchFragment = this.target;
        if (searchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchFragment.etSearch = null;
        searchFragment.tvKeyBlank = null;
        searchFragment.flagKeyBlank = null;
        searchFragment.rlKeyBlank = null;
        searchFragment.tvKeyId = null;
        searchFragment.flagKeyId = null;
        searchFragment.rlKeyId = null;
        searchFragment.tvChinaKeyNum = null;
        searchFragment.flagChinaKey = null;
        searchFragment.rlChinaKeyNum = null;
        searchFragment.tvBlitzCard = null;
        searchFragment.flagBlitzCard = null;
        searchFragment.rlBlitzCard = null;
        searchFragment.tvDsd = null;
        searchFragment.flagDsd = null;
        searchFragment.rlDsd = null;
        searchFragment.tvLkp = null;
        searchFragment.flagLkp = null;
        searchFragment.rlLkp = null;
        searchFragment.tvSilca = null;
        searchFragment.flagSilca = null;
        searchFragment.rlSilca = null;
        searchFragment.tvBarCode = null;
        searchFragment.flagBarCode = null;
        searchFragment.rlBarCode = null;
        searchFragment.rvResult = null;
        ((TextView) this.view7f0a017d).removeTextChangedListener(this.view7f0a017dTextWatcher);
        this.view7f0a017dTextWatcher = null;
        this.view7f0a017d = null;
        this.view7f0a0386.setOnClickListener(null);
        this.view7f0a0386 = null;
        this.view7f0a0387.setOnClickListener(null);
        this.view7f0a0387 = null;
        this.view7f0a0382.setOnClickListener(null);
        this.view7f0a0382 = null;
        this.view7f0a0381.setOnClickListener(null);
        this.view7f0a0381 = null;
        this.view7f0a0385.setOnClickListener(null);
        this.view7f0a0385 = null;
        this.view7f0a0388.setOnClickListener(null);
        this.view7f0a0388 = null;
        this.view7f0a038b.setOnClickListener(null);
        this.view7f0a038b = null;
        this.view7f0a0380.setOnClickListener(null);
        this.view7f0a0380 = null;
    }
}
