package com.kkkcut.e20j.DbBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.kkkcut.e20j.DbBean.china.ModelSeriesChina;
import com.kkkcut.e20j.DbBean.search.ChinaNumSearch;
import com.kkkcut.e20j.DbBean.userDB.CollectionData;
import com.kkkcut.e20j.DbBean.userDB.CustomKey;
import com.kkkcut.e20j.DbBean.userDB.CutHistoryData;

/* loaded from: classes.dex */
public class GoOperatBean implements Parcelable {
    public static final Parcelable.Creator<GoOperatBean> CREATOR = new Parcelable.Creator<GoOperatBean>() { // from class: com.kkkcut.e20j.DbBean.GoOperatBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GoOperatBean createFromParcel(Parcel parcel) {
            return new GoOperatBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GoOperatBean[] newArray(int i) {
            return new GoOperatBean[i];
        }
    };
    String KeyChinaNum;
    String codeSeries;
    String cuts;
    private boolean doorIgnition;
    private boolean doorToIgnition;
    boolean isCustomkey;
    String isn;
    int keyID;
    String remark;
    int seriesID;
    String title;
    String toothCode;
    String toothCodeSide;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GoOperatBean(ModelSeries modelSeries, String str, String str2) {
        this(modelSeries, str);
        this.isn = str2;
    }

    public GoOperatBean(ModelSeries modelSeries, String str) {
        this.codeSeries = modelSeries.getCodeSeries();
        this.seriesID = modelSeries.getModelSeriesID();
        this.keyID = modelSeries.getFK_KeyID();
        this.cuts = modelSeries.getCuts();
        this.title = str;
    }

    public GoOperatBean(ModelSeriesChina modelSeriesChina, String str) {
        this.codeSeries = modelSeriesChina.getCodeSeries();
        this.seriesID = modelSeriesChina.getID();
        this.KeyChinaNum = modelSeriesChina.getKeyChinaNum();
        this.cuts = modelSeriesChina.getCuts();
        this.keyID = (int) modelSeriesChina.getFK_KeyID();
        this.title = str;
    }

    public GoOperatBean(ChinaNumSearch chinaNumSearch, String str) {
        this.codeSeries = chinaNumSearch.getCodeSeries();
        this.seriesID = chinaNumSearch.getSeriesID();
        this.KeyChinaNum = chinaNumSearch.getKeyChinaNum();
        this.cuts = chinaNumSearch.getCuts();
        this.keyID = chinaNumSearch.getfK_KeyID();
        this.title = str;
    }

    public GoOperatBean(CollectionData collectionData) {
        this.codeSeries = collectionData.getCodeSeries();
        this.seriesID = collectionData.getSeriesID();
        this.KeyChinaNum = collectionData.getKeyChinaNum();
        this.keyID = collectionData.getBasicDataID();
        this.cuts = collectionData.getCuts();
        this.title = collectionData.getTitle();
        this.toothCode = collectionData.getToothCode();
    }

    public GoOperatBean(CutHistoryData cutHistoryData) {
        this.codeSeries = cutHistoryData.getCodeSeries();
        this.seriesID = cutHistoryData.getSeriesID();
        this.KeyChinaNum = cutHistoryData.getKeyChinaNum();
        this.keyID = cutHistoryData.getBasicDataID();
        this.cuts = cutHistoryData.getCuts();
        this.title = cutHistoryData.getTitle();
        this.toothCode = cutHistoryData.getToothCode();
        this.toothCodeSide = cutHistoryData.getToothCodeSide();
        this.isCustomkey = cutHistoryData.getIsCustomKey() == 1;
        this.doorIgnition = cutHistoryData.getDoorIgnition() == 1;
        this.doorToIgnition = cutHistoryData.getDoorToIgnition() == 1;
    }

    public GoOperatBean(int i, String str) {
        this.keyID = i;
        this.title = str;
    }

    public GoOperatBean(CustomKey customKey) {
        this.keyID = customKey.getIcCard().intValue();
        this.title = customKey.getKeyname();
        this.isCustomkey = true;
    }

    public int getSeriesID() {
        return this.seriesID;
    }

    public void setSeriesID(int i) {
        this.seriesID = i;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getToothCode() {
        return this.toothCode;
    }

    public void setToothCode(String str) {
        this.toothCode = str;
    }

    public int getKeyID() {
        return this.keyID;
    }

    public String getCuts() {
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public boolean isCustomkey() {
        return this.isCustomkey;
    }

    public void setCustomkey(boolean z) {
        this.isCustomkey = z;
    }

    public boolean isDoorIgnition() {
        return this.doorIgnition;
    }

    public void setDoorIgnition(boolean z) {
        this.doorIgnition = z;
    }

    public boolean isDoorToIgnition() {
        return this.doorToIgnition;
    }

    public String getIsn() {
        return this.isn;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.seriesID);
        parcel.writeString(this.codeSeries);
        parcel.writeString(this.toothCode);
        parcel.writeInt(this.keyID);
        parcel.writeString(this.cuts);
        parcel.writeString(this.KeyChinaNum);
        parcel.writeString(this.title);
        parcel.writeString(this.remark);
        parcel.writeString(this.isn);
        parcel.writeByte(this.isCustomkey ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.doorIgnition ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.doorToIgnition ? (byte) 1 : (byte) 0);
    }

    protected GoOperatBean(Parcel parcel) {
        this.seriesID = parcel.readInt();
        this.codeSeries = parcel.readString();
        this.toothCode = parcel.readString();
        this.keyID = parcel.readInt();
        this.cuts = parcel.readString();
        this.KeyChinaNum = parcel.readString();
        this.title = parcel.readString();
        this.remark = parcel.readString();
        this.isn = parcel.readString();
        this.isCustomkey = parcel.readByte() != 0;
        this.doorIgnition = parcel.readByte() != 0;
        this.doorToIgnition = parcel.readByte() != 0;
    }

    public String getToothCodeSide() {
        return this.toothCodeSide;
    }

    public void setToothCodeSide(String str) {
        this.toothCodeSide = str;
    }
}
