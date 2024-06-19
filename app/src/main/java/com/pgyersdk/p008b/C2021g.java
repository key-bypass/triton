package com.pgyersdk.p008b;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.pgyersdk.p008b.p009a.C2003a;
import com.pgyersdk.p008b.p009a.C2004b;
import com.pgyersdk.p008b.p009a.C2005c;
import com.pgyersdk.p008b.p010b.InterfaceC2008a;
import com.pgyersdk.p008b.p011c.C2013c;

/* compiled from: TracupCapture.java */
/* renamed from: com.pgyersdk.b.g */
/* loaded from: classes2.dex */
public class C2021g {

    /* renamed from: a */
    public static long f457a;

    /* renamed from: b */
    private static volatile C2021g f458b;

    /* renamed from: c */
    private static Handler f459c;

    /* renamed from: d */
    private C2002a f460d;

    /* renamed from: e */
    private C2013c f461e;

    /* renamed from: f */
    public InterfaceC2008a f462f;

    private C2021g(Activity activity) {
        C2002a c2002a = new C2002a();
        this.f460d = c2002a;
        c2002a.m107a(activity);
        this.f461e = m134b();
    }

    /* renamed from: a */
    public static C2021g m133a(Activity activity) {
        if (f458b == null) {
            synchronized (C2021g.class) {
                if (f458b == null) {
                    f458b = new C2021g(activity);
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
    private C2013c m134b() {
        if (this.f460d.m106a() != null) {
            return new C2013c();
        }
        throw new IllegalArgumentException("Your Acticity may be destroyed");
    }

    /* renamed from: b */
    public void m139b(View[] viewArr) {
        f457a = System.currentTimeMillis();
        Activity m106a = this.f460d.m106a();
        if (m106a != null) {
            InterfaceC2008a interfaceC2008a = this.f462f;
            if (interfaceC2008a != null) {
                interfaceC2008a.mo110a();
            }
            try {
                this.f461e.m119a(m106a, viewArr, f459c);
                return;
            } catch (C2005c e) {
                e.printStackTrace();
                this.f462f.mo112a(new C2004b("Sorry,Capture is failed."));
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                this.f462f.mo112a(new C2004b("Sorry,Capture is failed."));
                return;
            }
        }
        throw new C2003a("Is your activity running?");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public C2021g m136a(InterfaceC2008a interfaceC2008a) {
        this.f462f = interfaceC2008a;
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
