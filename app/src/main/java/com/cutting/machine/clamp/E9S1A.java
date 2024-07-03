package com.cutting.machine.clamp;

import com.cutting.machine.Command;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.SerialResBean;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorHelper;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.List;

/* loaded from: classes2.dex */
public class E9S1A extends ClampF {
    private int centerY;
    private int high1;
    private int high2;
    private int rightY;
    private int shoulderDiff;
    private int startX;
    private int tipX;

    public E9S1A() {
    }

    public E9S1A(List<SerialResBean> list) {
        setCenterY((list.get(4).getY() + list.get(5).getY()) / 2);
        setHigh1(Math.abs(list.get(0).getZ() - list.get(3).getZ()));
        setHigh2(Math.abs(list.get(0).getZ() - list.get(9).getZ()));
        setTipX(list.get(2).getX() + ToolSizeManager.getDecoderRadius2());
        setStartX(list.get(1).getX() + ToolSizeManager.getDecoderRadius2());
        setShoulderDiff(Math.abs(list.get(10).getX() - list.get(11).getX()) - ToolSizeManager.getDecoderSize2());
        setRightY(list.get(5).getY() - ToolSizeManager.getDecoderRadius2());
    }

    public int getShoulderDiff() {
        return this.shoulderDiff;
    }

    public void setShoulderDiff(int i) {
        this.shoulderDiff = i;
    }

    public int getHigh1() {
        return this.high1;
    }

    public void setHigh1(int i) {
        this.high1 = i;
    }

    public int getHigh2() {
        return this.high2;
    }

    public void setHigh2(int i) {
        this.high2 = i;
    }

    public int getTipX() {
        return this.tipX;
    }

    public void setTipX(int i) {
        this.tipX = i;
    }

    public int getStartX() {
        return this.startX;
    }

    public void setStartX(int i) {
        this.startX = i;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public void setCenterY(int i) {
        this.centerY = i;
    }

    public int getStartXToShoulderDistance() {
        return UnitConvertUtil.cmm2StepX(2700);
    }

    public int getShoulderDiffDown() {
        return UnitConvertUtil.cmm2StepX(3000);
    }

    public int getRightY() {
        return this.rightY;
    }

    public void setRightY(int i) {
        this.rightY = i;
    }

    public String toString() {
        return "E9S1A{shoulderDiff=" + this.shoulderDiff + ", high1=" + this.high1 + ", high2=" + this.high2 + ", tipX=" + this.tipX + ", startX=" + this.startX + ", centerY=" + this.centerY + '}';
    }

    @Override // com.cutting.machine.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(65, getShoulderDiff() + ":" + getHigh1() + ":" + getHigh2() + ":" + getTipX() + ":" + getStartX() + ":" + getCenterY() + ":" + getRightY());
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        int clampFace = keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(10);
        int shoulderCutter = keyAlignInfo.getShoulderCutter();
        int leftCutter = keyAlignInfo.getLeftCutter();
        int rightCutter = keyAlignInfo.getRightCutter();
        if (i3 >= getHigh1() + clampFace) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1a);
            return true;
        }
        if (i3 < clampFace || i < ((shoulderCutter - ((int) (10.0f / MachineData.dYScale))) - getStartXToShoulderDistance()) - ToolSizeManager.getCutterRadiusSize2()) {
            return false;
        }
        if (i2 < leftCutter + ToolSizeManager.getCutterRadiusSize2() + ((int) (10.0f / MachineData.dXScale))) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
            return true;
        }
        if (i2 > (rightCutter - ToolSizeManager.getCutterRadiusSize2()) - ((int) (10.0f / MachineData.dXScale))) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
            return true;
        }
        return false;
    }
}
