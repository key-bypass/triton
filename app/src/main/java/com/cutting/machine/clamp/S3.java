package com.cutting.machine.clamp;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.utils.UnitConvertUtil;

/* renamed from: com.cutting.machine.clamp.S3 */
/* loaded from: classes2.dex */
public class S3 extends ClampF {
    @Override // com.cutting.machine.clamp.ClampF
    public byte[] getCommand() {
        return new byte[0];
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        return keyAlignInfo.getClampFace() != 0 && i3 >= keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
    }
}
