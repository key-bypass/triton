package com.kkkcut.e20j.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.userDB.DuplicateDimple
import com.kkkcut.e20j.us.R
import java.text.SimpleDateFormat
import java.util.Locale

/* loaded from: classes.dex */
class DimpleDataAdapter :
    BaseQuickAdapter<DuplicateDimple, BaseAutolayoutHolder>(R.layout.item_dimple_data) {
    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(
        baseAutolayoutHolder: BaseAutolayoutHolder,
        duplicateDimple: DuplicateDimple
    ) {
        baseAutolayoutHolder.setText(R.id.title, duplicateDimple.keyname)
            .addOnClickListener(R.id.iv_delete).addOnClickListener(
            R.id.iv_edit
        ).addOnClickListener(R.id.iv_export)
        val DateToString = DateToString(duplicateDimple.time)
        if (!TextUtils.isEmpty(DateToString)) {
            baseAutolayoutHolder.setText(R.id.tv_time, DateToString)
        } else {
            baseAutolayoutHolder.setText(R.id.tv_time, "")
        }
    }

    private fun DateToString(j: Long): String {
        return if (j == 0L) "" else SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.US).format(j)
    }
}
