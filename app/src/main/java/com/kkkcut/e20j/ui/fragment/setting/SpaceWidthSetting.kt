package com.kkkcut.e20j.ui.fragment.setting

import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import com.kkkcut.e20j.androidquick.tool.SPUtils
import com.kkkcut.e20j.androidquick.tool.ToastUtil
import com.kkkcut.e20j.ui.fragment.BaseBackFragment
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
class SpaceWidthSetting() : BaseBackFragment() {
    private val SPACE_WIDTH: String = "SPACE_WIDTH"

    var etDoubleInsideKey: EditText? = null

    var etDoubleKey: EditText? = null

    var etDoubleOutsideKey: EditText? = null

    var etSingleInsideKey: EditText? = null

    var etSingleKey: EditText? = null

    var etSingleOutsideKey: EditText? = null

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun getContentViewLayoutID(): Int {
        return R.layout.fragment_space_width_setting
    }

    // com.kkkcut.e20j.ui.fragment.BaseBackFragment
    override fun setTitleStr(): String? {
        return null
    }

    // com.kkkcut.e20j.androidquick.ui.base.QuickFragment
    override fun initViewsAndEvents() {
        etSingleKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 1, 0).toString())
        etDoubleKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 0, 30).toString())
        etSingleInsideKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 5, 0).toString())
        etSingleOutsideKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 3, 0).toString())
        etDoubleInsideKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 2, 0).toString())
        etDoubleOutsideKey!!.setText(SPUtils.getInt(this.SPACE_WIDTH + 4, 0).toString())
    }

    fun onViewClicked() {
        save(this.etSingleKey, 1)
        save(this.etDoubleKey, 0)
        save(this.etSingleInsideKey, 5)
        save(this.etSingleOutsideKey, 3)
        save(this.etDoubleInsideKey, 2)
        save(this.etDoubleOutsideKey, 4)
        ToastUtil.showToast("保存成功")
    }

    private fun save(editText: EditText?, i: Int) {
        val obj: String = editText!!.getText().toString()
        if (TextUtils.isEmpty(obj)) {
            return
        }
        SPUtils.put(this.SPACE_WIDTH + i, obj.toInt())
    }

    companion object {
        val TAG: String = "SpaceWidthSetting"
        fun newInstance(): SpaceWidthSetting {
            val bundle: Bundle = Bundle()
            val spaceWidthSetting: SpaceWidthSetting = SpaceWidthSetting()
            spaceWidthSetting.setArguments(bundle)
            return spaceWidthSetting
        }
    }
}
