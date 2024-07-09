package com.cutting.machine.clamp;

import android.text.TextUtils;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;

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
                switch (clampSide) {
                    case "A", "B" -> {
                        return Clamp.E9S1A;
                    }
                    case "C", "D" -> {
                        return Clamp.E9S1C;
                    }
                }
            } else {
                switch (clampNum) {
                    case "S2" -> {
                        if (clampBean.getClampSide().equals("A")) {
                            return Clamp.E9S2A;
                        }
                        return Clamp.E9S2B;
                    }
                    case "S3" -> {
                        return Clamp.E9S3;
                    }
                    case "S4" -> {
                        return Clamp.E9S4;
                    }
                }
            }
        } else if (clampNum.equals("S1")) {
            switch (clampSide) {
                case "A" -> {
                    return Clamp.S1_A;
                }
                case "B" -> {
                    return Clamp.S1_B;
                }
                case "C" -> {
                    return Clamp.S1_C;
                }
                case "D" -> {
                    return Clamp.S1_D;
                }
            }
        } else {
            switch (clampNum) {
                case "S2" -> {
                    if (clampBean.getClampSide().equals("A")) {
                        return Clamp.S2_A;
                    }
                    return Clamp.S2_B;
                }
                case "S3" -> {
                    return Clamp.S3;
                }
                case "S4" -> {
                    return Clamp.S4;
                }
                case "S6" -> {
                    return Clamp.S6;
                }
                case "S9" -> {
                    return switch (clampBean.getClampSide()) {
                        case "A" -> Clamp.S9_A;
                        case "B" -> Clamp.S9_B;
                        case "C" -> Clamp.S9_C;
                        default -> Clamp.S9_D;
                    };
                }
                case "S10" -> {
                    return Clamp.S10;
                }
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
