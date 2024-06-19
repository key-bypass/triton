package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DimpleDuplicateDecodeDialog_ViewBinding implements Unbinder {
    private DimpleDuplicateDecodeDialog target;
    private View view7f0a0075;
    private View view7f0a0082;
    private View view7f0a01ef;
    private View view7f0a0231;
    private View view7f0a0232;
    private View view7f0a03aa;

    public DimpleDuplicateDecodeDialog_ViewBinding(final DimpleDuplicateDecodeDialog dimpleDuplicateDecodeDialog, View view) {
        this.target = dimpleDuplicateDecodeDialog;
        dimpleDuplicateDecodeDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        dimpleDuplicateDecodeDialog.ivDecoder = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_decoder, "field 'ivDecoder'", ImageView.class);
        dimpleDuplicateDecodeDialog.tvDecoderSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvDecoderSize'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.sb_round, "field 'sbRound' and method 'onChecekChange'");
        dimpleDuplicateDecodeDialog.sbRound = (CheckBox) Utils.castView(findRequiredView, R.id.sb_round, "field 'sbRound'", CheckBox.class);
        this.view7f0a03aa = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dimpleDuplicateDecodeDialog.onChecekChange(compoundButton, z);
            }
        });
        dimpleDuplicateDecodeDialog.tvRound = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_round, "field 'tvRound'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_cancle, "field 'btCancle' and method 'onViewClicked'");
        dimpleDuplicateDecodeDialog.btCancle = (TextView) Utils.castView(findRequiredView2, R.id.bt_cancle, "field 'btCancle'", TextView.class);
        this.view7f0a0075 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateDecodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_decode, "field 'btDecode' and method 'onViewClicked'");
        dimpleDuplicateDecodeDialog.btDecode = (TextView) Utils.castView(findRequiredView3, R.id.bt_decode, "field 'btDecode'", TextView.class);
        this.view7f0a0082 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateDecodeDialog.onViewClicked(view2);
            }
        });
        dimpleDuplicateDecodeDialog.tvTimeValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_time_value, "field 'tvTimeValue'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.iv_close, "method 'onViewClicked'");
        this.view7f0a01ef = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateDecodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, R.id.iv_time_reduce, "method 'onViewClicked'");
        this.view7f0a0232 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateDecodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_time_add, "method 'onViewClicked'");
        this.view7f0a0231 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.duplicatekey.dimple.DimpleDuplicateDecodeDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dimpleDuplicateDecodeDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DimpleDuplicateDecodeDialog dimpleDuplicateDecodeDialog = this.target;
        if (dimpleDuplicateDecodeDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dimpleDuplicateDecodeDialog.ivClamp = null;
        dimpleDuplicateDecodeDialog.ivDecoder = null;
        dimpleDuplicateDecodeDialog.tvDecoderSize = null;
        dimpleDuplicateDecodeDialog.sbRound = null;
        dimpleDuplicateDecodeDialog.tvRound = null;
        dimpleDuplicateDecodeDialog.btCancle = null;
        dimpleDuplicateDecodeDialog.btDecode = null;
        dimpleDuplicateDecodeDialog.tvTimeValue = null;
        ((CompoundButton) this.view7f0a03aa).setOnCheckedChangeListener(null);
        this.view7f0a03aa = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a0082.setOnClickListener(null);
        this.view7f0a0082 = null;
        this.view7f0a01ef.setOnClickListener(null);
        this.view7f0a01ef = null;
        this.view7f0a0232.setOnClickListener(null);
        this.view7f0a0232 = null;
        this.view7f0a0231.setOnClickListener(null);
        this.view7f0a0231 = null;
    }
}
