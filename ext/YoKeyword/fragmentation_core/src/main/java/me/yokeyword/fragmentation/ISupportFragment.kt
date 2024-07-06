package me.yokeyword.fragmentation

import android.os.Bundle
import androidx.annotation.IntDef
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * Created by YoKey on 17/6/23.
 */
interface ISupportFragment {
    @IntDef(*[STANDARD, SINGLETOP, SINGLETASK])
    @Retention(AnnotationRetention.SOURCE)
    annotation class LaunchMode

    val supportDelegate: SupportFragmentDelegate

    fun extraTransaction(): ExtraTransaction?

    fun enqueueAction(runnable: Runnable?)

    fun post(runnable: Runnable?)

    fun onEnterAnimationEnd(savedInstanceState: Bundle?)

    fun onLazyInitView(savedInstanceState: Bundle?)

    fun onSupportVisible()

    fun onSupportInvisible()

    val isSupportVisible: Boolean

    fun onCreateFragmentAnimator(): FragmentAnimator?

    var fragmentAnimator: FragmentAnimator?

    fun setFragmentResult(resultCode: Int, bundle: Bundle?)

    fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?)

    fun onNewBundle(args: Bundle?)

    fun putNewBundle(newBundle: Bundle?)

    fun onBackPressedSupport(): Boolean

    companion object {
        // LaunchMode
        const val STANDARD: Int = 0
        const val SINGLETOP: Int = 1
        const val SINGLETASK: Int = 2

        // ResultCode
        const val RESULT_CANCELED: Int = 0
        const val RESULT_OK: Int = -1
    }
}
