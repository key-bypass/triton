package com.kkkcut.e20j.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.bean.gsonBean.Configuration
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class HomepageCenterItemAdapter(private val context: Context) : RecyclerView.Adapter<HomepageCenterItemAdapter.ViewHolder>() {
    private var datas: List<Configuration.CenterLayoutBean>? = null
    private val item: MutableMap<String, Int> = HashMap<String, Int>()
    private var mOnCenterItemClickListener: OnCenterItemClickListener? = null

    /* loaded from: classes.dex */
    interface OnCenterItemClickListener {
        fun onCenterItemClick(i: Int)
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
    }

    init {
        Log.i(TAG, "HomepageCenterItemAdapter: ")
        add(context)
    }

    private fun add(context: Context) {
        item["Automobile"] = R.string.automobile
        item["Domestic car"] = R.string.chinese_car
        item["Dimple"] = R.string.dimple
        item["Motorcycle"] = R.string.motorcycle
        item["Tubular"] = R.string.tubular
        item["Standard"] = R.string.standard
    }

    fun setDatas(list: List<Configuration.CenterLayoutBean>?) {
        this.datas = list
        notifyDataSetChanged()
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_homepage_center, viewGroup, false)
        )
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun getItemCount(): Int {
        val list = this.datas ?: return 0
        return list.size
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* loaded from: classes.dex */
    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var button: Button? = null

        init {
            AutoUtils.autoTextSize(this.button)
            AutoUtils.autoSize(view)
        }
    }

    fun setOnCenterItemClickListener(onCenterItemClickListener: OnCenterItemClickListener?) {
        this.mOnCenterItemClickListener = onCenterItemClickListener
    }

    fun switchLanguage() {
        item.clear()
        add(this.context)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "HomepageExtraFuntionAdapter"
    }
}
