package com.pgyersdk.p008b.p011c;

import android.view.View;
import android.view.WindowManager;

/* compiled from: RootViewInfo.java */
/* renamed from: com.pgyersdk.b.c.b */
/* loaded from: classes2.dex */
public class RootViewInfo {

    /* renamed from: a */
    private final View f445a;

    /* renamed from: b */
    private final WindowManager.LayoutParams f446b;

    /* renamed from: c */
    private final int f447c;

    /* renamed from: d */
    private final int f448d;

    public RootViewInfo(View view, WindowManager.LayoutParams layoutParams) {
        this.f445a = view;
        this.f446b = layoutParams;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.f447c = iArr[0];
        this.f448d = iArr[1];
    }

    /* renamed from: a */
    public WindowManager.LayoutParams m115a() {
        return this.f446b;
    }

    /* renamed from: b */
    public int m116b() {
        return this.f447c;
    }

    /* renamed from: c */
    public int m117c() {
        return this.f448d;
    }

    /* renamed from: d */
    public View m118d() {
        return this.f445a;
    }
}
