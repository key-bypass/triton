package com.kkkcut.e20j.DbBean.china

import com.kkkcut.e20j.DbBean.ClampKeyBasicData
import com.kkkcut.e20j.DbBean.KeyBasicData
import com.kkkcut.e20j.DbBean.KeyInfoBase
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.ToOne

/* loaded from: classes.dex */
class ModelSeriesChina : KeyInfoBase() {
    var DistributorNum: String? = null

    var FK_ModelYearChinaID: Int = 0

    @Id
    var ID: Int = 0

    var KeyChinaNum: String? = null

    var Name: String? = null

    var Notes: String? = null

    var Remark: String? = null

    var Sort: Int = 0

    @ToOne
    var clampKeyBasicData: ClampKeyBasicData? = null

    var codeSeries: String? = null

    var cuts: String? = null

    var fK_KeyID: Long = 0

    @ToOne(joinProperty = "FK_KeyID")
    var keyBasicData: KeyBasicData? = null

    var modelNo: String? = null

    override fun getItemType(): Int {
        return 0
    }

    override fun getTarget(): String {
        return ""
    }
}
