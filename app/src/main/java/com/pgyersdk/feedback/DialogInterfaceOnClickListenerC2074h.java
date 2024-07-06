package com.pgyersdk.feedback;

import android.content.DialogInterface;
import android.widget.Toast;

import com.pgyersdk.PgyerProvider;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.p008b.FileManager;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.StringUtil;

import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.h */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2074h implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f672a;

    /* renamed from: b */
    final /* synthetic */ PgyerFeedbackManagerDelegate f673b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnClickListenerC2074h(PgyerFeedbackManagerDelegate c2077k, AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f673b = c2077k;
        this.f672a = alertDialogBuilderC2062m;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0037, code lost:
    
        if (com.pgyersdk.p016f.C2046k.m235a(r2.f672a.m308d().getText().toString().trim()) != false) goto L9;
     */
    @Override // android.content.DialogInterface.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onClick(android.content.DialogInterface r3, int r4) {
        String str;
        String str2;
        File file;
        File unused = PgyerFeedbackManagerDelegate.f678b = this.f672a.f602q;
        if (StringUtil.isEmpty(this.f672a.m307c().getText().toString())) {
            file = PgyerFeedbackManagerDelegate.f678b;
            if (file == null) {
            }
        }
        Toast.makeText(PgyerProvider.f436a, Strings.m151a(1056), 0).show();
        str = PgyerFeedbackManagerDelegate.f677a;
        if (str != null) {
            str2 = PgyerFeedbackManagerDelegate.f677a;
            FileManager.m132a(str2);
        }
        r3.dismiss();
    }
}
