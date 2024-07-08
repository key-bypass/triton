package com.kkkcut.e20j.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.gyf.barlibrary.ImmersionBar.Companion.with
import com.kkkcut.e20j.androidquick.tool.StringUtil
import com.kkkcut.e20j.base.BaseFActivity
import com.kkkcut.e20j.ui.dialog.LoadingDialog
import com.kkkcut.e20j.view.customkeyboard.KeyboardManager

/* loaded from: classes.dex */
abstract class BaseCustomKeyBoardActivity : BaseFActivity() {
    private var keyboardManager: KeyboardManager? = null
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(this).fitsSystemWindows(true)
    }

    fun bindToEditor(editText: EditText?, i: Int) {
        if (this.keyboardManager == null) {
            this.keyboardManager = KeyboardManager(this)
        }
        keyboardManager!!.bindToEditor(editText, i)
    }

    fun hideSoftKeyboard() {
        keyboardManager!!.hideSoftKeyboard()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (isSoftShowing) {
            hideInput()
            return true
        }
        if (this.keyboardManager != null) {
            if (ev!!.y > keyboardManager!!.top) {
                return super.dispatchTouchEvent(ev)
            }
            if (ev.action == 0) {
                hideSoftKeyboard()
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private val isSoftShowing: Boolean
        get() {
            val height = window.decorView.height
            val rect = Rect()
            window.decorView.getWindowVisibleDisplayFrame(rect)
            return (height * 2) / 3 > rect.bottom
        }

    private fun hideInput() {
        val inputMethodManager = getSystemService("input_method") as InputMethodManager
        val peekDecorView = window.peekDecorView()
        if (peekDecorView != null) {
            inputMethodManager.hideSoftInputFromWindow(peekDecorView.windowToken, 0)
        }
    }

    open fun showLoadingDialog(str: String?) {
        if (this.loadingDialog == null) {
            this.loadingDialog = LoadingDialog(this)
        }
        if (!StringUtil.isEmpty(str)) {
            loadingDialog!!.setTip(str)
        }
        loadingDialog!!.setCancelable(false)
        if (!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
        Handler().postDelayed(this::dismissLoadingDialog, 15000L)
    }

    open fun dismissLoadingDialog() {
        val loadingDialog = this.loadingDialog
        if (loadingDialog == null || !loadingDialog.isShowing) {
            return
        }
        this.loadingDialog!!.dismiss()
    }
}
