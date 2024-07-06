package com.pgyersdk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerColorPikcerBg.java */
/* renamed from: com.pgyersdk.feedback.a.d */
/* loaded from: classes2.dex */
public class C2053d extends Shape {

    /* renamed from: a */
    final /* synthetic */ C2054e f568a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2053d(C2054e c2054e) {
        this.f568a = c2054e;
    }

    @Override // android.graphics.drawable.shapes.Shape
    public void draw(Canvas canvas, Paint paint) {
        Context context;
        C2054e c2054e = this.f568a;
        context = c2054e.f569a;
        c2054e.m267a(context, canvas);
    }
}
