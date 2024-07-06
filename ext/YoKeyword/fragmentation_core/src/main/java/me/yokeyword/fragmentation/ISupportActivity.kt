package me.yokeyword.fragmentation

import android.view.MotionEvent
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Created by YoKey on 17/6/13.
 */
interface ISupportActivity {
    val supportDelegate: SupportActivityDelegate

    fun extraTransaction(): ExtraTransaction?

    var fragmentAnimator: FragmentAnimator?

    fun onCreateFragmentAnimator(): FragmentAnimator?

    fun post(runnable: Runnable?)

    fun onBackPressed()

    fun onBackPressedSupport()

    fun dispatchTouchEvent(ev: MotionEvent?): Boolean
}
