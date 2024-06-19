package com.liying.core.clamp;

import com.liying.core.Command;
import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.SerialResBean;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.utils.UnitConvertUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class E9S2A extends ClampF {
    private int leftY;
    private int shoulderDiff;
    private int tipX;

    public E9S2A() {
    }

    public E9S2A(List<SerialResBean> list) {
        setShoulderDiff(Math.abs(list.get(2).getX() - list.get(3).getX()) - ToolSizeManager.getDecoderSize2());
        setTipX(list.get(3).getX() + ToolSizeManager.getDecoderRadius2());
        setLeftY(list.get(1).getY() + ToolSizeManager.getDecoderRadius2());
    }

    public int getDeep() {
        return UnitConvertUtil.cmm2StepY(ErrorCode.keyCuttingError);
    }

    public int getShoulderDiff() {
        return this.shoulderDiff;
    }

    public void setShoulderDiff(int i) {
        this.shoulderDiff = i;
    }

    public int getTipX() {
        return this.tipX;
    }

    public void setTipX(int i) {
        this.tipX = i;
    }

    public int getLeftY() {
        return this.leftY;
    }

    public void setLeftY(int i) {
        this.leftY = i;
    }

    public int getShouldBlockThick() {
        return UnitConvertUtil.cmm2StepX(150);
    }

    @Override // com.liying.core.clamp.ClampF
    public byte[] getCommand() {
        return Command.WriteSpecifyLocationData(1, getShoulderDiff() + ":" + getDeep() + ":" + getTipX() + ":" + getLeftY());
    }

    public boolean haveRiskCutClamp(KeyAlignInfo keyAlignInfo, int i, int i2, int i3) {
        if (i2 <= keyAlignInfo.getLeftCutter() - UnitConvertUtil.cmm2StepY(ToolSizeManager.getCutterRadiusSize() + 10)) {
            return false;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampS2a);
        return true;
    }
}
