package com.kkkcut.e20j.ui.fragment

import android.view.View
import com.kkkcut.e20j.us.R
import me.yokeyword.fragmentation.ISupportFragment

/* loaded from: classes.dex */
class DeviceCenterFragment() : BaseBackFragment() {
    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_device_center
    }

    // com.kkkcut.e20j.android quick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    fun onViewClicked(view: View) {
        view.getId()
    }

    companion object {
        val TAG: String = "DeviceCenterFragment"

        fun newInstance(): ISupportFragment {
            return DeviceCenterFragment()
        }
    }
}
