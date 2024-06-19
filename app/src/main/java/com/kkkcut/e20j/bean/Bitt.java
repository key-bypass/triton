package com.kkkcut.e20j.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Bitt implements Parcelable {
    public static final Parcelable.Creator<Bitt> CREATOR = new Parcelable.Creator<Bitt>() { // from class: com.kkkcut.e20j.bean.Bitt.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bitt createFromParcel(Parcel parcel) {
            return new Bitt(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Bitt[] newArray(int i) {
            return new Bitt[i];
        }
    };
    private String coloum;
    private String depthName;
    private String realDepth;
    private String row;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bitt(String str, String str2, String str3, String str4) {
        this.row = str;
        this.coloum = str2;
        this.realDepth = str3;
        this.depthName = str4;
    }

    public String getRow() {
        return this.row;
    }

    public String getColoum() {
        return this.coloum;
    }

    public String getRealDepth() {
        return this.realDepth;
    }

    public String getDepthName() {
        return this.depthName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.row);
        parcel.writeString(this.coloum);
        parcel.writeString(this.realDepth);
        parcel.writeString(this.depthName);
    }

    protected Bitt(Parcel parcel) {
        this.row = parcel.readString();
        this.coloum = parcel.readString();
        this.realDepth = parcel.readString();
        this.depthName = parcel.readString();
    }

    public String toString() {
        return "Bitt{row='" + this.row + "', coloum='" + this.coloum + "', realDepth='" + this.realDepth + "', depthName='" + this.depthName + "'}";
    }
}
