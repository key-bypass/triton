package com.pgyersdk.p008b;

import android.app.Activity;
import android.graphics.Bitmap;

import com.pgyersdk.p008b.p010b.SimpleScreenCapturingListener;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CaptureExecute.java */
/* renamed from: com.pgyersdk.b.c */
/* loaded from: classes2.dex */
public class CaptureExecuteListener extends SimpleScreenCapturingListener {

    /* renamed from: a */
    final /* synthetic */ Activity f442a;

    /* renamed from: b */
    final /* synthetic */ CaptureExecute.ICaptureExecute f443b;

    /* renamed from: c */
    final /* synthetic */ WeakReference f444c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CaptureExecuteListener(Activity activity, CaptureExecute.ICaptureExecute ICaptureExecuteVar, WeakReference weakReference) {
        this.f442a = activity;
        this.f443b = ICaptureExecuteVar;
        this.f444c = weakReference;
    }

    @Override // com.pgyersdk.p008b.p010b.InterfaceC2008a
    /* renamed from: a */
    public void onComplete(Bitmap bitmap) {
        FileManager.m130a(bitmap, this.f442a.getApplicationContext(), new C2007b(this));
    }

    @Override // com.pgyersdk.p008b.p010b.InterfaceC2008a
    /* renamed from: a */
    public void onError(Throwable th) {
    }
}
