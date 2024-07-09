package com.kkkcut.e20j.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.bean.eventbus.InputFinishBean
import com.kkkcut.e20j.ui.dialog.WarningDialog
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.us.databinding.FragmentIgnitionDoorSearchBinding
import com.kkkcut.e20j.utils.ThemeUtils
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.Method

/* loaded from: classes.dex */
class IgnitionDoorSearchFragment() : BaseBackFragment() {
    var STATUS: Int = 0

    var binding: FragmentIgnitionDoorSearchBinding? = null
    private val myOnfocusChanged: MyOnfocusChanged = this.MyOnfocusChanged()

    private var currentEdit: EditText? = null

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        super.onCreateView(layoutInflater, viewGroup, bundle)
        binding = FragmentIgnitionDoorSearchBinding.inflate(layoutInflater, viewGroup, false)
        return binding!!.getRoot()
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_ignition_door_search
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.ignition_lock_door_lock_check)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        initView()
        if (MachineInfo.isChineseMachine()) {
            return
        }
        showRemind()
    }

    private fun showRemind() {
        val warningDialog: WarningDialog = WarningDialog(getContext())
        warningDialog.show()
        warningDialog.setRemind("The software will calculate the bittings of Side B & C automatically, but show virtual bittings, please ignore the bittings but continue to cut side by side")
    }

    private fun initView() {
        val i: Int = getArguments()!!.getInt(LENGTH)
        binding!!.llCode.removeAllViews()
        binding!!.llIndex.removeAllViews()
        binding!!.llTitle.removeAllViews()
        var i2: Int = 0
        while (i2 < i) {
            val editText: EditText = getEditText(i2, "")
            if (i2 == 0) {
                editText.requestFocus()
            }
            val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
            layoutParams.setMargins(1, 1, 0, 0)
            binding!!.llCode.addView(editText, layoutParams)
            i2++
            val textView: TextView = getTextView(i2.toString())
            val layoutParams2: LinearLayout.LayoutParams = LinearLayout.LayoutParams(60, 30)
            layoutParams2.setMargins(1, 1, 0, 0)
            binding!!.llIndex.addView(textView, layoutParams2)
        }
        if (i == 8) {
            binding!!.rbIgnitionToDoor.setVisibility(8)
        }
        if (this.STATUS == 0) {
            if (i != 8) {
                val textView2: TextView = getTextView(getString(R.string.side_face))
                val layoutParams3: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(if (i == 9) 182 else 243, 30)
                layoutParams3.setMargins(1, 0, 0, 0)
                binding!!.llTitle.addView(textView2, layoutParams3)
            }
            val textView3: TextView = getTextView(getString(R.string.front))
            val layoutParams4: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(if (i == 8) 487 else 365, 30)
            layoutParams4.setMargins(1, 0, 0, 0)
            binding!!.llTitle.addView(textView3, layoutParams4)
            return
        }
        val textView4: TextView = getTextView(getString(R.string.front))
        val layoutParams5: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(if (i == 9) 548 else 609, 30)
        layoutParams5.setMargins(1, 0, 0, 0)
        binding!!.llTitle.addView(textView4, layoutParams5)
    }

    private fun getTextView(str: String): TextView {
        val textView: TextView = TextView(getContext())
        textView.setGravity(17)
        textView.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        textView.setTextColor(-1)
        textView.setText(str)
        textView.setTextSize(18.0f)
        return textView
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.bt_delete) {
            delete()
            return
        }
        if (id == R.id.btn_cancel) {
            onBack()
            return
        }
        if (id != R.id.btn_ok) {
            when (id) {
                R.id.bt_number_1, R.id.bt_number_2, R.id.bt_number_3, R.id.bt_number_4, R.id.bt_number_5 -> {
                    inputNumb((view as Button).getText().toString().trim({ it <= ' ' }))
                    return
                }

                else -> when (id) {
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
        }
        val childCount: Int = binding!!.llCode.getChildCount()
        var str: String = ""
        for (i in 0 until childCount) {
            val editText: EditText = binding!!.llCode.getChildAt(i) as EditText
            val trim: String = editText.getText().toString().trim({ it <= ' ' })
            if (TextUtils.isEmpty(trim)) {
                editText.requestFocus()
                ToastUtil.showToast(R.string.please_complete_the_data)
                return
            }
            str = if (i == childCount - 1) str + trim + ";" else str + trim + ","
        }
        EventBus.getDefault()
            .post(EventCenter<Any?>(2, InputFinishBean(true, str, this.STATUS == 0)))
        start(KeyOperateFragment.Companion.newInstance(), 2)
    }

    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        val id: Int = compoundButton.getId()
        if (id == R.id.rb_door_to_ignition) {
            if (z) {
                this.STATUS = 0
                initView()
                return
            }
            return
        }
        if (id == R.id.rb_ignition_to_door && z) {
            this.STATUS = 1
            initView()
        }
    }

    private fun delete() {
        currentEdit!!.setText("")
        changeLast()
    }

    private fun getEditText(i: Int, str: String): EditText {
        val editText: EditText = EditText(getContext())
        editText.setGravity(17)
        editText.setPadding(0, 0, 0, 0)
        editText.setBackgroundResource(ThemeUtils.getResId(getContext(), R.attr.bg_customkey_input))
        editText.setTextColor(getContext()!!.getResources().getColor(R.color.color_ff205f))
        editText.setInputType(2)
        editText.setCursorVisible(false)
        editText.setTextSize(18.0f)
        editText.setFilters(arrayOf<InputFilter>(object : LengthFilter(5) {
            // from class: com.kkkcut.e20j.ui.fragment.IgnitionDoorSearchFragment.1
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
            editText.setHint("")
        } else {
            editText.setText(str)
        }
        return editText
    }

    /* JADX INFO: Access modifiers changed from: private */ /* loaded from: classes.dex */
    inner class MyOnfocusChanged constructor() : OnFocusChangeListener {
        // android.view.View.OnFocusChangeListener
        override fun onFocusChange(view: View, z: Boolean) {
            if (z) {
                if (this@IgnitionDoorSearchFragment.currentEdit != null) {
                    currentEdit!!.setTextColor(
                        getContext()!!.getResources().getColor(R.color.color_ff205f)
                    )
                }
                this@IgnitionDoorSearchFragment.currentEdit = view as EditText?
                currentEdit!!.setTextColor(-1)
            }
        }
    }

    private fun inputNumb(str: String) {
        currentEdit!!.setText(str)
        changeNext()
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
        val editText: EditText?
        val linearLayout: LinearLayout = currentEdit!!.getParent() as LinearLayout
        val childCount: Int = linearLayout.getChildCount()
        val indexOfChild: Int = linearLayout.indexOfChild(this.currentEdit)
        if (indexOfChild == childCount - 1) {
            editText = linearLayout.getChildAt(0) as EditText?
        } else {
            editText = linearLayout.getChildAt(indexOfChild + 1) as EditText?
        }
        if (editText != null) {
            editText.requestFocus()
        }
    }

    companion object {
        val DOOR_TO_IGNITION: Int = 0
        val IGNITION_TO_DOOR: Int = 1
        val LENGTH: String = "length"
        fun newInstance(i: Int): IgnitionDoorSearchFragment {
            val bundle: Bundle = Bundle()
            bundle.putInt(LENGTH, i)
            val ignitionDoorSearchFragment: IgnitionDoorSearchFragment =
                IgnitionDoorSearchFragment()
            ignitionDoorSearchFragment.setArguments(bundle)
            return ignitionDoorSearchFragment
        }
    }
}
