package com.kkkcut.e20j.ui.fragment;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.kkkcut.e20j.base.BaseFragment;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class WebFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webView;

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected int getContentViewLayoutID() {
        return R.layout.fragment_web;
    }

    public static WebFragment newInstance() {
        return new WebFragment();
    }

    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    protected void initViewsAndEvents() {
        WebSettings settings = this.webView.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl("file:////android_asset/HTMLPage1.html");
    }
}
