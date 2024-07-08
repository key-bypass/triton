package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentSupportBinding
import me.yokeyword.fragmentation.ISupportFragment

/* loaded from: classes.dex */
class SupportFragment() : BaseBackFragment() {
    var binding: FragmentSupportBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentSupportBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_support
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        if (MachineInfo.isChineseMachine()) {
            binding!!.llChinese.setVisibility(0)
            binding!!.llForeign.setVisibility(8)
            binding!!.llForeignUs.setVisibility(8)
        } else if (MachineInfo.isE20Us(getContext())) {
            binding!!.llChinese.setVisibility(8)
            binding!!.llForeign.setVisibility(8)
            binding!!.llForeignUs.setVisibility(0)
        } else {
            binding!!.llChinese.setVisibility(8)
            binding!!.llForeign.setVisibility(0)
            binding!!.llForeignUs.setVisibility(8)
        }
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.support)
    }

    companion object {
        val TAG: String = "SupportFragment"

        fun newInstance(): ISupportFragment {
            return SupportFragment()
        }
    }
}
