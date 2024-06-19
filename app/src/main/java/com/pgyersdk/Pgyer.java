package com.pgyersdk;

import com.pgyersdk.p012c.C2022a;
import com.pgyersdk.p016f.C2041f;
import com.pgyersdk.p016f.C2047l;

/* loaded from: classes2.dex */
public class Pgyer {
    public static void setAppId(String str) {
        C2022a.f474l = C2047l.m240c(str);
        if (C2047l.m236a()) {
            return;
        }
        C2041f.m218b("PgyerSDK", "Please config correct AppId");
    }
}
