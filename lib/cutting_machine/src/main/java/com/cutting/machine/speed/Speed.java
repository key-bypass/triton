package com.liying.core.speed;

import com.liying.core.CuttingMachine;

/* loaded from: classes2.dex */
public class Speed {
    private static int userSpeed = 5;

    public static void setSpeed(int i) {
        userSpeed = i;
    }

    public static int getUserSpeed() {
        return userSpeed;
    }

    public static String getSpeedStr(int i, int i2, int i3) {
        if (i > 12000) {
            i = 12000;
        }
        if (i2 > 12000) {
            i2 = 12000;
        }
        if (i3 > 4000) {
            i3 = 4000;
        }
        return i + "," + i2 + "," + i3;
    }

    public static float getRatio() {
        float f;
        float f2;
        int userSpeed2 = getUserSpeed();
        if (!CuttingMachine.getInstance().isBeta()) {
            if (CuttingMachine.getInstance().isAlpha()) {
                f = userSpeed2;
                f2 = 0.07f;
            } else {
                f = userSpeed2;
                f2 = 0.06f;
            }
            return (f * f2) + 0.25f;
        }
        switch (userSpeed2) {
            case 1:
                return 0.6f;
            case 2:
                return 0.7f;
            case 3:
                return 0.8f;
            case 4:
                return 0.9f;
            case 5:
            default:
                return 1.0f;
            case 6:
                return 1.1f;
            case 7:
                return 1.2f;
            case 8:
                return 1.3f;
            case 9:
                return 1.4f;
            case 10:
                return 1.5f;
        }
    }

    public static String get_Speed_Origin() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpeedXYZOrigin;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpeedXYZOrigin;
        } else {
            iArr = Speed_Alpha.SpeedXYZOrigin;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOff_Move() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOff_Move;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOff_Move;
        } else {
            iArr = Speed_Alpha.SpindleTurnOff_Move;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOn_Move() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOn_Move;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOn_Move;
        } else {
            iArr = Speed_Alpha.SpindleTurnOn_Move;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOff_ZUp() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOff_ZUp;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOff_ZUp;
        } else {
            iArr = Speed_Alpha.SpindleTurnOff_ZUp;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOn_ZUp() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOn_ZUp;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOn_ZUp;
        } else {
            iArr = Speed_Alpha.SpindleTurnOn_ZUp;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOff_ZDown() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOff_ZDown;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOff_ZDown;
        } else {
            iArr = Speed_Alpha.SpindleTurnOff_ZDown;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_SpindleTurnOn_ZDown() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.SpindleTurnOn_ZDown;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.SpindleTurnOn_ZDown;
        } else {
            iArr = Speed_Alpha.SpindleTurnOn_ZDown;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_CuttingIn() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.CuttingIn;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.CuttingIn;
        } else {
            iArr = Speed_Alpha.CuttingIn;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_DimleCuttingIn() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.CuttingInDimple;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.CuttingInDimple;
        } else {
            iArr = Speed_Alpha.CuttingInDimple;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_CuttingOut() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.CuttingOut;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.CuttingOut;
        } else {
            iArr = Speed_Alpha.CuttingOut;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_CuttingTurn() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.CuttingTurn;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.CuttingTurn;
        } else {
            iArr = Speed_Alpha.CuttingTurn;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_CuttingSharpen() {
        int[] iArr;
        if (CuttingMachine.getInstance().isBeta()) {
            iArr = Speed_Beta.CuttingSharpen;
        } else if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.CuttingSharpen;
        } else {
            iArr = Speed_Alpha.CuttingSharpen;
        }
        return getSpeedStr((int) (iArr[0] * getRatio()), (int) (iArr[1] * getRatio()), iArr[2]);
    }

    public static String get_Speed_Engrave() {
        int[] iArr;
        int i = userSpeed;
        float f = i == 1 ? 0.5f : i == 25 ? 1.5f : 1.0f;
        if (CuttingMachine.getInstance().isE9()) {
            iArr = Speed_E9.EngravingCutting;
        } else {
            iArr = Speed_Alpha.EngravingCutting;
        }
        return getSpeedStr((int) (iArr[0] * f), (int) (iArr[1] * f), iArr[2]);
    }
}
