package com.pgyersdk.update;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDownloadListener.java */
/* renamed from: com.pgyersdk.update.d */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2085d implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ C2086e f717a;

    public DialogInterfaceOnClickListenerC2085d(C2086e c2086e) {
        this.f717a = c2086e;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        PgyUpdateManager.downLoadApk(AsyncTaskC2083b.f712a);
    }
}
