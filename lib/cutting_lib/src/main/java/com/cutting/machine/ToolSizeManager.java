package com.cutting.machine;

import com.cutting.machine.clamp.MachineData;

/* loaded from: classes2.dex */
public class ToolSizeManager {
    public static int DimpleCutterAngle = 90;
    public static int DimpleCutterSize = 80;
    private static int cutterSize = 200;
    private static int decoderSize = 100;

    public static int getDecoderSize() {
        return decoderSize;
    }

    public static void setDecoderSize(int i) {
        if (i == 0) {
            return;
        }
        decoderSize = i;
    }

    public static int getDecoderRadius() {
        return decoderSize / 2;
    }

    public static int getDecoderSize2() {
        return Math.round(decoderSize / MachineData.dXScale);
    }

    public static int getDecoderRadius2() {
        return Math.round(decoderSize / (MachineData.dXScale * 2.0f));
    }

    public static int getCutterSize() {
        return cutterSize;
    }

    public static void setCutterSize(int i) {
        cutterSize = i;
    }

    public static int getCutterRadiusSize() {
        return cutterSize / 2;
    }

    public static int getCutterSize2() {
        return Math.round(cutterSize / MachineData.dXScale);
    }

    public static int getCutterRadiusSize2() {
        return Math.round(cutterSize / (MachineData.dXScale * 2.0f));
    }
}
