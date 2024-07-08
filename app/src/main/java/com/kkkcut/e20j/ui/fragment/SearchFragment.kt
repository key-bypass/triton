package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.GoOperatBean
import com.kkkcut.e20j.DbBean.search.BarCodeSearch
import com.kkkcut.e20j.DbBean.search.CardsSystem
import com.kkkcut.e20j.DbBean.search.ChinaNumSearch
import com.kkkcut.e20j.DbBean.search.KeyBlankItemSearch
import com.kkkcut.e20j.DbBean.search.SearchResult
import com.kkkcut.e20j.DbBean.search.UsaSearchExtItemBasicData
import com.kkkcut.e20j.adapter.KeySearchAdapter
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.KeyInfoDaoManager
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity
import com.kkkcut.e20j.ui.activity.BarCodeRemindActivity.Companion.start
import com.kkkcut.e20j.ui.activity.FrameActivity
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentSearchBinding
import com.kkkcut.e20j.utils.ThemeUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Callable

/* loaded from: classes.dex */
class SearchFragment : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var searchType: SearchType? = null
    var binding: FragmentSearchBinding? = null
    var keySearchAdapter: KeySearchAdapter<SearchResult>? = null
    var activity: FrameActivity? = null

    /* loaded from: classes.dex */
    enum class SearchType {
        KEY_BLANK,
        KEY_ID,
        CHINA_KEY_NUM,
        BLITZ_CARD,
        DSD,
        LKP_DSD,
        SILCA_CARD,
        BAR_CODE
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentSearchBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    private fun isShowRemind(i: Int): Boolean {
        return (i == 872) || (i == 1510) || (i == 909) || (i == 1309) || (i == 1097) || (i == 1373) || (i == 1370) || (i == 1407) || (i == 998)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_search
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.keySearchAdapter = KeySearchAdapter<SearchResult>()
        keySearchAdapter!!.setOnItemClickListener(this)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(1)
        binding!!.rvResult.setAdapter(this.keySearchAdapter)
        binding!!.rvResult.setLayoutManager(linearLayoutManager)
        val frameActivity: FrameActivity? = getActivity() as FrameActivity?
        this.activity = frameActivity
        frameActivity!!.bindToEditor(binding!!.etSearch, 0)
        Handler().postDelayed({ binding!!.etSearch.requestFocus() }, 500L)
        if (MachineInfo.isE20Us(context)) {
            binding!!.rlBlitzCard.visibility = 0
            binding!!.rlLkp.visibility = 0
            binding!!.rlBarCode.visibility = if (SPUtils.getBoolean("bar_code", true)) 0 else 8
            binding!!.tvKeyId.text = "Card"
        } else if (MachineInfo.isChineseMachine()) {
            binding!!.rlChinaKeyNum.visibility = 0
            binding!!.rlSilca.visibility = 8
        } else {
            binding!!.rlSilca.visibility = 0
        }
        if (arguments == null || (arguments!!.getSerializable("TYPE") as SearchType?) != SearchType.BAR_CODE) {
            return
        }
        binding!!.rlBarCode.performClick()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.search)
    }

    fun onViewClicked(view: View) {
        val color: Int = ThemeUtils.getColor(context, R.attr.color_red_blueDark)
        val color2: Int = ThemeUtils.getColor(context, R.attr.textColor_ffffff_333333)
        when (view.id) {
            R.id.rl_bar_code -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.BAR_CODE
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color)
                binding!!.flagBarCode.setBackgroundColor(color)
                activity!!.bindToEditor(binding!!.etSearch, 0)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_blitz_card -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.BLITZ_CARD
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color)
                binding!!.flagBlitzCard.setBackgroundColor(color)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 0)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_china_key_num -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.CHINA_KEY_NUM
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color)
                binding!!.flagChinaKey.setBackgroundColor(color)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 1)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_container, R.id.rl_data, R.id.rl_move, R.id.rl_parent -> return
            R.id.rl_dsd -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.DSD
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color)
                binding!!.flagDsd.setBackgroundColor(color)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 1)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_key_blank -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.KEY_BLANK
                binding!!.tvKeyBlank.setTextColor(color)
                binding!!.flagKeyBlank.setBackgroundColor(color)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 0)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_key_id -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.KEY_ID
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color)
                binding!!.flagKeyId.setBackgroundColor(color)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 1)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_lkp -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.LKP_DSD
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color)
                binding!!.flagLkp.setBackgroundColor(color)
                binding!!.tvSilca.setTextColor(color2)
                binding!!.flagSilca.setBackgroundColor(color2)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 1)
                binding!!.etSearch.requestFocus()
                return
            }

            R.id.rl_silca -> {
                binding!!.etSearch.setText("")
                this.searchType = SearchType.SILCA_CARD
                binding!!.tvKeyBlank.setTextColor(color2)
                binding!!.flagKeyBlank.setBackgroundColor(color2)
                binding!!.tvKeyId.setTextColor(color2)
                binding!!.flagKeyId.setBackgroundColor(color2)
                binding!!.tvChinaKeyNum.setTextColor(color2)
                binding!!.flagChinaKey.setBackgroundColor(color2)
                binding!!.tvBlitzCard.setTextColor(color2)
                binding!!.flagBlitzCard.setBackgroundColor(color2)
                binding!!.tvDsd.setTextColor(color2)
                binding!!.flagDsd.setBackgroundColor(color2)
                binding!!.tvLkp.setTextColor(color2)
                binding!!.flagLkp.setBackgroundColor(color2)
                binding!!.tvSilca.setTextColor(color)
                binding!!.flagSilca.setBackgroundColor(color)
                binding!!.tvBarCode.setTextColor(color2)
                binding!!.flagBarCode.setBackgroundColor(color2)
                activity!!.bindToEditor(binding!!.etSearch, 1)
                binding!!.etSearch.requestFocus()
                return
            }

            else -> return
        }
    }

    internal object AnonymousClass12 {
        val searchType: IntArray = IntArray(SearchType.entries.size)

        init {
            searchType[SearchType.KEY_BLANK.ordinal] = 1
            searchType[SearchType.KEY_ID.ordinal] = 2
            searchType[SearchType.CHINA_KEY_NUM.ordinal] = 3
            searchType[SearchType.BLITZ_CARD.ordinal] = 4
            searchType[SearchType.DSD.ordinal] = 5
            searchType[SearchType.LKP_DSD.ordinal] = 6
            searchType[SearchType.SILCA_CARD.ordinal] = 7
            searchType[SearchType.BAR_CODE.ordinal] = 8
        }
    }

    fun afterTextChanged(editable: Editable) {
        when (searchType) {
            SearchType.KEY_BLANK -> {
                val obj: String = editable.toString()
                if (!TextUtils.isEmpty(obj)) {
                    searchKeyBlank(obj)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.KEY_ID -> {
                val obj2: String = editable.toString()
                if (!TextUtils.isEmpty(obj2)) {
                    searchID(obj2)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.CHINA_KEY_NUM -> {
                val obj3: String = editable.toString()
                if (!TextUtils.isEmpty(obj3)) {
                    searchChinaKeyNum(obj3)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.BLITZ_CARD -> {
                val obj4: String = editable.toString()
                if (!TextUtils.isEmpty(obj4)) {
                    searchBlitzCardOrDsd(1, obj4)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.DSD -> {
                val obj5: String = editable.toString()
                if (!TextUtils.isEmpty(obj5)) {
                    searchBlitzCardOrDsd(2, obj5)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.LKP_DSD -> {
                val obj6: String = editable.toString()
                if (!TextUtils.isEmpty(obj6)) {
                    searchBlitzCardOrDsd(3, obj6)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.SILCA_CARD -> {
                val obj7: String = editable.toString()
                if (!TextUtils.isEmpty(obj7)) {
                    searchBlitzCardOrDsd(4, obj7)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            SearchType.BAR_CODE -> {
                val obj8: String = editable.toString()
                if (!TextUtils.isEmpty(obj8)) {
                    searchBarCode(obj8)
                } else {
                    keySearchAdapter!!.setNewData(ArrayList<SearchResult>())
                }
            }

            else -> return
        }
    }

    private fun searchBarCode(str: String) {
        val subscribe = Observable.fromCallable {
            KeyInfoDaoManager.getInstance().searchBarCode(str)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                keySearchAdapter!!.setNewData(list as List<SearchResult>)
            },  { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    private fun searchBlitzCardOrDsd(i: Int, str: String) {
        val subscribe: Disposable =
            Observable.fromCallable {
                KeyInfoDaoManager.getInstance().searchBlitzOrDsd(i, str)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    keySearchAdapter!!.setNewData(list as List<SearchResult>)
                }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    private fun searchKeyBlank(str: String) {
        val subscribe: Disposable =
            Observable.fromCallable {
                KeyInfoDaoManager.getInstance().searchKey("$str%")
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list -> keySearchAdapter!!.setNewData(list as List<SearchResult>)
            },  { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    private fun searchID(str: String) {
        val subscribe: Disposable = Observable.fromCallable {
            KeyInfoDaoManager.getInstance().searchID(str)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe    (  { list ->
                keySearchAdapter!!.setNewData(list as List<SearchResult>)
            },  { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    private fun searchChinaKeyNum(str: String) {
        val subscribe: Disposable =
            Observable.fromCallable {
                KeyInfoDaoManager.getInstance().searchChinaKeyNumber(str)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    Log.i(TAG, "accept: " + list.size)
                    keySearchAdapter!!.setNewData(list as List<SearchResult>)
                }, { dismissLoadingDialog() })
        clearDisposable()
        addDisposable(subscribe)
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val obj: Any = baseQuickAdapter.data[i]
        if (obj is ChinaNumSearch) {
            val chinaNumSearch: ChinaNumSearch = obj
            var nameCn: String = chinaNumSearch.name_CN
            if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(nameCn)) {
                nameCn = chinaNumSearch.name
            }
            var modelNameCn: String = chinaNumSearch.modelName_CN
            if (!MachineInfo.isChineseMachine() || TextUtils.isEmpty(modelNameCn)) {
                modelNameCn = chinaNumSearch.modelName
            }
            var fromYear: String = chinaNumSearch.fromYear
            if (TextUtils.isEmpty(fromYear)) {
                fromYear = "0"
            }
            val toYear: String = chinaNumSearch.toYear
            start(
                KeyOperateFragment.newInstance(
                    GoOperatBean(
                        chinaNumSearch,
                        "$nameCn>$modelNameCn>$fromYear~" + (if (TextUtils.isEmpty(
                                toYear
                            )
                        ) "0" else toYear) + "--ID:" + chinaNumSearch.getfK_KeyID()
                    )
                )
            )
            return
        }
        var str: String = ""
        var i2: Int = 0
        if (obj is KeyBlankItemSearch) {
            val keyBlankItemSearch: KeyBlankItemSearch = obj
            val keyID: Int = keyBlankItemSearch.fK_KeyID
            str = if (MachineInfo.isE20Us(context)) {
                keyBlankItemSearch.keyblankItemName + "(Card:" + keyID + ")"
            } else {
                "" + getString(R.string.key_blanks) + keyBlankItemSearch.keyblankItemName + "--ID:" + keyID
            }
            i2 = keyID
        }
        if (obj is CardsSystem) {
            i2 = obj.keyID
            str = if (MachineInfo.isE20Us(context)) {
                "Card:$i2"
            } else {
                "ID:$i2"
            }
        }
        if (obj is UsaSearchExtItemBasicData) {
            val usaSearchExtItemBasicData: UsaSearchExtItemBasicData = obj
            i2 = usaSearchExtItemBasicData.fkKeyID
            when (usaSearchExtItemBasicData.fK_SearchExtID) {
                1 -> {
                    str = getString(R.string.blitz_card) + ":" + usaSearchExtItemBasicData.name
                }
                2 -> {
                    str = "DSD:" + usaSearchExtItemBasicData.name
                }
                3 -> {
                    str = "LKP DSD:" + usaSearchExtItemBasicData.name
                }
                4 -> {
                    str = getString(R.string.silca_card) + ":" + usaSearchExtItemBasicData.name
                }
            }
            str = "$str(Card:$i2)"
        }
        if (obj is BarCodeSearch) {
            val barCodeSearch: BarCodeSearch = obj
            i2 = barCodeSearch.fK_KeyID
            str = getBarCodeTitle(i2, barCodeSearch.barCode)
            if (isShowRemind(i2)) {
                start((getActivity())!!, i2, barCodeSearch.barCode)
                return
            }
        }
        start(KeyOperateFragment.newInstance(GoOperatBean(i2, str)))
    }

    private fun getBarCodeTitle(i: Int, str: String?): String {
        return "Bar Code Scanning:$str(Card:$i)"
    }

    // com.kkkcut.e20j.base.BaseFFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment, androidx.fragment.app.Fragment
    override fun onDestroy() {
        activity!!.hideSoftKeyboard()
        super.onDestroy()
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        var bundle: Bundle? = null
        var i = 0
        if (eventCenter.eventCode == 55 && (((eventCenter.data as Bundle).also {
                bundle = it
            }).getInt(BarCodeRemindActivity.ID).also { i = it }) != 0) {
            start(
                KeyOperateFragment.newInstance(
                    GoOperatBean(
                        i,
                        getBarCodeTitle(i, bundle!!.getString("bar_code"))
                    )
                )
            )
        }
    }

    companion object {
        private const val TYPE: String = "TYPE"
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }

        fun newInstance(searchType: SearchType?): SearchFragment {
            val bundle = Bundle()
            bundle.putSerializable("TYPE", searchType)
            val searchFragment = SearchFragment()
            searchFragment.setArguments(bundle)
            return searchFragment
        }
    }
}
