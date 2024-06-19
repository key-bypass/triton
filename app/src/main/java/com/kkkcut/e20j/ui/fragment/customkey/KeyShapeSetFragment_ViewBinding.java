package com.kkkcut.e20j.ui.fragment.customkey;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyShapeSetFragment_ViewBinding implements Unbinder {
    private KeyShapeSetFragment target;
    private View view7f0a0095;
    private View view7f0a009b;
    private View view7f0a00e7;
    private View view7f0a0168;
    private View view7f0a0177;

    public KeyShapeSetFragment_ViewBinding(final KeyShapeSetFragment keyShapeSetFragment, View view) {
        this.target = keyShapeSetFragment;
        keyShapeSetFragment.ivWidth = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_width, "field 'ivWidth'", ImageView.class);
        keyShapeSetFragment.etWidth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_width, "field 'etWidth'", EditText.class);
        keyShapeSetFragment.llWidth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_width, "field 'llWidth'", LinearLayout.class);
        keyShapeSetFragment.ivThick = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_thick, "field 'ivThick'", ImageView.class);
        keyShapeSetFragment.etThickness = (EditText) Utils.findRequiredViewAsType(view, R.id.et_thickness, "field 'etThickness'", EditText.class);
        keyShapeSetFragment.llThickness = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_thickness, "field 'llThickness'", LinearLayout.class);
        keyShapeSetFragment.ivCutDepth = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_cut_depth, "field 'ivCutDepth'", ImageView.class);
        keyShapeSetFragment.etCutDepth = (EditText) Utils.findRequiredViewAsType(view, R.id.et_cut_depth, "field 'etCutDepth'", EditText.class);
        keyShapeSetFragment.llCutDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cut_depth, "field 'llCutDepth'", LinearLayout.class);
        keyShapeSetFragment.ivGroove = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_groove, "field 'ivGroove'", ImageView.class);
        keyShapeSetFragment.etGroove = (EditText) Utils.findRequiredViewAsType(view, R.id.et_groove, "field 'etGroove'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cb_extra_cut, "field 'cbExtraCut' and method 'onCheckedChanged'");
        keyShapeSetFragment.cbExtraCut = (CheckBox) Utils.castView(findRequiredView, R.id.cb_extra_cut, "field 'cbExtraCut'", CheckBox.class);
        this.view7f0a00e7 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                keyShapeSetFragment.onCheckedChanged(compoundButton, z);
            }
        });
        keyShapeSetFragment.llGroove = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_groove, "field 'llGroove'", LinearLayout.class);
        keyShapeSetFragment.ivGuide = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_guide, "field 'ivGuide'", ImageView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.et_guide, "field 'etGuide' and method 'onFocusChanged'");
        keyShapeSetFragment.etGuide = (EditText) Utils.castView(findRequiredView2, R.id.et_guide, "field 'etGuide'", EditText.class);
        this.view7f0a0168 = findRequiredView2;
        findRequiredView2.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment_ViewBinding.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                keyShapeSetFragment.onFocusChanged(view2, z);
            }
        });
        keyShapeSetFragment.llGuide = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_guide, "field 'llGuide'", LinearLayout.class);
        keyShapeSetFragment.ivNose = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_nose, "field 'ivNose'", ImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.et_nose, "field 'etNose' and method 'onFocusChanged'");
        keyShapeSetFragment.etNose = (EditText) Utils.castView(findRequiredView3, R.id.et_nose, "field 'etNose'", EditText.class);
        this.view7f0a0177 = findRequiredView3;
        findRequiredView3.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment_ViewBinding.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view2, boolean z) {
                keyShapeSetFragment.onFocusChanged(view2, z);
            }
        });
        keyShapeSetFragment.llNose = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_nose, "field 'llNose'", LinearLayout.class);
        keyShapeSetFragment.tvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'tvUnit'", TextView.class);
        keyShapeSetFragment.etAngle = (EditText) Utils.findRequiredViewAsType(view, R.id.et_angle, "field 'etAngle'", EditText.class);
        keyShapeSetFragment.llAngle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_angle, "field 'llAngle'", LinearLayout.class);
        keyShapeSetFragment.ivAngle = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_angle, "field 'ivAngle'", ImageView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.bt_last, "method 'onViewClicked'");
        this.view7f0a0095 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyShapeSetFragment.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.bt_next, "method 'onViewClicked'");
        this.view7f0a009b = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyShapeSetFragment_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyShapeSetFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyShapeSetFragment keyShapeSetFragment = this.target;
        if (keyShapeSetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyShapeSetFragment.ivWidth = null;
        keyShapeSetFragment.etWidth = null;
        keyShapeSetFragment.llWidth = null;
        keyShapeSetFragment.ivThick = null;
        keyShapeSetFragment.etThickness = null;
        keyShapeSetFragment.llThickness = null;
        keyShapeSetFragment.ivCutDepth = null;
        keyShapeSetFragment.etCutDepth = null;
        keyShapeSetFragment.llCutDepth = null;
        keyShapeSetFragment.ivGroove = null;
        keyShapeSetFragment.etGroove = null;
        keyShapeSetFragment.cbExtraCut = null;
        keyShapeSetFragment.llGroove = null;
        keyShapeSetFragment.ivGuide = null;
        keyShapeSetFragment.etGuide = null;
        keyShapeSetFragment.llGuide = null;
        keyShapeSetFragment.ivNose = null;
        keyShapeSetFragment.etNose = null;
        keyShapeSetFragment.llNose = null;
        keyShapeSetFragment.tvUnit = null;
        keyShapeSetFragment.etAngle = null;
        keyShapeSetFragment.llAngle = null;
        keyShapeSetFragment.ivAngle = null;
        ((CompoundButton) this.view7f0a00e7).setOnCheckedChangeListener(null);
        this.view7f0a00e7 = null;
        this.view7f0a0168.setOnFocusChangeListener(null);
        this.view7f0a0168 = null;
        this.view7f0a0177.setOnFocusChangeListener(null);
        this.view7f0a0177 = null;
        this.view7f0a0095.setOnClickListener(null);
        this.view7f0a0095 = null;
        this.view7f0a009b.setOnClickListener(null);
        this.view7f0a009b = null;
    }
}
