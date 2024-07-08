package com.kkkcut.e20j.ui.fragment.search

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.search.MenuSummary
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/* loaded from: classes.dex */
class SearchResultFragment : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    var advSearchAdapter1: AdvSearchAdapter1? = null

    var rvSearchResult1: RecyclerView? = null

    var tvTile: TextView? = null

    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_advance_search_result
    }

    override fun setTitleStr(): String? {
        return null
    }

    override fun initViewsAndEvents() {
        if (arguments == null) {
            return
        }
        tvTile!!.text =
            getConditionStr(arguments!!.getParcelable<Parcelable>(CONDITION) as SearchCondition?)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = 1
        rvSearchResult1!!.layoutManager = linearLayoutManager
        val advSearchAdapter1 = AdvSearchAdapter1()
        this.advSearchAdapter1 = advSearchAdapter1
        advSearchAdapter1.onItemClickListener = this
        rvSearchResult1!!.adapter = this.advSearchAdapter1
        getSearchResult(arguments!!.getParcelable<Parcelable>(CONDITION) as SearchCondition?)
    }

    private fun getSearchResult(searchCondition: SearchCondition?) {
        addDisposable(
            Observable.fromCallable
            {
                KeyInfoDaoManager.getInstance().advSearch(searchCondition)
            }.map { list ->
                val linkedHashMap = LinkedHashMap<Int, MutableList<MenuSummary>>()
                for (menuSummary in list) {
                    if (linkedHashMap.containsKey(menuSummary.fK_KeyID)) {
                        linkedHashMap[menuSummary.fK_KeyID]!!.add(menuSummary)
                    } else {
                        val arrayList = ArrayList<MenuSummary>()
                        arrayList.add(menuSummary)
                        linkedHashMap[menuSummary.fK_KeyID] = arrayList
                    }
                }
                val arrayList2 = ArrayList<AdvSearchResult>()
                for (num in linkedHashMap.keys) {
                    val list2 = linkedHashMap[num]
                    val advSearchResult = AdvSearchResult()
                    advSearchResult.childList = list2
                    advSearchResult.fK_KeyID = num
                    if (!list2.isNullOrEmpty()) {
                        advSearchResult.cuts = list2[0].nofcuts
                    }
                    arrayList2.add(advSearchResult)
                }
                arrayList2
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe
                {
                    val searchResultFragment = this@SearchResultFragment
                    searchResultFragment.showLoadingDialog(searchResultFragment.getString(R.string.waitting))
                }.doFinally
            { this@SearchResultFragment.dismissLoadingDialog() }
                .subscribeOn(AndroidSchedulers.mainThread()).subscribe(
            { list ->
                Log.i(TAG, "accept: $list")
                advSearchAdapter1!!.setNewData(list)
            }, { dismissLoadingDialog() }))
    }

    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val fkKeyid = (baseQuickAdapter.data[i] as AdvSearchResult).fK_KeyID
        start(KeyOperateFragment.newInstance(GoOperatBean(fkKeyid, "ID:$fkKeyid")))
    }

    private fun getConditionStr(searchCondition: SearchCondition?): String {
        if (searchCondition == null) {
            return ""
        }
        val sb = StringBuilder()
        if (!TextUtils.isEmpty(searchCondition.kid)) {
            sb.append(getString(R.string.kid))
            sb.append(":")
            sb.append(searchCondition.kid)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.silcaCard)) {
            sb.append(getString(R.string.silca_card))
            sb.append(":")
            sb.append(searchCondition.silcaCard)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.silcaSN)) {
            sb.append(getString(R.string.silca_sn))
            sb.append(":")
            sb.append(searchCondition.silcaSN)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.keyBlank)) {
            sb.append(getString(R.string.key_blank))
            sb.append(":")
            sb.append(searchCondition.keyBlank)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.keyBlankManu)) {
            sb.append(getString(R.string.key_manufacturer))
            sb.append(":")
            sb.append(searchCondition.keyBlankManu)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.lockManu)) {
            sb.append(getString(R.string.lock_manufacturer))
            sb.append(":")
            sb.append(searchCondition.lockManu)
            sb.append(", ")
        }
        if (!TextUtils.isEmpty(searchCondition.lockSys)) {
            sb.append(getString(R.string.lock_system))
            sb.append(":")
            sb.append(searchCondition.lockSys)
            sb.append(", ")
        }
        return sb.toString()
    }

    companion object {
        private const val CONDITION = "condition"
        fun newInstance(searchCondition: SearchCondition): SearchResultFragment {
            val bundle = Bundle()
            bundle.putParcelable(CONDITION, searchCondition)
            val searchResultFragment = SearchResultFragment()
            searchResultFragment.arguments = bundle
            return searchResultFragment
        }
    }
}
