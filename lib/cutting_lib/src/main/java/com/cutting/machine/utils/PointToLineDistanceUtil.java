package com.cutting.machine.utils;

import com.cutting.machine.bean.DestPoint;

/* loaded from: classes2.dex */
public class PointToLineDistanceUtil {
    public static double pointToLineDist(DestPoint destPoint, DestPoint destPoint2, DestPoint destPoint3) {
        double space = destPoint.getSpace();
        double depth = destPoint.getDepth();
        double space2 = destPoint2.getSpace();
        double depth2 = destPoint2.getDepth();
        double depth3 = destPoint3.getDepth() - depth2;
        double space3 = destPoint3.getSpace() - space2;
        return Math.abs((((space * depth3) - (depth * space3)) - (space2 * depth3)) + (depth2 * space3)) / Math.sqrt(Math.pow(depth3, 2.0d) + Math.pow(space3, 2.0d));
    }
}
