package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class UpdateLogAdapter :
    BaseQuickAdapter<String, BaseAutolayoutHolder>(R.layout.item_update_log) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, str: String) {
        baseAutolayoutHolder.setText(R.id.tv_content, str)
    }
}