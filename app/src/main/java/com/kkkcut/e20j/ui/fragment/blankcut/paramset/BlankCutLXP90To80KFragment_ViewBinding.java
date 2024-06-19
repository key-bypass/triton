package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutLXP90To80KFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutLXP90To80KFragment target;

    public BlankCutLXP90To80KFragment_ViewBinding(BlankCutLXP90To80KFragment blankCutLXP90To80KFragment, View view) {
        super(blankCutLXP90To80KFragment, view);
        this.target = blankCutLXP90To80KFragment;
        blankCutLXP90To80KFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutLXP90To80KFragment.etTipWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_tip_width, "field 'etTipWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutLXP90To80KFragment blankCutLXP90To80KFragment = this.target;
        if (blankCutLXP90To80KFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutLXP90To80KFragment.etRemainingThickness = null;
        blankCutLXP90To80KFragment.etTipWidth = null;
        super.unbind();
    }
}
