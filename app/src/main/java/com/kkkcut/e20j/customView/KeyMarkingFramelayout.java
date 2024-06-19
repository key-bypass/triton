package com.kkkcut.e20j.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.internal.view.SupportMenu;

/* loaded from: classes.dex */
public class KeyMarkingFramelayout extends FrameLayout {
    private int color_1b1a28;
    private int color_4b4d62;
    private boolean markLineVisible;
    private Paint paint;
    private int rightBorder;

    public KeyMarkingFramelayout(Context context) {
        this(context, null);
    }

    public KeyMarkingFramelayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KeyMarkingFramelayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.color_4b4d62 = Color.parseColor("#4b4d62");
        this.color_1b1a28 = Color.parseColor("#1b1a28");
        this.markLineVisible = true;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setTextSize(24.0f);
        setLayerType(1, this.paint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setMarkLineVisible(boolean z) {
        this.markLineVisible = z;
        invalidate();
    }

    public void showBorder(int i) {
        this.rightBorder = i + 45;
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.markLineVisible) {
            this.paint.setColor(this.color_1b1a28);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(45.0f, 0.0f, 45.0f, getMeasuredHeight(), this.paint);
            canvas.drawLine(0.0f, 45.0f, getMeasuredWidth(), 45.0f, this.paint);
            Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
            float f = ((fontMetrics.bottom - fontMetrics.top) / 2.0f) - fontMetrics.bottom;
            float f2 = f + 22.5f;
            for (int i = 0; i < 7; i++) {
                int i2 = i / 2;
                if (i % 2 == 0) {
                    float f3 = (i2 * 100) + 45.0f;
                    if (i != 0) {
                        canvas.drawText(String.valueOf(i2), f3, f2, this.paint);
                        canvas.drawLine(f3, 45.0f, f3, 35.0f, this.paint);
                        this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
                        this.paint.setColor(this.color_4b4d62);
                        canvas.drawLine(f3, 45.0f, f3, 400.0f, this.paint);
                        this.paint.setPathEffect(null);
                        this.paint.setColor(this.color_1b1a28);
                    } else {
                        canvas.drawText(String.valueOf(i2), 22.5f, f2, this.paint);
                    }
                } else {
                    float f4 = (i2 * 100) + 95.0f;
                    canvas.drawLine(f4, 45.0f, f4, 40.0f, this.paint);
                    this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
                    this.paint.setColor(this.color_4b4d62);
                    canvas.drawLine(f4, 45.0f, f4, 400.0f, this.paint);
                    this.paint.setPathEffect(null);
                    this.paint.setColor(this.color_1b1a28);
                }
            }
            for (int i3 = 0; i3 < 7; i3++) {
                int i4 = i3 / 2;
                if (i3 % 2 == 0) {
                    float f5 = (i4 * 100) + 45;
                    if (i3 != 0) {
                        canvas.drawText(String.valueOf(i4), 22.5f, f5 + f, this.paint);
                        canvas.drawLine(35.0f, f5, 45.0f, f5, this.paint);
                        this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
                        this.paint.setColor(this.color_4b4d62);
                        canvas.drawLine(45.0f, f5, 380.0f, f5, this.paint);
                        this.paint.setPathEffect(null);
                        this.paint.setColor(this.color_1b1a28);
                    }
                } else {
                    float f6 = (i4 * 100) + 95;
                    canvas.drawLine(40.0f, f6, 45.0f, f6, this.paint);
                    this.paint.setPathEffect(new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f));
                    this.paint.setColor(this.color_4b4d62);
                    canvas.drawLine(45.0f, f6, 380.0f, f6, this.paint);
                    this.paint.setPathEffect(null);
                    this.paint.setColor(this.color_1b1a28);
                }
            }
            if (!this.markLineVisible || this.rightBorder == 0) {
                return;
            }
            this.paint.setColor(SupportMenu.CATEGORY_MASK);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(75.0f, 75.0f, this.rightBorder, getHeight() - 10, this.paint);
        }
    }
}
