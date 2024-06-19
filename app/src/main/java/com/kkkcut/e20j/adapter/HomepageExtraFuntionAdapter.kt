package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.bean.gsonBean.Configuration
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class HomepageExtraFuntionAdapter(list: List<Configuration.BottomLayoutBean>) :
    BaseQuickAdapter<Configuration.BottomLayoutBean, BaseAutolayoutHolder>(
        R.layout.item_homepage_bottom, list
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
        val map = this.strMap
        val valueOf = R.string.service
        map["Service"] = valueOf
        strMap["Service"] = valueOf
        strMap["Technical Information"] = R.string.technical_information
        strMap["Key Marking"] = R.string.key_marking
        strMap["Blank Cut"] = R.string.blank_cut
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        bottomLayoutBean: Configuration.BottomLayoutBean
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
        if (baseAutolayoutHolder.layoutPosition % 2 == 0) {
            baseAutolayoutHolder.setBackgroundColor(
                R.id.fl_item,
                mContext.resources.getColor(R.color.color_1f2032)
            )
            baseAutolayoutHolder.setImageResource(R.id.iv_arrow, R.drawable.arrow)
        } else {
            baseAutolayoutHolder.setBackgroundColor(
                R.id.fl_item,
                mContext.resources.getColor(R.color.color_2a2b40)
            )
            baseAutolayoutHolder.setImageResource(R.id.iv_arrow, R.drawable.arrow_deep)
        }
        baseAutolayoutHolder.itemView.tag = strMap[bottomLayoutBean.name]
    }
}
