package com.kkkcut.e20j.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.DbBean.userDB.JpushMsg
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class MessageAdapter : BaseQuickAdapter<JpushMsg, BaseViewHolder>(R.layout.item_message) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseViewHolder: BaseViewHolder, jpushMsg: JpushMsg) {
        baseViewHolder.setText(R.id.tv_content, jpushMsg.content)
            .setText(R.id.tv_title, jpushMsg.title).setText(
                R.id.tv_date, jpushMsg.data
            )
    }
}