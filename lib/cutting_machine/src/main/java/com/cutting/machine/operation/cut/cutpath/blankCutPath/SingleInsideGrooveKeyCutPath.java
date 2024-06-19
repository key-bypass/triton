package com.liying.core.operation.cut.cutpath.blankCutPath;

import android.graphics.Point;
import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.KeyPoint;
import com.liying.core.operation.cut.cutpath.KeyPointFactory;
import com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SingleInsideGrooveKeyCutPath extends KeyBlankCutPath {
    private static final float[] layer2 = {0.7f, 1.0f};
    private static final float[] layer3 = {0.4f, 0.8f, 1.0f};

    public SingleInsideGrooveKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 2;
        }
        return (int) (Math.ceil((getGroove() * 1.0d) / (getMaxCut() * 2)) * 2.0d);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0219  */
    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.liying.core.bean.StepBean> getCutPathSteps() {
        /*
            Method dump skipped, instructions count: 680
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.SingleInsideGrooveKeyCutPath.getCutPathSteps():java.util.List");
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            int y = getKeyPointsGroup().get(i).get(0).getY();
            ArrayList arrayList2 = new ArrayList();
            if (getKeyId() != 1309 && getKeyId() != 1287 && getKeyId() != 601287) {
                int innerCutX = getInnerCutX() + 100;
                int innerCutY = getInnerCutY();
                if (innerCutX != 0) {
                    x -= innerCutX;
                }
                if (innerCutY != -1 && innerCutY != 0) {
                    y = innerCutY;
                }
                arrayList2.add(KeyPointFactory.getKeyPoint(x, y));
            } else {
                arrayList2.add(KeyPointFactory.getKeyPoint(x - 1, y));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getEndPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            List<KeyPoint> r3 = getKeyPointsGroup().get(i);
            KeyPoint keyPoint = r3.get(r3.size() - 1);
            ArrayList arrayList2 = new ArrayList();
            int nose = getNose();
            int shoulder2TipDistance = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getShoulder2TipDistance() : 0;
            if (getKeyId() == 1421 || getKeyId() == 1424 || getKeyId() == 1435) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, 100)));
            } else if (nose != 0) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance - nose, 0)));
            } else {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, 0)));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        int i;
        int i2;
        List<KeyPoint> list2;
        String str;
        String str2;
        ArrayList arrayList = new ArrayList();
        getLeftCutter();
        getRightCutter();
        if (list != null && list.size() != 0) {
            UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
            int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
            int size = list.size();
            int[] cutZ = cutZ();
            int i3 = 0;
            while (i3 < getLayer()) {
                int i4 = cutZ[i3];
                int i5 = 0;
                while (true) {
                    int i6 = size / 2;
                    if (i5 < i6) {
                        int i7 = i5 + 1;
                        List<KeyPoint> list3 = list.get(i6 - i7);
                        int size2 = list3.size() - 1;
                        while (size2 >= 0) {
                            KeyPoint keyPoint = list3.get(size2);
                            int x = keyPoint.getX();
                            int KeyY2MachineValue = KeyY2MachineValue(keyPoint.getY());
                            int keyX2MachineValue = keyX2MachineValue(x);
                            if (size2 != list3.size() - 1) {
                                list2 = list3;
                                str = "刀,第";
                                str2 = "点";
                            } else if (i3 == 0 && i5 == 0) {
                                str = "刀,第";
                                list2 = list3;
                                str2 = "点";
                                arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, KeyY2MachineValue, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, KeyY2MachineValue, i4, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter, KeyY2MachineValue, i4, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                            } else {
                                list2 = list3;
                                str = "刀,第";
                                str2 = "点";
                                arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, KeyY2MachineValue, i4, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                                arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, KeyY2MachineValue, i4, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                            arrayList.add(StepBeanFactory.getCutStepBean("左边第" + i7 + str + (size2 + 1) + str2, keyX2MachineValue, KeyY2MachineValue, i4, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            size2 += -1;
                            list3 = list2;
                        }
                        String str3 = "点";
                        String str4 = "刀,第";
                        List<KeyPoint> list4 = list.get(i6 + i5);
                        int i8 = 0;
                        while (i8 < list4.size()) {
                            KeyPoint keyPoint2 = list4.get(i8);
                            int x2 = keyPoint2.getX();
                            int KeyY2MachineValue2 = KeyY2MachineValue(keyPoint2.getY());
                            int keyX2MachineValue2 = keyX2MachineValue(x2);
                            StringBuilder sb = new StringBuilder();
                            sb.append("右边第");
                            sb.append(i7);
                            sb.append(str4);
                            int i9 = i8 + 1;
                            sb.append(i9);
                            sb.append(str3);
                            String str5 = str3;
                            int i10 = i8;
                            String str6 = str4;
                            arrayList.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue2, KeyY2MachineValue2, i4, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            if (i10 == list4.size() - 1) {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, KeyY2MachineValue2, i4, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                            str4 = str6;
                            i8 = i9;
                            str3 = str5;
                        }
                        int i11 = i6 - 1;
                        if (i5 == i11 && i3 == getLayer() - 1) {
                            List<KeyPoint> list5 = list.get(i11);
                            List<KeyPoint> list6 = list.get(i6);
                            KeyPoint keyPoint3 = list5.get(list5.size() - 1);
                            KeyPoint keyPoint4 = list5.get(list5.size() - 2);
                            KeyPoint keyPoint5 = list6.get(list6.size() - 1);
                            KeyPoint keyPoint6 = list6.get(list6.size() - 2);
                            i = i7;
                            i2 = i3;
                            arrayList.addAll(cutTip(new Point(keyX2MachineValue(keyPoint3.getX()), KeyY2MachineValue(keyPoint3.getY()) - UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint4.getX()), KeyY2MachineValue(keyPoint4.getY()) - UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint5.getX()), KeyY2MachineValue(keyPoint5.getY()) + UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint6.getX()), KeyY2MachineValue(keyPoint6.getY()) + UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), i4));
                        } else {
                            i = i7;
                            i2 = i3;
                        }
                        i5 = i;
                        i3 = i2;
                    }
                }
                i3++;
            }
            backOrigin(arrayList);
        }
        return arrayList;
    }

    private int KeyY2MachineValue(int i) {
        if (sideA()) {
            return keyY2MachineValueBaseLeft(i);
        }
        return KeyY2MachineValueBaseRight(i);
    }

    public List<KeyPoint> getArc(int i, int i2, int i3, int i4, int i5) {
        double atan;
        double atan2;
        int i6 = i5;
        int i7 = (i + i3) / 2;
        int i8 = (i2 + i4) / 2;
        double d = i - i3;
        double d2 = i2 - i4;
        int sqrt = ((int) Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))) / 2;
        double d3 = d / (d2 * 1.0d);
        if (sideA()) {
            atan = Math.atan(d3);
            atan2 = Math.atan(d3) + 3.141592653589793d;
        } else {
            atan = Math.atan(d3) + 3.141592653589793d;
            atan2 = Math.atan(d3);
        }
        ArrayList arrayList = new ArrayList();
        double d4 = (atan - atan2) / (i6 - 1);
        int i9 = 0;
        while (i9 < i6) {
            double d5 = sqrt;
            double d6 = (i9 * d4) + atan;
            arrayList.add(KeyPointFactory.getKeyPoint((int) (i7 + (Math.sin(d6) * d5)), (int) ((d5 * Math.cos(d6)) + i8)));
            i9++;
            i6 = i5;
            i7 = i7;
        }
        return arrayList;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected int[] splitCut(int i) {
        int cutCount = cutCount();
        int r4;
        int[] iArr = new int[cutCount];
        int groove = getGroove() - ToolSizeManager.getCutterSize();
        if (groove < 0) {
            groove = 0;
        }
        float f = (groove * 1.0f) / (cutCount - 1);
        for (int i2 = 0; i2 < cutCount; i2++) {
            iArr[i2] = ((int) ((sideA() ? i2 : r4 - i2) * f)) + i + getCutterRadiusSize();
        }
        return iArr;
    }

    protected int[] splitCutTip(int i) {
        int cutCount = cutCount();
        int[] iArr = new int[cutCount];
        int width = getWidth() - i;
        int i2 = 0;
        if (width < 0) {
            width = 0;
        }
        float f = (width * 1.0f) / cutCount;
        if (f > getMaxCut()) {
            f = getMaxCut();
        }
        while (i2 < cutCount) {
            iArr[i2] = ((int) ((i2 < cutCount / 2 ? i2 : (cutCount - 1) - i2) * f)) + i + getCutterRadiusSize();
            i2++;
        }
        return iArr;
    }

    private boolean sideA() {
        return getSide() == 0 || getSide() == 5;
    }

    private int[] cutZ() {
        int i;
        float f;
        float f2;
        int cutDepth = getCutDepth();
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
                iArr[i2] = getKeyFace() + UnitConvertUtil.cmm2StepZ(i);
            }
            i = (int) (f * f2);
            iArr[i2] = getKeyFace() + UnitConvertUtil.cmm2StepZ(i);
        }
        return iArr;
    }
}
