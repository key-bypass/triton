package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.cutting.machine.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopySingleInsideKey extends CopyKey {
    public CopySingleInsideKey(Context context, List<DestPoint> list, List<DestPoint> list2, int i, int i2) {
        super(context, list, list2, i, i2);
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void cacularKeySize() {
        setKeyLenght(getMaxX());
        setKeyWidth(getMaxY());
    }

    @Override // com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey
    protected void drawKeyView(Canvas canvas) {
        this.paint.setColor(-1);
        Rect keyRect = getKeyRect();
        this.path.moveTo(keyRect.left, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.top);
        this.path.lineTo(keyRect.right, keyRect.bottom);
        this.path.lineTo(keyRect.left, keyRect.bottom);
        canvas.drawPath(this.path, this.paint);
        this.path.reset();
        this.paint.setColor(this.colorRed);
        List<DestPoint> pointsA = getPointsA();
        int size = pointsA.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (!pointsA.get(size).isInvalid()) {
                float pointX = getPointX(size, CopyKey.Side.A);
                var r4 = pointsA.get(size);
                if (pointX != 0.0f) {
                    if (size == pointsA.size() - 1) {
                        this.path.moveTo(pointX, keyRect.top + (r4.getDepth() * getRatio()));
                    } else {
                        this.path.lineTo(pointX, keyRect.top + (r4.getDepth() * getRatio()));
                    }
                }
            }
        }
        List<DestPoint> pointsB = getPointsB();
        DestPoint destPoint = pointsA.get(0);
        DestPoint destPoint2 = pointsB.get(0);
        float pointX2 = getPointX(0, CopyKey.Side.A);
        float depth = keyRect.top + (destPoint.getDepth() * getRatio());
        float depth2 = keyRect.bottom - (destPoint2.getDepth() * getRatio());
        float f = (depth2 + depth) / 2.0f;
        float f2 = (depth2 - depth) / 2.0f;
        this.path.addArc(new RectF(pointX2 - f2, f - f2, pointX2 + f2, f + f2), -90.0f, -180.0f);
        for (int i = 0; i < pointsB.size(); i++) {
            var r1 = pointsB.get(i);
            if (!r1.isInvalid()) {
                float pointX3 = getPointX(i, CopyKey.Side.B);
                if (pointX3 != 0.0f) {
                    this.path.lineTo(pointX3, keyRect.bottom - (r1.getDepth() * getRatio()));
                }
            }
        }
        canvas.drawPath(this.path, this.paint);
    }
}
