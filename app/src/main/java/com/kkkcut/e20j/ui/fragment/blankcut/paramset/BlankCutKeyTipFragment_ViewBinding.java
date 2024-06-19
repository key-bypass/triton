package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutKeyTipFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutKeyTipFragment target;

    public BlankCutKeyTipFragment_ViewBinding(BlankCutKeyTipFragment blankCutKeyTipFragment, View view) {
        super(blankCutKeyTipFragment, view);
        this.target = blankCutKeyTipFragment;
        blankCutKeyTipFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutKeyTipFragment.etTipWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_tip_width, "field 'etTipWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutKeyTipFragment blankCutKeyTipFragment = this.target;
        if (blankCutKeyTipFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutKeyTipFragment.etRemainingThickness = null;
        blankCutKeyTipFragment.etTipWidth = null;
        super.unbind();
    }
}
