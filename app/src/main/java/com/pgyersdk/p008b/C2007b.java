package com.pgyersdk.p008b;

import android.app.Activity;
import android.net.Uri;
import com.pgyersdk.p008b.C2019e;

/* compiled from: CaptureExecute.java */
/* renamed from: com.pgyersdk.b.b */
/* loaded from: classes2.dex */
class C2007b implements C2019e.a {

    /* renamed from: a */
    final /* synthetic */ C2010c f441a;

    public C2007b(C2010c c2010c) {
        this.f441a = c2010c;
    }

    @Override // com.pgyersdk.p008b.C2019e.a
    /* renamed from: a */
    public void mo108a(Uri uri) {
        this.f441a.f443b.mo128a(uri);
        C2021g.m133a((Activity) this.f441a.f444c.get()).f462f = null;
    }

    @Override // com.pgyersdk.p008b.C2019e.a
    /* renamed from: a */
    public void mo109a(Throwable th) {
        this.f441a.f443b.mo129a(th);
        C2021g.m133a((Activity) this.f441a.f444c.get()).f462f = null;
    }
}
