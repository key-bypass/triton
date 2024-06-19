package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.internal.Utils;
import com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutThicknessFragment_ViewBinding extends BaseBlankCutParamSetFragment_ViewBinding {
    private BlankCutThicknessFragment target;

    public BlankCutThicknessFragment_ViewBinding(BlankCutThicknessFragment blankCutThicknessFragment, View view) {
        super(blankCutThicknessFragment, view);
        this.target = blankCutThicknessFragment;
        blankCutThicknessFragment.etThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_key_thickness, "field 'etThickness'", EditText.class);
        blankCutThicknessFragment.cbCutMoreThick = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_cut_more_thick, "field 'cbCutMoreThick'", CheckBox.class);
        blankCutThicknessFragment.ivWidth = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_width, "field 'ivWidth'", ImageView.class);
    }

    @Override // com.kkkcut.e20j.ui.fragment.blankcut.paramset.base.BaseBlankCutParamSetFragment_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BlankCutThicknessFragment blankCutThicknessFragment = this.target;
        if (blankCutThicknessFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutThicknessFragment.etThickness = null;
        blankCutThicknessFragment.cbCutMoreThick = null;
        blankCutThicknessFragment.ivWidth = null;
        super.unbind();
    }
}
