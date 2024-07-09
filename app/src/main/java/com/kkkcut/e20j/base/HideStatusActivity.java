package com.kkkcut.e20j.base;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.kkkcut.e20j.ui.activity.LookPicActivity;
import com.cutting.machine.MachineInfo;
import com.kkkcut.e20j.us.R;

/* loaded from: classes.dex */
public abstract class HideStatusActivity extends BaseActivity {
    private boolean wasOpened;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (this instanceof LookPicActivity) {
            setTheme(R.style.TranslucentTheme);
        } else if (MachineInfo.isE9Standard(this)) {
            setTheme(R.style.E9Theme);
        } else {
            setTheme(R.style.E20Theme);
        }
        super.onCreate(bundle);
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(5894);
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.kkkcut.e20j.base.HideStatusActivity.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                if ((i & 4) == 0) {
                    decorView.setSystemUiVisibility(5894);
                }
            }
        });
        if (decorView == null) {
            return;
        }
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.kkkcut.e20j.base.HideStatusActivity.2
            private final Rect r = new Rect();
            private final int visibleThreshold;

            {
                this.visibleThreshold = Math.round(HideStatusActivity.dip2px(HideStatusActivity.this, 100.0f));
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(this.r);
                boolean z = decorView.getRootView().getHeight() - this.r.height() > this.visibleThreshold;
                if (z == HideStatusActivity.this.wasOpened) {
                    return;
                }
                HideStatusActivity.this.wasOpened = z;
                if (HideStatusActivity.this.wasOpened) {
                    return;
                }
                decorView.setSystemUiVisibility(5894);
            }
        });
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.app.Activity, android.view.Window.Callback, me.yokeyword.fragmentation.ISupportActivity
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (isShouldHideKeyBord(currentFocus, motionEvent)) {
                hideSoftInput(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    protected boolean isShouldHideKeyBord(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    private void hideSoftInput(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }
}
