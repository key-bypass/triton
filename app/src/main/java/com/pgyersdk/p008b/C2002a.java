package com.pgyersdk.p008b;

import android.app.Activity;
import android.os.Build;
import java.lang.ref.WeakReference;

/* compiled from: ActvityReferenceManager.java */
/* renamed from: com.pgyersdk.b.a */
/* loaded from: classes2.dex */
public final class C2002a {

    /* renamed from: a */
    private WeakReference<Activity> f440a;

    /* renamed from: b */
    private boolean m105b(Activity activity) {
        if (activity == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return (activity.isFinishing() || activity.isDestroyed()) ? false : true;
        }
        return !activity.isFinishing();
    }

    /* renamed from: a */
    public void m107a(Activity activity) {
        this.f440a = new WeakReference<>(activity);
    }

    /* renamed from: a */
    public Activity m106a() {
        WeakReference<Activity> weakReference = this.f440a;
        if (weakReference == null) {
            return null;
        }
        Activity activity = weakReference.get();
        if (m105b(activity)) {
            return activity;
        }
        return null;
    }
}
