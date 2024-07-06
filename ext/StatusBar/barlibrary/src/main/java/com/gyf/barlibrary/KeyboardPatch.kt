package com.gyf.barlibrary

import android.R
import android.app.Activity
import android.app.Dialog
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * 解决底部输入框和软键盘的问题
 * Created by geyifeng on 2017/5/17.
 */
class KeyboardPatch {
    private var mActivity: Activity
    private var mWindow: Window?
    private var mDecorView: View
    private var mContentView: View
    private var mFlag = false
    private var mBarParams: BarParams?

    private constructor(
        activity: Activity, contentView: View = (activity.window.decorView.findViewById<View>(
            R.id.content
        ) as FrameLayout).getChildAt(0)
    ) : this(activity, null, "", contentView)

    private constructor(
        activity: Activity, dialog: Dialog?, tag: String, contentView: View? = dialog!!.window!!
            .findViewById(R.id.content)
    ) {
        this.mActivity = activity
        this.mWindow = if (dialog != null) dialog.window else activity.window
        this.mDecorView = activity.window.decorView
        this.mContentView = contentView ?: mWindow!!.decorView.findViewById(R.id.content)
        this.mBarParams =
            if (dialog != null) ImmersionBar.Companion.with(activity, dialog, tag).barParams
            else ImmersionBar.Companion.with(activity).barParams
        requireNotNull(mBarParams) { "先使用ImmersionBar初始化" }
        if (mContentView != mDecorView.findViewById(R.id.content)) this.mFlag = true
    }

    private constructor(activity: Activity, window: Window?, barParams: BarParams?) {
        this.mActivity = activity
        this.mWindow = window
        this.mDecorView = activity!!.window.decorView
        this.mBarParams = barParams
        val frameLayout = mWindow!!.decorView.findViewById<View>(R.id.content) as FrameLayout
        if (frameLayout.getChildAt(0) != null && !mBarParams!!.systemWindows) {
            this.mFlag = true
        }
        this.mContentView =
            if (frameLayout.getChildAt(0) != null) frameLayout.getChildAt(0) else frameLayout
    }

    /**
     * 监听layout变化
     */
    @JvmOverloads
    fun enable(
        mode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    ) {
        mWindow!!.setSoftInputMode(mode)
        mDecorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener) //当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
    }

    /**
     * 取消监听
     */
    @JvmOverloads
    fun disable(
        mode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    ) {
        mWindow!!.setSoftInputMode(mode)
        mDecorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    private val onGlobalLayoutListener = OnGlobalLayoutListener {
        val r = Rect()
        this.mDecorView.getWindowVisibleDisplayFrame(r) //获取当前窗口可视区域大小的
        val height = this.mDecorView.context.resources.displayMetrics.heightPixels //获取屏幕密度，不包含导航栏
        val diff = height - r.bottom
        if (diff >= 0) {
            if (mFlag || ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) && !OSUtils.isEMUI3_1)
                || !this.mBarParams!!.navigationBarEnable || !this.mBarParams!!.navigationBarWithKitkatEnable
            ) {
                this.mContentView.setPadding(0, this.mContentView.paddingTop, 0, diff)
            } else {
                this.mContentView.setPadding(
                    0, mContentView.paddingTop,
                    0, diff + ImmersionBar.Companion.getNavigationBarHeight(mActivity)
                )
            }
        }
    }

    companion object {
        fun patch(activity: Activity): KeyboardPatch {
            return KeyboardPatch(activity)
        }

        fun patch(activity: Activity, contentView: View): KeyboardPatch {
            return KeyboardPatch(activity, contentView)
        }

        fun patch(activity: Activity, dialog: Dialog?, tag: String): KeyboardPatch {
            return KeyboardPatch(activity, dialog, tag)
        }

        fun patch(
            activity: Activity,
            dialog: Dialog?,
            tag: String,
            contentView: View?
        ): KeyboardPatch {
            return KeyboardPatch(activity, dialog, tag, contentView)
        }

        fun patch(activity: Activity, window: Window?, barParams: BarParams?): KeyboardPatch {
            return KeyboardPatch(activity, window, barParams)
        }
    }
}
