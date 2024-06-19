package com.kkkcut.e20j.cockroach;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.kkkcut.e20j.cockroach.compat.ActivityKillerV15_V20;
import com.kkkcut.e20j.cockroach.compat.ActivityKillerV21_V23;
import com.kkkcut.e20j.cockroach.compat.ActivityKillerV24_V25;
import com.kkkcut.e20j.cockroach.compat.ActivityKillerV26;
import com.kkkcut.e20j.cockroach.compat.IActivityKiller;
import java.lang.Thread;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
public final class Cockroach {
    private static IActivityKiller sActivityKiller = null;
    private static ExceptionHandler sExceptionHandler = null;
    private static boolean sInstalled = false;
    private static boolean sIsSafeMode;

    private Cockroach() {
    }

    public static void install(ExceptionHandler exceptionHandler) {
        if (sInstalled) {
            return;
        }
        sInstalled = true;
        sExceptionHandler = exceptionHandler;
        initActivityKiller();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.kkkcut.e20j.cockroach.Cockroach.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                if (Cockroach.sExceptionHandler != null) {
                    Cockroach.sExceptionHandler.uncaughtExceptionHappened(thread, th);
                }
                if (thread == Looper.getMainLooper().getThread()) {
                    Cockroach.isChoreographerException(th);
                    Cockroach.safeMode();
                }
            }
        });
    }

    private static void initActivityKiller() {
        if (Build.VERSION.SDK_INT >= 26) {
            sActivityKiller = new ActivityKillerV26();
        } else if (Build.VERSION.SDK_INT == 25 || Build.VERSION.SDK_INT == 24) {
            sActivityKiller = new ActivityKillerV24_V25();
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23) {
            sActivityKiller = new ActivityKillerV21_V23();
        } else if (Build.VERSION.SDK_INT >= 15 && Build.VERSION.SDK_INT <= 20) {
            sActivityKiller = new ActivityKillerV15_V20();
        } else if (Build.VERSION.SDK_INT < 15) {
            sActivityKiller = new ActivityKillerV15_V20();
        }
        try {
            hookmH();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void hookmH() throws Exception {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Object invoke = cls.getDeclaredMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mH");
        declaredField.setAccessible(true);
        final Handler handler = (Handler) declaredField.get(invoke);
        Field declaredField2 = Handler.class.getDeclaredField("mCallback");
        declaredField2.setAccessible(true);
        declaredField2.set(handler, new Handler.Callback() { // from class: com.kkkcut.e20j.cockroach.Cockroach.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 104) {
                    try {
                        handler.handleMessage(message);
                    } catch (Throwable th) {
                        Cockroach.sActivityKiller.finishStopActivity(message);
                        Cockroach.notifyException(th);
                    }
                    return true;
                }
                if (i == 107) {
                    try {
                        handler.handleMessage(message);
                    } catch (Throwable th2) {
                        Cockroach.sActivityKiller.finishResumeActivity(message);
                        Cockroach.notifyException(th2);
                    }
                    return true;
                }
                if (i != 109) {
                    switch (i) {
                        case 100:
                            try {
                                handler.handleMessage(message);
                            } catch (Throwable th3) {
                                Cockroach.sActivityKiller.finishLaunchActivity(message);
                                Cockroach.notifyException(th3);
                            }
                            return true;
                        case 101:
                            try {
                                handler.handleMessage(message);
                            } catch (Throwable th4) {
                                Cockroach.sActivityKiller.finishPauseActivity(message);
                                Cockroach.notifyException(th4);
                            }
                            return true;
                        case 102:
                            try {
                                handler.handleMessage(message);
                            } catch (Throwable th5) {
                                Cockroach.sActivityKiller.finishPauseActivity(message);
                                Cockroach.notifyException(th5);
                            }
                            return true;
                        default:
                            return false;
                    }
                }
                try {
                    handler.handleMessage(message);
                } catch (Throwable th6) {
                    Cockroach.notifyException(th6);
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyException(Throwable th) {
        if (sExceptionHandler == null) {
            return;
        }
        if (isSafeMode()) {
            sExceptionHandler.bandageExceptionHappened(th);
        } else {
            sExceptionHandler.uncaughtExceptionHappened(Looper.getMainLooper().getThread(), th);
            safeMode();
        }
    }

    public static boolean isSafeMode() {
        return sIsSafeMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void safeMode() {
        sIsSafeMode = true;
        ExceptionHandler exceptionHandler = sExceptionHandler;
        if (exceptionHandler != null) {
            exceptionHandler.enterSafeMode();
        }
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable th) {
                isChoreographerException(th);
                if (sExceptionHandler != null) {
                    sExceptionHandler.bandageExceptionHappened(th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void isChoreographerException(Throwable th) {
        StackTraceElement[] stackTrace;
        if (th == null || sExceptionHandler == null || (stackTrace = th.getStackTrace()) == null) {
            return;
        }
        int length = stackTrace.length;
        while (true) {
            length--;
            if (length <= -1 || stackTrace.length - length > 20) {
                return;
            }
            StackTraceElement stackTraceElement = stackTrace[length];
            if ("android.view.Choreographer".equals(stackTraceElement.getClassName()) && "Choreographer.java".equals(stackTraceElement.getFileName()) && "doFrame".equals(stackTraceElement.getMethodName())) {
                sExceptionHandler.mayBeBlackScreen(th);
                return;
            }
        }
    }
}
