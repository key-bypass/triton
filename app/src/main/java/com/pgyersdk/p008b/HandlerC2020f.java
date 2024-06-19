package com.pgyersdk.p008b;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pgyersdk.p008b.p009a.C2004b;
import com.pgyersdk.p008b.p010b.InterfaceC2008a;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TracupCapture.java */
/* renamed from: com.pgyersdk.b.f */
/* loaded from: classes2.dex */
public class HandlerC2020f extends Handler {

    /* renamed from: a */
    final /* synthetic */ C2021g f456a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2020f(C2021g c2021g, Looper looper) {
        super(looper);
        this.f456a = c2021g;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        Bitmap bitmap = (Bitmap) message.obj;
        if (bitmap != null) {
            InterfaceC2008a interfaceC2008a = this.f456a.f462f;
            if (interfaceC2008a != null) {
                interfaceC2008a.mo111a(bitmap);
                return;
            }
            return;
        }
        InterfaceC2008a interfaceC2008a2 = this.f456a.f462f;
        if (interfaceC2008a2 != null) {
            interfaceC2008a2.mo112a(new C2004b("Sorry,Capture is failed."));
        }
    }
}
