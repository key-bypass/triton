package com.pgyersdk.p008b;

import android.app.Activity;
import android.graphics.Bitmap;
import com.pgyersdk.p008b.C2018d;
import com.pgyersdk.p008b.p010b.C2009b;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CaptureExecute.java */
/* renamed from: com.pgyersdk.b.c */
/* loaded from: classes2.dex */
public class C2010c extends C2009b {

    /* renamed from: a */
    final /* synthetic */ Activity f442a;

    /* renamed from: b */
    final /* synthetic */ C2018d.a f443b;

    /* renamed from: c */
    final /* synthetic */ WeakReference f444c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2010c(Activity activity, C2018d.a aVar, WeakReference weakReference) {
        this.f442a = activity;
        this.f443b = aVar;
        this.f444c = weakReference;
    }

    @Override // com.pgyersdk.p008b.p010b.InterfaceC2008a
    /* renamed from: a */
    public void mo111a(Bitmap bitmap) {
        C2019e.m130a(bitmap, this.f442a.getApplicationContext(), new C2007b(this));
    }

    @Override // com.pgyersdk.p008b.p010b.InterfaceC2008a
    /* renamed from: a */
    public void mo112a(Throwable th) {
    }
}
