package com.kkkcut.e20j.adapter

import android.text.TextUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class LanguageListAdapter(list: List<String>, map: Map<String, String>, str: String?) :
    BaseQuickAdapter<String, BaseAutolayoutHolder>(
        R.layout.language_list_item_view, list
    ) {
    private val languageMap: Map<String, String>
    private var shortName: String? = null

    init {
        if (TextUtils.isEmpty(str)) {
            this.shortName = "en"
        } else {
            this.shortName = str
        }
        this.languageMap = map
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.chad.library.adapter.base.BaseQuickAdapter
    override fun convert(baseAutolayoutHolder: BaseAutolayoutHolder, str: String) {
        if (this.shortName == str) {
            baseAutolayoutHolder.setChecked(R.id.cb, true)
        } else {
            baseAutolayoutHolder.setChecked(R.id.cb, false)
        }
        val str2 = languageMap[str]
        if (TextUtils.isEmpty(str2)) {
            return
        }
        baseAutolayoutHolder.setText(R.id.tv, str2)
    }

    companion object {
        private const val TAG = "LanguageListAdapter"
    }
}
