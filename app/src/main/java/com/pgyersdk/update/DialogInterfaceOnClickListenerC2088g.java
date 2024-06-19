package com.pgyersdk.update;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerUpdateListener.java */
/* renamed from: com.pgyersdk.update.g */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2088g implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ String f724a;

    /* renamed from: b */
    final /* synthetic */ C2090i f725b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnClickListenerC2088g(C2090i c2090i, String str) {
        this.f725b = c2090i;
        this.f724a = str;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        AlertDialog alertDialog;
        try {
            PgyUpdateManager.downLoadApk(this.f724a);
            alertDialog = C2090i.f727a;
            alertDialog.dismiss();
        } catch (Exception unused) {
        }
    }
}
