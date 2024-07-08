package com.kkkcut.e20j.ui.fragment.hondakey

import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo

/* loaded from: classes.dex */
object HondaKeyFactory {
    fun createHondaSideKey(i: Int, i2: Int): KeyInfo {
        if (i == 2020) {
            return createHondaSideKey2020(i2)
        }
        return createHondaSideKey2021(i2)
    }

    private fun createHondaSideKey2021(i: Int): KeyInfo {
        val keyInfo = KeyInfo()
        keyInfo.type = 3
        keyInfo.align = 1
        keyInfo.spaceStr = "2500,300"
        keyInfo.spaceWidthStr = "100,100"
        keyInfo.width = 150
        keyInfo.cutDepth = if (i == 0) 130 else 20
        keyInfo.guide = 40
        keyInfo.depthStr = "40"
        keyInfo.depthName = "1"
        keyInfo.thick = 700
        keyInfo.side = 0
        val clampBean = ClampBean()
        clampBean.clampNum = "S1"
        clampBean.clampSide = "B"
        clampBean.clampSlot = "1"
        keyInfo.setClampKeyBasicData(clampBean)
        return keyInfo
    }

    fun createHondaSideKey2020(i: Int): KeyInfo {
        val keyInfo = KeyInfo()
        keyInfo.type = 3
        keyInfo.align = 1
        if (i == 0) {
            keyInfo.spaceStr = "2800,300"
        } else {
            keyInfo.spaceStr = "2500,300"
        }
        keyInfo.spaceWidthStr = "100,100"
        keyInfo.width = 150
        keyInfo.cutDepth = if (i == 0) 165 else 55
        keyInfo.guide = 50
        keyInfo.depthStr = "50"
        keyInfo.depthName = "1"
        keyInfo.thick = 900
        keyInfo.side = 0
        val clampBean = ClampBean()
        clampBean.clampNum = "S1"
        clampBean.clampSide = "B"
        clampBean.clampSlot = "1"
        keyInfo.setClampKeyBasicData(clampBean)
        return keyInfo
    }
}
