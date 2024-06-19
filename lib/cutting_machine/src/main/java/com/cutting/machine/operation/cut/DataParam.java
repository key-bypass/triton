package com.liying.core.operation.cut;

import android.os.Parcel;
import android.os.Parcelable;
import com.liying.core.bean.BaseCutParam;
import com.liying.core.bean.KeyInfo;
import com.liying.core.clamp.Clamp;
import com.liying.core.clamp.ClampUtil;

/* loaded from: classes2.dex */
public class DataParam extends BaseCutParam implements Parcelable {
    public static final Parcelable.Creator<DataParam> CREATOR = new Parcelable.Creator<DataParam>() { // from class: com.liying.core.operation.cut.DataParam.1
        @Override // android.os.Parcelable.Creator
        public DataParam createFromParcel(Parcel parcel) {
            return new DataParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DataParam[] newArray(int i) {
            return new DataParam[i];
        }
    };
    public static final int DimpleMother = 1;
    public static final int DimpleSon = 2;
    private boolean angleDimple;
    private int angleDimpleIndex;
    private String angleKeySpace;
    private String angleKeyTooth;
    private int cutDimpleSonOrMother;
    private boolean dimpleUpDownCut;
    private boolean fo21AutoCut;
    private int hu101ExtCutFix;
    private KeyInfo keyInfo;
    private int pauseTime;
    private boolean plastic;
    private boolean quickCut;
    private boolean round;
    private int singleSideKeyCutShape;
    private boolean slantCorrection;
    private String toothCodeReal;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setAngleDimple(boolean z) {
        this.angleDimple = z;
    }

    public void setAngleDimpleIndex(int i) {
        this.angleDimpleIndex = i;
    }

    public boolean isAngleDimple() {
        return this.angleDimple;
    }

    public int getAngleDimpleIndex() {
        return this.angleDimpleIndex;
    }

    public boolean isFo21AutoCut() {
        return this.fo21AutoCut;
    }

    public void setFo21AutoCut(boolean z) {
        this.fo21AutoCut = z;
    }

    public boolean isSlantCorrection() {
        return this.slantCorrection;
    }

    public void setSlantCorrection(boolean z) {
        this.slantCorrection = z;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public KeyInfo getKeyInfo() {
        return this.keyInfo;
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

    public boolean isQuickCut() {
        return this.quickCut;
    }

    @Override // com.liying.core.communication.OperationParamBase
    public boolean isPlasticKey() {
        return this.plastic;
    }

    public boolean isDimpleUpDownCut() {
        return this.dimpleUpDownCut;
    }

    public void setToothCodeReal(String str) {
        this.toothCodeReal = str;
    }

    public void setCutDimpleSonOrMother(int i) {
        this.cutDimpleSonOrMother = i;
    }

    public String getToothCodeReal() {
        return this.toothCodeReal;
    }

    public boolean singleSideKeyCutJagged() {
        return this.singleSideKeyCutShape == 2;
    }

    public String getAngleKeySpace() {
        return this.angleKeySpace;
    }

    public void setAngleKeySpace(String str) {
        this.angleKeySpace = str;
    }

    public String getAngleKeyTooth() {
        return this.angleKeyTooth;
    }

    public void setAngleKeyTooth(String str) {
        this.angleKeyTooth = str;
    }

    public int getHu101ExtCutFix() {
        return this.hu101ExtCutFix;
    }

    public void setHu101ExtCutFix(int i) {
        this.hu101ExtCutFix = i;
    }

    public void setKeyInfo(KeyInfo keyInfo) {
        this.keyInfo = keyInfo;
        setCutDepth(keyInfo.getCutDepth());
        setClamp(ClampUtil.getClamp(keyInfo));
        setClampMode(ClampUtil.getClampMode(keyInfo));
    }

    public void setQuickCut(boolean z) {
        this.quickCut = z;
    }

    public void setPlastic(boolean z) {
        this.plastic = z;
    }

    public void setDimpleUpDownCut(boolean z) {
        this.dimpleUpDownCut = z;
    }

    public void setSingleSideKeyCutShape(int i) {
        this.singleSideKeyCutShape = i;
    }

    public boolean isPlastic() {
        return this.plastic;
    }

    public int getSingleSideKeyCutShape() {
        return this.singleSideKeyCutShape;
    }

    public int getCutDimpleSonOrMother() {
        return this.cutDimpleSonOrMother;
    }

    public DataParam() {
        this.slantCorrection = true;
    }

    public DataParam(KeyInfo keyInfo, Clamp clamp, int i, int i2, int i3, boolean z, int i4, int i5, boolean z2, boolean z3, int i6, boolean z4, int i7, int i8, boolean z5, int i9) {
        this.slantCorrection = true;
        this.keyInfo = keyInfo;
        this.clamp = clamp;
        if (clamp == null) {
            this.clamp = ClampUtil.getClamp(keyInfo);
        }
        this.clampMode = ClampUtil.getClampMode(keyInfo);
        this.cutDepth = i;
        this.decoderSize = i2;
        this.cutterSize = i3;
        this.quickCut = z;
        this.cutSpeed = i4;
        this.layer = i5;
        this.plastic = z2;
        this.dimpleUpDownCut = z3;
        this.singleSideKeyCutShape = i6;
        this.highHandleMode = z4;
        this.singleSideKeyCutDepthFix = i7;
        this.hu101ExtCutFix = i8;
        this.angleDimple = z5;
        this.angleDimpleIndex = i9;
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private boolean angleDimple;
        private int angleDimpleIndex;
        private Clamp clamp;
        private int cutDepth;
        private int cutSpeed;
        private int cutterSize;
        private int decoderSize;
        private boolean dimpleUpDownCut;
        private boolean highHandleMode;
        private int hu101ExtCutFix;
        private KeyInfo keyInfo;
        private int layer;
        private boolean plastic;
        private boolean quickCut;
        private int singleSideKeyCutDepthFix;
        private int singleSideKeyCutShape;

        public DataParam build() {
            return new DataParam(this.keyInfo, this.clamp, this.cutDepth, this.decoderSize, this.cutterSize, this.quickCut, this.cutSpeed, this.layer, this.plastic, this.dimpleUpDownCut, this.singleSideKeyCutShape, this.highHandleMode, this.singleSideKeyCutDepthFix, this.hu101ExtCutFix, this.angleDimple, this.angleDimpleIndex);
        }

        public Builder keyInfo(KeyInfo keyInfo) {
            this.keyInfo = keyInfo;
            return this;
        }

        public Builder clamp(Clamp clamp) {
            this.clamp = clamp;
            return this;
        }

        public Builder cutDepth(int i) {
            this.cutDepth = i;
            return this;
        }

        public Builder decoderSize(int i) {
            this.decoderSize = i;
            return this;
        }

        public Builder cutterSize(int i) {
            this.cutterSize = i;
            return this;
        }

        public Builder quickCut(boolean z) {
            this.quickCut = z;
            return this;
        }

        public Builder plastic(boolean z) {
            this.plastic = z;
            return this;
        }

        public Builder cutSpeed(int i) {
            this.cutSpeed = i;
            return this;
        }

        public Builder layer(int i) {
            this.layer = i;
            return this;
        }

        public Builder dimpleUpDownCut(boolean z) {
            this.dimpleUpDownCut = z;
            return this;
        }

        public Builder singleSideKeyCutShape(int i) {
            this.singleSideKeyCutShape = i;
            return this;
        }

        public Builder singleSideKeyCutDepthFix(int i) {
            this.singleSideKeyCutDepthFix = i;
            return this;
        }

        public Builder highHandleMode(boolean z) {
            this.highHandleMode = z;
            return this;
        }

        public Builder hu101ExtCutFix(int i) {
            this.hu101ExtCutFix = i;
            return this;
        }

        public Builder angleDimple(boolean z) {
            this.angleDimple = z;
            return this;
        }

        public Builder angleDimpleIndex(int i) {
            this.angleDimpleIndex = i;
            return this;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.keyInfo, i);
        parcel.writeByte(this.quickCut ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.plastic ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.dimpleUpDownCut ? (byte) 1 : (byte) 0);
        parcel.writeString(this.toothCodeReal);
        parcel.writeInt(this.cutDimpleSonOrMother);
        parcel.writeInt(this.singleSideKeyCutShape);
        parcel.writeString(this.angleKeySpace);
        parcel.writeString(this.angleKeyTooth);
        parcel.writeByte(this.round ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.pauseTime);
        parcel.writeByte(this.slantCorrection ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.fo21AutoCut ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.hu101ExtCutFix);
        parcel.writeByte(isAngleDimple() ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.angleDimpleIndex);
    }

    public void readFromParcel(Parcel parcel) {
        this.keyInfo = (KeyInfo) parcel.readParcelable(KeyInfo.class.getClassLoader());
        this.quickCut = parcel.readByte() != 0;
        this.plastic = parcel.readByte() != 0;
        this.dimpleUpDownCut = parcel.readByte() != 0;
        this.toothCodeReal = parcel.readString();
        this.cutDimpleSonOrMother = parcel.readInt();
        this.singleSideKeyCutShape = parcel.readInt();
        this.angleKeySpace = parcel.readString();
        this.angleKeyTooth = parcel.readString();
        this.round = parcel.readByte() != 0;
        this.pauseTime = parcel.readInt();
        this.slantCorrection = parcel.readByte() != 0;
        this.fo21AutoCut = parcel.readByte() != 0;
        this.hu101ExtCutFix = parcel.readInt();
        this.angleDimple = parcel.readByte() != 0;
        this.angleDimpleIndex = parcel.readInt();
    }

    protected DataParam(Parcel parcel) {
        this.slantCorrection = true;
        this.keyInfo = (KeyInfo) parcel.readParcelable(KeyInfo.class.getClassLoader());
        this.quickCut = parcel.readByte() != 0;
        this.plastic = parcel.readByte() != 0;
        this.dimpleUpDownCut = parcel.readByte() != 0;
        this.toothCodeReal = parcel.readString();
        this.cutDimpleSonOrMother = parcel.readInt();
        this.singleSideKeyCutShape = parcel.readInt();
        this.angleKeySpace = parcel.readString();
        this.angleKeyTooth = parcel.readString();
        this.round = parcel.readByte() != 0;
        this.pauseTime = parcel.readInt();
        this.slantCorrection = parcel.readByte() != 0;
        this.fo21AutoCut = parcel.readByte() != 0;
        this.hu101ExtCutFix = parcel.readInt();
        this.angleDimple = parcel.readByte() != 0;
        this.angleDimpleIndex = parcel.readInt();
    }

    /* renamed from: com.liying.core.operation.cut.DataParam$1 */
    /* loaded from: classes2.dex */
    class C19911 implements Parcelable.Creator<DataParam> {
        C19911() {
        }

        @Override // android.os.Parcelable.Creator
        public DataParam createFromParcel(Parcel parcel) {
            return new DataParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DataParam[] newArray(int i) {
            return new DataParam[i];
        }
    }
}
