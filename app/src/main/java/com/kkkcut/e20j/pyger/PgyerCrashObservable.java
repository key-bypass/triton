package com.kkkcut.e20j.pyger;

import com.pgyersdk.crash.PgyerObservable;
import com.pgyersdk.crash.PgyerObserver;
import com.pgyersdk.utils.AsyncTaskUtils;
import com.pgyersdk.utils.LogUtils;
import java.lang.Thread;

/* loaded from: classes2.dex */
public class PgyerCrashObservable extends PgyerObservable {

    /* renamed from: b */
    PgyerObserver f484b;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.kkkcut.e20j.pyger.PgyerCrashObservable$a */
    /* loaded from: classes2.dex */
    public static class C2025a {

        /* renamed from: a */
        private static final PgyerCrashObservable f485a = new PgyerCrashObservable();
    }

    /* synthetic */ PgyerCrashObservable(C2028c c2028c) {
        this();
    }

    /* renamed from: b */
    private void m155b() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            LogUtils.m216a("PgyerSDK", "Current handler class = " + defaultUncaughtExceptionHandler.getClass().getName());
        }
        if (defaultUncaughtExceptionHandler instanceof C2026a) {
            LogUtils.m216a("PgyerSDK", "ExceptionHandler is already reset");
        } else {
            Thread.setDefaultUncaughtExceptionHandler(new C2026a(defaultUncaughtExceptionHandler, this));
        }
    }

    public static PgyerCrashObservable get() {
        return C2025a.f485a;
    }

    /* renamed from: a */
    public void m156a() {
        AsyncTaskUtils.m194a(new AsyncTaskC2031f());
        m155b();
    }

    @Override // com.pgyersdk.crash.C2030e
    public /* bridge */ /* synthetic */ void attach(PgyerObserver pgyerObserver) {
        super.attach(pgyerObserver);
    }

    @Override // com.pgyersdk.crash.C2030e
    public void detach(PgyerObserver pgyerObserver) {
        if (!pgyerObserver.equals(this.f484b)) {
            super.detach(pgyerObserver);
        } else {
            LogUtils.m220d("PgyerSDK", "Can't detach pgyer default observer.");
        }
    }

    @Override // com.pgyersdk.crash.C2030e
    public /* bridge */ /* synthetic */ void notifyObservers(Thread thread, Throwable th) {
        super.notifyObservers(thread, th);
    }

    private PgyerCrashObservable() {
        C2029d c2029d = new C2029d();
        this.f484b = c2029d;
        attach(c2029d);
    }
}
