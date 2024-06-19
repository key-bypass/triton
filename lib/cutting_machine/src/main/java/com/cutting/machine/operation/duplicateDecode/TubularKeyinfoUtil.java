package com.liying.core.operation.duplicateDecode;

import com.liying.core.bean.KeyInfo;

/* loaded from: classes2.dex */
public class TubularKeyinfoUtil {
    public static KeyInfo getTubularKey() {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType(8);
        keyInfo.setAlign(0);
        keyInfo.setWidth(980);
        keyInfo.setThick(95);
        keyInfo.setSpaceStr("4500,9000,13500,18000,22500,27000,31500;");
        keyInfo.setDepthStr("38,78,116,157,195,236,276;");
        keyInfo.setDepthName("1,2,3,4,5,6,7;");
        keyInfo.setCutDepth(85);
        keyInfo.setType_specific_info("");
        return keyInfo;
    }
}
