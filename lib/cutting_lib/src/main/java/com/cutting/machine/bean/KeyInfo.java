package com.cutting.machine.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.cutting.machine.communication.KeyInfoBase;
import com.cutting.machine.utils.UnitConvertUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class KeyInfo implements Parcelable, KeyInfoBase {
    public static final int ANGLE_KEY = 7;
    public static final Parcelable.Creator<KeyInfo> CREATOR = new Parcelable.Creator<KeyInfo>() { // from class: com.cutting.machine.bean.KeyInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyInfo createFromParcel(Parcel parcel) {
            return new KeyInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public KeyInfo[] newArray(int i) {
            return new KeyInfo[i];
        }
    };
    public static final int DIMPLE_KEY = 6;
    public static final int DOUBLE_INSIDE_GROOVE_KEY = 2;
    public static final int DOUBLE_OUTSIDE_GROOVE_KEY = 4;
    public static final int DOUBLE_SIDE_KEY = 0;
    public static final int FRONTEND_ALIGN = 1;
    public static final int HONDA = 100;
    public static final int LEFT_GROOVE = 104;
    public static final int RIGHT_GROOVE = 105;
    public static final int SHOULDERS_ALIGN = 0;
    public static final int SIDE_HOLE = 103;
    public static final int SIDE_TOOTH_3KS_KEY = 92;
    public static final int SIDE_TOOTH_KEY = 9;
    public static final int SINGLE_INSIDE_GROOVE_KEY = 5;
    public static final int SINGLE_OUTSIDE_GROOVE_KEY = 3;
    public static final int SINGLE_SIDE_KEY = 1;
    public static final int THREE_GROOVE = 102;
    public static final int TUBULAR_KEY = 8;
    public static final int TWO_GROOVE = 101;
    private static final String TAG = "KeyInfo";
    HashMap<String, String> keyBlankMap;
    private String KeyChinaNum;
    private String ReadBittingRule;
    private int align;
    private ClampBean clampBean;
    private String clampInfo;
    private String codeSeries;
    private int cutDepth;
    private int cutDepthSingleKey;
    private String cutSharpenType;
    private int cut_speed;
    private String cuts;
    private int cutterSize;
    private int decode_locked;
    private String decoder;
    private String default_bitting;
    private String depthName;
    private String depthStr;
    private String desc;
    private String exCut;
    private int extDoublekeyDepth;
    private String extJaw;
    private String extTopCut;
    private int extraCut;
    private int face;
    private int groove;
    private int guide;
    private boolean highHandleKey;
    private int icCard;
    private int innerCutLength;
    private int innerCutX;
    private int innerCutY;
    private int inner_cut_angle;
    private boolean isCustomKey;
    private boolean isPlasticKey;
    private int isrule;
    private String keyBlanks;
    private String keyComb;
    private String keyToothCode;
    private String keyangle;
    private String keyanglecod;
    private int keyitemid;
    private int lastBitting;
    private String lastPosition;
    private int length;
    private int locked;
    private int nose;
    private int noseDown;
    private int noseUp;
    private boolean quickCut;
    private int remainingDepth;
    private int rowCount;
    private String row_pos;
    private String s9BclampInfo;
    private int seriesID;
    private String setting_round;
    private int shape;
    private int shoulderBlock;
    private String siblingProfile;
    private int side;
    private String spaceStr;
    private String spaceWidthStr;
    private String spacenew;
    private int spacewidthangles;
    private String spacewidthnew;
    private int thick;
    private String title;
    private int type;
    private String type_specific_info;
    private String variableSpace;
    private int width;

    public KeyInfo() {
    }

    protected KeyInfo(Parcel parcel) {
        this.clampBean = parcel.readParcelable(ClampBean.class.getClassLoader());
        this.keyBlankMap = (HashMap) parcel.readSerializable();
        this.isCustomKey = parcel.readByte() != 0;
        this.isPlasticKey = parcel.readByte() != 0;
        this.highHandleKey = parcel.readByte() != 0;
        this.cutSharpenType = parcel.readString();
        this.icCard = parcel.readInt();
        this.type = parcel.readInt();
        this.align = parcel.readInt();
        this.width = parcel.readInt();
        this.thick = parcel.readInt();
        this.length = parcel.readInt();
        this.rowCount = parcel.readInt();
        this.row_pos = parcel.readString();
        this.spaceStr = parcel.readString();
        this.spaceWidthStr = parcel.readString();
        this.depthStr = parcel.readString();
        this.depthName = parcel.readString();
        this.type_specific_info = parcel.readString();
        this.desc = parcel.readString();
        this.side = parcel.readInt();
        this.guide = parcel.readInt();
        this.cutDepth = parcel.readInt();
        this.extraCut = parcel.readInt();
        this.groove = parcel.readInt();
        this.nose = parcel.readInt();
        this.noseUp = parcel.readInt();
        this.noseDown = parcel.readInt();
        this.innerCutLength = parcel.readInt();
        this.lastBitting = parcel.readInt();
        this.variableSpace = parcel.readString();
        this.keyToothCode = parcel.readString();
        this.siblingProfile = parcel.readString();
        this.ReadBittingRule = parcel.readString();
        this.codeSeries = parcel.readString();
        this.clampInfo = parcel.readString();
        this.s9BclampInfo = parcel.readString();
        this.isrule = parcel.readInt();
        this.locked = parcel.readInt();
        this.decoder = parcel.readString();
        this.cutterSize = parcel.readInt();
        this.exCut = parcel.readString();
        this.setting_round = parcel.readString();
        this.keyitemid = parcel.readInt();
        this.seriesID = parcel.readInt();
        this.cuts = parcel.readString();
        this.KeyChinaNum = parcel.readString();
        this.title = parcel.readString();
        this.spacewidthnew = parcel.readString();
        this.spacenew = parcel.readString();
        this.inner_cut_angle = parcel.readInt();
        this.innerCutX = parcel.readInt();
        this.innerCutY = parcel.readInt();
        this.shoulderBlock = parcel.readInt();
        this.keyBlanks = parcel.readString();
        this.extTopCut = parcel.readString();
        this.extJaw = parcel.readString();
        this.shape = parcel.readInt();
        this.extDoublekeyDepth = parcel.readInt();
        this.cut_speed = parcel.readInt();
        this.remainingDepth = parcel.readInt();
        this.cutDepthSingleKey = parcel.readInt();
        this.face = parcel.readInt();
        this.decode_locked = parcel.readInt();
        this.spacewidthangles = parcel.readInt();
        this.default_bitting = parcel.readString();
        this.keyangle = parcel.readString();
        this.keyanglecod = parcel.readString();
        this.lastPosition = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HashMap<String, String> getKeyBlankMap() {
        return this.keyBlankMap;
    }

    public void setKeyBlankMap(HashMap<String, String> hashMap) {
        this.keyBlankMap = hashMap;
    }

    public boolean isPlasticKey() {
        return this.isPlasticKey;
    }

    public void setPlasticKey(boolean z) {
        this.isPlasticKey = z;
    }

    public boolean isHighHandleKey() {
        return this.highHandleKey;
    }

    public void setHighHandleKey(boolean z) {
        this.highHandleKey = z;
    }

    public String getCutSharpenType() {
        return this.cutSharpenType;
    }

    public void setCutSharpenType(String str) {
        this.cutSharpenType = str;
    }

    public ClampBean getClampBean() {
        return this.clampBean;
    }

    public void setClampKeyBasicData(ClampBean clampBean) {
        this.clampBean = clampBean;
    }

    public int getSpacewidthangles() {
        return this.spacewidthangles;
    }

    public void setSpacewidthangles(int i) {
        this.spacewidthangles = i;
    }

    public String getKeyComb() {
        return (getIcCard() == 1287 || getIcCard() == 601287 || getIcCard() == 611287) ? "A-1287,B-601287,C-611287" : this.keyComb;
    }

    public String getLastPosition() {
        return this.lastPosition;
    }

    public void setLastPosition(String str) {
        this.lastPosition = str;
    }

    public String getKeyangle() {
        return this.keyangle;
    }

    public void setKeyangle(String str) {
        this.keyangle = str;
    }

    public String getKeyanglecod() {
        return this.keyanglecod;
    }

    public void setKeyanglecod(String str) {
        this.keyanglecod = str;
    }

    public String getDefault_bitting() {
        return this.default_bitting;
    }

    public void setDefault_bitting(String str) {
        this.default_bitting = str;
    }

    public int getDecode_locked() {
        return this.decode_locked;
    }

    public void setDecode_locked(int i) {
        this.decode_locked = i;
    }

    public int getFace() {
        int i = this.face;
        if (i == 0) {
            return 4;
        }
        return i;
    }

    public void setFace(int i) {
        this.face = i;
    }

    public int getCutDepthSingleKey() {
        return this.cutDepthSingleKey;
    }

    public void setCutDepthSingleKey(int i) {
        this.cutDepthSingleKey = i;
    }

    public int getRemainingDepth() {
        return this.remainingDepth;
    }

    public void setRemainingDepth(int i) {
        this.remainingDepth = i;
    }

    public String getExCut() {
        return this.exCut;
    }

    public void setExCut(String str) {
        this.exCut = str;
    }

    public int getKeyitemid() {
        return this.keyitemid;
    }

    public void setKeyitemid(int i) {
        this.keyitemid = i;
    }

    public String getExtTopCut() {
        return this.extTopCut;
    }

    public void setExtTopCut(String str) {
        this.extTopCut = str;
    }

    public boolean isQuickCut() {
        return this.quickCut;
    }

    public void setQuickCut(boolean z) {
        this.quickCut = z;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getShoulderBlock() {
        return this.shoulderBlock;
    }

    public void setShoulderBlock(int i) {
        this.shoulderBlock = i;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getLengthSteps() {
        return UnitConvertUtil.xKeyCmm2Steps(getLength());
    }

    public int getInnerCutX() {
        return this.innerCutX;
    }

    public void setInnerCutX(int i) {
        this.innerCutX = i;
    }

    public int getInnerCutY() {
        return this.innerCutY;
    }

    public void setInnerCutY(int i) {
        this.innerCutY = i;
    }

    public int getInner_cut_angle() {
        return this.inner_cut_angle;
    }

    public void setInner_cut_angle(int i) {
        this.inner_cut_angle = i;
    }

    public String getSpacewidthnew() {
        return this.spacewidthnew;
    }

    public void setSpacewidthnew(String str) {
        this.spacewidthnew = str;
    }

    public String getSpacenew() {
        return this.spacenew;
    }

    public void setSpacenew(String str) {
        this.spacenew = str;
    }

    public int getSeriesID() {
        return this.seriesID;
    }

    public void setSeriesID(int i) {
        this.seriesID = i;
    }

    public boolean isCustomKey() {
        return this.isCustomKey;
    }

    public void setCustomKey(boolean z) {
        this.isCustomKey = z;
    }

    public String getCuts() {
        this.cuts = "";
        String[] split = getSpaceStr().split(";");
        for (int i = 0; i < split.length; i++) {
            if (i == split.length - 1) {
                this.cuts += split[i].split(",").length;
            } else {
                this.cuts += split[i].split(",").length + ",";
            }
        }
        return this.cuts;
    }

    public void setCuts(String str) {
        this.cuts = str;
    }

    public String getKeyChinaNum() {
        return this.KeyChinaNum;
    }

    public void setKeyChinaNum(String str) {
        this.KeyChinaNum = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDecoder() {
        return this.decoder;
    }

    public void setDecoder(String str) {
        this.decoder = str;
    }

    public int getCutterSize() {
        return this.cutterSize;
    }

    public void setCutterSize(int i) {
        this.cutterSize = i;
    }

    public int getLocked() {
        return this.locked;
    }

    public void setLocked(int i) {
        this.locked = i;
    }

    public String getClampInfo() {
        return this.clampInfo;
    }

    public void setClampInfo(String str) {
        this.clampInfo = str;
    }

    public int getS9BclampInfo() {
        if (TextUtils.isEmpty(this.s9BclampInfo)) {
            return 100;
        }
        return Integer.parseInt(this.s9BclampInfo);
    }

    public void setS9BclampInfo(String str) {
        this.s9BclampInfo = str;
    }

    public int getIsrule() {
        return this.isrule;
    }

    public void setIsrule(int i) {
        this.isrule = i;
    }

    public int getExtDoublekeyDepth() {
        return this.extDoublekeyDepth;
    }

    public void setExtDoublekeyDepth(int i) {
        this.extDoublekeyDepth = i;
    }

    public String getExtJaw() {
        return this.extJaw;
    }

    public void setExtJaw(String str) {
        this.extJaw = str;
    }

    public int getCut_speed() {
        return this.cut_speed;
    }

    public void setCut_speed(int i) {
        this.cut_speed = i;
    }

    public String getSetting_round() {
        return this.setting_round;
    }

    public void setSetting_round(String str) {
        this.setting_round = str;
    }

    public int getIcCard() {
        return this.icCard;
    }

    public void setIcCard(int i) {
        this.icCard = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getAlign() {
        return this.align;
    }

    public void setAlign(int i) {
        this.align = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getThick() {
        return this.thick;
    }

    public void setThick(int i) {
        this.thick = i;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public boolean isNewHonda() {
        return getType() == 1 && getCutDepth() > 0;
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int i) {
        this.rowCount = i;
    }

    public String getRow_pos() {
        return this.row_pos;
    }

    public void setRow_pos(String str) {
        this.row_pos = str;
    }

    public String getSpaceStr() {
        return this.spaceStr;
    }

    public void setSpaceStr(String str) {
        this.spaceStr = str;
    }

    public String getSpaceWidthStr() {
        return this.spaceWidthStr;
    }

    public void setSpaceWidthStr(String str) {
        this.spaceWidthStr = str;
    }

    public String getDepthStr() {
        return this.depthStr;
    }

    public void setDepthStr(String str) {
        this.depthStr = str;
    }

    public String getDepthName() {
        return this.depthName;
    }

    public void setDepthName(String str) {
        this.depthName = str;
    }

    public String getType_specific_info() {
        return this.type_specific_info;
    }

    public void setType_specific_info(String str) {
        this.type_specific_info = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public int getCutDepth() {
        return this.cutDepth;
    }

    public void setCutDepth(int i) {
        this.cutDepth = i;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public KeyType getKeyType() {
        return type(getType());
    }

    public KeyType type(int i) {
        if (i != 92) {
            switch (i) {
                case 0:
                    return KeyType.DOUBLE_SIDE_KEY;
                case 1:
                    return KeyType.SINGLE_SIDE_KEY;
                case 2:
                    return KeyType.DOUBLE_INSIDE_GROOVE_KEY;
                case 3:
                    return KeyType.SINGLE_OUTSIDE_GROOVE_KEY;
                case 4:
                    return KeyType.DOUBLE_OUTSIDE_GROOVE_KEY;
                case 5:
                    return KeyType.SINGLE_INSIDE_GROOVE_KEY;
                case 6:
                    return KeyType.DIMPLE_KEY;
                case 7:
                    return KeyType.ANGLE_KEY;
                case 8:
                    return KeyType.TUBULAR_KEY;
                default:
                    return null;
            }
        }
        return KeyType.SIDE_TOOTH_3KS_KEY;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public KeyAlign getKeyAlign() {
        return getAlign() == 0 ? KeyAlign.SHOULDERS_ALIGN : KeyAlign.FRONTEND_ALIGN;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getSide() {
        return this.side;
    }

    public void setSide(int i) {
        this.side = i;
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getKeyWidthSteps() {
        return UnitConvertUtil.yKeyCmm2Steps(getWidth());
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getExtDoublekeyDepthSteps() {
        return UnitConvertUtil.yKeyCmm2Steps(getExtDoublekeyDepth());
    }

    @Override // com.cutting.machine.communication.KeyInfoBase
    public int getThickSteps() {
        return UnitConvertUtil.zKeyCmm2Steps(getThick());
    }

    public int getGuide() {
        return this.guide;
    }

    public void setGuide(int i) {
        this.guide = i;
    }

    public int getExtraCut() {
        return this.extraCut;
    }

    public void setExtraCut(int i) {
        this.extraCut = i;
    }

    public int getGroove() {
        if (getIcCard() == 622213) {
            this.groove = 320;
        }
        if (getIcCard() == 20139) {
            this.groove = 315;
        }
        if (getIcCard() == 13094) {
            this.groove = 300;
        }
        if (this.groove == 0 && getType() == 5) {
            this.groove = 300;
        }
        return this.groove;
    }

    public void setGroove(int i) {
        this.groove = i;
    }

    public int getNose() {
        return this.nose;
    }

    public void setNose(int i) {
        this.nose = i;
    }

    public int getNoseUp() {
        return this.noseUp;
    }

    public void setNoseUp(int i) {
        this.noseUp = i;
    }

    public int getNoseDown() {
        return this.noseDown;
    }

    public void setNoseDown(int i) {
        this.noseDown = i;
    }

    public int getInnerCutLength() {
        return this.innerCutLength;
    }

    public void setInnerCutLength(int i) {
        this.innerCutLength = i;
    }

    public int getLastBitting() {
        return this.lastBitting;
    }

    public void setLastBitting(int i) {
        this.lastBitting = i;
    }

    public String getVariableSpace() {
        return this.variableSpace;
    }

    public void setVariableSpace(String str) {
        this.variableSpace = str;
    }

    public String getKeyToothCode() {
        return this.keyToothCode;
    }

    public void setKeyToothCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.keyToothCode = str;
    }

    public String getSiblingProfile() {
        return this.siblingProfile;
    }

    public void setSiblingProfile(String str) {
        this.siblingProfile = str;
    }

    public String getReadBittingRule() {
        return this.ReadBittingRule;
    }

    public void setReadBittingRule(String str) {
        this.ReadBittingRule = str;
    }

    public String getCodeSeries() {
        return this.codeSeries;
    }

    public void setCodeSeries(String str) {
        this.codeSeries = str;
    }

    public String getKeyBlanks() {
        return this.keyBlanks;
    }

    public void setKeyBlanks(String str) {
        this.keyBlanks = str;
    }

    public int getShape() {
        return this.shape;
    }

    public void setShape(int i) {
        this.shape = i;
    }

    public boolean isDimpleMotherSonKey() {
        return getType() == 6 && getSpaceWidthStr().contains("-");
    }

    public boolean isAngleDimple() {
        return getType() == 6 && TextUtils.equals(getClampBean().getClampNum(), "S10");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.clampBean, i);
        parcel.writeSerializable(this.keyBlankMap);
        parcel.writeByte(this.isCustomKey ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isPlasticKey ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.highHandleKey ? (byte) 1 : (byte) 0);
        parcel.writeString(this.cutSharpenType);
        parcel.writeInt(this.icCard);
        parcel.writeInt(this.type);
        parcel.writeInt(this.align);
        parcel.writeInt(this.width);
        parcel.writeInt(this.thick);
        parcel.writeInt(this.length);
        parcel.writeInt(this.rowCount);
        parcel.writeString(this.row_pos);
        parcel.writeString(this.spaceStr);
        parcel.writeString(this.spaceWidthStr);
        parcel.writeString(this.depthStr);
        parcel.writeString(this.depthName);
        parcel.writeString(this.type_specific_info);
        parcel.writeString(this.desc);
        parcel.writeInt(this.side);
        parcel.writeInt(this.guide);
        parcel.writeInt(this.cutDepth);
        parcel.writeInt(this.extraCut);
        parcel.writeInt(this.groove);
        parcel.writeInt(this.nose);
        parcel.writeInt(this.noseUp);
        parcel.writeInt(this.noseDown);
        parcel.writeInt(this.innerCutLength);
        parcel.writeInt(this.lastBitting);
        parcel.writeString(this.variableSpace);
        parcel.writeString(this.keyToothCode);
        parcel.writeString(this.siblingProfile);
        parcel.writeString(this.ReadBittingRule);
        parcel.writeString(this.codeSeries);
        parcel.writeString(this.clampInfo);
        parcel.writeString(this.s9BclampInfo);
        parcel.writeInt(this.isrule);
        parcel.writeInt(this.locked);
        parcel.writeString(this.decoder);
        parcel.writeInt(this.cutterSize);
        parcel.writeString(this.exCut);
        parcel.writeString(this.setting_round);
        parcel.writeInt(this.keyitemid);
        parcel.writeInt(this.seriesID);
        parcel.writeString(this.cuts);
        parcel.writeString(this.KeyChinaNum);
        parcel.writeString(this.title);
        parcel.writeString(this.spacewidthnew);
        parcel.writeString(this.spacenew);
        parcel.writeInt(this.inner_cut_angle);
        parcel.writeInt(this.innerCutX);
        parcel.writeInt(this.innerCutY);
        parcel.writeInt(this.shoulderBlock);
        parcel.writeString(this.keyBlanks);
        parcel.writeInt(this.shape);
        parcel.writeInt(this.cut_speed);
        parcel.writeInt(this.remainingDepth);
        parcel.writeInt(this.cutDepthSingleKey);
        parcel.writeInt(this.face);
        parcel.writeInt(this.decode_locked);
        parcel.writeInt(this.spacewidthangles);
        parcel.writeString(this.default_bitting);
        parcel.writeString(this.keyangle);
        parcel.writeString(this.keyanglecod);
        parcel.writeString(this.lastPosition);
    }

    public void readFromParcel(Parcel parcel) {
        this.clampBean = parcel.readParcelable(ClampBean.class.getClassLoader());
        this.keyBlankMap = (HashMap) parcel.readSerializable();
        this.isCustomKey = parcel.readByte() != 0;
        this.isPlasticKey = parcel.readByte() != 0;
        this.highHandleKey = parcel.readByte() != 0;
        this.cutSharpenType = parcel.readString();
        this.icCard = parcel.readInt();
        this.type = parcel.readInt();
        this.align = parcel.readInt();
        this.width = parcel.readInt();
        this.thick = parcel.readInt();
        this.length = parcel.readInt();
        this.rowCount = parcel.readInt();
        this.row_pos = parcel.readString();
        this.spaceStr = parcel.readString();
        this.spaceWidthStr = parcel.readString();
        this.depthStr = parcel.readString();
        this.depthName = parcel.readString();
        this.type_specific_info = parcel.readString();
        this.desc = parcel.readString();
        this.side = parcel.readInt();
        this.guide = parcel.readInt();
        this.cutDepth = parcel.readInt();
        this.extraCut = parcel.readInt();
        this.groove = parcel.readInt();
        this.nose = parcel.readInt();
        this.noseUp = parcel.readInt();
        this.noseDown = parcel.readInt();
        this.innerCutLength = parcel.readInt();
        this.lastBitting = parcel.readInt();
        this.variableSpace = parcel.readString();
        this.keyToothCode = parcel.readString();
        this.siblingProfile = parcel.readString();
        this.ReadBittingRule = parcel.readString();
        this.codeSeries = parcel.readString();
        this.clampInfo = parcel.readString();
        this.s9BclampInfo = parcel.readString();
        this.isrule = parcel.readInt();
        this.locked = parcel.readInt();
        this.decoder = parcel.readString();
        this.cutterSize = parcel.readInt();
        this.exCut = parcel.readString();
        this.setting_round = parcel.readString();
        this.keyitemid = parcel.readInt();
        this.seriesID = parcel.readInt();
        this.cuts = parcel.readString();
        this.KeyChinaNum = parcel.readString();
        this.title = parcel.readString();
        this.spacewidthnew = parcel.readString();
        this.spacenew = parcel.readString();
        this.inner_cut_angle = parcel.readInt();
        this.innerCutX = parcel.readInt();
        this.innerCutY = parcel.readInt();
        this.shoulderBlock = parcel.readInt();
        this.keyBlanks = parcel.readString();
        this.extTopCut = parcel.readString();
        this.extJaw = parcel.readString();
        this.shape = parcel.readInt();
        this.extDoublekeyDepth = parcel.readInt();
        this.cut_speed = parcel.readInt();
        this.remainingDepth = parcel.readInt();
        this.cutDepthSingleKey = parcel.readInt();
        this.face = parcel.readInt();
        this.decode_locked = parcel.readInt();
        this.spacewidthangles = parcel.readInt();
        this.default_bitting = parcel.readString();
        this.keyangle = parcel.readString();
        this.keyanglecod = parcel.readString();
        this.lastPosition = parcel.readString();
    }

    public List<List<Integer>> getSpaceList() {
        String spaceStr = getSpaceStr();
        if (TextUtils.isEmpty(spaceStr)) {
            return null;
        }
        String[] split = spaceStr.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            String[] split2 = str.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : split2) {
                arrayList2.add(Integer.valueOf(Integer.parseInt(str2.trim())));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<List<Integer>> getSpaceWidthList() {
        String spaceWidthStr = getSpaceWidthStr();
        if (TextUtils.isEmpty(spaceWidthStr)) {
            return null;
        }
        String[] split = spaceWidthStr.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            String[] split2 = str.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : split2) {
                arrayList2.add(Integer.valueOf(Integer.parseInt(str2.trim())));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<List<Integer>> getDepthList() {
        String depthStr = getDepthStr();
        if (TextUtils.isEmpty(depthStr)) {
            return null;
        }
        String[] split = depthStr.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            String[] split2 = str.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : split2) {
                arrayList2.add(Integer.valueOf(Integer.parseInt(str2.trim())));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<List<String>> getDepthNameList() {
        String depthName = getDepthName();
        if (TextUtils.isEmpty(depthName)) {
            return null;
        }
        String[] split = depthName.split(";");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            String[] split2 = str.split(",");
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : split2) {
                arrayList2.add(str2.trim());
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public List<List<String>> getToothCodeArray() {
        ArrayList arrayList = new ArrayList();
        String keyToothCode = getKeyToothCode();
        if (TextUtils.isEmpty(keyToothCode)) {
            for (List<Integer> list : getSpaceList()) {
                ArrayList arrayList2 = new ArrayList();
                for (Integer num : list) {
                    arrayList2.add("?");
                }
                arrayList.add(arrayList2);
            }
            return arrayList;
        }
        for (String str : keyToothCode.split(";")) {
            String[] split = str.split(",");
            ArrayList arrayList3 = new ArrayList();
            for (String str2 : split) {
                arrayList3.add(str2.trim());
            }
            arrayList.add(arrayList3);
        }
        return arrayList;
    }

    public List<List<Integer>> toothCode2RealDepthList(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            str = getKeyToothCode();
        }
        List<List<Integer>> spaceList = getSpaceList();
        List<List<Integer>> depthList = getDepthList();
        List<List<String>> depthNameList = getDepthNameList();
        if (TextUtils.isEmpty(str)) {
            for (int i = 0; i < spaceList.size(); i++) {
                List<Integer> list = spaceList.get(i);
                List<Integer> list2 = depthList.get(i);
                ArrayList arrayList2 = new ArrayList();
                for (Integer num : list) {
                    arrayList2.add(Integer.valueOf(doNotCut(list2)));
                }
                arrayList.add(arrayList2);
            }
            return arrayList;
        }
        String[] split = str.split(";");
        for (int i2 = 0; i2 < spaceList.size(); i2++) {
            String[] split2 = split[i2].split(",");
            List<Integer> list3 = spaceList.get(i2);
            List<Integer> list4 = depthList.get(i2);
            List<String> list5 = depthNameList.get(i2);
            ArrayList arrayList3 = new ArrayList();
            int i3 = 0;
            while (i3 < list3.size()) {
                arrayList3.add(Integer.valueOf(getDepthByCode(list4, list5, i3 < split2.length ? split2[i3] : "?")));
                i3++;
            }
            arrayList.add(arrayList3);
        }
        return arrayList;
    }

    public int getDepthByCode(List<Integer> list, List<String> list2, String str) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        if ("?".equals(str) || "?.0".equals(str)) {
            return doNotCut(list);
        }
        if (str.contains(".")) {
            String[] split = str.split("\\.");
            String str2 = split[0];
            float parseFloat = Float.parseFloat("0." + split[1]);
            if ("?".equals(str2)) {
                int r8 = (int) ((list.get(0).intValue() - list.get(1).intValue()) * parseFloat);
                return (list.get(0).intValue() - r8) + r8;
            }
            int indexOf = list2.indexOf(str2);
            if (indexOf >= 0) {
                if (indexOf == list.size() - 1) {
                    return (int) (list.get(indexOf).intValue() + ((list.get(list.size() - 1).intValue() - list.get(list.size() - 2).intValue()) * parseFloat));
                }
                return (int) (list.get(indexOf).intValue() + ((list.get(indexOf + 1).intValue() - list.get(indexOf).intValue()) * parseFloat));
            }
            return doNotCut(list);
        }
        int indexOf2 = list2.indexOf(str);
        if (indexOf2 == -1) {
            return doNotCut(list);
        }
        return list.get(indexOf2).intValue();
    }

    public String changeSingleDepth(int i, int i2, float f, boolean z, String str) {
        String codeByDepth;
        List<List<String>> depthNameList = getDepthNameList();
        List<List<Integer>> depthList = getDepthList();
        if (!TextUtils.isEmpty(str) && ("1".equals(str) || "11".equals(str))) {
            codeByDepth = getCodeByDepthForDimple(depthList.get(i), depthNameList.get(i), f, str);
        } else {
            codeByDepth = getCodeByDepth(depthList.get(i), depthNameList.get(i), f, z);
        }
        String str2 = "";
        if (!TextUtils.isEmpty(codeByDepth)) {
            for (int i3 = 0; i3 < getToothCodeArray().size(); i3++) {
                for (int i4 = 0; i4 < getToothCodeArray().get(i3).size(); i4++) {
                    if (i3 == i && i4 == i2) {
                        str2 = getToothCodeArray().get(i3).size() - 1 == i4 ? str2 + codeByDepth + ";" : str2 + codeByDepth + ",";
                    } else if (getToothCodeArray().get(i3).size() - 1 == i4) {
                        str2 = str2 + getToothCodeArray().get(i3).get(i4) + ";";
                    } else {
                        str2 = str2 + getToothCodeArray().get(i3).get(i4) + ",";
                    }
                }
            }
        }
        return str2;
    }

    public String getCodeByDepth(List<Integer> list, List<String> list2, float f, boolean z) {
        if (list.size() < 2) {
            return list2.get(0);
        }
        if (list.get(0).intValue() > list.get(1).intValue()) {
            if (f > list.get(0).intValue()) {
                if (z) {
                    return list2.get(0);
                }
                float round = Math.round((1.0f - ((f - list.get(0).intValue()) / (list.get(0).intValue() - list.get(1).intValue()))) * 10.0f) / 10.0f;
                if (round > 0.95f) {
                    return list2.get(0);
                }
                if (round < 0.5f) {
                    return "?.5";
                }
                String valueOf = String.valueOf(round);
                return "?" + valueOf.substring(valueOf.indexOf("."));
            }
            if (f <= list.get(list.size() - 1).intValue()) {
                if (z) {
                    return list2.get(list2.size() - 1);
                }
                float round2 = Math.round(((list.get(list.size() - 1).intValue() - f) / (list.get(list.size() - 2).intValue() - list.get(list.size() - 1).intValue())) * 10.0f) / 10.0f;
                if (round2 < 0.5f) {
                    String valueOf2 = String.valueOf(round2);
                    return list2.get(list.size() - 1) + valueOf2.substring(valueOf2.indexOf("."));
                }
                return list2.get(list.size() - 1) + ".4";
            }
            for (int i = 0; i < list.size() - 1; i++) {
                if (f <= list.get(i).intValue()) {
                    int i2 = i + 1;
                    if (f > list.get(i2).intValue()) {
                        float round3 = Math.round(((list.get(i).intValue() - f) / (list.get(i).intValue() - list.get(i2).intValue())) * 10.0f) / 10.0f;
                        if (z) {
                            if (round3 >= 0.5f) {
                                return list2.get(i2);
                            }
                            return list2.get(i);
                        }
                        double d = round3;
                        if (d > 0.95d) {
                            return list2.get(i2);
                        }
                        if (d < 0.05d) {
                            return list2.get(i);
                        }
                        String valueOf3 = String.valueOf(round3);
                        return list2.get(i) + valueOf3.substring(valueOf3.indexOf("."));
                    }
                }
            }
        } else {
            if (f < list.get(0).intValue()) {
                if (z) {
                    return list2.get(0);
                }
                float round4 = Math.round((1.0f - ((f - list.get(0).intValue()) / (list.get(0).intValue() - list.get(1).intValue()))) * 10.0f) / 10.0f;
                if (round4 > 0.95f) {
                    return list2.get(0);
                }
                if (round4 < 0.5f) {
                    return "?.5";
                }
                String valueOf4 = String.valueOf(round4);
                return "?" + valueOf4.substring(valueOf4.indexOf("."));
            }
            if (f >= list.get(list.size() - 1).intValue()) {
                if (z) {
                    return list2.get(list2.size() - 1);
                }
                float round5 = Math.round(((list.get(list.size() - 1).intValue() - f) / (list.get(list.size() - 2).intValue() - list.get(list.size() - 1).intValue())) * 10.0f) / 10.0f;
                if (round5 < 0.5f) {
                    String valueOf5 = String.valueOf(round5);
                    return list2.get(list.size() - 1) + valueOf5.substring(valueOf5.indexOf("."));
                }
                return list2.get(list.size() - 1) + ".4";
            }
            for (int i3 = 0; i3 < list.size() - 1; i3++) {
                if (f >= list.get(i3).intValue()) {
                    int i4 = i3 + 1;
                    if (f < list.get(i4).intValue()) {
                        float intValue = ((list.get(i3).intValue() * 1.0f) - f) / (list.get(i3).intValue() - list.get(i4).intValue());
                        if (z) {
                            if (intValue >= 0.5f) {
                                return list2.get(i4);
                            }
                            return list2.get(i3);
                        }
                        double d2 = intValue;
                        if (d2 > 0.95d) {
                            return list2.get(i4);
                        }
                        if (d2 < 0.05d) {
                            return list2.get(i3);
                        }
                        String valueOf6 = String.valueOf(intValue);
                        return list2.get(i3) + valueOf6.substring(valueOf6.indexOf("."));
                    }
                }
            }
        }
        return list2.get(0);
    }

    public String getCodeByDepthForDimple(List<Integer> list, List<String> list2, float f, String str) {
        if (list.size() < 2) {
            return list2.get(0);
        }
        float f2 = 1.0f;
        if (list.get(0).intValue() > list.get(1).intValue()) {
            if (f > list.get(0).intValue()) {
                return list2.get(0);
            }
            if (f <= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i = 0;
            while (i < list.size() - 1) {
                if (f <= list.get(i).intValue()) {
                    int i2 = i + 1;
                    if (f > list.get(i2).intValue()) {
                        float intValue = ((int) ((((list.get(i).intValue() - f) * f2) / (list.get(i).intValue() - list.get(i2).intValue())) * 10.0f)) / 10.0f;
                        if (str.equals("1")) {
                            if (intValue <= 0.2f) {
                                return list2.get(i);
                            }
                            if (intValue <= 0.7f) {
                                return list2.get(i) + ".5";
                            }
                            return list2.get(i2);
                        }
                        if (str.equals("11")) {
                            if (intValue <= 0.4f) {
                                return list2.get(i);
                            }
                            return list2.get(i2);
                        }
                    } else {
                        continue;
                    }
                }
                i++;
                f2 = 1.0f;
            }
        } else {
            if (f < list.get(0).intValue()) {
                return list2.get(0);
            }
            if (f >= list.get(list.size() - 1).intValue()) {
                return list2.get(list2.size() - 1);
            }
            int i3 = 0;
            for (int i4 = 1; i3 < list.size() - i4; i4 = 1) {
                if (f >= list.get(i3).intValue()) {
                    int i5 = i3 + 1;
                    if (f < list.get(i5).intValue()) {
                        float intValue2 = ((int) (((((list.get(i3).intValue() * 1.0f) - f)) / (list.get(i3).intValue() - list.get(i5).intValue())) * 10.0f)) / 10.0f;
                        if (str.equals("1")) {
                            if (intValue2 <= 0.2f) {
                                return list2.get(i3);
                            }
                            if (intValue2 <= 0.7f) {
                                return list2.get(i3) + ".5";
                            }
                            return list2.get(i5);
                        }
                        if (str.equals("11")) {
                            if (intValue2 <= 0.4f) {
                                return list2.get(i3);
                            }
                            return list2.get(i5);
                        }
                        i3++;
                    }
                }
                i3++;
            }
        }
        return list2.get(0);
    }

    private int doNotCut(List<Integer> list) {
        if (getType() == 0 || getType() == 1 || getType() == 4 || getType() == 3 || getType() == 9 || getType() == 5 || getType() == 2) {
            return Math.max(list.get(0).intValue(), list.get(list.size() - 1).intValue());
        }
        if (getType() == 6) {
            return getThick() == 0 ? Math.max(list.get(0).intValue(), list.get(list.size() - 1).intValue()) : getThick();
        }
        return 0;
    }
}
