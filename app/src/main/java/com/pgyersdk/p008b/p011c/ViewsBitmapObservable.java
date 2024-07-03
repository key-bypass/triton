package com.pgyersdk.p008b.p011c;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.pgyersdk.p008b.p009a.IllegalScreenSizeException;
import com.pgyersdk.utils.LogUtils;

/* compiled from: ViewsBitmapObservable.java */
/* renamed from: com.pgyersdk.b.c.g */
/* loaded from: classes2.dex */
public final class ViewsBitmapObservable {
    /* renamed from: a */
    public static void m126a(Activity activity, View[] viewArr, Handler handler) {
        activity.runOnUiThread(new RunnableC2016f(activity, viewArr, handler));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ViewsBitmapObservable.java */
    /* renamed from: com.pgyersdk.b.c.f */
    /* loaded from: classes2.dex */
    public static class RunnableC2016f implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Activity f453a;

        /* renamed from: b */
        final /* synthetic */ View[] f454b;

        /* renamed from: c */
        final /* synthetic */ Handler f455c;

        public RunnableC2016f(Activity activity, View[] viewArr, Handler handler) {
            this.f453a = activity;
            this.f454b = viewArr;
            this.f455c = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Bitmap m120a = ScreenshotTaker.m120a(this.f453a, this.f454b);
                if (m120a != null) {
                    Message message = new Message();
                    message.obj = m120a;
                    message.what = 0;
                    this.f455c.sendMessage(message);
                    return;
                }
                throw new IllegalScreenSizeException();
            } catch (IllegalScreenSizeException unused) {
                LogUtils.m218b("PgyerSDK", "Get screen shot failed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
