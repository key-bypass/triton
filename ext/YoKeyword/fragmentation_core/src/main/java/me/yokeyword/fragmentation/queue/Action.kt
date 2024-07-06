package me.yokeyword.fragmentation.queue

import androidx.fragment.app.FragmentManager

/**
 * Created by YoKey on 17/12/28.
 */
abstract class Action {
    var fragmentManager: FragmentManager? = null
    var action: Int = ACTION_NORMAL
    var duration: Long = 0

    constructor()

    constructor(action: Int) {
        this.action = action
    }

    constructor(action: Int, fragmentManager: FragmentManager?) : this(action) {
        this.fragmentManager = fragmentManager
    }

    abstract fun run()

    companion object {
        const val DEFAULT_POP_TIME: Long = 300L

        const val ACTION_NORMAL: Int = 0
        const val ACTION_POP: Int = 1
        const val ACTION_POP_MOCK: Int = 2
        const val ACTION_BACK: Int = 3
        const val ACTION_LOAD: Int = 4
    }
}
