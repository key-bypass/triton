package com.kkkcut.e20j.ui.fragment.clampswitch

import com.cutting.machine.bean.ClampBean
import com.cutting.machine.bean.KeyInfo
import com.kkkcut.e20j.us.R

/* loaded from: classes.dex */
object E9Clamp {
    fun getClampBeanListE9(keyInfo: KeyInfo, clampBean: ClampBean?): List<ClampDisplayBean> {
        val arrayList = ArrayList<ClampDisplayBean>()
        if (keyInfo.isNewHonda) {
            val clampBean2 = ClampBean()
            clampBean2.clampSlot = "0"
            clampBean2.clampNum = "S1"
            clampBean2.clampSide = "B"
            arrayList.add(getClampDisplayBean(clampBean2, keyInfo))
        }
        arrayList.add(getClampDisplayBean(keyInfo.clampBean, keyInfo))
        if (clampBean != null) {
            for (i in 1 until arrayList.size) {
                val clampDisplayBean = arrayList[i]
                val clampBean3 = clampDisplayBean.clampBean
                if (clampBean3!!.clampNum == clampBean.clampNum && clampBean3.clampSide == clampBean.clampSide && clampBean3.clampSlot == clampBean.clampSlot) {
                    arrayList[i] = arrayList[0]
                    arrayList[0] = clampDisplayBean
                    keyInfo.setClampKeyBasicData(clampBean3)
                }
            }
        }
        return arrayList
    }

    private fun getClampDisplayBean(clampBean: ClampBean, keyInfo: KeyInfo): ClampDisplayBean {
        return ClampDisplayBean(clampBean, getDrawableRes(clampBean, keyInfo))
    }

    private fun getDrawableRes(clampBean: ClampBean, keyInfo: KeyInfo): Int {
        return getDrawableResE9(clampBean, keyInfo)
    }

    fun getDrawableRes(keyInfo: KeyInfo): Int {
        return getDrawableResE9(keyInfo.clampBean, keyInfo)
    }

    /* JADX INFO: Access modifiers changed from: protected */
    fun getDrawableResE9(clampBean: ClampBean, keyInfo: KeyInfo): Int {
        if (clampBean.clampNum == "S1") {
            if (keyInfo.isNewHonda) {
                return R.drawable.honda_clamp
            }
            if (clampBean.clampSide == "A" || clampBean.clampSide == "B") {
                return if (clampBean.clampSlot == "1") if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_side_e9 else R.drawable.a9_laser_stop_4_side_e9 else if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_e9 else R.drawable.a9_laser_stop_4_e9
            }
            if (clampBean.clampSide == "C" || clampBean.clampSide == "D") {
                return if (keyInfo.align == 0) R.drawable.a9_standard_stop_1_e9 else if (keyInfo.icCard == 852) R.drawable.a9_standard_stop_4_e9_852 else if (keyInfo.icCard == 1047) R.drawable.a9_standard_stop_4_e9_1047 else R.drawable.a9_standard_stop_4_e9
            }
            return 0
        }
        if (clampBean.clampNum == "S2") {
            return if (clampBean.clampSide == "A") if (keyInfo.align == 0) R.drawable.single_key_5mm_shoulder_small_e9 else R.drawable.single_key_5mm_tip_small_e9 else if (keyInfo.align == 0) R.drawable.single_key_35mm_shoulder_small_e9 else R.drawable.single_key_35mm_tip_small_e9
        }
        if (clampBean.clampNum == "S3") {
            return R.drawable.a9_tubular_stop_e9
        }
        if (clampBean.clampNum == "S4") {
            return R.drawable.a9_tibbe_stop_3_e9
        }
        return 0
    }

    /* JADX INFO: Access modifiers changed from: protected */
    fun getClampBigImgE9(keyInfo: KeyInfo): Int {
        val clampBean = keyInfo.clampBean
        if (clampBean.clampNum == "S1") {
            if (keyInfo.isNewHonda) {
                return R.drawable.honda_clamp_big_e9
            }
            if (clampBean.clampSide == "A" || clampBean.clampSide == "B") {
                return if (clampBean.clampSlot == "1") if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_side_big_e9 else R.drawable.a9_laser_stop_4_side_big_e9 else if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_big_e9 else R.drawable.a9_laser_stop_4_big_e9
            }
            if (clampBean.clampSide == "C" || clampBean.clampSide == "D") {
                return if (keyInfo.align == 0) R.drawable.a9_standard_stop_1_big_e9 else if (keyInfo.icCard == 852) R.drawable.a9_standard_stop_4_big_e9_852 else if (keyInfo.icCard == 1047) R.drawable.a9_standard_stop_4_big_e9_1047 else R.drawable.a9_standard_stop_4_big_e9
            }
            return 0
        }
        if (clampBean.clampNum == "S2") {
            return if (clampBean.clampSide == "A") if (keyInfo.align == 0) R.drawable.single_key_5mm_shoulder_big_e9 else R.drawable.single_key_5mm_tip_big_e9 else if (keyInfo.align == 0) R.drawable.single_key_35mm_shoulder_big_e9 else R.drawable.single_key_35mm_tip_big_e9
        }
        if (clampBean.clampNum == "S3") {
            return R.drawable.a9_tubular_stop_big_e9
        }
        if (clampBean.clampNum == "S4") {
            return R.drawable.a9_tibbe_stop_3_big_e9
        }
        return 0
    }

    fun getClampZoomImgE9(keyInfo: KeyInfo): Int {
        val clampBean = keyInfo.clampBean
        if (keyInfo.isNewHonda) {
            return R.drawable.honda_clamp_large
        }
        if (clampBean.clampNum == "S1") {
            if (clampBean.clampSide == "A" || clampBean.clampSide == "B") {
                return if (clampBean.clampSlot == "1") if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_side_e9_large else R.drawable.a9_laser_stop_4_side_e9_large else if (keyInfo.align == 0) R.drawable.a9_laser_stop_1_e9_large else R.drawable.a9_laser_stop_4_e9_large
            }
            if (clampBean.clampSide == "C" || clampBean.clampSide == "D") {
                return if (keyInfo.align == 0) R.drawable.a9_standard_stop_1_e9_large else if (keyInfo.icCard == 852) R.drawable.a9_standard_stop_4_e9_852_large else if (keyInfo.icCard == 1047) R.drawable.a9_standard_stop_4_e9_1047_large else R.drawable.a9_standard_stop_4_e9_large
            }
            return 0
        }
        if (clampBean.clampNum == "S2") {
            return if (clampBean.clampSide == "A") if (keyInfo.align == 0) R.drawable.single_key_5mm_shoulder_big_e9 else R.drawable.single_key_5mm_tip_big_e9 else if (keyInfo.align == 0) R.drawable.single_key_35mm_shoulder_big_e9 else R.drawable.single_key_35mm_tip_big_e9
        }
        if (clampBean.clampNum == "S3") {
            return R.drawable.a9_tubular_stop_big_e9
        }
        if (clampBean.clampNum == "S4") {
            return R.drawable.a9_tibbe_stop_3_e9_large
        }
        return 0
    }

    /* JADX INFO: Access modifiers changed from: protected */
    fun getE9ClearClampImg(keyInfo: KeyInfo): Int {
        if (keyInfo.isNewHonda) {
            return R.drawable.clear_clamp_honda_key_e9
        }
        if (keyInfo.clampBean.clampNum == "S1") {
            return if ((keyInfo.clampBean.clampSide == "A" || keyInfo.clampBean.clampSide == "B")) R.drawable.clear_clamp_laser_key else R.drawable.clear_clamp_standard_key
        }
        if (keyInfo.clampBean.clampNum == "S2") {
            return R.drawable.clear_clamp_single_key_e9
        }
        if (keyInfo.clampBean.clampNum == "S3") {
            return R.drawable.clear_clamp_tubular_e9
        }
        if (keyInfo.clampBean.clampNum == "S4") {
            return R.drawable.clear_clamp_angle_key_e9
        }
        return 0
    }
}
