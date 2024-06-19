package com.liying.core.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import com.liying.core.bean.DestPoint;
import com.liying.core.duplicate.Benchmark;
import com.liying.core.duplicate.SinglePathData;
import com.liying.core.operation.duplicateDecode.DuplicateKeyData;
import java.util.List;

/* loaded from: classes2.dex */
public class CopyDoubleSideKey extends CopyKey {
    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void cacularKeySize() {
    }

    public CopyDoubleSideKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context, duplicateKeyData);
    }

    @Override // com.liying.core.duplicate.keyview.CopyKey
    protected void drawKeyView(Canvas canvas) {
        this.path.reset();
        List<SinglePathData> keyDataList = getKeyDataList();
        for (int i = 0; i < keyDataList.size(); i++) {
            SinglePathData singlePathData = keyDataList.get(i);
            List<DestPoint> destPointList = singlePathData.getDestPointList();
            if (singlePathData.getBenchmark() == Benchmark.RIGHT) {
                for (int i2 = 0; i2 < destPointList.size(); i2++) {
                    DestPoint destPoint = destPointList.get(i2);
                    if (!destPoint.isInvalid()) {
                        if (i2 == 0) {
                            this.path.moveTo(getPointX(destPoint), getPointYBaseCenter(destPoint, singlePathData.getBenchmark()));
                        } else {
                            this.path.lineTo(getPointX(destPoint), getPointYBaseCenter(destPoint, singlePathData.getBenchmark()));
                        }
                    }
                }
            } else {
                List<DestPoint> destPointList2 = keyDataList.get(0).getDestPointList();
                if (destPointList2.size() != 0 && destPointList.size() != 0) {
                    this.path.lineTo(getKeyRect().right + (Math.abs(getPointYBaseCenter(destPointList2.get(destPointList2.size() - 1), Benchmark.RIGHT) - getPointYBaseCenter(destPointList.get(destPointList.size() - 1), Benchmark.LEFT)) / 2.0f), getKeyRect().centerY());
                    for (int size = destPointList.size() - 1; size >= 0; size--) {
                        DestPoint destPoint2 = destPointList.get(size);
                        if (!destPoint2.isInvalid()) {
                            this.path.lineTo(getPointX(destPoint2), getPointYBaseCenter(destPoint2, singlePathData.getBenchmark()));
                        }
                    }
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
    }
}
