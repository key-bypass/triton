package com.kkkcut.e20j.DbBean.userDB;

import java.io.Serializable;

/* loaded from: classes.dex */
public class KeyMarkingChild implements Serializable {
    public static final int IMAGE = 2;
    public static final int TEXT = 1;
    private static final long serialVersionUID = 7469059693947474974L;
    private float fontSize;
    private int height;
    Long id;
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
