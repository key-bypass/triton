package com.kkkcut.e20j.ui.fragment.customkey

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.kkkcut.e20j.DbBean.userDB.CustomKey
import com.kkkcut.e20j.androidquick.autolayout.widget.AutoRadioGroup
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.ui.fragment.KeyOperateFragment.Companion.newInstance
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.ThemeUtils

/* loaded from: classes.dex */
class KeyAlignSelectFragment() : BaseBackFragment() {
    var customKey: CustomKey? = null

    var rbShoulder: RadioButton? = null

    var rbTip: RadioButton? = null

    var rg1: AutoRadioGroup? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_key_align_select
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.please_choose_key_align_method)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        val customKey: CustomKey? =
            getArguments()!!.getParcelable<Parcelable>(CUSTOMKEY) as CustomKey?
        this.customKey = customKey
        when (customKey!!.getType()) {
            0 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doublekey_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doublekey_tip_custom
                    )
                )
            }

            1 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singlekey_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singlekey_tip_custom
                    )
                )
            }

            2 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doubleinside_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doubleinside_tip_custom
                    )
                )
            }

            3 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singleoutside_down_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singleoutside_down_tip_custom
                    )
                )
            }

            4 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doubleoutside_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.doubleoutside_tip_custom
                    )
                )
            }

            5 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singleinside_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.singleinside_tip_custom
                    )
                )
            }

            6 -> {
                rbShoulder!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.dimple_shoulder_custom
                    )
                )
                rbTip!!.setBackgroundResource(
                    ThemeUtils.getResId(
                        getContext(),
                        R.attr.dimple_tip_custom
                    )
                )
            }
        }
        if (this.customKey!!.getAlign() == 1) {
            rbTip!!.setChecked(true)
        } else {
            rbShoulder!!.setChecked(true)
        }
        rg1!!.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            // from class: com.kkkcut.e20j.ui.fragment.customkey.KeyAlignSelectFragment.1
            // android.widget.RadioGroup.OnCheckedChangeListener
            override fun onCheckedChanged(radioGroup: RadioGroup, i: Int) {
                if (i == R.id.rb_shoulder) {
                    this@KeyAlignSelectFragment.customKey!!.setAlign(0)
                } else {
                    if (i != R.id.rb_tip) {
                        return
                    }
                    this@KeyAlignSelectFragment.customKey!!.setAlign(1)
                }
            }
        })
    }

    fun onViewClicked(view: View) {
        val id: Int = view.getId()
        if (id == R.id.bt_last) {
            onBack()
        } else {
            if (id != R.id.bt_next) {
                return
            }
            start(KeySpaceSetFragment.Companion.newInstance(this.customKey))
        }
    }

    companion object {
        private val CUSTOMKEY: String = "CUSTOMKEY"
        fun newInstance(customKey: CustomKey?): KeyAlignSelectFragment {
            val bundle: Bundle = Bundle()
            bundle.putParcelable(CUSTOMKEY, customKey)
            val keyAlignSelectFragment: KeyAlignSelectFragment = KeyAlignSelectFragment()
            keyAlignSelectFragment.setArguments(bundle)
            return keyAlignSelectFragment
        }
    }
}
