package com.kkkcut.e20j.ui.fragment.clampswitch

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

/* loaded from: classes.dex */
class ClampSwitchPagerAdapter(private var imgRes: List<ClampDisplayBean>, context: Context) :
    PagerAdapter() {
    private var imageViewList = ArrayList<ImageView>()

    // androidx.viewpager.widget.PagerAdapter
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    init {
        for (i in imgRes.indices) {
            val imageView = ImageView(context)
            imageView.setImageResource(imgRes[i].drawable)
            imageView.setPadding(5, 5, 5, 5)
            imageViewList.add(imageView)
        }
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun getCount(): Int {
        val list = this.imgRes ?: return 0
        return list.size
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun destroyItem(viewGroup: ViewGroup, i: Int, obj: Any) {
        viewGroup.removeView(obj as View)
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun instantiateItem(viewGroup: ViewGroup, i: Int): Any {
        val imageView = imageViewList[i]
        viewGroup.addView(imageView)
        return imageView
    }

    fun getViewByPosition(i: Int): View {
        return imageViewList[i]
    }
}
