package com.kkkcut.e20j.bean.gsonBean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes.dex */
public class LackToothRes {
    private String Code;
    private List<List<DataBean>> Data;
    private String Msg;

    public String getCode() {
        return this.Code;
    }

    public void setCode(String str) {
        this.Code = str;
    }

    public String getMsg() {
        return this.Msg;
    }

    public void setMsg(String str) {
        this.Msg = str;
    }

    public List<List<DataBean>> getData() {
        return this.Data;
    }

    public void setData(List<List<DataBean>> list) {
        this.Data = list;
    }

    /* loaded from: classes.dex */
    public static class DataBean implements Parcelable {
        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() { // from class: com.kkkcut.e20j.bean.gsonBean.LackToothRes.DataBean.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DataBean createFromParcel(Parcel parcel) {
                return new DataBean(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public DataBean[] newArray(int i) {
                return new DataBean[i];
            }
        };
        private String Bitting;
        private String Code;
        private boolean isChecked;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getCode() {
            return this.Code;
        }

        public void setCode(String str) {
            this.Code = str;
        }

        public String getBitting() {
            return this.Bitting;
        }

        public void setBitting(String str) {
            this.Bitting = str;
        }

        public boolean isChecked() {
            return this.isChecked;
        }

        public void setChecked(boolean z) {
            this.isChecked = z;
        }

        public DataBean() {
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.Code);
            parcel.writeString(this.Bitting);
            parcel.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        }

        public void readFromParcel(Parcel parcel) {
            this.Code = parcel.readString();
            this.Bitting = parcel.readString();
            this.isChecked = parcel.readByte() != 0;
        }

        protected DataBean(Parcel parcel) {
            this.Code = parcel.readString();
            this.Bitting = parcel.readString();
            this.isChecked = parcel.readByte() != 0;
        }
    }
}
