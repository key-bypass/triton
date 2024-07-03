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
public class E9S1C extends ClampF {

    /* renamed from: k */
    private float f404k;
    private int leftThick;
    private int rightThick;
    private int shoulderDiff;
    private int tipX;

    public E9S1C() {
    }

    public E9S1C(List<SerialResBean> list) {
        setShoulderDiff(UnitConvertUtil.cmm2StepX(2900));
        setLeftThick(Math.abs(list.get(2).getY() - list.get(3).getY()) - ToolSizeManager.getDecoderSize2());
        setRightThick(Math.abs(list.get(4).getY() - list.get(5).getY()) - ToolSizeManager.getDecoderSize2());
        setTipX(list.get(1).getX() + ToolSizeManager.getDecoderRadius2());
        SerialResBean serialResBean = list.get(7);
        SerialResBean serialResBean2 = list.get(8);
        setK((serialResBean2.getY() - (serialResBean.getY() * 1.0f)) / (serialResBean2.getX() - serialResBean.getX()));
    }

    public int getShoulderDiff() {
        return this.shoulderDiff;
    }

    public void setShoulderDiff(int i) {
        this.shoulderDiff = i;
    }

    public int getLeftThick() {
        return this.leftThick;
    }

    public void setLeftThick(int i) {
        this.leftThick = i;
    }

    public int getRightThick() {
        return this.rightThick;
    }

    public void setRightThick(int i) {
        this.rightThick = i;
    }

    public float getK() {
        return this.f404k;
    }

    public void setK(float f) {
        this.f404k = f;
    }

    public int getTipX() {
        return this.tipX;
    }

    public void setTipX(int i) {
        this.tipX = i;
    }

    public String toString() {
        return "E9S1C{shoulderDiff=" + this.shoulderDiff + ", leftThick=" + this.leftThick + ", rightThick=" + this.rightThick + ", tipX=" + this.tipX + ", k=" + this.f404k + '}';
    }

    @Override // com.cutting.machine.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(67, getShoulderDiff() + ":" + getLeftThick() + ":" + getRightThick() + ":" + getTipX() + ":" + getK());
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        if (i3 > keyAlignInfo.getClampFace() - UnitConvertUtil.cmm2StepZ(50)) {
            ErrorHelper.handleError(-4);
            return true;
        }
        if (i <= keyAlignInfo.getShoulderCutter() - UnitConvertUtil.cmm2StepX(ToolSizeManager.getCutterRadiusSize() + 10)) {
            return false;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1cShouldAlign);
        return true;
    }
}
