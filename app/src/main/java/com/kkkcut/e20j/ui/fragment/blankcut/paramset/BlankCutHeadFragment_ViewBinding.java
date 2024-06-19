package com.kkkcut.e20j.ui.fragment.blankcut.paramset;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class BlankCutHeadFragment_ViewBinding implements Unbinder {
    private BlankCutHeadFragment target;
    private View view7f0a0081;

    public BlankCutHeadFragment_ViewBinding(final BlankCutHeadFragment blankCutHeadFragment, View view) {
        this.target = blankCutHeadFragment;
        blankCutHeadFragment.etHeadWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_head_width, "field 'etHeadWidth'", EditText.class);
        blankCutHeadFragment.etHeadLength = (EditText) Utils.findRequiredViewAsType(view, R.id.et_head_length, "field 'etHeadLength'", EditText.class);
        blankCutHeadFragment.etHeadThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_head_thickness, "field 'etHeadThickness'", EditText.class);
        blankCutHeadFragment.etHeadGrooveLocation = (EditText) Utils.findRequiredViewAsType(view, R.id.et_head_groove_location, "field 'etHeadGrooveLocation'", EditText.class);
        blankCutHeadFragment.etHeadGrooveThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_head_groove_thickness, "field 'etHeadGrooveThickness'", EditText.class);
        blankCutHeadFragment.blankCutThickness = (ViewStub) Utils.findRequiredViewAsType(view, R.id.blank_cut_thickness, "field 'blankCutThickness'", ViewStub.class);
        blankCutHeadFragment.blankCutWidth = (ViewStub) Utils.findRequiredViewAsType(view, R.id.blank_cut_width, "field 'blankCutWidth'", ViewStub.class);
        blankCutHeadFragment.blankCutHead = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.blank_cut_head, "field 'blankCutHead'", ViewGroup.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_cut, "method 'onClick'");
        this.view7f0a0081 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.paramset.BlankCutHeadFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                blankCutHeadFragment.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BlankCutHeadFragment blankCutHeadFragment = this.target;
        if (blankCutHeadFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        blankCutHeadFragment.etHeadWidth = null;
        blankCutHeadFragment.etHeadLength = null;
        blankCutHeadFragment.etHeadThickness = null;
        blankCutHeadFragment.etHeadGrooveLocation = null;
        blankCutHeadFragment.etHeadGrooveThickness = null;
        blankCutHeadFragment.blankCutThickness = null;
        blankCutHeadFragment.blankCutWidth = null;
        blankCutHeadFragment.blankCutHead = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
    }
}
