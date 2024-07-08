package com.kkkcut.e20j.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.adapter.ToothKeyboardRvAdapter
import com.kkkcut.e20j.androidquick.tool.FileUtil
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.eventbus.InputFinishBean
import com.kkkcut.e20j.customView.drawKeyImg.AngleKey
import com.kkkcut.e20j.customView.drawKeyImg.DimpleKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleInsideGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleKey
import com.kkkcut.e20j.customView.drawKeyImg.DoubleOutsideKey
import com.kkkcut.e20j.customView.drawKeyImg.Key
import com.kkkcut.e20j.customView.drawKeyImg.Key.OnKeyboardChangedListener
import com.kkkcut.e20j.customView.drawKeyImg.Side3KsKey
import com.kkkcut.e20j.customView.drawKeyImg.SideToothKey
import com.kkkcut.e20j.customView.drawKeyImg.SigleInsideGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.SingleKey
import com.kkkcut.e20j.customView.drawKeyImg.SingleOutGrooveKey
import com.kkkcut.e20j.customView.drawKeyImg.TubularKey
import com.kkkcut.e20j.ui.fragment.duplicatekey.DuplicateKeyNewFragment.Companion.newInstance
import com.kkkcut.e20j.ui.fragment.search.AdvanceSearchFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentToothInputBinding
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class ToothCodeInputFragment() : BaseBackFragment(), BaseQuickAdapter.OnItemClickListener {
    private var doorIgnition: Boolean = false
    private var doorToIgnition: Boolean = false

    var key: Key? = null
    private var keyinfo: KeyInfo? = null

    private var toothKeyboardRvAdapter: ToothKeyboardRvAdapter? = null

    var binding: FragmentToothInputBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View? {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentToothInputBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_tooth_input
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        checkNotNull(getArguments())
        val keyInfo: KeyInfo? = getArguments()!!.get(KeyInfoFragment.Companion.KEY_INFO) as KeyInfo?
        this.keyinfo = keyInfo
        checkNotNull(keyInfo)
        val type: Int = keyInfo.getType()
        if (type != 92) {
            when (type) {
                0 -> this.key = DoubleKey(getContext(), this.keyinfo)
                1 -> this.key = SingleKey(getContext(), this.keyinfo)
                2 -> this.key = DoubleInsideGrooveKey(getContext(), this.keyinfo)
                3 -> this.key = SingleOutGrooveKey(getContext(), this.keyinfo)
                4 -> this.key = DoubleOutsideKey(getContext(), this.keyinfo)
                5 -> this.key = SigleInsideGrooveKey(getContext(), this.keyinfo)
                6 -> this.key = DimpleKey(getContext(), this.keyinfo)
                7 -> {
                    this.key = AngleKey(getContext(), this.keyinfo)
                    binding!!.switchDecimal.setVisibility(8)
                }

                8 -> this.key = TubularKey(getContext(), this.keyinfo)
                9 -> this.key = SideToothKey(getContext(), this.keyinfo)
            }
        } else {
            this.key = Side3KsKey(getContext(), this.keyinfo)
        }
        val key: Key? = this.key
        if (key != null) {
            key.setInputModel(true)
            binding!!.flKey.addView(this.key)
        }
        val setting_round: String = keyinfo!!.getSetting_round()
        val readBittingRule: String = keyinfo!!.getReadBittingRule()
        if (!TextUtils.isEmpty(setting_round)) {
            if (("1" == setting_round)) {
                binding!!.switchDecimal.setChecked(false)
                binding!!.btRounding.setVisibility(8)
            } else {
                binding!!.switchDecimal.setChecked(true)
            }
        } else if (!TextUtils.isEmpty(readBittingRule)) {
            if (("1" == readBittingRule)) {
                binding!!.switchDecimal.setChecked(true)
            }
            if (("11" == readBittingRule)) {
                binding!!.switchDecimal.setChecked(false)
                binding!!.btRounding.setVisibility(8)
            }
        } else {
            val z: Boolean = SPUtils.getBoolean("round", true)
            if (keyinfo!!.getType() != 7) {
                binding!!.switchDecimal.setChecked(!z)
            }
            if (z || keyinfo!!.getType() == 7) {
                binding!!.btRounding.setVisibility(8)
            }
        }
        val keyToothCode: String = keyinfo!!.getKeyToothCode()
        if (!TextUtils.isEmpty(keyToothCode)) {
            this.key!!.setToothCodeAndInvalidate(keyToothCode)
        }

        this.key!!.setOnKeyboardChangedListener(OnKeyboardChangedListener({ list: List<String> ->
            toothKeyboardRvAdapter!!.replaceData(
                fillData(list)
            )
        }))
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(getContext(), 5)
        gridLayoutManager.setOrientation(1)
        binding!!.rvKeyboard.setLayoutManager(gridLayoutManager)
        val toothKeyboardRvAdapter: ToothKeyboardRvAdapter = ToothKeyboardRvAdapter()
        this.toothKeyboardRvAdapter = toothKeyboardRvAdapter
        binding!!.rvKeyboard.setAdapter(toothKeyboardRvAdapter)
        this.toothKeyboardRvAdapter!!.setNewData(fillData(this.key!!.getAllDepthNames().get(0)))
        this.toothKeyboardRvAdapter!!.setOnItemClickListener(this)
        if (keyinfo!!.getIsrule() > 0) {
            binding!!.llInputRule.setVisibility(0)
            if (TextUtils.isEmpty(keyinfo!!.getReadBittingRule()) || !("3" == keyinfo!!.getReadBittingRule())) {
                return
            }
            binding!!.btIgnitionDoor.setVisibility(8)
            return
        }
        binding!!.llInputRule.setVisibility(8)
    }


    private fun fillData(list: List<String>): List<String> {
        val arrayList: ArrayList<String> = ArrayList(list)
        arrayList.add("?")
        arrayList.add(getString(R.string.clear))
        return arrayList
    }

    fun onViewClicked(view: View) {
        when (view.getId()) {
            R.id.bt_cancle -> {
                onBack()
                return
            }

            R.id.bt_ignition_door -> {
                start(
                    IgnitionDoorSearchFragment.Companion.newInstance(
                        keyinfo!!.getSpaceStr().split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                            .toTypedArray().get(0).split(",".toRegex())
                            .dropLastWhile({ it.isEmpty() }).toTypedArray().size
                    )
                )
                return
            }

            R.id.bt_input_rule -> {
                start(
                    InputRuleFragment.Companion.newInstance(
                        keyinfo!!.getIsrule(),
                        keyinfo!!.getReadBittingRule(), toothListToStr()
                    )
                )
                return
            }

            R.id.bt_rounding -> {
                round()
                return
            }

            R.id.iv_down -> {
                key!!.moveToDown()
                return
            }

            R.id.iv_left -> {
                key!!.moveToLeft()
                return
            }

            R.id.iv_right -> {
                key!!.moveToRight()
                return
            }

            R.id.iv_up -> {
                key!!.moveToUp()
                return
            }

            R.id.ll_confirm -> {
                EventBus.getDefault().post(
                    EventCenter(
                        2, InputFinishBean(
                            this.doorIgnition, toothListToStr(), this.doorToIgnition
                        )
                    )
                )
                onBack()
                return
            }

            else -> {}
        }
    }

    private fun round() {
        var list: List<String>? = null
        var indexOf = 0
        val toothCode: ArrayList<Array<String>> = key!!.getToothCode()
        var str = ""
        for (i in toothCode.indices) {
            for (i2 in toothCode[i].indices) {
                var str2: String = toothCode[i][i2]
                if (str2.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
                    val str3: String =
                        "0." + str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[1]
                    str2 =
                        str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    if (str3.toFloat() >= 0.5f && ((key!!.allDepthNames[i]
                            .also { list = it }).indexOf(str2)
                            .also { indexOf = it }) != list!!.size - 1
                    ) {
                        str2 = list!![indexOf + 1]
                    }
                }
                str = if (i2 == toothCode[i].size - 1) "$str$str2;" else "$str$str2,"
            }
        }
        key!!.setToothCodeAndInvalidate(str)
    }

    private fun toothListToStr(): String {
        val toothCode: ArrayList<Array<String>> = key!!.getToothCode()
        var str = ""
        for (i in toothCode.indices) {
            for (i2 in toothCode[i].indices) {
                str = if (i2 == toothCode[i].size - 1) str + toothCode[i][i2] + ";" else str + toothCode[i][i2] + ","
            }
        }
        Log.i(TAG, "toothListToStr: $str")
        return str
    }

    // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
    override fun onItemClick(baseQuickAdapter: BaseQuickAdapter<*, *>, view: View, i: Int) {
        val str: String = baseQuickAdapter.getData().get(i) as String
        if (TextUtils.isEmpty(str)) {
            return
        }
        key!!.changeSingleTooth(str)
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.input)
    }

    fun onCheckedChange(compoundButton: CompoundButton, z: Boolean) {
        val id: Int = compoundButton.id
        if (id == R.id.cb_invert) {
            invert()
            return
        }
        if (id != R.id.switch_decimal) {
            return
        }
        val key: Key? = this.key
        key?.setShowDecimal(z)
        if (z) {
            binding!!.btRounding.visibility = 0
        } else {
            binding!!.btRounding.visibility = 8
        }
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (eventCenter.eventCode == 15) {
            key!!.setToothCodeAndInvalidate(eventCenter.data as String?)
        } else if (eventCenter.eventCode == 22) {
            val inputFinishBean: InputFinishBean = eventCenter.data as InputFinishBean
            this.doorIgnition = true
            this.doorToIgnition = inputFinishBean.isDoorToIgnition
            key!!.setToothCodeAndInvalidate(inputFinishBean.toothCode)
        }
    }

    private fun invert() {
        var indexOf = 0
        val toothCode: ArrayList<Array<String>> = key!!.getToothCode()
        var str = ""
        for (i in toothCode.indices) {
            for (i2 in toothCode[i].indices) {
                val list: List<String> =
                    key!!.allDepthNames[i]
                var str2: String = toothCode[i][i2]
                if (str2.contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
                    val str3: String =
                        "0." + str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[1]
                    str2 =
                        str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                    if (str3.toFloat() >= 0.5f && (list.indexOf(str2)
                            .also { indexOf = it }) != list.size - 1
                    ) {
                        str2 = list[indexOf + 1]
                    }
                }
                val indexOf2: Int = list.indexOf(str2)
                val str4: String = if (indexOf2 != -1) list[(list.size - 1) - indexOf2] else "?"
                str = if (i2 == toothCode.get(i).size - 1) "$str$str4;" else "$str$str4,"
            }
        }
        key!!.setToothCodeAndInvalidate(str)
    }

    companion object {
        fun newInstance(keyInfo: KeyInfo): ToothCodeInputFragment {
            val toothCodeInputFragment = ToothCodeInputFragment()
            val bundle = Bundle()
            bundle.putParcelable(KeyInfoFragment.KEY_INFO, keyInfo)
            toothCodeInputFragment.setArguments(bundle)
            return toothCodeInputFragment
        }
    }
}
