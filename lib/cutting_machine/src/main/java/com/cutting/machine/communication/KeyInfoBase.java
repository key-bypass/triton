package com.liying.core.communication;

import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.KeyType;

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
