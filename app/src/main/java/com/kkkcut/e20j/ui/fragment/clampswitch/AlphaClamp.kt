package com.kkkcut.e20j.ui.fragment.clampswitch

import android.text.TextUtils
import com.cutting.machine.MachineInfo
import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.MyApplication
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
object AlphaClamp {
    fun getClampBeanListAlphaBeta(keyInfo: KeyInfo, clampBean: ClampBean?): List<ClampDisplayBean> {
        val arrayList = ArrayList<ClampDisplayBean>()
        arrayList.add(getClampDisplayBean(keyInfo.clampBean, keyInfo))
        val extJaw = keyInfo.extJaw
        if (!TextUtils.isEmpty(extJaw) && keyInfo.type != 0) {
            for (str in extJaw.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                arrayList.add(getClampDisplayBean(getSecondClampBean(str), keyInfo))
            }
        } else {
            val secondClampBean = getSecondClampBean(keyInfo)
            if (secondClampBean != null && !TextUtils.isEmpty(secondClampBean.clampNum)) {
                arrayList.add(getClampDisplayBean(secondClampBean, keyInfo))
            }
        }
        if (keyInfo.type == 0 && keyInfo.icCard != 1047 && keyInfo.icCard != 852) {
            val thirdClampBean = getThirdClampBean(keyInfo)
            if (thirdClampBean != null) {
                arrayList.add(getClampDisplayBean(thirdClampBean, keyInfo))
            }
            val fourthClampBean = getFourthClampBean(keyInfo)
            if (fourthClampBean != null) {
                arrayList.add(getClampDisplayBean(fourthClampBean, keyInfo))
            }
        }
        if (clampBean != null) {
            for (i in 1 until arrayList.size) {
                val clampDisplayBean = arrayList[i] as ClampDisplayBean
                val clampBean2 = clampDisplayBean.clampBean
                if (clampBean2!!.clampNum == clampBean.clampNum && clampBean2.clampSide == clampBean.clampSide && clampBean2.clampSlot == clampBean.clampSlot) {
                    arrayList[i] = arrayList[0] as ClampDisplayBean
                    arrayList[0] = clampDisplayBean
                    keyInfo.setClampKeyBasicData(clampBean2)
                }
            }
        }
        return arrayList
    }

    private fun getClampDisplayBean(clampBean: ClampBean, keyInfo: KeyInfo): ClampDisplayBean {
        return ClampDisplayBean(clampBean, getDrawableRes(clampBean, keyInfo))
    }

    private fun getDrawableRes(clampBean: ClampBean, keyInfo: KeyInfo): Int {
        return getDrawableResAlpha(clampBean, keyInfo)
    }

    fun getDrawableRes(keyInfo: KeyInfo): Int {
        return getDrawableResAlpha(keyInfo.clampBean, keyInfo)
    }

    private fun getSecondClampBean(keyInfo: KeyInfo): ClampBean? {
        if (keyInfo.icCard == 20131 || keyInfo.icCard == 1915 || keyInfo.shoulderBlock == 1) {
            return null
        }
        val clampBean = keyInfo.clampBean
        val clampBean2 = ClampBean()
        clampBean2.clampSlot = "0"
        if (keyInfo.type == 3 && keyInfo.side == 1 && !TextUtils.equals("1", clampBean.clampSlot)) {
            clampBean2.clampNum = "S1"
            clampBean2.clampSide = "C"
            clampBean2.clampSlot = "1"
            return clampBean2
        }
        if ("S1" == clampBean.clampNum) {
            clampBean2.clampNum = "S1"
            if ("A" == clampBean.clampSide) {
                clampBean2.clampSide = "B"
            } else if ("B" == clampBean.clampSide) {
                if ("0" != clampBean.clampSlot) {
                    return null
                }
                clampBean2.clampSide = "A"
            } else if ("C" == clampBean.clampSide) {
                clampBean2.clampSide = "D"
            } else if ("D" == clampBean.clampSide) {
                if (keyInfo.icCard == 852) {
                    clampBean2.clampNum = "S6"
                    clampBean2.clampSide = "A"
                } else if (keyInfo.icCard == 1047) {
                    clampBean2.clampNum = "S6"
                    clampBean2.clampSide = "B"
                } else {
                    clampBean2.clampSide = "C"
                }
            }
        } else if ("S2" == clampBean.clampNum) {
            clampBean2.clampNum = "S2"
            if ("A" == clampBean.clampSide) {
                clampBean2.clampSide = "B"
            } else {
                clampBean2.clampSide = "A"
            }
        } else {
            if ("S3" == clampBean.clampNum || "S4" == clampBean.clampNum) {
                return null
            }
            if ("S6" == clampBean.clampNum) {
                clampBean2.clampNum = "S1"
                clampBean2.clampSide = "D"
            } else if ("S10" == clampBean.clampNum) {
                return null
            }
        }
        return clampBean2
    }

    private fun getSecondClampBean(str: String): ClampBean {
        val clampBean = ClampBean()
        clampBean.clampSide = "A"
        clampBean.clampSlot = "0"
        if (str.contains("S1")) {
            clampBean.clampNum = "S1"
            if (str.contains("A")) {
                clampBean.clampSide = "A"
            } else if (str.contains("B")) {
                clampBean.clampSide = "B"
                if (str.contains("-1")) {
                    clampBean.clampSlot = "1"
                }
            } else if (str.contains("C")) {
                clampBean.clampSide = "C"
            } else if (str.contains("D")) {
                clampBean.clampSide = "D"
            }
        } else if (str.contains("S2")) {
            clampBean.clampNum = "S2"
            if (str.contains("A")) {
                clampBean.clampSide = "A"
            } else {
                clampBean.clampSide = "B"
            }
        } else if (str.contains("S3")) {
            clampBean.clampNum = "S3"
        } else if (str.contains("S4")) {
            clampBean.clampNum = "S4"
        } else if (str.contains("S6")) {
            clampBean.clampNum = "S6"
            if (str.contains("A")) {
                clampBean.clampSide = "A"
            } else {
                clampBean.clampSide = "B"
            }
        }
        return clampBean
    }

    private fun getThirdClampBean(keyInfo: KeyInfo): ClampBean? {
        val clampBean = keyInfo.clampBean
        val clampBean2 = ClampBean()
        clampBean2.clampSlot = "0"
        if ("S1" != clampBean.clampNum) {
            return null
        }
        if ("D" != clampBean.clampSide && "C" != clampBean.clampSide) {
            return null
        }
        clampBean2.clampNum = "S2"
        clampBean2.clampSide = "B"
        return clampBean2
    }

    private fun getFourthClampBean(keyInfo: KeyInfo): ClampBean? {
        val clampBean = keyInfo.clampBean
        val clampBean2 = ClampBean()
        clampBean2.clampSlot = "0"
        if ("S1" != clampBean.clampNum) {
            return null
        }
        if ("D" != clampBean.clampSide && "C" != clampBean.clampSide) {
            return null
        }
        clampBean2.clampNum = "S2"
        clampBean2.clampSide = "A"
        return clampBean2
    }

    /* JADX INFO: Access modifiers changed from: protected */
    fun getDrawableResAlpha(clampBean: ClampBean, keyInfo: KeyInfo): Int {
        if (keyInfo.icCard == 5590) {
            return R.drawable.sag_clamp
        }
        if (clampBean.clampNum == "S1") {
            if (clampBean.clampSide == "A") {
                return if (keyInfo.align == 0) R.drawable.car_clamp_a_shoulder else R.drawable.car_clamp_a_tip
            }
            if (clampBean.clampSide == "B") {
                return if (clampBean.clampSlot == "1") if (keyInfo.align == 0) R.drawable.car_clamp_b_shoulder_side else R.drawable.car_clamp_b_tip_side else if (keyInfo.align == 0) R.drawable.car_clamp_b_shoulder else if ((keyInfo.icCard == 1019 || keyInfo.icCard == 1369 || keyInfo.icCard == 1443)) R.drawable.car_clamp_b_tip_hu64 else R.drawable.car_clamp_b_tip
            }
            if (clampBean.clampSide == "C") {
                return if (keyInfo.align == 0) R.drawable.car_clamp_c_shoulder else if (TextUtils.equals(
                        clampBean.clampSlot,
                        "1"
                    )
                ) R.drawable.car_clamp_c_down_tip else if (keyInfo.spaceStr.split(";".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()[0].split(",".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[0].toInt() + 300 > 2650
                ) R.drawable.car_clamp_c_long_tip else R.drawable.car_clamp_c_tip
            }
            if (clampBean.clampSide == "D") {
                return if (keyInfo.align == 0) if (keyInfo.shoulderBlock == 1) R.drawable.car_clamp_d_shoulder_6620131 else R.drawable.car_clamp_d_shoulder else if ((keyInfo.icCard == 20131 || keyInfo.icCard == 1915)) R.drawable.car_clamp_d_tip_20131 else if (keyInfo.isNewHonda) R.drawable.car_clamp_d_tip_honda else R.drawable.car_clamp_d_tip
            }
        } else {
            if (clampBean.clampNum == "S2") {
                return if (clampBean.clampSide == "A") if (keyInfo.align == 0) R.drawable.singlekey_clamp_a_shoulder else if (keyInfo.type == 0) R.drawable.singlekey_clamp_a_tip2 else R.drawable.singlekey_clamp_a_tip else if (keyInfo.align == 0) R.drawable.singlekey_clamp_b_shoulder else if (keyInfo.isNewHonda) R.drawable.singlekey_clamp_b_tip_honda else if (keyInfo.type == 0) R.drawable.singlekey_clamp_b_tip2 else R.drawable.singlekey_clamp_b_tip
            }
            if (clampBean.clampNum == "S3") {
                return if (MachineInfo.isE20Us(MyApplication.getInstance())) R.drawable.tubular_clamp else R.drawable.tubular_clamp_s3_s7
            }
            if (clampBean.clampNum == "S4") {
                return R.drawable.anglekey_clamp
            }
            if (clampBean.clampNum == "S6") {
                return if (clampBean.clampSide == "A") R.drawable.sx9_clamp_side_a else R.drawable.sx9_clamp_side_b
            }
            if (clampBean.clampNum == "S9") {
                return if (clampBean.clampSide == "A") R.drawable.s9_a else R.drawable.s9_b
            }
            if (clampBean.clampNum == "S10") {
                return R.drawable.s10
            }
        }
        return 0
    }

    /* JADX INFO: Access modifiers changed from: protected */
    fun getClampBigImgAlphaBeta(keyInfo: KeyInfo): Int {
        if (keyInfo.icCard == 5590) {
            return R.drawable.sag_clamp_remind
        }
        val clampBean = keyInfo.clampBean
        if ("S1" == clampBean.clampNum) {
            if ("A" == clampBean.clampSide) {
                if ("0" == clampBean.clampSlot) {
                    return if (keyInfo.align == 0) R.drawable.car_clamp_remind_a_shoulder else R.drawable.car_clamp_remind_a_tip
                }
            } else {
                if ("B" == clampBean.clampSide) {
                    return if ("0" == clampBean.clampSlot) if (keyInfo.align == 0) R.drawable.car_clamp_remind_b_shoulder else if ((keyInfo.icCard == 1019 || keyInfo.icCard == 1369 || keyInfo.icCard == 1443)) R.drawable.car_clamp_remind_b_tip_hu64 else R.drawable.car_clamp_remind_b_tip else if (keyInfo.align == 0) R.drawable.car_clamp_remind_b_shoulder_side else R.drawable.car_clamp_remind_b_tip_side
                }
                if ("C" == clampBean.clampSide) {
                    return if ("0" == clampBean.clampSlot) if (keyInfo.align == 0) R.drawable.car_clamp_remind_c_shoulder else if (keyInfo.spaceStr.split(
                            ";".toRegex()
                        ).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[0].split(",".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[0].toInt() + 300 > 2650
                    ) R.drawable.car_clamp_remind_c_long_tip else R.drawable.car_clamp_remind_c_tip else R.drawable.car_clamp_remind_c_down_tip
                }
                if ("D" == clampBean.clampSide && "0" == clampBean.clampSlot) {
                    return if (keyInfo.align == 0) if (keyInfo.shoulderBlock == 1) R.drawable.car_clamp_remind_d_shoulder_6620131 else R.drawable.car_clamp_remind_d_shoulder else if (keyInfo.isNewHonda) R.drawable.car_clamp_remind_d_tip_honda else if ((keyInfo.icCard == 20131 || keyInfo.icCard == 1915)) R.drawable.car_clamp_remind_d_tip_20131 else R.drawable.car_clamp_remind_d_tip
                }
            }
        } else {
            if ("S2" == clampBean.clampNum) {
                return if ("A" == clampBean.clampSide) if (keyInfo.align == 0) R.drawable.singlekey_clamp_remind_a_shoulder else if (keyInfo.type == 0) R.drawable.singlekey_clamp_remind_a_tip2 else R.drawable.singlekey_clamp_remind_a_tip else if (keyInfo.align == 0) R.drawable.singlekey_clamp_remind_b_shoulder else if (keyInfo.isNewHonda) R.drawable.singlekey_clamp_remind_b_tip_honda else if (keyInfo.type == 0) R.drawable.singlekey_clamp_remind_b_tip2 else R.drawable.singlekey_clamp_remind_b_tip
            }
            if ("S3" == clampBean.clampNum) {
                if ("A" == clampBean.clampSide) {
                    return if (MachineInfo.isE20Us(MyApplication.getInstance())) R.drawable.tubular_clamp_remind else R.drawable.tubular_clamp_remind_s3_s7
                }
            } else if ("S4" == clampBean.clampNum) {
                if ("A" == clampBean.clampSide) {
                    return R.drawable.angel_key_clamp_remind
                }
            } else {
                if ("S6" == clampBean.clampNum) {
                    return if ("A" == clampBean.clampSide) R.drawable.sx9_clamp_remind_a else R.drawable.sx9_clamp_remind_b
                }
                if ("S9" == clampBean.clampNum) {
                    return if ("A" == clampBean.clampSide) R.drawable.s9_a_remind else R.drawable.s9_b_remind
                }
                if (TextUtils.equals(clampBean.clampNum, "S10")) {
                    return R.drawable.s10_remind
                }
            }
        }
        return 0
    }

    fun getClampZoomImgAlpha(keyInfo: KeyInfo): Int {
        if (keyInfo.icCard == 5590) {
            return R.drawable.sag_clamp_large
        }
        val clampBean = keyInfo.clampBean
        if ("S1" == clampBean.clampNum) {
            if ("A" == clampBean.clampSide) {
                if ("0" == clampBean.clampSlot) {
                    return if (keyInfo.align == 0) R.drawable.car_clamp_a_shoulder_large else R.drawable.car_clamp_a_tip_large
                }
            } else {
                if ("B" == clampBean.clampSide) {
                    return if ("0" == clampBean.clampSlot) if (keyInfo.align == 0) R.drawable.car_clamp_b_shoulder_large else if ((keyInfo.icCard == 1019 || keyInfo.icCard == 1369 || keyInfo.icCard == 1443)) R.drawable.car_clamp_remind_b_tip_hu64 else R.drawable.car_clamp_b_tip_large else if (keyInfo.align == 0) R.drawable.car_clamp_b_shoulder_side_large else R.drawable.car_clamp_b_tip_side_large
                }
                if ("C" == clampBean.clampSide) {
                    return if ("0" == clampBean.clampSlot) if (keyInfo.align == 0) R.drawable.car_clamp_c_shoulder_large else if (keyInfo.spaceStr.split(
                            ";".toRegex()
                        ).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[0].split(",".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()[0].toInt() + 300 > 2650
                    ) R.drawable.car_clamp_c_long_tip_large else R.drawable.car_clamp_c_tip_large else R.drawable.car_clamp_c_down_tip_large
                }
                if ("D" == clampBean.clampSide && "0" == clampBean.clampSlot) {
                    return if (keyInfo.align == 0) if (keyInfo.shoulderBlock == 1) R.drawable.car_clamp_d_shoulder_6620131 else R.drawable.car_clamp_d_shoulder_large else if (keyInfo.isNewHonda) R.drawable.car_clamp_d_tip_honda_large else if ((keyInfo.icCard == 20131 || keyInfo.icCard == 1915)) R.drawable.car_clamp_d_tip_20131_large else R.drawable.car_clamp_d_tip_large
                }
            }
        } else {
            if ("S2" == clampBean.clampNum) {
                return if ("A" == clampBean.clampSide) if (keyInfo.align == 0) R.drawable.singlekey_clamp_a_shoulder_large else R.drawable.singlekey_clamp_a_tip_large else if (keyInfo.align == 0) R.drawable.singlekey_clamp_b_shoulder_large else if (keyInfo.isNewHonda) R.drawable.singlekey_clamp_remind_b_tip_honda else R.drawable.singlekey_clamp_b_tip_large
            }
            if ("S3" == clampBean.clampNum) {
                if ("A" == clampBean.clampSide) {
                    return if (MachineInfo.isE20Us(MyApplication.getInstance())) R.drawable.tubular_clamp_large else R.drawable.tubular_clamp_large_s3_s7
                }
            } else if ("S4" == clampBean.clampNum) {
                if ("A" == clampBean.clampSide) {
                    return R.drawable.anglekey_clamp_large
                }
            } else {
                if ("S6" == clampBean.clampNum) {
                    return if ("A" == clampBean.clampSide) R.drawable.sx9_clamp_side_a_large else R.drawable.sx9_clamp_side_b_large
                }
                if ("S9" == clampBean.clampNum) {
                    return if ("A" == clampBean.clampSide) R.drawable.s9_a_large else R.drawable.s9_b_large
                }
                if (TextUtils.equals(clampBean.clampNum, "S10")) {
                    return R.drawable.s10_large
                }
            }
        }
        return 0
    }

    fun getAlphaClearClampImg(keyInfo: KeyInfo): Int {
        if (keyInfo.clampBean.clampNum == "S1") {
            return if (keyInfo.clampBean.clampSide == "A") R.drawable.clear_clamp_s1_a else if (keyInfo.clampBean.clampSide == "B") R.drawable.clear_clamp_s1_b else if (keyInfo.clampBean.clampSide == "C") R.drawable.clear_clamp_s1_c else R.drawable.clear_clamp_s1_d
        }
        if (keyInfo.clampBean.clampNum == "S2") {
            return if (keyInfo.clampBean.clampSide == "A") R.drawable.clear_clamp_s2_a else R.drawable.clear_clamp_s2_b
        }
        if (keyInfo.clampBean.clampNum == "S3") {
            return R.drawable.clear_clamp_s3
        }
        if (keyInfo.clampBean.clampNum == "S4") {
            return R.drawable.clear_clamp_s4
        }
        if (keyInfo.clampBean.clampNum == "S6") {
            return R.drawable.clear_clamp_s6
        }
        return 0
    }
}
