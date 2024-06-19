package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.technical.DataListBean
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class TechnicalInfoDataAdapter :
    BaseQuickAdapter<DataListBean, BaseAutolayoutHolder>(R.layout.item_technical_databean) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, dataListBean: DataListBean) {
        baseAutolayoutHolder.setText(R.id.tv_name, dataListBean.name)
            .setText(R.id.tv_content, dataListBean.content)
    }
}