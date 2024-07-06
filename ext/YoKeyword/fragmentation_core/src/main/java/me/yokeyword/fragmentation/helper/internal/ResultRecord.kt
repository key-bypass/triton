package me.yokeyword.fragmentation.helper.internal

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @Hide
 * Result 记录
 * Created by YoKeyword on 16/6/2.
 */
@Parcelize
class ResultRecord : Parcelable {
    var requestCode: Int = 0
    var resultCode: Int = 0
    var resultBundle: Bundle? = null

    override fun describeContents(): Int {
        return 0
    }
}
