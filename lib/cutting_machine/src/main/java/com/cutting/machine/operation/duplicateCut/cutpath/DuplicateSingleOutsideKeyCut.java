package com.liying.core.operation.duplicateCut.cutpath;

import android.graphics.PointF;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.DestPoint;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.duplicate.keyview.Line;
import com.liying.core.duplicate.keyview.LineUtils;
import com.liying.core.operation.duplicateCut.DuplicateCutParams;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateSingleOutsideKeyCut extends DuplicateCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.55f, 0.1f, 0.0f};
    private static final float[] fourCut = {0.7f, 0.4f, 0.1f, 0.0f};

    public DuplicateSingleOutsideKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        int i;
        int i2;
        int i3;
        ArrayList arrayList;
        int keyY2MachineValueBaseLeft;
        int i4;
        List<DestPoint> list;
        String str;
        String str2;
        int KeyY2MachineValueBaseRight;
        int i5;
        int i6;
        int i7;
        int i8;
        String str3;
        List<DestPoint> list2;
        ArrayList arrayList2;
        String str4;
        if (riskCutClamp()) {
            return null;
        }
        removeShoulderPoint();
        removeInvalidPoint();
        addEndPointList();
        int cutZ = cutZ();
        int zUp = zUp(cutZ);
        int leftCutter = getLeftCutter() + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int rightCutter = getRightCutter() - UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
        int cutCount = cutCount();
        ArrayList arrayList3 = new ArrayList();
        int i9 = 0;
        while (i9 < cutCount) {
            for (SinglePathData singlePathData : getPathDataList()) {
                List<DestPoint> destPointList = singlePathData.getDestPointList();
                String str5 = "点";
                String str6 = "刀,第";
                if (singlePathData.getBenchmark() == Benchmark.RIGHT) {
                    int i10 = 0;
                    while (i10 < destPointList.size()) {
                        DestPoint destPoint = destPointList.get(i10);
                        if (destPoint.isInvalid()) {
                            i5 = i10;
                            i6 = i9;
                            i7 = cutCount;
                            i8 = zUp;
                            str4 = str6;
                            list2 = destPointList;
                            arrayList2 = arrayList3;
                        } else {
                            int keyX2MachineValue = keyX2MachineValue(destPoint.getSpace());
                            if (destPoint.isDoNotSplitDepth()) {
                                KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(destPoint.getDepth());
                            } else {
                                KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(splitCut(destPoint.getDepth())[i9]);
                            }
                            int i11 = KeyY2MachineValueBaseRight;
                            if (i10 != 0) {
                                i5 = i10;
                                i6 = i9;
                                i7 = cutCount;
                                i8 = zUp;
                                str3 = str6;
                                list2 = destPointList;
                                arrayList2 = arrayList3;
                            } else if (i9 == 0) {
                                str3 = str6;
                                arrayList3.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, leftCutter, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                                str5 = str5;
                                i5 = i10;
                                list2 = destPointList;
                                i6 = i9;
                                i8 = zUp;
                                arrayList2 = arrayList3;
                                i7 = cutCount;
                                arrayList2.add(StepBeanFactory.getCutStepBean("下Z轴", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList2.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                            } else {
                                i5 = i10;
                                i6 = i9;
                                i7 = cutCount;
                                i8 = zUp;
                                str3 = str6;
                                list2 = destPointList;
                                arrayList2 = arrayList3;
                                arrayList2.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", keyX2MachineValue, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("左边第");
                            sb.append(i6 + 1);
                            str4 = str3;
                            sb.append(str4);
                            sb.append(i5 + 1);
                            sb.append(str5);
                            arrayList2.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue, i11, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            if (i5 == list2.size() - 1) {
                                if (i6 < i7 - 1) {
                                    arrayList2.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, i11, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                                    arrayList2.add(StepBeanFactory.getCutStepBean("离开左边", tipCutter, leftCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                                } else {
                                    arrayList2.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, i11, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                                }
                            }
                        }
                        i10 = i5 + 1;
                        arrayList3 = arrayList2;
                        i9 = i6;
                        destPointList = list2;
                        zUp = i8;
                        cutCount = i7;
                        str6 = str4;
                    }
                    i2 = cutCount;
                    i3 = zUp;
                    arrayList = arrayList3;
                    i = i9;
                } else {
                    i = i9;
                    i2 = cutCount;
                    i3 = zUp;
                    String str7 = "刀,第";
                    List<DestPoint> list3 = destPointList;
                    arrayList = arrayList3;
                    int size = list3.size() - 1;
                    while (size >= 0) {
                        DestPoint destPoint2 = list3.get(size);
                        int keyX2MachineValue2 = keyX2MachineValue(destPoint2.getSpace());
                        if (destPoint2.isDoNotSplitDepth()) {
                            keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(destPoint2.getDepth());
                        } else {
                            keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(splitCut(destPoint2.getDepth())[i]);
                        }
                        int i12 = keyY2MachineValueBaseLeft;
                        if (size != list3.size() - 1) {
                            i4 = size;
                            list = list3;
                            str = str7;
                            str2 = str5;
                        } else if (i == 0) {
                            String str8 = str7;
                            list = list3;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, i12, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                            str2 = str5;
                            i = i;
                            str = str8;
                            i4 = size;
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, i12, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter, i12, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                        } else {
                            i4 = size;
                            list = list3;
                            str = str7;
                            str2 = str5;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, i12, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                        }
                        arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i + 1) + str + (i4 + 1) + str2, keyX2MachineValue2, i12, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i4 == 0) {
                            if (i < i2 - 1) {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开右边", keyX2MachineValue2, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("移动到前端", tipCutter, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            } else {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开右边", keyX2MachineValue2, rightCutter, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", keyX2MachineValue2, rightCutter, i3, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            }
                        }
                        size = i4 - 1;
                        str5 = str2;
                        str7 = str;
                        list3 = list;
                    }
                }
                arrayList3 = arrayList;
                i9 = i;
                zUp = i3;
                cutCount = i2;
            }
            i9++;
            zUp = zUp;
        }
        ArrayList arrayList4 = arrayList3;
        backOrigin(arrayList4);
        return arrayList4;
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
                if (i < destPointList.size()) {
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
            }
        }
    }

    public void addEndPointList() {
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            DestPoint destPoint = destPointList.get(destPointList.size() - 1);
            DestPoint calculateEndPoint = calculateEndPoint(destPoint, DestPointFactory.getDestPoint(cutLength, 0));
            DestPoint destPoint2 = null;
            int size = destPointList.size() - 2;
            while (true) {
                if (size < 0) {
                    break;
                }
                DestPoint destPoint3 = destPointList.get(size);
                if (destPoint.getSpace() - destPoint3.getSpace() > UnitConvertUtil.xKeyCmm2Steps(150)) {
                    destPoint2 = destPoint3;
                    break;
                }
                size--;
            }
            if (destPoint2 == null) {
                destPointList.add(calculateEndPoint);
            } else {
                Line line = new Line(new PointF(destPoint2.getSpace(), destPoint2.getDepth()), new PointF(destPoint.getSpace(), destPoint.getDepth()));
                PointF crossPoint = LineUtils.getCrossPoint(line, new Line(new PointF(0.0f, 0.0f), new PointF(1.0f, 0.0f)));
                if (crossPoint != null && crossPoint.x <= cutLength && crossPoint.x >= destPoint.getSpace()) {
                    destPointList.add(calculateEndPoint(destPoint, DestPointFactory.getDestPoint((int) crossPoint.x, 0)));
                } else {
                    float f = cutLength;
                    PointF crossPoint2 = LineUtils.getCrossPoint(line, new Line(new PointF(f, 0.0f), new PointF(f, 1.0f)));
                    if (crossPoint2 != null && crossPoint2.y >= 0.0f) {
                        destPointList.add(calculateEndPoint(destPoint, DestPointFactory.getDestPoint(cutLength, (int) crossPoint2.y)));
                    } else {
                        destPointList.add(calculateEndPoint);
                    }
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

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public int cutCount() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (it2.hasNext()) {
                i = Math.min(it2.next().getDepth(), i);
            }
        }
        int ceil = (int) Math.ceil(((getDecodeWidth() - i) * 1.0f) / getMaxCut());
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
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleOutsideKeyCut.twiceCut
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
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleOutsideKeyCut.thriceCut
            r6 = r6[r4]
            goto L20
        L2d:
            r6 = 4
            if (r0 != r6) goto L35
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleOutsideKeyCut.fourCut
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
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.duplicateCut.cutpath.DuplicateSingleOutsideKeyCut.splitCut(int):int[]");
    }

    private int cutZ() {
        return getKeyFace() + getDuplicateKeyData().getCutDepth();
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
    }
}
