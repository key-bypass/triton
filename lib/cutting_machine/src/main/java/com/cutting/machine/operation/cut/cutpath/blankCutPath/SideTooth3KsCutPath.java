package com.liying.core.operation.cut.cutpath.blankCutPath;

import com.liying.core.KeyAlignInfo;
import com.liying.core.bean.StepBean;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.KeyPoint;
import com.liying.core.operation.cut.cutpath.KeyPointFactory;
import com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class SideTooth3KsCutPath extends KeyBlankCutPath {
    private static final int StopSpace = 100;

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

    public SideTooth3KsCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    public List<StepBean> getCutPathSteps() {
        int i;
        ArrayList arrayList = new ArrayList();
        getKeyData().getKeyInfo().getKeyangle().split(",");
        for (List<KeyPoint> list : getKeyPointsGroup()) {
            int i2 = 0;
            int i3 = 0;
            while (i3 < list.size()) {
                KeyPoint r6 = list.get(i3);
                int thick = getThick() - list.get(i3).getZ();
                if (thick <= 20) {
                    i = i3;
                } else {
                    double radians = Math.toRadians(60.0d);
                    int halfSpaceWidth = (int) ((getHalfSpaceWidth(i2, i3) * Math.sin(radians)) - 40.0d);
                    if (halfSpaceWidth < 0) {
                        halfSpaceWidth = 0;
                    }
                    int width = getWidth() + thick + 100;
                    arrayList.add(KeyPointFactory.getKeyPoint((int) ((r6.getX() - halfSpaceWidth) - ((width - (getWidth() / 2)) / Math.tan(radians))), width, thick));
                    int i4 = (-thick) - 100;
                    i = i3;
                    arrayList.add(KeyPointFactory.getKeyPoint((int) ((r6.getX() - halfSpaceWidth) - ((i4 - (getWidth() / 2)) / Math.tan(radians))), i4, thick));
                    arrayList.add(KeyPointFactory.getKeyPoint((int) ((r6.getX() + halfSpaceWidth) - ((i4 - (getWidth() / 2)) / Math.tan(radians))), i4, thick));
                    arrayList.add(KeyPointFactory.getKeyPoint((int) ((r6.getX() + halfSpaceWidth) - ((width - (getWidth() / 2)) / Math.tan(radians))), width, thick));
                }
                i3 = i + 1;
                i2 = 0;
            }
        }
        return generateKeyCutSteps(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.liying.core.bean.StepBean> generateKeyCutSteps(java.util.List<com.liying.core.operation.cut.cutpath.KeyPoint> r13) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.SideTooth3KsCutPath.generateKeyCutSteps(java.util.List):java.util.List");
    }
}
