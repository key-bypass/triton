package com.pgyersdk.feedback;

import android.content.DialogInterface;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.i */
/* loaded from: classes2.dex */
public class DialogInterfaceOnDismissListenerC2075i implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f674a;

    /* renamed from: b */
    final /* synthetic */ C2077k f675b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnDismissListenerC2075i(C2077k c2077k, AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f675b = c2077k;
        this.f674a = alertDialogBuilderC2062m;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f675b.m350c();
        this.f675b.m337a(this.f674a);
        dialogInterface.dismiss();
        this.f674a.m305a();
    }
}
