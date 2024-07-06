package com.gyf.barlibrary

import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

/**
 * 沉浸式参数信息
 * Created by geyifeng on 2017/5/9.
 */
class BarParams : Cloneable {
    @ColorInt
    var statusBarColor: Int = Color.TRANSPARENT //状态栏颜色

    @ColorInt
    var navigationBarColor: Int = Color.BLACK //导航栏颜色

    @FloatRange(from = 0.0, to = 1.0)
    var statusBarAlpha: Float = 0.0f //状态栏透明度

    @FloatRange(from = 0.0, to = 1.0)
    var navigationBarAlpha: Float = 0.0f //导航栏透明度
    var fullScreen: Boolean = false //有导航栏的情况，全屏显示
    var fullScreenTemp: Boolean = fullScreen
    var barHide: BarHide = BarHide.FLAG_SHOW_BAR //隐藏Bar
    var darkFont: Boolean = false //状态栏字体深色与亮色标志位
    var statusBarFlag: Boolean = true //是否可以修改状态栏颜色

    @ColorInt
    var statusBarColorTransform: Int = Color.BLACK //状态栏变换后的颜色

    @ColorInt
    var navigationBarColorTransform: Int = Color.BLACK //导航栏变换后的颜色
    var viewMap: MutableMap<View?, Map<Int, Int>> = HashMap() //支持view变色

    @FloatRange(from = 0.0, to = 1.0)
    var viewAlpha: Float = 0.0f
    var fits: Boolean = false //解决标题栏与状态栏重叠问题
    var navigationBarColorTemp: Int = navigationBarColor
    var statusBarView: View? = null //4.4自定义一个状态栏
    var navigationBarView: View? = null //4.4自定义一个导航栏
    var statusBarViewByHeight: View? = null //解决标题栏与状态栏重叠问题

    @ColorInt
    var flymeOSStatusBarFontColor: Int = 0 //flymeOS状态栏字体变色
    var isSupportActionBar: Boolean = false //结合actionBar使用
    var titleBarView: View? = null //标题栏view
    var titleBarHeight: Int = 0 //标题栏的高度
    var titleBarPaddingTopHeight: Int = 0 //标题栏的paddingTop高度
    var titleBarViewMarginTop: View? = null //使用margin来修正标题栏位置
    var titleBarViewMarginTopFlag: Boolean = false //标题栏标识，保证只执行一次
    var keyboardEnable: Boolean = false //解决软键盘与输入框冲突问题
    var keyboardMode: Int = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
            or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE //软键盘属性
            )
    var navigationBarEnable: Boolean = true //是否能修改导航栏颜色
    var navigationBarWithKitkatEnable: Boolean = true //是否能修改4.4手机导航栏颜色

    @Deprecated("")
    var fixMarginAtBottom: Boolean = false //解决出现底部多余导航栏高度，默认为false
    var systemWindows: Boolean = false

    public override fun clone(): BarParams {
        var barParams: BarParams? = null
        try {
            barParams = super.clone() as BarParams
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return barParams!!
    }
}
