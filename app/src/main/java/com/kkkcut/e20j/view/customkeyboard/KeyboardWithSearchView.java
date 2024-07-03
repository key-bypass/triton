package com.kkkcut.e20j.view.customkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public class KeyboardWithSearchView extends LinearLayout {
    private static final int MAX_VISIBLE_SIZE = 3;
    private BaseKeyboardView mBaseKeyboardView;
    private Context mContext;
    private LinearLayout mKeyboadViewContainer;

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public KeyboardWithSearchView(Context context) {
        super(context);
        init(context);
    }

    public KeyboardWithSearchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public KeyboardWithSearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public KeyboardWithSearchView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseKeyboardView getBaseKeyboardView() {
        return this.mBaseKeyboardView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LinearLayout getKeyboadViewContainer() {
        return this.mKeyboadViewContainer;
    }

    private void init(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_recycler_keyboard_view, (ViewGroup) this, true);
        this.mBaseKeyboardView = (BaseKeyboardView) inflate.findViewById(R.id.keyboard_view);
        this.mKeyboadViewContainer = (LinearLayout) inflate.findViewById(R.id.keyboard_container);
    }
}
