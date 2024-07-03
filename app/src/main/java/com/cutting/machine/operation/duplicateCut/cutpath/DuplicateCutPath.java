package com.cutting.machine.operation.duplicateCut.cutpath;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.communication.OperationManager;
import com.cutting.machine.duplicate.DecodeData;
import com.cutting.machine.duplicate.SinglePathData;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.List;

/* loaded from: classes2.dex */
public abstract class DuplicateCutPath {
    private final DuplicateCutParams cutParams;
    private final DecodeData decodeData;

    public DuplicateCutPath(DuplicateCutParams duplicateCutParams) {
        this.cutParams = duplicateCutParams;
        this.decodeData = duplicateCutParams.getKeyInfo().getDecodeData().m601clone();
    }

    public abstract int cutCount();

    public abstract List<StepBean> getCutPathSteps();

    public DuplicateKeyData getDuplicateKeyData() {
        return this.cutParams.getKeyInfo();
    }

    public List<SinglePathData> getPathDataList() {
        return this.decodeData.getPathDataList();
    }

    public DuplicateCutParams getCutParams() {
        return this.cutParams;
    }

    public Clamp getClamp() {
        return this.cutParams.getClamp();
    }

    public int getMaxCut() {
        float cutterSize2;
        float f;
        if (getCutterSize() == 100) {
            cutterSize2 = getCutterSize2();
            f = 0.9f;
        } else {
            cutterSize2 = getCutterSize2();
            f = 0.95f;
        }
        return (int) (cutterSize2 * f);
    }

    public int getCutterRadiusSize2() {
        return UnitConvertUtil.yKeyCmm2Steps(getCutParams().getCutterSize() / 2);
    }

    public int getCutterSize2() {
        return UnitConvertUtil.yKeyCmm2Steps(getCutParams().getCutterSize());
    }

    public int getCutterRadiusSize() {
        return getCutParams().getCutterSize() / 2;
    }

    public int getCutterSize() {
        return getCutParams().getCutterSize();
    }

    public KeyAlign getAlign() {
        return getDuplicateKeyData().keyAlign;
    }

    public int getDecodeLength() {
        return getDuplicateKeyData().getLength();
    }

    public int getCutLength() {
        return getKeyAlignInfo().getDecodeLength2();
    }

    public int getDecodeWidth() {
        return getKeyAlignInfo().getDecodeWidth2();
    }

    public int getLayer() {
        return getCutParams().getLayer();
    }

    public int keyX2MachineValue(int i) {
        int tipCutter;
        int xKey2MachineDire;
        if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
            tipCutter = getShoulderCutter();
            xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(i);
        } else {
            tipCutter = getTipCutter();
            xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(i);
        }
        return tipCutter + xKey2MachineDire;
    }

    public int keyY2MachineValueBaseLeft(int i) {
        return getLeftCutter() - UnitConvertUtil.yKey2MachineDire(i);
    }

    public int KeyY2MachineValueBaseRight(int i) {
        return getRightCutter() + UnitConvertUtil.yKey2MachineDire(i);
    }

    public int getLeftCutter() {
        return getKeyAlignInfo().getLeftCutter();
    }

    public int getRightCutter() {
        return getKeyAlignInfo().getRightCutter();
    }

    public int getTipCutter() {
        return getKeyAlignInfo().getTipCutter();
    }

    public int getShoulderCutter() {
        return getKeyAlignInfo().getShoulderCutter();
    }

    public int getClampFace() {
        return getKeyAlignInfo().getClampFace();
    }

    public int getKeyFace() {
        return getKeyAlignInfo().getKeyFace();
    }

    public int getSingleSideKeyCutDepthFix() {
        return getCutParams().getSingleSideKeyCutDepthFix();
    }

    public KeyAlignInfo getKeyAlignInfo() {
        return OperationManager.getInstance().getKeyAlignInfo();
    }

    public void backOrigin(List<StepBean> list) {
        list.add(StepBeanFactory.getStepBean("关闭主轴", 0, 0, 0, 0, Speed.get_Speed_Origin(), "U:X;U:Y;U:Z;SUP:0,0"));
        list.add(StepBeanFactory.getStepBean("抬Z轴", 998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        list.add(StepBeanFactory.getStepBean("回原点", 999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
    }

    public DestPoint calculateEndPoint(DestPoint destPoint, DestPoint destPoint2) {
        double space = destPoint.getSpace();
        double depth = destPoint.getDepth();
        double space2 = destPoint2.getSpace();
        double depth2 = destPoint2.getDepth();
        double atan = Math.atan(Math.abs((depth - depth2) / (space - space2)));
        return DestPointFactory.getDestPoint((int) (space2 + (ToolSizeManager.getCutterRadiusSize2() * Math.sin(atan))), (int) ((depth2 + (ToolSizeManager.getCutterRadiusSize2() * Math.cos(atan))) - ToolSizeManager.getCutterRadiusSize2()));
    }

    public int LeaveTipX() {
        return getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
    }
}
