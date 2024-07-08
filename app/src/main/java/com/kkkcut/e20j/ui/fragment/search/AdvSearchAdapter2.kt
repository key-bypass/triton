package com.kkkcut.e20j.ui.fragment.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.DbBean.search.MenuSummary
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class AdvSearchAdapter2(list: List<MenuSummary>) :
    BaseQuickAdapter<MenuSummary, BaseViewHolder>(
        R.layout.item_search_result_series, list
    ) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseViewHolder: BaseViewHolder, menuSummary: MenuSummary) {
        baseViewHolder.setText(R.id.tv_lock_sys, menuSummary.lockSystem)
            .setText(R.id.tv_series, menuSummary.series).setText(
            R.id.tv_card, menuSummary.card.toString()
        ).setText(R.id.tv_sn, menuSummary.sn).setText(R.id.tv_type, menuSummary.type)
    }
}
