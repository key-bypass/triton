package com.cutting.machine.operation.duplicateCut.cutpath;

import com.cutting.machine.bean.DestPoint;

/* loaded from: classes2.dex */
public class DestPointFactory {
    public static DestPoint getDestPoint(int i, int i2) {
        return new DestPoint(i, i2, false);
    }

    public static DestPoint getGapDestPoint(int i, int i2) {
        DestPoint destPoint = new DestPoint(i, i2, false);
        destPoint.setGapPoint(true);
        return destPoint;
    }

    public static DestPoint getDoNotSplitDestPoint(int i, int i2) {
        DestPoint destPoint = new DestPoint(i, i2, false);
        destPoint.setDoNotSplitDepth(true);
        return destPoint;
    }

    public static DestPoint getWudihuMoreCutDestPoint(int i, int i2) {
        DestPoint destPoint = new DestPoint(i, i2, false);
        destPoint.setGapPointMoreCut(true);
        return destPoint;
    }
}
