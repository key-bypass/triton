package com.kkkcut.e20j.ui.fragment.keyselect

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.adapter.BrandSelectAdapter
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class BrandSelectFragment() : BaseBackFragment() {
    var etSearch: EditText? = null

    var indexBar: IndexBar? = null
    private var mAdapter: BrandSelectAdapter? = null
    private var mDecoration: SuspensionDecoration? = null

    var rvCategoryList: RecyclerView? = null

    var tvSideBarHint: TextView? = null
    private var mDatas = ArrayList<Manufacturer>()
    private var tempData = ArrayList<Manufacturer>()

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_child
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        getManufacturers(arguments!!.getInt("category"))
    }

    private fun initView() {
        etSearch!!.hint = getString(R.string.manufacturer)
        val linearLayoutManager = LinearLayoutManager(context)
        rvCategoryList!!.layoutManager = linearLayoutManager
        val brandSelectAdapter = BrandSelectAdapter((context)!!)
        this.mAdapter = brandSelectAdapter
        rvCategoryList!!.adapter = brandSelectAdapter
        val suspensionDecoration = SuspensionDecoration(context)
        this.mDecoration = suspensionDecoration
        rvCategoryList!!.addItemDecoration(suspensionDecoration)
        rvCategoryList!!.addItemDecoration(
            DividerItemDecoration(
                context, 1
            )
        )
        indexBar!!.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true)
            .setmLayoutManager(linearLayoutManager)
        mAdapter!!.setOnKeySelectItemClickListener(object :
            BrandSelectAdapter.OnKeySelectItemClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.keyselect.BrandSelectFragment$$ExternalSyntheticLambda0
            // com.kkkcut.e20j.adapter.BrandSelectAdapter.OnKeySelectItemClickListener
            override fun onItemClick(i: Int) {
                this@BrandSelectFragment.m56x7698229b(i)
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-BrandSelectFragment, reason: not valid java name */
    /* synthetic */ fun m56x7698229b(i: Int) {
        val manufacturer = tempData[i]
        var manufacturerName = manufacturer!!.manufacturerName
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(manufacturer.manufacturerNameCN)) {
            manufacturerName = manufacturer.manufacturerNameCN
        }
        goModel(tempData[i]!!.manufacturerId, manufacturerName)
    }

    private fun getManufacturers(i: Int) {
        addDisposable(Observable.fromCallable {
            UserDataDaoManager.getInstance(this@BrandSelectFragment.context).manufacturerHidden
        }.subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread())
            .map
            { list ->
                val arrayList = ArrayList<Int>()
                for (item in list) {
                    arrayList.add(item.manufacturerId)
                }
                arrayList
            }.map
            { list ->
                KeyInfoDaoManager.getInstance().getManufacturers(i, list)
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { list ->
                this.tempData = list as ArrayList<Manufacturer>
                this.mDatas = list
                this.setData(list)
            }, {this.dismissLoadingDialog()}))

    }

    fun setData(list: List<Manufacturer>) {
        mAdapter!!.setDatas(list)
        indexBar!!.setmSourceDatas(list).invalidate()
        mDecoration!!.setmDatas(list)
    }

    fun goModel(i: Int, str: String?) {
        if (MachineInfo.isChineseMachine()) {
            start(ModelChinaSelectFragment.newInstance(i, str))
        } else {
            start(ModelSelectFragment.newInstance(arguments!!.getInt("category"), i, str))
        }
        hideSoftInput()
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        var manufacturerName: String
        this.tempData = ArrayList<Manufacturer>()
        for (i in mDatas.indices) {
            val manufacturer = mDatas[i]
            if (MachineInfo.isChineseMachine()) {
                manufacturerName = manufacturer!!.manufacturerNameCN
            } else {
                manufacturerName = manufacturer!!.manufacturerName
            }
            if (!TextUtils.isEmpty(manufacturerName) && manufacturerName.lowercase(Locale.getDefault())
                    .startsWith(
                        editable.toString().lowercase(
                            Locale.getDefault()
                        )
                    )
            ) {
                tempData.add(manufacturer)
            }
        }
        setData(this.tempData)
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroyView() {
        super.onDestroyView()
        hideSoftInput()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(arguments!!.getInt("tag"))
    }

    companion object {
        val TAG: String = "TechnicalInfoBrandSelectFragment"


        fun newInstance(i: Int, i2: Int): BrandSelectFragment {
            val brandSelectFragment = BrandSelectFragment()
            val bundle = Bundle()
            bundle.putInt("category", i)
            bundle.putInt("tag", i2)
            brandSelectFragment.arguments = bundle
            return brandSelectFragment
        }
    }
}
