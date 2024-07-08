package com.kkkcut.e20j.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.adapter.LackToothAdapter2
import com.kkkcut.e20j.adapter.ToothKeyboardRvAdapter
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.network.RetrofitManager
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.NetUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.CodeAndTooth
import com.kkkcut.e20j.bean.eventbus.InputFinishBean
import com.kkkcut.e20j.bean.gsonBean.LackToothRes
import com.kkkcut.e20j.dao.ToothCodeDaoManager
import com.kkkcut.e20j.net.Apis
import com.kkkcut.e20j.net.TUitls
import com.kkkcut.e20j.ui.dialog.MultiToothSelectDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentLacktoothBinding
import com.kkkcut.e20j.utils.GetUUID
import com.kkkcut.e20j.utils.UnifiedErrorUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import java.util.Arrays

class LackToothFragment : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var index: Int = 0
    var lackToothAdapter: LackToothAdapter2? = null
    var toothCodeDaoManager: ToothCodeDaoManager? = null
    private var toothKeyboardRvAdapter: ToothKeyboardRvAdapter? = null

    var binding: FragmentLacktoothBinding? = null

    var tvMulti: TextView? = null
    private val allDepthNames: ArrayList<List<String>> = ArrayList()
    private val textColorDefault: Int = -1
    private val textColorSelect: Int = Color.parseColor("#ff205f")
    private val multiToothMap = HashMap<Int, String>()

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentLacktoothBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_lacktooth
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initKeyboard()
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.setOrientation(1)
        binding!!.rvToothList.setLayoutManager(linearLayoutManager)
        val lackToothAdapter2 = LackToothAdapter2(
            R.layout.item_lacktooth,
            R.layout.item_lacktooth_title,
            (arguments!!.getParcelableArrayList(
                Bitings
            ))!!
        )
        this.lackToothAdapter = lackToothAdapter2
        lackToothAdapter2.setOnItemClickListener(this)
        binding!!.rvToothList.setAdapter(this.lackToothAdapter)
        val dividerItemDecoration = DividerItemDecoration(context, 1)
        dividerItemDecoration.setDrawable(ColorDrawable(Color.parseColor("#717178")))
        binding!!.rvToothList.addItemDecoration(dividerItemDecoration)
        this.toothCodeDaoManager = ToothCodeDaoManager(arguments!!.getInt("keyID"))
    }

    private fun initKeyboard() {
        val gridLayoutManager = GridLayoutManager(getContext(), 5)
        gridLayoutManager.setOrientation(1)
        binding!!.rvKeyboard.setLayoutManager(gridLayoutManager)
        val toothKeyboardRvAdapter = ToothKeyboardRvAdapter()
        this.toothKeyboardRvAdapter = toothKeyboardRvAdapter
        binding!!.rvKeyboard.setAdapter(toothKeyboardRvAdapter)
        this.toothKeyboardRvAdapter!!.setOnItemClickListener(this)
        val keyInfo: KeyInfo? =
            if (getArguments() != null) getArguments()!!.getParcelable<Parcelable>(
                KEY_INFO
            ) as KeyInfo? else null
        var strArr: Array<String?> = arrayOfNulls(0)
        if (keyInfo != null) {
            strArr = keyInfo.depthName.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        }
        for (str: String? in strArr) {
            val arrayList = ArrayList<String>()
            for (str2: String in str!!.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()) {
                arrayList.add(str2)
            }
            allDepthNames.add(fillData(arrayList))
        }
        this.toothKeyboardRvAdapter!!.setNewData(allDepthNames[0])
        val string: String? = getArguments()!!.getString(TOOTHCODE_Lack)
        var arrayList2: ArrayList<Array<String?>> = ArrayList()
        if (TextUtils.isEmpty(string)) {
            if (keyInfo!!.icCard == 1480 || keyInfo.icCard == 601480) {
                val toothCodeRoundArray: ArrayList<Array<String?>> = getToothCodeRoundArray(
                    getArguments()!!.getString(ToothCode_Honda_A)
                )
                val toothCodeRoundArray2: ArrayList<Array<String?>> = getToothCodeRoundArray(
                    getArguments()!!.getString(ToothCode_Honda_B)
                )
                if (toothCodeRoundArray.size > 0 && toothCodeRoundArray2.size > 0) {
                    val strArr2: Array<String?> = toothCodeRoundArray[0]
                    var strArr3: Array<String?> = toothCodeRoundArray2[0]
                    if (strArr3.size > 3) {
                        strArr3 = Arrays.copyOfRange(strArr3, 0, 3) as Array<String?>
                    }
                    arrayList2.add(strArr2)
                    arrayList2.add(strArr3)
                }
            } else {
                arrayList2 = getToothCodeRoundArray(keyInfo.keyToothCode)
            }
        } else {
            for (str3: String in string!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()) {
                val replace: String = str3.replace("_", "?")
                val strArr4: Array<String?> = arrayOfNulls(replace.length)
                val charArray: CharArray = replace.toCharArray()
                for (i in charArray.indices) {
                    strArr4[i] = charArray.get(i).toString()
                }
                arrayList2.add(strArr4)
            }
        }
        for (i2 in arrayList2.indices) {
            val strArr5: Array<String?> = arrayList2[i2]
            val layoutParams: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(AutoUtils.getPercentWidthSize(20), -2)
            for (i3 in strArr5.indices) {
                if (i2 > 0 && i3 == 0) {
                    binding!!.llToothcodeContainer.addView(getTextView("-"), layoutParams)
                }
                val textView: TextView = getTextView(strArr5.get(i3))
                if (i2 == 0 && i3 == 0) {
                    textView.setTextColor(
                        context!!.resources.getColor(R.color.color_ff205f)
                    )
                }
                binding!!.llToothcodeContainer.addView(textView, layoutParams)
            }
        }
    }

    fun getToothCodeRoundArray(str: String?): ArrayList<Array<String?>> {
        val arrayList: ArrayList<Array<String?>> = ArrayList()
        val keyInfo: KeyInfo? =
            if (arguments != null) arguments!!.getParcelable<Parcelable>(
                KEY_INFO
            ) as KeyInfo? else null
        if (TextUtils.isEmpty(str)) {
            val it: Iterator<List<Int>> = keyInfo!!.getSpaceList().iterator()
            while (it.hasNext()) {
                var size: Int = it.next().size
                if (keyInfo.icCard == 1480 || keyInfo.icCard == 601480) {
                    size = 4
                }
                val strArr: Array<String?> = arrayOfNulls(size)
                for (i in 0 until size) {
                    strArr[i] = "?"
                }
                arrayList.add(strArr)
            }
            return arrayList
        }
        val depthNameList: List<List<String>> = keyInfo!!.getDepthNameList()
        val split: Array<String> =
            str!!.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i2 in split.indices) {
            val split2: Array<String> =
                split.get(i2).split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val strArr2: Array<String?> = arrayOfNulls(split2.size)
            for (i3 in split2.indices) {
                strArr2[i3] =
                    getToothCodeRound(split2.get(i3).trim { it <= ' ' }, depthNameList[i2])
            }
            arrayList.add(strArr2)
        }
        return arrayList
    }

    fun getToothCodeRound(str: String, list: List<String>): String {
        if (!str.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            return str
        }
        val str2: String =
            str.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        val parseFloat: Float =
            ("0." + str.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]).toFloat()
        val indexOf: Int = list.indexOf(str2)
        if (parseFloat < 0.5f) {
            return if (indexOf == -1) "?" else list[indexOf]
        }
        if (indexOf == -1) {
            return list[0]
        }
        if (indexOf == list.size - 1) {
            return list[list.size - 1]
        }
        return list[indexOf + 1]
    }

    private fun getTextView(str: String?): TextView {
        val textView = TextView(getContext())
        textView.textSize = AutoUtils.getPercentHeightSize(24).toFloat()
        textView.setTextColor(this.textColorDefault)
        textView.text = str
        textView.setGravity(17)
        return textView
    }

    private fun inputCode(str: String) {
        (binding!!.llToothcodeContainer.getChildAt(this.index) as TextView).text = str
        moveToNext()
    }

    private fun moveToNext() {
        var textView: TextView
        (binding!!.llToothcodeContainer.getChildAt(this.index) as TextView).setTextColor(
            this.textColorDefault
        )
        if (this.index < binding!!.llToothcodeContainer.childCount - 1) {
            val i: Int = this.index + 1
            this.index = i
            textView = binding!!.llToothcodeContainer.getChildAt(i) as TextView
            if ((textView.getText().toString().trim { it <= ' ' } == "-")) {
                val i2: Int = this.index + 1
                this.index = i2
                textView = binding!!.llToothcodeContainer.getChildAt(i2) as TextView
            }
            textView.setTextColor(this.textColorSelect)
        } else {
            this.index = 0
            textView = binding!!.llToothcodeContainer.getChildAt(0) as TextView
            textView.setTextColor(this.textColorSelect)
        }
        if ((textView.getText().toString().trim { it <= ' ' } == "#")) {
            tvMulti!!.text = multiToothMap[this.index]
        } else {
            tvMulti!!.text = ""
        }
    }

    private fun moveToLast() {
        val i: Int = this.index
        if (i > 0) {
            (binding!!.llToothcodeContainer.getChildAt(i) as TextView).setTextColor(
                this.textColorDefault
            )
            val i2: Int = this.index - 1
            this.index = i2
            var textView: TextView = binding!!.llToothcodeContainer.getChildAt(i2) as TextView
            if ((textView.getText().toString().trim { it <= ' ' } == "-")) {
                val i3: Int = this.index - 1
                this.index = i3
                textView = binding!!.llToothcodeContainer.getChildAt(i3) as TextView
            }
            textView.setTextColor(this.textColorSelect)
            if ((textView.getText().toString().trim { it <= ' ' } == "#")) {
                tvMulti!!.text = multiToothMap[this.index]
            } else {
                tvMulti!!.text = ""
            }
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, i: Int) {
        val baseQuickAdapter = adapter as BaseQuickAdapter<CodeAndTooth, *>
        val obj: Any = baseQuickAdapter.data[i]
        if (obj is String) {
            val str: String = obj
            if ((">" == str)) {
                moveToNext()
                return
            }
            if (("<" == str)) {
                moveToLast()
                return
            } else if ((getString(R.string.multi) == str)) {
                showMultiInputDialog()
                return
            } else {
                inputCode(str)
                return
            }
        }
        val data = baseQuickAdapter.data
        for (codeAndTooth in data) {
            if (codeAndTooth.t != null) {
                (codeAndTooth.t as LackToothRes.DataBean).isChecked = false
            }
        }
        val codeAndTooth2 = data[i]
        if (codeAndTooth2.t != null) {
            (codeAndTooth2.t as LackToothRes.DataBean).isChecked = true
        }
        val dataBean: LackToothRes.DataBean = codeAndTooth2.t ?: return
        val charArray: CharArray = dataBean.bitting.toCharArray()
        var str2 = ""
        for (i2 in charArray.indices) {
            str2 = if (i2 == charArray.size - 1) {
                str2 + charArray[i2] + ";"
            } else if ("-".contains(charArray[i2].toString())) {
                str2.substring(0, str2.lastIndexOf(",")) + ";"
            } else {
                str2 + charArray[i2] + ","
            }
        }
        EventBus.getDefault().post(
            EventCenter<Any?>(
                2,
                InputFinishBean(str2, bittingString, data as ArrayList<CodeAndTooth>?)
            )
        )
        onBack()
    }

    private fun showMultiInputDialog() {
        MultiToothSelectDialog(
            context,
            (arguments!!.getParcelable<Parcelable>(KEY_INFO) as KeyInfo?)!!.depthName
        ).show()
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.lacktooth)
    }

    private fun fillData(list: List<String>): List<String> {
        val arrayList = list.toMutableList()
        arrayList.add("?")
        arrayList.add("<")
        arrayList.add(">")
        arrayList.add(getString(R.string.multi))
        return arrayList.toList()
    }

    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.bt_search -> {
                search()
                return
            }

            R.id.bt_search_offline -> {
                fuzzyQueryBitting(bittingString)
                return
            }

            else -> return
        }
    }

    private fun fuzzyQueryBitting(str: String) {
        showLoadingDialog(getString(R.string.waitting))
        addDisposable(
            Observable.fromCallable {
                toothCodeDaoManager!!.lackToothMulti(
                    str,
                    this@LackToothFragment.multiToothMap
                )
            }.map { list ->
                val arrayList = ArrayList<CodeAndTooth>()
                if (list.isNotEmpty()) {
                    for (i in list.indices) {
                        val dataBean: LackToothRes.DataBean = LackToothRes.DataBean()
                        dataBean.bitting = list[i].bitting
                        dataBean.code = list[i].code
                        arrayList.add(CodeAndTooth(dataBean))
                    }
                    return@map arrayList
                }
                throw Exception(this@LackToothFragment.getString(R.string.no_data_was_found))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { list ->
                    this@LackToothFragment.dismissLoadingDialog()
                    val lackToothAdapter2 = LackToothAdapter2(
                        R.layout.item_lacktooth,
                        R.layout.item_lacktooth_title,
                        list
                    )
                    lackToothAdapter2.setOnItemClickListener(this@LackToothFragment)
                    binding!!.rvToothList.setAdapter(lackToothAdapter2)
                }, { th ->
                    this@LackToothFragment.dismissLoadingDialog()
                    ToastUtil.showToast(R.string.no_data_was_found)
                }, { dismissLoadingDialog() }))
    }

    private fun search() {
        if (!NetUtil.isNetworkConnected(context)) {
            ToastUtil.showToast(R.string.network_unavailable)
            return
        }
        showLoadingDialog(getString(R.string.waitting))
        var str = ""
        for (i in 0 until binding!!.llToothcodeContainer.getChildCount()) {
            val charSequence: String =
                (binding!!.llToothcodeContainer.getChildAt(i) as TextView).getText().toString()
            str = if (TextUtils.equals(
                    charSequence,
                    "#"
                )
            ) str + "[" + multiToothMap[i] + "]" else str + charSequence
        }
        val keyInfo: KeyInfo? = arguments!!.getParcelable(KEY_INFO)
        if (keyInfo!!.icCard == 1480 || keyInfo.icCard == 601480) {
            str = "$str*"
        }
        addDisposable(
            RetrofitManager.getInstance().createApi(Apis::class.java).lackTooth(
                TUitls.lackToothParam(
                    str.replace("?", "*"),
                    arguments!!.getInt("keyID"),
                    GetUUID.getUUID(),
                    SPUtils.getString("series"),
                    arguments!!.getString("series")
                )
            ).map { lackToothRes: LackToothRes ->
                val data: List<List<LackToothRes.DataBean>>? = lackToothRes.data
                if (("0" == lackToothRes.code)) {
                    if (!data.isNullOrEmpty()) {
                        val arrayList = ArrayList<CodeAndTooth>()
                        var i2: Int = 0
                        while (i2 < data.size) {
                            val sb: StringBuilder = StringBuilder()
                            sb.append(this@LackToothFragment.getString(R.string.key))
                            sb.append(" ")
                            val i3: Int = i2 + 1
                            sb.append(i3)
                            arrayList.add(CodeAndTooth(true, sb.toString()))
                            for (i4 in data[i2].indices) {
                                arrayList.add(CodeAndTooth(data[i2][i4]))
                            }
                            i2 = i3
                        }
                        return@map arrayList
                    }
                    throw Exception(this@LackToothFragment.getString(R.string.no_data_was_found))
                }
                throw Exception(this@LackToothFragment.getString(R.string.no_data_was_found))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                { list ->
                    dismissLoadingDialog()
                    val lackToothAdapter2 =
                        LackToothAdapter2(R.layout.item_lacktooth, R.layout.item_lacktooth_title, list)
                    lackToothAdapter2.onItemClickListener = this
                    binding!!.rvToothList.setAdapter(lackToothAdapter2)
                },
                { th ->
                    this@LackToothFragment.dismissLoadingDialog()
                    ToastUtil.showToast(UnifiedErrorUtil.unifiedError(th).message)
                }, { dismissLoadingDialog() })
        )
    }


    private val bittingString: String
        get() {
            var str: String = ""
            for (i in 0 until binding!!.llToothcodeContainer.childCount) {
                str += (binding!!.llToothcodeContainer.getChildAt(i) as TextView).getText()
                    .toString().trim { it <= ' ' }
            }
            val replace: String = str.replace("?", "_")
            val keyInfo: KeyInfo? = arguments!!.getParcelable<Parcelable>(KEY_INFO) as KeyInfo?
            if (keyInfo!!.icCard != 1480 && keyInfo.icCard != 601480) {
                return replace
            }
            return replace + "_"
        }

    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (eventCenter.eventCode == 58) {
            val str: String = eventCenter.data as String
            if (str.length == 1) {
                inputCode(str)
            } else {
                multiToothMap[this.index] = str
                inputCode("#")
            }
        }
    }

    companion object {
        val Bitings: String = "bitings"
        val KEY_ID: String = "keyID"
        val KEY_INFO: String = "keyInfo"
        val SERIES: String = "series"
        val TOOTHCODE_Lack: String = "toothcode_lack"
        val ToothCode_Honda_A: String = "toothcode_honda_a"
        val ToothCode_Honda_B: String = "toothcode_honda_b"
        fun newInstance(
            i: Int,
            keyInfo: KeyInfo?,
            str: String?,
            str2: String?,
            str3: String?,
            str4: String?,
            arrayList: ArrayList<CodeAndTooth?>?
        ): LackToothFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt("keyID", i)
            bundle.putParcelable(KEY_INFO, keyInfo)
            bundle.putString(ToothCode_Honda_A, str)
            bundle.putString(ToothCode_Honda_B, str2)
            bundle.putString("series", str3)
            bundle.putString(TOOTHCODE_Lack, str4)
            bundle.putParcelableArrayList(Bitings, arrayList)
            val lackToothFragment: LackToothFragment = LackToothFragment()
            lackToothFragment.setArguments(bundle)
            return lackToothFragment
        }
    }
}
