package com.pgyersdk;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.TranslateAnimation;
import com.pgyersdk.feedback.ViewOnClickListenerC2070d;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeedbackAd.java */
/* renamed from: com.pgyersdk.feedback.b */
/* loaded from: classes2.dex */
public class ViewOnClickListenerC2068b implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ViewOnClickListenerC2070d f637a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewOnClickListenerC2068b(ViewOnClickListenerC2070d viewOnClickListenerC2070d) {
        this.f637a = viewOnClickListenerC2070d;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f637a.f647g.setColor(((ViewOnClickListenerC2070d.a) view.getTag()).f667a);
        ViewOnClickListenerC2070d viewOnClickListenerC2070d = this.f637a;
        ObjectAnimator objectAnimator = viewOnClickListenerC2070d.f660t;
        if (objectAnimator != null) {
            objectAnimator.start();
        } else {
            TranslateAnimation translateAnimation = viewOnClickListenerC2070d.f662v;
            if (translateAnimation != null) {
                viewOnClickListenerC2070d.f648h.startAnimation(translateAnimation);
            }
        }
        this.f637a.f663w = false;
        this.f637a.m326a(view);
    }
}
