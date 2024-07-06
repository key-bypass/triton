package me.yokeyword.fragmentation

import androidx.annotation.IntDef
import me.yokeyword.fragmentation.helper.ExceptionHandler
import kotlin.concurrent.Volatile

/**
 * Created by YoKey on 17/2/5.
 */
class Fragmentation internal constructor(builder: FragmentationBuilder) {
    var isDebug: Boolean = builder.debug
    var mode: Int = BUBBLE
    var handler: ExceptionHandler?

    @IntDef(*[NONE, SHAKE, BUBBLE])
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class StackViewMode

    init {
        mode = if (isDebug) {
            builder.mode
        } else {
            NONE
        }
        handler = builder.handler
    }

    class FragmentationBuilder {
        var debug: Boolean = false
        var mode: Int = 0
        var handler: ExceptionHandler? = null

        /**
         * @param debug Suppressed Exception("Can not perform this action after onSaveInstanceState!") when debug=false
         */
        fun debug(debug: Boolean): FragmentationBuilder {
            this.debug = debug
            return this
        }

        /**
         * Sets the mode to display the stack view
         *
         *
         * None if debug(false).
         *
         *
         * Default:NONE
         */
        fun stackViewMode(@StackViewMode mode: Int): FragmentationBuilder {
            this.mode = mode
            return this
        }

        /**
         * @param handler Handled Exception("Can not perform this action after onSaveInstanceState!") when debug=false.
         */
        fun handleException(handler: ExceptionHandler?): FragmentationBuilder {
            this.handler = handler
            return this
        }

        fun install(): Fragmentation? {
            INSTANCE = Fragmentation(this)
            return INSTANCE
        }
    }

    companion object {
        /**
         * Dont display stack view.
         */
        const val NONE: Int = 0

        /**
         * Shake it to display stack view.
         */
        const val SHAKE: Int = 1

        /**
         * As a bubble display stack view.
         */
        const val BUBBLE: Int = 2

        @Volatile
        var INSTANCE: Fragmentation? = null

        val default: Fragmentation?
            get() {
                if (INSTANCE == null) {
                    synchronized(Fragmentation::class.java) {
                        if (INSTANCE == null) {
                            INSTANCE = Fragmentation(FragmentationBuilder())
                        }
                    }
                }
                return INSTANCE
            }

        fun builder(): FragmentationBuilder {
            return FragmentationBuilder()
        }
    }
}
