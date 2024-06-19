package com.kkkcut.e20j.DbBean.userDB;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class JpushMsg implements Parcelable {
    public static final Parcelable.Creator<JpushMsg> CREATOR = new Parcelable.Creator<JpushMsg>() { // from class: com.kkkcut.e20j.DbBean.userDB.JpushMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JpushMsg createFromParcel(Parcel parcel) {
            return new JpushMsg(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JpushMsg[] newArray(int i) {
            return new JpushMsg[i];
        }
    };
    String content;
    String data;
    String extra;
    int haveRead;
    Long id;
    String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeString(this.extra);
        parcel.writeString(this.data);
    }

    public int getHaveRead() {
        return this.haveRead;
    }

    public void setHaveRead(int i) {
        this.haveRead = i;
    }

    protected JpushMsg(Parcel parcel) {
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.extra = parcel.readString();
        this.data = parcel.readString();
    }

    public JpushMsg(Long l, String str, String str2, String str3, String str4, int i) {
        this.id = l;
        this.title = str;
        this.content = str2;
        this.extra = str3;
        this.data = str4;
        this.haveRead = i;
    }

    public JpushMsg() {
    }
}
