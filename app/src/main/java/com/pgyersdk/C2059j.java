package com.pgyersdk;

import android.media.MediaPlayer;

import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.feedback.p017a.C2055f;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.j */
/* loaded from: classes2.dex */
public class C2059j implements MediaPlayer.OnCompletionListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f575a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2059j(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f575a = alertDialogBuilderC2062m;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        C2055f pgyerDeleteButton;
        this.f575a.f609x.cancel();
        this.f575a.f610y.cancel();
        this.f575a.f595j.m317a(3);
        pgyerDeleteButton = this.f575a.f597l;
        pgyerDeleteButton.setEnabled(true);
    }
}
