package com.kkkcut.e20j.ui.fragment.search

import android.os.Parcel
import android.os.Parcelable

/* loaded from: classes.dex */
class SearchCondition : Parcelable {
    @JvmField
    var keyBlank: String? = null
    @JvmField
    var keyBlankManu: String? = null
    @JvmField
    var kid: String? = null
    @JvmField
    var lockManu: String? = null
    @JvmField
    var lockSys: String? = null
    @JvmField
    var silcaCard: String? = null
    @JvmField
    var silcaSN: String? = null

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // android.os.Parcelable
    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(this.kid)
        parcel.writeString(this.silcaCard)
        parcel.writeString(this.silcaSN)
        parcel.writeString(this.keyBlankManu)
        parcel.writeString(this.keyBlank)
        parcel.writeString(this.lockManu)
        parcel.writeString(this.lockSys)
    }

    fun readFromParcel(parcel: Parcel) {
        this.kid = parcel.readString()
        this.silcaCard = parcel.readString()
        this.silcaSN = parcel.readString()
        this.keyBlankManu = parcel.readString()
        this.keyBlank = parcel.readString()
        this.lockManu = parcel.readString()
        this.lockSys = parcel.readString()
    }

    constructor()

    protected constructor(parcel: Parcel) {
        this.kid = parcel.readString()
        this.silcaCard = parcel.readString()
        this.silcaSN = parcel.readString()
        this.keyBlankManu = parcel.readString()
        this.keyBlank = parcel.readString()
        this.lockManu = parcel.readString()
        this.lockSys = parcel.readString()
    }

    override fun toString(): String {
        return "SearchCondition{kid='" + this.kid + "', silcaCard='" + this.silcaCard + "', silcaSN='" + this.silcaSN + "', keyBlankManu='" + this.keyBlankManu + "', keyBlank='" + this.keyBlank + "', lockManu='" + this.lockManu + "', lockSys='" + this.lockSys + "'}"
    }

    companion object {
        val CREATOR: Parcelable.Creator<SearchCondition?> =
            object : Parcelable.Creator<SearchCondition?> {
                // from class: com.kkkcut.e20j.ui.fragment.search.SearchCondition.1
                /* JADX WARN: Can't rename method to resolve collision */
                // android.os.Parcelable.Creator
                override fun createFromParcel(parcel: Parcel): SearchCondition? {
                    return SearchCondition(parcel)
                }

                /* JADX WARN: Can't rename method to resolve collision */
                // android.os.Parcelable.Creator
                override fun newArray(i: Int): Array<SearchCondition?> {
                    return arrayOfNulls(i)
                }
            }
    }
}
