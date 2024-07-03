package com.kkkcut.e20j.pyger;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.Shape;
import android.widget.Button;
import com.pgyersdk.utils.ConvertUtil;

/* compiled from: PgyerVoiceButton.java */
/* renamed from: com.pgyersdk.feedback.a.q */
/* loaded from: classes2.dex */
public class C2066q extends Button {

    /* renamed from: a */
    private String f620a;

    /* renamed from: b */
    private String f621b;

    /* renamed from: c */
    private Context f622c;

    /* renamed from: d */
    private ShapeDrawable f623d;

    /* renamed from: e */
    private ShapeDrawable f624e;

    /* renamed from: f */
    private Shape f625f;

    /* renamed from: g */
    private Shape f626g;

    public C2066q(Context context) {
        super(context);
        this.f620a = "#f2f2f2";
        this.f621b = "#e0e0e0";
        this.f622c = context;
        setPadding(ConvertUtil.m195a(context, 20.0f), 0, 0, 0);
        setBackground(3);
        setTextSize(16.0f);
        setPadding(0, 0, ConvertUtil.m195a(context, 10.0f), 0);
    }

    private void setBackground(int i) {
        this.f625f = new C2064o(this, i);
        ShapeDrawable shapeDrawable = new ShapeDrawable(this.f625f);
        this.f623d = shapeDrawable;
        shapeDrawable.getPaint().setColor(Color.parseColor(this.f620a));
        this.f623d.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        this.f626g = new C2065p(this, i);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(this.f626g);
        this.f624e = shapeDrawable2;
        shapeDrawable2.getPaint().setColor(Color.parseColor(this.f621b));
        this.f624e.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        Context context = this.f622c;
        ShapeDrawable shapeDrawable3 = this.f623d;
        ShapeDrawable shapeDrawable4 = this.f624e;
        setBackgroundDrawable(m312a(context, shapeDrawable3, shapeDrawable4, shapeDrawable4, shapeDrawable3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m313a(Context context, Canvas canvas, String str, int i) {
        Paint paint = new Paint();
        Path path = new Path();
        path.moveTo(0.0f, getHeight() / 2);
        path.lineTo(ConvertUtil.m195a(context, 10.0f), (getHeight() / 2) - ConvertUtil.m195a(context, 4.0f));
        path.lineTo(ConvertUtil.m195a(context, 10.0f), (getHeight() / 2) + ConvertUtil.m195a(context, 4.0f));
        path.lineTo(0.0f, getHeight() / 2);
        path.close();
        paint.setColor(Color.parseColor(str));
        canvas.drawPath(path, paint);
        canvas.drawRoundRect(new RectF(ConvertUtil.m195a(context, 10.0f), 0.0f, getWidth(), getHeight()), ConvertUtil.m195a(context, 5.0f), ConvertUtil.m195a(context, 5.0f), paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2.0f);
        paint2.setColor(-7829368);
        int m195a = ConvertUtil.m195a(context, 5.0f);
        if (i >= 1) {
            canvas.drawArc(new RectF(ConvertUtil.m195a(context, 8.0f), ConvertUtil.m195a(context, 8.0f) + m195a, (getHeight() - (m195a * 2)) - ConvertUtil.m195a(context, 8.0f), (getHeight() - m195a) - ConvertUtil.m195a(context, 8.0f)), -30.0f, 60.0f, false, paint2);
        }
        if (i >= 2) {
            canvas.drawArc(new RectF(ConvertUtil.m195a(context, 4.0f), ConvertUtil.m195a(context, 4.0f) + m195a, (getHeight() - (m195a * 2)) - ConvertUtil.m195a(context, 4.0f), (getHeight() - m195a) - ConvertUtil.m195a(context, 4.0f)), -30.0f, 60.0f, false, paint2);
        }
        if (i >= 3) {
            canvas.drawArc(new RectF(0.0f, m195a, getHeight() - (m195a * 2), getHeight() - m195a), -30.0f, 60.0f, false, paint2);
        }
    }

    /* renamed from: a */
    public void m317a(int i) {
        setBackground(i);
        postInvalidate();
    }

    /* renamed from: a */
    public static StateListDrawable m312a(Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, drawable2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, drawable);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, drawable4);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }
}
