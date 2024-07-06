package com.cutting.machine.communication;

import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.KeyType;

/* loaded from: classes2.dex */
public interface KeyInfoBase {
    int getExtDoublekeyDepthSteps();

    KeyAlign getKeyAlign();

    KeyType getKeyType();

    int getKeyWidthSteps();

    int getLengthSteps();

    int getShoulderBlock();

    int getSide();

    int getThickSteps();

    boolean isNewHonda();
}