package com.kkkcut.e20j.adapter

import android.text.TextUtils
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.internal.view.SupportMenu
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class ToothKeyboardRvAdapter : BaseQuickAdapter<String, BaseAutolayoutHolder> {
    constructor() : super(R.layout.item_input_number)

    constructor(i: Int) : super(i)

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, str: String) {
        baseAutolayoutHolder.setEnabled(R.id.bt_number, true)
        baseAutolayoutHolder.setTextColor(
            R.id.bt_number,
            getColor(mContext,
            R.color.textcolor_number_input)
        )
        for (viewId in baseAutolayoutHolder.nestViews){
            val view = baseAutolayoutHolder.getView<TextView>(viewId)
            val size = AutoUtils.getPercentHeightSize(25)
            view.setTextSize(size as Float)
            baseAutolayoutHolder.setText(R.id.bt_number, str)
        }
        if (TextUtils.isEmpty(str)) {
            baseAutolayoutHolder.setEnabled(R.id.bt_number, false)
        } else if (str == mContext.resources.getString(R.string.clear)) {
            for (viewId in baseAutolayoutHolder.nestViews) {
                val view = baseAutolayoutHolder.getView<TextView>(viewId)
                val size = AutoUtils.getPercentHeightSize(18)
                view.setTextSize(R.id.bt_number, size as Float)
                view.setTextColor(SupportMenu.CATEGORY_MASK)
            }
        }
    }
}