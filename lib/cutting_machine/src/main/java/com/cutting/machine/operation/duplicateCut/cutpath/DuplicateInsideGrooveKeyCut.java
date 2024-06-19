package com.liying.core.operation.duplicateCut.cutpath;

import android.graphics.Point;
import android.graphics.PointF;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.DestPoint;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.MachineData;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.duplicate.keyview.Line;
import com.liying.core.duplicate.keyview.LineUtils;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.operation.duplicateCut.DuplicateCutParams;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateInsideGrooveKeyCut extends DuplicateCutPath {
    private static final String TAG = "DuplicateInsideGroove";
    private static final float[] layer2 = {0.7f, 1.0f};
    private static final float[] layer3 = {0.4f, 0.8f, 1.0f};

    public DuplicateInsideGrooveKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        addEndPointList();
        fixSingleInsideGrooveData();
        removeShoulderPoint();
        int cutCount = cutCount();
        initSharpenPoint(cutCount);
        if (checkGrooveInvalid()) {
            ErrorHelper.handleError(ErrorCode.ReplaceSmallerCutter);
            return null;
        }
        removeInvalidPoint();
        return generateKeyCutSteps(cutCount);
    }

    private void removeShoulderPoint() {
        int r3;
        if (getCutterSize() == 100) {
            return;
        }
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (true) {
                if (it2.hasNext()) {
                    DestPoint next = it2.next();
                    if (next.getGroove() < getCutterSize2()) {
                        it2.remove();
                    } else {
                        next.setInvalid(false);
                        break;
                    }
                }
            }
        }
    }

    private boolean checkGrooveInvalid() {
        int i;
        if (getCutterSize() == 100) {
            return false;
        }
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            int i2 = 0;
            while (true) {
                if (i2 < destPointList.size() - 1) {
                    DestPoint destPoint = destPointList.get(i2);
                    if (destPoint.isInvalid()) {
                        i2++;
                    } else {
                        if (destPoint.getSpace() > destPoint.getSpace() + ((int) ((cutLength - r3) * 0.5d))) {
                            return true;
                        }
                    }
                }
            }
        }
        Iterator<SinglePathData> it2 = getPathDataList().iterator();
        while (it2.hasNext()) {
            List<DestPoint> destPointList2 = it2.next().getDestPointList();
            for (int i3 = 0; i3 < destPointList2.size() - 1; i3++) {
                DestPoint destPoint2 = destPointList2.get(i3);
                if (!destPoint2.isInvalid()) {
                    for (int i4 = i3 + 1; i4 < destPointList2.size(); i4++) {
                        DestPoint destPoint3 = destPointList2.get(i4);
                        if (!destPoint3.isInvalid() && destPoint3.getSpace() - destPoint2.getSpace() == 0) {
                            if (Math.min(destPoint2.getGroove(), destPoint3.getGroove()) < getCutterSize2()) {
                                return true;
                            }
                            if (Math.abs(((destPoint3.getGroove() - destPoint2.getGroove()) * 1.0f) / (destPoint3.getSpace() - destPoint2.getSpace())) <= 0.2d && (i = i4 - i3) != 1) {
                                if (destPointList2.get((i / 2) + i3).getGroove() < getCutterSize2()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private List<StepBean> generateKeyCutSteps(int i) {
        int i2;
        int i3;
        int i4;
        List<DestPoint> list;
        String str;
        int i5;
        List<DestPoint> list2;
        int i6;
        int i7;
        String str2;
        ArrayList arrayList = new ArrayList();
        int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
        int[] cutZ = cutZ();
        List<SinglePathData> pathDataList = getPathDataList();
        int i8 = 0;
        while (i8 < pathDataList.size() - 1) {
            int i9 = 0;
            while (i9 < getLayer()) {
                int i10 = cutZ[i9];
                int i11 = 0;
                while (i11 < i) {
                    List<DestPoint> destPointList = pathDataList.get(i8).getDestPointList();
                    int size = destPointList.size() - 1;
                    while (size >= 0) {
                        DestPoint destPoint = destPointList.get(size);
                        int space = destPoint.getSpace();
                        int[] iArr = cutZ;
                        int i12 = i11 + 1;
                        int keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(splitCut(destPoint, i)[i - i12]);
                        int keyX2MachineValue = keyX2MachineValue(space);
                        if (size != destPointList.size() - 1) {
                            str = "刀,第";
                            i5 = size;
                            list2 = destPointList;
                            i6 = i11;
                            i7 = i9;
                            str2 = "点";
                        } else if (i9 == 0 && i11 == 0 && i8 == 0) {
                            str = "刀,第";
                            int i13 = tipCutter;
                            i7 = i9;
                            str2 = "点";
                            i5 = size;
                            list2 = destPointList;
                            i6 = i11;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i13, keyY2MachineValueBaseLeft, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                            int i14 = i10;
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i13, keyY2MachineValueBaseLeft, i14, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", i13, keyY2MachineValueBaseLeft, i14, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                        } else {
                            str = "刀,第";
                            i5 = size;
                            list2 = destPointList;
                            i6 = i11;
                            i7 = i9;
                            str2 = "点";
                            int i15 = tipCutter;
                            int i16 = i10;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i15, keyY2MachineValueBaseLeft, i16, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i15, keyY2MachineValueBaseLeft, i16, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        }
                        int i17 = i10;
                        arrayList.add(StepBeanFactory.getCutStepBean("左边第" + i12 + str + (i5 + 1) + str2, keyX2MachineValue, keyY2MachineValueBaseLeft, i17, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        size = i5 + (-1);
                        i10 = i17;
                        destPointList = list2;
                        cutZ = iArr;
                        i9 = i7;
                        i11 = i6;
                    }
                    int[] iArr2 = cutZ;
                    List<DestPoint> list3 = destPointList;
                    int i18 = i11;
                    int i19 = i9;
                    int i20 = i10;
                    List<DestPoint> destPointList2 = pathDataList.get(i8 + 1).getDestPointList();
                    DestPoint destPoint2 = list3.get(0);
                    int space2 = destPoint2.getSpace();
                    DestPoint destPoint3 = destPointList2.get(0);
                    destPoint3.getSpace();
                    List<SinglePathData> list4 = pathDataList;
                    int i21 = i18 + 1;
                    int i22 = i - i21;
                    int i23 = splitCut(destPoint2, i)[i22];
                    int i24 = splitCut(destPoint3, i)[i22];
                    int decodeWidth = ((getDecodeWidth() - i23) - i24) / 2;
                    int i25 = i8;
                    String str3 = "点";
                    double d = decodeWidth;
                    int i26 = (int) (space2 - (0.707d * d));
                    int i27 = tipCutter;
                    String str4 = "刀,第";
                    double d2 = d * 0.293d;
                    arrayList.add(StepBeanFactory.getCutStepBean("切圆1", keyX2MachineValue(i26), keyY2MachineValueBaseLeft((int) (i23 + d2)), i20, Speed.get_Speed_CuttingTurn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("切圆1", keyX2MachineValue(space2 - decodeWidth), keyY2MachineValueBaseLeft(i23 + decodeWidth), i20, Speed.get_Speed_CuttingTurn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("切圆1", keyX2MachineValue(i26), KeyY2MachineValueBaseRight((int) (i24 + d2)), i20, Speed.get_Speed_CuttingTurn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    int i28 = 0;
                    while (i28 < destPointList2.size()) {
                        DestPoint destPoint4 = destPointList2.get(i28);
                        int space3 = destPoint4.getSpace();
                        int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(splitCut(destPoint4, i)[i22]);
                        int keyX2MachineValue2 = keyX2MachineValue(space3);
                        StringBuilder sb = new StringBuilder();
                        sb.append("右边第");
                        sb.append(i21);
                        String str5 = str4;
                        sb.append(str5);
                        int i29 = i28 + 1;
                        sb.append(i29);
                        String str6 = str3;
                        sb.append(str6);
                        arrayList.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue2, KeyY2MachineValueBaseRight, i20, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i28 == destPointList2.size() - 1) {
                            i4 = i21;
                            list = destPointList2;
                            i3 = i20;
                            arrayList.add(StepBeanFactory.getCutStepBean("离开前端", i27, KeyY2MachineValueBaseRight, i20, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        } else {
                            i3 = i20;
                            i4 = i21;
                            list = destPointList2;
                        }
                        i20 = i3;
                        destPointList2 = list;
                        str4 = str5;
                        i28 = i29;
                        str3 = str6;
                        i21 = i4;
                    }
                    int i30 = i20;
                    int i31 = i21;
                    List<DestPoint> list5 = destPointList2;
                    if (i18 == i - 1) {
                        i2 = i19;
                        if (i2 == getLayer() - 1) {
                            DestPoint sharpenPoint = getSharpenPoint(list3);
                            DestPoint sharpenPoint2 = getSharpenPoint(list5);
                            if (sharpenPoint != null && sharpenPoint2 != null) {
                                arrayList.addAll(cutTip(list3, list5, i, i30));
                            }
                        }
                    } else {
                        i2 = i19;
                    }
                    i10 = i30;
                    i9 = i2;
                    i11 = i31;
                    pathDataList = list4;
                    cutZ = iArr2;
                    i8 = i25;
                    tipCutter = i27;
                }
                i9++;
            }
            i8 += 2;
        }
        backOrigin(arrayList);
        return arrayList;
    }

    public List<StepBean> getArc(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = (i + i3) / 2;
        int i8 = (i2 + i4) / 2;
        double d = i - i3;
        double d2 = i2 - i4;
        int sqrt = ((int) Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))) / 2;
        double d3 = d / (d2 * 1.0d);
        double atan = Math.atan(d3);
        double atan2 = Math.atan(d3) + 3.141592653589793d;
        ArrayList arrayList = new ArrayList();
        double d4 = (atan - atan2) / (i5 - 1);
        for (int i9 = 0; i9 < i5; i9++) {
            double d5 = sqrt;
            double d6 = (i9 * d4) + atan;
            arrayList.add(StepBeanFactory.getCutStepBean("尾部切圆", i7 + UnitConvertUtil.xKey2MachineDire((int) (Math.sin(d6) * d5)), i8 + UnitConvertUtil.yKey2MachineDire((int) (d5 * Math.cos(d6))), i6, Speed.get_Speed_CuttingTurn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
        }
        return arrayList;
    }

    private DestPoint getSharpenPoint(List<DestPoint> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            DestPoint destPoint = list.get(size);
            if (destPoint.isSharpenPoint()) {
                return destPoint;
            }
        }
        return null;
    }

    private int maxCutCount() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        int i = 1;
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            for (int size = destPointList.size() - 1; size >= 0; size--) {
                DestPoint destPoint = destPointList.get(size);
                i = Math.max(i, (int) Math.ceil(destPoint.getGroove() / (getMaxCut() * 2.0f)));
                if (destPoint.isSharpenPoint()) {
                    break;
                }
            }
        }
        return i;
    }

    public List<StepBean> cutTip(List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        String str;
        ArrayList arrayList = new ArrayList();
        int maxCutCount = maxCutCount();
        int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
        for (int i3 = 0; i3 < maxCutCount; i3++) {
            if (i3 >= i) {
                for (int size = list.size() - 1; size >= 0; size += -1) {
                    DestPoint destPoint = list.get(size);
                    if (destPoint.isSharpenPoint()) {
                        break;
                    }
                    int space = destPoint.getSpace();
                    int keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(splitCut(destPoint, maxCutCount)[i3]);
                    int keyX2MachineValue = keyX2MachineValue(space);
                    if (size == list.size() - 1) {
                        str = "刀,第";
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, keyY2MachineValueBaseLeft, i2, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    } else {
                        str = "刀,第";
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("左边第" + (i3 + 1) + str + (size + 1) + "点", keyX2MachineValue, keyY2MachineValueBaseLeft, i2, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                }
                boolean z = false;
                int i4 = 0;
                while (i4 < list2.size()) {
                    DestPoint destPoint2 = list2.get(i4);
                    boolean z2 = destPoint2.isSharpenPoint() ? true : z;
                    if (z2) {
                        int space2 = destPoint2.getSpace();
                        int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(splitCut(destPoint2, maxCutCount)[i3]);
                        arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i3 + 1) + "刀,第" + (i4 + 1) + "点", keyX2MachineValue(space2), KeyY2MachineValueBaseRight, i2, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i4 == list2.size() - 1) {
                            arrayList.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, KeyY2MachineValueBaseRight, i2, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        }
                    }
                    i4++;
                    z = z2;
                }
            }
        }
        return arrayList;
    }

    public List<StepBean> cutTip(Point point, Point point2, Point point3, Point point4, int i) {
        ArrayList arrayList = new ArrayList();
        Point intersectPoint = getIntersectPoint(point, point2, point3, point4);
        int i2 = point.x;
        int i3 = point.y;
        int i4 = point3.x;
        int i5 = point3.y;
        int i6 = intersectPoint.x;
        int i7 = intersectPoint.y;
        int xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(point.y - point3.y);
        int LeaveTipX = LeaveTipX();
        int yKeyCmm2Steps = UnitConvertUtil.yKeyCmm2Steps(ToolSizeManager.getCutterRadiusSize());
        if (xKey2MachineDire > yKeyCmm2Steps) {
            int ceil = (int) Math.ceil((xKey2MachineDire - yKeyCmm2Steps) / ((ToolSizeManager.getCutterSize2() * 0.8d) * 2.0d));
            int i8 = xKey2MachineDire / (ceil * 2);
            int i9 = i5;
            int i10 = i6;
            int i11 = 0;
            while (i11 < ceil) {
                int yKey2MachineDire = i3 - UnitConvertUtil.yKey2MachineDire(i8);
                if (UnitConvertUtil.xKey2MachineDire(getTipCutter() - i2) > 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", getTipCutter(), yKey2MachineDire, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                int i12 = i2;
                int i13 = i2;
                int i14 = i11;
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", i12, yKey2MachineDire, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                int xKey2MachineDire2 = UnitConvertUtil.xKey2MachineDire(yKeyCmm2Steps) + i10;
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", xKey2MachineDire2, i7, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                int yKey2MachineDire2 = UnitConvertUtil.yKey2MachineDire(i8) + i9;
                int i15 = ceil;
                int i16 = yKeyCmm2Steps;
                int i17 = i4;
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", i4, yKey2MachineDire2, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                if (UnitConvertUtil.xKey2MachineDire(getTipCutter() - i17) > 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", getTipCutter(), yKey2MachineDire2, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                if (i14 == i15 - 1) {
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", LeaveTipX, yKey2MachineDire2, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                int i18 = i14 + 1;
                i2 = i13;
                i10 = xKey2MachineDire2;
                i9 = yKey2MachineDire2;
                ceil = i15;
                yKeyCmm2Steps = i16;
                i4 = i17;
                i3 = yKey2MachineDire;
                i11 = i18;
            }
        }
        return arrayList;
    }

    public Point getIntersectPoint(Point point, Point point2, Point point3, Point point4) {
        double d = point.y - point2.y;
        double d2 = point2.x - point.x;
        double d3 = (point.x * d) + (point.y * d2);
        double d4 = point3.y - point4.y;
        double d5 = point4.x - point3.x;
        double d6 = (point3.x * d4) + (point3.y * d5);
        double d7 = (d * d5) - (d4 * d2);
        if (Math.abs(d7) < 1.0E-5d) {
            return null;
        }
        return new Point((int) (((d5 / d7) * d3) + (((d2 * (-1.0d)) / d7) * d6)), (int) ((((d4 * (-1.0d)) / d7) * d3) + ((d / d7) * d6)));
    }

    private void initSharpenPoint(int i) {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            Iterator<DestPoint> it2 = it.next().getDestPointList().iterator();
            while (true) {
                if (it2.hasNext()) {
                    DestPoint next = it2.next();
                    if (next.getGroove() > getMaxCut() * 2 * i) {
                        next.setSharpenPoint(true);
                        next.setInvalid(false);
                        break;
                    }
                }
            }
        }
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public int cutCount() {
        List<SinglePathData> pathDataList = getPathDataList();
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        Iterator<SinglePathData> it = pathDataList.iterator();
        int i = 1;
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            int space = destPointList.get(0).getSpace();
            double d = cutLength - space;
            int i2 = ((int) (0.2d * d)) + space;
            int i3 = space + ((int) (d * 0.7d));
            for (int size = destPointList.size() - 1; size > 0; size--) {
                DestPoint destPoint = destPointList.get(size);
                if (!destPoint.isInvalid() && destPoint.getGroove() != 0 && !destPoint.isGapPointMoreCut()) {
                    if (destPoint.getSpace() < i2) {
                        break;
                    }
                    if (destPoint.getSpace() <= i3) {
                        int i4 = size - 1;
                        while (true) {
                            if (i4 >= 0) {
                                DestPoint destPoint2 = destPointList.get(i4);
                                if (!destPoint2.isInvalid() && destPoint2.getGroove() != 0 && !destPoint2.isGapPointMoreCut()) {
                                    if (destPoint2.getSpace() >= i2) {
                                        if (destPoint2.getSpace() <= i3) {
                                            if (Math.tan(Math.toRadians(15.0d)) > Math.abs(((destPoint.getDepth() - destPoint2.getDepth()) * 1.0f) / (destPoint.getSpace() - destPoint2.getSpace()))) {
                                                i = Math.max(i, (int) Math.ceil(destPointList.get((i4 + size) / 2).getGroove() / (getMaxCut() * 2.0f)));
                                            }
                                        }
                                    }
                                }
                                i4--;
                            }
                        }
                    }
                }
            }
        }
        return i;
    }

    private void fixSingleInsideGrooveData() {
        int depth;
        DestPoint destPoint;
        int depth2;
        int i;
        int depth3;
        DestPoint destPoint2;
        int depth4;
        int i2;
        int i3 = (int) (3.0f / MachineData.dXScale);
        List<SinglePathData> pathDataList = getPathDataList();
        for (int i4 = 0; i4 < pathDataList.size() - 1; i4 += 2) {
            SinglePathData singlePathData = pathDataList.get(i4);
            SinglePathData singlePathData2 = pathDataList.get(i4 + 1);
            List<DestPoint> destPointList = singlePathData.getDestPointList();
            List<DestPoint> destPointList2 = singlePathData2.getDestPointList();
            if (destPointList == null || destPointList2 == null || destPointList.size() == 0 || destPointList2.size() == 0) {
                return;
            }
            for (int i5 = 0; i5 < destPointList.size(); i5++) {
                DestPoint destPoint3 = destPointList.get(i5);
                destPoint3.setDepth(destPoint3.getDepth() - i3);
            }
            for (int i6 = 0; i6 < destPointList2.size(); i6++) {
                DestPoint destPoint4 = destPointList2.get(i6);
                destPoint4.setDepth(destPoint4.getDepth() - i3);
            }
            int max = Math.max(destPointList.size(), destPointList2.size()) - 1;
            for (int i7 = max; i7 >= 0; i7--) {
                if (i7 <= destPointList.size() - 1) {
                    DestPoint destPoint5 = destPointList.get(i7);
                    if (!destPoint5.isGapPointMoreCut()) {
                        int i8 = max;
                        int i9 = i8;
                        int i10 = Integer.MAX_VALUE;
                        while (i8 >= 0) {
                            if (i8 <= destPointList2.size() - 1) {
                                DestPoint destPoint6 = destPointList2.get(i8);
                                if (!destPoint6.isGapPointMoreCut() && Math.abs(destPoint6.getSpace() - destPoint5.getSpace()) <= i10) {
                                    i10 = Math.abs(destPoint6.getSpace() - destPoint5.getSpace());
                                    i9 = i8;
                                }
                            }
                            i8--;
                        }
                        if (i10 < UnitConvertUtil.xKeyCmm2Steps(70)) {
                            i2 = (getDecodeWidth() - destPoint5.getDepth()) - destPointList2.get(i9).getDepth();
                        } else {
                            if (i9 == destPointList2.size() - 1) {
                                destPoint2 = DestPointFactory.getDestPoint(destPoint5.getSpace(), destPointList2.get(i9).getDepth());
                                destPointList2.add(destPoint2);
                            } else if (i9 == 0) {
                                destPoint2 = DestPointFactory.getDestPoint(destPoint5.getSpace(), destPointList2.get(i9).getDepth());
                                destPointList2.add(0, destPoint2);
                            } else {
                                DestPoint destPoint7 = destPointList2.get(i9);
                                if (destPoint7.getSpace() > destPoint5.getSpace()) {
                                    DestPoint destPoint8 = destPointList2.get(i9 - 1);
                                    if (destPoint8.getSpace() - destPoint7.getSpace() == 0) {
                                        depth4 = destPoint7.getDepth();
                                    } else {
                                        depth4 = destPoint7.getDepth() + (((destPoint8.getDepth() - destPoint7.getDepth()) * (destPoint5.getSpace() - destPoint7.getSpace())) / (destPoint8.getSpace() - destPoint7.getSpace()));
                                    }
                                    destPoint2 = DestPointFactory.getDestPoint(destPoint5.getSpace(), depth4);
                                    destPointList2.add(i9, destPoint2);
                                } else {
                                    int i11 = i9 + 1;
                                    DestPoint destPoint9 = destPointList2.get(i11);
                                    if (destPoint9.getSpace() - destPoint7.getSpace() == 0) {
                                        depth3 = destPoint7.getDepth();
                                    } else {
                                        depth3 = destPoint7.getDepth() + (((destPoint9.getDepth() - destPoint7.getDepth()) * (destPoint5.getSpace() - destPoint7.getSpace())) / (destPoint9.getSpace() - destPoint7.getSpace()));
                                    }
                                    destPoint2 = DestPointFactory.getDestPoint(destPoint5.getSpace(), depth3);
                                    destPointList2.add(i11, destPoint2);
                                }
                            }
                            int decodeWidth = (getDecodeWidth() - destPoint5.getDepth()) - destPoint2.getDepth();
                            destPoint2.setGroove(decodeWidth);
                            i2 = decodeWidth;
                        }
                        destPoint5.setGroove(i2);
                    }
                }
            }
            for (int i12 = max; i12 >= 0; i12--) {
                if (i12 <= destPointList2.size() - 1) {
                    DestPoint destPoint10 = destPointList2.get(i12);
                    if (!destPoint10.isGapPointMoreCut()) {
                        int i13 = max;
                        int i14 = i13;
                        int i15 = Integer.MAX_VALUE;
                        while (i13 >= 0) {
                            if (i13 <= destPointList.size() - 1) {
                                DestPoint destPoint11 = destPointList.get(i13);
                                if (!destPoint11.isGapPointMoreCut() && Math.abs(destPoint11.getSpace() - destPoint10.getSpace()) <= i15) {
                                    i15 = Math.abs(destPoint11.getSpace() - destPoint10.getSpace());
                                    i14 = i13;
                                }
                            }
                            i13--;
                        }
                        if (i15 < UnitConvertUtil.xKeyCmm2Steps(70)) {
                            i = (getDecodeWidth() - destPoint10.getDepth()) - destPointList.get(i14).getDepth();
                        } else {
                            if (i14 == destPointList.size() - 1) {
                                destPoint = DestPointFactory.getDestPoint(destPoint10.getSpace(), destPointList.get(i14).getDepth());
                                destPointList.add(destPoint);
                            } else if (i14 == 0) {
                                destPoint = DestPointFactory.getDestPoint(destPoint10.getSpace(), destPointList.get(i14).getDepth());
                                destPointList.add(0, destPoint);
                            } else {
                                DestPoint destPoint12 = destPointList.get(i14);
                                if (destPoint12.getSpace() > destPoint10.getSpace()) {
                                    DestPoint destPoint13 = destPointList.get(i14 - 1);
                                    if (destPoint13.getSpace() - destPoint12.getSpace() == 0) {
                                        depth2 = destPoint12.getDepth();
                                    } else {
                                        depth2 = destPoint12.getDepth() + (((destPoint13.getDepth() - destPoint12.getDepth()) * (destPoint10.getSpace() - destPoint12.getSpace())) / (destPoint13.getSpace() - destPoint12.getSpace()));
                                    }
                                    destPoint = DestPointFactory.getDestPoint(destPoint10.getSpace(), depth2);
                                    destPointList.add(i14, destPoint);
                                } else {
                                    int i16 = i14 + 1;
                                    DestPoint destPoint14 = destPointList.get(i16);
                                    if (destPoint14.getSpace() - destPoint12.getSpace() == 0) {
                                        depth = destPoint12.getDepth();
                                    } else {
                                        depth = destPoint12.getDepth() + (((destPoint14.getDepth() - destPoint12.getDepth()) * (destPoint10.getSpace() - destPoint12.getSpace())) / (destPoint14.getSpace() - destPoint12.getSpace()));
                                    }
                                    destPoint = DestPointFactory.getDestPoint(destPoint10.getSpace(), depth);
                                    destPointList.add(i16, destPoint);
                                }
                            }
                            int decodeWidth2 = (getDecodeWidth() - destPoint10.getDepth()) - destPoint.getDepth();
                            destPoint.setGroove(decodeWidth2);
                            i = decodeWidth2;
                        }
                        destPoint10.setGroove(i);
                    }
                }
            }
            Collections.sort(destPointList, new Comparator<DestPoint>() { // from class: com.liying.core.operation.duplicateCut.cutpath.DuplicateInsideGrooveKeyCut.1
                @Override // java.util.Comparator
                public int compare(DestPoint destPoint15, DestPoint destPoint16) {
                    return destPoint15.getSpace() - destPoint16.getSpace();
                }
            });
            Collections.sort(destPointList2, new Comparator<DestPoint>() { // from class: com.liying.core.operation.duplicateCut.cutpath.DuplicateInsideGrooveKeyCut.2
                @Override // java.util.Comparator
                public int compare(DestPoint destPoint15, DestPoint destPoint16) {
                    return destPoint15.getSpace() - destPoint16.getSpace();
                }
            });
        }
    }

    public void addEndPointList() {
        for (int i = 0; i < getPathDataList().size(); i++) {
            SinglePathData singlePathData = getPathDataList().get(i);
            if (i == 0 || i == getPathDataList().size() - 1) {
                addEndPointSide(singlePathData);
            } else {
                addEndPointMid(i, singlePathData);
            }
        }
    }

    private void addEndPointMid(int i, SinglePathData singlePathData) {
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        List<DestPoint> destPointList = singlePathData.getDestPointList();
        DestPoint destPoint = destPointList.get(destPointList.size() - 1);
        if (cutLength - destPoint.getSpace() > UnitConvertUtil.xKeyCmm2Steps(200)) {
            if (i % 2 != 0) {
                int yKeyCmm2Steps = UnitConvertUtil.yKeyCmm2Steps(10);
                List<DestPoint> destPointList2 = getPathDataList().get(i + 1).getDestPointList();
                DestPoint destPoint2 = destPointList2.get(destPointList2.size() - 1);
                int decodeWidth = (((getDecodeWidth() - destPoint2.getDepth()) + destPoint.getDepth()) / 2) - yKeyCmm2Steps;
                int space = destPoint.getSpace() + getCutterRadiusSize2();
                destPointList.add(DestPointFactory.getGapDestPoint(space, decodeWidth));
                destPointList.add(DestPointFactory.getWudihuMoreCutDestPoint(space, decodeWidth - UnitConvertUtil.yKeyCmm2Steps(50)));
                int decodeWidth2 = (((getDecodeWidth() - destPoint.getDepth()) + destPoint2.getDepth()) / 2) - yKeyCmm2Steps;
                destPointList2.add(DestPointFactory.getGapDestPoint(space, decodeWidth2));
                destPointList2.add(DestPointFactory.getWudihuMoreCutDestPoint(space, decodeWidth2 - UnitConvertUtil.yKeyCmm2Steps(50)));
                return;
            }
            return;
        }
        if (i % 2 != 0) {
            int yKeyCmm2Steps2 = UnitConvertUtil.yKeyCmm2Steps(15);
            List<DestPoint> destPointList3 = getPathDataList().get(i + 1).getDestPointList();
            DestPoint destPoint3 = destPointList3.get(destPointList3.size() - 1);
            destPointList.add(DestPointFactory.getDestPoint(cutLength, (((getDecodeWidth() - destPoint3.getDepth()) + destPoint.getDepth()) / 2) + yKeyCmm2Steps2));
            destPointList3.add(DestPointFactory.getDestPoint(cutLength, (((getDecodeWidth() - destPoint.getDepth()) + destPoint3.getDepth()) / 2) + yKeyCmm2Steps2));
        }
    }

    private void addEndPointSide(SinglePathData singlePathData) {
        int cutLength = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getCutLength() : 0;
        List<DestPoint> destPointList = singlePathData.getDestPointList();
        DestPoint destPoint = destPointList.get(destPointList.size() - 1);
        DestPoint calculateEndPoint = calculateEndPoint(destPoint, DestPointFactory.getDestPoint(cutLength, 0));
        DestPoint destPoint2 = null;
        int size = destPointList.size() - 2;
        while (true) {
            if (size < 0) {
                break;
            }
            DestPoint destPoint3 = destPointList.get(size);
            if (destPoint.getSpace() - destPoint3.getSpace() > UnitConvertUtil.xKeyCmm2Steps(200)) {
                destPoint2 = destPoint3;
                break;
            }
            size--;
        }
        if (destPoint2 == null) {
            destPointList.add(calculateEndPoint);
            return;
        }
        Line line = new Line(new PointF(destPoint2.getSpace(), destPoint2.getDepth()), new PointF(destPoint.getSpace(), destPoint.getDepth()));
        PointF crossPoint = LineUtils.getCrossPoint(line, new Line(new PointF(0.0f, 0.0f), new PointF(1.0f, 0.0f)));
        if (crossPoint != null && crossPoint.x <= cutLength && crossPoint.x >= destPoint.getSpace()) {
            destPointList.add(calculateEndPoint(destPoint, DestPointFactory.getDestPoint((int) crossPoint.x, 0)));
            return;
        }
        float f = cutLength;
        PointF crossPoint2 = LineUtils.getCrossPoint(line, new Line(new PointF(f, 0.0f), new PointF(f, 1.0f)));
        if (crossPoint2 != null && crossPoint2.y >= 0.0f) {
            destPointList.add(calculateEndPoint(destPoint, DestPointFactory.getDestPoint(cutLength, (int) crossPoint2.y)));
        } else {
            destPointList.add(calculateEndPoint);
        }
    }

    protected int[] splitCut(DestPoint destPoint, int i) {
        int groove = destPoint.getGroove();
        int depth = destPoint.getDepth();
        int i2 = i * 2;
        int[] iArr = new int[i2];
        int cutterSize2 = groove - ToolSizeManager.getCutterSize2();
        if (cutterSize2 < 0) {
            cutterSize2 = 0;
        }
        float f = (cutterSize2 * 1.0f) / (i2 - 1);
        if (f > getMaxCut()) {
            f = getMaxCut();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = ((int) (i3 * f)) + depth + getCutterRadiusSize2();
        }
        return iArr;
    }

    private int[] cutZ() {
        int i;
        float f;
        float f2;
        int cutDepth = getDuplicateKeyData().getCutDepth();
        int layer = getLayer();
        int[] iArr = new int[layer];
        for (int i2 = 0; i2 < layer; i2++) {
            if (layer == 2) {
                f = cutDepth;
                f2 = layer2[i2];
            } else if (layer == 3) {
                f = cutDepth;
                f2 = layer3[i2];
            } else {
                i = (cutDepth / layer) * (i2 + 1);
                iArr[i2] = getKeyFace() + i;
            }
            i = (int) (f * f2);
            iArr[i2] = getKeyFace() + i;
        }
        return iArr;
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
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
}
