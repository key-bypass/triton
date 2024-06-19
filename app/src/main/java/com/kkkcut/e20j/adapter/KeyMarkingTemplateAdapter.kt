package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.KeyMarkingTemplate
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class KeyMarkingTemplateAdapter :
    BaseQuickAdapter<KeyMarkingTemplate, BaseAutolayoutHolder>(R.layout.item_keymarking_template) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        keyMarkingTemplate: KeyMarkingTemplate
    ) {
        baseAutolayoutHolder.setText(R.id.tv_remark, keyMarkingTemplate.description)
            .addOnClickListener(
                R.id.iv_delete
            )
    }
}
