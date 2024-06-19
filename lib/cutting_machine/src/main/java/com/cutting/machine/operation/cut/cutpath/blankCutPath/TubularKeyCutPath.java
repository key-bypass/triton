package com.liying.core.operation.cut.cutpath.blankCutPath;

import android.graphics.PointF;
import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
import com.liying.core.bean.StepBean;
import com.liying.core.clamp.ClampManager;
import com.liying.core.clamp.DecoderCutterDistance;
import com.liying.core.clamp.MachineData;
import com.liying.core.error.ErrorCode;
import com.liying.core.error.ErrorHelper;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.KeyPoint;
import com.liying.core.operation.cut.cutpath.KeyPointFactory;
import com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import com.liying.core.speed.Speed;
import com.liying.core.utils.CircleUtils;
import com.liying.core.utils.StepBeanFactory;
import com.liying.core.utils.UnitConvertUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class TubularKeyCutPath extends KeyBlankCutPath {
    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        return 0;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getEndPointsGroup() {
        return null;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getStartPointsGroup() {
        return null;
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected int[] splitCut(int i) {
        return new int[0];
    }

    public TubularKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        int r4;
        int thick = getThick();
        if (thick == 0) {
            thick = 100;
        }
        int remainingDepth = getKeyData().getRemainingDepth();
        if (remainingDepth == 0) {
            remainingDepth = 20;
        }
        int i = thick - remainingDepth;
        KeyAlignInfo keyAlignInfo = getKeyAlignInfo();
        int step2CmmX = UnitConvertUtil.step2CmmX((int) CircleUtils.getRadiusOfCircle(keyAlignInfo.getTlX(), keyAlignInfo.getTlY(), keyAlignInfo.getTrX(), keyAlignInfo.getTrY(), keyAlignInfo.getTdX(), keyAlignInfo.getTdY())) + ToolSizeManager.getDecoderRadius() + thick;
        int cutterRadiusSize = ToolSizeManager.getCutterRadiusSize() + step2CmmX + 50;
        double d = (step2CmmX + 300) - i;
        double acos = Math.acos(((Math.pow(d, 2.0d) + Math.pow(300, 2.0d)) - Math.pow(step2CmmX, 2.0d)) / ((r4 * 2) * 300));
        ArrayList arrayList = new ArrayList();
        List<List<KeyPoint>> keyPointsGroup = getKeyPointsGroup();
        int i2 = 0;
        while (i2 < keyPointsGroup.size()) {
            List<KeyPoint> list = keyPointsGroup.get(i2);
            int i3 = 0;
            while (i3 < list.size()) {
                KeyPoint keyPoint = list.get(i3);
                double radians = Math.toRadians(keyPoint.getX());
                double radians2 = Math.toRadians(180.0d) + radians;
                double d2 = radians2 + acos;
                double d3 = radians2 - acos;
                int i4 = i2;
                int cos = (int) (d * Math.cos(radians));
                ArrayList arrayList2 = arrayList;
                int sin = (int) (d * Math.sin(radians));
                int z = keyPoint.getZ();
                List<KeyPoint> list2 = list;
                int ceil = (int) Math.ceil(z / 120.0d);
                if (isQuickCut()) {
                    ceil = 1;
                }
                ArrayList arrayList3 = new ArrayList();
                double d4 = acos;
                int i5 = 0;
                while (i5 < ceil) {
                    int i6 = i5 + 1;
                    int i7 = (z / ceil) * i6;
                    int i8 = z;
                    List<KeyPoint> arc = getArc(cos, sin, 300 - ToolSizeManager.getCutterRadiusSize(), d2, d3, 16, i7);
                    double d5 = cutterRadiusSize;
                    KeyPoint keyPoint2 = KeyPointFactory.getKeyPoint((int) (d5 * Math.cos(radians)), (int) (d5 * Math.sin(radians)), i7);
                    KeyPoint keyPoint3 = KeyPointFactory.getKeyPoint((int) (Math.cos(radians) * d5), (int) (d5 * Math.sin(radians)), i7);
                    arrayList3.add(keyPoint2);
                    arrayList3.addAll(arc);
                    arrayList3.add(keyPoint3);
                    d = d;
                    keyPointsGroup = keyPointsGroup;
                    cos = cos;
                    i5 = i6;
                    z = i8;
                }
                arrayList2.add(arrayList3);
                i3++;
                arrayList = arrayList2;
                list = list2;
                i2 = i4;
                acos = d4;
            }
            i2++;
            acos = acos;
        }
        return generateKeyCutSteps(arrayList);
    }

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        int i;
        int i2;
        int i3;
        ArrayList arrayList = new ArrayList();
        int keyFace = getKeyFace();
        int i4 = (int) (keyFace - (100.0f / MachineData.dZScale));
        int clampFace = getClampFace() - UnitConvertUtil.cmm2StepZ(10);
        KeyAlignInfo keyAlignInfo = getKeyAlignInfo();
        PointF centerPointOfCircle = CircleUtils.getCenterPointOfCircle(keyAlignInfo.getTlX(), keyAlignInfo.getTlY(), keyAlignInfo.getTrX(), keyAlignInfo.getTrY(), keyAlignInfo.getTdX(), keyAlignInfo.getTdY());
        DecoderCutterDistance dc = ClampManager.getInstance().getDC();
        int i5 = ((int) centerPointOfCircle.x) + dc.getxDistance();
        int i6 = ((int) centerPointOfCircle.y) + dc.getyDistance();
        for (int i7 = 0; i7 < list.size(); i7++) {
            List<KeyPoint> list2 = list.get(i7);
            int i8 = 0;
            while (i8 < list2.size()) {
                KeyPoint keyPoint = list2.get(i8);
                int xKey2Machine = i5 + UnitConvertUtil.xKey2Machine(keyPoint.getX());
                int yKey2Machine = i6 + UnitConvertUtil.yKey2Machine(keyPoint.getY());
                int cmm2StepZ = UnitConvertUtil.cmm2StepZ(keyPoint.getZ()) + keyFace;
                if (cmm2StepZ > clampFace) {
                    ErrorHelper.handleError(ErrorCode.RiskCutClampFaceS3);
                    return null;
                }
                if (i8 == 0) {
                    i = cmm2StepZ;
                    i2 = i8;
                    arrayList.add(StepBeanFactory.getCutStepBeanNoChangeDirect("移动到第" + (i7 + 1) + "齿", xKey2Machine, yKey2Machine, i4, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z"));
                    if (i7 == 0) {
                        arrayList.add(StepBeanFactory.getCutStepBeanNoChangeDirect("启动主轴", xKey2Machine, yKey2Machine, i4, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                    }
                } else {
                    i = cmm2StepZ;
                    i2 = i8;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("第");
                sb.append(i7 + 1);
                sb.append("齿，第");
                int i9 = i2 + 1;
                sb.append(i9);
                sb.append("点");
                arrayList.add(StepBeanFactory.getCutStepBeanNoChangeDirect(sb.toString(), xKey2Machine, yKey2Machine, i, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
                if (i2 == list2.size() - 1) {
                    i3 = i9;
                    arrayList.add(StepBeanFactory.getCutStepBeanNoChangeDirect("抬Z轴", xKey2Machine, yKey2Machine, i4, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;CUTSM;BREAK"));
                } else {
                    i3 = i9;
                }
                i8 = i3;
            }
        }
        backOrigin(arrayList);
        return arrayList;
    }

    public List<KeyPoint> getArc(int i, int i2, int i3, double d, double d2, int i4, int i5) {
        ArrayList arrayList = new ArrayList();
        double d3 = (d - d2) / (i4 - 1);
        for (int i6 = 0; i6 < i4; i6++) {
            double d4 = i3;
            double d5 = d - (i6 * d3);
            arrayList.add(KeyPointFactory.getKeyPoint((int) (i + (Math.cos(d5) * d4)), (int) (i2 + (d4 * Math.sin(d5))), i5));
        }
        return arrayList;
    }
}
