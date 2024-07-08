package com.kkkcut.e20j.ui.fragment.keyselect

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.ModelSeries
import com.kkkcut.e20j.DbBean.ModelYear
import com.kkkcut.e20j.adapter.SeriesSelectAdapter
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
class SeriesSelectFragment() : BaseBackFragment() {
    private var mAdapter: SeriesSelectAdapter? = null
    private var manager: LinearLayoutManager? = null

    var rvCategoryList: RecyclerView? = null

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable?) {
    }

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
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        this.manager = linearLayoutManager
        rvCategoryList!!.setLayoutManager(linearLayoutManager)
        val seriesSelectAdapter: SeriesSelectAdapter = SeriesSelectAdapter((getContext())!!)
        this.mAdapter = seriesSelectAdapter
        rvCategoryList!!.setAdapter(seriesSelectAdapter)
        rvCategoryList!!.addItemDecoration(DividerItemDecoration(getContext(), 1))
        mAdapter!!.setOnItemChildClickListener(object :
            SeriesSelectAdapter.OnItemChildClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda1
            // com.kkkcut.e20j.adapter.SeriesSelectAdapter.OnItemChildClickListener
            override fun onItemChildClick(str: String?, modelSeries: ModelSeries?) {
                this@SeriesSelectFragment.m66xe5cd62fb(str, modelSeries)
            }
        })
        mAdapter!!.setOnItemClickListener(object : SeriesSelectAdapter.OnItemClickListener {
            // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda2
            // com.kkkcut.e20j.adapter.SeriesSelectAdapter.OnItemClickListener
            override fun onItemClick(view: View?, i: Int, str: String?, i2: Int) {
                this@SeriesSelectFragment.m67xebd12e5a(view, i, str, i2)
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$0$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    /* synthetic */ fun m66xe5cd62fb(str: String?, modelSeries: ModelSeries?) {
        start(
            newInstance(
                GoOperatBean(
                    (modelSeries)!!,
                    getArguments()!!.getString("title") + ">" + str
                )
            )
        )
        hideSoftInput()
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$initView$1$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    /* synthetic */ fun m67xebd12e5a(view: View?, i: Int, str: String?, i2: Int) {
        val linearLayout: LinearLayout = view!!.findViewById<View>(R.id.ll_space_a) as LinearLayout
        val childCount: Int = linearLayout.getChildCount()
        if (childCount == 1) {
            showSeries(view, i, str, i2)
        } else {
            (view.findViewById<View>(R.id.iv_arrow) as ImageView).setImageResource(R.drawable.arrow_key_select)
            linearLayout.removeViews(1, childCount - 1)
        }
    }

    private fun showSeries(view: View?, i: Int, str: String?, i2: Int) {
        // from class: com.kkkcut.e20j.ui.fragment.keyselect.SeriesSelectFragment$$ExternalSyntheticLambda6
// java.util.concurrent.Callable
        addDisposable(
            Observable.fromCallable({
                var modelSeries: kotlin.collections.MutableList<*>?
                modelSeries = KeyInfoDaoManager.getInstance().getModelSeries(i)
                modelSeries
            }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ obj: Any ->
                    this@SeriesSelectFragment.m69x57bdac18(i2, view, str, obj as List<*>)
                }, { th -> this.dismissLoadingDialog() }))

    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeries$4$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    @Throws(Exception::class)  /* synthetic */ fun m69x57bdac18(
        i: Int,
        view: View?,
        str: String?,
        list: List<*>
    ) {
        if (list.size == 0) {
            ToastUtil.showToast(R.string.no_data_was_found)
            return
        }
        val topLinearSmoothScroller: TopLinearSmoothScroller =
            TopLinearSmoothScroller(getActivity())
        topLinearSmoothScroller.setTargetPosition(i)
        manager!!.startSmoothScroll(topLinearSmoothScroller)
        (view!!.findViewById<View>(R.id.iv_arrow) as ImageView).setImageResource(R.drawable.arrow_bottom)
        var i2: Int = -1
        val linearLayout: LinearLayout = view.findViewById(R.id.ll_space_a)
        val it: Iterator<*> = list.iterator()
        var view2: View? = null
        while (it.hasNext()) {
            val modelSeries: ModelSeries = it.next() as ModelSeries
            if (modelSeries.getFK_KeyID() != i2) {
                view2 = getLayoutInflater().inflate(
                    R.layout.item_years_child_us,
                    linearLayout as ViewGroup?,
                    false
                )
                val textView: TextView = view2.findViewById(R.id.tv_title_id)
                if (MachineInfo.isE20Us(getContext())) {
                    textView.setText("Card")
                } else {
                    textView.setText(R.string.ic_card)
                }
                (view2.findViewById<View>(R.id.tv_ic_card) as TextView).setText(
                    modelSeries.getFK_KeyID().toString()
                )
                (view2.findViewById<View>(R.id.tv_cuts) as TextView).setText(modelSeries.getCuts())
                ResUpdateUtils.showKeyImage(
                    getContext(), modelSeries.getFK_KeyID(), view2.findViewById<View>(
                        R.id.iv_thumb
                    ) as ImageView?
                )
                linearLayout.addView(view2)
                i2 = modelSeries.getFK_KeyID()
            }
            val linearLayout2: LinearLayout = view2!!.findViewById(R.id.ll_series_container)
            val inflate: View =
                getLayoutInflater().inflate(R.layout.item_serie_us, null as ViewGroup?)
            (inflate.findViewById<View>(R.id.tv_series) as TextView).setText(modelSeries.getCodeSeries())
            val textView2: TextView? = inflate.findViewById(R.id.tv_nick_name)
            if (textView2 != null) {
                textView2.setText(modelSeries.getName())
            }
            val textView3: TextView = inflate.findViewById(R.id.tv_isn)
            val isn: String = modelSeries.getISN()
            textView3.setText(isn)
            linearLayout2.addView(inflate)
            inflate.setOnClickListener(View.OnClickListener({ view3: View? ->
                this@SeriesSelectFragment.m68x51b9e0b9(str, modelSeries, isn, view3)
            }))
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: lambda$showSeries$3$com-kkkcut-e20j-ui-fragment-keyselect-SeriesSelectFragment, reason: not valid java name */
    /* synthetic */ fun m68x51b9e0b9(
        str: String?,
        modelSeries: ModelSeries,
        str2: String?,
        view: View?
    ) {
        val str3: String
        val str4: String = getArguments()!!.getString("title") + ">" + str
        if (MachineInfo.isE20Us(getContext())) {
            str3 = str4 + "(Card:" + modelSeries.getFK_KeyID() + ")"
        } else {
            str3 = str4 + "--" + getString(R.string.ic_card) + ":" + modelSeries.getFK_KeyID()
        }
        start(newInstance(GoOperatBean(modelSeries, str3, str2)))
    }

    private fun getModelYears(i: Int) {
        addDisposable(
            Observable.fromCallable {
                val modelYears: List<*>
                modelYears = KeyInfoDaoManager.getInstance().getModelYears(i)
                modelYears
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { obj ->
                    this@SeriesSelectFragment.m65x3628abc1(
                        obj
                    )
                }, { this.dismissLoadingDialog() }
            )
        )
    }

    private fun byteToBitmap(bArr: ByteArray?): Bitmap? {
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.size, null)
        }
        return null
    }

    /* renamed from: setData, reason: merged with bridge method [inline-methods] */
    fun m65x3628abc1(list: List<ModelYear>) {
        mAdapter!!.datas = list
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
        fun newInstance(i: Int, i2: Int, str: String?): SeriesSelectFragment {
            val seriesSelectFragment: SeriesSelectFragment = SeriesSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("modelID", i)
            bundle.putInt("category", i2)
            bundle.putString("title", str)
            seriesSelectFragment.setArguments(bundle)
            return seriesSelectFragment
        }
    }
}
