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
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        obj: T
    ) {
        if (obj is CutHistoryData) {
            val cutHistoryData = obj as CutHistoryData
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
            val date: CharSequence = dateToString(cutHistoryData.time)
            if (!TextUtils.isEmpty(date)) {
                baseAutolayoutHolder.setText(R.id.tv_time, date)
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
        val collectionData = obj as CollectionData
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
        val dateToStringValue = dateToString(collectionData.time)
        if (!TextUtils.isEmpty(dateToStringValue)) {
            baseAutolayoutHolder.setText(R.id.tv_time, dateToStringValue)
        } else {
            baseAutolayoutHolder.setText(R.id.tv_time, "")
        }
    }

    private fun dateToString(j: Long): String {
        return if (j == 0L) "" else SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.US).format(j)
    }
}