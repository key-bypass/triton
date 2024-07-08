package com.kkkcut.e20j.ui.activity

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.ImageView
import com.kkkcut.e20j.androidquick.tool.AppUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.base.HideStatusActivity
import com.kkkcut.e20j.presenter.SplashPresenter
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.ActivityLanuchBinding
import com.kkkcut.e20j.view.SplashView

/* loaded from: classes.dex */
class WelcomeActivity() : HideStatusActivity(), SplashView {
    private var splashPresenter: SplashPresenter? = null

    var binding: ActivityLanuchBinding? = null

    override fun onCreate(bundle: Bundle?, persistableBundle: PersistableBundle?) {
        super.onCreate(bundle, persistableBundle)
        this.binding = ActivityLanuchBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
    }

    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_lanuch
    }

    override fun initViewsAndEvents() {
        var resources: Resources
        var identifier: Int = 0
        this.splashPresenter = SplashPresenter(this, this)
        val packageName: String = AppUtil.getPackageName(this)
        val imageView: ImageView = findViewById(R.id.iv_splash)
        if (packageName.endsWith(".us")) {
            imageView.setImageResource(R.drawable.welcome_spl)
            return
        }
        if (packageName.endsWith(".neutral")) {
            imageView.setImageResource(R.drawable.welcome)
            return
        }
        val string: String = SPUtils.getString("welcome")

        if (TextUtils.isEmpty(string) || ((getResources().also { it ->
                resources = it
            }) == null) || ((getResources().getIdentifier(string, "drawable", getPackageName())
                .also { identifier = it }) == 0)
        ) {
            return
        }
        imageView.setImageResource(identifier)
    }

    // com.kkkcut.e20j.view.SplashView
    /* renamed from: goMain, reason: merged with bridge method [inline-methods] */ override fun goMain() {
        startActivity(Intent(this, FrameActivity::class.java as Class<*>?))
        finish()
    }

    // com.kkkcut.e20j.view.SplashView
    override fun goMainDelay(j: Long) {
        Handler().postDelayed({ this@WelcomeActivity.goMain() }, j)
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    override fun onDestroy() {
        super.onDestroy()
        splashPresenter!!.onDetach()
        this.splashPresenter = null
    }
}
