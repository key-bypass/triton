package com.kkkcut.e20j.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BittingCode {
    String bitting;
    String code;
    String isn;

    @Generated(hash = 1556232697)
    public BittingCode(String bitting, String code, String isn) {
        this.bitting = bitting;
        this.code = code;
        this.isn = isn;
    }

    @Generated(hash = 496046989)
    public BittingCode() {
    }
    
    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getBitting() {
        return this.bitting;
    }

    public void setBitting(String str) {
        this.bitting = str;
    }

    public String getIsn() {
        return this.isn;
    }

    public void setIsn(String str) {
        this.isn = str;
    }
}
