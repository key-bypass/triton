package com.kkkcut.e20j.DbBean

import android.os.Parcelable
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina
import com.kkkcut.e20j.DbBean.search.ChinaNumSearch
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData
import kotlinx.parcelize.Parcelize


@Parcelize
public class GoOperatBean() : Parcelable {
    var KeyChinaNum: String? = null;
    var codeSeries: String? = null;
    var cuts: String? = null;
    var doorIgnition: Boolean = false;
    var doorToIgnition: Boolean = false;
    var isCustomkey: Boolean = false;
    var isn: String? = null;
    var keyID: Int = 0;
    val remark: String? = null;
    var seriesID: Int = 0;
    var title: String? = null;
    var toothCode: String? = null;
    var toothCodeSide: String? = null;

    fun isDoorIgnition() : Boolean {
        return this.doorIgnition
    }

    fun isDoorToIgnition() : Boolean {
        return this.doorToIgnition
    }


    constructor(modelSeries: ModelSeries, str: String?, str2: String?) :         this(modelSeries, str) {
        this.isn = str2
    }

    constructor(modelSeries: ModelSeries, str: String?) : this() {
        this.codeSeries = modelSeries.getCodeSeries()
        this.seriesID = modelSeries.getModelSeriesID()
        this.keyID = modelSeries.getFK_KeyID()
        this.cuts = modelSeries.getCuts()
        this.title = str
    }

    constructor(modelSeriesChina: ModelSeriesChina, str: String?) : this() {
        this.codeSeries = modelSeriesChina.codeSeries
        this.seriesID = modelSeriesChina.ID
        this.KeyChinaNum = modelSeriesChina.KeyChinaNum
        this.cuts = modelSeriesChina.cuts
        this.keyID = modelSeriesChina.fK_KeyID.toInt()
        this.title = str
    }

    constructor(chinaNumSearch: ChinaNumSearch, str: String?) : this() {
        this.codeSeries = chinaNumSearch.codeSeries
        this.seriesID = chinaNumSearch.seriesID
        this.KeyChinaNum = chinaNumSearch.keyChinaNum
        this.cuts = chinaNumSearch.cuts
        this.keyID = chinaNumSearch.getfK_KeyID()
        this.title = str
    }

    constructor(collectionData: CollectionData) : this() {
        this.codeSeries = collectionData.codeSeries
        this.seriesID = collectionData.seriesID
        this.KeyChinaNum = collectionData.keyChinaNum
        this.keyID = collectionData.basicDataID
        this.cuts = collectionData.cuts
        this.title = collectionData.title
        this.toothCode = collectionData.toothCode
    }

    constructor(cutHistoryData: CutHistoryData) : this() {
        this.codeSeries = cutHistoryData.codeSeries
        this.seriesID = cutHistoryData.seriesID
        this.KeyChinaNum = cutHistoryData.keyChinaNum
        this.keyID = cutHistoryData.basicDataID
        this.cuts = cutHistoryData.cuts
        this.title = cutHistoryData.title
        this.toothCode = cutHistoryData.toothCode
        this.toothCodeSide = cutHistoryData.toothCodeSide
        this.isCustomkey = cutHistoryData.isCustomKey == 1
        this.doorIgnition = cutHistoryData.doorIgnition == 1
        this.doorToIgnition = cutHistoryData.doorToIgnition == 1
    }

    constructor(i: Int, str: String?) : this() {
        this.keyID = i
        this.title = str
    }

    constructor(customKey: CustomKey) : this() {
        this.keyID = customKey.icCard.toInt()
        this.title = customKey.keyname
        this.isCustomkey = true
    }


}
