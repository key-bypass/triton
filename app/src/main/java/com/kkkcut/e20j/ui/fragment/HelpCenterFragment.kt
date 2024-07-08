package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentHelpCenterBinding
import spa.lyh.cn.lib_largeimageview.LargeImageView

/* loaded from: classes.dex */
class HelpCenterFragment() : BaseBackFragment() {
    private var lastIndex: Int = 0

    var binding: FragmentHelpCenterBinding? = null

    private val pics_en: IntArray = intArrayOf(R.drawable.help1_en, R.drawable.help2_en)
    private val pics_ch: IntArray = intArrayOf(R.drawable.help1_ch, R.drawable.help2_ch)
    private val pics_easy: IntArray = intArrayOf(R.drawable.help1_easy, R.drawable.help2_easy)
    private val pics_us: IntArray = intArrayOf(R.drawable.help1_us, R.drawable.help2_us)

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentHelpCenterBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_help_center
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.help_center)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        binding!!.viewpager.setAdapter(object : PagerAdapter() {
            // from class: com.kkkcut.e20j.ui.fragment.HelpCenterFragment.1
            // androidx.viewpager.widget.PagerAdapter
            override fun isViewFromObject(view: View, obj: Any): Boolean {
                return view === obj
            }

            // androidx.viewpager.widget.PagerAdapter
            override fun getCount(): Int {
                return pics.size
            }

            // androidx.viewpager.widget.PagerAdapter
            override fun instantiateItem(viewGroup: ViewGroup, i: Int): Any {
                val largeImageView: LargeImageView =
                    LargeImageView(this@HelpCenterFragment.getContext())
                largeImageView.setImage(pics.get(i))
                viewGroup.addView(largeImageView)
                return largeImageView
            }

            // androidx.viewpager.widget.PagerAdapter
            override fun destroyItem(viewGroup: ViewGroup, i: Int, obj: Any) {
                viewGroup.removeView(obj as View?)
            }
        })
        binding!!.viewpager.addOnPageChangeListener(object : OnPageChangeListener {
            // from class: com.kkkcut.e20j.ui.fragment.HelpCenterFragment.2
            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageScrollStateChanged(i: Int) {
            }

            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageScrolled(i: Int, f: Float, i2: Int) {
            }

            // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            override fun onPageSelected(i: Int) {
                if (this@HelpCenterFragment.lastIndex != i) {
                    val childAt: View =
                        binding!!.viewpager.getChildAt(this@HelpCenterFragment.lastIndex)
                    val largeImageView: LargeImageView? =
                        if (childAt is LargeImageView) childAt else null
                    if (largeImageView != null) {
                        largeImageView.setScale(1.0f)
                    }
                    this@HelpCenterFragment.lastIndex = i
                }
            }
        })
    }

    val pics: IntArray
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            if (MachineInfo.isE20Us(getContext())) {
                return this.pics_us
            }
            if (MachineInfo.isChineseMachine()) {
                return this.pics_ch
            }
            return this.pics_en
        }

    companion object {
        fun newInstance(): HelpCenterFragment {
            val bundle: Bundle = Bundle()
            val helpCenterFragment: HelpCenterFragment = HelpCenterFragment()
            helpCenterFragment.setArguments(bundle)
            return helpCenterFragment
        }
    }
}
