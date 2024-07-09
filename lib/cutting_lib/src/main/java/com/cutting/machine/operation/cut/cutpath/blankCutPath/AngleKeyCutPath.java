package com.cutting.machine.operation.cut.cutpath.blankCutPath;

import android.text.TextUtils;

import com.cutting.machine.KeyAlignInfo;
import com.cutting.machine.ToolSizeManager;
import com.cutting.machine.bean.KeyAlign;
import com.cutting.machine.bean.StepBean;
import com.cutting.machine.error.ErrorCode;
import com.cutting.machine.operation.cut.DataParam;
import com.cutting.machine.operation.cut.cutpath.KeyPoint;
import com.cutting.machine.operation.cut.cutpath.KeyPointFactory;
import com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import com.cutting.machine.speed.Speed;
import com.cutting.machine.utils.StepBeanFactory;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class AngleKeyCutPath extends KeyBlankCutPath {
    public AngleKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public int cutCount() {
        return 0;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getEndPointsGroup() {
        return null;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected List<List<KeyPoint>> getStartPointsGroup() {
        return null;
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    protected int[] splitCut(int i) {
        return new int[0];
    }

    @Override // com.cutting.machine.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        String angleKeySpace = getCutSetting().getAngleKeySpace();
        int intValue = (getSpaceWidthList().get(0).get(0).intValue() / 2) - ToolSizeManager.getCutterRadiusSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (TextUtils.isEmpty(angleKeySpace)) {
            return null;
        }
        int decodeWidth = getDecodeWidth();
        for (String str : angleKeySpace.split(",")) {
            int parseInt = Integer.parseInt(str);
            if (getAlign() == KeyAlign.FRONTEND_ALIGN) {
                parseInt = -parseInt;
            }
            int i = parseInt - intValue;
            arrayList2.add(KeyPointFactory.getKeyPoint(i, decodeWidth));
            arrayList2.add(KeyPointFactory.getKeyPoint(i, 0));
            int i2 = parseInt + intValue;
            arrayList2.add(KeyPointFactory.getKeyPoint(i2, 0));
            arrayList2.add(KeyPointFactory.getKeyPoint(i2, decodeWidth));
        }
        arrayList.add(arrayList2);
        return generateKeyCutSteps(arrayList);
    }

    private List<StepBean> generateKeyCutSteps(List<List<KeyPoint>> list) {
        ArrayList arrayList = new ArrayList();
        int keyFace = getKeyFace();
        int keyFace2 = getKeyFace() - UnitConvertUtil.cmm2StepZ(ErrorCode.keyCuttingError);
        for (int i = 0; i < list.size(); i++) {
            List<KeyPoint> list2 = list.get(i);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                KeyPoint keyPoint = list2.get(i2);
                int keyX2MachineValue = keyX2MachineValue(keyPoint.getX());
                int KeyY2MachineValueBaseRight = KeyY2MachineValueBaseRight(keyPoint.getY());
                if (i2 == 0) {
                    arrayList.add(StepBeanFactory.getCutStepBean("移动到起点", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;"));
                    arrayList.add(StepBeanFactory.getCutStepBean("启动主轴", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_SpindleTurnOff_Move(), "C:5,X;C:5,Y;C:5,Z;SUP:1,8000"));
                }
                arrayList.add(StepBeanFactory.getCutStepBean("切割", keyX2MachineValue, KeyY2MachineValueBaseRight, keyFace, Speed.get_Speed_CuttingIn(), "C:5,X;C:5,Y;C:5,Z;CUTSM"));
            }
        }
        arrayList.add(StepBeanFactory.getStepBean("关闭主轴", 0, 0, 0, 0, Speed.get_Speed_Origin(), "U:X;U:Y;U:Z;SUP:0,0"));
        arrayList.add(StepBeanFactory.getStepBean("抬Z轴", 0, 0, 0, keyFace2, Speed.get_Speed_SpindleTurnOff_ZUp(), "C:5,Z;"));
        return arrayList;
    }
}
