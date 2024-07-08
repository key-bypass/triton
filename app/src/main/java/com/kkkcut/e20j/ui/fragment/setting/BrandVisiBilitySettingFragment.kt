package com.kkkcut.e20j.ui.fragment.setting

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.Manufacturer
import com.kkkcut.e20j.DbBean.userDB.ManufacturerHidden
import com.kkkcut.e20j.adapter.BrandVisibilityAdapter
import com.kkkcut.e20j.customView.indexlib.IndexBar.widget.IndexBar
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class BrandVisiBilitySettingFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    var hideBrandAdapter: BrandVisibilityAdapter? = null

    var indexBarBrandHide: IndexBar? = null

    var indexBarBrandShow: IndexBar? = null

    var rvBrandHidden: RecyclerView? = null

    var rvBrandShow: RecyclerView? = null
    var showBrandAdapter: BrandVisibilityAdapter? = null

    var tvSideBarHintBradHide: TextView? = null

    var tvSideBarHintBradShow: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_brand_visibility_settting
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.showBrandAdapter = BrandVisibilityAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = 1
        rvBrandShow!!.layoutManager = linearLayoutManager
        rvBrandShow!!.adapter = this.showBrandAdapter
        showBrandAdapter!!.onItemClickListener = this
        rvBrandShow!!.addItemDecoration(
            DividerItemDecoration(
                context, 1
            )
        )
        indexBarBrandShow!!.setmPressedShowTextView(this.tvSideBarHintBradShow)
            .setNeedRealIndex(true).setmLayoutManager(linearLayoutManager)
        this.hideBrandAdapter = BrandVisibilityAdapter()
        val linearLayoutManager2 = LinearLayoutManager(context)
        linearLayoutManager2.orientation = 1
        rvBrandHidden!!.layoutManager = linearLayoutManager2
        rvBrandHidden!!.adapter = this.hideBrandAdapter
        hideBrandAdapter!!.onItemClickListener = this
        rvBrandHidden!!.addItemDecoration(
            DividerItemDecoration(
                context, 1
            )
        )
        indexBarBrandHide!!.setmPressedShowTextView(this.tvSideBarHintBradHide)
            .setNeedRealIndex(true).setmLayoutManager(linearLayoutManager2)
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onUserVisible() {
        manufacturer
    }

    val manufacturer: Unit
        /* JADX INFO: Access modifiers changed from: private */
        get() {
            addDisposable(Observable.fromCallable(
                // java.util.concurrent.Callable
                Callable {
                    // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.5
                    UserDataDaoManager.getInstance(this@BrandVisiBilitySettingFragment.context).manufacturerHidden
                }).subscribeOn(
                Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread())
                .doOnNext
                { list ->
                    if (this@BrandVisiBilitySettingFragment.hideBrandAdapter != null) {
                        hideBrandAdapter!!.setNewData(list)
                        indexBarBrandHide!!.setmSourceDatas(list).invalidate()
                    }
                }.observeOn(Schedulers.io()).map
                { list ->
                    val arrayList = ArrayList<Int>()
                    val it = list.iterator()
                    while (it.hasNext()) {
                        arrayList.add(it.next().manufacturerId)
                    }
                    arrayList
                }.map
                { list ->
                    // from class: com.kkkcut.e20j.ui.fragment.setting.BrandVisiBilitySettingFragment.2
                    Log.i(TAG, "Thread3: " + Thread.currentThread().name)
                    KeyInfoDaoManager.getInstance().getManufacturersExceptKeys(list)
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                        if (this@BrandVisiBilitySettingFragment.showBrandAdapter != null) {
                            showBrandAdapter!!.setNewData(list)
                            indexBarBrandShow!!.setmSourceDatas(list).invalidate()
                    }
                }, { th -> this.dismissLoadingDialog() })
            )
        }

    fun onViewClicked(view: View) {
        val id = view.id
        if (id == R.id.iv_hide) {
            hide()
        } else {
            if (id != R.id.iv_show) {
                return
            }
            show()
        }
    }

    private fun show() {
        val data = hideBrandAdapter!!.data
        val arrayList = ArrayList<ManufacturerHidden>()
        for (manufacturer: Manufacturer in data) {
            if (manufacturer.isChecked()) {
                arrayList.add(ManufacturerHidden(manufacturer))
            }
        }
        Observable.fromIterable(data).subscribeOn(Schedulers.io())
            .filter { manufacturer2 ->
                manufacturer2.isChecked()
            }.map { manufacturer2 ->
                ManufacturerHidden(manufacturer2)
            }.doOnNext { manufacturerHidden ->
                UserDataDaoManager.getInstance(this@BrandVisiBilitySettingFragment.context)
                    .showManufacturer(manufacturerHidden)
            }.observeOn(AndroidSchedulers.mainThread()).doOnComplete { this@BrandVisiBilitySettingFragment.manufacturer }
            .subscribe()
    }

    private fun hide() {
        val data = showBrandAdapter!!.data
        val arrayList = ArrayList<ManufacturerHidden>()
        for (manufacturer: Manufacturer in data) {
            Log.i(
                TAG,
                "name: " + manufacturer.manufacturerName + "--ischecked:" + manufacturer.isChecked()
            )
            if (manufacturer.isChecked()) {
                arrayList.add(ManufacturerHidden(manufacturer))
            }
        }
        Observable.fromIterable(data).subscribeOn(Schedulers.io())
            .filter { manufacturer2 ->
                manufacturer2.isChecked()
            }.map { manufacturer2 ->
                ManufacturerHidden(manufacturer2)
            }.doOnNext { manufacturerHidden ->
                UserDataDaoManager.getInstance(this@BrandVisiBilitySettingFragment.context)
                    .hideManufacturer(manufacturerHidden)

            }.observeOn(AndroidSchedulers.mainThread()).doOnComplete { this@BrandVisiBilitySettingFragment.manufacturer }
            .subscribe()
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val manufacturer = baseQuickAdapter.data[i] as Manufacturer
        val checkBox = view.findViewById<View>(R.id.checkbox_brand_visibility) as CheckBox
        manufacturer.setChecked(!checkBox.isChecked)
        checkBox.performClick()
    }

    companion object {
        fun newInstance(): BrandVisiBilitySettingFragment {
            val bundle = Bundle()
            val brandVisiBilitySettingFragment = BrandVisiBilitySettingFragment()
            brandVisiBilitySettingFragment.arguments = bundle
            return brandVisiBilitySettingFragment
        }
    }
}
