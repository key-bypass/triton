package com.pgyersdk;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pgyersdk.PgyerProvider;
import com.pgyersdk.feedback.p017a.AlertDialogBuilderC2062m;
import com.pgyersdk.feedback.p017a.C2052c;
import com.pgyersdk.p012c.Strings;
import com.pgyersdk.utils.SharedPreferenesManager;
import com.pgyersdk.utils.Util;

import java.io.File;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PgyerDialogBuilder.java */
/* renamed from: com.pgyersdk.feedback.a.i */
/* loaded from: classes2.dex */
public class ViewOnTouchListenerC2058i implements View.OnTouchListener {

    /* renamed from: a */
    final /* synthetic */ AlertDialogBuilderC2062m f574a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewOnTouchListenerC2058i(AlertDialogBuilderC2062m alertDialogBuilderC2062m) {
        this.f574a = alertDialogBuilderC2062m;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        C2052c c2052c;
        C2052c c2052c2;
        long j;
        long j2;
        C2052c c2052c3;
        LinearLayout linearLayout;
        C2052c c2052c4;
        LinearLayout linearLayout2;
        if (!Util.m243d()) {
            Toast.makeText(PgyerProvider.f436a, Strings.m151a(1074), 0).show();
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f574a.m301p();
            c2052c = this.f574a.f594i;
            c2052c.setText(Strings.m151a(1073));
        } else if (action == 1 || action == 3) {
            this.f574a.f604s = new Date().getTime();
            c2052c2 = this.f574a.f594i;
            c2052c2.setText(Strings.m151a(1072));
            this.f574a.m302q();
            j = this.f574a.f604s;
            j2 = this.f574a.f603r;
            if (j - j2 >= 1000) {
                c2052c3 = this.f574a.f594i;
                c2052c3.setVisibility(8);
                linearLayout = this.f574a.f596k;
                linearLayout.setVisibility(0);
                File file = this.f574a.f602q;
                if (file != null) {
                    SharedPreferenesManager.m224a("voicefile", file.getAbsolutePath());
                    SharedPreferenesManager.m224a("voiceTime", this.f574a.f595j.getText().toString());
                }
            } else {
                Toast.makeText(PgyerProvider.f436a, Strings.m151a(1075), 0).show();
                AlertDialogBuilderC2062m alertDialogBuilderC2062m = this.f574a;
                alertDialogBuilderC2062m.f602q = null;
                c2052c4 = alertDialogBuilderC2062m.f594i;
                c2052c4.setVisibility(0);
                linearLayout2 = this.f574a.f596k;
                linearLayout2.setVisibility(8);
            }
            this.f574a.m274a(view);
        }
        return false;
    }
}
