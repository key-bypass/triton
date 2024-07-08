package com.kkkcut.e20j.ui.fragment.blankcut

import com.kkkcut.e20j.SpKeys
import com.kkkcut.e20j.androidquick.tool.SPUtils

/* loaded from: classes.dex */
object BlankCutSpeedUtils {
    fun saveSpeed(blankCutType: BlankCutType, i: Int) {
        SPUtils.put(SpKeys.BLANK_CUT_SPEED + blankCutType.flag, i)
    }

    @JvmStatic
    fun getSpeed(blankCutType: BlankCutType): Int {
        return SPUtils.getInt(SpKeys.BLANK_CUT_SPEED + blankCutType.flag, 10)
    }
}
