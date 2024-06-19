package com.kkkcut.e20j.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.kkkcut.e20j.bean.gsonBean.LackToothRes;

/* loaded from: classes.dex */
public class CodeAndTooth extends SectionEntity<LackToothRes.DataBean> implements Parcelable {
    public static final Parcelable.Creator<CodeAndTooth> CREATOR = new Parcelable.Creator<CodeAndTooth>() { // from class: com.kkkcut.e20j.bean.CodeAndTooth.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CodeAndTooth createFromParcel(Parcel parcel) {
            return new CodeAndTooth(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CodeAndTooth[] newArray(int i) {
            return new CodeAndTooth[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public CodeAndTooth(boolean z, String str) {
        super(z, str);
    }

    public CodeAndTooth(LackToothRes.DataBean dataBean) {
        super(dataBean);
    }

    protected CodeAndTooth(Parcel parcel) {
        this(false, "1");
    }
}
