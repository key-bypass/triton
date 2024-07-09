package com.cutting.machine.clamp;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.utils.UnitConvertUtil;

/* loaded from: classes2.dex */
public class E9S3 extends ClampF {
    @Override // com.cutting.machine.clamp.ClampF
    public byte[] getCommand() {
        return new byte[0];
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        return i3 >= keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
    }
}
