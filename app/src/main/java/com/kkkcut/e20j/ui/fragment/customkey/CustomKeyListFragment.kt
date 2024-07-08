package com.kkkcut.e20j.ui.fragment.customkey

import android.app.AlertDialog
import android.content.DialogInterface
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.KeyBasicData
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.adapter.CustomKeyAdapter
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.gsonBean.UploadCustomKey
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.GetUUID
import com.kkkcut.e20j.utils.SpecificParamUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class CustomKeyListFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener,
    BaseQuickAdapter.OnItemChildClickListener {
    var btCreateKey: Button? = null
    private var customKeyAdapter: CustomKeyAdapter? = null

    var rvCustomKey: RecyclerView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_customkey_list
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = 1
        rvCustomKey!!.layoutManager = linearLayoutManager
        rvCustomKey!!.addItemDecoration(
            DividerItemDecoration(
                context, 1
            )
        )
        val customKeyAdapter = CustomKeyAdapter()
        this.customKeyAdapter = customKeyAdapter
        customKeyAdapter.onItemChildClickListener = this
        this.customKeyAdapter!!.onItemClickListener = this
        rvCustomKey!!.adapter = this.customKeyAdapter
        customKeys
        if (SPUtils.getBoolean(SpKeys.CUSTOMKEY_WARNING_NEVER_ASK, false)) {
            return
        }
        val warningDialog = WarningDialog(context)
        warningDialog.setRemind(getString(R.string.this_function_requires_professional_lock_knowledge_to_use_if_you_use_this_function_incorrectly_it_may_cause_damage_to_the_fixture_or_machine_please_use_it_with_caution))
        warningDialog.setCheckbox(true, SpKeys.CUSTOMKEY_WARNING_NEVER_ASK)
        warningDialog.show()
    }

    private val customKeys: Unit
        get() {
            addDisposable(
                Observable.fromCallable(
                    // java.util.concurrent.Callable
                    Callable {
                        // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.2
                        UserDataDaoManager.getInstance(this@CustomKeyListFragment.context).customKeys
                    }).subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    { list ->

                        // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.1
                        Log.i(TAG, "accept: " + list.size)
                        customKeyAdapter!!.setNewData(list)
                    }, { dismissLoadingDialog() })
            )
        }

    fun onViewClicked(view: View) {
        val id = view.id
        if (id == R.id.bt_create_key) {
            start(KeyTypeSelectFragment.Companion.newInstance(CustomKey()))
        } else {
            if (id != R.id.bt_load_from_id) {
                return
            }
            showLoadIDDialog()
        }
    }

    private fun showLoadIDDialog() {
        val editDialog = EditDialog(context)
        editDialog.setTip(getString(R.string.please_input_profile_id))
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.3
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                this@CustomKeyListFragment.loadProfileId(str)
            }
        })
        editDialog.show()
        val editText: EditText? = editDialog.findViewById<View>(R.id.et_input) as EditText
        if (editText != null) {
            editText.inputType = 2
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun loadProfileId(str: String) {
        addDisposable(
            Observable.fromCallable<KeyBasicData>(object : Callable<KeyBasicData> {
                // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.7
                /* JADX WARN: Can't rename method to resolve collision */
                @Throws(Exception::class)  // java.util.concurrent.Callable
                override fun call(): KeyBasicData {
                    val basicData = KeyInfoDaoManager.getInstance().getBasicData(str.toInt())
                    if (basicData != null) {
                        return basicData
                    }
                    throw Exception(this@CustomKeyListFragment.getString(R.string.id_does_not_exist))
                }
            }).map<CustomKey>(object : Function<KeyBasicData, CustomKey> {
                // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.6
                @Throws(Exception::class)  // io.reactivex.functions.Function
                override fun apply(keyBasicData: KeyBasicData): CustomKey {
                    val customKey = CustomKey(keyBasicData)
                    val param = SpecificParamUtils.getParam(customKey.parameter_info, "locked")
                    if (keyBasicData.icCard > 5000 && ("1" == param)) {
                        throw Exception(this@CustomKeyListFragment.getString(R.string.cannot_load_this_data))
                    }
                    if (keyBasicData.type == 7 || keyBasicData.type == 9) {
                        throw Exception(this@CustomKeyListFragment.getString(R.string.cannot_load_this_data))
                    }
                    return customKey
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ customKey ->
                        this@CustomKeyListFragment.start(
                            KeyTypeSelectFragment.newInstance(
                                customKey
                            )
                        )
                }, {
                    ToastUtil.showToast(R.string.network_unavailable)
                }, { dismissLoadingDialog() }))
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.my_key_info)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener
    override fun onItemChildClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val customKey = baseQuickAdapter.data[i] as CustomKey
        val id = view.id
        if (id == R.id.iv_delete) {
            AlertDialog.Builder(context).setMessage(R.string.the_key_profile_will_be_deleted)
                .setPositiveButton(
                    R.string.ok, object : DialogInterface.OnClickListener {
                        // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.8
                        // android.content.DialogInterface.OnClickListener
                        override fun onClick(dialogInterface: DialogInterface, i2: Int) {
                            UserDataDaoManager.getInstance(this@CustomKeyListFragment.context)
                                .deleteCustomKey(customKey)
                            baseQuickAdapter.remove(i)
                        }
                    }).setNegativeButton(R.string.cancel, null as DialogInterface.OnClickListener?)
                .show()
        } else if (id == R.id.iv_edit) {
            start(KeyTypeSelectFragment.Companion.newInstance(customKey))
        } else {
            if (id != R.id.iv_synchronize) {
                return
            }
            syncData(customKey)
        }
    }

    private fun syncData(customKey: CustomKey) {
        showLoadingDialog(getString(R.string.waitting), false)
        addDisposable(
            (RetrofitManager.getInstance()
                .createApi(Apis::class.java) as Apis).uploadCustomkey(
                TUitls.uploadCustomkey(SPUtils.getString("series"), GetUUID.getUUID(), customKey)
            ).subscribeOn(
                Schedulers.io()
            ).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { uploadCustomKey ->

                    // from class: com.kkkcut.e20j.ui.fragment.customkey.CustomKeyListFragment.9
                    val str: String
                    val split = uploadCustomKey.msg.split("\\n".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                    if (MachineInfo.isChineseMachine()) {
                        str = split[0]
                    } else if (split.size == 2) {
                        str = split[1]
                    } else {
                        str = split[0]
                    }
                    this@CustomKeyListFragment.dismissLoadingDialog()
                    ToastUtil.showToast(str)
                },

            {
                this@CustomKeyListFragment.dismissLoadingDialog()
                ToastUtil.showToast(R.string.network_unavailable)
            }, { dismissLoadingDialog() })
        )
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        start(newInstance(GoOperatBean((baseQuickAdapter.data[i] as CustomKey))))
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (eventCenter.eventCode == 11) {
            customKeys
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    fun afterTextChanged(editable: Editable) {
        val subscribe = Observable.fromCallable<List<CustomKey>>
        {
            UserDataDaoManager.getInstance(this@CustomKeyListFragment.context)
                .fuzzyQueryCustomKeys(editable.toString())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                customKeyAdapter!!.setNewData(list!!)
            }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    companion object {
        fun newInstance(): CustomKeyListFragment {
            return CustomKeyListFragment()
        }
    }
}
