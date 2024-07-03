package com.kkkcut.e20j.DbBean.userDB;

import android.os.Parcel;
import android.os.Parcelable;
import com.kkkcut.e20j.DbBean.KeyBasicData;
import com.kkkcut.e20j.utils.DesUtil;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.utils.KeyDataUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CustomKey implements Parcelable {
    public static final Parcelable.Creator<CustomKey> CREATOR = new Parcelable.Creator<CustomKey>() { // from class: com.kkkcut.e20j.DbBean.userDB.CustomKey.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomKey createFromParcel(Parcel parcel) {
            return new CustomKey(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CustomKey[] newArray(int i) {
            return new CustomKey[i];
        }
    };
    String ClampNum;
    String ClampSide;
    String ClampSlot;
    int KeyTypeItemID;
    boolean abSame;
    int align;
    String depth;
    String depth_name;
    int face;
    Long icCard;
    boolean isInch;
    String keyname;
    int length;
    String parameter_info;
    int row_count;
    String row_pos;
    String space;
    String space_width;
    int thick;
    long time;
    int type;
    int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isAbSame() {
        return this.abSame;
    }

    public void setAbSame(boolean z) {
        this.abSame = z;
    }

    public CustomKey(Long l, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, long j) {
        this.icCard = l;
        this.KeyTypeItemID = i;
        this.type = i2;
        this.align = i3;
        this.width = i4;
        this.thick = i5;
        this.length = i6;
        this.row_count = i7;
        this.face = i8;
        this.row_pos = str;
        this.space = str2;
        this.space_width = str3;
        this.depth = str4;
        this.depth_name = str5;
        this.parameter_info = str6;
        this.keyname = str7;
        this.ClampNum = str8;
        this.ClampSide = str9;
        this.ClampSlot = str10;
        this.time = j;
    }

    public CustomKey(KeyBasicData keyBasicData) {
        this.type = keyBasicData.getType();
        this.align = keyBasicData.getAlign();
        this.width = keyBasicData.getWidth();
        this.thick = keyBasicData.getThick();
        this.length = keyBasicData.getLength();
        this.row_count = keyBasicData.getRow_count();
        this.face = keyBasicData.getFace();
        try {
            this.row_pos = DesUtil.decrypt(keyBasicData.getRow_pos(), DesUtil.DATABASE).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.space = DesUtil.decrypt(keyBasicData.getSpace(), DesUtil.DATABASE).trim();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.space_width = KeyDataUtils.fillSpaceWidth(this.type, this.space, DesUtil.decrypt(keyBasicData.getSpace_width(), DesUtil.DATABASE)).trim();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            this.depth = DesUtil.decrypt(keyBasicData.getDepth(), DesUtil.DATABASE).trim();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            this.depth_name = KeyDataUtils.fillNoneDepthNameData(this.depth, DesUtil.decrypt(keyBasicData.getDepth_name(), DesUtil.DATABASE)).trim();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            this.parameter_info = DesUtil.decrypt(keyBasicData.getParameter_info(), DesUtil.DATABASE);
        } catch (Exception e6) {
            e6.printStackTrace();
        }
    }

    public KeyInfo toKeyInfo() {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setType(this.type);
        keyInfo.setAlign(this.align);
        keyInfo.setWidth(getWidth());
        keyInfo.setThick(getThick());
        keyInfo.setLength(getLength());
        keyInfo.setRowCount(getRow_count());
        keyInfo.setRow_pos(getRow_pos());
        keyInfo.setSpaceStr(getSpace());
        keyInfo.setSpaceWidthStr(KeyDataUtils.fillSpaceWidth(getType(), getSpace(), getSpace_width()).trim());
        keyInfo.setDepthStr(getDepth());
        keyInfo.setDepthName(KeyDataUtils.fillNoneDepthNameData(getDepth(), getDepth_name().trim()));
        keyInfo.setType_specific_info(getParameter_info());
        KeyDataUtils.parseKeySpecificInfo(keyInfo, getParameter_info());
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum(getClampNum());
        clampBean.setClampSide(getClampSide());
        clampBean.setClampSlot(getClampSlot());
        keyInfo.setClampKeyBasicData(clampBean);
        keyInfo.setCustomKey(true);
        return keyInfo;
    }

    public CustomKey() {
    }

    public Long getIcCard() {
        return this.icCard;
    }

    public void setIcCard(Long l) {
        this.icCard = l;
    }

    public int getKeyTypeItemID() {
        return this.KeyTypeItemID;
    }

    public void setKeyTypeItemID(int i) {
        this.KeyTypeItemID = i;
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

    public int getRow_count() {
        return this.row_count;
    }

    public void setRow_count(int i) {
        this.row_count = i;
    }

    public int getFace() {
        return this.face;
    }

    public void setFace(int i) {
        this.face = i;
    }

    public String getRow_pos() {
        return this.row_pos;
    }

    public void setRow_pos(String str) {
        this.row_pos = str;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String str) {
        this.space = str;
    }

    public String getSpace_width() {
        return this.space_width;
    }

    public void setSpace_width(String str) {
        this.space_width = str;
    }

    public String getDepth() {
        return this.depth;
    }

    public void setDepth(String str) {
        this.depth = str;
    }

    public String getDepth_name() {
        return this.depth_name;
    }

    public void setDepth_name(String str) {
        this.depth_name = str;
    }

    public String getParameter_info() {
        return this.parameter_info;
    }

    public void setParameter_info(String str) {
        this.parameter_info = str;
    }

    public String getKeyname() {
        return this.keyname;
    }

    public void setKeyname(String str) {
        this.keyname = str;
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

    public boolean isInch() {
        return this.isInch;
    }

    public void setInch(boolean z) {
        this.isInch = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.icCard);
        parcel.writeInt(this.KeyTypeItemID);
        parcel.writeInt(this.type);
        parcel.writeInt(this.align);
        parcel.writeInt(this.width);
        parcel.writeInt(this.thick);
        parcel.writeInt(this.length);
        parcel.writeInt(this.row_count);
        parcel.writeInt(this.face);
        parcel.writeString(this.row_pos);
        parcel.writeString(this.space);
        parcel.writeString(this.space_width);
        parcel.writeString(this.depth);
        parcel.writeString(this.depth_name);
        parcel.writeString(this.parameter_info);
        parcel.writeString(this.keyname);
        parcel.writeString(this.ClampNum);
        parcel.writeString(this.ClampSide);
        parcel.writeString(this.ClampSlot);
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public boolean getAbSame() {
        return this.abSame;
    }

    public boolean getIsInch() {
        return this.isInch;
    }

    public void setIsInch(boolean isInch) {
        this.isInch = isInch;
    }

    protected CustomKey(Parcel parcel) {
        this.icCard = (Long) parcel.readValue(Long.class.getClassLoader());
        this.KeyTypeItemID = parcel.readInt();
        this.type = parcel.readInt();
        this.align = parcel.readInt();
        this.width = parcel.readInt();
        this.thick = parcel.readInt();
        this.length = parcel.readInt();
        this.row_count = parcel.readInt();
        this.face = parcel.readInt();
        this.row_pos = parcel.readString();
        this.space = parcel.readString();
        this.space_width = parcel.readString();
        this.depth = parcel.readString();
        this.depth_name = parcel.readString();
        this.parameter_info = parcel.readString();
        this.keyname = parcel.readString();
        this.ClampNum = parcel.readString();
        this.ClampSide = parcel.readString();
        this.ClampSlot = parcel.readString();
    }

    @Generated(hash = 716185227)
    public CustomKey(String ClampNum, String ClampSide, String ClampSlot, int KeyTypeItemID, boolean abSame, int align, String depth, String depth_name, int face, Long icCard, boolean isInch, String keyname, int length,
            String parameter_info, int row_count, String row_pos, String space, String space_width, int thick, long time, int type, int width) {
        this.ClampNum = ClampNum;
        this.ClampSide = ClampSide;
        this.ClampSlot = ClampSlot;
        this.KeyTypeItemID = KeyTypeItemID;
        this.abSame = abSame;
        this.align = align;
        this.depth = depth;
        this.depth_name = depth_name;
        this.face = face;
        this.icCard = icCard;
        this.isInch = isInch;
        this.keyname = keyname;
        this.length = length;
        this.parameter_info = parameter_info;
        this.row_count = row_count;
        this.row_pos = row_pos;
        this.space = space;
        this.space_width = space_width;
        this.thick = thick;
        this.time = time;
        this.type = type;
        this.width = width;
    }
}
