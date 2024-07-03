package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.cutting.machine.bean.DestPoint;
import com.cutting.machine.clamp.MachineData;
import java.util.List;

/* loaded from: classes.dex */
public class CopySideHoleKey extends CopyKey {
    public CopySideHoleKey(Context context, List<DestPoint> list, int i, int i2) {
        super(context, list, null, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyLenght(getMaxX());
        setKeyWidth(getMaxY());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        List<DestPoint> list;
        int i;
        Rect keyRect = getKeyRect();
        this.paint.setColor(-1);
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
        List<DestPoint> pointsA = getPointsA();
        float f = 8.0f / MachineData.dZScale;
        int size = pointsA.size() - 1;
        int size2 = pointsA.size() - 1;
        while (true) {
            if (size2 < 0) {
                break;
            }
            if (pointsA.get(size2).getDepth() < f) {
                size = size2;
                break;
            }
            size2--;
        }
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(keyRect.height() * 0.3f);
        int i2 = 0;
        boolean z = true;
        int i3 = 0;
        while (i2 <= size) {
            DestPoint destPoint = pointsA.get(i2);
            if (destPoint.getDepth() > f) {
                if (z) {
                    list = pointsA;
                    i = size;
                    this.path.moveTo(getPointX(i2, CopyKey.Side.A), (float) (keyRect.bottom - (keyRect.height() * 0.5d)));
                    z = false;
                } else {
                    list = pointsA;
                    i = size;
                }
                i3 = i2;
            } else {
                list = pointsA;
                i = size;
            }
            if (!z && destPoint.getDepth() <= f) {
                this.path.lineTo(getPointX(i3, CopyKey.Side.A), (float) (keyRect.bottom - (keyRect.height() * 0.5d)));
                z = true;
            }
            i2++;
            pointsA = list;
            size = i;
        }
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
    }
}
