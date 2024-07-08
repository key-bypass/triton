package com.kkkcut.e20j.ui.fragment.blankcut

import android.os.Parcel
import android.os.Parcelable

/* loaded from: classes.dex */
class BlankCutBean : Parcelable {
    var blankCutType: BlankCutType?
        private set
    @JvmField
    var drawRes: Int = 0
    @JvmField
    var isChecked: Boolean = false
    @JvmField
    var name: String?

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }

    constructor(blankCutType: BlankCutType?, str: String?) {
        this.blankCutType = blankCutType
        this.name = str
    }

    constructor(blankCutType: BlankCutType?, str: String?, i: Int) {
        this.blankCutType = blankCutType
        this.name = str
        this.drawRes = i
    }

    fun setModifyType(blankCutType: BlankCutType?) {
        this.blankCutType = blankCutType
    }

    // android.os.Parcelable
    override fun writeToParcel(parcel: Parcel, i: Int) {
        val blankCutType = this.blankCutType
        parcel.writeInt(blankCutType?.ordinal ?: -1)
        parcel.writeString(this.name)
        parcel.writeByte(if (this.isChecked) 1.toByte() else 0.toByte())
        parcel.writeInt(this.drawRes)
    }

    fun readFromParcel(parcel: Parcel) {
        val readInt = parcel.readInt()
        this.blankCutType = if (readInt == -1) null else BlankCutType.entries[readInt]
        this.name = parcel.readString()
        this.isChecked = parcel.readByte().toInt() != 0
        this.drawRes = parcel.readInt()
    }

    protected constructor(parcel: Parcel) {
        val readInt = parcel.readInt()
        this.blankCutType = if (readInt == -1) null else BlankCutType.entries[readInt]
        this.name = parcel.readString()
        this.isChecked = parcel.readByte().toInt() != 0
        this.drawRes = parcel.readInt()
    }

    companion object {
        val CREATOR: Parcelable.Creator<BlankCutBean?> = object : Parcelable.Creator<BlankCutBean?> {
            // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            // android.os.Parcelable.Creator
            override fun createFromParcel(parcel: Parcel): BlankCutBean? {
                return BlankCutBean(parcel)
            }

            /* JADX WARN: Can't rename method to resolve collision */
            // android.os.Parcelable.Creator
            override fun newArray(i: Int): Array<BlankCutBean?> {
                return arrayOfNulls(i)
            }
        }
    }
}
