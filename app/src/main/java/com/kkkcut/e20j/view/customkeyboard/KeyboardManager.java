package com.kkkcut.e20j.view.customkeyboard;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.kkkcut.e20j.us.R;
import com.kkkcut.e20j.view.customkeyboard.BaseKeyboard;
import com.liying.core.MachineInfo;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class KeyboardManager {
    public static final int ABC_KEYBOARD = 0;
    public static final int NUMBER_KEYBOARD = 1;
    protected static final String TAG = "KeyboardManager";
    private boolean isShow;
    private BaseKeyboard keyboard;
    protected Activity mContext;
    protected FrameLayout.LayoutParams mKeyboardContainerLayoutParams;
    protected KeyboardWithSearchView mKeyboardWithSearchView;
    protected ViewGroup mRootView;
    protected BaseKeyboard.DefaultKeyStyle mDefaultKeyStyle = new BaseKeyboard.DefaultKeyStyle();
    private int top = 0;
    private final View.OnFocusChangeListener editorFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.kkkcut.e20j.view.customkeyboard.KeyboardManager.2
        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(final View view, boolean z) {
            if ((view instanceof EditText) && z) {
                view.postDelayed(new Runnable() { // from class: com.kkkcut.e20j.view.customkeyboard.KeyboardManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (KeyboardManager.this.isShow) {
                            KeyboardManager.this.keyboard.setEditText((EditText) view);
                        } else {
                            KeyboardManager.this.showSoftKeyboard((EditText) view);
                        }
                    }
                }, 100L);
            }
        }
    };
    private final View.OnLayoutChangeListener mOnLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: com.kkkcut.e20j.view.customkeyboard.KeyboardManager.3
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            Object tag = KeyboardManager.this.mRootView.getTag(R.id.scroll_height_by_keyboard);
            int intValue = tag != null ? ((Integer) tag).intValue() : 0;
            if (KeyboardManager.this.mKeyboardWithSearchView.getVisibility() == 8) {
                KeyboardManager.this.mRootView.removeOnLayoutChangeListener(KeyboardManager.this.mOnLayoutChangeListener);
                if (intValue > 0) {
                    KeyboardManager.this.mRootView.getChildAt(0).scrollBy(0, intValue * (-1));
                    KeyboardManager.this.mRootView.setTag(R.id.scroll_height_by_keyboard, 0);
                    return;
                }
                return;
            }
            EditText editText = ((BaseKeyboard) KeyboardManager.this.mKeyboardWithSearchView.getBaseKeyboardView().getKeyboard()).getEditText();
            Rect rect = new Rect();
            KeyboardManager.this.mRootView.getWindowVisibleDisplayFrame(rect);
            int[] iArr = new int[2];
            editText.getLocationOnScreen(iArr);
            int height = iArr[1] + editText.getHeight() + editText.getPaddingTop() + editText.getPaddingBottom() + 1;
            Object tag2 = editText.getTag(R.id.anchor_view);
            View view2 = null;
            if (tag2 != null && (tag2 instanceof View)) {
                view2 = (View) tag2;
            }
            if (view2 != null) {
                int[] iArr2 = new int[2];
                view2.getLocationOnScreen(iArr2);
                height = iArr2[1] + view2.getHeight() + view2.getPaddingTop() + view2.getPaddingBottom() + 1;
            }
            int height2 = (height + KeyboardManager.this.mKeyboardWithSearchView.getHeight()) - rect.bottom;
            if (height2 > 0) {
                KeyboardManager.this.mRootView.getChildAt(0).scrollBy(0, height2);
                KeyboardManager.this.mRootView.setTag(R.id.scroll_height_by_keyboard, Integer.valueOf(intValue + height2));
                return;
            }
            int min = Math.min(intValue, Math.abs(height2));
            if (min > 0) {
                KeyboardManager.this.mRootView.getChildAt(0).scrollBy(0, min * (-1));
                KeyboardManager.this.mRootView.setTag(R.id.scroll_height_by_keyboard, Integer.valueOf(intValue - min));
            }
        }
    };

    public int getTop() {
        return this.top;
    }

    public KeyboardManager(Activity activity) {
        this.mContext = activity;
        if (activity instanceof Activity) {
            this.mRootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
            KeyboardWithSearchView keyboardWithSearchView = (KeyboardWithSearchView) LayoutInflater.from(this.mContext).inflate(R.layout.layout_keyboard_view, (ViewGroup) null);
            this.mKeyboardWithSearchView = keyboardWithSearchView;
            keyboardWithSearchView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kkkcut.e20j.view.customkeyboard.KeyboardManager.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    KeyboardManager keyboardManager = KeyboardManager.this;
                    keyboardManager.top = keyboardManager.mKeyboardWithSearchView.getTop();
                }
            });
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
            this.mKeyboardContainerLayoutParams = layoutParams;
            layoutParams.gravity = 80;
            return;
        }
        Log.e(TAG, "context must be activity");
    }

    public void bindToEditor(EditText editText, int i) {
        if (i != 0) {
            if (i == 1) {
                this.keyboard = new NumberKeyboard(this.mContext, R.xml.keyboard_number);
            }
        } else if (MachineInfo.isE20Us(this.mContext)) {
            this.keyboard = new ABCKeyboard(this.mContext, R.xml.keyboard_abc_us);
        } else {
            this.keyboard = new ABCKeyboard(this.mContext, R.xml.keyboard_abc);
        }
        hideSystemSoftKeyboard(editText);
        if (this.keyboard.getKeyStyle() == null) {
            this.keyboard.setKeyStyle(this.mDefaultKeyStyle);
        }
        editText.setOnFocusChangeListener(this.editorFocusChangeListener);
    }

    private void initKeyboard(BaseKeyboard baseKeyboard) {
        this.mKeyboardWithSearchView.getBaseKeyboardView().setKeyboard(baseKeyboard);
        this.mKeyboardWithSearchView.getBaseKeyboardView().setEnabled(true);
        this.mKeyboardWithSearchView.getBaseKeyboardView().setPreviewEnabled(false);
        this.mKeyboardWithSearchView.getBaseKeyboardView().setOnKeyboardActionListener(baseKeyboard);
    }

    public void showSoftKeyboard(EditText editText) {
        this.isShow = true;
        this.mRootView.addOnLayoutChangeListener(this.mOnLayoutChangeListener);
        this.keyboard.setEditText(editText);
        initKeyboard(this.keyboard);
        this.mKeyboardWithSearchView.getKeyboadViewContainer().setPadding(Utils.dipToPx(this.mContext, this.keyboard.getPadding().left), Utils.dipToPx(this.mContext, this.keyboard.getPadding().top), Utils.dipToPx(this.mContext, this.keyboard.getPadding().right), Utils.dipToPx(this.mContext, this.keyboard.getPadding().bottom));
        if (this.mRootView.indexOfChild(this.mKeyboardWithSearchView) == -1) {
            this.mRootView.addView(this.mKeyboardWithSearchView, this.mKeyboardContainerLayoutParams);
        } else {
            this.mKeyboardWithSearchView.setVisibility(0);
        }
        this.mKeyboardWithSearchView.setAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.bottom_in));
    }

    public void hideSoftKeyboard() {
        if (this.isShow) {
            this.isShow = false;
            this.mKeyboardWithSearchView.setVisibility(8);
            this.mKeyboardWithSearchView.setAnimation(AnimationUtils.loadAnimation(this.mContext, R.anim.bottom_out));
            ViewGroup viewGroup = this.mRootView;
            if (viewGroup != null) {
                viewGroup.clearFocus();
            }
            unBindEdit();
        }
    }

    public void unBindEdit() {
        BaseKeyboard baseKeyboard = this.keyboard;
        if (baseKeyboard != null) {
            baseKeyboard.setEditText(null);
        }
    }

    public void hideSystemSoftKeyboard(EditText editText) {
        HideKeyboard(editText, this.mContext);
    }

    public void HideKeyboard(EditText editText, Activity activity) {
        activity.getWindow().setSoftInputMode(3);
        int i = Build.VERSION.SDK_INT;
        String str = i >= 16 ? "setShowSoftInputOnFocus" : i >= 14 ? "setSoftInputShownOnFocus" : null;
        if (str == null) {
            editText.setInputType(0);
            return;
        }
        try {
            Method method = EditText.class.getMethod(str, Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(editText, false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            editText.setInputType(0);
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }
}
