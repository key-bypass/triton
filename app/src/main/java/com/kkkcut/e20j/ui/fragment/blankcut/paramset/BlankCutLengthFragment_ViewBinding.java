package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutLengthFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutLengthFragment target;

    public BlankCutLengthFragment_ViewBinding(BlankCutLengthFragment blankCutLengthFragment, View view) {
        super(blankCutLengthFragment, view);
        this.target = blankCutLengthFragment;
        blankCutLengthFragment.etKeyLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_key_length, "field 'etKeyLength'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutLengthFragment blankCutLengthFragment = this.target;
        if (blankCutLengthFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutLengthFragment.etKeyLength = null;
        super.unbind();
    }
}
