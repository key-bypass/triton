package com.pgyersdk.feedback;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.Shape;
import com.pgyersdk.p016f.C2037b;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeedbackAd.java */
/* renamed from: com.pgyersdk.feedback.a */
/* loaded from: classes2.dex */
public class C2049a extends Shape {

    /* renamed from: a */
    final /* synthetic */ Paint f548a;

    /* renamed from: b */
    final /* synthetic */ Context f549b;

    /* renamed from: c */
    final /* synthetic */ int f550c;

    /* renamed from: d */
    final /* synthetic */ ViewOnClickListenerC2070d f551d;

    public C2049a(ViewOnClickListenerC2070d viewOnClickListenerC2070d, Paint paint, Context context, int i) {
        this.f551d = viewOnClickListenerC2070d;
        this.f548a = paint;
        this.f549b = context;
        this.f550c = i;
    }

    @Override // android.graphics.drawable.shapes.Shape
    public void draw(Canvas canvas, Paint paint) {
        this.f548a.setAntiAlias(true);
        int m195a = C2037b.m195a(this.f549b, 1.0f);
        float f = m195a;
        float f2 = this.f550c - m195a;
        canvas.drawArc(new RectF(f, f, f2, f2), 0.0f, 360.0f, true, this.f548a);
    }
}
