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
import com.spl.key.Key;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class PeugeotKeyCutPath extends KeyBlankCutPath {
    private static final float[] layer2 = {0.7f, 1.0f};
    private static final float[] layer3 = {0.4f, 0.8f, 1.0f};

    public PeugeotKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 1;
        }
        return (int) Math.ceil((getGroove() * 1.0d) / (getMaxCut() * 2));
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        List<KeyPoint> list;
        int i;
        int i2;
        List<KeyPoint> list2;
        int i3;
        int[] iArr;
        List<List<KeyPoint>> keyPointsGroup = getKeyPointsGroup();
        List<List<KeyPoint>> startPointsGroup = getStartPointsGroup();
        List<List<KeyPoint>> endPointsGroup = getEndPointsGroup();
        fixSpace(keyPointsGroup);
        for (int i4 = 0; i4 < keyPointsGroup.size(); i4++) {
            keyPointsGroup.get(i4).addAll(0, startPointsGroup.get(i4));
            keyPointsGroup.get(i4).addAll(endPointsGroup.get(i4));
        }
        ArrayList<KeyPoint> arrayList = new ArrayList<KeyPoint>();
        ArrayList<KeyPoint> arrayList2 = new ArrayList<KeyPoint>();
        for (int i5 = 0; i5 < cutCount(); i5++) {
            ArrayList<KeyPoint> arrayList2 = new ArrayList();
            arrayList.add(arrayList2);

        }
        int miniSpace = getMiniSpace();
        for (int i6 = 0; i6 < keyPointsGroup.size(); i6++) {
            List<KeyPoint> list3 = keyPointsGroup.get(i6);
            for (int i7 = 0; i7 < list3.size(); i7++) {
                KeyPoint keyPoint = list3.get(i7);
                int[] splitCut = splitCut(keyPoint.getY());
                int i8 = 0;
                while (i8 < cutCount()) {
                    if (i6 == 0) {
                        list = (List<KeyPoint>) arrayList.get(i8);
                    } else {
                        list = (List<KeyPoint>) arrayList.get(i8);
                    }
                    List<KeyPoint> list4 = list;
                    if (keyPoint.isDoNotSplitDepth()) {
                        i = keyPoint.getY();
                    } else {
                        i = splitCut[i8];
                    }
                    int i9 = i;
                    if (i6 == 0 && i7 == 0) {
                        i2 = i9;
                        list2 = list4;
                        i3 = i8;
                        iArr = splitCut;
                        list2.addAll(getArc(miniSpace, i2, miniSpace, splitCut[(splitCut.length - 1) - i8], 10));
                    } else {
                        i2 = i9;
                        list2 = list4;
                        i3 = i8;
                        iArr = splitCut;
                    }
                    list2.add(KeyPointFactory.getKeyPoint(keyPoint.getX(), i2));
                    i8 = i3 + 1;
                    splitCut = iArr;
                }
            }
        }
        return generateKeyCutSteps(arrayList, arrayList2);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            int y = getKeyPointsGroup().get(i).get(0).getY();
            ArrayList arrayList2 = new ArrayList();
            int innerCutX = getInnerCutX() + 100;
            int innerCutY = getInnerCutY();
            if (innerCutX != 0) {
                x -= innerCutX;
            }
            if (innerCutY != -1 && innerCutY != 0) {
                y = innerCutY;
            }
            arrayList2.add(KeyPointFactory.getKeyPoint(x, y));
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getEndPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            KeyPoint keyPoint = getKeyPointsGroup().get(i).get(r3.size() - 1);
            ArrayList arrayList2 = new ArrayList();
            int nose = getNose();
            int guide = getGuide();
            int shoulder2TipDistance = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getShoulder2TipDistance() : 0;
            if (nose != 0) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance - nose, 0)));
            } else if (guide != 0) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(0, guide)));
            } else {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, 0)));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list, List<List<KeyPoint>> list2) {
        int i;
        int i2;
        int i3;
        List<KeyPoint> list3;
        String str;
        String str2;
        int i4;
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() != 0) {
            int tipCutter = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
            int size = list.size();
            int[] cutZ = cutZ();
            int i5 = 0;
            while (i5 < getLayer()) {
                int i6 = cutZ[i5];
                int i7 = 0;
                while (i7 < size) {
                    int i8 = i7 + 1;
                    int i9 = size - i8;
                    List<KeyPoint> list4 = list.get(i9);
                    int size2 = list4.size() - 1;
                    while (size2 >= 0) {
                        KeyPoint keyPoint = list4.get(size2);
                        int x = keyPoint.getX();
                        int keyY2MachineValueBaseLeft = keyY2MachineValueBaseLeft(keyPoint.getY());
                        int keyX2MachineValue = keyX2MachineValue(x);
                        if (size2 != list4.size() - 1) {
                            list3 = list4;
                            str = "刀,第";
                            str2 = "点";
                            i4 = size2;
                        } else if (i5 == 0 && i7 == 0) {
                            list3 = list4;
                            str = "刀,第";
                            str2 = "点";
                            i4 = size2;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, keyY2MachineValueBaseLeft, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, keyY2MachineValueBaseLeft, i6, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter, keyY2MachineValueBaseLeft, i6, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                        } else {
                            list3 = list4;
                            str = "刀,第";
                            str2 = "点";
                            i4 = size2;
                            arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter, keyY2MachineValueBaseLeft, i6, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                            arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter, keyY2MachineValueBaseLeft, i6, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        }
                        arrayList.add(StepBeanFactory.getCutStepBean("左边第" + i8 + str + (i4 + 1) + str2, keyX2MachineValue, keyY2MachineValueBaseLeft, i6, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        size2 = i4 + (-1);
                        list4 = list3;
                    }
                    String str3 = "刀,第";
                    String str4 = "点";
                    List<KeyPoint> list5 = list2.get(i9);
                    int i10 = 0;
                    while (i10 < list5.size()) {
                        KeyPoint keyPoint2 = list5.get(i10);
                        int x2 = keyPoint2.getX();
                        int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(keyPoint2.getY());
                        int keyX2MachineValue2 = keyX2MachineValue(x2);
                        StringBuilder sb = new StringBuilder();
                        sb.append("右边第");
                        sb.append(i8);
                        sb.append(str3);
                        int i11 = i10 + 1;
                        sb.append(i11);
                        sb.append(str4);
                        String str5 = str3;
                        int i12 = i10;
                        List<KeyPoint> list6 = list5;
                        String str6 = str4;
                        arrayList.add(StepBeanFactory.getCutStepBean(sb.toString(), keyX2MachineValue2, KeyY2MachineValueBaseRight, i6, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        if (i12 == list6.size() - 1) {
                            arrayList.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter, KeyY2MachineValueBaseRight, i6, Speed.get_Speed_CuttingOut(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                        }
                        str4 = str6;
                        i10 = i11;
                        str3 = str5;
                        list5 = list6;
                    }
                    if (i7 == size - 1 && i5 == getLayer() - 1) {
                        List<KeyPoint> list7 = list.get(i9);
                        List<KeyPoint> list8 = list2.get(i9);
                        KeyPoint keyPoint3 = list7.get(list7.size() - 1);
                        KeyPoint keyPoint4 = list7.get(list7.size() - 2);
                        KeyPoint keyPoint5 = list8.get(list8.size() - 1);
                        KeyPoint keyPoint6 = list8.get(list8.size() - 2);
                        i = i8;
                        i2 = i5;
                        i3 = size;
                        arrayList.addAll(cutTip(new Point(keyX2MachineValue(keyPoint3.getX()), keyY2MachineValueBaseLeft(keyPoint3.getY())), new Point(keyX2MachineValue(keyPoint4.getX()), keyY2MachineValueBaseLeft(keyPoint4.getY())), new Point(keyX2MachineValue(keyPoint5.getX()), KeyY2MachineValueBaseRight(keyPoint5.getY())), new Point(keyX2MachineValue(keyPoint6.getX()), KeyY2MachineValueBaseRight(keyPoint6.getY())), i6));
                    } else {
                        i = i8;
                        i2 = i5;
                        i3 = size;
                    }
                    size = i3;
                    i7 = i;
                    i5 = i2;
                }
                i5++;
            }
            backOrigin(arrayList);
        }
        return arrayList;
    }

    public List<KeyPoint> getArc(int i, int i2, int i3, int i4, int i5) {
        int i6 = (i + i3) / 2;
        int i7 = (i2 + i4) / 2;
        double d = i - i3;
        double d2 = i2 - i4;
        int sqrt = ((int) Math.sqrt(Math.pow(d, 2.0d) + Math.pow(d2, 2.0d))) / 2;
        double d3 = d / (d2 * 1.0d);
        double atan = Math.atan(d3);
        double atan2 = Math.atan(d3) + 3.141592653589793d;
        ArrayList arrayList = new ArrayList();
        double d4 = (atan - atan2) / (i5 - 1);
        for (int i8 = 0; i8 < i5; i8++) {
            double d5 = sqrt;
            double d6 = (i8 * d4) + atan;
            arrayList.add(KeyPointFactory.getKeyPoint((int) (i6 + (Math.sin(d6) * d5)), (int) ((d5 * Math.cos(d6)) + i7)));
        }
        return arrayList;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected int[] splitCut(int i) {
        int cutCount = cutCount() * 2;
        int[] iArr = new int[cutCount];
        int groove = getGroove() - ToolSizeManager.getCutterSize();
        if (groove < 0) {
            groove = 0;
        }
        float f = (groove * 1.0f) / (cutCount - 1);
        for (int i2 = 0; i2 < cutCount; i2++) {
            iArr[i2] = ((int) (i2 * f)) + i + getCutterRadiusSize();
        }
        return iArr;
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
