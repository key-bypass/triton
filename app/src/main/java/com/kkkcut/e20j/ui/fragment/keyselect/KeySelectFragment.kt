package com.kkkcut.e20j.ui.fragment.keyselect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.DbBean.Model
import com.kkkcut.e20j.DbBean.ModelYear
import com.kkkcut.e20j.adapter.KeySelectAdapter
import com.kkkcut.e20j.base.BaseFragment
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentKeyselectBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class KeySelectFragment() : BaseFragment(), BaseQuickAdapter.OnItemClickListener {
    var binding: FragmentKeyselectBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentKeyselectBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyselect
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL)
        binding!!.rvCategoryList.setLayoutManager(linearLayoutManager)
        manufacturers
    }

    private val manufacturers: Unit
        get() {
            arguments!!.getInt("category")
        }

    private fun getModels(i: Int) {
        addDisposable(
            Observable.fromCallable { KeyInfoDaoManager.getInstance().getModels(i) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list: List<Model> ->
                    val keySelectAdapter = KeySelectAdapter<Model>(list)
                    keySelectAdapter.onItemClickListener = this
                    binding!!.rvCategoryList.setAdapter(keySelectAdapter)
                }, { th -> this.dismissLoadingDialog() }
            ))

    }

    private fun getModelYears(i: Int) {
        addDisposable(
            Observable.fromCallable { KeyInfoDaoManager.getInstance().getModelYears(i) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ list: List<ModelYear> ->
                val keySelectAdapter = KeySelectAdapter<ModelYear>(list)
                keySelectAdapter.onItemClickListener = this
                binding!!.rvCategoryList.setAdapter(keySelectAdapter)
            }, {this.dismissLoadingDialog()})
        )
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val itemViewType: Int = baseQuickAdapter.getItemViewType(i)
        if (itemViewType == 0) {
            getModels((baseQuickAdapter.data[i] as Manufacturer).manufacturerId)
        } else {
            if (itemViewType != 1) {
                return
            }
            getModelYears((baseQuickAdapter.data[i] as Model).modelID)
        }
    }

    companion object {
        fun newInstance(i: Int): KeySelectFragment {
            val bundle = Bundle()
            bundle.putInt("category", i)
            val keySelectFragment = KeySelectFragment()
            keySelectFragment.setArguments(bundle)
            return keySelectFragment
        }
    }
}
