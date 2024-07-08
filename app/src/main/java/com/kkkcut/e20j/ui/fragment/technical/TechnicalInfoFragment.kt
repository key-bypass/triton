package com.kkkcut.e20j.ui.fragment.technical

import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kkkcut.e20j.DbBean.technical.DataList
import com.kkkcut.e20j.DbBean.technical.DataListBean
import com.kkkcut.e20j.adapter.TechnicalInfoDataAdapter
import com.kkkcut.e20j.customView.ItemDecorationPowerful
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

/* loaded from: classes.dex */
class TechnicalInfoFragment() : BaseBackFragment() {
    private val names = arrayOf(
        "遥控拷贝",
        "遥控类型",
        "芯片拷贝",
        "是否拆读",
        "钥匙匹配",
        "OBD位置",
        "码片类型",
        "钥匙坯号",
        "锁片差位",
        "匹配设备",
        "防盗类型",
        "开锁工具",
        "匹配密码",
        "芯片型号",
        "遥控生成",
        "遥控匹配",
        "芯片生成",
        "注意事项",
        "密码获取",
        "零件位置",
        "开锁方向"
    )

    var rvDataList: RecyclerView? = null
    private var technicalInfoDataAdapter: TechnicalInfoDataAdapter? = null

    var tvInfo: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_tehnical_info
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.technical_information)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        tvInfo!!.text = arguments!!.getString("title")
        val gridLayoutManager = GridLayoutManager(context, 5)
        gridLayoutManager.orientation = 1
        rvDataList!!.layoutManager = gridLayoutManager
        val technicalInfoDataAdapter = TechnicalInfoDataAdapter()
        this.technicalInfoDataAdapter = technicalInfoDataAdapter
        rvDataList!!.adapter = technicalInfoDataAdapter
        rvDataList!!.addItemDecoration(ItemDecorationPowerful(2))
        datas
    }

    private val datas: Unit
        get() {
            addDisposable(Observable.fromCallable
            // java.util.concurrent.Callable
            {
                // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.4
                /* JADX WARN: Can't rename method to resolve collision */
                KeyInfoDaoManager.getInstance().getTechnicalDataList(
                    this@TechnicalInfoFragment.arguments!!
                        .getInt("yearID")
                )
            }.map(
                // io.reactivex.functions.Function
                Function<DataList, List<DataListBean>> { dataList ->

                    // from class: com.kkkcut.e20j.ui.fragment.technical.TechnicalInfoFragment.3
                    val arrayList = ArrayList<DataListBean>()
                    for (i in names.indices) {
                        arrayList.add(DataListBean(names[i], dataList.getColoumn(i)))
                    }
                    arrayList
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list ->
                    if (this@TechnicalInfoFragment.technicalInfoDataAdapter != null) {
                        technicalInfoDataAdapter!!.setNewData(list)
                    }
                },
                { }, {this.dismissLoadingDialog()})
            )
        }

    companion object {
        fun newInstance(i: Int, str: String?): TechnicalInfoFragment {
            val bundle = Bundle()
            bundle.putInt("yearID", i)
            bundle.putString("title", str)
            val technicalInfoFragment = TechnicalInfoFragment()
            technicalInfoFragment.arguments = bundle
            return technicalInfoFragment
        }
    }
}
