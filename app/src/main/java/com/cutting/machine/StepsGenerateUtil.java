package com.cutting.machine;

import android.graphics.PointF;
import android.text.TextUtils;
import android.util.Log;

import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.bean.KeyType;
import com.cutting.machine.bean.PointXyz;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.clamp.Clamp;
import com.cutting.machine.clamp.ClampManager;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.error.ErrorHelper;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.CircleUtils;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class StepsGenerateUtil {
    private static final String TAG = "DuplicateSteps";
    private static final int laserKeyUp = -300;
    private static final int tipLeaveSpace = (int) (50.0f / MachineData.dXScale);
    private static final int decodeHigh = (int) (30.0f / MachineData.dXScale);
    private static final int startCutLeaveSpace = (int) (150.0f / MachineData.dXScale);
    private static final int endCutLeaveSpace = (int) (100.0f / MachineData.dXScale);
    private static final int cutAngle = 90;

    public static List<StepBean> getDuplicateCutSteps(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, KeyAlignInfo keyAlignInfo2, List<DestPoint> list, List<DestPoint> list2, int i, GrooveGravity grooveGravity) {
        return null;
    }

    public static String getInsideKeySpeedStr(int i) {
        return "3000,3000,3000";
    }

    public static String getSpeedStr(int i) {
        return "7000,6000,3000";
    }

    public static int maxFeed() {
        return (int) (ToolSizeManager.getCutterSize2() * 0.9d);
    }

    public static List<StepBean> generateDuplicateDecodeSteps(DuplicateKeyData duplicateKeyData, KeyAlignInfo keyAlignInfo, int i, List<PointXyz> list) {
        int cutDepth = duplicateKeyData.getCutDepth();
        if (cutDepth == 0) {
            cutDepth = keyAlignInfo.getCutDepth();
        }
        int i2 = cutDepth / 2;
        switch (C19693.$SwitchMap$com$liying$core$bean$KeyType[duplicateKeyData.getKeyType().ordinal()]) {
            case 1:
                return getDoubleSideKeyStepBeans(keyAlignInfo, duplicateKeyData.getSide(), i);
            case 2:
                return getDoubleOutSideGrooveKeyStepBeans(keyAlignInfo, duplicateKeyData.getKeyAlign(), i, i2);
            case 3:
                return getSingleOutGrooveStepBeans(keyAlignInfo, duplicateKeyData.getKeyAlign(), duplicateKeyData.getSide(), i, i2);
            case 4:
                return getSingleSideKeyStepBeans(keyAlignInfo, duplicateKeyData.getKeyAlign(), i);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return getInsideGrooveKeyStepBeans(keyAlignInfo, i, list, i2);
            case 10:
                return getSideHoleStepBeans(keyAlignInfo, i);
            default:
                ArrayList arrayList = new ArrayList();
                arrayList.add(new StepBean(998, 0, 0, 0, getSpeedStr(0), ""));
                arrayList.add(new StepBean(999, 0, 0, 0, getSpeedStr(0), ""));
                return arrayList;
        }
    }

    private static String getDuplicateStr(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return "DU:" + i + "," + i2 + "," + i3 + "," + i4 + "," + i5 + "," + i6 + "," + i7;
    }

    private static List<StepBean> getSideHoleStepBeans(KeyAlignInfo keyAlignInfo, int i) {
        String duplicateStr;
        ArrayList arrayList = new ArrayList();
        arrayList.add(StepBeanFactory.getStepBean(0, UnitConvertUtil.xKey2Machine(-100), keyAlignInfo.getCenter(), 0, getSpeedStr(0), SL_S() + C5_Y()));
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, UnitConvertUtil.zKey2Machine(-100), getSpeedStr(1), "SL:U,Z"));
        int i2 = (int) (((float) i) / MachineData.dYScale);
        int clampFace = keyAlignInfo.getClampFace();
        ClampManager clampManager = ClampManager.getInstance();
        if (clampManager.getCurrentClamp().getClampStr().equals("S8")) {
            clampFace += clampManager.getS8().getHeight1();
        }
        int i3 = clampFace;
        if (CuttingMachine.getInstance().isE9()) {
            duplicateStr = getDuplicateStr(1, i2, 0, 1, i3, 2, 0);
        } else {
            duplicateStr = getDuplicateStr(1, i2, 0, 1, i3, 3, 0);
        }
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr));
        arrayList.add(StepBeanFactory.getStepBean(0, UnitConvertUtil.xKey2Machine(200), 0, 0, getSpeedStr(0), U_X()));
        arrayList.add(StepBeanFactory.getStepBean(998, 0, 0, 0, getSpeedStr(0), ""));
        arrayList.add(StepBeanFactory.getStepBean(999, 0, 0, 0, getSpeedStr(0), ""));
        return arrayList;
    }

    private static List<StepBean> getSingleSideKeyStepBeans(KeyAlignInfo keyAlignInfo, KeyAlign keyAlign, int i) {
        int i2;
        String duplicateStr;
        int decoderRadius = keyAlign == KeyAlign.SHOULDERS_ALIGN ? ToolSizeManager.getDecoderRadius() + 20 : 0;
        Clamp currentClamp = ClampManager.getInstance().getCurrentClamp();
        int i3 = (currentClamp == Clamp.S2_A || currentClamp == Clamp.E9S2A) ? 700 : 850;
        if (currentClamp == Clamp.S2_A || currentClamp == Clamp.S2_B) {
            i2 = 900;
        } else {
            i2 = (currentClamp == Clamp.E9S2A || currentClamp == Clamp.E9S2B) ? -200 : 0;
        }
        int left = keyAlignInfo.getLeft() + UnitConvertUtil.yKey2Machine(15);
        ArrayList arrayList = new ArrayList();
        arrayList.add(StepBeanFactory.getStepBean(0, -UnitConvertUtil.xKey2Machine(decoderRadius), UnitConvertUtil.yKey2Machine(i3), 0, getSpeedStr(0), SL_S() + SL_L()));
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, UnitConvertUtil.zKey2Machine(i2), getSpeedStr(1), "SL:U,Z"));
        int i4 = (int) (((float) i) / MachineData.dYScale);
        if (CuttingMachine.getInstance().isE9()) {
            duplicateStr = getDuplicateStr(1, i4, 0, 1, left, 0, 0);
        } else {
            duplicateStr = getDuplicateStr(0, i4, 0, 1, left, 1, 0);
        }
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr));
        arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(50), 0, getSpeedStr(0), U_Y()));
        arrayList.add(StepBeanFactory.getStepBean(998, 0, 0, 0, getSpeedStr(0), ""));
        arrayList.add(StepBeanFactory.getStepBean(999, 0, 0, 0, getSpeedStr(0), ""));
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<StepBean> getSingleOutGrooveStepBeans(KeyAlignInfo r11, KeyAlign r12, int r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.getSingleOutGrooveStepBeans(com.cutting.machine.KeyAlignInfo, com.cutting.machine.bean.KeyAlign, int, int, int):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<StepBean> getDoubleOutSideGrooveKeyStepBeans(KeyAlignInfo r11, KeyAlign r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.getDoubleOutSideGrooveKeyStepBeans(com.cutting.machine.KeyAlignInfo, com.cutting.machine.bean.KeyAlign, int, int):java.util.List");
    }

    private static String SL_L() {
        return CuttingMachine.getInstance().isE9() ? "SL:L,Y;" : "SL:L,X;";
    }

    private static String SL_R() {
        return CuttingMachine.getInstance().isE9() ? "SL:R,Y;" : "SL:R,X;";
    }

    private static String SL_S() {
        return CuttingMachine.getInstance().isE9() ? "SL:S,X;" : "SL:S,Y;";
    }

    private static String SL_T() {
        return CuttingMachine.getInstance().isE9() ? "SL:T,X;" : "SL:T,Y;";
    }

    private static String U_X() {
        return CuttingMachine.getInstance().isE9() ? "U:X;" : "U:Y;";
    }

    private static String U_Y() {
        return CuttingMachine.getInstance().isE9() ? "U:Y;" : "U:X;";
    }

    /* renamed from: C2 */
    private static String m92C2(int i, int i2) {
        if (CuttingMachine.getInstance().isE9()) {
            return "C:2,Y,Y-" + i + ",Y-" + i2;
        }
        return "C:2,X,X-" + i + ",X-" + i2;
    }

    private static String C5_Y() {
        return CuttingMachine.getInstance().isE9() ? "C:5,Y;" : "C:5,X;";
    }

    private static String C5_X() {
        return CuttingMachine.getInstance().isE9() ? "C:5,X;" : "C:5,Y;";
    }

    private static List<StepBean> getDoubleSideKeyStepBeans(KeyAlignInfo keyAlignInfo, int i, int i2) {
        int zKey2Machine;
        String duplicateStr;
        String duplicateStr2;
        int center = keyAlignInfo.getCenter();
        ArrayList arrayList = new ArrayList();
        arrayList.add(StepBeanFactory.getStepBean(0, -UnitConvertUtil.xKey2Machine(ToolSizeManager.getDecoderRadius() + 20), UnitConvertUtil.yKey2Machine(200), 0, getSpeedStr(0), SL_S() + SL_L()));
        if (CuttingMachine.getInstance().isE9()) {
            zKey2Machine = UnitConvertUtil.zKey2Machine(-200);
        } else {
            zKey2Machine = UnitConvertUtil.zKey2Machine(-400);
        }
        int i3 = zKey2Machine;
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, i3, getSpeedStr(1), "SL:Z,Z"));
        int i4 = (int) (i2 / MachineData.dYScale);
        if (CuttingMachine.getInstance().isE9()) {
            duplicateStr = getDuplicateStr(1, i4, 0, 2, center, 0, 0);
        } else {
            duplicateStr = getDuplicateStr(0, i4, 0, 2, center, 1, 0);
        }
        arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr));
        arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(50), 0, getSpeedStr(0), U_Y()));
        if (i == 3) {
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, UnitConvertUtil.cmm2StepZ(-600), getSpeedStr(0), "U:Z;"));
            arrayList.add(StepBeanFactory.getStepBean(0, -UnitConvertUtil.xKey2Machine(ToolSizeManager.getDecoderRadius() + 20), UnitConvertUtil.yKey2Machine(-200), 0, getSpeedStr(0), SL_S() + SL_R()));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, i3, getSpeedStr(1), "SL:Z,Z"));
            if (CuttingMachine.getInstance().isE9()) {
                duplicateStr2 = getDuplicateStr(0, i4, 0, 1, center, 0, 0);
            } else {
                duplicateStr2 = getDuplicateStr(1, i4, 0, 1, center, 1, 0);
            }
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr2));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(-50), 0, getSpeedStr(0), U_Y()));
        }
        arrayList.add(StepBeanFactory.getStepBean(998, 0, 0, 0, getSpeedStr(0), ""));
        arrayList.add(StepBeanFactory.getStepBean(999, 0, 0, 0, getSpeedStr(0), ""));
        return arrayList;
    }

    private static List<StepBean> getInsideGrooveKeyStepBeans(KeyAlignInfo keyAlignInfo, int i, List<PointXyz> list, int i2) {
        int i3;
        int i4;
        String duplicateStr;
        String duplicateStr2;
        if (list == null || list.size() == 0) {
            ErrorHelper.handleError(150);
            return null;
        }
        int cmm2StepY = UnitConvertUtil.cmm2StepY(i);
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 1;
        int i7 = 0;
        while (i7 < list.size()) {
            PointXyz pointXyz = list.get(i7);
            arrayList.add(new StepBean(0, pointXyz.getX(), pointXyz.getY(), 0, getSpeedStr(i5), "C:5,X;C:5,Y;", false));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, i2, getSpeedStr(i5), "SL:U,Z"));
            int i8 = i6 + 1 + 1;
            int i9 = i8;
            for (int i10 = 20; i10 > 0; i10--) {
                arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getInsideKeySpeedStr(i5), SL_S() + "GO3:" + (i8 + 120)));
                arrayList.add(StepBeanFactory.getStepBean(0, UnitConvertUtil.xKey2Machine(15), 0, 0, getInsideKeySpeedStr(i5), U_X()));
                arrayList.add(StepBeanFactory.getStepBean(1, 0, 0, 0, getInsideKeySpeedStr(1), SL_L()));
                arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(-15), 0, getInsideKeySpeedStr(i5), U_Y()));
                arrayList.add(StepBeanFactory.getStepBean(1, 0, 0, 0, getInsideKeySpeedStr(1), SL_R()));
                int i11 = i9 + 1 + 1 + 1 + 1 + 1;
                arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getInsideKeySpeedStr(i5), m92C2(i11 - 3, i11 - 1)));
                i9 = i11 + 1;
            }
            arrayList.add(StepBeanFactory.getStepBean(0, UnitConvertUtil.xKey2Machine(50), 0, 0, getSpeedStr(i5), U_X()));
            arrayList.add(StepBeanFactory.getStepBean(1, 0, 0, 0, getSpeedStr(1), SL_L()));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(-15), 0, getSpeedStr(i5), U_Y()));
            arrayList.add(StepBeanFactory.getStepBean(1, 0, 0, 0, getSpeedStr(1), SL_R()));
            int i12 = i9 + 1 + 1 + 1 + 1;
            Log.i(TAG, "nKeyTailSteupNum: " + i12);
            arrayList.add(StepBeanFactory.getStepBean("标记" + i12, 0, 0, 0, 0, getSpeedStr(i5), m92C2(i12 - 3, i12 - 1)));
            int i13 = i12 + 1;
            int left = keyAlignInfo.getLeft();
            int right = keyAlignInfo.getRight();
            if (list.size() > 1) {
                int decodeWidth2 = keyAlignInfo.getDecodeWidth2() / list.size();
                int i14 = (int) (20.0f / MachineData.dXScale);
                i4 = ((left - (decodeWidth2 * (i7 + 1))) - ToolSizeManager.getDecoderSize2()) - i14;
                i3 = (left - (decodeWidth2 * i7)) + i14;
            } else {
                i3 = left;
                i4 = right;
            }
            if (CuttingMachine.getInstance().isE9()) {
                duplicateStr = getDuplicateStr(0, cmm2StepY, 0, 1, i3, 0, i7);
            } else {
                duplicateStr = getDuplicateStr(1, cmm2StepY, 0, 1, i3, 1, i7);
            }
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr));
            arrayList.add(new StepBean(0, 0, 0, laserKeyUp, getSpeedStr(i5), "U:Z"));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(0), "S:" + i12 + ",X;S:" + i12 + ",Y"));
            String speedStr = getSpeedStr(0);
            String sb = "S:" +
                    i12 +
                    ",Z";
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, speedStr, sb));
            int i15 = i13 + 1 + 1 + 1 + 1;
            if (CuttingMachine.getInstance().isE9()) {
                duplicateStr2 = getDuplicateStr(1, cmm2StepY, 0, 2, i4, 0, i7);
            } else {
                duplicateStr2 = getDuplicateStr(0, cmm2StepY, 0, 2, i4, 1, i7);
            }
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), duplicateStr2));
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, UnitConvertUtil.zKey2Machine(-150), getSpeedStr(0), "U:Z"));
            i6 = i15 + 1 + 1;
            i7++;
            i5 = 0;
        }
        arrayList.add(new StepBean(998, 0, 0, 0, getSpeedStr(0), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, getSpeedStr(0), ""));
        return arrayList;
    }

    public static List<StepBean> generateHighDecodeSteps(KeyAlignInfo keyAlignInfo) {
        int high1;
        ArrayList arrayList = new ArrayList();
        int ceil = (int) ((keyAlignInfo.getDecodeWidth2() != 0 ? r1 / ((int) Math.ceil(r1 / 25)) : 25) / MachineData.dXScale);
        arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(ToolSizeManager.getDecoderRadius()), 0, getSpeedStr(0), SL_R()));
        int clampFace = keyAlignInfo.getClampFace();
        Clamp currentClamp = ClampManager.getInstance().getCurrentClamp();
        if (currentClamp == Clamp.S8) {
            high1 = ClampManager.getInstance().getS8().getHeight1();
        } else {
            high1 = currentClamp == Clamp.E9S1A ? ClampManager.getInstance().getE9S1A().getHigh1() : 0;
        }
        int cmm2StepZ = (clampFace + high1) - UnitConvertUtil.cmm2StepZ(50);
        if (CuttingMachine.getInstance().isE9()) {
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), getDuplicateStr(1, ceil, 0, 1, cmm2StepZ, 3, 0)));
        } else {
            arrayList.add(StepBeanFactory.getStepBean(0, 0, 0, 0, getSpeedStr(1), getDuplicateStr(1, ceil, 1, 1, cmm2StepZ, 2, 0)));
        }
        arrayList.add(StepBeanFactory.getStepBean(0, 0, UnitConvertUtil.yKey2Machine(300), 0, getSpeedStr(0), SL_L()));
        arrayList.add(StepBeanFactory.getStepBean(998, 0, 0, 0, getSpeedStr(0), ""));
        return arrayList;
    }

    private static void fixSpaceWidth(List<DestPoint> list) {
        double cos = 0;
        int cutterRadiusSize2 = 0;
        double cos2;
        int cutterRadiusSize22;
        int i;
        float f;
        if (list == null || list.size() <= 0) {
            return;
        }
        DestPoint destPoint = list.get(0);
        float f2 = 0.0f;
        int i2 = 0;
        for (int i3 = 1; i3 < list.size(); i3++) {
            DestPoint destPoint2 = list.get(i3);
            if (!destPoint2.isInvalid()) {
                int space = destPoint2.getSpace() - destPoint.getSpace();
                float depth = space == 0 ? Float.MAX_VALUE : ((destPoint2.getDepth() - destPoint.getDepth()) * 1.0f) / space;
                if (Math.abs(depth) < 0.15d) {
                    if (Math.abs(f2) >= 0.15d) {
                        if (f2 > 0.0f) {
                            cos2 = Math.cos((3.141592653589793d - Math.atan(f2)) / 2.0d);
                            cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                            f = ((int) (cos2 * cutterRadiusSize22)) * 0.4f;
                            i = (int) f;
                            destPoint.setSpace(destPoint.getSpace() - i);
                            list.set(i2, destPoint);
                            i2 = i3;
                            destPoint = destPoint2;
                            f2 = depth;
                        } else {
                            cos = Math.cos((3.141592653589793d - Math.atan(f2)) / 2.0d);
                            cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                            f = ((int) (cos * cutterRadiusSize2)) * 0.9f;
                            i = (int) f;
                            destPoint.setSpace(destPoint.getSpace() - i);
                            list.set(i2, destPoint);
                            i2 = i3;
                            destPoint = destPoint2;
                            f2 = depth;
                        }
                    }
                    i = 0;
                    destPoint.setSpace(destPoint.getSpace() - i);
                    list.set(i2, destPoint);
                    i2 = i3;
                    destPoint = destPoint2;
                    f2 = depth;
                } else if (depth > 0.0f) {
                    if (Math.abs(f2) < 0.15d) {
                        cos = Math.cos((3.141592653589793d - Math.atan(depth)) / 2.0d);
                        cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                    } else {
                        if (f2 > 0.0f) {
                            cos = Math.cos((3.141592653589793d - Math.atan(f2)) / 2.0d);
                            cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                        }
                        i = 0;
                        destPoint.setSpace(destPoint.getSpace() - i);
                        list.set(i2, destPoint);
                        i2 = i3;
                        destPoint = destPoint2;
                        f2 = depth;
                    }
                    f = ((int) (cos * cutterRadiusSize2)) * 0.9f;
                    i = (int) f;
                    destPoint.setSpace(destPoint.getSpace() - i);
                    list.set(i2, destPoint);
                    i2 = i3;
                    destPoint = destPoint2;
                    f2 = depth;
                } else if (Math.abs(f2) < 0.15d) {
                    cos2 = Math.cos((3.141592653589793d - Math.atan(depth)) / 2.0d);
                    cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                    f = ((int) (cos2 * cutterRadiusSize22)) * 0.4f;
                    i = (int) f;
                    destPoint.setSpace(destPoint.getSpace() - i);
                    list.set(i2, destPoint);
                    i2 = i3;
                    destPoint = destPoint2;
                    f2 = depth;
                } else {
                    if (f2 < 0.0f) {
                        cos = Math.cos((3.141592653589793d - Math.atan(f2)) / 2.0d);
                        cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                        f = ((int) (cos * cutterRadiusSize2)) * 0.9f;
                        i = (int) f;
                        destPoint.setSpace(destPoint.getSpace() - i);
                        list.set(i2, destPoint);
                        i2 = i3;
                        destPoint = destPoint2;
                        f2 = depth;
                    }
                    i = 0;
                    destPoint.setSpace(destPoint.getSpace() - i);
                    list.set(i2, destPoint);
                    i2 = i3;
                    destPoint = destPoint2;
                    f2 = depth;
                }
            }
        }
    }

    private static List<DestPoint> copyList(List<DestPoint> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i).m600clone());
        }
        return arrayList;
    }

    public static List<StepBean> getKeyBlanksCutSteps(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<List<DestPoint>> list, int i, boolean z) {
        List<List<CutPoint>> generateKeyBlankMultiCutPath;
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<DestPoint> list2 = list.get(0);
        List<DestPoint> list3 = list.size() > 1 ? list.get(1) : null;
        int keyBlankCutCount = (list2 == null || list2.size() <= 0) ? 0 : getKeyBlankCutCount(keyInfo, list2);
        if (list3 != null && list3.size() > 0) {
            keyBlankCutCount = Math.max(getKeyBlankCutCount(keyInfo, list3), keyBlankCutCount);
        }
        if (keyInfo.getType() != 8) {
            keyAlignInfo.setCutDepth((int) (keyInfo.getCutDepth() / MachineData.dZScale));
        }
        switch (keyInfo.getType()) {
            case 0:
                List<List<CutPoint>> generateKeyBlankMultiCutPath2 = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, true);
                if (generateKeyBlankMultiCutPath2 == null || (generateKeyBlankMultiCutPath = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list3, keyBlankCutCount, false)) == null) {
                    return null;
                }
                if (ClampManager.getInstance().getCurrentClamp().getClampStr().contains("S2")) {
                    return generateSingleSideKeyCutSteps(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath2);
                }
                return generateDoubleSideKeyCutSteps(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath2, generateKeyBlankMultiCutPath, false);
            case 1:
                return generateSingleSideKeyCutSteps(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, true));
            case 2:
                List<List<CutPoint>> generateKeyBlankMultiCutPath3 = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, false);
                List<List<CutPoint>> generateKeyBlankMultiCutPath4 = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list3, keyBlankCutCount, true);
                if (keyInfo.getGroove() > 0) {
                    return generateSingleInSideKeyCutSteps(keyInfo, keyAlignInfo, i, generateKeyBlankMultiCutPath3, generateKeyBlankMultiCutPath4, false);
                }
                return generateDoubleInSideKeyCutSteps(keyAlignInfo, i, generateKeyBlankMultiCutPath3, generateKeyBlankMultiCutPath4);
            case 3:
                List<List<CutPoint>> generateKeyBlankMultiCutPath5 = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, true);
                List<List<CutPoint>> generateKeyBlankMultiCutPath6 = generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list3, keyBlankCutCount, false);
                if (keyInfo.getIcCard() == 1311) {
                    return HY18Path(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath6);
                }
                if (keyInfo.getIcCard() == 1372) {
                    return HY18Path(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath5);
                }
                if (keyInfo.getSide() == 0) {
                    return singleOutSideGrooveTip2ShoulderPath(keyInfo, keyAlignInfo, generateKeyBlankMultiCutPath6);
                }
                return singleOutSideGrooveShoulder2TipPath(keyAlignInfo, generateKeyBlankMultiCutPath5);
            case 4:
                return generateDoubleOutSideKeyCutSteps(keyAlignInfo, generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, true), generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list3, keyBlankCutCount, false), false);
            case 5:
                return generateSingleInSideKeyCutSteps(keyInfo, keyAlignInfo, i, generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list2, keyBlankCutCount, false), generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list3, keyBlankCutCount, true), false);
            case 6:
                return generateDimpleKeyCutSteps(keyInfo, keyAlignInfo, generateDimpleMultiCutPath(keyInfo, keyAlignInfo, list, z));
            case 7:
                return generateAngleKeyCutSteps(keyAlignInfo, generateKeyBlankMultiCutPath(keyInfo, keyAlignInfo, list.get(0), keyBlankCutCount, false));
            case 8:
                return generateTubularKeyCutSteps(keyAlignInfo, generateTubularMultiCutPath(keyInfo, keyAlignInfo, list.get(0), false));
            default:
                return null;
        }
    }

    private static List<StepBean> generateSingleSideKeyCutSteps(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        int keyFace;
        List<List<CutPoint>> list2 = list;
        ArrayList arrayList = new ArrayList();
        int right = ((int) (keyAlignInfo.getRight() + (keyInfo.getWidth() / MachineData.dXScale) + ToolSizeManager.getDecoderRadius2() + ClampManager.getInstance().getDC().getxDistance())) + ToolSizeManager.getCutterRadiusSize2() + startCutLeaveSpace;
        int cutDepth = keyInfo.getCutDepth();
        if (cutDepth > 0) {
            keyFace = (int) (keyAlignInfo.getKeyFace() + (cutDepth / MachineData.dZScale));
        } else {
            keyFace = (int) (keyAlignInfo.getKeyFace() + (900.0f / MachineData.dZScale));
        }
        if (list2 != null) {
            int i = 0;
            while (i < list.size()) {
                List<CutPoint> list3 = list2.get(i);
                int i2 = 0;
                while (i2 < list3.size()) {
                    CutPoint cutPoint = list3.get(i2);
                    int x = cutPoint.getX();
                    int y = cutPoint.getY();
                    if (i2 == 0) {
                        if (i == 0) {
                            arrayList.add(new StepBean(0, right, y, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y", false));
                            arrayList.add(new StepBean(0, 0, 0, keyFace, Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;C:5,Z;", false));
                            arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                        } else {
                            arrayList.add(new StepBean(0, right, y, keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                        }
                    }
                    int i3 = i2 + 1;
                    String sb = "左边第" +
                            (i + 1) +
                            "刀，第" +
                            i3 +
                            "点";
                    int i4 = i2;
                    arrayList.add(new StepBean(sb, 0, x, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    if (i4 == list3.size() - 1) {
                        if (i == list.size() - 1) {
                            arrayList.add(new StepBean("离开左边", 0, x + endCutLeaveSpace, y, keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                        } else {
                            arrayList.add(new StepBean("离开左边", 0, right, y, keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                        }
                    }
                    i2 = i3;
                }
                i++;
                list2 = list;
            }
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    private static List<StepBean> generateDimpleKeyCutSteps(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        int clampFace;
        float clampFace2;
        float f;
        float f2;
        int i;
        List<List<CutPoint>> list2 = list;
        ArrayList arrayList = new ArrayList();
        if (list2 == null || list.size() == 0) {
            return null;
        }
        int thick = keyInfo.getThick();
        if (thick == 0) {
            for (String str : keyInfo.getDepthStr().split(";")) {
                String[] split = str.split(",");
                thick = Math.max(Math.max(thick, Integer.parseInt(split[0])), Integer.parseInt(split[split.length - 1]));
            }
        }
        if (ClampManager.getInstance().getCurrentClamp().getClampStr().equals("S1-A")) {
            clampFace = (int) ((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh()) - ((thick + 200) / MachineData.dZScale));
            clampFace2 = keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh();
            f = thick;
            f2 = MachineData.dZScale;
        } else {
            clampFace = (int) ((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1()) - ((thick + 200) / MachineData.dZScale));
            clampFace2 = keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1();
            f = thick;
            f2 = MachineData.dZScale;
        }
        int i2 = (int) (clampFace2 - (f / f2));
        int i3 = clampFace;
        int i4 = 0;
        while (i4 < list.size()) {
            List<CutPoint> list3 = list2.get(i4);
            int i5 = 0;
            while (i5 < list3.size()) {
                CutPoint cutPoint = list3.get(i5);
                int x = cutPoint.getX();
                int y = cutPoint.getY();
                int z = cutPoint.getZ();
                if (i5 == 0) {
                    arrayList.add(new StepBean(0, x, y, i3, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;", false));
                    if (i4 == 0) {
                        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                    }
                    i = i5;
                    arrayList.add(new StepBean(0, x, y, i2, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;", false));
                } else {
                    i = i5;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("左边第");
                sb.append(i4 + 1);
                sb.append("刀，第");
                i5 = i + 1;
                sb.append(i5);
                sb.append("点");
                String sb2 = sb.toString();
                String str2 = Speed.get_Speed_DimleCuttingIn();
                String sb3 = "C:5,X;C:5,Y;C:5,Z;CUTSM;" +
                        (cutPoint.isBreakPoint() ? "BREAK" : "");
                arrayList.add(new StepBean(sb2, 0, x, y, z, str2, sb3, false));
                if (i == list3.size() - 1) {
                    arrayList.add(new StepBean(0, x, y, i3, Speed.get_Speed_SpindleTurnOn_ZUp(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                }
            }
            i4++;
            list2 = list;
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    private static List<List<CutPoint>> generateDimpleMultiCutPath(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<List<DestPoint>> list, boolean z) {
        int shoulder;
        int fixZ;
        int i;
        ArrayList arrayList;
        double high2;
        double tan;
        float depth;
        float f;
        String str;
        int i2;
        int clampFace;
        int high1;
        boolean z2;
        int i3;
        int i4;
        int i5;
        ArrayList arrayList2;
        KeyAlignInfo keyAlignInfo2 = keyAlignInfo;
        int i6 = 1;
        boolean z3 = keyInfo.getSpaceWidthStr().contains("-") || z;
        int thick = keyInfo.getThick();
        if (thick == 0) {
            for (String str2 : keyInfo.getDepthStr().split(";")) {
                String[] split = str2.split(",");
                thick = Math.max(Math.max(thick, Integer.parseInt(split[0])), Integer.parseInt(split[split.length - 1]));
            }
        }
        int i7 = ClampManager.getInstance().getDC().getyDistance();
        int i8 = ClampManager.getInstance().getDC().getxDistance();
        ArrayList arrayList3 = new ArrayList();
        int i9 = 0;
        while (i9 < list.size()) {
            List<DestPoint> list2 = list.get(i9);
            int i10 = 0;
            while (i10 < list2.size()) {
                ArrayList arrayList4 = new ArrayList();
                DestPoint destPoint = list2.get(i10);
                int left = (int) (((keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) + i8) - (destPoint.getRowPos() / MachineData.dXScale));
                if (keyInfo.getAlign() == i6) {
                    shoulder = (int) ((destPoint.getSpace() / MachineData.dYScale) + keyAlignInfo.getTip() + i7);
                } else {
                    shoulder = (int) ((keyAlignInfo.getShoulder() - (destPoint.getSpace() / MachineData.dYScale)) + i7);
                }
                if (CuttingMachine.getInstance().isE9()) {
                    fixZ = DataFixUtil.getFixZ(keyAlignInfo2, left);
                } else {
                    fixZ = DataFixUtil.getFixZ(keyAlignInfo2, shoulder);
                }
                int i11 = fixZ;
                int abs = Math.abs(thick - destPoint.getDepth());
                int i12 = i7;
                int i13 = i8;
                int ceil = (int) Math.ceil((abs * 1.0f) / (z3 ? 40 : 80));
                if (ceil == 0) {
                    ceil = 1;
                }
                int i14 = abs / ceil;
                int min = Math.min(Math.abs(keyAlignInfo.getRightCutter() - left), Math.abs(keyAlignInfo.getLeftCutter() - left));
                int depth2 = (int) (destPoint.getDepth() / MachineData.dZScale);
                String clampStr = ClampManager.getInstance().getCurrentClamp().getClampStr();
                List<DestPoint> list3 = list2;
                double radians = Math.toRadians(ToolSizeManager.DimpleCutterAngle / 2.0d);
                int i15 = i9;
                if (clampStr.equals("S1-A")) {
                    i = i10;
                    arrayList = arrayList4;
                    high2 = (ClampManager.getInstance().getS1A().getHigh() + (11.0f / MachineData.dZScale)) - depth2;
                    tan = Math.tan(radians);
                } else {
                    i = i10;
                    arrayList = arrayList4;
                    String clampSlot = keyInfo.getClampBean().getClampSlot();
                    if (!TextUtils.isEmpty(clampSlot) && clampSlot.equals("0")) {
                        high2 = (ClampManager.getInstance().getS1B().getHigh1() + (11.0f / MachineData.dZScale)) - depth2;
                        tan = Math.tan(radians);
                    } else {
                        high2 = (ClampManager.getInstance().getS1B().getHigh2() + (11.0f / MachineData.dZScale)) - depth2;
                        tan = Math.tan(radians);
                    }
                }
                int cutterRadiusSize2 = min - (((int) ((((int) (high2 * tan)) * MachineData.dZScale) / MachineData.dXScale)) + ToolSizeManager.getCutterRadiusSize2());
                if (cutterRadiusSize2 >= 35.0f / MachineData.dXScale) {
                    cutterRadiusSize2 = (int) (35.0f / MachineData.dXScale);
                }
                if (cutterRadiusSize2 < 0) {
                    if (clampStr.equals("S1-A")) {
                        ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                        return null;
                    }
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
                    return null;
                }
                int i16 = 0;
                while (i16 < ceil) {
                    if (i16 < ceil - 1) {
                        depth = thick - ((i16 + 1) * i14);
                        f = MachineData.dZScale;
                    } else {
                        depth = destPoint.getDepth();
                        f = MachineData.dZScale;
                    }
                    int i17 = (int) (depth / f);
                    if (clampStr.equals("S1-A")) {
                        i2 = (keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh()) - i17;
                        str = clampStr;
                    } else if (clampStr.equals("S1-B")) {
                        String clampSlot2 = keyInfo.getClampBean().getClampSlot();
                        str = clampStr;
                        if (!TextUtils.isEmpty(clampSlot2) && clampSlot2.equals("1")) {
                            clampFace = keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1();
                            high1 = ClampManager.getInstance().getS1B().getHigh2();
                        } else {
                            clampFace = keyAlignInfo.getClampFace();
                            high1 = ClampManager.getInstance().getS1B().getHigh1();
                        }
                        i2 = (clampFace + high1) - i17;
                    } else {
                        str = clampStr;
                        i2 = 0;
                    }
                    int i18 = i2 + i11;
                    CutPoint cutPoint = new CutPoint(left, shoulder, i18);
                    ArrayList arrayList5 = arrayList;
                    arrayList5.add(cutPoint);
                    if (z3) {
                        z2 = z3;
                        arrayList5.add(new CutPoint(left, shoulder, i18 - ((int) (5.0f / MachineData.dZScale))));
                        i3 = ceil;
                        i4 = thick;
                        i5 = i14;
                        arrayList2 = arrayList5;
                    } else {
                        z2 = z3;
                        i3 = ceil;
                        cutPoint.setBreakPoint(true);
                        i4 = thick;
                        i5 = i14;
                        int tan2 = ((int) (((((ceil - i16) - 1) * i14) * Math.tan(radians)) / MachineData.dXScale)) + cutterRadiusSize2;
                        arrayList2 = arrayList5;
                        arrayList2.addAll(getArc(left, shoulder, tan2, 6.283185307179586d, 0.0d, 32, i18));
                        CutPoint cutPoint2 = new CutPoint(left, shoulder, i18);
                        cutPoint2.setBreakPoint(true);
                        arrayList2.add(cutPoint2);
                    }
                    i16++;
                    z3 = z2;
                    clampStr = str;
                    thick = i4;
                    i14 = i5;
                    arrayList = arrayList2;
                    ceil = i3;
                }
                i6 = 1;
                arrayList3.add(arrayList);
                i10 = i + 1;
                keyAlignInfo2 = keyAlignInfo;
                i7 = i12;
                i8 = i13;
                list2 = list3;
                i9 = i15;
            }
            i9++;
            keyAlignInfo2 = keyAlignInfo;
        }
        return arrayList3;
    }

    private static List<StepBean> generateTubularKeyCutSteps(KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return null;
        }
        int keyFace = (int) (keyAlignInfo.getKeyFace() - (100.0f / MachineData.dZScale));
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<CutPoint> list2 = list.get(i);
            int i2 = 0;
            while (i2 < list2.size()) {
                CutPoint cutPoint = list2.get(i2);
                int x = cutPoint.getX();
                int y = cutPoint.getY();
                int z = cutPoint.getZ() + keyAlignInfo.getKeyFace();
                if (i2 == 0) {
                    arrayList.add(new StepBean(0, x, y, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;;", false));
                    if (i == 0) {
                        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                    }
                }
                int i3 = i2 + 1;
                String sb = "左边第" +
                        (i + 1) +
                        "刀，第" +
                        i3 +
                        "点";
                arrayList.add(new StepBean(sb, 0, x, y, z, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                if (i2 == list2.size() - 1) {
                    arrayList.add(new StepBean(0, x, y, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;", false));
                }
                i2 = i3;
            }
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    private static List<List<CutPoint>> generateTubularMultiCutPath(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<DestPoint> list, boolean z) {
        double radians;
        int i;
        int depth;
        List<DestPoint> list2 = list;
        ArrayList arrayList = new ArrayList();
        char c = 0;
        int i2 = 0;
        while (i2 < list.size()) {
            int i3 = ClampManager.getInstance().getDC().getyDistance();
            int i4 = ClampManager.getInstance().getDC().getxDistance();
            int thick = (int) (keyInfo.getThick() / MachineData.dXScale);
            if (thick == 0) {
                thick = (int) (100.0f / MachineData.dXScale);
            }
            int remainingDepth = keyInfo.getRemainingDepth();
            if (remainingDepth == 0) {
                remainingDepth = 20;
            }
            int i5 = thick - ((int) (remainingDepth / MachineData.dXScale));
            PointF centerPointOfCircle = CircleUtils.getCenterPointOfCircle(keyAlignInfo.getTlX(), keyAlignInfo.getTlY(), keyAlignInfo.getTrX(), keyAlignInfo.getTrY(), keyAlignInfo.getTdX(), keyAlignInfo.getTdY());
            int i6 = ((int) centerPointOfCircle.x) + i4;
            int i7 = ((int) centerPointOfCircle.y) + i3;
            int radiusOfCircle = (int) (((int) CircleUtils.getRadiusOfCircle(keyAlignInfo.getTlX(), keyAlignInfo.getTlY(), keyAlignInfo.getTrX(), keyAlignInfo.getTrY(), keyAlignInfo.getTdX(), keyAlignInfo.getTdY())) + ToolSizeManager.getDecoderRadius2() + (keyInfo.getThick() / MachineData.dXScale));
            String[] split = keyInfo.getSpaceStr().split(";")[c].split(",");
            if (CuttingMachine.getInstance().isE9()) {
                radians = Math.toRadians(Integer.parseInt(split[i2]) / 100);
            } else {
                radians = Math.toRadians(Integer.parseInt(split[i2]) / 100) - 90.0d;
            }
            int cutterRadiusSize2 = (int) (ToolSizeManager.getCutterRadiusSize2() + radiusOfCircle + (50.0f / MachineData.dXScale));
            int i8 = (int) (300.0f / MachineData.dXScale);
            double d = (radiusOfCircle + i8) - i5;
            int i9 = i2;
            int i10 = cutterRadiusSize2;
            double acos = Math.acos(((Math.pow(d, 2.0d) + Math.pow(i8, 2.0d)) - Math.pow(radiusOfCircle, 2.0d)) / ((r12 * 2) * i8));
            double radians2 = Math.toRadians(180.0d) + radians;
            double d2 = radians2 + acos;
            double d3 = radians2 - acos;
            double d4 = i6;
            int cos = (int) (d4 - (Math.cos(radians) * d));
            double d5 = i7;
            int sin = (int) ((d * Math.sin(radians)) + d5);
            if (z) {
                i = i9;
                depth = list2.get(i).getDepth();
            } else {
                i = i9;
                depth = (int) (list2.get(i).getDepth() / MachineData.dZScale);
            }
            int i11 = (int) ((depth / (120.0f / MachineData.dZScale)) + 1.0f);
            ArrayList arrayList2 = new ArrayList();
            int i12 = 0;
            while (i12 < i11) {
                int i13 = i12 + 1;
                int i14 = (depth / i11) * i13;
                int i15 = cos;
                List<CutPoint> arc = getArc(cos, sin, i8 - ToolSizeManager.getCutterRadiusSize2(), d2, d3, 20, i14);
                int i16 = sin;
                int i17 = i8;
                int i18 = i10;
                int i19 = depth;
                double d6 = i18;
                ArrayList arrayList3 = arrayList2;
                ArrayList arrayList4 = arrayList;
                double d7 = d4;
                CutPoint cutPoint = new CutPoint((int) (d4 - (Math.cos(radians) * d6)), (int) ((Math.sin(radians) * d6) + d5), i14);
                CutPoint cutPoint2 = new CutPoint((int) (d7 - (Math.cos(radians) * d6)), (int) ((d6 * Math.sin(radians)) + d5), i14);
                arrayList3.add(cutPoint);
                arrayList3.addAll(arc);
                arrayList3.add(cutPoint2);
                arrayList2 = arrayList3;
                arrayList = arrayList4;
                depth = i19;
                sin = i16;
                i11 = i11;
                i12 = i13;
                d4 = d7;
                cos = i15;
                i10 = i18;
                i8 = i17;
            }
            arrayList.add(arrayList2);
            i2 = i + 1;
            list2 = list;
            c = 0;
        }
        return arrayList;
    }

    private static List<StepBean> generateAngleKeyCutSteps(KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            List<CutPoint> list2 = list.get(i);
            int i2 = 0;
            while (i2 < list2.size()) {
                CutPoint cutPoint = list2.get(i2);
                int x = cutPoint.getX();
                int y = cutPoint.getY();
                if (i2 == 0) {
                    arrayList.add(new StepBean(0, x, y, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;SL:U,Z", false));
                    arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                }
                StringBuilder sb = new StringBuilder();
                sb.append("左边第");
                sb.append(i + 1);
                sb.append("刀，第");
                i2++;
                sb.append(i2);
                sb.append("点");
                arrayList.add(new StepBean(sb.toString(), 0, x, y, 0, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;U:Z;CUTSM", false));
            }
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(0, 0, 0, -500, Speed.get_Speed_SpindleTurnOff_ZUp(), "U:X;U:Y;SL:U,Z;"));
        return arrayList;
    }

    private static List<StepBean> generateDoubleInSideKeyCutSteps(KeyAlignInfo keyAlignInfo, int i, List<List<CutPoint>> list, List<List<CutPoint>> list2) {
        float f;
        float f2 = 0;
        int i2;
        int i3;
        int i4;
        String str;
        String str2;
        int i5;
        List<CutPoint> list3;
        int i6;
        int i7;
        int i8;
        if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_A) {
            if ((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh()) - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1a);
                return null;
            }
        } else if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_B && (keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1()) - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1b);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        keyAlignInfo.getRight();
        ToolSizeManager.getDecoderRadius2();
        ClampManager.getInstance().getDC().getxDistance();
        int left = (keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) + ClampManager.getInstance().getDC().getxDistance();
        int tip = keyAlignInfo.getTip() + ClampManager.getInstance().getDC().getyDistance();
        int cutDepth = keyAlignInfo.getCutDepth();
        int i9 = 0;
        while (i9 < i) {
            int i10 = 1;
            if (i == 2) {
                if (i9 == 0) {
                    if (CuttingMachine.getInstance().isAlpha()) {
                        f = cutDepth;
                        f2 = 0.5f;
                    } else {
                        f = cutDepth;
                        f2 = 0.7f;
                    }
                    i2 = (int) (f * f2);
                }
                i2 = cutDepth;
            } else {
                if (i == 3) {
                    if (i9 == 0) {
                        f = cutDepth;
                        f2 = 0.4f;
                    } else if (i9 == 1) {
                        f = cutDepth;
                        f2 = 0.8f;
                    }
                    i2 = (int) (f * f2);
                }
                i2 = cutDepth;
            }
            int keyFace = keyAlignInfo.getKeyFace() + i2;
            int size = list.size() - 1;
            while (size >= 0) {
                List<CutPoint> list4 = list.get(size);
                int size2 = list4.size() - i10;
                while (size2 >= 0) {
                    CutPoint cutPoint = list4.get(size2);
                    int x = cutPoint.getX();
                    int y = cutPoint.getY();
                    if (size != list.size() - 1 || size2 != list4.size() - i10) {
                        str = "刀，第";
                        str2 = "点";
                        i5 = size2;
                        list3 = list4;
                        i6 = i9;
                        i7 = cutDepth;
                        i8 = size;
                    } else if (size == list.size() - i10 && i9 == 0) {
                        str = "刀，第";
                        str2 = "点";
                        i5 = size2;
                        list3 = list4;
                        i6 = i9;
                        i8 = size;
                        i7 = cutDepth;
                        arrayList.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;", false));
                        arrayList.add(new StepBean(0, 0, 0, i2, Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                    } else {
                        str = "刀，第";
                        str2 = "点";
                        i5 = size2;
                        list3 = list4;
                        i6 = i9;
                        i7 = cutDepth;
                        i8 = size;
                        arrayList.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), keyFace, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    }
                    arrayList.add(new StepBean("左边第" + (i8 + 1) + str + (i5 + 1) + str2, 0, x, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    size2 = i5 + (-1);
                    size = i8;
                    list4 = list3;
                    i9 = i6;
                    cutDepth = i7;
                    i10 = 1;
                }
                int i11 = i9;
                int i12 = cutDepth;
                int i13 = size;
                List<CutPoint> list5 = list2.get(i13);
                int i14 = 0;
                int i15 = 0;
                while (i15 < list5.size()) {
                    CutPoint cutPoint2 = list5.get(i15);
                    int x2 = cutPoint2.getX();
                    int y2 = cutPoint2.getY();
                    StringBuilder sb = new StringBuilder();
                    sb.append("右边第");
                    sb.append(i13 + 1);
                    sb.append("刀，第");
                    i15++;
                    sb.append(i15);
                    sb.append("点");
                    arrayList.add(new StepBean(sb.toString(), 0, x2, y2, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i14 = x2;
                }
                arrayList.add(new StepBean(0, i14, tip, keyFace, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                if (i11 < i - 1) {
                    i4 = i11;
                    arrayList.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                } else {
                    i4 = i11;
                }
                size = i13 - 1;
                i9 = i4;
                cutDepth = i12;
                i10 = 1;
            }
            int i16 = cutDepth;
            int i17 = i9;
            if (i17 == i - 1) {
                List<CutPoint> list6 = list.get(list.size() - 1);
                int x3 = list6.get(list6.size() - 1).getX();
                int y3 = list6.get(list6.size() - 2).getY();
                List<CutPoint> list7 = list2.get(list2.size() - 1);
                int x4 = list7.get(list7.size() - 1).getX();
                int y4 = list7.get(list7.size() - 2).getY();
                int x5 = (list6.get(list6.size() - 2).getX() + list7.get(list7.size() - 2).getX()) / 2;
                int min = Math.min(y3, y4) + ToolSizeManager.getCutterRadiusSize2();
                int i18 = x3 - x4;
                if (i18 > 0) {
                    i3 = ((int) (i18 / ((ToolSizeManager.getCutterSize2() * 0.8d) * 2.0d))) + 1;
                } else {
                    arrayList.add(new StepBean(0, x4, (int) ((tip - ToolSizeManager.getCutterRadiusSize2()) - (100.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i3 = 0;
                }
                int i19 = x3;
                int i20 = min;
                int i21 = x4;
                int i22 = 0;
                while (i22 < i3) {
                    int i23 = i22;
                    int i24 = i3;
                    arrayList.add(new StepBean(0, i19, tip, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    int cutterSize2 = (int) (i19 - (ToolSizeManager.getCutterSize2() * 0.8d));
                    arrayList.add(new StepBean(0, x5, i20, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i20 -= ToolSizeManager.getCutterRadiusSize2();
                    int cutterSize22 = (int) (i21 + (ToolSizeManager.getCutterSize2() * 0.8d));
                    arrayList.add(new StepBean(0, cutterSize22, tip, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    if (i23 == i24 - 1) {
                        arrayList.add(new StepBean(0, cutterSize22, (int) ((tip - ToolSizeManager.getCutterRadiusSize2()) - (100.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    }
                    i22 = i23 + 1;
                    i3 = i24;
                    i21 = cutterSize22;
                    i19 = cutterSize2;
                }
            }
            i9 = i17 + 1;
            cutDepth = i16;
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_SpindleTurnOff_ZUp(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    private static List<List<CutPoint>> generateSingleInsideGrooveMultiCutPath(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<DestPoint> list, int i, boolean z) {
        int shoulder;
        int shoulder2;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int align = keyInfo.getAlign();
        int left = keyAlignInfo.getLeft();
        int right = keyAlignInfo.getRight();
        int groove = keyInfo.getGroove();
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < list.size(); i3++) {
                DestPoint destPoint = list.get(i3);
                int depth = destPoint.getDepth() + (i2 * 2 * ((groove - ToolSizeManager.getCutterSize()) / ((i * 2) - 1)));
                int decoderRadius2 = ToolSizeManager.getDecoderRadius2();
                int i4 = ClampManager.getInstance().getDC().getxDistance();
                int cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2();
                int i5 = z ? depth + right + decoderRadius2 + i4 + cutterRadiusSize2 : (((left - depth) - decoderRadius2) + i4) - cutterRadiusSize2;
                int i6 = ClampManager.getInstance().getDC().getyDistance();
                if (align == 1) {
                    shoulder2 = destPoint.getSpace() + keyAlignInfo.getTip() + i6;
                } else {
                    shoulder2 = (keyAlignInfo.getShoulder() - destPoint.getSpace()) + i6;
                }
                arrayList2.add(new CutPoint(i5, shoulder2));
            }
            arrayList.add(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            for (int i7 = 0; i7 < list.size(); i7++) {
                DestPoint destPoint2 = list.get(i7);
                int depth2 = destPoint2.getDepth() + (((i2 * 2) + 1) * ((groove - ToolSizeManager.getCutterSize()) / ((i * 2) - 1)));
                int decoderRadius22 = ToolSizeManager.getDecoderRadius2();
                int i8 = ClampManager.getInstance().getDC().getxDistance();
                int cutterRadiusSize22 = ToolSizeManager.getCutterRadiusSize2();
                int i9 = z ? depth2 + right + decoderRadius22 + i8 + cutterRadiusSize22 : (((left - depth2) - decoderRadius22) + i8) - cutterRadiusSize22;
                int i10 = ClampManager.getInstance().getDC().getyDistance();
                if (align == 1) {
                    shoulder = destPoint2.getSpace() + keyAlignInfo.getTip() + i10;
                } else {
                    shoulder = (keyAlignInfo.getShoulder() - destPoint2.getSpace()) + i10;
                }
                arrayList3.add(new CutPoint(i9, shoulder));
            }
            arrayList.add(arrayList3);
        }
        return arrayList;
    }

    private static List<StepBean> generateSingleInSideKeyCutSteps(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, int i, List<List<CutPoint>> list, List<List<CutPoint>> list2, boolean z) {
        float f;
        float f2;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        int x;
        int min;
        int i5;
        int i6;
        String str;
        String str2;
        int i7;
        List<CutPoint> list3;
        int i8;
        int i9;
        int i10;
        int i11 = i;
        List<List<CutPoint>> list4 = list;
        List<List<CutPoint>> list5 = list2;
        if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_A) {
            if ((keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1A().getHigh()) - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1a);
                return null;
            }
        } else if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_B && (keyAlignInfo.getClampFace() + ClampManager.getInstance().getS1B().getHigh1()) - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
            ErrorHelper.handleError(ErrorCode.RiskCutClampDownFaceS1b);
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        keyAlignInfo.getRight();
        ToolSizeManager.getDecoderRadius2();
        ClampManager.getInstance().getDC().getxDistance();
        int left = (keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) + ClampManager.getInstance().getDC().getxDistance();
        int tip = keyAlignInfo.getTip() + ClampManager.getInstance().getDC().getyDistance();
        int cutDepth = keyAlignInfo.getCutDepth();
        int i12 = 0;
        while (i12 < i11) {
            int i13 = 1;
            if (i11 == 2) {
                if (i12 == 0) {
                    if (CuttingMachine.getInstance().isAlpha()) {
                        f = cutDepth;
                        f2 = 0.5f;
                    } else {
                        f = cutDepth;
                        f2 = 0.7f;
                    }
                    i2 = (int) (f * f2);
                }
                i2 = cutDepth;
            } else {
                if (i11 == 3) {
                    if (i12 == 0) {
                        f = cutDepth;
                        f2 = 0.4f;
                    } else if (i12 == 1) {
                        f = cutDepth;
                        f2 = 0.8f;
                    }
                    i2 = (int) (f * f2);
                }
                i2 = cutDepth;
            }
            int keyFace = keyAlignInfo.getKeyFace() + i2;
            int size = list.size() - 1;
            while (size >= 0) {
                List<CutPoint> list6 = list4.get(size);
                int size2 = list6.size() - i13;
                while (size2 >= 0) {
                    CutPoint cutPoint = list6.get(size2);
                    int x2 = cutPoint.getX();
                    int y = cutPoint.getY();
                    if (size != list.size() - 1 || size2 != list6.size() - i13) {
                        str = "刀，第";
                        str2 = "点";
                        i7 = size2;
                        list3 = list6;
                        i8 = i12;
                        i9 = cutDepth;
                        i10 = size;
                    } else if (size == list.size() - i13 && i12 == 0) {
                        str = "刀，第";
                        str2 = "点";
                        i7 = size2;
                        list3 = list6;
                        i8 = i12;
                        i10 = size;
                        i9 = cutDepth;
                        arrayList2.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;", false));
                        arrayList2.add(new StepBean(0, 0, 0, i2, Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                        arrayList2.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                    } else {
                        str = "刀，第";
                        str2 = "点";
                        i7 = size2;
                        list3 = list6;
                        i8 = i12;
                        i9 = cutDepth;
                        i10 = size;
                        arrayList2.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), keyFace, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                    }
                    arrayList2.add(new StepBean("左边第" + (i10 + 1) + str + (i7 + 1) + str2, 0, x2, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    size2 = i7 + (-1);
                    size = i10;
                    list6 = list3;
                    i12 = i8;
                    cutDepth = i9;
                    i13 = 1;
                }
                int i14 = i12;
                int i15 = cutDepth;
                int i16 = size;
                List<CutPoint> list7 = list5.get(i16);
                int i17 = 0;
                int i18 = 0;
                while (i18 < list7.size()) {
                    CutPoint cutPoint2 = list7.get(i18);
                    int x3 = cutPoint2.getX();
                    int y2 = cutPoint2.getY();
                    StringBuilder sb = new StringBuilder();
                    sb.append("右边第");
                    sb.append(i16 + 1);
                    sb.append("刀，第");
                    i18++;
                    sb.append(i18);
                    sb.append("点");
                    arrayList2.add(new StepBean(sb.toString(), 0, x3, y2, keyFace, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i17 = x3;
                }
                arrayList2.add(new StepBean(0, i17, tip, keyFace, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                if (i14 < i11 - 1) {
                    i6 = i14;
                    arrayList2.add(new StepBean(0, left, (int) ((tip - (20.0f / MachineData.dYScale)) - ToolSizeManager.getCutterRadiusSize2()), keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                } else {
                    i6 = i14;
                }
                size = i16 - 1;
                i12 = i6;
                cutDepth = i15;
                i13 = 1;
            }
            int i19 = cutDepth;
            int i20 = i12;
            if (i20 == i11 - 1) {
                List<CutPoint> list8 = list4.get(list.size() - 1);
                int x4 = list8.get(list8.size() - 1).getX();
                int y3 = list8.get(list8.size() - 2).getY();
                List<CutPoint> list9 = list5.get(list2.size() - 1);
                int x5 = list9.get(list9.size() - 1).getX();
                int y4 = list9.get(list9.size() - 2).getY();
                ToolSizeManager.getCutterSize2();
                if (z) {
                    int x6 = (list8.get(list8.size() - 2).getX() + list9.get(list9.size() - 2).getX()) / 2;
                    min = (int) (tip + (400.0f / MachineData.dXScale));
                    List<CutPoint> list10 = list4.get(list.size() - 1);
                    List<CutPoint> list11 = list5.get(list2.size() - 1);
                    int min2 = Math.min(list10.size(), list11.size()) - 1;
                    int i21 = Integer.MAX_VALUE;
                    while (true) {
                        if (min2 < 0) {
                            arrayList = arrayList2;
                            i3 = left;
                            i4 = tip;
                            break;
                        }
                        int abs = Math.abs(list10.get(min2).getX() - list11.get(min2).getX());
                        arrayList = arrayList2;
                        i3 = left;
                        i4 = tip;
                        if (abs < ToolSizeManager.getCutterSize2() * 0.8d) {
                            x6 = (list8.get(min2).getX() + list9.get(min2).getX()) / 2;
                            min = list8.get(min2).getY();
                            break;
                        }
                        if (abs > i21 && min2 < list10.size() - 1) {
                            int i22 = min2 + 1;
                            x6 = (list8.get(i22).getX() + list9.get(i22).getX()) / 2;
                            min = list8.get(min2).getY();
                            break;
                        }
                        min2--;
                        arrayList2 = arrayList;
                        i21 = abs;
                        left = i3;
                        tip = i4;
                    }
                    x = x6;
                } else {
                    arrayList = arrayList2;
                    i3 = left;
                    i4 = tip;
                    x = (list8.get(list8.size() - 2).getX() + list9.get(list9.size() - 2).getX()) / 2;
                    min = Math.min(y3, y4);
                }
                int i23 = x4 - x5;
                if (i23 > 0) {
                    i5 = ((int) (i23 / ((ToolSizeManager.getCutterSize2() * 0.8d) * 2.0d))) + 1;
                    arrayList2 = arrayList;
                } else {
                    arrayList2 = arrayList;
                    arrayList2.add(new StepBean(0, x5, (int) ((i4 - ToolSizeManager.getCutterRadiusSize2()) - (100.0f / MachineData.dYScale)), 0, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;U:Z;CUTSM", false));
                    i5 = 0;
                }
                int i24 = x4;
                int i25 = x5;
                int i26 = min;
                int i27 = 0;
                while (i27 < i5) {
                    int i28 = i27;
                    List<CutPoint> list12 = list9;
                    arrayList2.add(new StepBean(0, i24, i4, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    arrayList2.add(new StepBean(0, i24, list8.get(list8.size() - 1).getY(), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i24 = (int) (i24 - (ToolSizeManager.getCutterSize2() * 0.8d));
                    arrayList2.add(new StepBean(0, x, i26, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i26 -= ToolSizeManager.getCutterRadiusSize2();
                    i25 = (int) (i25 + (ToolSizeManager.getCutterSize2() * 0.8d));
                    arrayList2.add(new StepBean(0, i25, list12.get(list12.size() - 1).getY(), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    arrayList2.add(new StepBean(0, i25, i4, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    if (i28 == i5 - 1) {
                        arrayList2.add(new StepBean(0, i25, (int) ((i4 - ToolSizeManager.getCutterRadiusSize2()) - (100.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    }
                    i27 = i28 + 1;
                    list9 = list12;
                }
            } else {
                i3 = left;
                i4 = tip;
            }
            i12 = i20 + 1;
            i11 = i;
            list4 = list;
            list5 = list2;
            left = i3;
            tip = i4;
            cutDepth = i19;
        }
        arrayList2.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList2.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList2.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList2;
    }

    private static List<StepBean> singleOutSideGrooveShoulder2TipPath(KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        List<List<CutPoint>> list2 = list;
        if (keyAlignInfo.getClampFace() - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
            if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_A) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                return null;
            }
            if (ClampManager.getInstance().getCurrentClamp() != Clamp.S1_B) {
                return null;
            }
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int left = (keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) + ClampManager.getInstance().getDC().getxDistance();
        int keyFace = keyAlignInfo.getKeyFace() + keyAlignInfo.getCutDepth();
        int i = 0;
        if (list2 != null) {
            int i2 = 0;
            while (i2 < list.size()) {
                List<CutPoint> list3 = list2.get(i2);
                int i3 = 0;
                while (i3 < list3.size()) {
                    CutPoint cutPoint = list3.get(i3);
                    int x = cutPoint.getX();
                    int y = cutPoint.getY();
                    if (i3 == 0) {
                        if (i2 == 0) {
                            arrayList.add(new StepBean(0, ToolSizeManager.getCutterRadiusSize2() + left + startCutLeaveSpace, y, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y", false));
                            arrayList.add(new StepBean(0, 0, 0, keyAlignInfo.getCutDepth(), Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                            arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(i), "U:X;U:Y;U:Z;SUP:1,8000"));
                        } else {
                            arrayList.add(new StepBean(0, startCutLeaveSpace + ToolSizeManager.getCutterRadiusSize2() + left, y, keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                        }
                    }
                    int i4 = i3 + 1;
                    String sb = "左边第" +
                            (i2 + 1) +
                            "刀，第" +
                            i4 +
                            "点";
                    int i5 = i3;
                    arrayList.add(new StepBean(sb, 0, x, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    if (i5 == list3.size() - 1) {
                        if (i2 == list.size() - 1) {
                            arrayList.add(new StepBean("离开前端", 0, x, (int) ((keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - (50.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                        } else {
                            arrayList.add(new StepBean("离开前端", 0, x, (int) ((keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - (50.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                            arrayList.add(new StepBean("离开左边", 0, startCutLeaveSpace + left + ToolSizeManager.getCutterRadiusSize2(), (int) ((keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - (50.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                        }
                    }
                    i3 = i4;
                    i = 0;
                }
                i2++;
                list2 = list;
                i = 0;
            }
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0209 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<StepBean> singleOutSideGrooveTip2ShoulderPath(KeyInfo r36, KeyAlignInfo r37, List<List<CutPoint>> r38) {
        /*
            Method dump skipped, instructions count: 596
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.singleOutSideGrooveTip2ShoulderPath(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, java.util.List):java.util.List");
    }

    private static List<StepBean> HY18Path(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            List<CutPoint> list2 = list.get(i);
            int i2 = 1;
            int size = list2.size() - 1;
            while (size >= 0) {
                CutPoint cutPoint = list2.get(size);
                int x = cutPoint.getX();
                int y = cutPoint.getY();
                if (size == list2.size() - i2) {
                    if (i == 0 && !z) {
                        arrayList.add(new StepBean(0, x, (int) ((keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - (50.0f / MachineData.dYScale)), 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y", false));
                        arrayList.add(new StepBean(0, 0, 0, keyAlignInfo.getCutDepth(), Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                        z = true;
                    } else {
                        arrayList.add(new StepBean(0, x, (int) ((keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - (50.0f / MachineData.dYScale)), 0, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y", false));
                        arrayList.add(new StepBean(0, 0, 0, keyAlignInfo.getCutDepth(), Speed.get_Speed_SpindleTurnOn_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                    }
                }
                arrayList.add(new StepBean("右边第" + (i + 1) + "刀，第" + (size + 1) + "点", 0, x, y, 0, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;U:Z;CUTSM", false));
                size += -1;
                i2 = 1;
            }
            arrayList.add(new StepBean(0, 0, 0, (int) ((-300.0f) / MachineData.dZScale), Speed.get_Speed_SpindleTurnOn_ZUp(), "U:X;U:Y;U:Z;", false));
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static List<StepBean> generateDoubleSideKeyCutSteps(KeyInfo r45, KeyAlignInfo r46, List<List<CutPoint>> r47, List<List<CutPoint>> r48, boolean r49) {
        /*
            Method dump skipped, instructions count: 1497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.generateDoubleSideKeyCutSteps(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, java.util.List, java.util.List, boolean):java.util.List");
    }

    private static List<StepBean> generateDoubleOutSideKeyCutSteps(KeyAlignInfo keyAlignInfo, List<List<CutPoint>> list, List<List<CutPoint>> list2, boolean z) {
        String str;
        int i;
        List<CutPoint> list3;
        String str2;
        String str3;
        String str4;
        int i2;
        List<CutPoint> list4;
        List<CutPoint> list5;
        int i3;
        if (keyAlignInfo.getClampFace() - (keyAlignInfo.getCutDepth() + keyAlignInfo.getKeyFace()) < 10.0f / MachineData.dZScale) {
            if (ClampManager.getInstance().getCurrentClamp() == Clamp.S1_A) {
                ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1a);
                return null;
            }
            if (ClampManager.getInstance().getCurrentClamp() != Clamp.S1_B) {
                return null;
            }
            ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS1b);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        keyAlignInfo.getRight();
        ToolSizeManager.getDecoderRadius2();
        ClampManager.getInstance().getDC().getxDistance();
        int left = (keyAlignInfo.getLeft() - ToolSizeManager.getDecoderRadius2()) + ClampManager.getInstance().getDC().getxDistance();
        int center = keyAlignInfo.getCenter() + ClampManager.getInstance().getDC().getxDistance();
        int keyFace = keyAlignInfo.getKeyFace() + keyAlignInfo.getCutDepth();
        int i4 = keyFace + ((int) ((-300.0f) / MachineData.dZScale));
        int size = list2.size();
        int i5 = 0;
        while (i5 < 1) {
            int i6 = 0;
            while (i6 < size) {
                List<CutPoint> list6 = list2.get(i6);
                List<CutPoint> list7 = list.get(i6);
                int i7 = 0;
                while (true) {
                    str = "刀，第";
                    if (i7 >= list7.size()) {
                        break;
                    }
                    CutPoint cutPoint = list7.get(i7);
                    int x = cutPoint.getX();
                    int y = cutPoint.getY();
                    if (i7 == 0) {
                        int cutterRadiusSize2 = ToolSizeManager.getCutterRadiusSize2() + left + startCutLeaveSpace;
                        if (i6 == 0) {
                            arrayList.add(new StepBean(0, cutterRadiusSize2, y, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y", false));
                            arrayList.add(new StepBean(0, 0, 0, keyAlignInfo.getCutDepth(), Speed.get_Speed_SpindleTurnOff_ZDown(), "U:X;U:Y;SL:U,Z;", false));
                            arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:1,8000"));
                        } else {
                            arrayList.add(new StepBean(0, cutterRadiusSize2, y, 0, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y", false));
                            str4 = "点";
                            i2 = i7;
                            list4 = list7;
                            list5 = list6;
                            i3 = i6;
                            arrayList.add(new StepBean(0, cutterRadiusSize2, y, keyFace, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                            int i8 = i2 + 1;
                            String sb = "左边第" +
                                    (i3 + 1) +
                                    "刀，第" +
                                    i8 +
                                    str4;
                            arrayList.add(new StepBean(sb, 0, x, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                            i7 = i8;
                            i6 = i3;
                            list7 = list4;
                            list6 = list5;
                        }
                    }
                    str4 = "点";
                    i2 = i7;
                    list4 = list7;
                    list5 = list6;
                    i3 = i6;
                    int i82 = i2 + 1;
                    String sb2 = "左边第" +
                            (i3 + 1) +
                            "刀，第" +
                            i82 +
                            str4;
                    arrayList.add(new StepBean(sb2, 0, x, y, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    i7 = i82;
                    i6 = i3;
                    list7 = list4;
                    list6 = list5;
                }
                List<CutPoint> list8 = list6;
                int i9 = i6;
                int i10 = i5;
                int i11 = left;
                String str5 = "点";
                arrayList.add(new StepBean(0, center, (keyAlignInfo.getTip() - ToolSizeManager.getCutterRadiusSize2()) - ((int) (200.0f / MachineData.dYScale)), keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK", false));
                int size2 = list8.size() - 1;
                while (size2 >= 0) {
                    List<CutPoint> list9 = list8;
                    CutPoint cutPoint2 = list9.get(size2);
                    int x2 = cutPoint2.getX();
                    int y2 = cutPoint2.getY();
                    if (size2 == list9.size() - 1) {
                        list3 = list9;
                        i = center;
                        str2 = str;
                        arrayList.add(new StepBean("右边第" + (i9 + 1) + str + (size2 + 1) + str5, 0, x2, y2, keyFace, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                        str3 = str5;
                    } else {
                        i = center;
                        list3 = list9;
                        str2 = str;
                        str3 = str5;
                        arrayList.add(new StepBean("右边第" + (i9 + 1) + str2 + (size2 + 1) + str5, 0, x2, y2, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    }
                    if (size2 == 0) {
                        int i12 = startCutLeaveSpace;
                        arrayList.add(new StepBean("离开右边", 0, x2 - i12, y2, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                        arrayList.add(new StepBean(0, x2 - i12, y2, i4, Speed.get_Speed_SpindleTurnOn_ZUp(), "C:5,X;C:5,Y;C:5,Z;CUTSM", false));
                    }
                    size2--;
                    str = str2;
                    center = i;
                    str5 = str3;
                    list8 = list3;
                }
                i6 = i9 + 1;
                left = i11;
                i5 = i10;
            }
            i5++;
        }
        arrayList.add(new StepBean(0, 0, 0, 0, getSpeedStr(0), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(new StepBean(998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        arrayList.add(new StepBean(999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c6, code lost:

        if (r12 < 3) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getDuplicateCutCountAndMaxDepth(KeyInfo r11, KeyAlignInfo r12, List<DestPoint> r13, int r14) {
        /*
            int r0 = r11.getType()
            r1 = 2
            if (r0 != r1) goto Ld
            int[] r11 = new int[r1]
            r11 = {x00d2: FILL_ARRAY_DATA , data: [1, 0} // fill-array
            return r11
        Ld:
            r0 = 0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            r4 = 0
        L13:
            int r5 = r13.size()
            r6 = 4
            if (r3 >= r5) goto L5c
            java.lang.Object r5 = r13.get(r3)
            com.cutting.machine.bean.DestPoint r5 = (com.cutting.machine.bean.DestPoint) r5
            int r7 = r5.getSpace()
            int r7 = r7 - r14
            int r8 = r12.getShoulder()
            if (r7 <= r8) goto L2c
            goto L59
        L2c:
            int r8 = r11.getType()
            if (r8 == 0) goto L38
            int r8 = r11.getType()
            if (r8 != r6) goto L49
        L38:
            float r6 = (float) r7
            int r7 = r12.getTip()
            float r7 = (float) r7
            r8 = 1135542272(0x43af0000, float:350.0)
            float r9 = com.cutting.machine.clamp.MachineData.dYScale
            float r8 = r8 / r9
            float r7 = r7 + r8
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L49
            goto L59
        L49:
            int r6 = r5.getDepth()
            int r4 = java.lang.Math.max(r6, r4)
            int r5 = r5.getDepth()
            int r2 = java.lang.Math.min(r5, r2)
        L59:
            int r3 = r3 + 1
            goto L13
        L5c:
            int r13 = r11.getType()
            r14 = 1
            if (r13 == 0) goto L74
            int r13 = r11.getType()
            if (r13 != r14) goto L6a
            goto L74
        L6a:
            int r13 = r12.getDecodeWidth2()
            int r13 = r13 - r2
            int r13 = java.lang.Math.abs(r13)
            goto L7a
        L74:
            int r13 = r4 - r2
            int r13 = java.lang.Math.abs(r13)
        L7a:
            int r2 = r11.getType()
            r3 = 3
            if (r2 != r3) goto L96
            int r12 = r12.getDecodeWidth2()
            float r12 = (float) r12
            r13 = 1065353216(0x3f800000, float:1.0)
            float r12 = r12 * r13
            int r13 = maxFeed()
            float r13 = (float) r13
            float r12 = r12 / r13
            double r12 = (double) r12
            double r12 = java.lang.Math.ceil(r12)
            goto La8
        L96:
            double r12 = (double) r13
            int r2 = com.cutting.machine.ToolSizeManager.getCutterSize2()
            double r7 = (double) r2
            r9 = 4605380978949069210(0x3fe999999999999a, double:0.8)
            double r7 = r7 * r9
            double r12 = r12 / r7
            double r12 = java.lang.Math.ceil(r12)
        La8:
            int r12 = (int) r12
            int r13 = r11.getType()
            if (r13 == r6) goto Lc6
            int r13 = r11.getType()
            if (r13 == r3) goto Lc6
            int r13 = r11.getType()
            if (r13 == 0) goto Lc6
            int r11 = r11.getType()
            if (r11 != r14) goto Lc2
            goto Lc6
        Lc2:
            if (r12 >= r1) goto Lc9
            r3 = 2
            goto Lca
        Lc6:
            if (r12 >= r3) goto Lc9
            goto Lca
        Lc9:
            r3 = r12
        Lca:
            int[] r11 = new int[r1]
            r11[r0] = r3
            r11[r14] = r4
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.getDuplicateCutCountAndMaxDepth(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, java.util.List, int):int[]");
    }

    private static int getInsideGrooveKeyDuplicateCutCount(KeyInfo keyInfo, KeyAlignInfo keyAlignInfo, KeyAlignInfo keyAlignInfo2, List<DestPoint> list, List<DestPoint> list2) {
        float f;
        int groove;
        int groove2;
        int max = Math.max(list.size(), list2.size()) - 1;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            f = (max * 0.6666667f) - 1.0f;
            if (i2 >= f) {
                break;
            }
            if (i2 <= list.size() - 1 && (groove2 = list.get(i2).getGroove()) > i3) {
                i3 = groove2;
            }
            i2++;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < f; i5++) {
            if (i5 <= list2.size() - 1 && (groove = list2.get(i5).getGroove()) > i4) {
                i4 = groove;
            }
        }
        int ceil = (int) (Math.ceil(Math.max(i3, i4) / (maxFeed() * 2.0f)) * 2.0d);
        if (ceil < 2) {
            ceil = 2;
        }
        Log.i(TAG, "cutCount: " + ceil);
        int i6 = 0;
        while (true) {
            if (i6 >= list.size()) {
                break;
            }
            DestPoint destPoint = list.get(i6);
            if (destPoint.getGroove() > maxFeed() * ceil) {
                destPoint.setSharpenPoint(true);
                break;
            }
            if (i6 == 0) {
                list.get(list.size() - 1).setSharpenPoint(true);
            }
            i6++;
        }
        while (true) {
            if (i >= list2.size()) {
                break;
            }
            DestPoint destPoint2 = list2.get(i);
            if (destPoint2.getGroove() > maxFeed() * ceil) {
                destPoint2.setSharpenPoint(true);
                break;
            }
            if (i == 0) {
                list2.get(list2.size() - 1).setSharpenPoint(true);
            }
            i++;
        }
        Log.i(TAG, "cutCount: " + ceil);
        return ceil;
    }

    private static void fixSingleInsideGrooveData(KeyAlignInfo keyAlignInfo, KeyAlignInfo keyAlignInfo2, List<DestPoint> list, List<DestPoint> list2) {
        int decodeWidth2;
        int decodeWidth22;
        int abs;
        if (list == null || list2 == null || list.size() == 0 || list2.size() == 0) {
            return;
        }
        int i = (int) (5.0f / MachineData.dXScale);
        for (int i2 = 0; i2 < list.size(); i2++) {
            DestPoint destPoint = list.get(i2);
            destPoint.setDepth(destPoint.getDepth() - i);
        }
        for (int i3 = 0; i3 < list2.size(); i3++) {
            DestPoint destPoint2 = list2.get(i3);
            destPoint2.setDepth(destPoint2.getDepth() - i);
        }
        DestPoint destPoint3 = list.get(list.size() - 1);
        if (destPoint3.getSpace() - keyAlignInfo2.getTip() > 100.0f / MachineData.dYScale) {
            list.add(new DestPoint((int) ((destPoint3.getSpace() - destPoint3.getDepth()) - (30.0f / MachineData.dYScale)), -((int) (5.0f / MachineData.dXScale)), false));
        }
        DestPoint destPoint4 = list2.get(list2.size() - 1);
        if (destPoint4.getSpace() - keyAlignInfo2.getTip() > 100.0f / MachineData.dYScale) {
            list2.add(new DestPoint((int) ((destPoint4.getSpace() - destPoint4.getDepth()) - (30.0f / MachineData.dYScale)), -((int) (5.0f / MachineData.dXScale)), false));
        }
        Log.i(TAG, "sizeA: " + list.size() + "--sizeB:" + list2.size());
        int max = Math.max(list.size(), list2.size()) + (-1);
        for (int i4 = max; i4 >= 0; i4--) {
            if (i4 <= list.size() - 1) {
                DestPoint destPoint5 = list.get(i4);
                int i5 = max;
                int i6 = i5;
                int i7 = Integer.MAX_VALUE;
                while (i5 >= 0) {
                    if (i5 <= list2.size() - 1 && (abs = Math.abs(list2.get(i5).getSpace() - destPoint5.getSpace())) <= i7 && abs < 50.0f / MachineData.dYScale) {
                        i6 = i5;
                        i7 = abs;
                    }
                    i5--;
                }
                if (i7 == Integer.MAX_VALUE) {
                    decodeWidth22 = Math.abs(keyAlignInfo.getDecodeWidth2() - destPoint5.getDepth());
                    DestPoint destPoint6 = new DestPoint(destPoint5.getSpace(), -((int) (5.0f / MachineData.dXScale)));
                    list2.add(destPoint6);
                    destPoint6.setInvalid(destPoint5.isInvalid());
                    destPoint6.setGroove(decodeWidth22);
                } else {
                    decodeWidth22 = (keyAlignInfo.getDecodeWidth2() - destPoint5.getDepth()) - list2.get(i6).getDepth();
                }
                destPoint5.setGroove(decodeWidth22);
            }
        }
        for (int i8 = max; i8 >= 0; i8--) {
            if (i8 <= list2.size() - 1) {
                DestPoint destPoint7 = list2.get(i8);
                int i9 = max;
                int i10 = i9;
                int i11 = Integer.MAX_VALUE;
                while (i9 >= 0) {
                    if (i9 <= list.size() - 1) {
                        int abs2 = Math.abs(destPoint7.getSpace() - list.get(i9).getSpace());
                        if (abs2 <= i11 && abs2 < 50.0f / MachineData.dYScale) {
                            i10 = i9;
                            i11 = abs2;
                        }
                    }
                    i9--;
                }
                if (i11 == Integer.MAX_VALUE) {
                    decodeWidth2 = Math.abs(keyAlignInfo.getDecodeWidth2() - destPoint7.getDepth());
                    DestPoint destPoint8 = new DestPoint(destPoint7.getSpace(), -((int) (5.0f / MachineData.dXScale)));
                    list.add(destPoint8);
                    destPoint8.setGroove(decodeWidth2);
                    destPoint8.setInvalid(destPoint7.isInvalid());
                } else {
                    decodeWidth2 = (keyAlignInfo.getDecodeWidth2() - destPoint7.getDepth()) - list.get(i10).getDepth();
                }
                destPoint7.setGroove(decodeWidth2);
            }
        }
        Collections.sort(list, new Comparator<DestPoint>() { // from class: com.cutting.machine.StepsGenerateUtil.1
            @Override // java.util.Comparator
            public int compare(DestPoint destPoint9, DestPoint destPoint10) {
                return destPoint10.getSpace() - destPoint9.getSpace();
            }
        });
        Collections.sort(list2, new Comparator<DestPoint>() { // from class: com.cutting.machine.StepsGenerateUtil.2
            @Override // java.util.Comparator
            public int compare(DestPoint destPoint9, DestPoint destPoint10) {
                return destPoint10.getSpace() - destPoint9.getSpace();
            }
        });
    }

    private static int getKeyBlankCutCount(KeyInfo keyInfo, List<DestPoint> list) {
        int cutterSize;
        float abs;
        double ceil;
        int groove = keyInfo.getGroove();
        int i = (keyInfo.getIcCard() == 1372 || keyInfo.getIcCard() == 1311) ? 380 : 0;
        if (keyInfo.getType() == 5 || (keyInfo.getType() == 2 && groove > 0)) {
            cutterSize = ((int) (groove / ((ToolSizeManager.getCutterSize() * 2) * 0.95d))) + 1;
        } else {
            if (keyInfo.getType() == 3) {
                ceil = Math.ceil(((keyInfo.getWidth() - i) / MachineData.dXScale) / maxFeed());
            } else {
                if (keyInfo.getType() == 7 || keyInfo.getType() == 8) {
                    return 1;
                }
                int i2 = Integer.MAX_VALUE;
                for (int i3 = 0; i3 < list.size(); i3++) {
                    DestPoint destPoint = list.get(i3);
                    if (destPoint.isTooth()) {
                        i2 = Math.min(destPoint.getDepth(), i2);
                    }
                }
                if (keyInfo.getType() == 2 && groove == 0) {
                    abs = Math.abs((keyInfo.getWidth() / (MachineData.dXScale * 2.0f)) - i2);
                } else {
                    abs = Math.abs(((keyInfo.getWidth() - i) / MachineData.dXScale) - i2);
                }
                ceil = Math.ceil(((int) abs) / (ToolSizeManager.getCutterSize2() * 0.95d));
            }
            cutterSize = (int) ceil;
        }
        if (keyInfo.getType() == 5 || (keyInfo.getType() == 2 && groove > 0)) {
            if (cutterSize < 1) {
                return 1;
            }
        } else if (keyInfo.getType() == 4 || keyInfo.getType() == 0 || keyInfo.getType() == 3) {
            if (cutterSize < 3) {
                return 3;
            }
        } else if (cutterSize < 2) {
            return 2;
        }
        return cutterSize;
    }

    /* JADX WARN: Code restructure failed: missing block: B:128:0x02f2, code lost:

        if (r14 == 2) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x03b1, code lost:

        if (r22.getType() == 3) goto L176;
     */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x040a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0361  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<List<CutPoint>> generateDuplicateMultiCutPath(KeyInfo r22, KeyAlignInfo r23, KeyAlignInfo r24, List<DestPoint> r25, int[] r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 1095
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.generateDuplicateMultiCutPath(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, com.cutting.machine.KeyAlignInfo, java.util.List, int[], boolean):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:121:0x025d, code lost:

        r0 = (((r16 + r4) + r0) + r2) + r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:52:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x033b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0285  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static List<List<CutPoint>> generateKeyBlankMultiCutPath(KeyInfo r21, KeyAlignInfo r22, List<DestPoint> r23, int r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 901
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.StepsGenerateUtil.generateKeyBlankMultiCutPath(com.cutting.machine.bean.KeyInfo, com.cutting.machine.KeyAlignInfo, java.util.List, int, boolean):java.util.List");
    }

    public static List<DestPoint> getCircle(int i, int i2, int i3, int i4) {
        ArrayList arrayList = new ArrayList();
        double d = 3.141592653589793d / i4;
        double cos = i3 / Math.cos(d);
        for (int i5 = 0; i5 < i4; i5++) {
            double d2 = ((i5 * 2) + 1) * d;
            arrayList.add(new DestPoint((int) (Math.cos(d2) * cos), (int) (Math.sin(d2) * cos)));
        }
        return arrayList;
    }

    public static List<CutPoint> getArc(int i, int i2, int i3, double d, double d2, int i4, int i5) {
        ArrayList arrayList = new ArrayList();
        double d3 = (d - d2) / (i4 - 1);
        for (int i6 = 0; i6 <= i4; i6++) {
            double d4 = i3;
            double d5 = d - (i6 * d3);
            arrayList.add(new CutPoint((int) (i - (Math.cos(d5) * d4)), (int) ((d4 * Math.sin(d5)) + i2), i5));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.cutting.machine.StepsGenerateUtil$3 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C19693 {
        static final /* synthetic */ int[] $SwitchMap$com$liying$core$bean$KeyType;

        static {
            int[] iArr = new int[KeyType.values().length];
            $SwitchMap$com$liying$core$bean$KeyType = iArr;
            try {
                iArr[KeyType.DOUBLE_SIDE_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_OUTSIDE_GROOVE_KEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_OUTSIDE_GROOVE_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SINGLE_SIDE_KEY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.DOUBLE_INSIDE_GROOVE_KEY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.LEFT_GROOVE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.RIGHT_GROOVE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.TWO_GROOVE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.THREE_GROOVE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$liying$core$bean$KeyType[KeyType.SIDE_HOLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
