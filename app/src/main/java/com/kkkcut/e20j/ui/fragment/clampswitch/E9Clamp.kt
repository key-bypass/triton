package com.kkkcut.e20j.ui.fragment.clampswitch;

import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class E9Clamp {
    public static List<ClampDisplayBean> getClampBeanListE9(KeyInfo keyInfo, ClampBean clampBean) {
        ArrayList arrayList = new ArrayList();
        if (keyInfo.isNewHonda()) {
            ClampBean clampBean2 = new ClampBean();
            clampBean2.setClampSlot("0");
            clampBean2.setClampNum("S1");
            clampBean2.setClampSide("B");
            arrayList.add(getClampDisplayBean(clampBean2, keyInfo));
        }
        arrayList.add(getClampDisplayBean(keyInfo.getClampBean(), keyInfo));
        if (clampBean != null) {
            for (int i = 1; i < arrayList.size(); i++) {
                ClampDisplayBean clampDisplayBean = (ClampDisplayBean) arrayList.get(i);
                ClampBean clampBean3 = clampDisplayBean.getClampBean();
                if (clampBean3.getClampNum().equals(clampBean.getClampNum()) && clampBean3.getClampSide().equals(clampBean.getClampSide()) && clampBean3.getClampSlot().equals(clampBean.getClampSlot())) {
                    arrayList.set(i, (ClampDisplayBean) arrayList.get(0));
                    arrayList.set(0, clampDisplayBean);
                    keyInfo.setClampKeyBasicData(clampBean3);
                }
            }
        }
        return arrayList;
    }

    private static ClampDisplayBean getClampDisplayBean(ClampBean clampBean, KeyInfo keyInfo) {
        return new ClampDisplayBean(clampBean, getDrawableRes(clampBean, keyInfo));
    }

    private static int getDrawableRes(ClampBean clampBean, KeyInfo keyInfo) {
        return getDrawableResE9(clampBean, keyInfo);
    }

    public static int getDrawableRes(KeyInfo keyInfo) {
        return getDrawableResE9(keyInfo.getClampBean(), keyInfo);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getDrawableResE9(ClampBean clampBean, KeyInfo keyInfo) {
        if (clampBean.getClampNum().equals("S1")) {
            if (keyInfo.isNewHonda()) {
                return R.drawable.honda_clamp;
            }
            if (clampBean.getClampSide().equals("A") || clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_side_e9 : R.drawable.a9_laser_stop_4_side_e9 : keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_e9 : R.drawable.a9_laser_stop_4_e9;
            }
            if (clampBean.getClampSide().equals("C") || clampBean.getClampSide().equals("D")) {
                return keyInfo.getAlign() == 0 ? R.drawable.a9_standard_stop_1_e9 : keyInfo.getIcCard() == 852 ? R.drawable.a9_standard_stop_4_e9_852 : keyInfo.getIcCard() == 1047 ? R.drawable.a9_standard_stop_4_e9_1047 : R.drawable.a9_standard_stop_4_e9;
            }
            return 0;
        }
        if (clampBean.getClampNum().equals("S2")) {
            return clampBean.getClampSide().equals("A") ? keyInfo.getAlign() == 0 ? R.drawable.single_key_5mm_shoulder_small_e9 : R.drawable.single_key_5mm_tip_small_e9 : keyInfo.getAlign() == 0 ? R.drawable.single_key_35mm_shoulder_small_e9 : R.drawable.single_key_35mm_tip_small_e9;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return R.drawable.a9_tubular_stop_e9;
        }
        if (clampBean.getClampNum().equals("S4")) {
            return R.drawable.a9_tibbe_stop_3_e9;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getClampBigImgE9(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean.getClampNum().equals("S1")) {
            if (keyInfo.isNewHonda()) {
                return R.drawable.honda_clamp_big_e9;
            }
            if (clampBean.getClampSide().equals("A") || clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_side_big_e9 : R.drawable.a9_laser_stop_4_side_big_e9 : keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_big_e9 : R.drawable.a9_laser_stop_4_big_e9;
            }
            if (clampBean.getClampSide().equals("C") || clampBean.getClampSide().equals("D")) {
                return keyInfo.getAlign() == 0 ? R.drawable.a9_standard_stop_1_big_e9 : keyInfo.getIcCard() == 852 ? R.drawable.a9_standard_stop_4_big_e9_852 : keyInfo.getIcCard() == 1047 ? R.drawable.a9_standard_stop_4_big_e9_1047 : R.drawable.a9_standard_stop_4_big_e9;
            }
            return 0;
        }
        if (clampBean.getClampNum().equals("S2")) {
            return clampBean.getClampSide().equals("A") ? keyInfo.getAlign() == 0 ? R.drawable.single_key_5mm_shoulder_big_e9 : R.drawable.single_key_5mm_tip_big_e9 : keyInfo.getAlign() == 0 ? R.drawable.single_key_35mm_shoulder_big_e9 : R.drawable.single_key_35mm_tip_big_e9;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return R.drawable.a9_tubular_stop_big_e9;
        }
        if (clampBean.getClampNum().equals("S4")) {
            return R.drawable.a9_tibbe_stop_3_big_e9;
        }
        return 0;
    }

    public static int getClampZoomImgE9(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (keyInfo.isNewHonda()) {
            return R.drawable.honda_clamp_large;
        }
        if (clampBean.getClampNum().equals("S1")) {
            if (clampBean.getClampSide().equals("A") || clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_side_e9_large : R.drawable.a9_laser_stop_4_side_e9_large : keyInfo.getAlign() == 0 ? R.drawable.a9_laser_stop_1_e9_large : R.drawable.a9_laser_stop_4_e9_large;
            }
            if (clampBean.getClampSide().equals("C") || clampBean.getClampSide().equals("D")) {
                return keyInfo.getAlign() == 0 ? R.drawable.a9_standard_stop_1_e9_large : keyInfo.getIcCard() == 852 ? R.drawable.a9_standard_stop_4_e9_852_large : keyInfo.getIcCard() == 1047 ? R.drawable.a9_standard_stop_4_e9_1047_large : R.drawable.a9_standard_stop_4_e9_large;
            }
            return 0;
        }
        if (clampBean.getClampNum().equals("S2")) {
            return clampBean.getClampSide().equals("A") ? keyInfo.getAlign() == 0 ? R.drawable.single_key_5mm_shoulder_big_e9 : R.drawable.single_key_5mm_tip_big_e9 : keyInfo.getAlign() == 0 ? R.drawable.single_key_35mm_shoulder_big_e9 : R.drawable.single_key_35mm_tip_big_e9;
        }
        if (clampBean.getClampNum().equals("S3")) {
            return R.drawable.a9_tubular_stop_big_e9;
        }
        if (clampBean.getClampNum().equals("S4")) {
            return R.drawable.a9_tibbe_stop_3_e9_large;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getE9ClearClampImg(KeyInfo keyInfo) {
        if (keyInfo.isNewHonda()) {
            return R.drawable.clear_clamp_honda_key_e9;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S1")) {
            return (keyInfo.getClampBean().getClampSide().equals("A") || keyInfo.getClampBean().getClampSide().equals("B")) ? R.drawable.clear_clamp_laser_key : R.drawable.clear_clamp_standard_key;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S2")) {
            return R.drawable.clear_clamp_single_key_e9;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S3")) {
            return R.drawable.clear_clamp_tubular_e9;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S4")) {
            return R.drawable.clear_clamp_angle_key_e9;
        }
        return 0;
    }
}
