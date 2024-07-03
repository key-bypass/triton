package com.kkkcut.e20j.DbBean;

import com.kkkcut.e20j.utils.DesUtil;
import com.cutting.machine.bean.ClampBean;
import com.cutting.machine.bean.KeyInfo;
import com.cutting.machine.utils.KeyDataUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity
/* loaded from: classes.dex */
public class KeyBasicDataItem {
    String Description;

    int FK_KeyID;
    int align;
    String depth;
    String depth_name;
    int face;

    @Id
    long id;
    int length;
    String parameter_info;
    int row_count;
    String row_pos;
    String space;
    String space_width;
    int thick;
    int type;
    int width;


    @Generated(hash = 396570674)
    public KeyBasicDataItem(String Description, int FK_KeyID, int align, String depth, String depth_name, int face, long id, int length, String parameter_info,
            int row_count, String row_pos, String space, String space_width, int thick, int type, int width) {
        this.Description = Description;
        this.FK_KeyID = FK_KeyID;
        this.align = align;
        this.depth = depth;
        this.depth_name = depth_name;
        this.face = face;
        this.id = id;
        this.length = length;
        this.parameter_info = parameter_info;
        this.row_count = row_count;
        this.row_pos = row_pos;
        this.space = space;
        this.space_width = space_width;
        this.thick = thick;
        this.type = type;
        this.width = width;
    }

    @Generated(hash = 505438139)
    public KeyBasicDataItem() {
    }


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

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }


    public int getAlign() {
        return this.align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public String getDepth() {
        return this.depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getDepth_name() {
        return this.depth_name;
    }

    public void setDepth_name(String depth_name) {
        this.depth_name = depth_name;
    }

    public int getFace() {
        return this.face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getParameter_info() {
        return this.parameter_info;
    }

    public void setParameter_info(String parameter_info) {
        this.parameter_info = parameter_info;
    }

    public int getRow_count() {
        return this.row_count;
    }

    public void setRow_count(int row_count) {
        this.row_count = row_count;
    }

    public String getRow_pos() {
        return this.row_pos;
    }

    public void setRow_pos(String row_pos) {
        this.row_pos = row_pos;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getSpace_width() {
        return this.space_width;
    }

    public void setSpace_width(String space_width) {
        this.space_width = space_width;
    }

    public int getThick() {
        return this.thick;
    }

    public void setThick(int thick) {
        this.thick = thick;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setFK_KeyID(int FK_KeyID) {
        this.FK_KeyID = FK_KeyID;
    }

}
