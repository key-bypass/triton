package com.kkkcut.e20j.ui.fragment

import android.os.Handler
import android.widget.ImageView
import com.kkkcut.e20j.base.BaseFFragment
import com.kkkcut.e20j.presenter.SplashPresenter
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.Companion.newInstance
import com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.view.SplashView
import me.yokeyword.fragmentation.ISupportFragment

/* loaded from: classes.dex */
class SplashFragment() : BaseFFragment(), SplashView {
    var ivSplash: ImageView? = null
    private var splashPresenter: SplashPresenter? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.activity_lanuch
    }

    fun onViewClicked() {
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.splashPresenter = SplashPresenter(getContext(), this)
    }

    // com.kkkcut.e20j.view.SplashView
    override fun goMain() {
        startWithPop(MainFragment.Companion.newInstance())
    }

    // com.kkkcut.e20j.view.SplashView
    override fun goMainDelay(j: Long) {
        Handler().postDelayed(object : Runnable {
            // from class: com.kkkcut.e20j.ui.fragment.SplashFragment.1
            // java.lang.Runnable
            override fun run() {
                this@SplashFragment.goMain()
            }
        }, j)
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroyView() {
        super.onDestroyView()
        splashPresenter!!.onDetach()
    }

    companion object {
        fun newInstance(): ISupportFragment {
            return SplashFragment()
        }
    }
}
