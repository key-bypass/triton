package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;
import android.text.Editable;
import com.kkkcut.e20j.androidquick.tool.FileUtil;
import com.kkkcut.e20j.view.customkeyboard.BaseKeyboard;

/* loaded from: classes.dex */
public class NumberKeyboard extends BaseKeyboard {
    public static final int DEFAULT_NUMBER_XML_LAYOUT = 2132082691;
    public ActionDoneClickListener mActionDoneClickListener;

    /* loaded from: classes.dex */
    public interface ActionDoneClickListener {
        void onActionDone(CharSequence charSequence);
    }

    public NumberKeyboard(Context context, int i) {
        super(context, i);
    }

    public NumberKeyboard(Context context, int i, int i2, int i3, int i4) {
        super(context, i, i2, i3, i4);
    }

    public NumberKeyboard(Context context, int i, int i2) {
        super(context, i, i2);
    }

    public NumberKeyboard(Context context, int i, CharSequence charSequence, int i2, int i3) {
        super(context, i, charSequence, i2, i3);
    }

    public void setActionDoneClickListener(ActionDoneClickListener actionDoneClickListener) {
        this.mActionDoneClickListener = actionDoneClickListener;
    }

    @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard
    public boolean handleSpecialKey(int i) {
        Editable text = getEditText().getText();
        int selectionStart = getEditText().getSelectionStart();
        if (i != 46) {
            return false;
        }
        if (text.toString().contains(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            return true;
        }
        if (!text.toString().startsWith(FileUtil.FILE_EXTENSION_SEPARATOR)) {
            text.insert(selectionStart, Character.toString((char) i));
            return true;
        }
        text.insert(selectionStart, "0" + Character.toString((char) i));
        return true;
    }

    @Override // com.kkkcut.e20j.view.customkeyboard.BaseKeyboard
    public BaseKeyboard.Padding getPadding() {
        return new BaseKeyboard.Padding(3, 0, 3, 0);
    }
}
