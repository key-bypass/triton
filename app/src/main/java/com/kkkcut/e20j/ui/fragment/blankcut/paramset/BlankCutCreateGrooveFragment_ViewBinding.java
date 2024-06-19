package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutCreateGrooveFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutCreateGrooveFragment target;

    public BlankCutCreateGrooveFragment_ViewBinding(BlankCutCreateGrooveFragment blankCutCreateGrooveFragment, View view) {
        super(blankCutCreateGrooveFragment, view);
        this.target = blankCutCreateGrooveFragment;
        blankCutCreateGrooveFragment.etGrooveLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_groove_length, "field 'etGrooveLength'", EditText.class);
        blankCutCreateGrooveFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutCreateGrooveFragment.etLeftWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_left_width, "field 'etLeftWidth'", EditText.class);
        blankCutCreateGrooveFragment.etRightWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_right_width, "field 'etRightWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutCreateGrooveFragment blankCutCreateGrooveFragment = this.target;
        if (blankCutCreateGrooveFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutCreateGrooveFragment.etGrooveLength = null;
        blankCutCreateGrooveFragment.etRemainingThickness = null;
        blankCutCreateGrooveFragment.etLeftWidth = null;
        blankCutCreateGrooveFragment.etRightWidth = null;
        super.unbind();
    }
}
