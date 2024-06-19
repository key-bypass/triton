package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.bean.gsonBean.ConfigurationE9
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class HomepageEExtraFunctionAdapter(list: List<ConfigurationE9.BottomLayoutBean>) :
    BaseQuickAdapter<ConfigurationE9.BottomLayoutBean, BaseAutolayoutHolder>(
        R.layout.item_homepage_bottom_e9, list
    ) {
    private val strMap: MutableMap<String, Int>

    init {
        val hashMap = HashMap<String, Int>()
        this.strMap = hashMap
        hashMap["Cut History"] = R.string.cut_history
        strMap["Duplicating Key"] = R.string.duplicating_key
        strMap["My Key Info"] = R.string.my_key_info
        strMap["Calibration"] = R.string.calibration
        strMap["Favorite"] = R.string.favorites
        strMap["Service"] = R.string.service
        strMap["Technical Information"] = R.string.technical_information
        strMap["Key Marking"] = R.string.key_marking
        strMap["Blank Cut"] = R.string.blank_cut
        strMap["Last Key"] = R.string.last_key
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        bottomLayoutBean: ConfigurationE9.BottomLayoutBean
    ) {
        baseAutolayoutHolder.setImageResource(
            R.id.item_image,
            mContext.resources.getIdentifier(
                bottomLayoutBean.img, "drawable",
                mContext.packageName
            )
        ).setText(
            R.id.item_title,
            strMap[bottomLayoutBean.name]!!
        )
        baseAutolayoutHolder.itemView.tag = strMap[bottomLayoutBean.name]
    }
}
