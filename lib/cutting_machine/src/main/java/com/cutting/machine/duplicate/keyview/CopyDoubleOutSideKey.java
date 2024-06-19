package com.liying.core.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.liying.core.bean.DestPoint;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateDecode.DuplicateKeyData;
import java.util.List;

/* loaded from: classes2.dex */
public class CopyDoubleOutSideKey extends CopyKey {
    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void cacularKeySize() {
    }

    public CopyDoubleOutSideKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context, duplicateKeyData);
    }

    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void drawKeyView(Canvas canvas) {
        Rect keyRect = getKeyRect();
        this.paint.setColor(getKeyColor());
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top + (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right, keyRect.bottom - (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setColor(this.colorRed);
        List<SinglePathData> keyDataList = getKeyDataList();
        for (int i = 0; i < keyDataList.size(); i++) {
            SinglePathData singlePathData = keyDataList.get(i);
            List<DestPoint> destPointList = singlePathData.getDestPointList();
            if (singlePathData.getBenchmark() == Benchmark.RIGHT) {
                for (int i2 = 0; i2 < destPointList.size(); i2++) {
                    DestPoint destPoint = destPointList.get(i2);
                    if (!destPoint.isInvalid()) {
                        if (i2 == 0) {
                            this.path.moveTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                        } else {
                            this.path.lineTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                        }
                        if (i2 == destPointList.size() - 1) {
                            this.path.lineTo(getKeyRect().right, getPointY(new DestPoint(getKeyLenght(), destPoint.getDepth()), singlePathData.getBenchmark()));
                        }
                    }
                }
            } else {
                for (int size = destPointList.size() - 1; size >= 0; size--) {
                    DestPoint destPoint2 = destPointList.get(size);
                    if (!destPoint2.isInvalid()) {
                        if (size == destPointList.size() - 1) {
                            this.path.lineTo(getKeyRect().right, getPointY(new DestPoint(getKeyLenght(), destPoint2.getDepth()), singlePathData.getBenchmark()));
                        }
                        this.path.lineTo(getPointX(destPoint2), getPointY(destPoint2, singlePathData.getBenchmark()));
                    }
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
    }
}
