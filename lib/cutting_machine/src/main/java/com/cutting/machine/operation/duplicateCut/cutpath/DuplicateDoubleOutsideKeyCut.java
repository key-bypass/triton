package com.liying.core.operation.duplicateCut.cutpath;

import com.liying.core.bean.DestPoint;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateCut.DuplicateCutParams;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateDoubleOutsideKeyCut extends DuplicateCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.55f, 0.1f, 0.0f};
    private static final float[] fourCut = {0.7f, 0.4f, 0.1f, 0.0f};

    public DuplicateDoubleOutsideKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        ArrayList arrayList;
        int i;
        int i2;
        int keyY2MachineValueBaseLeft;
        int KeyY2MachineValueBaseRight;
        int i3;
        ArrayList arrayList2;
        int i4;
        String str;
        String str2;
        List<DestPoint> list;
        int i5;
        String str3;
        DuplicateDoubleOutsideKeyCut duplicateDoubleOutsideKeyCut = this;
        if (riskCutClamp()) {
            return null;
        }
        removeShoulderPoint();
        removeInvalidPoint();
        addEndPointList();
        int cutZ = cutZ();
        int zUp = duplicateDoubleOutsideKeyCut.zUp(cutZ);
        int leftCutter = getLeftCutter() + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int rightCutter = getRightCutter() - UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int cutCount = cutCount();
        ArrayList arrayList3 = new ArrayList();
        int i6 = 0;
        while (i6 < cutCount) {
            for (SinglePathData singlePathData : getPathDataList()) {
                List<DestPoint> destPointList = singlePathData.getDestPointList();
                String str4 = "点";
                String str5 = "刀,第";
                if (singlePathData.getBenchmark() == Benchmark.RIGHT) {
                    int i7 = 0;
                    while (i7 < destPointList.size()) {
                        DestPoint destPoint = destPointList.get(i7);
                        if (destPoint.isInvalid()) {
                            i3 = i7;
                            arrayList2 = arrayList3;
                            i4 = cutCount;
                            str3 = str5;
                            str2 = str4;
                            list = destPointList;
                            i5 = i6;
                        } else {
                            int keyX2MachineValue = duplicateDoubleOutsideKeyCut.keyX2MachineValue(destPoint.getSpace());
                            if (destPoint.isDoNotSplitDepth()) {
                                KeyY2MachineValueBaseRight = duplicateDoubleOutsideKeyCut.KeyY2MachineValueBaseRight(destPoint.getDepth());
                            } else {
                                KeyY2MachineValueBaseRight = duplicateDoubleOutsideKeyCut.KeyY2MachineValueBaseRight(duplicateDoubleOutsideKeyCut.splitCut(destPoint.getDepth())[i6]);
                            }
                            int i8 = KeyY2MachineValueBaseRight;
                            if (i7 != 0) {
                                i3 = i7;
                                arrayList2 = arrayList3;
                                i4 = cutCount;
                                str = str5;
                                str2 = str4;
                                list = destPointList;
                                i5 = i6;
                            } else if (i6 == 0) {
                                str = str5;
                                arrayList3.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, leftCutter, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                                str2 = str4;
                                i3 = i7;
                                list = destPointList;
                                i5 = i6;
                                arrayList2 = arrayList3;
                                i4 = cutCount;
                                arrayList2.add(StepBeanFactory.getCutStepBean("下Z轴", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList2.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                            } else {
                                i3 = i7;
                                arrayList2 = arrayList3;
                                i4 = cutCount;
                                str = str5;
                                str2 = str4;
                                list = destPointList;
                                i5 = i6;
                                arrayList2.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, leftCutter, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                                arrayList2.add(StepBeanFactory.getCutStepBean("下Z轴", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("左边第");
                            sb.append(i5 + 1);
                            str3 = str;
                            sb.append(str3);
                            sb.append(i3 + 1);
                            sb.append(str2);
                            arrayList2.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue, i8, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        }
                        i7 = i3 + 1;
                        arrayList3 = arrayList2;
                        cutCount = i4;
                        destPointList = list;
                        i6 = i5;
                        duplicateDoubleOutsideKeyCut = this;
                        str5 = str3;
                        str4 = str2;
                    }
                    i2 = i6;
                    arrayList = arrayList3;
                    i = cutCount;
                } else {
                    arrayList = arrayList3;
                    i = cutCount;
                    String str6 = "刀,第";
                    i2 = i6;
                    int size = destPointList.size() - 1;
                    while (size >= 0) {
                        DestPoint destPoint2 = destPointList.get(size);
                        int keyX2MachineValue2 = keyX2MachineValue(destPoint2.getSpace());
                        if (destPoint2.isDoNotSplitDepth()) {
                            keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(destPoint2.getDepth());
                        } else {
                            keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(splitCut(destPoint2.getDepth())[i2]);
                        }
                        String str7 = str6;
                        arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i2 + 1) + str6 + (size + 1) + "点", keyX2MachineValue2, keyY2MachineValueBaseLeft, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (size == 0) {
                            arrayList.add(StepBeanFactory.getCutStepBean("离开右边", keyX2MachineValue2, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            if (i2 < i - 1) {
                                arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", keyX2MachineValue2, rightCutter, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            }
                        }
                        size--;
                        str6 = str7;
                    }
                }
                arrayList3 = arrayList;
                cutCount = i;
                duplicateDoubleOutsideKeyCut = this;
                i6 = i2;
            }
            i6++;
            duplicateDoubleOutsideKeyCut = duplicateDoubleOutsideKeyCut;
        }
        DuplicateDoubleOutsideKeyCut duplicateDoubleOutsideKeyCut2 = duplicateDoubleOutsideKeyCut;
        ArrayList arrayList4 = arrayList3;
        duplicateDoubleOutsideKeyCut2.backOrigin(arrayList4);
        return arrayList4;
    }

    public void addEndPointList() {
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            DestPoint destPoint = destPointList.get(destPointList.size() - 1);
            int decodeWidth = (getDecodeWidth() / 2) + UnitConvertUtil.yKeyCmm2Steps(30);
            destPointList.add(calculateEndPoint(destPoint, DestPointFactory.getDestPoint(cutLength, decodeWidth)));
            destPointList.add(DestPointFactory.getDestPoint(UnitConvertUtil.xKeyCmm2Steps(getCutterSize()) + cutLength, decodeWidth));
        }
    }

    public boolean riskCutClamp() {
        return cutZ() > getClampFace() - UnitConvertUtil.zKey2Machine(10);
    }

    public void removeShoulderPoint() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            int i = 0;
            while (true) {
                if (i >= destPointList.size()) {
                    break;
                }
                DestPoint destPoint = destPointList.get(i);
                int space = destPoint.getSpace();
                if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    if (space <= getCutterRadiusSize2() + UnitConvertUtil.xKeyCmm2Steps(10)) {
                        destPoint.setInvalid(true);
                    } else {
                        destPoint.setInvalid(false);
                        break;
                    }
                }
                i++;
            }
            int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
            for (int size = destPointList.size() - 1; size >= 0; size--) {
                DestPoint destPoint2 = destPointList.get(size);
                if (!destPoint2.isInvalid() && cutLength - destPoint2.getSpace() < UnitConvertUtil.yKeyCmm2Steps(200)) {
                    destPoint2.setInvalid(true);
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

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public int cutCount() {
        int ceil = (int) Math.ceil((getDecodeWidth() * 0.5d) / getMaxCut());
        if (ceil < 3) {
            return 3;
        }
        return ceil;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0050 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int[] splitCut(int r11) {
        /*
            r10 = this;
            int r0 = r10.cutCount()
            int[] r1 = new int[r0]
            int r2 = r10.getDecodeWidth()
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
            if (r4 >= r0) goto L59
            r6 = 2
            if (r0 != r6) goto L25
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleOutsideKeyCut.twiceCut
            r6 = r6[r4]
        L20:
            float r6 = r6 * r3
        L22:
            int r6 = (int) r6
            int r6 = r6 + r11
            goto L3c
        L25:
            r6 = 3
            if (r0 != r6) goto L2d
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleOutsideKeyCut.thriceCut
            r6 = r6[r4]
            goto L20
        L2d:
            r6 = 4
            if (r0 != r6) goto L35
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleOutsideKeyCut.fourCut
            r6 = r6[r4]
            goto L20
        L35:
            int r6 = r0 + (-1)
            int r6 = r6 - r4
            float r6 = (float) r6
            float r6 = r6 * r5
            goto L22
        L3c:
            int r7 = r2 - r6
            int r8 = r10.getMaxCut()
            int r9 = r4 + 1
            int r8 = r8 * r9
            if (r7 <= r8) goto L50
            int r6 = r10.getMaxCut()
            int r6 = r6 * r9
            int r6 = r2 - r6
        L50:
            int r7 = r10.getCutterRadiusSize2()
            int r6 = r6 + r7
            r1[r4] = r6
            r4 = r9
            goto L17
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleOutsideKeyCut.splitCut(int):int[]");
    }

    private int cutZ() {
        return getKeyFace() + getDuplicateKeyData().getCutDepth();
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
    }
}
