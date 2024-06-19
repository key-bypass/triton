package com.liying.core.operation.cut.cutpath.blankCutPath;

import com.liying.core.KeyAlignInfo;
import com.liying.core.ToolSizeManager;
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
public class AY1AngleKeyCutPath extends KeyBlankCutPath {
    private static final String TAG = "AY1AngleKeyCutPath";
    private static final int maxCutDepth = 70;

    public int GetAbusKeyAngle(double d) {
        return 0;
    }

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

    public AY1AngleKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    public int cutCount(int i) {
        return (int) Math.ceil((i * 1.0f) / 70.0f);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<KeyPoint> list = getKeyPointsGroup().get(0);
        int decodeWidth = getDecodeWidth();
        int intValue = (getSpaceWidthList().get(0).get(0).intValue() / 2) - ToolSizeManager.getCutterRadiusSize();
        for (int i = 0; i < list.size(); i++) {
            KeyPoint keyPoint = list.get(i);
            int GetAbusKeyAngle = GetAbusKeyAngle(keyPoint.getAngle());
            int totalCutDepth = getTotalCutDepth(keyPoint.getAngle());
            int cutCount = cutCount(totalCutDepth);
            int i2 = totalCutDepth / cutCount;
            for (int i3 = 0; i3 < cutCount; i3++) {
                int i4 = GetAbusKeyAngle - (((cutCount - i3) - 1) * i2);
                arrayList2.add(KeyPointFactory.getKeyPoint(keyPoint.getX() - intValue, decodeWidth, i4));
                arrayList2.add(KeyPointFactory.getKeyPoint(keyPoint.getX() - intValue, 0, i4));
                arrayList2.add(KeyPointFactory.getKeyPoint(keyPoint.getX() + intValue, 0, i4));
                arrayList2.add(KeyPointFactory.getKeyPoint(keyPoint.getX() + intValue, decodeWidth, i4));
            }
        }
        arrayList.add(arrayList2);
        return generateKeyCutSteps(arrayList);
    }

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        ArrayList arrayList = new ArrayList();
        int keyFace = getKeyFace() - UnitConvertUtil.cmm2StepZ(ErrorCode.keyCuttingError);
        for (int i = 0; i < list.size(); i++) {
            List<KeyPoint> list2 = list.get(i);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                KeyPoint keyPoint = list2.get(i2);
                int keyX2MachineValue = keyX2MachineValue(keyPoint.getX());
                int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(keyPoint.getY());
                int zKey2Machine = UnitConvertUtil.zKey2Machine(keyPoint.getZ()) + getKeyFace();
                if (i2 == 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("移动到起点", keyX2MachineValue, KeyY2MachineValueBaseRight, zKey2Machine, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, KeyY2MachineValueBaseRight, zKey2Machine, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                }
                arrayList.add(StepBeanFactory.getCutStepBean("切割", keyX2MachineValue, KeyY2MachineValueBaseRight, zKey2Machine, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
            }
        }
        arrayList.add(StepBeanFactory.getStepBean("关闭主轴", 0, 0, 0, 0, Speed.get_Speed_Origin(), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(StepBeanFactory.getStepBean("抬Z轴", 0, 0, 0, keyFace, Speed.get_Speed_SpindleTurnOff_ZUp(), "C:5,Z;"));
        return arrayList;
    }

    public int getTotalCutDepth(float f) {
        return (int) ((Math.sin(Math.toRadians(f)) * getWidth()) / 2.0d);
    }
}
