package com.liying.core.clamp;

/* loaded from: classes2.dex */
public enum Clamp {
    S1_A("S1-A"),
    S1_B("S1-B"),
    S1_B_D("S1-B-D"),
    S1_C("S1-C"),
    S1_D("S1-D"),
    D_C("D-C"),
    D_C_S2_A("D-C(S2-A)"),
    S2_A("S2-A"),
    S2_B("S2-B"),
    S3("S3"),
    S4("S4"),
    S5("S5"),
    S6("S6"),
    S7("S7"),
    S8("S8"),
    S9_A("S9-A"),
    S9_B("S9-B"),
    S9_C("S9_C"),
    S9_D("S9-D"),
    S10("S10"),
    E9S1A("E9-S1-A"),
    E9S1C("E9-S1-C"),
    E9S2A("E9-S2-A"),
    E9S2B("E9-S2-B"),
    E9S3("E9-S3"),
    E9S4("E9-S4"),
    E9Honda("E9-Honda"),
    E9S5("E9-S5");

    private final String clampStr;

    Clamp(String str) {
        this.clampStr = str;
    }

    public String getClampStr() {
        return this.clampStr;
    }
}
