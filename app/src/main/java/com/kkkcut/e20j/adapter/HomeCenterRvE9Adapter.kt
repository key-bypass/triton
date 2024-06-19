package com.kkkcut.e20j.adapter

import android.util.Pair
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class HomeCenterRvE9Adapter(list: List<ConfigurationE9.CenterLayoutBean>) :
    BaseQuickAdapter<ConfigurationE9.CenterLayoutBean, BaseViewHolder>(
        R.layout.item_homepage_center_e9,
        list
    ) {
    private val names: MutableMap<String, Pair<Int, Int>>

    init {
        val hashMap = HashMap<String, Pair<Int, Int>>()
        this.names = hashMap
        hashMap["Automobile"] =
            Pair<Int, Int>(R.string.automobile, R.drawable.icon_automobile)
        val map = this.names
        val valueOf = R.string.chinese_car
        val valueOf2 = R.drawable.icon_domestic_car
        map["Domestic car"] = Pair(valueOf, valueOf2)
        names["Domestic Car"] =
            Pair(valueOf, valueOf2)
        names["Dimple"] =
            Pair(R.string.dimple, R.drawable.icon_dimple)
        names["Motorcycle"] =
            Pair(R.string.motorcycle, R.drawable.icon_motorcycle)
        names["Tubular"] =
            Pair(R.string.tubular, R.drawable.icon_tubular)
        names["Standard"] = Pair(R.string.single_standard, R.drawable.icon_standard)
        names["Search"] =
            Pair(R.string.search, R.drawable.icon_search)
        names["Calibration"] =
            Pair(R.string.calibration, R.drawable.icon_setup)
        names["Support"] =
            Pair(R.string.service, R.drawable.icon_support)
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseViewHolder: BaseViewHolder,
        centerLayoutBean: ConfigurationE9.CenterLayoutBean
    ) {
        val pair = names[centerLayoutBean.name] ?: return
        val num = pair.first as Int
        baseViewHolder.setText(R.id.button, num)
        if ("Standard".equals(centerLayoutBean.name, ignoreCase = true)) {
            for (viewId in baseViewHolder.nestViews){
            val view = baseViewHolder.getView<TextView>(viewId)
            view.setTextSize(R.id.button, 22.0f)
        }
        } else {
            for (viewId in baseViewHolder.nestViews){
                val view = baseViewHolder.getView<TextView>(viewId)
                view.setTextSize(R.id.button, 24.0f)
            }

        }
        baseViewHolder.setBackgroundRes(R.id.button, ((pair.second as Int)))
        baseViewHolder.itemView.tag = num
        AutoUtils.auto(baseViewHolder.itemView)
    }
}