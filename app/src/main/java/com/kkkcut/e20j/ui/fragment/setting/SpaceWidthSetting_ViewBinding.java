package com.kkkcut.e20j.ui.fragment.setting;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class SpaceWidthSetting_ViewBinding implements Unbinder {
    private SpaceWidthSetting target;
    private View view7f0a00b1;

    public SpaceWidthSetting_ViewBinding(final SpaceWidthSetting spaceWidthSetting, View view) {
        this.target = spaceWidthSetting;
        spaceWidthSetting.etSingleKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_single_key, "field 'etSingleKey'", EditText.class);
        spaceWidthSetting.etDoubleKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_double_key, "field 'etDoubleKey'", EditText.class);
        spaceWidthSetting.etSingleInsideKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_single_inside_key, "field 'etSingleInsideKey'", EditText.class);
        spaceWidthSetting.etSingleOutsideKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_single_outside_key, "field 'etSingleOutsideKey'", EditText.class);
        spaceWidthSetting.etDoubleInsideKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_double_inside_key, "field 'etDoubleInsideKey'", EditText.class);
        spaceWidthSetting.etDoubleOutsideKey = (EditText) Utils.findRequiredViewAsType(view, R.id.et_double_outside_key, "field 'etDoubleOutsideKey'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bt_save, "method 'onViewClicked'");
        this.view7f0a00b1 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.setting.SpaceWidthSetting_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                spaceWidthSetting.onViewClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SpaceWidthSetting spaceWidthSetting = this.target;
        if (spaceWidthSetting == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        spaceWidthSetting.etSingleKey = null;
        spaceWidthSetting.etDoubleKey = null;
        spaceWidthSetting.etSingleInsideKey = null;
        spaceWidthSetting.etSingleOutsideKey = null;
        spaceWidthSetting.etDoubleInsideKey = null;
        spaceWidthSetting.etDoubleOutsideKey = null;
        this.view7f0a00b1.setOnClickListener(null);
        this.view7f0a00b1 = null;
    }
}
