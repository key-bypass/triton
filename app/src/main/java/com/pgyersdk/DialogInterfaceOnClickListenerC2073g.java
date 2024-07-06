package com.pgyersdk;

import android.content.DialogInterface;

import com.pgyersdk.feedback.PgyerFeedbackManagerDelegate;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.g */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2073g implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f670a;

    /* renamed from: b */
    final /* synthetic */ PgyerFeedbackManagerDelegate f671b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnClickListenerC2073g(PgyerFeedbackManagerDelegate pgyerFeedbackManagerDelegate, AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f671b = pgyerFeedbackManagerDelegate;
        this.f670a = alertDialogBuilderC2062m;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        String str;
        File file;
        File unused = PgyerFeedbackManagerDelegate.f678b = this.f670a.f602q;
        PgyerFeedbackManagerDelegate pgyerFeedbackManagerDelegate = this.f671b;
        String obj = this.f670a.m308d().getText().toString();
        String obj2 = this.f670a.m307c().getText().toString();
        str = PgyerFeedbackManagerDelegate.f677a;
        file = PgyerFeedbackManagerDelegate.f678b;
        pgyerFeedbackManagerDelegate.m349a(obj, obj2, str, file, Boolean.valueOf(this.f670a.m306b().isChecked()));
        dialogInterface.dismiss();
    }
}
