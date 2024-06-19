package com.kkkcut.e20j.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class RegisterActivity_ViewBinding implements Unbinder {
    private RegisterActivity target;
    private View view7f0a0070;
    private View view7f0a0179;
    private TextWatcher view7f0a0179TextWatcher;
    private View view7f0a0182;
    private TextWatcher view7f0a0182TextWatcher;

    public RegisterActivity_ViewBinding(RegisterActivity registerActivity) {
        this(registerActivity, registerActivity.getWindow().getDecorView());
    }

    public RegisterActivity_ViewBinding(final RegisterActivity registerActivity, View view) {
        this.target = registerActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.et_sn, "field 'etSn' and method 'onSnChanged'");
        registerActivity.etSn = (EditText) Utils.castView(findRequiredView, R.id.et_sn, "field 'etSn'", EditText.class);
        this.view7f0a0182 = findRequiredView;
        TextWatcher textWatcher = new TextWatcher() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity_ViewBinding.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                registerActivity.onSnChanged((CharSequence) Utils.castParam(editable, "afterTextChanged", 0, "onSnChanged", 0, CharSequence.class));
            }
        };
        this.view7f0a0182TextWatcher = textWatcher;
        ((TextView) findRequiredView).addTextChangedListener(textWatcher);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.et_regCode, "field 'etRegCode' and method 'onRegCodeChanged'");
        registerActivity.etRegCode = (EditText) Utils.castView(findRequiredView2, R.id.et_regCode, "field 'etRegCode'", EditText.class);
        this.view7f0a0179 = findRequiredView2;
        TextWatcher textWatcher2 = new TextWatcher() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity_ViewBinding.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                registerActivity.onRegCodeChanged((CharSequence) Utils.castParam(editable, "afterTextChanged", 0, "onRegCodeChanged", 0, CharSequence.class));
            }
        };
        this.view7f0a0179TextWatcher = textWatcher2;
        ((TextView) findRequiredView2).addTextChangedListener(textWatcher2);
        registerActivity.tvCpu = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cpu, "field 'tvCpu'", TextView.class);
        registerActivity.tvSn = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sn, "field 'tvSn'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_activate, "method 'onViewClicked'");
        this.view7f0a0070 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.activity.RegisterActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                registerActivity.onViewClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RegisterActivity registerActivity = this.target;
        if (registerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        registerActivity.etSn = null;
        registerActivity.etRegCode = null;
        registerActivity.tvCpu = null;
        registerActivity.tvSn = null;
        ((TextView) this.view7f0a0182).removeTextChangedListener(this.view7f0a0182TextWatcher);
        this.view7f0a0182TextWatcher = null;
        this.view7f0a0182 = null;
        ((TextView) this.view7f0a0179).removeTextChangedListener(this.view7f0a0179TextWatcher);
        this.view7f0a0179TextWatcher = null;
        this.view7f0a0179 = null;
        this.view7f0a0070.setOnClickListener(null);
        this.view7f0a0070 = null;
    }
}
