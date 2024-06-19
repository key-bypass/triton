package com.kkkcut.e20j.DbBean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ClampKeyBasicData implements Parcelable {
    public static final Parcelable.Creator<ClampKeyBasicData> CREATOR = new Parcelable.Creator<ClampKeyBasicData>() { // from class: com.kkkcut.e20j.DbBean.ClampKeyBasicData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClampKeyBasicData createFromParcel(Parcel parcel) {
            return new ClampKeyBasicData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClampKeyBasicData[] newArray(int i) {
            return new ClampKeyBasicData[i];
        }
    };
    String ClampNum;
    String ClampSide;
    String ClampSlot;
    Long FK_KeyID;
    int id;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ClampKeyBasicData(int i, Long l, String str, String str2, String str3) {
        this.id = i;
        this.FK_KeyID = l;
        this.ClampNum = str;
        this.ClampSide = str2;
        this.ClampSlot = str3;
    }

    public ClampKeyBasicData() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public Long getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(Long l) {
        this.FK_KeyID = l;
    }

    public String getClampNum() {
        return this.ClampNum;
    }

    public void setClampNum(String str) {
        this.ClampNum = str;
    }

    public String getClampSide() {
        return this.ClampSide;
    }

    public void setClampSide(String str) {
        this.ClampSide = str;
    }

    public String getClampSlot() {
        return this.ClampSlot;
    }

    public void setClampSlot(String str) {
        this.ClampSlot = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeValue(this.FK_KeyID);
        parcel.writeString(this.ClampNum);
        parcel.writeString(this.ClampSide);
        parcel.writeString(this.ClampSlot);
    }

    protected ClampKeyBasicData(Parcel parcel) {
        this.id = parcel.readInt();
        this.FK_KeyID = (Long) parcel.readValue(Long.class.getClassLoader());
        this.ClampNum = parcel.readString();
        this.ClampSide = parcel.readString();
        this.ClampSlot = parcel.readString();
    }
}
