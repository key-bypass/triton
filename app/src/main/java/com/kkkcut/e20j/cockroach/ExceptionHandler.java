package com.kkkcut.e20j.cockroach;

/* loaded from: classes.dex */
public abstract class ExceptionHandler {
    protected abstract void onBandageExceptionHappened(Throwable th);

    protected abstract void onEnterSafeMode();

    protected void onMayBeBlackScreen(Throwable th) {
    }

    protected abstract void onUncaughtExceptionHappened(Thread thread, Throwable th);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void uncaughtExceptionHappened(Thread thread, Throwable th) {
        try {
            onUncaughtExceptionHappened(thread, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void bandageExceptionHappened(Throwable th) {
        try {
            onBandageExceptionHappened(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void enterSafeMode() {
        try {
            onEnterSafeMode();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void mayBeBlackScreen(Throwable th) {
        try {
            onMayBeBlackScreen(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
