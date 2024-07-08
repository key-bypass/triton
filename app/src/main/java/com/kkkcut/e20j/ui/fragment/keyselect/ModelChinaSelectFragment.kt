package com.kkkcut.e20j.ui.fragment.keyselect

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.china.ModelChina
import com.kkkcut.e20j.adapter.ModelChinaSelectAdapter
import com.kkkcut.e20j.adapter.OnKeySelectItemClickListener
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale

/* loaded from: classes.dex */
class ModelChinaSelectFragment() : BaseBackFragment() {
    var etSearch: EditText? = null

    var indexBar: IndexBar? = null
    private var mAdapter: ModelChinaSelectAdapter? = null
    private var mDatas: List<ModelChina>? = null
    private var mDecoration: SuspensionDecoration? = null

    var rvCategoryList: RecyclerView? = null
    var tempData = ArrayList<ModelChina>()

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
        etSearch!!.setHint(getString(R.string.search_by_model))
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        rvCategoryList!!.setLayoutManager(linearLayoutManager)
        val modelChinaSelectAdapter: ModelChinaSelectAdapter =
            ModelChinaSelectAdapter((getContext())!!)
        this.mAdapter = modelChinaSelectAdapter
        rvCategoryList!!.setAdapter(modelChinaSelectAdapter)
        val suspensionDecoration: SuspensionDecoration = SuspensionDecoration(context)
        this.mDecoration = suspensionDecoration
        rvCategoryList!!.addItemDecoration(suspensionDecoration)
        rvCategoryList!!.addItemDecoration(DividerItemDecoration(context, 1))
        indexBar!!.setmPressedShowTextView(this.tvSideBarHint).setNeedRealIndex(true)
            .setmLayoutManager(linearLayoutManager)
        mAdapter!!.onKeySelectItemClickListener = object : OnKeySelectItemClickListener {
            override fun onItemClick(i: Int) {
                val string =
                    this@ModelChinaSelectFragment.arguments!!.getString("title")
                val modelChina: ModelChina = this@ModelChinaSelectFragment.tempData[i]
                var modelName: String = modelChina.modelName
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(modelChina.modelName_CN)) {
                    modelName = modelChina.modelName_CN
                }
                val modelChinaSelectFragment: ModelChinaSelectFragment =
                    this@ModelChinaSelectFragment
                modelChinaSelectFragment.goSeries(
                    modelChinaSelectFragment.tempData[i].modelID,
                    "$string>$modelName"
                )
            }
        }
    }

    fun goSeries(i: Int, str: String?) {
        start(
            SeriesChinaSelectFragment.Companion.newInstance(
                i,
                getArguments()!!.getInt("category"),
                str
            )
        )
        hideSoftInput()
    }

    private fun getModels(i: Int) {
        Log.i("TechnicalInfoBrandSelectFragment", "getModels: " + i)
        addDisposable(
            Observable.fromCallable {
                val chinaModels: List<*>
                chinaModels = KeyInfoDaoManager.getInstance().getChinaModels(i)
                chinaModels
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { list ->
                Log.i("TechnicalInfoBrandSelectFragment", "getModels: $list")
                if (list != null) {
                    this.tempData = list as ArrayList<ModelChina>
                    this.mDatas = list
                    setData(list)
                }
            }
        )
    }

    fun setData(list: List<ModelChina>) {
        mAdapter!!.datas = list
        indexBar!!.setmSourceDatas(list).invalidate()
        mDecoration!!.setmDatas(list)
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        var modelName: String
        this.tempData = ArrayList<ModelChina>()
        for (i in mDatas!!.indices) {
            val modelChina: ModelChina = mDatas!!.get(i)
            if (MachineInfo.isChineseMachine()) {
                modelName = modelChina.getModelName_CN()
            } else {
                modelName = modelChina.getModelName()
            }
            if (!TextUtils.isEmpty(modelName) && modelName.lowercase(Locale.getDefault())
                    .startsWith(
                        editable.toString().lowercase(
                            Locale.getDefault()
                        )
                    )
            ) {
                tempData.add(modelChina)
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
        return getArguments()!!.getString("title")
    }

    companion object {
        val TAG: String = "TechnicalInfoBrandSelectFragment"

        fun newInstance(i: Int, str: String?): ModelChinaSelectFragment {
            val modelChinaSelectFragment: ModelChinaSelectFragment = ModelChinaSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("brandID", i)
            bundle.putString("title", str)
            modelChinaSelectFragment.setArguments(bundle)
            return modelChinaSelectFragment
        }
    }
}
