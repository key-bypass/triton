package com.pgyersdk;

import com.pgyersdk.feedback.PgyerFeedbackManagerDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerShakeManager.java */
/* renamed from: com.pgyersdk.feedback.l */
/* loaded from: classes2.dex */
public class PgyerShakeManager implements PgyerFeedbackManagerDelegate.IPgyerFeedbackManagerDelegate {

    /* renamed from: a */
    final /* synthetic */ com.pgyersdk.feedback.PgyerShakeManager f684a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PgyerShakeManager(com.pgyersdk.feedback.PgyerShakeManager pgyerShakeManager) {
        this.f684a = pgyerShakeManager;
    }

    @Override // com.kkkcut.e20j.pyger.PgyerFeedbackManagerDelegate.a
    /* renamed from: a */
    public void mo354a() {
        this.f684a.m362f();
        this.f684a.m361e();
    }

    @Override // com.kkkcut.e20j.pyger.PgyerFeedbackManagerDelegate.a
    /* renamed from: b */
    public void mo355b() {
        this.f684a.m360d();
        this.f684a.m364b();
    }
}
