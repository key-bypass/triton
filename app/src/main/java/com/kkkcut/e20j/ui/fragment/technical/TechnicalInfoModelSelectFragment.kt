package com.kkkcut.e20j.ui.fragment.technical

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.technical.DataModel
import com.kkkcut.e20j.adapter.TechnicalInfoModelAdapter
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.p005ui.fragment.technical.TechnicalInfoSeriesSelectFragment
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale

/* loaded from: classes.dex */
class TechnicalInfoModelSelectFragment() : BaseBackFragment() {
    var etSearch: EditText? = null

    var indexBar: IndexBar? = null
    private var mAdapter: TechnicalInfoModelAdapter? = null
    private var mDatas1: List<DataModel>? = null
    private var mDecoration: SuspensionDecoration? = null

    var rvCategoryList: RecyclerView? = null
    private var tempData = ArrayList<DataModel>()

    var tvSideBarHint: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_child
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        getModels(getArguments()!!.getInt("brandID"))
    }

    private fun initView() {
        etSearch!!.setHint(getString(R.string.model))
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        rvCategoryList!!.setLayoutManager(linearLayoutManager)
        val technicalInfoModelAdapter: TechnicalInfoModelAdapter =
            TechnicalInfoModelAdapter(getContext())
        this.mAdapter = technicalInfoModelAdapter
        rvCategoryList!!.setAdapter(technicalInfoModelAdapter)
        val suspensionDecoration: SuspensionDecoration = SuspensionDecoration(getContext())
        this.mDecoration = suspensionDecoration
        rvCategoryList!!.addItemDecoration(suspensionDecoration)
        rvCategoryList!!.addItemDecoration(DividerItemDecoration(getContext(), 1))
        indexBar!!.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true)
            .setmLayoutManager(linearLayoutManager)
        mAdapter!!.setOnKeySelectItemClickListener(object :
            TechnicalInfoModelAdapter.OnKeySelectItemClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoModelSelectFragment.1
            // com.kkkcut.e20j.adapter.TechnicalInfoModelAdapter.OnKeySelectItemClickListener
            override fun onItemClick(i: Int) {
                val string: String? =
                    getArguments()!!.getString("title")
                val dataModel: DataModel? = tempData.get(i)
                var modelName: String = dataModel!!.getModelName()
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(dataModel.getModelName_CN())) {
                    modelName = dataModel.getModelName_CN()
                }
                val technicalInfoModelSelectFragment: TechnicalInfoModelSelectFragment =
                    this@TechnicalInfoModelSelectFragment
                technicalInfoModelSelectFragment.goSeries(
                    technicalInfoModelSelectFragment.tempData.get(
                        i
                    )!!.getModelID(), string + ">" + modelName
                )
            }
        })
    }

    fun goSeries(i: Int, str: String?) {
        start(TechnicalInfoSeriesSelectFragment.Companion.newInstance(i, str))
        hideSoftInput()
    }

    private fun getModels(i: Int) {
        addDisposable(
            Observable.fromCallable {
                val technicalInfoModels = KeyInfoDaoManager.getInstance().getTechnicalInfoModels(i)
                technicalInfoModels

    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { list ->
                    if (list != null) {
                        this.tempData = list as ArrayList<DataModel>
                        this.mDatas1 = list
                        setData(list)
                    }
                }, {this.dismissLoadingDialog()})
        )
    }



    fun setData(list: List<DataModel?>?) {
        mAdapter!!.setDatas(list)
        indexBar!!.setmSourceDatas(list).invalidate()
        mDecoration!!.setmDatas(list)
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        var modelName: String
        this.tempData = ArrayList<DataModel>()
        if (this.mDatas1 != null) {
            for (i in mDatas1!!.indices) {
                val dataModel: DataModel = mDatas1!!.get(i)
                if (MachineInfo.isChineseMachine()) {
                    modelName = dataModel.getModelName_CN()
                } else {
                    modelName = dataModel.getModelName()
                }
                if (!TextUtils.isEmpty(modelName) && modelName.lowercase(Locale.getDefault())
                        .startsWith(
                            editable.toString().lowercase(
                                Locale.getDefault()
                            )
                        )
                ) {
                    tempData.add(dataModel)
                }
            }
            setData(this.tempData)
        }
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroyView() {
        super.onDestroyView()
        hideSoftInput()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getArguments()!!.getString("title")
    }

    companion object {
        val TAG: String = "TechnicalInfoBrandSelectFragment"

        fun newInstance(i: Int, str: String?): TechnicalInfoModelSelectFragment {
            val technicalInfoModelSelectFragment: TechnicalInfoModelSelectFragment =
                TechnicalInfoModelSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("brandID", i)
            bundle.putString("title", str)
            technicalInfoModelSelectFragment.setArguments(bundle)
            return technicalInfoModelSelectFragment
        }
    }
}
