package com.kkkcut.e20j.ui.fragment.blankcut

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.adapter.BaseAutolayoutHolder
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class BlankCutAdapter(list: List<BlankCutBean>) :
    BaseQuickAdapter<BlankCutBean, BaseAutolayoutHolder>(
        R.layout.item_blank_cut, list
    ) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, blankCutBean: BlankCutBean) {
        baseAutolayoutHolder.setText(R.id.cb_black_cut, blankCutBean.name)
        baseAutolayoutHolder.setChecked(R.id.cb_black_cut, blankCutBean.isChecked)
        baseAutolayoutHolder.addOnClickListener(R.id.cb_black_cut)
    }
}
