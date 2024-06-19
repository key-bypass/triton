package com.liying.core.operation.cut.cutpath.blankCutPath;

import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.KeyAlign;
import com.liying.core.bean.StepBean;
import com.liying.core.error.ErrorCode;
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
public class HY18KeyCutPath extends KeyBlankCutPath {
    private static final float[] twiceCut = {0.4f, 0.0f};
    private static final float[] thriceCut = {0.55f, 0.1f, 0.0f};
    private static final float[] fourCut = {0.7f, 0.4f, 0.1f, 0.0f};

    public HY18KeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        if (isQuickCut()) {
            return 1;
        }
        List<KeyPoint> list = getEndPointsGroup().get(0);
        int ceil = (int) Math.ceil((((getWidth() - 380) - list.get(list.size() - 1).getY()) * 1.0d) / getMaxCut());
        if (ceil < 3) {
            return 3;
        }
        return ceil;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        List<List<KeyPoint>> keyPointsGroup = getKeyPointsGroup();
        List<List<KeyPoint>> startPointsGroup = getStartPointsGroup();
        List<List<KeyPoint>> endPointsGroup = getEndPointsGroup();
        fixSpace(keyPointsGroup);
        for (int i = 0; i < keyPointsGroup.size(); i++) {
            keyPointsGroup.get(i).addAll(0, startPointsGroup.get(i));
            keyPointsGroup.get(i).addAll(endPointsGroup.get(i));
        }
        ArrayList arrayList = new ArrayList<ArrayList>();
        for (int i2 = 0; i2 < cutCount(); i2++) {
            arrayList.add(new ArrayList());
        }
        for (int i3 = 0; i3 < keyPointsGroup.size(); i3++) {
            List<KeyPoint> list = keyPointsGroup.get(i3);
            for (int i4 = 0; i4 < list.size(); i4++) {
                KeyPoint keyPoint = list.get(i4);
                int[] splitCut = splitCut(keyPoint.getY());
                for (int i5 = 0; i5 < cutCount(); i5++) {
                    List<KeyPoint> list = (List<KeyPoint>)arrayList.get(i5);
                    list.add(KeyPointFactory.getKeyPoint(keyPoint.getX(), splitCut[i5]));
                }
            }
        }
        return generateKeyCutSteps(arrayList);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<List<KeyPoint>> getStartPointsGroup() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getKeyPointsGroup().size(); i++) {
            int x = getKeyPointsGroup().get(i).get(0).getX();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(KeyPointFactory.getKeyPoint(x - 200, ErrorCode.keyDecodeFailed));
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

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        int yKey2Machine;
        int tipCutter;
        int xKey2Machine;
        int i;
        List<KeyPoint> list2;
        int i2;
        int i3;
        int cutZ = cutZ();
        int zUp = zUp(cutZ);
        ArrayList arrayList = new ArrayList();
        int leftCutter = getLeftCutter();
        int rightCutter = getRightCutter();
        if (list != null && list.size() != 0) {
            int tipCutter2 = getTipCutter() + UnitConvertUtil.xKey2Machine(ToolSizeManager.getCutterRadiusSize() + 50);
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                List<KeyPoint> list3 = list.get(i4);
                int size2 = list3.size() - 1;
                while (size2 >= 0) {
                    KeyPoint keyPoint = list3.get(size2);
                    int x = keyPoint.getX();
                    int y = keyPoint.getY();
                    if (getKeyId() == 1311) {
                        yKey2Machine = leftCutter - UnitConvertUtil.yKey2Machine(y);
                    } else {
                        yKey2Machine = UnitConvertUtil.yKey2Machine(y) + rightCutter;
                    }
                    int i5 = yKey2Machine;
                    if (getAlign() == KeyAlign.SHOULDERS_ALIGN) {
                        tipCutter = getShoulderCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                    } else {
                        tipCutter = getTipCutter();
                        xKey2Machine = UnitConvertUtil.xKey2Machine(x);
                    }
                    int i6 = tipCutter + xKey2Machine;
                    if (size2 != list3.size() - 1) {
                        i = size2;
                        list2 = list3;
                        i2 = i4;
                        i3 = size;
                    } else if (i4 == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter2, i5, 0, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                        i = size2;
                        list2 = list3;
                        i2 = i4;
                        i3 = size;
                        arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter2, i5, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;"));
                        arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", tipCutter2, i5, cutZ, Speed.get_Speed_SpindleTurnOff_ZDown(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                    } else {
                        i = size2;
                        list2 = list3;
                        i2 = i4;
                        i3 = size;
                        arrayList.add(StepBeanFactory.getCutStepBean("移动至第一个点位旁边", tipCutter2, i5, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                        arrayList.add(StepBeanFactory.getCutStepBean("下Z轴", tipCutter2, i5, cutZ, Speed.get_Speed_SpindleTurnOn_ZDown(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                    }
                    arrayList.add(StepBeanFactory.getCutStepBean("右边第" + (i2 + 1) + "刀,第" + (i + 1) + "点", i6, i5, cutZ, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    if (i == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBean("抬Z轴", i6, i5, zUp, Speed.get_Speed_SpindleTurnOn_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;"));
                    }
                    size2 = i - 1;
                    size = i3;
                    list3 = list2;
                    i4 = i2;
                }
                i4++;
            }
            backOrigin(arrayList);
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0059 A[SYNTHETIC] */
    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
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
            int r2 = r2 + (-380)
            int r3 = r2 - r11
            r4 = 0
            if (r3 >= 0) goto L12
            r3 = 0
        L12:
            float r3 = (float) r3
            r5 = 1065353216(0x3f800000, float:1.0)
            float r5 = r5 * r3
            float r6 = (float) r0
            float r5 = r5 / r6
        L19:
            if (r4 >= r0) goto L62
            r6 = 2
            if (r0 != r6) goto L27
            float[] r6 = com.liying.core.operation.cut.cutpath.blankCutPath.HY18KeyCutPath.twiceCut
            r6 = r6[r4]
        L22:
            float r6 = r6 * r3
        L24:
            int r6 = (int) r6
            int r6 = r6 + r11
            goto L3e
        L27:
            r6 = 3
            if (r0 != r6) goto L2f
            float[] r6 = com.liying.core.operation.cut.cutpath.blankCutPath.HY18KeyCutPath.thriceCut
            r6 = r6[r4]
            goto L22
        L2f:
            r6 = 4
            if (r0 != r6) goto L37
            float[] r6 = com.liying.core.operation.cut.cutpath.blankCutPath.HY18KeyCutPath.fourCut
            r6 = r6[r4]
            goto L22
        L37:
            int r6 = r0 + (-1)
            int r6 = r6 - r4
            float r6 = (float) r6
            float r6 = r6 * r5
            goto L24
        L3e:
            int r7 = r2 - r6
            int r8 = r10.getMaxCut()
            int r9 = r4 + 1
            int r8 = r8 * r9
            if (r7 <= r8) goto L52
            int r6 = r10.getMaxCut()
            int r6 = r6 * r9
            int r6 = r2 - r6
        L52:
            boolean r7 = r10.isQuickCut()
            if (r7 == 0) goto L59
            r6 = r11
        L59:
            int r7 = r10.getCutterRadiusSize()
            int r6 = r6 + r7
            r1[r4] = r6
            r4 = r9
            goto L19
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.HY18KeyCutPath.splitCut(int):int[]");
    }

    private int cutZ() {
        return getKeyFace() + UnitConvertUtil.cmm2StepZ(getCutDepth());
    }

    private int zUp(int i) {
        return i - UnitConvertUtil.cmm2StepZ(300);
    }
}
