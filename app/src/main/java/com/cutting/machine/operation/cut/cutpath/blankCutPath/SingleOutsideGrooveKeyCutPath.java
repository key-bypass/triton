package com.cutting.machine.operation.cut.cutpath.blankCutPath;

import android.text.TextUtils;
import android.util.Log;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.ToolSizeManager;
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
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class SingleOutsideGrooveKeyCutPath extends KeyBlankCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.55f, 0.1f, 0.0f};
    private static final float[] fourCut = {0.7f, 0.4f, 0.1f, 0.0f};

    public SingleOutsideGrooveKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 1;
        }
        List<KeyPoint> list = getEndPointsGroup().get(0);
        int ceil = (int) Math.ceil(((getWidth() - list.get(list.size() - 1).getY()) * 1.0d) / getMaxCut());
        if (ceil < 3) {
            return 3;
        }
        return ceil;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        List<List<KeyPoint>> keyPointsGroup = getKeyPointsGroup();
        List<List<KeyPoint>> startPointsGroup = getStartPointsGroup();
        List<List<KeyPoint>> endPointsGroup = getEndPointsGroup();
        fixSpace(keyPointsGroup);
        for (int i = 0; i < keyPointsGroup.size(); i++) {
            keyPointsGroup.get(i).addAll(0, startPointsGroup.get(i));
            keyPointsGroup.get(i).addAll(endPointsGroup.get(i));
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < cutCount(); i2++) {
            arrayList.add(new ArrayList());
        }
        for (int i3 = 0; i3 < keyPointsGroup.size(); i3++) {
            List<KeyPoint> list = keyPointsGroup.get(i3);
            for (int i4 = 0; i4 < list.size(); i4++) {
                KeyPoint keyPoint = list.get(i4);
                int[] splitCut = splitCut(keyPoint.getY());
                for (int i5 = 0; i5 < cutCount(); i5++) {
                    List<KeyPoint> r5 = (List<KeyPoint>) arrayList.get(i5);
                    r5.add(KeyPointFactory.getKeyPoint(keyPoint.getX(), splitCut[i5]));
                }
            }
        }
        return generateKeyCutSteps(arrayList);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            ArrayList arrayList2 = new ArrayList();
            String lstPosition = getLstPosition();
            if (!TextUtils.isEmpty(lstPosition) && getAlign() == KeyAlign.FRONTEND_ALIGN) {
                String[] split = lstPosition.split(",");
                try {
                    arrayList2.add(KeyPointFactory.getKeyPoint((ToolSizeManager.getCutterRadiusSize() - Integer.parseInt(split[0])) - getCutSetting().getHu101ExtCutFix(), Integer.parseInt(split[1])));
                } catch (Exception e) {
                    Log.i("TAG", "Exception: " + e);
                }
            } else if (getKeyId() == 1311 || getKeyId() == 1372) {
                arrayList2.add(KeyPointFactory.getKeyPoint((x - 200) - getCutSetting().getHu101ExtCutFix(), getWidth() - 380));
            } else if (getKeyId() == 998) {
                Iterator<Integer> it = getKeyData().getKeyInfo().getDepthList().get(0).iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    i2 = Math.max(i2, it.next().intValue());
                }
                arrayList2.add(KeyPointFactory.getKeyPoint((ToolSizeManager.getCutterRadiusSize() - 2800) - getCutSetting().getHu101ExtCutFix(), i2));
            } else if (getKeyId() == 1479) {
                arrayList2.add(KeyPointFactory.getKeyPoint((ToolSizeManager.getCutterRadiusSize() - 2420) - getCutSetting().getHu101ExtCutFix(), 300));
            } else if (getKeyId() == 810) {
                Iterator<Integer> it2 = getKeyData().getKeyInfo().getDepthList().get(0).iterator();
                int i3 = 0;
                while (it2.hasNext()) {
                    i3 = Math.max(i3, it2.next().intValue());
                }
                arrayList2.add(KeyPointFactory.getKeyPoint((ToolSizeManager.getCutterRadiusSize() - 2600) - getCutSetting().getHu101ExtCutFix(), i3));
            } else {
                String exCut = getExCut();
                if (!TextUtils.isEmpty(exCut) && getKeyId() == 1347) {
                    String[] split2 = exCut.split(",");
                    int parseInt = Integer.parseInt(split2[1]);
                    int i4 = x - 250;
                    int parseInt2 = x - Integer.parseInt(split2[2]);
                    if (i4 > parseInt2) {
                        i4 = parseInt2;
                    }
                    arrayList2.add(KeyPointFactory.getKeyPoint(parseInt2, getWidth()));
                    arrayList2.add(KeyPointFactory.getKeyPoint(parseInt2, parseInt));
                    arrayList2.add(KeyPointFactory.getKeyPoint(i4, parseInt));
                } else {
                    int i5 = getSide() == 0 ? 250 : 200;
                    if (getAlign() == KeyAlign.SHOULDERS_ALIGN && x - getCutterRadiusSize() < 260) {
                        i5 = (x - getCutterRadiusSize()) - 10;
                    }
                    arrayList2.add(KeyPointFactory.getKeyPoint((x - i5) - getCutSetting().getHu101ExtCutFix(), getWidth()));
                }
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getEndPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            List<KeyPoint> r3 = getKeyPointsGroup().get(i);
            KeyPoint keyPoint = r3.get(r3.size() - 1);
            ArrayList arrayList2 = new ArrayList();
            int nose = getNose();
            int guide = getGuide();
            int shoulder2TipDistance = getAlign() == KeyAlign.SHOULDERS_ALIGN ? getShoulder2TipDistance() : 0;
            if (nose != 0) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance - nose, 0)));
            } else if (guide != 0) {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, guide)));
            } else {
                arrayList2.add(calculateEndPoint(keyPoint, KeyPointFactory.getKeyPoint(shoulder2TipDistance, 0)));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        if (getSide() == 0) {
            return sideBCutSteps(list);
        }
        return sideACutSteps(list);
    }

    private List<StepBean> sideBCutSteps(List<List<KeyPoint>> list) {
        int tipCutter;
        int xKey2Machine;
        int i;
        List<KeyPoint> list2;
        int i2;
        int i3;
        List<List<KeyPoint>> list3 = list;
        int cutZ = cutZ();
        int zUp = zUp(cutZ);
        ArrayList arrayList = new ArrayList();
        int leftCutter = getLeftCutter();
        int rightCutter = getRightCutter();
        if (list3 != null && list.size() != 0) {
            int yKey2Machine = rightCutter - UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
            int tipCutter2 = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                List<KeyPoint> list4 = list3.get(i4);
                int size2 = list4.size() - 1;
                while (size2 >= 0) {
                    KeyPoint keyPoint = list4.get(size2);
                    int x = keyPoint.getX();
                    int yKey2Machine2 = leftCutter - UnitConvertUtil.yKey2Machine(keyPoint.getY());
                    if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                        tipCutter = getShoulderCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                    } else {
                        tipCutter = getTipCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                    }
                    int i5 = tipCutter + xKey2Machine;
                    if (size2 != list4.size() - 1) {
                        i = size2;
                        list2 = list4;
                        i2 = i4;
                        i3 = size;
                    } else if (i4 == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter2, yKey2Machine2, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                        i = size2;
                        list2 = list4;
                        i2 = i4;
                        i3 = size;
                        arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter2, yKey2Machine2, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                        arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter2, yKey2Machine2, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                    } else {
                        i = size2;
                        list2 = list4;
                        i2 = i4;
                        i3 = size;
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter2, yKey2Machine2, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i2 + 1) + "刀,第" + (i + 1) + "点", i5, yKey2Machine2, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    if (i == 0) {
                        if (i2 < i3 - 1) {
                            arrayList.add(StepBeanFactory.getCutStepBean("离开右边", i5, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("移动到前端", tipCutter2, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        } else {
                            arrayList.add(StepBeanFactory.getCutStepBean("离开右边", i5, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                            arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", i5, yKey2Machine, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                        }
                    }
                    size2 = i - 1;
                    i4 = i2;
                    size = i3;
                    list4 = list2;
                }
                i4++;
                list3 = list;
            }
            backOrigin(arrayList);
        }
        return arrayList;
    }

    private List<StepBean> sideACutSteps(List<List<KeyPoint>> list) {
        int tipCutter;
        int xKey2Machine;
        int i;
        List<KeyPoint> list2;
        int i2;
        int i3;
        int cutZ = cutZ();
        zUp(cutZ);
        ArrayList arrayList = new ArrayList();
        int leftCutter = getLeftCutter();
        int rightCutter = getRightCutter();
        if (list == null || list.size() == 0) {
            return null;
        }
        int yKey2Machine = leftCutter + UnitConvertUtil.yKey2Machine(getCutterRadiusSize() + 200);
        int tipCutter2 = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
        int size = list.size();
        int i4 = 0;
        while (i4 < size) {
            List<KeyPoint> list3 = list.get(i4);
            int i5 = 0;
            while (i5 < list3.size()) {
                KeyPoint keyPoint = list3.get(i5);
                int x = keyPoint.getX();
                int yKey2Machine2 = rightCutter + UnitConvertUtil.yKey2Machine(keyPoint.getY());
                if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                    tipCutter = getShoulderCutter();
                    xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                } else {
                    tipCutter = getTipCutter();
                    xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                }
                int i6 = tipCutter + xKey2Machine;
                if (i5 != 0) {
                    i = i5;
                    list2 = list3;
                    i2 = i4;
                    i3 = size;
                } else if (i4 == 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i6, yKey2Machine, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    i = i5;
                    list2 = list3;
                    i2 = i4;
                    i3 = size;
                    arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", i6, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", i6, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                } else {
                    i = i5;
                    list2 = list3;
                    i2 = i4;
                    i3 = size;
                    arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", i6, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                }
                int i7 = i + 1;
                String sb = "左边第" +
                        (i2 + 1) +
                        "刀,第" +
                        i7 +
                        "点";
                arrayList.add(StepBeanFactory.getCutStepBean(sb, i6, yKey2Machine2, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                if (i == list2.size() - 1) {
                    arrayList.add(StepBeanFactory.getCutStepBean("离开前端", tipCutter2, yKey2Machine2, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                    arrayList.add(StepBeanFactory.getCutStepBean("离开左边", tipCutter2, yKey2Machine, cutZ, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                }
                size = i3;
                i5 = i7;
                list3 = list2;
                i4 = i2;
            }
            i4++;
        }
        backOrigin(arrayList);
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
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.SingleOutsideGrooveKeyCutPath.twiceCut
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
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.SingleOutsideGrooveKeyCutPath.thriceCut
            r5 = r5[r3]
            goto L1f
        L2c:
            r5 = 4
            if (r0 != r5) goto L34
            float[] r5 = com.cutting.machine.operation.cut.cutpath.blankCutPath.SingleOutsideGrooveKeyCutPath.fourCut
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
        throw new UnsupportedOperationException("Method not decompiled: com.cutting.machine.operation.cut.cutpath.blankCutPath.SingleOutsideGrooveKeyCutPath.splitCut(int):int[]");
    }

    private int cutZ() {
        return getKeyFace() + UnitConvertUtil.cmm2StepZ(getCutDepth());
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
    }
}
