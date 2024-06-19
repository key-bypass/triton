package com.liying.core.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import com.liying.core.bean.DestPoint;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateDecode.DuplicateKeyData;
import java.util.List;

/* loaded from: classes2.dex */
public class CopySingleInsideKey extends CopyKey {
    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void cacularKeySize() {
    }

    public CopySingleInsideKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context, duplicateKeyData);
    }

    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void drawKeyView(Canvas canvas) {
        this.paint.setColor(getKeyColor());
        Rect keyRect = getKeyRect();
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setColor(this.colorRed);
        List<SinglePathData> keyDataList = getKeyDataList();
        for (int i = 0; i < keyDataList.size(); i++) {
            SinglePathData singlePathData = keyDataList.get(i);
            List<DestPoint> destPointList = singlePathData.getDestPointList();
            if (singlePathData.getBenchmark() == Benchmark.LEFT) {
                for (int size = destPointList.size() - 1; size >= 0; size--) {
                    DestPoint destPoint = destPointList.get(size);
                    if (!destPoint.isInvalid()) {
                        if (size == destPointList.size() - 1) {
                            this.path.moveTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                        } else {
                            this.path.lineTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                        }
                    }
                }
            } else {
                List<DestPoint> destPointList2 = keyDataList.get(i - 1).getDestPointList();
                List<DestPoint> destPointList3 = keyDataList.get(i).getDestPointList();
                DestPoint destPoint2 = destPointList2.get(0);
                DestPoint destPoint3 = destPointList3.get(0);
                float pointX = getPointX(destPoint2);
                float pointY = getPointY(destPoint2, Benchmark.LEFT);
                float pointY2 = getPointY(destPoint3, Benchmark.RIGHT);
                float f = (pointY2 + pointY) / 2.0f;
                float f2 = (pointY2 - pointY) / 2.0f;
                this.path.addArc(new RectF(pointX - f2, f - f2, pointX + f2, f + f2), -90.0f, -180.0f);
                for (int i2 = 0; i2 < destPointList.size(); i2++) {
                    DestPoint destPoint4 = destPointList.get(i2);
                    if (!destPoint4.isInvalid()) {
                        this.path.lineTo(getPointX(destPoint4), getPointY(destPoint4, singlePathData.getBenchmark()));
                    }
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
    }
}
