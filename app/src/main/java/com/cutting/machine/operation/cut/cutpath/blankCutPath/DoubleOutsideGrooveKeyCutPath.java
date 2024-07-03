package com.cutting.machine.operation.cut.cutpath.blankCutPath;

import android.text.TextUtils;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.operation.cut.cutpath.KeyPoint;
import com.cutting.machine.operation.cut.cutpath.KeyPointFactory;
import com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DoubleOutsideGrooveKeyCutPath extends KeyBlankCutPath {
    private static final int minCutTime = 3;
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.65f, 0.3f, 0.0f};
    private static final float[] fourCut = {0.7f, 0.4f, 0.1f, 0.0f};

    public DoubleOutsideGrooveKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 1;
        }
        int ceil = (int) Math.ceil((getWidth() * 0.5d) / getMaxCut());
        if (ceil < 3) {
            return 3;
        }
        return ceil;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        List<KeyPoint> list;
        int i;
        List<List<KeyPoint>> keyPointsGroup = getKeyPointsGroup();
        List<List<KeyPoint>> startPointsGroup = getStartPointsGroup();
        List<List<KeyPoint>> endPointsGroup = getEndPointsGroup();
        fixSpace(keyPointsGroup);
        for (int i2 = 0; i2 < keyPointsGroup.size(); i2++) {
            keyPointsGroup.get(i2).addAll(0, startPointsGroup.get(i2));
            keyPointsGroup.get(i2).addAll(endPointsGroup.get(i2));
        }
        ArrayList<List<KeyPoint>> arrayList = new ArrayList<List<KeyPoint>>();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < cutCount(); i3++) {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            arrayList.add(arrayList3);
            arrayList2.add(arrayList4);
        }
        for (int i4 = 0; i4 < keyPointsGroup.size(); i4++) {
            List<KeyPoint> list2 = keyPointsGroup.get(i4);
            for (int i5 = 0; i5 < list2.size(); i5++) {
                KeyPoint keyPoint = list2.get(i5);
                int[] splitCut = splitCut(keyPoint.getY());
                for (int i6 = 0; i6 < cutCount(); i6++) {
                    if (i4 == 0) {
                        list = arrayList.get(i6);
                    } else {
                        list = (List<KeyPoint>) arrayList2.get(i6);
                    }
                    if (keyPoint.isDoNotSplitDepth()) {
                        i = keyPoint.getY();
                    } else {
                        i = splitCut[i6];
                    }
                    list.add(KeyPointFactory.getKeyPoint(keyPoint.getX(), i));
                }
            }
        }
        return generateKeyCutSteps(arrayList, arrayList2);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getEndPointsGroup() {
        ArrayList arrayList = new ArrayList();
        List<List<KeyPoint>> r3 = getKeyPointsGroup();
        for (int i = 0; i < r3.size(); i++) {
            KeyPoint keyPoint = getKeyPointsGroup().get(i).get(r3.size() - 1);
            ArrayList arrayList2 = new ArrayList();
            int shoulder2TipDistance = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getShoulder2TipDistance() : 0;
            arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, (getWidth() / 2) + 40)));
            arrayList2.add(KeyPointFactory.getKeyPoint(shoulder2TipDistance + getCutterSize(), (getWidth() / 2) + 40));
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list, List<List<KeyPoint>> list2) {
        int tipCutter;
        int xKey2Machine;
        int tipCutter2;
        int xKey2Machine2;
        String str;
        int i;
        List<KeyPoint> list3;
        int i2;
        String str2;
        int i3;
        int i4;
        DoubleOutsideGrooveKeyCutPath doubleOutsideGrooveKeyCutPath = this;
        List<List<KeyPoint>> list4 = list;
        int cutZ = cutZ();
        int zUp = doubleOutsideGrooveKeyCutPath.zUp(cutZ);
        ArrayList arrayList = new ArrayList();
        int leftCutter = getLeftCutter();
        int rightCutter = getRightCutter();
        int i5 = (leftCutter + rightCutter) / 2;
        if (list4 == null || list.size() == 0) {
            return null;
        }
        int yKey2Machine = leftCutter + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int size = list.size();
        int i6 = 0;
        while (i6 < size) {
            List<KeyPoint> list5 = list4.get(i6);
            int i7 = 0;
            while (i7 < list5.size()) {
                KeyPoint keyPoint = list5.get(i7);
                int x = keyPoint.getX();
                int yKey2Machine2 = rightCutter + UnitConvertUtil.yKey2Machine(keyPoint.getY());
                if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    tipCutter2 = getShoulderCutter();
                    xKey2Machine2 = UnitConvertUtil.xKey2Machine(x);
                } else {
                    tipCutter2 = getTipCutter();
                    xKey2Machine2 = UnitConvertUtil.xKey2Machine(x);
                }
                int i8 = tipCutter2 + xKey2Machine2;
                if (i7 != 0) {
                    str = "点";
                    i = i7;
                    list3 = list5;
                    i2 = leftCutter;
                    str2 = "刀,第";
                    i3 = i6;
                    i4 = size;
                } else if (i6 == 0) {
                    str2 = "刀,第";
                    arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i8, yKey2Machine, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    str = "点";
                    i = i7;
                    list3 = list5;
                    i2 = leftCutter;
                    i3 = i6;
                    i4 = size;
                    arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i8, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", i8, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                } else {
                    str = "点";
                    i = i7;
                    list3 = list5;
                    i2 = leftCutter;
                    str2 = "刀,第";
                    i3 = i6;
                    i4 = size;
                    arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i8, yKey2Machine, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i8, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                }
                int i9 = i + 1;
                String sb = "左边第" +
                        (i3 + 1) +
                        str2 +
                        i9 +
                        str;
                arrayList.add(StepBeanFactory.getCutStepBean(sb, i8, yKey2Machine2, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                i7 = i9;
                i6 = i3;
                size = i4;
                list5 = list3;
                leftCutter = i2;
            }
            int i10 = leftCutter;
            int i11 = i6;
            int i12 = size;
            if (list2 == null || list2.size() == 0) {
                return null;
            }
            int yKey2Machine3 = rightCutter - UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
            List<KeyPoint> list6 = list2.get(i11);
            int size2 = list6.size() - 1;
            while (size2 >= 0) {
                KeyPoint keyPoint2 = list6.get(size2);
                int x2 = keyPoint2.getX();
                int yKey2Machine4 = i10 - UnitConvertUtil.yKey2Machine(keyPoint2.getY());
                if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    tipCutter = getShoulderCutter();
                    xKey2Machine = UnitConvertUtil.xKey2Machine(x2);
                } else {
                    tipCutter = getTipCutter();
                    xKey2Machine = UnitConvertUtil.xKey2Machine(x2);
                }
                int i13 = tipCutter + xKey2Machine;
                int i14 = size2;
                List<KeyPoint> list7 = list6;
                arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i11 + 1) + "刀,第" + (size2 + 1) + "点", i13, yKey2Machine4, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                if (i14 == 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("离开右边", i13, yKey2Machine3, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    if (i11 < i12 - 1) {
                        arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", i13, yKey2Machine3, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    }
                }
                size2 = i14 - 1;
                list6 = list7;
            }
            i6 = i11 + 1;
            doubleOutsideGrooveKeyCutPath = this;
            list4 = list;
            size = i12;
            leftCutter = i10;
        }
        doubleOutsideGrooveKeyCutPath.backOrigin(arrayList);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[SYNTHETIC] */
    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int[] splitCut(int r10) {
        /*
            r9 = this;
            int r0 = r9.cutCount()
            int[] r1 = new int[r0]
            int r2 = r9.getWidth()
            int r2 = r2 - r10
            r3 = 0
            if (r2 >= 0) goto Lf
            r2 = 0
        Lf:
            float r2 = (float) r2
            r4 = 1065353216(0x3f800000, float:1.0)
            float r4 = r4 * r2
            float r5 = (float) r0
            float r4 = r4 / r5
        L16:
            if (r3 >= r0) goto L65
            r5 = 2
            if (r0 != r5) goto L24
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleOutsideGrooveKeyCutPath.twiceCut
            r5 = r5[r3]
        L1f:
            float r5 = r5 * r2
        L21:
            int r5 = (int) r5
            int r5 = r5 + r10
            goto L3b
        L24:
            r5 = 3
            if (r0 != r5) goto L2c
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleOutsideGrooveKeyCutPath.thriceCut
            r5 = r5[r3]
            goto L1f
        L2c:
            r5 = 4
            if (r0 != r5) goto L34
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleOutsideGrooveKeyCutPath.fourCut
            r5 = r5[r3]
            goto L1f
        L34:
            int r5 = r0 + (-1)
            int r5 = r5 - r3
            float r5 = (float) r5
            float r5 = r5 * r4
            goto L21
        L3b:
            int r6 = r9.getWidth()
            int r6 = r6 - r5
            int r7 = r9.getMaxCut()
            int r8 = r3 + 1
            int r7 = r7 * r8
            if (r6 <= r7) goto L55
            int r5 = r9.getWidth()
            int r6 = r9.getMaxCut()
            int r6 = r6 * r8
            int r5 = r5 - r6
        L55:
            boolean r6 = r9.isQuickCut()
            if (r6 == 0) goto L5c
            r5 = r10
        L5c:
            int r6 = r9.getCutterRadiusSize()
            int r5 = r5 + r6
            r1[r3] = r5
            r3 = r8
            goto L16
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleOutsideGrooveKeyCutPath.splitCut(int):int[]");
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            ArrayList arrayList2 = new ArrayList();
            String exCut = getExCut();
            if (!TextUtils.isEmpty(exCut)) {
                String[] split = exCut.split(",");
                int width = (getWidth() + Integer.parseInt(split[1])) / 2;
                int i2 = x - 250;
                int parseInt = x - Integer.parseInt(split[2]);
                arrayList2.add(KeyPointFactory.getKeyPoint(parseInt - 250, getWidth()));
                arrayList2.add(KeyPointFactory.getKeyPoint(parseInt, width));
                arrayList2.add(KeyPointFactory.getKeyPoint(i2, width));
            } else {
                arrayList2.add(KeyPointFactory.getKeyPoint(x - ((getAlign() != KeyAlign.SHOULDERS_ALIGN || x - getCutterRadiusSize() >= 260) ? 250 : (x - getCutterRadiusSize()) - 10), getWidth()));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private int cutZ() {
        return getKeyFace() + UnitConvertUtil.cmm2StepZ(getCutDepth());
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
    }
}
