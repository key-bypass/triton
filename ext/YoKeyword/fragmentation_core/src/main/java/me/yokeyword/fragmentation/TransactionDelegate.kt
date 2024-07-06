package me.yokeyword.fragmentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentationMagician.executePendingTransactionsAllowingStateLoss
import androidx.fragment.app.FragmentationMagician.getActiveFragments
import androidx.fragment.app.FragmentationMagician.isStateSaved
import androidx.fragment.app.FragmentationMagician.popBackStackAllowingStateLoss

import me.yokeyword.fragmentation.SupportFragmentDelegate.EnterAnimListener
import me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning
import me.yokeyword.fragmentation.helper.internal.ResultRecord
import me.yokeyword.fragmentation.helper.internal.TransactionRecord.SharedElement
import me.yokeyword.fragmentation.queue.Action
import me.yokeyword.fragmentation.queue.ActionQueue

/**
 * Controller
 * Created by YoKeyword on 16/1/22.
 */
class TransactionDelegate(private val mSupport: ISupportActivity) {
    private val mActivity = mSupport as FragmentActivity

    private val mHandler = Handler(Looper.getMainLooper())

    var mActionQueue: ActionQueue = ActionQueue(mHandler)

    fun post(runnable: Runnable) {
        mActionQueue.enqueue(object : Action() {
            override fun run() {
                runnable.run()
            }
        })
    }

    fun loadRootTransaction(
        fm: FragmentManager?,
        containerId: Int,
        to: ISupportFragment,
        addToBackStack: Boolean,
        allowAnimation: Boolean
    ) {
        enqueue(fm, object : Action(ACTION_LOAD) {
            override fun run() {
                bindContainerId(containerId, to)

                var toFragmentTag = to.javaClass.name
                val transactionRecord = to.supportDelegate.mTransactionRecord
                if (transactionRecord != null) {
                    if (transactionRecord.tag != null) {
                        toFragmentTag = transactionRecord.tag
                    }
                }

                start(
                    fm,
                    null,
                    to,
                    toFragmentTag,
                    !addToBackStack,
                    null,
                    allowAnimation,
                    TYPE_REPLACE
                )
            }
        })
    }

    fun loadMultipleRootTransaction(
        fm: FragmentManager,
        containerId: Int,
        showPosition: Int,
        vararg tos: ISupportFragment
    ) {
        enqueue(fm, object : Action(Action.Companion.ACTION_LOAD) {
            override fun run() {
                val ft = fm.beginTransaction()
                for (i in tos.indices) {
                    val to = tos[i] as Fragment

                    val args = getArguments(to)
                    args.putInt(
                        FRAGMENTATION_ARG_ROOT_STATUS,
                        SupportFragmentDelegate.Companion.STATUS_ROOT_ANIM_DISABLE
                    )
                    bindContainerId(containerId, tos[i])

                    val toName = to.javaClass.name
                    ft.add(containerId, to, toName)

                    if (i != showPosition) {
                        ft.hide(to)
                    }
                }

                supportCommit(fm, ft)
            }
        })
    }

    /**
     * Dispatch the start transaction.
     */
    fun dispatchStartTransaction(
        fm: FragmentManager?,
        from: ISupportFragment?,
        to: ISupportFragment,
        requestCode: Int,
        launchMode: Int,
        type: Int
    ) {
        enqueue(
            fm,
            object :
                Action(if (launchMode == ISupportFragment.Companion.SINGLETASK) Action.Companion.ACTION_POP_MOCK else Action.Companion.ACTION_NORMAL) {
                override fun run() {
                    doDispatchStartTransaction(fm, from, to, requestCode, launchMode, type)
                }
            })
    }

    /**
     * Show showFragment then hide hideFragment
     */
    fun showHideFragment(
        fm: FragmentManager,
        showFragment: ISupportFragment,
        hideFragment: ISupportFragment?
    ) {
        enqueue(fm, object : Action() {
            override fun run() {
                doShowHideFragment(fm, showFragment, hideFragment)
            }
        })
    }

    /**
     * Start the target Fragment and pop itself
     */
    fun startWithPop(fm: FragmentManager?, from: ISupportFragment?, to: ISupportFragment) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP_MOCK) {
            override fun run() {
                val top = getTopFragmentForStart(from, fm)
                    ?: throw NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!")

                val containerId = top.supportDelegate.mContainerId
                bindContainerId(containerId, to)

                handleAfterSaveInStateTransactionException(fm, "popTo()")
                executePendingTransactionsAllowingStateLoss(fm!!)
                top.supportDelegate.mLockAnim = true
                if (!isStateSaved(fm)) {
                    SupportHelper.getTopFragment(fm)?.let {
                        mockStartWithPopAnim(
                            it,
                            to,
                            top.supportDelegate!!.mAnimHelper!!.popExitAnim
                        )
                    }
                }

                removeTopFragment(fm)
                popBackStackAllowingStateLoss(fm)
                executePendingTransactionsAllowingStateLoss(fm)
            }
        })

        dispatchStartTransaction(fm, from, to, 0, ISupportFragment.Companion.STANDARD, TYPE_ADD)
    }

    fun startWithPopTo(
        fm: FragmentManager?,
        from: ISupportFragment?,
        to: ISupportFragment,
        fragmentTag: String?,
        includeTargetFragment: Boolean
    ) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP_MOCK) {
            override fun run() {
                var flag = 0
                if (includeTargetFragment) {
                    flag = FragmentManager.POP_BACK_STACK_INCLUSIVE
                }

                val willPopFragments =
                    SupportHelper.getWillPopFragments(fm, fragmentTag, includeTargetFragment)

                val top = getTopFragmentForStart(from, fm)
                    ?: throw NullPointerException("There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment() first!")

                val containerId = top.supportDelegate.mContainerId
                bindContainerId(containerId, to)

                if (willPopFragments!!.isEmpty()) return

                handleAfterSaveInStateTransactionException(fm, "startWithPopTo()")
                executePendingTransactionsAllowingStateLoss(fm!!)
                if (!isStateSaved(fm)) {
                    SupportHelper.getTopFragment(fm)?.let {
                        mockStartWithPopAnim(
                            it,
                            to,
                            top.supportDelegate.mAnimHelper!!.popExitAnim
                        )
                    }
                }

                safePopTo(fragmentTag, fm, flag, willPopFragments)
            }
        })

        dispatchStartTransaction(fm, from, to, 0, ISupportFragment.Companion.STANDARD, TYPE_ADD)
    }


    /**
     * Remove
     */
    fun remove(fm: FragmentManager?, fragment: Fragment, showPreFragment: Boolean) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP, fm) {
            override fun run() {
                val ft = fm!!.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .remove(fragment)

                if (showPreFragment) {
                    val preFragment = SupportHelper.getPreFragment(fragment)
                    if (preFragment is Fragment) {
                        ft.show((preFragment as Fragment))
                    }
                }
                supportCommit(fm, ft)
            }
        })
    }

    /**
     * Pop
     */
    fun pop(fm: FragmentManager?) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP, fm) {
            override fun run() {
                handleAfterSaveInStateTransactionException(fm, "pop()")
                popBackStackAllowingStateLoss(fm!!)
                removeTopFragment(fm)
            }
        })
    }

    private fun removeTopFragment(fm: FragmentManager?) {
        try { // Safe popBackStack()
            val top = SupportHelper.getBackStackTopFragment(fm)
            if (top != null) {
                fm!!.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .remove((top as Fragment))
                    .commitAllowingStateLoss()
            }
        } catch (ignored: Exception) {
        }
    }

    fun popQuiet(fm: FragmentManager?, fragment: Fragment?) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP_MOCK) {
            override fun run() {
                mSupport.supportDelegate.mPopMultipleNoAnim = true
                removeTopFragment(fm)
                popBackStackAllowingStateLoss(fm!!, fragment!!.tag, 0)
                popBackStackAllowingStateLoss(fm)
                executePendingTransactionsAllowingStateLoss(fm)
                mSupport.supportDelegate.mPopMultipleNoAnim = false
            }
        })
    }

    /**
     * Pop the last fragment transition from the manager's fragment pop stack.
     *
     * @param targetFragmentTag     Tag
     * @param includeTargetFragment Whether it includes targetFragment
     */
    fun popTo(
        targetFragmentTag: String?,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable?,
        fm: FragmentManager?,
        popAnim: Int
    ) {
        enqueue(fm, object : Action(Action.Companion.ACTION_POP_MOCK) {
            override fun run() {
                doPopTo(targetFragmentTag, includeTargetFragment, fm, popAnim)

                afterPopTransactionRunnable?.run()
            }
        })
    }

    /**
     * Dispatch the pop-event. Priority of the top of the stack of Fragment
     */
    fun dispatchBackPressedEvent(activeFragment: ISupportFragment?): Boolean {
        if (activeFragment != null) {
            val result = activeFragment.onBackPressedSupport()
            if (result) {
                return true
            }

            val parentFragment = (activeFragment as Fragment).parentFragment
            if (dispatchBackPressedEvent(parentFragment as ISupportFragment?)) {
                return true
            }
        }
        return false
    }

    fun handleResultRecord(from: Fragment?) {
        try {
            val args = from!!.arguments ?: return
            val resultRecord = args.getParcelable<ResultRecord>(FRAGMENTATION_ARG_RESULT_RECORD)
                ?: return

            val targetFragment = from.fragmentManager!!
                .getFragment(from.arguments!!, FRAGMENTATION_STATE_SAVE_RESULT) as ISupportFragment?
            targetFragment!!.onFragmentResult(
                resultRecord.requestCode,
                resultRecord.resultCode,
                resultRecord.resultBundle
            )
        } catch (ignored: IllegalStateException) {
            // Fragment no longer exists
        }
    }

    private fun enqueue(fm: FragmentManager?, action: Action) {
        if (fm == null) {
            Log.w(TAG, "FragmentManager is null, skip the action!")
            return
        }
        mActionQueue.enqueue(action)
    }

    private fun doDispatchStartTransaction(
        fm: FragmentManager?,
        from: ISupportFragment?,
        to: ISupportFragment,
        requestCode: Int,
        launchMode: Int,
        type: Int
    ) {
        var from = from
        checkNotNull(to, "toFragment == null")

        if ((type == TYPE_ADD_RESULT || type == TYPE_ADD_RESULT_WITHOUT_HIDE) && from != null) {
            if (!(from as Fragment).isAdded) {
                Log.w(
                    TAG,
                    (from as Fragment).javaClass.simpleName + " has not been attached yet! startForResult() converted to start()"
                )
            } else {
                saveRequestCode(fm, from as Fragment, to as Fragment, requestCode)
            }
        }

        from = getTopFragmentForStart(from, fm)

        val containerId = getArguments(to as Fragment).getInt(FRAGMENTATION_ARG_CONTAINER, 0)
        if (from == null && containerId == 0) {
            Log.e(
                TAG,
                "There is no Fragment in the FragmentManager, maybe you need to call loadRootFragment()!"
            )
            return
        }

        if (from != null && containerId == 0) {
            bindContainerId(from.supportDelegate.mContainerId, to)
        }

        // process ExtraTransaction
        var toFragmentTag = to.javaClass.name
        var dontAddToBackStack = false
        var sharedElementList: ArrayList<SharedElement?>? = null
        val transactionRecord = to.supportDelegate.mTransactionRecord
        if (transactionRecord != null) {
            if (transactionRecord.tag != null) {
                toFragmentTag = transactionRecord.tag
            }
            dontAddToBackStack = transactionRecord.dontAddToBackStack
            if (transactionRecord.sharedElementList != null) {
                sharedElementList = transactionRecord.sharedElementList
            }
        }

        if (handleLaunchMode(fm, from, to, toFragmentTag, launchMode)) return

        start(fm, from, to, toFragmentTag, dontAddToBackStack, sharedElementList, false, type)
    }

    private fun getTopFragmentForStart(
        from: ISupportFragment?,
        fm: FragmentManager?
    ): ISupportFragment? {
        val top: ISupportFragment?
        if (from == null) {
            top = SupportHelper.getTopFragment(fm)
        } else {
            if (from.supportDelegate.mContainerId == 0) {
                val fromF = from as Fragment
                check(!(fromF.tag != null && !fromF.tag!!.startsWith("android:switcher:"))) { "Can't find container, please call loadRootFragment() first!" }
            }
            top = SupportHelper.getTopFragment(fm, from.supportDelegate.mContainerId)
        }
        return top
    }

    private fun start(
        fm: FragmentManager?,
        from: ISupportFragment?,
        to: ISupportFragment,
        toFragmentTag: String?,
        dontAddToBackStack: Boolean,
        sharedElementList: ArrayList<SharedElement?>?,
        allowRootFragmentAnim: Boolean,
        type: Int
    ) {
        val ft = fm!!.beginTransaction()
        val addMode =
            (type == TYPE_ADD || type == TYPE_ADD_RESULT || type == TYPE_ADD_WITHOUT_HIDE || type == TYPE_ADD_RESULT_WITHOUT_HIDE)
        val fromF = from as Fragment?
        val toF = to as Fragment
        val args = getArguments(toF)
        args.putBoolean(FRAGMENTATION_ARG_REPLACE, !addMode)

        if (sharedElementList == null) {
            if (addMode) { // Replace mode forbidden animation, the replace animations exist overlapping Bug on support-v4.
                val record = to.supportDelegate.mTransactionRecord
                if (record != null && record.targetFragmentEnter != Int.MIN_VALUE) {
                    ft.setCustomAnimations(
                        record.targetFragmentEnter, record.currentFragmentPopExit,
                        record.currentFragmentPopEnter, record.targetFragmentExit
                    )
                    args.putInt(FRAGMENTATION_ARG_CUSTOM_ENTER_ANIM, record.targetFragmentEnter)
                    args.putInt(FRAGMENTATION_ARG_CUSTOM_EXIT_ANIM, record.targetFragmentExit)
                    args.putInt(
                        FRAGMENTATION_ARG_CUSTOM_POP_EXIT_ANIM,
                        record.currentFragmentPopExit
                    )
                } else {
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
            } else {
                args.putInt(
                    FRAGMENTATION_ARG_ROOT_STATUS,
                    SupportFragmentDelegate.Companion.STATUS_ROOT_ANIM_DISABLE
                )
            }
        } else {
            args.putBoolean(FRAGMENTATION_ARG_IS_SHARED_ELEMENT, true)
            for (item in sharedElementList) {
                ft.addSharedElement(item!!.sharedElement, item.sharedName)
            }
        }
        if (from == null) {
            ft.replace(args.getInt(FRAGMENTATION_ARG_CONTAINER), toF, toFragmentTag)
            if (!addMode) {
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                args.putInt(
                    FRAGMENTATION_ARG_ROOT_STATUS,
                    if (allowRootFragmentAnim) SupportFragmentDelegate.Companion.STATUS_ROOT_ANIM_ENABLE else SupportFragmentDelegate.Companion.STATUS_ROOT_ANIM_DISABLE
                )
            }
        } else {
            if (addMode) {
                ft.add(from.supportDelegate.mContainerId, toF, toFragmentTag)
                if (type != TYPE_ADD_WITHOUT_HIDE && type != TYPE_ADD_RESULT_WITHOUT_HIDE) {
                    ft.hide(fromF!!)
                }
            } else {
                ft.replace(from.supportDelegate.mContainerId, toF, toFragmentTag)
            }
        }

        if (!dontAddToBackStack && type != TYPE_REPLACE_DONT_BACK) {
            ft.addToBackStack(toFragmentTag)
        }
        supportCommit(fm, ft)
    }

    private fun doShowHideFragment(
        fm: FragmentManager,
        showFragment: ISupportFragment,
        hideFragment: ISupportFragment?
    ) {
        if (showFragment === hideFragment) return

        val ft = fm.beginTransaction().show((showFragment as Fragment))

        if (hideFragment == null) {
            val fragmentList = getActiveFragments(fm)
            if (fragmentList != null) {
                for (fragment in fragmentList) {
                    if (fragment != null && fragment !== showFragment) {
                        ft.hide(fragment)
                    }
                }
            }
        } else {
            ft.hide((hideFragment as Fragment?)!!)
        }
        supportCommit(fm, ft)
    }

    private fun bindContainerId(containerId: Int, to: ISupportFragment) {
        val args = getArguments(to as Fragment)
        args.putInt(FRAGMENTATION_ARG_CONTAINER, containerId)
    }

    private fun getArguments(fragment: Fragment): Bundle {
        var bundle = fragment.arguments
        if (bundle == null) {
            bundle = Bundle()
            fragment.arguments = bundle
        }
        return bundle
    }

    private fun supportCommit(fm: FragmentManager?, transaction: FragmentTransaction) {
        handleAfterSaveInStateTransactionException(fm, "commit()")
        transaction.commitAllowingStateLoss()
    }

    private fun handleLaunchMode(
        fm: FragmentManager?,
        topFragment: ISupportFragment?,
        to: ISupportFragment,
        toFragmentTag: String?,
        launchMode: Int
    ): Boolean {
        if (topFragment == null) return false
        val stackToFragment = SupportHelper.findBackStackFragment(to.javaClass, toFragmentTag, fm)
            ?: return false

        if (launchMode == ISupportFragment.Companion.SINGLETOP) {
            if (to === topFragment || to.javaClass.name == topFragment.javaClass.name) {
                handleNewBundle(to, stackToFragment)
                return true
            }
        } else if (launchMode == ISupportFragment.Companion.SINGLETASK) {
            doPopTo(toFragmentTag, false, fm, DEFAULT_POPTO_ANIM)
            mHandler.post { handleNewBundle(to, stackToFragment) }
            return true
        }

        return false
    }

    private fun handleNewBundle(toFragment: ISupportFragment, stackToFragment: ISupportFragment) {
        val argsNewBundle = toFragment.supportDelegate.mNewBundle

        val args = getArguments(toFragment as Fragment)
        if (args.containsKey(FRAGMENTATION_ARG_CONTAINER)) {
            args.remove(FRAGMENTATION_ARG_CONTAINER)
        }

        if (argsNewBundle != null) {
            args.putAll(argsNewBundle)
        }

        stackToFragment.onNewBundle(args)
    }

    /**
     * save requestCode
     */
    private fun saveRequestCode(
        fm: FragmentManager?,
        from: Fragment,
        to: Fragment,
        requestCode: Int
    ) {
        val bundle = getArguments(to)
        val resultRecord = ResultRecord()
        resultRecord.requestCode = requestCode
        bundle.putParcelable(FRAGMENTATION_ARG_RESULT_RECORD, resultRecord)
        fm!!.putFragment(bundle, FRAGMENTATION_STATE_SAVE_RESULT, from)
    }

    private fun doPopTo(
        targetFragmentTag: String?,
        includeTargetFragment: Boolean,
        fm: FragmentManager?,
        popAnim: Int
    ) {
        handleAfterSaveInStateTransactionException(fm, "popTo()")

        val targetFragment = fm!!.findFragmentByTag(targetFragmentTag)

        if (targetFragment == null) {
            Log.e(
                TAG,
                "Pop failure! Can't find FragmentTag:$targetFragmentTag in the FragmentManager's Stack."
            )
            return
        }

        var flag = 0
        if (includeTargetFragment) {
            flag = FragmentManager.POP_BACK_STACK_INCLUSIVE
        }

        val willPopFragments =
            SupportHelper.getWillPopFragments(fm, targetFragmentTag, includeTargetFragment)
        if (willPopFragments!!.size <= 0) return

        val top = willPopFragments[0]
        mockPopToAnim(top, targetFragmentTag, fm, flag, willPopFragments, popAnim)
    }

    private fun safePopTo(
        fragmentTag: String?,
        fm: FragmentManager?,
        flag: Int,
        willPopFragments: List<Fragment?>?
    ) {
        mSupport.supportDelegate.mPopMultipleNoAnim = true

        val transaction = fm!!.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        for (fragment in willPopFragments!!) {
            transaction.remove(fragment!!)
        }
        transaction.commitAllowingStateLoss()

        popBackStackAllowingStateLoss(fm, fragmentTag, flag)
        executePendingTransactionsAllowingStateLoss(fm)
        mSupport.supportDelegate.mPopMultipleNoAnim = false
    }

    private fun mockPopToAnim(
        from: Fragment?,
        targetFragmentTag: String?,
        fm: FragmentManager?,
        flag: Int,
        willPopFragments: List<Fragment?>?,
        popAnim: Int
    ) {
        if (from !is ISupportFragment) {
            safePopTo(targetFragmentTag, fm, flag, willPopFragments)
            return
        }

        val fromSupport = from as ISupportFragment
        val container = findContainerById(from, fromSupport.supportDelegate.mContainerId) ?: return

        val fromView = from.view ?: return

        container.removeViewInLayout(fromView)
        val mock = addMockView(fromView, container)

        safePopTo(targetFragmentTag, fm, flag, willPopFragments)

        var animation: Animation?
        if (popAnim == DEFAULT_POPTO_ANIM) {
            animation = fromSupport.supportDelegate.exitAnim
            if (animation == null) {
                animation = object : Animation() {
                }
            }
        } else if (popAnim == 0) {
            animation = object : Animation() {
            }
        } else {
            animation = AnimationUtils.loadAnimation(mActivity, popAnim)
        }

        fromView.startAnimation(animation)
        mHandler.postDelayed({
            try {
                mock.removeViewInLayout(fromView)
                container.removeViewInLayout(mock)
            } catch (ignored: Exception) {
            }
        }, animation!!.duration)
    }


    private fun mockStartWithPopAnim(
        from: ISupportFragment,
        to: ISupportFragment,
        exitAnim: Animation?
    ) {
        val fromF = from as Fragment
        val container = findContainerById(fromF, from.supportDelegate.mContainerId) ?: return

        val fromView = fromF!!.view ?: return

        container.removeViewInLayout(fromView)
        val mock = addMockView(fromView, container)

        to.supportDelegate.mEnterAnimListener = object : EnterAnimListener {
            override fun onEnterAnimStart() {
                fromView.startAnimation(exitAnim)
                mHandler.postDelayed({
                    try {
                        mock.removeViewInLayout(fromView)
                        container.removeViewInLayout(mock)
                    } catch (ignored: Exception) {
                    }
                }, exitAnim!!.duration)
            }
        }
    }

    private fun addMockView(fromView: View, container: ViewGroup): ViewGroup {
        val mock: ViewGroup = object : ViewGroup(mActivity) {
            override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
            }
        }

        mock.addView(fromView)
        container.addView(mock)
        return mock
    }

    private fun findContainerById(fragment: Fragment?, containerId: Int): ViewGroup? {
        if (fragment!!.view == null) return null

        val container: View?
        val parentFragment = fragment.parentFragment
        container = if (parentFragment != null) {
            if (parentFragment.view != null) {
                parentFragment.view!!.findViewById<View>(containerId)
            } else {
                findContainerById(parentFragment, containerId)
            }
        } else {
            mActivity.findViewById(containerId)
        }

        if (container is ViewGroup) {
            return container
        }

        return null
    }

    private fun handleAfterSaveInStateTransactionException(fm: FragmentManager?, action: String) {
        val stateSaved = isStateSaved(fm)
        if (stateSaved) {
            val e = AfterSaveStateTransactionWarning(action)
            if (Fragmentation.default!!.handler != null) {
                Fragmentation.Companion.default!!.handler!!.onException(e)
            }
        }
    }

    companion object {
        const val DEFAULT_POPTO_ANIM: Int = Int.MAX_VALUE

        private const val TAG = "Fragmentation"

        const val FRAGMENTATION_ARG_RESULT_RECORD: String = "fragment_arg_result_record"
        const val FRAGMENTATION_ARG_ROOT_STATUS: String = "fragmentation_arg_root_status"
        const val FRAGMENTATION_ARG_IS_SHARED_ELEMENT: String =
            "fragmentation_arg_is_shared_element"
        const val FRAGMENTATION_ARG_CONTAINER: String = "fragmentation_arg_container"
        const val FRAGMENTATION_ARG_REPLACE: String = "fragmentation_arg_replace"
        const val FRAGMENTATION_ARG_CUSTOM_ENTER_ANIM: String =
            "fragmentation_arg_custom_enter_anim"
        const val FRAGMENTATION_ARG_CUSTOM_EXIT_ANIM: String = "fragmentation_arg_custom_exit_anim"
        const val FRAGMENTATION_ARG_CUSTOM_POP_EXIT_ANIM: String =
            "fragmentation_arg_custom_pop_exit_anim"

        const val FRAGMENTATION_STATE_SAVE_ANIMATOR: String = "fragmentation_state_save_animator"
        const val FRAGMENTATION_STATE_SAVE_IS_HIDDEN: String = "fragmentation_state_save_status"

        private const val FRAGMENTATION_STATE_SAVE_RESULT = "fragmentation_state_save_result"

        const val TYPE_ADD: Int = 0
        const val TYPE_ADD_RESULT: Int = 1
        const val TYPE_ADD_WITHOUT_HIDE: Int = 2
        const val TYPE_ADD_RESULT_WITHOUT_HIDE: Int = 3
        const val TYPE_REPLACE: Int = 10
        const val TYPE_REPLACE_DONT_BACK: Int = 11

        private fun <T> checkNotNull(value: T?, message: String) {
            if (value == null) {
                throw NullPointerException(message)
            }
        }
    }
}
