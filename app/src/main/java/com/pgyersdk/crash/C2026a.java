package com.pgyersdk.crash;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.app.NotificationCompat;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.C2022a;
import java.lang.Thread;
import net.sqlcipher.database.SQLiteDatabase;

/* compiled from: ExceptionHandler.java */
/* renamed from: com.pgyersdk.crash.a */
/* loaded from: classes2.dex */
public class C2026a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private boolean f486a = false;

    /* renamed from: b */
    private Thread.UncaughtExceptionHandler f487b;

    /* renamed from: c */
    C2030e f488c;

    public C2026a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, C2030e c2030e) {
        this.f487b = uncaughtExceptionHandler;
        this.f488c = c2030e;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.f486a = PgyCrashManager.isIsIgnoreDefaultHander();
        if (C2022a.f463a == null) {
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
        intent.setFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra("crash", true);
        ((AlarmManager) PgyerProvider.f436a.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 1000, PendingIntent.getActivity(PgyerProvider.f436a, 0, intent, BasicMeasure.EXACTLY));
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
