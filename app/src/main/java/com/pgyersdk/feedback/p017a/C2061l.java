package com.pgyersdk.feedback.p017a;

import android.os.Handler;
import android.os.Message;
import java.util.TimerTask;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.l */
/* loaded from: classes2.dex */
public class C2061l extends TimerTask {

    /* renamed from: a */
    int f577a = 0;

    /* renamed from: b */
    final /* synthetic */ AlertDialogBuilderC2062m f578b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2061l(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f578b = alertDialogBuilderC2062m;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        Handler handler;
        Message message = new Message();
        message.what = 20003;
        message.obj = Integer.valueOf(this.f577a);
        handler = this.f578b.f587E;
        handler.sendMessage(message);
        this.f577a++;
    }
}
