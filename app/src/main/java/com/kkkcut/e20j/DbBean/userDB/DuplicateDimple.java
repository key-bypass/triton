package com.kkkcut.e20j.DbBean.userDB;

/* loaded from: classes.dex */
public class DuplicateDimple {
    String ClampNum;
    String ClampSide;
    String ClampSlot;
    int KeyTypeItemID;
    int align;
    String depth;
    String depth_name;
    int face;
    Long icCard;
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

    public DuplicateDimple(Long l, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, long j) {
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

    public DuplicateDimple() {
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

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
