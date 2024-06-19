package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutLeftGrooveFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutLeftGrooveFragment target;

    public BlankCutLeftGrooveFragment_ViewBinding(BlankCutLeftGrooveFragment blankCutLeftGrooveFragment, View view) {
        super(blankCutLeftGrooveFragment, view);
        this.target = blankCutLeftGrooveFragment;
        blankCutLeftGrooveFragment.etGrooveLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_groove_length, "field 'etGrooveLength'", EditText.class);
        blankCutLeftGrooveFragment.etRemainingThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_thickness, "field 'etRemainingThickness'", EditText.class);
        blankCutLeftGrooveFragment.etRemainingWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_remaining_width, "field 'etRemainingWidth'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutLeftGrooveFragment blankCutLeftGrooveFragment = this.target;
        if (blankCutLeftGrooveFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutLeftGrooveFragment.etGrooveLength = null;
        blankCutLeftGrooveFragment.etRemainingThickness = null;
        blankCutLeftGrooveFragment.etRemainingWidth = null;
        super.unbind();
    }
}
