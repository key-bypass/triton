package com.pgyersdk.p008b;

import android.app.Activity;
import android.net.Uri;

/* compiled from: CaptureExecute.java */
/* renamed from: com.pgyersdk.b.b */
/* loaded from: classes2.dex */
class C2007b implements FileManager.a {

    /* renamed from: a */
    final /* synthetic */ CaptureExecuteListener f441a;

    public C2007b(CaptureExecuteListener captureExecuteListener) {
        this.f441a = captureExecuteListener;
    }

    @Override // com.pgyersdk.p008b.C2019e.a
    /* renamed from: a */
    public void mo108a(Uri uri) {
        this.f441a.f443b.mo128a(uri);
        TracupCapture.m133a((Activity) this.f441a.f444c.get()).f462f = null;
    }

    @Override // com.pgyersdk.p008b.C2019e.a
    /* renamed from: a */
    public void mo109a(Throwable th) {
        this.f441a.f443b.mo129a(th);
        TracupCapture.m133a((Activity) this.f441a.f444c.get()).f462f = null;
    }
}
