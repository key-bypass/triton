package com.pgyersdk.feedback.p017a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerVoiceButton.java */
/* renamed from: com.pgyersdk.feedback.a.p */
/* loaded from: classes2.dex */
public class C2065p extends Shape {

    /* renamed from: a */
    final /* synthetic */ int f618a;

    /* renamed from: b */
    final /* synthetic */ C2066q f619b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2065p(C2066q c2066q, int i) {
        this.f619b = c2066q;
        this.f618a = i;
    }

    @Override // android.graphics.drawable.shapes.Shape
    public void draw(Canvas canvas, Paint paint) {
        Context context;
        String str;
        C2066q c2066q = this.f619b;
        context = c2066q.f622c;
        str = this.f619b.f621b;
        c2066q.m313a(context, canvas, str, this.f618a);
    }
}
