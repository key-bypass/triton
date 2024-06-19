package com.liying.core.bean;

/* loaded from: classes2.dex */
public enum KeyType {
    DOUBLE_SIDE_KEY(0),
    SINGLE_SIDE_KEY(1),
    DOUBLE_INSIDE_GROOVE_KEY(2),
    SINGLE_OUTSIDE_GROOVE_KEY(3),
    DOUBLE_OUTSIDE_GROOVE_KEY(4),
    SINGLE_INSIDE_GROOVE_KEY(5),
    DIMPLE_KEY(6),
    ANGLE_KEY(7),
    TUBULAR_KEY(8),
    SIDE_TOOTH_KEY(9),
    SIDE_TOOTH_3KS_KEY(92),
    HONDA(100),
    TWO_GROOVE(101),
    THREE_GROOVE(102),
    SIDE_HOLE(103),
    LEFT_GROOVE(104),
    RIGHT_GROOVE(105);

    private int value;

    KeyType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
