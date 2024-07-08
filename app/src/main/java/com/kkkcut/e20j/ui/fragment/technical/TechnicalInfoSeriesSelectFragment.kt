package com.kkkcut.e20j.p005ui.fragment.technical

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.technical.DataModelSeries
import com.kkkcut.e20j.DbBean.technical.DataModelSeriesYear
import com.kkkcut.e20j.adapter.TechnicalSeriesAdapter
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable
import java.util.function.Consumer

/* loaded from: classes.dex */
class TechnicalInfoSeriesSelectFragment() : BaseBackFragment() {
    private var mAdapter: TechnicalSeriesAdapter? = null

    var rvCategoryList: RecyclerView? = null

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_series
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        getModelSeries(getArguments()!!.getInt("modelID"))
    }

    private fun initView() {
        rvCategoryList!!.setLayoutManager(LinearLayoutManager(getContext()))
        val technicalSeriesAdapter: TechnicalSeriesAdapter = TechnicalSeriesAdapter(getContext())
        this.mAdapter = technicalSeriesAdapter
        rvCategoryList!!.setAdapter(technicalSeriesAdapter)
        rvCategoryList!!.addItemDecoration(DividerItemDecoration(getContext(), 1))
        mAdapter!!.setOnItemClickListener(object : TechnicalSeriesAdapter.OnItemClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoSeriesSelectFragment$$ExternalSyntheticLambda1
            // com.kkkcut.e20j.adapter.TechnicalSeriesAdapter.OnItemClickListener
            override fun onItemClick(view: View, i: Int, str: String) {
                this@TechnicalInfoSeriesSelectFragment.m80x6fd42498(view, i, str)
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    /* synthetic */ fun m80x6fd42498(view: View, i: Int, str: String) {
        val linearLayout: LinearLayout = view.findViewById(R.id.container)
        val imageView: ImageView = view.findViewById(R.id.iv_arrow)
        val childCount: Int = linearLayout.getChildCount()
        if (childCount == 1) {
            imageView.setImageResource(R.drawable.arrow_bottom)
            showSeriesYear(view, i, str)
        } else {
            imageView.setImageResource(R.drawable.arrow_key_select)
            linearLayout.removeViews(1, childCount - 1)
        }
    }

    private fun getModelSeries(i: Int) {
        addDisposable(
            Observable.fromCallable {
                val technicalInfoModelSeries: List<*>
                technicalInfoModelSeries =
                    KeyInfoDaoManager.getInstance().getTechnicalInfoModelSeries(i)
                technicalInfoModelSeries
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ obj ->
                    this@TechnicalInfoSeriesSelectFragment.m79x3621488f(obj)

                    }, {this.dismissLoadingDialog()})
        )
    }

    private fun showSeriesYear(view: View, i: Int, str: String) {
        addDisposable(
            Observable.fromCallable {
                val technicalInfoModelSeriesYear: List<*>
                technicalInfoModelSeriesYear =
                    KeyInfoDaoManager.getInstance().getTechnicalInfoModelSeriesYear(i)
                technicalInfoModelSeriesYear
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { obj: List<*> ->
                    this@TechnicalInfoSeriesSelectFragment.m82x17da8d39(view, str, obj)
                }, { this.dismissLoadingDialog() })
        )
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeriesYear$5$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    @Throws(Exception::class)  /* synthetic */ fun m82x17da8d39(
        view: View,
        str: String,
        list: List<*>
    ) {
        if (list.isEmpty()) {
            ToastUtil.showToast(R.string.no_data_was_found)
            return
        }
        val linearLayout: LinearLayout = view.findViewById(R.id.container)
        val inflate: View = getLayoutInflater().inflate(
            R.layout.item_technical_info_child,
            linearLayout as ViewGroup?,
            false
        )
        linearLayout.addView(inflate)
        val it: Iterator<*> = list.iterator()
        while (it.hasNext()) {
            val dataModelSeriesYear: DataModelSeriesYear = it.next() as DataModelSeriesYear
            val linearLayout2: LinearLayout = inflate.findViewById(R.id.ll_series_container)
            val inflate2: View =
                getLayoutInflater().inflate(R.layout.item_technical_info_years, null as ViewGroup?)
            (inflate2.findViewById<View>(R.id.tv_year) as TextView).setText(dataModelSeriesYear.getSeriesYearName())
            linearLayout2.addView(inflate2)
            inflate2.setOnClickListener(View.OnClickListener({ view2: View? ->
                this@TechnicalInfoSeriesSelectFragment.m81x50cea638(str, dataModelSeriesYear, view2)
            }))
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeriesYear$4$com-kkkcut-e20j-ui-fragment-technical-TechnicalInfoSeriesSelectFragment */
    /* synthetic */ fun m81x50cea638(
        str: String,
        dataModelSeriesYear: DataModelSeriesYear,
        view: View?
    ) {
        val string: String? = getArguments()!!.getString("title")
        Log.i(TAG, "showSeriesYear: " + string)
        start(
            TechnicalInfoFragment.Companion.newInstance(
                dataModelSeriesYear.getModelSeriesYearID(),
                (string + ">" + str) + ">" + dataModelSeriesYear.getSeriesYearName()
            )
        )
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    fun m79x3621488f(list: List<DataModelSeries?>?) {
        mAdapter!!.setDatas(list)
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroyView() {
        super.onDestroyView()
        hideSoftInput()
    }

    // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getArguments()!!.getString("title")
    }

    companion object {
        fun newInstance(i: Int, str: String?): TechnicalInfoSeriesSelectFragment {
            val technicalInfoSeriesSelectFragment: TechnicalInfoSeriesSelectFragment =
                TechnicalInfoSeriesSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("modelID", i)
            bundle.putString("title", str)
            technicalInfoSeriesSelectFragment.setArguments(bundle)
            return technicalInfoSeriesSelectFragment
        }
    }
}