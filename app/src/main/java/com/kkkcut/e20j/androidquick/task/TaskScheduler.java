package com.kkkcut.e20j.androidquick.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class TaskScheduler {
    private static final int CPU_COUNT;
    private static final long KEEP_ALIVE = 60;
    private static final int MAXIMUM_POOL_SIZE;
    private static final String TAG = "TaskScheduler";
    private static final ThreadFactory TIME_OUT_THREAD_FACTORY;
    private static volatile TaskScheduler sTaskScheduler;
    private Handler mMainHandler = new SafeDispatchHandler(Looper.getMainLooper());
    private Map<String, Handler> mHandlerMap = new ConcurrentHashMap();
    private Executor mParallelExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
    private ExecutorService mTimeOutExecutor = new ThreadPoolExecutor(0, MAXIMUM_POOL_SIZE, 60, TimeUnit.SECONDS, new SynchronousQueue(), TIME_OUT_THREAD_FACTORY);

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        MAXIMUM_POOL_SIZE = (availableProcessors * 2) + 1;
        TIME_OUT_THREAD_FACTORY = new ThreadFactory() { // from class: com.kkkcut.e20j.androidquick.task.TaskScheduler.2
            private final AtomicInteger mCount = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "TaskScheduler timeoutThread #" + this.mCount.getAndIncrement());
                thread.setPriority(10);
                return thread;
            }
        };
    }

    private static TaskScheduler getInstance() {
        if (sTaskScheduler == null) {
            synchronized (TaskScheduler.class) {
                if (sTaskScheduler == null) {
                    sTaskScheduler = new TaskScheduler();
                }
            }
        }
        return sTaskScheduler;
    }

    private TaskScheduler() {
    }

    public static Handler provideHandler(String str) {
        if (getInstance().mHandlerMap.containsKey(str)) {
            return getInstance().mHandlerMap.get(str);
        }
        HandlerThread handlerThread = new HandlerThread(str, 10);
        handlerThread.start();
        SafeDispatchHandler safeDispatchHandler = new SafeDispatchHandler(handlerThread.getLooper());
        getInstance().mHandlerMap.put(str, safeDispatchHandler);
        return safeDispatchHandler;
    }

    public static void execute(Runnable runnable) {
        getInstance().mParallelExecutor.execute(runnable);
    }

    public static <R> void execute(Task<R> task) {
        getInstance().mParallelExecutor.execute(task);
    }

    public static void cancelTask(Task task) {
        if (task != null) {
            task.cancel();
        }
    }

    public static <R> void executeTimeOutTask(final long j, final Task<R> task) {
        final Future<?> submit = getInstance().mTimeOutExecutor.submit(task);
        getInstance().mTimeOutExecutor.execute(new Runnable() { // from class: com.kkkcut.e20j.androidquick.task.TaskScheduler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    submit.get(j, TimeUnit.MILLISECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException unused) {
                    TaskScheduler.runOnUIThread(new Runnable() { // from class: com.kkkcut.e20j.androidquick.task.TaskScheduler.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (task.isCanceled()) {
                                return;
                            }
                            task.cancel();
                        }
                    });
                }
            }
        });
    }

    public static void runOnUIThread(Runnable runnable) {
        getInstance().mMainHandler.post(runnable);
    }

    public static void removeHandlerCallback(String str, Runnable runnable) {
        if (isMainThread()) {
            getInstance().mMainHandler.removeCallbacks(runnable);
        } else if (getInstance().mHandlerMap.get(str) != null) {
            getInstance().mHandlerMap.get(str).removeCallbacks(runnable);
        }
    }

    public static Handler getMainHandler() {
        return getInstance().mMainHandler;
    }

    public static void runOnUIThread(Runnable runnable, long j) {
        getInstance().mMainHandler.postDelayed(runnable, j);
    }

    public static void removeUICallback(Runnable runnable) {
        removeHandlerCallback("main", runnable);
    }

    public static boolean isMainThread() {
        return Thread.currentThread() == getInstance().mMainHandler.getLooper().getThread();
    }
}
