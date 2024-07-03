package com.kkkcut.e20j.DbBean.userDB;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
/* loaded from: classes.dex */
public class KeyMarkingChild implements Serializable {
    @Id
    Long id;

    private static final long serialVersionUID = 7469059693947474974L;
    private float fontSize;
    private int height;

    @Generated(hash = 439588204)
    public KeyMarkingChild(Long id, float fontSize, int height, byte[] imageByte, int marginLeft, int marginTop,
            Long parentId, String text, int type, int width) {
        this.id = id;
        this.fontSize = fontSize;
        this.height = height;
        this.imageByte = imageByte;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.parentId = parentId;
        this.text = text;
        this.type = type;
        this.width = width;
    }
    private byte[] imageByte;
    private int marginLeft;
    private int marginTop;
    private Long parentId;
    private String text;
    private int type;
    private int width;

    public KeyMarkingChild(Long l, Long l2, int i, int i2, int i3, int i4, int i5, float f, String str, byte[] bArr) {
        this.id = l;
        this.parentId = l2;
        this.type = i;
        this.width = i2;
        this.height = i3;
        this.marginLeft = i4;
        this.marginTop = i5;
        this.fontSize = f;
        this.text = str;
        this.imageByte = bArr;
    }

    public KeyMarkingChild() {
    }

    public enum Identifier {
        TEXT,
        IMAGE
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long l) {
        this.parentId = l;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public void setMarginLeft(int i) {
        this.marginLeft = i;
    }

    public int getMarginTop() {
        return this.marginTop;
    }

    public void setMarginTop(int i) {
        this.marginTop = i;
    }

    public float getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(float f) {
        this.fontSize = f;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public byte[] getImageByte() {
        return this.imageByte;
    }

    public void setImageByte(byte[] bArr) {
        this.imageByte = bArr;
    }
}
