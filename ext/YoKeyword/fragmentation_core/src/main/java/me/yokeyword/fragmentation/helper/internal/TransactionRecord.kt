package me.yokeyword.fragmentation.helper.internal

import android.view.View

/**
 * @hide Created by YoKey on 16/11/25.
 */
class TransactionRecord {
    var tag: String? = null
    var targetFragmentEnter: Int = Int.MIN_VALUE
    var currentFragmentPopExit: Int = Int.MIN_VALUE
    var currentFragmentPopEnter: Int = Int.MIN_VALUE
    var targetFragmentExit: Int = Int.MIN_VALUE
    var dontAddToBackStack: Boolean = false
    var sharedElementList: ArrayList<SharedElement?>? = null

    class SharedElement(var sharedElement: View, var sharedName: String)
}
