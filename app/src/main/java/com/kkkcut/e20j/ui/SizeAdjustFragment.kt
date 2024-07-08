package com.kkkcut.e20j.ui

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.autolayout.utils.AutoUtils
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentSizeAdjustBinding
import com.kkkcut.e20j.utils.ThemeUtils
import com.kkkcut.e20j.utils.UnitUtils
import org.greenrobot.eventbus.EventBus
import kotlin.math.max

/* loaded from: classes.dex */
class SizeAdjustFragment : BaseBackFragment() {
    var keyInfo: KeyInfo? = null

    private var lastText: TextView? = null

    var binding: FragmentSizeAdjustBinding? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentSizeAdjustBinding.inflate(getLayoutInflater())
        val view: ScrollView = binding!!.getRoot()
        return view
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_size_adjust
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val keyInfo: KeyInfo? = arguments!!.getParcelable<Parcelable>(KEYINFO) as KeyInfo?
        this.keyInfo = keyInfo
        val spaceList: List<List<Int>> = keyInfo!!.getSpaceList()
        val spaceWidthList: List<List<Int>> =
            this.keyInfo!!.getSpaceWidthList()
        initSpaceIndex(spaceList)
        initSpaceAndSpaceWidth(spaceList, spaceWidthList)
        (binding!!.sizeContainer.getChildAt(1) as LinearLayout).getChildAt(1).performClick()
    }

    private fun initSpaceAndSpaceWidth(list: List<List<Int>>, list2: List<List<Int>>) {
        for (i in list.indices) {
            val list3: List<Int> = list[i]
            val list4: List<Int> = list2[i]
            val linearLayout = LinearLayout(requireContext())
            linearLayout.orientation = 1
            val linearLayout2 = LinearLayout(requireContext())
            linearLayout2.orientation = 1
            val textView = TextView(requireContext())
            textView.text = getString(R.string.spaces)
            textView.setTextColor(ThemeUtils.getColor(requireContext(), R.attr.textColor_ffffff_333333))
            textView.textSize = 16.0f
            linearLayout.addView(textView)
            val textView2 = TextView(requireContext())
            textView2.text = getString(R.string.width)
            textView2.setTextColor(
                ThemeUtils.getColor(
                    requireContext(),
                    R.attr.textColor_ffffff_333333
                )
            )
            textView2.textSize = 16.0f
            linearLayout2.addView(textView2)
            for (i2 in list3.indices) {
                linearLayout.addView(getSpaceText(list3[i2].toString(), 0), layoutParams)
                linearLayout2.addView(getSpaceText(list4[i2].toString(), 1), layoutParams)
            }
            if (i == 0) {
                binding!!.sizeContainer.addView(linearLayout)
            } else {
                binding!!.sizeContainer.addView(linearLayout, containerLayoutParam)
            }
            binding!!.sizeContainer.addView(linearLayout2, containerLayoutParam)
        }
    }

    private val containerLayoutParam: LinearLayout.LayoutParams
        get() {
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(-2, -2)
            layoutParams.setMargins(AutoUtils.getPercentWidthSize(2), 0, 0, 0)
            return layoutParams
        }

    private val layoutParams: LinearLayout.LayoutParams
        get() {
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                AutoUtils.getPercentWidthSize(60),
                AutoUtils.getPercentHeightSize(30)
            )
            layoutParams.setMargins(0, AutoUtils.getPercentHeightSize(2), 0, 0)
            return layoutParams
        }

    private fun initSpaceIndex(list: List<List<Int>>) {
        val maxSpaceCount: Int = getMaxSpaceCount(list)
        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = 1
        for (i in 0 until (maxSpaceCount + 1)) {
            val textView = TextView(requireContext())
            textView.textSize = 16.0f
            textView.setTextColor(ThemeUtils.getColor(requireContext(), R.attr.textColor_ffffff_333333))
            if (i == 0) {
                textView.text = ""
                linearLayout.addView(textView)
            } else {
                textView.text = i.toString()
                textView.setGravity(17)
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    AutoUtils.getPercentWidthSize(60),
                    AutoUtils.getPercentHeightSize(30)
                )
                layoutParams.setMargins(0, AutoUtils.getPercentHeightSize(2), 0, 0)
                linearLayout.addView(textView, layoutParams)
            }
        }
        binding!!.sizeContainer.addView(linearLayout)
    }

    private fun getSpaceText(str: String, i: Int): TextView {
        val textView = TextView(requireContext())
        textView.setGravity(17)
        textView.setBackgroundResource(
            ThemeUtils.getResId(
                requireContext(),
                R.attr.color_blackLight_blueDark
            )
        )
        textView.setTextColor(-1)
        textView.textSize = 20.0f
        textView.setOnClickListener(MyClickListener())
        textView.tag = i
        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
            textView.text = UnitUtils.mm2Inch(str.toInt()).toString()
        } else {
            textView.text = str
        }
        return textView
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class MyClickListener : View.OnClickListener {
        // android.view.View.OnClickListener
        override fun onClick(view: View) {
            if (this@SizeAdjustFragment.lastText === view) {
                return
            }
            val textView: TextView = view as TextView
            textView.getText()
            textView.setTextColor(-1)
            textView.setBackgroundResource(R.drawable.bg_ff205f)
            if (this@SizeAdjustFragment.lastText != null) {
                lastText!!.setTextColor(-1)
                lastText!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        this@SizeAdjustFragment.requireContext(),
                        R.attr.color_blackLight_blueDark
                    )
                )
            }
            this@SizeAdjustFragment.lastText = textView
            if ((textView.tag as Int) == 0) {
                binding!!.tvSpaceValue.text = textView.getText().toString().trim { it <= ' ' }
            } else {
                binding!!.tvSpaceWidthValue.text = textView.getText().toString().trim { it <= ' ' }
            }
        }
    }

    /* loaded from: classes.dex */
    private class Tag private constructor() {
        private val type: Int = 0
    }

    private fun getMaxSpaceCount(list: List<List<Int>>): Int {
        val it: Iterator<List<Int>> = list.iterator()
        var i: Int = 0
        while (it.hasNext()) {
            i = max(i.toDouble(), it.next().size.toDouble()).toInt()
        }
        return i
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.bt_save -> {
                val arrayList = ArrayList<Any>()
                val arrayList2 = ArrayList<Any>()
                val childCount: Int = binding!!.sizeContainer.childCount
                val sb: StringBuilder = StringBuilder()
                val sb2: StringBuilder = StringBuilder()
                var i: Int = 1
                var i2: Int = 1
                while (i2 < childCount - 1) {
                    val arrayList3 = ArrayList<Any>()
                    val linearLayout: LinearLayout =
                        binding!!.sizeContainer.getChildAt(i2) as LinearLayout
                    var i3: Int = 1
                    while (i3 < linearLayout.childCount) {
                        val trim: String =
                            (linearLayout.getChildAt(i3) as TextView).getText().toString()
                                .trim { it <= ' ' }
                        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                            sb.append(UnitUtils.inch2Mm(trim.toInt()))
                        } else {
                            sb.append(trim)
                        }
                        if (i3 == linearLayout.childCount - i) {
                            sb.append(";")
                        } else {
                            sb.append(",")
                        }
                        arrayList3.add(trim.toInt() as Nothing)
                        i3++
                    }
                    val linearLayout2: LinearLayout =
                        binding!!.sizeContainer.getChildAt(i2 + 1) as LinearLayout
                    val arrayList4 = ArrayList<Any>()
                    var i4: Int = 1
                    while (i4 < linearLayout2.childCount) {
                        val trim2: String =
                            (linearLayout2.getChildAt(i4) as TextView).getText().toString()
                                .trim { it <= ' ' }
                        if (SPUtils.getBoolean(SpKeys.UNIT_INCH)) {
                            sb2.append(UnitUtils.inch2Mm(trim2.toInt()))
                        } else {
                            sb2.append(trim2)
                        }
                        if (i4 == linearLayout2.childCount - 1) {
                            sb2.append(";")
                        } else {
                            sb2.append(",")
                        }
                        arrayList4.add(trim2.toInt())
                        i4++
                    }
                    arrayList.add(arrayList3)
                    arrayList2.add(arrayList4)
                    i2 += 2
                    i = 1
                }
                var i5: Int = 0
                while (i5 < arrayList.size) {
                    val list: List<*> = arrayList.get(i5) as List<*>
                    val list2: List<*> = arrayList2.get(i5) as List<*>
                    var i6: Int = 0
                    while (i6 < list.size - 1) {
                        val intValue: Int = (list.get(i6) as Int)
                        val i7: Int = i6 + 1
                        val intValue2: Int = (list.get(i7) as Int)
                        val intValue3: Int = (list2.get(i6) as Int)
                        val intValue4: Int = (list2.get(i7) as Int)
                        if (keyInfo!!.align == 0) {
                            if (intValue2 - intValue <= (intValue3 + intValue4) / 2) {
                                ToastUtil.showToast(getString(R.string.key_data_error))
                                return
                            }
                        } else if (intValue - intValue2 <= (intValue3 + intValue4) / 2) {
                            ToastUtil.showToast(getString(R.string.key_data_error))
                            return
                        }
                        i6 = i7
                    }
                    i5++
                }
                keyInfo!!.spaceStr = sb.toString()
                keyInfo!!.spaceWidthStr = sb2.toString()
                EventBus.getDefault().post(EventCenter<Any?>(57))
                onBack()
                return
            }

            R.id.iv_down -> {
                moveToDown()
                return
            }

            R.id.iv_left -> {
                moveToLeft()
                return
            }

            R.id.iv_right -> {
                moveToRight()
                return
            }

            R.id.iv_spaceWidth_add -> {
                modifySpaceWidth(10)
                return
            }

            R.id.iv_spaceWidth_reduce -> {
                modifySpaceWidth(-10)
                return
            }

            R.id.iv_space_add -> {
                modifySpace(10)
                return
            }

            R.id.iv_space_reduce -> {
                modifySpace(-10)
                return
            }

            R.id.iv_up -> {
                moveToUp()
                return
            }

            else -> return
        }
    }

    private fun moveToLeft() {
        val textView: TextView? = this.lastText
        if (textView == null) {
            moveToDefault()
            return
        }
        val linearLayout: LinearLayout = textView.parent as LinearLayout
        val indexOfChild: Int = linearLayout.indexOfChild(this.lastText)
        val indexOfChild2: Int = binding!!.sizeContainer.indexOfChild(linearLayout)
        if (indexOfChild2 > 1) {
            val linearLayout2: LinearLayout =
                binding!!.sizeContainer.getChildAt(indexOfChild2 - 1) as LinearLayout
            if (linearLayout2.childCount > indexOfChild) {
                linearLayout2.getChildAt(indexOfChild).performClick()
            } else {
                linearLayout2.getChildAt(linearLayout2.childCount - 1).performClick()
            }
        }
    }

    private fun moveToRight() {
        val textView: TextView? = this.lastText
        if (textView == null) {
            moveToDefault()
            return
        }
        val linearLayout: LinearLayout = textView.parent as LinearLayout
        val indexOfChild: Int = linearLayout.indexOfChild(this.lastText)
        val indexOfChild2: Int = binding!!.sizeContainer.indexOfChild(linearLayout)
        if (indexOfChild2 < binding!!.sizeContainer.childCount - 1) {
            val linearLayout2: LinearLayout =
                binding!!.sizeContainer.getChildAt(indexOfChild2 + 1) as LinearLayout
            if (linearLayout2.childCount > indexOfChild) {
                linearLayout2.getChildAt(indexOfChild).performClick()
            } else {
                linearLayout2.getChildAt(linearLayout2.childCount - 1).performClick()
            }
        }
    }

    private fun moveToUp() {
        val textView: TextView? = this.lastText
        if (textView == null) {
            moveToDefault()
            return
        }
        val linearLayout: LinearLayout = textView.parent as LinearLayout
        val indexOfChild: Int = linearLayout.indexOfChild(this.lastText)
        if (indexOfChild > 1) {
            linearLayout.getChildAt(indexOfChild - 1).performClick()
        }
    }

    private fun moveToDown() {
        val textView: TextView? = this.lastText
        if (textView == null) {
            moveToDefault()
            return
        }
        val linearLayout: LinearLayout = textView.parent as LinearLayout
        val indexOfChild: Int = linearLayout.indexOfChild(this.lastText)
        if (indexOfChild < linearLayout.childCount - 1) {
            linearLayout.getChildAt(indexOfChild + 1).performClick()
        }
    }

    private fun moveToDefault() {
        (binding!!.sizeContainer.getChildAt(1) as LinearLayout).getChildAt(1).performClick()
    }

    private fun modifySpace(i: Int) {
        selectSpaceDefault()
        modifySingleValue(binding!!.tvSpaceValue, i)
        if (binding!!.cbAllSpace.isChecked) {
            modifyAll(this.lastText, i)
        } else {
            modifySingleValue(this.lastText, i)
        }
    }

    private fun modifySpaceWidth(i: Int) {
        selectSpaceWidthDefault()
        modifySingleValue(binding!!.tvSpaceWidthValue, i)
        if (binding!!.cbAllSpaceWidth.isChecked) {
            modifyAll(this.lastText, i)
        } else {
            modifySingleValue(this.lastText, i)
        }
    }

    private fun modifyAll(textView: TextView?, i: Int) {
        val linearLayout: LinearLayout = textView!!.parent as LinearLayout
        for (i2 in 1 until linearLayout.childCount) {
            modifySingleValue(linearLayout.getChildAt(i2) as TextView?, i)
        }
    }

    private fun modifySingleValue(textView: TextView?, i: Int) {
        val trim: String = textView!!.getText().toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim)) {
            return
        }
        textView.text = (trim.toInt() + i).toString()
    }

    private fun selectSpaceDefault() {
        val textView: TextView? = this.lastText
        if (textView == null || (textView.tag as Int) == 1) {
            val textView2: TextView? = this.lastText
            ((binding!!.sizeContainer.getChildAt(
                if (textView2 != null) binding!!.sizeContainer.indexOfChild(
                    textView2.parent as View?
                ) - 1 else 1
            ) as LinearLayout).getChildAt(1) as TextView).performClick()
        }
    }

    private fun selectSpaceWidthDefault() {
        val textView: TextView? = this.lastText
        if (textView == null || (textView.tag as Int) == 0) {
            val textView2: TextView? = this.lastText
            ((binding!!.sizeContainer.getChildAt(
                if (textView2 != null) binding!!.sizeContainer.indexOfChild(
                    textView2.parent as View?
                ) + 1 else 2
            ) as LinearLayout).getChildAt(1) as TextView).performClick()
        }
    }

    companion object {
        private val KEYINFO: String = "SizeAdjustFragment"

        fun newInstance(keyInfo: KeyInfo?): SizeAdjustFragment {
            val bundle = Bundle()
            bundle.putParcelable(KEYINFO, keyInfo)
            val sizeAdjustFragment = SizeAdjustFragment()
            sizeAdjustFragment.setArguments(bundle)
            return sizeAdjustFragment
        }
    }
}
