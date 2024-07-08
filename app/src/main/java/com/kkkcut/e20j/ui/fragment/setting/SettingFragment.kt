package com.kkkcut.e20j.ui.fragment.setting

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.androidquick.ui.eventbus.EventCenter
import com.kkkcut.e20j.driver.pl2303.UsbSerialManager
import com.kkkcut.e20j.ui.dialog.RemindDialog
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R
import com.kkkcut.e20j.utils.CutCountHelper
import com.kkkcut.e20j.utils.CutCountHelper.ReadCutCountListener

/* loaded from: classes.dex */
class SettingFragment() : BaseBackFragment() {
    private var currentFragment: Fragment? = null

    var rbCutSetting: RadioButton? = null

    var rb_space_Width: RadioButton? = null

    var tvCutCount: TextView? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_setting
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return getString(R.string.setting)
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        this.currentFragment = SpeedSetFragment.newInstance()
        getChildFragmentManager().beginTransaction()
            .add(R.id.container, this.currentFragment!!, SpeedSetFragment.TAG).commit()
        if (MyApplication.getInstance().isShowRealDepth) {
            rb_space_Width!!.visibility = 0
            rbCutSetting!!.visibility = 0
        }
        CutCountHelper.getInstance().getCutCount(object : ReadCutCountListener {
            // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment.1
            // com.kkkcut.e20j.utils.CutCountHelper.ReadCutCountListener
            override fun onReadFinish(str: String) {
                tvCutCount!!.setText(str)
            }
        })
    }


    fun onCheckedChanged(compoundButton: CompoundButton, z: Boolean) {
        when (compoundButton.getId()) {
            R.id.rb_cut_setting -> {
                if (z) {
                    switchPage(CutSettingFragment.Companion.TAG, CutSettingFragment::class.java)
                    return
                }
                return
            }

            R.id.rb_other -> {
                if (z) {
                    switchPage(OtherSettingFragment.Companion.TAG, OtherSettingFragment::class.java)
                    return
                }
                return
            }

            R.id.rb_space_Width -> {
                if (z) {
                    switchPage(SpaceWidthSetting.Companion.TAG, SpaceWidthSetting::class.java)
                    return
                }
                return
            }

            R.id.rb_speed -> {
                if (z) {
                    switchPage(SpeedSetFragment.Companion.TAG, SpeedSetFragment::class.java)
                    return
                }
                return
            }

            else -> return
        }
    }

    private fun switchPage(str: String, cls: Class<*>) {
        val findFragmentByTag: Fragment? = getChildFragmentManager().findFragmentByTag(str)
        if (findFragmentByTag == null) {
            var fragment: Fragment? = null
            try {
                try {
                    fragment = cls.newInstance() as Fragment?
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                }
                getChildFragmentManager().beginTransaction().add(R.id.container, (fragment)!!, str)
                    .hide(
                        (currentFragment)!!
                    ).commit()
                this.currentFragment = fragment
                return
            } catch (e2: Exception) {
                e2.printStackTrace()
                return
            }
        }
        getChildFragmentManager().beginTransaction().hide((currentFragment)!!)
            .show(findFragmentByTag).commit()
        this.currentFragment = findFragmentByTag
    }

    // com.kkkcut.e20j.base.BaseFragment, com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun onEventComing(eventCenter: EventCenter<*>) {
        if (eventCenter.getEventCode() == 21) {
            rb_space_Width!!.setVisibility(0)
            rbCutSetting!!.setVisibility(0)
        }
    }

    fun onClick() {
        val remindDialog: RemindDialog = RemindDialog(getContext())
        remindDialog.setRemindMsg(getString(R.string.do_you_want_to_exit_the_program))
        remindDialog.setDialogBtnCallback(object : RemindDialog.DialogBtnCallBack {
            // from class: com.kkkcut.e20j.ui.fragment.setting.SettingFragment.2
            // com.kkkcut.e20j.ui.dialog.RemindDialog.DialogBtnCallBack
            override fun onDialogButClick(z: Boolean) {
                if (z) {
                    UsbSerialManager.getInstance().stop()
                    getActivity()!!.finish()
                    System.exit(0)
                }
            }
        })
        remindDialog.show()
    }

    companion object {
        fun newInstance(): SettingFragment {
            val bundle: Bundle = Bundle()
            val settingFragment: SettingFragment = SettingFragment()
            settingFragment.setArguments(bundle)
            return settingFragment
        }
    }
}
