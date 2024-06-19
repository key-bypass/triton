package com.pgyersdk.feedback.p017a;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.h */
/* loaded from: classes2.dex */
public class ViewOnTouchListenerC2057h implements View.OnTouchListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f573a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewOnTouchListenerC2057h(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f573a = alertDialogBuilderC2062m;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.f573a.m274a(view);
        return false;
    }
}
