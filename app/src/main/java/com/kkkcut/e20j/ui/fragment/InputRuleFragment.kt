package com.kkkcut.e20j.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentInputRuleBinding
import com.kkkcut.e20j.utils.ThemeUtils
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.Method

/* loaded from: classes.dex */
class InputRuleFragment() : BaseBackFragment() {
    private val textColorDefault: Int = -1
    private val myOnfocusChanged = this.MyOnfocusChanged()

    var binding: FragmentInputRuleBinding? = null

    private var currentEdit: EditText? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        this.binding = FragmentInputRuleBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_input_rule
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
    }

    private fun initView() {
        val i: Int = arguments!!.getInt("isrule", 0)
        ("3" == arguments!!.getString("ReadBittingRule"))
        val replace: String = arguments!!.getString("toothcode")!!
            .replace(",", "").replace(";", "")
        if (i == 1) {
            val str: String =
                replace.get(0).toString() + replace.get(2) + replace.get(5) + replace.get(7)
            val str2: String =
                getStringAt(replace, 6, 1) + getStringAt(replace, 6, 3) + getStringAt(
                    replace,
                    6,
                    4
                ) + getStringAt(replace, 6, 6)
            for (i2 in 0..3) {
                val editText: EditText = getEditText(str.get(i2).toString())
                val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams.setMargins(10, 0, 0, 0)
                binding!!.llA.addView(editText, layoutParams)
            }
            for (i3 in 0..3) {
                val editText2: EditText = getEditText(str2.get(i3).toString())
                val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams2.setMargins(10, 0, 0, 0)
                binding!!.llB.addView(editText2, layoutParams2)
            }
        } else if (i == 3) {
            val str3: String = replace.get(4).toString() + replace.get(5) + replace.get(7)
            val str4: String =
                getStringAt(replace, 6, 3) + getStringAt(replace, 6, 6) + getStringAt(replace, 6, 8)
            for (i4 in 0..2) {
                val editText3: EditText = getEditText(str3.get(i4).toString())
                val layoutParams3: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams3.setMargins(10, 0, 0, 0)
                binding!!.llA.addView(editText3, layoutParams3)
            }
            for (i5 in 0..2) {
                val editText4: EditText = getEditText(str4.get(i5).toString())
                val layoutParams4: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams4.setMargins(10, 0, 0, 0)
                binding!!.llB.addView(editText4, layoutParams4)
            }
        } else if (i == 4) {
            val str5: String = getStringAt(replace, 5, 1) + getStringAt(replace, 5, 2)
            val valueOf: String = replace.get(0).toString()
            for (i6 in 0..1) {
                val editText5: EditText = getEditText(str5.get(i6).toString())
                val layoutParams5: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams5.setMargins(10, 0, 0, 0)
                binding!!.llA.addView(editText5, layoutParams5)
            }
            for (i7 in 0..0) {
                val editText6: EditText = getEditText(valueOf.get(i7).toString())
                val layoutParams6: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams6.setMargins(10, 0, 0, 0)
                binding!!.llB.addView(editText6, layoutParams6)
            }
        } else if (i == 5) {
            val str6: String =
                getStringAt(replace, 6, 4) + getStringAt(replace, 6, 6) + getStringAt(replace, 6, 9)
            val str7: String =
                replace.get(5).toString() + replace.get(7).toString() + replace.get(8)
            for (i8 in 0..2) {
                val editText7: EditText = getEditText(str6.get(i8).toString())
                val layoutParams7: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams7.setMargins(10, 0, 0, 0)
                binding!!.llA.addView(editText7, layoutParams7)
            }
            for (i9 in 0..2) {
                val editText8: EditText = getEditText(str7.get(i9).toString())
                val layoutParams8: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams8.setMargins(10, 0, 0, 0)
                binding!!.llB.addView(editText8, layoutParams8)
            }
        } else if (i == 6) {
            val str8: String = replace.get(2).toString() + replace.get(3).toString()
            val str9: String = getStringAt(replace, 5, 0) + getStringAt(replace, 5, 1)
            for (i10 in 0..1) {
                val editText9: EditText = getEditText(str8.get(i10).toString())
                val layoutParams9: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams9.setMargins(10, 0, 0, 0)
                binding!!.llA.addView(editText9, layoutParams9)
            }
            for (i11 in 0..1) {
                val editText10: EditText = getEditText(str9.get(i11).toString())
                val layoutParams10: LinearLayout.LayoutParams = LinearLayout.LayoutParams(50, 50)
                layoutParams10.setMargins(10, 0, 0, 0)
                binding!!.llB.addView(editText10, layoutParams10)
            }
        }
        binding!!.llA.setTag("A")
        binding!!.llB.setTag("B")
        binding!!.llA.getChildAt(0).requestFocus()
    }

    private fun getEditText(str: String): EditText {
        val editText: EditText = EditText(getContext())
        editText.setGravity(17)
        editText.setPadding(0, 0, 0, 0)
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        editText.setTextColor(-1)
        editText.setInputType(2)
        editText.setCursorVisible(false)
        editText.setTextSize(18.0f)
        editText.setFilters(arrayOf<InputFilter>(object : LengthFilter(1) {
            // from class: com.kkkcut.e20j.ui.fragment.InputRuleFragment.1
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
        editText.setText(str.toString())
        return editText
    }

    private fun getStringAt(str: String, i: Int, i2: Int): String {
        return if (str.get(i2) == '?') "?" else (i - str.get(i2).toString().toInt()).toString()
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class MyOnfocusChanged constructor() : OnFocusChangeListener {
        // android.view.View.OnFocusChangeListener
        override fun onFocusChange(view: View, z: Boolean) {
            if (z) {
                this@InputRuleFragment.currentEdit = view as EditText?
                Log.i(TAG, "onFocusChange: " + this@InputRuleFragment.currentEdit)
            }
        }
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.btn_cancel) {
            onBack()
            return
        }
        if (id != R.id.btn_ok) {
            if (id != R.id.tvX) {
                when (id) {
                    R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5 -> {}
                    else -> return
                }
            }
            inputNumb((view as Button).getText().toString())
            return
        }
        var str: String? = ""
        for (i in 0 until binding!!.llA.getChildCount()) {
            val trim: String =
                (binding!!.llA.getChildAt(i) as EditText).getText().toString().trim({ it <= ' ' })
            if (TextUtils.isEmpty(trim) || (trim == "?")) {
                ToastUtil.showToast(R.string.please_complete_the_data)
                return
            }
        }
        for (i2 in 0 until binding!!.llB.getChildCount()) {
            val trim2: String =
                (binding!!.llB.getChildAt(i2) as EditText).getText().toString().trim({ it <= ' ' })
            if (TextUtils.isEmpty(trim2) || (trim2 == "?")) {
                ToastUtil.showToast(R.string.please_complete_the_data)
                return
            }
        }
        val i3: Int = getArguments()!!.getInt("isrule", 0)
        if (i3 == 1) {
            str = ((("" + getToothFromContainer(
                binding!!.llA, 0
            ) + "," + (6 - getToothFromContainer(
                binding!!.llB, 0
            ).toInt())) + "," + getToothFromContainer(
                binding!!.llA,
                1
            ) + "," + (6 - getToothFromContainer(
                binding!!.llB, 1
            ).toInt())) + "," + (6 - getToothFromContainer(
                binding!!.llB, 2
            ).toInt()) + "," + getToothFromContainer(
                binding!!.llA,
                2
            )) + "," + (6 - getToothFromContainer(
                binding!!.llB, 3
            ).toInt()) + "," + getToothFromContainer(binding!!.llA, 3) + ";"
        } else if (i3 == 3) {
            str = (("2,2,2," + (6 - getToothFromContainer(
                binding!!.llB, 0
            ).toInt()) + "," + getToothFromContainer(
                binding!!.llA,
                0
            )) + "," + getToothFromContainer(
                binding!!.llA, 1
            ) + "," + (6 - getToothFromContainer(
                binding!!.llB, 1
            ).toInt())) + "," + getToothFromContainer(
                binding!!.llA,
                2
            ) + "," + (6 - getToothFromContainer(
                binding!!.llB, 2
            ).toInt()) + ";"
        } else if (i3 == 4) {
            str = ("" + getToothFromContainer(binding!!.llB, 0) + "," + (5 - getToothFromContainer(
                binding!!.llA, 0
            ).toInt())) + "," + (5 - getToothFromContainer(
                binding!!.llA, 1
            ).toInt()) + ",1,1,1,1,1,1;"
        } else if (i3 == 5) {
            str = (("2,2,2,2," + (6 - getToothFromContainer(
                binding!!.llA, 0
            ).toInt()) + "," + getToothFromContainer(
                binding!!.llB,
                0
            )) + "," + (6 - getToothFromContainer(
                binding!!.llA, 1
            ).toInt()) + "," + getToothFromContainer(
                binding!!.llB,
                1
            )) + "," + getToothFromContainer(
                binding!!.llB, 2
            ) + "," + (6 - getToothFromContainer(
                binding!!.llA, 2
            ).toInt()) + ";"
        } else if (i3 == 6) {
            str = ("" + (5 - getToothFromContainer(
                binding!!.llB,
                0
            ).toInt()) + "," + (5 - getToothFromContainer(
                binding!!.llB, 1
            ).toInt())) + "," + getToothFromContainer(
                binding!!.llA,
                0
            ) + "," + getToothFromContainer(
                binding!!.llA, 1
            ) + ",1,1,1,1,1,1;"
        }
        EventBus.getDefault().post(EventCenter<Any?>(15, str))
        onBack()
    }

    private fun getToothFromContainer(linearLayout: LinearLayout, i: Int): String {
        return (linearLayout.getChildAt(i) as EditText).getText().toString().trim({ it <= ' ' })
    }

    private fun inputNumb(str: String) {
        val editText: EditText? = this.currentEdit
        if (editText != null) {
            editText.setText(str)
            val linearLayout: LinearLayout = currentEdit!!.getParent() as LinearLayout
            val indexOfChild: Int = linearLayout.indexOfChild(this.currentEdit)
            if (indexOfChild < linearLayout.getChildCount() - 1) {
                this.currentEdit = linearLayout.getChildAt(indexOfChild + 1) as EditText?
            } else {
                val str2: String = linearLayout.getTag() as String
                if (!TextUtils.isEmpty(str2)) {
                    if ((str2 == "A")) {
                        this.currentEdit = binding!!.llB.getChildAt(0) as EditText?
                    }
                    if ((str2 == "B")) {
                        this.currentEdit = binding!!.llA.getChildAt(0) as EditText?
                    }
                }
            }
            currentEdit!!.requestFocus()
        }
    }

    companion object {
        fun newInstance(i: Int, str: String?, str2: String?): InputRuleFragment {
            val bundle: Bundle = Bundle()
            val inputRuleFragment: InputRuleFragment = InputRuleFragment()
            bundle.putInt("isrule", i)
            bundle.putString("ReadBittingRule", str)
            bundle.putString("toothcode", str2)
            inputRuleFragment.setArguments(bundle)
            return inputRuleFragment
        }
    }
}
