package com.pgyersdk.feedback;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;
import android.view.View;

import com.pgyersdk.utils.ConvertUtil;

public class FeedbackAdShape extends Shape {

    final Paint f548a;

    final Context f549b;

    final int f550c;

    final View.OnClickListener f551d;

    public FeedbackAdShape(View.OnClickListener viewOnClickListenerC2070d, Paint paint, Context context, int i) {
        this.f551d = viewOnClickListenerC2070d;
        this.f548a = paint;
        this.f549b = context;
        this.f550c = i;
    }

    @Override // android.graphics.drawable.shapes.Shape
    public void draw(Canvas canvas, Paint paint) {
        this.f548a.setAntiAlias(true);
        int m195a = ConvertUtil.m195a(this.f549b, 1.0f);
        float f = m195a;
        float f2 = this.f550c - m195a;
        canvas.drawArc(new RectF(f, f, f2, f2), 0.0f, 360.0f, true, this.f548a);
    }
}
