package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;
import com.kkkcut.e20j.view.customkeyboard.BaseKeyboard;

/* loaded from: classes.dex */
public class ABCKeyboard extends BaseKeyboard {
    public static final int DEFAULT_ABC_XML_LAYOUT = 2132082689;
    public static final int DEFAULT_ABC_XML_LAYOUT_US = 2132082690;

    @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard
    public boolean handleSpecialKey(int i) {
        return false;
    }

    public ABCKeyboard(Context context, int i) {
        super(context, i);
    }

    public ABCKeyboard(Context context, int i, int i2, int i3, int i4) {
        super(context, i, i2, i3, i4);
    }

    public ABCKeyboard(Context context, int i, int i2) {
        super(context, i, i2);
    }

    public ABCKeyboard(Context context, int i, CharSequence charSequence, int i2, int i3) {
        super(context, i, charSequence, i2, i3);
    }

    @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard
    public BaseKeyboard.Padding getPadding() {
        return new BaseKeyboard.Padding(3, 0, 3, 0);
    }
}
