package com.cutting.machine.speed;

import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.error.ErrorCode;

/* loaded from: classes2.dex */
public class Speed_Alpha {
    public static final int[] SpeedXYZOrigin = {24000, 24000, 8000};
    public static final int[] SpindleTurnOff_Move = {20000, 20000, 6000};
    public static final int[] SpindleTurnOn_Move = {16000, 16000, 3000};
    public static final int[] SpindleTurnOff_ZUp = {6000, 6000, 4000};
    public static final int[] SpindleTurnOn_ZUp = {MachineData.s1dShoulderDis, MachineData.s1dShoulderDis, 4000};
    public static final int[] SpindleTurnOff_ZDown = {6000, 6000, 4000};
    public static final int[] SpindleTurnOn_ZDown = {1000, 1000, MachineData.s1dShoulderDis};
    public static final int[] CuttingIn = {1000, 1000, 800};
    public static final int[] CuttingInDimple = {4000, 4000, ErrorCode.keyDecodeFailed};
    public static final int[] CuttingOut = {1000, 1000, 800};
    public static final int[] CuttingTurn = {1000, 1000, 800};
    public static final int[] CuttingSharpen = {1600, 1600, 1600};
    public static final int[] EngravingCutting = {3000, 3000, MachineData.s1dShoulderDis};
}
