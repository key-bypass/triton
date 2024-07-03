package com.cutting.machine.duplicate.keyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import androidx.core.view.InputDeviceCompat;

import com.cutting.machine.bean.DestPoint;

import java.util.List;

/* loaded from: classes2.dex */
public class CopyMultiGrooveInsideKey extends CopyKey {
    private final Paint pointPaint;
    private final List<List<DestPoint>> pointsAContainer;
    private final List<List<DestPoint>> pointsBContainer;

    public CopyMultiGrooveInsideKey(Context context, List<List<DestPoint>> list, List<List<DestPoint>> list2, int i, int i2) {
        super(context);
        for (int i3 = 0; i3 < list.size(); i3++) {
        }
        for (int i4 = 0; i4 < list2.size(); i4++) {
        }
        this.pointsAContainer = list;
        this.pointsBContainer = list2;
        this.paint = new Paint();
        this.paint.setColor(getKeyColor());
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
        this.paint.setColor(this.colorRed);
    }
}
