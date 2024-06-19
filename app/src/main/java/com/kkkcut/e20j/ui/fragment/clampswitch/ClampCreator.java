package com.kkkcut.e20j.ui.fragment.clampswitch;

import com.liying.core.CuttingMachine;
import com.liying.core.bean.ClampBean;
import com.liying.core.bean.KeyInfo;
import java.util.List;

/* loaded from: classes.dex */
public class ClampCreator {
    public static int getDrawableRes(KeyInfo keyInfo) {
        if (CuttingMachine.getInstance().isE9()) {
            return E9Clamp.getDrawableResE9(keyInfo.getClampBean(), keyInfo);
        }
        return AlphaClamp.getDrawableResAlpha(keyInfo.getClampBean(), keyInfo);
    }

    public static int getClampBigImg(KeyInfo keyInfo) {
        if (CuttingMachine.getInstance().isE9()) {
            return E9Clamp.getClampBigImgE9(keyInfo);
        }
        return AlphaClamp.getClampBigImgAlphaBeta(keyInfo);
    }

    public static List<ClampDisplayBean> getClampBeanList(KeyInfo keyInfo, ClampBean clampBean) {
        if (CuttingMachine.getInstance().isE9()) {
            return E9Clamp.getClampBeanListE9(keyInfo, clampBean);
        }
        return AlphaClamp.getClampBeanListAlphaBeta(keyInfo, clampBean);
    }

    public static int getClearClampImg(KeyInfo keyInfo) {
        if (CuttingMachine.getInstance().isE9()) {
            return E9Clamp.getE9ClearClampImg(keyInfo);
        }
        return AlphaClamp.getAlphaClearClampImg(keyInfo);
    }

    public static int getClampZoomImg(KeyInfo keyInfo) {
        if (CuttingMachine.getInstance().isE9()) {
            return E9Clamp.getClampZoomImgE9(keyInfo);
        }
        return AlphaClamp.getClampZoomImgAlpha(keyInfo);
    }
}
