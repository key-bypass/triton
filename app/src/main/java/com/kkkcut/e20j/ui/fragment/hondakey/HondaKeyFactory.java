package com.kkkcut.e20j.ui.fragment.hondakey;

import com.liying.core.bean.ClampBean;
import com.liying.core.bean.KeyInfo;

/* loaded from: classes.dex */
public class HondaKeyFactory {
    public static KeyInfo createHondaSideKey(int i, int i2) {
        if (i == 2020) {
            return createHondaSideKey2020(i2);
        }
        return createHondaSideKey2021(i2);
    }

    private static KeyInfo createHondaSideKey2021(int i) {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType(3);
        keyInfo.setAlign(1);
        keyInfo.setSpaceStr("2500,300");
        keyInfo.setSpaceWidthStr("100,100");
        keyInfo.setWidth(150);
        keyInfo.setCutDepth(i == 0 ? 130 : 20);
        keyInfo.setGuide(40);
        keyInfo.setDepthStr("40");
        keyInfo.setDepthName("1");
        keyInfo.setThick(700);
        keyInfo.setSide(0);
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum("S1");
        clampBean.setClampSide("B");
        clampBean.setClampSlot("1");
        keyInfo.setClampKeyBasicData(clampBean);
        return keyInfo;
    }

    public static KeyInfo createHondaSideKey2020(int i) {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType(3);
        keyInfo.setAlign(1);
        if (i == 0) {
            keyInfo.setSpaceStr("2800,300");
        } else {
            keyInfo.setSpaceStr("2500,300");
        }
        keyInfo.setSpaceWidthStr("100,100");
        keyInfo.setWidth(150);
        keyInfo.setCutDepth(i == 0 ? 165 : 55);
        keyInfo.setGuide(50);
        keyInfo.setDepthStr("50");
        keyInfo.setDepthName("1");
        keyInfo.setThick(900);
        keyInfo.setSide(0);
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum("S1");
        clampBean.setClampSide("B");
        clampBean.setClampSlot("1");
        keyInfo.setClampKeyBasicData(clampBean);
        return keyInfo;
    }
}
