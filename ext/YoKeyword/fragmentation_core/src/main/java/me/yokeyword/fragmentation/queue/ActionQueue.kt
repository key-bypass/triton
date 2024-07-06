package me.yokeyword.fragmentation.queue

import android.os.Handler
import android.os.Looper
import me.yokeyword.fragmentation.SupportHelper
import java.util.LinkedList
import java.util.Queue

/**
 * The queue of perform action.
 *
 *
 * Created by YoKey on 17/12/29.
 */
class ActionQueue(private val mMainHandler: Handler) {
    private val mQueue: Queue<Action> = LinkedList()

    fun enqueue(action: Action) {
        if (isThrottleBACK(action)) return

        if (action.action == Action.Companion.ACTION_LOAD && mQueue.isEmpty() && Thread.currentThread() === Looper.getMainLooper().thread) {
            action.run()
            return
        }

        mMainHandler.post { enqueueAction(action) }
    }

    private fun enqueueAction(action: Action) {
        mQueue.add(action)
        if (mQueue.size == 1) {
            handleAction()
        }
    }

    private fun handleAction() {
        if (mQueue.isEmpty()) return

        val action = mQueue.peek()
        action.run()

        executeNextAction(action)
    }

    private fun executeNextAction(action: Action) {
        if (action.action == Action.Companion.ACTION_POP) {
            val top = SupportHelper.getBackStackTopFragment(action.fragmentManager)
            action.duration = top?.supportDelegate?.exitAnimDuration
                ?: Action.Companion.DEFAULT_POP_TIME
        }

        mMainHandler.postDelayed({
            mQueue.poll()
            handleAction()
        }, action.duration)
    }

    private fun isThrottleBACK(action: Action): Boolean {
        if (action.action == Action.Companion.ACTION_BACK) {
            val head = mQueue.peek()
            if (head != null && head.action == Action.Companion.ACTION_POP) {
                return true
            }
        }
        return false
    }
}
