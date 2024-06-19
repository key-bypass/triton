package com.pgyersdk.p016f;

import android.os.AsyncTask;
import android.os.Build;

/* compiled from: AsyncTaskUtils.java */
/* renamed from: com.pgyersdk.f.a */
/* loaded from: classes2.dex */
public class C2036a {
    /* renamed from: a */
    public static void m194a(AsyncTask<Void, ?, ?> asyncTask) {
        if (Build.VERSION.SDK_INT <= 12) {
            asyncTask.execute(new Void[0]);
        } else {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }
}
