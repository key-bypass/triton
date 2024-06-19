package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyInfoFragment_ViewBinding implements Unbinder {
    private KeyInfoFragment target;
    private View view7f0a0212;

    public KeyInfoFragment_ViewBinding(final KeyInfoFragment keyInfoFragment, View view) {
        this.target = keyInfoFragment;
        keyInfoFragment.tvDetail = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_detail, "field 'tvDetail'", TextView.class);
        keyInfoFragment.tvKeyNumbering = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_numbering, "field 'tvKeyNumbering'", TextView.class);
        keyInfoFragment.tvAlign = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_align, "field 'tvAlign'", TextView.class);
        keyInfoFragment.ivClamp = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_clamp, "field 'ivClamp'", ImageView.class);
        keyInfoFragment.ivRealKey = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_real_key, "field 'ivRealKey'", ImageView.class);
        keyInfoFragment.llKeyBlanks = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_key_blanks, "field 'llKeyBlanks'", LinearLayout.class);
        keyInfoFragment.tvKeySeries = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_series, "field 'tvKeySeries'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_play_video, "field 'ivPlayVideo' and method 'onViewClicked'");
        keyInfoFragment.ivPlayVideo = (ImageView) Utils.castView(findRequiredView, R.id.iv_play_video, "field 'ivPlayVideo'", ImageView.class);
        this.view7f0a0212 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.kkkcut.e20j.ui.fragment.KeyInfoFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                keyInfoFragment.onViewClicked();
            }
        });
        keyInfoFragment.webviewDescription = (WebView) Utils.findRequiredViewAsType(view, R.id.webview_description, "field 'webviewDescription'", WebView.class);
        keyInfoFragment.tvQrcode = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_qrcode, "field 'tvQrcode'", TextView.class);
        keyInfoFragment.ivQrcode = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_qrcode, "field 'ivQrcode'", ImageView.class);
        keyInfoFragment.tvKeyWidth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_width, "field 'tvKeyWidth'", TextView.class);
        keyInfoFragment.llSpace = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_space, "field 'llSpace'", LinearLayout.class);
        keyInfoFragment.llIndex = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_index, "field 'llIndex'", LinearLayout.class);
        keyInfoFragment.llDepth = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_depth, "field 'llDepth'", LinearLayout.class);
        keyInfoFragment.tvKeyThickness = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_key_thickness, "field 'tvKeyThickness'", TextView.class);
        keyInfoFragment.tvTitleThickness = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_thickness, "field 'tvTitleThickness'", TextView.class);
        keyInfoFragment.tvTitleSpace = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_space, "field 'tvTitleSpace'", TextView.class);
        keyInfoFragment.tvTitleDepth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_depth, "field 'tvTitleDepth'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        KeyInfoFragment keyInfoFragment = this.target;
        if (keyInfoFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        keyInfoFragment.tvDetail = null;
        keyInfoFragment.tvKeyNumbering = null;
        keyInfoFragment.tvAlign = null;
        keyInfoFragment.ivClamp = null;
        keyInfoFragment.ivRealKey = null;
        keyInfoFragment.llKeyBlanks = null;
        keyInfoFragment.tvKeySeries = null;
        keyInfoFragment.ivPlayVideo = null;
        keyInfoFragment.webviewDescription = null;
        keyInfoFragment.tvQrcode = null;
        keyInfoFragment.ivQrcode = null;
        keyInfoFragment.tvKeyWidth = null;
        keyInfoFragment.llSpace = null;
        keyInfoFragment.llIndex = null;
        keyInfoFragment.llDepth = null;
        keyInfoFragment.tvKeyThickness = null;
        keyInfoFragment.tvTitleThickness = null;
        keyInfoFragment.tvTitleSpace = null;
        keyInfoFragment.tvTitleDepth = null;
        this.view7f0a0212.setOnClickListener(null);
        this.view7f0a0212 = null;
    }
}
