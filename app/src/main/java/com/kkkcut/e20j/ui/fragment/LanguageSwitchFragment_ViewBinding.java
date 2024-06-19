package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class LanguageSwitchFragment_ViewBinding implements Unbinder {
    private LanguageSwitchFragment target;

    public LanguageSwitchFragment_ViewBinding(LanguageSwitchFragment languageSwitchFragment, View view) {
        this.target = languageSwitchFragment;
        languageSwitchFragment.rvLanguage = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_language, "field 'rvLanguage'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        LanguageSwitchFragment languageSwitchFragment = this.target;
        if (languageSwitchFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        languageSwitchFragment.rvLanguage = null;
    }
}
