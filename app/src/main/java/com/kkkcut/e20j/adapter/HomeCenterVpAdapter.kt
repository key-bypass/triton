package com.kkkcut.e20j.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.gsonBean.Configuration
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class HomeCenterVpAdapter(private val context: Context) : PagerAdapter() {
    var layoutList: List<Configuration.CenterLayoutBean>? = null

    // androidx.viewpager.widget.PagerAdapter
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    fun setDatas(list: List<Configuration.CenterLayoutBean>?) {
        this.layoutList = list
        notifyDataSetChanged()
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun getCount(): Int {
        val list = this.layoutList
        if (list != null) {
            return list.size
        }
        return 0
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun instantiateItem(viewGroup: ViewGroup, i: Int): Any {
        val recyclerView = RecyclerView(this.context)
        val linearLayoutManager = LinearLayoutManager(this.context)
        linearLayoutManager.orientation = 0
        recyclerView.layoutManager = linearLayoutManager
        val homeCenterRvAdapter = HomeCenterRvAdapter(layoutList!![i].keytype)
        homeCenterRvAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { baseQuickAdapter, view, i2 ->
                // from class: com.kkkcut.e20j.adapter.HomeCenterVpAdapter$$ExternalSyntheticLambda0
                // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                `lambda$instantiateItem$0`(baseQuickAdapter, view, i2)
            }
        recyclerView.adapter = homeCenterRvAdapter
        viewGroup.addView(recyclerView)
        return recyclerView
    }

    // androidx.viewpager.widget.PagerAdapter
    override fun destroyItem(viewGroup: ViewGroup, i: Int, obj: Any) {
        viewGroup.removeView(obj as View)
    }

    companion object {
        private const val TAG = "HomeCenterVpAdapter"

        /* JADX INFO: Access modifiers changed from: package-private */
        fun `lambda$instantiateItem$0`(
            baseQuickAdapter: BaseQuickAdapter<*, *>?,
            view: View,
            i: Int
        ) {
            Log.i(TAG, "instantiateItem: " + view.tag)
            if (view.tag != null) {
                EventBus.getDefault().post(EventCenter<Any?>(5, (view.tag as Int)))
            }
        }
    }
}
