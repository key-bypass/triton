package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.kkkcut.e20j.view.customkeyboard.BaseKeyboard;
import java.util.List;

/* loaded from: classes.dex */
public class BaseKeyboardView extends KeyboardView {
    private static final String TAG = "BaseKeyboardView";
    private Rect rClipRegion;
    private Keyboard.Key rInvalidatedKey;
    private Drawable rKeyBackground;
    private int rKeyTextColor;
    private int rKeyTextSize;
    private int rLabelTextSize;

    public BaseKeyboardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public BaseKeyboardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    public BaseKeyboardView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.rKeyBackground = (Drawable) ReflectionUtils.getFieldValue(this, "mKeyBackground");
        this.rLabelTextSize = ((Integer) ReflectionUtils.getFieldValue(this, "mLabelTextSize")).intValue();
        this.rKeyTextSize = ((Integer) ReflectionUtils.getFieldValue(this, "mKeyTextSize")).intValue();
        this.rKeyTextColor = ((Integer) ReflectionUtils.getFieldValue(this, "mKeyTextColor")).intValue();
    }

    @Override // android.inputmethodservice.KeyboardView, android.view.View
    public void onDraw(Canvas canvas) {
        if (getKeyboard() == null || !(getKeyboard() instanceof BaseKeyboard) || ((BaseKeyboard) getKeyboard()).getKeyStyle() == null) {
            Log.e(TAG, "");
            super.onDraw(canvas);
        } else {
            this.rClipRegion = (Rect) ReflectionUtils.getFieldValue(this, "mClipRegion");
            this.rInvalidatedKey = (Keyboard.Key) ReflectionUtils.getFieldValue(this, "mInvalidatedKey");
            super.onDraw(canvas);
            onRefreshKey(canvas);
        }
    }

    private void onRefreshKey(Canvas canvas) {
        boolean z;
        Keyboard.Key key;
        Paint paint;
        Paint paint2 = (Paint) ReflectionUtils.getFieldValue(this, "mPaint");
        Rect rect = (Rect) ReflectionUtils.getFieldValue(this, "mPadding");
        paint2.setColor(this.rKeyTextColor);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        Rect rect2 = this.rClipRegion;
        Keyboard.Key key2 = this.rInvalidatedKey;
        boolean z2 = key2 != null && canvas.getClipBounds(rect2) && (key2.x + paddingLeft) - 1 <= rect2.left && (key2.y + paddingTop) - 1 <= rect2.top && ((key2.x + key2.width) + paddingLeft) + 1 >= rect2.right && ((key2.y + key2.height) + paddingTop) + 1 >= rect2.bottom;
        ((BaseKeyboard) getKeyboard()).getEditText();
        BaseKeyboard.KeyStyle keyStyle = ((BaseKeyboard) getKeyboard()).getKeyStyle();
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        int size = keys.size();
        int i = 0;
        int r7 = 0;
        int r8 = 0;
        while (i < size) {
            Keyboard.Key key3 = keys.get(i);
            if (!z2 || key2 == key3) {
                Drawable keyBackound = keyStyle.getKeyBackound(key3);
                Drawable drawable = keyBackound == null ? this.rKeyBackground : keyBackound;
                drawable.setState(key3.getCurrentDrawableState());
                CharSequence keyLabel = keyStyle.getKeyLabel(key3);
                if (keyLabel == null) {
                    keyLabel = key3.label;
                }
                String charSequence = keyLabel == null ? null : adjustCase(keyLabel).toString();
                Rect bounds = drawable.getBounds();
                z = z2;
                key = key2;
                if (key3.width != bounds.right || key3.height != bounds.bottom) {
                    drawable.setBounds(0, 0, key3.width, key3.height);
                }
                canvas.translate(key3.x + paddingLeft, key3.y + paddingTop);
                drawable.draw(canvas);
                if (charSequence != null) {
                    Float keyTextSize = keyStyle.getKeyTextSize(key3);
                    if (keyTextSize != null) {
                        paint2.setTextSize(keyTextSize.floatValue());
                        paint2.setTypeface(Typeface.DEFAULT);
                    } else if (charSequence.length() > 1 && key3.codes.length < 2) {
                        paint2.setTextSize(this.rLabelTextSize);
                        paint2.setTypeface(Typeface.DEFAULT);
                    } else {
                        paint2.setTextSize(this.rKeyTextSize);
                        paint2.setTypeface(Typeface.DEFAULT);
                    }
                    Integer keyTextColor = keyStyle.getKeyTextColor(key3);
                    if (keyTextColor != null) {
                        paint2.setColor(keyTextColor.intValue());
                    } else {
                        paint2.setColor(this.rKeyTextColor);
                    }
                    canvas.drawText(charSequence, (((key3.width - rect.left) - rect.right) / 2) + rect.left, (((key3.height - rect.top) - rect.bottom) / 2) + ((paint2.getTextSize() - paint2.descent()) / 2.0f) + rect.top, paint2);
                } else if (key3.icon != null) {
                    canvas.translate(((((key3.width - rect.left) - rect.right) - key3.icon.getIntrinsicWidth()) / 2) + rect.left, ((((key3.height - rect.top) - rect.bottom) - key3.icon.getIntrinsicHeight()) / 2) + rect.top);
                    paint = paint2;
                    key3.icon.setBounds(0, 0, key3.icon.getIntrinsicWidth(), key3.icon.getIntrinsicHeight());
                    key3.icon.draw(canvas);
                    canvas.translate(-r7, -r8);
                    canvas.translate((-key3.x) - paddingLeft, (-key3.y) - paddingTop);
                }
                paint = paint2;
                canvas.translate((-key3.x) - paddingLeft, (-key3.y) - paddingTop);
            } else {
                paint = paint2;
                z = z2;
                key = key2;
            }
            i++;
            z2 = z;
            key2 = key;
            paint2 = paint;
        }
        this.rInvalidatedKey = null;
    }

    private CharSequence adjustCase(CharSequence charSequence) {
        return (!getKeyboard().isShifted() || charSequence == null || charSequence.length() >= 3 || !Character.isLowerCase(charSequence.charAt(0))) ? charSequence : charSequence.toString().toUpperCase();
    }

    @Override // android.inputmethodservice.KeyboardView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }
}