package com.kkkcut.e20j.ui.fragment.search

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.customView.searchSpinner.SearchableListDialog
import com.kkkcut.e20j.customView.searchSpinner.SearchableListDialog.SearchableItem
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentAdvanceSearchBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.Locale
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class AdvanceSearchFragment : BaseBackFragment(), SearchableItem<Any> {
    var binding: FragmentAdvanceSearchBinding? = null
    private var inputType: InputType? = null


    /* JADX INFO: Access modifiers changed from: package-private */ /* loaded from: classes.dex */
    enum class InputType {
        CARD,
        KEY_BLANK,
        KEY_MANUFACTURER,
        LOCK_MANUFACTURER,
        LOCK_SYSTEM
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentAdvanceSearchBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.root
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_advance_search
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String {
        return getString(R.string.advanced_search)
    }

    private fun prompt(inputType: InputType, editText: EditText) {
        val upperCase = editText.text.toString().trim { it <= ' ' }.uppercase(Locale.getDefault())
        if (TextUtils.isEmpty(upperCase)) {
            return
        }
        this.inputType = inputType

        addDisposable(Observable.fromCallable { this.searchInput(inputType, upperCase)!! }.subscribeOn(
            Schedulers.io()
        ).observeOn(AndroidSchedulers.mainThread()).subscribe({ list: List<String?>? ->
            val newInstance = SearchableListDialog.newInstance(list)
            newInstance.show(this.activity!!.fragmentManager, "Search")
            newInstance.setOnSearchableItemClickListener(this)
        }, { dismissLoadingDialog() }))
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment$5, reason: invalid class name */ /* loaded from: classes.dex */
    object AnonymousClass5 {
        /* synthetic */val `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`: IntArray

        init {
            val iArr = IntArray(InputType.entries.size)
            `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType` = iArr

            iArr[InputType.CARD.ordinal] = 1

            `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`[InputType.KEY_BLANK.ordinal] =
                2

            `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`[InputType.KEY_MANUFACTURER.ordinal] =
                3

            `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`[InputType.LOCK_SYSTEM.ordinal] =
                4

            `$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`[InputType.LOCK_MANUFACTURER.ordinal] =
                5
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun searchInput(inputType: InputType, str: String?): List<String?>? {
        val i =
            AnonymousClass5.`$SwitchMap$com$kkkcut$e20j$ui$fragment$search$AdvanceSearchFragment$InputType`[inputType.ordinal]
        if (i == 1) {
            val selectedItemPosition = binding!!.spinnerCard.selectedItemPosition
            if (selectedItemPosition == 0) {
                return KeyInfoDaoManager.getInstance().searchKid(str)
            }
            if (selectedItemPosition == 1) {
                return KeyInfoDaoManager.getInstance().searchCard(str)
            }
            if (selectedItemPosition == 2) {
                return KeyInfoDaoManager.getInstance().searchSn(str)
            }
            return null
        }
        if (i == 2) {
            return KeyInfoDaoManager.getInstance().searchKeyBlank(str)
        }
        if (i == 3) {
            return KeyInfoDaoManager.getInstance().searchKeyManu(str)
        }
        if (i == 4) {
            return KeyInfoDaoManager.getInstance().searchLockSystem(str)
        }
        if (i != 5) {
            return null
        }
        return KeyInfoDaoManager.getInstance().searchLockManu(str)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.bt_clear -> {
                binding!!.etLockSystem.setText("")
                binding!!.etKeyBlank.setText("")
                binding!!.etCard.setText("")
                binding!!.etLockManufacturer.setText("")
                binding!!.spinnerKeyManufacturer.adapter = null
                binding!!.spinnerCard.reset()
                return
            }

            R.id.bt_search -> {
                var z = false
                val searchCondition = SearchCondition()
                val trim = binding!!.etCard.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(trim)) {
                    val selectedItemPosition = binding!!.spinnerCard.selectedItemPosition
                    if (selectedItemPosition == -1) {
                        ToastUtil.showToast("Please select a card type first")
                        return
                    }
                    if (selectedItemPosition == 0) {
                        searchCondition.kid = trim
                    } else if (selectedItemPosition == 1) {
                        searchCondition.silcaCard = trim
                    } else if (selectedItemPosition == 2) {
                        searchCondition.silcaSN = trim
                    }
                    z = true
                }
                val trim2 =
                    binding!!.etKeyBlank.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(trim2)) {
                    z = true
                }
                searchCondition.keyBlank = trim2
                searchCondition.keyBlankManu =
                    binding!!.spinnerKeyManufacturer.selectedItem as String
                val trim3 =
                    binding!!.etLockManufacturer.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(trim3)) {
                    z = true
                }
                searchCondition.lockManu = trim3
                val trim4 =
                    binding!!.etLockSystem.text.toString().trim { it <= ' ' }
                val z2 = if (TextUtils.isEmpty(trim4)) z else true
                searchCondition.lockSys = trim4
                if (!z2) {
                    ToastUtil.showToast("Please insert a searching parameter")
                    return
                } else {
                    start(SearchResultFragment.Companion.newInstance(searchCondition))
                    return
                }
            }

            R.id.iv_card -> if (binding!!.spinnerCard.selectedItemPosition == -1) {
                ToastUtil.showToast("Please select a card type first")
                return
            } else {
                if (TextUtils.isEmpty(binding!!.etCard.text.toString().trim { it <= ' ' }
                        .uppercase(Locale.getDefault()))) {
                    return
                }
                prompt(
                    InputType.CARD,
                    binding!!.etCard
                )
                return
            }

            R.id.iv_key_blank -> {
                if (TextUtils.isEmpty(binding!!.etKeyBlank.text.toString().trim { it <= ' ' }
                        .uppercase(Locale.getDefault()))) {
                    return
                }
                prompt(
                    InputType.KEY_BLANK,
                    binding!!.etKeyBlank
                )
                return
            }

            R.id.iv_lock_manu -> {
                if (TextUtils.isEmpty(
                        binding!!.etLockManufacturer.text.toString().trim { it <= ' ' }
                            .uppercase(Locale.getDefault()))
                ) {
                    return
                }
                prompt(
                    InputType.LOCK_MANUFACTURER,
                    binding!!.etLockManufacturer
                )
                return
            }

            R.id.iv_lock_system -> {
                if (TextUtils.isEmpty(binding!!.etLockSystem.text.toString().trim { it <= ' ' }
                        .uppercase(Locale.getDefault()))) {
                    return
                }
                prompt(
                    InputType.LOCK_SYSTEM,
                    binding!!.etLockSystem
                )
                return
            }

            else -> return
        }
    }

    override fun onSearchableItemClicked(t: Any?, i: Int) {
        val i2 = i as InputType
        when (i2) {
            InputType.CARD -> {
                binding!!.etCard.setText(t as String)
            }
            InputType.KEY_BLANK -> {
                Observable.fromCallable {
                    this.searchInput(
                        InputType.KEY_MANUFACTURER,
                        t as String
                    )!!
                }.subscribeOn(
                    Schedulers.io()
                ).observeOn(AndroidSchedulers.mainThread()).subscribe { list: List<String?>? ->
                    binding!!.spinnerKeyManufacturer.adapter = ArrayAdapter<Any?>(
                        this@AdvanceSearchFragment.context!!,
                        R.layout.support_simple_spinner_dropdown_item,
                        list!!
                    )
                }
                binding!!.etKeyBlank.setText(t as String)
            }
            InputType.LOCK_MANUFACTURER -> {
                binding!!.etLockSystem.setText(t as String)
            }
            InputType.LOCK_SYSTEM -> {
                binding!!.etLockManufacturer.setText(t as String)
            }

            InputType.KEY_MANUFACTURER -> return
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onUserVisible() {
        Log.d(TAG, "onUserVisible() called")
        super.onUserVisible()
    }

    // com.kkkcut.e20j.base.BaseFFragment, me.yokeyword.fragmentation.ISupportFragment
    override fun onSupportVisible() {
        Log.i(TAG, "onSupportVisible: ")
        super.onSupportVisible()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment, com.kkkcut.e20j.base.BaseFFragment, androidx.fragment.app.Fragment
    override fun onHiddenChanged(z: Boolean) {
        Log.d(TAG, "onHiddenChanged() called with: hidden = [$z]")
        super.onHiddenChanged(z)
    }

    companion object {
        @JvmStatic
        fun newInstance(): AdvanceSearchFragment {
            return AdvanceSearchFragment()
        }
    }

}
