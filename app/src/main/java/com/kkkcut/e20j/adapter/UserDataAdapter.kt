package com.kkkcut.e20j.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData
import com.kkkcut.e20j.us.R
import java.text.SimpleDateFormat
import java.util.Locale

/* loaded from: classes.dex */
class UserDataAdapter<T> : BaseQuickAdapter<T, BaseAutolayoutHolder>(R.layout.item_user_data) {
    /* JADX WARN: Multi-variable type inference failed */
    // com.chad.library.adapter.base.BaseQuickAdapter
    protected /* bridge */ /* synthetic */ override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        obj: T
    ) {
        convert2(baseAutolayoutHolder, obj)
    }

    /* JADX WARN: Multi-variable type inference failed */ /* renamed from: convert, reason: avoid collision after fix types in other method */
    protected fun convert2(baseAutolayoutHolder: BaseAutolayoutHolder, t: T) {
        if (t is CutHistoryData) {
            val cutHistoryData = t as CutHistoryData
            var title = cutHistoryData.title
            if (title.contains("--")) {
                val split = title.split("--".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val codeSeries = cutHistoryData.codeSeries
                if (!TextUtils.isEmpty(codeSeries)) {
                    title = split[0] + ">" + codeSeries + "--" + split[1]
                }
            }
            baseAutolayoutHolder.setText(R.id.title, title).addOnClickListener(R.id.iv_delete)
                .addOnClickListener(
                    R.id.iv_edit
                )
            val DateToString: CharSequence = DateToString(cutHistoryData.time)
            if (!TextUtils.isEmpty(DateToString)) {
                baseAutolayoutHolder.setText(R.id.tv_time, DateToString)
            } else {
                baseAutolayoutHolder.setText(R.id.tv_time, "")
            }
            val remark: CharSequence = cutHistoryData.remark
            if (!TextUtils.isEmpty(remark)) {
                baseAutolayoutHolder.setText(R.id.tv_remark, remark)
                return
            } else {
                baseAutolayoutHolder.setText(R.id.tv_remark, "")
                return
            }
        }
        val collectionData = t as CollectionData
        var title2 = collectionData.title
        if (title2.contains("--")) {
            val split2 = title2.split("--".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
            val codeSeries2 = collectionData.codeSeries
            if (!TextUtils.isEmpty(codeSeries2)) {
                title2 = split2[0] + ">" + codeSeries2 + "--" + split2[1]
            }
        }
        baseAutolayoutHolder.setText(R.id.title, title2)
            .setText(R.id.tv_remark, collectionData.remark).addOnClickListener(
                R.id.iv_delete
            ).addOnClickListener(R.id.iv_edit)
        val DateToString2: CharSequence = DateToString(collectionData.time)
        if (!TextUtils.isEmpty(DateToString2)) {
            baseAutolayoutHolder.setText(R.id.tv_time, DateToString2)
        } else {
            baseAutolayoutHolder.setText(R.id.tv_time, "")
        }
    }

    private fun DateToString(j: Long): String {
        return if (j == 0L) "" else SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.US).format(j)
    }
}