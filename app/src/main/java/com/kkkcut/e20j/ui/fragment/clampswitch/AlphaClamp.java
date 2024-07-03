package com.kkkcut.e20j.ui.fragment.clampswitch;

import android.text.TextUtils;

import com.cutting.machine.MachineInfo;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.kkkcut.e20j.MyApplication;
import com.kkkcut.e20j.us.R;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AlphaClamp {
    public static List<ClampDisplayBean> getClampBeanListAlphaBeta(KeyInfo keyInfo, ClampBean clampBean) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getClampDisplayBean(keyInfo.getClampBean(), keyInfo));
        String extJaw = keyInfo.getExtJaw();
        if (!TextUtils.isEmpty(extJaw) && keyInfo.getType() != 0) {
            for (String str : extJaw.split(",")) {
                arrayList.add(getClampDisplayBean(getSecondClampBean(str), keyInfo));
            }
        } else {
            ClampBean secondClampBean = getSecondClampBean(keyInfo);
            if (secondClampBean != null && !TextUtils.isEmpty(secondClampBean.getClampNum())) {
                arrayList.add(getClampDisplayBean(secondClampBean, keyInfo));
            }
        }
        if (keyInfo.getType() == 0 && keyInfo.getIcCard() != 1047 && keyInfo.getIcCard() != 852) {
            ClampBean thirdClampBean = getThirdClampBean(keyInfo);
            if (thirdClampBean != null) {
                arrayList.add(getClampDisplayBean(thirdClampBean, keyInfo));
            }
            ClampBean fourthClampBean = getFourthClampBean(keyInfo);
            if (fourthClampBean != null) {
                arrayList.add(getClampDisplayBean(fourthClampBean, keyInfo));
            }
        }
        if (clampBean != null) {
            for (int i = 1; i < arrayList.size(); i++) {
                ClampDisplayBean clampDisplayBean = (ClampDisplayBean) arrayList.get(i);
                ClampBean clampBean2 = clampDisplayBean.getClampBean();
                if (clampBean2.getClampNum().equals(clampBean.getClampNum()) && clampBean2.getClampSide().equals(clampBean.getClampSide()) && clampBean2.getClampSlot().equals(clampBean.getClampSlot())) {
                    arrayList.set(i, (ClampDisplayBean) arrayList.get(0));
                    arrayList.set(0, clampDisplayBean);
                    keyInfo.setClampKeyBasicData(clampBean2);
                }
            }
        }
        return arrayList;
    }

    private static ClampDisplayBean getClampDisplayBean(ClampBean clampBean, KeyInfo keyInfo) {
        return new ClampDisplayBean(clampBean, getDrawableRes(clampBean, keyInfo));
    }

    private static int getDrawableRes(ClampBean clampBean, KeyInfo keyInfo) {
        return getDrawableResAlpha(clampBean, keyInfo);
    }

    public static int getDrawableRes(KeyInfo keyInfo) {
        return getDrawableResAlpha(keyInfo.getClampBean(), keyInfo);
    }

    private static ClampBean getSecondClampBean(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915 || keyInfo.getShoulderBlock() == 1) {
            return null;
        }
        ClampBean clampBean = keyInfo.getClampBean();
        ClampBean clampBean2 = new ClampBean();
        clampBean2.setClampSlot("0");
        if (keyInfo.getType() == 3 && keyInfo.getSide() == 1 && !TextUtils.equals("1", clampBean.getClampSlot())) {
            clampBean2.setClampNum("S1");
            clampBean2.setClampSide("C");
            clampBean2.setClampSlot("1");
            return clampBean2;
        }
        if ("S1".equals(clampBean.getClampNum())) {
            clampBean2.setClampNum("S1");
            if ("A".equals(clampBean.getClampSide())) {
                clampBean2.setClampSide("B");
            } else if ("B".equals(clampBean.getClampSide())) {
                if (!"0".equals(clampBean.getClampSlot())) {
                    return null;
                }
                clampBean2.setClampSide("A");
            } else if ("C".equals(clampBean.getClampSide())) {
                clampBean2.setClampSide("D");
            } else if ("D".equals(clampBean.getClampSide())) {
                if (keyInfo.getIcCard() == 852) {
                    clampBean2.setClampNum("S6");
                    clampBean2.setClampSide("A");
                } else if (keyInfo.getIcCard() == 1047) {
                    clampBean2.setClampNum("S6");
                    clampBean2.setClampSide("B");
                } else {
                    clampBean2.setClampSide("C");
                }
            }
        } else if ("S2".equals(clampBean.getClampNum())) {
            clampBean2.setClampNum("S2");
            if ("A".equals(clampBean.getClampSide())) {
                clampBean2.setClampSide("B");
            } else {
                clampBean2.setClampSide("A");
            }
        } else {
            if ("S3".equals(clampBean.getClampNum()) || "S4".equals(clampBean.getClampNum())) {
                return null;
            }
            if ("S6".equals(clampBean.getClampNum())) {
                clampBean2.setClampNum("S1");
                clampBean2.setClampSide("D");
            } else if ("S10".equals(clampBean.getClampNum())) {
                return null;
            }
        }
        return clampBean2;
    }

    private static ClampBean getSecondClampBean(String str) {
        ClampBean clampBean = new ClampBean();
        clampBean.setClampSide("A");
        clampBean.setClampSlot("0");
        if (str.contains("S1")) {
            clampBean.setClampNum("S1");
            if (str.contains("A")) {
                clampBean.setClampSide("A");
            } else if (str.contains("B")) {
                clampBean.setClampSide("B");
                if (str.contains("-1")) {
                    clampBean.setClampSlot("1");
                }
            } else if (str.contains("C")) {
                clampBean.setClampSide("C");
            } else if (str.contains("D")) {
                clampBean.setClampSide("D");
            }
        } else if (str.contains("S2")) {
            clampBean.setClampNum("S2");
            if (str.contains("A")) {
                clampBean.setClampSide("A");
            } else {
                clampBean.setClampSide("B");
            }
        } else if (str.contains("S3")) {
            clampBean.setClampNum("S3");
        } else if (str.contains("S4")) {
            clampBean.setClampNum("S4");
        } else if (str.contains("S6")) {
            clampBean.setClampNum("S6");
            if (str.contains("A")) {
                clampBean.setClampSide("A");
            } else {
                clampBean.setClampSide("B");
            }
        }
        return clampBean;
    }

    private static ClampBean getThirdClampBean(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        ClampBean clampBean2 = new ClampBean();
        clampBean2.setClampSlot("0");
        if (!"S1".equals(clampBean.getClampNum())) {
            return null;
        }
        if (!"D".equals(clampBean.getClampSide()) && !"C".equals(clampBean.getClampSide())) {
            return null;
        }
        clampBean2.setClampNum("S2");
        clampBean2.setClampSide("B");
        return clampBean2;
    }

    private static ClampBean getFourthClampBean(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        ClampBean clampBean2 = new ClampBean();
        clampBean2.setClampSlot("0");
        if (!"S1".equals(clampBean.getClampNum())) {
            return null;
        }
        if (!"D".equals(clampBean.getClampSide()) && !"C".equals(clampBean.getClampSide())) {
            return null;
        }
        clampBean2.setClampNum("S2");
        clampBean2.setClampSide("A");
        return clampBean2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getDrawableResAlpha(ClampBean clampBean, KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 5590) {
            return R.drawable.sag_clamp;
        }
        if (clampBean.getClampNum().equals("S1")) {
            if (clampBean.getClampSide().equals("A")) {
                return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_a_shoulder : R.drawable.car_clamp_a_tip;
            }
            if (clampBean.getClampSide().equals("B")) {
                return clampBean.getClampSlot().equals("1") ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_b_shoulder_side : R.drawable.car_clamp_b_tip_side : keyInfo.getAlign() == 0 ? R.drawable.car_clamp_b_shoulder : (keyInfo.getIcCard() == 1019 || keyInfo.getIcCard() == 1369 || keyInfo.getIcCard() == 1443) ? R.drawable.car_clamp_b_tip_hu64 : R.drawable.car_clamp_b_tip;
            }
            if (clampBean.getClampSide().equals("C")) {
                return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_c_shoulder : TextUtils.equals(clampBean.getClampSlot(), "1") ? R.drawable.car_clamp_c_down_tip : Integer.parseInt(keyInfo.getSpaceStr().split(";")[0].split(",")[0]) + 300 > 2650 ? R.drawable.car_clamp_c_long_tip : R.drawable.car_clamp_c_tip;
            }
            if (clampBean.getClampSide().equals("D")) {
                return keyInfo.getAlign() == 0 ? keyInfo.getShoulderBlock() == 1 ? R.drawable.car_clamp_d_shoulder_6620131 : R.drawable.car_clamp_d_shoulder : (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915) ? R.drawable.car_clamp_d_tip_20131 : keyInfo.isNewHonda() ? R.drawable.car_clamp_d_tip_honda : R.drawable.car_clamp_d_tip;
            }
        } else {
            if (clampBean.getClampNum().equals("S2")) {
                return clampBean.getClampSide().equals("A") ? keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_a_shoulder : keyInfo.getType() == 0 ? R.drawable.singlekey_clamp_a_tip2 : R.drawable.singlekey_clamp_a_tip : keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_b_shoulder : keyInfo.isNewHonda() ? R.drawable.singlekey_clamp_b_tip_honda : keyInfo.getType() == 0 ? R.drawable.singlekey_clamp_b_tip2 : R.drawable.singlekey_clamp_b_tip;
            }
            if (clampBean.getClampNum().equals("S3")) {
                return MachineInfo.isE20Us(MyApplication.getInstance()) ? R.drawable.tubular_clamp : R.drawable.tubular_clamp_s3_s7;
            }
            if (clampBean.getClampNum().equals("S4")) {
                return R.drawable.anglekey_clamp;
            }
            if (clampBean.getClampNum().equals("S6")) {
                return clampBean.getClampSide().equals("A") ? R.drawable.sx9_clamp_side_a : R.drawable.sx9_clamp_side_b;
            }
            if (clampBean.getClampNum().equals("S9")) {
                return clampBean.getClampSide().equals("A") ? R.drawable.s9_a : R.drawable.s9_b;
            }
            if (clampBean.getClampNum().equals("S10")) {
                return R.drawable.s10;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getClampBigImgAlphaBeta(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 5590) {
            return R.drawable.sag_clamp_remind;
        }
        ClampBean clampBean = keyInfo.getClampBean();
        if ("S1".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                if ("0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_a_shoulder : R.drawable.car_clamp_remind_a_tip;
                }
            } else {
                if ("B".equals(clampBean.getClampSide())) {
                    return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_b_shoulder : (keyInfo.getIcCard() == 1019 || keyInfo.getIcCard() == 1369 || keyInfo.getIcCard() == 1443) ? R.drawable.car_clamp_remind_b_tip_hu64 : R.drawable.car_clamp_remind_b_tip : keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_b_shoulder_side : R.drawable.car_clamp_remind_b_tip_side;
                }
                if ("C".equals(clampBean.getClampSide())) {
                    return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_remind_c_shoulder : Integer.parseInt(keyInfo.getSpaceStr().split(";")[0].split(",")[0]) + 300 > 2650 ? R.drawable.car_clamp_remind_c_long_tip : R.drawable.car_clamp_remind_c_tip : R.drawable.car_clamp_remind_c_down_tip;
                }
                if ("D".equals(clampBean.getClampSide()) && "0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? keyInfo.getShoulderBlock() == 1 ? R.drawable.car_clamp_remind_d_shoulder_6620131 : R.drawable.car_clamp_remind_d_shoulder : keyInfo.isNewHonda() ? R.drawable.car_clamp_remind_d_tip_honda : (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915) ? R.drawable.car_clamp_remind_d_tip_20131 : R.drawable.car_clamp_remind_d_tip;
                }
            }
        } else {
            if ("S2".equals(clampBean.getClampNum())) {
                return "A".equals(clampBean.getClampSide()) ? keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_remind_a_shoulder : keyInfo.getType() == 0 ? R.drawable.singlekey_clamp_remind_a_tip2 : R.drawable.singlekey_clamp_remind_a_tip : keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_remind_b_shoulder : keyInfo.isNewHonda() ? R.drawable.singlekey_clamp_remind_b_tip_honda : keyInfo.getType() == 0 ? R.drawable.singlekey_clamp_remind_b_tip2 : R.drawable.singlekey_clamp_remind_b_tip;
            }
            if ("S3".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return MachineInfo.isE20Us(MyApplication.getInstance()) ? R.drawable.tubular_clamp_remind : R.drawable.tubular_clamp_remind_s3_s7;
                }
            } else if ("S4".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return R.drawable.angel_key_clamp_remind;
                }
            } else {
                if ("S6".equals(clampBean.getClampNum())) {
                    return "A".equals(clampBean.getClampSide()) ? R.drawable.sx9_clamp_remind_a : R.drawable.sx9_clamp_remind_b;
                }
                if ("S9".equals(clampBean.getClampNum())) {
                    return "A".equals(clampBean.getClampSide()) ? R.drawable.s9_a_remind : R.drawable.s9_b_remind;
                }
                if (TextUtils.equals(clampBean.getClampNum(), "S10")) {
                    return R.drawable.s10_remind;
                }
            }
        }
        return 0;
    }

    public static int getClampZoomImgAlpha(KeyInfo keyInfo) {
        if (keyInfo.getIcCard() == 5590) {
            return R.drawable.sag_clamp_large;
        }
        ClampBean clampBean = keyInfo.getClampBean();
        if ("S1".equals(clampBean.getClampNum())) {
            if ("A".equals(clampBean.getClampSide())) {
                if ("0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? R.drawable.car_clamp_a_shoulder_large : R.drawable.car_clamp_a_tip_large;
                }
            } else {
                if ("B".equals(clampBean.getClampSide())) {
                    return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_b_shoulder_large : (keyInfo.getIcCard() == 1019 || keyInfo.getIcCard() == 1369 || keyInfo.getIcCard() == 1443) ? R.drawable.car_clamp_remind_b_tip_hu64 : R.drawable.car_clamp_b_tip_large : keyInfo.getAlign() == 0 ? R.drawable.car_clamp_b_shoulder_side_large : R.drawable.car_clamp_b_tip_side_large;
                }
                if ("C".equals(clampBean.getClampSide())) {
                    return "0".equals(clampBean.getClampSlot()) ? keyInfo.getAlign() == 0 ? R.drawable.car_clamp_c_shoulder_large : Integer.parseInt(keyInfo.getSpaceStr().split(";")[0].split(",")[0]) + 300 > 2650 ? R.drawable.car_clamp_c_long_tip_large : R.drawable.car_clamp_c_tip_large : R.drawable.car_clamp_c_down_tip_large;
                }
                if ("D".equals(clampBean.getClampSide()) && "0".equals(clampBean.getClampSlot())) {
                    return keyInfo.getAlign() == 0 ? keyInfo.getShoulderBlock() == 1 ? R.drawable.car_clamp_d_shoulder_6620131 : R.drawable.car_clamp_d_shoulder_large : keyInfo.isNewHonda() ? R.drawable.car_clamp_d_tip_honda_large : (keyInfo.getIcCard() == 20131 || keyInfo.getIcCard() == 1915) ? R.drawable.car_clamp_d_tip_20131_large : R.drawable.car_clamp_d_tip_large;
                }
            }
        } else {
            if ("S2".equals(clampBean.getClampNum())) {
                return "A".equals(clampBean.getClampSide()) ? keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_a_shoulder_large : R.drawable.singlekey_clamp_a_tip_large : keyInfo.getAlign() == 0 ? R.drawable.singlekey_clamp_b_shoulder_large : keyInfo.isNewHonda() ? R.drawable.singlekey_clamp_remind_b_tip_honda : R.drawable.singlekey_clamp_b_tip_large;
            }
            if ("S3".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return MachineInfo.isE20Us(MyApplication.getInstance()) ? R.drawable.tubular_clamp_large : R.drawable.tubular_clamp_large_s3_s7;
                }
            } else if ("S4".equals(clampBean.getClampNum())) {
                if ("A".equals(clampBean.getClampSide())) {
                    return R.drawable.anglekey_clamp_large;
                }
            } else {
                if ("S6".equals(clampBean.getClampNum())) {
                    return "A".equals(clampBean.getClampSide()) ? R.drawable.sx9_clamp_side_a_large : R.drawable.sx9_clamp_side_b_large;
                }
                if ("S9".equals(clampBean.getClampNum())) {
                    return "A".equals(clampBean.getClampSide()) ? R.drawable.s9_a_large : R.drawable.s9_b_large;
                }
                if (TextUtils.equals(clampBean.getClampNum(), "S10")) {
                    return R.drawable.s10_large;
                }
            }
        }
        return 0;
    }

    public static int getAlphaClearClampImg(KeyInfo keyInfo) {
        if (keyInfo.getClampBean().getClampNum().equals("S1")) {
            return keyInfo.getClampBean().getClampSide().equals("A") ? R.drawable.clear_clamp_s1_a : keyInfo.getClampBean().getClampSide().equals("B") ? R.drawable.clear_clamp_s1_b : keyInfo.getClampBean().getClampSide().equals("C") ? R.drawable.clear_clamp_s1_c : R.drawable.clear_clamp_s1_d;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S2")) {
            return keyInfo.getClampBean().getClampSide().equals("A") ? R.drawable.clear_clamp_s2_a : R.drawable.clear_clamp_s2_b;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S3")) {
            return R.drawable.clear_clamp_s3;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S4")) {
            return R.drawable.clear_clamp_s4;
        }
        if (keyInfo.getClampBean().getClampNum().equals("S6")) {
            return R.drawable.clear_clamp_s6;
        }
        return 0;
    }
}
