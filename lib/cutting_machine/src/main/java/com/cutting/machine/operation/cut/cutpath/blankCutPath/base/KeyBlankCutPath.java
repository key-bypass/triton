package com.liying.core.operation.cut.cutpath.blankCutPath.base;

import android.graphics.Point;
import com.liying.core.CuttingMachine;
import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.KeyInfo;
import com.liying.core.bean.KeyType;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.MachineData;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.KeyData;
import com.liying.core.operation.cut.cutpath.KeyPoint;
import com.liying.core.speed.Speed;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class KeyBlankCutPath {
    private static final int SINGLE_SIDE_ANGLE_DEFAULT = 100;
    private DataParam cutSetting;
    private KeyAlignInfo keyAlignInfo;
    private KeyData keyData;
    private List<List<KeyPoint>> keyPointsGroup = initKeyPoint();

    public abstract int cutCount();

    public abstract List<StepBean> getCutPathSteps();

    protected abstract List<List<KeyPoint>> getEndPointsGroup();

    protected abstract List<List<KeyPoint>> getStartPointsGroup();

    protected abstract int[] splitCut(int i);

    public KeyBlankCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        this.keyAlignInfo = keyAlignInfo;
        this.keyData = new KeyData(dataParam.getKeyInfo(), dataParam.getToothCodeReal());
        this.cutSetting = dataParam;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.util.List<com.liying.core.operation.cut.cutpath.KeyPoint>> initKeyPoint() {
        /*
            Method dump skipped, instructions count: 955
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath.initKeyPoint():java.util.List");
    }

    private KeyType getKeyType() {
        return getKeyData().getKeyType();
    }

    public boolean PeugeoKey() {
        return getKeyData().PeugeoKey();
    }

    public KeyData getKeyData() {
        return this.keyData;
    }

    public DataParam getCutSetting() {
        return this.cutSetting;
    }

    public void setCutSetting(DataParam dataParam) {
        this.cutSetting = dataParam;
    }

    public KeyAlignInfo getKeyAlignInfo() {
        return this.keyAlignInfo;
    }

    public String getExCut() {
        return getKeyData().getExCut();
    }

    public String getLstPosition() {
        return getKeyData().getLstPosition();
    }

    public int getKeyId() {
        return getKeyData().getKeyId();
    }

    public KeyInfo getKeyInfo() {
        return getKeyData().getKeyInfo();
    }

    public int getCutDeepest() {
        Iterator<List<Integer>> it = getKeyData().getRealDepthList().iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().iterator();
            while (it2.hasNext()) {
                i = Math.min(it2.next().intValue(), i);
            }
        }
        return i;
    }

    public int getMiniSpace() {
        Iterator<List<KeyPoint>> it = getKeyPointsGroup().iterator();
        int i = Integer.MAX_VALUE;
        while (it.hasNext()) {
            Iterator<KeyPoint> it2 = it.next().iterator();
            while (it2.hasNext()) {
                i = Math.min(it2.next().getX(), i);
            }
        }
        return i;
    }

    public int getMaxStandardDepth() {
        Iterator<List<Integer>> it = getKeyData().getKeyInfo().getDepthList().iterator();
        int i = 0;
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().iterator();
            while (it2.hasNext()) {
                i = Math.max(it2.next().intValue(), i);
            }
        }
        return i;
    }

    public int getSpaceStep(int i) {
        return ((int) (i / MachineData.getXRatio())) * getXDirection();
    }

    public int getYSteps(int i) {
        return (int) (i / MachineData.getYRatio());
    }

    public int getZSteps(int i) {
        return (int) (i / MachineData.getZRatio());
    }

    public int getXDirection() {
        return MachineData.getXDirection() * (getAlign() == KeyAlign.SHOULDERS_ALIGN ? 1 : -1);
    }

    public int getYDirection() {
        return MachineData.getYDirection();
    }

    public int getZDirection() {
        return MachineData.getZDirection();
    }

    public List<List<Integer>> getRealDepthList() {
        return getKeyData().getRealDepthList();
    }

    public List<List<Integer>> getSpaceList() {
        return getKeyData().getSpaceList();
    }

    public List<List<Integer>> getSpaceWidthList() {
        return getKeyData().getSpaceWidthList();
    }

    public int getWidth() {
        return getKeyData().getWidth();
    }

    public int getDecodeWidth() {
        return getKeyAlignInfo().getDecodeWidth();
    }

    public KeyAlign getAlign() {
        return getKeyData().getAlign();
    }

    public int getCutDepth() {
        return getCutSetting().getCutDepth();
    }

    public int getLayer() {
        if (isQuickCut()) {
            return 1;
        }
        return getCutSetting().getLayer();
    }

    public int getCutterSize() {
        return getCutSetting().getCutterSize();
    }

    public int getCutterRadiusSize() {
        return getCutSetting().getCutterSize() / 2;
    }

    public Clamp getClamp() {
        return getCutSetting().getClamp();
    }

    public int getMaxCut() {
        return (int) (getCutSetting().getCutterSize() * 0.95f);
    }

    public boolean isPlastic() {
        return getCutSetting().isPlasticKey();
    }

    public boolean isQuickCut() {
        return getCutSetting().isQuickCut();
    }

    public int getThick() {
        return getKeyData().getThick();
    }

    public int getHalfSpaceWidth(int i, int i2) {
        return getSpaceWidthList().get(i).get(i2).intValue() / 2;
    }

    public int getClampFace() {
        return getKeyAlignInfo().getClampFace();
    }

    public int getKeyFace() {
        return getKeyAlignInfo().getKeyFace();
    }

    public List<List<KeyPoint>> getKeyPointsGroup() {
        return this.keyPointsGroup;
    }

    public int getSpaceLeft(int i, int i2) {
        return getSpaceList().get(i).get(i2).intValue() - getHalfSpaceWidth(i, i2);
    }

    public int getSpaceRight(int i, int i2) {
        return getSingleSpace(i, i2) + getHalfSpaceWidth(i, i2);
    }

    public int getSingleRealDepth(int i, int i2) {
        return getRealDepthList().get(i).get(i2).intValue();
    }

    public int getSingleSpace(int i, int i2) {
        return getSpaceList().get(i).get(i2).intValue();
    }

    public String getCutSharpenType() {
        return getKeyData().getCutSharpenType();
    }

    public int getNose() {
        return getKeyData().getNose();
    }

    public int getGuide() {
        return getKeyData().getGuide();
    }

    public int getSide() {
        return getKeyData().getSide();
    }

    public int getGroove() {
        return getKeyData().getGroove() + 10;
    }

    public int getInnerCutX() {
        return getKeyData().getInnerCutX();
    }

    public int getInnerCutY() {
        return getKeyData().getInnerCutY();
    }

    public int getSingleSideKeyCutDepthFix() {
        return getCutSetting().getSingleSideKeyCutDepthFix();
    }

    public boolean upDownCut() {
        if (dimpleMotherSonKey()) {
            return true;
        }
        return getCutSetting().isDimpleUpDownCut();
    }

    public int getClampMode() {
        return getCutSetting().getClampMode();
    }

    public boolean dimpleMotherSonKey() {
        return getKeyData().getKeyInfo().isDimpleMotherSonKey();
    }

    public int getShoulder2TipDistance() {
        if (CuttingMachine.getInstance().isE9()) {
            return UnitConvertUtil.step2CmmX(Math.abs(getKeyAlignInfo().getShoulder() - getKeyAlignInfo().getTip()));
        }
        return UnitConvertUtil.step2CmmY(Math.abs(getKeyAlignInfo().getShoulder() - getKeyAlignInfo().getTip()));
    }

    public int getTipCutter() {
        return getKeyAlignInfo().getTipCutter();
    }

    public int getShoulderCutter() {
        return getKeyAlignInfo().getShoulderCutter();
    }

    public int getLeftCutter() {
        return this.keyAlignInfo.getLeftCutter();
    }

    public int getRightCutter() {
        return this.keyAlignInfo.getRightCutter();
    }

    public void backOrigin(List<StepBean> list) {
        list.add(StepBeanFactory.getStepBean("关闭主轴", 0, 0, 0, 0, Speed.get_Speed_Origin(), "U:X;U:Y;U:Z;SUP:0,0"));
        list.add(StepBeanFactory.getStepBean("抬Z轴", 998, 0, 0, 0, Speed.get_Speed_Origin(), ""));
        list.add(StepBeanFactory.getStepBean("回原点", 999, 0, 0, 0, Speed.get_Speed_Origin(), ""));
    }

    public KeyPoint calculateEndPoint(KeyPoint keyPoint, KeyPoint keyPoint2) {
        double x = keyPoint.getX();
        double y = keyPoint.getY();
        double x2 = keyPoint2.getX();
        double y2 = keyPoint2.getY();
        double atan = Math.atan(Math.abs((y - y2) / (x - x2)));
        return new KeyPoint((int) (x2 + (ToolSizeManager.getCutterRadiusSize() * Math.sin(atan))), (int) ((y2 + (ToolSizeManager.getCutterRadiusSize() * Math.cos(atan))) - ToolSizeManager.getCutterRadiusSize()));
    }

    public KeyPoint calculateEndPointOtherSide(KeyPoint keyPoint, KeyPoint keyPoint2) {
        double x = keyPoint.getX();
        double y = keyPoint.getY();
        double x2 = keyPoint2.getX();
        double y2 = keyPoint2.getY();
        double atan = Math.atan(Math.abs((y - y2) / (x - x2)));
        return new KeyPoint((int) (x2 + (ToolSizeManager.getCutterRadiusSize() * Math.sin(atan))), (int) ((y2 - (ToolSizeManager.getCutterRadiusSize() * Math.cos(atan))) + ToolSizeManager.getCutterRadiusSize()));
    }

    public int keyX2MachineValue(int i) {
        int tipCutter;
        int xKey2Machine;
        if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
            tipCutter = getShoulderCutter();
            xKey2Machine = UnitConvertUtil.xKey2Machine(i);
        } else {
            tipCutter = getTipCutter();
            xKey2Machine = UnitConvertUtil.xKey2Machine(i);
        }
        return tipCutter + xKey2Machine;
    }

    public int keyY2MachineValueBaseLeft(int i) {
        return getLeftCutter() - UnitConvertUtil.yKey2Machine(i);
    }

    public int KeyY2MachineValueBaseRight(int i) {
        return getRightCutter() + UnitConvertUtil.yKey2Machine(i);
    }

    public int LeaveTipX() {
        return getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fixSpace(List<List<KeyPoint>> list) {
        KeyPoint keyPoint;
        int i;
        KeyPoint keyPoint2;
        KeyPoint keyPoint3;
        int i2;
        KeyPoint keyPoint4;
        int cutterRadiusSize = (int) (getCutterRadiusSize() * Math.cos(Math.asin((getCutterRadiusSize() - 2.0d) / getCutterRadiusSize())));
        for (int i3 = 0; i3 < list.size(); i3++) {
            List<KeyPoint> list2 = list.get(i3);
            for (int i4 = 0; i4 < list2.size(); i4++) {
                KeyPoint keyPoint5 = list2.get(i4);
                List<KeyPoint> r6 = getStartPointsGroup().get(i3);
                if (!keyPoint5.isDoNotFixSpaceWidth()) {
                    if (i4 == 0) {
                        KeyPoint keyPoint6 = r6.get(r6.size() - 1);
                        keyPoint3 = new KeyPoint(keyPoint6.getX(), keyPoint6.getY());
                    } else {
                        keyPoint3 = list2.get(i4 - 1);
                    }
                    int y = keyPoint5.getY() - keyPoint3.getY();
                    if (keyPoint5.getX() - keyPoint3.getX() != 0) {
                        if (y < 0) {
                            i2 = cutterRadiusSize;
                        } else {
                            if (y == 0) {
                                int i5 = i4 + 1;
                                if (i5 >= list2.size()) {
                                    KeyPoint keyPoint7 = getEndPointsGroup().get(i3).get(0);
                                    keyPoint4 = new KeyPoint(keyPoint7.getX(), keyPoint7.getY());
                                } else {
                                    keyPoint4 = list2.get(i5);
                                }
                                if (keyPoint4.getY() - keyPoint5.getY() > 0) {
                                    i2 = -cutterRadiusSize;
                                }
                            }
                            i2 = 0;
                        }
                        keyPoint5.setX(keyPoint5.getX() + i2);
                    }
                }
            }
        }
        for (int i6 = 0; i6 < list.size(); i6++) {
            List<KeyPoint> list3 = list.get(i6);
            for (int i7 = 0; i7 < list3.size(); i7++) {
                KeyPoint keyPoint8 = list3.get(i7);
                List<KeyPoint> r5 = getStartPointsGroup().get(i6);
                if (!keyPoint8.isDoNotFixSpaceWidth()) {
                    if (i7 == 0) {
                        KeyPoint keyPoint9 = r5.get(r5.size() - 1);
                        keyPoint = new KeyPoint(keyPoint9.getX(), keyPoint9.getY());
                    } else {
                        keyPoint = list3.get(i7 - 1);
                    }
                    int y2 = keyPoint8.getY() - keyPoint.getY();
                    int x = keyPoint8.getX() - keyPoint.getX();
                    if (x != 0) {
                        if (y2 > 0) {
                            i = -getHighPointSpaceFix(y2, x);
                        } else {
                            if (y2 == 0) {
                                int i8 = i7 + 1;
                                if (i8 >= list3.size()) {
                                    KeyPoint keyPoint10 = getEndPointsGroup().get(i6).get(0);
                                    keyPoint2 = new KeyPoint(keyPoint10.getX(), keyPoint10.getY());
                                } else {
                                    keyPoint2 = list3.get(i8);
                                }
                                int y3 = keyPoint2.getY() - keyPoint8.getY();
                                int x2 = keyPoint2.getX() - keyPoint8.getX();
                                if (y3 < 0) {
                                    i = getHighPointSpaceFix(y3, x2);
                                }
                            }
                            i = 0;
                        }
                        keyPoint8.setX(keyPoint8.getX() + i);
                    }
                }
            }
        }
    }

    public List<StepBean> cutTip(Point point, Point point2, Point point3, Point point4, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        Point intersectPoint = getIntersectPoint(point, point2, point3, point4);
        if (point.x - intersectPoint.x > 0) {
            return arrayList;
        }
        int i3 = point.x;
        int i4 = point.y;
        int i5 = point3.x;
        int i6 = point3.y;
        int i7 = intersectPoint.x;
        int i8 = intersectPoint.y;
        int abs = (Math.abs(point.y - point3.y) - ToolSizeManager.getCutterRadiusSize2()) + UnitConvertUtil.cmm2StepY(30);
        int LeaveTipX = LeaveTipX();
        int yKeyCmm2Steps = UnitConvertUtil.yKeyCmm2Steps(ToolSizeManager.getCutterRadiusSize());
        if (abs > 0) {
            int ceil = (int) Math.ceil(abs / (ToolSizeManager.getCutterSize2() * 0.7d));
            int i9 = abs / (ceil * 2);
            int i10 = i6;
            int i11 = i7;
            int i12 = 0;
            while (i12 < ceil) {
                int yKey2MachineDire = i4 - UnitConvertUtil.yKey2MachineDire(i9);
                if (UnitConvertUtil.xKey2MachineDire(getTipCutter() - i3) > 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", getTipCutter(), yKey2MachineDire, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", i3, yKey2MachineDire, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                int xKey2MachineDire = UnitConvertUtil.xKey2MachineDire(yKeyCmm2Steps) + i11;
                int i13 = i12;
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", xKey2MachineDire, i8, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                i10 += UnitConvertUtil.yKey2MachineDire(i9);
                int i14 = ceil;
                arrayList.add(StepBeanFactory.getCutStepBean("修尖", i5, i10, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                if (UnitConvertUtil.xKey2MachineDire(getTipCutter() - i5) > 0) {
                    i2 = i13;
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", getTipCutter(), i10, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                } else {
                    i2 = i13;
                }
                if (i2 == i14 - 1) {
                    arrayList.add(StepBeanFactory.getCutStepBean("修尖", LeaveTipX, i10, i, Speed.get_Speed_CuttingSharpen(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                i12 = i2 + 1;
                i11 = xKey2MachineDire;
                ceil = i14;
                i4 = yKey2MachineDire;
            }
        }
        return arrayList;
    }

    private int getHighPointSpaceFix(int i, int i2) {
        return (int) ((ToolSizeManager.getCutterRadiusSize() * 1.0f) / Math.tan((3.141592653589793d - Math.atan(Math.abs((i * 1.0d) / i2))) / 2.0d));
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

    public ArrayList<Index> getPeugeoKeySortSpace() {
        ArrayList<Index> arrayList = new ArrayList<>();
        List<List<Integer>> spaceList = getSpaceList();
        for (int i = 0; i < spaceList.size(); i++) {
            List<Integer> list = spaceList.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(new Index(list.get(i2).intValue(), i, i2));
            }
        }
        Collections.sort(arrayList, new Comparator<Index>() { // from class: com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath.1
            @Override // java.util.Comparator
            public int compare(Index index, Index index2) {
                return index.getData() - index2.getData();
            }
        });
        return arrayList;
    }

    /* loaded from: classes2.dex */
    public static class Index {
        int column;
        int data;
        int row;

        public Index(int i, int i2, int i3) {
            this.data = i;
            this.row = i2;
            this.column = i3;
        }

        public int getData() {
            return this.data;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public String toString() {
            return "Index{data=" + this.data + ", row=" + this.row + ", colum=" + this.column + '}';
        }
    }
}
