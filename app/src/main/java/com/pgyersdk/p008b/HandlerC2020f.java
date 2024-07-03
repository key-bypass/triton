package com.pgyersdk.p008b;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pgyersdk.p008b.p009a.CaptureFailException;
import com.pgyersdk.p008b.p010b.ScreenCaptureListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TracupCapture.java */
/* renamed from: com.pgyersdk.b.f */
/* loaded from: classes2.dex */
public class HandlerC2020f extends Handler {

    /* renamed from: a */
    final /* synthetic */ TracupCapture f456a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2020f(TracupCapture tracupCapture, Looper looper) {
        super(looper);
        this.f456a = tracupCapture;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        Bitmap bitmap = (Bitmap) message.obj;
        if (bitmap != null) {
            ScreenCaptureListener screenCaptureListener = this.f456a.f462f;
            if (screenCaptureListener != null) {
                screenCaptureListener.onComplete(bitmap);
                return;
            }
            return;
        }
        ScreenCaptureListener screenCaptureListener2 = this.f456a.f462f;
        if (screenCaptureListener2 != null) {
            screenCaptureListener2.onError(new CaptureFailException("Sorry,Capture is failed."));
        }
    }
}
