package com.kkkcut.e20j.ui.fragment.blankcut;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class BlankCutBean implements Parcelable {
    public static final Parcelable.Creator<BlankCutBean> CREATOR = new Parcelable.Creator<BlankCutBean>() { // from class: com.kkkcut.e20j.ui.fragment.blankcut.BlankCutBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BlankCutBean createFromParcel(Parcel parcel) {
            return new BlankCutBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BlankCutBean[] newArray(int i) {
            return new BlankCutBean[i];
        }
    };
    private BlankCutType blankCutType;
    private int drawRes;
    private boolean isChecked;
    private String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BlankCutBean(BlankCutType blankCutType, String str) {
        this.blankCutType = blankCutType;
        this.name = str;
    }

    public BlankCutBean(BlankCutType blankCutType, String str, int i) {
        this.blankCutType = blankCutType;
        this.name = str;
        this.drawRes = i;
    }

    public BlankCutType getBlankCutType() {
        return this.blankCutType;
    }

    public void setModifyType(BlankCutType blankCutType) {
        this.blankCutType = blankCutType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public int getDrawRes() {
        return this.drawRes;
    }

    public void setDrawRes(int i) {
        this.drawRes = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        BlankCutType blankCutType = this.blankCutType;
        parcel.writeInt(blankCutType == null ? -1 : blankCutType.ordinal());
        parcel.writeString(this.name);
        parcel.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.drawRes);
    }

    public void readFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        this.blankCutType = readInt == -1 ? null : BlankCutType.values()[readInt];
        this.name = parcel.readString();
        this.isChecked = parcel.readByte() != 0;
        this.drawRes = parcel.readInt();
    }

    protected BlankCutBean(Parcel parcel) {
        int readInt = parcel.readInt();
        this.blankCutType = readInt == -1 ? null : BlankCutType.values()[readInt];
        this.name = parcel.readString();
        this.isChecked = parcel.readByte() != 0;
        this.drawRes = parcel.readInt();
    }
}
