package com.kkkcut.e20j.DbBean.search;

import android.text.TextUtils;
import com.kkkcut.e20j.utils.DesUtil;
import com.cutting.machine.utils.KeyDataUtils;
import com.spl.key.SpecificParamUtils;

/* loaded from: classes.dex */
public class UsaSearchExtItemBasicData implements SearchResult {
    private int FK_SearchExtID;
    private int fkKeyID;
    private String name;
    private String parameter_info;
    private String space;

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getKeyBlankName() {
        return "";
    }

    public UsaSearchExtItemBasicData(int i, int i2, String str, String str2, String str3) {
        this.fkKeyID = i;
        this.FK_SearchExtID = i2;
        this.name = str;
        this.space = str2;
        this.parameter_info = str3;
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayKeyID() {
        String str = "";
        try {
            this.space = DesUtil.decrypt(this.space, DesUtil.DATABASE);
            String decrypt = DesUtil.decrypt(this.parameter_info, DesUtil.DATABASE);
            this.parameter_info = decrypt;
            if (!TextUtils.isEmpty(decrypt)) {
                str = SpecificParamUtils.INSTANCE.getParam(this.parameter_info, SpecificParamUtils.VARIABLE_SPACE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return this.fkKeyID + KeyDataUtils.getCutsBySpace(this.space);
        }
        return this.fkKeyID + "{" + str + "}";
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayName() {
        return this.name;
    }

    public int getFkKeyID() {
        return this.fkKeyID;
    }

    public void setFkKeyID(int i) {
        this.fkKeyID = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSpace() {
        return this.space;
    }

    public void setSpace(String str) {
        this.space = str;
    }

    public String getParameter_info() {
        return this.parameter_info;
    }

    public void setParameter_info(String str) {
        this.parameter_info = str;
    }

    public int getFK_SearchExtID() {
        return this.FK_SearchExtID;
    }

    public void setFK_SearchExtID(int i) {
        this.FK_SearchExtID = i;
    }
}
