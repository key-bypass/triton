package com.pgyersdk.p008b.p011c;

import java.nio.IntBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: ScreenshotTaker.java */
/* renamed from: com.pgyersdk.b.c.d */
/* loaded from: classes2.dex */
class RunnableC2014d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f449a;

    /* renamed from: b */
    final /* synthetic */ int f450b;

    /* renamed from: c */
    final /* synthetic */ IntBuffer f451c;

    /* renamed from: d */
    final /* synthetic */ CountDownLatch f452d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2014d(int i, int i2, IntBuffer intBuffer, CountDownLatch countDownLatch) {
        this.f449a = i;
        this.f450b = i2;
        this.f451c = intBuffer;
        this.f452d = countDownLatch;
    }

    @Override // java.lang.Runnable
    public void run() {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        egl10.eglWaitGL();
        GL10 gl10 = (GL10) egl10.eglGetCurrentContext().getGL();
        gl10.glFinish();
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gl10.glReadPixels(0, 0, this.f449a, this.f450b + 0, 6408, 5121, this.f451c);
        this.f452d.countDown();
    }
}
