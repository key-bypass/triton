package com.cutting.machine.speed;

import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.error.ErrorCode;

/* loaded from: classes2.dex */
public class Speed_Beta {
    public static final int[] SpeedXYZOrigin = {12000, 12000, 4000};
    public static final int[] SpindleTurnOff_Move = {10000, 10000, 3000};
    public static final int[] SpindleTurnOn_Move = {8000, 8000, 3000};
    public static final int[] SpindleTurnOff_ZUp = {3000, 3000, MachineData.s1dShoulderDis};
    public static final int[] SpindleTurnOn_ZUp = {1000, 1000, MachineData.s1dShoulderDis};
    public static final int[] SpindleTurnOff_ZDown = {3000, 3000, MachineData.s1dShoulderDis};
    public static final int[] SpindleTurnOn_ZDown = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, 1000};
    public static final int[] CuttingIn = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, ErrorCode.keyDecodeFailed};
    public static final int[] CuttingInDimple = {MachineData.s1dShoulderDis, MachineData.s1dShoulderDis, 200};
    public static final int[] CuttingOut = {700, 700, ErrorCode.keyDecodeFailed};
    public static final int[] CuttingTurn = {ErrorCode.keyCuttingError, ErrorCode.keyCuttingError, ErrorCode.keyDecodeFailed};
    public static final int[] CuttingSharpen = {800, 800, 800};
}
