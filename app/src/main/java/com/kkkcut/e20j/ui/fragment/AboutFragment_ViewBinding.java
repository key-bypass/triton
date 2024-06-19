package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class AboutFragment_ViewBinding implements Unbinder {
    private AboutFragment target;

    public AboutFragment_ViewBinding(AboutFragment aboutFragment, View view) {
        this.target = aboutFragment;
        aboutFragment.tvSoftVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_soft_version, "field 'tvSoftVersion'", TextView.class);
        aboutFragment.tvCompany = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_company, "field 'tvCompany'", TextView.class);
        aboutFragment.tvFirmware = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_firmware, "field 'tvFirmware'", TextView.class);
        aboutFragment.tvSerial = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_serial, "field 'tvSerial'", TextView.class);
        aboutFragment.tvDbVersion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_db_version, "field 'tvDbVersion'", TextView.class);
        aboutFragment.tvModelName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_model_name, "field 'tvModelName'", TextView.class);
        aboutFragment.tvUpdateLog = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_update_log, "field 'tvUpdateLog'", TextView.class);
        aboutFragment.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'webView'", WebView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AboutFragment aboutFragment = this.target;
        if (aboutFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        aboutFragment.tvSoftVersion = null;
        aboutFragment.tvCompany = null;
        aboutFragment.tvFirmware = null;
        aboutFragment.tvSerial = null;
        aboutFragment.tvDbVersion = null;
        aboutFragment.tvModelName = null;
        aboutFragment.tvUpdateLog = null;
        aboutFragment.webView = null;
    }
}
