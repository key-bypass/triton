package com.pgyersdk;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.TypedValue;
import android.widget.ImageButton;
import androidx.core.view.ViewCompat;
import com.pgyersdk.utils.ConvertUtil;

/* compiled from: PgyerDeleteButton.java */
/* renamed from: com.pgyersdk.feedback.a.f */
/* loaded from: classes2.dex */
public class PgyerDeleteButton extends androidx.appcompat.widget.AppCompatImageButton {

    /* renamed from: a */
    private Context f571a;

    public PgyerDeleteButton(Context context) {
        super(context);
        this.f571a = context;
        TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(Color.parseColor("#f2f2f2"));
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        shapeDrawable.setBounds(0, 0, ConvertUtil.m195a(context, 30.0f), ConvertUtil.m195a(context, 30.0f));
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new OvalShape());
        shapeDrawable2.getPaint().setColor(Color.parseColor("#e0e0e0"));
        shapeDrawable2.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        shapeDrawable2.setBounds(0, 0, ConvertUtil.m195a(context, 30.0f), ConvertUtil.m195a(context, 30.0f));
        setBackgroundDrawable(m269a(context, shapeDrawable, shapeDrawable2, shapeDrawable2, shapeDrawable));
    }

    /* renamed from: a */
    public static StateListDrawable m269a(Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, drawable2);
        stateListDrawable.addState(new int[]{R.attr.state_enabled, R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_enabled}, drawable);
        stateListDrawable.addState(new int[]{R.attr.state_focused}, drawable3);
        stateListDrawable.addState(new int[]{R.attr.state_window_focused}, drawable4);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStrokeWidth(2.0f);
        paint.getStyle();
        paint.setStyle(Paint.Style.STROKE);
        float m195a = ConvertUtil.m195a(this.f571a, 10.0f);
        canvas.drawLine(m195a, m195a, getHeight() - m195a, getHeight() - m195a, paint);
        canvas.drawLine(getHeight() - m195a, m195a, m195a, getHeight() - m195a, paint);
    }
}
