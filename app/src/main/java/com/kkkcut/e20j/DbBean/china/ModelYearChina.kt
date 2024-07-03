package com.kkkcut.e20j.DbBean.china

import com.kkkcut.e20j.DbBean.KeyInfoBase
import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.ToMany

@Entity
class ModelYearChina : KeyInfoBase() {
    var FK_ModelChinaID: Int = 0
    var description: String? = null
    var fromYear: String? = null

    @Id
    var id: Int = 0

    @ToMany
    var modelSeriesList: List<ModelSeriesChina>? = null
    var sort: Int = 0
    var toYear: String? = null

    override fun getTarget(): String {
        TODO("Not yet implemented")
    }

    override fun getItemType(): Int {
        TODO("Not yet implemented")
    }
}
