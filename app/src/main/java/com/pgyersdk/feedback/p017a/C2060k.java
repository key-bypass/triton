package com.pgyersdk.feedback.p017a;

import android.media.MediaPlayer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.k */
/* loaded from: classes2.dex */
public class C2060k implements MediaPlayer.OnPreparedListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f576a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2060k(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f576a = alertDialogBuilderC2062m;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        C2055f c2055f;
        c2055f = this.f576a.f597l;
        c2055f.setEnabled(false);
    }
}
