package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ThemeUtils
import com.spl.key.SpecificParamUtils
import java.lang.reflect.Method

/* loaded from: classes.dex */
class KeySpaceWidthSetFragment() : BaseBackFragment() {
    private var currentEdit: EditText? = null
    private var customKey: CustomKey? = null

    var flRowTool: FrameLayout? = null
    var isDimple: Boolean = false

    var ivSpaceWidth: ImageView? = null

    var llIndex: LinearLayout? = null

    var llSpace: LinearLayout? = null

    var llSpaceTool: LinearLayout? = null
    private val myFocusChanged = this.MyFocusChanged()

    var tvSideRow: TextView? = null

    var tvUnit: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_spacewidth_set
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.space_width)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var editText: EditText
        var text: TextView?
        val customKey: CustomKey? =
            requireArguments().getParcelable<Parcelable>(CUSTOM_KEY) as CustomKey?
        this.customKey = customKey
        when (customKey!!.type) {
            0 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.doublekey_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.doublekey_tip_space_width)
            }

            1 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.singlekey_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.singlekey_tip_space_width)
            }

            2 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.double_internal_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.double_internal_tip_space_width)
            }

            3 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.single_external_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.single_external_tip_space_width)
            }

            4 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.double_external_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.double_external_tip_space_width)
            }

            5 -> if (this.customKey!!.align == 0) {
                ivSpaceWidth!!.setImageResource(R.drawable.single_internal_shoulder_space_width)
            } else {
                ivSpaceWidth!!.setImageResource(R.drawable.single_internal_tip_space_width)
            }

            6 -> {
                tvSideRow!!.text = "Rows"
                this.isDimple = true
            }

            7 -> ivSpaceWidth!!.setImageResource(R.drawable.tibbe_space_width)
        }
        val param: String = SpecificParamUtils.getParam(
            this.customKey!!.parameter_info, SpecificParamUtils.SIDE
        )!!
        val spaceWidth: String = this.customKey!!.space_width
        val split: Array<String>? =
            if (!TextUtils.isEmpty(spaceWidth)) spaceWidth.split(";".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray() else null
        val split2: Array<String> =
            this.customKey!!.space.split(";".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()
        val length: Int = if (this.customKey!!.isAbSame()) 1 else split2.size
        var i: Int = 0
        while (i < length) {
            if (!this.isDimple) {
                if (((this.customKey!!.type == 0) || (this.customKey!!.type == 2) || (this.customKey!!.type == 4)) && this.customKey!!.isAbSame) {
                    text =
                        getText("AB", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
                } else if (i == 0 && (TextUtils.isEmpty(param) || (param == "0") || (param == "3"))) {
                    text =
                        getText("A", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
                } else {
                    text =
                        getText("B", ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
                }
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(140, 30)
                layoutParams.setMargins(0, 1, 0, 0)
                llIndex!!.addView(text, layoutParams)
            } else {
                llIndex!!.addView(
                    getText(
                        (i + 1).toString(),
                        ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)
                    ), LinearLayout.LayoutParams(140, 30)
                )
            }
            val split3: Array<String>? =
                if ((split == null || i >= split.size)) null else split.get(i).split(",".toRegex())
                    .dropLastWhile({ it.isEmpty() }).toTypedArray()
            val linearLayout: LinearLayout = LinearLayout(getContext())
            linearLayout.setOrientation(0)
            var i2: Int = 0
            while (i2 < split2.get(i).split(",".toRegex()).dropLastWhile({ it.isEmpty() })
                    .toTypedArray().size
            ) {
                if (split3 == null) {
                    editText = getEditText(i2, "")
                } else {
                    editText = getEditText(i2, if (i2 < split3.size) split3.get(i2) else "")
                }
                if ((this.customKey!!.getType() != 6) && (i == 0) && (i2 == 0)) {
                    editText.requestFocus()
                }
                val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
                layoutParams2.setMargins(1, 1, 0, 0)
                linearLayout.addView(editText, layoutParams2)
                i2++
            }
            llSpace!!.addView(linearLayout)
            val linearLayout2: LinearLayout = LinearLayout(getContext())
            val text2: TextView = getText(getString(R.string.auto), R.drawable.bg_auto)
            text2.setTextColor(ThemeUtils.getColor(getContext(), R.attr.textColor_ffffff_333333))
            text2.setTag("auto")
            val layoutParams3: LinearLayout.LayoutParams = LinearLayout.LayoutParams(90, 30)
            layoutParams3.setMargins(16, 1, 0, 0)
            text2.setOnClickListener(SpaceToolClickListener(linearLayout))
            linearLayout2.addView(text2, layoutParams3)
            llSpaceTool!!.addView(linearLayout2)
            i++
        }
        if (this.customKey!!.isInch()) {
            tvUnit!!.setText(R.string._1inch_1000)
        }
    }

    private fun getEditText(i: Int, str: String): EditText {
        val editText: EditText = EditText(getContext())
        editText.setGravity(17)
        editText.setPadding(0, 0, 0, 0)
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        editText.setTextColor(-1)
        editText.setInputType(2)
        editText.setCursorVisible(false)
        editText.setTextSize(18.0f)
        editText.setFilters(arrayOf<InputFilter>(object : LengthFilter(5) {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeySpaceWidthSetFragment.1
        }))
        if (Build.VERSION.SDK_INT >= 21) {
            editText.setShowSoftInputOnFocus(false)
        } else {
            getActivity()!!.getWindow().setSoftInputMode(3)
            try {
                val method: Method = EditText::class.java.getMethod(
                    "setShowSoftInputOnFocus",
                    java.lang.Boolean.TYPE
                )
                method.setAccessible(true)
                method.invoke(editText, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        editText.setOnFocusChangeListener(this.myFocusChanged)
        if (TextUtils.isEmpty(str)) {
            editText.setHint((i + 1).toString())
        } else if (customKey!!.isInch()) {
            editText.setText(Math.round(str.toInt() / 2.54).toString())
        } else {
            editText.setText(str)
        }
        return editText
    }

    private fun getText(str: String, i: Int): TextView {
        val textView: TextView = TextView(getContext())
        textView.setGravity(17)
        textView.setBackgroundResource(i)
        textView.setPadding(0, 0, 0, 0)
        textView.setTextColor(-1)
        textView.setText(str)
        textView.setTextSize(0, 18.0f)
        return textView
    }

    inner class MyFocusChanged constructor() : OnFocusChangeListener {
        override fun onFocusChange(view: View, focused: Boolean) {
            if (focused) {
                this@KeySpaceWidthSetFragment.currentEdit = view as EditText?
                Log.i(TAG, "onFocusChange: " + this@KeySpaceWidthSetFragment.currentEdit)
            }
        }
    }

    fun onViewClicked(view: View) {
        val id: Int = view.id
        if (id == R.id.bt_delete) {
            delete()
            return
        }
        if (id == R.id.bt_last) {
            onBack()
            return
        }
        if (id != R.id.bt_next) {
            when (id) {
                R.id.bt_number_0, R.id.bt_number_1, R.id.bt_number_2, R.id.bt_number_3, R.id.bt_number_4, R.id.bt_number_5, R.id.bt_number_6, R.id.bt_number_7, R.id.bt_number_8, R.id.bt_number_9 -> {
                    inputNumb((view as Button).getText().toString().trim { it <= ' ' })
                    return
                }

                R.id.bt_number_last -> {
                    val r12: LinearLayout = currentEdit!!.parent as LinearLayout
                    val editText: EditText? =
                        r12.getChildAt(r12.indexOfChild(this.currentEdit) - 1) as EditText?
                    if (editText != null) {
                        editText.requestFocus()
                        return
                    }
                    return
                }

                R.id.bt_number_next -> {
                    val tag: Any? = currentEdit!!.tag
                    if (tag != null) {
                        ((llSpace!!.getChildAt(((tag as Int?)!!)) as LinearLayout).getChildAt(0) as EditText).requestFocus()
                        return
                    }
                    val linearLayout: LinearLayout = currentEdit!!.parent as LinearLayout
                    val editText2: EditText? = linearLayout.getChildAt(
                        linearLayout.indexOfChild(
                            this.currentEdit
                        ) + 1
                    ) as EditText?
                    if (editText2 != null) {
                        editText2.requestFocus()
                        return
                    }
                    return
                }

                else -> return
            }
        }
        var str = ""
        if (customKey!!.isAbSame && customKey!!.type != 0) {
            val linearLayout2: LinearLayout = llSpace!!.getChildAt(0) as LinearLayout
            for (i in 0 until linearLayout2.childCount) {
                val editText3: EditText = linearLayout2.getChildAt(i) as EditText
                val text: Editable = editText3.getText()
                if (TextUtils.isEmpty(text)) {
                    editText3.requestFocus()
                    ToastUtil.showToast(R.string.please_complete_the_data)
                    return
                }
                var parseInt: Int = text.toString().toInt()
                if (customKey!!.isInch()) {
                    parseInt = Math.round(parseInt * 2.54f)
                }
                str =
                    if (i == linearLayout2.childCount - 1) "$str$parseInt;" else "$str$parseInt,"
            }
            str += str
        } else {
            for (i2 in 0 until llSpace!!.childCount) {
                val linearLayout3: LinearLayout = llSpace!!.getChildAt(i2) as LinearLayout
                for (i3 in 0 until linearLayout3.childCount) {
                    val editText4: EditText = linearLayout3.getChildAt(i3) as EditText
                    val text2: Editable = editText4.getText()
                    if (TextUtils.isEmpty(text2)) {
                        editText4.requestFocus()
                        ToastUtil.showToast(R.string.please_complete_the_data)
                        return
                    }
                    var parseInt2: Int = text2.toString().toInt()
                    if (customKey!!.isInch()) {
                        parseInt2 = Math.round(parseInt2 * 2.54f)
                    }
                    str =
                        if (i3 == linearLayout3.childCount - 1) "$str$parseInt2;" else "$str$parseInt2,"
                }
            }
        }
        Log.i(TAG, "spaceWidth: $str")
        customKey!!.space_width = str
        start(KeyDepthSetFragment.Companion.newInstance(this.customKey))
    }

    private fun delete() {
        val trim: String = currentEdit!!.getText().toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(trim)) {
            return
        }
        currentEdit!!.setText(trim.substring(0, trim.length - 1))
    }

    private fun inputNumb(str: String) {
        currentEdit!!.append(str)
    }

    /* loaded from: classes.dex */
    private inner class SpaceToolClickListener(private val parent: LinearLayout) :
        View.OnClickListener {
        override fun onClick(view: View) {
            val str: String = view.tag as String
            Log.i(TAG, "onClick: $str")
            if (TextUtils.isEmpty(str) || str != "auto") {
                return
            }
            this@KeySpaceWidthSetFragment.auto(this.parent)
        }
    }

    fun auto(linearLayout: LinearLayout) {
        var str = ""
        for (i in 0 until linearLayout.childCount) {
            str = (linearLayout.getChildAt(i) as EditText).getText().toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(str) && "0" != str) {
                break
            }
        }
        if (!TextUtils.isEmpty(str) && "0" != str) {
            for (i2 in 0 until linearLayout.childCount) {
                (linearLayout.getChildAt(i2) as EditText).setText(str)
            }
            return
        }
        ToastUtil.showToast(R.string.please_enter_a_data_first)
    }

    companion object {
        private const val CUSTOM_KEY: String = "CUSTOMKEY"
        fun newInstance(customKey: CustomKey?): KeySpaceWidthSetFragment {
            val bundle = Bundle()
            bundle.putParcelable(CUSTOM_KEY, customKey)
            val keySpaceWidthSetFragment = KeySpaceWidthSetFragment()
            keySpaceWidthSetFragment.setArguments(bundle)
            return keySpaceWidthSetFragment
        }
    }
}
