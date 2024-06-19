package com.kkkcut.e20j.adapter

import android.util.Log
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.bean.gsonBean.Configuration.CenterLayoutBean.KeytypeBean
import com.kkkcut.e20j.us.R
import org.w3c.dom.Text

/* loaded from: classes.dex */
class HomeCenterRvAdapter(list: List<KeytypeBean>) :
    BaseQuickAdapter<KeytypeBean, BaseViewHolder>(R.layout.item_homepage_center, list) {
    private val names: MutableMap<String, Int>

    init {
        val hashMap = HashMap<String, Int>()
        this.names = hashMap
        hashMap["Automobile"] = R.string.automobile
        names["Domestic car"] = R.string.chinese_car
        names["Dimple"] = R.string.dimple
        names["Motorcycle"] = R.string.motorcycle
        names["Tubular"] = R.string.tubular
        names["Standard"] = R.string.single_standard
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseViewHolder: BaseViewHolder, keytypeBean: KeytypeBean) {
        val num = names[keytypeBean.name]
        baseViewHolder.setText(R.id.tv_keyType, num!!)
        if ("Standard".equals(keytypeBean.name, ignoreCase = true)) {
            Log.i(TAG, "convert: ")
            for (viewId in baseViewHolder.nestViews) {
                val view = baseViewHolder.getView<TextView>(viewId)
                view.setTextSize(R.id.tv_keyType, 22.0f)
            }

        } else {
            for (viewId in baseViewHolder.nestViews) {
                val view = baseViewHolder.getView<TextView>(viewId)
                view.setTextSize(R.id.tv_keyType, 24.0f)
            }
        }
        baseViewHolder.setImageResource(
            R.id.iv_keyType,
            MyApplication.getInstance().resources.getIdentifier(
                keytypeBean.img,
                "drawable",
                MyApplication.getInstance().packageName
            )
        )
        baseViewHolder.itemView.tag = num
        AutoUtils.auto(baseViewHolder.itemView)
    }
}