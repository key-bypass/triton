package com.kkkcut.e20j.DbBean.search;

import android.text.TextUtils;

import com.cutting.machine.utils.KeyDataUtils;
import com.kkkcut.e20j.utils.DesUtil;
import com.spl.key.SpecificParamUtils;

/* loaded from: classes.dex */
public class KeyBlankItemSearch implements SearchResult {
    private int FK_KeyID;
    private String KeyblankItemName;
    private String KeyblankName;
    private String parameter_info;
    private String space;

    public KeyBlankItemSearch(int i, String str, String str2, String str3, String str4) {
        this.FK_KeyID = i;
        this.KeyblankItemName = str;
        this.KeyblankName = str2;
        this.space = str3;
        this.parameter_info = str4;
    }

    public String getKeyblankName() {
        return this.KeyblankName;
    }

    public void setKeyblankName(String str) {
        this.KeyblankName = str;
    }

    public int getFK_KeyID() {
        return this.FK_KeyID;
    }

    public void setFK_KeyID(int i) {
        this.FK_KeyID = i;
    }

    public String getKeyblankItemName() {
        return this.KeyblankItemName;
    }

    public void setKeyblankItemName(String str) {
        this.KeyblankItemName = str;
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
            return this.FK_KeyID + KeyDataUtils.getCutsBySpace(this.space);
        }
        return this.FK_KeyID + "{" + str + "}";
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayName() {
        return this.KeyblankItemName;
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getKeyBlankName() {
        return this.KeyblankName;
    }
}
