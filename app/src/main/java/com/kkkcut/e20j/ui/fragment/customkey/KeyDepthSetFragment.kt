package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ThemeUtils
import com.spl.key.SpecificParamUtils
import java.lang.reflect.Method

/* loaded from: classes.dex */
class KeyDepthSetFragment() : BaseBackFragment() {
    private var currentEdit: EditText? = null
    private var customKey: CustomKey? = null
    private var isDimple: Boolean = false

    var ivSpace: ImageView? = null

    var llDepth: LinearLayout? = null

    var llDepthName: LinearLayout? = null

    var llDepthTool: LinearLayout? = null

    var llIndex: LinearLayout? = null

    var llSide: LinearLayout? = null
    private val myOnfocusChanged = this.MyOnfocusChanged()
    private val rowCount: Int = 0

    var tvRowAdd: ImageView? = null

    var tvRowReduce: ImageView? = null

    var tvSideRow: TextView? = null

    var tvUnit: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_space_depth_set
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.depth)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var depthNameEditText: EditText?
        var editText: EditText
        var text: TextView?
        val customKey: CustomKey? =
            getArguments()!!.getParcelable<Parcelable>(CUSTOMKEY) as CustomKey?
        this.customKey = customKey
        var i: Int = 1
        when (customKey!!.getType()) {
            0 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.doublekey_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.doublekey_depth_tip)
            }

            1 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.singlekey_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.singlekey_depth_tip)
            }

            2 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.double_internal_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.double_internal_depth_tip)
            }

            3 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.single_external_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.single_external_depth_tip)
            }

            4 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.double_external_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.double_external_depth_tip)
            }

            5 -> if (this.customKey!!.getAlign() == 0) {
                ivSpace!!.setImageResource(R.drawable.single_internal_depth_shoulder)
            } else {
                ivSpace!!.setImageResource(R.drawable.single_internal_depth_tip)
            }

            6 -> {
                ivSpace!!.setImageResource(R.drawable.dimple_depth)
                tvSideRow!!.setText("Rows")
                this.isDimple = true
            }

            7 -> ivSpace!!.setImageResource(R.drawable.tibbe_depth)
            8 -> ivSpace!!.setImageResource(R.drawable.tubular_depth)
        }
        val param: String = SpecificParamUtils.getParam(
            this.customKey!!.getParameter_info(), SpecificParamUtils.SIDE
        )!!
        val split: Array<String> =
            this.customKey!!.getSpace().split(";".toRegex()).dropLastWhile({ it.isEmpty() })
                .toTypedArray()
        val depth: String = this.customKey!!.getDepth()
        val split2: Array<String>? = if (!TextUtils.isEmpty(depth)) depth.split(";".toRegex())
            .dropLastWhile({ it.isEmpty() }).toTypedArray() else null
        val length: Int = if (this.customKey!!.isAbSame()) 1 else split.size
        var i2: Int = 0
        var i3: Int = 0
        while (true) {
            var i4: Int = 60
            if (i3 < length) {
                if (!this.isDimple) {
                    if (((this.customKey!!.getType() == 0) || (this.customKey!!.getType() == 2) || (this.customKey!!.getType() == 4)) && this.customKey!!.isAbSame()) {
                        text = getText(
                            "AB",
                            ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)
                        )
                    } else if (i3 == 0 && (TextUtils.isEmpty(param) || (param == "0") || (param == "3"))) {
                        text = getText(
                            "A",
                            ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)
                        )
                    } else {
                        text = getText(
                            "B",
                            ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)
                        )
                    }
                    val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(70, 30)
                    layoutParams.setMargins(i2, i, i2, i2)
                    llIndex!!.addView(text, layoutParams)
                } else {
                    val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(70, 30)
                    layoutParams2.setMargins(i2, i, i2, i2)
                    llIndex!!.addView(
                        getText(
                            (i3 + 1).toString(),
                            ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input)
                        ), layoutParams2
                    )
                }
                val linearLayout: LinearLayout = LinearLayout(getContext())
                linearLayout.setOrientation(i2)
                val split3: Array<String>? =
                    if ((split2 == null || i3 >= split2.size)) null else split2.get(i3)
                        .split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val length2: Int = if (split3 != null) split3.size else 5
                var i5: Int = 0
                while (i5 < length2) {
                    if (split3 == null) {
                        editText = getEditText(i5, "")
                    } else {
                        var str: String = split3.get(i5)
                        if (this.customKey!!.isInch()) {
                            str = Math.round(split3.get(i5).toInt() / 2.54f).toString()
                        }
                        editText = getEditText(i5, str)
                    }
                    if ((this.customKey!!.getType() != 6) && (i3 == 0) && (i5 == 0)) {
                        editText.requestFocus()
                    }
                    val layoutParams3: LinearLayout.LayoutParams = LinearLayout.LayoutParams(i4, 30)
                    layoutParams3.setMargins(1, 1, 0, 0)
                    linearLayout.addView(editText, layoutParams3)
                    i5++
                    i4 = 60
                }
                llDepth!!.addView(linearLayout)
                val linearLayout2: LinearLayout = LinearLayout(getContext())
                val imageView: ImageView = getImageView(R.drawable.space_reduce)
                imageView.setTag("-")
                val layoutParams4: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
                layoutParams4.setMargins(10, 1, 0, 0)
                imageView.setOnClickListener(SpaceToolClickListener(linearLayout))
                linearLayout2.addView(imageView, layoutParams4)
                val imageView2: ImageView = getImageView(R.drawable.space_add)
                imageView2.setTag("+")
                val layoutParams5: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
                layoutParams5.setMargins(10, 1, 0, 0)
                imageView2.setOnClickListener(SpaceToolClickListener(linearLayout))
                linearLayout2.addView(imageView2, layoutParams5)
                val text2: TextView = getText(getString(R.string.auto), R.drawable.bg_auto)
                text2.setTextColor(
                    ThemeUtils.getColor(
                        getContext(),
                        R.attr.textColor_ffffff_333333
                    )
                )
                text2.setTag("auto")
                val layoutParams6: LinearLayout.LayoutParams = LinearLayout.LayoutParams(90, 30)
                layoutParams6.setMargins(16, 1, 0, 0)
                text2.setOnClickListener(SpaceToolClickListener(linearLayout))
                linearLayout2.addView(text2, layoutParams6)
                llDepthTool!!.addView(linearLayout2)
                i3++
                i = 1
                i2 = 0
            } else {
                val depth_name: String = this.customKey!!.getDepth_name()
                val split4: Array<String>? =
                    if (!TextUtils.isEmpty(depth_name)) depth_name.split(";".toRegex())
                        .dropLastWhile({ it.isEmpty() }).toTypedArray().get(0).split(",".toRegex())
                        .dropLastWhile({ it.isEmpty() }).toTypedArray() else null
                for (i6 in 0..11) {
                    if (split4 != null && i6 < split4.size) {
                        depthNameEditText = getDepthNameEditText(i6, split4.get(i6))
                    } else {
                        depthNameEditText = getDepthNameEditText(i6, "")
                    }
                    val layoutParams7: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
                    layoutParams7.setMargins(1, 0, 0, 0)
                    llDepthName!!.addView(depthNameEditText, layoutParams7)
                }
                llDepthName!!.addView(Space(getContext()), LinearLayout.LayoutParams(0, 0, 1.0f))
                val text3: TextView = getText(getString(R.string.auto), R.drawable.bg_auto)
                text3.setTextColor(
                    ThemeUtils.getColor(
                        getContext(),
                        R.attr.textColor_ffffff_333333
                    )
                )
                val layoutParams8: LinearLayout.LayoutParams = LinearLayout.LayoutParams(90, 30)
                layoutParams8.setMargins(16, 0, 0, 0)
                text3.setOnClickListener(object : View.OnClickListener {
                    // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.1
                    // android.view.View.OnClickListener
                    override fun onClick(view: View) {
                        this@KeyDepthSetFragment.autoDepthName()
                    }
                })
                llDepthName!!.addView(text3, layoutParams8)
                if (this.customKey!!.isInch()) {
                    tvUnit!!.setText(R.string._1inch_1000)
                    return
                }
                return
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun autoDepthName() {
        var i: Int = 0
        val trim: String =
            (llDepthName!!.getChildAt(0) as EditText).getText().toString().trim({ it <= ' ' })
        if (TextUtils.isEmpty(trim)) {
            return
        }
        val c: Char = trim.toCharArray().get(0)
        if (c > '@' && c < '[') {
            val childCount: Int = llDepthName!!.getChildCount()
            while (i < childCount - 2) {
                val editText: EditText = llDepthName!!.getChildAt(i) as EditText
                var c2: Char = (c.code + i).toChar()
                if (c2 > 'Z') {
                    c2 = (c2.code - 26).toChar()
                }
                editText.setText(c2.toString())
                i++
            }
            return
        }
        if (c > '`' && c < '{') {
            val childCount2: Int = llDepthName!!.getChildCount()
            while (i < childCount2 - 2) {
                val editText2: EditText = llDepthName!!.getChildAt(i) as EditText
                var c3: Char = (c.code + i).toChar()
                if (c3 > 'z') {
                    c3 = (c3.code - 26).toChar()
                }
                editText2.setText(c3.toString())
                i++
            }
            return
        }
        if (c <= '/' || c >= ':') {
            return
        }
        val childCount3: Int = llDepthName!!.getChildCount()
        while (i < childCount3 - 2) {
            val editText3: EditText = llDepthName!!.getChildAt(i) as EditText
            var c4: Char = (c.code + i).toChar()
            if (c4 > '9') {
                c4 = (c4.code + 7).toChar()
            }
            editText3.setText(c4.toString())
            i++
        }
    }

    fun onViewClicked(view: View) {
        val str: String
        val str2: String
        var str3: String?
        var str4: String?
        var str5: String?
        val id: Int = view.getId()
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
                    inputNumb((view as Button).getText().toString().trim({ it <= ' ' }))
                    return
                }

                R.id.bt_number_last -> {
                    changeLast()
                    return
                }

                R.id.bt_number_next -> {
                    changeNext()
                    return
                }

                else -> return
            }
        }
        val isAbSame: Boolean = customKey!!.isAbSame()
        var i: Int = 10
        var i2: Int = R.string.cannot_be_negative
        var str6: String = ""
        if (isAbSame && customKey!!.getType() != 0) {
            val linearLayout: LinearLayout = llDepth!!.getChildAt(0) as LinearLayout
            var str7: String = ""
            var i3: Int = 0
            while (i3 < linearLayout.getChildCount()) {
                val editText: EditText = linearLayout.getChildAt(i3) as EditText
                var obj: String = editText.getText().toString()
                if (TextUtils.isEmpty(obj)) {
                    editText.requestFocus()
                    ToastUtil.showToast(R.string.please_complete_the_data)
                    return
                }
                if (obj.contains("-")) {
                    editText.requestFocus()
                    ToastUtil.showToast(R.string.cannot_be_negative)
                    return
                }
                var trim: String = (llDepthName!!.getChildAt(i3) as EditText).getText().toString()
                    .trim({ it <= ' ' })
                if (TextUtils.isEmpty(trim)) {
                    val i4: Int = i3 + 1
                    if (i4 == i) {
                        trim = "A"
                    } else if (i4 == 11) {
                        trim = "B"
                    } else {
                        trim = if (i4 == 12) "C" else i4.toString()
                    }
                }
                if (customKey!!.isInch()) {
                    obj = Math.round(obj.toInt() * 2.54f).toString()
                }
                if (i3 == linearLayout.getChildCount() - 1) {
                    str4 = str6 + obj + ";"
                    str5 = str7 + trim + ";"
                } else {
                    str4 = str6 + obj + ","
                    str5 = str7 + trim + ","
                }
                str7 = str5
                str6 = str4
                i3++
                i = 10
            }
            str2 = str6 + str6
            str = str7 + str7
        } else {
            var str8: String = ""
            var i5: Int = 0
            while (i5 < llDepth!!.getChildCount()) {
                val linearLayout2: LinearLayout = llDepth!!.getChildAt(i5) as LinearLayout
                var i6: Int = 0
                while (i6 < linearLayout2.getChildCount()) {
                    val editText2: EditText = linearLayout2.getChildAt(i6) as EditText
                    var obj2: String = editText2.getText().toString()
                    if (TextUtils.isEmpty(obj2)) {
                        editText2.requestFocus()
                        ToastUtil.showToast(R.string.please_complete_the_data)
                        return
                    }
                    if (obj2.contains("-")) {
                        editText2.requestFocus()
                        ToastUtil.showToast(i2)
                        return
                    }
                    var trim2: String =
                        (llDepthName!!.getChildAt(i6) as EditText).getText().toString()
                            .trim({ it <= ' ' })
                    if (TextUtils.isEmpty(trim2)) {
                        val i7: Int = i6 + 1
                        if (i7 == 10) {
                            trim2 = "A"
                        } else if (i7 == 11) {
                            trim2 = "B"
                        } else {
                            trim2 = if (i7 == 12) "C" else i7.toString()
                        }
                    }
                    if (customKey!!.isInch()) {
                        obj2 = Math.round(obj2.toInt() * 2.54f).toString()
                    }
                    if (i6 == linearLayout2.getChildCount() - 1) {
                        str3 = str6 + obj2 + ";"
                        str8 = str8 + trim2 + ";"
                    } else {
                        str3 = str6 + obj2 + ","
                        str8 = str8 + trim2 + ","
                    }
                    str6 = str3
                    i6++
                    i2 = R.string.cannot_be_negative
                }
                i5++
                i2 = R.string.cannot_be_negative
            }
            str = str8
            str2 = str6
        }
        Log.i(TAG, "depth: " + str2)
        customKey!!.setDepth(str2)
        Log.i(TAG, "depthName: " + str)
        customKey!!.setDepth_name(str)
        start(KeyShapeSetFragment.Companion.newInstance(this.customKey))
    }

    private fun changeLast() {
        val r0: LinearLayout = currentEdit!!.getParent() as LinearLayout
        val editText: EditText? =
            (r0).getChildAt(r0.indexOfChild(this.currentEdit) - 1) as EditText?
        if (editText != null) {
            editText.requestFocus()
        }
    }

    private fun changeNext() {
        val tag: Any? = currentEdit!!.getTag()
        if (tag != null) {
            ((llDepth!!.getChildAt(((tag as Int?)!!)) as LinearLayout).getChildAt(0) as EditText).requestFocus()
            return
        }
        val linearLayout: LinearLayout = currentEdit!!.getParent() as LinearLayout
        val editText: EditText? =
            linearLayout.getChildAt(linearLayout.indexOfChild(this.currentEdit) + 1) as EditText?
        if (editText != null) {
            editText.requestFocus()
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
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.2
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
        editText.setOnFocusChangeListener(this.myOnfocusChanged)
        if (TextUtils.isEmpty(str)) {
            editText.setHint((i + 1).toString())
        } else {
            editText.setText(str)
        }
        return editText
    }

    private fun getDepthNameEditText(i: Int, str: String): EditText {
        val editText: EditText = EditText(getContext())
        editText.setGravity(17)
        editText.setPadding(0, 0, 0, 0)
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        editText.setTextColor(-1)
        editText.setCursorVisible(true)
        editText.setTextSize(18.0f)
        editText.setInputType(4096)
        editText.setFilters(arrayOf<InputFilter>(object : AdnNameLengthFilter(editText) {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyDepthSetFragment.3
        }))
        editText.setOnFocusChangeListener(this.myOnfocusChanged)
        if (TextUtils.isEmpty(str)) {
            editText.setHint((i + 1).toString())
        } else {
            editText.setText(str)
        }
        return editText
    }

    /* loaded from: classes.dex */
    open class AdnNameLengthFilter(private val editText: EditText) : InputFilter {
        // android.text.InputFilter
        override fun filter(
            charSequence: CharSequence,
            i: Int,
            i2: Int,
            spanned: Spanned,
            i3: Int,
            i4: Int
        ): CharSequence? {
            Log.d(
                TAG,
                "filter() called with: source = [" + (charSequence as Any) + "], start = [" + i + "], end = [" + i2 + "], dest = [" + (spanned as Any) + "], dstart = [" + i3 + "], dend = [" + i4 + "]"
            )
            if (spanned.isEmpty()) {
                return charSequence
            }
            editText.setText(charSequence)
            return null
        }
    }

    private fun getText(str: String, i: Int): TextView {
        val textView = TextView(context)
        textView.gravity = 17
        textView.setBackgroundResource(i)
        textView.setPadding(0, 0, 0, 0)
        textView.setTextColor(-1)
        textView.text = str
        textView.setTextSize(0, 18.0f)
        return textView
    }

    private fun getImageView(i: Int): ImageView {
        return ImageView(context).also { it.setImageResource(i) }
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

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class MyOnfocusChanged constructor() : OnFocusChangeListener {
        // android.view.View.OnFocusChangeListener
        override fun onFocusChange(view: View, z: Boolean) {
            if (z) {
                this@KeyDepthSetFragment.currentEdit = view as EditText?
            }
        }
    }

    /* loaded from: classes.dex */
    private inner class SpaceToolClickListener(private val parent: LinearLayout) :
        View.OnClickListener {
        // android.view.View.OnClickListener
        override fun onClick(view: View) {
            val str: String = view.tag as String
            Log.i(TAG, "onClick: $str")
            if (TextUtils.isEmpty(str)) {
                return
            }
            if ((str == "-")) {
                this@KeyDepthSetFragment.reduce(this.parent)
            } else if ((str == "+")) {
                this@KeyDepthSetFragment.add(this.parent)
            } else if ((str == "auto")) {
                this@KeyDepthSetFragment.auto(this.parent)
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun reduce(linearLayout: LinearLayout) {
        val childCount: Int = linearLayout.childCount
        if (childCount > 0) {
            linearLayout.removeViewAt(childCount - 1)
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun add(linearLayout: LinearLayout) {
        val childCount: Int = linearLayout.childCount
        if (childCount == 12) {
            return
        }
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
        layoutParams.setMargins(1, 1, 0, 0)
        linearLayout.addView(getEditText(childCount, ""), layoutParams)
    }

    fun auto(linearLayout: LinearLayout) {
        val arrayList = ArrayList<BitIndex>()
        val childCount: Int = linearLayout.childCount
        for (i in 0 until childCount) {
            val trim: String =
                (linearLayout.getChildAt(i) as EditText).getText().toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(BitIndex(i, trim.toInt()))
                if (arrayList.size == 2) {
                    val space: Int =
                        (arrayList[0].space - arrayList[1].space) / (arrayList[0].index - arrayList[1].index)
                    for (i2 in 0 until childCount) {
                        (linearLayout.getChildAt(i2) as EditText).setText(
                            (((i2 - arrayList[0].index) * space) + arrayList[0].space).toString()
                        )
                    }
                    return
                }
            }
        }
    }

    inner class BitIndex(val index: Int, val space: Int)
    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"
        fun newInstance(customKey: CustomKey?): KeyDepthSetFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            val keyDepthSetFragment: KeyDepthSetFragment = KeyDepthSetFragment()
            keyDepthSetFragment.setArguments(bundle)
            return keyDepthSetFragment
        }
    }
}
