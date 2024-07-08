package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.kkkcut.e20j.base.BaseFFragment

/* loaded from: classes.dex */
abstract class BaseBackFragment() : BaseFFragment() {
    abstract fun setTitleStr(): String?

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        setTittle()
        fullScreen()
    }

    fun onBack() {
        _mActivity.onBackPressed()
    }

    fun setTittle() {
        val frameActivity: FragmentActivity? = this._mActivity
        if (frameActivity != null) {
            var titleStr: String? = setTitleStr()
            if (titleStr == null) {
                titleStr = ""
            }
            frameActivity.setTitle(titleStr)
        }
    }

    // com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    override fun onHiddenChanged(z: Boolean) {
        if (z || !isAdded()) {
            return
        }
        setTittle()
    }
}
