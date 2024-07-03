package com.pgyersdk.feedback;

import android.media.MediaPlayer;
import android.net.Uri;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.feedback.PgyerFeedbackManagerDelegate;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerShakeManager.java */
/* renamed from: com.pgyersdk.feedback.m */
/* loaded from: classes2.dex */
public class PgyerShakeManager implements ShakeListener.a {

    /* renamed from: a */
    private static ShakeListener f685a;

    /* renamed from: b */
    private static PgyerFeedbackManagerDelegate.a f686b;

    /* renamed from: c */
    private int f687c;

    /* renamed from: d */
    private MediaPlayer f688d = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PgyerShakeManager(int i) {
        this.f687c = i;
        m358c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m360d() {
        MediaPlayer mediaPlayer = this.f688d;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m361e() {
        MediaPlayer create = MediaPlayer.create(PgyerProvider.f436a, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
        this.f688d = create;
        synchronized (create) {
            this.f688d.setVolume(10.0f, 10.0f);
            this.f688d.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m362f() {
        ShakeListener shakeListener = f685a;
        if (shakeListener != null) {
            shakeListener.m367b();
        }
    }

    /* renamed from: c */
    private void m358c() {
        f686b = new C2078l(this);
    }

    @Override // com.pgyersdk.feedback.C2080n.a
    /* renamed from: a */
    public void mo363a() {
        PgyerFeedbackManager.getInstance().m257b().m348a(f686b);
        PgyerFeedbackManager.getInstance().m257b().m352e();
    }

    /* renamed from: b */
    public void m364b() {
        if (f685a != null) {
            m362f();
        }
        f685a = new ShakeListener(PgyerProvider.f436a);
        ShakeListener.f689a = this.f687c;
        f685a.m366a(this);
    }
}
