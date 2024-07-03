package com.pgyersdk.update;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerUpdateListener.java */
/* renamed from: com.pgyersdk.update.h */
/* loaded from: classes2.dex */
public class DialogInterfaceOnDismissListenerC2089h implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    final /* synthetic */ PgyerUpdateListener f726a;

    public DialogInterfaceOnDismissListenerC2089h(PgyerUpdateListener pgyerUpdateListener) {
        this.f726a = pgyerUpdateListener;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f726a.m385b();
    }
}
