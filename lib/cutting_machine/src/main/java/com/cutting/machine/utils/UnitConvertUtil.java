package com.liying.core.utils;

import com.liying.core.CuttingMachine;
import com.liying.core.clamp.MachineData;

/* loaded from: classes2.dex */
public class UnitConvertUtil {
    public static int cmm2StepX(int i) {
        return (int) (i / MachineData.getXRatio());
    }

    public static int cmm2StepY(int i) {
        return (int) (i / MachineData.getYRatio());
    }

    public static int cmm2StepZ(int i) {
        return (int) (i / MachineData.getZRatio());
    }

    public static int cmm2StepXDirection(int i) {
        return ((int) (i / MachineData.getXRatio())) * MachineData.getXDirection();
    }

    public static int cmm2StepYDirection(int i) {
        return ((int) (i / MachineData.getYRatio())) * MachineData.getYDirection();
    }

    public static int cmm2StepZDirection(int i) {
        return ((int) (i / MachineData.getZRatio())) * MachineData.getZDirection();
    }

    public static int xKey2Machine(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return cmm2StepXDirection(i);
        }
        return cmm2StepYDirection(i);
    }

    public static int yKey2Machine(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return cmm2StepYDirection(i);
        }
        return cmm2StepXDirection(i);
    }

    public static int xKeyCmm2Steps(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return cmm2StepX(i);
        }
        return cmm2StepY(i);
    }

    public static int yKeyCmm2Steps(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return cmm2StepY(i);
        }
        return cmm2StepX(i);
    }

    public static int zKeyCmm2Steps(int i) {
        return cmm2StepZ(i);
    }

    public static int xKey2MachineDire(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return directionX(i);
        }
        return directionY(i);
    }

    public static int yKey2MachineDire(int i) {
        if (CuttingMachine.getInstance().isE9()) {
            return directionY(i);
        }
        return directionX(i);
    }

    public static int zKey2Machine(int i) {
        return cmm2StepZDirection(i);
    }

    public static int step2CmmX(int i) {
        return (int) (i * MachineData.getXRatio());
    }

    public static int step2CmmY(int i) {
        return (int) (i * MachineData.getYRatio());
    }

    public static int step2CmmKeyX(int i) {
        float f;
        float yRatio;
        if (CuttingMachine.getInstance().isE9()) {
            f = i;
            yRatio = MachineData.getXRatio();
        } else {
            f = i;
            yRatio = MachineData.getYRatio();
        }
        return (int) (f * yRatio);
    }

    public static int step2CmmKeyY(int i) {
        float f;
        float xRatio;
        if (CuttingMachine.getInstance().isE9()) {
            f = i;
            xRatio = MachineData.getYRatio();
        } else {
            f = i;
            xRatio = MachineData.getXRatio();
        }
        return (int) (f * xRatio);
    }

    public static int step2CmmZ(int i) {
        return (int) (i * MachineData.getZRatio());
    }

    public static int directionX(int i) {
        return i * MachineData.getXDirection();
    }

    public static int directionY(int i) {
        return i * MachineData.getYDirection();
    }

    public static int directionZ(int i) {
        return i * MachineData.getZDirection();
    }
}
