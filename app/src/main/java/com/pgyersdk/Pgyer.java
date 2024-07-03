package com.pgyersdk;

import com.pgyersdk.p012c.Constants;
import com.pgyersdk.utils.LogUtils;
import com.pgyersdk.utils.Util;

/* loaded from: classes2.dex */
public class Pgyer {
    public static void setAppId(String str) {
        Constants.f474l = Util.m240c(str);
        if (Util.m236a()) {
            return;
        }
        LogUtils.m218b("PgyerSDK", "Please config correct AppId");
    }
}
