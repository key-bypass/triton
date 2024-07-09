package com.kkkcut.e20j.ui.fragment.technical

import android.text.Editable
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.technical.DataManufacturer
import com.kkkcut.e20j.adapter.OnKeySelectItemClickListener
import com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter
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
class TechnicalInfoBrandSelectFragment : BaseBackFragment() {
    var etSearch: EditText? = null

    var indexBar: IndexBar? = null
    private var mAdapter: TechnicalInfoBradAdapter? = null
    private var mDecoration: SuspensionDecoration? = null

    var rvCategoryList: RecyclerView? = null

    var tvSideBarHint: TextView? = null
    private var mDatas = ArrayList<DataManufacturer>()
    private var tempData = ArrayList<DataManufacturer>()

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_child
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        getManufacturers()
    }

    private fun initView() {
        etSearch!!.hint = getString(R.string.manufacturer)
        val linearLayoutManager = LinearLayoutManager(context)
        rvCategoryList!!.layoutManager = linearLayoutManager
        val technicalInfoBradAdapter = TechnicalInfoBradAdapter(context!!)
        this.mAdapter = technicalInfoBradAdapter
        rvCategoryList!!.adapter = technicalInfoBradAdapter
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
        mAdapter!!.onKeySelectItemClickListener = object : OnKeySelectItemClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoBrandSelectFragment$$ExternalSyntheticLambda0
            // com.kkkcut.e20j.adapter.TechnicalInfoBradAdapter.OnKeySelectItemClickListener
            override fun onItemClick(i: Int) {
                this@TechnicalInfoBrandSelectFragment.m71x9c14395e(i)
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoBrandSelectFragment, reason: not valid java name */
    /* synthetic */ fun m71x9c14395e(i: Int) {
        val dataManufacturer = tempData!![i]
        var manufacturerName = dataManufacturer!!.manufacturerName
        if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(dataManufacturer.manufacturerNameCN)) {
            manufacturerName = dataManufacturer.manufacturerNameCN
        }
        goModel(tempData!![i]!!.manufacturerId, manufacturerName)
    }

    fun getManufacturers() {
            addDisposable(
                Observable.fromCallable {
                    val technicalInfoManufacturers: List<*> =
                        KeyInfoDaoManager.getInstance().technicalInfoManufacturers
                    technicalInfoManufacturers
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { obj ->
                    this@TechnicalInfoBrandSelectFragment.m70x814ceec5(obj as MutableList<*>)
                }, { this.dismissLoadingDialog() })
            )
        }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$getManufacturers$2$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoBrandSelectFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun m70x814ceec5(list: MutableList<*>?) {
        this.tempData = list as ArrayList<DataManufacturer>
        this.mDatas = list
        setData(list)
    }

    fun setData(list: List<DataManufacturer>) {
        mAdapter!!.setDatas(list)
        indexBar!!.setmSourceDatas(list).invalidate()
        mDecoration!!.setmDatas(list)
    }

    fun goModel(i: Int, str: String?) {
        start(TechnicalInfoModelSelectFragment.newInstance(i, str))
        hideSoftInput()
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        var manufacturerName: String
        this.tempData = ArrayList<DataManufacturer>()
        for (i in mDatas!!.indices) {
            val dataManufacturer = mDatas!![i]
            manufacturerName = if (MachineInfo.isChineseMachine()) {
                dataManufacturer!!.manufacturerNameCN
            } else {
                dataManufacturer!!.manufacturerName
            }
            if (!TextUtils.isEmpty(manufacturerName) && manufacturerName.lowercase(Locale.getDefault())
                    .startsWith(
                        editable.toString().lowercase(
                            Locale.getDefault()
                        )
                    )
            ) {
                tempData.add(dataManufacturer)
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
        return getString(R.string.technical_information)
    }

    companion object {
        const val TAG: String = "TechnicalInfoBrandSelectFragment"

        fun newInstance(): TechnicalInfoBrandSelectFragment {
            return TechnicalInfoBrandSelectFragment()
        }
    }
}
