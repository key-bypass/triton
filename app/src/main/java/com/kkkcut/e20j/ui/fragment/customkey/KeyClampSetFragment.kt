package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioButton
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.dao.UserDataDaoManager
import com.kkkcut.e20j.ui.dialog.EditDialog
import com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import org.greenrobot.eventbus.EventBus

/* loaded from: classes.dex */
class KeyClampSetFragment() : BaseBackFragment() {
    var btNext: Button? = null
    private var customKey: CustomKey? = null

    var rbClamp1: RadioButton? = null

    var rbClamp2: RadioButton? = null

    var rbClamp3: RadioButton? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_clamp_set
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val customKey: CustomKey? =
            getArguments()!!.getParcelable<Parcelable>(CUSTOMKEY) as CustomKey?
        this.customKey = customKey
        customKey!!.setClampNum("S1")
        rbClamp2!!.setChecked(true)
        val type: Int = this.customKey!!.getType()
        if (type == 0) {
            if (this.customKey!!.getAlign() == 0) {
                rbClamp1!!.setBackgroundResource(R.drawable.custom_clamp_s1_c_shoulder)
                rbClamp2!!.setBackgroundResource(R.drawable.custom_clamp_s1_d_shoulder)
            } else {
                rbClamp1!!.setBackgroundResource(R.drawable.custom_clamp_s1_c_tip)
                rbClamp2!!.setBackgroundResource(R.drawable.custom_clamp_s1_d_tip)
            }
            if ((this.customKey!!.getClampSide() == "C")) {
                rbClamp1!!.setChecked(true)
            }
            rbClamp3!!.setVisibility(8)
            return
        }
        if ((type == 2) || (type == 3) || (type == 4) || (type == 5) || (type == 6)) {
            if (this.customKey!!.getAlign() == 0) {
                rbClamp1!!.setBackgroundResource(R.drawable.custom_clamp_s1_a_shoulder)
                rbClamp2!!.setBackgroundResource(R.drawable.custom_clamp_s1_b_shoulder)
                rbClamp3!!.setBackgroundResource(R.drawable.custom_clamp_s1_b_side_shoulder)
            } else {
                rbClamp1!!.setBackgroundResource(R.drawable.custom_clamp_s1_a_tip)
                rbClamp2!!.setBackgroundResource(R.drawable.custom_clamp_s1_b_tip)
                rbClamp3!!.setBackgroundResource(R.drawable.custom_clamp_s1_b_side_tip)
            }
            if ((this.customKey!!.getClampSide() == "A")) {
                rbClamp1!!.setChecked(true)
            }
            if ((this.customKey!!.getClampSide() == "B") && (this.customKey!!.getClampSlot() == "1")) {
                rbClamp3!!.setChecked(true)
            }
        }
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.bt_last) {
            onBack()
            return
        }
        if (id != R.id.bt_next) {
            return
        }
        Log.i(
            TAG,
            "onViewClicked: " + customKey!!.getClampNum() + "-:" + customKey!!.getClampSide() + "-:" + customKey!!.getClampSlot()
        )
        showEditDialog(this.customKey)
    }

    fun onCheckedChange(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.getId()) {
            R.id.rb_clamp_1 -> {
                if (z) {
                    if (customKey!!.getType() == 0) {
                        customKey!!.setClampSide("C")
                    } else {
                        customKey!!.setClampSide("A")
                    }
                    customKey!!.setClampSlot("0")
                    return
                }
                return
            }

            R.id.rb_clamp_2 -> {
                if (z) {
                    if (customKey!!.getType() == 0) {
                        customKey!!.setClampSide("D")
                    } else {
                        customKey!!.setClampSide("B")
                    }
                    customKey!!.setClampSlot("0")
                    return
                }
                return
            }

            R.id.rb_clamp_3 -> {
                if (z) {
                    customKey!!.setClampSide("B")
                    customKey!!.setClampSlot("1")
                    return
                }
                return
            }

            else -> return
        }
    }

    private fun showEditDialog(customKey: CustomKey?) {
        val editDialog: EditDialog = EditDialog(getContext())
        editDialog.setTip(getString(R.string.please_input_the_key_name))
        val keyname: String = customKey!!.getKeyname()
        if (!TextUtils.isEmpty(keyname)) {
            editDialog.setEditTextContent(keyname)
        }
        editDialog.setDialogBtnCallback(object : DialogInputFinishCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyClampSetFragment.1
            // com.kkkcut.e20j.ui.dialog.EditDialog.DialogInputFinishCallBack
            override fun onDialogButClick(str: String) {
                customKey.setKeyname(str)
                UserDataDaoManager.getInstance(this@KeyClampSetFragment.getContext())
                    .saveCustomKey(customKey)
                this@KeyClampSetFragment.start(CustomKeyListFragment.Companion.newInstance(), 2)
                EventBus.getDefault().post(EventCenter<Any?>(11))
            }
        })
        editDialog.show()
    }

    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"

        fun newInstance(customKey: CustomKey?): KeyClampSetFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            val keyClampSetFragment: KeyClampSetFragment = KeyClampSetFragment()
            keyClampSetFragment.setArguments(bundle)
            return keyClampSetFragment
        }
    }
}
