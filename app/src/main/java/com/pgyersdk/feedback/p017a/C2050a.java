package com.pgyersdk.feedback.p017a;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.ImageButton;
import android.widget.ImageView;

/* compiled from: BarButton.java */
/* renamed from: com.pgyersdk.feedback.a.a */
/* loaded from: classes2.dex */
public class C2050a extends ImageButton {

    /* renamed from: a */
    public static String f552a = "#383737";

    /* renamed from: b */
    private Context f553b;

    /* renamed from: c */
    private String f554c;

    /* renamed from: d */
    private ColorDrawable f555d;

    /* renamed from: e */
    private ColorDrawable f556e;

    public C2050a(Context context) {
        super(context);
        this.f554c = "#ffffff";
        setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        m259a();
    }

    /* renamed from: a */
    private void m259a() {
        this.f555d = new ColorDrawable(0);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor(f552a));
        this.f556e = colorDrawable;
        Context context = this.f553b;
        ColorDrawable colorDrawable2 = this.f555d;
        setBackgroundDrawable(m258a(context, colorDrawable2, colorDrawable, colorDrawable, colorDrawable2));
    }

    /* renamed from: a */
    public static StateListDrawable m258a(Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, drawable2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, drawable);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, drawable4);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }
}
