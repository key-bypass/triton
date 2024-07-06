package me.yokeyword.fragmentation.helper.internal

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import me.yokeyword.fragmentation.R
import me.yokeyword.fragmentation.anim.FragmentAnimator

/**
 * @Hide Created by YoKeyword on 16/7/26.
 */
class AnimatorHelper(private val context: Context, fragmentAnimator: FragmentAnimator?) {
    var noneAnim: Animation? = null
        get() {
            if (field == null) {
                field = AnimationUtils.loadAnimation(context, R.anim.no_anim)
            }
            return field
        }
        private set
    var noneAnimFixed: Animation? = null
        get() {
            if (field == null) {
                field = object : Animation() {
                }
            }
            return field
        }
        private set
    var enterAnim: Animation? = null
    var exitAnim: Animation? = null
    var popEnterAnim: Animation? = null
    var popExitAnim: Animation? = null

    private var fragmentAnimator: FragmentAnimator? = null

    init {
        notifyChanged(fragmentAnimator)
    }

    fun notifyChanged(fragmentAnimator: FragmentAnimator?) {
        this.fragmentAnimator = fragmentAnimator
        initEnterAnim()
        initExitAnim()
        initPopEnterAnim()
        initPopExitAnim()
    }

    fun compatChildFragmentExitAnim(fragment: Fragment?): Animation? {
        if ((fragment!!.tag != null && fragment.tag!!
                .startsWith("android:switcher:") && fragment.userVisibleHint) ||
            (fragment.parentFragment != null && fragment.parentFragment!!
                .isRemoving && !fragment.isHidden)
        ) {
            val animation: Animation = object : Animation() {
            }
            animation.duration = exitAnim!!.duration
            return animation
        }
        return null
    }

    private fun initEnterAnim(): Animation? {
        enterAnim = if (fragmentAnimator!!.enter == 0) {
            AnimationUtils.loadAnimation(
                context,
                R.anim.no_anim
            )
        } else {
            AnimationUtils.loadAnimation(context, fragmentAnimator!!.enter)
        }
        return enterAnim
    }

    private fun initExitAnim(): Animation? {
        exitAnim = if (fragmentAnimator!!.exit == 0) {
            AnimationUtils.loadAnimation(
                context,
                R.anim.no_anim
            )
        } else {
            AnimationUtils.loadAnimation(context, fragmentAnimator!!.exit)
        }
        return exitAnim
    }

    private fun initPopEnterAnim(): Animation? {
        popEnterAnim = if (fragmentAnimator!!.popEnter == 0) {
            AnimationUtils.loadAnimation(
                context,
                R.anim.no_anim
            )
        } else {
            AnimationUtils.loadAnimation(context, fragmentAnimator!!.popEnter)
        }
        return popEnterAnim
    }

    private fun initPopExitAnim(): Animation? {
        popExitAnim = if (fragmentAnimator!!.popExit == 0) {
            AnimationUtils.loadAnimation(
                context,
                R.anim.no_anim
            )
        } else {
            AnimationUtils.loadAnimation(context, fragmentAnimator!!.popExit)
        }
        return popExitAnim
    }
}
