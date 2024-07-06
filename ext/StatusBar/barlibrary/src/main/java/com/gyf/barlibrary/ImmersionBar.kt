package com.gyf.barlibrary

import android.R
import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import kotlin.math.abs

/**
 * android 4.4以上沉浸式以及bar的管理
 * Created by gyf on 2017/05/09.
 */
class ImmersionBar {
    private var mActivity: Activity
    private var mWindow: Window?
    private var mDecorView: ViewGroup? = null
    private var mContentView: ViewGroup? = null

    /**
     * Gets bar params.
     *
     * @return the bar params
     */
    var barParams: BarParams? = null
        private set

    private var mConfig: BarConfig? = null
    private var mActivityName: String
    private var mFragmentName: String? = null
    private var mImmersionBarName: String
    private var mKeyboardPatch: KeyboardPatch? = null

    /**
     * 在Activit里初始化
     * Instantiates a new Immersion bar.
     *
     * @param activity the activity
     */
    private constructor(activity: Activity) {
        mActivity = activity
        mActivityName = activity.javaClass.name
        mWindow = mActivity!!.window
        mImmersionBarName = mActivityName
        initParams()
    }

    /**
     * 在Fragment里初始化
     * Instantiates a new Immersion bar.
     *
     * @param fragment the fragment
     */
    private constructor(fragment: Fragment) {
        mActivity = fragment.activity!!
        mActivityName = mActivity!!.javaClass.name
        mWindow = mActivity!!.window
        mFragmentName = mActivityName + "_AND_" + fragment.javaClass.name
        mImmersionBarName = mFragmentName!!
        initParams()
    }

    /**
     * 在Dialog里初始化
     * Instantiates a new Immersion bar.
     *
     * @param activity  the activity
     * @param dialog    the dialog
     * @param dialogTag the dialog tag  dialog标识，不能为空
     */
    private constructor(activity: Activity, dialog: Dialog, dialogTag: String) {
        mActivity = activity
        mActivityName = mActivity!!.javaClass.name
        mWindow = dialog.window
        mImmersionBarName = mActivityName + "_AND_" + dialogTag
        initParams()
    }

    /**
     * 透明状态栏，默认透明
     *
     * @return the immersion bar
     */
    fun transparentStatusBar(): ImmersionBar {
        barParams!!.statusBarColor = Color.TRANSPARENT
        return this
    }

    /**
     * 透明导航栏，默认黑色
     *
     * @return the immersion bar
     */
    fun transparentNavigationBar(): ImmersionBar {
        barParams!!.navigationBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.fullScreen = true
        return this
    }

    /**
     * 透明状态栏和导航栏
     *
     * @return the immersion bar
     */
    fun transparentBar(): ImmersionBar {
        barParams!!.statusBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.fullScreen = true
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    fun statusBarColor(@ColorRes statusBarColor: Int): ImmersionBar {
        return this.statusBarColorInt(
            ContextCompat.getColor(
                mActivity!!, statusBarColor
            )
        )
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        @ColorRes statusBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(
            ContextCompat.getColor(
                mActivity!!, statusBarColor
            ), alpha
        )
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        @ColorRes statusBarColor: Int,
        @ColorRes statusBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(
            ContextCompat.getColor(
                mActivity!!, statusBarColor
            ),
            ContextCompat.getColor(mActivity!!, statusBarColorTransform),
            alpha
        )
    }

    /**
     * 状态栏颜色
     * Status bar color int immersion bar.
     *
     * @param statusBarColor the status bar color
     * @return the immersion bar
     */
    fun statusBarColor(statusBarColor: String?): ImmersionBar {
        return this.statusBarColorInt(Color.parseColor(statusBarColor))
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        statusBarColor: String?,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(Color.parseColor(statusBarColor), alpha)
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        statusBarColor: String?,
        statusBarColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(
            Color.parseColor(statusBarColor),
            Color.parseColor(statusBarColorTransform),
            alpha
        )
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    fun statusBarColorInt(@ColorInt statusBarColor: Int): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColorInt(
        @ColorInt statusBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        barParams!!.statusBarAlpha = alpha
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColorInt(
        @ColorInt statusBarColor: Int,
        @ColorInt statusBarColorTransform: Int,
        @FloatRange(
            from = 0.0,
            to = 1.0
        ) alpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        barParams!!.statusBarColorTransform = statusBarColorTransform
        barParams!!.statusBarAlpha = alpha
        return this
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColor(@ColorRes navigationBarColor: Int): ImmersionBar {
        return this.navigationBarColorInt(
            ContextCompat.getColor(
                mActivity!!, navigationBarColor
            )
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        @ColorRes navigationBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            ContextCompat.getColor(
                mActivity!!, navigationBarColor
            ), navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        @ColorRes navigationBarColor: Int,
        @ColorRes navigationBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            ContextCompat.getColor(
                mActivity!!, navigationBarColor
            ),
            ContextCompat.getColor(mActivity!!, navigationBarColorTransform), navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColor(navigationBarColor: String?): ImmersionBar {
        return this.navigationBarColorInt(Color.parseColor(navigationBarColor))
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        navigationBarColor: String?,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(Color.parseColor(navigationBarColor), navigationAlpha)
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        navigationBarColor: String?,
        navigationBarColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            Color.parseColor(navigationBarColor),
            Color.parseColor(navigationBarColorTransform), navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColorInt(@ColorInt navigationBarColor: Int): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColorInt(
        @ColorInt navigationBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarAlpha = navigationAlpha
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColorInt(
        @ColorInt navigationBarColor: Int,
        @ColorInt navigationBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarColorTransform = navigationBarColorTransform
        barParams!!.navigationBarAlpha = navigationAlpha
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColor(@ColorRes barColor: Int): ImmersionBar {
        return this.barColorInt(ContextCompat.getColor(mActivity!!, barColor))
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        @ColorRes barColor: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(ContextCompat.getColor(mActivity!!, barColor), barColor.toFloat())
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        @ColorRes barColor: Int,
        @ColorRes barColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(
            ContextCompat.getColor(mActivity!!, barColor),
            ContextCompat.getColor(mActivity!!, barColorTransform), barAlpha
        )
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColor(barColor: String?): ImmersionBar {
        return this.barColorInt(Color.parseColor(barColor))
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barColor(barColor: String?, @FloatRange(from = 0.0, to = 1.0) barAlpha: Float): ImmersionBar {
        return this.barColorInt(Color.parseColor(barColor), barAlpha)
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        barColor: String?,
        barColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(
            Color.parseColor(barColor),
            Color.parseColor(barColorTransform),
            barAlpha
        )
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColorInt(@ColorInt barColor: Int): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barColorInt(
        @ColorInt barColor: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColorInt(
        @ColorInt barColor: Int,
        @ColorInt barColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor

        barParams!!.statusBarColorTransform = barColorTransform
        barParams!!.navigationBarColorTransform = barColorTransform

        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }


    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransform(@ColorRes statusBarColorTransform: Int): ImmersionBar {
        return this.statusBarColorTransformInt(
            ContextCompat.getColor(
                mActivity!!, statusBarColorTransform
            )
        )
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransform(statusBarColorTransform: String?): ImmersionBar {
        return this.statusBarColorTransformInt(Color.parseColor(statusBarColorTransform))
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransformInt(@ColorInt statusBarColorTransform: Int): ImmersionBar {
        barParams!!.statusBarColorTransform = statusBarColorTransform
        return this
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransform(@ColorRes navigationBarColorTransform: Int): ImmersionBar {
        return this.navigationBarColorTransformInt(
            ContextCompat.getColor(
                mActivity!!, navigationBarColorTransform
            )
        )
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransform(navigationBarColorTransform: String?): ImmersionBar {
        return this.navigationBarColorTransformInt(Color.parseColor(navigationBarColorTransform))
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransformInt(@ColorInt navigationBarColorTransform: Int): ImmersionBar {
        barParams!!.navigationBarColorTransform = navigationBarColorTransform
        return this
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransform(@ColorRes barColorTransform: Int): ImmersionBar {
        return this.barColorTransformInt(
            ContextCompat.getColor(
                mActivity!!, barColorTransform
            )
        )
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransform(barColorTransform: String?): ImmersionBar {
        return this.barColorTransformInt(Color.parseColor(barColorTransform))
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransformInt(@ColorInt barColorTransform: Int): ImmersionBar {
        barParams!!.statusBarColorTransform = barColorTransform
        barParams!!.navigationBarColorTransform = barColorTransform
        return this
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(view: View?): ImmersionBar {
        return this.addViewSupportTransformColorInt(view, barParams!!.statusBarColorTransform)
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View?,
        @ColorRes viewColorAfterTransform: Int
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view, ContextCompat.getColor(
                mActivity!!, viewColorAfterTransform
            )
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View?, @ColorRes viewColorBeforeTransform: Int,
        @ColorRes viewColorAfterTransform: Int
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view,
            ContextCompat.getColor(mActivity!!, viewColorBeforeTransform),
            ContextCompat.getColor(mActivity!!, viewColorAfterTransform)
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(view: View?, viewColorAfterTransform: String?): ImmersionBar {
        return this.addViewSupportTransformColorInt(view, Color.parseColor(viewColorAfterTransform))
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View?, viewColorBeforeTransform: String?,
        viewColorAfterTransform: String?
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view,
            Color.parseColor(viewColorBeforeTransform),
            Color.parseColor(viewColorAfterTransform)
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColorInt(
        view: View?,
        @ColorInt viewColorAfterTransform: Int
    ): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        val map: MutableMap<Int, Int> = HashMap()
        map[barParams!!.statusBarColor] = viewColorAfterTransform
        barParams!!.viewMap[view] = map
        return this
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColorInt(
        view: View?, @ColorInt viewColorBeforeTransform: Int,
        @ColorInt viewColorAfterTransform: Int
    ): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        val map: MutableMap<Int, Int> = HashMap()
        map[viewColorBeforeTransform] = viewColorAfterTransform
        barParams!!.viewMap[view] = map
        return this
    }

    /**
     * view透明度
     * View alpha immersion bar.
     *
     * @param viewAlpha the view alpha
     * @return the immersion bar
     */
    fun viewAlpha(@FloatRange(from = 0.0, to = 1.0) viewAlpha: Float): ImmersionBar {
        barParams!!.viewAlpha = viewAlpha
        return this
    }

    /**
     * 有导航栏的情况下，Activity是否全屏显示
     *
     * @param isFullScreen the is full screen
     * @return the immersion bar
     */
    fun fullScreen(isFullScreen: Boolean): ImmersionBar {
        barParams!!.fullScreen = isFullScreen
        return this
    }

    /**
     * 状态栏透明度
     *
     * @param statusAlpha the status alpha
     * @return the immersion bar
     */
    fun statusBarAlpha(@FloatRange(from = 0.0, to = 1.0) statusAlpha: Float): ImmersionBar {
        barParams!!.statusBarAlpha = statusAlpha
        return this
    }

    /**
     * 导航栏透明度
     *
     * @param navigationAlpha the navigation alpha
     * @return the immersion bar
     */
    fun navigationBarAlpha(@FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float): ImmersionBar {
        barParams!!.navigationBarAlpha = navigationAlpha
        return this
    }

    /**
     * 状态栏和导航栏透明度
     *
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barAlpha(@FloatRange(from = 0.0, to = 1.0) barAlpha: Float): ImmersionBar {
        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }

    /**
     * 状态栏字体深色或亮色，判断设备支不支持状态栏变色来设置状态栏透明度
     * Status bar dark font immersion bar.
     *
     * @param isDarkFont  the is dark font
     * @param statusAlpha the status alpha 如果不支持状态栏字体变色可以使用statusAlpha来指定状态栏透明度，比如白色状态栏的时候可以用到
     * @return the immersion bar
     */
    /**
     * 状态栏字体深色或亮色
     *
     * @param isDarkFont true 深色
     * @return the immersion bar
     */
    @JvmOverloads
    fun statusBarDarkFont(
        isDarkFont: Boolean,
        @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float = 0f
    ): ImmersionBar {
        barParams!!.darkFont = isDarkFont
        if (!isDarkFont) barParams!!.flymeOSStatusBarFontColor = 0
        if (isSupportStatusBarDarkFont) {
            barParams!!.statusBarAlpha = 0f
        } else {
            barParams!!.statusBarAlpha = statusAlpha
        }
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColor(@ColorRes flymeOSStatusBarFontColor: Int): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor = ContextCompat.getColor(
            mActivity!!, flymeOSStatusBarFontColor
        )
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColor(flymeOSStatusBarFontColor: String?): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor = Color.parseColor(flymeOSStatusBarFontColor)
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColorInt(@ColorInt flymeOSStatusBarFontColor: Int): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor
        return this
    }

    /**
     * 解决布局与状态栏重叠问题
     *
     * @param fits the fits
     * @return the immersion bar
     */
    fun fitsSystemWindows(fits: Boolean): ImmersionBar {
        barParams!!.fits = fits
        return this
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     *
     * @param view the view
     * @return the immersion bar
     */
    fun statusBarView(view: View?): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        barParams!!.statusBarViewByHeight = view
        return this
    }

    /**
     * 通过状态栏高度动态设置状态栏布局,只能在Activity中使用
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    fun statusBarView(@IdRes viewId: Int): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("未找到viewId")
        return statusBarView(view)
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     * Status bar view immersion bar.
     *
     * @param viewId   the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    fun statusBarView(@IdRes viewId: Int, rootView: View): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("未找到viewId")
        return statusBarView(view)
    }

    /**
     * 支持有actionBar的界面,调用该方法，布局讲从actionBar下面开始绘制
     * Support action bar immersion bar.
     *
     * @param isSupportActionBar the is support action bar
     * @return the immersion bar
     */
    fun supportActionBar(isSupportActionBar: Boolean): ImmersionBar {
        barParams!!.isSupportActionBar = isSupportActionBar
        return this
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    fun titleBar(view: View?): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        return titleBar(view, true)
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view          the view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    fun titleBar(view: View?, statusBarFlag: Boolean): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        barParams!!.titleBarView = view
        barParams!!.statusBarFlag = statusBarFlag
        setTitleBar()
        return this
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，只支持Activity
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("参数错误")
        return titleBar(view, true)
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId        the view id
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, statusBarFlag: Boolean): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("参数错误")
        return titleBar(view, statusBarFlag)
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId   the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, rootView: View): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("参数错误")
        return titleBar(view, true)
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，支持任何view
     * Title bar immersion bar.
     *
     * @param viewId        the view id
     * @param rootView      the root view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, rootView: View, statusBarFlag: Boolean): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
            ?: throw IllegalArgumentException("参数错误")
        return titleBar(view, statusBarFlag)
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId the view id   标题栏资源id
     * @return the immersion bar
     */
    fun titleBarMarginTop(@IdRes viewId: Int): ImmersionBar {
        return titleBarMarginTop(mActivity!!.findViewById<View>(viewId))
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId   the view id  标题栏资源id
     * @param rootView the root view  布局view
     * @return the immersion bar
     */
    fun titleBarMarginTop(@IdRes viewId: Int, rootView: View): ImmersionBar {
        return titleBarMarginTop(rootView.findViewById<View>(viewId))
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param view the view  要改变的标题栏view
     * @return the immersion bar
     */
    fun titleBarMarginTop(view: View?): ImmersionBar {
        requireNotNull(view) { "参数错误" }
        barParams!!.titleBarViewMarginTop = view
        if (!barParams!!.titleBarViewMarginTopFlag) setTitleBarMarginTop()
        return this
    }

    /**
     * Status bar color transform enable immersion bar.
     *
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    fun statusBarColorTransformEnable(statusBarFlag: Boolean): ImmersionBar {
        barParams!!.statusBarFlag = statusBarFlag
        return this
    }

    /**
     * 给某个页面设置tag来标识这页bar的属性.
     * Add tag bar tag.
     *
     * @param tag the tag
     * @return the bar tag
     */
    fun addTag(tag: String): ImmersionBar {
        var tag = tag
        tag = mActivityName + "_TAG_" + tag
        if (!isEmpty(tag)) {
            val barParams = barParams!!.clone()
            mTagMap[tag] = barParams
            var tagList = mTagKeyMap[mActivityName]
            if (tagList != null) {
                if (!tagList.contains(tag)) tagList.add(tag)
            } else {
                tagList = ArrayList()
                tagList.add(tag)
            }
            mTagKeyMap[mActivityName] = tagList
        }
        return this
    }

    /**
     * 根据tag恢复到某次调用时的参数
     * Recover immersion bar.
     *
     * @param tag the tag
     * @return the immersion bar
     */
    fun getTag(tag: String): ImmersionBar {
        if (!isEmpty(tag)) {
            val barParams = mTagMap[mActivityName + "_TAG_" + tag]
            if (barParams != null) {
                this.barParams = barParams.clone()
            }
        }
        return this
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     *
     * @param enable       the enable
     * @param keyboardMode the keyboard mode
     * @return the immersion bar
     */
    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     * Keyboard enable immersion bar.
     *
     * @param enable the enable
     * @return the immersion bar
     */
    @JvmOverloads
    fun keyboardEnable(
        enable: Boolean, keyboardMode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    ): ImmersionBar {
        barParams!!.keyboardEnable = enable
        barParams!!.keyboardMode = keyboardMode
        return this
    }

    /**
     * 是否可以修改导航栏颜色，默认为true
     * Navigation bar enable immersion bar.
     *
     * @param navigationBarEnable the enable
     * @return the immersion bar
     */
    fun navigationBarEnable(navigationBarEnable: Boolean): ImmersionBar {
        barParams!!.navigationBarEnable = navigationBarEnable
        return this
    }

    /**
     * 是否可以修改4.4设备导航栏颜色，默认为true
     *
     * @param navigationBarWithKitkatEnable the navigation bar with kitkat enable
     * @return the immersion bar
     */
    fun navigationBarWithKitkatEnable(navigationBarWithKitkatEnable: Boolean): ImmersionBar {
        barParams!!.navigationBarWithKitkatEnable = navigationBarWithKitkatEnable
        return this
    }

    /**
     * 当xml里使用android:fitsSystemWindows="true"属性时，
     * 解决4.4和emui3.1手机底部有时会出现多余空白的问题 ，已过时，代码中没用的此处
     * Fix margin atbottom immersion bar.
     *
     * @param fixMarginAtBottom the fix margin atbottom
     * @return the immersion bar
     */
    @Deprecated("")
    fun fixMarginAtBottom(fixMarginAtBottom: Boolean): ImmersionBar {
        barParams!!.fixMarginAtBottom = fixMarginAtBottom
        return this
    }

    /**
     * 通过上面配置后初始化后方可成功调用
     */
    fun init() {
        mMap[mImmersionBarName] = barParams
        initBar() //初始化沉浸式
        setStatusBarView() //通过状态栏高度动态设置状态栏布局
        transformView() //变色view
        keyboardEnable() //解决软键盘与底部输入框冲突问题
    }

    /**
     * 初始化沉浸式默认参数
     * Init params.
     */
    private fun initParams() {
        mDecorView = mWindow!!.decorView as ViewGroup
        mContentView = mDecorView!!.findViewById<View>(R.id.content) as ViewGroup
        mConfig = BarConfig(mActivity)
        if (mMap[mImmersionBarName] == null) {
            barParams = BarParams()
            if (!isEmpty(mFragmentName) && OSUtils.isEMUI3_1) { //保证一个activity页面有同一个状态栏view和导航栏view
                requireNotNull(mMap[mActivityName]) { "在Fragment里使用时，请先在加载Fragment的Activity里初始化！！！" }
                barParams!!.statusBarView = mMap[mActivityName]!!.statusBarView
                barParams!!.navigationBarView = mMap[mActivityName]?.navigationBarView
            }
            mMap[mImmersionBarName] = barParams
        } else {
            barParams = mMap[mImmersionBarName]
        }
    }

    /**
     * 初始化android 5.0以上状态栏和导航栏
     *
     * @param uiFlags the ui flags
     * @return the int
     */
    private fun initBarAboveLOLLIPOP(uiFlags: Int): Int {
        var uiFlags = uiFlags
        uiFlags =
            uiFlags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态栏遮住。
        if (barParams!!.fullScreen && barParams!!.navigationBarEnable) {
            uiFlags =
                uiFlags or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //Activity全屏显示，但导航栏不会被隐藏覆盖，导航栏依然可见，Activity底部布局部分会被导航栏遮住。
        }
        mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (mConfig!!.hasNavigtionBar()) {  //判断是否存在导航栏
            mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        mWindow!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) //需要设置这个才能设置状态栏颜色
        if (barParams!!.statusBarFlag) mWindow!!.statusBarColor = ColorUtils.blendARGB(
            barParams!!.statusBarColor,
            barParams!!.statusBarColorTransform, barParams!!.statusBarAlpha
        ) //设置状态栏颜色
        else mWindow!!.statusBarColor = ColorUtils.blendARGB(
            barParams!!.statusBarColor,
            Color.TRANSPARENT, barParams!!.statusBarAlpha
        ) //设置状态栏颜色

        if (barParams!!.navigationBarEnable) mWindow!!.navigationBarColor = ColorUtils.blendARGB(
            barParams!!.navigationBarColor,
            barParams!!.navigationBarColorTransform, barParams!!.navigationBarAlpha
        ) //设置导航栏颜色

        return uiFlags
    }

    /**
     * 初始化android 4.4和emui3.1状态栏和导航栏
     */
    private fun initBarBelowLOLLIPOP() {
        mWindow!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) //透明状态栏
        setupStatusBarView() //创建一个假的状态栏
        if (mConfig!!.hasNavigtionBar()) {  //判断是否存在导航栏，是否禁止设置导航栏
            if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) mWindow!!.addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            ) //透明导航栏，设置这个，如果有导航栏，底部布局会被导航栏遮住
            else mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            setupNavBarView() //创建一个假的导航栏
        }
    }

    /**
     * 设置一个可以自定义颜色的状态栏
     */
    private fun setupStatusBarView() {
        if (barParams!!.statusBarView == null) {
            barParams!!.statusBarView = View(mActivity)
        }
        val params = mConfig?.let {
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                it.statusBarHeight
            )
        }
        params!!.gravity = Gravity.TOP
        barParams!!.statusBarView!!.layoutParams = params
        if (barParams!!.statusBarFlag) barParams!!.statusBarView!!.setBackgroundColor(
            ColorUtils.blendARGB(
                barParams!!.statusBarColor,
                barParams!!.statusBarColorTransform, barParams!!.statusBarAlpha
            )
        )
        else barParams!!.statusBarView!!.setBackgroundColor(
            ColorUtils.blendARGB(
                barParams!!.statusBarColor,
                Color.TRANSPARENT, barParams!!.statusBarAlpha
            )
        )
        barParams!!.statusBarView!!.visibility = View.VISIBLE
        val viewGroup = barParams!!.statusBarView!!.parent as ViewGroup
        viewGroup?.removeView(barParams!!.statusBarView)
        mDecorView!!.addView(barParams!!.statusBarView)
    }

    /**
     * 设置一个可以自定义颜色的导航栏
     */
    private fun setupNavBarView() {
        if (barParams!!.navigationBarView == null) {
            barParams!!.navigationBarView = View(mActivity)
        }
        val params: FrameLayout.LayoutParams
        if (mConfig!!.isNavigationAtBottom) {
            params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                mConfig!!.navigationBarHeight
            )
            params.gravity = Gravity.BOTTOM
        } else {
            params = FrameLayout.LayoutParams(
                mConfig!!.navigationBarWidth,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            params.gravity = Gravity.END
        }
        barParams!!.navigationBarView!!.layoutParams = params
        if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
            if (!barParams!!.fullScreen && (barParams!!.navigationBarColorTransform == Color.TRANSPARENT)) {
                barParams!!.navigationBarView!!.setBackgroundColor(
                    ColorUtils.blendARGB(
                        barParams!!.navigationBarColor,
                        Color.BLACK, barParams!!.navigationBarAlpha
                    )
                )
            } else {
                barParams!!.navigationBarView!!.setBackgroundColor(
                    ColorUtils.blendARGB(
                        barParams!!.navigationBarColor,
                        barParams!!.navigationBarColorTransform, barParams!!.navigationBarAlpha
                    )
                )
            }
        } else barParams!!.navigationBarView!!.setBackgroundColor(Color.TRANSPARENT)
        barParams!!.navigationBarView!!.visibility = View.VISIBLE
        val viewGroup = barParams!!.navigationBarView!!.parent as ViewGroup
        viewGroup?.removeView(barParams!!.navigationBarView)
        mDecorView!!.addView(barParams!!.navigationBarView)
    }

    /**
     * 解决安卓4.4和EMUI3.1导航栏与状态栏的问题，以及系统属性fitsSystemWindows的坑
     */
    private fun solveNavigation() {
        var i = 0
        val count = mContentView!!.childCount
        while (i < count) {
            val childView = mContentView!!.getChildAt(i)
            if (childView is ViewGroup) {
                barParams!!.systemWindows = childView.getFitsSystemWindows()
                if (barParams!!.systemWindows) {
                    mContentView!!.setPadding(0, 0, 0, 0)
                    return
                }
            }
            i++
        }
        // 解决android4.4有导航栏的情况下，activity底部被导航栏遮挡的问题
        if (mConfig!!.hasNavigtionBar() && !barParams!!.fullScreenTemp && !barParams!!.fullScreen) {
            if (mConfig!!.isNavigationAtBottom) { //判断导航栏是否在底部
                if (!barParams!!.isSupportActionBar) { //判断是否支持actionBar
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight,
                            0, mConfig!!.navigationBarHeight
                        ) //有导航栏，获得rootView的根节点，然后设置距离底部的padding值为导航栏的高度值
                        else mContentView!!.setPadding(0, 0, 0, mConfig!!.navigationBarHeight)
                    } else {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0,
                            mConfig!!.statusBarHeight,
                            0,
                            0
                        )
                        else mContentView!!.setPadding(0, 0, 0, 0)
                    }
                } else {
                    //支持有actionBar的界面
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
                        mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight +
                                    mConfig!!.actionBarHeight + 10, 0, mConfig!!.navigationBarHeight
                        )
                    } else {
                        mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight +
                                    mConfig!!.actionBarHeight + 10, 0, 0
                        )
                    }
                }
            } else {
                if (!barParams!!.isSupportActionBar) {
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight,
                            mConfig!!.navigationBarWidth, 0
                        ) //不在底部，设置距离右边的padding值为导航栏的宽度值
                        else mContentView!!.setPadding(0, 0, mConfig!!.navigationBarWidth, 0)
                    } else {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0,
                            mConfig!!.statusBarHeight,
                            0,
                            0
                        )
                        else mContentView!!.setPadding(0, 0, 0, 0)
                    }
                } else {
                    //支持有actionBar的界面
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, mConfig!!.navigationBarWidth, 0
                    )
                    else mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, 0, 0
                    )
                }
            }
        } else {
            if (!barParams!!.isSupportActionBar) {
                if (barParams!!.fits) mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight,
                    0,
                    0
                )
                else mContentView!!.setPadding(0, 0, 0, 0)
            } else {
                //支持有actionBar的界面
                mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight + mConfig!!.actionBarHeight + 10,
                    0,
                    0
                )
            }
        }
    }

    /**
     * Remove support view immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    fun removeSupportView(view: View?): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        val map = barParams!!.viewMap[view]
        if (map!!.size != 0) {
            barParams!!.viewMap.remove(view)
        }
        return this
    }

    /**
     * Sets status bar dark font.
     * 设置状态栏字体颜色，android6.0以上
     */
    private fun setStatusBarDarkFont(uiFlags: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && barParams!!.darkFont) {
            uiFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            uiFlags
        }
    }

    /**
     * Remove support all view immersion bar.
     *
     * @return the immersion bar
     */
    fun removeSupportAllView(): ImmersionBar {
        if (!barParams!!.viewMap.isEmpty()) {
            barParams!!.viewMap.clear()
        }
        return this
    }

    /**
     * 隐藏导航栏或状态栏
     *
     * @param barHide the bar hide
     * @return the immersion bar
     */
    fun hideBar(barHide: BarHide?): ImmersionBar {
        barParams!!.barHide = barHide!!
        if (OSUtils.isEMUI3_1) {
            if ((barParams!!.barHide == BarHide.FLAG_HIDE_NAVIGATION_BAR) ||
                (barParams!!.barHide == BarHide.FLAG_HIDE_BAR)
            ) {
                barParams!!.navigationBarColor = Color.TRANSPARENT
                barParams!!.fullScreenTemp = true
            } else {
                barParams!!.navigationBarColor = barParams!!.navigationBarColorTemp
                barParams!!.fullScreenTemp = false
            }
        }
        return this
    }

    /**
     * 一键重置所有参数
     * Reset immersion bar.
     *
     * @return the immersion bar
     */
    fun reset(): ImmersionBar {
        val barParamsTemp = barParams
        barParams = BarParams()
        if (OSUtils.isEMUI3_1) {
            barParams!!.statusBarView = barParamsTemp!!.statusBarView
            barParams!!.navigationBarView = barParamsTemp.navigationBarView
        }
        mMap[mImmersionBarName] = barParams
        return this
    }

    /**
     * 当Activity/Fragment/Dialog关闭的时候调用
     */
    fun destroy() {
        if (mKeyboardPatch != null) {
            mKeyboardPatch!!.disable(barParams!!.keyboardMode) //取消监听
            mKeyboardPatch = null
        }
        if (mDecorView != null) mDecorView = null
        if (mContentView != null) mContentView = null
        if (mConfig != null) mConfig = null
        if (mWindow != null) mWindow = null
        if (!isEmpty(mImmersionBarName)) {
            if (barParams != null) barParams = null
            val tagList = mTagKeyMap[mActivityName]
            if (tagList != null && !tagList.isEmpty()) {
                for (tag in tagList) {
                    mTagMap.remove(tag)
                }
                mTagKeyMap.remove(mActivityName)
            }
            mMap.remove(mImmersionBarName)
        }
    }

    /**
     * 初始化状态栏和导航栏
     */
    private fun initBar() {
        var uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE //防止系统栏隐藏时内容区域大小发生变化
        if (!OSUtils.isEMUI3_1) {
            uiFlags = initBarAboveLOLLIPOP(uiFlags) //初始化5.0以上，包含5.0
            uiFlags = setStatusBarDarkFont(uiFlags) //android 6.0以上设置状态栏字体为暗色
            supportActionBar()
        } else {
            initBarBelowLOLLIPOP() //初始化5.0以下，4.4以上沉浸式
            solveNavigation() //解决android4.4有导航栏的情况下，activity底部被导航栏遮挡的问题和android 5.0以下解决状态栏和布局重叠问题
        }
        uiFlags = hideBar(uiFlags) //隐藏状态栏或者导航栏
        mWindow!!.decorView.systemUiVisibility = uiFlags
        if (OSUtils.isMIUI6Later) setMIUIStatusBarDarkFont(
            mWindow,
            barParams!!.darkFont
        ) //修改miui状态栏字体颜色

        if (OSUtils.isFlymeOS4Later) {          // 修改Flyme OS状态栏字体颜色
            if (barParams!!.flymeOSStatusBarFontColor != 0) {
                FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(
                    mActivity,
                    barParams!!.flymeOSStatusBarFontColor
                )
            } else {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(
                    mActivity,
                    barParams!!.darkFont
                )
            }
        }
    }

    /**
     * Hide bar.
     * 隐藏或显示状态栏和导航栏。
     *
     * @param uiFlags the ui flags
     * @return the int
     */
    private fun hideBar(uiFlags: Int): Int {
        var uiFlags = uiFlags
        uiFlags = when (barParams!!.barHide) {
            BarHide.FLAG_HIDE_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.INVISIBLE)

            BarHide.FLAG_HIDE_STATUS_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.INVISIBLE)
            BarHide.FLAG_HIDE_NAVIGATION_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

            BarHide.FLAG_SHOW_BAR -> uiFlags or View.SYSTEM_UI_FLAG_VISIBLE
        }
        return uiFlags or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    /**
     * 变色view
     *
     *
     * Transform view.
     */
    private fun transformView() {
        if (barParams!!.viewMap.isNotEmpty()) {
            val entrySet: MutableSet<MutableMap.MutableEntry<View?, Map<Int, Int>>> = barParams!!.viewMap.entries
            for ((view, map) in entrySet) {
                var colorBefore = barParams!!.statusBarColor
                var colorAfter = barParams!!.statusBarColorTransform
                for ((key, value) in map!!) {
                    colorBefore = key
                    colorAfter = value!!
                }
                if (view != null) {
                    if (abs((barParams!!.viewAlpha - 0.0f)) == 0.0f) view.setBackgroundColor(
                        ColorUtils.blendARGB(
                            colorBefore, colorAfter, barParams!!.statusBarAlpha
                        )
                    )
                    else view.setBackgroundColor(
                        ColorUtils.blendARGB(
                            colorBefore, colorAfter, barParams!!.viewAlpha
                        )
                    )
                }
            }
        }
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     */
    private fun setStatusBarView() {
        if (barParams!!.statusBarViewByHeight != null) {
            val params = barParams!!.statusBarViewByHeight!!.layoutParams
            params.height = mConfig!!.statusBarHeight
            barParams!!.statusBarViewByHeight!!.layoutParams = params
        }
    }

    /**
     * 重新绘制标题栏高度，解决状态栏与顶部重叠问题
     * Sets title bar.
     */
    private fun setTitleBar() {
        if (barParams!!.titleBarView != null) {
            val width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            barParams!!.titleBarView!!.measure(width, height)
            val layoutParams = barParams!!.titleBarView!!.layoutParams
            if (barParams!!.titleBarHeight == 0) barParams!!.titleBarHeight =
                barParams!!.titleBarView!!.measuredHeight + (mConfig?.statusBarHeight ?: 0)
            if (barParams!!.titleBarPaddingTopHeight == 0) barParams!!.titleBarPaddingTopHeight =
                (barParams!!.titleBarView!!.paddingTop
                        + (mConfig?.statusBarHeight ?: 0))
            layoutParams.height = barParams!!.titleBarHeight
            barParams!!.titleBarView!!.setPadding(
                barParams!!.titleBarView!!.paddingLeft,
                barParams!!.titleBarPaddingTopHeight,
                barParams!!.titleBarView!!.paddingRight,
                barParams!!.titleBarView!!.paddingBottom
            )
            barParams!!.titleBarView!!.layoutParams = layoutParams
        }
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Sets title bar margin top.
     */
    private fun setTitleBarMarginTop() {
        val layoutParams = barParams!!.titleBarViewMarginTop!!.layoutParams as MarginLayoutParams
        layoutParams.setMargins(
            layoutParams.leftMargin,
            layoutParams.topMargin + mConfig!!.statusBarHeight,
            layoutParams.rightMargin,
            layoutParams.bottomMargin
        )
        barParams!!.titleBarViewMarginTopFlag = true
    }

    /**
     * 支持actionBar的界面
     * Support action bar.
     */
    private fun supportActionBar() {
        if (!OSUtils.isEMUI3_1) {
            if (barParams!!.isSupportActionBar) {
                mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight + mConfig!!.actionBarHeight,
                    0,
                    0
                )
            } else {
                if (barParams!!.fits) mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight,
                    0,
                    0
                )
                else mContentView!!.setPadding(0, 0, 0, 0)
            }
        }
    }

    /**
     * 解决底部输入框与软键盘问题
     * Keyboard enable.
     */
    private fun keyboardEnable() {
        if (mKeyboardPatch == null) mKeyboardPatch =
            KeyboardPatch.Companion.patch(mActivity, mWindow, barParams)
        if (barParams!!.keyboardEnable) {  //解决软键盘与底部输入框冲突问题
            mKeyboardPatch!!.enable(barParams!!.keyboardMode)
        } else {
            mKeyboardPatch!!.disable(barParams!!.keyboardMode)
        }
    }

    fun getTagBarParams(tag: String): BarParams? {
        var barParams: BarParams? = null
        if (!isEmpty(tag)) {
            barParams = mTagMap[mActivityName + "_TAG_" + tag]
        }
        return barParams
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @return boolean 成功执行返回true
     */
    private fun setMIUIStatusBarDarkFont(window: Window?, darkFont: Boolean) {
        if (window != null) {
            val clazz: Class<out Window> = window.javaClass
            try {
                val darkModeFlag: Int
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                if (darkFont) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private val mMap: MutableMap<String, BarParams?> = HashMap()
        private val mTagMap: MutableMap<String, BarParams?> = HashMap()
        private val mTagKeyMap: MutableMap<String, ArrayList<String>> = HashMap()
        val isSupportStatusBarDarkFont: Boolean
            /**
             * 判断手机支不支持状态栏字体变色
             * Is support status bar dark font boolean.
             *
             * @return the boolean
             */
            get() = (OSUtils.isMIUI6Later || OSUtils.isFlymeOS4Later
                    || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M))

        /**
         * 初始化Activity
         * With immersion bar.
         *
         * @param activity the activity
         * @return the immersion bar
         */
        @JvmStatic
        fun with(activity: Activity?): ImmersionBar {
            requireNotNull(activity) { "Activity不能为null" }
            return ImmersionBar(activity)
        }

        /**
         * 调用该方法必须保证加载Fragment的Activity先初始化
         * With immersion bar.
         *
         * @param fragment the fragment
         * @return the immersion bar
         */
        fun with(fragment: Fragment?): ImmersionBar {
            requireNotNull(fragment) { "Fragment不能为null" }
            return ImmersionBar(fragment)
        }

        /**
         * 在dialog里使用
         * With immersion bar.
         *
         * @param activity  the activity
         * @param dialog    the dialog
         * @param dialogTag the dialog tag
         * @return the immersion bar
         */
        fun with(activity: Activity?, dialog: Dialog?, dialogTag: String): ImmersionBar {
            requireNotNull(activity) { "Activity不能为null" }
            requireNotNull(dialog) { "Dialog不能为null" }
            require(!isEmpty(dialogTag)) { "tag不能为null或空" }
            return ImmersionBar(activity, dialog, dialogTag)
        }

        /**
         * 单独设置标题栏的高度
         * Sets title bar.
         *
         * @param activity the activity
         * @param view     the view
         */
        fun setTitleBar(activity: Activity?, view: View) {
            val width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(width, height)
            val lp = view.layoutParams
            lp.height = view.measuredHeight + getStatusBarHeight(activity)
            view.setPadding(
                view.paddingLeft, view.paddingTop + getStatusBarHeight(activity),
                view.paddingRight, view.paddingBottom
            )
        }

        /**
         * Sets status bar view.
         *
         * @param activity the activity
         * @param view     the view
         */
        fun setStatusBarView(activity: Activity?, view: View) {
            val params = view.layoutParams
            params.height = getStatusBarHeight(activity)
            view.layoutParams = params
        }

        fun setTitleBarMarginTop(activity: Activity?, view: View) {
            val layoutParams = view.layoutParams as MarginLayoutParams
            layoutParams.setMargins(
                layoutParams.leftMargin,
                layoutParams.topMargin + getStatusBarHeight(activity),
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )
        }

        /**
         * 解决顶部与布局重叠问题
         * Sets fits system windows.
         *
         * @param activity the activity
         */
        fun setFitsSystemWindows(activity: Activity) {
            val parent = activity.findViewById<ViewGroup>(R.id.content)
            var i = 0
            val count = parent.childCount
            while (i < count) {
                val childView = parent.getChildAt(i)
                if (childView is ViewGroup) {
                    childView.setFitsSystemWindows(true)
                    childView.clipToPadding = true
                }
                i++
            }
        }

        private fun isEmpty(str: String?): Boolean {
            return str == null || str.trim { it <= ' ' }.isEmpty()
        }

        /**
         * Has navigtion bar boolean.
         * 判断是否存在导航栏
         *
         * @param activity the activity
         * @return the boolean
         */
        fun hasNavigationBar(activity: Activity?): Boolean {
            val config = BarConfig(activity)
            return config.hasNavigtionBar()
        }

        /**
         * Gets navigation bar height.
         * 获得导航栏的高度
         *
         * @param activity the activity
         * @return the navigation bar height
         */
        fun getNavigationBarHeight(activity: Activity?): Int {
            val config = BarConfig(activity)
            return config.navigationBarHeight
        }

        /**
         * Gets navigation bar width.
         * 获得导航栏的宽度
         *
         * @param activity the activity
         * @return the navigation bar width
         */
        fun getNavigationBarWidth(activity: Activity?): Int {
            val config = BarConfig(activity)
            return config.navigationBarWidth
        }

        /**
         * Is navigation at bottom boolean.
         * 判断导航栏是否在底部
         *
         * @param activity the activity
         * @return the boolean
         */
        fun isNavigationAtBottom(activity: Activity?): Boolean {
            val config = BarConfig(activity)
            return config.isNavigationAtBottom
        }

        /**
         * Gets status bar height.
         * 或得状态栏的高度
         *
         * @param activity the activity
         * @return the status bar height
         */
        fun getStatusBarHeight(activity: Activity?): Int {
            val config = BarConfig(activity)
            return config.statusBarHeight
        }

        /**
         * Gets action bar height.
         * 或得ActionBar得高度
         *
         * @param activity the activity
         * @return the action bar height
         */
        fun getActionBarHeight(activity: Activity?): Int {
            val config = BarConfig(activity)
            return config.actionBarHeight
        }

        /**
         * 隐藏状态栏
         * Hide status bar.
         *
         * @param window the window
         */
        fun hideStatusBar(window: Window) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}
