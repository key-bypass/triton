package com.kkkcut.e20j.androidquick.task;

import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class Task<R> implements Runnable {
    private static final String TAG = "Task";
    private AtomicBoolean mCanceledAtomic = new AtomicBoolean(false);
    private AtomicReference<Thread> mTaskThread = new AtomicReference<>();

    public abstract R doInBackground() throws InterruptedException;

    public void onCancel() {
    }

    public void onFail(Throwable th) {
    }

    public abstract void onSuccess(R r);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.mCanceledAtomic.set(true);
        Thread thread = this.mTaskThread.get();
        if (thread != null) {
            Log.d(TAG, "Task cancel: " + thread.getName());
            thread.interrupt();
        }
        TaskScheduler.runOnUIThread(new Runnable() { // from class: com.kkkcut.e20j.androidquick.task.Task.1
            @Override // java.lang.Runnable
            public void run() {
                Task.this.onCancel();
            }
        });
    }

    public boolean isCanceled() {
        return this.mCanceledAtomic.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Log.d(TAG, "Task : " + Thread.currentThread().getName());
            this.mTaskThread.compareAndSet(null, Thread.currentThread());
            this.mCanceledAtomic.set(false);
            final R doInBackground = doInBackground();
            TaskScheduler.runOnUIThread(new Runnable() { // from class: com.kkkcut.e20j.androidquick.task.Task.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    if (Task.this.isCanceled()) {
                        return;
                    }
                    Task.this.onSuccess(doInBackground);
                }
            });
        } catch (Throwable th) {
            Log.e(TAG, "handle background Task  error " + th);
            TaskScheduler.runOnUIThread(new Runnable() { // from class: com.kkkcut.e20j.androidquick.task.Task.3
                @Override // java.lang.Runnable
                public void run() {
                    if (Task.this.isCanceled()) {
                        return;
                    }
                    Task.this.onFail(th);
                }
            });
        }
    }
}
