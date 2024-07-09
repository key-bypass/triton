package com.kkkcut.e20j.ui.fragment.customkey

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ThemeUtils
import com.spl.key.SpecificParamUtils
import java.lang.reflect.Method

/* loaded from: classes.dex */
class KeySpaceSetFragment : BaseBackFragment() {
    private var abSame: Boolean = false
    private var currentEdit: EditText? = null
    private var customKey: CustomKey? = null

    var flRowTool: FrameLayout? = null
    private var isDimple: Boolean = false

    var ivSpace: ImageView? = null

    var llIndex: LinearLayout? = null

    var llSide: LinearLayout? = null

    var llSpace: LinearLayout? = null

    var llSpaceTool: LinearLayout? = null
    private val myOnfocusChanged: MyOnfocusChanged = this.MyOnfocusChanged()

    var rbInch: RadioButton? = null

    var rbMetric: RadioButton? = null

    var rbSideA: RadioButton? = null

    var rbSideB: RadioButton? = null
    private var rowCount: Int = 0
    private var side: String? = null

    var tvRowAdd: ImageView? = null

    var tvRowReduce: ImageView? = null

    var tvRows: TextView? = null

    var tvSideRow: TextView? = null

    var tvUnit: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_keyspace_set
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        var editText: EditText
        var strArr: Array<String>?
        var editText2: EditText
        var split: Array<String>
        var text: TextView?
        val customKey: CustomKey? = arguments!!.getParcelable(CUSTOMKEY)
        this.customKey = customKey
        val space: String = customKey!!.space
        val split2: Array<String>? = if (!TextUtils.isEmpty(space)) space.split(";".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray() else null
        val row_pos: String = this.customKey!!.row_pos
        val split3: Array<String>? = if (!TextUtils.isEmpty(row_pos)) row_pos.split(";".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray() else null
        when (this.customKey!!.type) {
            0 -> {
                if (split2 != null && split2.size == 1) {
                    this.abSame = true
                }
                this.rowCount = 2
                llSide!!.visibility = 0
                rbSideA!!.text = getString(R.string.ab_diff)
                rbSideA!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doublekey_abdiff
                    ), null as Drawable?, null as Drawable?
                )
                rbSideB!!.text = getString(R.string.ab_same)
                rbSideB!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doublekey_absame
                    ), null as Drawable?, null as Drawable?
                )
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.doublekey_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.doublekey_tip_space)
                }
            }

            1 -> {
                this.rowCount = 1
                llSide!!.visibility = 8
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.singlekey_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.singlekey_tip_space)
                }
            }

            2 -> {
                if ((split2 != null) && (split2.size == 2) && (split2[0] == split2[1])) {
                    this.abSame = true
                }
                this.rowCount = 2
                llSide!!.visibility = 0
                rbSideA!!.text = getString(R.string.ab_diff)
                rbSideA!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doubleinside_abdiff
                    ), null as Drawable?, null as Drawable?
                )
                rbSideB!!.text = getString(R.string.ab_same)
                rbSideB!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doubleinside_absame
                    ), null as Drawable?, null as Drawable?
                )
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.double_internal_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.double_internal_tip_space)
                }
            }

            3 -> {
                this.rowCount = 1
                llSide!!.visibility = View.VISIBLE
                rbSideA!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.singleoutside_down
                    ), null as Drawable?, null as Drawable?
                )
                rbSideB!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.singleoutside_up
                    ), null as Drawable?, null as Drawable?
                )
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.single_external_down_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.single_external_down_tip_space)
                }
            }

            4 -> {
                if ((split2 != null) && (split2.size == 2) && (split2[0] == split2[1])) {
                    this.abSame = true
                }
                this.rowCount = 2
                llSide!!.visibility = 0
                rbSideA!!.text = getString(R.string.ab_diff)
                rbSideA!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doubleoutside_abdiff
                    ), null as Drawable?, null as Drawable?
                )
                rbSideB!!.text = getString(R.string.ab_same)
                rbSideB!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.doubleoutside_absame
                    ), null as Drawable?, null as Drawable?
                )
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.double_external_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.double_external_tip_space)
                }
            }

            5 -> {
                this.rowCount = 1
                llSide!!.visibility = 0
                rbSideA!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.singleinside_a
                    ), null as Drawable?, null as Drawable?
                )
                rbSideB!!.setCompoundDrawablesWithIntrinsicBounds(
                    null as Drawable?, resources.getDrawable(
                        R.drawable.singleinside_b
                    ), null as Drawable?, null as Drawable?
                )
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.single_internal_shoulder_a_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.single_internal_tip_a_space)
                }
            }

            6 -> {
                llSide!!.visibility = 8
                if (this.customKey!!.align == 0) {
                    ivSpace!!.setImageResource(R.drawable.dimple_shoulder_space)
                } else {
                    ivSpace!!.setImageResource(R.drawable.dimple_tip_space)
                }
                tvSideRow!!.text = "Row Position"
                if (split3 == null) {
                    this.rowCount = 2
                } else {
                    this.rowCount = split2!!.size
                }
                this.isDimple = true
                flRowTool!!.visibility = 0
                tvRows!!.text = rowCount.toString()
            }

            7 -> {
                llSide!!.visibility = 8
                this.rowCount = 2
                ivSpace!!.setImageResource(R.drawable.tibbe_space)
            }

            8 -> {
                this.rowCount = 1
                llSide!!.visibility = 8
                ivSpace!!.setImageResource(R.drawable.tubular_space)
                tvUnit!!.text = getString(R.string._1o_100)
            }
        }
        for (i in 0 until this.rowCount) {
            if (!this.isDimple) {
                if (i == 0) {
                    val param: String = SpecificParamUtils.getParam(
                        this.customKey!!.parameter_info, SpecificParamUtils.SIDE
                    )!!
                    if (!TextUtils.isEmpty(param) && (param == "1")) {
                        text = getText(
                            "B",
                            ThemeUtils.getResId(context, R.attr.bg_customkey_input)
                        )
                        rbSideB!!.setChecked(true)
                    } else {
                        text = getText(
                            "A",
                            ThemeUtils.getResId(context, R.attr.bg_customkey_input)
                        )
                    }
                } else {
                    text =
                        getText("B", ThemeUtils.getResId(context, R.attr.bg_customkey_input))
                }
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(70, 30)
                layoutParams.setMargins(0, 1, 0, 0)
                llIndex!!.addView(text, layoutParams)
            } else {
                val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(70, 30)
                layoutParams2.setMargins(0, 1, 0, 0)
                editText = if (split3 == null) {
                    getEditText(i, "")
                } else {
                    getEditText(i, split3[i])
                }
                editText.tag = i
                if (i == 0) {
                    editText.requestFocus()
                }
                llIndex!!.addView(editText, layoutParams2)
            }
            val linearLayout: LinearLayout = LinearLayout(context)
            linearLayout.orientation = 0
            var i2: Int = 5
            if (split2 != null) {
                split = if (this.abSame) {
                    split2.get(0).split(",".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                } else {
                    split2.get(i).split(",".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                }
                strArr = split
                i2 = split.size
            } else {
                strArr = null
            }
            for (i3 in 0 until i2) {
                editText2 = if (strArr == null) {
                    getEditText(i3, "")
                } else {
                    getEditText(i3, strArr[i3])
                }
                if ((this.customKey!!.type != 6) && (i == 0) && (i3 == 0)) {
                    editText2.requestFocus()
                }
                val layoutParams3: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
                layoutParams3.setMargins(1, 1, 0, 0)
                linearLayout.addView(editText2, layoutParams3)
            }
            llSpace!!.addView(linearLayout)
            val linearLayout2: LinearLayout = LinearLayout(context)
            val imageView: ImageView = getImageView(R.drawable.space_reduce)
            imageView.tag = "-"
            val layoutParams4: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
            layoutParams4.setMargins(10, 1, 0, 0)
            imageView.setOnClickListener(SpaceToolClickListener(linearLayout))
            linearLayout2.addView(imageView, layoutParams4)
            val imageView2: ImageView = getImageView(R.drawable.space_add)
            imageView2.tag = "+"
            val layoutParams5: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
            layoutParams5.setMargins(10, 1, 0, 0)
            imageView2.setOnClickListener(SpaceToolClickListener(linearLayout))
            linearLayout2.addView(imageView2, layoutParams5)
            val text2: TextView = getText(getString(R.string.auto), R.drawable.bg_auto)
            text2.tag = "auto"
            val layoutParams6: LinearLayout.LayoutParams = LinearLayout.LayoutParams(90, 30)
            layoutParams6.setMargins(16, 1, 0, 0)
            text2.setOnClickListener(SpaceToolClickListener(linearLayout))
            linearLayout2.addView(text2, layoutParams6)
            llSpaceTool!!.addView(linearLayout2)
        }
        if (this.abSame) {
            rbSideB!!.setChecked(true)
        }
        this.customKey!!.setInch(false)
    }

    private fun getEditText(i: Int, str: String): EditText {
        val editText = EditText(context)
        editText.setGravity(17)
        editText.setPadding(0, 0, 0, 0)
        editText.setBackgroundResource(ThemeUtils.getResId(context, R.attr.bg_customkey_input))
        editText.setTextColor(-1)
        editText.setInputType(2)
        editText.setCursorVisible(false)
        editText.textSize = 18.0f
        editText.setFilters(arrayOf<InputFilter>(object : LengthFilter(5) {
        }))
        if (Build.VERSION.SDK_INT >= 21) {
            editText.setShowSoftInputOnFocus(false)
        } else {
            activity!!.window.setSoftInputMode(3)
            try {
                val method: Method = EditText::class.java.getMethod(
                    "setShowSoftInputOnFocus",
                    java.lang.Boolean.TYPE
                )
                method.isAccessible = true
                method.invoke(editText, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        editText.onFocusChangeListener = this.myOnfocusChanged
        if (TextUtils.isEmpty(str)) {
            editText.setHint((i + 1).toString())
        } else {
            editText.setText(str)
        }
        return editText
    }

    private fun getText(str: String, i: Int): TextView {
        val textView: TextView = TextView(context)
        textView.setGravity(17)
        textView.setBackgroundResource(i)
        textView.setPadding(0, 0, 0, 0)
        textView.setTextColor(-1)
        textView.text = str
        textView.setTextSize(0, 18.0f)
        return textView
    }

    private fun getImageView(i: Int): ImageView {
        val imageView: ImageView = ImageView(context)
        imageView.setImageResource(i)
        return imageView
    }

    override fun setTitleStr(): String {
        return getString(R.string.space_loacation)
    }

    fun onViewClicked(view: View) {
        var str: String
        var str2: String?
        var str3: String?
        when (val id: Int = view.id) {
            R.id.bt_delete -> {
                delete()
                return
            }

            R.id.bt_last -> {
                onBack()
                return
            }

            R.id.bt_next -> {
                val type: Int = customKey!!.type
                var i: Int = R.string.please_complete_the_data
                if (type == 6) {
                    var str4: String = ""
                    var i2: Int = 0
                    while (i2 < llIndex!!.childCount) {
                        val editText: EditText = llIndex!!.getChildAt(i2) as EditText
                        val trim: String = editText.getText().toString().trim { it <= ' ' }
                        if (TextUtils.isEmpty(trim)) {
                            editText.requestFocus()
                            ToastUtil.showToast(R.string.please_complete_the_data)
                            hideSoftInput()
                            return
                        } else {
                            if (trim.contains("-")) {
                                editText.requestFocus()
                                ToastUtil.showToast(R.string.cannot_be_negative)
                                return
                            }
                            var parseInt: Int = trim.toInt()
                            if (customKey!!.isInch()) {
                                parseInt = Math.round(parseInt * 2.54f)
                            }
                            str4 = "$str4$parseInt;"
                        }
                        i2++
                    }
                    customKey!!.row_pos = str4
                    Log.i(TAG, "row_position: $str4")
                }
                if (this.abSame) {
                    val linearLayout: LinearLayout = llSpace!!.getChildAt(0) as LinearLayout
                    str = ""
                    var i3: Int = 0
                    while (i3 < linearLayout.childCount) {
                        val editText2: EditText = linearLayout.getChildAt(i3) as EditText
                        val text: Editable = editText2.getText()
                        if (TextUtils.isEmpty(text)) {
                            editText2.requestFocus()
                            ToastUtil.showToast(R.string.please_complete_the_data)
                            return
                        }
                        var parseInt2: Int = text.toString().toInt()
                        if (customKey!!.isInch()) {
                            parseInt2 = Math.round(parseInt2 * 2.54f)
                        }
                        str =
                            if (i3 == linearLayout.childCount - 1) "$str$parseInt2;" else "$str$parseInt2,"
                        i3++
                    }
                    if (customKey!!.type == 4 || customKey!!.type == 2) {
                        str += str
                    }
                } else {
                    str = ""
                    var i4: Int = 0
                    while (i4 < llSpace!!.childCount) {
                        val linearLayout2: LinearLayout = llSpace!!.getChildAt(i4) as LinearLayout
                        var i5: Int = 0
                        while (i5 < linearLayout2.childCount) {
                            val editText3: EditText = linearLayout2.getChildAt(i5) as EditText
                            val text2: Editable = editText3.getText()
                            if (TextUtils.isEmpty(text2)) {
                                editText3.requestFocus()
                                ToastUtil.showToast(i)
                                return
                            }
                            var parseInt3: Int = text2.toString().toInt()
                            if (customKey!!.isInch()) {
                                parseInt3 = Math.round(parseInt3 * 2.54f)
                            }
                            str =
                                if (i5 == linearLayout2.childCount - 1) "$str$parseInt3;" else "$str$parseInt3,"
                            i5++
                            i = R.string.please_complete_the_data
                        }
                        i4++
                        i = R.string.please_complete_the_data
                    }
                }
                Log.i(TAG, "space: $str")
                customKey!!.space = str
                if (customKey!!.type == 6) {
                    val split: Array<String> =
                        customKey!!.row_pos.split(";".toRegex())
                            .dropLastWhile { it.isEmpty() }.toTypedArray()
                    val split2: Array<String> =
                        str.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    var str5: String = ""
                    var i6: Int = 0
                    while (i6 < split2.size) {
                        if (i6 != split2.size - 1) {
                            val i7: Int = i6 + 1
                            if ((split2[i6] == split2[i7]) && (split[i6] == split[i7])) {
                                val length: Int = split2[i6].split(",".toRegex())
                                    .dropLastWhile { it.isEmpty() }.toTypedArray().size
                                var str6: String = ""
                                var str7: String = str6
                                var i8: Int = 0
                                while (i8 < length) {
                                    if (i8 == length - 1) {
                                        str2 = "$str6-200;"
                                        str3 = str7 + "0;"
                                    } else {
                                        str2 = "$str6-200,"
                                        str3 = str7 + "0,"
                                    }
                                    str7 = str3
                                    str6 = str2
                                    i8++
                                }
                                str5 = (str5 + str6) + str7
                                customKey!!.space_width = str5
                                i6 = i7
                            }
                        }
                        i6++
                    }
                }
                var parameter_info: String = customKey!!.parameter_info
                if (customKey!!.type == 0) {
                    if (!this.abSame) {
                        parameter_info = SpecificParamUtils.putParam(
                            parameter_info,
                            SpecificParamUtils.SIDE,
                            "3"
                        )
                    } else {
                        parameter_info =
                            SpecificParamUtils.putParam(parameter_info, SpecificParamUtils.SIDE, "")
                    }
                }
                if (customKey!!.type == 5 || customKey!!.type == 3) {
                    parameter_info = SpecificParamUtils.putParam(
                        parameter_info,
                        SpecificParamUtils.SIDE,
                        this.side!!
                    )
                }
                Log.i(TAG, "parameter_info: $parameter_info")
                customKey!!.parameter_info = parameter_info
                customKey!!.isAbSame = this.abSame
                if (customKey!!.type == 8) {
                    start(KeyDepthSetFragment.newInstance(this.customKey))
                    return
                }
                if (customKey!!.type == 6) {
                    val space_width: String = customKey!!.space_width
                    if (TextUtils.isEmpty(space_width) || !space_width.contains("-")) {
                        start(KeySpaceWidthSetFragment.newInstance(this.customKey))
                        return
                    } else {
                        start(KeyDepthSetFragment.newInstance(this.customKey))
                        return
                    }
                }
                start(KeySpaceWidthSetFragment.newInstance(this.customKey))
                return
            }

            R.id.iv_add -> {
                val editText4: EditText? = this.currentEdit
                if (editText4 != null) {
                    addSpace(editText4.parent as LinearLayout)
                    return
                }
                return
            }

            R.id.iv_reduce -> {
                val editText5: EditText? = this.currentEdit
                if (editText5 != null) {
                    reduceSpace(editText5.parent as LinearLayout)
                    return
                }
                return
            }

            R.id.tv_auto -> {
                val editText6: EditText? = this.currentEdit
                if (editText6 != null) {
                    autoSpace(editText6.parent as LinearLayout)
                    return
                }
                return
            }

            R.id.tv_row_add -> {
                val childCount: Int = llIndex!!.childCount
                if (childCount > 3) {
                    return
                }
                tvRows!!.text = (childCount + 1).toString()
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(70, 30)
                layoutParams.setMargins(0, 1, 0, 0)
                val editText7: EditText = getEditText(childCount, "")
                editText7.tag = childCount
                llIndex!!.addView(editText7, layoutParams)
                val linearLayout3 = LinearLayout(context)
                linearLayout3.orientation = 0
                var i9: Int = 0
                while (i9 < 5) {
                    val editText8: EditText = getEditText(i9, "")
                    if ((customKey!!.type == 6) && (childCount == 0) && (i9 == 0)) {
                        editText8.requestFocus()
                    }
                    val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
                    layoutParams2.setMargins(1, 1, 0, 0)
                    linearLayout3.addView(editText8, layoutParams2)
                    i9++
                }
                llSpace!!.addView(linearLayout3)
                val linearLayout4 = LinearLayout(context)
                val imageView: ImageView = getImageView(R.drawable.space_reduce)
                imageView.tag = "-"
                val layoutParams3: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
                layoutParams3.setMargins(10, 1, 0, 0)
                imageView.setOnClickListener(SpaceToolClickListener(linearLayout3))
                linearLayout4.addView(imageView, layoutParams3)
                val imageView2: ImageView = getImageView(R.drawable.space_add)
                imageView2.tag = "+"
                val layoutParams4: LinearLayout.LayoutParams = LinearLayout.LayoutParams(36, 30)
                layoutParams4.setMargins(10, 1, 0, 0)
                imageView2.setOnClickListener(SpaceToolClickListener(linearLayout3))
                linearLayout4.addView(imageView2, layoutParams4)
                val text3: TextView = getText(getString(R.string.auto), R.drawable.bg_auto)
                text3.tag = "auto"
                val layoutParams5: LinearLayout.LayoutParams = LinearLayout.LayoutParams(90, 30)
                layoutParams5.setMargins(16, 1, 0, 0)
                text3.setOnClickListener(SpaceToolClickListener(linearLayout3))
                linearLayout4.addView(text3, layoutParams5)
                llSpaceTool!!.addView(linearLayout4)
                return
            }

            R.id.tv_row_reduce -> {
                val childCount2: Int = llIndex!!.childCount
                if (childCount2 > 1) {
                    val i10: Int = childCount2 - 1
                    tvRows!!.text = i10.toString()
                    llIndex!!.removeViewAt(i10)
                    llSpace!!.removeViewAt(i10)
                    llSpaceTool!!.removeViewAt(i10)
                    return
                }
                return
            }

            else -> when (id) {
                R.id.bt_number_0, R.id.bt_number_1, R.id.bt_number_2, R.id.bt_number_3, R.id.bt_number_4, R.id.bt_number_5, R.id.bt_number_6, R.id.bt_number_7, R.id.bt_number_8, R.id.bt_number_9 -> {
                    inputNumb((view as Button).getText().toString().trim { it <= ' ' })
                    return
                }

                R.id.bt_number_last -> {
                    val linearLayout5: LinearLayout = currentEdit!!.parent as LinearLayout
                    val editText9: EditText? = linearLayout5.getChildAt(
                        linearLayout5.indexOfChild(
                            this.currentEdit
                        ) - 1
                    ) as EditText?
                    if (editText9 != null) {
                        editText9.requestFocus()
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
                    val linearLayout6: LinearLayout = currentEdit!!.parent as LinearLayout
                    val editText10: EditText? = linearLayout6.getChildAt(
                        linearLayout6.indexOfChild(
                            this.currentEdit
                        ) + 1
                    ) as EditText?
                    if (editText10 != null) {
                        editText10.requestFocus()
                        return
                    }
                    return
                }

                else -> return
            }
        }
    }

    private fun autoSpace(linearLayout: LinearLayout) {
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
                    if (customKey!!.align != 0 || space >= 0) {
                        if (customKey!!.align != 1 || space <= 0) {
                            for (i2 in 0 until childCount) {
                                (linearLayout.getChildAt(i2) as EditText).setText(
                                    (((i2 - arrayList[0].index) * space) + arrayList.get(0).space).toString()
                                )
                            }
                            return
                        }
                        return
                    }
                    return
                }
            }
        }
    }

    private fun addSpace(linearLayout: LinearLayout) {
        var childCount = 0
        if (linearLayout === this.llIndex || (linearLayout.childCount
                .also { childCount = it }) == 13
        ) {
            return
        }
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
        layoutParams.setMargins(1, 1, 0, 0)
        linearLayout.addView(getEditText(childCount, ""), layoutParams)
    }

    private fun reduceSpace(linearLayout: LinearLayout) {
        var childCount = 0
        if (linearLayout !== this.llIndex && (linearLayout.childCount
                .also { childCount = it }) >= 2
        ) {
            if (linearLayout.childCount - 1 == linearLayout.indexOfChild(this.currentEdit)) {
                linearLayout.getChildAt(0).requestFocus()
            }
            linearLayout.removeViewAt(childCount - 1)
        }
    }

    fun oncheckChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.id) {
            R.id.rb_imperial -> {
                if (z) {
                    if (customKey!!.type != 8) {
                        tvUnit!!.setText(R.string._1inch_1000)
                        mm2inch()
                    }
                    customKey!!.setInch(true)
                    return
                }
                return
            }

            R.id.rb_metric -> {
                if (z) {
                    if (customKey!!.type != 8) {
                        tvUnit!!.setText(R.string._1mm_100)
                        inch2mm()
                    }
                    customKey!!.setInch(false)
                    return
                }
                return
            }

            R.id.rb_sideA -> {
                if (z) {
                    val type: Int = customKey!!.type
                    if (type != 0 && type != 2) {
                        if (type == 3) {
                            this.side = "0"
                            (llIndex!!.getChildAt(0) as TextView).text = "A"
                            if (customKey!!.align == 0) {
                                ivSpace!!.setImageResource(R.drawable.single_external_down_shoulder_space)
                                return
                            } else {
                                ivSpace!!.setImageResource(R.drawable.single_external_down_tip_space)
                                return
                            }
                        }
                        if (type != 4) {
                            if (type != 5) {
                                return
                            }
                            this.side = "0"
                            (llIndex!!.getChildAt(0) as TextView).text = "A"
                            if (customKey!!.align == 0) {
                                ivSpace!!.setImageResource(R.drawable.single_internal_shoulder_a_space)
                                return
                            } else {
                                ivSpace!!.setImageResource(R.drawable.single_internal_tip_a_space)
                                return
                            }
                        }
                    }
                    this.abSame = false
                    val textView: TextView? = llIndex!!.getChildAt(0) as TextView?
                    if (textView != null) {
                        textView.text = "A"
                    }
                    llIndex!!.getChildAt(1).visibility = 0
                    llSpace!!.getChildAt(1).visibility = 0
                    llSpaceTool!!.getChildAt(1).visibility = 0
                    return
                }
                return
            }

            R.id.rb_sideB -> {
                if (z) {
                    val type2: Int = customKey!!.type
                    if (type2 != 0 && type2 != 2) {
                        if (type2 == 3) {
                            this.side = "1"
                            val textView2: TextView? = llIndex!!.getChildAt(0) as TextView?
                            if (textView2 != null) {
                                textView2.text = "B"
                            }
                            if (customKey!!.align == 0) {
                                ivSpace!!.setImageResource(R.drawable.single_external_up_shoulder_space)
                                return
                            } else {
                                ivSpace!!.setImageResource(R.drawable.single_external_up_tip_space)
                                return
                            }
                        }
                        if (type2 != 4) {
                            if (type2 != 5) {
                                return
                            }
                            this.side = "1"
                            val textView3: TextView? = llIndex!!.getChildAt(0) as TextView?
                            if (textView3 != null) {
                                textView3.text = "B"
                            }
                            if (customKey!!.align == 0) {
                                ivSpace!!.setImageResource(R.drawable.single_internal_shoulder_b_space)
                                return
                            } else {
                                ivSpace!!.setImageResource(R.drawable.single_internal_tip_b_space)
                                return
                            }
                        }
                    }
                    this.abSame = true
                    val textView4: TextView? = llIndex!!.getChildAt(0) as TextView?
                    if (textView4 != null) {
                        textView4.text = "AB"
                    }
                    llIndex!!.getChildAt(1).visibility = 8
                    llSpace!!.getChildAt(1).visibility = 8
                    llSpaceTool!!.getChildAt(1).visibility = 8
                    return
                }
                return
            }

            else -> return
        }
    }

    private fun inch2mm() {
        for (i in 0 until llSpace!!.childCount) {
            val linearLayout: LinearLayout = llSpace!!.getChildAt(i) as LinearLayout
            for (i2 in 0 until linearLayout.childCount) {
                val editText: EditText = linearLayout.getChildAt(i2) as EditText
                val r7: Editable = editText.getText()
                if (!TextUtils.isEmpty(r7)) {
                    try {
                        editText.setText(Math.round(r7.toString().toInt() * 2.54).toString())
                    } catch (unused: Exception) {
                    }
                }
            }
        }
        if (customKey!!.type == 6) {
            for (i3 in 0 until llIndex!!.childCount) {
                val editText2: EditText = llIndex!!.getChildAt(i3) as EditText
                val r2: String = editText2.getText().toString().trim({ it <= ' ' })
                if (!TextUtils.isEmpty(r2)) {
                    editText2.setText(Math.round(r2.toInt() * 2.54).toString())
                }
            }
        }
    }

    private fun mm2inch() {
        for (i in 0 until llSpace!!.childCount) {
            val linearLayout: LinearLayout = llSpace!!.getChildAt(i) as LinearLayout
            for (i2 in 0 until linearLayout.childCount) {
                val editText: EditText = linearLayout.getChildAt(i2) as EditText
                val r7: Editable = editText.getText()
                if (!TextUtils.isEmpty(r7)) {
                    editText.setText(Math.round(r7.toString().toInt() / 2.54).toString())
                }
            }
        }
        if (customKey!!.type == 6) {
            for (i3 in 0 until llIndex!!.childCount) {
                val editText2: EditText = llIndex!!.getChildAt(i3) as EditText
                val r2: String = editText2.getText().toString().trim({ it <= ' ' })
                if (!TextUtils.isEmpty(r2)) {
                    editText2.setText(Math.round(r2.toInt() / 2.54).toString())
                }
            }
        }
    }

    private fun delete() {
        val trim: String = currentEdit!!.getText().toString().trim({ it <= ' ' })
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
                this@KeySpaceSetFragment.currentEdit = view as EditText?
            }
        }
    }

    /* loaded from: classes.dex */
    private inner class SpaceToolClickListener(private val parent: LinearLayout) :
        View.OnClickListener {
        // android.view.View.OnClickListener
        override fun onClick(view: View) {
            val str: String = view.tag as String
            Log.i(TAG, "onClick: " + str)
            if (TextUtils.isEmpty(str)) {
                return
            }
            if ((str == "-")) {
                this@KeySpaceSetFragment.reduce(this.parent)
            } else if ((str == "+")) {
                this@KeySpaceSetFragment.add(this.parent)
            } else if ((str == "auto")) {
                this@KeySpaceSetFragment.auto(this.parent)
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

    /* JADX INFO: Access modifiers changed from: private */
    fun auto(linearLayout: LinearLayout) {
        val arrayList = ArrayList<BitIndex>()
        val childCount: Int = linearLayout.childCount
        for (i in 0 until childCount) {
            val trim: String =
                (linearLayout.getChildAt(i) as EditText).getText().toString().trim({ it <= ' ' })
            if (!TextUtils.isEmpty(trim)) {
                arrayList.add(BitIndex(i, trim.toInt()))
                if (arrayList.size == 2) {
                    val space: Int =
                        ((arrayList.get(0) as BitIndex).space - (arrayList.get(1) as BitIndex).space) / ((arrayList.get(
                            0
                        ) as BitIndex).index - (arrayList.get(1) as BitIndex).index)
                    if (customKey!!.align == 0 && space < 0) {
                        Log.i(TAG, "auto: 由小到大")
                        return
                    }
                    if (customKey!!.align == 1 && space > 0) {
                        Log.i(TAG, "auto: 由大到小")
                        return
                    }
                    for (i2 in 0 until childCount) {
                        (linearLayout.getChildAt(i2) as EditText).setText(
                            (((i2 - (arrayList.get(0) as BitIndex).index) * space) + (arrayList.get(
                                0
                            ) as BitIndex).space).toString()
                        )
                    }
                    return
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class BitIndex(val index: Int, val space: Int)
    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"
        fun newInstance(customKey: CustomKey?): KeySpaceSetFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            val keySpaceSetFragment: KeySpaceSetFragment = KeySpaceSetFragment()
            keySpaceSetFragment.setArguments(bundle)
            return keySpaceSetFragment
        }
    }
}
