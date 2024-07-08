package com.kkkcut.e20j.ui.fragment.duplicatekey.dimple

import android.app.Activity
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.ui.dialog.base.BottomInDialog

/* loaded from: classes.dex */
class DimpleDuplicateDecodeDialog(activity: Activity?) : BottomInDialog(activity) {
    var btCancle: TextView? = null

    var btDecode: TextView? = null
    var isRound: Boolean = false

    var ivClamp: ImageView? = null

    var ivDecoder: ImageView? = null
    private val keyInfo: KeyInfo? = null
    var roundBtVisible: Boolean = false

    var sbRound: CheckBox? = null
    private val timeValue: Int = 0

    var tvDecoderSize: TextView? = null

    var tvRound: TextView? = null

    var tvTimeValue: TextView? = null

    override fun getContentLayoutID(): Int {
        return 0
    }

    override fun initView() {
    }

    companion object {
        val PARAM: String = "param"
        private val TAG: String = "DecodeDialog"
    }
}
