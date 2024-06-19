package com.liying.core.operation.decode;

import android.os.Parcel;
import android.os.Parcelable;
import com.liying.core.bean.KeyInfo;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampUtil;
import com.liying.core.operation.BaseParam;

/* loaded from: classes2.dex */
public class DecodeParams extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<DecodeParams> CREATOR = new Parcelable.Creator<DecodeParams>() { // from class: com.liying.core.operation.decode.DecodeParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DecodeParams createFromParcel(Parcel parcel) {
            return new DecodeParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DecodeParams[] newArray(int i) {
            return new DecodeParams[i];
        }
    };
    private Clamp clamp;
    private int clampMode;
    private int decoderSize;
    private KeyInfo keyInfo;
    private int pauseTime;
    private boolean round;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public boolean isPlasticKey() {
        return false;
    }

    public DecodeParams() {
    }

    public DecodeParams(KeyInfo keyInfo, boolean z, int i) {
        this.keyInfo = keyInfo;
        this.round = z;
        this.decoderSize = i;
        this.clamp = ClampUtil.getClamp(keyInfo);
        this.clampMode = ClampUtil.getClampMode(keyInfo);
    }

    @Override // com.liying.core.communication.OperationParamBase
    public KeyInfo getKeyInfo() {
        return this.keyInfo;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
    }

    public boolean isRound() {
        return this.round;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public int getPauseTime() {
        return this.pauseTime;
    }

    public void setPauseTime(int i) {
        this.pauseTime = i;
    }

    public int getDecoderSize() {
        return this.decoderSize;
    }

    public void setDecoderSize(int i) {
        this.decoderSize = i;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public Clamp getClamp() {
        return this.clamp;
    }

    public void setClamp(Clamp clamp) {
        this.clamp = clamp;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public int getClampMode() {
        return this.clampMode;
    }

    public void setClampMode(int i) {
        this.clampMode = i;
    }

    public String toString() {
        return "DecodeParams{round=" + this.round + ", decodeSize=" + this.decoderSize + ", clamp=" + this.clamp + ", clampMode=" + this.clampMode + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.keyInfo, i);
        parcel.writeByte(this.round ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.decoderSize);
        Clamp clamp = this.clamp;
        parcel.writeInt(clamp == null ? -1 : clamp.ordinal());
        parcel.writeInt(this.clampMode);
        parcel.writeInt(this.pauseTime);
    }

    public void readFromParcel(Parcel parcel) {
        this.keyInfo = (KeyInfo) parcel.readParcelable(KeyInfo.class.getClassLoader());
        this.round = parcel.readByte() != 0;
        this.decoderSize = parcel.readInt();
        int readInt = parcel.readInt();
        this.clamp = readInt == -1 ? null : Clamp.values()[readInt];
        this.clampMode = parcel.readInt();
        this.pauseTime = parcel.readInt();
    }

    protected DecodeParams(Parcel parcel) {
        this.keyInfo = (KeyInfo) parcel.readParcelable(KeyInfo.class.getClassLoader());
        this.round = parcel.readByte() != 0;
        this.decoderSize = parcel.readInt();
        int readInt = parcel.readInt();
        this.clamp = readInt == -1 ? null : Clamp.values()[readInt];
        this.clampMode = parcel.readInt();
        this.pauseTime = parcel.readInt();
    }
}
