package com.pgyersdk.feedback.p017a;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.widget.Button;

/* compiled from: PgyerButton.java */
/* renamed from: com.pgyersdk.feedback.a.c */
/* loaded from: classes2.dex */
public class C2052c extends Button {
    public C2052c(Context context) {
        super(context);
        float applyDimension = (int) TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        float[] fArr = {applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(Color.parseColor("#f2f2f2"));
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new RoundRectShape(fArr, null, null));
        shapeDrawable2.getPaint().setColor(Color.parseColor("#e0e0e0"));
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        setBackgroundDrawable(m264a(context, shapeDrawable, shapeDrawable2, shapeDrawable2, shapeDrawable));
        setTextSize(16.0f);
    }

    /* renamed from: a */
    public static StateListDrawable m264a(Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, drawable2);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }
}
