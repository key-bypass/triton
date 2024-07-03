package com.pgyersdk.feedback.p017a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.p013d.DeviceHelper;
import com.pgyersdk.utils.ConvertUtil;

/* compiled from: PgyerRecordPopup.java */
/* renamed from: com.pgyersdk.feedback.a.n */
/* loaded from: classes2.dex */
public class C2063n extends View {

    /* renamed from: a */
    private Context f612a;

    /* renamed from: b */
    private int f613b;

    /* renamed from: c */
    private int f614c;

    /* renamed from: d */
    private String f615d;

    public C2063n(Context context) {
        super(context);
        this.f613b = 0;
        this.f614c = 10;
        this.f612a = context;
        this.f615d = DeviceHelper.m169b(context);
    }

    /* renamed from: a */
    public void m310a(int i, int i2) {
        this.f613b = i;
        this.f614c = i2;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#5f000000"));
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2.0f);
        paint2.setAntiAlias(true);
        int m195a = ConvertUtil.m195a(this.f612a, 15.0f);
        int m195a2 = ConvertUtil.m195a(this.f612a, 18.0f);
        int m195a3 = ConvertUtil.m195a(this.f612a, 13.0f);
        float f3 = m195a2;
        float f4 = m195a;
        new RectF(-ConvertUtil.m195a(this.f612a, f3), f4, (getHeight() - (m195a * 2)) - m195a2, getHeight() - m195a);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, getWidth(), getHeight()), ConvertUtil.m195a(this.f612a, 10.0f), ConvertUtil.m195a(this.f612a, 10.0f), paint);
        if (this.f613b != -1) {
            Paint paint3 = new Paint();
            paint3.setColor(-1);
            paint3.setStyle(Paint.Style.FILL_AND_STROKE);
            paint3.setStrokeWidth(2.0f);
            paint3.setAntiAlias(true);
            canvas.drawRoundRect(new RectF(f3, f4, ConvertUtil.m195a(this.f612a, 10.0f) + m195a2, ConvertUtil.m195a(this.f612a, 24.0f) + m195a), ConvertUtil.m195a(this.f612a, 5.0f), ConvertUtil.m195a(this.f612a, 5.0f), paint3);
            canvas.drawArc(new RectF(m195a3, ConvertUtil.m195a(this.f612a, 25.0f), m195a3 + ConvertUtil.m195a(this.f612a, 20.0f), ConvertUtil.m195a(this.f612a, 45.0f)), 0.0f, 180.0f, false, paint2);
            canvas.drawLine(ConvertUtil.m195a(this.f612a, 23.0f), ConvertUtil.m195a(this.f612a, 45.0f), ConvertUtil.m195a(this.f612a, 23.0f), ConvertUtil.m195a(this.f612a, 53.0f), paint2);
            canvas.drawLine(ConvertUtil.m195a(this.f612a, 18.0f), ConvertUtil.m195a(this.f612a, 53.0f), ConvertUtil.m195a(this.f612a, 28.0f), ConvertUtil.m195a(this.f612a, 53.0f), paint2);
            int m195a4 = ConvertUtil.m195a(this.f612a, 6.0f);
            int m195a5 = ConvertUtil.m195a(this.f612a, 2.5f);
            int m195a6 = ConvertUtil.m195a(this.f612a, 5.0f);
            if (this.f613b >= 1) {
                f = 35.0f;
                f2 = 40.0f;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, 35.0f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f), ConvertUtil.m195a(this.f612a, 40.0f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f), paint2);
            } else {
                f = 35.0f;
                f2 = 40.0f;
            }
            if (this.f613b >= 2) {
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - m195a4, ConvertUtil.m195a(this.f612a, f2) + m195a6 + m195a5, ConvertUtil.m195a(this.f612a, 53.0f) - m195a4, paint2);
            }
            if (this.f613b >= 3) {
                int i = m195a4 * 2;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - i, ConvertUtil.m195a(this.f612a, f2) + m195a6 + (m195a5 * 2), ConvertUtil.m195a(this.f612a, 53.0f) - i, paint2);
            }
            if (this.f613b >= 4) {
                int i2 = m195a4 * 3;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - i2, ConvertUtil.m195a(this.f612a, f2) + m195a6 + (m195a5 * 3), ConvertUtil.m195a(this.f612a, 53.0f) - i2, paint2);
            }
            if (this.f613b >= 5) {
                int i3 = m195a4 * 4;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - i3, ConvertUtil.m195a(this.f612a, f2) + m195a6 + (m195a5 * 4), ConvertUtil.m195a(this.f612a, 53.0f) - i3, paint2);
            }
            if (this.f613b >= 6) {
                int i4 = m195a4 * 5;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - i4, ConvertUtil.m195a(this.f612a, f2) + m195a6 + (m195a5 * 5), ConvertUtil.m195a(this.f612a, 53.0f) - i4, paint2);
            }
            if (this.f613b >= 7) {
                int i5 = m195a4 * 6;
                canvas.drawLine(ConvertUtil.m195a(this.f612a, f) + m195a6, ConvertUtil.m195a(this.f612a, 53.0f) - i5, m195a6 + ConvertUtil.m195a(this.f612a, f2) + (m195a5 * 6), ConvertUtil.m195a(this.f612a, 53.0f) - i5, paint2);
            }
        } else {
            Paint paint4 = new Paint();
            paint4.setColor(-1);
            paint4.setTextSize(80.0f);
            String str = this.f614c + "";
            if (this.f614c <= 0) {
                this.f614c = 0;
                str = "0";
            }
            float measureText = paint4.measureText(str);
            Paint.FontMetrics fontMetrics = paint4.getFontMetrics();
            canvas.drawText(str, (getWidth() - measureText) / 2.0f, ((getHeight() - ((getHeight() - (fontMetrics.bottom - fontMetrics.top)) / 2.0f)) - fontMetrics.bottom) - ConvertUtil.m195a(this.f612a, 5.0f), paint4);
        }
        Paint paint5 = new Paint();
        paint5.setColor(-1);
        if ("mdpi".equals(this.f615d)) {
            paint5.setTextSize(13.0f);
        } else if ("hdpi".equals(this.f615d)) {
            paint5.setTextSize(14.0f);
        } else {
            paint5.setTextSize(17.0f);
        }
        String m151a = Strings.m151a(1076);
        float measureText2 = paint5.measureText(m151a);
        Paint.FontMetrics fontMetrics2 = paint5.getFontMetrics();
        canvas.drawText(m151a, (getWidth() - measureText2) / 2.0f, (getHeight() - (fontMetrics2.bottom - fontMetrics2.top)) + ConvertUtil.m195a(this.f612a, 2.0f), paint5);
    }
}
