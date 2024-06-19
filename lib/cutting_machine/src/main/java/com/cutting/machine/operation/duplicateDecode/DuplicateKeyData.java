package com.liying.core.operation.duplicateDecode;

import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.KeyType;
import com.liying.core.clamp.Clamp;
import com.liying.core.communication.KeyInfoBase;
import com.liying.core.duplicate.DecodeData;

/* loaded from: classes2.dex */
public class DuplicateKeyData implements KeyInfoBase {
    private KeyAlign align;
    private Clamp clamp;
    private int clampMode;
    private int cutDepth;
    private DecodeData decodeData;
    private int decodeLength;
    private int decodeWidth;
    private int side;
    private KeyType type;

    public int getExtDoublekeyDepth() {
        return 0;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getExtDoublekeyDepthSteps() {
        return 0;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getShoulderBlock() {
        return 0;
    }

    public int getThick() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public boolean isNewHonda() {
        return false;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public KeyType getKeyType() {
        return this.type;
    }

    public void setType(KeyType keyType) {
        this.type = keyType;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public KeyAlign getKeyAlign() {
        return this.align;
    }

    public void setAlign(KeyAlign keyAlign) {
        this.align = keyAlign;
    }

    public Clamp getClamp() {
        return this.clamp;
    }

    public void setClamp(Clamp clamp) {
        this.clamp = clamp;
    }

    public int getClampMode() {
        return this.clampMode;
    }

    public void setClampMode(int i) {
        this.clampMode = i;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getSide() {
        return this.side;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getKeyWidthSteps() {
        return getDecodeWidth();
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getThickSteps() {
        return getThick();
    }

    public DecodeData getDecodeData() {
        return this.decodeData;
    }

    public void setDecodeData(DecodeData decodeData) {
        this.decodeData = decodeData;
    }

    @Override // com.liying.core.communication.KeyInfoBase
    public int getLengthSteps() {
        return getLength();
    }

    public int getCutDepth() {
        return this.cutDepth;
    }

    public void setCutDepth(int i) {
        this.cutDepth = i;
    }

    public int getLength() {
        return this.decodeLength;
    }

    public void setDecodeLength(int i) {
        this.decodeLength = i;
    }

    public void setSide(int i) {
        this.side = i;
    }

    public int getDecodeWidth() {
        return this.decodeWidth;
    }

    public void setDecodeWidth(int i) {
        this.decodeWidth = i;
    }
}
