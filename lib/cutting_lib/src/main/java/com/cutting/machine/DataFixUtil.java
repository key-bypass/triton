package com.cutting.machine;

import com.cutting.machine.clamp.MachineData;

/* loaded from: classes2.dex */
public class DataFixUtil {
    public static int getFixZ(KeyAlignInfo keyAlignInfo, int i) {
        int i2 = (int) (0.0f / MachineData.dZScale);
        int clampDownFaceY2 = keyAlignInfo.getClampDownFaceY2() - keyAlignInfo.getClampDownFaceY1();
        return clampDownFaceY2 == 0 ? i2 : Math.round((((keyAlignInfo.getClampDownFaceZ2() - keyAlignInfo.getClampDownFaceZ1()) * 1.0f) / clampDownFaceY2) * (i - keyAlignInfo.getClampDownFaceY2())) + i2;
    }
}
