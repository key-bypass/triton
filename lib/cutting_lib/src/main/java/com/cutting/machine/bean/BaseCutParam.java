package com.cutting.machine.bean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.operation.BaseParam;
import com.cutting.machine.utils.UnitConvertUtil;

/* loaded from: classes2.dex */
public abstract class BaseCutParam extends BaseParam {
    protected Clamp clamp;
    protected int clampMode;
    protected int cutDepth;
    protected int cutSpeed;
    protected int cutterSize;
    protected int decoderSize;
    protected boolean highHandleMode;
    protected int layer;
    protected int singleSideKeyCutDepthFix;

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

    public int getCutDepth() {
        return this.cutDepth;
    }

    public void setCutDepth(int i) {
        this.cutDepth = i;
    }

    public int getDecoderSize() {
        return this.decoderSize;
    }

    public int getDecoderRadius() {
        return this.decoderSize / 2;
    }

    public int getDecoderSize2() {
        return UnitConvertUtil.cmm2StepX(this.decoderSize);
    }

    public int getDecoderRadius2() {
        return UnitConvertUtil.cmm2StepX(this.decoderSize / 2);
    }

    public int getCutterRadiusSize() {
        return this.cutterSize / 2;
    }

    public int getCutterSize2() {
        return UnitConvertUtil.cmm2StepX(this.cutterSize);
    }

    public int getCutterRadiusSize2() {
        return UnitConvertUtil.cmm2StepX(this.cutterSize / 2);
    }

    public void setDecoderSize(int i) {
        this.decoderSize = i;
    }

    public int getCutterSize() {
        return this.cutterSize;
    }

    public void setCutterSize(int i) {
        this.cutterSize = i;
    }

    public int getCutSpeed() {
        return this.cutSpeed;
    }

    public void setCutSpeed(int i) {
        this.cutSpeed = i;
    }

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int i) {
        this.layer = i;
    }

    public boolean isHighHandleMode() {
        return this.highHandleMode;
    }

    public void setHighHandleMode(boolean z) {
        this.highHandleMode = z;
    }

    public int getSingleSideKeyCutDepthFix() {
        return this.singleSideKeyCutDepthFix;
    }

    public void setSingleSideKeyCutDepthFix(int i) {
        this.singleSideKeyCutDepthFix = i;
    }
}