package com.pgyersdk.crash;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Constants;

/* compiled from: ExceptionHandler.java */
/* renamed from: com.pgyersdk.crash.a */
/* loaded from: classes2.dex */
public class ExceptionHandlerThread implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private boolean f486a = false;

    /* renamed from: b */
    private Thread.UncaughtExceptionHandler f487b;

    /* renamed from: c */
    PgyerObservable f488c;

    public ExceptionHandlerThread(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, PgyerObservable pgyerObservable) {
        this.f487b = uncaughtExceptionHandler;
        this.f488c = pgyerObservable;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.f486a = PgyCrashManager.isIsIgnoreDefaultHander();
        if (Constants.f463a == null) {
            this.f487b.uncaughtException(thread, th);
            return;
        }
        this.f488c.notifyObservers(thread, th);
        if (!this.f486a) {
            this.f487b.uncaughtException(thread, th);
            Process.killProcess(Process.myPid());
            System.exit(10);
            return;
        }
        Intent intent = new Intent(PgyerProvider.f436a, PgyerActivityManager.getInstance().getCurrentActivity().getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("crash", true);
        ((AlarmManager) PgyerProvider.f436a.getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC, System.currentTimeMillis() + 1000, PendingIntent.getActivity(PgyerProvider.f436a, 0, intent, PendingIntent.FLAG_ONE_SHOT));
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
