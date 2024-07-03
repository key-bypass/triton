package com.pgyersdk.crash;

import com.pgyersdk.utils.Utils;

public class PgyerCrashObserver implements PgyerObserver {
    @Override
    public void receivedCrash(Thread thread, Throwable th) {
        PgyCrashManager.m154a(Utils.m246a(th), PgyCrashManager.EnumC2024a.CRASH);
    }
}
