package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.BittingCode
import com.kkkcut.e20j.adapter.CodeFindToothAdapter
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.NetUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.eventbus.InputFinishBean
import com.kkkcut.e20j.bean.gsonBean.CodeFindToothRes
import com.kkkcut.e20j.dao.ToothCodeDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.activity.FrameActivity
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentCodeFindToothBinding
import com.kkkcut.e20j.utils.GetUUID
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class CodeFindToothFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var adapter: CodeFindToothAdapter? = null

    private var toothCodeDaoManager: ToothCodeDaoManager? = null

    var binding: FragmentCodeFindToothBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentCodeFindToothBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_code_find_tooth
    }

    // com.kkkcut.e20j.androidquick.p004ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val string: String? = getArguments()!!.getString("series")
        if (!TextUtils.isEmpty(string)) {
            binding!!.tvSeries.setText(string)
        }
        this.toothCodeDaoManager = ToothCodeDaoManager(getArguments()!!.getInt("keyID"))
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL)
        binding!!.rvToothList.setLayoutManager(linearLayoutManager)
        val codeFindToothAdapter: CodeFindToothAdapter = CodeFindToothAdapter()
        this.adapter = codeFindToothAdapter
        codeFindToothAdapter.setOnItemClickListener(this)
        binding!!.rvToothList.setAdapter(this.adapter)
        binding!!.rvToothList.addItemDecoration(DividerItemDecoration(requireContext(), 1))
        (activity as FrameActivity?)!!.bindToEditor(binding!!.etSearch, 0)
    }

    private fun doSearch(str: String, str2: String?) {
        addDisposable(
            Observable.fromCallable<List<BittingCode>> {
                toothCodeDaoManager!!.codeFindTooth(str, str2) as List<BittingCode>
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                        if (list.isEmpty()) {
                            adapter!!.setNewData(ArrayList<BittingCode>())
                            ToastUtil.showToast(R.string.no_data_was_found)
                        } else {
                            adapter!!.setNewData(list)
                        }

                }, {
                    ToastUtil.showToast(R.string.no_data_was_found)
                }, { dismissLoadingDialog() }))
    }

    fun onViewClicked(view: View) {
        val trim: String = binding!!.etSearch.getText().toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim)) {
            ToastUtil.showToast(R.string.not_null)
            return
        }
        var string: String? = arguments!!.getString(ISN)
        if (TextUtils.isEmpty(string)) {
            string = "0"
        }
        when (view.getId()) {
            R.id.bt_search_offline -> {
                doSearch(trim, string)
                hideSoftInput()
                binding!!.etSearch.clearFocus()
                return
            }

            R.id.bt_search_online -> {
                doSearchOnline(trim, string)
                hideSoftInput()
                binding!!.etSearch.clearFocus()
                return
            }

            else -> return
        }
    }

    private fun doSearchOnline(str: String, str2: String?) {
        var str2: String? = str2
        if (!NetUtil.isNetworkConnected(getContext())) {
            ToastUtil.showToast(R.string.network_unavailable)
            return
        }
        showLoadingDialog(getString(R.string.waitting))
        if (TextUtils.isEmpty(str2)) {
            str2 = "0"
        }
        addDisposable(
            RetrofitManager.getInstance().createApi<Apis>(Apis::class.java)
                .getToothByCode(
                    TUitls.codeFindToothParam(
                        str,
                        getArguments()!!.getInt("keyID"),
                        GetUUID.getUUID(),
                        SPUtils.getString("series"),
                        str2
                    )
                ).map { codeFindToothRes: CodeFindToothRes ->
                    if (TextUtils.equals(codeFindToothRes.code, "0")) {
                        val arrayList = ArrayList<BittingCode>()
                        val data: List<CodeFindToothRes.DataBean> = codeFindToothRes.data
                        for (dataBean: CodeFindToothRes.DataBean in data) {
                            val bittingCode = BittingCode()
                            bittingCode.bitting = dataBean.bitting
                            bittingCode.isn = dataBean.isn
                            arrayList.add(bittingCode)
                        }
                        return@map arrayList
                        throw Exception(this.getString(R.string.no_data_was_found))
                    }
                    throw Exception(codeFindToothRes.msg)
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doFinally {
                    this.dismissLoadingDialog()
                }.subscribe({ list ->
                    if (list.size == 0) {
                        ToastUtil.showToast(R.string.no_data_was_found)
                    }
                    adapter!!.setNewData(list)
                }, { th -> ToastUtil.showToast(th.message) }, { dismissLoadingDialog() }))
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val bittingCode: BittingCode = baseQuickAdapter.data[i] as BittingCode? ?: return
        val charArray: CharArray = bittingCode.bitting.toCharArray()
        var str = ""
        for (i2 in charArray.indices) {
            if (i2 == charArray.size - 1) {
                str = str + charArray[i2] + ";"
            } else if ("-".contains(charArray[i2].toString())) {
                str = str.substring(0, str.lastIndexOf(",")) + ";"
            } else {
                str = str + charArray[i2] + ","
            }
        }
        EventBus.getDefault().post(EventCenter<Any?>(2, InputFinishBean(false, str)))
        onBack()
    }

    // com.kkkcut.e20j.p005ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.code)
    }

    companion object {
        val ISN: String = "isn"
        val KEY_ID: String = "keyID"
        val SERIES: String = "series"
        fun newInstance(i: Int, str: String?, str2: String?): CodeFindToothFragment {
            val codeFindToothFragment: CodeFindToothFragment = CodeFindToothFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("keyID", i)
            bundle.putString("series", str)
            bundle.putString(ISN, str2)
            codeFindToothFragment.setArguments(bundle)
            return codeFindToothFragment
        }
    }
}