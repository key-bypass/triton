package com.liying.core.operation.cut.cutpath.blankCutPath;

import com.liying.core.KeyAlignInfo;
import com.liying.core.operation.cut.DataParam;
import com.liying.core.operation.cut.cutpath.KeyPoint;
import com.liying.core.operation.cut.cutpath.KeyPointFactory;
import com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DimpleKeyCutPath extends KeyBlankCutPath {
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

    public DimpleKeyCutPath(KeyAlignInfo keyAlignInfo, DataParam dataParam) {
        super(keyAlignInfo, dataParam);
    }

    public int getKeyThick() {
        int thick = getThick();
        return thick == 0 ? getMaxStandardDepth() + 50 : thick;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x017a  */
    @Override // com.liying.core.operation.cut.cutpath.blankCutPath.base.KeyBlankCutPath
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.liying.core.bean.StepBean> getCutPathSteps() {
        /*
            Method dump skipped, instructions count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.DimpleKeyCutPath.getCutPathSteps():java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.liying.core.bean.StepBean> generateKeyCutSteps(java.util.List<java.util.List<com.liying.core.operation.cut.cutpath.KeyPoint>> r26) {
        /*
            Method dump skipped, instructions count: 509
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liying.core.operation.cut.cutpath.blankCutPath.DimpleKeyCutPath.generateKeyCutSteps(java.util.List):java.util.List");
    }

    public List<KeyPoint> getCircle(int i, int i2, int i3, int i4, int i5) {
        ArrayList arrayList = new ArrayList();
        double d = (-6.283185307179586d) / (i5 - 1);
        for (int i6 = 0; i6 < i5; i6++) {
            double d2 = i4;
            double d3 = (-1.5707963267948966d) + (i6 * d);
            arrayList.add(KeyPointFactory.getKeyPoint((int) (i + (Math.sin(d3) * d2)), (int) ((d2 * Math.cos(d3)) + i2), i3));
        }
        return arrayList;
    }
}
