package com.kkkcut.e20j.DbBean;

import com.kkkcut.e20j.utils.DesUtil;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.utils.KeyDataUtils;

/* loaded from: classes.dex */
public class KeyBasicDataItem {
    String Description;
    int FK_KeyID;
    int align;
    String depth;
    String depth_name;
    int face;
    int id;
    int length;
    String parameter_info;
    int row_count;
    String row_pos;
    String space;
    String space_width;
    int thick;
    int type;
    int width;

    public KeyInfo toKeyInfo() {
        KeyInfo keyInfo = new KeyInfo();
        keyInfo.setIcCard(getFK_KeyID());
        keyInfo.setType(getType());
        keyInfo.setAlign(getAlign());
        keyInfo.setWidth(getWidth());
        keyInfo.setThick(getThick());
        keyInfo.setLength(getLength());
        keyInfo.setRowCount(getRow_count());
        try {
            String trim = DesUtil.decrypt(getRow_pos(), DesUtil.DATABASE).trim();
            if (trim.contains("-")) {
                trim = trim.replace("-", "");
            }
            keyInfo.setRow_pos(trim);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            keyInfo.setSpaceStr(DesUtil.decrypt(getSpace(), DesUtil.DATABASE).trim());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            keyInfo.setSpaceWidthStr(KeyDataUtils.fillSpaceWidth(this.type, keyInfo.getSpaceStr(), DesUtil.decrypt(getSpace_width(), DesUtil.DATABASE)).trim());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            keyInfo.setDepthStr(DesUtil.decrypt(getDepth(), DesUtil.DATABASE).trim());
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            keyInfo.setDepthName(KeyDataUtils.fillNoneDepthNameData(keyInfo.getDepthStr(), DesUtil.decrypt(getDepth_name(), DesUtil.DATABASE)).trim());
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            String decrypt = DesUtil.decrypt(getParameter_info(), DesUtil.DATABASE);
            keyInfo.setType_specific_info(decrypt.trim());
            KeyDataUtils.parseKeySpecificInfo(keyInfo, decrypt);
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        ClampBean clampBean = new ClampBean();
        clampBean.setClampNum("S1");
        clampBean.setClampSide("B");
        clampBean.setClampSlot("1");
        keyInfo.setClampKeyBasicData(clampBean);
        return keyInfo;
    }

    public KeyBasicDataItem(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = i;
        this.FK_KeyID = i2;
        this.type = i3;
        this.align = i4;
        this.width = i5;
        this.thick = i6;
        this.length = i7;
        this.row_count = i8;
        this.face = i9;
        this.row_pos = str;
        this.space = str2;
        this.space_width = str3;
        this.depth = str4;
        this.depth_name = str5;
        this.parameter_info = str6;
        this.Description = str7;
    }

    public KeyBasicDataItem() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
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

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String str) {
        this.Description = str;
    }
}
