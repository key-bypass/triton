package com.pgyersdk.update;

import android.content.DialogInterface;

import com.kkkcut.e20j.pyger.update.PgyerDownloadListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDownloadListener.java */
/* renamed from: com.pgyersdk.update.d */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2085d implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ PgyerDownloadListener f717a;

    public DialogInterfaceOnClickListenerC2085d(PgyerDownloadListener pgyerDownloadListener) {
        this.f717a = pgyerDownloadListener;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        PgyUpdateManager.downLoadApk(AsyncTaskC2083b.f712a);
    }
}
