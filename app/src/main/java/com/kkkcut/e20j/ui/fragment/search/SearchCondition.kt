package com.kkkcut.e20j.ui.fragment.search;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class SearchCondition implements Parcelable {
    public static final Parcelable.Creator<SearchCondition> CREATOR = new Parcelable.Creator<SearchCondition>() { // from class: com.kkkcut.e20j.ui.fragment.search.SearchCondition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchCondition createFromParcel(Parcel parcel) {
            return new SearchCondition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchCondition[] newArray(int i) {
            return new SearchCondition[i];
        }
    };
    private String keyBlank;
    private String keyBlankManu;
    private String kid;
    private String lockManu;
    private String lockSys;
    private String silcaCard;
    private String silcaSN;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getKid() {
        return this.kid;
    }

    public void setKid(String str) {
        this.kid = str;
    }

    public String getSilcaCard() {
        return this.silcaCard;
    }

    public void setSilcaCard(String str) {
        this.silcaCard = str;
    }

    public String getSilcaSN() {
        return this.silcaSN;
    }

    public void setSilcaSN(String str) {
        this.silcaSN = str;
    }

    public String getKeyBlankManu() {
        return this.keyBlankManu;
    }

    public void setKeyBlankManu(String str) {
        this.keyBlankManu = str;
    }

    public String getKeyBlank() {
        return this.keyBlank;
    }

    public void setKeyBlank(String str) {
        this.keyBlank = str;
    }

    public String getLockManu() {
        return this.lockManu;
    }

    public void setLockManu(String str) {
        this.lockManu = str;
    }

    public String getLockSys() {
        return this.lockSys;
    }

    public void setLockSys(String str) {
        this.lockSys = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.kid);
        parcel.writeString(this.silcaCard);
        parcel.writeString(this.silcaSN);
        parcel.writeString(this.keyBlankManu);
        parcel.writeString(this.keyBlank);
        parcel.writeString(this.lockManu);
        parcel.writeString(this.lockSys);
    }

    public void readFromParcel(Parcel parcel) {
        this.kid = parcel.readString();
        this.silcaCard = parcel.readString();
        this.silcaSN = parcel.readString();
        this.keyBlankManu = parcel.readString();
        this.keyBlank = parcel.readString();
        this.lockManu = parcel.readString();
        this.lockSys = parcel.readString();
    }

    public SearchCondition() {
    }

    protected SearchCondition(Parcel parcel) {
        this.kid = parcel.readString();
        this.silcaCard = parcel.readString();
        this.silcaSN = parcel.readString();
        this.keyBlankManu = parcel.readString();
        this.keyBlank = parcel.readString();
        this.lockManu = parcel.readString();
        this.lockSys = parcel.readString();
    }

    public String toString() {
        return "SearchCondition{kid='" + this.kid + "', silcaCard='" + this.silcaCard + "', silcaSN='" + this.silcaSN + "', keyBlankManu='" + this.keyBlankManu + "', keyBlank='" + this.keyBlank + "', lockManu='" + this.lockManu + "', lockSys='" + this.lockSys + "'}";
    }
}
