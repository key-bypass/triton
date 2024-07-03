package com.cutting.machine.operation.duplicateCut.cutpath;

import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.operation.duplicateCut.DuplicateCutParams;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateSideHoleKeyCut extends DuplicateCutPath {
    public static final int SIDE_REMAIN = 50;

    public DuplicateSideHoleKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.cutting.machine.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        List<DestPoint> destPointList = getPathDataList().get(0).getDestPointList();
        int size = destPointList.size() - 1;
        int size2 = destPointList.size() - 1;
        while (true) {
            if (size2 < 0) {
                break;
            }
            DestPoint r5 = destPointList.get(size2);
            if (!r5.isInvalid() && r5.getDepth() < 8.0f / MachineData.dZScale) {
                size = size2;
                break;
            }
            size2--;
        }
        int cutCount = cutCount();
        int keyFace = getKeyFace() - UnitConvertUtil.cmm2StepZ(50);
        ArrayList arrayList = new ArrayList();
        int[] splitCut = splitCut(cutCount);
        for (int i = 0; i < cutCount; i++) {
            for (int i2 = 0; i2 <= size; i2++) {
                DestPoint destPoint = destPointList.get(i2);
                if (!destPoint.isInvalid()) {
                    int keyX2MachineValue = keyX2MachineValue(destPoint.getSpace());
                    int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(splitCut[i]);
                    int cutZ = cutZ(destPoint.getDepth());
                    if (i2 == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                        if (i == 0) {
                            arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                        }
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("第" + (i + 1) + "刀，第" + (i2 + 1) + "点", keyX2MachineValue, KeyY2MachineValueBaseRight, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    if (i2 == size) {
                        arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    }
                }
            }
        }
        backOrigin(arrayList);
        return arrayList;
    }

    @Override // com.cutting.machine.operation.duplicateCut.cutpath.DuplicateCutPath
    public int cutCount() {
        int r0 = 0;
        if (getDecodeWidth() - UnitConvertUtil.cmm2StepY(100) > getCutterSize2()) {
            return (int) Math.ceil((r0 * 1.0f) / getMaxCut());
        }
        return 1;
    }

    protected int[] splitCut(int i) {
        int[] iArr = new int[i];
        int decodeWidth = (getDecodeWidth() - UnitConvertUtil.cmm2StepY(100)) - ToolSizeManager.getCutterSize2();
        if (decodeWidth < 0) {
            decodeWidth = 0;
        }
        float f = (decodeWidth * 1.0f) / (i - 1);
        if (f > getMaxCut()) {
            f = getMaxCut();
        }
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = UnitConvertUtil.cmm2StepY(50) + ((int) (i2 * f)) + getCutterRadiusSize2();
        }
        return iArr;
    }

    private int cutZ(int i) {
        if (i < 8.0f / MachineData.dZScale) {
            i = (int) ((-5.0f) / MachineData.dZScale);
        }
        return getKeyFace() + i;
    }
}
