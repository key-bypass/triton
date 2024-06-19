package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.search.SearchResult
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class KeySearchAdapter<T : SearchResult> :
    BaseQuickAdapter<T, BaseAutolayoutHolder>(R.layout.item_key_search) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, t: T) {
        baseAutolayoutHolder.setText(R.id.tv_id, t!!.displayKeyID)
            .setText(R.id.tv_name, t.displayName).setText(
            R.id.tv_blank, t.keyBlankName
        )
    }
}
