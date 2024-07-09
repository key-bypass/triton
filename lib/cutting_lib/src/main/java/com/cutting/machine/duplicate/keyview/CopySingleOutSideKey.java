package com.cutting.machine.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.duplicate.SinglePathData;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;

import java.util.List;

/* loaded from: classes2.dex */
public class CopySingleOutSideKey extends CopyKey {
    public CopySingleOutSideKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context, duplicateKeyData);
    }

    @Override // com.cutting.machine.duplicate.keyview.CopyKey
    protected void cacularKeySize() {
    }

    @Override // com.cutting.machine.duplicate.keyview.CopyKey
    protected void drawKeyView(Canvas canvas) {
        this.paint.setColor(getKeyColor());
        Rect keyRect = getKeyRect();
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        List<SinglePathData> keyDataList = getKeyDataList();
        for (int i = 0; i < keyDataList.size(); i++) {
            SinglePathData singlePathData = keyDataList.get(i);
            List<DestPoint> destPointList = singlePathData.getDestPointList();
            for (int i2 = 0; i2 < destPointList.size(); i2++) {
                DestPoint destPoint = destPointList.get(i2);
                if (!destPoint.isInvalid()) {
                    if (i2 == 0) {
                        this.path.moveTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                    } else {
                        this.path.lineTo(getPointX(destPoint), getPointY(destPoint, singlePathData.getBenchmark()));
                    }
                }
            }
        }
        this.paint.setColor(this.colorRed);
        canvas.drawPath(this.path, this.paint);
    }
}
