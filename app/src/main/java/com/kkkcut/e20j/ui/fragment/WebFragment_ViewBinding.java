package com.kkkcut.e20j.ui.fragment;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class WebFragment_ViewBinding implements Unbinder {
    private WebFragment target;

    public WebFragment_ViewBinding(WebFragment webFragment, View view) {
        this.target = webFragment;
        webFragment.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.webview, "field 'webView'", WebView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WebFragment webFragment = this.target;
        if (webFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        webFragment.webView = null;
    }
}
