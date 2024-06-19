package com.liying.core.operation.duplicateCut.cutpath;

import com.liying.core.bean.DestPoint;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.communication.OperationManager;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateCut.DuplicateCutParams;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateSingleSideKeyCut extends DuplicateCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.6f, 0.2f, 0.0f};

    public DuplicateSingleSideKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        int i;
        List<DestPoint> list;
        int i2;
        ArrayList arrayList;
        removeDeepPoint();
        removeInvalidPoint();
        int cutZ = cutZ();
        int rightCutter = getRightCutter() + UnitConvertUtil.yKey2MachineDire(maxDepth()) + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int cutCount = cutCount();
        ArrayList arrayList2 = new ArrayList();
        int i3 = 0;
        while (i3 < cutCount) {
            Iterator<SinglePathData> it = getPathDataList().iterator();
            while (it.hasNext()) {
                List<DestPoint> destPointList = it.next().getDestPointList();
                int i4 = 0;
                while (i4 < destPointList.size()) {
                    DestPoint destPoint = destPointList.get(i4);
                    if (destPoint.isInvalid()) {
                        i = i4;
                        list = destPointList;
                        i2 = i3;
                        arrayList = arrayList2;
                    } else {
                        int keyX2MachineValue = keyX2MachineValue(destPoint.getSpace());
                        int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(splitCut(destPoint.getDepth())[i3]);
                        if (i4 != 0) {
                            i = i4;
                            list = destPointList;
                            i2 = i3;
                            arrayList = arrayList2;
                        } else if (i3 == 0) {
                            arrayList2.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, rightCutter, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                            i = i4;
                            list = destPointList;
                            i2 = i3;
                            arrayList = arrayList2;
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", keyX2MachineValue, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                        } else {
                            i = i4;
                            list = destPointList;
                            i2 = i3;
                            arrayList = arrayList2;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", keyX2MachineValue, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        }
                        arrayList.add(StepBeanFactory.getCutStepBean("左边第" + (i2 + 1) + "刀,第" + (i + 1) + "点", keyX2MachineValue, KeyY2MachineValueBaseRight, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i == list.size() - 1) {
                            if (i2 == cutCount - 1) {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开左边", keyX2MachineValue, KeyY2MachineValueBaseRight + UnitConvertUtil.yKey2Machine(100), cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            } else {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开左边", keyX2MachineValue, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                        }
                    }
                    i4 = i + 1;
                    arrayList2 = arrayList;
                    destPointList = list;
                    i3 = i2;
                }
            }
            i3++;
        }
        ArrayList arrayList3 = arrayList2;
        backOrigin(arrayList3);
        return arrayList3;
    }

    public void removeDeepPoint() {
        int deep;
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            for (int i = 0; i < destPointList.size(); i++) {
                DestPoint destPoint = destPointList.get(i);
                int depth = destPoint.getDepth();
                if (getClamp() == Clamp.S2_A) {
                    deep = ClampManager.getInstance().getS2A().getDeep();
                } else if (getClamp() == Clamp.E9S2A) {
                    deep = ClampManager.getInstance().getE9S2A().getDeep();
                } else if (getClamp() == Clamp.S2_B) {
                    deep = ClampManager.getInstance().getS2B().getDeep();
                } else {
                    deep = getClamp() == Clamp.E9S2B ? ClampManager.getInstance().getE9S2B().getDeep() : 0;
                }
                if (depth < deep + UnitConvertUtil.yKeyCmm2Steps(10)) {
                    destPoint.setInvalid(true);
                }
            }
        }
    }

    public void removeInvalidPoint() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (it2.hasNext()) {
                if (it2.next().isInvalid()) {
                    it2.remove();
                }
            }
        }
    }

    public int maxDepth() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (it2.hasNext()) {
                i = Math.max(it2.next().getDepth(), i);
            }
        }
        return i;
    }

    public int minDepth() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (it2.hasNext()) {
                i = Math.min(it2.next().getDepth(), i);
            }
        }
        return i;
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public int cutCount() {
        int ceil = (int) Math.ceil(((maxDepth() - minDepth()) * 1.0d) / getMaxCut());
        if (ceil < 3) {
            return 3;
        }
        return ceil;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int[] splitCut(int r11) {
        /*
            r10 = this;
            int r0 = r10.cutCount()
            int[] r1 = new int[r0]
            int r2 = r10.maxDepth()
            int r3 = r2 - r11
            r4 = 0
            if (r3 >= 0) goto L10
            r3 = 0
        L10:
            float r3 = (float) r3
            r5 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 * r3
            float r6 = (float) r0
            float r5 = r5 / r6
        L17:
            if (r4 >= r0) goto L51
            r6 = 2
            if (r0 != r6) goto L25
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleSideKeyCut.twiceCut
            r6 = r6[r4]
        L20:
            float r6 = r6 * r3
        L22:
            int r6 = (int) r6
            int r6 = r6 + r11
            goto L34
        L25:
            r6 = 3
            if (r0 != r6) goto L2d
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleSideKeyCut.thriceCut
            r6 = r6[r4]
            goto L20
        L2d:
            int r6 = r0 + (-1)
            int r6 = r6 - r4
            float r6 = (float) r6
            float r6 = r6 * r5
            goto L22
        L34:
            int r7 = r2 - r6
            int r8 = r10.getMaxCut()
            int r9 = r4 + 1
            int r8 = r8 * r9
            if (r7 <= r8) goto L48
            int r6 = r10.getMaxCut()
            int r6 = r6 * r9
            int r6 = r2 - r6
        L48:
            int r7 = r10.getCutterRadiusSize2()
            int r6 = r6 + r7
            r1[r4] = r6
            r4 = r9
            goto L17
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleSideKeyCut.splitCut(int):int[]");
    }

    public int KeyY2MachineValueBaseCenterRight(int i) {
        return OperationManager.getInstance().getKeyAlignInfo().getKeyCenterCutter() + UnitConvertUtil.yKey2MachineDire(i);
    }

    private int cutZ() {
        if (getClamp() == Clamp.E9S2A || getClamp() == Clamp.E9S2B) {
            return getKeyFace() - UnitConvertUtil.cmm2StepZ(getSingleSideKeyCutDepthFix() + 230);
        }
        return getKeyFace() + UnitConvertUtil.cmm2StepZ(900 - getSingleSideKeyCutDepthFix());
    }
}
