package me.yokeyword.fragmentation.anim

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by YoKeyword on 16/2/15.
 */
class DefaultNoAnimator : FragmentAnimator, Parcelable {
    constructor() {
        enter = 0
        exit = 0
        popEnter = 0
        popExit = 0
    }

    protected constructor(`in`: Parcel) : super(`in`)

    override fun writeToParcel(dest: Parcel, flags: Int) {
        super.writeToParcel(dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR: Parcelable.Creator<DefaultNoAnimator?> =
            object : Parcelable.Creator<DefaultNoAnimator?> {
                override fun createFromParcel(`in`: Parcel): DefaultNoAnimator? {
                    return DefaultNoAnimator(`in`)
                }

                override fun newArray(size: Int): Array<DefaultNoAnimator?> {
                    return arrayOfNulls(size)
                }
            }
    }
}
