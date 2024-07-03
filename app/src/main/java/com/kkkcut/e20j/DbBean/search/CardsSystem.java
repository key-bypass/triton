package com.kkkcut.e20j.DbBean.search;

import android.text.TextUtils;
import com.kkkcut.e20j.utils.DesUtil;
import com.kkkcut.e20j.utils.SpecificParamUtils;
import com.cutting.machine.utils.KeyDataUtils;

/* loaded from: classes.dex */
public class CardsSystem implements SearchResult {
    int KeyID;
    String parameter_info;
    String space;

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayName() {
        return "";
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getKeyBlankName() {
        return "";
    }

    public CardsSystem(int i, String str, String str2) {
        this.KeyID = i;
        this.space = str;
        this.parameter_info = str2;
    }

    public CardsSystem() {
    }

    @Override // com.kkkcut.e20j.DbBean.search.SearchResult
    public String getDisplayKeyID() {
        String str = "";
        try {
            this.space = DesUtil.decrypt(this.space, DesUtil.DATABASE);
            String decrypt = DesUtil.decrypt(this.parameter_info, DesUtil.DATABASE);
            this.parameter_info = decrypt;
            if (!TextUtils.isEmpty(decrypt)) {
                str = SpecificParamUtils.getParam(this.parameter_info, SpecificParamUtils.VARIABLE_SPACE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str)) {
            return this.KeyID + KeyDataUtils.getCutsBySpace(this.space);
        }
        return this.KeyID + "{" + str + "}";
    }

    public int getKeyID() {
        return this.KeyID;
    }

    public void setKeyID(int i) {
        this.KeyID = i;
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
}
