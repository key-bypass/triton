package com.kkkcut.e20j.DbBean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ModelSeries extends KeyInfoBase implements Parcelable {
    public static final Parcelable.Creator<ModelSeries> CREATOR = new Parcelable.Creator<ModelSeries>() { // from class: com.kkkcut.e20j.DbBean.ModelSeries.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModelSeries createFromParcel(Parcel parcel) {
            return new ModelSeries(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModelSeries[] newArray(int i) {
            return new ModelSeries[i];
        }
    };
    int FK_ModelYearID;
    String ISN;
    String Notes;
    String Remark;
    int Sort;
    String codeSeries;
    String cuts;
    int fK_KeyID;
    String modelNo;
    int modelSeriesID;
    String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.chad.library.adapter.base.entity.MultiItemEntity
    public int getItemType() {
        return 0;
    }

    @Override // com.kkkcut.e20j.customView.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    public ModelSeries(int i, int i2, String str, String str2, String str3, int i3, String str4, String str5, String str6, int i4, String str7) {
        this.modelSeriesID = i;
        this.FK_ModelYearID = i2;
        this.codeSeries = str;
        this.name = str2;
        this.modelNo = str3;
        this.fK_KeyID = i3;
        this.cuts = str4;
        this.ISN = str5;
        this.Notes = str6;
        this.Sort = i4;
        this.Remark = str7;
    }

    public ModelSeries() {
    }

    public int getModelSeriesID() {
        return this.modelSeriesID;
    }

    public void setModelSeriesID(int i) {
        this.modelSeriesID = i;
    }

    public int getFK_ModelYearID() {
        return this.FK_ModelYearID;
    }

    public void setFK_ModelYearID(int i) {
        this.FK_ModelYearID = i;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getModelNo() {
        return this.modelNo;
    }

    public void setModelNo(String str) {
        this.modelNo = str;
    }

    public int getFK_KeyID() {
        return this.fK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.fK_KeyID = i;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public String getISN() {
        return this.ISN;
    }

    public void setISN(String str) {
        this.ISN = str;
    }

    public String getNotes() {
        return this.Notes;
    }

    public void setNotes(String str) {
        this.Notes = str;
    }

    public String getRemark() {
        return this.Remark;
    }

    public void setRemark(String str) {
        this.Remark = str;
    }

    public int getSort() {
        return this.Sort;
    }

    public void setSort(int i) {
        this.Sort = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.modelSeriesID);
        parcel.writeInt(this.FK_ModelYearID);
        parcel.writeString(this.codeSeries);
        parcel.writeString(this.modelNo);
        parcel.writeInt(this.fK_KeyID);
        parcel.writeString(this.cuts);
        parcel.writeString(this.ISN);
        parcel.writeString(this.Notes);
        parcel.writeInt(this.Sort);
        parcel.writeString(this.Remark);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    protected ModelSeries(Parcel parcel) {
        this.modelSeriesID = parcel.readInt();
        this.FK_ModelYearID = parcel.readInt();
        this.codeSeries = parcel.readString();
        this.modelNo = parcel.readString();
        this.fK_KeyID = parcel.readInt();
        this.cuts = parcel.readString();
        this.ISN = parcel.readString();
        this.Notes = parcel.readString();
        this.Sort = parcel.readInt();
        this.Remark = parcel.readString();
    }

    @Generated(hash = 1637681422)
    public ModelSeries(int FK_ModelYearID, String ISN, String Notes, String Remark, int Sort, String codeSeries, String cuts, int fK_KeyID, String modelNo,
            int modelSeriesID, String name) {
        this.FK_ModelYearID = FK_ModelYearID;
        this.ISN = ISN;
        this.Notes = Notes;
        this.Remark = Remark;
        this.Sort = Sort;
        this.codeSeries = codeSeries;
        this.cuts = cuts;
        this.fK_KeyID = fK_KeyID;
        this.modelNo = modelNo;
        this.modelSeriesID = modelSeriesID;
        this.name = name;
    }
}
