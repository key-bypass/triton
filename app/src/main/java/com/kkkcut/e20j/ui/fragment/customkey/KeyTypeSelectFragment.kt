package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.cutting.machine.MachineInfo
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class KeyTypeSelectFragment() : BaseBackFragment() {
    private var customKey: CustomKey? = null

    var rbDimpleKey: RadioButton? = null

    var rbDoubleInsideKey: RadioButton? = null

    var rbDoubleKey: RadioButton? = null

    var rbDoubleOutsideKey: RadioButton? = null

    var rbSingleInsideKey: RadioButton? = null

    var rbSingleKey: RadioButton? = null

    var rbSingleOutsideKey: RadioButton? = null

    var rbTubularKey: RadioButton? = null

    var rg1: RadioGroup? = null

    var rg2: RadioGroup? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_type_select
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        rg1!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment.1
            // android.widget.RadioGroup.OnCheckedChangeListener
            override fun onCheckedChanged(radioGroup: RadioGroup, i: Int) {
                when (i) {
                    R.id.rb_dimple_key -> {
                        if (rbDimpleKey!!.isChecked()) {
                            rg2!!.clearCheck()
                            customKey!!.setType(6)
                            return
                        }
                        return
                    }

                    R.id.rb_double_inside_key -> {
                        if (rbDoubleInsideKey!!.isChecked()) {
                            rg2!!.clearCheck()
                            customKey!!.setType(2)
                            return
                        }
                        return
                    }

                    R.id.rb_double_key -> {
                        if (rbDoubleKey!!.isChecked()) {
                            rg2!!.clearCheck()
                            customKey!!.setType(0)
                            return
                        }
                        return
                    }

                    R.id.rb_inside_key -> {
                        if (rbSingleInsideKey!!.isChecked()) {
                            rg2!!.clearCheck()
                            customKey!!.setType(5)
                            return
                        }
                        return
                    }

                    else -> return
                }
            }
        })
        rg2!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyTypeSelectFragment.2
            // android.widget.RadioGroup.OnCheckedChangeListener
            override fun onCheckedChanged(radioGroup: RadioGroup, i: Int) {
                when (i) {
                    R.id.rb_double_outside_key -> {
                        if (rbDoubleOutsideKey!!.isChecked()) {
                            rg1!!.clearCheck()
                            customKey!!.setType(4)
                            return
                        }
                        return
                    }

                    R.id.rb_single_key -> {
                        if (rbSingleKey!!.isChecked()) {
                            rg1!!.clearCheck()
                            customKey!!.setType(1)
                            return
                        }
                        return
                    }

                    R.id.rb_single_outside_key -> {
                        if (rbSingleOutsideKey!!.isChecked()) {
                            rg1!!.clearCheck()
                            customKey!!.setType(3)
                            return
                        }
                        return
                    }

                    R.id.rb_tubular_key -> {
                        if (rbTubularKey!!.isChecked()) {
                            rg1!!.clearCheck()
                            customKey!!.setType(8)
                            return
                        }
                        return
                    }

                    else -> return
                }
            }
        })
        val customKey: CustomKey? =
            getArguments()!!.getParcelable<Parcelable>(CUSTOMKEY) as CustomKey?
        this.customKey = customKey
        if (customKey != null) {
            when (customKey.getType()) {
                0 -> rbDoubleKey!!.setChecked(true)
                1 -> rbSingleKey!!.setChecked(true)
                2 -> rbDoubleInsideKey!!.setChecked(true)
                3 -> rbSingleOutsideKey!!.setChecked(true)
                4 -> rbDoubleOutsideKey!!.setChecked(true)
                5 -> rbSingleInsideKey!!.setChecked(true)
                6 -> rbDimpleKey!!.setChecked(true)
                8 -> rbTubularKey!!.setChecked(true)
            }
        }
        if (MachineInfo.isChineseMachine()) {
            rbDimpleKey!!.setVisibility(8)
            rbSingleKey!!.setVisibility(8)
            rbTubularKey!!.setVisibility(8)
        }
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.select_key_type)
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.bt_last) {
            onBack()
        } else {
            if (id != R.id.bt_next) {
                return
            }
            if (customKey!!.getType() == 8) {
                start(KeySpaceSetFragment.Companion.newInstance(this.customKey))
            } else {
                start(KeyAlignSelectFragment.Companion.newInstance(this.customKey))
            }
        }
    }

    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"
        fun newInstance(customKey: CustomKey?): KeyTypeSelectFragment {
            val keyTypeSelectFragment: KeyTypeSelectFragment = KeyTypeSelectFragment()
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            keyTypeSelectFragment.setArguments(bundle)
            return keyTypeSelectFragment
        }
    }
}
