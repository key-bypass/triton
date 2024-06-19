package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class CustomKeyAdapter :
    BaseQuickAdapter<CustomKey, BaseAutolayoutHolder>(R.layout.item_customkey) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, customKey: CustomKey) {
        baseAutolayoutHolder.setText(R.id.tv_title, customKey.keyname)
        baseAutolayoutHolder.addOnClickListener(R.id.iv_edit, R.id.iv_delete, R.id.iv_synchronize)
    }
}