package com.kkkcut.e20j.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class ThemeUtils {
    public static int getResId(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        return typedValue.resourceId;
    }

    public static int getColor(Context context, int i) {
        return context.getResources().getColor(getResId(context, i));
    }

    public static Drawable getImg(Context context, int i) {
        return context.getDrawable(getResId(context, i));
    }
}
