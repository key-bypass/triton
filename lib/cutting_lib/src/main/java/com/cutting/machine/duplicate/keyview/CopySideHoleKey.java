package com.cutting.machine.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.clamp.MachineData;
import com.cutting.machine.operation.duplicateDecode.DuplicateKeyData;

import java.util.List;

/* loaded from: classes2.dex */
public class CopySideHoleKey extends CopyKey {
    public CopySideHoleKey(Context context, DuplicateKeyData duplicateKeyData) {
        super(context, duplicateKeyData);
    }

    @Override // com.cutting.machine.duplicate.keyview.CopyKey
    protected void cacularKeySize() {
    }

    @Override // com.cutting.machine.duplicate.keyview.CopyKey
    protected void drawKeyView(Canvas canvas) {
        int i;
        int i2;
        Rect keyRect = getKeyRect();
        this.paint.setColor(getKeyColor());
        this.paint.setStrokeCap(Paint.Cap.SQUARE);
        this.paint.setStrokeWidth(2.0f);
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top + (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right, keyRect.bottom - (keyRect.height() * 0.3f));
        this.path.lineTo(keyRect.right - (keyRect.height() * 0.2f), keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        List<DestPoint> destPointList = getKeyDataList().get(0).getDestPointList();
        float f = 8.0f / MachineData.dZScale;
        int size = destPointList.size() - 1;
        int size2 = destPointList.size() - 1;
        while (true) {
            if (size2 < 0) {
                break;
            }
            if (destPointList.get(size2).getDepth() < f) {
                size = size2;
                break;
            }
            size2--;
        }
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(keyRect.height() * 0.3f);
        int i3 = 0;
        boolean z = true;
        int i4 = 0;
        while (i3 <= size) {
            DestPoint destPoint = destPointList.get(i3);
            if (destPoint.getDepth() > f) {
                if (z) {
                    i = size;
                    i2 = i3;
                    this.path.moveTo(getPointX(destPoint), (float) (keyRect.bottom - (keyRect.height() * 0.5d)));
                    z = false;
                } else {
                    i = size;
                    i2 = i3;
                }
                i4 = i2;
            } else {
                i = size;
                i2 = i3;
            }
            if (!z && destPoint.getDepth() <= f) {
                this.path.lineTo(getPointX(destPointList.get(i4)), (float) (keyRect.bottom - (keyRect.height() * 0.5d)));
                z = true;
            }
            i3 = i2 + 1;
            size = i;
        }
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
    }
}
