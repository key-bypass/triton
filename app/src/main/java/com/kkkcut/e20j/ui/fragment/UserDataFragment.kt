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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.userDB.CollectionData
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.adapter.UserDataAdapter
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.bean.gsonBean.GetTestData
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentUserDataBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Random
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class UserDataFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener,
    BaseQuickAdapter.OnItemChildClickListener {
    var binding: FragmentUserDataBinding? = null
    var adapter: BaseQuickAdapter<CollectionData, *>? = null

    private var pageIndex: Int = 0

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentUserDataBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_user_data
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(1)
        binding!!.rvUserData.setLayoutManager(linearLayoutManager)
        binding!!.rvUserData.addItemDecoration(DividerItemDecoration(context, 1))
        if (arguments!!.getInt(TYPE) == 0) {
            this.adapter = UserDataAdapter<CollectionData>()
            binding!!.rvUserData.setAdapter(adapter)
            getDataList(0, 50, false)
        } else {
            this.adapter = UserDataAdapter<CollectionData>()
            binding!!.rvUserData.setAdapter(adapter)
            getDataList(0, 50, false)
            if (MyApplication.getInstance().isShowRealDepth) {
                binding!!.btGetTestData.visibility = 0
            }
        }

        adapter!!.setOnLoadMoreListener({
            nextPage(this@UserDataFragment)
            Log.d(TAG, "onLoadMoreRequested() called" + this@UserDataFragment.pageIndex)
            val userDataFragment: UserDataFragment = this@UserDataFragment
            userDataFragment.getDataList(userDataFragment.pageIndex, 50, false)
        }, binding!!.rvUserData)
        adapter!!.setOnItemChildClickListener(this)
        adapter!!.setOnItemClickListener(this)
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun getDataList(i: Int, i2: Int, newSearch: Boolean) {
        val trim: String = binding!!.etSearch.getText().toString().trim { it <= ' ' }

        val subscribe: Disposable = Observable.fromCallable{ this@UserDataFragment.getData(i, i2, trim) }
        .subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe({ list ->
                if (newSearch) {
                    adapter!!.setNewData(list as List<CollectionData>)
                } else {
                    adapter!!.addData(list as List<CollectionData>)
                }
                if (list!!.size < 50) {
                    adapter!!.loadMoreEnd(false)
                } else {
                    adapter!!.loadMoreComplete()
                }
            }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    fun getData(i: Int, i2: Int, str: String): List<*> {
        if (arguments!!.getInt(TYPE) == 0) {
            return UserDataDaoManager.getInstance(context).getCutHistory(i, i2, str)
        }
        return UserDataDaoManager.getInstance(context).getCollection(i, i2, str)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val i2: Int = getArguments()!!.getInt(TYPE)
        val id: Int = view.getId()
        if (id != R.id.iv_delete) {
            if (id != R.id.iv_edit) {
                return
            }
            showEditDialog(
                baseQuickAdapter.getData().get(i), (view.getParent() as View).findViewById(
                    R.id.tv_remark
                )
            )
            return
        }
        if (i2 == 0) {
            UserDataDaoManager.getInstance(getContext())
                .deleteCutHistory(baseQuickAdapter.getData().get(i) as CutHistoryData?)
        } else {
            UserDataDaoManager.getInstance(getContext())
                .deleteCollection(baseQuickAdapter.getData().get(i) as CollectionData?)
        }
        baseQuickAdapter.remove(i)
    }

    private fun showEditDialog(obj: Any, textView: TextView) {
        val remark: String
        val i: Int = getArguments()!!.getInt(TYPE)
        if (i == 0) {
            remark = (obj as CutHistoryData).getRemark()
        } else {
            remark = (obj as CollectionData).getRemark()
        }
        val str: String = remark
        val editDialog: EditDialog = EditDialog(getContext())
        editDialog.setTip(getString(R.string.enter_remarks))
        if (!TextUtils.isEmpty(str)) {
            editDialog.setEditTextContent(str)
        }

        editDialog.setDialogBtnCallback { str2: String ->
            if (TextUtils.isEmpty(str2) || (str2 == str)) {
                return@setDialogBtnCallback
            }
            textView.text = str2
            if (i == 0) {
                val cutHistoryData: CutHistoryData = obj as CutHistoryData
                cutHistoryData.remark = str2
                UserDataDaoManager.getInstance(this@UserDataFragment.context)
                    .saveCutHistory(cutHistoryData)
            } else {
                val collectionData: CollectionData = obj as CollectionData
                collectionData.remark = str2
                UserDataDaoManager.getInstance(this@UserDataFragment.context)
                    .collectKey(collectionData)
            }
        }
        editDialog.show()
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        if (getArguments()!!.getInt(TYPE) == 0) {
            start(
                KeyOperateFragment.newInstance(
                    GoOperatBean(
                        (baseQuickAdapter.getData().get(i) as CutHistoryData?)!!
                    )
                )
            )
        } else {
            start(
                KeyOperateFragment.newInstance(
                    GoOperatBean(
                        (baseQuickAdapter.getData().get(i) as CollectionData?)!!
                    )
                )
            )
        }
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        if (getArguments()!!.getInt(TYPE) == 0) {
            return getString(R.string.cut_history)
        }
        return getString(R.string.favorites)
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id != R.id.bt_delete_all) {
            if (id != R.id.bt_get_test_data) {
                return
            }

            addDisposable(
                RetrofitManager.getInstance().createApi(Apis::class.java)
                    .getTestData(TUitls.getTestData(SPUtils.getString("series", "E219082007")))
                    .subscribeOn(
                        Schedulers.io()
                    ).flatMap { getTestData: GetTestData ->
                        if ("0" != getTestData.code) {
                            Log.i(TAG, "getCode: 0")
                        }
                        var dataList: List<GetTestData.DataListBean>? = getTestData.data_list
                        if (dataList == null) {
                            Log.i(TAG, "data_list: null")
                            dataList = ArrayList()
                        }
                        Observable.fromIterable(dataList)
                    }
                .map { dataListBean: GetTestData.DataListBean ->
                            Random().nextInt(Int.MAX_VALUE)
                            val collectionData = CollectionData()
                    collectionData.title = dataListBean.title
                    collectionData.toothCode = dataListBean.tooth_Code
                    collectionData.basicDataID = dataListBean.basic_data_ID.toInt()
                    collectionData.cuts = dataListBean.cuts
                            collectionData
                        }
                    .doOnNext { collectionData: CollectionData? ->
                        UserDataDaoManager.getInstance(
                            this@UserDataFragment.context
                        ).collectKey(collectionData)
                    }.observeOn(
                    AndroidSchedulers.mainThread()
                ).subscribe(
                        { collectionData: CollectionData ->
                            Log.i(TAG, "accept: " + collectionData.title)
                            adapter!!.addData(collectionData)
                        }, { th: Throwable -> Log.i(TAG, "throwable: " + th.message) }, { dismissLoadingDialog() }
                )
            )
        } else {
            val remindDialog: RemindDialog = RemindDialog(getContext())
            remindDialog.setRemindMsg(getString(R.string.delete_all_recordes))

            remindDialog.setDialogBtnCallback(RemindDialog.DialogBtnCallBack({ z: Boolean ->
                if (z) {
                    this@UserDataFragment.deleteAll()
                }
            }))
            remindDialog.show()
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun deleteAll() {
        addDisposable(
            Observable.fromCallable<Boolean> {
                if (arguments!!.getInt(TYPE) == 0) {
                    return@fromCallable UserDataDaoManager.getInstance(this@UserDataFragment.context)
                        .deleteAllCutHistories()
                }
                UserDataDaoManager.getInstance(this@UserDataFragment.context)
                    .deleteAllCollections()
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ bool: Boolean ->
                if (bool) {
                    adapter!!.setNewData(null)
                }
            },  { dismissLoadingDialog() })
        )
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable?) {
        this.pageIndex = 0
        getDataList(0, 50, true)
    }

    companion object {
        val COLLECTION: Int = 1
        val CUT_HISTORY: Int = 0
        private val PAGE_SIZE: Int = 50
        private val TYPE: String = "type"

        fun nextPage(userDataFragment: UserDataFragment): Int {
            val i: Int = userDataFragment.pageIndex
            userDataFragment.pageIndex = i + 1
            return i
        }

        fun newInstance(i: Int): UserDataFragment {
            val bundle: Bundle = Bundle()
            val userDataFragment: UserDataFragment = UserDataFragment()
            bundle.putInt(TYPE, i)
            userDataFragment.setArguments(bundle)
            return userDataFragment
        }
    }
}
