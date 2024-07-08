package com.kkkcut.e20j.ui.fragment.keyselect

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.Model
import com.kkkcut.e20j.adapter.ModelSelectAdapter
import com.kkkcut.e20j.adapter.OnKeySelectItemClickListener
import com.kkkcut.e20j.customView.indexlib.suspension.SuspensionDecoration
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentKeyselectBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class ModelSelectFragment() : BaseBackFragment() {
    var binding: FragmentKeyselectBinding? = null
    private var mAdapter: ModelSelectAdapter? = null
    private var mDatas: List<Model>? = null
    private var mDecoration: SuspensionDecoration? = null

    private var tempData: MutableList<Model> = ArrayList()

    companion object {
        val TAG: String = "TechnicalInfoBrandSelectFragment"

        fun newInstance(i: Int, i2: Int, str: String?): ModelSelectFragment {
            val modelSelectFragment: ModelSelectFragment = ModelSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putInt("brandID", i2)
            bundle.putInt("category", i)
            bundle.putString("title", str)
            modelSelectFragment.setArguments(bundle)
            return modelSelectFragment
        }
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentKeyselectBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect_child
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        this.getModels(arguments!!.getInt("brandID"))
    }

    private fun initView() {
        if ((arguments!!.getInt("category") == 3) || (arguments!!.getInt("category") == 4) || (getArguments()!!.getInt(
                "category"
            ) == 5)
        ) {
            binding!!.etSearch.setHint(getString(R.string.please_input_the_key_name))
        } else {
            binding!!.etSearch.setHint(getString(R.string.model))
        }
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(requireContext())
        binding!!.rvCategoryList.setLayoutManager(linearLayoutManager)
        val modelSelectAdapter: ModelSelectAdapter = ModelSelectAdapter(requireContext())
        this.mAdapter = modelSelectAdapter
        binding!!.rvCategoryList.setAdapter(modelSelectAdapter)
        val suspensionDecoration: SuspensionDecoration = SuspensionDecoration(requireContext())
        this.mDecoration = suspensionDecoration
        binding!!.rvCategoryList.addItemDecoration(suspensionDecoration)
        binding!!.rvCategoryList.addItemDecoration(DividerItemDecoration(requireContext(), 1))
        binding!!.indexBar.setmPressedShowTextView(binding!!.tvSideBarHint).setNeedRealIndex(true)
            .setmLayoutManager(linearLayoutManager)

        mAdapter!!.onKeySelectItemClickListener = object : OnKeySelectItemClickListener {
            override fun onItemClick(i: Int) {
                val string: String? =
                    arguments!!.getString("title")
                val model: Model? = tempData[i]
                var modelName: String = model!!.modelName
                if (MachineInfo.isChineseMachine() && !TextUtils.isEmpty(model.modelName_CN)) {
                    modelName = model.modelName_CN
                }
                val modelSelectFragment: ModelSelectFragment = this@ModelSelectFragment
                modelSelectFragment.goSeries(
                    modelSelectFragment.tempData.get(i)!!.modelID,
                    "$string>$modelName"
                )
            }
        }
    }

    fun goSeries(i: Int, str: String?) {
        start(
            SeriesSelectFragment.Companion.newInstance(
                i,
                getArguments()!!.getInt("category"),
                str
            )
        )
        hideSoftInput()
    }

    fun getModels(i: Int) {
        addDisposable(
            Observable.fromCallable { KeyInfoDaoManager.getInstance().getModels(i) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ list: MutableList<Model> ->
                this.tempData = list
                this.mDatas = list
                setData(list)
            },{this.dismissLoadingDialog()} )
        )
    }

    fun setData(list: List<Model>) {
        mAdapter!!.datas = list
        binding!!.indexBar.setmSourceDatas(list).invalidate()
        mDecoration!!.setmDatas(list)
    }

    fun afterTextChanged(editable: Editable) {
        var modelName: String
        this.tempData = ArrayList<Model>()
        for (i in mDatas!!.indices) {
            val model: Model? = mDatas!![i]
            if (MachineInfo.isChineseMachine()) {
                modelName = model!!.modelName_CN
            } else {
                modelName = model!!.modelName
            }
            if (!TextUtils.isEmpty(modelName) && modelName.lowercase(Locale.getDefault())
                    .startsWith(
                        editable.toString().lowercase(
                            Locale.getDefault()
                        )
                    )
            ) {
                tempData.add(model)
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


}
