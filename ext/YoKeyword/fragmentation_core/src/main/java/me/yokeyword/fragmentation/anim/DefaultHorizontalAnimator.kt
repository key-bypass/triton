package me.yokeyword.fragmentation.anim

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.yokeyword.fragmentation.R

/**
 * Created by YoKeyword on 16/2/5.
 */
@Parcelize
class DefaultHorizontalAnimator() : FragmentAnimator(), Parcelable {

    override fun describeContents(): Int {
        return 0
    }

}
