package com.pgyersdk;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: classes2.dex */
public class PgyerActivityManager {

    /* renamed from: a */
    private static volatile PgyerActivityManager f432a;

    /* renamed from: b */
    private Activity f433b;

    /* renamed from: c */
    private C1999a f434c;

    /* renamed from: com.pgyersdk.PgyerActivityManager$a */
    /* loaded from: classes2.dex */
    private class C1999a implements Application.ActivityLifecycleCallbacks {
        private C1999a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            PgyerActivityManager.this.f433b = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            PgyerActivityManager.this.f433b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    private PgyerActivityManager(Application application) {
        C1999a c1999a = new C1999a();
        this.f434c = c1999a;
        application.registerActivityLifecycleCallbacks(c1999a);
    }

    public static PgyerActivityManager getInstance() {
        if (f432a != null) {
            return f432a;
        }
        throw new Error("PGER SDK init PgyerActivityManager is error.");
    }

    public static boolean isSuccessSetInstance() {
        return f432a != null;
    }

    public static void set(Application application) {
        synchronized (PgyerActivityManager.class) {
            if (f432a != null) {
                f432a.f433b = null;
                f432a.f434c = null;
            }
            f432a = new PgyerActivityManager(application);
        }
    }

    public Activity getCurrentActivity() {
        return this.f433b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m95a(Application application) {
        if (f432a == null) {
            synchronized (PgyerActivityManager.class) {
                if (f432a == null) {
                    f432a = new PgyerActivityManager(application);
                }
            }
        }
    }
}
