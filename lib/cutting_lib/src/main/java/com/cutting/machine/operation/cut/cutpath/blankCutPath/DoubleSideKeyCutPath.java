package com.cutting.machine.operation.cut.cutpath.blankCutPath;

import android.text.TextUtils;

import androidx.core.app.NotificationManagerCompat;

import com.cutting.machine.CuttingMachine;
import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
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
public class DoubleSideKeyCutPath extends KeyBlankCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.6f, 0.2f, 0.0f};

    public DoubleSideKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 1;
        }
        int ceil = (int) Math.ceil(((getWidth() - getCutDeepest()) * 1.0d) / getMaxCut());
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
        ArrayList arrayList = new ArrayList();
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
                        list = (List<KeyPoint>) arrayList.get(i6);
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
        return generateKeyCutSteps(arrayList, arrayList2, cutTip());
    }

    public List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list, List<List<KeyPoint>> list2, boolean z) {
        int leftCutter;
        List<List<KeyPoint>> list3;
        int tipCutter;
        int xKey2Machine;
        int i;
        List<KeyPoint> list4;
        int tipCutter2;
        int xKey2Machine2;
        List<KeyPoint> list5;
        int i2;
        int i3;
        int i4;
        String str;
        String str2;
        int i5;
        int i6;
        DoubleSideKeyCutPath doubleSideKeyCutPath = this;
        List<List<KeyPoint>> list6 = list;
        int cutZ = cutZ();
        int zUp = doubleSideKeyCutPath.zUp(cutZ);
        ArrayList arrayList = new ArrayList();
        if (cutOnSingleKeyClamp()) {
            leftCutter = getRightCutter() + UnitConvertUtil.yKey2Machine(getWidth());
        } else {
            leftCutter = getLeftCutter();
        }
        int rightCutter = getRightCutter();
        int i7 = (leftCutter + rightCutter) / 2;
        if (list6 == null || list.size() == 0) {
            return null;
        }
        int yKey2Machine = leftCutter + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int size = list.size();
        int i8 = 0;
        while (i8 < size) {
            List<KeyPoint> list7 = list6.get(i8);
            int i9 = 0;
            while (i9 < list7.size()) {
                KeyPoint keyPoint = list7.get(i9);
                int x = keyPoint.getX();
                int y = keyPoint.getY();
                if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    tipCutter2 = getShoulderCutter();
                    xKey2Machine2 = UnitConvertUtil.xKey2Machine(x);
                } else {
                    tipCutter2 = getTipCutter();
                    xKey2Machine2 = UnitConvertUtil.xKey2Machine(x);
                }
                int i10 = tipCutter2 + xKey2Machine2;
                int yKey2Machine2 = UnitConvertUtil.yKey2Machine(y) + i7;
                if (CuttingMachine.getInstance().isE9() && getKeyAlignInfo().getSlopeX() != 0) {
                    yKey2Machine2 -= (int) ((getKeyAlignInfo().getSlopeCutterX() - i10) * ClampManager.getInstance().getE9S1C().getK());
                }
                int i11 = yKey2Machine2;
                if (i9 != 0) {
                    list5 = list7;
                    i2 = i8;
                    i3 = rightCutter;
                    i4 = i7;
                    str = "刀,第";
                    str2 = "点";
                    i5 = i9;
                    i6 = size;
                } else if (i8 == 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i10, yKey2Machine, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    i4 = i7;
                    str2 = "点";
                    str = "刀,第";
                    i3 = rightCutter;
                    i5 = i9;
                    list5 = list7;
                    i2 = i8;
                    i6 = size;
                    arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                } else {
                    list5 = list7;
                    i2 = i8;
                    i3 = rightCutter;
                    i4 = i7;
                    str = "刀,第";
                    str2 = "点";
                    i5 = i9;
                    i6 = size;
                    if (cutOnSingleKeyClamp()) {
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    } else {
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i10, yKey2Machine, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                }
                int i12 = i5 + 1;
                String sb = "左边第" +
                        (i2 + 1) +
                        str +
                        i12 +
                        str2;
                arrayList.add(StepBeanFactory.getCutStepBean(sb, i10, i11, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                if (i5 == list5.size() - 1) {
                    if (cutOnSingleKeyClamp()) {
                        arrayList.add(StepBeanFactory.getCutStepBean("离开左边", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    } else if (!z) {
                        arrayList.add(StepBeanFactory.getCutStepBean("离开左边", i10, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", i10, yKey2Machine, zUp, Speed.get_Speed_SpindleTurnOn_ZUp(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                    }
                }
                i8 = i2;
                i9 = i12;
                size = i6;
                i7 = i4;
                rightCutter = i3;
                list7 = list5;
            }
            int i13 = i8;
            int i14 = rightCutter;
            int i15 = i7;
            int i16 = size;
            if (cutOnSingleKeyClamp()) {
                list3 = list2;
            } else {
                list3 = list2;
                if (list3 == null || list2.size() == 0) {
                    return null;
                }
                int yKey2Machine3 = i14 - UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
                List<KeyPoint> list8 = list3.get(i13);
                int size2 = list8.size() - 1;
                while (size2 >= 0) {
                    KeyPoint keyPoint2 = list8.get(size2);
                    int x2 = keyPoint2.getX();
                    int y2 = keyPoint2.getY();
                    if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                        tipCutter = getShoulderCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x2);
                    } else {
                        tipCutter = getTipCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x2);
                    }
                    int i17 = tipCutter + xKey2Machine;
                    int yKey2Machine4 = i15 - UnitConvertUtil.yKey2Machine(y2);
                    if (CuttingMachine.getInstance().isE9() && getKeyAlignInfo().getSlopeX() != 0) {
                        yKey2Machine4 -= (int) ((getKeyAlignInfo().getSlopeCutterX() - i17) * ClampManager.getInstance().getE9S1C().getK());
                    }
                    int i18 = yKey2Machine4;
                    if (size2 != list8.size() - 1 || z) {
                        i = size2;
                        list4 = list8;
                    } else {
                        i = size2;
                        list4 = list8;
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至最后一个点位旁边", i17, yKey2Machine3, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i17, yKey2Machine3, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("左边第" + (i13 + 1) + "刀,第" + (i + 1) + "点", i17, i18, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    if (i == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBean("离开右边", i17, yKey2Machine3, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i13 < i16 - 1) {
                            arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", i17, yKey2Machine3, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        }
                    }
                    size2 = i - 1;
                    list8 = list4;
                }
            }
            i8 = i13 + 1;
            doubleSideKeyCutPath = this;
            size = i16;
            i7 = i15;
            rightCutter = i14;
            list6 = list;
        }
        doubleSideKeyCutPath.backOrigin(arrayList);
        return arrayList;
    }

    private boolean cutOnSingleKeyClamp() {
        return getClamp() == Clamp.S2_A || getClamp() == Clamp.S2_B || getClamp() == Clamp.E9S2A || getClamp() == Clamp.E9S2B;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054 A[SYNTHETIC] */
    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int[] splitCut(int r11) {
        /*
            r10 = this;
            int r0 = r10.cutCount()
            int[] r1 = new int[r0]
            int r2 = r10.getWidth()
            int r2 = r2 - r11
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
            if (r3 >= r0) goto L63
            r5 = 2
            if (r0 != r5) goto L24
            float[] r6 = com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleSideKeyCutPath.twiceCut
            r6 = r6[r3]
        L1f:
            float r6 = r6 * r2
        L21:
            int r6 = (int) r6
            int r6 = r6 + r11
            goto L33
        L24:
            r6 = 3
            if (r0 != r6) goto L2c
            float[] r6 = com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleSideKeyCutPath.thriceCut
            r6 = r6[r3]
            goto L1f
        L2c:
            int r6 = r0 + (-1)
            int r6 = r6 - r3
            float r6 = (float) r6
            float r6 = r6 * r4
            goto L21
        L33:
            int r7 = r10.getWidth()
            int r7 = r7 - r6
            int r8 = r10.getMaxCut()
            int r9 = r3 + 1
            int r8 = r8 * r9
            if (r7 <= r8) goto L4d
            int r6 = r10.getWidth()
            int r7 = r10.getMaxCut()
            int r7 = r7 * r9
            int r6 = r6 - r7
        L4d:
            boolean r7 = r10.isQuickCut()
            if (r7 == 0) goto L54
            r6 = r11
        L54:
            int r7 = r10.getCutterRadiusSize()
            int r6 = r6 + r7
            int r7 = r10.getWidth()
            int r7 = r7 / r5
            int r6 = r6 - r7
            r1[r3] = r6
            r3 = r9
            goto L16
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.operation.cut.cutpath.blankCutPath.DoubleSideKeyCutPath.splitCut(int):int[]");
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getEndPointsGroup() {
        int i;
        int shoulder2TipDistance;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getKeyPointsGroup().size(); i2++) {
            List<KeyPoint> list = getKeyPointsGroup().get(i2);
            int x = list.get(list.size() - 1).getX();
            ArrayList arrayList2 = new ArrayList();
            String cutSharpenType = getCutSharpenType();
            int parseInt = !TextUtils.isEmpty(cutSharpenType) ? Integer.parseInt(cutSharpenType.split(",")[0]) : 0;
            if (parseInt == 0) {
                arrayList2.add(KeyPointFactory.getKeyPoint(x + 200, getWidth()));
            } else if (parseInt == 1) {
                if (getAlign() == KeyAlign.FRONTEND_ALIGN) {
                    shoulder2TipDistance = getCutterSize();
                } else {
                    shoulder2TipDistance = getShoulder2TipDistance() + getCutterSize();
                }
                arrayList2.add(KeyPointFactory.getKeyPoint(shoulder2TipDistance, getWidth()));
            } else if (parseInt == 2) {
                String[] split = cutSharpenType.split(",");
                int parseInt2 = Integer.parseInt(split[1]);
                int parseInt3 = Integer.parseInt(split[2]);
                int width = (getWidth() + Integer.parseInt(split[3])) / 2;
                int width2 = (getWidth() + parseInt2) / 2;
                int i3 = 30;
                if (getAlign() == KeyAlign.FRONTEND_ALIGN) {
                    i = -parseInt3;
                } else {
                    int shoulder2TipDistance2 = getShoulder2TipDistance();
                    i3 = 30 + shoulder2TipDistance2;
                    i = shoulder2TipDistance2 - parseInt3;
                }
                arrayList2.add(KeyPointFactory.getKeyPoint(i, width));
                arrayList2.add(KeyPointFactory.getKeyPoint(i3, width2));
                arrayList2.add(KeyPointFactory.getKeyPoint(i3 + getCutterSize(), width2));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private boolean cutTip() {
        String cutSharpenType = getCutSharpenType();
        return (!TextUtils.isEmpty(cutSharpenType) ? Integer.parseInt(cutSharpenType.split(",")[0]) : 0) != 0;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            ArrayList arrayList2 = new ArrayList();
            int i2 = 250;
            if (getAlign() == KeyAlign.SHOULDERS_ALIGN && x - getCutterRadiusSize() < 260) {
                i2 = (x - getCutterRadiusSize()) - 10;
            }
            if (getClamp() == Clamp.S6) {
                i2 = 50;
            }
            arrayList2.add(KeyPointFactory.getKeyPoint(x - i2, getWidth()));
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private int cutZ() {
        int i;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3;
        Clamp clamp = getClamp();
        if (clamp == Clamp.S1_C) {
            f3 = MachineData.dZScale;
        } else {
            if (clamp == Clamp.S6) {
                if (getClampMode() == 1) {
                    f3 = MachineData.dZScale;
                } else {
                    f = -250.0f;
                    f2 = MachineData.dZScale;
                }
            } else if (clamp == Clamp.S1_D) {
                f = -400.0f;
                f2 = MachineData.dZScale;
            } else if (clamp == Clamp.E9S1C) {
                f = -150.0f;
                f2 = MachineData.dZScale;
            } else {
                if (getClamp() == Clamp.E9S2A || getClamp() == Clamp.E9S2B) {
                    return getKeyFace() - UnitConvertUtil.cmm2StepZ(getSingleSideKeyCutDepthFix() + 230);
                }
                if (getClamp() == Clamp.S2_A || getClamp() == Clamp.S2_B) {
                    return getKeyFace() + UnitConvertUtil.cmm2StepZ(900 - getSingleSideKeyCutDepthFix());
                }
                i = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                return getClampFace() + (i * getZDirection());
            }
            i = (int) (f / f2);
            return getClampFace() + (i * getZDirection());
        }
        i = (int) ((-300.0f) / f3);
        return getClampFace() + (i * getZDirection());
    }

    private int zUp(int i) {
        float f;
        float f2;
        if (getClamp() == Clamp.S6) {
            f = -900.0f;
            f2 = MachineData.dZScale;
        } else if (getClamp() == Clamp.E9S1C) {
            f = -600.0f;
            f2 = MachineData.dZScale;
        } else {
            f = -500.0f;
            f2 = MachineData.dZScale;
        }
        return i + (((int) (f / f2)) * getZDirection());
    }
}
