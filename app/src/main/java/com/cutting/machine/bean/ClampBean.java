package com.cutting.machine.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ClampBean implements Parcelable {
    public static final Parcelable.Creator<ClampBean> CREATOR = new Parcelable.Creator<ClampBean>() { // from class: com.cutting.machine.bean.ClampBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClampBean createFromParcel(Parcel parcel) {
            return new ClampBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClampBean[] newArray(int i) {
            return new ClampBean[i];
        }
    };
    String ClampNum;
    String ClampSide;
    String ClampSlot;
    Long FK_KeyID;

    /* renamed from: id */
    int f380id;

    public ClampBean() {
    }

    protected ClampBean(Parcel parcel) {
        this.f380id = parcel.readInt();
        this.FK_KeyID = (Long) parcel.readValue(Long.class.getClassLoader());
        this.ClampNum = parcel.readString();
        this.ClampSide = parcel.readString();
        this.ClampSlot = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f380id);
        parcel.writeValue(this.FK_KeyID);
        parcel.writeString(this.ClampNum);
        parcel.writeString(this.ClampSide);
        parcel.writeString(this.ClampSlot);
    }

    public int getId() {
        return this.f380id;
    }

    public void setId(int i) {
        this.f380id = i;
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
}
