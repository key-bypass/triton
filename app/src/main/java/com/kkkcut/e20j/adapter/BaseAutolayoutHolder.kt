package com.kkkcut.e20j.adapter

import android.view.View
import com.chad.library.adapter.base.BaseViewHolder
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils

/* loaded from: classes.dex */
class BaseAutolayoutHolder(view: View?) : BaseViewHolder(view) {
    init {
        AutoUtils.autoSize(view)
    }
}