package com.liying.core.clamp;

import com.liying.core.KeyAlignInfo;
import com.liying.core.utils.UnitConvertUtil;

/* renamed from: com.liying.core.clamp.S3 */
/* loaded from: classes2.dex */
public class C1974S3 extends ClampF {
    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return new byte[0];
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        return keyAlignInfo.getClampFace() != 0 && i3 >= keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
    }
}
