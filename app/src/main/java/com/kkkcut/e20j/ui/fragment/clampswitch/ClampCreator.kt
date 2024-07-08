package com.kkkcut.e20j.ui.fragment.clampswitch

import com.cutting.machine.CuttingMachine
import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo

/* loaded from: classes.dex */
object ClampCreator {
    fun getDrawableRes(keyInfo: KeyInfo): Int {
        if (CuttingMachine.getInstance().isE9) {
            return E9Clamp.getDrawableResE9(keyInfo.clampBean, keyInfo)
        }
        return AlphaClamp.getDrawableResAlpha(keyInfo.clampBean, keyInfo)
    }

    @JvmStatic
    fun getClampBigImg(keyInfo: KeyInfo): Int {
        if (CuttingMachine.getInstance().isE9) {
            return E9Clamp.getClampBigImgE9(keyInfo)
        }
        return AlphaClamp.getClampBigImgAlphaBeta(keyInfo)
    }

    fun getClampBeanList(keyInfo: KeyInfo, clampBean: ClampBean?): List<ClampDisplayBean> {
        if (CuttingMachine.getInstance().isE9) {
            return E9Clamp.getClampBeanListE9(keyInfo, clampBean)
        }
        return AlphaClamp.getClampBeanListAlphaBeta(keyInfo, clampBean)
    }

    @JvmStatic
    fun getClearClampImg(keyInfo: KeyInfo): Int {
        if (CuttingMachine.getInstance().isE9) {
            return E9Clamp.getE9ClearClampImg(keyInfo)
        }
        return AlphaClamp.getAlphaClearClampImg(keyInfo)
    }

    fun getClampZoomImg(keyInfo: KeyInfo): Int {
        if (CuttingMachine.getInstance().isE9) {
            return E9Clamp.getClampZoomImgE9(keyInfo)
        }
        return AlphaClamp.getClampZoomImgAlpha(keyInfo)
    }
}
