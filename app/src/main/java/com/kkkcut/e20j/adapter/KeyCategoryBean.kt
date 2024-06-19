package com.kkkcut.e20j.adapter

import com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean

/* loaded from: classes.dex */
class KeyCategoryBean : BaseIndexPinyinBean {
    var id: Int = 0
    var isTop: Boolean = false
        private set
    var keyBlank: String? = null
    var logo: String? = null
    var model: String? = null
    var name: String? = null
        private set
    var seria: String? = null
    var title: String? = null
    var year: String? = null

    constructor()

    constructor(str: String?) {
        this.name = str
    }

    fun setName(str: String?): KeyCategoryBean {
        this.name = str
        return this
    }

    fun setTop(z: Boolean): KeyCategoryBean {
        this.isTop = z
        return this
    }

    // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    override fun getTarget(): String {
        return name!!
    }

    // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    override fun isNeedToPinyin(): Boolean {
        return !this.isTop
    }

    // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexBean, com.kkkcut.e20j.customView.indexlib.suspension.ISuspensionInterface
    override fun isShowSuspension(): Boolean {
        return !this.isTop
    }
}
