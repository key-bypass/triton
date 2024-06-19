package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.EditText;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutDrillingFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutDrillingFragment target;

    public BlankCutDrillingFragment_ViewBinding(BlankCutDrillingFragment blankCutDrillingFragment, View view) {
        super(blankCutDrillingFragment, view);
        this.target = blankCutDrillingFragment;
        blankCutDrillingFragment.etDiameter = (EditText) Utils.findRequiredViewAsType(view, R.id.et_diameter, "field 'etDiameter'", EditText.class);
        blankCutDrillingFragment.etDistance = (EditText) Utils.findRequiredViewAsType(view, R.id.et_distance, "field 'etDistance'", EditText.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutDrillingFragment blankCutDrillingFragment = this.target;
        if (blankCutDrillingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutDrillingFragment.etDiameter = null;
        blankCutDrillingFragment.etDistance = null;
        super.unbind();
    }
}
