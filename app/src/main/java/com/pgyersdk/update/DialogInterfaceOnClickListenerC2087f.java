package com.pgyersdk.update;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerUpdateListener.java */
/* renamed from: com.pgyersdk.update.f */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2087f implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ PgyerUpdateListener f723a;

    public DialogInterfaceOnClickListenerC2087f(PgyerUpdateListener pgyerUpdateListener) {
        this.f723a = pgyerUpdateListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
