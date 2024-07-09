package com.cutting.machine.speed;

import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.error.ErrorCode;

/* loaded from: classes2.dex */
public class Speed_E9 {
    public static final int[] SpeedXYZOrigin = {12000, 12000, 8000};
    public static final int[] SpindleTurnOff_Move = {10000, 10000, 6000};
    public static final int[] SpindleTurnOn_Move = {8000, 8000, 3000};
    public static final int[] SpindleTurnOff_ZUp = {3000, 3000, 4000};
    public static final int[] SpindleTurnOn_ZUp = {1000, 1000, 4000};
    public static final int[] SpindleTurnOff_ZDown = {3000, 3000, 4000};
    public static final int[] SpindleTurnOn_ZDown = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, MachineData.s1dShoulderDis};
    public static final int[] CuttingIn = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, 800};
    public static final int[] CuttingInDimple = {MachineData.s1dShoulderDis, MachineData.s1dShoulderDis, ErrorCode.keyDecodeFailed};
    public static final int[] CuttingOut = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, 800};
    public static final int[] CuttingTurn = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, 800};
    public static final int[] CuttingSharpen = {ErrorCode.keyDecodeFailed, ErrorCode.keyDecodeFailed, 1600};
    public static final int[] EngravingCutting = {1500, 1500, MachineData.s1dShoulderDis};
}
