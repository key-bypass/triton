package com.pgyersdk.feedback.p017a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerVoiceButton.java */
/* renamed from: com.pgyersdk.feedback.a.o */
/* loaded from: classes2.dex */
public class C2064o extends Shape {

    /* renamed from: a */
    final /* synthetic */ int f616a;

    /* renamed from: b */
    final /* synthetic */ C2066q f617b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2064o(C2066q c2066q, int i) {
        this.f617b = c2066q;
        this.f616a = i;
    }

    @Override // android.graphics.drawable.shapes.Shape
    public void draw(Canvas canvas, Paint paint) {
        Context context;
        String str;
        C2066q c2066q = this.f617b;
        context = c2066q.f622c;
        str = this.f617b.f620a;
        c2066q.m313a(context, canvas, str, this.f616a);
    }
}
