package com.liying.core.operation.duplicateCut;

import com.liying.core.bean.BaseCutParam;
import com.liying.core.clamp.Clamp;
import com.liying.core.operation.duplicateDecode.DuplicateKeyData;

/* loaded from: classes2.dex */
public class DuplicateCutParams extends BaseCutParam {
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

    /* loaded from: classes2.dex */
    public static final class DuplicateCutParamsBuilder {
        private Clamp clamp;
        private int clampMode;
        private int cutDepth;
        private int cutSpeed;
        private int cutterSize;
        private int decoderSize;
        private boolean highHandleMode;
        private DuplicateKeyData keyInfo;
        private int layer;
        private int singleSideKeyCutDepthFix;

        private DuplicateCutParamsBuilder() {
        }

        public static DuplicateCutParamsBuilder aDuplicateCutParams() {
            return new DuplicateCutParamsBuilder();
        }

        public DuplicateCutParamsBuilder withKeyInfo(DuplicateKeyData duplicateKeyData) {
            this.keyInfo = duplicateKeyData;
            return this;
        }

        public DuplicateCutParamsBuilder withClamp(Clamp clamp) {
            this.clamp = clamp;
            return this;
        }

        public DuplicateCutParamsBuilder withClampMode(int i) {
            this.clampMode = i;
            return this;
        }

        public DuplicateCutParamsBuilder withCutDepth(int i) {
            this.cutDepth = i;
            return this;
        }

        public DuplicateCutParamsBuilder withDecoderSize(int i) {
            this.decoderSize = i;
            return this;
        }

        public DuplicateCutParamsBuilder withCutterSize(int i) {
            this.cutterSize = i;
            return this;
        }

        public DuplicateCutParamsBuilder withCutSpeed(int i) {
            this.cutSpeed = i;
            return this;
        }

        public DuplicateCutParamsBuilder withLayer(int i) {
            this.layer = i;
            return this;
        }

        public DuplicateCutParamsBuilder highHandleMode(boolean z) {
            this.highHandleMode = z;
            return this;
        }

        public DuplicateCutParamsBuilder singleSideKeyCutDepthFix(int i) {
            this.singleSideKeyCutDepthFix = i;
            return this;
        }

        public DuplicateCutParams build() {
            DuplicateCutParams duplicateCutParams = new DuplicateCutParams();
            duplicateCutParams.keyInfo = this.keyInfo;
            duplicateCutParams.clamp = this.clamp;
            duplicateCutParams.cutterSize = this.cutterSize;
            duplicateCutParams.layer = this.layer;
            duplicateCutParams.cutDepth = this.cutDepth;
            duplicateCutParams.decoderSize = this.decoderSize;
            duplicateCutParams.clampMode = this.clampMode;
            duplicateCutParams.cutSpeed = this.cutSpeed;
            duplicateCutParams.highHandleMode = this.highHandleMode;
            duplicateCutParams.singleSideKeyCutDepthFix = this.singleSideKeyCutDepthFix;
            return duplicateCutParams;
        }
    }
}
