package com.kkkcut.e20j.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.gyf.barlibrary.ImmersionBar;
import com.kkkcut.e20j.androidquick.tool.StringUtil;
import com.kkkcut.e20j.base.BaseFActivity;
import com.kkkcut.e20j.ui.dialog.LoadingDialog;
import com.kkkcut.e20j.view.customkeyboard.KeyboardManager;

/* loaded from: classes.dex */
public abstract class BaseCustomKeyBoardActivity extends BaseFActivity {
    private KeyboardManager keyboardManager;
    private LoadingDialog loadingDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kkkcut.e20j.base.BaseFActivity, com.kkkcut.e20j.base.HideStatusActivity, com.kkkcut.e20j.androidquick.ui.base.QuickActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ImmersionBar.with(this).fitsSystemWindows(true);
    }

    public void bindToEditor(EditText editText, int i) {
        if (this.keyboardManager == null) {
            this.keyboardManager = new KeyboardManager(this);
        }
        this.keyboardManager.bindToEditor(editText, i);
    }

    public void hideSoftKeyboard() {
        this.keyboardManager.hideSoftKeyboard();
    }

    @Override // com.kkkcut.e20j.base.BaseFActivity, com.kkkcut.e20j.base.HideStatusActivity, android.app.Activity, android.view.Window.Callback, me.yokeyword.fragmentation.ISupportActivity
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isSoftShowing()) {
            hideInput();
            return true;
        }
        if (this.keyboardManager != null) {
            if (motionEvent.getY() > this.keyboardManager.getTop()) {
                return super.dispatchTouchEvent(motionEvent);
            }
            if (motionEvent.getAction() == 0) {
                hideSoftKeyboard();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private boolean isSoftShowing() {
        int height = getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return (height * 2) / 3 > rect.bottom;
    }

    protected void hideInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View peekDecorView = getWindow().peekDecorView();
        if (peekDecorView != null) {
            inputMethodManager.hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLoadingDialog(String str) {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this);
        }
        if (!StringUtil.isEmpty(str)) {
            this.loadingDialog.setTip(str);
        }
        this.loadingDialog.setCancelable(false);
        if (!this.loadingDialog.isShowing()) {
            this.loadingDialog.show();
        }
        new Handler().postDelayed(new Runnable() { // from class: com.kkkcut.e20j.ui.activity.BaseCustomKeyBoardActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseCustomKeyBoardActivity.this.dissmissLoadingDialog();
            }
        }, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dissmissLoadingDialog() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }
}
