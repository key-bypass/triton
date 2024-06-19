package com.pgyersdk.feedback;

import android.content.DialogInterface;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerFeedbackManagerDelegate.java */
/* renamed from: com.pgyersdk.feedback.h */
/* loaded from: classes2.dex */
public class DialogInterfaceOnClickListenerC2074h implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f672a;

    /* renamed from: b */
    final /* synthetic */ C2077k f673b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterfaceOnClickListenerC2074h(C2077k c2077k, AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
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
        /*
            r2 = this;
            com.pgyersdk.feedback.a.m r4 = r2.f672a
            java.io.File r4 = r4.f602q
            com.pgyersdk.feedback.C2077k.m334a(r4)
            com.pgyersdk.feedback.a.m r4 = r2.f672a
            android.widget.EditText r4 = r4.m307c()
            android.text.Editable r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            boolean r4 = com.pgyersdk.p016f.C2046k.m235a(r4)
            if (r4 == 0) goto L39
            java.io.File r4 = com.pgyersdk.feedback.C2077k.m340b()
            if (r4 != 0) goto L39
            com.pgyersdk.feedback.a.m r4 = r2.f672a
            android.widget.EditText r4 = r4.m308d()
            android.text.Editable r4 = r4.getText()
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.trim()
            boolean r4 = com.pgyersdk.p016f.C2046k.m235a(r4)
            if (r4 != 0) goto L49
        L39:
            android.content.Context r4 = com.pgyersdk.PgyerProvider.f436a
            r0 = 1056(0x420, float:1.48E-42)
            java.lang.String r0 = com.pgyersdk.p012c.C2023b.m151a(r0)
            r1 = 0
            android.widget.Toast r4 = android.widget.Toast.makeText(r4, r0, r1)
            r4.show()
        L49:
            java.lang.String r4 = com.pgyersdk.feedback.C2077k.m335a()
            if (r4 == 0) goto L56
            java.lang.String r4 = com.pgyersdk.feedback.C2077k.m335a()
            com.pgyersdk.p008b.C2019e.m132a(r4)
        L56:
            r3.dismiss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pgyersdk.feedback.DialogInterfaceOnClickListenerC2074h.onClick(android.content.DialogInterface, int):void");
    }
}
