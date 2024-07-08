package com.kkkcut.e20j.ui.fragment.blankcut;

/* loaded from: classes.dex */
public enum BlankCutType {
    KEY_HEAD(1),
    THICKNESS(2),
    WIDTH(3),
    DRILLING(4),
    LEFT_GROOVE(5),
    RIGHT_GROOVE(6),
    KEY_TIP(7),
    CREATE_GROOVE(8),
    SIDE_GROOVE(9),
    HY18(10),
    K4080K(11),
    Toyota80K(12),
    k9ToLxp90(13),
    HY18R(14),
    KW16ToKW15(15),
    LXP90To80K(16),
    KW14ToKW15(17);

    private int flag;

    BlankCutType(int i) {
        this.flag = i;
    }

    public int getFlag() {
        return this.flag;
    }
}
