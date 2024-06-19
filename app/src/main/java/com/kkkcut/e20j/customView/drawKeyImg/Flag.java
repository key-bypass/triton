package com.kkkcut.e20j.customView.drawKeyImg;

/* loaded from: classes.dex */
public class Flag {
    private int column;
    private boolean isDicimal;
    private int rowPosition;

    public Flag(int i, int i2) {
        this.rowPosition = i;
        this.column = i2;
    }

    public Flag() {
    }

    public boolean isDicimal() {
        return this.isDicimal;
    }

    public void setDicimal(boolean z) {
        this.isDicimal = z;
    }

    public int getRowPosition() {
        return this.rowPosition;
    }

    public void setRowPosition(int i) {
        this.rowPosition = i;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int i) {
        this.column = i;
    }
}
