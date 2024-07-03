package com.pgyersdk.p008b;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.pgyersdk.p008b.p009a.ActivityNotRunningException;
import com.pgyersdk.p008b.p009a.CaptureFailException;
import com.pgyersdk.p008b.p009a.IllegalScreenSizeException;
import com.pgyersdk.p008b.p010b.ScreenCaptureListener;
import com.pgyersdk.p008b.p011c.ScreenshotProvider;

/* compiled from: TracupCapture.java */
/* renamed from: com.pgyersdk.b.g */
/* loaded from: classes2.dex */
public class TracupCapture {

    /* renamed from: a */
    public static long f457a;

    /* renamed from: b */
    private static volatile TracupCapture f458b;

    /* renamed from: c */
    private static Handler f459c;

    /* renamed from: d */
    private ActvityReferenceManager f460d;

    /* renamed from: e */
    private ScreenshotProvider f461e;

    /* renamed from: f */
    public ScreenCaptureListener f462f;

    private TracupCapture(Activity activity) {
        ActvityReferenceManager actvityReferenceManager = new ActvityReferenceManager();
        this.f460d = actvityReferenceManager;
        actvityReferenceManager.m107a(activity);
        this.f461e = m134b();
    }

    /* renamed from: a */
    public static TracupCapture m133a(Activity activity) {
        if (f458b == null) {
            synchronized (TracupCapture.class) {
                if (f458b == null) {
                    f458b = new TracupCapture(activity);
                } else {
                    f458b.m135b(activity);
                }
            }
        } else {
            f458b.m135b(activity);
        }
        return f458b;
    }

    /* renamed from: b */
    private void m135b(Activity activity) {
        this.f460d.m107a(activity);
    }

    /* renamed from: b */
    private ScreenshotProvider m134b() {
        if (this.f460d.m106a() != null) {
            return new ScreenshotProvider();
        }
        throw new IllegalArgumentException("Your Acticity may be destroyed");
    }

    /* renamed from: b */
    public void m139b(View[] viewArr) {
        f457a = System.currentTimeMillis();
        Activity m106a = this.f460d.m106a();
        if (m106a != null) {
            ScreenCaptureListener screenCaptureListener = this.f462f;
            if (screenCaptureListener != null) {
                screenCaptureListener.mo110a();
            }
            try {
                this.f461e.m119a(m106a, viewArr, f459c);
                return;
            } catch (IllegalScreenSizeException e) {
                e.printStackTrace();
                this.f462f.onError(new CaptureFailException("Sorry,Capture is failed."));
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                this.f462f.onError(new CaptureFailException("Sorry,Capture is failed."));
                return;
            }
        }
        throw new ActivityNotRunningException("Is your activity running?");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public TracupCapture m136a(ScreenCaptureListener screenCaptureListener) {
        this.f462f = screenCaptureListener;
        return f458b;
    }

    /* renamed from: a */
    public void m137a() {
        m138a((View[]) null);
    }

    /* renamed from: a */
    public void m138a(View[] viewArr) {
        f459c = new HandlerC2020f(this, Looper.getMainLooper());
        m139b(viewArr);
    }
}
