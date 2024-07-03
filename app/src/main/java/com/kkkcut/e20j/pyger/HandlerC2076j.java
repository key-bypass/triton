package com.kkkcut.e20j.pyger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.pgyersdk.PgyerProvider;
import com.pgyersdk.p012c.Strings;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.j */
/* loaded from: classes2.dex */
public class HandlerC2076j extends Handler {

    /* renamed from: a */
    final /* synthetic */ C2077k f676a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2076j(C2077k c2077k, Looper looper) {
        super(looper);
        this.f676a = c2077k;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 20001) {
            this.f676a.m342g();
            Toast.makeText(PgyerProvider.f436a, Strings.m151a(1058), 0).show();
        } else if (i == 20002) {
            Toast.makeText(PgyerProvider.f436a, Strings.m151a(1059), 0).show();
        }
        this.f676a.m350c();
    }
}
