package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutRightGrooveFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutRightGrooveFragment target;

    public BlankCutRightGrooveFragment_ViewBinding(BlankCutRightGrooveFragment blankCutRightGrooveFragment, View view) {
        super(blankCutRightGrooveFragment, view);
        this.target = blankCutRightGrooveFragment;
        blankCutRightGrooveFragment.etGrooveLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_groove_length, "field 'etGrooveLength'", EditText.class);
        blankCutRightGrooveFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutRightGrooveFragment.etRemainingWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_width, "field 'etRemainingWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutRightGrooveFragment blankCutRightGrooveFragment = this.target;
        if (blankCutRightGrooveFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutRightGrooveFragment.etGrooveLength = null;
        blankCutRightGrooveFragment.etRemainingThickness = null;
        blankCutRightGrooveFragment.etRemainingWidth = null;
        super.unbind();
    }
}
