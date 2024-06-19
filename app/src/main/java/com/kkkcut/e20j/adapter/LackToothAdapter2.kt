package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.kkkcut.e20j.bean.CodeAndTooth
import com.kkkcut.e20j.bean.gsonBean.LackToothRes
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class LackToothAdapter2(i: Int, i2: Int, list: List<CodeAndTooth>) :
    BaseSectionQuickAdapter<CodeAndTooth, BaseAutolayoutHolder>(i, i2, list) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseSectionQuickAdapter
    override fun convertHead(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        codeAndTooth: CodeAndTooth
    ) {
        baseAutolayoutHolder.setText(R.id.tv_index, codeAndTooth.header)
    }

    /* JADX INFO: Access modifiers changed from: protected */ /* JADX WARN: Multi-variable type inference failed */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, codeAndTooth: CodeAndTooth) {
        val dataBean = codeAndTooth.t as LackToothRes.DataBean
        baseAutolayoutHolder.setText(R.id.tv_tooth, dataBean.bitting)
            .setText(R.id.tv_code, dataBean.code)
        if (dataBean.isChecked) {
            val color =
                baseAutolayoutHolder.itemView.context.resources.getColor(R.color.color_ff205f)
            baseAutolayoutHolder.setTextColor(R.id.tv_tooth, color)
                .setTextColor(R.id.tv_code, color)
        } else {
            baseAutolayoutHolder.setTextColor(R.id.tv_tooth, -1).setTextColor(R.id.tv_code, -1)
        }
    }
}
