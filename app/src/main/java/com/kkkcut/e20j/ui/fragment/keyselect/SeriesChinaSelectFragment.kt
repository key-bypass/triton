package com.kkkcut.e20j.ui.fragment.keyselect

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina
import com.kkkcut.e20j.DbBean.china.ModelYearChina
import com.kkkcut.e20j.adapter.SeriesChinaSelectAdapter
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ResUpdateUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class SeriesChinaSelectFragment() : BaseBackFragment() {
    private var mAdapter: SeriesChinaSelectAdapter? = null

    var rvCategoryList: RecyclerView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_series
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        getModelYears(getArguments()!!.getInt("modelID"))
    }

    private fun initView() {
        rvCategoryList!!.setLayoutManager(LinearLayoutManager(getContext()))
        val seriesChinaSelectAdapter: SeriesChinaSelectAdapter =
            SeriesChinaSelectAdapter(getContext())
        this.mAdapter = seriesChinaSelectAdapter
        rvCategoryList!!.setAdapter(seriesChinaSelectAdapter)
        rvCategoryList!!.addItemDecoration(DividerItemDecoration(getContext(), 1))
        mAdapter!!.setOnItemClickListener(SeriesChinaSelectAdapter.OnItemClickListener({ view: View, i: Int, str: String ->
            this@SeriesChinaSelectFragment.m62xa03c1a74(view, i, str)
        }))
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    /* synthetic */ fun m62xa03c1a74(view: View, i: Int, str: String) {
        val linearLayout: LinearLayout = view.findViewById(R.id.ll_space_a)
        val imageView: ImageView = view.findViewById(R.id.iv_arrow)
        val childCount: Int = linearLayout.getChildCount()
        if (childCount == 1) {
            imageView.setImageResource(R.drawable.arrow_bottom)
            showSeries(view, i, str)
        } else {
            imageView.setImageResource(R.drawable.arrow_key_select)
            linearLayout.removeViews(1, childCount - 1)
        }
    }

    private fun getModelYears(i: Int) {
        addDisposable(
            Observable.fromCallable {
                val chinaModelYears = KeyInfoDaoManager.getInstance().getChinaModelYears(i)
                chinaModelYears
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    mAdapter!!.setDatas(list);
                })
    }

    private fun showSeries(view: View, i: Int, str: String) {
        Log.d(
            TAG,
            "showSeries() called with: itemView = [" + view + "], yearID = [" + i + "], years = [" + str + "]"
        )
        addDisposable(
            Observable.fromCallable {
                val chinaModelSeries: List<*>
                chinaModelSeries = KeyInfoDaoManager.getInstance().getChinaModelSeries(i)
                chinaModelSeries
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ obj: List<*> ->
                this@SeriesChinaSelectFragment.m64xf54d7378(view, str, obj)
            }, {this.dismissLoadingDialog()})
        )
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeries$5$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun m64xf54d7378(
        view: View,
        str: String,
        list: List<*>
    ) {
        if (list.size == 0) {
            ToastUtil.showToast(R.string.no_data_was_found)
            return
        }
        var i: Int = -1
        val linearLayout: LinearLayout = view.findViewById(R.id.ll_space_a)
        val it: Iterator<*> = list.iterator()
        var view2: View? = null
        while (it.hasNext()) {
            val modelSeriesChina: ModelSeriesChina = it.next() as ModelSeriesChina
            if (modelSeriesChina.getFK_KeyID() != i.toLong()) {
                view2 = getLayoutInflater().inflate(
                    R.layout.item_years_china_child,
                    linearLayout as ViewGroup?,
                    false
                )
                val textView: TextView = view2.findViewById(R.id.tv_detail)
                var str2: String = ""
                if (!TextUtils.isEmpty(modelSeriesChina.getName())) {
                    str2 = "" + modelSeriesChina.getName() + " "
                }
                if (!TextUtils.isEmpty(modelSeriesChina.getCuts())) {
                    str2 = str2 + modelSeriesChina.getCuts() + "齿 "
                }
                if (!TextUtils.isEmpty(modelSeriesChina.getKeyChinaNum())) {
                    str2 = str2 + modelSeriesChina.getKeyChinaNum() + "钥匙头"
                }
                textView.setText(str2)
                linearLayout.addView(view2)
                i = modelSeriesChina.getFK_KeyID().toInt()
            }
            val linearLayout2: LinearLayout = view2!!.findViewById(R.id.ll_series_container)
            val inflate: View =
                getLayoutInflater().inflate(R.layout.item_series_china, null as ViewGroup?)
            ResUpdateUtils.showKeyImage(
                getContext(), modelSeriesChina.getFK_KeyID().toInt(), inflate.findViewById<View>(
                    R.id.iv_thumb
                ) as ImageView?
            )
            (inflate.findViewById<View>(R.id.tv_clamp) as TextView).setText(
                modelSeriesChina.getClampKeyBasicData()
                    .getClampNum() + " " + modelSeriesChina.getClampKeyBasicData().getClampSide()
            )
            val textView2: TextView = inflate.findViewById(R.id.tv_align)
            if (modelSeriesChina.getKeyBasicData().getAlign() == 0) {
                textView2.setText(R.string.shoulder)
            } else {
                textView2.setText(R.string.tip)
            }
            linearLayout2.addView(inflate)
            inflate.setOnClickListener(object : View.OnClickListener {
                // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesChinaSelectFragment$$ExternalSyntheticLambda0
                // android.view.View.OnClickListener
                override fun onClick(view3: View) {
                    this@SeriesChinaSelectFragment.m63xa78dfb77(str, modelSeriesChina, view3)
                }
            })
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeries$4$com-kkkcut-e20j-ui-fragment-keyselect-SeriesChinaSelectFragment, reason: not valid java name */
    /* synthetic */ fun m63xa78dfb77(str: String, modelSeriesChina: ModelSeriesChina, view: View?) {
        start(
            newInstance(
                GoOperatBean(
                    modelSeriesChina,
                    (getArguments()!!.getString("title") + ">" + str) + "--ID:" + modelSeriesChina.getFK_KeyID()
                )
            )
        )
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    fun m61x488f7d6a(list: List<ModelYearChina?>?) {
        mAdapter!!.setDatas(list)
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
        fun newInstance(i: Int, i2: Int, str: String?): SeriesChinaSelectFragment {
            val seriesChinaSelectFragment: SeriesChinaSelectFragment = SeriesChinaSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("modelID", i)
            bundle.putInt("category", i2)
            bundle.putString("title", str)
            seriesChinaSelectFragment.setArguments(bundle)
            return seriesChinaSelectFragment
        }
    }
}
