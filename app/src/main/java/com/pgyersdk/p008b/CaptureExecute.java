package com.pgyersdk.p008b;

import android.app.Activity;
import android.net.Uri;
import java.lang.ref.WeakReference;

/* compiled from: CaptureExecute.java */
/* renamed from: com.pgyersdk.b.d */
/* loaded from: classes2.dex */
public class CaptureExecute {

    /* compiled from: CaptureExecute.java */
    /* renamed from: com.pgyersdk.b.d$a */
    /* loaded from: classes2.dex */
    public interface a {
        /* renamed from: a */
        void mo128a(Uri uri);

        /* renamed from: a */
        void mo129a(Throwable th);
    }

    /* renamed from: a */
    public static synchronized void m127a(Activity activity, a aVar) {
        synchronized (CaptureExecute.class) {
            if (activity != null) {
                if (!activity.isFinishing()) {
                    WeakReference weakReference = new WeakReference(activity);
                    TracupCapture.m133a((Activity) weakReference.get()).m136a(new CaptureExecuteListener(activity, aVar, weakReference)).m137a();
                }
            }
        }
    }
}
