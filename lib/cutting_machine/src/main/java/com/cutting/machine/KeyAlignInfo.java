package com.liying.core;

import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.DecoderCutterDistance;
import com.liying.core.utils.UnitConvertUtil;

/* loaded from: classes2.dex */
public class KeyAlignInfo {
    private int clampDownFaceY1;
    private int clampDownFaceY2;
    private int clampDownFaceZ1;
    private int clampDownFaceZ2;
    private int clampFace;
    private int cutDepth;
    private int keyFace;
    private int left;
    private int right;
    private int shoulder;
    private int shoulder1;
    private int slopeX;
    private int slopeY;

    /* renamed from: tc */
    private int f364tc;
    private int tdX;
    private int tdY;
    private int tip;
    private int tlX;
    private int tlY;
    private int trX;
    private int trY;

    public void clear() {
    }

    public int getTc() {
        return this.f364tc;
    }

    public void setTc(int i) {
        this.f364tc = i;
    }

    public int getTlX() {
        return this.tlX;
    }

    public void setTlX(int i) {
        this.tlX = i;
    }

    public int getTrX() {
        return this.trX;
    }

    public void setTrX(int i) {
        this.trX = i;
    }

    public int getKeyFace() {
        return this.keyFace;
    }

    public void setKeyFace(int i) {
        this.keyFace = i;
    }

    public int getClampFace() {
        return this.clampFace;
    }

    public void setClampFace(int i) {
        this.clampFace = i;
    }

    public int getLeft() {
        return this.left;
    }

    public void setLeft(int i) {
        this.left = i;
    }

    public int getRight() {
        return this.right;
    }

    public void setRight(int i) {
        this.right = i;
    }

    public int getTip() {
        return this.tip;
    }

    public void setTip(int i) {
        this.tip = i;
    }

    public int getShoulder() {
        return this.shoulder;
    }

    public void setShoulder(int i) {
        this.shoulder = i;
    }

    public int getCenter() {
        return (getLeft() + getRight()) / 2;
    }

    public int getClampDownFaceZ1() {
        return this.clampDownFaceZ1;
    }

    public void setClampDownFaceZ1(int i) {
        this.clampDownFaceZ1 = i;
    }

    public int getClampDownFaceZ2() {
        return this.clampDownFaceZ2;
    }

    public void setClampDownFaceZ2(int i) {
        this.clampDownFaceZ2 = i;
    }

    public int getClampDownFaceY1() {
        return this.clampDownFaceY1;
    }

    public void setClampDownFaceY1(int i) {
        this.clampDownFaceY1 = i;
    }

    public int getClampDownFaceY2() {
        return this.clampDownFaceY2;
    }

    public void setClampDownFaceY2(int i) {
        this.clampDownFaceY2 = i;
    }

    public int getDecodeWidth() {
        return UnitConvertUtil.step2CmmKeyY(Math.abs(getRight() - getLeft())) - ToolSizeManager.getDecoderSize();
    }

    public int getDecodeWidth2() {
        return Math.abs(getRight() - getLeft()) - ToolSizeManager.getDecoderSize2();
    }

    public int getDecodeLength2() {
        return Math.abs(getShoulder() - getTip());
    }

    public int getCutDepth() {
        return this.cutDepth;
    }

    public void setCutDepth(int i) {
        this.cutDepth = i;
    }

    public int getTdY() {
        return this.tdY;
    }

    public void setTdY(int i) {
        this.tdY = i;
    }

    public int getShoulder1() {
        return this.shoulder1;
    }

    public void setShoulder1(int i) {
        this.shoulder1 = i;
    }

    public String toString() {
        return "KeyAlignInfo{keyFace=" + this.keyFace + ", clampFace=" + this.clampFace + ", left=" + this.left + ", right=" + this.right + ", tip=" + this.tip + ", shoulder=" + this.shoulder + ", cutDepth=" + this.cutDepth + ", center=" + getCenter() + ", centerCutter=" + getKeyCenterCutter() + '}';
    }

    public int getLeftCutter() {
        int left;
        int i;
        if (CuttingMachine.getInstance().isE9()) {
            left = getLeft() + ToolSizeManager.getDecoderRadius2();
            i = ClampManager.getInstance().getDC().getyDistance();
        } else {
            left = getLeft() - ToolSizeManager.getDecoderRadius2();
            i = ClampManager.getInstance().getDC().getxDistance();
        }
        return left + i;
    }

    public int getRightCutter() {
        int right;
        int i;
        if (CuttingMachine.getInstance().isE9()) {
            right = getRight() - ToolSizeManager.getDecoderRadius2();
            i = ClampManager.getInstance().getDC().getyDistance();
        } else {
            right = getRight() + ToolSizeManager.getDecoderRadius2();
            i = ClampManager.getInstance().getDC().getxDistance();
        }
        return right + i;
    }

    public int getTipCutter() {
        int tip;
        int i;
        if (CuttingMachine.getInstance().isE9()) {
            tip = getTip();
            i = ClampManager.getInstance().getDC().getxDistance();
        } else {
            tip = getTip();
            i = ClampManager.getInstance().getDC().getyDistance();
        }
        return tip + i;
    }

    public int getShoulderCutter() {
        int shoulder;
        int i;
        if (CuttingMachine.getInstance().isE9()) {
            shoulder = getShoulder();
            i = ClampManager.getInstance().getDC().getxDistance();
        } else {
            shoulder = getShoulder();
            i = ClampManager.getInstance().getDC().getyDistance();
        }
        return shoulder + i;
    }

    public int getShoulder1Cutter() {
        return getShoulder1() + ClampManager.getInstance().getDC().getyDistance();
    }

    public int getKeyCenterCutter() {
        int center;
        int i;
        DecoderCutterDistance dc = ClampManager.getInstance().getDC();
        if (dc == null) {
            return 0;
        }
        if (CuttingMachine.getInstance().isE9()) {
            center = getCenter();
            i = dc.getyDistance();
        } else {
            center = getCenter();
            i = dc.getxDistance();
        }
        return center + i;
    }

    public int getTlY() {
        return this.tlY;
    }

    public void setTlY(int i) {
        this.tlY = i;
    }

    public int getTrY() {
        return this.trY;
    }

    public void setTrY(int i) {
        this.trY = i;
    }

    public int getTdX() {
        return this.tdX;
    }

    public void setTdX(int i) {
        this.tdX = i;
    }

    public int getSlopeX() {
        return this.slopeX;
    }

    public int getSlopeCutterX() {
        return this.slopeX + ClampManager.getInstance().getDC().getxDistance();
    }

    public void setSlopeX(int i) {
        this.slopeX = i;
    }

    public int getSlopeY() {
        return this.slopeY;
    }

    public void setSlopeY(int i) {
        this.slopeY = i;
    }
}
