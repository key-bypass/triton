package com.kkkcut.e20j.ui.dialog;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class DecodeDialog_ViewBinding implements Unbinder {
    private DecodeDialog target;
    private View view7f0a0075;
    private View view7f0a0082;
    private View view7f0a01ef;
    private View view7f0a0231;
    private View view7f0a0232;
    private View view7f0a0323;
    private View view7f0a0326;
    private View view7f0a03aa;

    public DecodeDialog_ViewBinding(final DecodeDialog decodeDialog, View view) {
        this.target = decodeDialog;
        decodeDialog.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        decodeDialog.ivDecoder = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_decoder, "field 'ivDecoder'", ImageView.class);
        decodeDialog.tvDecoderSize = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_cutter_size, "field 'tvDecoderSize'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.sb_round, "field 'sbRound' and method 'onChecekChange'");
        decodeDialog.sbRound = (CheckBox) Utils.castView(findRequiredView, R.id.sb_round, "field 'sbRound'", CheckBox.class);
        this.view7f0a03aa = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                decodeDialog.onChecekChange(compoundButton, z);
            }
        });
        decodeDialog.tvRound = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_round, "field 'tvRound'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.bt_cancle, "field 'btCancle' and method 'onViewClicked'");
        decodeDialog.btCancle = (TextView) Utils.castView(findRequiredView2, R.id.bt_cancle, "field 'btCancle'", TextView.class);
        this.view7f0a0075 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                decodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.bt_decode, "field 'btDecode' and method 'onViewClicked'");
        decodeDialog.btDecode = (TextView) Utils.castView(findRequiredView3, R.id.bt_decode, "field 'btDecode'", TextView.class);
        this.view7f0a0082 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                decodeDialog.onViewClicked(view2);
            }
        });
        decodeDialog.tvTimeValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_time_value, "field 'tvTimeValue'", TextView.class);
        decodeDialog.llDecodeSlowly = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_decode_slowly, "field 'llDecodeSlowly'", LinearLayout.class);
        decodeDialog.tvDecodeSlowly = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_decode_slowly, "field 'tvDecodeSlowly'", TextView.class);
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rb_50, "field 'rb50' and method 'onChecekChange'");
        decodeDialog.rb50 = (RadioButton) Utils.castView(findRequiredView4, R.id.rb_50, "field 'rb50'", RadioButton.class);
        this.view7f0a0326 = findRequiredView4;
        ((CompoundButton) findRequiredView4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                decodeDialog.onChecekChange(compoundButton, z);
            }
        });
        decodeDialog.rgDecodeSize = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.rg_decode_size, "field 'rgDecodeSize'", RadioGroup.class);
        decodeDialog.tvSlantCorrection = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_slant_correction, "field 'tvSlantCorrection'", TextView.class);
        decodeDialog.cbSlantCorrection = (CheckBox) Utils.findRequiredViewAsType(view, R.id.cb_slant_correction, "field 'cbSlantCorrection'", CheckBox.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rb_100, "method 'onChecekChange'");
        this.view7f0a0323 = findRequiredView5;
        ((CompoundButton) findRequiredView5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                decodeDialog.onChecekChange(compoundButton, z);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.iv_close, "method 'onViewClicked'");
        this.view7f0a01ef = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                decodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.iv_time_reduce, "method 'onViewClicked'");
        this.view7f0a0232 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                decodeDialog.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.iv_time_add, "method 'onViewClicked'");
        this.view7f0a0231 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.dialog.DecodeDialog_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                decodeDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DecodeDialog decodeDialog = this.target;
        if (decodeDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        decodeDialog.ivClamp = null;
        decodeDialog.ivDecoder = null;
        decodeDialog.tvDecoderSize = null;
        decodeDialog.sbRound = null;
        decodeDialog.tvRound = null;
        decodeDialog.btCancle = null;
        decodeDialog.btDecode = null;
        decodeDialog.tvTimeValue = null;
        decodeDialog.llDecodeSlowly = null;
        decodeDialog.tvDecodeSlowly = null;
        decodeDialog.rb50 = null;
        decodeDialog.rgDecodeSize = null;
        decodeDialog.tvSlantCorrection = null;
        decodeDialog.cbSlantCorrection = null;
        ((CompoundButton) this.view7f0a03aa).setOnCheckedChangeListener(null);
        this.view7f0a03aa = null;
        this.view7f0a0075.setOnClickListener(null);
        this.view7f0a0075 = null;
        this.view7f0a0082.setOnClickListener(null);
        this.view7f0a0082 = null;
        ((CompoundButton) this.view7f0a0326).setOnCheckedChangeListener(null);
        this.view7f0a0326 = null;
        ((CompoundButton) this.view7f0a0323).setOnCheckedChangeListener(null);
        this.view7f0a0323 = null;
        this.view7f0a01ef.setOnClickListener(null);
        this.view7f0a01ef = null;
        this.view7f0a0232.setOnClickListener(null);
        this.view7f0a0232 = null;
        this.view7f0a0231.setOnClickListener(null);
        this.view7f0a0231 = null;
    }
}
