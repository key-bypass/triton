package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutSideGrooveFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutSideGrooveFragment target;

    public BlankCutSideGrooveFragment_ViewBinding(BlankCutSideGrooveFragment blankCutSideGrooveFragment, View view) {
        super(blankCutSideGrooveFragment, view);
        this.target = blankCutSideGrooveFragment;
        blankCutSideGrooveFragment.etGrooveLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_groove_length, "field 'etGrooveLength'", EditText.class);
        blankCutSideGrooveFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutSideGrooveFragment.etGrooveWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_side_groove_width, "field 'etGrooveWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutSideGrooveFragment blankCutSideGrooveFragment = this.target;
        if (blankCutSideGrooveFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutSideGrooveFragment.etGrooveLength = null;
        blankCutSideGrooveFragment.etRemainingThickness = null;
        blankCutSideGrooveFragment.etGrooveWidth = null;
        super.unbind();
    }
}
