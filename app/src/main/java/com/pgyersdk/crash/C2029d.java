package com.pgyersdk.crash;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.p016f.C2048m;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerCrashObserver.java */
/* renamed from: com.pgyersdk.crash.d */
/* loaded from: classes2.dex */
public class C2029d implements PgyerObserver {
    @Override // com.pgyersdk.crash.PgyerObserver
    public void receivedCrash(Thread thread, Throwable th) {
        PgyCrashManager.m154a(C2048m.m246a(th), PgyCrashManager.EnumC2024a.CRASH);
    }
}
