package com.pgyersdk.update;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDownloadListener.java */
/* renamed from: com.pgyersdk.update.c */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2084c implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ PgyerDownloadListener f716a;

    public DialogInterfaceOnClickListenerC2084c(PgyerDownloadListener pgyerDownloadListener) {
        this.f716a = pgyerDownloadListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.f716a.m382a();
    }
}
