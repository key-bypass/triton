package com.kkkcut.e20j.ui.fragment

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kkkcut.e20j.base.BaseFragment
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class WebFragment() : BaseFragment() {
    var webView: WebView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_web
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val settings: WebSettings = webView!!.getSettings()
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        settings.setJavaScriptEnabled(true)
        settings.setBuiltInZoomControls(true)
        settings.setSupportZoom(true)
        webView!!.setWebViewClient(WebViewClient())
        webView!!.loadUrl("file:////android_asset/HTMLPage1.html")
    }

    companion object {
        fun newInstance(): WebFragment {
            return WebFragment()
        }
    }
}
