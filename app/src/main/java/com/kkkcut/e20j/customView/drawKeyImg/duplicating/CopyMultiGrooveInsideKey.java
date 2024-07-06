package com.kkkcut.e20j.customView.drawKeyImg.duplicating;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.view.InputDeviceCompat;
import com.kkkcut.e20j.customView.drawKeyImg.duplicating.CopyKey;
import com.cutting.machine.bean.DestPoint;
import java.util.List;

/* loaded from: classes.dex */
public class CopyMultiGrooveInsideKey extends CopyKey {
    private final Paint pointPaint;
    private List<List<DestPoint>> pointsAContainer;
    private List<List<DestPoint>> pointsBContainer;

    public CopyMultiGrooveInsideKey(Context context, List<List<DestPoint>> list, List<List<DestPoint>> list2, int i, int i2) {
        super(context);
        setMaxY(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            setMaxX(Math.max(list.get(i3).get(0).getSpace() - i, getMaxX()));
        }
        for (int i4 = 0; i4 < list2.size(); i4++) {
            setMaxX(Math.max(list2.get(i4).get(0).getSpace() - i, getMaxX()));
        }
        this.pointsAContainer = list;
        this.pointsBContainer = list2;
        this.paint = new Paint();
        this.paint.setColor(-1);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setPathEffect(new CornerPathEffect(4.0f));
        this.paint.setAntiAlias(true);
        this.path = new Path();
        Paint paint = new Paint();
        this.pointPaint = paint;
        paint.setColor(InputDeviceCompat.SOURCE_ANY);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10.0f);
        cacularKeySize();
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
        for (int i = 0; i < this.pointsAContainer.size(); i++) {
            this.path.reset();
            List<DestPoint> list = this.pointsAContainer.get(i);
            setPointsA(list);
            int size = list.size();

            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                var r6 = list.get(size);
                if (!list.get(size).isInvalid()) {
                    float pointX = getPointX(size, CopyKey.Side.A);
                    if (pointX != 0.0f) {
                        if (size == list.size() - 1) {
                            this.path.moveTo(pointX, keyRect.top + (r6.getDepth() * getRatio()));
                        } else {
                            this.path.lineTo(pointX, keyRect.top + (r6.getDepth() * getRatio()));
                        }
                    }
                }
            }
            List<DestPoint> list2 = this.pointsBContainer.get(i);
            setPointsB(list2);
            DestPoint destPoint = list.get(0);
            DestPoint destPoint2 = list2.get(0);
            float pointX2 = getPointX(0, CopyKey.Side.A);
            float depth = keyRect.top + (destPoint.getDepth() * getRatio());
            float depth2 = keyRect.bottom - (destPoint2.getDepth() * getRatio());
            float f = (depth2 + depth) / 2.0f;
            float f2 = (depth2 - depth) / 2.0f;
            this.path.addArc(new RectF(pointX2 - f2, f - f2, pointX2 + f2, f + f2), -90.0f, -180.0f);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                var r6 = list2.get(i2);
                if (!r6.isInvalid()) {
                    float pointX3 = getPointX(i2, CopyKey.Side.B);
                    if (pointX3 != 0.0f) {
                        this.path.lineTo(pointX3, keyRect.bottom - (r6.getDepth() * getRatio()));
                    }
                }
            }
            canvas.drawPath(this.path, this.paint);
        }
    }
}
