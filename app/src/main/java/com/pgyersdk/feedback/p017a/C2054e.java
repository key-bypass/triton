package com.pgyersdk.feedback.p017a;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.LinearLayout;
import com.pgyersdk.p016f.C2037b;

/* compiled from: PgyerColorPikcerBg.java */
/* renamed from: com.pgyersdk.feedback.a.e */
/* loaded from: classes2.dex */
public class C2054e extends LinearLayout {

    /* renamed from: a */
    private Context f569a;

    /* renamed from: b */
    private Paint f570b;

    public C2054e(Context context, int i) {
        super(context);
        this.f569a = context;
        this.f570b = new Paint();
        setBottomBarColor(i);
    }

    public void setBottomBarColor(int i) {
        this.f570b.setColor(i);
        this.f570b.setStyle(Paint.Style.FILL);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new C2053d(this));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        setBackgroundDrawable(m266a(shapeDrawable, shapeDrawable, shapeDrawable, shapeDrawable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m267a(Context context, Canvas canvas) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(getWidth(), 0.0f);
        path.lineTo(getWidth(), getHeight() - C2037b.m195a(this.f569a, 5.0f));
        path.lineTo(0.0f, getHeight() - C2037b.m195a(this.f569a, 5.0f));
        canvas.drawPath(path, this.f570b);
        int m195a = (context.getResources().getDisplayMetrics().widthPixels / 6) - C2037b.m195a(context, 10.0f);
        Path path2 = new Path();
        float f = m195a;
        path2.moveTo(f, getHeight() - C2037b.m195a(this.f569a, 5.0f));
        path2.lineTo(C2037b.m195a(this.f569a, 5.0f) + m195a, getHeight());
        path2.lineTo(m195a + C2037b.m195a(this.f569a, 10.0f), getHeight() - C2037b.m195a(this.f569a, 5.0f));
        path2.lineTo(f, getHeight() - C2037b.m195a(this.f569a, 5.0f));
        canvas.drawPath(path2, this.f570b);
    }

    /* renamed from: a */
    public static StateListDrawable m266a(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
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
