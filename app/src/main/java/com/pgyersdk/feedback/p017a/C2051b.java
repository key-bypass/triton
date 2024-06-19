package com.pgyersdk.feedback.p017a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;

/* compiled from: HandwritingView.java */
/* renamed from: com.pgyersdk.feedback.a.b */
/* loaded from: classes2.dex */
public class C2051b extends ImageView {

    /* renamed from: a */
    private Paint f557a;

    /* renamed from: b */
    private Bitmap f558b;

    /* renamed from: c */
    private Bitmap f559c;

    /* renamed from: d */
    private float f560d;

    /* renamed from: e */
    private float f561e;

    /* renamed from: f */
    private float f562f;

    /* renamed from: g */
    private float f563g;

    /* renamed from: h */
    private boolean f564h;

    /* renamed from: i */
    private boolean f565i;

    /* renamed from: j */
    private int f566j;

    /* renamed from: k */
    private float f567k;

    public C2051b(Context context) {
        super(context);
        this.f557a = null;
        this.f558b = null;
        this.f559c = null;
        this.f560d = 0.0f;
        this.f561e = 0.0f;
        this.f562f = 0.0f;
        this.f563g = 0.0f;
        this.f564h = true;
        this.f565i = false;
        this.f566j = SupportMenu.CATEGORY_MASK;
        this.f567k = 4.0f;
        setScaleType(ImageView.ScaleType.FIT_XY);
    }

    /* renamed from: a */
    public void m261a() {
        this.f565i = true;
        Bitmap bitmap = this.f559c;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f559c = null;
        }
        this.f559c = Bitmap.createBitmap(this.f558b);
        invalidate();
    }

    /* renamed from: b */
    public void m262b() {
        Bitmap bitmap = this.f558b;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f558b = null;
        }
        Bitmap bitmap2 = this.f559c;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            return;
        }
        this.f559c = null;
    }

    /* renamed from: c */
    public Bitmap m263c() {
        if (this.f565i) {
            return this.f558b;
        }
        return this.f559c;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.f559c;
        if (bitmap == null) {
            return;
        }
        canvas.drawBitmap(m260a(bitmap), 0.0f, 0.0f, (Paint) null);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f562f = motionEvent.getX();
            this.f563g = motionEvent.getY();
            this.f564h = false;
            this.f565i = false;
            invalidate();
            return true;
        }
        if (motionEvent.getAction() == 2) {
            this.f564h = true;
            this.f560d = motionEvent.getX();
            this.f561e = motionEvent.getY();
            invalidate();
            return true;
        }
        if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return super.onTouchEvent(motionEvent);
        }
        this.f564h = false;
        return true;
    }

    public void setColor(String str) {
        this.f566j = Color.parseColor(str);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (bitmap == null) {
            return;
        }
        Bitmap copy = Bitmap.createBitmap(bitmap).copy(Bitmap.Config.ARGB_8888, true);
        this.f558b = copy;
        this.f559c = Bitmap.createBitmap(copy);
        setDrawingCacheEnabled(true);
    }

    public void setstyle(float f) {
        this.f567k = f;
    }

    /* renamed from: a */
    public Bitmap m260a(Bitmap bitmap) {
        Canvas canvas;
        if (this.f565i) {
            canvas = new Canvas(this.f558b);
        } else {
            canvas = new Canvas(this.f559c);
        }
        Paint paint = new Paint();
        this.f557a = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f557a.setAntiAlias(true);
        this.f557a.setColor(this.f566j);
        this.f557a.setStrokeWidth(this.f567k);
        if (this.f564h) {
            Path path = new Path();
            path.moveTo(this.f562f, this.f563g);
            path.quadTo(this.f562f, this.f563g, this.f560d, this.f561e);
            canvas.drawPath(path, this.f557a);
            this.f562f = this.f560d;
            this.f563g = this.f561e;
        }
        if (this.f565i) {
            return this.f558b;
        }
        return this.f559c;
    }
}
