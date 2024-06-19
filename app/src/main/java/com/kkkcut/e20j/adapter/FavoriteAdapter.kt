package com.kkkcut.e20j.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.us.R
import java.text.SimpleDateFormat
import java.util.Locale

/* loaded from: classes.dex */
class FavoriteAdapter :
    BaseQuickAdapter<CollectionData, BaseAutolayoutHolder>(R.layout.item_favorite) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        collectionData: CollectionData
    ) {
        var title = collectionData.title
        if (title.contains("(")) {
            val split = title.split("\\(".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val codeSeries = collectionData.codeSeries
            title = if (!TextUtils.isEmpty(codeSeries)) {
                split[0] + ">" + codeSeries
            } else {
                split[0]
            }
        }
        var remark = collectionData.remark
        var cuts = collectionData.cuts
        if (TextUtils.isEmpty(remark)) {
            remark = ""
        }
        if (TextUtils.isEmpty(cuts)) {
            cuts = ""
        }
        baseAutolayoutHolder.setText(R.id.title, title).setText(R.id.tv_remark, remark)
            .addOnClickListener(
                R.id.iv_delete
            ).addOnClickListener(R.id.iv_edit).setText(R.id.tv_cuts, cuts)
            .setText(R.id.tv_ic_card, collectionData.basicDataID.toString())
    }

    private fun DateToString(j: Long): String {
        return if (j == 0L) "" else SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.US).format(j)
    }
}
