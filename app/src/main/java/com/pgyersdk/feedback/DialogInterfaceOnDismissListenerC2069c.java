package com.pgyersdk.feedback;

import android.content.DialogInterface;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeedbackAd.java */
/* renamed from: com.pgyersdk.feedback.c */
/* loaded from: classes2.dex */
public class DialogInterfaceOnDismissListenerC2069c implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f638a;

    /* renamed from: b */
    final /* synthetic */ ViewOnClickListenerC2070d f639b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnDismissListenerC2069c(ViewOnClickListenerC2070d viewOnClickListenerC2070d, AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f639b = viewOnClickListenerC2070d;
        this.f638a = alertDialogBuilderC2062m;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f638a.m305a();
        this.f639b.f654n.setVisibility(8);
        this.f639b.m327a(this.f638a);
    }
}
