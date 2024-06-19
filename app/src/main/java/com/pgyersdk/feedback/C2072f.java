package com.pgyersdk.feedback;

import android.net.Uri;
import com.pgyersdk.p008b.C2018d;
import com.pgyersdk.p016f.C2041f;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.f */
/* loaded from: classes2.dex */
public class C2072f implements C2018d.a {

    /* renamed from: a */
    final /* synthetic */ C2077k f669a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2072f(C2077k c2077k) {
        this.f669a = c2077k;
    }

    @Override // com.pgyersdk.p008b.C2018d.a
    /* renamed from: a */
    public void mo128a(Uri uri) {
        String unused = C2077k.f677a = uri.getPath();
        this.f669a.m346k();
    }

    @Override // com.pgyersdk.p008b.C2018d.a
    /* renamed from: a */
    public void mo129a(Throwable th) {
        this.f669a.m350c();
        C2041f.m217a("PgyerSDK", "Take screen shot failed", th);
    }
}
