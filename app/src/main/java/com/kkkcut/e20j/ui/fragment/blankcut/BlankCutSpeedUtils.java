package com.kkkcut.e20j.ui.fragment.blankcut;

import com.kkkcut.e20j.SpKeys;
import com.kkkcut.e20j.androidquick.tool.SPUtils;

/* loaded from: classes.dex */
public class BlankCutSpeedUtils {
    public static void saveSpeed(BlankCutType blankCutType, int i) {
        SPUtils.put(SpKeys.BLANK_CUT_SPEED + blankCutType.getFlag(), i);
    }

    public static int getSpeed(BlankCutType blankCutType) {
        return SPUtils.getInt(SpKeys.BLANK_CUT_SPEED + blankCutType.getFlag(), 10);
    }
}
