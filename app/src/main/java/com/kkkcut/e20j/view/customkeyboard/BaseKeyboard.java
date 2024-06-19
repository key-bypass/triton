package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;

/* loaded from: classes.dex */
public abstract class BaseKeyboard extends Keyboard implements KeyboardView.OnKeyboardActionListener {
    private static final String TAG = "BaseKeyboard";
    protected Context mContext;
    private EditText mEditText;
    private KeyStyle mKeyStyle;

    /* loaded from: classes.dex */
    public interface KeyStyle {
        Drawable getKeyBackound(Keyboard.Key key);

        CharSequence getKeyLabel(Keyboard.Key key);

        Integer getKeyTextColor(Keyboard.Key key);

        Float getKeyTextSize(Keyboard.Key key);
    }

    public abstract boolean handleSpecialKey(int i);

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onPress(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onRelease(int i) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onText(CharSequence charSequence) {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeDown() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeLeft() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeRight() {
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void swipeUp() {
    }

    public BaseKeyboard(Context context, int i) {
        super(context, i);
        this.mContext = context;
    }

    public BaseKeyboard(Context context, int i, int i2, int i3, int i4) {
        super(context, i, i2, i3, i4);
        this.mContext = context;
    }

    public BaseKeyboard(Context context, int i, int i2) {
        super(context, i, i2);
        this.mContext = context;
    }

    public BaseKeyboard(Context context, int i, CharSequence charSequence, int i2, int i3) {
        super(context, i, charSequence, i2, i3);
        this.mContext = context;
    }

    public void setEditText(EditText editText) {
        this.mEditText = editText;
    }

    public void setKeyStyle(KeyStyle keyStyle) {
        this.mKeyStyle = keyStyle;
    }

    public EditText getEditText() {
        return this.mEditText;
    }

    public KeyStyle getKeyStyle() {
        return this.mKeyStyle;
    }

    public int getKeyCode(int i) {
        return this.mContext.getResources().getInteger(i);
    }

    @Override // android.inputmethodservice.KeyboardView.OnKeyboardActionListener
    public void onKey(int i, int[] iArr) {
        Log.d(TAG, "onKey() called with: primaryCode = [" + i + "], keyCodes = [" + iArr + "]");
        EditText editText = this.mEditText;
        if (editText == null || !editText.hasFocus() || handleSpecialKey(i)) {
            return;
        }
        Editable text = this.mEditText.getText();
        int selectionStart = this.mEditText.getSelectionStart();
        int selectionEnd = this.mEditText.getSelectionEnd();
        if (selectionEnd > selectionStart) {
            text.delete(selectionStart, selectionEnd);
        }
        if (i == -5) {
            if (TextUtils.isEmpty(text) || selectionStart <= 0) {
                return;
            }
            text.delete(selectionStart - 1, selectionStart);
            return;
        }
        text.insert(selectionStart, Character.toString((char) i));
    }

    public Padding getPadding() {
        return new Padding(0, 0, 0, 0);
    }

    /* loaded from: classes.dex */
    public static class DefaultKeyStyle implements KeyStyle {
        @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard.KeyStyle
        public Integer getKeyTextColor(Keyboard.Key key) {
            return null;
        }

        @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard.KeyStyle
        public Float getKeyTextSize(Keyboard.Key key) {
            return null;
        }

        @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard.KeyStyle
        public Drawable getKeyBackound(Keyboard.Key key) {
            return key.iconPreview;
        }

        @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard.KeyStyle
        public CharSequence getKeyLabel(Keyboard.Key key) {
            return key.label;
        }
    }

    /* loaded from: classes.dex */
    public static class Padding {
        int bottom;
        int left;
        int right;
        int top;

        public Padding(int i, int i2, int i3, int i4) {
            this.top = i;
            this.left = i2;
            this.bottom = i3;
            this.right = i4;
        }
    }

    public float convertSpToPixels(Context context, float f) {
        return TypedValue.applyDimension(2, f, context.getResources().getDisplayMetrics());
    }
}
