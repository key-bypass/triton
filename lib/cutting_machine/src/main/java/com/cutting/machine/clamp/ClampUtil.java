package com.liying.core.clamp;

import android.text.TextUtils;
import com.liying.core.CuttingMachine;
import com.liying.core.bean.ClampBean;
import com.liying.core.bean.KeyInfo;

/* loaded from: classes2.dex */
public class ClampUtil {
    public static Clamp getClamp(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean == null) {
            return null;
        }
        String clampNum = clampBean.getClampNum();
        String clampSide = clampBean.getClampSide();
        if (TextUtils.isEmpty(clampNum)) {
            return null;
        }
        if (CuttingMachine.getInstance().isE9()) {
            if (clampNum.equals("S1")) {
                if (keyInfo.isNewHonda()) {
                    return Clamp.E9Honda;
                }
                if (clampSide.equals("A")) {
                    return Clamp.E9S1A;
                }
                if (clampSide.equals("B")) {
                    return Clamp.E9S1A;
                }
                if (clampSide.equals("C")) {
                    return Clamp.E9S1C;
                }
                if (clampSide.equals("D")) {
                    return Clamp.E9S1C;
                }
            } else {
                if (clampNum.equals("S2")) {
                    if (clampBean.getClampSide().equals("A")) {
                        return Clamp.E9S2A;
                    }
                    return Clamp.E9S2B;
                }
                if (clampNum.equals("S3")) {
                    return Clamp.E9S3;
                }
                if (clampNum.equals("S4")) {
                    return Clamp.E9S4;
                }
            }
        } else if (clampNum.equals("S1")) {
            if (clampSide.equals("A")) {
                return Clamp.S1_A;
            }
            if (clampSide.equals("B")) {
                return Clamp.S1_B;
            }
            if (clampSide.equals("C")) {
                return Clamp.S1_C;
            }
            if (clampSide.equals("D")) {
                return Clamp.S1_D;
            }
        } else {
            if (clampNum.equals("S2")) {
                if (clampBean.getClampSide().equals("A")) {
                    return Clamp.S2_A;
                }
                return Clamp.S2_B;
            }
            if (clampNum.equals("S3")) {
                return Clamp.S3;
            }
            if (clampNum.equals("S4")) {
                return Clamp.S4;
            }
            if (clampNum.equals("S6")) {
                return Clamp.S6;
            }
            if (clampNum.equals("S9")) {
                if (clampBean.getClampSide().equals("A")) {
                    return Clamp.S9_A;
                }
                if (clampBean.getClampSide().equals("B")) {
                    return Clamp.S9_B;
                }
                if (clampBean.getClampSide().equals("C")) {
                    return Clamp.S9_C;
                }
                return Clamp.S9_D;
            }
            if (clampNum.equals("S10")) {
                return Clamp.S10;
            }
        }
        return null;
    }

    public static int getClampMode(KeyInfo keyInfo) {
        ClampBean clampBean = keyInfo.getClampBean();
        if (clampBean == null) {
            return 0;
        }
        String clampNum = clampBean.getClampNum();
        String clampSide = clampBean.getClampSide();
        String clampSlot = clampBean.getClampSlot();
        if (TextUtils.isEmpty(clampNum)) {
            return 0;
        }
        if (CuttingMachine.getInstance().isE9()) {
            if (clampNum.equals("S1") && clampSlot.equals("1")) {
                return 1;
            }
        } else {
            if (!clampNum.equals("S1")) {
                return (!clampNum.equals("S6") || clampBean.getClampSide().equals("A")) ? 0 : 1;
            }
            if (clampSide.equals("B")) {
                if (clampSlot.equals("1")) {
                    return 1;
                }
            } else if (clampSide.equals("C") && clampSlot.equals("1")) {
                return 1;
            }
        }
        return 0;
    }
}
