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
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SingleInsideGrooveKeyCutNewPath extends KeyBlankCutPath {
    private static final float[] layer2 = {0.6f, 1.0f};
    private static final float[] layer3 = {0.4f, 0.8f, 1.0f};

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected int[] splitCut(int i) {
        return new int[0];
    }

    public SingleInsideGrooveKeyCutNewPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 2;
        }
        return (int) (Math.ceil((getGroove() * 1.0d) / (getMaxCut() * 2)) * 2.0d);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02df  */
    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.liying.core.bean.StepBean> getCutPathSteps() {
        /*
            Method dump skipped, instructions count: 916
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.SingleInsideGrooveKeyCutNewPath.getCutPathSteps():java.util.List");
    }

    private void fixSecondarySideSpace(List<List<KeyPoint>> list) {
        for (int i = 0; i < list.size(); i++) {
            List<KeyPoint> list2 = list.get(i);
            int i2 = 0;
            while (i2 < list2.size() - 1) {
                KeyPoint keyPoint = list2.get(i2);
                i2++;
                KeyPoint keyPoint2 = list2.get(i2);
                if (keyPoint2.getX() - keyPoint.getX() != 0) {
                    int groove = (int) ((getGroove() - getCutterRadiusSize()) * Math.tan(Math.atan(((keyPoint2.getY() - keyPoint.getY()) * 1.0f) / r6) / 2.0d));
                    keyPoint.setX(keyPoint.getX() - groove);
                    keyPoint2.setX(keyPoint2.getX() - groove);
                }
            }
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            List<KeyPoint> list3 = list.get(i3);
            int i4 = 0;
            while (i4 < list3.size() - 1) {
                KeyPoint keyPoint3 = list3.get(i4);
                int i5 = i4 + 1;
                KeyPoint keyPoint4 = list3.get(i5);
                if (keyPoint4.getX() - keyPoint3.getX() < 0) {
                    if (i4 == 0 || i4 == list3.size() - 1) {
                        keyPoint3.setX(keyPoint4.getX());
                    } else {
                        KeyPoint keyPoint5 = list3.get(i4 - 1);
                        KeyPoint keyPoint6 = list3.get(i4 + 2);
                        Point intersectPoint = getIntersectPoint(new Point(keyPoint5.getX(), keyPoint5.getY()), new Point(keyPoint3.getX(), keyPoint3.getY()), new Point(keyPoint4.getX(), keyPoint4.getY()), new Point(keyPoint6.getX(), keyPoint6.getY()));
                        keyPoint3.setX(intersectPoint.x);
                        keyPoint3.setY(intersectPoint.y);
                        keyPoint4.setX(intersectPoint.x);
                        keyPoint4.setY(intersectPoint.y);
                    }
                }
                i4 = i5;
            }
        }
    }

    private List<List<KeyPoint>> clonePointsGroup(List<List<KeyPoint>> list) {
        ArrayList arrayList = new ArrayList();
        for (List<KeyPoint> list2 : list) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<KeyPoint> it = list2.iterator();
            while (it.hasNext()) {
                try {
                    arrayList2.add(it.next().m603clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
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

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list, int i) {
        int i2;
        int i3;
        boolean z;
        String str;
        String str2;
        int i4 = 1;
        boolean z2 = i == 0;
        boolean z3 = i == getKeyPointsGroup().size() - 1;
        ArrayList arrayList = new ArrayList();
        getLeftCutter();
        getRightCutter();
        if (list != null && list.size() != 0) {
            UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
            int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 100);
            int size = list.size();
            int[] cutZ = cutZ();
            int i5 = 0;
            while (i5 < getLayer()) {
                int i6 = cutZ[i5];
                int i7 = 0;
                while (true) {
                    int i8 = size / 2;
                    if (i7 < i8) {
                        int i9 = i7 + 1;
                        List<KeyPoint> list2 = list.get(i8 - i9);
                        int size2 = list2.size() - i4;
                        while (size2 >= 0) {
                            KeyPoint keyPoint = list2.get(size2);
                            int x = keyPoint.getX();
                            int KeyY2MachineValue = KeyY2MachineValue(keyPoint.getY(), i);
                            int keyX2MachineValue = keyX2MachineValue(x);
                            List<KeyPoint> list3 = list2;
                            if (size2 != list2.size() - 1) {
                                z = z2;
                                str = "刀,第";
                                str2 = "点";
                            } else if (i5 == 0 && i7 == 0 && z2) {
                                z = z2;
                                str = "刀,第";
                                str2 = "点";
                                arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, KeyY2MachineValue, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, KeyY2MachineValue, i6, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                                arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter, KeyY2MachineValue, i6, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                            } else {
                                z = z2;
                                str = "刀,第";
                                str2 = "点";
                                arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, KeyY2MachineValue, i6, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                                arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, KeyY2MachineValue, i6, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                            arrayList.add(StepBeanFactory.getCutStepBean("左边第" + i9 + str + (size2 + 1) + str2, keyX2MachineValue, KeyY2MachineValue, i6, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            size2 += -1;
                            list2 = list3;
                            z2 = z;
                        }
                        boolean z4 = z2;
                        List<KeyPoint> list4 = list.get(i8 + i7);
                        int i10 = 0;
                        while (i10 < list4.size()) {
                            KeyPoint keyPoint2 = list4.get(i10);
                            int x2 = keyPoint2.getX();
                            int KeyY2MachineValue2 = KeyY2MachineValue(keyPoint2.getY(), i);
                            int keyX2MachineValue2 = keyX2MachineValue(x2);
                            StringBuilder sb = new StringBuilder();
                            boolean z5 = z3;
                            sb.append("右边第");
                            sb.append(i9);
                            sb.append("刀,第");
                            int i11 = i10 + 1;
                            sb.append(i11);
                            sb.append("点");
                            arrayList.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue2, KeyY2MachineValue2, i6, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            if (i10 == list4.size() - 1) {
                                arrayList.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, KeyY2MachineValue2, i6, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                            }
                            i10 = i11;
                            z3 = z5;
                        }
                        boolean z6 = z3;
                        int i12 = i8 - 1;
                        if (i7 != i12) {
                            i2 = i9;
                            i3 = i5;
                        } else if (i5 == getLayer() - 1) {
                            List<KeyPoint> list5 = list.get(i12);
                            List<KeyPoint> list6 = list.get(i8);
                            KeyPoint keyPoint3 = list5.get(list5.size() - 1);
                            KeyPoint keyPoint4 = list5.get(list5.size() - 2);
                            KeyPoint keyPoint5 = list6.get(list6.size() - 1);
                            KeyPoint keyPoint6 = list6.get(list6.size() - 2);
                            i2 = i9;
                            i3 = i5;
                            arrayList.addAll(cutTip(new Point(keyX2MachineValue(keyPoint3.getX()), KeyY2MachineValue(keyPoint3.getY(), i) - UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint4.getX()), KeyY2MachineValue(keyPoint4.getY(), i) - UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint5.getX()), KeyY2MachineValue(keyPoint5.getY(), i) + UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), new Point(keyX2MachineValue(keyPoint6.getX()), KeyY2MachineValue(keyPoint6.getY(), i) + UnitConvertUtil.yKey2Machine(ToolSizeManager.getCutterRadiusSize())), i6));
                        } else {
                            i2 = i9;
                            i3 = i5;
                        }
                        i7 = i2;
                        i5 = i3;
                        z3 = z6;
                        z2 = z4;
                        i4 = 1;
                    }
                }
                i5++;
                z2 = z2;
                i4 = 1;
            }
            if (z3) {
                backOrigin(arrayList);
            }
        }
        return arrayList;
    }

    private int KeyY2MachineValue(int i, int i2) {
        if (sideA(i2)) {
            return keyY2MachineValueBaseLeft(i);
        }
        return KeyY2MachineValueBaseRight(i);
    }

    public List<KeyPoint> getArc(int i, int i2, int i3, int i4, int i5, int i6) {
        double atan;
        double atan2;
        int i7 = i5;
        int i8 = (i + i3) / 2;
        int i9 = (i2 + i4) / 2;
        double d = i - i3;
        double d2 = i2 - i4;
        int sqrt = ((int) Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))) / 2;
        double d3 = d / (d2 * 1.0d);
        if (sideA(i6)) {
            atan = Math.atan(d3);
            atan2 = Math.atan(d3) + 3.141592653589793d;
        } else {
            atan = Math.atan(d3) + 3.141592653589793d;
            atan2 = Math.atan(d3);
        }
        ArrayList arrayList = new ArrayList();
        double d4 = (atan - atan2) / (i7 - 1);
        int i10 = 0;
        while (i10 < i7) {
            double d5 = sqrt;
            double d6 = (i10 * d4) + atan;
            arrayList.add(KeyPointFactory.getKeyPoint((int) (i8 + (Math.sin(d6) * d5)), (int) ((d5 * Math.cos(d6)) + i9)));
            i10++;
            i7 = i5;
            i8 = i8;
        }
        return arrayList;
    }

    protected int[] splitCut(int i, int i2) {
        int cutCount = cutCount();
        int[] iArr = new int[cutCount];
        int groove = getGroove() - ToolSizeManager.getCutterSize();
        if (groove < 0) {
            groove = 0;
        }
        float f = (groove * 1.0f) / (cutCount - 1);
        for (int i3 = 0; i3 < cutCount; i3++) {
            iArr[i3] = ((int) ((sideA(i2) ? i3 : r4 - i3) * f)) + i + getCutterRadiusSize();
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

    private boolean sideA(int i) {
        if (getKeyId() == 601287) {
            return true;
        }
        return getSide() == 6 ? i == 0 : getSide() == 0 || getSide() == 5;
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
