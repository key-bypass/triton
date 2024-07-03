package com.kkkcut.e20j.pyger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;

import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;

import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.g */
/* loaded from: classes2.dex */
public class HandlerC2056g extends Handler {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f572a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC2056g(AlertDialogBuilderC2062m alertDialogBuilderC2062m, Looper looper) {
        super(looper);
        this.f572a = alertDialogBuilderC2062m;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        long j;
        long j2;
        int i;
        long j3;
        long j4;
        int i2;
        int i3;
        long j5;
        long j6;
        Handler handler;
        Handler handler2;
        int i4;
        long j7;
        long j8;
        Handler handler3;
        C2052c c2052c;
        LinearLayout linearLayout;
        C2052c c2052c2;
        int i5 = message.what;
        if (i5 == 20003) {
            this.f572a.f595j.m317a((Integer.valueOf(message.obj.toString()).intValue() % 3) + 1);
            return;
        }
        if (i5 != 20005) {
            if (i5 != 20006) {
                return;
            }
            this.f572a.f605t = new Date().getTime();
            i4 = this.f572a.f599n;
            j7 = this.f572a.f605t;
            j8 = this.f572a.f603r;
            int i6 = ((int) (i4 - (j7 - j8))) / 1000;
            if (i6 <= 0) {
                this.f572a.m302q();
                c2052c = this.f572a.f594i;
                c2052c.setVisibility(8);
                linearLayout = this.f572a.f596k;
                linearLayout.setVisibility(0);
                AlertDialogBuilderC2062m alertDialogBuilderC2062m = this.f572a;
                c2052c2 = alertDialogBuilderC2062m.f594i;
                alertDialogBuilderC2062m.m274a(c2052c2);
            } else {
                handler3 = this.f572a.f587E;
                handler3.sendEmptyMessageDelayed(20006, 1000L);
            }
            this.f572a.f608w.m310a(-1, i6);
            return;
        }
        int intValue = Integer.valueOf(message.obj.toString()).intValue();
        this.f572a.f605t = new Date().getTime();
        StringBuilder sb = new StringBuilder();
        j = this.f572a.f605t;
        j2 = this.f572a.f603r;
        sb.append((j - j2) / 1000);
        sb.append(" ");
        i = this.f572a.f599n;
        sb.append((i - 10000) / 1000);
        Log.i("duration", sb.toString());
        j3 = this.f572a.f605t;
        j4 = this.f572a.f603r;
        long j9 = (j3 - j4) / 1000;
        i2 = this.f572a.f599n;
        if (j9 >= (i2 - 10000) / 1000) {
            AlertDialogBuilderC2062m alertDialogBuilderC2062m2 = this.f572a;
            C2063n c2063n = alertDialogBuilderC2062m2.f608w;
            i3 = alertDialogBuilderC2062m2.f599n;
            j5 = this.f572a.f605t;
            j6 = this.f572a.f603r;
            c2063n.m310a(-1, ((int) (i3 - (j5 - j6))) / 1000);
            handler = this.f572a.f587E;
            handler.removeMessages(20005);
            handler2 = this.f572a.f587E;
            handler2.sendEmptyMessageDelayed(20006, 1000L);
            return;
        }
        this.f572a.f608w.m310a(intValue, -1);
        this.f572a.m303r();
    }
}
