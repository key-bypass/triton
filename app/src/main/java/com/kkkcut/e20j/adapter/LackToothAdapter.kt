package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.BittingCode
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class LackToothAdapter :
    BaseQuickAdapter<BittingCode, BaseAutolayoutHolder>(R.layout.item_lacktooth) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, bittingCode: BittingCode) {
        baseAutolayoutHolder.setText(R.id.tv_tooth, bittingCode.bitting)
            .setText(R.id.tv_code, bittingCode.code)
    }
}
