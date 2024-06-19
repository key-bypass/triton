package com.liying.core.operation.duplicateDecode;

import com.liying.core.clamp.Clamp;
import com.liying.core.operation.BaseParam;

/* loaded from: classes2.dex */
public class DuplicateDecodeParams extends BaseParam {
    private Clamp clamp;
    private int clampMode;
    private int decodeSize;
    private int density;
    private DuplicateKeyData keyInfo;

    @Override // com.liying.core.communication.OperationParamBase
    public int getPauseTime() {
        return 0;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public boolean isPlasticKey() {
        return false;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public DuplicateKeyData getKeyInfo() {
        return this.keyInfo;
    }

    public void setKeyInfo(DuplicateKeyData duplicateKeyData) {
        this.keyInfo = duplicateKeyData;
    }

    public int getDecodeSize() {
        return this.decodeSize;
    }

    public void setDecodeSize(int i) {
        this.decodeSize = i;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public Clamp getClamp() {
        return this.clamp;
    }

    public void setClamp(Clamp clamp) {
        this.clamp = clamp;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public int getClampMode() {
        return this.clampMode;
    }

    public void setClampMode(int i) {
        this.clampMode = i;
    }

    public int getDensity() {
        return this.density;
    }

    public void setDensity(int i) {
        this.density = i;
    }

    public String toString() {
        return "DecodeParams{keyInfo=" + this.keyInfo + ", decodeSize=" + this.decodeSize + ", clamp=" + this.clamp + ", clampMode=" + this.clampMode + ", density=" + this.density + '}';
    }

    /* loaded from: classes2.dex */
    public static final class DecodeParamsBuilder {
        private Clamp clamp;
        private int clampMode;
        private int decodeSize;
        private int density;
        private DuplicateKeyData keyInfo;

        private DecodeParamsBuilder() {
        }

        public static DecodeParamsBuilder aDecodeParams() {
            return new DecodeParamsBuilder();
        }

        public DecodeParamsBuilder withKeyInfo(DuplicateKeyData duplicateKeyData) {
            this.keyInfo = duplicateKeyData;
            return this;
        }

        public DecodeParamsBuilder withDecodeSize(int i) {
            this.decodeSize = i;
            return this;
        }

        public DecodeParamsBuilder withClamp(Clamp clamp) {
            this.clamp = clamp;
            return this;
        }

        public DecodeParamsBuilder withClampMode(int i) {
            this.clampMode = i;
            return this;
        }

        public DecodeParamsBuilder withDensity(int i) {
            this.density = i;
            return this;
        }

        public DuplicateDecodeParams build() {
            DuplicateDecodeParams duplicateDecodeParams = new DuplicateDecodeParams();
            duplicateDecodeParams.setKeyInfo(this.keyInfo);
            duplicateDecodeParams.setDecodeSize(this.decodeSize);
            duplicateDecodeParams.setClamp(this.clamp);
            duplicateDecodeParams.setClampMode(this.clampMode);
            duplicateDecodeParams.setDensity(this.density);
            return duplicateDecodeParams;
        }
    }
}
