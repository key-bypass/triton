package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.adapter.FavoriteAdapter
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.bean.gsonBean.GetTestData
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.driver.communication.DataBean
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentFavoriteBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Random
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class FavoriteFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener,
    BaseQuickAdapter.OnItemChildClickListener {
    var binding: FragmentFavoriteBinding? = null
    var adapter: FavoriteAdapter? = null


    private var pageIndex: Int = 0

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentFavoriteBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_favorite
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL)
        binding!!.rvUserData.setLayoutManager(linearLayoutManager)
        binding!!.rvUserData.addItemDecoration(DividerItemDecoration(getContext(), 1))
        val favoriteAdapter: FavoriteAdapter = FavoriteAdapter()
        this.adapter = favoriteAdapter
        binding!!.rvUserData.setAdapter(favoriteAdapter)
        getDataList(0, 50, false)
        if (MyApplication.getInstance().isShowRealDepth()) {
            binding!!.btGetTestData.setVisibility(View.VISIBLE)
        }

        adapter!!.setOnLoadMoreListener(RequestLoadMoreListener({
            pageIndex++
            Log.d(TAG, "onLoadMoreRequested() called" + this@FavoriteFragment.pageIndex)
            val favoriteFragment: FavoriteFragment = this@FavoriteFragment
            favoriteFragment.getDataList(favoriteFragment.pageIndex, 50, false)
        }), binding!!.rvUserData)
        adapter!!.setOnItemChildClickListener(this)
        adapter!!.setOnItemClickListener(this)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun getDataList(i: Int, i2: Int, z: Boolean) {
        val str: String = binding!!.etSearch.getText().toString().trim { it <= ' ' }
        val subscribe: Disposable = Observable.fromCallable {
            UserDataDaoManager.getInstance(requireContext()
            ).getCollection(i, i2, str)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ list: List<CollectionData> ->
            if (z) {
                adapter!!.setNewData(list)
            } else {
                adapter!!.addData(list)
            }
            if (list.size < 50) {
                adapter!!.loadMoreEnd(false)
            } else {
                adapter!!.loadMoreComplete()
            }
        }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val id: Int = view.getId()
        if (id == R.id.iv_delete) {
            UserDataDaoManager.getInstance(getContext())
                .deleteCollection(baseQuickAdapter.getData().get(i) as CollectionData?)
            baseQuickAdapter.remove(i)
        } else {
            if (id != R.id.iv_edit) {
                return
            }
            showEditDialog(
                baseQuickAdapter.getData().get(i),
                (view.getParent().getParent() as View).findViewById(
                    R.id.tv_remark
                )
            )
        }
    }

    private fun showEditDialog(obj: Any, textView: TextView) {
        val remark: String = (obj as CollectionData).getRemark()
        val editDialog: EditDialog = EditDialog(getContext())
        editDialog.setTip(getString(R.string.enter_remarks))
        if (!TextUtils.isEmpty(remark)) {
            editDialog.setEditTextContent(remark)
        }

        editDialog.setDialogBtnCallback { str: String ->
            if (!TextUtils.isEmpty(str) && (str != remark)) {
                textView.text = str
                val collectionData: CollectionData = obj
                collectionData.remark = str
                UserDataDaoManager.getInstance(this@FavoriteFragment.requireContext())
                    .collectKey(collectionData)
            }
        }
        editDialog.show()
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        arguments!!.getInt(TYPE)
        start(
            KeyOperateFragment.newInstance(
                GoOperatBean(
                    (baseQuickAdapter.data[i] as CollectionData?)!!
                )
            )
        )
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        arguments!!.getInt(TYPE)
        return getString(R.string.favorites)
    }

    fun onViewClicked(view: View) {
        val id: Int = view.id
        if (id != R.id.bt_delete_all) {
            if (id != R.id.bt_get_test_data) {
                return
            }

            addDisposable(
                RetrofitManager.getInstance().createApi(Apis::class.java)
                    .getTestData(TUitls.getTestData(SPUtils.getString("series", "E219082007")))
                    .subscribeOn(
                        Schedulers.io()
                    ).flatMap { getTestData ->
                        if ("0" != getTestData.code) {
                            Log.i(TAG, "getCode: 0")
                        }
                        var dataList: List<GetTestData.DataListBean>? = getTestData.data_list
                        if (dataList == null) {
                            Log.i(TAG, "data_list: null")
                        }
                        if (dataList == null) {
                            dataList = ArrayList()
                        }
                        return@flatMap Observable.fromIterable(dataList)
                    }.map { dataListBean: GetTestData.DataListBean ->
                        Random().nextInt(Int.MAX_VALUE)
                        val collectionData = CollectionData()
                        collectionData.title = dataListBean.title
                        collectionData.toothCode = dataListBean.tooth_Code
                        collectionData.basicDataID = dataListBean.basic_data_ID.toInt()
                        collectionData.cuts = dataListBean.cuts
                        collectionData
                    }.doOnNext { collectionData: CollectionData? ->
                        UserDataDaoManager.getInstance(
                            this@FavoriteFragment.getContext()
                        ).collectKey(collectionData)
                    }.observeOn(
                    AndroidSchedulers.mainThread()
                ).subscribe(
                    { collectionData: CollectionData ->
                        Log.i(TAG, "accept: " + collectionData.title)
                        adapter!!.addData(collectionData)
                    }, { th: Throwable -> Log.i(TAG, "throwable: " + th.message) },
                        { dismissLoadingDialog() }
                )
            )
        } else {
            val remindDialog = RemindDialog(getContext())
            remindDialog.setRemindMsg(getString(R.string.delete_all_recordes))

            remindDialog.setDialogBtnCallback { z: Boolean ->
                if (z) {
                    this@FavoriteFragment.deleteAll()
                }
            }
            remindDialog.show()
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun deleteAll() {
        addDisposable(
            Observable.fromCallable(Callable {
                UserDataDaoManager.getInstance(
                    this@FavoriteFragment.getContext()
                ).deleteAllCollections()
            }).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe({ bool: Boolean ->
                if (bool) {
                    adapter!!.setNewData(null)
                }
            },
            { dismissLoadingDialog() })
        )
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable?) {
        this.pageIndex = 0
        getDataList(0, 50, true)
    }

    companion object {
        private val PAGE_SIZE: Int = 50
        private val TYPE: String = "type"

        fun newInstance(): FavoriteFragment {
            val bundle: Bundle = Bundle()
            val favoriteFragment: FavoriteFragment = FavoriteFragment()
            favoriteFragment.setArguments(bundle)
            return favoriteFragment
        }
    }
}
