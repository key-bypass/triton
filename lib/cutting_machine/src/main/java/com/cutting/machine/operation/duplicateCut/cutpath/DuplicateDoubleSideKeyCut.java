package com.liying.core.operation.duplicateCut.cutpath;

import androidx.core.app.NotificationManagerCompat;
import com.liying.core.CuttingMachine;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.DestPoint;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.MachineData;
import com.liying.core.communication.OperationManager;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.operation.duplicateCut.DuplicateCutParams;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class DuplicateDoubleSideKeyCut extends DuplicateCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.6f, 0.2f, 0.0f};

    public DuplicateDoubleSideKeyCut(DuplicateCutParams duplicateCutParams) {
        super(duplicateCutParams);
    }

    @Override // com.liying.core.operation.duplicateCut.cutpath.DuplicateCutPath
    public List<StepBean> getCutPathSteps() {
        ArrayList arrayList;
        int i;
        int i2;
        int keyY2MachineValueBaseCenterLeft;
        int KeyY2MachineValueBaseCenterRight;
        int i3;
        ArrayList arrayList2;
        int i4;
        String str;
        String str2;
        List<DestPoint> list;
        int i5;
        String str3;
        DuplicateDoubleSideKeyCut duplicateDoubleSideKeyCut = this;
        if (riskCutClamp()) {
            return null;
        }
        removeShoulderPoint();
        removeInvalidPoint();
        fixSpaceWidth();
        addEndPointList();
        int cutZ = cutZ();
        int zUp = duplicateDoubleSideKeyCut.zUp(cutZ);
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
                            int keyX2MachineValue = duplicateDoubleSideKeyCut.keyX2MachineValue(destPoint.getSpace()) - UnitConvertUtil.xKey2Machine(5);
                            if (destPoint.isDoNotSplitDepth()) {
                                KeyY2MachineValueBaseCenterRight = duplicateDoubleSideKeyCut.KeyY2MachineValueBaseCenterRight(destPoint.getDepth());
                            } else {
                                KeyY2MachineValueBaseCenterRight = duplicateDoubleSideKeyCut.KeyY2MachineValueBaseCenterRight(duplicateDoubleSideKeyCut.splitCut(destPoint.getDepth())[i6]);
                            }
                            if (CuttingMachine.getInstance().isE9() && getKeyAlignInfo().getSlopeX() != 0) {
                                KeyY2MachineValueBaseCenterRight -= (int) ((getKeyAlignInfo().getSlopeCutterX() - keyX2MachineValue) * ClampManager.getInstance().getE9S1C().getK());
                            }
                            int i8 = KeyY2MachineValueBaseCenterRight;
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
                        duplicateDoubleSideKeyCut = this;
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
                        int keyX2MachineValue2 = keyX2MachineValue(destPoint2.getSpace()) - UnitConvertUtil.xKey2Machine(5);
                        if (destPoint2.isDoNotSplitDepth()) {
                            keyY2MachineValueBaseCenterLeft = keyY2MachineValueBaseCenterLeft(destPoint2.getDepth());
                        } else {
                            keyY2MachineValueBaseCenterLeft = keyY2MachineValueBaseCenterLeft(splitCut(destPoint2.getDepth())[i2]);
                        }
                        if (CuttingMachine.getInstance().isE9() && getKeyAlignInfo().getSlopeX() != 0) {
                            keyY2MachineValueBaseCenterLeft -= (int) ((getKeyAlignInfo().getSlopeCutterX() - keyX2MachineValue2) * ClampManager.getInstance().getE9S1C().getK());
                        }
                        String str7 = str6;
                        arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i2 + 1) + str6 + (size + 1) + "点", keyX2MachineValue2, keyY2MachineValueBaseCenterLeft, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
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
                duplicateDoubleSideKeyCut = this;
                i6 = i2;
            }
            i6++;
            duplicateDoubleSideKeyCut = duplicateDoubleSideKeyCut;
        }
        DuplicateDoubleSideKeyCut duplicateDoubleSideKeyCut2 = duplicateDoubleSideKeyCut;
        ArrayList arrayList4 = arrayList3;
        duplicateDoubleSideKeyCut2.backOrigin(arrayList4);
        return arrayList4;
    }

    public void addEndPointList() {
        double xKeyCmm2Steps;
        int cutterRadiusSize2;
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            List<DestPoint> destPointList = it.next().getDestPointList();
            if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                xKeyCmm2Steps = getDecodeLength() + UnitConvertUtil.xKeyCmm2Steps(30);
                cutterRadiusSize2 = getCutterRadiusSize2();
            } else {
                xKeyCmm2Steps = UnitConvertUtil.xKeyCmm2Steps(30);
                cutterRadiusSize2 = getCutterRadiusSize2();
            }
            destPointList.add(DestPointFactory.getDoNotSplitDestPoint((int) (xKeyCmm2Steps + (cutterRadiusSize2 * 1.414d)), 0));
        }
    }

    public boolean riskCutClamp() {
        if (getAlign() != KeyAlign.FRONTEND_ALIGN || getDecodeLength() <= getCutLength() + UnitConvertUtil.xKeyCmm2Steps(200)) {
            return false;
        }
        ErrorHelper.handleError(ErrorCode.RiskCutClampShoulderS1dTipAlign);
        return true;
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
                            i++;
                        } else {
                            destPoint.setInvalid(false);
                            break;
                        }
                    } else if (space + getCutLength() <= getCutterRadiusSize2() + UnitConvertUtil.xKeyCmm2Steps(10)) {
                        destPoint.setInvalid(true);
                        i++;
                    } else {
                        destPoint.setInvalid(false);
                        break;
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

    public void fixSpaceWidth() {
        Iterator<SinglePathData> it = getPathDataList().iterator();
        while (it.hasNext()) {
            fixSpaceWidth(it.next().getDestPointList());
        }
    }

    private void fixSpaceWidth(List<DestPoint> list) {
        int highPointSpaceFix;
        if (list == null || list.size() <= 0) {
            return;
        }
        int i = 1;
        while (i < list.size() - 1) {
            DestPoint destPoint = list.get(i - 1);
            DestPoint destPoint2 = list.get(i);
            i++;
            DestPoint destPoint3 = list.get(i);
            float depth = ((destPoint2.getDepth() - destPoint.getDepth()) * 1.0f) / (destPoint2.getSpace() - destPoint.getSpace());
            float depth2 = ((destPoint3.getDepth() - destPoint2.getDepth()) * 1.0f) / (destPoint3.getSpace() - destPoint2.getSpace());
            int i2 = 0;
            if (Math.abs(depth) < 0.15d) {
                if (Math.abs(depth2) >= 0.15d) {
                    if (depth2 > 0.0f) {
                        highPointSpaceFix = getBottomPointSpaceFix();
                        i2 = -highPointSpaceFix;
                    } else {
                        i2 = getHighPointSpaceFix(depth2);
                    }
                }
                destPoint2.setSpace(destPoint2.getSpace() + i2);
            } else {
                if (depth > 0.0f) {
                    if (Math.abs(depth2) < 0.15d) {
                        highPointSpaceFix = getHighPointSpaceFix(depth);
                    } else if (depth2 > 0.0f) {
                        highPointSpaceFix = getHighPointSpaceFix(depth);
                    }
                    i2 = -highPointSpaceFix;
                } else if (Math.abs(depth2) < 0.15d) {
                    i2 = getBottomPointSpaceFix();
                } else if (depth2 < -0.15d) {
                    i2 = getHighPointSpaceFix(depth2);
                }
                destPoint2.setSpace(destPoint2.getSpace() + i2);
            }
        }
    }

    private int getHighPointSpaceFix(float f) {
        return (int) (((ToolSizeManager.getCutterRadiusSize2() - ToolSizeManager.getDecoderRadius2()) * 1.2f) / Math.tan((3.141592653589793d - Math.atan(Math.abs(f))) / 2.0d));
    }

    private int getBottomPointSpaceFix() {
        return (int) (getCutterRadiusSize2() * 0.7d * Math.cos(Math.asin((getCutterRadiusSize() - 2.0d) / getCutterRadiusSize())));
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
        int ceil = (int) Math.ceil((maxDepth() * 1.0d) / getMaxCut());
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
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleSideKeyCut.twiceCut
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
            float[] r6 = com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleSideKeyCut.thriceCut
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
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.duplicateCut.cutpath.DuplicateDoubleSideKeyCut.splitCut(int):int[]");
    }

    public int keyY2MachineValueBaseCenterLeft(int i) {
        return OperationManager.getInstance().getKeyAlignInfo().getKeyCenterCutter() - UnitConvertUtil.yKey2MachineDire(i);
    }

    public int KeyY2MachineValueBaseCenterRight(int i) {
        return OperationManager.getInstance().getKeyAlignInfo().getKeyCenterCutter() + UnitConvertUtil.yKey2MachineDire(i);
    }

    private int cutZ() {
        int i;
        float f;
        float f2;
        Clamp clamp = getClamp();
        if (clamp == Clamp.S1_C) {
            f = -300.0f;
            f2 = MachineData.dZScale;
        } else if (clamp == Clamp.S6) {
            f = -340.0f;
            f2 = MachineData.dZScale;
        } else if (clamp == Clamp.S1_D) {
            f = -400.0f;
            f2 = MachineData.dZScale;
        } else if (clamp == Clamp.E9S1C) {
            f = -150.0f;
            f2 = MachineData.dZScale;
        } else {
            i = NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
            return getClampFace() + UnitConvertUtil.directionZ(i);
        }
        i = (int) (f / f2);
        return getClampFace() + UnitConvertUtil.directionZ(i);
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
        return i + UnitConvertUtil.directionZ((int) (f / f2));
    }
}
