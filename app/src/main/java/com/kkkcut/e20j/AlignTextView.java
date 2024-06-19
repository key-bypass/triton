package com.kkkcut.e20j;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes.dex */
public class AlignTextView extends AppCompatTextView {
    public AlignTextView(Context context) {
        super(context);
    }

    public AlignTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Layout layout = getLayout();
        if (layout == null) {
            return;
        }
        if (layout.getLineCount() < 2) {
            super.onDraw(canvas);
            return;
        }
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        measureText(getMeasuredWidth(), getText(), (int) ((((int) Math.ceil(fontMetrics.descent - fontMetrics.ascent)) * layout.getSpacingMultiplier()) + layout.getSpacingAdd()), canvas);
    }

    public void measureText(int i, CharSequence charSequence, int i2, Canvas canvas) {
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.drawableState = getDrawableState();
        float desiredWidth = StaticLayout.getDesiredWidth(charSequence, paint);
        int length = charSequence.length();
        float textSize = paint.getTextSize();
        if (desiredWidth < i) {
            canvas.drawText(charSequence, 0, length, 0.0f, textSize, paint);
        } else {
            drawMultiLine(canvas, i, charSequence, paint, i2);
        }
    }

    private void drawMultiLine(Canvas canvas, int i, CharSequence charSequence, TextPaint textPaint, int i2) {
        int length = charSequence.length();
        float textSize = textPaint.getTextSize();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int i6 = i3 + 1;
            if (StaticLayout.getDesiredWidth(charSequence.subSequence(i4, i6).toString().trim(), textPaint) > i) {
                String trim = charSequence.subSequence(i4, i3).toString().trim();
                canvas.drawText((CharSequence) trim, 0, trim.length(), 0.0f, textSize + (i2 * i5), (Paint) textPaint);
                i5++;
                i4 = i3;
            }
            if (i3 == length - 1) {
                CharSequence subSequence = charSequence.subSequence(i4, length);
                canvas.drawText(subSequence, 0, subSequence.length(), 0.0f, textSize + (i2 * i5), textPaint);
                i5++;
                i4 = i3;
            }
            i3 = i6;
        }
    }

    public CharSequence getTwoLine(int i, CharSequence charSequence, TextPaint textPaint) {
        int length = charSequence.length();
        float desiredWidth = StaticLayout.getDesiredWidth("...", textPaint);
        for (int i2 = 0; i2 < length; i2++) {
            if (StaticLayout.getDesiredWidth(charSequence.subSequence(0, i2), textPaint) + desiredWidth > i) {
                return ((Object) charSequence.subSequence(0, i2 - 1)) + "...";
            }
        }
        return charSequence;
    }

    public CharSequence getOneLine(int i, CharSequence charSequence, TextPaint textPaint) {
        int length = charSequence.length();
        CharSequence charSequence2 = null;
        for (int i2 = 0; i2 < length; i2++) {
            charSequence2 = charSequence.subSequence(0, i2);
            float desiredWidth = StaticLayout.getDesiredWidth(charSequence2, textPaint);
            float f = i;
            if (desiredWidth >= f) {
                int i3 = i2 - 1;
                return desiredWidth - f < StaticLayout.getDesiredWidth(charSequence.subSequence(i3, i2), textPaint) ? charSequence.subSequence(0, i3) : charSequence2;
            }
        }
        return charSequence2;
    }
}
